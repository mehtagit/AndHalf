package com.ceir.CeirCode.util;

import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ceir.CeirCode.model.MessageConfigurationDb;
import com.ceir.CeirCode.model.Notification;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.constants.ChannelType;
import com.ceir.CeirCode.repo.MessageConfigurationDbRepository;
import com.ceir.CeirCode.repo.NotificationRepository;
import com.ceir.CeirCode.service.ConfigurationManagementServiceImpl;
import com.ceir.CeirCode.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Component
public class EmailUtil {

	private Logger logger = (Logger) LogManager.getLogger(EmailUtil.class);

	@Autowired
	MailSender mailSender; 

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired
	MessageConfigurationDbRepository messageConfigurationDbRepository;

	@Autowired
	UserService userService;

	@Autowired
	NotificationRepository notificationRepo;

	public boolean sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromAddress);
		simpleMailMessage.setTo(toAddress);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msgBody);

		try {
			mailSender.send(simpleMailMessage);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	public void sendEmailWithAttactment(String toAddress, String fromAddress, String subject, String msgBody, String attachment) {

		MimeMessage message = javaMailSender.createMimeMessage();
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(fromAddress);
			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(msgBody);

			FileSystemResource file = new FileSystemResource(attachment);
			helper.addAttachment(file.getFilename(), file);

		}catch (MessagingException e) {
			throw new MailParseException(e);
		}

		javaMailSender.send(message);
	}

	public boolean sendMessageAndSaveNotification(@NonNull String tag, UserProfile userProfileData, long featureId, String featureName, String subFeature, String featureTxnId,String subject,String otp) {
		try {
			String emailBody=null;
			MessageConfigurationDb messageDB = new MessageConfigurationDb();
			messageDB = messageConfigurationDbRepository.getByTag(tag);
			logger.info("messageDB data by tag: "+messageDB);
			emailBody=userService.emailContent(messageDB, userProfileData, otp);
			logger.info("email body=  "+emailBody);

			if(sendEmail(userProfileData.getEmail(), "heenakumari1024@gmail.com",subject, emailBody)) {
				logger.info("Email to user have been sent successfully."); 
				configurationManagementServiceImpl.saveNotification(ChannelType.EMAIL, messageDB.getValue(), userProfileData.getUser(), featureId, featureName, subFeature, featureTxnId,subject);

			}else {
				logger.info("Email to user have been failed.");
			}

			return Boolean.TRUE;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}

	public boolean saveNotification(@NonNull String tag, UserProfile userProfileData, long featureId, String featureName, String subFeature, String featureTxnId,String subject,String otp,String channelType) {
		try {
			String emailBody=null;
			MessageConfigurationDb messageDB = new MessageConfigurationDb();
			messageDB = messageConfigurationDbRepository.getByTag(tag);
			logger.info("messageDB data by tag: "+messageDB);
			emailBody=userService.emailContent(messageDB, userProfileData, otp);
			logger.info("email body=  "+emailBody);
			Notification notification=new Notification();
			notification.setChannelType(channelType);
			notification.setCreatedOn(LocalDateTime.now());
			notification.setFeatureId(featureId);
			notification.setFeatureName(featureName);
			notification.setMessage(emailBody);
			notification.setModifiedOn(LocalDateTime.now());
			notification.setSubFeature(subFeature);
			notification.setUserForNofication(userProfileData.getUser());
			notification.setFeatureTxnId(featureTxnId);
			notification.setStatus(1); 
			notification.setSubject(subject);
			notification.setRetryCount(0);
			Notification output=notificationRepo.save(notification);
			if(output!=null) {
				logger.info("notification sucessfully saved");
			}
			else {
				logger.info("notification failed to save");
			}

			return Boolean.TRUE;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}




}

