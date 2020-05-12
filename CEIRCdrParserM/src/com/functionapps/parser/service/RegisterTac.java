package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.functionapps.dao.MessageConfigurationDbDao;
import com.functionapps.dao.NotificationDao;
import com.functionapps.dao.TypeApprovalDbDao;
import com.functionapps.dao.UserWithProfileDao;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.HttpResponse;
import com.functionapps.pojo.MessageConfigurationDb;
import com.functionapps.pojo.Notification;
import com.functionapps.pojo.TypeApprovedDb;
import com.functionapps.pojo.UserWithProfile;
import com.functionapps.resttemplate.TacApiConsumer;
import com.gl.Rule_engine.RuleEngineApplication;

public class RegisterTac {
	static Logger logger = Logger.getLogger(RegisterTac.class);
	
	public RegisterTac() {
		
	}

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txnId, 
			String operator_tag, String usertypeName){

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
				
				String [] ruleArr = {"EXISTS_IN_TAC_DB",
						"1",
						"TAC",
						typeApprovedDb.getTac(),
						"0",
						"", // file_name
						"0",
						"", // record_time
						"", // operator
						"error",
						"", // operator_tag
						"", // period
						"", // servedMSISDN
						"" // action
						};
				
				/*
				 * String output = RuleEngineApplication.startRuleEngine(ruleArr);
				 * 
				 * System.out.println("Rule [EXISTS_IN_TAC_DB] Execution output is [" + output +
				 * "]");
				 * 
				 * if("yes".equalsIgnoreCase(output)) { typeApprovedDb.setApproveStatus(3); //
				 * Pending by CEIR Admin }else { typeApprovedDb.setApproveStatus(2); // Rejected
				 * By System. }
				 */

				HttpResponse httpResponse = tacApiConsumer.approveReject(typeApprovedDb.getTxnId(), 3);
				
				if(httpResponse.getErrorCode() != 200) {
					// TODO Add to the Alert.
					logger.info("Approve_Reject API for type_approved_db is failed. Response[" + httpResponse + "]");
					return;
				}
				
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

		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}
