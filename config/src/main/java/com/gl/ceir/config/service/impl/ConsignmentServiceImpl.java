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
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.SystemConfigListDb;
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
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.MessageConfigurationDbRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
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

	@Transactional
	public GenricResponse registerConsignment(ConsignmentMgmt consignmentFileRequest) {

		try {

			/*
			 * if(consignmentFileRequest.getConsignmentNumber() != null ||
			 * consignmentFileRequest.getConsignmentNumber() != " ") {
			 * 
			 * ConsignmentMgmt consignmentMgmt =
			 * consignmentRepository.getByConsignmentNumber(consignmentFileRequest.
			 * getConsignmentNumber());
			 * 
			 * if(consignmentMgmt != null) {
			 * 
			 * return new
			 * GenricResponse(3,"Consignment Already Exist",consignmentFileRequest.getTxnId(
			 * )); } }
			 */
			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.CONSIGNMENT_REGISTER.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(consignmentFileRequest.getTxnId());

			consignmentFileRequest.setConsignmentStatus(ConsignmentStatus.INIT.getCode());
			consignmentFileRequest.setTaxPaidStatus(TaxStatus.TAX_NOT_PAID.getCode());
			webActionDbRepository.save(webActionDb);

			// Set user for mapping.
			consignmentFileRequest.setUser(new User().setId(Long.valueOf(consignmentFileRequest.getUserId())));
			consignmentRepository.save(consignmentFileRequest);

			return new GenricResponse(0, "Register Successfully", consignmentFileRequest.getTxnId());

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

			SpecificationBuilder<ConsignmentMgmt> specificationBuilder = new SpecificationBuilder<ConsignmentMgmt>(propertiesReader.dialect);

			if(Objects.nonNull(consignmentMgmt.getUserId()))
				specificationBuilder.with(new SearchCriteria("userId", consignmentMgmt.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(consignmentMgmt.getStartDate()))
				specificationBuilder.with(new SearchCriteria("createdOn", consignmentMgmt.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(consignmentMgmt.getEndDate()))
				specificationBuilder.with(new SearchCriteria("createdOn",consignmentMgmt.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(consignmentMgmt.getConsignmentStatus()))
				specificationBuilder.with(new SearchCriteria("consignmentStatus", consignmentMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(consignmentMgmt.getTaxPaidStatus()))
				specificationBuilder.with(new SearchCriteria("taxPaidStatus", consignmentMgmt.getTaxPaidStatus(), SearchOperation.EQUALITY, Datatype.STRING));

			List<ConsignmentMgmt> data = consignmentRepository.findByUser_id(consignmentMgmt.getUserId());
			logger.info("Data to be fetch in db using jioin = " + data);

			return consignmentRepository.findAll(specificationBuilder.build(), pageable).getContent();

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

			SpecificationBuilder<ConsignmentMgmt> cmsb = new SpecificationBuilder<ConsignmentMgmt>(propertiesReader.dialect);

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
				cmsb.orSearch(new SearchCriteria("taxPaidStatus", consignmentMgmt.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				cmsb.orSearch(new SearchCriteria("txnId", consignmentMgmt.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			}

			Page<ConsignmentMgmt> page = consignmentRepository.findAll(cmsb.build(), pageable);

			for(ConsignmentMgmt consignmentMgmt2 : page.getContent()) {

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(consignmentMgmt2.getConsignmentStatus() == stateMgmtDb.getState()) {
						consignmentMgmt2.setStateInterp(stateMgmtDb.getInterp()); 
						break; 
					} 
				}

				setInterp(consignmentMgmt2);
			}

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

	@Transactional
	public GenricResponse updateConsignment(ConsignmentMgmt consignmentFileRequest) {

		try {
			ConsignmentMgmt consignmentInfo = consignmentRepository.getByTxnId(consignmentFileRequest.getTxnId());

			logger.info("ConsignmentInfo="+consignmentInfo.toString());

			if(Objects.isNull(consignmentInfo)) {
				return new GenricResponse(4, "Consignment Does Not exist.", consignmentFileRequest.getTxnId());
			}
			else {
				/*
				if(consignmentInfo.getConsignmentNumber() == consignmentFileRequest.getConsignmentNumber()){
					return new GenricResponse(3, "Consignment Already Exist", consignmentFileRequest.getTxnId());	
				}else { */
				consignmentInfo.setConsignmentNumber(consignmentFileRequest.getConsignmentNumber());
				consignmentInfo.setExpectedArrivaldate(consignmentFileRequest.getExpectedArrivaldate());
				consignmentInfo.setExpectedArrivalPort(consignmentFileRequest.getExpectedArrivalPort());
				consignmentInfo.setExpectedDispatcheDate(consignmentFileRequest.getExpectedDispatcheDate());
				consignmentInfo.setOrganisationCountry(consignmentFileRequest.getOrganisationCountry());
				consignmentInfo.setQuantity(consignmentFileRequest.getQuantity());
				consignmentInfo.setSupplierId(consignmentFileRequest.getSupplierld());
				consignmentInfo.setSupplierName(consignmentFileRequest.getSupplierName());
				// consignmentInfo.setTaxPaidStatus(consignmentFileRequest.getTaxPaidStatus());
				consignmentInfo.setTotalPrice(consignmentFileRequest.getTotalPrice());
				consignmentInfo.setCurrency(consignmentFileRequest.getCurrency());

				if(consignmentFileRequest.getFileName() != null && consignmentFileRequest.getFileName() != " "){
					consignmentInfo.setConsignmentStatus(ConsignmentStatus.INIT.getCode());	
					consignmentInfo.setFileName(consignmentFileRequest.getFileName());
				}

				consignmentRepository.save(consignmentInfo);

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
				webActionDb.setSubFeature(WebActionDbSubFeature.UPDATE.getName());
				webActionDb.setState(WebActionDbState.INIT.getCode());
				webActionDb.setTxnId(consignmentFileRequest.getTxnId());

				webActionDbRepository.save(webActionDb);

				return new GenricResponse(0,"Consignment Update in Processing.", consignmentFileRequest.getTxnId());
			}				
			// }
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	} 

	@Transactional
	public GenricResponse deleteConsigmentInfo(ConsignmentMgmt consignmentRequest, String userType) {
		try {
			if(Objects.isNull(consignmentRequest.getTxnId())) {
				logger.info("TxnId is null in the request.");
				return new GenricResponse(1001, "TxnId is null in the request.", consignmentRequest.getTxnId());
			}

			if(Objects.isNull(userType)) {
				logger.info("userType is null in the request.");
				return new GenricResponse(1002, "TxnId is null in the request.", consignmentRequest.getTxnId());
			}

			ConsignmentMgmt consignment = consignmentRepository.getByTxnId(consignmentRequest.getTxnId());

			if(Objects.isNull(consignment)) {
				return new GenricResponse(4, "Consignment Does not Exist",consignmentRequest.getTxnId());
			}

			if("CEIRADMIN".equalsIgnoreCase(userType))
				consignment.setConsignmentStatus(ConsignmentStatus.WITHDRAWN_BY_CEIR.getCode());
			else if("IMPORTER".equalsIgnoreCase(userType))
				consignment.setConsignmentStatus(ConsignmentStatus.WITHDRAWN_BY_IMPORTER.getCode());
			else
				return new GenricResponse(1, "UserType is invalid.", consignmentRequest.getTxnId());

			consignment.setRemarks(consignmentRequest.getRemarks());

			consignmentRepository.save(consignment);

			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(WebActionDbFeature.CONSIGNMENT.getName());
			webActionDb.setSubFeature(WebActionDbSubFeature.DELETE.getName());
			webActionDb.setState(WebActionDbState.INIT.getCode());
			webActionDb.setTxnId(consignmentRequest.getTxnId());

			webActionDbRepository.save(webActionDb);

			return new GenricResponse(200, "Delete In Progress", consignmentRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	public GenricResponse updateConsignmentStatus(ConsignmentUpdateRequest consignmentUpdateRequest) {
		try {
			UserProfile userProfile = null;
			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(consignmentUpdateRequest.getTxnId());
			logger.debug("Accept/Reject Consignment : " + consignmentMgmt);

			// Fetch user_profile to update user over mail/sms regarding the action.
			userProfile = userProfileRepository.getByUserId(consignmentUpdateRequest.getUserId());
			logger.debug("userProfile : " + userProfile);

			// 0 - Accept, 1 - Reject
			if(0 == consignmentUpdateRequest.getAction()) {

				if(Objects.isNull(consignmentMgmt)) {
					return new GenricResponse(4, "TxnId Does not Exist", consignmentUpdateRequest.getTxnId());
				}else {

					if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){

						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PENDING_APPROVAL_FROM_CUSTOMS.getCode());
						consignmentRepository.save(consignmentMgmt);	

						emailUtil.saveNotification("Consignment_Success_CEIRAuthority_Email_Message", 
								userProfile, 
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT,
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								MailSubjects.SUBJECT);

					}else if("CUSTOM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {

						consignmentMgmt.setConsignmentStatus(ConsignmentStatus.APPROVED.getCode());
						consignmentRepository.save(consignmentMgmt);

						emailUtil.saveNotification("Consignment_Approved_CustomImporter_Email_Message", 
								userProfile, 
								consignmentUpdateRequest.getFeatureId(),
								Features.CONSIGNMENT, 
								SubFeatures.ACCEPT,
								consignmentUpdateRequest.getTxnId(),
								MailSubjects.SUBJECT);

					}
				}
			}else {
				if("CEIRADMIN".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())){

					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.REJECTED_BY_CEIR_AUTHORITY.getCode());
					consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
					consignmentRepository.save(consignmentMgmt);

					emailUtil.saveNotification("Consignment_Reject_CEIRAuthority_Email_Message", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.REJECT,
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT);

				}else if("CUSTOM".equalsIgnoreCase(consignmentUpdateRequest.getRoleType())) {

					consignmentMgmt.setConsignmentStatus(ConsignmentStatus.REJECTED_BY_CUSTOMS.getCode());
					consignmentMgmt.setRemarks(consignmentUpdateRequest.getRemarks());
					consignmentRepository.save(consignmentMgmt);

					emailUtil.saveNotification("Consignment_Rejected_Custom_Email_Message", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.REJECT, 
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT);
				}
			}

			return new GenricResponse(0, "Consignment Update SuccessFully.", consignmentUpdateRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public FileDetails getFilteredConsignmentInFile(FilterRequest filterRequest, Integer pageNo, Integer pageSize) {
		String fileName = null;
		Writer writer   = null;
		ConsignmentFileModel cfm = null;
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		String filePath  = fileStorageProperties.getConsignmentDownloadDir();
		StatefulBeanToCsvBuilder<ConsignmentFileModel> builder = null;
		StatefulBeanToCsv<ConsignmentFileModel> csvWriter = null;
		List< ConsignmentFileModel > fileRecords = null;

		// HeaderColumnNameTranslateMappingStrategy<GrievanceFileModel> mapStrategy = null;
		try {
			List<ConsignmentMgmt> consignmentMgmts = getFilterPaginationConsignments(filterRequest, pageNo, pageSize).getContent();
			if( !consignmentMgmts.isEmpty() ) {
				if(Objects.nonNull(filterRequest.getUserId()) && (filterRequest.getUserId() != -1 && filterRequest.getUserId() != 0)) {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_" + consignmentMgmts.get(0).getUser().getUsername()+"_Consignments.csv";
				}else {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_Consignments.csv";
				}
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_Consignments.csv";
			}

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<ConsignmentFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();

			if( !consignmentMgmts.isEmpty() ) {

				List<SystemConfigListDb> customTagStatusList = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.CUSTOMS_TAX_STATUS);
				fileRecords = new ArrayList<>(); 

				for(ConsignmentMgmt consignmentMgmt : consignmentMgmts ) {
					cfm = new ConsignmentFileModel();

					cfm.setConsignmentId( consignmentMgmt.getId() );
					cfm.setConsignmentStatus( consignmentMgmt.getStateInterp());

					for( SystemConfigListDb config : customTagStatusList ) {

						if( config.getValue() == consignmentMgmt.getTaxPaidStatus() ) {
							cfm.setTaxPaidStatus(config.getInterp());
						}
					}

					cfm.setTxnId( consignmentMgmt.getTxnId());
					cfm.setCreatedOn(consignmentMgmt.getCreatedOn().format(dtf));
					cfm.setModifiedOn( consignmentMgmt.getModifiedOn().format(dtf));
					cfm.setFileName( consignmentMgmt.getFileName());

					logger.debug(cfm);

					fileRecords.add(cfm);
				}

				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath, fileStorageProperties.getConsignmentDownloadLink() + fileName ); 

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

	private void setInterp(ConsignmentMgmt consignmentMgmt) {
		if(Objects.nonNull(consignmentMgmt.getExpectedArrivalPort()))
			consignmentMgmt.setExpectedArrivalPortInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_PORT, consignmentMgmt.getExpectedArrivalPort()));
		
		if(Objects.nonNull(consignmentMgmt.getCurrency()))
			consignmentMgmt.setCurrencyInterp(interpSetter.setConfigInterp(Tags.CURRENCY, consignmentMgmt.getCurrency()));
		
		if(Objects.nonNull(consignmentMgmt.getTaxPaidStatus()))
			consignmentMgmt.setTaxInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_TAX_STATUS, consignmentMgmt.getTaxPaidStatus()));
	}
}
