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
import com.gl.ceir.config.model.CeirActionRequest;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.EndUserDB;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RawMail;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.UserDepartment;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.model.VisaDb;
import com.gl.ceir.config.model.VisaUpdateDb;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.EndUserStatus;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.ReferTable;
import com.gl.ceir.config.model.constants.RegularizeDeviceStatus;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.model.constants.TaxStatus;
import com.gl.ceir.config.model.file.EndUserFileModel;
import com.gl.ceir.config.model.file.UpdateVisaFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.EndUserDbRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.UpdateVisaRepository;
import com.gl.ceir.config.repository.VipListRepository;
import com.gl.ceir.config.repository.VisaHistoryDBRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.transaction.EndUserTransaction;
import com.gl.ceir.config.util.CommonFunction;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.DateUtil;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class EnduserServiceImpl {

	private static final Logger logger = LogManager.getLogger(EnduserServiceImpl.class);

	@Autowired
	EndUserDbRepository endUserDbRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;

	@Autowired
	VisaHistoryDBRepository visaHistoryDBRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	VipListRepository vipListRepository;

	@Autowired
	EmailUtil emailUtil;

	@Autowired
	EndUserTransaction endUserTransaction;

	@Autowired
	CommonFunction commonFunction;

	@Autowired
	RegularizedDeviceServiceImpl regularizedService;

	@Autowired
	UpdateVisaRepository visaUpdateRepo;

	@Autowired
	UserStaticServiceImpl userStaticServiceImpl;

	@Autowired
	UpdateVisaRepository updateVisaRepo;

	@Autowired
	UpdateVisaRepository updateVisaRepository;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;

	@Autowired
	InterpSetter interpSetter;

	@Autowired
	Utility utility;


	public GenricResponse endUserByNid(String nid) {
		try {
			EndUserDB endUserDB = endUserDbRepository.getByNid(nid);

			// End user is not registered with CEIR system.
			if(Objects.nonNull(endUserDB)) {
				List<RegularizeDeviceDb> regulaizedList=new ArrayList<RegularizeDeviceDb>();
				if(Objects.nonNull(endUserDB.getRegularizeDeviceDbs()))
					for(RegularizeDeviceDb regularizeData:endUserDB.getRegularizeDeviceDbs()) {
						regularizeData.setEndUserDB(new EndUserDB());
						regulaizedList.add(regularizeData);
					}
				endUserDB.setRegularizeDeviceDbs(regulaizedList);
				logger.info("End User with nid [" + nid + "] does exist.");
				return new GenricResponse(1, "End User does exist.", nid, endUserDB);
			}else {
				logger.info("End User with nid [" + nid + "] does not exist.");
				return new GenricResponse(0, "User does not exist.", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	@Transactional
	public GenricResponse saveEndUser(EndUserDB endUserDB) {
		try {
			List<WebActionDb> webActionDbs = new ArrayList<>();

			// End user is not registered with CEIR system.
			if(Objects.isNull(endUserDB)) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), "");
			}

			// If user is already registerd.
			EndUserDB endUserDB2 = endUserDbRepository.getByNid(endUserDB.getNid());
			if(Objects.nonNull(endUserDB2)) {
				return new GenricResponse(3, GenericMessageTags.USER_ALREADY_EXIST.getTag(), 
						GenericMessageTags.USER_ALREADY_EXIST.getMessage(), "");
			}


			// Add department if user is VIP.
			if("Y".equals(endUserDB.getIsVip())) {
				UserDepartment userDepartment = endUserDB.getUserDepartment();
				UserDepartment newUserDepartment = new UserDepartment(userDepartment.getName(), userDepartment.getDepartmentId(), 
						userDepartment.getDepartmentFilename());
				newUserDepartment.setEndUserDB(endUserDB);

				if(Objects.isNull(userDepartment)) {
					return new GenricResponse(2, GenericMessageTags.NULL_USER_DEPARTMENT.getTag(), 
							GenericMessageTags.NULL_USER_DEPARTMENT.getMessage(), "");
				}else {
					endUserDB.setUserDepartment(newUserDepartment);	
				}
			}

			Integer type=null;


			logger.info("nationality= "+endUserDB.getNationality());
			if(Objects.nonNull(endUserDB)) {
				if("Cambodian".equalsIgnoreCase(endUserDB.getNationality())) {
					type=1;
				}
				else {
					type=2;
				}	
			}

			// Validate and set visa expiry date as per default rule.
			GenricResponse response = setVisaExpiryDate(endUserDB);
			if(response.getErrorCode() != 0) 
				return response;

			// Validate end user devices.
			if(!endUserDB.getRegularizeDeviceDbs().isEmpty()){

				logger.info("nationality= "+endUserDB.getNationality());
				if(Objects.nonNull(endUserDB)) {
					if("Cambodian".equalsIgnoreCase(endUserDB.getNationality())) {
						type=1;
					}
					else {
						type=2;
					}	
				}
				if(regularizedService.validateRegularizedDevicesCount(endUserDB.getNid(), endUserDB.getRegularizeDeviceDbs(),type)) {
					if(commonFunction.hasDuplicateImeiInRequest(endUserDB.getRegularizeDeviceDbs())) {
						return new GenricResponse(6,GenericMessageTags.DUPLICATE_IMEI_IN_REQUEST.getTag(),GenericMessageTags.DUPLICATE_IMEI_IN_REQUEST.getMessage(), ""); 
					}
					for(RegularizeDeviceDb regularizeDeviceDb : endUserDB.getRegularizeDeviceDbs()) {

						regularizeDeviceDb.setEndUserDB(endUserDB);
						//TO DO
						if(commonFunction.checkAllImeiOfRegularizedDevice(regularizeDeviceDb)) {
							return new GenricResponse(5,GenericMessageTags.DUPLICATE_IMEI.getTag(),GenericMessageTags.DUPLICATE_IMEI.getMessage(),"");
						}

						if(Objects.isNull(regularizeDeviceDb.getTaxPaidStatus())) {
							regularizeDeviceDb.setTaxPaidStatus(TaxStatus.TAX_NOT_PAID.getCode());
						}

						if(Objects.isNull(regularizeDeviceDb.getStatus())) {
							regularizeDeviceDb.setStatus(RegularizeDeviceStatus.New.getCode());
						}

						if(Objects.isNull(endUserDB.getOrigin())) {
							endUserDB.setOrigin(regularizeDeviceDb.getOrigin());
						}

						// Add in web action list.
						webActionDbs.add(new WebActionDb(Features.REGISTER_DEVICE, SubFeatures.REGISTER, 0, 
								regularizeDeviceDb.getTxnId()));
					}

					logger.info("regularize devices: "+endUserDB.getRegularizeDeviceDbs());
				}else {
					logger.warn("Regularized Devices are exceeding the allowed count." + endUserDB);
					return new GenricResponse(3,GenericMessageTags.REGULARISED_DEVICE_EXCEEDED.getTag(),GenericMessageTags.REGULARISED_DEVICE_EXCEEDED.getMessage(), "");
				}
			}

			endUserDB = endUserDbRepository.save(endUserDB);
			if(Objects.nonNull(endUserDB)){

				if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty() ) {
					List<RawMail> rawMails = new ArrayList<>();
					String mailTag = "END_USER_REGISTER";
					Map<String, String> placeholderMap = new HashMap<String, String>();
					placeholderMap.put("<First name>", endUserDB.getFirstName());
					placeholderMap.put("<Txn id>", endUserDB.getTxnId());
					// Mail to End user.
					rawMails.add(new RawMail(mailTag, endUserDB.getId(), Long.valueOf(12), 
							Features.REGISTER_DEVICE, SubFeatures.REGISTER, endUserDB.getTxnId(), 
							endUserDB.getTxnId(), placeholderMap, ReferTable.END_USER, null, "End User"));

					emailUtil.saveNotification(rawMails);

				}
				else {
					logger.info("this end user don't have any email");
				}

			}
			logger.info(GenericMessageTags.USER_REGISTER_SUCCESS.getMessage() + " with nid [" + endUserDB.getNid() + "]");

			webActionDbRepository.saveAll(webActionDbs);
			logger.info("Batch update in web_action_db. " + webActionDbs );

			auditTrailRepository.save(new AuditTrail(endUserDB.getId(), "", 17L,
					"End User", 0L,Features.REGISTER_DEVICE, SubFeatures.REGISTER, "", endUserDB.getTxnId()));
			logger.info("AUDIT : Saved request in audit.");

			return new GenricResponse(0, GenericMessageTags.USER_REGISTER_SUCCESS.getTag(),GenericMessageTags.USER_REGISTER_SUCCESS.getMessage(), endUserDB.getTxnId());

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public GenricResponse updateEndUser(EndUserDB endUserDBNew) {
		try {
			if(Objects.isNull(endUserDBNew)) {
				logger.info("Request can't be null.");
				return new GenricResponse(2, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}
			String nid = endUserDBNew.getNid();
			if(Objects.isNull(endUserDBNew)) {
				logger.info("Request have nid as null.");
				return new GenricResponse(3, GenericMessageTags.NULL_NID.getTag(), GenericMessageTags.NULL_NID.getMessage(), null);
			}

			EndUserDB endUserDBOld = endUserDbRepository.getByNid(nid);
			endUserDBNew.setId(endUserDBOld.getId());

			// Visa Old
			if(!endUserDBOld.getVisaDb().isEmpty()) {
				VisaDb visaDbOld = endUserDBOld.getVisaDb().get(0);

				// Visa New
				VisaDb visaDbNew = endUserDBNew.getVisaDb().get(0);
				visaDbNew.setId(visaDbOld.getId());
				visaDbNew.setEndUserDB(endUserDBNew);

				ArrayList<VisaDb> visaDbListNew = new ArrayList<>();
				visaDbListNew.add(visaDbNew);

				// Set New objects to new end user db.
				endUserDBNew.setVisaDb(visaDbListNew);
			}

			// User department - Old
			UserDepartment userDepartmentOld = endUserDBOld.getUserDepartment();

			if(Objects.nonNull(userDepartmentOld)) {
				// User department - New			
				UserDepartment userDepartmentNew = endUserDBNew.getUserDepartment();
				userDepartmentNew.setId(userDepartmentOld.getId());
				userDepartmentNew.setEndUserDB(endUserDBNew);

				endUserDBNew.setUserDepartment(userDepartmentNew);
			}

			logger.info(GenericMessageTags.USER_UPDATE_SUCCESS.getMessage() + "of NID [" + nid +"]");

			endUserDbRepository.save(endUserDBNew);

			auditTrailRepository.save(new AuditTrail(endUserDBOld.getId(), "", 17L,
					"End User", 0L,Features.REGISTER_DEVICE, SubFeatures.UPDATE, ""));
			logger.info("AUDIT : Saved update request in audit.");

			return new GenricResponse(1, GenericMessageTags.USER_UPDATE_SUCCESS.getTag(), GenericMessageTags.USER_UPDATE_SUCCESS.getMessage(), nid);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}

	public GenricResponse updateVisaEndUser(EndUserDB endUserDB) {
		try {
			VisaDb latestVisa = null;
			// Check if request is null
			if(Objects.isNull(endUserDB.getNid())) {
				logger.info("Request should not have nid as null.");
				return new GenricResponse(2, GenericMessageTags.NULL_NID.getTag(), 
						GenericMessageTags.NULL_NID.getMessage(), null);
			}
			// Check if requested VISA is null or empty.
			if(Objects.isNull(endUserDB.getVisaDb())) {
				logger.info("Request visa update should not be null.");
				return new GenricResponse(3, GenericMessageTags.NULL_VISA.getTag(), 
						GenericMessageTags.NULL_VISA.getMessage(), null);
			}else if(endUserDB.getVisaDb().isEmpty()){
				logger.info("Request visa update should not be empty.");
				return new GenricResponse(4, GenericMessageTags.EMPTY_VISA.getTag(), 
						GenericMessageTags.EMPTY_VISA.getMessage(), null);
			}else {
				latestVisa = endUserDB.getVisaDb().get(0);
			}

			EndUserDB endUserDB1 = endUserDbRepository.getByNid(endUserDB.getNid());

			// End user is not registered with CEIR system.
			if(Objects.isNull(endUserDB1)) {
				return new GenricResponse(5, GenericMessageTags.INVALID_USER.getTag(), 
						GenericMessageTags.INVALID_USER.getMessage(), 
						endUserDB.getNid());

			}else {
				logger.info("Going to update VISA of user. " + endUserDB1);

				List<VisaDb> visaDbs = endUserDB1.getVisaDb();

				if(visaDbs.isEmpty()) {
					logger.info("You are not allowed to update Visa." + endUserDB.getNid());
					return new GenricResponse(6, GenericMessageTags.VISA_UPDATE_NOT_ALLOWED.getTag(), 
							GenericMessageTags.VISA_UPDATE_NOT_ALLOWED.getMessage(), endUserDB.getNid());
				}else {
					//					 Update expiry date of latest Visa
					//					VisaDb visaDb = visaDbs.get(visaDbs.size() - 1);
					//					visaDb.setVisaExpiryDate(latestVisa.getVisaExpiryDate());	

					VisaDb OldVisa=visaDbs.get(0);
					VisaUpdateDb visaUpdateDb=new VisaUpdateDb(OldVisa.getVisaType(), OldVisa.getVisaNumber(),
							latestVisa.getVisaFileName(), OldVisa.getEntryDateInCountry(), latestVisa.getVisaExpiryDate(),
							0,endUserDB1.getId(),endUserDB.getTxnId(),endUserDB.getNid()); 
					String mailTag = "Update_Visa_Request";
					List<RawMail> rawMails = new ArrayList<>();
					Map<String, String> placeholderMap = new HashMap<String, String>();
					placeholderMap.put("<First name>", endUserDB1.getFirstName());
					placeholderMap.put("<txn_id>", endUserDB1.getTxnId());
					rawMails.add(new RawMail(mailTag, endUserDB1.getId(), Long.valueOf(12), 
							Features.UPDATE_VISA, SubFeatures.REQUEST, endUserDB1.getTxnId(), 
							endUserDB1.getTxnId(), placeholderMap, ReferTable.END_USER, null, "End User"));
					emailUtil.saveNotification(rawMails);
					VisaUpdateDb visaDb=updateVisaRepository.findByUserId(endUserDB1.getId());

		
					if(visaDb!=null) { 
						visaUpdateDb.setId(visaDb.getId());
						visaUpdateDb.setCreatedOn(visaDb.getCreatedOn());

					}
					else {

					}
					WebActionDb webAction=new	 WebActionDb(Features.UPDATE_VISA, SubFeatures.REQUEST, 0, 
							endUserDB.getTxnId());

					if(endUserTransaction.addUpdateVisaRequest(visaUpdateDb, endUserDB1,webAction)) {
						return new GenricResponse(0, GenericMessageTags.VISA_UPDATE_REQUEST_SUCCESS.getTag(), 
								GenericMessageTags.VISA_UPDATE_REQUEST_SUCCESS.getMessage(), endUserDB.getNid());

					}else {
						return new GenricResponse(1, GenericMessageTags.VISA_UPDATE_REQUEST_FAIL.getTag(), 
								GenericMessageTags.VISA_UPDATE_REQUEST_FAIL.getMessage(), endUserDB.getNid());
					}
				}


			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("End user Service", e.getMessage());
		}
	}

	public Page<EndUserDB> filter(FilterRequest filterRequest, Integer pageNo, 
			Integer pageSize) {

		List<StateMgmtDb> statusList = null;

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			Page<EndUserDB> page = endUserDbRepository.findAll(buildSpecification(filterRequest, statusList).build(), pageable);

			for(EndUserDB endUserDB : page.getContent()) {
				setInterp(endUserDB);
				if(Objects.nonNull(endUserDB.getRegularizeDeviceDbs())) {
					List<RegularizeDeviceDb> regulaizedList=new ArrayList<RegularizeDeviceDb>();
					if(Objects.nonNull(endUserDB.getRegularizeDeviceDbs()))
						for(RegularizeDeviceDb regularizeData:endUserDB.getRegularizeDeviceDbs()) {
							regularizeData.setEndUserDB(new EndUserDB());
							regulaizedList.add(regularizeData);
						}
					endUserDB.setRegularizeDeviceDbs(regulaizedList);
				}
			}

			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), "", 
					Long.valueOf(filterRequest.getUserTypeId()), filterRequest.getUserType(), 
					Long.valueOf(filterRequest.getFeatureId()),
					Features.MANAGE_USER, SubFeatures.VIEW, ""));
			logger.info("AUDIT : Saved view request in audit.");
			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private List<EndUserDB> getAll(FilterRequest filterRequest){
		try {

			List<EndUserDB> endUserDBs = endUserDbRepository.findAll(buildSpecification(filterRequest, null).build(), new Sort(Sort.Direction.DESC, "modifiedOn"));
			logger.info("endUserDBs " + endUserDBs);

			for(EndUserDB endUserDB : endUserDBs) {
				setInterp(endUserDB);
			}

			logger.info("endUserDBs : " + endUserDBs);
			return endUserDBs;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public FileDetails getFilteredEndUserInFileV2(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		//String[] columns = new String[]{"grievanceId","userId","userType","grievanceStatus","txnId","categoryId","fileName","createdOn","modifiedOn","remarks"};
		EndUserFileModel fm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");

		StatefulBeanToCsvBuilder<EndUserFileModel> builder = null;
		StatefulBeanToCsv<EndUserFileModel> csvWriter      = null;
		List< EndUserFileModel> fileRecords                = null;
		CustomMappingStrategy<EndUserFileModel> mappingStrategy = new CustomMappingStrategy<>();

		try {
			List<EndUserDB> endUserDBs = getAll(filterRequest);

			fileName = LocalDateTime.now().format(dtf2).replace(" ", "_")+"_User.csv";
			writer = Files.newBufferedWriter(Paths.get(filepath.getValue() + fileName));
			mappingStrategy.setType(EndUserFileModel.class);

			builder = new StatefulBeanToCsvBuilder<EndUserFileModel>(writer);
			csvWriter = builder.withMappingStrategy(mappingStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( endUserDBs.size() > 0 ) {
				fileRecords = new ArrayList<>();

				for( EndUserDB endUserDB : endUserDBs ) { 
					String visaExpiryDate = "";
					if(Objects.nonNull(endUserDB.getVisaDb())) {
						if(!endUserDB.getVisaDb().isEmpty()) {
							VisaDb visaDb = endUserDB.getVisaDb().get(endUserDB.getVisaDb().size()-1);
							visaExpiryDate = visaDb.getVisaExpiryDate();
						}
					}

					fm = new EndUserFileModel(endUserDB.getTxnId(), 
							endUserDB.getCreatedOn().toString(), 
							endUserDB.getNid(), 
							endUserDB.getFirstName() +" "+ endUserDB.getLastName(), 
							endUserDB.getNationality(), 
							visaExpiryDate, 
							endUserDB.getPhoneNo());

					/*consignmentMgmt.getCreatedOn().format(dtf),
							consignmentMgmt.getModifiedOn().format(dtf)*/

					fileRecords.add(fm); 
				}


				csvWriter.write(fileRecords);
			}else {
				csvWriter.write( new EndUserFileModel());
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

	private SpecificationBuilder<EndUserDB> buildSpecification(FilterRequest filterRequest, List<StateMgmtDb> statusList){
		SpecificationBuilder<EndUserDB> specificationBuilder = new SpecificationBuilder<>(propertiesReader.dialect);


		if(Objects.nonNull(filterRequest.getNid()))
			specificationBuilder.with(new SearchCriteria("nid", filterRequest.getNid(), SearchOperation.EQUALITY, Datatype.STRING));

		if("IMMIGRATION".equalsIgnoreCase(filterRequest.getOrigin())) {
			List<String> notInList = new ArrayList<>();
			notInList.add("Cambodian");

			specificationBuilder.notIn("nationality", notInList);
		}

		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			specificationBuilder.orSearch(new SearchCriteria("nid", filterRequest.getNid(), SearchOperation.LIKE, Datatype.STRING));
		}

		return specificationBuilder;
	}

	private void setInterp(EndUserDB endUserDB) {

	}

	private GenricResponse setVisaExpiryDate(EndUserDB endUserDB) {
		if("Y".equalsIgnoreCase(endUserDB.getOnVisa())) {
			if(Objects.isNull(endUserDB.getVisaDb())) {
				return new GenricResponse(11, GenericMessageTags.NULL_VISA.getTag(), GenericMessageTags.NULL_VISA.getMessage(), "");
			}else {
				List<VisaDb> visaDbs = endUserDB.getVisaDb();
				if(visaDbs.isEmpty()) {
					return new GenricResponse(12, GenericMessageTags.VISA_EMPTY.getTag(), GenericMessageTags.VISA_EMPTY.getMessage(), "");
				}else if(Objects.isNull(visaDbs.get(0).getVisaExpiryDate()) || visaDbs.get(0).getVisaExpiryDate().isEmpty()) {
					SystemConfigurationDb defaultVisaExpirationDays = systemConfigurationDbRepository.getByTag(ConfigTags.default_visa_expiration_days);
					int day = 0;
					try {
						day = Integer.parseInt(defaultVisaExpirationDays.getValue());
					}catch (NumberFormatException e) {
						logger.info("Config value 'default_visa_expiration_days' [" + defaultVisaExpirationDays.getValue() + "] must be cast to numeric value.");
						return new GenricResponse(13, GenericMessageTags.DISCREPENCY_IN_CONFIG.getTag(), 
								GenericMessageTags.DISCREPENCY_IN_CONFIG.getMessage(), "default_visa_expiration_days must be cast to numeric value");
					}
					String date = DateUtil.nextDate(day, null);
					visaDbs.get(0).setVisaExpiryDate(date);
					visaDbs.get(0).setEndUserDB(endUserDB);
					return new GenricResponse(0);
				}else {
					visaDbs.get(0).setEndUserDB(endUserDB);
					return new GenricResponse(0);
				}
			}
		}else {
			return new GenricResponse(0);
		}

	}

	public GenricResponse acceptReject(ConsignmentUpdateRequest updateRequest) {
		try {
			UserProfile userProfile = null;
			String nid = updateRequest.getNid();
			Map<String, String> placeholderMap = new HashMap<String, String>();
			EndUserDB endUserDB = endUserDbRepository.getByNid(nid);

			logger.info(endUserDB);

			if(Objects.isNull(endUserDB)) {
				logger.info(GenericMessageTags.INVALID_USER.getMessage() + " of NID [" +nid+"]");
				return new GenricResponse(4, GenericMessageTags.INVALID_USER.getTag(), 
						GenericMessageTags.INVALID_USER.getMessage(), updateRequest.getTxnId());
			}

			// Build placeholders map to replace placeholders from mail.
			placeholderMap.put("<First name>", endUserDB.getFirstName());

			// 0 - Accept, 1 - Reject
			if("CEIRADMIN".equalsIgnoreCase(updateRequest.getUserType())){
				String mailTag = null;
				String action = null;
				String receiverUserType = null;

				// If end user state is not pending approval on ceir admin, reject the request.
				if(endUserDB.getStatus() != EndUserStatus.PENDING_APPROVAL_ON_CEIR_ADMIN.getCode()) {
					logger.info(GenericMessageTags.INVALID_STATE_TRANSTION.getMessage() + " for user " + endUserDB);
					return new GenricResponse(5, GenericMessageTags.INVALID_STATE_TRANSTION.getTag(), 
							GenericMessageTags.INVALID_STATE_TRANSTION.getMessage(), "");
				}

				if(updateRequest.getAction() == 0) {
					action = SubFeatures.ACCEPT;
					mailTag = "END_USER_APPROVED_BY_CEIR_ADMIN"; 
					receiverUserType = "End User";
					endUserDB.setStatus(EndUserStatus.APPROVED.getCode());

					// if user is VIP, add imei's of user in vip_list table.
					updateImeiInVipList(endUserDB);

				}else {
					action = SubFeatures.REJECT;
					receiverUserType = "End User";
					mailTag = "END_USER_REJECT_BY_CEIR_ADMIN";

					endUserDB.setStatus(EndUserStatus.REJECTED_BY_CEIR_ADMIN.getCode());
					endUserDB.setRemarks(updateRequest.getRemarks());
				}

				// Update Stock and its history.
				if(!endUserTransaction.updateStatusWithHistory(endUserDB)){
					logger.warn("Unable to update End userdb.");
					return new GenricResponse(3, "Unable to update End Userdb.", updateRequest.getTxnId()); 
				}else {
					List<RawMail> rawMails = new ArrayList<>();
					mailTag="END_USER_REGISTER";
					// Mail to End user. 
					rawMails.add(new RawMail(mailTag, endUserDB.getId(), Long.valueOf(updateRequest.getFeatureId()), 
							Features.MANAGE_USER, SubFeatures.ACCEPT_REJECT, updateRequest.getTxnId(), 
							"SUBJECT", placeholderMap, ReferTable.END_USER, null, "END USER"));
					emailUtil.saveNotification(rawMails);
				}

			}else {
				logger.warn("Accept/reject of Stock not allowed to you.");
				new GenricResponse(1, "Operation not Allowed", updateRequest.getTxnId());
			}

			return new GenricResponse(0, "Consignment Update SuccessFully.", updateRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private void updateImeiInVipList(EndUserDB endUserDB) {
		if("Y".equals(endUserDB.getIsVip())) {
			if(endUserDB.getRegularizeDeviceDbs().isEmpty()) {
				logger.info("End User is VIP but no device is registered for him/her with NID/Passport. ["+endUserDB.getNid()+"]");
			}else {
				RegularizeDeviceDb regularizeDeviceDb = endUserDB.getRegularizeDeviceDbs().get(0);

				List<VipList> vipsImeiList = new ArrayList<>(4);
				if(Objects.nonNull(regularizeDeviceDb.getFirstImei())) 
					vipsImeiList.add(new VipList(regularizeDeviceDb.getFirstImei(), Long.parseLong(endUserDB.getPhoneNo())));

				if(Objects.nonNull(regularizeDeviceDb.getSecondImei()))
					vipsImeiList.add(new VipList(regularizeDeviceDb.getSecondImei(), Long.parseLong(endUserDB.getPhoneNo())));

				if(Objects.nonNull(regularizeDeviceDb.getThirdImei()))
					vipsImeiList.add(new VipList(regularizeDeviceDb.getThirdImei(), Long.parseLong(endUserDB.getPhoneNo())));

				if(Objects.nonNull(regularizeDeviceDb.getFourthImei()))
					vipsImeiList.add(new VipList(regularizeDeviceDb.getFourthImei(), Long.parseLong(endUserDB.getPhoneNo())));

				vipListRepository.saveAll(vipsImeiList);
			}
		}else {
			// user is not VIP, so nothing to do with table vip_list table.
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
			String username="";
			VisaDb latestVisa = null;            
			VisaUpdateDb visaDb =new VisaUpdateDb();
			String sufeature="";
			if("CEIRADMIN".equalsIgnoreCase(ceirActionRequest.getUserType())){
				visaDb=visaUpdateRepo.getById(ceirActionRequest.getId());
				if(Objects.isNull(visaDb)) {
					return new GenricResponse(1, "Visa Db is incorrect", "");				
				}
				endUserDB = endUserDbRepository.getById(visaDb.getUserId());

				if(Objects.isNull(endUserDB.getVisaDb())) {
					logger.info("Request visa update should not be null.");
					return new GenricResponse(3, GenericMessageTags.NULL_VISA.getTag(), 
							GenericMessageTags.NULL_VISA.getMessage(), null);
				}else if(endUserDB.getVisaDb().isEmpty()){
					logger.info("Request visa update should not be empty.");
					return new GenricResponse(4, GenericMessageTags.EMPTY_VISA.getTag(), 
							GenericMessageTags.EMPTY_VISA.getMessage(), null);
				}else {
					latestVisa = endUserDB.getVisaDb().get(0);
				}
				placeholders.put("<Txn id>", endUserDB.getTxnId());
				placeholders.put("<First name>", endUserDB.getFirstName());

				userTypeId=8;
				if(Objects.nonNull(ceirActionRequest.getUsername())) {
					username=ceirActionRequest.getUsername();		
				}

				if(ceirActionRequest.getAction() == 0) {
					visaDb.setStatus(RegularizeDeviceStatus.APPROVED.getCode());
					List<VisaDb> visaDbs = endUserDB.getVisaDb();
					//					 Update expiry date of latest Visa
					VisaDb visaDbUpdate = visaDbs.get(visaDbs.size() - 1);
					visaDbUpdate.setVisaExpiryDate(visaDb.getVisaExpiryDate());	
					visaDbUpdate.setVisaFileName(visaDb.getVisaFileName());
					if(visaDbs.isEmpty()) {
						logger.info("You are not allowed to update Visa." + endUserDB.getNid());
						return new GenricResponse(6, GenericMessageTags.VISA_UPDATE_NOT_ALLOWED.getTag(), 
								GenericMessageTags.VISA_UPDATE_NOT_ALLOWED.getMessage(), endUserDB.getNid());
					}else {}
					if(!endUserTransaction.executeUpdateVisa(endUserDB)) {
						return new GenricResponse(1, GenericMessageTags.VISA_UPDATE_FAIL.getTag(), 
								GenericMessageTags.VISA_UPDATE_FAIL.getMessage(), endUserDB.getNid());
					}
					tag = "Update_Visa_Approved_CEIRAdmin";
					receiverUserType = "End User";
					sufeature=SubFeatures.ACCEPT;
					//feature= 
					txnId = endUserDB.getTxnId();
					userId=ceirActionRequest.getUserId();
				}else if(ceirActionRequest.getAction() == 1){
					visaDb.setStatus(RegularizeDeviceStatus.REJECTED_BY_CEIR_ADMIN.getCode());
					visaDb.setRemark(ceirActionRequest.getRemarks()); 
					tag = "Update_Visa_Reject_CEIRAdmin";	
					receiverUserType = "End User";
					txnId = endUserDB.getTxnId();
					sufeature=SubFeatures.REJECT;
				}else {
					return new GenricResponse(2, "unknown operation", "");
				}
				// Send Notifications
				if(Objects.nonNull(endUserDB)) {
					if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty()) {
						rawMails.add(new RawMail(tag, 
								endUserDB.getId(), 
								4, 
								Features.UPDATE_VISA, 
								sufeature, 
								endUserDB.getTxnId(), 
								endUserDB.getTxnId(), 
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
				visaDb=visaUpdateRepo.getByTxnId(ceirActionRequest.getTxnId());
				if(Objects.isNull(visaDb)) {
					return new GenricResponse(1, "transaction id is incorrect", "");				
				}
				endUserDB = endUserDbRepository.getById(visaDb.getUserId());

				if(Objects.isNull(endUserDB.getVisaDb())) {
					logger.info("Request visa update should not be null.");
					return new GenricResponse(3, GenericMessageTags.NULL_VISA.getTag(), 
							GenericMessageTags.NULL_VISA.getMessage(), null);
				}else if(endUserDB.getVisaDb().isEmpty()){
					logger.info("Request visa update should not be empty.");
					return new GenricResponse(4, GenericMessageTags.EMPTY_VISA.getTag(), 
							GenericMessageTags.EMPTY_VISA.getMessage(), null);
				}else {
					latestVisa = endUserDB.getVisaDb().get(0);
				}
				placeholders.put("<Txn id>", endUserDB.getTxnId());
				placeholders.put("<First name>", endUserDB.getFirstName());

				userTypeId=0;
				if(ceirActionRequest.getAction() == 0) {
					visaDb.setStatus(RegularizeDeviceStatus.PENDING_APPROVAL_FROM_CEIR_ADMIN.getCode());
					tag = "MAIL_TO_USER_ON_CEIR_DEVICE_APPROVAL";
					txnId = endUserDB.getTxnId();
					List<User> user= new ArrayList<User>();
					user=userStaticServiceImpl.getUserbyUsertypeId(8);
					UserProfile ceirUserProfile = new UserProfile();
					ceirUserProfile.setUser(userStaticServiceImpl.getCeirAdmin());
					sufeature="SYSTEM_ACCEPT";
					if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty()) {
						if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty()) {
							rawMails.add(new RawMail("Update_Visa_Approved_System", 
									endUserDB.getId(), 
									4, 
									Features.UPDATE_VISA, 
									SubFeatures.SYSTEM_ACCEPT, 
									endUserDB.getTxnId(), 
									endUserDB.getTxnId(), 
									placeholders,
									ReferTable.END_USER,
									null,
									"End User"));
						}
					}	
					for(User userData:user) {

						rawMails.add(new RawMail("Update_Visa_Request_CEIRAdmin", 
								userData.getId(), 
								4, 
								Features.UPDATE_VISA, 
								SubFeatures.SYSTEM_ACCEPT, 
								endUserDB.getTxnId(), 
								endUserDB.getTxnId(), 
								placeholders,
								ReferTable.USERS,
								null,
								"CEIRAdmin"));
					}


					emailUtil.saveNotification(rawMails);	
					txnId = endUserDB.getTxnId();

				}else if(ceirActionRequest.getAction() == 1){

					sufeature="SYSTEM_REJECT";
					visaDb.setStatus(RegularizeDeviceStatus.Rejected_By_System.getCode());
					tag = "MAIL_TO_USER_ON_CEIR_DEVICE_DISAPPROVAL";	
					receiverUserType = "End User";
					txnId = endUserDB.getTxnId();
					if(Objects.nonNull(endUserDB.getEmail()) && !endUserDB.getEmail().isEmpty()) {
						rawMails.add(new RawMail("Update_Visa_Reject_System", 
								endUserDB.getId(), 
								4, 
								Features.UPDATE_VISA, 
								SubFeatures.SYSTEM_REJECT, 
								endUserDB.getTxnId(), 
								endUserDB.getTxnId(), 
								placeholders,
								ReferTable.END_USER,
								null,
								receiverUserType));
						emailUtil.saveNotification(rawMails);	
						txnId = endUserDB.getTxnId();

					}
				}else {
					txnId = "0";
					userId=0;
					return new GenricResponse(2, "unknown operation", "");


				}
			}
			else {
				userTypeId=0;
				txnId = "0";
				userId=0;
				return new GenricResponse(1, "You are not allowed to do this operation.", "");
			}
			auditTrailRepository.save(new AuditTrail(userId, username, userTypeId,
					ceirActionRequest.getUserType(), 43,Features.UPDATE_VISA, sufeature, "", txnId));
			updateVisaRepo.save(visaDb);

			return new GenricResponse(0, "Visa Update SuccessFully.", ceirActionRequest.getTxnId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}


	private GenericSpecificationBuilder<VisaUpdateDb> buildSpecification(FilterRequest filterRequest){

		GenericSpecificationBuilder<VisaUpdateDb> uPSB = new GenericSpecificationBuilder<VisaUpdateDb>(propertiesReader.dialect);	
		if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getStatus()) && filterRequest.getStatus()!=-1)
		{
			uPSB.with(new SearchCriteria("status",filterRequest.getStatus(), SearchOperation.EQUALITY, Datatype.INT));
		}
		else {
			uPSB.with(new SearchCriteria("status",3, SearchOperation.EQUALITY, Datatype.INT));
		}

		if(Objects.nonNull(filterRequest.getTxnId()) && !filterRequest.getTxnId().isEmpty()) {
			uPSB.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));
		}
		
		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			uPSB.orSearch(new SearchCriteria("visaNumber", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			uPSB.orSearch(new SearchCriteria("visaExpiryDate", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			uPSB.orSearch(new SearchCriteria("createdOn", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			uPSB.orSearch(new SearchCriteria("modifiedOn", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			uPSB.orSearch(new SearchCriteria("txnId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));

		}
		
		return uPSB;
	}


	public Page<VisaUpdateDb>  viewAllUpdateVisaRecord(FilterRequest filterRequest, Integer pageNo, Integer pageSize){
		try { 
			logger.info("filter data:  "+filterRequest);
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
			Page<VisaUpdateDb> page = updateVisaRepository.findAll( buildSpecification(filterRequest).build(), pageable );


			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), filterRequest.getUserName(), 8L,
					filterRequest.getUserType(), 43,Features.UPDATE_VISA, SubFeatures.VIEW_ALL, "","NA"));


			for(VisaUpdateDb visa : page.getContent()) {
				List<StateMgmtDb> statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
				logger.info("after fetching state mgmt data");

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(visa.getStatus() == stateMgmtDb.getState()) {
						visa.setStateInterp(stateMgmtDb.getInterp());
						break;
					}
				}
				// setInterp(consignmentMgmt2);
				if(Objects.nonNull(visa.getVisaType()))
				{
					visa.setVisaTypeInterp(interpSetter.setConfigInterp(Tags.VISA_TYPE, visa.getVisaType()));	
				}
			}
			return page;

		} catch (Exception e) {
			logger.info("Exception found ="+e.getMessage());
			logger.info(e.getClass().getMethods().toString());
			logger.info(e.toString());
			return null;

		}
	}
	public List<VisaUpdateDb> getAllVisaUpdate(FilterRequest filterRequest) {

		try {
			List<VisaUpdateDb> visaData = updateVisaRepository.findAll( buildSpecification(filterRequest).build());

			return visaData;

		} catch (Exception e) {
			logger.info("Exception found ="+e.getMessage());
			logger.info(e.getClass().getMethods().toString());
			logger.info(e.toString());
			return null;
		}

	}

	public FileDetails getFilterDataInFile(FilterRequest filterRequest) {
		logger.info("inside export user profile data into file service");
		logger.info("filter data:  "+filterRequest);
		String fileName = null;
		Writer writer   = null;
		UpdateVisaFileModel uVFm = null;
		SystemConfigurationDb userProfileDowlonadDir=configurationManagementServiceImpl.findByTag("VisaUpdate_Download_Dir");
		SystemConfigurationDb userProfileDowlonadLink=configurationManagementServiceImpl.findByTag("VisaUpdate_Download_link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


		String filePath  = userProfileDowlonadDir.getValue();
		logger.info("filePath:  "+filePath);
		StatefulBeanToCsvBuilder<UpdateVisaFileModel> builder = null;
		StatefulBeanToCsv<UpdateVisaFileModel> csvWriter      = null;
		List<UpdateVisaFileModel> fileRecords       = null;
		HeaderColumnNameTranslateMappingStrategy<UpdateVisaFileModel> mapStrategy = null;
		try {
			List<VisaUpdateDb> visaData = this.getAllVisaUpdate(filterRequest);
			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), filterRequest.getUserName(), 8L,
					filterRequest.getUserType(), 43,Features.UPDATE_VISA, SubFeatures.EXPORT, "","NA"));

			for(VisaUpdateDb visa : visaData) {
				List<StateMgmtDb> statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());
				logger.info("after fetching state mgmt data");

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(visa.getStatus() == stateMgmtDb.getState()) {
						visa.setStateInterp(stateMgmtDb.getInterp());
						break;
					}
				}
				// setInterp(consignmentMgmt2);
				if(Objects.nonNull(visa.getVisaType()))
				{
					visa.setVisaTypeInterp(interpSetter.setConfigInterp(Tags.VISA_TYPE, visa.getVisaType()));	

				}
			}

			if( visaData.size()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_UpdateVisa.csv";
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_UpdateVisa.csv";
			}
			logger.info(" file path plus filke name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<UpdateVisaFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( visaData.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<UpdateVisaFileModel>(); 
				for( VisaUpdateDb visa : visaData ) {
					uVFm = new UpdateVisaFileModel();
					uVFm.setRequestedOn(utility.converedtlocalTime(visa.getCreatedOn()));
					uVFm.setModifiedOn(utility.converedtlocalTime(visa.getModifiedOn()));
					uVFm.setVisaExpiryDate(visa.getVisaExpiryDate());
					uVFm.setVisaNumber(visa.getVisaNumber());
					uVFm.setVisaType(visa.getVisaTypeInterp());
					uVFm.setStatus(visa.getStateInterp());
					//System.out.println(uVFm.toString());
					fileRecords.add(uVFm);
				}
				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath,userProfileDowlonadLink.getValue()+fileName ); 
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

	public GenricResponse endUserById(FilterRequest filterRequest) {
		try {
			EndUserDB endUserDB = endUserDbRepository.getById(filterRequest.getId());
			// End user is not registered with CEIR system.
			logger.info("inside end user data by id service and given data: "+filterRequest.getId());

			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), filterRequest.getUserName(), 8L,
					filterRequest.getUserType(), 43,Features.UPDATE_VISA, SubFeatures.VIEW, "","NA"));

			
			if(Objects.nonNull(endUserDB)) {
				VisaUpdateDb visaUpdateDb=updateVisaRepo.findByUserId(filterRequest.getId());
				List<RegularizeDeviceDb> regulaizedList=new ArrayList<RegularizeDeviceDb>();
				if(Objects.nonNull(endUserDB.getRegularizeDeviceDbs()))
					for(RegularizeDeviceDb regularizeData:endUserDB.getRegularizeDeviceDbs()) {
						regularizeData.setEndUserDB(null); 
						regulaizedList.add(regularizeData);
					}
				endUserDB.setRegularizeDeviceDbs(regulaizedList);
				logger.info("End User with id [" + filterRequest.getId() + "] does exist.");
				if(Objects.nonNull(endUserDB.getVisaDb())) {
					List<VisaDb> visaList=new ArrayList<VisaDb>();
					for(VisaDb visa:endUserDB.getVisaDb()) {
						visa.setVisaTypeInterp(interpSetter.setConfigInterp(Tags.VISA_TYPE, visa.getVisaType()));	
						visaList.add(visa);
					}
					endUserDB.setVisaDb(visaList);
					if(Objects.nonNull(endUserDB.getDocType())) {
						endUserDB.setDocTypeInterp(interpSetter.setTagId(Tags.DOC_TYPE, endUserDB.getDocType()));	
					}
					if(Objects.nonNull(visaUpdateDb.getRemark())) {
						endUserDB.setRejectedRemark(visaUpdateDb.getRemark());
					}
				}
				return new GenricResponse(1, "End User does exist.", "", endUserDB);
			}else {
				logger.info("End User with id [" + filterRequest.getId() + "] does not exist.");
				return new GenricResponse(0, "User does not exist.", "");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}
}