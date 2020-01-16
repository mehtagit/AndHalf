package com.ceir.CEIRPostman.service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ceir.CEIRPostman.Repository.NotificationRepository;
import com.ceir.CEIRPostman.RepositoryImpl.NotificationRepoImpl;
import com.ceir.CEIRPostman.RepositoryImpl.SystemConfigurationDbRepoImpl;
import com.ceir.CEIRPostman.configuration.AppConfig;
import com.ceir.CEIRPostman.model.Notification;
import com.ceir.CEIRPostman.model.SystemConfigurationDb;
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

	private final Logger log = LoggerFactory.getLogger(getClass());
	public void run() {
		while (true) {
			log.info("inside run method");
			try {
				List<Notification> notificationData=notificationRepoImpl.notitificationByStatus(1);
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
						boolean emailStatus=emailUtil.sendEmail(notification.getUserForNofication().getUserProfile().getEmail(), "heenakumari1024@gmail.com",notification.getSubject() , body);
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
				Thread.sleep(3600000);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}