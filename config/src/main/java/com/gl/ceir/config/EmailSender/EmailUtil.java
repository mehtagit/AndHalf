package com.gl.ceir.config.EmailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.model.MessageConfigurationDb;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.constants.ChannelType;
import com.gl.ceir.config.repository.MessageConfigurationDbRepository;
import com.gl.ceir.config.service.impl.ConfigurationManagementServiceImpl;

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

	public boolean saveNotification(@NonNull String tag, UserProfile userProfile, long featureId, 
			String featureName, String subFeature, String featureTxnId, String subject) {
		try {
			MessageConfigurationDb messageDB = messageConfigurationDbRepository.getByTagAndActive(tag, 0);
			String emailBody=null;
			logger.info("messageDB data by tag: "+messageDB);
			emailBody=emailContent(messageDB,userProfile);
			logger.info("email body=  "+emailBody);
			// Save email in notification table.
			configurationManagementServiceImpl.saveNotification(ChannelType.EMAIL,emailBody, 
					userProfile.getUser().getId(), featureId, featureName, subFeature, featureTxnId, subject, 0);


			return Boolean.TRUE;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}
	
	public String emailContent(MessageConfigurationDb msgConfigDb,UserProfile profile) {
		logger.info("tag:  "+msgConfigDb.getTag());
		String emailBody=null;
		
		emailBody=msgConfigDb.getValue();
		//Consignment_Success_CEIRAuthority_Email_Message
		if("Consignment_Approved_CustomImporter_Email_Message".equals(msgConfigDb.getTag()) || "Consignment_Success_CEIRAuthority_Email_Message".equals(msgConfigDb.getTag())
	|| "Consignment_Reject_CEIRAuthority_Email_Message".equals(msgConfigDb.getTag())
	||"Consignment_Rejected_Custom_Email_Message".equals(msgConfigDb.getTag()) ){
			emailBody=emailBody.replaceAll("<Importer first name>", profile.getFirstName());
			emailBody=emailBody.replaceAll("<txn_name>","");
			return emailBody;
		}
		
		else if("STOCK_APPROVED_BY_CEIR_ADMIN".equals(msgConfigDb.getTag())  || 
				"STOCK_APPROVED_BY_CEIR_ADMIN".equals(msgConfigDb.getTag()) || 
				"STOCK_REJECT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag())
				){
			emailBody=emailBody.replaceAll("<Custom first name>", profile.getFirstName());
			emailBody=emailBody.replaceAll("<txn_name>","");
			return emailBody;
		}

		else if("STOLEN_APPROVED_BY_CEIR_ADMIN".equals(msgConfigDb.getTag()) ||
				"RECOVERY_APPROVED_BY_CEIR_ADMIN".equals(msgConfigDb.getTag()) ||
				"BLOCK_APPROVED_BY_CEIR_ADMIN".equals(msgConfigDb.getTag()) ||
				"UNBLOCK_APPROVED_BY_CEIR_ADMIN".equals(msgConfigDb.getTag()) ||
				"STOLEN_REJECT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag()) ||
				"RECOVERY_REJECT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag()) ||
				"BLOCK_REJECT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag()) ||
				"UNBLOCK_REJECT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag())
				){
			emailBody=emailBody.replaceAll("<first name>", profile.getFirstName());
			emailBody=emailBody.replaceAll("<txn_name>","");
			return emailBody;
		}
		else {return emailBody;}
	}
	
}


