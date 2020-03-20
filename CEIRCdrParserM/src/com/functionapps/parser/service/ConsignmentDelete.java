package com.functionapps.parser.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.functionapps.dao.DeviceDbDao;
import com.functionapps.dao.DeviceImporterDbDao;
import com.functionapps.parser.Rule;
import com.functionapps.pojo.DeviceDb;
import com.functionapps.pojo.DeviceImporterDb;

public class ConsignmentDelete {
	static Logger logger = Logger.getLogger(ConsignmentDelete.class);

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txnId, String operator_tag ){

		DeviceDbDao deviceDbDao = new DeviceDbDao();
		DeviceImporterDbDao deviceImporterDbDao = new DeviceImporterDbDao();

		try{
			List<DeviceDb> deviceDbs = deviceDbDao.getDeviceDbByTxnId(conn, "", txnId);
			deviceDbDao.insertDeviceDbAud(conn, deviceDbs);
			deviceDbDao.deleteDevicesFromDeviceDb(conn, txnId);

			List<DeviceImporterDb> deviceImporterDbs = deviceImporterDbDao.getDeviceImporterDbByTxnId(conn, "", txnId);
			deviceImporterDbDao.insertDeviceImporterDbAud(conn, deviceImporterDbs);
			deviceImporterDbDao.deleteDevicesFromDeviceImporterDb(conn, txnId);

			conn.commit();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
