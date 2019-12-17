package com.ceir.CEIRPostman.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ceir.CEIRPostman.Repository.NotificationRepository;
import com.ceir.CEIRPostman.model.Notification;
import com.ceir.CEIRPostman.util.EmailUtil;

@Service
public class EmailService implements Runnable {

	@Autowired
	EmailUtil emailUtil;

	@Autowired
	NotificationRepository notificationRepo;

	@Value("${maxCount}")
	Integer maxCount;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public void run() {
		while (true) {
		log.info("inside run method");
		try {
			
			List<Notification> notificationData=notificationRepo.findByStatus(1);
			for(Notification notification:notificationData) {
				boolean emailStataus=emailUtil.sendEmail(notification.getUserForNofication().getUserProfile().getEmail(), "heenakumari1024@gmail.com",notification.getSubject() , notification.getMessage());
				if(emailStataus) {
					log.info("if email sent");
					notification.setStatus(0);
				}
				else {
					log.info("if email not sent");
					notification.setRetryCount(notification.getRetryCount()+1);
					if(notification.getRetryCount()==maxCount) {
						notification.setStatus(2);
					}
				}
				notificationRepo.save(notification);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {
              Thread.sleep(10000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	}
}