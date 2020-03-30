package com.functionapps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.functionapps.pojo.DeviceImporterDb;
import com.functionapps.util.DateUtil;

public class DeviceImporterDbDao {
	static Logger logger = Logger.getLogger(DeviceImporterDbDao.class);
	static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";

	public List<DeviceImporterDb> getDeviceImporterDbByTxnId(Connection conn, String managementDb, String txnId) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;

		List<DeviceImporterDb> deviceImporterDbs = new LinkedList<>();
		try{
			query = "select * from device_importer_db where txn_id='" + txnId + "'";
			logger.info("Query to get File Details ["+query+"]");
			System.out.println("Query to get File Details ["+query+"]");
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(query);

			while(rs.next()){
				deviceImporterDbs.add(new DeviceImporterDb(0L, 0, rs.getString("created_on"), rs.getString("modified_on"), 
						rs.getString("manufature_date"), rs.getString("device_type"), rs.getString("device_id_type"), 
						rs.getString("multiple_sim_status"), rs.getString("sn_of_device"), rs.getString("imei_esn_meid"), 
						rs.getString("device_launch_date"), rs.getString("device_status"), rs.getString("device_action"), 
						rs.getLong("user_id"), rs.getString("txn_id"), rs.getString("local_date"), rs.getInt("device_state"), 
						rs.getInt("previous_device_status"),rs.getString("period"), rs.getInt("feature_id"))
						);
			}
		}
		catch(Exception e){
			logger.info("Exception in getFeatureMapping"+e);
		}
		finally{
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}

		return deviceImporterDbs;
	}

	public void insertDeviceImporterDbAud(Connection conn, List<DeviceImporterDb> deviceImporterDbs) {
		String query = "insert into device_importer_db_aud (rev, revtype, created_on, device_action, "
				+ "device_id_type, device_launch_date, device_status," 
				+ "device_type, imei_esn_meid,"
				+ "manufature_date, modified_on, multiple_sim_status, period," 
				+ "sn_of_device, device_block_status, local_date, previous_device_status," 
				+ "txn_id, user_id, device_state, feature_id"
				+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conn.prepareStatement(query);

			for (DeviceImporterDb deviceImporterDb : deviceImporterDbs) {
				preparedStatement.setLong(1, deviceImporterDb.getId());
				preparedStatement.setInt(2, 2);
				preparedStatement.setString(3, DateUtil.nextDate(0, null));
				preparedStatement.setString(4, deviceImporterDb.getDeviceAction());	 
				preparedStatement.setString(5, deviceImporterDb.getDeviceIdType());
				preparedStatement.setString(6, deviceImporterDb.getDeviceLaunchDate());
				preparedStatement.setString(7, deviceImporterDb.getDeviceStatus());
				preparedStatement.setString(8, deviceImporterDb.getDeviceType());
				preparedStatement.setString(9, deviceImporterDb.getImeiEsnMeid()); 
				preparedStatement.setString(10, deviceImporterDb.getManufatureDate());
				preparedStatement.setString(11, deviceImporterDb.getModifiedOn());
				preparedStatement.setString(12, deviceImporterDb.getMultipleSimStatus());
				preparedStatement.setString(13, deviceImporterDb.getPeriod());
				preparedStatement.setString(14, deviceImporterDb.getSnOfDevice());
				preparedStatement.setString(15, deviceImporterDb.getDeviceStatus());
				preparedStatement.setString(16, deviceImporterDb.getLocalDate() );
				preparedStatement.setInt(17, deviceImporterDb.getPreviousDeviceStatus()); 
				preparedStatement.setString(18, deviceImporterDb.getTxnId());
				preparedStatement.setLong(19, deviceImporterDb.getUserId());
				preparedStatement.setString(20, deviceImporterDb.getDeviceStatus()); 
				preparedStatement.setInt(21, deviceImporterDb.getFeatureId());

				System.out.println("Query " + preparedStatement);
				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();

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

	public int deleteDevicesFromDeviceImporterDb(Connection conn, String txnId) {
		String query = "";
		Statement stmt = null;
		int executeStatus = 0;

		query = "delete from device_importer_db where txn_id='" + txnId + "'";	
		logger.info("delete device_importer_db ["+query+"]");
		System.out.println("update web action db["+query+"]");

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
