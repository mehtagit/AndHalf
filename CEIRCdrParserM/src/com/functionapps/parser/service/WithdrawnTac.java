package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.functionapps.dao.TypeApprovalDbFinalDao;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.Rule;

public class WithdrawnTac {
	static Logger logger = Logger.getLogger(WithdrawnTac.class);

	public WithdrawnTac() {
	}
	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txnId, String operator_tag ){

		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
		TypeApprovalDbFinalDao typeApprovalDbDao = new TypeApprovalDbFinalDao();

		try{
			typeApprovalDbDao.deleteByTxnId(conn, txnId);
			ceirfunction.updateFeatureFileStatus(conn, txnId, 2, operator, sub_feature);	

			conn.commit();
//			conn.close();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}