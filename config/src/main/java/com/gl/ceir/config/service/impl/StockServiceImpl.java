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

import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.EmailSender.MailSubjects;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.StockMgmtHistoryDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.StockStatus;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.model.file.StockFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StockMgmtHistoryRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
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
	InterpSetter interpSetter;

	public GenricResponse uploadStock(StockMgmt stockMgmt) {

		try {
			stockMgmt.setStockStatus(StockStatus.UPLOADING.getCode());

			if("Custom".equalsIgnoreCase(stockMgmt.getUserType())) {
				User user =	userRepository.getByUsername(stockMgmt.getSupplierId());
				
				if(Objects.isNull(user)) {
					logger.info("This is not a valid user to assign a stock. ");
					return new GenricResponse(1, "This is not a valid user.", "");
				}
				stockMgmt.setUserId(new Long(user.getId()));
				stockMgmt.setUser(user);

			}else {
				stockMgmt.setUser(new User().setId(new Long(stockMgmt.getUserId())));
			}

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.UPLOAD.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(stockMgmt.getTxnId());

			if(executeRegisterStock(stockMgmt, webActionDb)) {
				logger.info("Stock have been registered Successfully" + stockMgmt.getTxnId());
				return new GenricResponse(0, "Stock have been registered Successfully.", stockMgmt.getTxnId());	
			}else {
				logger.info("Stock registeration have been failed." + stockMgmt.getTxnId());
				return new GenricResponse(1, "Stock registeration have been failed.", stockMgmt.getTxnId());
			}

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	@Transactional(rollbackOn = Exception.class)
	private boolean executeRegisterStock(StockMgmt stockMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in web_action_db.");

		stockManagementRepository.save(stockMgmt);
		logger.info("Stock [" + stockMgmt.getTxnId() + "] saved in stock_mgmt.");

		auditTrailRepository.save(new AuditTrail(stockMgmt.getUser().getId(), "", 0L, "", 0L, Features.STOCK, SubFeatures.REGISTER, ""));
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

			List<StockMgmt> stockMgmts = stockManagementRepository.findAll(buildSpecification(filterRequest, statusList).build());

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
	
	private SpecificationBuilder<StockMgmt> buildSpecification(FilterRequest filterRequest, List<StateMgmtDb> statusList){
		SpecificationBuilder<StockMgmt> specificationBuilder = new SpecificationBuilder<>(propertiesReader.dialect);

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

		StockMgmt stockMgmt = stockManagementRepository.findByRoleTypeAndTxnId(distributerManagement.getRoleType(), distributerManagement.getTxnId());

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
		String filePath  = fileStorageProperties.getStockDownloadDir();

		StatefulBeanToCsvBuilder<StockFileModel> builder = null;
		StatefulBeanToCsv<StockFileModel> csvWriter = null;
		List< StockFileModel > fileRecords = null;

		// HeaderColumnNameTranslateMappingStrategy<GrievanceFileModel> mapStrategy = null;
		try {
			List<StockMgmt> stockMgmts = getAll(filterRequest);

			if( !stockMgmts.isEmpty() ) {
				if(Objects.nonNull(filterRequest.getUserId()) && (filterRequest.getUserId() != -1 && filterRequest.getUserId() != 0)) {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_Stocks.csv";
				}else {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_Stocks.csv";
				}
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_Stocks.csv";
			}

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<StockFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !stockMgmts.isEmpty() ) {

				fileRecords = new ArrayList<>();
				// List<SystemConfigListDb> customTagStatusList = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.CUSTOMS_TAX_STATUS);

				for( StockMgmt stockMgmt : stockMgmts ) {
					sfm = new StockFileModel();

					sfm.setStockId(stockMgmt.getId());
					sfm.setStockStatus(stockMgmt.getStateInterp());
					sfm.setTxnId( stockMgmt.getTxnId());
					sfm.setCreatedOn(stockMgmt.getCreatedOn().format(dtf));
					sfm.setModifiedOn( stockMgmt.getModifiedOn().format(dtf));
					sfm.setFileName( stockMgmt.getFileName());

					logger.debug(sfm);

					fileRecords.add(sfm);
				}

				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath, fileStorageProperties.getStockDownloadLink() + fileName ); 

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
			StockMgmt stockMgmt = stockManagementRepository.getByTxnId(consignmentUpdateRequest.getTxnId());

			// Fetch user_profile to update user over mail/sms regarding the action.
			userProfile = userProfileRepository.getByUserId(consignmentUpdateRequest.getUserId());

			if(Objects.isNull(stockMgmt)) {
				String message = "TxnId Does not Exist";
				logger.info(message + " " + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(4, message, consignmentUpdateRequest.getTxnId());
			}

			// 0 - Accept, 1 - Reject
			if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
				String mailTag = null;
				String action = null;

				if(consignmentUpdateRequest.getAction() == 0) {
					action = SubFeatures.ACCEPT;
					mailTag = "STOCK_APPROVED_BY_CEIR_ADMIN"; 
					stockMgmt.setStockStatus(StockStatus.APPROVED_BY_CEIR_ADMIN.getCode());
				}else {
					action = SubFeatures.REJECT;
					mailTag = "STOCK_REJECT_BY_CEIR_ADMIN";
					stockMgmt.setStockStatus(StockStatus.REJECTED_BY_CEIR_ADMIN.getCode());
					stockMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
				}

				// Update Stock and its history.
				if(!updateStatusWithHistory(stockMgmt)){
					logger.warn("Unable to update Stolen and recovery entity.");
					return new GenricResponse(3, "Unable to update stock entity.", consignmentUpdateRequest.getTxnId()); 
				}else {
					emailUtil.saveNotification(mailTag, 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.STOCK,
							action,
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT);
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
}