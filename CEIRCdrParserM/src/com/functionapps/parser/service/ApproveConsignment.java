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

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txnId, String operator_tag , String usertype_name){

		DeviceCustomDbDao deviceCustomDbDao = new DeviceCustomDbDao();
		DeviceImporterDbDao deviceImporterDbDao = new DeviceImporterDbDao();
		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();

		try{
			System.out.println("sop1");
			List<DeviceImporterDb> deviceImporterDbs = deviceImporterDbDao.getDeviceImporterDbByTxnId(conn, "", txnId);
			System.out.println(deviceImporterDbs);

			System.out.println("deviceImporterDbs.size()" + deviceImporterDbs.size());

			System.out.println("sop2");
			deviceCustomDbDao.insertDeviceCustomDb(conn, deviceImporterDbs);

			System.out.println("sop3");
			ceirfunction.updateFeatureFileStatus(conn, txnId, 2, operator, sub_feature);
			// TODO hit API to update delete flag in consignment.

			conn.commit();
//			c onn.close();

		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}
