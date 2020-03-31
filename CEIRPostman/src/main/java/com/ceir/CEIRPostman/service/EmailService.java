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
import com.ceir.CEIRPostman.RepositoryService.UserTempRepoService;
import com.ceir.CEIRPostman.configuration.AppConfig;
import com.ceir.CEIRPostman.model.EndUserDB;
import com.ceir.CEIRPostman.model.Notification;
import com.ceir.CEIRPostman.model.SystemConfigurationDb;
import com.ceir.CEIRPostman.model.User;
import com.ceir.CEIRPostman.model.UserTemporarydetails;
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
	
	@Autowired
	UserTempRepoService userTempRepoService;
	
	@Value("${type}")
	String type;

	@Autowired
	EmailConfiguration emailConfiguration;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public void run() {
		SystemConfigurationDb batchSizeData=systemConfigRepoImpl.getDataByTag("Total_email_Send_InSec");
		SystemConfigurationDb emailProcessSleep=systemConfigRepoImpl.getDataByTag("EmailProcess_Sleep");
		SystemConfigurationDb sleepTps=systemConfigRepoImpl.getDataByTag("Email_TPS_Milli_Sec");
  		Integer sleepTimeinMilliSec=Integer.parseInt(sleepTps.getValue());
  		emailConfiguration.getEmailConfiguration();
		try {
        		sleepTimeinMilliSec=Integer.parseInt(sleepTps.getValue());
        	  
          }
          catch(Exception e) {
            log.info(e.toString());        	  
          }
		while (true) {
			log.info("inside email process");
			int batchSize=0;
			if(batchSizeData!=null) {
				log.info("no of email per second value from db: "+batchSizeData.getValue());
				batchSize=Integer.parseInt(batchSizeData.getValue());			
			}
			else {
				batchSize=1;
			}
	
			try {
				log.info("inside email process");
				log.info("going to fetch data from notification table by status=1 and channel type= "+type);
				List<Notification> notificationData=notificationRepoImpl.notitificationByStatus(1,type);
				int totalMailsent=0;
				int totalMailNotsent=0;
				if(notificationData!=null) {
					log.info("if notification data is not empty");
					SystemConfigurationDb emailBodyFooter=systemConfigRepoImpl.getDataByTag("mail_signature");
					int sNo=0;
					emailUtil.setBatchSize(batchSize, notificationData.size());
					for(Notification notification:notificationData) {
						log.info("notifiaction data id= "+notification.getId());
						sNo++;
						String body=new String();
						body=notification.getMessage();
						if(emailBodyFooter!=null) {
							body=body+"\n"+emailBodyFooter.getValue();
						}
						String toEmail=new String();
						
						if(notification.getReferTable()!=null) {
							log.info("refer Table: "+notification.getReferTable());
							if("END_USER".equalsIgnoreCase(notification.getReferTable())) {
							   EndUserDB endUser=endUserRepoService.getById(notification.getUserId());
							   toEmail=endUser.getEmail();
							}
							else if("user_temp".equalsIgnoreCase(notification.getReferTable())){
								UserTemporarydetails details=userTempRepoService.getUserTempByUserId(notification.getUserId());
							    if(details!=null) {
							    	toEmail=details.getEmailId();
							    }
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
							 emailStatus=emailUtil.sendEmail(toEmail,fromEmail,notification.getSubject() , body,notificationData.size(),sNo,sleepTimeinMilliSec);
						}
							if(emailStatus) {
							notification.setStatus(0);
							totalMailsent++;
						}
						else{
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
			log.info("exit from email process");
			}
			catch(Exception e) {
				log.info("error in sending email");
				log.info(e.toString());
				log.info(e.toString());
			}
			try {
				Thread.sleep(Integer.parseInt(emailProcessSleep.getValue()));
			}
			catch(Exception e) {
				log.info(e.toString());
			}
		}
	}
}