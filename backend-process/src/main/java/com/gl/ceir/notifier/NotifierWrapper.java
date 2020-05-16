package com.gl.ceir.notifier;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.entity.Notification;
import com.gl.ceir.pojo.RawMail;
import com.gl.ceir.repo.MessageConfigurationDbRepository;
import com.gl.ceir.service.NotificationServiceImpl;
import com.gl.ceir.service.RawmailServiceImpl;

@Component
public class NotifierWrapper {

	private Logger logger = LogManager.getLogger(NotifierWrapper.class);

	@Autowired
	NotificationServiceImpl notificationServiceImpl;

	@Autowired
	MessageConfigurationDbRepository messageConfigurationDbRepository;
	
	@Autowired
	RawmailServiceImpl rawmailServiceImpl;

	public boolean saveNotification(List<RawMail> rawMails) {
		List<Notification> notifications = new ArrayList<>();
		
		if(rawMails.isEmpty()) {
			return Boolean.TRUE;
		}
		
		try {
			for(RawMail rawMail : rawMails) {
				String message = rawmailServiceImpl.createMailContent(rawMail);
				if(message.isEmpty()) {
					continue;
				}
				
				notifications.add(new Notification(rawMail.getChannel(), 
						message, 
						rawMail.getUserId(), 
						rawMail.getFeatureId(),
						rawMail.getFeatureName(), 
						rawMail.getSubFeature(), 
						rawMail.getFeatureTxnId(), 
						rawMail.getSubject(), 
						0,
						rawMail.getReferTable(),
						rawMail.getRoleType(),
						rawMail.getReceiverUserType()));
			}
			
			notificationServiceImpl.saveAllNotifications(notifications);
			
			logger.info("Notification have been saved." + rawMails);
			return Boolean.TRUE;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}
}

