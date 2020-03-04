package com.gl.ceir.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.constant.Alerts;
import com.gl.ceir.constant.ConfigTags;
import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.ReferTable;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.entity.EndUserDB;
import com.gl.ceir.entity.PolicyBreachNotification;
import com.gl.ceir.entity.RegularizeDeviceDb;
import com.gl.ceir.entity.SystemConfigurationDb;
import com.gl.ceir.notifier.NotifierWrapper;
import com.gl.ceir.pojo.RawMail;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.PolicyBreachNotificationRepository;
import com.gl.ceir.repo.RegularizedDeviceDbRepository;
import com.gl.ceir.repo.SystemConfigurationDbRepository;
import com.gl.ceir.service.BaseService;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;

@Component
public class DeviceTaxReminder extends BaseService{

	private static final Logger logger = LogManager.getLogger(DeviceTaxReminder.class);

	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;

	@Autowired
	AlertServiceImpl alertServiceImpl;

	@Autowired
	RegularizedDeviceDbRepository regularizedDeviceDbRepository;
	
	@Autowired
	PolicyBreachNotificationRepository policyBreachNotificationRepository;
	
	@Autowired
	NotifierWrapper notifierWrapper;

	@Override
	public void fetch() {

		try {
			SystemConfigurationDb graceDays 	= systemConfigurationDbRepository.getByTag(ConfigTags.GRACE_PERIOD_FOR_RGISTER_DEVICE);
			SystemConfigurationDb reminderDays 	= systemConfigurationDbRepository.getByTag(ConfigTags.REMINDER_DAYS);

			SystemConfigurationDb sendNotiOnDeviceTaxNotPaid 	= systemConfigurationDbRepository.getByTag(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID);
			systemConfigMap.put(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID, sendNotiOnDeviceTaxNotPaid);

			if(Objects.isNull(graceDays) || Objects.isNull(reminderDays)) {
				alertServiceImpl.raiseAnAlert(Alerts.ALERT_003, 0);
				logger.info("Alert [ALERT_003] is raised. So, doing nothing.");
				return;
			}

			String reminderDate = DateUtil.nextDate( (Integer.parseInt(graceDays.getValue()) + Integer.parseInt(reminderDays.getValue())) * -1);
			logger.info("Reminder will sent to user who has registered device on date [" + reminderDate + "] and not paid tax.");

			List<RegularizeDeviceDb> regularizeDeviceDbs = regularizedDeviceDbRepository.findAll(buildSpecification(reminderDate).build());

			for(RegularizeDeviceDb regularizeDeviceDb : regularizeDeviceDbs) {
				process(regularizeDeviceDb);
			}

		}catch (NumberFormatException e) {
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_003, 0);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void process(Object o) {
		String channel = "SMS";
		String policyBreachMessage = "User have not paid tax of registered device.";
		
		RegularizeDeviceDb regularizeDeviceDb = (RegularizeDeviceDb) o;
		EndUserDB endUserDB = regularizeDeviceDb.getEndUserDB();

		if(Objects.isNull(endUserDB)) {
			logger.info("No end user is found associated with device of IMEI [" + regularizeDeviceDb.getFirstImei() + "]");
		}else {
			if("Cambodian".equalsIgnoreCase(endUserDB.getNationality())) {
				
				// Add Entry In Policy Breach Table.
				List<PolicyBreachNotification> policyBreachNotifications = new ArrayList<>(4);
				
				policyBreachNotifications.add(new PolicyBreachNotification(
						channel, 
						policyBreachMessage, 
						"", 
						Long.parseLong(endUserDB.getPhoneNo()), 
						regularizeDeviceDb.getFirstImei()));
				if(Objects.nonNull(regularizeDeviceDb.getSecondImei()))
					policyBreachNotifications.add(new PolicyBreachNotification(
							channel, 
							policyBreachMessage, 
							"", 
							Long.parseLong(endUserDB.getPhoneNo()), 
							regularizeDeviceDb.getSecondImei()));
				
				if(Objects.nonNull(regularizeDeviceDb.getThirdImei()))
					policyBreachNotifications.add(new PolicyBreachNotification(
							channel, 
							policyBreachMessage, 
							"", 
							Long.parseLong(endUserDB.getPhoneNo()), 
							regularizeDeviceDb.getThirdImei()));
				
				if(Objects.nonNull(regularizeDeviceDb.getFourthImei()))
					policyBreachNotifications.add(new PolicyBreachNotification(
							channel, 
							policyBreachMessage, 
							"", 
							Long.parseLong(endUserDB.getPhoneNo()), 
							regularizeDeviceDb.getFourthImei()));
					
				policyBreachNotificationRepository.saveAll(policyBreachNotifications);
				
				// Save in notification.
				if("Y".equalsIgnoreCase(systemConfigMap.get(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID).getValue())) {
					List<RawMail> rawMails = new ArrayList<>(1);
					Map<String, String> placeholderMap = new HashMap<>();
					
					rawMails.add(new RawMail(channel, 
							"REMINDER_DEVICE_TAX_NOT_PAID", 
							endUserDB.getId(), 
							0L, // Feature Id 
							"Process",
							"Reminder",
							"", // Txn Id 
							"", // Subject 
							placeholderMap, 
							ReferTable.END_USER, 
							"End User",
							"End User"));
					notifierWrapper.saveNotification(rawMails);
					
				}else {
					logger.info("WARN : Notification is off for reminding user on failure of tax paying of registered device.");
				}
			}else {
				logger.info("End user is a foreigner associated with device of IMEI [" + regularizeDeviceDb.getFirstImei() + "]");
			}
		}
	}

	private GenericSpecificationBuilder<RegularizeDeviceDb> buildSpecification(String reminderDate){
		GenericSpecificationBuilder<RegularizeDeviceDb> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		cmsb.with(new SearchCriteria("createdOn", reminderDate, SearchOperation.GREATER_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("createdOn", reminderDate, SearchOperation.LESS_THAN, Datatype.DATE));
		cmsb.with(new SearchCriteria("taxPaidStatus", 1, SearchOperation.EQUALITY, Datatype.STRING));

		return cmsb;
	}	
}
