package com.gl.ceir.factory.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.constant.Alerts;
import com.gl.ceir.constant.ConfigTags;
import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.entity.RegularizeDeviceDb;
import com.gl.ceir.entity.SystemConfigurationDb;
import com.gl.ceir.factory.service.BaseService;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.PolicyBreachNotificationRepository;
import com.gl.ceir.repo.RegularizedDeviceDbRepository;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;

@Component
public class BlockEndUserDevice extends BaseService{

	private static final Logger logger = LogManager.getLogger(BlockEndUserDevice.class);

	@Autowired
	RegularizedDeviceDbRepository regularizedDeviceDbRepository;

	@Autowired
	PolicyBreachNotificationRepository policyBreachNotificationRepository;


	@Override
	public void fetch() {

		try {
			SystemConfigurationDb graceDays 	= systemConfigurationDbRepository.getByTag(ConfigTags.GRACE_PERIOD_FOR_RGISTER_DEVICE);

			SystemConfigurationDb sendNotiOnDeviceTaxNotPaid 	= systemConfigurationDbRepository.getByTag(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID);
			systemConfigMap.put(ConfigTags.SEND_NOTI_ON_DEVICE_TAX_NOT_PAID, sendNotiOnDeviceTaxNotPaid);

			if(Objects.isNull(graceDays)) {
				onErrorRaiseAnAlert(Alerts.ALERT_003, null);
				logger.info("Alert [ALERT_003] is raised. So, doing nothing.");
				return;
			}

			String reminderDate = DateUtil.nextDate( (Integer.parseInt(graceDays.getValue())) * -1);
			logger.info("Reminder will sent to user who has registered device on date [" + reminderDate + "] and not paid tax.");

			List<RegularizeDeviceDb> regularizeDeviceDbs = regularizedDeviceDbRepository.findAll(buildSpecification(reminderDate).build());

			for(RegularizeDeviceDb regularizeDeviceDb : regularizeDeviceDbs) {
				regularizeDeviceDb.setStatus(3); // Blocked State

			}

			process(regularizeDeviceDbs);

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

		if(regularizeDeviceDbs.isEmpty()) {
			logger.info("No device to be found for blocking [" + DateUtil.nextDate(0) + "]");
		}else {
			regularizedDeviceDbRepository.saveAll(regularizeDeviceDbs);
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
