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
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.DashboardUsersFeatureStateMap;
import com.gl.ceir.config.model.FeatureValidateReq;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenericResponseObject;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RawMail;
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
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.ReferTable;
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
import com.gl.ceir.config.repository.DashboardUsersFeatureStateMapRepository;
import com.gl.ceir.config.repository.MessageConfigurationDbRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.service.businesslogic.StateMachine;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.transaction.ConsignmentTransaction;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.HttpResponse;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;
import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class ConsignmentServiceImpl {

	private static final Logger logger = LogManager.getLogger(ConsignmentServiceImpl.class);

	private final String CEIRSYSTEM = "CEIRSYSTEM";

	@Autowired
	private ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired	
	StockDetailsOperationRepository stockDetailsOperationRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	DashboardUsersFeatureStateMapRepository dashboardUsersFeatureStateMapRepository; 

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

	@Autowired
	PendingTacApprovedImpl pendingTacApprovedImpl;

	@Autowired
	UserStaticServiceImpl userStaticServiceImpl;

	@Autowired
	ConsignmentTransaction consignmentTransaction;

	@Autowired
	UserRepository userRepository;


	@Autowired
	UserFeignClient userFeignClient;
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

			if(consignmentTransaction.executeRegisterConsignment(consignmentFileRequest, webActionDb)) {
				return new GenricResponse(0, "Register Successfully", consignmentFileRequest.getTxnId());
			}else {
				return new GenricResponse(1, "Consignment Registeration failed.", consignmentFileRequest.getTxnId());
			}

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
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

			
			/*
			 * GenricResponse response = userFeignClient.nameById(3); Object
			 * objectResponse=response.getData(); Gson gson= new Gson(); String apiResponse
			 * = gson.toJson(objectResponse); GenericResponseObject genericResponseObject =
			 * gson.fromJson(apiResponse, GenericResponseObject.class);
			 * logger.info("name::::::::::"+genericResponseObject.getName());
			 */
			
			auditTrailRepository.save(new AuditTrail(consignmentMgmt.getUserId(), consignmentMgmt.getUserName(), 
					Long.valueOf(consignmentMgmt.getUserTypeId()), consignmentMgmt.getUserType(), Long.valueOf(consignmentMgmt.getFeatureId()),
					Features.CONSIGNMENT, SubFeatures.VIEW, "", "NA"));
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

			FilterRequest filterRequest = new FilterRequest().setTxnId(consignmentMgmt.getTxnId());
			if(pendingTacApprovedImpl.findByTxnId(filterRequest).getErrorCode() == 0) {
				logger.info("Tac related to the consignment with txn_id [" + consignmentMgmt.getTxnId() + "] found in pending_tac_approval_db");
				consignmentMgmt.setPendingTacApprovedByCustom("Y");
			}else {
				logger.info("No tac for the consignment with txn_id [" + consignmentMgmt.getTxnId() + "] is pending.");
				consignmentMgmt.setPendingTacApprovedByCustom("N");
			}

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
				consignmentInfo.setPortAddress(consignmentFileRequest.getPortAddress());
				consignmentInfo.setDeviceQuantity(consignmentFileRequest.getDeviceQuantity());

				consignmentInfo.setUserName(consignmentFileRequest.getUserName());
				consignmentInfo.setUserType(consignmentFileRequest.getUserType());
				consignmentInfo.setUserTypeId(consignmentFileRequest.getUserTypeId());
				consignmentInfo.setFeatureId(consignmentFileRequest.getFeatureId());
				// pending tac if available in pending_tac_approval_db.
				FilterRequest filterRequest = new FilterRequest().setTxnId(consignmentFileRequest.getTxnId());
				if(pendingTacApprovedImpl.findByTxnId(filterRequest).getErrorCode() == 0) {
					logger.info("Tac related to the consignment with txn_id [" + consignmentFileRequest.getTxnId() + "] found in pending_tac_approval_db");
					consignmentInfo.setPendingTacApprovedByCustom("Y");
				}else {
					logger.info("No tac for the consignment with txn_id [" + consignmentFileRequest.getTxnId() + "] is pending.");
					consignmentInfo.setPendingTacApprovedByCustom("N");
				}

				if(Objects.nonNull(consignmentFileRequest.getFileName()) && !consignmentFileRequest.getFileName().isEmpty()){
					consignmentInfo.setConsignmentStatus(ConsignmentStatus.INIT.getCode());	
					consignmentInfo.setFileName(consignmentFileRequest.getFileName());
				}

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
				webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(consignmentFileRequest.getTxnId());

				if(consignmentTransaction.executeUpdateConsignment(consignmentInfo, webActionDb)) {
					return new GenricResponse(0, "Consignment Update in Processing.", consignmentFileRequest.getTxnId());
				}else {
					return new GenricResponse(1, "Consignment Update have been failed.", consignmentFileRequest.getTxnId());
				}
			}				
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	} 

	public GenricResponse deleteConsigmentInfo(ConsignmentUpdateRequest consignmentUpdateRequest) {
		try {
			if(Objects.isNull(consignmentUpdateRequest.getTxnId())) {
				logger.info("TxnId is null in the request." + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(1001, "TxnId is null in the request.", consignmentUpdateRequest.getTxnId());
			}

			if(Objects.isNull(consignmentUpdateRequest.getUserType())) {
				logger.info("userType is null in the request." + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(1002, "userType is null in the request.", consignmentUpdateRequest.getTxnId());
			}

			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(consignmentUpdateRequest.getTxnId());

			if(Objects.isNull(consignmentMgmt)) {
				logger.info("Consignment Does not Exist" + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(4, "Consignment Does not Exist",consignmentUpdateRequest.getTxnId());
			}
			
			if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getUserType())) {
				consignmentMgmt.setConsignmentStatus(ConsignmentStatus.WITHDRAWN_BY_CEIR.getCode());
			}else if("IMPORTER".equalsIgnoreCase(consignmentUpdateRequest.getUserType())) {
				// Check status must be Init or Rejected by system.
				if(consignmentMgmt.getConsignmentStatus() == ConsignmentStatus.INIT.getCode() || 
				consignmentMgmt.getConsignmentStatus() == ConsignmentStatus.REJECTED_BY_SYSTEM.getCode()) {
					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.WITHDRAWN_BY_IMPORTER.getCode());	
				}else {
					return new GenricResponse(5, GenericMessageTags.INVALID_STATE_TRANSTION.getTag(), GenericMessageTags.INVALID_STATE_TRANSTION.getMessage(), consignmentUpdateRequest.getTxnId());
				}
			}
			else {
				logger.info("UserType is invalid." + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(1, "UserType is invalid.", consignmentUpdateRequest.getTxnId());
			}

			consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
			consignmentMgmt.setDeleteFlag(0);
			consignmentMgmt.setUserTypeId(consignmentUpdateRequest.getUserTypeId());
			consignmentMgmt.setFeatureId(consignmentUpdateRequest.getFeatureId());
			consignmentMgmt.setUserName(consignmentUpdateRequest.getUserName());
			consignmentMgmt.setUserType(consignmentUpdateRequest.getUserType());
						
			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(consignmentUpdateRequest.getTxnId());

			if(consignmentTransaction.executeDeleteConsignment(consignmentMgmt, webActionDb)) {
				logger.info("Deletion of consignment is in Progress."+ consignmentUpdateRequest.getTxnId());
				return new GenricResponse(200, "Deletion of consignment is in Progress.", consignmentUpdateRequest.getTxnId());
			}else {
				logger.info("Deletion of consignment have been failed." + consignmentUpdateRequest.getTxnId());
				return new GenricResponse(2, "Deletion of consignment have been failed.", consignmentUpdateRequest.getTxnId());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse updateConsignmentStatus(ConsignmentUpdateRequest consignmentUpdateRequest) {
		try {
			UserProfile userProfile = null;
			Map<String, String> placeholderMap = new HashMap<>();
			WebActionDb webActionDb = null;

			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(consignmentUpdateRequest.getTxnId());
			logger.debug("Accept/Reject Consignment : " + consignmentMgmt);

			
			
			
			// Fetch user_profile to update user over mail/sms regarding the action.
			userProfile = userProfileRepository.getByUserId(consignmentMgmt.getUserId());
			logger.debug("UserProfile : " + userProfile);
			UserProfile ceirUserProfile = new UserProfile();
			ceirUserProfile.setUser(userStaticServiceImpl.getCeirAdmin());
			logger.debug("CeirUserProfile : " + ceirUserProfile);
			// 0 - Accept, 1 - Reject
			if(0 == consignmentUpdateRequest.getAction()) {

				if(Objects.isNull(consignmentMgmt)) {
					return new GenricResponse(1, "TxnId Does not Exist.", consignmentUpdateRequest.getTxnId());
				}else {

					if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
						if(!StateMachine.isConsignmentStatetransitionAllowedWithAction("CEIRADMIN", consignmentMgmt.getConsignmentStatus(), 0)) {
							logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
							return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
						}
						Integer nextStatus;
						// Check if this feature is supported in current period.
						//GenricResponse response = userFeignClient.usertypeStatus(7);
						
						
						GenricResponse response=mockFeignResponse();
						logger.info("FEIGN : response for validatePeriod " + response);
						if(response.getErrorCode() == 200) {
							nextStatus=ConsignmentStatus.PENDING_APPROVAL_FROM_CUSTOMS.getCode();
						}
						else {
							nextStatus=ConsignmentStatus.APPROVED.getCode();
						}

						logger.info("nextStatus:"+nextStatus);
						consignmentMgmt.setConsignmentStatus(nextStatus);
						
						consignmentMgmt.setCeirAdminID(consignmentUpdateRequest.getUserId());
						
					
						
						placeholderMap.put("<Importer first name>", userProfile.getFirstName());
						placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());

						emailUtil.saveNotification("Consignment_Success_CEIRAuthority_Email_Message", 
								userProfile, 
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT,
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								consignmentMgmt.getTxnId(),
								placeholderMap, null, "Importer");
						

					}else if("CUSTOM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {

						webActionDb = new WebActionDb();
						webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
						webActionDb.setSubFeature(WebActionDbSubFeature.APPROVE.getName());
						webActionDb.setState(WebActionDbState.INIT.getCode());
						webActionDb.setTxnId(consignmentUpdateRequest.getTxnId());
						consignmentMgmt.setCustomID(consignmentUpdateRequest.getUserId());
						if(!StateMachine.isConsignmentStatetransitionAllowed("CUSTOM", consignmentMgmt.getConsignmentStatus())) {
							logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
							return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
						}

						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.APPROVED.getCode());
						consignmentMgmt.setTaxPaidStatus(TaxStatus.TAX_PAID.getCode());
						consignmentMgmt.setCustomID(consignmentUpdateRequest.getUserId());
						
						
						// Delete tac if available in pending_tac_approval_db.
						FilterRequest filterRequest = new FilterRequest().setTxnId(consignmentMgmt.getTxnId());
						if(pendingTacApprovedImpl.findByTxnId(filterRequest).getErrorCode() == 0) {
							logger.info("Tac related to the consignment with txn_id [" + consignmentMgmt.getTxnId() + "] found in pending_tac_approval_db");
							pendingTacApprovedImpl.deletePendingApproval(filterRequest);
							consignmentMgmt.setPendingTacApprovedByCustom("Y");
						}else {
							logger.info("No tac for the consignment with txn_id [" + consignmentMgmt.getTxnId() + "] is pending.");
							consignmentMgmt.setPendingTacApprovedByCustom("N");
						}

						placeholderMap.put("<Importer first name>", userProfile.getFirstName());
						placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());

						emailUtil.saveNotification("Consignment_Approved_CustomImporter_Email_Message", 
								userProfile, 
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT, 
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								consignmentMgmt.getTxnId(),
								placeholderMap, 
								null, 
								"Importer");
						emailUtil.saveNotification("Consignment_Approved_CustomCEIRAdmin_Email_Message", 
								ceirUserProfile,
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT, 
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								consignmentMgmt.getTxnId(),
								placeholderMap, 
								null, 
								"CEIRAdmin");

					}else if(CEIRSYSTEM.equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {

						List<RawMail> rawMails = new LinkedList<>();
						if(!StateMachine.isConsignmentStatetransitionAllowed(CEIRSYSTEM, consignmentMgmt.getConsignmentStatus())) {
							logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
							return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
						}
						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PENDING_APPROVAL_FROM_CEIR_AUTHORITY.getCode());
						
						
						//consignmentMgmt.setCustomID(consignmentUpdateRequest.getUserId());
						UserProfile userProfile2 = new UserProfile();
						User user2 = userRepository.getById(consignmentMgmt.getUserId()); 
						userProfile2.setUser(user2);
						logger.info("userProfile2 " + userProfile2);

						placeholderMap.put("<First name>", userProfile.getFirstName());
						placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId()); 

						rawMails.add(new RawMail("CONSIGNMENT_PROCESS_SUCCESS_TO_IMPORTER_MAIL", 
								userProfile2, 
								consignmentUpdateRequest.getFeatureId(), 
								Features.CONSIGNMENT,
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(), 
								consignmentMgmt.getTxnId(), 
								placeholderMap, ReferTable.USERS, 
								consignmentUpdateRequest.getRoleType(),
								"Importer"));

						rawMails.add(new RawMail("CONSIGNMENT_PROCESS_SUCCESS_TO_CEIR_MAIL", 
								ceirUserProfile, 
								consignmentUpdateRequest.getFeatureId(), 
								Features.CONSIGNMENT,
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(), 
								consignmentMgmt.getTxnId(), 
								placeholderMap, ReferTable.USERS, 
								consignmentUpdateRequest.getRoleType(),
								"CEIRAdmin"));

						emailUtil.saveNotification(rawMails);

					}
					else if("DRT".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {

						

						if(!StateMachine.isConsignmentStatetransitionAllowed("DRT", consignmentMgmt.getConsignmentStatus())) {
							logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
							return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
						}

						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.APPROVED.getCode());	
						consignmentMgmt.setDrtID(consignmentUpdateRequest.getUserId());

						
						placeholderMap.put("<Importer first name>", userProfile.getFirstName());
						placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());

						emailUtil.saveNotification("Consignment_Approved_DRTImporter_Email_Message", 
								userProfile, 
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT, 
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								consignmentMgmt.getTxnId(),
								placeholderMap, 
								null, 
								"Importer");
						emailUtil.saveNotification("Consignment_Approved_DRTCEIRAdmin_Email_Message", 
								ceirUserProfile,
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT, 
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								consignmentMgmt.getTxnId(),
								placeholderMap, 
								null, 
								"CEIRAdmin");

					}
					else {
						logger.info("Nothing to update for request " + consignmentUpdateRequest);
					}
				}
			}else {
				if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){
					if(!StateMachine.isConsignmentStatetransitionAllowedWithAction("CEIRADMIN", consignmentMgmt.getConsignmentStatus(), 1)) {
						logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
						return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
					}

					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.REJECTED_BY_CEIR_AUTHORITY.getCode());
					consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
					consignmentMgmt.setCeirAdminID(consignmentUpdateRequest.getUserId());
					
				
					
					placeholderMap.put("<Importer first name>", userProfile.getFirstName());
					placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());

					emailUtil.saveNotification("Consignment_Reject_CEIRAuthority_Email_Message", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.REJECT,
							consignmentUpdateRequest.getTxnId(),
							consignmentMgmt.getTxnId(),
							placeholderMap, 
							null,
							"Importer");

				}else if("CUSTOM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {
					if(!StateMachine.isConsignmentStatetransitionAllowed("CUSTOM", consignmentMgmt.getConsignmentStatus())) {
						logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
						return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
					}

					
					Integer nextStatus;
					// Check if this feature is supported in current period.
					//GenricResponse response = userFeignClient.usertypeStatus(21);
					GenricResponse response=mockFeignResponse();
					logger.info("FEIGN : response for validatePeriod " + response);
					if(response.getErrorCode() == 200) {
						nextStatus=ConsignmentStatus.PENDING_CLEARANCE_FROM_DRT.getCode();
					}
					else {
						nextStatus=ConsignmentStatus.APPROVED.getCode();
					}

					logger.info("nextStatus:"+nextStatus);
					consignmentMgmt.setConsignmentStatus(nextStatus);



					consignmentMgmt.setCustomID(consignmentUpdateRequest.getUserId());
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
							consignmentMgmt.getTxnId(),
							placeholderMap, 
							null,
							"Importer");

					emailUtil.saveNotification("CONSIGNMENT_REJECTED_BY_CUSTOM_TO_CEIR_EMAIL", 
							ceirUserProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.REJECT, 
							consignmentUpdateRequest.getTxnId(),
							consignmentMgmt.getTxnId(),
							placeholderMap, 
							null,
							"Importer");

				}else if(CEIRSYSTEM.equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {
					List<RawMail> rawMails = new LinkedList<>();
					if(!StateMachine.isConsignmentStatetransitionAllowed(CEIRSYSTEM, consignmentMgmt.getConsignmentStatus())) {
						logger.info("state transition is not allowed." + consignmentUpdateRequest.getTxnId());
						return new GenricResponse(3, "state transition is not allowed.", consignmentUpdateRequest.getTxnId());
					}
					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.REJECTED_BY_SYSTEM.getCode());
					
				
					
					UserProfile userProfile2 = new UserProfile();
					User user2 = userRepository.getById(consignmentMgmt.getUserId()); 
					userProfile2.setUser(user2);
					logger.info("userProfile2 " + userProfile2);
					placeholderMap.put("<First name>", userProfile.getFirstName());
					placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());

					rawMails.add(new RawMail("CONSIGNMENT_PROCESS_FAILED_TO_IMPORTER_MAIL", 
							userProfile2, 
							consignmentUpdateRequest.getFeatureId(), 
							Features.CONSIGNMENT,
							SubFeatures.ACCEPT,
							consignmentUpdateRequest.getTxnId(), 
							consignmentMgmt.getTxnId(), 
							placeholderMap, ReferTable.USERS, 
							consignmentUpdateRequest.getRoleType(),
							"Importer"));

					emailUtil.saveNotification(rawMails);

				}
				
				else if("DRT".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {
					/*
					 * if(!StateMachine.isConsignmentStatetransitionAllowed("CUSTOM",
					 * consignmentMgmt.getConsignmentStatus())) {
					 * logger.info("state transition is not allowed." +
					 * consignmentUpdateRequest.getTxnId()); return new GenricResponse(3,
					 * "state transition is not allowed.", consignmentUpdateRequest.getTxnId()); }
					 */


					Integer nextStatus;
					// Check if this feature is supported in current period.
					//GenricResponse response = userFeignClient.usertypeStatus(21);
					GenricResponse response=mockFeignResponse();
					logger.info("FEIGN : response for validatePeriod " + response);
					if(response.getErrorCode() == 200) {
						nextStatus=ConsignmentStatus.REJECTED_BY_DRT.getCode();
					}
					else {
						logger.info("Operation not allowed." + consignmentUpdateRequest.getTxnId());
						return new GenricResponse(5, "Operation not allowed.", consignmentUpdateRequest.getTxnId());
			
					}

					logger.info("nextStatus:"+nextStatus);
					consignmentMgmt.setConsignmentStatus(nextStatus);
					consignmentMgmt.setDrtID(consignmentUpdateRequest.getUserId());
					consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());

					
					placeholderMap.put("<Importer first name>", userProfile.getFirstName());
					placeholderMap.put("<txn_name>", consignmentMgmt.getTxnId());

					emailUtil.saveNotification("'Consignment_Rejected_DRT_Email_Message ", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.REJECT, 
							consignmentUpdateRequest.getTxnId(),
							consignmentMgmt.getTxnId(),
							placeholderMap, 
							null,
							"Importer");

					emailUtil.saveNotification("CONSIGNMENT_REJECTED_BY_DRT_TO_CEIR_EMAIL'", 
							ceirUserProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.REJECT, 
							consignmentUpdateRequest.getTxnId(),
							consignmentMgmt.getTxnId(),
							placeholderMap, 
							null,
							"Importer");

				}
				else {
					logger.info("Nothing to update for request " + consignmentUpdateRequest);
				}
			}
			//TODO check if CUSTOM approved the consignment than we need to add an entry in webaction
			consignmentMgmt.setUserName(consignmentUpdateRequest.getUserName());
			consignmentMgmt.setUserType(consignmentUpdateRequest.getRoleType());
			consignmentMgmt.setUserTypeId(consignmentUpdateRequest.getRoleTypeUserId().intValue());
			consignmentMgmt.setFeatureId(consignmentUpdateRequest.getFeatureId());
			
			
			if(consignmentTransaction.executeUpdateStatusConsignment(consignmentMgmt,webActionDb)) {
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



	public FileDetails getFilteredConsignmentInFileV2(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;

		ConsignmentFileModel cfm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
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

			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !consignmentMgmts.isEmpty() ) {
				fileRecords = new ArrayList<>();
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
					Features.CONSIGNMENT, SubFeatures.VIEW, "", "NA"));
			logger.info("AUDIT : Saved file export request in audit.");

			FileDetails fileDetails = new FileDetails( fileName, filepath.getValue(), link.getValue() + fileName );
			logger.info(fileDetails);
			return fileDetails;

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
		List<Integer> status = new ArrayList<>();
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
			return new ResponseCountAndQuantity(0,0);
		}
	}

	private GenericSpecificationBuilder<ConsignmentMgmt> buildSpecification(FilterRequest consignmentMgmt, List<StateMgmtDb> statusList){
		GenericSpecificationBuilder<ConsignmentMgmt> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
		String consignmentStatusLiteral = "consignmentStatus";

		if("IMPORTER".equalsIgnoreCase(consignmentMgmt.getUserType()) 
				&& Objects.nonNull(consignmentMgmt.getUserId())) {

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

		// Status handling.
		if("CEIRADMIN".equalsIgnoreCase(consignmentMgmt.getUserType())) {
			if(Objects.isNull(consignmentMgmt.getConsignmentStatus()))
				cmsb.with(new SearchCriteria(consignmentStatusLiteral, 3, SearchOperation.EQUALITY, Datatype.STRING));
			else {
				cmsb.with(new SearchCriteria(consignmentStatusLiteral, consignmentMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			}
		}else if("Custom".equalsIgnoreCase(consignmentMgmt.getUserType())) {
			if(Objects.isNull(consignmentMgmt.getConsignmentStatus()))
				cmsb.with(new SearchCriteria(consignmentStatusLiteral, 5, SearchOperation.EQUALITY, Datatype.STRING));
			else {
				cmsb.with(new SearchCriteria(consignmentStatusLiteral, consignmentMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
			}
		}else if(Objects.nonNull(consignmentMgmt.getConsignmentStatus())) {
			cmsb.with(new SearchCriteria("consignmentStatus", consignmentMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
		}else {

			if(Objects.nonNull(consignmentMgmt.getFeatureId()) && Objects.nonNull(consignmentMgmt.getUserTypeId())) {

				List<DashboardUsersFeatureStateMap> dashboardUsersFeatureStateMap = dashboardUsersFeatureStateMapRepository.findByUserTypeIdAndFeatureId(consignmentMgmt.getUserTypeId(), consignmentMgmt.getFeatureId());
				logger.debug(dashboardUsersFeatureStateMap);
				List<Integer> consignmentStatus = new LinkedList<>();


				if(Objects.nonNull(dashboardUsersFeatureStateMap)) {	
					for(DashboardUsersFeatureStateMap dashboardUsersFeatureStateMap2 : dashboardUsersFeatureStateMap ) {
						consignmentStatus.add(dashboardUsersFeatureStateMap2.getState());
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

	public void setInterp(ConsignmentMgmt consignmentMgmt) {
		if(Objects.nonNull(consignmentMgmt.getExpectedArrivalPort()))
			consignmentMgmt.setExpectedArrivalPortInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_PORT, consignmentMgmt.getExpectedArrivalPort()));

		if(Objects.nonNull(consignmentMgmt.getCurrency()))
			consignmentMgmt.setCurrencyInterp(interpSetter.setConfigInterp(Tags.CURRENCY, consignmentMgmt.getCurrency()));

		if(Objects.nonNull(consignmentMgmt.getTaxPaidStatus()))
			consignmentMgmt.setTaxInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_TAX_STATUS, consignmentMgmt.getTaxPaidStatus()));

		if(Objects.nonNull(consignmentMgmt.getTaxPaidStatus()))
			consignmentMgmt.setDeleteFlagInterp(interpSetter.setConfigInterp(Tags.DELETE_FLAG, consignmentMgmt.getDeleteFlag()));

	}
	
	private GenricResponse mockFeignResponse() {
		return new GenricResponse(200);	
	}
}


