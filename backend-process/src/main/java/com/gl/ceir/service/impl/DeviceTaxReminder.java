package com.gl.ceir.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.configuration.PropertiesReader;
import com.gl.ceir.constant.Alerts;
import com.gl.ceir.constant.ConfigTags;
import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.entity.EndUserDB;
import com.gl.ceir.entity.RegularizeDeviceDb;
import com.gl.ceir.entity.SystemConfigurationDb;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.RegularizedDeviceDbRepository;
import com.gl.ceir.repo.SystemConfigurationDbRepository;
import com.gl.ceir.service.BaseService;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;

@Component
public class DeviceTaxReminder extends BaseService{

	private static final Logger logger = LogManager.getLogger(DeviceTaxReminder.class);

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;

	@Autowired
	AlertServiceImpl alertServiceImpl;

	@Autowired
	RegularizedDeviceDbRepository regularizedDeviceDbRepository;

	@Override
	public void fetch() {
		try {
			SystemConfigurationDb graceDays 	= systemConfigurationDbRepository.getByTag(ConfigTags.GRACE_PERIOD_FOR_RGISTERDEVICE);
			SystemConfigurationDb reminderDays 	= systemConfigurationDbRepository.getByTag(ConfigTags.REMINDER_DAYS);

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
		RegularizeDeviceDb regularizeDeviceDb = (RegularizeDeviceDb) o;
		EndUserDB endUserDB = regularizeDeviceDb.getEndUserDB();
		
		if(Objects.isNull(endUserDB)) {
			logger.info("No end user is found associated with device of IMEI [" + regularizeDeviceDb.getFirstImei() + "]");
		}else {
			if("Cambodian".equalsIgnoreCase(endUserDB.getNationality())) {
				
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
