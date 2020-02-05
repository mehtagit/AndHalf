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
import com.gl.ceir.config.EmailSender.MailSubjects;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.ConsignmentStatus;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.model.constants.TaxStatus;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.model.file.ConsignmentFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.MessageConfigurationDbRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.service.businesslogic.StateMachine;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder2;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class ConsignmentServiceImpl {

	private static final Logger logger = LogManager.getLogger(ConsignmentServiceImpl.class);

	//private final Path fileStorageLocation;

	@Autowired
	private ConsignmentRepository consignmentRepository;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired	
	StockDetailsOperationRepository stockDetailsOperationRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	MessageConfigurationDbRepository messageConfigurationDbRepository;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired
	InterpSetter interpSetter;

	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;
	
	public GenricResponse registerConsignment(ConsignmentMgmt consignmentFileRequest) {

		try {
			Long importerId = Long.valueOf(consignmentFileRequest.getUserId());

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.CONSIGNMENT_REGISTER.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(consignmentFileRequest.getTxnId());

			consignmentFileRequest.setConsignmentStatus(ConsignmentStatus.INIT.getCode());
			consignmentFileRequest.setTaxPaidStatus(TaxStatus.TAX_NOT_PAID.getCode());
			// Set user for mapping.
			consignmentFileRequest.setUser(new User().setId(importerId));

			if(executeRegisterConsignment(consignmentFileRequest, webActionDb)) {
				return new GenricResponse(0, "Register Successfully", consignmentFileRequest.getTxnId());
			}else {
				return new GenricResponse(1, "Consignment Registeration failed.", consignmentFileRequest.getTxnId());
			}

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	private boolean executeRegisterConsignment(ConsignmentMgmt consignmentMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in web_action_db.");

		consignmentRepository.save(consignmentMgmt);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in consigment_mgmt_db.");

		auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUser().getId(), "", 0L, "", 0L, Features.CONSIGNMENT, SubFeatures.REGISTER, ""));
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	public List<ConsignmentMgmt> getAll(Long importerId) {
		try {
			logger.info("Going to get All Cosignment List ");
			return consignmentRepository.getByUserIdOrderByIdDesc(importerId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<ConsignmentMgmt> getFilterConsignments(FilterRequest consignmentMgmt, Integer pageNo, Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			System.out.println("dialect : " + propertiesReader.dialect);

			return consignmentRepository.findAll(buildSpecification(consignmentMgmt, null).build(), pageable).getContent();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	private List<ConsignmentMgmt> getAll(FilterRequest filterRequest){
		try {

			List<StateMgmtDb> statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info("statusList " + statusList);

			List<ConsignmentMgmt> consignmentMgmts = consignmentRepository.findAll(buildSpecification(filterRequest, statusList).build(), new Sort(Sort.Direction.DESC, "modifiedOn"));
			logger.info("consignmentMgmts " + consignmentMgmts);

			for(ConsignmentMgmt consignmentMgmt2 : consignmentMgmts) {

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(consignmentMgmt2.getConsignmentStatus() == stateMgmtDb.getState()) {
						consignmentMgmt2.setStateInterp(stateMgmtDb.getInterp()); 
						break; 
					} 
				}

				setInterp(consignmentMgmt2);
			}

			logger.info("ConsignmentMgmt : " + consignmentMgmts);
			return consignmentMgmts;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Page<ConsignmentMgmt> getFilterPaginationConsignments(FilterRequest consignmentMgmt, Integer pageNo, 
			Integer pageSize) {

		List<StateMgmtDb> statusList = null;

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(consignmentMgmt.getFeatureId(), consignmentMgmt.getUserTypeId());

			Page<ConsignmentMgmt> page = consignmentRepository.findAll(buildSpecification(consignmentMgmt, statusList).build(), pageable);

			for(ConsignmentMgmt consignmentMgmt2 : page.getContent()) {

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(consignmentMgmt2.getConsignmentStatus() == stateMgmtDb.getState()) {
						consignmentMgmt2.setStateInterp(stateMgmtDb.getInterp()); 
						break; 
					} 
				}

				setInterp(consignmentMgmt2);
			}

			auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUserId(), "", 
					Long.valueOf(consignmentMgmt.getUserTypeId()), consignmentMgmt.getUserType(), Long.valueOf(consignmentMgmt.getFeatureId()),
					Features.CONSIGNMENT, SubFeatures.VIEW, ""));
			logger.info("AUDIT : Saved view request in audit.");
			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public ConsignmentMgmt getRecordInfo(String txnId) {
		try {
			logger.info("Going to get Cosignment Record Info for txnId : " + txnId);

			if(Objects.isNull(txnId)) {
				throw new IllegalArgumentException();
			}

			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(txnId);
			setInterp(consignmentMgmt);

			return consignmentMgmt;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse updateConsignment(ConsignmentMgmt consignmentFileRequest) {

		try {
			ConsignmentMgmt consignmentInfo = consignmentRepository.getByTxnId(consignmentFileRequest.getTxnId());

			logger.info("ConsignmentInfo = " + consignmentInfo.toString());

			if(Objects.isNull(consignmentInfo)) {
				return new GenricResponse(4, "Consignment Does Not exist.", consignmentFileRequest.getTxnId());
			}
			else {
				consignmentInfo.setConsignmentNumber(consignmentFileRequest.getConsignmentNumber());
				consignmentInfo.setExpectedArrivaldate(consignmentFileRequest.getExpectedArrivaldate());
				consignmentInfo.setExpectedArrivalPort(consignmentFileRequest.getExpectedArrivalPort());
				consignmentInfo.setExpectedDispatcheDate(consignmentFileRequest.getExpectedDispatcheDate());
				consignmentInfo.setOrganisationCountry(consignmentFileRequest.getOrganisationCountry());
				consignmentInfo.setQuantity(consignmentFileRequest.getQuantity());
				consignmentInfo.setSupplierId(consignmentFileRequest.getSupplierld());
				consignmentInfo.setSupplierName(consignmentFileRequest.getSupplierName());
				consignmentInfo.setTotalPrice(consignmentFileRequest.getTotalPrice());
				consignmentInfo.setCurrency(consignmentFileRequest.getCurrency());

				if(Objects.nonNull(consignmentFileRequest.getFileName()) && !consignmentFileRequest.getFileName().isEmpty()){
					consignmentInfo.setConsignmentStatus(ConsignmentStatus.INIT.getCode());	
					consignmentInfo.setFileName(consignmentFileRequest.getFileName());
				}

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
				webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(consignmentFileRequest.getTxnId());

				if(executeUpdateConsignment(consignmentInfo, webActionDb)) {
					return new GenricResponse(0, "Consignment Update in Processing.", consignmentFileRequest.getTxnId());
				}else {
					return new GenricResponse(1, "Consignment Update have been failed.", consignmentFileRequest.getTxnId());
				}
			}				
			// }
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	} 

	@Transactional
	private boolean executeUpdateConsignment(ConsignmentMgmt consignmentMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in web_action_db.");

		consignmentRepository.save(consignmentMgmt);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] updated in consigment_mgmt_db.");

		auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUser().getId(), "", 0L, "", 0L, Features.CONSIGNMENT, SubFeatures.UPDATE, ""));
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	public GenricResponse deleteConsigmentInfo(ConsignmentMgmt consignmentRequest, String userType) {
		try {
			if(Objects.isNull(consignmentRequest.getTxnId())) {
				logger.info("TxnId is null in the request." + consignmentRequest.getTxnId());
				return new GenricResponse(1001, "TxnId is null in the request.", consignmentRequest.getTxnId());
			}

			if(Objects.isNull(userType)) {
				logger.info("userType is null in the request." + consignmentRequest.getTxnId());
				return new GenricResponse(1002, "userType is null in the request.", consignmentRequest.getTxnId());
			}

			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(consignmentRequest.getTxnId());

			if(Objects.isNull(consignmentMgmt)) {
				logger.info("Consignment Does not Exist" + consignmentRequest.getTxnId());
				return new GenricResponse(4, "Consignment Does not Exist",consignmentRequest.getTxnId());
			}

			if("CEIRADMIN".equalsIgnoreCase(userType))
				consignmentMgmt.setConsignmentStatus(ConsignmentStatus.WITHDRAWN_BY_CEIR.getCode());
			else if("IMPORTER".equalsIgnoreCase(userType))
				consignmentMgmt.setConsignmentStatus(ConsignmentStatus.WITHDRAWN_BY_IMPORTER.getCode());
			else {
				logger.info("UserType is invalid." + consignmentRequest.getTxnId());
				return new GenricResponse(1, "UserType is invalid.", consignmentRequest.getTxnId());
			}

			consignmentMgmt.setRemarks(consignmentRequest.getRemarks());

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(consignmentRequest.getTxnId());

			if(executeDeleteConsignment(consignmentMgmt, webActionDb)) {
				logger.info("Deletion of consignment is in Progress."+ consignmentRequest.getTxnId());
				return new GenricResponse(200, "Deletion of consignment is in Progress.", consignmentRequest.getTxnId());
			}else {
				logger.info("Deletion of consignment have been failed." + consignmentRequest.getTxnId());
				return new GenricResponse(2, "Deletion of consignment have been failed.", consignmentRequest.getTxnId());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	private boolean executeDeleteConsignment(ConsignmentMgmt consignmentMgmt, WebActionDb webActionDb) {
		boolean queryStatus = Boolean.FALSE;
		webActionDbRepository.save(webActionDb);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in web_action_db.");

		consignmentRepository.save(consignmentMgmt);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] updated in consigment_mgmt_db.");

		auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUser().getId(), "", 0L, "", 0L, Features.CONSIGNMENT, SubFeatures.DELETE, ""));
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}

	public GenricResponse updateConsignmentStatus(ConsignmentUpdateRequest consignmentUpdateRequest) {
		try {
			UserProfile userProfile = null;
			String firstName = "";
			Map<String, String> placeholderMap = new HashMap<String, String>();
			
			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(consignmentUpdateRequest.getTxnId());
			logger.debug("Accept/Reject Consignment : " + consignmentMgmt);

			// Fetch user_profile to update user over mail/sms regarding the action.
			userProfile = userProfileRepository.getByUserId(consignmentMgmt.getUserId());
			logger.debug("UserProfile : " + userProfile);
			

			// 0 - Accept, 1 - Reject
			if(0 == consignmentUpdateRequest.getAction()) {

				if(Objects.isNull(consignmentMgmt)) {
					return new GenricResponse(1, "TxnId Does not Exist.", consignmentUpdateRequest.getTxnId());
				}else {

					if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
						if(!StateMachine.isConsignmentStatetransitionAllowed("CEIRADMIN", consignmentMgmt.getConsignmentStatus())) {
							logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
							return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
						}

						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PENDING_APPROVAL_FROM_CUSTOMS.getCode());

						placeholderMap.put("<Importer first name>", userProfile.getFirstName());
						placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());
						
						emailUtil.saveNotification("Consignment_Success_CEIRAuthority_Email_Message", 
								userProfile, 
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT,
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								MailSubjects.SUBJECT,
								placeholderMap);

					}else if("CUSTOM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {

						if(!StateMachine.isConsignmentStatetransitionAllowed("CUSTOM", consignmentMgmt.getConsignmentStatus())) {
							logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
							return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
						}

						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.APPROVED.getCode());
						consignmentMgmt.setTaxPaidStatus(TaxStatus.TAX_PAID.getCode());
						
						placeholderMap.put("<Importer first name>", userProfile.getFirstName());
						placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());
						
						emailUtil.saveNotification("Consignment_Approved_CustomImporter_Email_Message", 
								userProfile, 
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT, 
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								MailSubjects.SUBJECT,
								placeholderMap);

					}
				}
			}else {
				if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
					if(!StateMachine.isConsignmentStatetransitionAllowed("CEIRADMIN", consignmentMgmt.getConsignmentStatus())) {
						logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
						return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
					}

					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.REJECTED_BY_CEIR_AUTHORITY.getCode());
					consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());

					placeholderMap.put("<Importer first name>", userProfile.getFirstName());
					placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());
					
					emailUtil.saveNotification("Consignment_Reject_CEIRAuthority_Email_Message", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.REJECT,
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT,
							placeholderMap);

				}else if("CUSTOM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {
					if(!StateMachine.isConsignmentStatetransitionAllowed("CUSTOM", consignmentMgmt.getConsignmentStatus())) {
						logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
						return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
					}

					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.REJECTED_BY_CUSTOMS.getCode());
					consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());

					placeholderMap.put("<Importer first name>", userProfile.getFirstName());
					placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());
					
					emailUtil.saveNotification("Consignment_Rejected_Custom_Email_Message", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.REJECT, 
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT,
							placeholderMap);
				}
			}

			if(executeUpdateStatusConsignment(consignmentMgmt)) {
				logger.info("Consignment status have Update SuccessFully." + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(0, "Consignment status have Update SuccessFully.", consignmentUpdateRequest.getTxnId());
			}else {
				logger.info("Consignment status Update have failed." + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(2, "Consignment status Update have failed.", consignmentUpdateRequest.getTxnId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	private boolean executeUpdateStatusConsignment(ConsignmentMgmt consignmentMgmt) {
		boolean queryStatus = Boolean.FALSE;

		consignmentRepository.save(consignmentMgmt);
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in consigment_mgmt_db.");

		auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUser().getId(), "", 0L, "", 0L, Features.CONSIGNMENT, SubFeatures.UPDATE, ""));
		logger.info("Consignment [" + consignmentMgmt.getTxnId() + "] saved in audit_trail.");

		queryStatus = Boolean.TRUE;
		return queryStatus;
	}
	
	public FileDetails getFilteredConsignmentInFileV2(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		//String[] columns = new String[]{"grievanceId","userId","userType","grievanceStatus","txnId","categoryId","fileName","createdOn","modifiedOn","remarks"};
		ConsignmentFileModel cfm = null;
		
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		
		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_consignment_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_consignment_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");
		
		StatefulBeanToCsvBuilder<ConsignmentFileModel> builder = null;
		StatefulBeanToCsv<ConsignmentFileModel> csvWriter      = null;
		List< ConsignmentFileModel> fileRecords                = null;
		CustomMappingStrategy<ConsignmentFileModel> mappingStrategy = new CustomMappingStrategy<>();
		
		try {
			List<ConsignmentMgmt> consignmentMgmts = getAll(filterRequest);
			
			fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_Consignment.csv";
			writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
			mappingStrategy.setType(ConsignmentFileModel.class);
			
			builder = new StatefulBeanToCsvBuilder<ConsignmentFileModel>(writer);
			csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			
			if( consignmentMgmts.size() > 0 ) {
				fileRecords = new ArrayList<ConsignmentFileModel>();
				for( ConsignmentMgmt consignmentMgmt : consignmentMgmts ) {
					cfm = new ConsignmentFileModel(consignmentMgmt.getStateInterp(), 
							consignmentMgmt.getTxnId(), 
							consignmentMgmt.getSupplierName(), consignmentMgmt.getTaxInterp(), consignmentMgmt.getFileName(), 
							consignmentMgmt.getCreatedOn().format(dtf),
							consignmentMgmt.getModifiedOn().format(dtf),
							consignmentMgmt.getQuantity());
					
					fileRecords.add(cfm);
				}
				
				csvWriter.write(fileRecords);
			}else {
				csvWriter.write( new ConsignmentFileModel());
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

	public ResponseCountAndQuantity getConsignmentCountAndQuantity( Integer userId, Integer userTypeId, Integer featureId, String userType ) {
		List<StateMgmtDb> featureList = null;
		List<Integer> status = new ArrayList<Integer>();
		try {
			logger.info("Going to get  Cosignment count and quantity.");
			featureList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId( featureId, userTypeId);
			if(Objects.nonNull(featureList)) {	
				for(StateMgmtDb stateDb : featureList ) {
					status.add(stateDb.getState());
				}
			}
			if( !userType.equalsIgnoreCase("ceiradmin"))
				return consignmentRepository.getConsignmentCountAndQuantity( userId, status);
			else
				return consignmentRepository.getConsignmentCountAndQuantity( status);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
			return new ResponseCountAndQuantity(0,0);
		}
	}

	private GenericSpecificationBuilder<ConsignmentMgmt> buildSpecification(FilterRequest consignmentMgmt, List<StateMgmtDb> statusList){
		GenericSpecificationBuilder<ConsignmentMgmt> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		if("IMPORTER".equalsIgnoreCase(consignmentMgmt.getUserType())) {
			if(Objects.nonNull(consignmentMgmt.getUserId()))
				cmsb.with(new SearchCriteria("userId", consignmentMgmt.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));
		}

		if(Objects.nonNull(consignmentMgmt.getTxnId()) && !consignmentMgmt.getTxnId().isEmpty())
			cmsb.with(new SearchCriteria("txnId", consignmentMgmt.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(consignmentMgmt.getStartDate()) && !consignmentMgmt.getStartDate().isEmpty())
			cmsb.with(new SearchCriteria("createdOn",consignmentMgmt.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(consignmentMgmt.getEndDate()) && !consignmentMgmt.getEndDate().isEmpty())
			cmsb.with(new SearchCriteria("createdOn",consignmentMgmt.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(consignmentMgmt.getTaxPaidStatus()))
			cmsb.with(new SearchCriteria("taxPaidStatus", consignmentMgmt.getTaxPaidStatus(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(consignmentMgmt.getConsignmentStatus())) {
			cmsb.with(new SearchCriteria("consignmentStatus", consignmentMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
		}else {

			if(Objects.nonNull(consignmentMgmt.getFeatureId()) && Objects.nonNull(consignmentMgmt.getUserTypeId())) {

				List<Integer> consignmentStatus = new LinkedList<>();
				// featureList =	stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(consignmentMgmt.getFeatureId(), consignmentMgmt.getUserTypeId());
				logger.debug(statusList);

				if(Objects.nonNull(statusList)) {	
					for(StateMgmtDb stateDb : statusList ) {
						consignmentStatus.add(stateDb.getState());
					}
					logger.info("Array list to add is = " + consignmentStatus);

					if(!consignmentStatus.isEmpty()) {
						cmsb.addSpecification(cmsb.in("consignmentStatus", consignmentStatus));
					}else {
						logger.warn("no status are predefined foe the user.");
					}

				}
			}
		}

		if(Objects.nonNull(consignmentMgmt.getSearchString()) && !consignmentMgmt.getSearchString().isEmpty()){
			cmsb.orSearch(new SearchCriteria("txnId", consignmentMgmt.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return cmsb;
	}

	private void setInterp(ConsignmentMgmt consignmentMgmt) {
		if(Objects.nonNull(consignmentMgmt.getExpectedArrivalPort()))
			consignmentMgmt.setExpectedArrivalPortInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_PORT, consignmentMgmt.getExpectedArrivalPort()));

		if(Objects.nonNull(consignmentMgmt.getCurrency()))
			consignmentMgmt.setCurrencyInterp(interpSetter.setConfigInterp(Tags.CURRENCY, consignmentMgmt.getCurrency()));

		if(Objects.nonNull(consignmentMgmt.getTaxPaidStatus()))
			consignmentMgmt.setTaxInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_TAX_STATUS, consignmentMgmt.getTaxPaidStatus()));
	}
}
