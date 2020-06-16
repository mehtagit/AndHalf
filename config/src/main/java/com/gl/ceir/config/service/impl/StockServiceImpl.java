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
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

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
import com.gl.ceir.config.feign.UserFeignClient;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.DashboardUsersFeatureStateMap;
import com.gl.ceir.config.model.FeatureValidateReq;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.StatesInterpretationDb;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.Userrole;
import com.gl.ceir.config.model.Usertype;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Alerts;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.StockStatus;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;

import com.gl.ceir.config.model.file.StockFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.DashboardUsersFeatureStateMapRepository;
import com.gl.ceir.config.repository.StatesInterpretaionRepository;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StockMgmtHistoryRepository;
import com.gl.ceir.config.repository.UserProfileRepo;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.request.model.Generic_Response_Notification;
import com.gl.ceir.config.request.model.RegisterationUser;
import com.gl.ceir.config.service.businesslogic.StateMachine;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.transaction.StockTransaction;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.HttpResponse;
import com.gl.ceir.config.util.InterpSetter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class StockServiceImpl {

	private static final Logger logger = LogManager.getLogger(StockServiceImpl.class);

	// This is set with @postconstruct
	private String featureName;

	@Autowired
	StockManagementRepository stockManagementRepository;

	@Autowired
	StockMgmtHistoryRepository stockMgmtHistoryRepository;

	@Autowired
	DashboardUsersFeatureStateMapRepository dashboardUsersFeatureStateMapRepository; 

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired	
	EmailUtil emailUtil;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl; 

	@Autowired
	UserProfileRepo userProfileRepo;

	@Autowired
	InterpSetter interpSetter;

	@Autowired
	StatesInterpretaionRepository statesInterpretaionRepository;

	@Autowired 
	UserFeignClient userFeignClient;

	@Autowired
	StockTransaction stockTransaction;

	@Autowired
	StakeholderfeatureServiceImpl stakeholderfeatureServiceImpl;

	@Autowired
	AlertServiceImpl alertServiceImpl;

	@Autowired
	UserStaticServiceImpl userStaticServiceImpl;

	public GenricResponse uploadStock(StockMgmt stockMgmt) {
		boolean isStockAssignRequest = Boolean.FALSE;
		boolean isAnonymousUpload = Boolean.FALSE;

		User user = null;

		try {
			stockMgmt.setStockStatus(StockStatus.NEW.getCode());

			// Assign Stock is done by custom.
			if("Custom".equalsIgnoreCase(stockMgmt.getUserType())) {
				String secondaryRoleType = null;
				user =	userRepository.getByUsername(stockMgmt.getSupplierId());
				logger.info(user);

				if(Objects.isNull(user)) {
					logger.info("This is not a valid user to assign a stock. ");
					return new GenricResponse(2, "This is not a valid user.", "");
				}

				// Fetch all roles of user.
				List<String> userRoles = user.getUserRole().stream()
						.map(u -> u.getUsertypeData().getUsertypeName())
						.collect(Collectors.toList());

				logger.info(userRoles);

				if(userRoles.isEmpty()) {
					logger.info("No role assigned to the user.");
					return new GenricResponse(4, "No role assigned to the user.", "");
				}

				secondaryRoleType = getSecondaryRoleType(userRoles);

				if(Objects.isNull(secondaryRoleType)) {
					logger.info("User is not a distributer or retailer to assign a stock.");
					return new GenricResponse(5, "User is not a distributer or retailer to assign a stock.", "");
				}

				stockMgmt.setUserId(user.getId());
				stockMgmt.setUser(user);
				stockMgmt.setRoleType(secondaryRoleType);
				isStockAssignRequest = Boolean.TRUE;

				addInAuditTrail(stockMgmt.getAssignerId(), stockMgmt.getTxnId(), SubFeatures.ASSIGN, "Custom");

			}else if("End User".equalsIgnoreCase(stockMgmt.getUserType())){
				// Check if this feature is supported in current period.
				HttpResponse response = userFeignClient.validatePeriod(new FeatureValidateReq(4, 17));
				logger.info("FEIGN : response for validatePeriod " + response);
				if(response.getStatusCode() == 420) {
					logger.info("Feature [Stock] user [End User]" + GenericMessageTags.FEATURE_NOT_ALLOWED.getMessage());
					return new GenricResponse(420, GenericMessageTags.FEATURE_NOT_ALLOWED.getTag(), 
							GenericMessageTags.FEATURE_NOT_ALLOWED.getMessage(), "");
				}

				if(validateUserProfileOfStock(stockMgmt)) {
					user = User.getDefaultUser();

					UserProfile userProfile = UserProfile.getDefaultUserProfile();
					userProfile.setEmail(stockMgmt.getUser().getUserProfile().getEmail());
					userProfile.setUser(user);
					Usertype usertype = new Usertype();
					usertype.setId(17);

					Userrole roles = new Userrole(user, usertype);
					List<Userrole> userRolesList = new ArrayList<Userrole>();
					userRolesList.add(roles);
					user.setUserRole(userRolesList);
					user.setUserProfile(userProfile);
					user.setUsertype(usertype);

					user = userRepository.save(user);
					logger.info("User [" + user + "] have been saved successfully.");

					stockMgmt.setUserId(new Long(user.getId()));
					stockMgmt.setUser(user);
					stockMgmt.setRoleType("End User");
					isAnonymousUpload = Boolean.TRUE;
				}else {
					logger.info("Invalid request for stock registeration.", stockMgmt.getTxnId());
					return new GenricResponse(3, "Invalid request for stock registeration.", stockMgmt.getTxnId());
				}
				addInAuditTrail(user.getId(), stockMgmt.getTxnId(), SubFeatures.UPLOAD, stockMgmt.getRoleType());
			}else {
				stockMgmt.setUser(new User().setId(new Long(stockMgmt.getUserId())));
				addInAuditTrail(stockMgmt.getUserId(), stockMgmt.getTxnId(), SubFeatures.UPLOAD,stockMgmt.getRoleType());
			}

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			//webActionDb.setFeature(stakeholderfeatureServiceImpl.getFeatureNameById(4L));
			webActionDb.setSubFeature(WebActionDbSubFeature.UPLOAD.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(stockMgmt.getTxnId());

			if(isStockAssignRequest) {
				if(stockTransaction.executeRegisterStock(stockMgmt, webActionDb, user.getUserProfile(), isStockAssignRequest, isAnonymousUpload)) {
					logger.info("Stock have been registered Successfully" + stockMgmt.getTxnId());
					return new GenricResponse(0, "Stock have been registered Successfully.", stockMgmt.getTxnId());	
				}else {
					logger.info("Stock registeration have been failed." + stockMgmt.getTxnId());
					return new GenricResponse(1, "Stock registeration have been failed.", stockMgmt.getTxnId());
				}
			}else if(isAnonymousUpload) {
				if(stockTransaction.executeRegisterStock(stockMgmt, webActionDb, user.getUserProfile(), isStockAssignRequest, 
						isAnonymousUpload)) {

					logger.info("Stock have been registered Successfully" + stockMgmt.getTxnId());
					return new GenricResponse(0, "Stock have been registered Successfully.", stockMgmt.getTxnId());	
				}else {
					logger.info("Stock registeration have been failed." + stockMgmt.getTxnId());
					return new GenricResponse(1, "Stock registeration have been failed.", stockMgmt.getTxnId());
				}
			}else {
				if(stockTransaction.executeRegisterStock(stockMgmt, webActionDb)) {

					logger.info("Stock have been registered Successfully" + stockMgmt.getTxnId());
					return new GenricResponse(0, "Stock have been registered Successfully.", stockMgmt.getTxnId());	
				}else {
					logger.info("Stock registeration have been failed." + stockMgmt.getTxnId());
					return new GenricResponse(1, "Stock registeration have been failed.", stockMgmt.getTxnId());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", featureName);
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.REGISTER);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, stockMgmt.getUserId().intValue(), bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<StockMgmt> getAllData(StockMgmt stockMgmt){
		try {

			return stockManagementRepository.findByRoleTypeAndUserId(stockMgmt.getInvoiceNumber(), stockMgmt.getUserId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", featureName);
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.VIEW);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, 0, bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Page<StockMgmt> getAllFilteredData(FilterRequest filterRequest, Integer pageNo, Integer pageSize, 
			String source){

		List<StateMgmtDb> statusList = null;

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info(statusList);

			Page<StockMgmt> page = stockManagementRepository.findAll(buildSpecification(filterRequest, statusList, source).build(), pageable);

			for(StockMgmt stockMgmt : page.getContent()) {

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(stockMgmt.getStockStatus() == stateMgmtDb.getState()) {
						stockMgmt.setStateInterp(stateMgmtDb.getInterp()); 
					} 
				}
			}



			/*
			 * auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), "",
			 * Long.valueOf(filterRequest.getUserTypeId()), filterRequest.getUserType(),
			 * Long.valueOf(filterRequest.getFeatureId()), Features.STOCK, SubFeatures.VIEW,
			 * "", "NA"));
			 * 
			 * logger.info("AUDIT : Saved view request in audit.");
			 */
			if(Objects.isNull(filterRequest.getTxnId())) {

				addInAuditTrail(Long.valueOf(filterRequest.getUserId()), "NA", SubFeatures.VIEW_ALL,filterRequest.getRoleType());

			}else {

				addInAuditTrail(Long.valueOf(filterRequest.getUserId()), filterRequest.getTxnId(), SubFeatures.FILTER,filterRequest.getRoleType());

			}

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", featureName);
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.VIEW_ALL);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, filterRequest.getUserId(), bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private List<StockMgmt> getAll(FilterRequest filterRequest){
		List<StateMgmtDb> statusList = null;

		try {
			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info("statusList " + statusList);
			List<StockMgmt> stockMgmts = stockManagementRepository.findAll(buildSpecification(filterRequest, statusList, null).build(), new Sort(Sort.Direction.DESC, "modifiedOn"));

			logger.info(statusList);

			for(StockMgmt stockMgmt : stockMgmts) {

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(stockMgmt.getStockStatus() == stateMgmtDb.getState()) {
						stockMgmt.setStateInterp(stateMgmtDb.getInterp());
					}
				}
			}

			return stockMgmts;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", featureName);
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.VIEW_ALL);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, filterRequest.getUserId(), bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private GenericSpecificationBuilder<StockMgmt> buildSpecification(FilterRequest filterRequest, 
			List<StateMgmtDb> statusList, String source){
		GenericSpecificationBuilder<StockMgmt> specificationBuilder = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		if("Importer".equalsIgnoreCase(filterRequest.getUserType()) || 
				"Distributor".equalsIgnoreCase(filterRequest.getUserType()) || 
				"Retailer".equalsIgnoreCase(filterRequest.getUserType()) || 
				"Manufacturer".equalsIgnoreCase(filterRequest.getUserType())
				) {

			if(Objects.nonNull(filterRequest.getUserId()) )
				specificationBuilder.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getRoleType()))
				specificationBuilder.with(new SearchCriteria("roleType", filterRequest.getRoleType(), SearchOperation.EQUALITY, Datatype.STRING));
		} 

		if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
			specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
			specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getTxnId()) && !filterRequest.getTxnId().isEmpty())
			specificationBuilder.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getUserType()) && "Custom".equalsIgnoreCase(filterRequest.getUserType())) {
			logger.info("Inside custom block.");
			specificationBuilder.with(new SearchCriteria("userType", filterRequest.getUserType(), SearchOperation.EQUALITY, Datatype.STRING));
		}

		if(Objects.nonNull(filterRequest.getFilteredUserType()) && !filterRequest.getFilteredUserType().isEmpty()) {
			logger.info("Inside getFilteredUserType block.");
			specificationBuilder.with(new SearchCriteria("userType", filterRequest.getFilteredUserType(), SearchOperation.EQUALITY, Datatype.STRING));
		}

		if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
			specificationBuilder.with(new SearchCriteria("stockStatus", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
		}else {
			if(Objects.nonNull(filterRequest.getFeatureId()) && Objects.nonNull(filterRequest.getUserTypeId())) {

				List<DashboardUsersFeatureStateMap> dashboardUsersFeatureStateMap = dashboardUsersFeatureStateMapRepository.findByUserTypeIdAndFeatureId(filterRequest.getUserTypeId(), filterRequest.getFeatureId());
				logger.debug(dashboardUsersFeatureStateMap);

				List<Integer> stockStatus = new LinkedList<>();

				if(Objects.nonNull(dashboardUsersFeatureStateMap)) {
					if("dashboard".equalsIgnoreCase(source) || "menu".equalsIgnoreCase(source)) {
						for(DashboardUsersFeatureStateMap dashboardUsersFeatureStateMap2 : dashboardUsersFeatureStateMap ) {
							stockStatus.add(dashboardUsersFeatureStateMap2.getState());
						}
					}else if("filter".equalsIgnoreCase(source)) {
						boolean isFilterEmpty = nothingInFilter(filterRequest);
						logger.info("Nothing in filter : " + isFilterEmpty);

						if(isFilterEmpty) {
							for(DashboardUsersFeatureStateMap dashboardUsersFeatureStateMap2 : dashboardUsersFeatureStateMap ) {
								stockStatus.add(dashboardUsersFeatureStateMap2.getState());
							}
						}else {
							for(StateMgmtDb stateMgmtDb : statusList ) {
								stockStatus.add(stateMgmtDb.getState());
							}
						}
					}else if("noti".equalsIgnoreCase(source)) {
						logger.info("Skip status check, because source is noti.");
					}

					logger.info("Array list to add is = " + stockStatus);
					if(!stockStatus.isEmpty()) {
						specificationBuilder.addSpecification(specificationBuilder.in("stockStatus", stockStatus));
					}else {
						logger.warn("no predefined status are available.");
					}
				}
			}
		}


		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			specificationBuilder.orSearch(new SearchCriteria("txnId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return specificationBuilder;
	}

	public StockMgmt view(FilterRequest filterRequest) {
		try {
			logger.info("Going to get Stock Record Info for txnId : " + filterRequest.getTxnId());

			if(Objects.isNull(filterRequest.getTxnId())) {
				throw new IllegalArgumentException();
			}

			StockMgmt stockMgmt2 = stockManagementRepository.getByTxnId(filterRequest.getTxnId()); 

			if("End User".equalsIgnoreCase(filterRequest.getUserType())) {
				StatesInterpretationDb statesInterpretationDb = statesInterpretaionRepository.findByFeatureIdAndState(4, stockMgmt2.getStockStatus());
				stockMgmt2.setStateInterp(statesInterpretationDb.getInterp());
			}else {
				User user = userRepository.getById(filterRequest.getUserId());
				logger.info(user);

				/*
				 * auditTrailRepository.save(new AuditTrail(user.getId(), user.getUsername(),
				 * user.getUsertype().getId(), user.getUsertype().getUsertypeName(), 4,
				 * Features.STOCK, SubFeatures.VIEW, "", filterRequest.getTxnId()));
				 * logger.info("Stock [ View ][" + filterRequest.getTxnId() +
				 * "] saved in audit_trail.");
				 */
			}
			addInAuditTrail(Long.valueOf(filterRequest.getUserId()), filterRequest.getTxnId(), SubFeatures.VIEW,filterRequest.getRoleType());
			return stockMgmt2;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", featureName);
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.VIEW);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, filterRequest.getUserId(), bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse deleteStockDetailes(ConsignmentUpdateRequest deleteObj) {
		UserProfile userProfile = null;
		String firstName = "";
		User user = null;
		Map<String, String> placeholderMap = new HashMap<>();
		//String mailTag = null;
		String action = null;
		String txnId = null;
		String userMailTag = null;
		Boolean isUserCeirAdmin=false;
		String receiverUserType = deleteObj.getUserType();
		action = SubFeatures.DELETE;
		//	mailTag = "STOCK_DELETE_BY_CEIR_ADMIN"; 
		userMailTag = "STOCK_DELETE_BY_USER"; 
		user = userRepository.getById(deleteObj.getUserId());				
		userProfile = user.getUserProfile();
		txnId = deleteObj.getTxnId();


		/*placeholderMap.put("<First name>", userProfile_generic_Response_Notification.getFirstName());
		placeholderMap.put("<Txn id>", consignmentMgmt.getTxnId());*/

		placeholderMap.put("<First name>",userProfile.getFirstName());
		placeholderMap.put("<Txn id>", deleteObj.getTxnId());
		try {

			if(Objects.isNull(deleteObj.getTxnId())) {
				logger.info("TxnId is null in the request.");
				return new GenricResponse(1001, "TxnId is null in the request.", deleteObj.getTxnId());
			}

			if(Objects.isNull(deleteObj.getUserType())) {
				logger.info("userType is null in the request.");
				return new GenricResponse(1002, "Usertype is null in the request.", deleteObj.getTxnId());
			}

			StockMgmt txnRecord	= stockManagementRepository.getByTxnId(deleteObj.getTxnId());

			if(Objects.isNull(txnRecord)) {
				return new GenricResponse(1000, "No record found against this transactionId.", deleteObj.getTxnId());
			}else {

				if("CEIRADMIN".equalsIgnoreCase(deleteObj.getUserType())) {
					txnRecord.setStockStatus(StockStatus.WITHDRAWN_BY_CEIR_ADMIN.getCode());
					isUserCeirAdmin=true;

				}

				else {
					txnRecord.setStockStatus(StockStatus.WITHDRAWN_BY_USER.getCode());
				}
				txnRecord.setRemarks(deleteObj.getRemarks());
				txnRecord.setDeleteFlag(0);

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
				// webActionDb.setFeature(stakeholderfeatureServiceImpl.getFeatureNameById(4L));
				webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(deleteObj.getTxnId());

				addInAuditTrail(Long.valueOf(deleteObj.getUserId()), deleteObj.getTxnId(), SubFeatures.DELETE,deleteObj.getRoleType());
				if(stockTransaction.executeDeleteStock(txnRecord, webActionDb)) {
					logger.info("Deletion of Stock is in Progress." + deleteObj.getTxnId());
					if(isUserCeirAdmin) {

						user = userRepository.getById(txnRecord.getUserId());				
						userProfile = user.getUserProfile();
						logger.info("user profile details-");
						logger.info(userProfile);
						Generic_Response_Notification generic_Response_Notification = userFeignClient.ceirInfoByUserTypeId(8);

						logger.info("generic_Response_Notification::::::::"+generic_Response_Notification);

						List<RegisterationUser> registerationUserList = generic_Response_Notification.getData();

						for(RegisterationUser registerationUser :registerationUserList) {
							UserProfile userProfile_generic_Response_Notification = new UserProfile();
							userProfile_generic_Response_Notification = userProfileRepository.getByUserId(registerationUser.getId());


							emailUtil.saveNotification(userMailTag, 
									userProfile_generic_Response_Notification,
									deleteObj.getFeatureId(),
									Features.STOCK,
									action,
									deleteObj.getTxnId(),
									txnId,
									placeholderMap,
									deleteObj.getRoleType(),
									receiverUserType,
									"Users");
							logger.info("Notfication for CEIRAdmin have been saved.");
						}
						emailUtil.saveNotification(userMailTag, 
								userProfile, 
								deleteObj.getFeatureId(),
								Features.STOCK,
								action,
								deleteObj.getTxnId(),
								txnId,
								placeholderMap,
								deleteObj.getRoleType(),
								receiverUserType,
								"Users");
						logger.info("Notfication for user have been saved.");
					}
					else {
						emailUtil.saveNotification(userMailTag, 
								userProfile, 
								deleteObj.getFeatureId(),
								Features.STOCK,
								action,
								deleteObj.getTxnId(),
								txnId,
								placeholderMap,
								deleteObj.getRoleType(),
								receiverUserType,
								"Users");
						logger.info("Notfication have been saved.");	
					}

					return new GenricResponse(0, "Deletion of Stock is in Progress.",deleteObj.getTxnId());
				}else {
					logger.info("Deletion of Stock have been failed." + deleteObj.getTxnId());
					return new GenricResponse(1, "Deletion of Stock have been failed.",deleteObj.getTxnId());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", featureName);
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.DELETE);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, 0, bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse updateStockInfo(StockMgmt distributerManagement) {
		StockMgmt stockMgmt = null;

		if("End User".equalsIgnoreCase(distributerManagement.getUserType())){
			distributerManagement.setRoleType("End User");
		}
		stockMgmt = stockManagementRepository.findByRoleTypeAndTxnId(distributerManagement.getRoleType(), 
				distributerManagement.getTxnId());
		logger.info(stockMgmt);

		if(Objects.isNull(stockMgmt)) {
			return new GenricResponse(1000, "No record found against this transactionId.",distributerManagement.getTxnId());

		}else {

			stockMgmt.setInvoiceNumber(distributerManagement.getInvoiceNumber());
			stockMgmt.setQuantity(distributerManagement.getQuantity());
			stockMgmt.setSuplierName(distributerManagement.getSuplierName());
			stockMgmt.setSupplierId(distributerManagement.getSupplierId());
			stockMgmt.setTotalPrice(distributerManagement.getTotalPrice());
			stockMgmt.setDeviceQuantity(distributerManagement.getDeviceQuantity());
			if(Objects.nonNull(distributerManagement.getFileName()) && !distributerManagement.getFileName().isEmpty()) {
				stockMgmt.setStockStatus(StockStatus.NEW.getCode());
				stockMgmt.setFileName(distributerManagement.getFileName());
			}

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			// webActionDb.setFeature(stakeholderfeatureServiceImpl.getFeatureNameById(4L));
			webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(distributerManagement.getTxnId());

			addInAuditTrail(stockMgmt.getUserId(), stockMgmt.getTxnId(), SubFeatures.UPDATE, stockMgmt.getRoleType());

			if(stockTransaction.executeUpdateStock(stockMgmt, webActionDb)) {
				logger.info("Stock Update have been Successful." + stockMgmt.getTxnId());
				return new GenricResponse(0, "Stock Update have been Successful.", distributerManagement.getTxnId());
			}else {
				logger.info("Stock Update have been failed." + stockMgmt.getTxnId());
				return new GenricResponse(1, "Stock Update have been failed.",stockMgmt.getTxnId());
			}
		}
	}


	public FileDetails getFilteredStockInFile(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		StockFileModel sfm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");

		String filePath = filepath.getValue();

		StatefulBeanToCsvBuilder<StockFileModel> builder = null;
		StatefulBeanToCsv<StockFileModel> csvWriter = null;
		List< StockFileModel > fileRecords = null;

		try {
			List<StockMgmt> stockMgmts = getAll(filterRequest);

			fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_Stock.csv";

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<StockFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !stockMgmts.isEmpty() ) {

				fileRecords = new ArrayList<>();

				for( StockMgmt stockMgmt : stockMgmts ) {
					sfm = new StockFileModel();

					sfm.setStockStatus(stockMgmt.getStateInterp());
					sfm.setTxnId( stockMgmt.getTxnId());
					sfm.setCreatedOn(stockMgmt.getCreatedOn().format(dtf));
					sfm.setModifiedOn( stockMgmt.getModifiedOn().format(dtf));
					sfm.setFileName( stockMgmt.getFileName());
					sfm.setSupplierName(stockMgmt.getSuplierName());

					logger.debug(sfm);

					fileRecords.add(sfm);
				}

				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath, link.getValue() + fileName ); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", featureName);
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.EXPORT);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, filterRequest.getUserId(), bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( Objects.nonNull(writer) )
					writer.close();
			} catch (IOException e) {}
		}
	}

	public FileDetails getFilteredStockInFileV2(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		StockFileModel sfm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");

		String filePath = filepath.getValue();

		StatefulBeanToCsvBuilder<StockFileModel> builder = null;
		StatefulBeanToCsv<StockFileModel> csvWriter = null;
		List< StockFileModel > fileRecords = null;

		CustomMappingStrategy<StockFileModel> mappingStrategy = new CustomMappingStrategy<>();

		try {
			List<StockMgmt> stockMgmts = getAll(filterRequest);
			fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_Stock.csv";

			/* if(filterRequest.getUserType().equalsIgnoreCase("CEIR")) */
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			mappingStrategy.setType(StockFileModel.class);

			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withMappingStrategy(mappingStrategy)
					.withSeparator(',')
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !stockMgmts.isEmpty() ) {

				fileRecords = new ArrayList<>();
				if(filterRequest.getUserType().equalsIgnoreCase("CUSTOM"))
				{
					logger.info("file export for custom.");
					for( StockMgmt stockMgmt : stockMgmts ) {

						sfm = new StockFileModel();

						if(Objects.nonNull(stockMgmt.getSuplierName())) {
							sfm.setAssigneName(stockMgmt.getSuplierName());
						}
						else {
							sfm.setAssigneName("NA");
						}

						if(Objects.nonNull(stockMgmt.getInvoiceNumber())) {
							sfm.setInvoiceNumber(stockMgmt.getInvoiceNumber());
						}
						else {
							sfm.setInvoiceNumber("NA");
						}
						sfm.setStockStatus(stockMgmt.getStateInterp());
						sfm.setTxnId( stockMgmt.getTxnId());
						sfm.setCreatedOn(stockMgmt.getCreatedOn().format(dtf));
						sfm.setModifiedOn( stockMgmt.getModifiedOn().format(dtf));
						sfm.setFileName( stockMgmt.getFileName());
						sfm.setSupplierName("NA");
						sfm.setQuantity(stockMgmt.getQuantity());
						sfm.setDeviceQuantity(stockMgmt.getDeviceQuantity());



						logger.info(sfm);

						fileRecords.add(sfm);
					}

				}
				else {
					logger.info("else condition for export.");
					for( StockMgmt stockMgmt : stockMgmts ) {

						sfm = new StockFileModel();
						if(Objects.nonNull(stockMgmt.getSuplierName())) {
							sfm.setSupplierName(stockMgmt.getSuplierName());
						}
						else {
							sfm.setSupplierName("NA");
						}
						sfm.setStockStatus(stockMgmt.getStateInterp());
						sfm.setTxnId( stockMgmt.getTxnId());
						sfm.setCreatedOn(stockMgmt.getCreatedOn().format(dtf));
						sfm.setModifiedOn( stockMgmt.getModifiedOn().format(dtf));
						sfm.setFileName( stockMgmt.getFileName());
						sfm.setQuantity(stockMgmt.getQuantity());
						sfm.setDeviceQuantity(stockMgmt.getDeviceQuantity());
						sfm.setAssigneName("NA");
						if(Objects.nonNull(stockMgmt.getInvoiceNumber())) {
							sfm.setInvoiceNumber(stockMgmt.getInvoiceNumber());
						}
						else {
							sfm.setInvoiceNumber("NA");
						}
						logger.info(sfm);
						fileRecords.add(sfm);
					}

				}
				if(csvWriter!=null) {
					logger.info("csv writer  is not  empty++++++++++++++++++++++++++++++++++++");
					csvWriter.write(fileRecords);
				}
				else {
					logger.info(" csv writer is empty---------------------------------------");
				}

			}else {
				csvWriter.write( new StockFileModel());
			}
			addInAuditTrail(Long.valueOf(filterRequest.getUserId()), "NA", SubFeatures.EXPORT,filterRequest.getRoleType());

			return new FileDetails( fileName, filePath, link.getValue() + fileName ); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			Map<String, String> bodyPlaceHolderMap = new HashMap<>();
			bodyPlaceHolderMap.put("<feature>", featureName);
			bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.EXPORT);
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, filterRequest.getUserId(), bodyPlaceHolderMap);

			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( Objects.nonNull(writer) )
					writer.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public ResponseCountAndQuantity getStockCountAndQuantity( long userId, Integer userTypeId, Integer featureId, String userType ) {
		List<StateMgmtDb> featureList = null;
		List<Integer> status = new ArrayList<>();
		try {
			logger.info("Going to get  stock count and quantity.");
			featureList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId( featureId, userTypeId);
			if(Objects.nonNull(featureList)) {	
				for(StateMgmtDb stateDb : featureList ) {
					status.add(stateDb.getState());
				}
			}
			if( !userType.equalsIgnoreCase("ceiradmin") )
				return stockManagementRepository.getStockCountAndQuantity( userId, status );
			else
				return stockManagementRepository.getStockCountAndQuantity( status );
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseCountAndQuantity(0,0);
		}
	}

	public GenricResponse acceptReject(ConsignmentUpdateRequest consignmentUpdateRequest) {
		try {
			UserProfile userProfile = null;
			String firstName = "";
			User user = null;
			Map<String, String> placeholderMap = new HashMap<>();
			Integer currentStatus = null;
			String adminMailTag = null;
			adminMailTag = "STOCK_PROCESS_SUCCESS_TO_CEIR_MAIL";
			logger.info("enter in accept reject method..");
			StockMgmt stockMgmt = stockManagementRepository.getByTxnId(consignmentUpdateRequest.getTxnId());
			currentStatus = stockMgmt.getStockStatus();

			// Fetch user_profile to update user over mail/sms regarding the action.
			if("Custom".equals(stockMgmt.getRoleType())) {
				user = userRepository.getById(stockMgmt.getAssignerId());				
			}else {
				user = userRepository.getById(stockMgmt.getUserId());
			}
			userProfile = user.getUserProfile();

			if(Objects.isNull(stockMgmt)) {
				String message = "TxnId Does not Exist";
				logger.info(message + " " + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(4, message, consignmentUpdateRequest.getTxnId());
			}

			logger.info(user);

			if(Objects.nonNull(user)) {
				firstName = user.getUserProfile().getFirstName();
			}

			// 0 - Accept, 1 - Reject
			if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
				String mailTag = null;
				String action = null;
				String txnId = null;
				String receiverUserType = stockMgmt.getUserType();

				logger.info("enter in CEIR Admin block.........");


				if(consignmentUpdateRequest.getAction() == 0) {
					logger.info("enter in accept method..");
					action = SubFeatures.ACCEPT;
					mailTag = "STOCK_APPROVED_BY_CEIR_ADMIN"; 

					txnId = stockMgmt.getTxnId();

					placeholderMap.put("<First name>", firstName);
					placeholderMap.put("<Txn id>", stockMgmt.getTxnId());

					stockMgmt.setStockStatus(StockStatus.APPROVED_BY_CEIR_ADMIN.getCode());
				}else {
					logger.info("enter in  reject method..");
					action = SubFeatures.REJECT;
					mailTag = "STOCK_REJECT_BY_CEIR_ADMIN";
					txnId =  stockMgmt.getTxnId();

					placeholderMap.put("<First name>", firstName);
					placeholderMap.put("<Txn id>", stockMgmt.getTxnId());

					stockMgmt.setStockStatus(StockStatus.REJECTED_BY_CEIR_ADMIN.getCode());
					stockMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
					stockMgmt.setCeirAdminId(consignmentUpdateRequest.getUserId());
				}

				// Update Stock and its history.
				if(!stockTransaction.updateStatusWithHistory(stockMgmt)){
					logger.warn("Unable to update Stolen and recovery entity.");
					return new GenricResponse(3, "Unable to update stock entity.", consignmentUpdateRequest.getTxnId()); 
				}else {
					addInAuditTrail(Long.valueOf(consignmentUpdateRequest.getUserId()), consignmentUpdateRequest.getTxnId(), action, consignmentUpdateRequest.getRoleType());
					emailUtil.saveNotification(mailTag, 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.STOCK,
							action,
							consignmentUpdateRequest.getTxnId(),
							txnId,
							placeholderMap,
							stockMgmt.getRoleType(),
							receiverUserType,
							"Users");
					logger.info("Notfication have been saved for user.");
					if(consignmentUpdateRequest.getAction() == 0) {
						Generic_Response_Notification generic_Response_Notification =
								userFeignClient.ceirInfoByUserTypeId(8);

						logger.info("generic_Response_Notification::::::::"+
								generic_Response_Notification);

						List<RegisterationUser> registerationUserList =
								generic_Response_Notification.getData();

						for(RegisterationUser registerationUser :registerationUserList) { UserProfile
							userProfile_generic_Response_Notification = new UserProfile();
						userProfile_generic_Response_Notification =
								userProfileRepository.getByUserId(registerationUser.getId());
						emailUtil.saveNotification(adminMailTag,
								userProfile_generic_Response_Notification,
								consignmentUpdateRequest.getFeatureId(), Features.STOCK, action,
								consignmentUpdateRequest.getTxnId(), txnId, placeholderMap,
								stockMgmt.getRoleType(), receiverUserType, "Users");
						logger.info("Notfication have been saved for CEIR Admin."); }	
					}



				}

			}else if("CEIRSYSTEM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
				String mailTag = null;

				String action = null;
				String txnId = null;
				String receiverUserType = stockMgmt.getUserType();
				logger.info("enter in CEIR system.......");

				if(!StateMachine.isStockStatetransitionAllowed("CEIRSYSTEM", stockMgmt.getStockStatus())) {
					logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
					return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
				}

				if(consignmentUpdateRequest.getAction() == 0) {
					logger.info("enter in accept  method..");
					action = SubFeatures.ACCEPT;
					mailTag = "STOCK_PROCESS_SUCCESS_TO_USER_MAIL"; 
					adminMailTag = "STOCK_PROCESS_SUCCESS_TO_CEIR_MAIL";
					txnId = stockMgmt.getTxnId();

					placeholderMap.put("<First name>", firstName);
					placeholderMap.put("<Txn id>", stockMgmt.getTxnId());

					if(stockMgmt.getStockStatus() == StockStatus.NEW.getCode()) {
						stockMgmt.setStockStatus(StockStatus.PROCESSING.getCode());
					}else {
						stockMgmt.setStockStatus(StockStatus.PENDING_APPROVAL_FROM_CEIR_ADMIN.getCode());
					}

				}else {
					logger.info("enter in reject  method..");
					action = SubFeatures.REJECT;
					mailTag = "STOCK_PROCESS_FAILED_TO_USER_MAIL";
					txnId = stockMgmt.getTxnId();

					placeholderMap.put("<First name>", firstName);
					placeholderMap.put("<Txn id>", stockMgmt.getTxnId());

					stockMgmt.setStockStatus(StockStatus.REJECTED_BY_SYSTEM.getCode());
					stockMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
				}

				// Update Stock and its history.
				if(!stockTransaction.updateStatusWithHistory(stockMgmt)){
					logger.warn("Unable to update Stolen and recovery entity.");
					return new GenricResponse(3, "Unable to update stock entity.", consignmentUpdateRequest.getTxnId()); 
				}else {
					logger.info("enter in mail sending   method..");

					if(currentStatus == StockStatus.PROCESSING.getCode()) {
						emailUtil.saveNotification(mailTag, 
								userProfile, 
								consignmentUpdateRequest.getFeatureId(),
								Features.STOCK,
								action,
								consignmentUpdateRequest.getTxnId(),
								txnId,
								placeholderMap,
								stockMgmt.getRoleType(),
								receiverUserType,
								"Users");

						logger.info("Notfication have been saved for user.");

						if(consignmentUpdateRequest.getAction() == 0) {
							Generic_Response_Notification generic_Response_Notification = userFeignClient.ceirInfoByUserTypeId(8);

							logger.info("generic_Response_Notification::::::::"+generic_Response_Notification);

							List<RegisterationUser> registerationUserList = generic_Response_Notification.getData();

							for(RegisterationUser registerationUser :registerationUserList) {
								UserProfile userProfile_generic_Response_Notification = new UserProfile();
								userProfile_generic_Response_Notification = userProfileRepository.getByUserId(registerationUser.getId());

								emailUtil.saveNotification(adminMailTag, 
										userProfile_generic_Response_Notification,
										consignmentUpdateRequest.getFeatureId(),
										Features.STOCK,
										action,
										consignmentUpdateRequest.getTxnId(),
										txnId,
										placeholderMap,
										stockMgmt.getRoleType(),
										receiverUserType,
										"Users");
								logger.info("Notfication have been saved for CEIR Admin.");
							}
							//							emailUtil.saveNotification(mailTag, 
							//									userProfile, 
							//									consignmentUpdateRequest.getFeatureId(),
							//									Features.STOCK,
							//									action,
							//									consignmentUpdateRequest.getTxnId(),
							//									txnId,
							//									placeholderMap,
							//									stockMgmt.getRoleType(),
							//									receiverUserType,
							//									"Users");
							//							logger.info("Notfication have been saved for user.");
						}
					}

					logger.debug(consignmentUpdateRequest);
					/*
					 * addInAuditTrail(Long.valueOf(consignmentUpdateRequest.getUserId()),
					 * consignmentUpdateRequest.getTxnId(), action,
					 * consignmentUpdateRequest.getRoleType());
					 */
				}

			}else {
				logger.warn("Accept/reject of Stock not allowed to you.");

				Map<String, String> bodyPlaceHolderMap = new HashMap<>();
				bodyPlaceHolderMap.put("<feature>", featureName);
				bodyPlaceHolderMap.put("<sub_feature>", SubFeatures.ACCEPT_REJECT);
				alertServiceImpl.raiseAnAlert(Alerts.ALERT_011, 0, bodyPlaceHolderMap);

				new GenricResponse(1, "Operation not Allowed", consignmentUpdateRequest.getTxnId());
			}

			return new GenricResponse(0, "Consignment Update SuccessFully.", consignmentUpdateRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private boolean validateUserProfileOfStock(StockMgmt stockMgmt) {
		if(Objects.nonNull(stockMgmt.getUser())) {
			if(Objects.nonNull(stockMgmt.getUser().getUserProfile())) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	private String getSecondaryRoleType(List<String> userRoles) {
		if(userRoles.contains(com.gl.ceir.config.model.constants.Usertype.DISTRIBUTOR.getName()))
			return com.gl.ceir.config.model.constants.Usertype.DISTRIBUTOR.getName();
		else if (userRoles.contains(com.gl.ceir.config.model.constants.Usertype.RETAILER.getName()))
			return com.gl.ceir.config.model.constants.Usertype.RETAILER.getName();
		else
			return null;
	}

	private void addInAuditTrail(Long userId, String txnId, String subFeatureName, String roleType) {

		User requestUser = null;
		try {
			requestUser = userRepository.getById(userId);
			if(requestUser.getUsertype().getId() == 17) {
				requestUser.getUsertype().setUsertypeName("End User");
			}
			logger.info("User Details"+requestUser);
		} catch (Exception e) {
			logger.error("Error while fetching user information for user id = "+userId);
		}
		if(Objects.nonNull(requestUser)) {
			logger.info("Inserting in audit table for feature = "+Features.STOCK+"and Subfeature"+subFeatureName);
			auditTrailRepository.save(new AuditTrail(
					requestUser.getId(),
					requestUser.getUsername(), 
					requestUser.getUsertype().getId(),
					requestUser.getUsertype().getUsertypeName(),
					4,
					Features.STOCK,
					subFeatureName,
					"", 
					txnId,
					roleType));
		}else {
			logger.error("Could not find the user information");
		}

	}

	public boolean nothingInFilter(FilterRequest filterRequest) {

		// Clean
		if(filterRequest.getStartDate().isEmpty()) {
			filterRequest.setStartDate(null);
		}

		if(filterRequest.getEndDate().isEmpty()) {
			filterRequest.setEndDate(null);
		}

		if(filterRequest.getTxnId().isEmpty()) {
			filterRequest.setTxnId(null);
		}

		// Logic
		if(Objects.nonNull(filterRequest.getStartDate())) {
			return Boolean.FALSE;
		}
		if(Objects.nonNull(filterRequest.getEndDate())) {
			return Boolean.FALSE;
		}

		if(Objects.nonNull(filterRequest.getTxnId())) {
			return Boolean.FALSE;
		}

		if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}

	@PostConstruct
	public void setFeatureName() {
		featureName = stakeholderfeatureServiceImpl.getFeatureNameById(4L);
	}
}