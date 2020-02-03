package com.gl.ceir.config.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.transaction.annotation.Transactional;

import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.CeirActionRequest;
import com.gl.ceir.config.model.Count;
import com.gl.ceir.config.model.EndUserDB;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.PolicyConfigurationDb;
import com.gl.ceir.config.model.RawMail;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.model.RegularizeDeviceHistoryDb;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.RegularizeDeviceStatus;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.model.constants.TaxStatus;
import com.gl.ceir.config.model.file.RegularizeDeviceFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.CustomDetailsRepository;
import com.gl.ceir.config.repository.EndUserDbRepository;
import com.gl.ceir.config.repository.RegularizeDeviceHistoryDbRepository;
import com.gl.ceir.config.repository.RegularizedDeviceDbRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.util.DateUtil;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.StatusSetter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class RegularizedDeviceServiceImpl {

	private static final Logger logger = LogManager.getLogger(RegularizedDeviceServiceImpl.class);

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	CustomDetailsRepository customDetailsRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	EndUserDbRepository endUserDbRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	RegularizedDeviceDbRepository regularizedDeviceDbRepository;

	@Autowired
	RegularizeDeviceHistoryDbRepository userCustomHistoryDbRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;

	@Autowired	
	EmailUtil emailUtil;

	@Autowired
	InterpSetter interpSetter;

	@Autowired
	StatusSetter statusSetter;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	DateUtil dateUtil;

	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;

	@Autowired
	UserStaticServiceImpl userStaticServiceImpl;

	private List<RegularizeDeviceDb> getAll(FilterRequest filterRequest){

		List<StateMgmtDb> stateList = null;

		try {
			stateList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			System.out.println("dialect : " + propertiesReader.dialect);

			List<RegularizeDeviceDb> regularizeDeviceDbs = regularizedDeviceDbRepository.findAll(buildSpecification(filterRequest).build());

			for(RegularizeDeviceDb regularizeDeviceDb : regularizeDeviceDbs) {

				for(StateMgmtDb stateMgmtDb : stateList) {
					if(regularizeDeviceDb.getStatus() == stateMgmtDb.getState()) {
						regularizeDeviceDb.setStateInterp(stateMgmtDb.getInterp()); 
						break; 
					} 
				}

				setInterp(regularizeDeviceDb);
			}

			return regularizeDeviceDbs;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Regularized Device Service", e.getMessage());
		}
	}

	public Page<RegularizeDeviceDb> filter(FilterRequest filterRequest, Integer pageNo, Integer pageSize){

		List<StateMgmtDb> stateList = null;
		SystemConfigurationDb newYearDateRegisterDevice = systemConfigurationDbRepository.getByTag(ConfigTags.new_year_date_register_device);
		SystemConfigurationDb gracePeriodDorRegisterDevice = systemConfigurationDbRepository.getByTag(ConfigTags.grace_period_for_rgister_device);

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			if(filterRequest.getTaxPaidStatus() != TaxStatus.BLOCKED.getCode()) {
				// TODO

			}

			stateList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
			logger.info(stateList);
			logger.info("dialect : " + propertiesReader.dialect);

			Page<RegularizeDeviceDb> page = regularizedDeviceDbRepository.findAll(buildSpecification(filterRequest).build(), pageable);

			for(RegularizeDeviceDb regularizeDeviceDb : page.getContent()) {

				for(StateMgmtDb stateMgmtDb : stateList) {
					if(regularizeDeviceDb.getStatus() == stateMgmtDb.getState()) {
						regularizeDeviceDb.setStateInterp(stateMgmtDb.getInterp()); 
						break; 
					} 
				}

				setInterp(regularizeDeviceDb);
			}

			// Save in audit.
			AuditTrail auditTrail = new AuditTrail(filterRequest.getUserId(), "", 0L, 
					"", 12, Features.REGISTER_DEVICE, 
					SubFeatures.VIEW, "");
			auditTrailRepository.save(auditTrail);
			logger.info("AUDIT : View in audit_trail. " + auditTrail);

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Regularized Device Service", e.getMessage());
		}
	}

	public FileDetails getFilteredDeviceInFile(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		RegularizeDeviceFileModel rdfm = null;
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_custom_regularized_dir);
		logger.info("CONFIG : file_custom_regularized_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_custom_regularized_download_link);
		logger.info("CONFIG : file_custom_regularized_download_link [" + link + "]");

		String filePath = filepath.getValue();
		StatefulBeanToCsvBuilder<RegularizeDeviceFileModel> builder = null;
		StatefulBeanToCsv<RegularizeDeviceFileModel> csvWriter = null;
		List< RegularizeDeviceFileModel > fileRecords = null;

		try {
			List<RegularizeDeviceDb> regularizeDevices = getAll(filterRequest);

			fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_RegularizeDevice.csv";

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<RegularizeDeviceFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !regularizeDevices.isEmpty() ) {

				List<SystemConfigListDb> currencyList = configurationManagementServiceImpl.getSystemConfigListByTag(Tags.CURRENCY);

				fileRecords = new ArrayList<>(); 

				for(RegularizeDeviceDb regularizeDeviceDb : regularizeDevices ) {
					rdfm = new RegularizeDeviceFileModel();

					rdfm.setCreatedOn(regularizeDeviceDb.getCreatedOn().format(dtf));
					rdfm.setDeviceIdTypeInterp(regularizeDeviceDb.getDeviceIdTypeInterp());
					rdfm.setDeviceTypeInterp(regularizeDeviceDb.getDeviceTypeInterp());
					rdfm.setPrice( regularizeDeviceDb.getPrice());

					for(SystemConfigListDb systemConfigListDb : currencyList) {
						if(regularizeDeviceDb.getCurrency() == systemConfigListDb.getValue()) {
							rdfm.setCurrency(systemConfigListDb.getInterp()); 
							break;
						} 
					}
					rdfm.setCountry(regularizeDeviceDb.getCountry());
					rdfm.setTaxPaidStatus(regularizeDeviceDb.getTaxPaidStatusInterp());

					logger.debug(rdfm);

					fileRecords.add(rdfm);
				}

				csvWriter.write(fileRecords);
			}

			// Save in audit.
			AuditTrail auditTrail = new AuditTrail(filterRequest.getUserId(), "", 0L, 
					"", 12, Features.REGISTER_DEVICE, 
					SubFeatures.EXPORT, "");
			auditTrailRepository.save(auditTrail);
			logger.info("AUDIT : Export in audit_trail. " + auditTrail);

			return new FileDetails(fileName, filePath, link.getValue() + fileName ); 

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
	public GenricResponse saveDevices(EndUserDB endUserDB) {
		try {
			List<WebActionDb> webActionDbs = new ArrayList<>();
			String nid = endUserDB.getNid();

			if(Objects.isNull(endUserDB.getNid())) {
				logger.info(GenericMessageTags.NULL_NID);
				return new GenricResponse(1, GenericMessageTags.NULL_NID.getTag(), 
						GenericMessageTags.NULL_NID.getMessage(), 
						"");
			}

			EndUserDB endUserDB2 = endUserDbRepository.getByNid(nid);

			if(!endUserDB.getRegularizeDeviceDbs().isEmpty()) {
				if(validateRegularizedDevicesCount(nid, endUserDB.getRegularizeDeviceDbs())) {
					for(RegularizeDeviceDb regularizeDeviceDb : endUserDB.getRegularizeDeviceDbs()) {
						regularizeDeviceDb.setStatus(RegularizeDeviceStatus.PENDING_APPROVAL_FROM_CEIR_ADMIN.getCode());

						if(Objects.isNull(regularizeDeviceDb.getTaxPaidStatus())) {
							regularizeDeviceDb.setTaxPaidStatus(TaxStatus.TAX_NOT_PAID.getCode());
						}

						// Add in web action list.
						webActionDbs.add(new WebActionDb(Features.REGISTER_DEVICE, SubFeatures.REGISTER, 0, 
								regularizeDeviceDb.getTxnId()));
					}

					logger.info(endUserDB.getRegularizeDeviceDbs());

					boolean executionSuccess = Boolean.FALSE;

					// Start query execution.
					if(Objects.isNull(endUserDB2)) {
						// End user is not registered with CEIR system.
						executionSuccess = executeSaveDevices(webActionDbs, endUserDB);
					}else {
						executionSuccess = executeSaveDevices(webActionDbs, endUserDB.getRegularizeDeviceDbs());
					}

					// Return message to the client.
					if(executionSuccess) {
						logger.info("End user device registration is sucessful." + endUserDB);

						// Save in audit.
						AuditTrail auditTrail = new AuditTrail(endUserDB.getCreatorUserId(), "", 0L, 
								"", 12, Features.REGISTER_DEVICE, 
								SubFeatures.REGISTER, "");
						auditTrailRepository.save(auditTrail);
						logger.info("AUDIT : Saved in audit_trail. " + auditTrail);

						return new GenricResponse(0, "End user device registration is sucessful.", "");
					}else {
						logger.info("End user device registration have been failed" + endUserDB);
						return new GenricResponse(2, "End user device registration have been failed.", "");
					}

				}else {
					logger.warn("Regularized Devices are exceeding the allowed count." + endUserDB);
					return new GenricResponse(3, "Regularized Devices are exceeding the allowed count.", "");
				}

			}else {
				return new GenricResponse(4, "No device found in the request.", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	@Transactional
	private boolean executeSaveDevices(List<WebActionDb> webActionDbs, EndUserDB endUserDB) {
		boolean status = Boolean.FALSE;
		webActionDbRepository.saveAll(webActionDbs);
		logger.info("Batch update in web_action_db. " + webActionDbs );

		endUserDbRepository.save(endUserDB);
		logger.info("End user have been saved with its devices. " + endUserDB);

		status = Boolean.TRUE;
		return status;
	}

	@Transactional
	private boolean executeSaveDevices(List<WebActionDb> webActionDbs, List<RegularizeDeviceDb> regularizeDeviceDbs) {
		boolean status = Boolean.FALSE;
		webActionDbRepository.saveAll(webActionDbs);
		logger.info("Batch update in web_action_db. " + webActionDbs );

		regularizedDeviceDbRepository.saveAll(regularizeDeviceDbs);
		logger.info("Regularized devices have been saved. " + regularizeDeviceDbs);

		status = Boolean.TRUE;
		return status;
	}

	public GenricResponse updateTaxStatus( RegularizeDeviceDb regularizeDeviceDb) {
		try {
			String tag = "";
			List<RawMail> rawMails = new ArrayList<>();
			Map<String, String> placeholders = new HashMap<>();
			RegularizeDeviceDb userCustomDbDetails = regularizedDeviceDbRepository.getByFirstImei(regularizeDeviceDb.getFirstImei());

			if(Objects.nonNull(userCustomDbDetails)) {

				userCustomDbDetails.setTaxPaidStatus(regularizeDeviceDb.getTaxPaidStatus());
				regularizedDeviceDbRepository.save(userCustomDbDetails);

				placeholders.put("<device>", Long.toString(regularizeDeviceDb.getFirstImei()));

				// Send Notifications
				if(regularizeDeviceDb.getTaxPaidStatus() == 0) {
					// Mail to CEIR Admin on tax update status change.
					tag = "MAIL_TO_CEIR_ADMIN_ON_DEVICE_TAX_PAID";
				}else {
					tag = "MAIL_TO_CEIR_ADMIN_ON_DEVICE_TAX_NOT_PAID";	
				}
				rawMails.add(new RawMail(tag, 
						userStaticServiceImpl.getCeirAdmin().getUserProfile(), 
						4, 
						Features.REGISTER_DEVICE, 
						SubFeatures.REGISTER, 
						regularizeDeviceDb.getTxnId(), 
						"Subject", 
						placeholders));


				emailUtil.saveNotification(rawMails);
				
				// Save in audit.
				AuditTrail auditTrail = new AuditTrail(0, "", 0L, 
						"", 12, Features.REGISTER_DEVICE, 
						SubFeatures.UPDATE, "");
				auditTrailRepository.save(auditTrail);
				logger.info("AUDIT : update in audit_trail. " + auditTrail);
				
				return new GenricResponse(0, "Update Successfully.", Long.toString(userCustomDbDetails.getFirstImei()));

			}else {
				return  new GenricResponse(4,"TxnId Does Not exist.", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());}
	}

	public RegularizeDeviceDb viewDeviceInfoByImei1(long imei) {
		try {
			logger.info("Going to get deviceInfo Info for imei : " + imei);

			if(Objects.isNull(imei)) {
				throw new IllegalArgumentException();
			}

			RegularizeDeviceDb regularizeDeviceDb = regularizedDeviceDbRepository.getByFirstImei(imei);
			setInterp(regularizeDeviceDb);

			return regularizeDeviceDb;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}

	}

	@Transactional
	public GenricResponse deleteCustomInfo(Long imei) {
		try {
			RegularizeDeviceDb regularizeDeviceDb = regularizedDeviceDbRepository.getByFirstImei(imei);

			if(Objects.nonNull(regularizeDeviceDb)) {

				regularizedDeviceDbRepository.deleteById(regularizeDeviceDb.getId());

				userCustomHistoryDbRepository.save(new RegularizeDeviceHistoryDb(regularizeDeviceDb.getNid(), regularizeDeviceDb.getDeviceStatus(), regularizeDeviceDb.getFirstImei(), 
						regularizeDeviceDb.getSecondImei(), regularizeDeviceDb.getThirdImei(), regularizeDeviceDb.getFourthImei(), regularizeDeviceDb.getTaxPaidStatus(),
						regularizeDeviceDb.getDeviceType(), regularizeDeviceDb.getDeviceIdType(), regularizeDeviceDb.getMultiSimStatus(), regularizeDeviceDb.getCountry(),
						regularizeDeviceDb.getDeviceSerialNumber()));

				return new GenricResponse(0, "Device have been deleted sucessfully.", Long.toString(regularizeDeviceDb.getFirstImei()));
			}else {

				return new GenricResponse(4, "This IMEI does not exist.", Long.toString(regularizeDeviceDb.getFirstImei()));	
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	@Transactional
	public GenricResponse acceptReject(CeirActionRequest ceirActionRequest) {
		try {
			UserProfile userProfile = null;
			RegularizeDeviceDb regularizeDeviceDb = regularizedDeviceDbRepository.getByFirstImei(ceirActionRequest.getImei1());
			logger.debug("Accept/Reject regularized Devices : " + regularizeDeviceDb);

			// Fetch user_profile to update user over mail/sms regarding the action.
			userProfile = userProfileRepository.getByUserId(ceirActionRequest.getUserId());
			logger.debug("userProfile : " + userProfile);

			if("CEIRADMIN".equalsIgnoreCase(ceirActionRequest.getUserType())){

				if(ceirActionRequest.getAction() == 0) {
					regularizeDeviceDb.setStatus(RegularizeDeviceStatus.APPROVED.getCode());

					/*
					emailUtil.sendMessageAndSaveNotification("Consignment_Success_CEIRAuthority_Email_Message", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT,
							SubFeatures.ACCEPT,
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT);
					 */
				}else if(ceirActionRequest.getAction() == 1){
					regularizeDeviceDb.setStatus(RegularizeDeviceStatus.REJECTED_BY_CEIR_ADMIN.getCode());

					/*
					emailUtil.sendMessageAndSaveNotification("Consignment_Approved_CustomImporter_Email_Message", 
							userProfile, 
							consignmentUpdateRequest.getFeatureId(),
							Features.CONSIGNMENT, 
							SubFeatures.ACCEPT,
							consignmentUpdateRequest.getTxnId(),
							MailSubjects.SUBJECT);
					 */
				}
			}else {
				return new GenricResponse(1, "You are not allowed to do this operation.", "");
			}

			regularizedDeviceDbRepository.save(regularizeDeviceDb);
			return new GenricResponse(0, "Device Update SuccessFully.", ceirActionRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse getCountOfRegularizedDevicesByNid(String nid) {
		try {
			PolicyConfigurationDb policyConfigurationDb = configurationManagementServiceImpl.getPolicyConfigDetailsByTag(ConfigTags.max_end_user_device_count);

			return new GenricResponse(0, "", "", new Count(Long.parseLong(policyConfigurationDb.getValue()), regularizedDeviceDbRepository.countByNid(nid)));	
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	private boolean validateRegularizedDevicesCount(String nid, List<RegularizeDeviceDb> regularizeDeviceDbs) {
		try {
			Count count = (Count) getCountOfRegularizedDevicesByNid(nid).getData();
			return validateRegularizedDevicesCount(count, regularizeDeviceDbs);
		}catch (ClassCastException e) {
			return Boolean.FALSE;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}

	private boolean validateRegularizedDevicesCount(Count count, List<RegularizeDeviceDb> regularizeDeviceDbs) {
		try {
			Long regularizedDeviceCount = regularizedDevicesCountByStatus(regularizeDeviceDbs, TaxStatus.REGULARIZED.getCode());
			if(count.getAllowed() >= regularizedDeviceCount + count.getCurrent()) {
				return Boolean.TRUE;
			}else {
				return Boolean.FALSE;
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}

	private Long regularizedDevicesCountByStatus(List<RegularizeDeviceDb> regularizeDeviceDbs, int status) {
		try {
			return regularizeDeviceDbs.stream().filter(o -> o.getTaxPaidStatus() == status).count();

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return -1L;
		}
	}

	private SpecificationBuilder<RegularizeDeviceDb> buildSpecification(FilterRequest filterRequest){
		SpecificationBuilder<RegularizeDeviceDb> specificationBuilder = new SpecificationBuilder<RegularizeDeviceDb>(propertiesReader.dialect);

		if(Objects.nonNull(filterRequest.getNid()) && !filterRequest.getNid().isEmpty())
			specificationBuilder.with(new SearchCriteria("nid", filterRequest.getNid(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
			specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
			specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getDeviceIdType()))
			specificationBuilder.with(new SearchCriteria("deviceIdType", filterRequest.getDeviceIdType(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getDeviceType()))
			specificationBuilder.with(new SearchCriteria("deviceType", filterRequest.getDeviceType(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getTaxPaidStatus()))
			specificationBuilder.with(new SearchCriteria("taxPaidStatus", filterRequest.getTaxPaidStatus(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getConsignmentStatus())) {
			specificationBuilder.with(new SearchCriteria("status", filterRequest.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));
		}

		if(Objects.nonNull(filterRequest.getTxnId()) && !filterRequest.getTxnId().isEmpty()) {
			specificationBuilder.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));
		}

		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			specificationBuilder.orSearch(new SearchCriteria("txnId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			specificationBuilder.orSearch(new SearchCriteria("nid", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return specificationBuilder;
	}

	private void setInterp(RegularizeDeviceDb regularizeDeviceDb) {
		if(Objects.nonNull(regularizeDeviceDb.getTaxPaidStatus()))
			regularizeDeviceDb.setTaxPaidStatusInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_TAX_STATUS, regularizeDeviceDb.getTaxPaidStatus()));

		if(Objects.nonNull(regularizeDeviceDb.getDeviceIdType()))
			regularizeDeviceDb.setDeviceIdTypeInterp(interpSetter.setConfigInterp(Tags.DEVICE_ID_TYPE, regularizeDeviceDb.getDeviceIdType()));

		if(Objects.nonNull(regularizeDeviceDb.getDeviceType()))
			regularizeDeviceDb.setDeviceTypeInterp(interpSetter.setConfigInterp(Tags.DEVICE_TYPE, regularizeDeviceDb.getDeviceType()));

		if(Objects.nonNull(regularizeDeviceDb.getDeviceStatus()))
			regularizeDeviceDb.setDeviceStatusInterp(interpSetter.setConfigInterp(Tags.DEVICE_STATUS, regularizeDeviceDb.getDeviceStatus()));

		if(Objects.nonNull(regularizeDeviceDb.getCurrency()))
			regularizeDeviceDb.setCurrencyInterp(interpSetter.setConfigInterp(Tags.CURRENCY, regularizeDeviceDb.getCurrency(), 0, 1));
	}

}
