package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.functionapps.dao.ConsignmentMgmtDao;
import com.functionapps.dao.DeviceCustomDbDao;
import com.functionapps.dao.DeviceDbDao;
import com.functionapps.dao.DeviceImporterDbDao;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.DeviceCustomDb;
import com.functionapps.pojo.DeviceDb;
import com.functionapps.pojo.DeviceImporterDb;

public class ConsignmentDelete {
	static Logger logger = Logger.getLogger(ConsignmentDelete.class);

	public void process(Connection conn, String operator, String subFeature, ArrayList<Rule> rulelist, String txnId, String operator_tag,
			String usertypeName ){

		DeviceDbDao deviceDbDao = new DeviceDbDao();
		DeviceImporterDbDao deviceImporterDbDao = new DeviceImporterDbDao();
		ConsignmentMgmtDao consignmentMgmtDao = new ConsignmentMgmtDao();
		DeviceCustomDbDao deviceCustomDbDao = new DeviceCustomDbDao();
		
		try{
			List<DeviceDb> deviceDbs = deviceDbDao.getDeviceDbByTxnId(conn, "", txnId);
			logger.info("deviceDbs" + deviceDbs);
			deviceDbDao.insertDeviceDbAud(conn, deviceDbs);
			deviceDbDao.deleteDevicesFromDeviceDb(conn, txnId);
			
			List<DeviceImporterDb> deviceImporterDbs = deviceImporterDbDao.getDeviceImporterDbByTxnId(conn, txnId);
			logger.info("deviceImporterDbs" + deviceImporterDbs);
			deviceImporterDbDao.insertDeviceImporterDbAud(conn, deviceImporterDbs);
			deviceImporterDbDao.deleteDevicesFromDeviceImporterDb(conn, txnId);
			
			List<DeviceCustomDb> deviceCustomDbs = deviceCustomDbDao.getDeviceCustomDbByTxnId(conn, txnId);
			logger.info("deviceCustomDbs" + deviceCustomDbs);
			deviceCustomDbDao.insertDeviceCustomDbAud(conn, deviceCustomDbs);
			deviceCustomDbDao.deleteDevicesFromDeviceCustomDb(conn, txnId);
			
			consignmentMgmtDao.updateConsignmentMgmtDeleteFlag(conn, txnId, 2);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}