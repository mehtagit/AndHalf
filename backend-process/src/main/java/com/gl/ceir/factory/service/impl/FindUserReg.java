package com.gl.ceir.factory.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.constant.Alerts;
import com.gl.ceir.constant.ReferTable;
import com.gl.ceir.entity.DaywiseUserReg;
import com.gl.ceir.entity.DeviceUsageDb;
import com.gl.ceir.entity.PolicyBreachNotification;
import com.gl.ceir.factory.service.BaseService;
import com.gl.ceir.factory.service.transaction.FindUserRegTransaction;
import com.gl.ceir.pojo.RawMail;
import com.gl.ceir.repo.DaywiseUserRegRepository;
import com.gl.ceir.repo.PolicyBreachNotificationRepository;
import com.gl.ceir.service.DeviceUsageServiceImpl;
import com.gl.ceir.util.DateUtil;

@Component
public class FindUserReg extends BaseService{

	private static final Logger logger = LogManager.getLogger(FindUserReg.class);

	List<PolicyBreachNotification> policyBreachNotifications = new LinkedList<>();
	List<RawMail> rawMails = new ArrayList<>();

	@Autowired
	DaywiseUserRegRepository daywiseUserRegRepository;
	
	@Autowired
	PolicyBreachNotificationRepository policyBreachNotificationRepository;
	
	@Autowired
	FindUserRegTransaction findUserRegTransaction;

	@Autowired
	DeviceUsageServiceImpl deviceUsageServiceImpl;

	@Override
	public void fetch() {

		try {			
			List<DeviceUsageDb> deviceUsageDbs = deviceUsageServiceImpl.getDeviceUsageOfTodayHavingActionUserReg();
			logger.info(deviceUsageDbs);
			
			if(deviceUsageDbs.isEmpty()) {
				logger.info("No User_Reg found close today. [" + DateUtil.nextDate(0) + "]");
			}else {
				List<DaywiseUserReg> daywiseUserRegs = new LinkedList<>();
				
				for(DeviceUsageDb deviceUsageDb : deviceUsageDbs) {
					Map<String, String> placeholderMap = new HashMap<>();
					
					DaywiseUserReg daywiseUserReg = daywiseUserRegRepository.getByImei(deviceUsageDb.getImei());
					if(Objects.isNull(daywiseUserReg)) {
						daywiseUserRegs.add(new DaywiseUserReg(deviceUsageDb.getImei(), deviceUsageDb.getMsisdn(), 1));
					}else {
						// Increase reminder count if user has already got the reminder.
						daywiseUserReg.setReminderCount(daywiseUserReg.getReminderCount() + 1);
						daywiseUserRegs.add(daywiseUserReg);
					}
					
					policyBreachNotifications.add(new PolicyBreachNotification("SMS", 
							"User have not paid taxes.", 
							"", 
							deviceUsageDb.getMsisdn(), 
							deviceUsageDb.getImei()));

					rawMails.add(new RawMail("SMS", 
							"REMINDER_DEVICE_TAX_NOT_PAID", 
							0, 
							0L, // Feature Id 
							"Process",
							"Reminder",
							"", // Txn Id 
							"", // Subject 
							placeholderMap, 
							ReferTable.END_USER, 
							"End User",
							"End User"));
				}

				process(daywiseUserRegs);
			}

		}catch (NumberFormatException e) {
			onErrorRaiseAnAlert(Alerts.ALERT_003, null);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void process(Object o) {
		
		@SuppressWarnings("unchecked")
		List<DaywiseUserReg> daywiseUserReg = (List<DaywiseUserReg>) o;

		findUserRegTransaction.performTransaction(daywiseUserReg, policyBreachNotifications, rawMails);
	}	
}