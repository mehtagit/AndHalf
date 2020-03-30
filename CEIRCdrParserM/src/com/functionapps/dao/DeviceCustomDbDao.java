package com.functionapps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.functionapps.pojo.DeviceImporterDb;
import com.functionapps.util.DateUtil;

public class DeviceCustomDbDao {
	static Logger logger = Logger.getLogger(DeviceCustomDbDao.class);
	static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";

	public DeviceCustomDbDao(){

	}

	public void insertDeviceCustomDb(Connection conn, List<DeviceImporterDb> deviceImporterDbs) {
		
		PreparedStatement preparedStatement = null;
		String query = "insert into device_custom_db (created_on, device_action, device_id_type, "
				+ "device_launch_date, device_status," 
				+ "device_type, imei_esn_meid,"
				+ "manufature_date, modified_on, multiple_sim_status, period," 
				+ "sn_of_device, device_block_status, local_date, previous_device_status," 
				+ "txn_id, user_id, device_state, feature_id"
				+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		System.out.println("Add device_custom_db [" + query + " ]");
		logger.info("Add device_custom_db ["+query+"]");

		try {
			System.out.println("sop2.1");
			preparedStatement = conn.prepareStatement(query);

			for (DeviceImporterDb deviceImporterDb : deviceImporterDbs) {
				preparedStatement.setString(1, DateUtil.nextDate(0, null));
				preparedStatement.setString(2, deviceImporterDb.getDeviceAction());
				preparedStatement.setString(3, deviceImporterDb.getDeviceIdType()); 
				preparedStatement.setDate(4, deviceImporterDb.getDeviceLaunchDate());
				preparedStatement.setString(5, deviceImporterDb.getDeviceStatus()); 
				preparedStatement.setString(6, deviceImporterDb.getDeviceType()); 
				preparedStatement.setString(7, deviceImporterDb.getImeiEsnMeid());
				preparedStatement.setString(8, deviceImporterDb.getManufatureDate());
				preparedStatement.setString(9, DateUtil.nextDate(0, null));
				preparedStatement.setString(10, deviceImporterDb.getMultipleSimStatus());
				preparedStatement.setString(11, deviceImporterDb.getPeriod()); 
				preparedStatement.setString(12, deviceImporterDb.getSnOfDevice());
				preparedStatement.setInt(13, deviceImporterDb.getDeviceState());
				preparedStatement.setString(14, deviceImporterDb.getLocalDate());
				preparedStatement.setLong(15, deviceImporterDb.getPreviousDeviceStatus());
				preparedStatement.setString(16, deviceImporterDb.getTxnId()); 
				preparedStatement.setLong(17, deviceImporterDb.getUserId());
				preparedStatement.setInt(18, deviceImporterDb.getDeviceState());
				preparedStatement.setInt(19, deviceImporterDb.getFeatureId());
				
				System.out.println("Query"+preparedStatement);
				preparedStatement.addBatch();
				
			}

			preparedStatement.executeBatch();

			System.out.println("sop2.2");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				if(Objects.nonNull(preparedStatement))
					preparedStatement.close();
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
		logger.info("delete device_custom_db [" + query + "]");
		System.out.println("delete device_custom_db [" + query + "]");

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
