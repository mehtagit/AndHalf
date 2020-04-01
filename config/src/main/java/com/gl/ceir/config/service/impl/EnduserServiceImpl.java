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
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.EndUserDB;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RawMail;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.UserDepartment;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.model.VisaDb;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.EndUserStatus;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.ReferTable;
import com.gl.ceir.config.model.constants.RegularizeDeviceStatus;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.TaxStatus;
import com.gl.ceir.config.model.file.EndUserFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.EndUserDbRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.VipListRepository;
import com.gl.ceir.config.repository.VisaHistoryDBRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.transaction.EndUserTransaction;
import com.gl.ceir.config.util.CommonFunction;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.DateUtil;
import com.opencsv.CSVWriter;
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

	public GenricResponse endUserByNid(String nid) {
		try {
			EndUserDB endUserDB = endUserDbRepository.getByNid(nid);

			// End user is not registered with CEIR system.
			if(Objects.nonNull(endUserDB)) {
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

			// Validate and set visa expiry date as per default rule.
			GenricResponse response = setVisaExpiryDate(endUserDB);
			if(response.getErrorCode() != 0) 
				return response;

			// Validate end user devices.
			if(!endUserDB.getRegularizeDeviceDbs().isEmpty()){
				if(commonFunction.hasDuplicateImeiInRequest(endUserDB.getRegularizeDeviceDbs())) {
					return new GenricResponse(6,GenericMessageTags.DUPLICATE_IMEI_IN_REQUEST.getTag(),GenericMessageTags.DUPLICATE_IMEI_IN_REQUEST.getMessage(), ""); 
				}
				for(RegularizeDeviceDb regularizeDeviceDb : endUserDB.getRegularizeDeviceDbs()) {
					//TO DO
					if(commonFunction.checkAllImeiOfRegularizedDevice(regularizeDeviceDb)) {
						return new GenricResponse(5,GenericMessageTags.DUPLICATE_IMEI.getTag(),GenericMessageTags.DUPLICATE_IMEI.getMessage(),"");
					}
					
					if(Objects.isNull(regularizeDeviceDb.getTaxPaidStatus())) {
						regularizeDeviceDb.setTaxPaidStatus(TaxStatus.TAX_NOT_PAID.getCode());
					}

					if(Objects.isNull(regularizeDeviceDb.getStatus())) {
						regularizeDeviceDb.setStatus(RegularizeDeviceStatus.PENDING_APPROVAL_FROM_CEIR_ADMIN.getCode());
					}

					if(Objects.isNull(endUserDB.getOrigin())) {
						endUserDB.setOrigin(regularizeDeviceDb.getOrigin());
					}

					// Add in web action list.
					webActionDbs.add(new WebActionDb(Features.REGISTER_DEVICE, SubFeatures.REGISTER, 0, 
							regularizeDeviceDb.getTxnId()));
				}

				logger.info(endUserDB.getRegularizeDeviceDbs());
			}

			endUserDB = endUserDbRepository.save(endUserDB);
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
					// Update expiry date of latest Visa
					VisaDb visaDb = visaDbs.get(visaDbs.size() - 1);
					visaDb.setVisaExpiryDate(latestVisa.getVisaExpiryDate());	
				}

				if(endUserTransaction.executeUpdateVisa(endUserDB1)) {
					return new GenricResponse(0, GenericMessageTags.VISA_UPDATE_SUCCESS.getTag(), 
							GenericMessageTags.VISA_UPDATE_SUCCESS.getMessage(), endUserDB.getNid());
				}else {
					return new GenricResponse(1, GenericMessageTags.VISA_UPDATE_FAIL.getTag(), 
							GenericMessageTags.VISA_UPDATE_FAIL.getMessage(), endUserDB.getNid());
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

					// Mail to End user.
					rawMails.add(new RawMail(mailTag, endUserDB.getId(), Long.valueOf(updateRequest.getFeatureId()), 
							Features.MANAGE_USER, SubFeatures.ACCEPT_REJECT, updateRequest.getTxnId(), 
							"SUBJECT", placeholderMap, ReferTable.END_USER, null, "END_USER_REJECT_BY_CEIR_ADMIN"));

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

}