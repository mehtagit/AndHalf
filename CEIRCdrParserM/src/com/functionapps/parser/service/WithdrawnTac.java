package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.functionapps.dao.TypeApprovalDbDao;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.HttpResponse;
import com.functionapps.pojo.TypeApprovedDb;
import com.functionapps.resttemplate.TacApiConsumer;

public class WithdrawnTac {
	static Logger logger = Logger.getLogger(WithdrawnTac.class);

	public WithdrawnTac() {

	}

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txnId, 
			String operator_tag, String usertypeName){

		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
		TypeApprovalDbDao typeApprovalDbDao = new TypeApprovalDbDao();
		TacApiConsumer tacApiConsumer = new TacApiConsumer();

		try{
			Optional<TypeApprovedDb> typeApprovedDbOptional = typeApprovalDbDao.getTypeApprovedDbTxnId(conn, "", txnId);

			if(typeApprovedDbOptional.isPresent()) {
				TypeApprovedDb typeApprovedDb = typeApprovedDbOptional.get();
				
				HttpResponse httpResponse = tacApiConsumer.delete(txnId, typeApprovedDb.getUserId(), 
						"SystemAdmin", 2);

				if(httpResponse.getStatusCode() != 200) {
					// TODO Add to the Alert.
					logger.info("Delete API for type_approved_db is failed. Response[" + httpResponse + "]");
					return;
				}else {
					logger.info("Delete API for type_approved_db is Success. Response[" + httpResponse + "]");
					return;
				}
			}else {
				logger.info("type_approved_db has no record for txn_id[" + txnId + "]");
				return;
			}

			// ceirfunction.updateFeatureFileStatus(conn, txnId, 2, operator, sub_feature);	

		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}