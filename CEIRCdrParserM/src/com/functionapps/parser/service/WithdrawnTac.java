package com.functionapps.parser.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.functionapps.constants.PropertyReader;
import com.functionapps.dao.TypeApprovalDbFinalDao;
import com.functionapps.http.HttpService;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.HttpResponse;
import com.google.gson.Gson;

public class WithdrawnTac {
	static Logger logger = Logger.getLogger(WithdrawnTac.class);

	static String userId = "0";
	static String userType = "CEIRSYSTEM";
	static String deleteFlag = "1";
	static String deleteUri;

	PropertyReader propertyReader;
	Gson gson;

	public WithdrawnTac() {
		propertyReader = new PropertyReader();
		gson = new Gson();

		try {
			deleteUri = propertyReader.getPropValue("api.tac.delete") 
					+ "?userId=" + userId 
					+ "&userType=" + userType 
					+ "&deleteFlag=" + deleteFlag 
					+ "&txnId=<txn_id>";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void process(Connection conn, String operator, String subFeature, ArrayList<Rule> rulelist, String txnId, String operator_tag ){

		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();

		try{
			deleteUri = deleteUri.replace("<txn_id>", txnId) ;

			System.out.println("Going to hit uri : " + deleteUri);
			String response = HttpService.sendPOST(deleteUri);
			HttpResponse httpResponse = gson.fromJson(response, HttpResponse.class);
			
			if(httpResponse.getStatusCode() == 200) {
				ceirfunction.updateFeatureFileStatus(conn, txnId, 2, operator, subFeature);	
			}else {
				System.out.println("Tac deletion failed.");
			}

			conn.commit();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}