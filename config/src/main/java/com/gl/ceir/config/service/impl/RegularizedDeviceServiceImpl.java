package com.gl.ceir.config.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.Count;
import com.gl.ceir.config.model.EndUserDB;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.model.RegularizeDeviceHistoryDb;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.RegularizeDeviceStatus;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.model.file.RegularizeDeviceFileModel;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.CustomDetailsRepository;
import com.gl.ceir.config.repository.EndUserDbRepository;
import com.gl.ceir.config.repository.RegularizeDeviceHistoryDbRepository;
import com.gl.ceir.config.repository.RegularizedDeviceDbRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.StatusSetter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class RegularizedDeviceServiceImpl {

	private static final Logger logger = LogManager.getLogger(RegularizedDeviceServiceImpl.class);

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	CustomDetailsRepository customDetailsRepository;

	@Autowired
	EndUserDbRepository endUserDbRepository;

	@Autowired
	RegularizedDeviceDbRepository regularizedDeviceDbRepository;

	@Autowired
	RegularizeDeviceHistoryDbRepository userCustomHistoryDbRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;
	
	@Autowired
	InterpSetter interpSetter;
	
	@Autowired
	StatusSetter statusSetter;

	public Page<RegularizeDeviceDb> filter(FilterRequest filterRequest, Integer pageNo, Integer pageSize){
		
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			System.out.println("dialect : " + propertiesReader.dialect);

			SpecificationBuilder<RegularizeDeviceDb> specificationBuilder = new SpecificationBuilder<RegularizeDeviceDb>(propertiesReader.dialect);

			if(Objects.nonNull(filterRequest.getNid()))
				specificationBuilder.with(new SearchCriteria("nid", filterRequest.getNid(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
				specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
				specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getDeviceIdType()))
				specificationBuilder.with(new SearchCriteria("deviceIdType", filterRequest.getDeviceIdType(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getDeviceType()))
				specificationBuilder.with(new SearchCriteria("deviceType", filterRequest.getDeviceType(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getTaxPaidStatus()))
				specificationBuilder.with(new SearchCriteria("taxPaidStatus", filterRequest.getTaxPaidStatus(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
				specificationBuilder.with(new SearchCriteria("status", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			}
			
			Page<RegularizeDeviceDb> page = regularizedDeviceDbRepository.findAll(specificationBuilder.build(), pageable);

			for(RegularizeDeviceDb regularizeDeviceDb : page.getContent()) {

				regularizeDeviceDb.setTaxPaidStatusInterp(interpSetter.setInterp(Tags.CUSTOMS_TAX_STATUS, regularizeDeviceDb.getTaxPaidStatus()));

				regularizeDeviceDb.setDeviceIdTypeInterp(interpSetter.setInterp(Tags.DEVICE_ID_TYPE, regularizeDeviceDb.getDeviceIdType()));
				
				regularizeDeviceDb.setDeviceTypeInterp(interpSetter.setInterp(Tags.DEVICE_TYPE, regularizeDeviceDb.getDeviceType()));
				
				regularizeDeviceDb.setDeviceStatusInterp(interpSetter.setInterp(Tags.DEVICE_STATUS, regularizeDeviceDb.getDeviceStatus()));
			}

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Regularized Device Service", e.getMessage());
		}
	}

	public FileDetails getFilteredDeviceInFile(FilterRequest filterRequest, Integer pageNo, Integer pageSize) {
		String fileName = null;
		Writer writer   = null;
		RegularizeDeviceFileModel rdfm = null;
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		String filePath  = fileStorageProperties.getRegularizeDeviceDownloadDir();
		StatefulBeanToCsvBuilder<RegularizeDeviceFileModel> builder = null;
		StatefulBeanToCsv<RegularizeDeviceFileModel> csvWriter = null;
		List< RegularizeDeviceFileModel > fileRecords = null;

		try {
			List<RegularizeDeviceDb> regularizeDevices = filter(filterRequest, pageNo, pageSize).getContent();

			if( !regularizeDevices.isEmpty() ) {
				if(Objects.nonNull(filterRequest.getUserId()) && (filterRequest.getUserId() != -1 && filterRequest.getUserId() != 0)) {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_" + "_RegularizeDevices.csv";
				}else {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_RegularizeDevices.csv";
				}
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_RegularizeDevices.csv";
			}

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<RegularizeDeviceFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

			if( !regularizeDevices.isEmpty() ) {

				List<SystemConfigListDb> currencyList = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.CURRENCY);

				fileRecords = new ArrayList<>(); 

				for(RegularizeDeviceDb regularizeDeviceDb : regularizeDevices ) {
					rdfm = new RegularizeDeviceFileModel();

					rdfm.setCreatedOn(regularizeDeviceDb.getCreatedOn().format(dtf));
					rdfm.setDeviceIdTypeInterp(regularizeDeviceDb.getDeviceIdTypeInterp());
					rdfm.setDeviceTypeInterp(regularizeDeviceDb.getDeviceTypeInterp());
					rdfm.setPrice( regularizeDeviceDb.getPrice());

					for(SystemConfigListDb systemConfigListDb : currencyList) {
						if(regularizeDeviceDb.getCurrency() == systemConfigListDb.getValue()) {
							rdfm.setCurrency(systemConfigListDb.getInterp()); 
							break;
						} 
					}
					rdfm.setCountry(regularizeDeviceDb.getCountry());
					rdfm.setTaxPaidStatus(regularizeDeviceDb.getTaxPaidStatusInterp());

					logger.debug(rdfm);

					fileRecords.add(rdfm);
				}

				csvWriter.write(fileRecords);
			}
			return new FileDetails(fileName, filePath, fileStorageProperties.getRegularizeDeviceDownloadLink() + fileName ); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( Objects.nonNull(writer) )
					writer.close();
			} catch (IOException e) {}
		}
	}

	@Transactional
	public GenricResponse saveDevices(EndUserDB endUserDB) {
		try {

			EndUserDB endUserDB2 = endUserDbRepository.getByNid(endUserDB.getNid());

			statusSetter.setStatus(endUserDB.getRegularizeDeviceDbs(), RegularizeDeviceStatus.PENDING_APPROVAL_FROM_CEIR_ADMIN.getCode());
			// End user is not registered with CEIR system.
			if(Objects.isNull(endUserDB2)) {
				endUserDbRepository.save(endUserDB);
			}else {
				// TODO Validation required.
				regularizedDeviceDbRepository.saveAll(endUserDB.getRegularizeDeviceDbs());
			}
			
			return new GenricResponse(0, "User register sucessfully", endUserDB.getRegularizeDeviceDbs().get(0).getTxnId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	public GenricResponse updateTaxStatus( RegularizeDeviceDb regularizeDeviceDb) {
		try {
			RegularizeDeviceDb userCustomDbDetails = regularizedDeviceDbRepository.getByFirstImei(regularizeDeviceDb.getFirstImei());

			if(Objects.nonNull(userCustomDbDetails)) {
				
				userCustomDbDetails.setTaxPaidStatus(regularizeDeviceDb.getTaxPaidStatus());
				regularizedDeviceDbRepository.save(userCustomDbDetails);
				return new GenricResponse(0, "Update Successfully.",userCustomDbDetails.getDeviceSerialNumber());

			}else {
				return  new GenricResponse(4,"TxnId Does Not exist.", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());}
	}

	public RegularizeDeviceDb viewDeviceInfoByImei1(long imei) {
		try {

			return regularizedDeviceDbRepository.getByFirstImei(imei);
			
			/*if(userDetails != null) {
				return userDetails;
			}else {

				RegularizeDeviceDb deviceDbFetchDetails = new  RegularizeDeviceDb();
				List<DeviceDb> deviceDb = stokeDetailsRepository.getByDeviceNumber(UserCustomDb.getDeviceSerialNumber());

				for(int i=0;i < deviceDb.size();i++) {
					if(i == 0) {
						deviceDbFetchDetails.setFirstImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
						deviceDbFetchDetails.setMultiSimStatus(deviceDb.get(i).getMultipleSimStatus());
						deviceDbFetchDetails.setNid(deviceDb.get(i).getEndUserUserId());
						deviceDbFetchDetails.setTaxPaidStatus(deviceDb.get(i).getEndUserDeviceStatus());
						deviceDbFetchDetails.setTxnId(deviceDb.get(i).getEndUserTxnId());
						deviceDbFetchDetails.setCountry(deviceDb.get(i).getEndUserCountry());
						// deviceDbFetchDetails.setDeviceType(deviceDb.get(i).getDeviceType());
					}
					if(i == 1) {
						deviceDbFetchDetails.setSecondImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}
					if(i == 2) {
						deviceDbFetchDetails.setThirdImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}
					if(i == 3) {
						deviceDbFetchDetails.setFourthImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}

				}

				return deviceDbFetchDetails;
			}*/
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}

	}

	@Transactional
	public GenricResponse deleteCustomInfo(Long imei) {
		try {
			RegularizeDeviceDb regularizeDeviceDb = regularizedDeviceDbRepository.getByFirstImei(imei);
			
			if(Objects.nonNull(regularizeDeviceDb)) {
				
				regularizedDeviceDbRepository.deleteById(regularizeDeviceDb.getId());

				userCustomHistoryDbRepository.save(new RegularizeDeviceHistoryDb(regularizeDeviceDb.getNid(), regularizeDeviceDb.getDeviceStatus(), regularizeDeviceDb.getFirstImei(), 
						regularizeDeviceDb.getSecondImei(), regularizeDeviceDb.getThirdImei(), regularizeDeviceDb.getFourthImei(), regularizeDeviceDb.getTaxPaidStatus(),
						regularizeDeviceDb.getDeviceType(), regularizeDeviceDb.getDeviceIdType(), regularizeDeviceDb.getMultiSimStatus(), regularizeDeviceDb.getCountry(),
						regularizeDeviceDb.getDeviceSerialNumber()));

				return new GenricResponse(0, "Device have been deleted sucessfully.", Long.toString(regularizeDeviceDb.getFirstImei()));
			}else {

				return new GenricResponse(4, "This IMEI does not exist.", Long.toString(regularizeDeviceDb.getFirstImei()));	
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}
	
	public GenricResponse getCountOfRegularizedDevicesByNid(String nid) {
		try {
			return new GenricResponse(0, "", "", new Count(propertiesReader.defaultNoOfRegularizedDevices, regularizedDeviceDbRepository.countByNid(nid)));	
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}
	
}
