package com.functionapps.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.functionapps.pojo.DeviceImporterDb;
import com.functionapps.util.DateUtil;

public class DeviceCustomDbDao {
	static Logger logger = Logger.getLogger(DeviceCustomDbDao.class);
	
	public DeviceCustomDbDao(){

	}
	
	public void insertDeviceCustomDb(Connection conn, List<DeviceImporterDb> deviceImporterDbs) {
		String query = "";
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();

			for (DeviceImporterDb deviceImporterDb : deviceImporterDbs) {
				query = "insert into device_custom_db (created_on, device_action, device_id_type, "
						+ "device_launch_date, device_status," 
						+ "device_type, imei_esn_meid, importer_date, importer_device_status, importer_txn_id,"
						+ "manufature_date, modified_on, multiple_sim_status, period," 
						+ "sn_of_device, device_block_status, local_date, previous_device_status," 
						+ "txn_id, user_id, device_state, feature_id"
						+ ") values('"
						+ DateUtil.nextDate(0, null) 
						+ "','" + deviceImporterDb.getDeviceAction() + "','" + deviceImporterDb.getDeviceIdType() + "','" 
						+ deviceImporterDb.getDeviceLaunchDate() + "','" + deviceImporterDb.getDeviceStatus() + "','" 
						+ deviceImporterDb.getDeviceType() + "','" + deviceImporterDb.getImeiEsnMeid() + "','" 
						+ deviceImporterDb.getManufatureDate() + "','" + deviceImporterDb.getModifiedOn() + "','"
						+ deviceImporterDb.getMultipleSimStatus() + "','" + deviceImporterDb.getPeriod() + "','"
						+ deviceImporterDb.getSnOfDevice() + "','" + deviceImporterDb.getDeviceStatus() + "','"
						+ deviceImporterDb.getLocalDate() + "','" + deviceImporterDb.getPreviousDeviceStatus() + "','"
						+ deviceImporterDb.getTxnId() + "'," + deviceImporterDb.getUserId() + ","
						+ deviceImporterDb.getDeviceStatus() + "," + deviceImporterDb.getFeatureId()
						+")";

				System.out.println("Add device_custom_db [" + query + " ]");
				logger.info("Add feature file Details in config DB["+query+"]");
				stmt.addBatch(query);
			}

			stmt.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(Objects.nonNull(stmt))
					stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
	}
	
	public int deleteDevicesFromDeviceCustomDb(Connection conn, String txnId) {
		String query = "";
		Statement stmt = null;
		int executeStatus = 0;

		query = "delete from device_custom_db where txn_id='" + txnId + "'";	
		logger.info("delete device_custom_db ["+query+"]");
		System.out.println("delete device_custom_db ["+query+"]");

		try {
			stmt = conn.createStatement();
			executeStatus = stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return executeStatus;
	}
}
