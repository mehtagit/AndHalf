package com.gl.ceir.factory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.constant.Alerts;
import com.gl.ceir.constant.ConfigTags;
import com.gl.ceir.entity.EndUserDB;
import com.gl.ceir.entity.PolicyBreachNotification;
import com.gl.ceir.entity.RegularizeDeviceDb;
import com.gl.ceir.entity.SystemConfigurationDb;
import com.gl.ceir.factory.service.BaseService;
import com.gl.ceir.pojo.MessageConfigurationDb;
import com.gl.ceir.pojo.UserWiseMailCount;
import com.gl.ceir.repo.MessageConfigurationDbRepository;
import com.gl.ceir.repo.PolicyBreachNotificationRepository;
import com.gl.ceir.service.RegularizeDbServiceImpl;
import com.gl.ceir.util.DateUtil;

@Component
public class DeviceTaxReminder extends BaseService{

	private static final Logger logger = LogManager.getLogger(DeviceTaxReminder.class);

	@Autowired
	PolicyBreachNotificationRepository policyBreachNotificationRepository;

	@Autowired
	RegularizeDbServiceImpl regularizeDbServiceImpl;

	@Autowired
	MessageConfigurationDbRepository messageConfigurationDbRepository;

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

			String toDate = DateUtil.nextDate( (Integer.parseInt(graceDays.getValue()) - Integer.parseInt(reminderDays.getValue()) -1) * -1);
			logger.info("Reminder will sent to user who has registered device on toDate[" + toDate + "] and not paid tax.");

			List<RegularizeDeviceDb> regularizeDeviceDbs = regularizeDbServiceImpl.getDevicesbyTaxStatusAndDate(toDate, 1);

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
				logger.info("No new device of cambodian to send reminder found today[" + DateUtil.nextDate(0) + "]");
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
		String tag = "REMINDER_DEVICE_TAX_NOT_PAID";
		String policyBreachMessage = "";

		// Check if user has email.
		MessageConfigurationDb messageDB = messageConfigurationDbRepository.getByTagAndActive(tag, 0);
		policyBreachMessage = messageDB.getValue();

		@SuppressWarnings("unchecked")
		List<RegularizeDeviceDb> regularizeDeviceDbs = (List<RegularizeDeviceDb>) o;
		logger.info("Going to send reminder for devices : " + regularizeDeviceDbs);

		// Add Entry In Policy Breach Table.
		List<PolicyBreachNotification> policyBreachNotifications = new ArrayList<>();

		List<UserWiseMailCount> userWiseMailCounts = regularizeDbServiceImpl.getUserWiseMailCountDto(regularizeDeviceDbs);

		for(UserWiseMailCount userWiseMailCount : userWiseMailCounts) {
			Map<String, String>  placeholders = userWiseMailCount.getPlaceholderMap();
			
			// Replace Placeholders from message.
			if(Objects.nonNull(placeholders)) {
				for (Map.Entry<String, String> entry : placeholders.entrySet()) {
					logger.debug("Placeholder key : " + entry.getKey() + " value : " + entry.getValue());
					policyBreachMessage = policyBreachMessage.replaceAll(entry.getKey(), entry.getValue());
				}
			}
			
			policyBreachNotifications.add(new PolicyBreachNotification(
					channel, 
					policyBreachMessage, 
					"", 
					Long.parseLong(userWiseMailCount.getPhoneNo()), 
					userWiseMailCount.getFirstImei()));
			if(Objects.nonNull(userWiseMailCount.getSecondImei()))
				policyBreachNotifications.add(new PolicyBreachNotification(
						channel, 
						policyBreachMessage, 
						"", 
						Long.parseLong(userWiseMailCount.getPhoneNo()), 
						userWiseMailCount.getSecondImei()));

			if(Objects.nonNull(userWiseMailCount.getThirdImei()))
				policyBreachNotifications.add(new PolicyBreachNotification(
						channel, 
						policyBreachMessage, 
						"", 
						Long.parseLong(userWiseMailCount.getPhoneNo()), 
						userWiseMailCount.getThirdImei()));

			if(Objects.nonNull(userWiseMailCount.getFourthImei()))
				policyBreachNotifications.add(new PolicyBreachNotification(
						channel, 
						policyBreachMessage, 
						"", 
						Long.parseLong(userWiseMailCount.getPhoneNo()), 
						userWiseMailCount.getFourthImei()));
		}

		logger.info(policyBreachNotifications);

		policyBreachNotificationRepository.saveAll(policyBreachNotifications);
		logger.info("Entry added in policy_breach_notification.");

		// Save in notification.
		if("Y".equalsIgnoreCase(systemConfigMap.get(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID).getValue())) {
			regularizeDbServiceImpl.sendNotification(regularizeDeviceDbs, tag, "Reminder");
		}else {
			logger.info("WARN : Notification is off for reminding user on failure of tax paying of registered device.");
		}
	}
}