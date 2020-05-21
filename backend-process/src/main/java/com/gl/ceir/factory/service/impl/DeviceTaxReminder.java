package com.gl.ceir.factory.service.impl;

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
import com.gl.ceir.factory.service.BaseService;
import com.gl.ceir.pojo.RawMail;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.pojo.UserWiseMailCount;
import com.gl.ceir.repo.PolicyBreachNotificationRepository;
import com.gl.ceir.repo.RegularizedDeviceDbRepository;
import com.gl.ceir.service.RegularizeDbServiceImpl;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;

@Component
public class DeviceTaxReminder extends BaseService{

	private static final Logger logger = LogManager.getLogger(DeviceTaxReminder.class);

	@Autowired
	PolicyBreachNotificationRepository policyBreachNotificationRepository;

	@Autowired
	RegularizeDbServiceImpl regularizeDbServiceImpl;

	@Override
	public void fetch() {

		try {
			SystemConfigurationDb graceDays 	= systemConfigurationDbRepository.getByTag(ConfigTags.GRACE_PERIOD_FOR_RGISTER_DEVICE);
			SystemConfigurationDb reminderDays 	= systemConfigurationDbRepository.getByTag(ConfigTags.REMINDER_DAYS);

			SystemConfigurationDb sendNotiOnDeviceTaxNotPaid 	= systemConfigurationDbRepository.getByTag(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID);
			systemConfigMap.put(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID, sendNotiOnDeviceTaxNotPaid);

			if(Objects.isNull(graceDays) || Objects.isNull(reminderDays)) {
				onErrorRaiseAnAlert(Alerts.ALERT_003, null);
				logger.info("Alert [ALERT_003] is raised. So, doing nothing.");
				return;
			}

			String fromDate = DateUtil.nextDate( (Integer.parseInt(graceDays.getValue()) + Integer.parseInt(reminderDays.getValue())) * -1);
			String toDate = DateUtil.nextDate( (Integer.parseInt(graceDays.getValue()) - Integer.parseInt(reminderDays.getValue()) -1) * -1);
			logger.info("Reminder will sent to user who has registered device on date [" + fromDate + "] toDate[" + toDate + "] and not paid tax.");

			List<RegularizeDeviceDb> regularizeDeviceDbs = regularizeDbServiceImpl.getDevicesbyTaxStatusAndDate(fromDate, toDate, 1);

			List<RegularizeDeviceDb> processedDeviceDbs = new ArrayList<>();
			for(RegularizeDeviceDb regularizeDeviceDb : regularizeDeviceDbs) {
				EndUserDB endUserDB = regularizeDeviceDb.getEndUserDB();
				logger.info(endUserDB);
				if("cambodian".equalsIgnoreCase(endUserDB.getNationality())) {
					processedDeviceDbs.add(regularizeDeviceDb);
				}else {
					logger.info("Current Device belong to a foreigner, So no need to send a reminder because of tax not paid.");
				}
			}

			if(processedDeviceDbs.isEmpty()) {
				logger.info("No new device of cambodian to send reminder found today[" + fromDate + "]");
				return;
			}

			logger.info("No. of reminders need send update today is[" + processedDeviceDbs.size() + "]");

			process(processedDeviceDbs);

		}catch (NumberFormatException e) {
			onErrorRaiseAnAlert(Alerts.ALERT_003, null);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void process(Object o) {
		String channel = "SMS";
		String policyBreachMessage = "User have not paid tax of registered device.";

		@SuppressWarnings("unchecked")
		List<RegularizeDeviceDb> regularizeDeviceDbs = (List<RegularizeDeviceDb>) o;
		logger.info("Going to send reminder for devices : " + regularizeDeviceDbs);
		
		// Add Entry In Policy Breach Table.
		List<PolicyBreachNotification> policyBreachNotifications = new ArrayList<>();

		for(RegularizeDeviceDb regularizeDeviceDb : regularizeDeviceDbs) {
			EndUserDB endUserDB = regularizeDeviceDb.getEndUserDB();
			if(Objects.isNull(endUserDB)) {
				logger.info("No end user is found associated with device of IMEI [" + regularizeDeviceDb.getFirstImei() + "]");
			}else {	
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
			}
			
			logger.info(policyBreachNotifications);
			
			policyBreachNotificationRepository.saveAll(policyBreachNotifications);
			logger.info("Entry added in policy_breach_notification.");
			
			// Save in notification.
			if("Y".equalsIgnoreCase(systemConfigMap.get(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID).getValue())) {
				regularizeDbServiceImpl.sendNotification(regularizeDeviceDbs, "REMINDER_DEVICE_TAX_NOT_PAID");
			}else {
				logger.info("WARN : Notification is off for reminding user on failure of tax paying of registered device.");
			}
		}
	}	
}
