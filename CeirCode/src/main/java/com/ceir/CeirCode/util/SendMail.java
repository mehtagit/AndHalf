package com.ceir.CeirCode.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMail {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendingMail(String to, String subject, String body) {
		MimeMessage message=javaMailSender.createMimeMessage();  
		MimeMessageHelper helper;
		log.info("inside email sending");
		try {
			
			helper=new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);  
			javaMailSender.send(message);
			log.info("email sucessfully sent");
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.info("email fail to sent");
			log.info(e.getMessage());
			e.printStackTrace();
			return false;
		}



	}}