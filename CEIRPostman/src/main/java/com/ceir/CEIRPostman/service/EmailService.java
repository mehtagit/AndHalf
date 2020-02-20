package com.ceir.CEIRPostman.service;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ceir.CEIRPostman.Repository.NotificationRepository;
import com.ceir.CEIRPostman.RepositoryService.EndUserRepoService;
import com.ceir.CEIRPostman.RepositoryService.NotificationRepoImpl;
import com.ceir.CEIRPostman.RepositoryService.SystemConfigurationDbRepoImpl;
import com.ceir.CEIRPostman.RepositoryService.UserRepoService;
import com.ceir.CEIRPostman.configuration.AppConfig;
import com.ceir.CEIRPostman.model.EndUserDB;
import com.ceir.CEIRPostman.model.Notification;
import com.ceir.CEIRPostman.model.SystemConfigurationDb;
import com.ceir.CEIRPostman.model.User;
import com.ceir.CEIRPostman.util.EmailUtil;

@Service
public class EmailService implements Runnable {

	@Autowired
	EmailUtil emailUtil;

	@Autowired
	NotificationRepository notificationRepo;

	@Autowired
	AppConfig appConfig;

	@Autowired
	NotificationRepoImpl notificationRepoImpl;
	
	@Autowired
	SystemConfigurationDbRepoImpl systemConfigRepoImpl;
	
	@Value("${mailusername}")
	String fromEmail;
	
	@Autowired
	EndUserRepoService endUserRepoService;
	
	@Autowired
	UserRepoService userRepoService;

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public void run() {
		while (true) {
			log.info("inside run method");
			try {
				List<Notification> notificationData=notificationRepoImpl.notitificationByStatus(1,"SMS");
				int totalMailsent=0;
				int totalMailNotsent=0;
				if(notificationData!=null) {
					SystemConfigurationDb emailBodyFooter=systemConfigRepoImpl.getDataByTag("mail_signature");
					for(Notification notification:notificationData) {
						String body=new String();
						body=notification.getMessage();
						if(emailBodyFooter!=null) {
							body=body+"\n"+emailBodyFooter.getValue();
						}
						String toEmail=new String();
						
						if(notification.getReferTable()!=null) {
							log.info("refer Table: "+notification.getReferTable());
							if("END_USER".equals(notification.getReferTable())) {
							   EndUserDB endUser=endUserRepoService.getById(notification.getUserId());
							   toEmail=endUser.getEmail();
							}
							else {
								User user=userRepoService.getById(notification.getUserId());
								toEmail=user.getUserProfile().getEmail();
										
							}
						}
						else {
							User user=userRepoService.getById(notification.getUserId());
							toEmail=user.getUserProfile().getEmail();
						}
						boolean emailStatus = false;
						if(toEmail!=null) {
							 emailStatus=emailUtil.sendEmail(toEmail,fromEmail,notification.getSubject() , body);
						}
							if(emailStatus) {
							log.info("if email sent");
							notification.setStatus(0);
							totalMailsent++;
						}
						else{
							log.info("if email not sent");
							notification.setRetryCount(notification.getRetryCount()+1);
							if(notification.getRetryCount()==appConfig.getMaxCount()) {
								notification.setStatus(2);
							}
							totalMailNotsent++;
						}
						notificationRepo.save(notification);
					}
					log.info("total mail sent=  "+totalMailsent);
					log.info("email fail to send: "+totalMailNotsent);
				}
				else {
					log.info("if no email is pending to send");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(60000);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}