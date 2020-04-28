package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.functionapps.dao.ConsignmentMgmtDao;
import com.functionapps.dao.DeviceDbDao;
import com.functionapps.dao.DeviceImporterDbDao;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.DeviceDb;
import com.functionapps.pojo.DeviceImporterDb;

public class ConsignmentDelete {
	static Logger logger = Logger.getLogger(ConsignmentDelete.class);

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txnId, String operator_tag ){

		DeviceDbDao deviceDbDao = new DeviceDbDao();
		DeviceImporterDbDao deviceImporterDbDao = new DeviceImporterDbDao();
		ConsignmentMgmtDao consignmentMgmtDao = new ConsignmentMgmtDao();
		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
		
		
		try{
			List<DeviceDb> deviceDbs = deviceDbDao.getDeviceDbByTxnId(conn, "", txnId);
			System.out.println("deviceDbs" + deviceDbs);
			logger.info("deviceDbs" + deviceDbs);
			deviceDbDao.insertDeviceDbAud(conn, deviceDbs);
			deviceDbDao.deleteDevicesFromDeviceDb(conn, txnId);
			
			List<DeviceImporterDb> deviceImporterDbs = deviceImporterDbDao.getDeviceImporterDbByTxnId(conn, "", txnId);
			System.out.println("deviceImporterDbs" + deviceImporterDbs);
			logger.info("deviceImporterDbs" + deviceImporterDbs);
			deviceImporterDbDao.insertDeviceImporterDbAud(conn, deviceImporterDbs);
			
			deviceImporterDbDao.deleteDevicesFromDeviceImporterDb(conn, txnId);
			ceirfunction.updateFeatureFileStatus(conn, txnId, 2, operator, sub_feature);
			
			consignmentMgmtDao.updateDeviceImporterDbAud(conn, txnId, 2);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}
