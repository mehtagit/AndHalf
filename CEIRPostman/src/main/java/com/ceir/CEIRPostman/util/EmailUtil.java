package com.ceir.CEIRPostman.util;

import org.slf4j.LoggerFactory;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ceir.CEIRPostman.RepositoryService.SystemConfigurationDbRepoImpl;
import com.ceir.CEIRPostman.model.SystemConfigurationDb;
import com.mysql.cj.log.Log;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
@Service
public class EmailUtil {

	private final Logger logger = LoggerFactory.getLogger(getClass());	

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	MailSender mailSender; 
	@Autowired
	SystemConfigurationDbRepoImpl systemConfigRepoImpl;

	private final Logger log = LoggerFactory.getLogger(getClass());

	int batchSize =1; // for example, adjust it to you needs
	SimpleMailMessage[] messages;
	int messageIndex = 0;
	


	public void setBatchSize(int batch,int noOfElements) {
		if(noOfElements<batch) {
			logger.info("if total mails less than batch size");
			logger.info("message array size: "+noOfElements);
			messages = new SimpleMailMessage[noOfElements];
			batchSize=noOfElements;
		}
		else {
			logger.info("if total mails greater than or equals to batch size");
			messages = new SimpleMailMessage[batch];
			batchSize=batch;
		}
	}
	public boolean sendEmail(String toAddress, String fromAddress, String subject, String msgBody,int totalData,int dataRead,Integer sleep) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromAddress);
		simpleMailMessage.setTo(toAddress);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msgBody);
		
		try {
			logger.info("adding emails into the array");
			 messages[messageIndex++] = simpleMailMessage;
			    if (messageIndex == batchSize) {
			    	logger.info("if batch size equals to no of mails added into array");
			    	logger.info("now going to send emails");
			        mailSender.send(messages);
			        logger.info("no of emails are sent: "+batchSize);
			        int difference=totalData-dataRead;
			        setBatchSize(batchSize,difference);
			        logger.info("now batchSize is "+batchSize);
			        messageIndex = 0;
			    }
			    try {
			    	log.info("sleep time in milliseconds: "+sleep);
					Thread.sleep(sleep);
				}
				catch(Exception e) {
					log.info(e.toString());
				}
			return Boolean.TRUE;
		}catch (Exception e) {
			logger.info("error occur while send email");
			logger.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
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
}