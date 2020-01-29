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

import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.EndUserDB;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.file.EndUserFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.EndUserDbRepository;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class EnduserServiceImpl {

	private static final Logger logger = LogManager.getLogger(EnduserServiceImpl.class);

	@Autowired
	EndUserDbRepository endUserDbRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	public GenricResponse endUserByNid(String nid) {
		try {
			EndUserDB endUserDB = endUserDbRepository.getByNid(nid);

			// End user is not registered with CEIR system.
			if(Objects.nonNull(endUserDB)) {
				logger.info("End User with nid [" + nid + "] does exist.");
				return new GenricResponse(1, "End User does exist.", nid);
			}else {
				logger.info("End User with nid [" + nid + "] does not exist.");
				return new GenricResponse(0, "User does not exist.", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	public GenricResponse updateVisaEndUser(EndUserDB endUserDB) {
		try {
			if(Objects.isNull(endUserDB.getNid())) {
				logger.info("Request should not have nid as null.");
				return new GenricResponse(2, "NULL_NID", "Request should not have nid as null.", null);
			}

			EndUserDB endUserDB1 = endUserDbRepository.getByNid(endUserDB.getNid());

			// End user is not registered with CEIR system.
			if(Objects.nonNull(endUserDB1)) {
				logger.info("Going to update VISA of user. " + endUserDB1);

				// VisaDb visaDb = endUserDB1.getVisaDb();
				/*
				 * if(Objects.isNull(visaDb)) { return new GenricResponse(3,
				 * "VISA_UPDATE_NOT_ALLOWED", "You are not allowed to update Visa.",
				 * endUserDB.getNid()); }else {
				 * endUserDB1.getVisaDb().setVisaExpiryDate(visaDb.getVisaExpiryDate()); }
				 */

				endUserDbRepository.save(endUserDB1);
				logger.info("Visa of user have been updated succesfully." +  endUserDB1);
				return new GenricResponse(0, "VISA_UPDATE_SUCCESS", "Visa of user have been updated succesfully.", endUserDB.getNid());
			}else {
				return new GenricResponse(1, "INVALID_USER", "User does not exist.", endUserDB.getNid());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("End user Service", e.getMessage());
		}
	}

	public Page<EndUserDB> filter(FilterRequest filterRequest, Integer pageNo, 
			Integer pageSize) {

		List<StateMgmtDb> statusList = null;

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			// statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(consignmentMgmt.getFeatureId(), consignmentMgmt.getUserTypeId());

			Page<EndUserDB> page = endUserDbRepository.findAll(buildSpecification(filterRequest, statusList).build(), pageable);

			for(EndUserDB endUserDB : page.getContent()) {
				setInterp(endUserDB);
			}

			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), "", 
					Long.valueOf(filterRequest.getUserTypeId()), filterRequest.getUserType(), 
					Long.valueOf(filterRequest.getFeatureId()),
					Features.MANAGE_USER, SubFeatures.VIEW, ""));
			logger.info("AUDIT : Saved view request in audit.");
			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private List<EndUserDB> getAll(FilterRequest filterRequest){
		try {

			// List<StateMgmtDb> statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			// logger.info("statusList " + statusList);

			List<EndUserDB> endUserDBs = endUserDbRepository.findAll(buildSpecification(filterRequest, null).build(), new Sort(Sort.Direction.DESC, "modifiedOn"));
			logger.info("endUserDBs " + endUserDBs);

			for(EndUserDB endUserDB : endUserDBs) {
				setInterp(endUserDB);
			}

			logger.info("endUserDBs : " + endUserDBs);
			return endUserDBs;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public FileDetails getFilteredEndUserInFileV2(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		//String[] columns = new String[]{"grievanceId","userId","userType","grievanceStatus","txnId","categoryId","fileName","createdOn","modifiedOn","remarks"};
		EndUserFileModel fm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_end_user_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_end_user_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");

		StatefulBeanToCsvBuilder<EndUserFileModel> builder = null;
		StatefulBeanToCsv<EndUserFileModel> csvWriter      = null;
		List< EndUserFileModel> fileRecords                = null;
		CustomMappingStrategy<EndUserFileModel> mappingStrategy = new CustomMappingStrategy<>();

		try {
			List<EndUserDB> endUserDBs = getAll(filterRequest);

			fileName = LocalDateTime.now().format(dtf2).replace(" ", "_")+"_User.csv";
			writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
			mappingStrategy.setType(EndUserFileModel.class);

			builder = new StatefulBeanToCsvBuilder<EndUserFileModel>(writer);
			csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( endUserDBs.size() > 0 ) {
				fileRecords = new ArrayList<>();
				// TODO

				for( EndUserDB endUserDB : endUserDBs ) { 
					fm = new EndUserFileModel(consignmentMgmt.getStateInterp(),
							consignmentMgmt.getTxnId(), consignmentMgmt.getSupplierName(),
							consignmentMgmt.getTaxInterp(), consignmentMgmt.getFileName(),
							consignmentMgmt.getCreatedOn().format(dtf),
							consignmentMgmt.getModifiedOn().format(dtf));

					fileRecords.add(fm); 
				}


				csvWriter.write(fileRecords);
			}else {
				csvWriter.write( new EndUserFileModel());
			}

			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), "", 
					Long.valueOf(filterRequest.getUserTypeId()), filterRequest.getUserType(), 
					Long.valueOf(filterRequest.getFeatureId()),
					Features.CONSIGNMENT, SubFeatures.VIEW, ""));
			logger.info("AUDIT : Saved file export request in audit.");

			return new FileDetails( fileName, filepath.getValue(), link.getValue() + fileName );
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( writer != null )
					writer.close();
			} catch (IOException e) {}
		}
	}

	private SpecificationBuilder<EndUserDB> buildSpecification(FilterRequest filterRequest, List<StateMgmtDb> statusList){
		SpecificationBuilder<EndUserDB> specificationBuilder = new SpecificationBuilder<>(propertiesReader.dialect);


		if(Objects.nonNull(filterRequest.getNid()))
			specificationBuilder.with(new SearchCriteria("nid", filterRequest.getNid(), SearchOperation.EQUALITY, Datatype.STRING));

		if("IMMIGRATION".equalsIgnoreCase(filterRequest.getOrigin())) {
			List<String> notInList = new ArrayList<>();
			notInList.add("Cambodian");

			specificationBuilder.notIn("nationality", notInList);
		}

		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			specificationBuilder.orSearch(new SearchCriteria("nid", filterRequest.getNid(), SearchOperation.LIKE, Datatype.STRING));
		}

		return specificationBuilder;
	}

	private void setInterp(EndUserDB endUserDB) {

	}
}