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
import com.gl.ceir.config.EmailSender.MailSubjects;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.feign.UserFeignClient;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FeatureValidateReq;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RawMail;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.StatesInterpretationDb;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.StockMgmtHistoryDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.Userrole;
import com.gl.ceir.config.model.Usertype;
import com.gl.ceir.config.model.WebActionDb;
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
import com.gl.ceir.config.repository.StatesInterpretaionRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StockMgmtHistoryRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.UserProfileRepo;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.HttpResponse;
import com.gl.ceir.config.util.InterpSetter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class StockServiceImpl {

	private static final Logger logger = LogManager.getLogger(StockServiceImpl.class);

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StockManagementRepository stockManagementRepository;

	@Autowired
	StockMgmtHistoryRepository stockMgmtHistoryRepository;

	@Autowired
	StockDetailsOperationRepository stockDetailsOperationRepository;

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

	public GenricResponse uploadStock(StockMgmt stockMgmt) {
		boolean isStockAssignRequest = Boolean.FALSE;
		boolean isAnonymousUpload = Boolean.FALSE;

		User user = null;

		try {
			stockMgmt.setStockStatus(StockStatus.UPLOADING.getCode());

			if("Custom".equalsIgnoreCase(stockMgmt.getUserType())) {
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

				if(!isUserRetailerOrDistributor(userRoles)) {
					logger.info("User is not a distributer or retailer to assign a stock.");
					return new GenricResponse(5, "User is not a distributer or retailer to assign a stock.", "");
				}

				stockMgmt.setUserId(new Long(user.getId()));
				stockMgmt.setUser(user);
				stockMgmt.setRoleType(user.getUsertype().getUsertypeName());
				isStockAssignRequest = Boolean.TRUE;

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
			}else {
				stockMgmt.setUser(new User().setId(new Long(stockMgmt.getUserId())));
			}

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.UPLOAD.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(stockMgmt.getTxnId());

			if(isStockAssignRequest) {
				if(executeRegisterStock(stockMgmt, webActionDb, user.getUserProfile(), isStockAssignRequest, isAnonymousUpload)) {
					logger.info("Stock have been registered Successfully" + stockMgmt.getTxnId());
					return new GenricResponse(0, "Stock have been registered Successfully.", stockMgmt.getTxnId());	
				}else {
					logger.info("Stock registeration have been failed." + stockMgmt.getTxnId());
					return new GenricResponse(1, "Stock registeration have been failed.", stockMgmt.getTxnId());
				}
			}else if(isAnonymousUpload) {
				if(executeRegisterStock(stockMgmt, webActionDb, user.getUserProfile(), isStockAssignRequest, 
						isAnonymousUpload)) {

					logger.info("Stock have been registered Successfully" + stockMgmt.getTxnId());
					return new GenricResponse(0, "Stock have been registered Successfully.", stockMgmt.getTxnId());	
				}else {
					logger.info("Stock registeration have been failed." + stockMgmt.getTxnId());
					return new GenricResponse(1, "Stock registeration have been failed.", stockMgmt.getTxnId());
				}
			}else {
				if(executeRegisterStock(stockMgmt, webActionDb)) {

					logger.info("Stock have been registered Successfully" + stockMgmt.getTxnId());
					return new GenricResponse(0, "Stock have been registered Successfully.", stockMgmt.getTxnId());	
				}else {
					logger.info("Stock registeration have been failed." + stockMgmt.getTxnId());
					return new GenricResponse(1, "Stock registeration have been failed.", stockMgmt.getTxnId());
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional(rollbackOn = Exception.class)
	private boolean executeRegisterStock(StockMgmt stockMgmt, WebActionDb webActionDb, UserProfile userProfile,
			boolean isStockAssignRequest, boolean isAnonymousUpload) {

		boolean queryStatus = Boolean.FALSE;

		logger.info("Going to save webActionDb [" + webActionDb + "]");
		webActionDbRepository.save(webActionDb);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in web_action_db.");

		logger.info("Going to save Stock [" + stockMgmt + "]");
		stockManagementRepository.save(stockMgmt);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in stock_mgmt.");

		auditTrailRepository.save(new AuditTrail(stockMgmt.getUser().getId(), "", 0L, "", 0L, Features.STOCK, 
				SubFeatures.REGISTER, "", stockMgmt.getTxnId()));
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in audit_trail.");

		if(isStockAssignRequest) {
			User user = userRepository.getById(stockMgmt.getUserId());
			logger.info(user);
			Map<String, String> placeholderMap = new HashMap<String, String>();
			placeholderMap.put("<First name>", user.getUserProfile().getFirstName());
			placeholderMap.put("<txn_id>", stockMgmt.getTxnId());

			if(emailUtil.saveNotification("ASSIGN_STOCK", 
					userProfile, 
					4,
					Features.STOCK,
					SubFeatures.ASSIGN,
					stockMgmt.getTxnId(),
					MailSubjects.SUBJECT,
					placeholderMap)) {
				logger.info("Notification have been saved.");
			}else {
				logger.info("Notification have been not saved.");
			}
		}else if(isAnonymousUpload) {
			List<RawMail> rawMails = new ArrayList<RawMail>();

			User user = userRepository.getByUsername("CEIRAdmin");
			logger.info(user);

			// Send notification to the CeirAdmin. 
			Map<String, String> placeholderMapForCeirAdmin = new HashMap<String, String>();
			placeholderMapForCeirAdmin.put("<txn_id>", stockMgmt.getTxnId());

			rawMails.add(new RawMail("MAIL_TO_CEIR_ADMIN_ON_STOCK_UPLOAD", user.getUserProfile(), 
					4, Features.STOCK, SubFeatures.REGISTER, stockMgmt.getTxnId(), MailSubjects.SUBJECT, 
					placeholderMapForCeirAdmin));

			// Send notification to the anonymous user if mail is provided.
			if(Objects.nonNull(userProfile.getEmail()) && !userProfile.getEmail().isEmpty()) {
				Map<String, String> placeholderMapForAnonymousUser = new HashMap<String, String>();
				placeholderMapForAnonymousUser.put("<txn_id>", stockMgmt.getTxnId());

				rawMails.add(new RawMail("MAIL_TO_ANONYMOUS_ON_STOCK_UPLOAD", userProfile, 
						4, Features.STOCK, SubFeatures.REGISTER, stockMgmt.getTxnId(), MailSubjects.SUBJECT,  
						placeholderMapForAnonymousUser));
			}

			if(emailUtil.saveNotification(rawMails)) {
				logger.info("Notifications [" + rawMails.size() + "] have been saved.");
			}else {
				logger.info("Notification have been not saved.");
			}
		}

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	@Transactional(rollbackOn = Exception.class)
	private boolean executeRegisterStock(StockMgmt stockMgmt, WebActionDb webActionDb) {

		boolean queryStatus = Boolean.FALSE;

		logger.info("Going to save webActionDb [" + webActionDb + "]");
		webActionDbRepository.save(webActionDb);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in web_action_db.");

		logger.info("Going to save Stock [" + stockMgmt + "]");
		stockManagementRepository.save(stockMgmt);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in stock_mgmt.");

		auditTrailRepository.save(new AuditTrail(stockMgmt.getUser().getId(), "", 0L, "", 0L, Features.STOCK, 
				SubFeatures.REGISTER, "", stockMgmt.getTxnId()));
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	public List<StockMgmt> getAllData(StockMgmt stockMgmt){
		try {

			return stockManagementRepository.findByRoleTypeAndUserId(stockMgmt.getInvoiceNumber(), stockMgmt.getUserId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Page<StockMgmt> getAllFilteredData(FilterRequest filterRequest, Integer pageNo, Integer pageSize){

		List<StateMgmtDb> statusList = null;

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info(statusList);

			Page<StockMgmt> page = stockManagementRepository.findAll(buildSpecification(filterRequest, statusList).build(), pageable);

			for(StockMgmt stockMgmt : page.getContent()) {

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(stockMgmt.getStockStatus() == stateMgmtDb.getState()) {
						stockMgmt.setStateInterp(stateMgmtDb.getInterp()); 
					} 
				}
			}

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private List<StockMgmt> getAll(FilterRequest filterRequest){
		List<StateMgmtDb> statusList = null;

		try {
			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info("statusList " + statusList);
			List<StockMgmt> stockMgmts = stockManagementRepository.findAll(buildSpecification(filterRequest, statusList).build(), new Sort(Sort.Direction.DESC, "modifiedOn"));

			logger.info(statusList);

			for(StockMgmt stockMgmt : stockMgmts) {

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(stockMgmt.getStockStatus() == stateMgmtDb.getState()) {
						stockMgmt.setStateInterp(stateMgmtDb.getInterp()); 
						// break; 
					} 
				}
			}

			return stockMgmts;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private GenericSpecificationBuilder<StockMgmt> buildSpecification(FilterRequest filterRequest, List<StateMgmtDb> statusList){
		GenericSpecificationBuilder<StockMgmt> specificationBuilder = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		if("Importer".equalsIgnoreCase(filterRequest.getUserType()) || 
				"Distributor".equalsIgnoreCase(filterRequest.getUserType()) || 
				"Retailer".equalsIgnoreCase(filterRequest.getUserType()) || 
				"Manufacturer".equalsIgnoreCase(filterRequest.getUserType())
				) {

			if(Objects.nonNull(filterRequest.getUserId()) )
				specificationBuilder.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getUserId()))
				specificationBuilder.with(new SearchCriteria("roleType", filterRequest.getRoleType(), SearchOperation.EQUALITY, Datatype.STRING));
		} 

		if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
			specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
			specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getTxnId()) && !filterRequest.getTxnId().isEmpty())
			specificationBuilder.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getUserType()) && "Custom".equalsIgnoreCase(filterRequest.getUserType()))
			specificationBuilder.with(new SearchCriteria("userType", filterRequest.getUserType(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
			specificationBuilder.with(new SearchCriteria("stockStatus", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));

		}else {
			if(Objects.nonNull(filterRequest.getFeatureId()) && Objects.nonNull(filterRequest.getUserTypeId())) {

				List<Integer> stockStatus = new LinkedList<>();
				logger.debug(statusList);

				if(Objects.nonNull(statusList)) {	
					for(StateMgmtDb stateDb : statusList ) {
						stockStatus.add(stateDb.getState());
					}

					logger.info("Array list to add is = " + stockStatus);
					if(!statusList.isEmpty()) {
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

	public StockMgmt view(StockMgmt stockMgmt) {
		try {
			logger.info("Going to get Stock Record Info for txnId : " + stockMgmt.getTxnId());

			if(Objects.isNull(stockMgmt.getTxnId())) {
				throw new IllegalArgumentException();
			}

			StockMgmt stockMgmt2 = stockManagementRepository.getByTxnId(stockMgmt.getTxnId()); 

			if("End User".equalsIgnoreCase(stockMgmt.getUserType())) {
				StatesInterpretationDb statesInterpretationDb = statesInterpretaionRepository.findByFeatureIdAndState(4, stockMgmt2.getStockStatus());
				stockMgmt2.setStateInterp(statesInterpretationDb.getInterp());
			}

			return stockMgmt2;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse deleteStockDetailes(StockMgmt stockMgmt, String userType) {
		try {

			if(Objects.isNull(stockMgmt.getTxnId())) {
				logger.info("TxnId is null in the request.");
				return new GenricResponse(1001, "TxnId is null in the request.", stockMgmt.getTxnId());
			}

			if(Objects.isNull(userType)) {
				logger.info("userType is null in the request.");
				return new GenricResponse(1002, "Usertype is null in the request.", stockMgmt.getTxnId());
			}

			StockMgmt txnRecord	= stockManagementRepository.getByTxnId(stockMgmt.getTxnId());

			if(Objects.isNull(txnRecord)) {
				return new GenricResponse(1000, "No record found against this transactionId.", stockMgmt.getTxnId());
			}else {

				if("CEIRADMIN".equalsIgnoreCase(userType))
					txnRecord.setStockStatus(StockStatus.WITHDRAWN_BY_CEIR_ADMIN.getCode());
				else
					txnRecord.setStockStatus(StockStatus.WITHDRAWN_BY_USER.getCode());

				txnRecord.setRemarks(stockMgmt.getRemarks());

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
				webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(stockMgmt.getTxnId());

				if(executeDeleteStock(txnRecord, webActionDb)) {
					logger.info("Deletion of Stock is in Progress." + stockMgmt.getTxnId());
					return new GenricResponse(0, "Deletion of Stock is in Progress.",stockMgmt.getTxnId());
				}else {
					logger.info("Deletion of Stock have been failed." + stockMgmt.getTxnId());
					return new GenricResponse(1, "Deletion of Stock have been failed.",stockMgmt.getTxnId());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	private boolean executeDeleteStock(StockMgmt stockMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in web_action_db.");

		stockManagementRepository.save(stockMgmt);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in stock_mgmt.");

		auditTrailRepository.save(new AuditTrail(stockMgmt.getUser().getId(), "", 0L, "", 0L, Features.STOCK, SubFeatures.DELETE, ""));
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
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

			if(Objects.nonNull(distributerManagement.getFileName()) && !distributerManagement.getFileName().isEmpty()) {
				stockMgmt.setStockStatus(StockStatus.PROCESSING.getCode());
				stockMgmt.setFileName(distributerManagement.getFileName());
			}

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(distributerManagement.getTxnId());

			if(executeUpdateStock(stockMgmt, webActionDb)) {
				logger.info("Stock Update have been Successful." + stockMgmt.getTxnId());
				return new GenricResponse(0, "Stock Update have been Successful.", distributerManagement.getTxnId());
			}else {
				logger.info("Deletion of Stock have been failed." + stockMgmt.getTxnId());
				return new GenricResponse(1, "Deletion of Stock have been failed.",stockMgmt.getTxnId());
			}
		}
	}

	@Transactional
	private boolean executeUpdateStock(StockMgmt stockMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in web_action_db.");

		stockManagementRepository.save(stockMgmt);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in stock_mgmt.");

		auditTrailRepository.save(new AuditTrail(stockMgmt.getUser().getId(), "", 0L, "", 0L, Features.STOCK, SubFeatures.UPDATE, ""));
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	public FileDetails getFilteredStockInFile(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		StockFileModel sfm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_stock_download_dir);
		logger.info("CONFIG : file_stock_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_stock_download_link);
		logger.info("CONFIG : file_stock_download_link [" + link + "]");

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
		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_stock_download_dir);
		logger.info("CONFIG : file_stock_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_stock_download_link);
		logger.info("CONFIG : file_stock_download_link [" + link + "]");

		String filePath = filepath.getValue();

		StatefulBeanToCsvBuilder<StockFileModel> builder = null;
		StatefulBeanToCsv<StockFileModel> csvWriter = null;
		List< StockFileModel > fileRecords = null;
		CustomMappingStrategy<StockFileModel> mappingStrategy = new CustomMappingStrategy<>();

		try {
			List<StockMgmt> stockMgmts = getAll(filterRequest);
			fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_Stock.csv";

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			mappingStrategy.setType(StockFileModel.class);

			builder = new StatefulBeanToCsvBuilder<StockFileModel>(writer);
			csvWriter = builder.withMappingStrategy(mappingStrategy)
					.withSeparator(',')
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !stockMgmts.isEmpty() ) {

				fileRecords = new ArrayList<>();
				// List<SystemConfigListDb> customTagStatusList = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.CUSTOMS_TAX_STATUS);

				for( StockMgmt stockMgmt : stockMgmts ) {
					sfm = new StockFileModel();

					sfm.setStockStatus(stockMgmt.getStateInterp());
					sfm.setTxnId( stockMgmt.getTxnId());
					sfm.setCreatedOn(stockMgmt.getCreatedOn().format(dtf));
					sfm.setModifiedOn( stockMgmt.getModifiedOn().format(dtf));
					sfm.setFileName( stockMgmt.getFileName());
					sfm.setSupplierName(stockMgmt.getSuplierName());
					sfm.setQuantity(stockMgmt.getQuantity());

					logger.debug(sfm);

					fileRecords.add(sfm);
				}

				csvWriter.write(fileRecords);
			}else {
				csvWriter.write( new StockFileModel());
			}

			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), "", 
					Long.valueOf(filterRequest.getUserTypeId()), filterRequest.getUserType(), 
					Long.valueOf(filterRequest.getFeatureId()),
					Features.STOCK, SubFeatures.VIEW, ""));
			logger.info("AUDIT : Saved file export request in audit.");

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

	public ResponseCountAndQuantity getStockCountAndQuantity( long userId, Integer userTypeId, Integer featureId, String userType ) {
		List<StateMgmtDb> featureList = null;
		List<Integer> status = new ArrayList<Integer>();
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
			//throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
			return new ResponseCountAndQuantity(0,0);
		}
	}

	public GenricResponse acceptReject(ConsignmentUpdateRequest consignmentUpdateRequest) {
		try {
			UserProfile userProfile = null;
			String firstName = "";
			User user = null;
			Map<String, String> placeholderMap = new HashMap<String, String>();
			StockMgmt stockMgmt = stockManagementRepository.getByTxnId(consignmentUpdateRequest.getTxnId());

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

				if(consignmentUpdateRequest.getAction() == 0) {
					action = SubFeatures.ACCEPT;
					mailTag = "STOCK_APPROVED_BY_CEIR_ADMIN"; 

					placeholderMap.put("<Custom first name>", firstName);
					placeholderMap.put("<txn_name>", stockMgmt.getTxnId());

					stockMgmt.setStockStatus(StockStatus.APPROVED_BY_CEIR_ADMIN.getCode());
				}else {
					action = SubFeatures.REJECT;
					mailTag = "STOCK_REJECT_BY_CEIR_ADMIN";

					placeholderMap.put("<Custom first name>", firstName);
					placeholderMap.put("<txn_name>", stockMgmt.getTxnId());

					stockMgmt.setStockStatus(StockStatus.REJECTED_BY_CEIR_ADMIN.getCode());
					stockMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
				}

				// Update Stock and its history.
				if(!updateStatusWithHistory(stockMgmt)){
					logger.warn("Unable to update Stolen and recovery entity.");
					return new GenricResponse(3, "Unable to update stock entity.", consignmentUpdateRequest.getTxnId()); 
				}else {
					// TODO : NOTI
					emailUtil.saveNotification(mailTag, 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.STOCK,
							action,
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT,
							placeholderMap);
					logger.info("Notfication have been saved.");
				}

			}else {
				logger.warn("Accept/reject of Stock not allowed to you.");
				new GenricResponse(1, "Operation not Allowed", consignmentUpdateRequest.getTxnId());
			}

			return new GenricResponse(0, "Consignment Update SuccessFully.", consignmentUpdateRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	private boolean updateStatusWithHistory(StockMgmt stockMgmt) {
		boolean status = Boolean.FALSE;

		stockManagementRepository.save(stockMgmt);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in stock_mgmt.");

		stockMgmtHistoryRepository.save(
				new StockMgmtHistoryDb(stockMgmt.getTxnId(), stockMgmt.getStockStatus())
				);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in stock_mgmt_history_db.");

		auditTrailRepository.save(new AuditTrail(stockMgmt.getUser().getId(), "", 0L, "", 0L, Features.STOCK, SubFeatures.UPDATE, ""));
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in audit_trail.");

		status = Boolean.TRUE;

		return status;
	}

	private boolean validateUserProfileOfStock(StockMgmt stockMgmt) {
		if(Objects.nonNull(stockMgmt.getUser())) {
			if(Objects.nonNull(stockMgmt.getUser().getUserProfile())) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	private boolean isUserRetailerOrDistributor(List<String> userRoles) {
		return userRoles.contains("Distributor") || userRoles.contains("Retailer");
	}
}