package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.functionapps.dao.DeviceCustomDbDao;
import com.functionapps.dao.DeviceImporterDbDao;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.DeviceImporterDb;

public class ApproveConsignment {
	static Logger logger = Logger.getLogger(ApproveConsignment.class);

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txnId, String operator_tag ){

		DeviceCustomDbDao deviceCustomDbDao = new DeviceCustomDbDao();
		DeviceImporterDbDao deviceImporterDbDao = new DeviceImporterDbDao();
		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
		
		try{

			List<DeviceImporterDb> deviceImporterDbs = deviceImporterDbDao.getDeviceImporterDbByTxnId(conn, "", txnId);
			deviceCustomDbDao.insertDeviceCustomDb(conn, deviceImporterDbs);
		
			ceirfunction.updateFeatureFileStatus(conn, txnId, 2, operator, sub_feature);
			// TODO hit API to update delete flag in consignment.
			conn.commit();

		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}
