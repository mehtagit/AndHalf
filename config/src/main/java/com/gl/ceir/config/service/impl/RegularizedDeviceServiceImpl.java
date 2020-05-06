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
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.EmailSender.MailSubject;
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
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.VisaDb;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.ReferTable;
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
import com.gl.ceir.config.repository.RegularizedDeviceDbRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.transaction.RegularizeDeviceTransaction;
import com.gl.ceir.config.util.CommonFunction;
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
	RegularizeDeviceTransaction regularizeDeviceTransaction;

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
	PropertiesReader propertiesReader;

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

	@Autowired
	UserRepository userRepository;
	@Autowired
	CommonFunction commonFunction;

	private List<RegularizeDeviceDb> getAll(FilterRequest filterRequest){

		List<StateMgmtDb> stateList = null;

		try {
			stateList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());

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

		User user = null;
		List<StateMgmtDb> stateList = null;
		SystemConfigurationDb newYearDateRegisterDevice = systemConfigurationDbRepository.getByTag(ConfigTags.new_year_date_register_device);
		SystemConfigurationDb gracePeriodForRegisterDevice = systemConfigurationDbRepository.getByTag(ConfigTags.grace_period_for_rgister_device);

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
				
				if(Objects.nonNull(regularizeDeviceDb.getEndUserDB())) {
					regularizeDeviceDb.setNationality(regularizeDeviceDb.getEndUserDB().getNationality());
					EndUserDB endUser=regularizeDeviceDb.getEndUserDB();
					endUser.setRegularizeDeviceDbs(new ArrayList<>(1));
					//logger.info("nationality= "+regularizeDeviceDb.getEndUserDB().getNationality());
					regularizeDeviceDb.setEndUserDB(endUser);
				}
				

				setInterp(regularizeDeviceDb);
			}


			// Save in audit.
			String username="";
			 int userId=0;
			if(Objects.nonNull(filterRequest.getUserType()))
			{

				if("End User".equalsIgnoreCase(filterRequest.getUserType())){
					logger.info("usertype is end user so setting username is empty");
					username="";
				}	
				else {

					user = userRepository.getById(filterRequest.getUserId());
					username=user.getUsername();
					userId=filterRequest.getUserId();
				}
			}
        
         
			AuditTrail auditTrail = new AuditTrail(userId, 
					username, 
					Long.valueOf(filterRequest.getUserTypeId()), 
					filterRequest.getUserType(), 
					12, Features.REGISTER_DEVICE, 
					SubFeatures.VIEW, 
					"", "NA");
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
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");

		String filePath = filepath.getValue();
		StatefulBeanToCsvBuilder<RegularizeDeviceFileModel> builder = null;
		StatefulBeanToCsv<RegularizeDeviceFileModel> csvWriter = null;
		List< RegularizeDeviceFileModel > fileRecords = null;

		try {
			List<RegularizeDeviceDb> regularizeDevices = getAll(filterRequest);

			fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_RegularizeDevice.csv";

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
			
			String txnId = null;
			List<WebActionDb> webActionDbs = new ArrayList<>();
			String nid = endUserDB.getNid();

			if(Objects.isNull(endUserDB.getNid())) {
				logger.info(GenericMessageTags.NULL_NID);
				return new GenricResponse(1, GenericMessageTags.NULL_NID.getTag(), 
						GenericMessageTags.NULL_NID.getMessage(), 
						"");
			}

			EndUserDB endUserDB2 = endUserDbRepository.getByNid(nid);
			Integer type=null;
			
			
			logger.info("nationality= "+endUserDB2.getNationality());
			if(Objects.nonNull(endUserDB2)) {
				if("Cambodian".equalsIgnoreCase(endUserDB2.getNationality())) {
					type=1;
				}
				else {
					type=2;
				}	
			}
			
			
			if(!endUserDB.getRegularizeDeviceDbs().isEmpty()) {
				if(validateRegularizedDevicesCount(nid, endUserDB.getRegularizeDeviceDbs(),type)) {
					for(RegularizeDeviceDb regularizeDeviceDb : endUserDB.getRegularizeDeviceDbs()) {
						// TODO     responsse 5
						if(Objects.isNull(endUserDB2)) {
							regularizeDeviceDb.setEndUserDB(endUserDB);
							}
							else {
								regularizeDeviceDb.setEndUserDB(endUserDB2);	
							}
						
					if(commonFunction.hasDuplicateImeiInRequest(endUserDB.getRegularizeDeviceDbs())) {
						return new GenricResponse(6,GenericMessageTags.DUPLICATE_IMEI_IN_REQUEST.getTag(),GenericMessageTags.DUPLICATE_IMEI_IN_REQUEST.getMessage(), ""); 
					}
					
						if(commonFunction.checkAllImeiOfRegularizedDevice(regularizeDeviceDb)) {
							return new GenricResponse(5,GenericMessageTags.DUPLICATE_IMEI.getTag(),GenericMessageTags.DUPLICATE_IMEI.getMessage(), "");
						}
						if(Objects.isNull(regularizeDeviceDb.getTaxPaidStatus())) {
							regularizeDeviceDb.setTaxPaidStatus(TaxStatus.TAX_NOT_PAID.getCode());
						}

						if(Objects.isNull(regularizeDeviceDb.getStatus())) {
							regularizeDeviceDb.setStatus(RegularizeDeviceStatus.New.getCode());
						}

						if(Objects.isNull(regularizeDeviceDb.getTxnId())) {
							regularizeDeviceDb.setTxnId(endUserDB.getTxnId());
							txnId = endUserDB.getTxnId();
						}
						else {
							endUserDB.setTxnId(regularizeDeviceDb.getTxnId());
							txnId = regularizeDeviceDb.getTxnId();
						}

						if(Objects.isNull(endUserDB.getOrigin())) {
							endUserDB.setOrigin(regularizeDeviceDb.getOrigin());
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
						executionSuccess = regularizeDeviceTransaction.executeSaveDevices(webActionDbs, endUserDB);
					}else {
						executionSuccess = regularizeDeviceTransaction.executeSaveDevices(webActionDbs, endUserDB.getRegularizeDeviceDbs());
					}

					// Return message to the client.
					if(executionSuccess) {
						logger.info("End user device registration is sucessful." + endUserDB);
						String mailTag = "END_USER_NEW_DEVICE_ADD";
						List<RawMail> rawMails = new ArrayList<>();
						Map<String, String> placeholderMap = new HashMap<String, String>();

						// Mail to End user. 
						if(Objects.nonNull(endUserDB2)) {
							if(Objects.nonNull(endUserDB2.getEmail()) && !endUserDB2.getEmail().isEmpty()) {
								placeholderMap.put("<First name>", endUserDB2.getFirstName());
								placeholderMap.put("<Txn id>", endUserDB2.getTxnId());
								rawMails.add(new RawMail(mailTag, endUserDB2.getId(), Long.valueOf(12), 
										Features.REGISTER_DEVICE, SubFeatures.REGISTER, endUserDB2.getTxnId(), 
										endUserDB2.getTxnId(), placeholderMap, ReferTable.END_USER, null, "End User"));
								emailUtil.saveNotification(rawMails);	
								
							}
							else {
								logger.info("this end user don't have any email");
							}
						}
						
						// Save in audit.
						AuditTrail auditTrail = new AuditTrail(endUserDB.getCreatorUserId(), endUserDB.getNid(), 
								17L, 
								"End User", 12, Features.REGISTER_DEVICE, 
								SubFeatures.REGISTER, "");
						auditTrailRepository.save(auditTrail);
						logger.info("AUDIT : Saved in audit_trail. " + auditTrail);

						return new GenricResponse(0, "End user device registration is sucessful.", txnId);
					}else {
						logger.info("End user device registration have been failed" + endUserDB);
						return new GenricResponse(2,GenericMessageTags.DEVICE_REGISTRATION_FAILED.getTag(),GenericMessageTags.DEVICE_REGISTRATION_FAILED.getMessage(), "");
					}

				}else {
					logger.warn("Regularized Devices are exceeding the allowed count." + endUserDB);
					return new GenricResponse(3,GenericMessageTags.REGULARISED_DEVICE_EXCEEDED.getTag(),GenericMessageTags.REGULARISED_DEVICE_EXCEEDED.getMessage(), "");
				}

			}else {
				return new GenricResponse(4, "No device found in the request.", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}



	public GenricResponse updateTaxStatus( RegularizeDeviceDb regularizeDeviceDb) {
		try {
			String tag = null;
			String receiverUserType = null;
			String mailSubject = null;
			List<RawMail> rawMails = new ArrayList<>(1);
			Map<String, String> placeholders = new HashMap<>();
			RegularizeDeviceDb userCustomDbDetails = regularizedDeviceDbRepository.getByFirstImei(regularizeDeviceDb.getFirstImei());
			UserProfile ceirAdminProfile = userStaticServiceImpl.getCeirAdmin().getUserProfile();

			if(Objects.nonNull(userCustomDbDetails)) {

				userCustomDbDetails.setTaxPaidStatus(regularizeDeviceDb.getTaxPaidStatus());
				regularizedDeviceDbRepository.save(userCustomDbDetails);

				/*
				 * placeholders.put("<FIRST_NAME>", ceirAdminProfile.getFirstName());
				 * placeholders.put("<txn_id>", regularizeDeviceDb.getTxnId());
				 */
				/*
				 * // Send Notifications if(regularizeDeviceDb.getTaxPaidStatus() == 0) { //
				 * Mail to CEIR Admin on tax update status change. tag =
				 * "MAIL_TO_CEIR_ADMIN_ON_DEVICE_TAX_PAID"; receiverUserType = "CEIRAdmin";
				 * mailSubject =
				 * MailSubject.MAIL_TO_CEIR_ADMIN_ON_DEVICE_TAX_PAID.replace("<XXX>",
				 * userCustomDbDetails.getTxnId()); }else { tag =
				 * "MAIL_TO_CEIR_ADMIN_ON_DEVICE_TAX_NOT_PAID"; receiverUserType = "CEIRAdmin";
				 * mailSubject =
				 * MailSubject.MAIL_TO_CEIR_ADMIN_ON_DEVICE_TAX_NOT_PAID.replace("<XXX>",
				 * userCustomDbDetails.getTxnId()); } rawMails.add(new RawMail(tag,
				 * ceirAdminProfile, 4, Features.REGISTER_DEVICE, SubFeatures.REGISTER,
				 * regularizeDeviceDb.getTxnId(), mailSubject, placeholders, ReferTable.USERS,
				 * null, receiverUserType));
				 * 
				 * 
				 * emailUtil.saveNotification(rawMails);
				 */

				// Save in audit.
				AuditTrail auditTrail = new AuditTrail(0, "", 0L, 
						"", 12, Features.REGISTER_DEVICE, 
						SubFeatures.UPDATE, "");
				auditTrailRepository.save(auditTrail);
				logger.info("AUDIT : update in audit_trail. " + auditTrail);

				return new GenricResponse(0, "Update Successfully.", userCustomDbDetails.getFirstImei());

			}else {
				return  new GenricResponse(4,"TxnId Does Not exist.", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());}
	}

	public RegularizeDeviceDb viewDeviceInfoByImei1(String imei) {
		try {
			logger.info("Going to get deviceInfo Info for imei : " + imei);

			if(Objects.isNull(imei)) {
				throw new IllegalArgumentException();
			}
			RegularizeDeviceDb regularizeDeviceDb=new RegularizeDeviceDb();
			try {
				 regularizeDeviceDb = regularizedDeviceDbRepository.getByFirstImei(imei);				
			}
			catch(Exception e) {
				logger.info(e.toString());
				return null;
			}

			if(Objects.nonNull(regularizeDeviceDb)) {
				EndUserDB endUserDB = endUserDbRepository.getByNid(regularizeDeviceDb.getNid());
				//EndUserDB endUserDB =regularizeDeviceDb.getEndUserDB();
				endUserDB.setRegularizeDeviceDbs(new ArrayList<>(1));
				if(Objects.nonNull(endUserDB.getDocType())) {
					endUserDB.setDocTypeInterp(interpSetter.setTagId(Tags.DOC_TYPE, endUserDB.getDocType()));	
				}
				if(Objects.nonNull(endUserDB.getVisaDb())) {
					List<VisaDb> visaList=new ArrayList<VisaDb>();
					for(VisaDb visa:endUserDB.getVisaDb()) {
						visa.setVisaTypeInterp(interpSetter.setConfigInterp(Tags.VISA_TYPE, visa.getVisaType()));	
						visaList.add(visa);
					}
					endUserDB.setVisaDb(visaList);
				}
				//VISA_TYPE
				regularizeDeviceDb.setEndUserDB(endUserDB);
				
				setInterp(regularizeDeviceDb);
			}
			return regularizeDeviceDb;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}

	}

	@Transactional
	public GenricResponse deleteCustomInfo(String imei) {
		try {
			RegularizeDeviceDb regularizeDeviceDb = regularizedDeviceDbRepository.getByFirstImei(imei);

			if(Objects.nonNull(regularizeDeviceDb)) {

				regularizedDeviceDbRepository.deleteById(regularizeDeviceDb.getId());

				return new GenricResponse(0, "Device have been deleted sucessfully.", regularizeDeviceDb.getFirstImei());
			}else {

				return new GenricResponse(4, "This IMEI does not exist.", "");	
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	@Transactional
	public GenricResponse acceptReject(CeirActionRequest ceirActionRequest) {
		try {
			String tag = null;
			String receiverUserType = null;
			String txnId = null;
			EndUserDB endUserDB = null;
			List<RawMail> rawMails = new ArrayList<>();
			Map<String, String> placeholders = new HashMap<>();
            long userTypeId=0;
            long userId=0;
            String subFeature="";
            String username="";
			RegularizeDeviceDb regularizeDeviceDb =new RegularizeDeviceDb();

			if("CEIRADMIN".equalsIgnoreCase(ceirActionRequest.getUserType())){
				regularizeDeviceDb=regularizedDeviceDbRepository.getByFirstImei(ceirActionRequest.getImei1());
				logger.debug("Accept/Reject regularized Devices : " + regularizeDeviceDb);
	            if(Objects.nonNull(regularizeDeviceDb))
	            {
	            	return new GenricResponse(1, "First imei is incorrect", "");            	
	            }
				endUserDB = endUserDbRepository.getByNid(regularizeDeviceDb.getNid());

				placeholders.put("<Txn id>", regularizeDeviceDb.getTxnId());
				placeholders.put("<First name>", endUserDB.getFirstName());

			userId=ceirActionRequest.getUserId();
				userTypeId=8;
                if(Objects.nonNull(ceirActionRequest.getUsername())) {
                	username=ceirActionRequest.getUsername();
                }
				if(ceirActionRequest.getAction() == 0) {
					regularizeDeviceDb.setStatus(RegularizeDeviceStatus.APPROVED.getCode());
					tag = "MAIL_TO_USER_ON_CEIR_DEVICE_APPROVAL";
					receiverUserType = "End User";
					subFeature=SubFeatures.ACCEPT;
					//feature=
					txnId = regularizeDeviceDb.getTxnId();
				}else if(ceirActionRequest.getAction() == 1){
					regularizeDeviceDb.setStatus(RegularizeDeviceStatus.REJECTED_BY_CEIR_ADMIN.getCode());
					tag = "MAIL_TO_USER_ON_CEIR_DEVICE_DISAPPROVAL";	
					receiverUserType = "End User";
					txnId = regularizeDeviceDb.getTxnId();
					subFeature=SubFeatures.REJECT;
				}else {
					return new GenricResponse(2, "unknown operation", "");
				}
				// Send Notifications
				if(Objects.nonNull(endUserDB)) {
					if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty()) {
						rawMails.add(new RawMail(tag, 
								endUserDB.getId(), 
								4, 
								Features.REGISTER_DEVICE, 
								subFeature, 
								regularizeDeviceDb.getTxnId(), 
								regularizeDeviceDb.getTxnId(), 
								placeholders,
								ReferTable.END_USER,
								null,
								receiverUserType));
						emailUtil.saveNotification(rawMails);	
						
					}
					else {
						logger.info("this end user don't have any email");
					}
				}
			}
			else if("CEIRSYSTEM".equalsIgnoreCase(ceirActionRequest.getUserType())){
				regularizeDeviceDb=regularizedDeviceDbRepository.getByTxnId(ceirActionRequest.getTxnId());
				logger.debug("Accept/Reject regularized Devices : " + regularizeDeviceDb);
	            if(Objects.nonNull(regularizeDeviceDb))
	            {
	            	return new GenricResponse(1, "transaction id is incorrect", "");            	
	            }
				endUserDB = endUserDbRepository.getByNid(regularizeDeviceDb.getNid());

				placeholders.put("<Txn id>", regularizeDeviceDb.getTxnId());
				placeholders.put("<First name>", endUserDB.getFirstName());

				userTypeId=0;
				if(ceirActionRequest.getAction() == 0) {
					regularizeDeviceDb.setStatus(RegularizeDeviceStatus.PENDING_APPROVAL_FROM_CEIR_ADMIN.getCode());
					tag = "MAIL_TO_USER_ON_CEIR_DEVICE_APPROVAL";
					txnId = regularizeDeviceDb.getTxnId();
                    List<User> user= new ArrayList<User>();
                    user=userStaticServiceImpl.getUserbyUsertypeId(8);
					UserProfile ceirUserProfile = new UserProfile();
					ceirUserProfile.setUser(userStaticServiceImpl.getCeirAdmin());
					
					subFeature=SubFeatures.SYSTEM_ACCEPT;
					if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty()) {
						rawMails.add(new RawMail("Reg_Device_Process_success_To_EndUser", 
								endUserDB.getId(), 
								4, 
								Features.REGISTER_DEVICE, 
								SubFeatures.SYSTEM_ACCEPT, 
								regularizeDeviceDb.getTxnId(), 
								regularizeDeviceDb.getTxnId(), 
								placeholders,
								ReferTable.END_USER,
								null,
								"End User"));
					}
for(User userData:user) {
	
	rawMails.add(new RawMail("Reg_Device_Process_success_mail_To_CEIRAdmin", 
			userData.getId(), 
			4, 
			Features.REGISTER_DEVICE, 
			SubFeatures.SYSTEM_ACCEPT, 
			regularizeDeviceDb.getTxnId(), 
			regularizeDeviceDb.getTxnId(), 
			placeholders,
			ReferTable.USERS,
			null,
			"CEIRAdmin"));
					}
					

					emailUtil.saveNotification(rawMails);	
					
				}else if(ceirActionRequest.getAction() == 1){
					regularizeDeviceDb.setStatus(RegularizeDeviceStatus.Rejected_By_System.getCode());
					tag = "MAIL_TO_USER_ON_CEIR_DEVICE_DISAPPROVAL";	
					receiverUserType = "End User";
					txnId = regularizeDeviceDb.getTxnId();
					subFeature=SubFeatures.SYSTEM_REJECT;
					if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty()) {
						if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty()) {
						rawMails.add(new RawMail("Reg_Device_Process_Fail_To_EndUser", 
								endUserDB.getId(), 
								4, 
								Features.REGISTER_DEVICE, 
								SubFeatures.SYSTEM_REJECT, 
								regularizeDeviceDb.getTxnId(), 
								regularizeDeviceDb.getTxnId(), 
								placeholders,
								ReferTable.END_USER,
								null,
								receiverUserType));
						emailUtil.saveNotification(rawMails);	
						}
					}
				}else {
					return new GenricResponse(2, "unknown operation", "");
				}
			}
			else {
				userTypeId=0;
				userId=0;
				subFeature="";
				return new GenricResponse(1, "You are not allowed to do this operation.", "");
			}
			auditTrailRepository.save(new AuditTrail(userId, username, userTypeId,
					ceirActionRequest.getUserType(), 12,Features.REGISTER_DEVICE, subFeature, "", txnId));

			regularizedDeviceDbRepository.save(regularizeDeviceDb);

			
			
			return new GenricResponse(0, "Device Update SuccessFully.", ceirActionRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse getCountOfRegularizedDevicesByNid(String nid,Integer type) {
		try {
			logger.info("nid: "+nid + "type: "+type);
			if(Objects.nonNull(type)) {
				String tag="";
				if(type==1) 
				{
					tag=ConfigTags.max_end_user_device_count;

				}
				else {
					tag=ConfigTags.max_foreigner_end_user_device_count;
				}
				PolicyConfigurationDb policyConfigurationDb = configurationManagementServiceImpl.getPolicyConfigDetailsByTag(tag);

				return new GenricResponse(0, "", "", new Count(Long.parseLong(policyConfigurationDb.getValue()), regularizedDeviceDbRepository.countByNidAndTaxPaidStatus(nid,2)));	

			}
			else {
				return new GenricResponse(1,"Please enter correct type","");	

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	public boolean validateRegularizedDevicesCount(String nid, List<RegularizeDeviceDb> regularizeDeviceDbs,Integer type) {
		try {
			Count count = (Count) getCountOfRegularizedDevicesByNid(nid, type).getData();
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

	private GenericSpecificationBuilder<RegularizeDeviceDb> buildSpecification(FilterRequest filterRequest){
		GenericSpecificationBuilder<RegularizeDeviceDb> specificationBuilder = new GenericSpecificationBuilder<RegularizeDeviceDb>(propertiesReader.dialect);

		if(Objects.nonNull(filterRequest.getNid()) && !filterRequest.getNid().isEmpty())
			specificationBuilder.with(new SearchCriteria("nid", filterRequest.getNid(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
 			specificationBuilder.with(new SearchCriteria("createdO[EmailService.javan", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));
        
			if(Objects.nonNull(filterRequest.getUserTypeId())) {
            if(filterRequest.getUserTypeId()==18)		
            {
     			specificationBuilder.with(new SearchCriteria("origin","Immigration" , SearchOperation.EQUALITY, Datatype.STRING));
            }
            else if(filterRequest.getUserTypeId()==17)		
            {
     			specificationBuilder.with(new SearchCriteria("origin", "Self" , SearchOperation.EQUALITY, Datatype.STRING));
            }

            else {
           	 
            }

		}

		
		if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
			specificationBuilder.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getDeviceIdType()))
			specificationBuilder.with(new SearchCriteria("deviceIdType", filterRequest.getDeviceIdType(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getDeviceType()))
			specificationBuilder.with(new SearchCriteria("deviceType", filterRequest.getDeviceType(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getTaxPaidStatus()))
			specificationBuilder.with(new SearchCriteria("taxPaidStatus", filterRequest.getTaxPaidStatus(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getStatus())) {
			specificationBuilder.with(new SearchCriteria("status", filterRequest.getStatus(), SearchOperation.EQUALITY, Datatype.INT));
		}
		else {
			if(Objects.nonNull(filterRequest.getUserTypeId())) {
				
             if(filterRequest.getUserTypeId()==8)		
             {
     			specificationBuilder.with(new SearchCriteria("status",RegularizeDeviceStatus.PENDING_APPROVAL_FROM_CEIR_ADMIN.getCode(), SearchOperation.EQUALITY, Datatype.INT));
             }
             else {
            	 
             }
			}
			
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

	public void setInterp(RegularizeDeviceDb regularizeDeviceDb) {
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
		
		if(Objects.nonNull(regularizeDeviceDb.getMultiSimStatus()))
			regularizeDeviceDb.setMultiSimStatusInterp(interpSetter.setConfigInterp(Tags.MULTI_SIM_STATUS, regularizeDeviceDb.getDeviceIdType()));

		
	}

}
