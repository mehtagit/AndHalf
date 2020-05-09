package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.functionapps.dao.DeviceCustomDbDao;
import com.functionapps.dao.DeviceImporterDbDao;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.DeviceImporterDb;

public class ApproveConsignment {
	static Logger logger = Logger.getLogger(ApproveConsignment.class);

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, 
			String txnId, String operator_tag, String usertypeName ){

		DeviceCustomDbDao deviceCustomDbDao = new DeviceCustomDbDao();
		DeviceImporterDbDao deviceImporterDbDao = new DeviceImporterDbDao();
	
		try{
			List<DeviceImporterDb> deviceImporterDbs = deviceImporterDbDao.getDeviceImporterDbByTxnId(conn, txnId);
			logger.info(deviceImporterDbs);

			deviceCustomDbDao.insertDeviceCustomDbWithImporterObject(conn, deviceImporterDbs);
			deviceCustomDbDao.insertDeviceCustomDbAudWithImporterObject(conn, deviceImporterDbs, 0);
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
	}
}