package com.gl.ceir.factory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.constant.Alerts;
import com.gl.ceir.constant.ConfigTags;
import com.gl.ceir.entity.EndUserDB;
import com.gl.ceir.entity.RegularizeDeviceDb;
import com.gl.ceir.entity.SystemConfigurationDb;
import com.gl.ceir.factory.service.BaseService;
import com.gl.ceir.service.RegularizeDbServiceImpl;
import com.gl.ceir.util.DateUtil;

@Component
public class BlockEndUserDevice extends BaseService{

	private static final Logger logger = LogManager.getLogger(BlockEndUserDevice.class);

	@Autowired
	RegularizeDbServiceImpl regularizeDbServiceImpl;

	@Override
	public void fetch() {

		try {
			SystemConfigurationDb graceDays = systemConfigurationDbRepository.getByTag(ConfigTags.GRACE_PERIOD_FOR_RGISTER_DEVICE);
			logger.info("graceDays [" + graceDays + "]");

			SystemConfigurationDb sendNotiOnDeviceTaxNotPaid = systemConfigurationDbRepository.getByTag(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID);
			logger.info("sendNotiOnDeviceTaxNotPaid [" + sendNotiOnDeviceTaxNotPaid + "]");

			systemConfigMap.put(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID, sendNotiOnDeviceTaxNotPaid);

			if(Objects.isNull(graceDays)) {
				onErrorRaiseAnAlert(Alerts.ALERT_003, null);
				logger.info("Alert [ALERT_003] is raised. So, doing nothing.");
				return;
			}

			String fromDate = DateUtil.nextDate( (Integer.parseInt(graceDays.getValue())) * -1);
			String toDate = DateUtil.nextDate( ( Integer.parseInt( graceDays.getValue() ) - 1) * -1);
			logger.info("Device block notification will sent to user who has registered device on Date [" + fromDate + "] and not paid tax.");

			List<RegularizeDeviceDb> regularizeDeviceDbs = regularizeDbServiceImpl.getDevicesbyTaxStatusAndDate(fromDate, toDate, 1);

			List<RegularizeDeviceDb> processedDeviceDbs = new ArrayList<>();
			for(RegularizeDeviceDb regularizeDeviceDb : regularizeDeviceDbs) {
				EndUserDB endUserDB = regularizeDeviceDb.getEndUserDB();
				logger.info(endUserDB);
				if("cambodian".equalsIgnoreCase(endUserDB.getNationality())) {
					// regularizeDeviceDb.setStatus(3); // Blocked State
					regularizeDeviceDb.setTaxPaidStatus(3);
					processedDeviceDbs.add(regularizeDeviceDb);
				}else {
					logger.info("Current Device belong to a foreigner, So no need to block the device because of tax not paid.");
				}
			}

			if(processedDeviceDbs.isEmpty()) {
				logger.info("No new device of cambodian to be block found today[" + fromDate + "]");
				return;
			}

			logger.info("No. of devices need to update today is[" + processedDeviceDbs.size() + "]");

			process(processedDeviceDbs);

		}catch (NumberFormatException e) {
			onErrorRaiseAnAlert(Alerts.ALERT_003, null);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void process(Object o) {
		
		@SuppressWarnings("unchecked")
		List<RegularizeDeviceDb> regularizeDeviceDbs = (List<RegularizeDeviceDb>) o;
		logger.info("Going to block devices : " + regularizeDeviceDbs);
		
		regularizeDbServiceImpl.saveAllDevices(regularizeDeviceDbs);
		logger.info("All devices are blocked.");
		
		// Save in notification.
		if("Y".equalsIgnoreCase(systemConfigMap.get(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID).getValue())) {
			regularizeDbServiceImpl.sendNotification(regularizeDeviceDbs, "BLOCK_DEVICE_ON_TAX_NOT_PAID_MAIL", "Block device");
		}else {
			logger.info("WARN : Notification is off for reminding user on failure of tax paying of registered device.");
		}
	}
}
