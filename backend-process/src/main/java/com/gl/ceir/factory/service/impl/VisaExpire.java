package com.gl.ceir.factory.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.constant.Alerts;
import com.gl.ceir.constant.ConfigTags;
import com.gl.ceir.constant.Datatype;
import com.gl.ceir.constant.SearchOperation;
import com.gl.ceir.entity.EndUserDB;
import com.gl.ceir.entity.SystemConfigurationDb;
import com.gl.ceir.entity.VisaDb;
import com.gl.ceir.factory.service.BaseService;
import com.gl.ceir.factory.service.transaction.ExpireVisaTransaction;
import com.gl.ceir.pojo.SearchCriteria;
import com.gl.ceir.repo.EndUserDbRepository;
import com.gl.ceir.repo.VisaDbRepository;
import com.gl.ceir.specification.GenericSpecificationBuilder;
import com.gl.ceir.util.DateUtil;

@Component
public class VisaExpire extends BaseService{

	private static final Logger logger = LogManager.getLogger(VisaExpire.class);

	@Autowired
	EndUserDbRepository endUserDbRepository;

	@Autowired
	VisaDbRepository visaDbRepository;

	@Autowired
	ExpireVisaTransaction expireVisaTransaction;

	@Override
	public void fetch() {
		try {
			SystemConfigurationDb reminderDays 	= systemConfigurationDbRepository.getByTag(ConfigTags.REMINDER_DAYS);

			if(Objects.isNull(reminderDays)) {
				onErrorRaiseAnAlert(Alerts.ALERT_003, null);
				logger.info("Alert [ALERT_003] is raised. So, doing nothing.");
				return;
			}

			String date = DateUtil.nextDate( Integer.parseInt(reminderDays.getValue()) * -1);
			logger.info("Date : " + date);

			List<VisaDb> visaDbs = visaDbRepository.findAll(buildSpecification(date).build());
			
			logger.info("visaDbs : " + visaDbs);

			for(VisaDb visaDb : visaDbs) {
				process(visaDb);
			}

		}catch (NumberFormatException e) {
			onErrorRaiseAnAlert(Alerts.ALERT_003, null);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void process(Object o) {
		VisaDb visaDb = (VisaDb) o;
		logger.info("Visa DB : " + visaDb);

		EndUserDB endUserDB = visaDb.getEndUserDB();
		logger.info("End User DB : " + endUserDB);

		try {
			expireVisaTransaction.performTransaction(endUserDB, visaDb);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private GenericSpecificationBuilder<VisaDb> buildSpecification(String date){
		GenericSpecificationBuilder<VisaDb> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		cmsb.with(new SearchCriteria("visaExpiryDate", date, SearchOperation.EQUALITY, Datatype.STRING));
		cmsb.with(new SearchCriteria("visaExpiryDate", date, SearchOperation.EQUALITY, Datatype.STRING));

		return cmsb;
	}

}
