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
import com.gl.ceir.constant.ReferTable;
import com.gl.ceir.entity.SystemConfigurationDb;
import com.gl.ceir.entity.User;
import com.gl.ceir.factory.service.BaseService;
import com.gl.ceir.factory.service.transaction.RemoveInCompleteUserTransaction;
import com.gl.ceir.pojo.RawMail;
import com.gl.ceir.service.UsersServiceImpl;
import com.gl.ceir.util.DateUtil;

@Component
public class RemoveIncompleteUser extends BaseService{

	private static final Logger logger = LogManager.getLogger(RemoveIncompleteUser.class);

	List<RawMail> rawMails = new ArrayList<>();

	@Autowired
	UsersServiceImpl usersServiceImpl;

	@Autowired
	RemoveInCompleteUserTransaction removeIncompleteUser;

	@Override
	public void fetch() {
		
		try {
			SystemConfigurationDb removeInCompleteUserInDays = systemConfigurationDbRepository.getByTag(ConfigTags.REMOVE_IN_COMPLETE_USER_IN_DAYS);

			if(Objects.isNull(removeInCompleteUserInDays)) {
				onErrorRaiseAnAlert(Alerts.ALERT_007, null);
				logger.info("Alert [ALERT_007] is raised. So, doing nothing.");
				return;
			}

			// Read all inactive grievances for last configured number of days.
			List<User> userList = usersServiceImpl.getUserWithStatusPendingOtp((Integer.parseInt(removeInCompleteUserInDays.getValue())) * -1);

			List<Long> userIds = new ArrayList<>();
			Map<String, String> placeholderMap = null;

			for(User user : userList) {

				// Add userId to delete them.
				userIds.add(user.getId());

				// Save in notification.
				placeholderMap = new HashMap<>();
				placeholderMap.put("<User>", user.getUserProfile().getFirstName());

				rawMails.add(new RawMail("Email", 
						"MAIL_TO_USER_ON_ACCOUNT_REMOVAL_FOR_OTP_PENDING", 
						user.getId(), 
						8L, // Feature Id 
						"Process",
						"Removal",
						user.getUsername(),
						"Removing user With Username " + user.getUsername(), // Subject 
						placeholderMap, 
						ReferTable.USERS, 
						user.getUsertype().getUsertypeName(),
						user.getUsertype().getUsertypeName()));

			}

			if(userIds.isEmpty()) {
				logger.info("No user with otp pending is there in DB today. [" + DateUtil.nextDate(0) + "]");
			}else {
				process(userIds);
			}

		}catch (NumberFormatException e) {
			alertServiceImpl.raiseAnAlert(Alerts.ALERT_007, 0);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void process(Object o) {
		@SuppressWarnings("unchecked")
		List<Long> userIds = (List<Long>) o;

		try {
			removeIncompleteUser.performTransaction(userIds, rawMails);
		}catch (Exception e) {
			logger.info(e.getMessage(), e);
			Map<String, String> bodyPlaceholder = new HashMap<>();
			bodyPlaceholder.put("<e>", e.getMessage());
			bodyPlaceholder.put("<process_name>", "Remove incomplete users.");
			onErrorRaiseAnAlert(Alerts.ALERT_006, bodyPlaceholder);	
		}
	}

}
