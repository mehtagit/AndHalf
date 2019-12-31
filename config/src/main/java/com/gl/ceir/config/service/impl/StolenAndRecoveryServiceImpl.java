package com.gl.ceir.config.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.SingleImeiDetails;
import com.gl.ceir.config.model.SingleImeiHistoryDb;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.StolenAndRecoveryHistoryMgmt;
import com.gl.ceir.config.model.StolenandRecoveryMgmt;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.ConsignmentStatus;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.StockStatus;
import com.gl.ceir.config.model.constants.StolenStatus;
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.model.file.StolenAndRecoveryFileModel;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.ImmegreationImeiDetailsRepository;
import com.gl.ceir.config.repository.SingleImeiHistoryDbRepository;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryHistoryMgmtRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.StolenAndRecoverySpecificationBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class StolenAndRecoveryServiceImpl {

	private static final Logger logger = LogManager.getLogger(StolenAndRecoveryServiceImpl.class);

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StolenAndRecoveryRepository stolenAndRecoveryRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	StolenAndRecoveryHistoryMgmtRepository stolenAndRecoveryHistoryMgmtRepository;

	@Autowired
	StockManagementRepository distributerManagementRepository;

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	SingleImeiHistoryDbRepository singleImeiHistoryDbRepository;

	@Autowired
	ImmegreationImeiDetailsRepository immegreationImeiDetailsRepository;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl; 

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;

	@Transactional
	public GenricResponse uploadDetails( StolenandRecoveryMgmt stolenandRecoveryDetails) {

		try {
			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(Integer.toString(stolenandRecoveryDetails.getRequestType()));
			webActionDb.setSubFeature("upload");
			webActionDb.setTxnId(stolenandRecoveryDetails.getTxnId());
			webActionDb.setState(0);

			stolenAndRecoveryRepository.save(stolenandRecoveryDetails);

			webActionDbRepository.save(webActionDb);

			return new GenricResponse(0,"Upload Successfully.", stolenandRecoveryDetails.getTxnId());

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	public GenricResponse v2uploadDetails(StolenandRecoveryMgmt stolenandRecoveryDetails) {

		try {
			// Single = 4
			if(stolenandRecoveryDetails.getSourceType() == 4){
				SingleImeiDetails singleImeiDetails = new SingleImeiDetails();	
				singleImeiDetails.setFirstImei(stolenandRecoveryDetails.getImei());
				singleImeiDetails.setsARm(stolenandRecoveryDetails);
				stolenandRecoveryDetails.setSingleImeiDetails(singleImeiDetails);

			}

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(""/*stolenandRecoveryDetails.getRequestType() */);
			webActionDb.setSubFeature(WebActionDbSubFeature.UPLOAD.getName());
			webActionDb.setTxnId(stolenandRecoveryDetails.getTxnId());
			webActionDb.setState(WebActionDbState.INIT.getCode());


			stolenandRecoveryDetails.setFileStatus(WebActionDbState.INIT.getCode());
			stolenAndRecoveryRepository.save(stolenandRecoveryDetails);

			webActionDbRepository.save(webActionDb);

			return new GenricResponse(0,"Upload Successfully.",stolenandRecoveryDetails.getTxnId());

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public Page<StolenandRecoveryMgmt> getAllInfo(FilterRequest filterRequest, Integer pageNo, Integer pageSize){

		List<SystemConfigListDb> sourceTypes = null;
		List<SystemConfigListDb> requestTypes = null;
		List<StateMgmtDb> stateInterpList = null;
		List<StateMgmtDb> statusList = null;

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());

			StolenAndRecoverySpecificationBuilder srsb = new StolenAndRecoverySpecificationBuilder(propertiesReader.dialect);

			if(Objects.nonNull(filterRequest.getUserId()))
				srsb.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
				srsb.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
				srsb.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getTxnId()) && !filterRequest.getTxnId().isEmpty())
				srsb.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getRoleType()))
				srsb.with(new SearchCriteria("roleType", filterRequest.getRoleType(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getRequestType()))
				srsb.with(new SearchCriteria("requestType", filterRequest.getRequestType(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getSourceType())) 
				srsb.with(new SearchCriteria("sourceType", filterRequest.getRequestType(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
				srsb.with(new SearchCriteria("fileStatus", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			}else {
				if(Objects.nonNull(filterRequest.getFeatureId()) && Objects.nonNull(filterRequest.getUserTypeId())) {

					List<Integer> configuredStatus = new LinkedList<Integer>();
					// featureList =	stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(consignmentMgmt.getFeatureId(), consignmentMgmt.getUserTypeId());
					logger.debug(statusList);

					if(Objects.nonNull(statusList)) {	
						for(StateMgmtDb stateDb : statusList ) {
							configuredStatus.add(stateDb.getState());
						}
						logger.info("Array list to add is = " + configuredStatus);

						srsb.addSpecification(srsb.in(new SearchCriteria("consignmentStatus", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.INT), configuredStatus));
					}
				}
			}

			Page<StolenandRecoveryMgmt> stolenandRecoveryMgmtPage = stolenAndRecoveryRepository.findAll(srsb.build(), pageable);
			stateInterpList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info(stateInterpList);

			sourceTypes = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.SOURCE_TYPE); 
			logger.info(sourceTypes);

			requestTypes = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.REQ_TYPE); 
			logger.info(requestTypes);

			for(StolenandRecoveryMgmt stolenandRecoveryMgmt : stolenandRecoveryMgmtPage.getContent()) {

				for(SystemConfigListDb configListDb : sourceTypes) {
					if(stolenandRecoveryMgmt.getSourceType() == configListDb.getValue()) {
						stolenandRecoveryMgmt.setSourceTypeInterp(configListDb.getInterp());
						break;
					}
				}

				for(SystemConfigListDb configListDb : requestTypes) {
					if(stolenandRecoveryMgmt.getRequestType() == configListDb.getValue()) {
						stolenandRecoveryMgmt.setRequestTypeInterp(configListDb.getInterp());
						break;
					}
				}

				for(StateMgmtDb stateMgmtDb : stateInterpList) {
					if(stolenandRecoveryMgmt.getFileStatus() == stateMgmtDb.getState()) {
						stolenandRecoveryMgmt.setStateInterp(stateMgmtDb.getInterp()); 
						break;
					}
				}
			}

			logger.info(stolenandRecoveryMgmtPage.getContent());
			return stolenandRecoveryMgmtPage;

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}

	public FileDetails getFilteredStolenAndRecoveryInFile(FilterRequest filterRequest, Integer pageNo, Integer pageSize) {
		String fileName = null;
		Writer writer   = null;
		StolenAndRecoveryFileModel srfm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String filePath  = fileStorageProperties.getStolenAndRecoveryDownloadDir();

		StatefulBeanToCsvBuilder<StolenAndRecoveryFileModel> builder = null;
		StatefulBeanToCsv<StolenAndRecoveryFileModel> csvWriter = null;
		List< StolenAndRecoveryFileModel > fileRecords = null;

		// HeaderColumnNameTranslateMappingStrategy<GrievanceFileModel> mapStrategy = null;
		try {
			List<StolenandRecoveryMgmt> stolenandRecoveryMgmts = getAllInfo(filterRequest, pageNo, pageSize).getContent();

			if( !stolenandRecoveryMgmts.isEmpty() ) {
				if(Objects.nonNull(filterRequest.getUserId()) && (filterRequest.getUserId() != -1 && filterRequest.getUserId() != 0)) {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_StolenAndRecovery.csv";
				}else {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_StolenAndRecovery.csv";
				}
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_StolenAndRecovery.csv";
			}

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<StolenAndRecoveryFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

			if( !stolenandRecoveryMgmts.isEmpty() ) {

				fileRecords = new ArrayList<>();
				// List<SystemConfigListDb> customTagStatusList = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.CUSTOMS_TAX_STATUS);

				for( StolenandRecoveryMgmt stolenandRecoveryMgmt : stolenandRecoveryMgmts ) {
					srfm = new StolenAndRecoveryFileModel();

					srfm.setStolenId(stolenandRecoveryMgmt.getId());
					srfm.setStolenStatus(stolenandRecoveryMgmt.getStateInterp());
					srfm.setTxnId( stolenandRecoveryMgmt.getTxnId());
					srfm.setCreatedOn(stolenandRecoveryMgmt.getCreatedOn().format(dtf));
					srfm.setModifiedOn( stolenandRecoveryMgmt.getModifiedOn().format(dtf));
					srfm.setFileName( stolenandRecoveryMgmt.getFileName());

					logger.debug(srfm);

					fileRecords.add(srfm);
				}

				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath, fileStorageProperties.getStolenAndRecoveryDownloadLink() + fileName ); 

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
	public GenricResponse uploadMultipleStolen(List<StolenandRecoveryMgmt> stolenandRecoveryMgmt) {
		try {

			for(StolenandRecoveryMgmt  request : stolenandRecoveryMgmt) {

				// Consignment = 0
				// Stock = 1
				if(request.getSourceType() == 0){
					ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(request.getTxnId());

					// Stolen = 0
					if(request.getRequestType() == 0) {
						consignmentMgmt.setPreviousConsignmentStatus(consignmentMgmt.getConsignmentStatus());
						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.STOLEN.getCode());
					}else {
						consignmentMgmt.setConsignmentStatus(consignmentMgmt.getPreviousConsignmentStatus());
					}

					consignmentRepository.save(consignmentMgmt);
				}else if(request.getSourceType() == 1) {

					StockMgmt stockMgmt = distributerManagementRepository.findByRoleTypeAndTxnId(request.getRoleType(), request.getTxnId());

					if(request.getRequestType() == 0) {
						stockMgmt.setPreviousStockStatus(stockMgmt.getStockStatus());
						stockMgmt.setStockStatus(StockStatus.STOLEN.getCode());
					}else {
						stockMgmt.setStockStatus(stockMgmt.getPreviousStockStatus());
					}
					distributerManagementRepository.save(stockMgmt);
				}

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setState(0);
				webActionDb.setFeature(Integer.toString(request.getRequestType()));				
				webActionDb.setSubFeature("Upload");
				webActionDb.setData(request.getTxnId());

				webActionDbRepository.save(webActionDb);

			}

			stolenAndRecoveryRepository.saveAll(stolenandRecoveryMgmt);

			return new GenricResponse(0, "Upload SucessFully", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	@Transactional
	public GenricResponse deleteRecord(StolenandRecoveryMgmt stolenandRecoveryMgmt) {

		try {
			StolenandRecoveryMgmt stolenandRecoveryMgmtInfo =stolenAndRecoveryRepository.getById(stolenandRecoveryMgmt.getId());
			if(stolenandRecoveryMgmtInfo == null) {

				return new GenricResponse(4,"TxnId Does Not exist", stolenandRecoveryMgmt.getTxnId());
			}
			else {
				StolenAndRecoveryHistoryMgmt historyMgmt = new  StolenAndRecoveryHistoryMgmt();
				historyMgmt.setBlockingTimePeriod(stolenandRecoveryMgmtInfo.getBlockingTimePeriod());
				historyMgmt.setBlockingType("" /* stolenandRecoveryMgmtInfo.getBlockingType() */);
				historyMgmt.setFileName(stolenandRecoveryMgmtInfo.getFileName());
				historyMgmt.setFileStatus(stolenandRecoveryMgmtInfo.getFileStatus());
				historyMgmt.setRequestType(stolenandRecoveryMgmtInfo.getRequestType());
				historyMgmt.setRoleType(stolenandRecoveryMgmtInfo.getRoleType());
				historyMgmt.setTxnId(stolenandRecoveryMgmtInfo.getTxnId());
				historyMgmt.setUserId(stolenandRecoveryMgmtInfo.getUserId());
				historyMgmt.setSourceType(stolenandRecoveryMgmtInfo.getSourceType());

				// 4 = Single
				if (stolenandRecoveryMgmt.getSourceType() == 4){

					SingleImeiHistoryDb singleImeiHistoryDb = new SingleImeiHistoryDb();
					singleImeiHistoryDb.setImei(stolenandRecoveryMgmtInfo.getSingleImeiDetails().getFirstImei());
					singleImeiHistoryDb.setProcessState(stolenandRecoveryMgmtInfo.getSingleImeiDetails().getProcessState());
					singleImeiHistoryDb.setTxnId(stolenandRecoveryMgmt.getId());

					singleImeiHistoryDbRepository.save(singleImeiHistoryDb);
					//immegreationImeiDetailsRepository.deleteById(stolenandRecoveryMgmtInfo.getSingleImeiDetails().getId());

				}

				stolenAndRecoveryHistoryMgmtRepository.save(historyMgmt);
				stolenAndRecoveryRepository.deleteById(stolenandRecoveryMgmt.getId());

				return new GenricResponse(0,"Record Delete Sucessfully", stolenandRecoveryMgmt.getTxnId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	@Transactional
	public GenricResponse updateRecord(StolenandRecoveryMgmt stolenandRecoveryMgmt) {

		try {
			StolenandRecoveryMgmt stolenandRecoveryMgmtInfo = stolenAndRecoveryRepository.getById(stolenandRecoveryMgmt.getId());
			if(stolenandRecoveryMgmtInfo == null) {

				return new GenricResponse(4,"TxnId Does Not exist", stolenandRecoveryMgmt.getTxnId());
			}else {

				StolenAndRecoveryHistoryMgmt historyMgmt = new  StolenAndRecoveryHistoryMgmt();
				historyMgmt.setBlockingTimePeriod(stolenandRecoveryMgmtInfo.getBlockingTimePeriod());
				historyMgmt.setBlockingType(stolenandRecoveryMgmtInfo.getBlockingType());
				historyMgmt.setFileName(stolenandRecoveryMgmtInfo.getFileName());
				historyMgmt.setFileStatus(stolenandRecoveryMgmtInfo.getFileStatus());
				historyMgmt.setRequestType(stolenandRecoveryMgmtInfo.getRequestType());
				historyMgmt.setRoleType(stolenandRecoveryMgmtInfo.getRoleType());
				historyMgmt.setTxnId(stolenandRecoveryMgmtInfo.getTxnId());
				historyMgmt.setUserId(stolenandRecoveryMgmtInfo.getUserId());
				historyMgmt.setSourceType(stolenandRecoveryMgmtInfo.getSourceType());

				stolenAndRecoveryHistoryMgmtRepository.save(historyMgmt);

				// 0 = Stolen
				if (stolenandRecoveryMgmt.getRequestType() == 0){
					stolenandRecoveryMgmtInfo.setBlockingTimePeriod(stolenandRecoveryMgmt.getBlockingTimePeriod());
					stolenandRecoveryMgmtInfo.setBlockingType(stolenandRecoveryMgmt.getBlockingType());
				}

				stolenandRecoveryMgmtInfo.setFileName(stolenandRecoveryMgmt.getFileName());
				stolenandRecoveryMgmtInfo.setFileStatus(StolenStatus.INIT.getCode());

				stolenAndRecoveryRepository.save(stolenandRecoveryMgmtInfo);

				return new GenricResponse(0, "Record update sucessfully", stolenandRecoveryMgmt.getTxnId());
			}
		}catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public StolenandRecoveryMgmt viewRecord(StolenandRecoveryMgmt stolenandRecoveryMgmt) {
		try {
			return stolenAndRecoveryRepository.getById(stolenandRecoveryMgmt.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public StolenandRecoveryMgmt getByTxnId(StolenandRecoveryMgmt stolenandRecoveryMgmt) {
		try {
			return stolenAndRecoveryRepository.getByTxnId(stolenandRecoveryMgmt.getTxnId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public ResponseCountAndQuantity getStolenAndRecoveryCount( long userId, Integer userTypeId, Integer featureId, String requestType, String userType ) {
		List<StateMgmtDb> featureList = null;
		List<Integer> status = new ArrayList<Integer>();
		try {
			logger.info("Going to get StolenAndRecovery count.");
			featureList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId( featureId, userTypeId);
			if(Objects.nonNull(featureList)) {	
				for(StateMgmtDb stateDb : featureList ) {
					status.add(stateDb.getState());
				}
			}
			if( !userType.equalsIgnoreCase("ceiradmin"))
				return stolenAndRecoveryRepository.getStolenandRecoveryCount( userId, status, Integer.valueOf(requestType));
			else
				return stolenAndRecoveryRepository.getStolenandRecoveryCount( status, Integer.valueOf(requestType));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
			return new ResponseCountAndQuantity(0,0);
		}
	}
}
