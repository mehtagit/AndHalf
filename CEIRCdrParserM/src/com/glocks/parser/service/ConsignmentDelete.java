package com.glocks.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.glocks.dao.ConsignmentMgmtDao;
import com.glocks.dao.DeviceCustomDbDao;
import com.glocks.dao.DeviceDbDao;
import com.glocks.dao.DeviceImporterDbDao;
import com.glocks.dao.SourceTacInactiveInfoDao;
import com.glocks.parser.Rule;
import com.glocks.pojo.DeviceCustomDb;
import com.glocks.pojo.DeviceDb;
import com.glocks.pojo.DeviceImporterDb;

public class ConsignmentDelete {
	static Logger logger = Logger.getLogger(ConsignmentDelete.class);

	public void process(Connection conn, String operator, String subFeature, ArrayList<Rule> rulelist, String txnId, String operator_tag,
			String usertypeName ){

		DeviceDbDao deviceDbDao = new DeviceDbDao();
		DeviceImporterDbDao deviceImporterDbDao = new DeviceImporterDbDao();
		ConsignmentMgmtDao consignmentMgmtDao = new ConsignmentMgmtDao();
		DeviceCustomDbDao deviceCustomDbDao = new DeviceCustomDbDao();
		SourceTacInactiveInfoDao sourceTacInactiveInfoDao = new SourceTacInactiveInfoDao();
		
		try{
			List<DeviceDb> deviceDbs = deviceDbDao.getDeviceDbByTxnId(conn, "", txnId);
			logger.info("deviceDb Values Get" );
//			deviceDbDao.insertDeviceDbAud(conn, deviceDbs);
			deviceDbDao.deleteDevicesFromDeviceDb(conn, txnId);
          logger.info(" deviceDb Values Get" );
			
			List<DeviceImporterDb> deviceImporterDbs = deviceImporterDbDao.getDeviceImporterDbByTxnId(conn, txnId);
//			logger.info("deviceImporterDbs" + deviceImporterDbs);
//			deviceImporterDbDao.insertDeviceImporterDbAud(conn, deviceImporterDbs);
			deviceImporterDbDao.deleteDevicesFromDeviceImporterDb(conn, txnId);
			
			List<DeviceCustomDb> deviceCustomDbs = deviceCustomDbDao.getDeviceCustomDbByTxnId(conn, txnId);
//			logger.info("deviceCustomDbs" + deviceCustomDbs);
//			deviceCustomDbDao.insertDeviceCustomDbAud(conn, deviceCustomDbs);
			deviceCustomDbDao.deleteDevicesFromDeviceCustomDb(conn, txnId);
			
			sourceTacInactiveInfoDao.deleteFromSourceTacInactiveInfo(conn, txnId);
			
			consignmentMgmtDao.updateConsignmentMgmtDeleteFlag(conn, txnId, 2);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}
}



