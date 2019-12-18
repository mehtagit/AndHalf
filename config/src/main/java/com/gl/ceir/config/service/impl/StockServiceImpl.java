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
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.StockMgmt;
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
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.StockMgmtSpecificationBuiler;
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
	StockDetailsOperationRepository stockDetailsOperationRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired	
	EmailUtil emailUtil;
	
	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl; 

	public GenricResponse uploadStock(StockMgmt stackholderRequest) {

		try {
			stackholderRequest.setStockStatus(StockStatus.UPLOADING.getCode());

			if("Custom".equalsIgnoreCase(stackholderRequest.getUserType())) {
				User user =	userRepository.getByUsername(stackholderRequest.getSupplierId());
				stackholderRequest.setUserId(new Long(user.getId()));
				stackholderRequest.setUser(user);
				
			}

			stockManagementRepository.save(stackholderRequest);

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.UPLOAD.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(stackholderRequest.getTxnId());

			webActionDbRepository.save(webActionDb);

			return new GenricResponse(0, "Upload Successfully", stackholderRequest.getTxnId());

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
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

		List<StateMgmtDb> stateInterpList = null;
		List<StateMgmtDb> statusList = null;

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());

			if("CEIRAdmin".equalsIgnoreCase(filterRequest.getUserType())) {
				filterRequest.setUserType("Custom");
			}

			StockMgmtSpecificationBuiler smsb = new StockMgmtSpecificationBuiler(propertiesReader.dialect);

			if("Importer".equalsIgnoreCase(filterRequest.getUserType()) || 
					"Distributor".equalsIgnoreCase(filterRequest.getUserType()) || 
					"Retailer".equalsIgnoreCase(filterRequest.getUserType())) {
				
				if(Objects.nonNull(filterRequest.getUserId()) )
					smsb.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));

				if(Objects.nonNull(filterRequest.getUserId()))
					smsb.with(new SearchCriteria("roleType", filterRequest.getRoleType(), SearchOperation.EQUALITY, Datatype.STRING));
			} 

			if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
				smsb.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
				smsb.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getTxnId()) && !filterRequest.getTxnId().isEmpty())
				smsb.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getUserType()) && "Custom".equalsIgnoreCase(filterRequest.getUserType()))
				smsb.with(new SearchCriteria("userType", filterRequest.getUserType(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
				smsb.with(new SearchCriteria("stockStatus", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			
			}else {
				if(Objects.nonNull(filterRequest.getFeatureId()) && Objects.nonNull(filterRequest.getUserTypeId())) {

					List<Integer> consignmentStatus = new LinkedList<Integer>();
					// featureList =	stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(consignmentMgmt.getFeatureId(), consignmentMgmt.getUserTypeId());
					logger.debug(statusList);

					if(Objects.nonNull(statusList)) {	
						for(StateMgmtDb stateDb : statusList ) {
							consignmentStatus.add(stateDb.getState());
						}
						logger.info("Array list to add is = " + consignmentStatus);

						smsb.addSpecification(smsb.in(new SearchCriteria("consignmentStatus", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.INT), consignmentStatus));
					}
				}
			}

			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
				smsb.orSearch(new SearchCriteria("taxPaidStatus", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				smsb.orSearch(new SearchCriteria("txnId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			}

			Page<StockMgmt> page = stockManagementRepository.findAll(smsb.build(), pageable);
			stateInterpList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info(stateInterpList);
			
			for(StockMgmt stockMgmt : page.getContent()) {
				for(StateMgmtDb stateMgmtDb : stateInterpList) {
					if(stockMgmt.getStockStatus() == stateMgmtDb.getState()) {
						stockMgmt.setStateInterp(stateMgmtDb.getInterp()); 
						// break;
					}
				}
			}

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public StockMgmt view(StockMgmt stockMgmt) {
		try {

			return stockManagementRepository.getByTxnId(stockMgmt.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	public GenricResponse deleteStockDetailes(StockMgmt stockMgmt) {
		try {

			StockMgmt txnRecord	= stockManagementRepository.findByRoleTypeAndTxnId(stockMgmt.getRoleType(), stockMgmt.getTxnId());

			if(Objects.isNull(txnRecord)) {
				return new GenricResponse(1000, "No record found against this transactionId.", stockMgmt.getTxnId());
			}else {

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
				webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(stockMgmt.getTxnId());

				webActionDbRepository.save(webActionDb);
			}

			return new GenricResponse(0, "Delete In Progress.",stockMgmt.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	public GenricResponse updateStockInfo(StockMgmt distributerManagement) {

		StockMgmt stackHolderInfo =	stockManagementRepository.findByRoleTypeAndTxnId(distributerManagement.getRoleType(), distributerManagement.getTxnId());

		if(Objects.isNull(stackHolderInfo)) {
			return new GenricResponse(1000, "No record found against this transactionId.",distributerManagement.getTxnId());

		}else {

			stackHolderInfo.setInvoiceNumber(distributerManagement.getInvoiceNumber());
			stackHolderInfo.setQuantity(distributerManagement.getQuantity());
			stackHolderInfo.setSuplierName(distributerManagement.getSuplierName());
			stackHolderInfo.setSupplierId(distributerManagement.getSupplierId());
			stackHolderInfo.setTotalPrice(distributerManagement.getTotalPrice());

			if(distributerManagement.getFileName() != null || distributerManagement.getFileName() != " ") {
				stackHolderInfo.setStockStatus(StockStatus.PROCESSING.getCode());
				stackHolderInfo.setFileName(distributerManagement.getFileName());
			}

			stockManagementRepository.save(stackHolderInfo);

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.STOCK.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(distributerManagement.getTxnId());

			webActionDbRepository.save(webActionDb);

			/*}else {

				return new GenricResponse(200, "Operation Not Allowed.",distributerManagement.getTxnId());
			}*/

			return new GenricResponse(0, "Update SuccessFully",distributerManagement.getTxnId());
		}
	}
	
	public FileDetails getFilteredStockInFile(FilterRequest filterRequest, Integer pageNo, Integer pageSize) {
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
			List<StockMgmt> stockMgmts = getAllFilteredData(filterRequest, pageNo, pageSize).getContent();
			
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
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			
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

	public ResponseCountAndQuantity getStockCountAndQuantity( long userId, Integer userTypeId, Integer featureId ) {
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
			return stockManagementRepository.getStockCountAndQuantity( userId, status );
		} catch (Exception e) {
			//logger.error(e.getMessage(), e);
			//throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
			return new ResponseCountAndQuantity(0,0);
		}
	}

	@Transactional
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
				if(consignmentUpdateRequest.getAction() == 0) {

					stockMgmt.setStockStatus(StockStatus.APPROVED_BY_CEIR_ADMIN.getCode());
					stockManagementRepository.save(stockMgmt);	

					emailUtil.sendMessageAndSaveNotification("STOCK_APPROVED_BY_CEIR_ADMIN", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.STOCK,
							SubFeatures.ACCEPT,
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT);

				}else {
					stockMgmt.setStockStatus(StockStatus.REJECTED_BY_CEIR_ADMIN.getCode());
					stockMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
					stockManagementRepository.save(stockMgmt);

					emailUtil.sendMessageAndSaveNotification("STOCK_REJECT_BY_CEIR_ADMIN", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.STOCK,
							SubFeatures.REJECT,
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT);
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

}