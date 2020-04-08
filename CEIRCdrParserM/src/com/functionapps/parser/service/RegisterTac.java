package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.functionapps.constants.PropertyReader;
import com.functionapps.dao.MessageConfigurationDbDao;
import com.functionapps.dao.NotificationDao;
import com.functionapps.dao.TypeApprovalDbDao;
import com.functionapps.dao.UserWithProfileDao;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.MessageConfigurationDb;
import com.functionapps.pojo.Notification;
import com.functionapps.pojo.TypeApprovedDb;
import com.functionapps.pojo.UserWithProfile;
import com.functionapps.resttemplate.TacApiConsumer;

public class RegisterTac {
	static Logger logger = Logger.getLogger(RegisterTac.class);
	
	public RegisterTac() {
		
	}

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txnId, String operator_tag ){

		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
		
		TypeApprovalDbDao typeApprovalDbDao = new TypeApprovalDbDao();
		MessageConfigurationDbDao messageConfigurationDbDao = new MessageConfigurationDbDao();
		NotificationDao notificationDao = new NotificationDao();
		UserWithProfileDao userWithProfileDao = new UserWithProfileDao();
		TacApiConsumer tacApiConsumer = new TacApiConsumer();

		try{
			// Fetch type approved details.
			Optional<TypeApprovedDb> typeApprovedDbOptional = typeApprovalDbDao.getTypeApprovedDbTxnId(conn, "", txnId);
			
			if(typeApprovedDbOptional.isPresent()) {
				MessageConfigurationDb messageDb = null;
				
				TypeApprovedDb typeApprovedDb = typeApprovedDbOptional.get();
				typeApprovedDb.setApproveStatus(3); // Pending by CEIR Admin
				System.out.println(typeApprovedDb);
				
				tacApiConsumer.updateStatus(typeApprovedDb.getTxnId(), typeApprovedDb.getUserId(), 
						"CEIRSYSTEM", 2);
				
				// Get users Profile.
				UserWithProfile userWithProfile = userWithProfileDao.getUserWithProfileById(conn, typeApprovedDb.getUserId());
				
				// Read message
				Optional<MessageConfigurationDb> messageDbOptional = messageConfigurationDbDao.getMessageDbTag(conn, "", "TAC_PROCESS_SUCCESFUL_MAIL_TO_USER");
				
				if(messageDbOptional.isPresent()) {
					
					messageDb = messageDbOptional.get();
					String message = messageDb.getValue().replace("<tac>", typeApprovedDb.getTac());
					message = message.replace("<txn_name>", txnId);
					message = message.replace("<first name>", userWithProfile.getFirstName());
					
					// Saving in notification
					Notification notification = new Notification("EMAIL", message, typeApprovedDb.getUserId(), 
							21L, 
							"Type Approved", 
							"Register", 
							txnId, 
							messageDb.getSubject().replace("<XXX>", txnId), 
							"", //roleType
							userWithProfile.getUsertypeName() // receiverUserType
							);
					
					notificationDao.insertNotification(conn, notification);
					
				}else {
					logger.warn("No message is configured for tag [TAC_PROCESS_SUCCESFUL_MAIL_TO_USER]");
					System.out.println("No message is configured for tag [TAC_PROCESS_SUCCESFUL_MAIL_TO_USER]");
				}
				
				ceirfunction.updateFeatureFileStatus(conn, txnId, 2, operator, sub_feature);	
			}else {
				logger.info("Txn_id [" + txnId + "] is is not present in type_approved_db.");
				System.out.println("Txn_id [" + txnId + "] is is not present in type_approved_db.");
			}
			conn.commit();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}