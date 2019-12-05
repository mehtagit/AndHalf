package com.gl.ceir.config.service.impl;

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
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RequestCountAndQuantityWithLongUserId;
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
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.ImmegreationImeiDetailsRepository;
import com.gl.ceir.config.repository.SingleImeiHistoryDbRepository;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryHistoryMgmtRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.StolenAndRecoverySpecificationBuilder;

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

			return new GenricResponse(0,"Upload Successfully.",stolenandRecoveryDetails.getTxnId());

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	@Transactional
	public GenricResponse v2uploadDetails(StolenandRecoveryMgmt stolenandRecoveryDetails) {

		try {
			if("Single".equalsIgnoreCase(""/*stolenandRecoveryDetails.getSourceType()*/)){
				SingleImeiDetails singleImeiDetails = new SingleImeiDetails();	
				singleImeiDetails.setImei(stolenandRecoveryDetails.getImei());
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
			
			for(StolenandRecoveryMgmt stolenandRecoveryMgmt : stolenandRecoveryMgmtPage.getContent()) {
				sourceTypes = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.SOURCE_TYPE); 
				requestTypes = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.REQ_TYPE); 
				
				for(SystemConfigListDb configListDb : sourceTypes) {
					if(stolenandRecoveryMgmt.getSourceType() == configListDb.getValue()) {
						stolenandRecoveryMgmt.setSourceTypeInterp(configListDb.getInterp());
					}
					break;
				}
				
				for(SystemConfigListDb configListDb : requestTypes) {
					if(stolenandRecoveryMgmt.getRequestType() == configListDb.getValue()) {
						stolenandRecoveryMgmt.setRequestTypeInterp(configListDb.getInterp());
					}
					break;
				}
				
				for(StateMgmtDb stateMgmtDb : stateInterpList) {
					if(stolenandRecoveryMgmt.getFileStatus() == stateMgmtDb.getState()) {
						stolenandRecoveryMgmt.setStateInterp(stateMgmtDb.getInterp()); 
						break;
					}
				}
			}
			
			return stolenandRecoveryMgmtPage;

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}

	@Transactional
	public GenricResponse uploadMultipleStolen(List<StolenandRecoveryMgmt> stolenandRecoveryMgmt) {
		try {

			for(StolenandRecoveryMgmt  request:stolenandRecoveryMgmt) {

				if("Consignment".equalsIgnoreCase("" /* request.getSourceType() */ )){
					ConsignmentMgmt consignmentMgmt =	consignmentRepository.getByTxnId(request.getTxnId());
					if("Stolen".equalsIgnoreCase("" /*request.getRequestType() */ )) {
						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.STOLEN.getCode());
					}else {
						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.RECOVERY.getCode());
					}
					consignmentRepository.save(consignmentMgmt);
				}else if("STOCK".equalsIgnoreCase("" /* request.getSourceType() */)) {

					StockMgmt stockMgmt = distributerManagementRepository.findByRoleTypeAndTxnId(request.getRoleType(),request.getTxnId());
					if("Stolen".equalsIgnoreCase("" /* request.getRequestType() */)) {
						stockMgmt.setStockStatus(StockStatus.STOLEN.getCode());
					}else {
						stockMgmt.setStockStatus(StockStatus.RECOVERY.getCode());
					}
					distributerManagementRepository.save(stockMgmt);
				}

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setState(0);
				webActionDb.setFeature("" /* request.getRequestType() */ );				
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
				historyMgmt.setRequestType("" /* stolenandRecoveryMgmtInfo.getRequestType() */);
				historyMgmt.setRoleType(stolenandRecoveryMgmtInfo.getRoleType());
				historyMgmt.setTxnId(stolenandRecoveryMgmtInfo.getTxnId());
				historyMgmt.setUserId(stolenandRecoveryMgmtInfo.getUserId());
				historyMgmt.setSourceType("" /* stolenandRecoveryMgmtInfo.getSourceType() */);

				if ("Single".equalsIgnoreCase("" /* stolenandRecoveryMgmt.getSourceType() */)){

					SingleImeiHistoryDb singleImeiHistoryDb = new SingleImeiHistoryDb();
					singleImeiHistoryDb.setImei(stolenandRecoveryMgmtInfo.getSingleImeiDetails().getImei());
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
			StolenandRecoveryMgmt stolenandRecoveryMgmtInfo =stolenAndRecoveryRepository.getById(stolenandRecoveryMgmt.getId());
			if(stolenandRecoveryMgmtInfo == null) {

				return new GenricResponse(4,"TxnId Does Not exist", stolenandRecoveryMgmt.getTxnId());
			}else {

				StolenAndRecoveryHistoryMgmt historyMgmt = new  StolenAndRecoveryHistoryMgmt();
				historyMgmt.setBlockingTimePeriod(stolenandRecoveryMgmtInfo.getBlockingTimePeriod());
				historyMgmt.setBlockingType(stolenandRecoveryMgmtInfo.getBlockingType());
				historyMgmt.setFileName(stolenandRecoveryMgmtInfo.getFileName());
				historyMgmt.setFileStatus(stolenandRecoveryMgmtInfo.getFileStatus());
				historyMgmt.setRequestType("" /* stolenandRecoveryMgmtInfo.getRequestType() */);
				historyMgmt.setRoleType(stolenandRecoveryMgmtInfo.getRoleType());
				historyMgmt.setTxnId(stolenandRecoveryMgmtInfo.getTxnId());
				historyMgmt.setUserId(stolenandRecoveryMgmtInfo.getUserId());
				historyMgmt.setSourceType("" /* stolenandRecoveryMgmtInfo.getSourceType() */);

				stolenAndRecoveryHistoryMgmtRepository.save(historyMgmt);

				if ("Stolen".equalsIgnoreCase("" /* stolenandRecoveryMgmt.getRequestType() */)){
					stolenandRecoveryMgmtInfo.setBlockingTimePeriod(stolenandRecoveryMgmt.getBlockingTimePeriod());
					stolenandRecoveryMgmtInfo.setBlockingType(stolenandRecoveryMgmt.getBlockingType());
				}
				stolenandRecoveryMgmtInfo.setFileName(stolenandRecoveryMgmt.getFileName());
				stolenandRecoveryMgmtInfo.setFileStatus(0);

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

	public ResponseCountAndQuantity getStolenAndRecoveryCount( RequestCountAndQuantityWithLongUserId request, String requestType) {
		try {
			logger.info("Going to get StolenAndRecovery count.");
			return stolenAndRecoveryRepository.getStolenandRecoveryCount( request.getUserId(), request.getStatus(), requestType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
