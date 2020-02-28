package com.gl.ceir.config.service.impl;

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

import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.MessageConfigurationDb;
import com.gl.ceir.config.model.MessageConfigurationHistoryDb;
import com.gl.ceir.config.model.Notification;
import com.gl.ceir.config.model.PolicyConfigurationDb;
import com.gl.ceir.config.model.PolicyConfigurationHistoryDb;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.SystemConfigUserwiseDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.SystemConfigurationHistoryDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.MessageConfigurationDbRepository;
import com.gl.ceir.config.repository.MessageConfigurationHistoryDbRepository;
import com.gl.ceir.config.repository.NotificationRepository;
import com.gl.ceir.config.repository.PolicyConfigurationDbRepository;
import com.gl.ceir.config.repository.PolicyConfigurationHistoryDbRepository;
import com.gl.ceir.config.repository.SystemConfigListRepository;
import com.gl.ceir.config.repository.SystemConfigUserwiseRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.SystemConfigurationHistoryDbRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.util.InterpSetter;

@Service
public class ConfigurationManagementServiceImpl {

	private static final Logger logger = LogManager.getLogger(ConfigurationManagementServiceImpl.class);

	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;

	@Autowired
	MessageConfigurationDbRepository messageConfigurationDbRepository;

	@Autowired
	PolicyConfigurationDbRepository policyConfigurationDbRepository;

	@Autowired
	SystemConfigurationHistoryDbRepository systemConfigurationHistoryDbRepository;

	@Autowired
	MessageConfigurationHistoryDbRepository messageConfigurationHistoryDbRepository;

	@Autowired
	PolicyConfigurationHistoryDbRepository policyConfigurationHistoryDbRepository;

	@Autowired
	SystemConfigListRepository systemConfigListRepository;

	@Autowired
	SystemConfigUserwiseRepository systemConfigUserwiseRepository;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	InterpSetter interpSetter;

	public List<SystemConfigurationDb> getAllInfo(){
		try {
			return systemConfigurationDbRepository.findAll();
		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Page<SystemConfigurationDb> filterSystemConfiguration(FilterRequest filterRequest, Integer pageNo, Integer pageSize){
		try {

			Pageable pageable = PageRequest.of(pageNo, pageSize);
			GenericSpecificationBuilder<SystemConfigurationDb> sb = new GenericSpecificationBuilder<SystemConfigurationDb>(propertiesReader.dialect);

			if(Objects.nonNull(filterRequest.getTag()))
				sb.with(new SearchCriteria("tag", filterRequest.getTag(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getType()))
				sb.with(new SearchCriteria("type", filterRequest.getType(), SearchOperation.EQUALITY, Datatype.STRING));
			
			if(Objects.nonNull(filterRequest.getFeatureName()))
				sb.with(new SearchCriteria("featureName", filterRequest.getFeatureName(), SearchOperation.EQUALITY, Datatype.STRING));
			
			if(Objects.nonNull(filterRequest.getUserType()))
				sb.with(new SearchCriteria("userType", filterRequest.getUserType(), SearchOperation.EQUALITY, Datatype.STRING));
			
			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
				sb.orSearch(new SearchCriteria("description", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				sb.orSearch(new SearchCriteria("value", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			}

			Page<SystemConfigurationDb> page = systemConfigurationDbRepository.findAll(sb.build(), pageable);

			for(SystemConfigurationDb systemConfigurationDb : page.getContent()) {	
				systemConfigurationDb.setTypeInterp(interpSetter.setConfigInterp(Tags.CONFIG_TYPE, systemConfigurationDb.getType()));
			}
			return page;

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public SystemConfigurationDb findByTag(SystemConfigurationDb systemConfigurationDb){
		try {
			return systemConfigurationDbRepository.getByTag(systemConfigurationDb.getTag());
		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public SystemConfigurationDb findByTag(String tag){
		try {
			return systemConfigurationDbRepository.getByTag(tag);
		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	@Transactional
	public GenricResponse updateSystemInfo(SystemConfigurationDb systemConfigurationDb) {
		try {
			GenricResponse genricResponse = validateUpdateRequest(systemConfigurationDb.getTag(), systemConfigurationDb.getValue());
			if(genricResponse.getErrorCode() != 0) {
				return genricResponse;
			}

			SystemConfigurationDb systemConfigurationDb2 = systemConfigurationDbRepository.getByTag(systemConfigurationDb.getTag());
			logger.info("Persisted data " + systemConfigurationDb2);

			if(Objects.isNull(systemConfigurationDb2)) {
				return new GenricResponse(15, "This Id does not exist", "");
			}

			systemConfigurationHistoryDbRepository.save(
					new SystemConfigurationHistoryDb(systemConfigurationDb2.getTag(), systemConfigurationDb2.getValue(), systemConfigurationDb2.getDescription()
							));

			systemConfigurationDb2.setValue(systemConfigurationDb.getValue());
			systemConfigurationDbRepository.save(systemConfigurationDb2);

			return new GenricResponse(0, "System configuration update Sucessfully", "");

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());	
		}
	}

	public List<MessageConfigurationDb> getMessageConfigAllDetails(){

		try {

			return messageConfigurationDbRepository.findAll();

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	

	}

	public Page<MessageConfigurationDb> filterMessageConfiguration(FilterRequest filterRequest, Integer pageNo, Integer pageSize){
		try {

			Pageable pageable = PageRequest.of(pageNo, pageSize);
			GenericSpecificationBuilder<MessageConfigurationDb> sb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

			if(Objects.nonNull(filterRequest.getTag()))
				sb.with(new SearchCriteria("tag", filterRequest.getTag(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getChannel()))
				sb.with(new SearchCriteria("channel", filterRequest.getChannel(), SearchOperation.EQUALITY, Datatype.STRING));
			
			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
				sb.orSearch(new SearchCriteria("description", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				sb.orSearch(new SearchCriteria("value", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			}
			
			Page<MessageConfigurationDb> page = messageConfigurationDbRepository.findAll(sb.build(), pageable);

			for(MessageConfigurationDb messageConfigurationDb : page.getContent()) {	
				messageConfigurationDb.setChannelInterp(interpSetter.setConfigInterp(Tags.CHANNEL, messageConfigurationDb.getChannel()));
			}

			return page;

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public MessageConfigurationDb getMessageConfigDetailsByTag(MessageConfigurationDb messageConfigurationDb){
		try {

			MessageConfigurationDb messageConfigurationDb2 = messageConfigurationDbRepository.getByTag(messageConfigurationDb.getTag());
			messageConfigurationDb2.setChannelInterp(interpSetter.setConfigInterp(Tags.CHANNEL, messageConfigurationDb2.getChannel()));
			
			return messageConfigurationDb2;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}

	@Transactional
	public GenricResponse updateMessageInfo(MessageConfigurationDb messageConfigurationDb) {
		try {
			GenricResponse genricResponse = validateUpdateRequest(messageConfigurationDb.getTag(), messageConfigurationDb.getValue());
			if(genricResponse.getErrorCode() != 0) {
				return genricResponse;
			}

			MessageConfigurationDb mcd = messageConfigurationDbRepository.getByTag(messageConfigurationDb.getTag());

			if(Objects.isNull(mcd)) {
				return new GenricResponse(15, "This id does not exist","");
			}

			MessageConfigurationHistoryDb mshb = new MessageConfigurationHistoryDb();
			mshb.setDescription(mcd.getDescription());
			mshb.setTag(mcd.getTag());
			mshb.setValue(mcd.getValue());

			messageConfigurationHistoryDbRepository.save(mshb);

			mcd.setValue(messageConfigurationDb.getValue());
			mcd.setDescription(messageConfigurationDb.getDescription());
			logger.info("Persisted message data " + messageConfigurationDb);
			messageConfigurationDbRepository.save(mcd);

			return new  GenricResponse(0, "Message config info update sucessfully", "");

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());	
		}
	}

	public PolicyConfigurationDb getPolicyConfigDetailsByTag(PolicyConfigurationDb messageConfigurationDb){
		try {

			return policyConfigurationDbRepository.getByTag(messageConfigurationDb.getTag());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}

	public PolicyConfigurationDb getPolicyConfigDetailsByTag(String tag){
		try {

			return policyConfigurationDbRepository.getByTag(tag);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}

	public Page<PolicyConfigurationDb> filterPolicyConfiguration(FilterRequest filterRequest, Integer pageNo, Integer pageSize){
		try {

			Pageable pageable = PageRequest.of(pageNo, pageSize);
			GenericSpecificationBuilder<PolicyConfigurationDb> sb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

			if(Objects.nonNull(filterRequest.getTag()))
				sb.with(new SearchCriteria("tag", filterRequest.getTag(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getStatus()))
				sb.with(new SearchCriteria("status", filterRequest.getStatus(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
				sb.orSearch(new SearchCriteria("description", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				sb.orSearch(new SearchCriteria("value", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			}
			
			Page<PolicyConfigurationDb> page = policyConfigurationDbRepository.findAll(sb.build(), pageable);

			for(PolicyConfigurationDb policyConfigurationDb : page.getContent()) {
				policyConfigurationDb.setStatusInterp(interpSetter.setConfigInterp(Tags.IS_ACTIVE, policyConfigurationDb.getStatus()));
			}

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<PolicyConfigurationDb> getPolicyConfigDetails(){
		try {

			return policyConfigurationDbRepository.findAll();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}

	@Transactional
	public GenricResponse updatePolicyInfo(PolicyConfigurationDb policyConfigurationDb) {
		try {
			GenricResponse genricResponse = validateUpdateRequest(policyConfigurationDb.getTag(), policyConfigurationDb.getValue());
			if(genricResponse.getErrorCode() != 0) {
				return genricResponse;
			}

			PolicyConfigurationDb mcd = policyConfigurationDbRepository.getByTag(policyConfigurationDb.getTag());
			if(Objects.isNull(mcd)) {
				return new GenricResponse(15, "This tag does not exist", "");
			}

			PolicyConfigurationHistoryDb mshb = new PolicyConfigurationHistoryDb();

			mshb.setDescription(mcd.getDescription());
			mshb.setTag(mcd.getTag());
			mshb.setValue(mcd.getValue());
			policyConfigurationHistoryDbRepository.save(mshb);

			policyConfigurationDb.setTag(mshb.getTag());
			policyConfigurationDb.setCreatedOn(mcd.getCreatedOn());
			policyConfigurationDb.setActive(mcd.getActive());
			policyConfigurationDbRepository.save(policyConfigurationDb);

			return new  GenricResponse(0, "Policy config info update sucessfully", "");

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());	
		}
	}

	@Transactional
	public GenricResponse saveAudit(AuditTrail auditTrail) {
		try {

			auditTrailRepository.save(auditTrail);

			return new GenricResponse(0, "Audit trail save Sucess fully", "");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse saveNotification(Notification notification) {
		try {

			notificationRepository.save(notification);
			return new GenricResponse(0, "Notification have been saved Sucessfully", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse saveNotification(String channelType, String message, Long userId, Long featureId, String featureName, 
			String subFeature, String featureTxnId, String subject, int retryCount, String referTable, String roleType, String receiverUserType) {
		try {

			notificationRepository.save(new Notification(channelType, message, userId, featureId, featureName, 
					subFeature, featureTxnId, subject, retryCount, referTable, roleType, receiverUserType));

			return new GenricResponse(0, "Notification have been saved Sucessfully", "");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public GenricResponse saveAllNotifications(List<Notification> notifications) {
		try {

			List<Notification> notifications2 = notificationRepository.saveAll(notifications);

			return new GenricResponse(0, "Notification have been saved Sucessfully", "", notifications2);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<SystemConfigListDb> getSystemConfigListByTag(String tag){
		try {

			logger.debug("getSystemConfigListByTag : " + tag);

			return systemConfigListRepository.findByTag(tag, new Sort(Sort.Direction.ASC, "listOrder"));

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<SystemConfigListDb> getSystemConfigListByTagAndUserType(String tagId, int userTypeId){
		try {

			logger.debug("getSystemConfigListByTag : " + tagId);

			return getSystemConfigListDb(systemConfigListRepository.findByTag(tagId, new Sort(Sort.Direction.ASC, "listOrder")), systemConfigUserwiseRepository.findByTagIdAndUserTypeId(tagId, userTypeId));

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<SystemConfigListDb> getSystemConfigListByTagAndFeatureId(String tagId, int featureId){
		try {

			logger.debug("getSystemConfigListByTag : " + tagId);

			return getSystemConfigListDb(systemConfigListRepository.findByTag(tagId, new Sort(Sort.Direction.ASC, "listOrder")), systemConfigUserwiseRepository.findByTagIdAndFeatureId(tagId, featureId));

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private List<SystemConfigListDb> getSystemConfigListDb(List<SystemConfigListDb> systemConfigListDbs, List<SystemConfigUserwiseDb> systemConfigUserwiseDbs){

		List<SystemConfigListDb> systemConfigListDbResult = new LinkedList<>();

		for(SystemConfigListDb systemConfigListDb : systemConfigListDbs) {

			for(SystemConfigUserwiseDb systemConfigUserwiseDb : systemConfigUserwiseDbs) {

				if(systemConfigListDb.getValue() == systemConfigUserwiseDb.getValue()) {
					systemConfigListDbResult.add(systemConfigListDb);
					break;
				}
			}
		}

		return systemConfigListDbResult;
	}

	private GenricResponse validateUpdateRequest(String tag, String value) {
		if(Objects.isNull(tag)) {
			logger.info("Receiving tag as null.");
			return new GenricResponse(1, "Receiving tag as null.","");
		}
		if(Objects.isNull(value) && !value.isEmpty()) {
			logger.info("Value of a tag can't be set to null or empty.");
			return new GenricResponse(2, "Value of a tag can't be set to null or empty.","");
		}

		return new GenricResponse(0, "","");
	}
	
	public SystemConfigurationDb saveSystemConfiguration(SystemConfigurationDb systemConfigurationDb){
		try {
			return systemConfigurationDbRepository.save(systemConfigurationDb);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public MessageConfigurationDb saveMessageConfiguration(MessageConfigurationDb messageConfigurationDb){
		try {
			return messageConfigurationDbRepository.save(messageConfigurationDb);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public PolicyConfigurationDb savePolicyConfiguration(PolicyConfigurationDb policyConfigurationDb){
		try {
			return policyConfigurationDbRepository.save(policyConfigurationDb);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
}
