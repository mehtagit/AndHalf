package com.gl.ceir.config.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.DashboardUsersFeatureStateMap;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.SingleImeiDetails;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.StolenAndRecoveryHistoryMgmt;
import com.gl.ceir.config.model.StolenIndividualUserDB;
import com.gl.ceir.config.model.StolenOrganizationUserDB;
import com.gl.ceir.config.model.StolenandRecoveryMgmt;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.ConsignmentStatus;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.StockStatus;
import com.gl.ceir.config.model.constants.StolenStatus;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.model.constants.WebActionStatus;
import com.gl.ceir.config.model.file.StolenAndRecoveryFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.DashboardUsersFeatureStateMapRepository;
import com.gl.ceir.config.repository.ImmegreationImeiDetailsRepository;
import com.gl.ceir.config.repository.SingleImeiHistoryDbRepository;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryHistoryMgmtRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryRepository;
import com.gl.ceir.config.repository.StolenIndividualUserRepository;
import com.gl.ceir.config.repository.StolenOrganizationUserRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.transaction.StolenAndRecoveryTransaction;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.InterpSetter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class StolenAndRecoveryServiceImpl {

	private static final Logger logger = LogManager.getLogger(StolenAndRecoveryServiceImpl.class);

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	DashboardUsersFeatureStateMapRepository dashboardUsersFeatureStateMapRepository; 

	@Autowired
	StolenAndRecoveryRepository stolenAndRecoveryRepository;

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

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	EmailUtil emailUtil;

	@Autowired
	InterpSetter interpSetter;

	@Autowired
	StolenIndividualUserRepository stolenIndividualUserRepository;

	@Autowired
	StolenOrganizationUserRepository stolenOrganizationUserRepository;

	@Autowired
	StolenAndRecoveryTransaction stolenAndRecoveryTransaction;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;
	
	@Autowired
	StakeholderfeatureServiceImpl stakeholderfeatureServiceImpl;

	public GenricResponse uploadDetails(StolenandRecoveryMgmt stolenandRecoveryMgmt) {

		try {
			WebActionDb webActionDb = new WebActionDb(decideFeature(stolenandRecoveryMgmt.getRequestType()), SubFeatures.REGISTER, 
					WebActionStatus.INIT.getCode(), stolenandRecoveryMgmt.getTxnId());

			stolenandRecoveryMgmt.setFileStatus(StolenStatus.INIT.getCode());
			if(Objects.nonNull(stolenandRecoveryMgmt.getStolenIndividualUserDB())) {
				StolenIndividualUserDB stolenIndividualUserDB = stolenandRecoveryMgmt.getStolenIndividualUserDB();
				stolenandRecoveryMgmt.setQty(countImeiForIndividual(stolenIndividualUserDB.getImeiEsnMeid1(), 
						stolenIndividualUserDB.getImeiEsnMeid2(), 
						stolenIndividualUserDB.getImeiEsnMeid3(), 
						stolenIndividualUserDB.getImeiEsnMeid4()));

				stolenandRecoveryMgmt.getStolenIndividualUserDB().setStolenandRecoveryMgmt(stolenandRecoveryMgmt);
			} else if (Objects.nonNull(stolenandRecoveryMgmt.getStolenOrganizationUserDB())) {
				stolenandRecoveryMgmt.getStolenOrganizationUserDB().setStolenandRecoveryMgmt(stolenandRecoveryMgmt);
			}

			if(stolenAndRecoveryTransaction.executeUploadDetails(stolenandRecoveryMgmt, webActionDb)) {
				logger.info("Upload Successfully." +  stolenandRecoveryMgmt.getTxnId());
				addInAuditTrail(stolenandRecoveryMgmt.getUserId(), stolenandRecoveryMgmt.getTxnId(), SubFeatures.UPLOAD, stolenandRecoveryMgmt.getRoleType(),stolenandRecoveryMgmt.getRequestType(),0);
				return new GenricResponse(0, "Upload Successfully.", stolenandRecoveryMgmt.getTxnId());
			}else {
				logger.info("Upload have been failed." + stolenandRecoveryMgmt.getTxnId());
				return new GenricResponse(1, "Upload have been failed.", stolenandRecoveryMgmt.getTxnId());
			}

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
			webActionDb.setFeature(decideFeature(stolenandRecoveryDetails.getRequestType()));
			webActionDb.setSubFeature(WebActionDbSubFeature.UPLOAD.getName());
			webActionDb.setTxnId(stolenandRecoveryDetails.getTxnId());
			webActionDb.setState(WebActionDbState.INIT.getCode());


			stolenandRecoveryDetails.setFileStatus(WebActionDbState.INIT.getCode());
			stolenAndRecoveryRepository.save(stolenandRecoveryDetails);

			webActionDbRepository.save(webActionDb);
			addInAuditTrail(stolenandRecoveryDetails.getUserId(), stolenandRecoveryDetails.getTxnId(), SubFeatures.UPLOAD, stolenandRecoveryDetails.getRoleType(),stolenandRecoveryDetails.getRequestType(),0);
			return new GenricResponse(0,"Upload Successfully.",stolenandRecoveryDetails.getTxnId());

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public Page<StolenandRecoveryMgmt> getAllInfo(FilterRequest filterRequest, Integer pageNo, Integer pageSize){
		List<StateMgmtDb> stateInterpList = null;
		List<StateMgmtDb> statusList = null;

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());

			Page<StolenandRecoveryMgmt> stolenandRecoveryMgmtPage = stolenAndRecoveryRepository.findAll(buildSpecification(filterRequest, statusList).build(), pageable);
			stateInterpList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info(stateInterpList);

			for(StolenandRecoveryMgmt stolenandRecoveryMgmt : stolenandRecoveryMgmtPage.getContent()) {				
				for(StateMgmtDb stateMgmtDb : stateInterpList) {
					if(stolenandRecoveryMgmt.getFileStatus() == stateMgmtDb.getState()) {
						stolenandRecoveryMgmt.setStateInterp(stateMgmtDb.getInterp()); 
						break;
					}
				}

				setInterp(stolenandRecoveryMgmt);

				// Operator type id for stolen request of registered by Ceir Admin.
				if(Objects.nonNull(stolenandRecoveryMgmt.getOperatorTypeId())) {
					if(stolenandRecoveryMgmt.getOperatorTypeId() == -1) 
						stolenandRecoveryMgmt.setOperatorTypeIdInterp("CEIR Admin");
				}else {
					stolenandRecoveryMgmt.setOperatorTypeIdInterp("");
					logger.info("WARN : OperatorTypeId is null for [" + stolenandRecoveryMgmt + "]");
				}

			}

			logger.info(stolenandRecoveryMgmtPage.getContent());
			if(Objects.nonNull(filterRequest.getTxnId())) {
				addInAuditTrail(Long.valueOf(filterRequest.getUserId()), filterRequest.getTxnId(), SubFeatures.FILTER, filterRequest.getRoleType(),filterRequest.getRequestType(),filterRequest.getFeatureId());
			}else {
				addInAuditTrail(Long.valueOf(filterRequest.getUserId()), "NA", SubFeatures.VIEW_ALL, filterRequest.getRoleType(),filterRequest.getRequestType(),filterRequest.getFeatureId());
			}
			return stolenandRecoveryMgmtPage;

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}

	public List<StolenandRecoveryMgmt> getAll(FilterRequest filterRequest){

		List<StateMgmtDb> stateInterpList = null;
		List<StateMgmtDb> statusList = null;

		try {
			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());

			List<StolenandRecoveryMgmt> stolenandRecoveryMgmts = stolenAndRecoveryRepository.findAll(buildSpecification(filterRequest, statusList).build());
			stateInterpList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			//stateInterpList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info(stateInterpList);

			/*
			 * for(StolenandRecoveryMgmt stolenandRecoveryMgmt : stolenandRecoveryMgmts) {
			 * for(StateMgmtDb stateMgmtDb : stateInterpList) {
			 * if(stolenandRecoveryMgmt.getFileStatus().equals(stateMgmtDb.getState())) {
			 * stolenandRecoveryMgmt.setStateInterp(stateMgmtDb.getInterp()); break; } }
			 * 
			 * setInterp(stolenandRecoveryMgmt); }
			 */
			for(StolenandRecoveryMgmt stolenandRecoveryMgmt : stolenandRecoveryMgmts) {				
				for(StateMgmtDb stateMgmtDb : stateInterpList) {
					if(stolenandRecoveryMgmt.getFileStatus() == stateMgmtDb.getState()) {
						stolenandRecoveryMgmt.setStateInterp(stateMgmtDb.getInterp()); 
						break;
					}
				}

				setInterp(stolenandRecoveryMgmt);

				// Operator type id for stolen request of registered by Ceir Admin.
				if(Objects.nonNull(stolenandRecoveryMgmt.getOperatorTypeId())) {
					if(stolenandRecoveryMgmt.getOperatorTypeId() == -1) 
						stolenandRecoveryMgmt.setOperatorTypeIdInterp("CEIR Admin");
				}else {
					stolenandRecoveryMgmt.setOperatorTypeIdInterp("");
					logger.info("WARN : OperatorTypeId is null for [" + stolenandRecoveryMgmt + "]");
				}

			}

			return stolenandRecoveryMgmts;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}

	private GenericSpecificationBuilder<StolenandRecoveryMgmt> buildSpecification(FilterRequest filterRequest, List<StateMgmtDb> statusList) {
		GenericSpecificationBuilder<StolenandRecoveryMgmt> srsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
		String ceirAdmin = "CEIRADMIN";
		String fileStatus = "fileStatus";

		if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
			srsb.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
			srsb.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getTxnId()) && !filterRequest.getTxnId().isEmpty())
			srsb.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));

		/*
		 * if(Objects.nonNull(filterRequest.getRoleType())) srsb.with(new
		 * SearchCriteria("roleType", filterRequest.getRoleType(),
		 * SearchOperation.EQUALITY, Datatype.STRING));
		 */

		if(Objects.nonNull(filterRequest.getSourceType())) {
			srsb.with(new SearchCriteria("sourceType", filterRequest.getSourceType(), SearchOperation.EQUALITY, Datatype.STRING));
		}

		if(Objects.nonNull(filterRequest.getOperatorTypeId())) {
			srsb.with(new SearchCriteria("operatorTypeId", filterRequest.getOperatorTypeId(), SearchOperation.EQUALITY, Datatype.STRING));
		}

		if(Objects.nonNull(filterRequest.getRequestType())) {
			srsb.with(new SearchCriteria("requestType", filterRequest.getRequestType(), SearchOperation.EQUALITY, Datatype.STRING));
		}else {
			if(Objects.nonNull(filterRequest.getFeatureId())) {
				List<Integer> configuredRequestTypeOfFeature = new LinkedList<>();
				List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTagAndFeatureId(Tags.REQ_TYPE, filterRequest.getFeatureId());
				logger.debug(systemConfigListDbs);

				if(Objects.nonNull(systemConfigListDbs)) {	
					for(SystemConfigListDb systemConfigListDb : systemConfigListDbs ) {
						configuredRequestTypeOfFeature.add(systemConfigListDb.getValue());
					}

					if(!configuredRequestTypeOfFeature.isEmpty()) {
						logger.info("List of configuredRequestTypeOfFeature = " + configuredRequestTypeOfFeature);
						srsb.addSpecification(srsb.in("requestType", configuredRequestTypeOfFeature));
					}else{
						logger.info("No predefined request type is configured for this request.");
					}
				}
			}
		}

		if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
			srsb.with(new SearchCriteria(fileStatus, filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
		}else {
			if(Objects.nonNull(filterRequest.getFeatureId()) && Objects.nonNull(filterRequest.getUserTypeId())) {

				List<Integer> configuredStatus = new LinkedList<>();

				List<DashboardUsersFeatureStateMap> dashboardUsersFeatureStateMap = dashboardUsersFeatureStateMapRepository.findByUserTypeIdAndFeatureId(filterRequest.getUserTypeId(), filterRequest.getFeatureId());
				logger.debug(dashboardUsersFeatureStateMap);

				if(Objects.nonNull(dashboardUsersFeatureStateMap)) {	
					for(DashboardUsersFeatureStateMap dashboardUsersFeatureStateMap2 : dashboardUsersFeatureStateMap ) {
						configuredStatus.add(dashboardUsersFeatureStateMap2.getState());
					}
					logger.info("Array list to add is = " + configuredStatus);

					if(!configuredStatus.isEmpty())
						srsb.addSpecification(srsb.in("fileStatus", configuredStatus));
					else{
						logger.info("No predefined status is configured for this request.");
					}
				}
			}
		}

		if(ceirAdmin.equalsIgnoreCase(filterRequest.getUserType())) {
			if(Objects.nonNull(filterRequest.getUserId())) { 
				logger.info("Inside ceir admin block.");
				srsb.or(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));
			}
			else 
				logger.info("Usertype in request is must when ceir admin is logged in to the system.");

			if(Objects.nonNull(filterRequest.getConsignmentStatus())) {

			}
		}else if(!"Lawful Agency".equalsIgnoreCase(filterRequest.getUserType())) {
			if(Objects.nonNull(filterRequest.getUserId())) {
				logger.info("Inside !Lawful Agency block.");
				srsb.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));
			}
		}

		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			srsb.orSearch(new SearchCriteria("txnId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return srsb;
	}

	public FileDetails getFilteredStolenAndRecoveryInFile(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;

		StolenAndRecoveryFileModel srfm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");

		String filePath = filepath.getValue();

		StatefulBeanToCsvBuilder<StolenAndRecoveryFileModel> builder = null;
		StatefulBeanToCsv<StolenAndRecoveryFileModel> csvWriter = null;
		List< StolenAndRecoveryFileModel > fileRecords = null;
		CustomMappingStrategy<StolenAndRecoveryFileModel> mappingStrategy = new CustomMappingStrategy<>();

		try {
			List<StolenandRecoveryMgmt> stolenandRecoveryMgmts = getAll(filterRequest);
			if(filterRequest.getFeatureId() == 5) {
				fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_StolenAndRecovery.csv";
			}else {
				fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_Block_Unblock.csv";
			}

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			mappingStrategy.setType(StolenAndRecoveryFileModel.class);

			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( stolenandRecoveryMgmts.isEmpty() ) {
				csvWriter.write(new StolenAndRecoveryFileModel());
			}else {

				fileRecords = new ArrayList<>();
				for( StolenandRecoveryMgmt stolenandRecoveryMgmt : stolenandRecoveryMgmts ) {
					srfm = new StolenAndRecoveryFileModel();
					if(Objects.isNull(stolenandRecoveryMgmt)) {
						continue;
					}

					srfm.setCreatedOn(stolenandRecoveryMgmt.getCreatedOn().format(dtf));
					srfm.setModifiedOn( stolenandRecoveryMgmt.getModifiedOn().format(dtf));
					srfm.setTxnId( stolenandRecoveryMgmt.getTxnId());
					srfm.setRequestType(stolenandRecoveryMgmt.getRequestTypeInterp());
					srfm.setMode(stolenandRecoveryMgmt.getSourceTypeInterp());
					logger.info("Status : "+stolenandRecoveryMgmt.getStateInterp());
					srfm.setStolenStatus(stolenandRecoveryMgmt.getStateInterp());

					if(Objects.isNull(stolenandRecoveryMgmt.getOperatorTypeId())) {
						srfm.setSource("");
					}else if(stolenandRecoveryMgmt.getOperatorTypeId() == -1) {
						srfm.setSource("Ceir Admin");
					}else {
						srfm.setSource(stolenandRecoveryMgmt.getOperatorTypeIdInterp());
					}

					srfm.setFileName( stolenandRecoveryMgmt.getFileName());
					srfm.setDeviceQuantity(stolenandRecoveryMgmt.getDeviceQuantity());
					
					logger.debug(srfm);
					fileRecords.add(srfm);
				}
				csvWriter.write(fileRecords);
			}
			addInAuditTrail(Long.valueOf(filterRequest.getUserId()), "NA", SubFeatures.EXPORT, filterRequest.getRoleType(),filterRequest.getRequestType(),filterRequest.getFeatureId());
			return new FileDetails( fileName, filePath, link.getValue() + fileName ); 

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
			Boolean bool = true;	
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
				webActionDb.setFeature(decideFeature(request.getRequestType()));				
				webActionDb.setSubFeature("Upload");
				webActionDb.setData(request.getTxnId());

				webActionDbRepository.save(webActionDb);
				while(bool) {
					addInAuditTrail(request.getUserId(), request.getTxnId(), SubFeatures.UPLOAD, request.getRoleType(),request.getRequestType(),0);
					bool=false;
				}
			}

			stolenAndRecoveryRepository.saveAll(stolenandRecoveryMgmt);

			return new GenricResponse(0, "Upload SucessFully", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	@Transactional
	public GenricResponse deleteRecord(FilterRequest filterRequest) {

		try {
			StolenandRecoveryMgmt stolenandRecoveryMgmtInfo = stolenAndRecoveryRepository.getById(filterRequest.getId());
			if(Objects.isNull(filterRequest.getRemark())) {
				return new GenricResponse(5,"Remarks Missing", filterRequest.getTxnId());
			}
			if(Objects.isNull(stolenandRecoveryMgmtInfo)) {
				return new GenricResponse(4,"TxnId Does Not exist", filterRequest.getTxnId());
			}else {
				if("Lawful Agency".equalsIgnoreCase(filterRequest.getUserType()) || "Operator".equalsIgnoreCase(filterRequest.getUserType())) {
					if(stolenandRecoveryMgmtInfo.getFileStatus()==0 || stolenandRecoveryMgmtInfo.getFileStatus()==3 || stolenandRecoveryMgmtInfo.getFileStatus()==4) {
						//set file status =7
						stolenandRecoveryMgmtInfo.setFileStatus(7);//withdrawn by user 
						stolenandRecoveryMgmtInfo.setRemark(filterRequest.getRemark());
					}
					else {
						return new GenricResponse(2,"State transaction not allowed", filterRequest.getTxnId());
					}
				}else if("CEIRAdmin".equalsIgnoreCase(filterRequest.getUserType())){
					//set file status =6
					stolenandRecoveryMgmtInfo.setFileStatus(6);//withdrawn by CEIR Admin 
					stolenandRecoveryMgmtInfo.setRemark(filterRequest.getRemark());
				}else {
					return new GenricResponse(3,"Operation not allowed", filterRequest.getTxnId());
				}
				stolenAndRecoveryRepository.save(stolenandRecoveryMgmtInfo);
				addInAuditTrail(Long.valueOf(filterRequest.getUserId()), filterRequest.getTxnId(), SubFeatures.DELETE, filterRequest.getRoleType(),filterRequest.getRequestType(),filterRequest.getFeatureId());
				return new GenricResponse(0,"Record Delete Sucessfully", filterRequest.getTxnId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	public GenricResponse updateRecord(StolenandRecoveryMgmt stolenandRecoveryMgmt) {

		try {
			StolenandRecoveryMgmt stolenandRecoveryMgmtInfo = stolenAndRecoveryRepository.getByTxnId(stolenandRecoveryMgmt.getTxnId());
			logger.info(stolenandRecoveryMgmtInfo);
			if(Objects.isNull(stolenandRecoveryMgmtInfo)) {
				return new GenricResponse(4, "TxnId Does Not exist", stolenandRecoveryMgmt.getTxnId());
			}else {

				WebActionDb webActionDb = new WebActionDb(decideFeature(stolenandRecoveryMgmt.getRequestType()), 
						SubFeatures.UPDATE, 
						WebActionStatus.INIT.getCode(), stolenandRecoveryMgmt.getTxnId());

				StolenAndRecoveryHistoryMgmt historyMgmt = new StolenAndRecoveryHistoryMgmt();
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
				stolenandRecoveryMgmtInfo.setBlockCategory(stolenandRecoveryMgmt.getBlockCategory());
				stolenandRecoveryMgmtInfo.setRemark(stolenandRecoveryMgmt.getRemark());
				stolenandRecoveryMgmtInfo.setQty(stolenandRecoveryMgmt.getQty());
				stolenandRecoveryMgmtInfo.setFileStatus(StolenStatus.INIT.getCode());
				stolenandRecoveryMgmtInfo.setDeviceQuantity(stolenandRecoveryMgmt.getDeviceQuantity());
				
				// Update StolenIndividualUserDB
				if(Objects.nonNull(stolenandRecoveryMgmt.getStolenIndividualUserDB())) {
					StolenIndividualUserDB stolenIndividualUserDB = updateStolenIndividualUserDB(
							stolenandRecoveryMgmtInfo.getStolenIndividualUserDB(),
							stolenandRecoveryMgmt.getStolenIndividualUserDB()
							);

					stolenandRecoveryMgmtInfo.setQty(countImeiForIndividual(stolenIndividualUserDB.getImeiEsnMeid1(), 
							stolenIndividualUserDB.getImeiEsnMeid2(), 
							stolenIndividualUserDB.getImeiEsnMeid3(), 
							stolenIndividualUserDB.getImeiEsnMeid4()));

					stolenandRecoveryMgmtInfo.setStolenIndividualUserDB(stolenIndividualUserDB);
					stolenIndividualUserDB.setStolenandRecoveryMgmt(stolenandRecoveryMgmtInfo);

					logger.info("After object update " + stolenIndividualUserDB);
				}

				// update StolenOrganizationUserDB
				if(Objects.nonNull(stolenandRecoveryMgmt.getStolenOrganizationUserDB())) {
					StolenOrganizationUserDB stolenOrganizationUserDB = updateStolenOrganizationUserDB(
							stolenandRecoveryMgmtInfo.getStolenOrganizationUserDB(),
							stolenandRecoveryMgmt.getStolenOrganizationUserDB()
							);

					stolenandRecoveryMgmtInfo.setStolenOrganizationUserDB(stolenOrganizationUserDB);

					stolenOrganizationUserDB.setStolenandRecoveryMgmt(stolenandRecoveryMgmtInfo);

					logger.info("After object update " + stolenOrganizationUserDB);
				}

				logger.info("Final object StolenandRecoveryMgmt : " + stolenandRecoveryMgmtInfo);

				//	StolenandRecoveryMgmt stolenandRecoveryMgmtNew = 
				webActionDbRepository.save(webActionDb);
				logger.info("Update request saved in web action : " + webActionDb);
				stolenAndRecoveryRepository.save(stolenandRecoveryMgmtInfo);
				addInAuditTrail(Long.valueOf(stolenandRecoveryMgmt.getUserId()), stolenandRecoveryMgmt.getTxnId(), SubFeatures.UPDATE, stolenandRecoveryMgmt.getRoleType(),stolenandRecoveryMgmt.getRequestType(),0);
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
			logger.info("Going to get Stolen and recovery Info for txnId : " + stolenandRecoveryMgmt.getTxnId());

			if(Objects.isNull(stolenandRecoveryMgmt.getTxnId())) {
				throw new IllegalArgumentException();
			}

			StolenandRecoveryMgmt stolenandRecoveryMgmt2 = stolenAndRecoveryRepository.getByTxnId(stolenandRecoveryMgmt.getTxnId());
			setInterp(stolenandRecoveryMgmt2);
			addInAuditTrail(Long.valueOf(stolenandRecoveryMgmt.getUserId()), stolenandRecoveryMgmt.getTxnId(), SubFeatures.VIEW, stolenandRecoveryMgmt.getRoleType(),stolenandRecoveryMgmt.getRequestType(),0);
			return stolenandRecoveryMgmt2;
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

	public GenricResponse acceptReject(ConsignmentUpdateRequest consignmentUpdateRequest) {
		try {
			UserProfile userProfile = null;
			Map<String, String> placeholderMap1 = null;
			StolenandRecoveryMgmt stolenandRecoveryMgmt = stolenAndRecoveryRepository.getByTxnId(consignmentUpdateRequest.getTxnId());

			// Fetch user_profile to update user over mail/sms regarding the action.
			userProfile = userProfileRepository.getByUserId(stolenandRecoveryMgmt.getUserId());

			User user = userRepository.getById(stolenandRecoveryMgmt.getUserId());

			logger.info("User is " + user);

			if(Objects.isNull(stolenandRecoveryMgmt)) {
				String message = "TxnId Does not Exist";
				logger.info(message + " " + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(4, message, consignmentUpdateRequest.getTxnId());
			}

			// 0 - Accept, 1 - Reject
			if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
				String mailTag = null;
				String action = null;
				String txnId = null;

				if(consignmentUpdateRequest.getAction() == 0) {
					action = SubFeatures.ACCEPT;

					if(consignmentUpdateRequest.getRequestType() == 0) {
						mailTag = "STOLEN_APPROVED_BY_CEIR_ADMIN";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 1){
						mailTag = "RECOVERY_APPROVED_BY_CEIR_ADMIN";
						txnId =  stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 2){
						mailTag = "BLOCK_APPROVED_BY_CEIR_ADMIN";
						txnId =  stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 3){
						mailTag = "UNBLOCK_APPROVED_BY_CEIR_ADMIN";
						txnId =  stolenandRecoveryMgmt.getTxnId();
					}else {
						logger.warn("unknown request type received for stolen and recovery.");
					}

					stolenandRecoveryMgmt.setFileStatus(StolenStatus.APPROVED_BY_CEIR_ADMIN.getCode());

				}else {
					action = SubFeatures.REJECT;

					if(consignmentUpdateRequest.getRequestType() == 0) {
						mailTag = "STOLEN_REJECT_BY_CEIR_ADMIN";
						txnId =  stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 1){
						mailTag = "RECOVERY_REJECT_BY_CEIR_ADMIN";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 2){
						mailTag = "BLOCK_REJECT_BY_CEIR_ADMIN";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 3){
						mailTag = "UNBLOCK_REJECT_BY_CEIR_ADMIN";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else {
						logger.warn("unknown request type received for stolen and recovery.");
						return new GenricResponse(2, "unknown request type received for stolen and recovery.", consignmentUpdateRequest.getTxnId());
					}

					stolenandRecoveryMgmt.setFileStatus(StolenStatus.REJECTED_BY_CEIR_ADMIN.getCode());
					stolenandRecoveryMgmt.setRejectedRemark(consignmentUpdateRequest.getRemarks());
					stolenandRecoveryMgmt.setCeirAdminId(consignmentUpdateRequest.getUserId());

				}

				if(!stolenAndRecoveryTransaction.updateStatusWithHistory(stolenandRecoveryMgmt)) {
					logger.warn("Unable to update Stolen and recovery entity.");
					return new GenricResponse(3, "Unable to update Stolen and recovery entity.", consignmentUpdateRequest.getTxnId());
				}else {
					placeholderMap1 = new HashMap<>();

					placeholderMap1.put("<First name>", userProfile.getFirstName());
					placeholderMap1.put("<Txn id>", txnId);

					emailUtil.saveNotification(mailTag, 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							decideFeature(consignmentUpdateRequest.getRequestType()),
							action,
							consignmentUpdateRequest.getTxnId(),
							txnId,
							placeholderMap1,
							"CEIRADMIN",
							user.getUsertype().getUsertypeName());
					logger.info("Notfication have been saved.");
				}
				addInAuditTrail(Long.valueOf(stolenandRecoveryMgmt.getUserId()), stolenandRecoveryMgmt.getTxnId(), action, stolenandRecoveryMgmt.getRoleType(),stolenandRecoveryMgmt.getRequestType(),0);
			}else if("CEIRSYSTEM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
				String mailTag = null;
				String action = null;
				String txnId = null;

				if(consignmentUpdateRequest.getAction() == 0) {
					action = SubFeatures.ACCEPT;

					if(consignmentUpdateRequest.getRequestType() == 0) {
						mailTag = "STOLEN_PROCESSED_SUCESSFULLY";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 1){
						mailTag = "RECOVERY_PROCESSED_SUCESSFULLY";
						txnId =  stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 2){
						mailTag = "BLOCK_PROCESSED_SUCESSFULLY";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 3){
						mailTag = "UNBLOCK_PROCESSED_SUCESSFULLY";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else {
						logger.warn("unknown request type received for stolen and recovery.");
					}

					stolenandRecoveryMgmt.setFileStatus(StolenStatus.PENDING_APPROVAL_FROM_CEIR_ADMIN.getCode());

				}else {
					action = SubFeatures.REJECT;

					if(consignmentUpdateRequest.getRequestType() == 0){
						mailTag = "STOLEN_PROCESSED_FAILED";
						txnId =  stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 1){
						mailTag = "RECOVERY_PROCESSED_FAILED";
						txnId =  stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 2){
						mailTag = "BLOCK_PROCESSED_FAILED";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else if(consignmentUpdateRequest.getRequestType() == 3){
						mailTag = "UNBLOCK_PROCESSED_FAILED";
						txnId = stolenandRecoveryMgmt.getTxnId();
					}else {
						logger.warn("unknown request type received for stolen and recovery.");
						return new GenricResponse(2, "unknown request type received for stolen and recovery.", consignmentUpdateRequest.getTxnId());
					}

					stolenandRecoveryMgmt.setFileStatus(StolenStatus.REJECTED_BY_SYSTEM.getCode());
					stolenandRecoveryMgmt.setRejectedRemark(consignmentUpdateRequest.getRemarks());

				}

				if(!stolenAndRecoveryTransaction.updateStatusWithHistory(stolenandRecoveryMgmt)) {
					logger.warn("Unable to update Stolen and recovery entity.");
					return new GenricResponse(3, "Unable to update Stolen and recovery entity.", consignmentUpdateRequest.getTxnId());
				}else {
					placeholderMap1 = new HashMap<>();
					placeholderMap1.put("<First name>", userProfile.getFirstName());
					placeholderMap1.put("<Txn id>", txnId);

					emailUtil.saveNotification(mailTag, 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							decideFeature(consignmentUpdateRequest.getRequestType()),
							action,
							consignmentUpdateRequest.getTxnId(),
							txnId,
							placeholderMap1,
							"CEIRSYSTEM",
							user.getUsertype().getUsertypeName());
					logger.info("Notfication have been saved.");
				}
		
			}else {
				logger.warn("Accept/reject of Stock not allowed to you.");
				new GenricResponse(1, "Operation not Allowed", consignmentUpdateRequest.getTxnId());
			}

			return new GenricResponse(0, "Stolen/Block request Updated SuccessFully.", consignmentUpdateRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private StolenIndividualUserDB updateStolenIndividualUserDB(StolenIndividualUserDB stolenIndividualUserDBOld, 
			StolenIndividualUserDB stolenIndividualUserDBNew) {
		stolenIndividualUserDBNew.setId(stolenIndividualUserDBOld.getId());


		return stolenIndividualUserDBNew;
	}

	private StolenOrganizationUserDB updateStolenOrganizationUserDB(StolenOrganizationUserDB stolenOrganizationUserDbOld,
			StolenOrganizationUserDB stolenOrganizationUserDbNew) {
		stolenOrganizationUserDbNew.setId(stolenOrganizationUserDbOld.getId());

		return stolenOrganizationUserDbNew;
	}

	private void setInterp(StolenandRecoveryMgmt stolenandRecoveryMgmt) {
		if(Objects.nonNull(stolenandRecoveryMgmt.getSourceType()))
			stolenandRecoveryMgmt.setSourceTypeInterp(interpSetter.setConfigInterp(Tags.SOURCE_TYPE, stolenandRecoveryMgmt.getSourceType()));

		if(Objects.nonNull(stolenandRecoveryMgmt.getRequestType()))
			stolenandRecoveryMgmt.setRequestTypeInterp(interpSetter.setConfigInterp(Tags.REQ_TYPE, stolenandRecoveryMgmt.getRequestType()));

		if(Objects.nonNull(stolenandRecoveryMgmt.getOperatorTypeId()))
			stolenandRecoveryMgmt.setOperatorTypeIdInterp(interpSetter.setConfigInterp(Tags.OPERATORS, stolenandRecoveryMgmt.getOperatorTypeId()));

		if(Objects.nonNull(stolenandRecoveryMgmt.getBlockCategory()))
			stolenandRecoveryMgmt.setBlockCategoryInterp(interpSetter.setConfigInterp(Tags.BLOCK_CATEGORY, stolenandRecoveryMgmt.getBlockCategory()));

		if( Objects.nonNull( stolenandRecoveryMgmt.getSingleImeiDetails() ) ){
			if( Objects.nonNull( stolenandRecoveryMgmt.getSingleImeiDetails().getMultipleSimStatus() ) )
				stolenandRecoveryMgmt.getSingleImeiDetails().setMultipleSimStatusInterp(interpSetter.setConfigInterp(Tags.MULTI_SIM_STATUS, stolenandRecoveryMgmt.getSingleImeiDetails().getMultipleSimStatus()));

			if(Objects.nonNull(stolenandRecoveryMgmt.getSingleImeiDetails().getDeviceType()))
				stolenandRecoveryMgmt.getSingleImeiDetails().setDeviceTypeInterp(interpSetter.setConfigInterp(Tags.DEVICE_TYPE, stolenandRecoveryMgmt.getSingleImeiDetails().getDeviceType()));

			if(Objects.nonNull(stolenandRecoveryMgmt.getSingleImeiDetails().getDeviceIdType()))
				stolenandRecoveryMgmt.getSingleImeiDetails().setDeviceIdTypeInterp(interpSetter.setConfigInterp(Tags.DEVICE_ID_TYPE, stolenandRecoveryMgmt.getSingleImeiDetails().getDeviceIdType()));

			if(Objects.nonNull(stolenandRecoveryMgmt.getSingleImeiDetails().getCategory()))
				stolenandRecoveryMgmt.getSingleImeiDetails().setCategoryInterp(interpSetter.setConfigInterp(Tags.BLOCK_CATEGORY, stolenandRecoveryMgmt.getSingleImeiDetails().getCategory()));
		}
	}

	private String decideFeature(int requestType) {
		switch (requestType) {
		case 0:
			return "Stolen";
			//return decideFeature(5L);
		case 1:
			return "Recovery";
			// return decideFeature(5L);
		case 2:
			return "Block";
			//return decideFeature(7L);
		case 3:
			return "Unblock";
			// return decideFeature(7L);
		default:
			return null;
		}
	}
	
	private String decideFeature(Long id) {
		return stakeholderfeatureServiceImpl.getFeatureNameById(id);
	}

	private int countImeiForIndividual(Long imei1, Long imei2, Long imei3, Long imei4) {
		int count = 0;
		if(Objects.nonNull(imei1))
			count++;

		if(Objects.nonNull(imei2))
			count++;

		if(Objects.nonNull(imei3))
			count++;

		if(Objects.nonNull(imei4))
			count++;

		return count==0? 1:count;
	}

	private void addInAuditTrail(Long userId, String txnId, String subFeatureName, String roleType,Integer requestType,Integer featureId) {

		User requestUser = null;
		String fearure = null;
		try {
			logger.info("Fetching user Info User ID"+userId);	
			requestUser = userRepository.getById(userId);
			if(Objects.nonNull(featureId) && featureId!=0) {
				if(featureId==5) {
					fearure = "Stolen/Recovery";
				}
				if(featureId==7) {
					fearure = "Block/unblock Devices";
				}
			}else {
				if(Objects.nonNull(requestType)) {
					if(requestType == 0 || requestType == 1) {
						featureId=5;
						fearure = "Stolen/Recovery";
					}
					else {
						featureId=7;
						fearure = "Block/unblock Devices";
					}
				}else {
					logger.error("Error while fetching user info requestType: "+requestType+" featureId : "+featureId);
				}

			}

			logger.info("User Details"+requestUser);
		} catch (Exception e) {
			logger.error("Error while fetching user information for user id = "+userId);
		}
		if(Objects.nonNull(requestUser)) {
			//	logger.info("Inserting in audit table for feature = "+Features.Sto+"and Subfeature"+subFeatureName);
			AuditTrail obj = new AuditTrail(
					requestUser.getId(),
					requestUser.getUsername(), 
					requestUser.getUsertype().getId(),
					requestUser.getUsertype().getUsertypeName(),
					featureId,
					fearure,
					subFeatureName,
					"", 
					txnId,
					roleType);
			logger.info("Inserting in audit table object = "+obj);
			auditTrailRepository.save(obj);
		}else {
			logger.error("Could not find the user information");
		}
	}

}