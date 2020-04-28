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

import com.functionapps.pojo.DeviceDb;
import com.functionapps.util.DateUtil;
import com.functionapps.util.Util;

public class DeviceDbDao {
	static Logger logger = Logger.getLogger(DeviceDbDao.class);
	static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";

	public List<DeviceDb> getDeviceDbByTxnId(Connection conn, String managementDb, String txnId) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;

		List<DeviceDb> deviceDbs = new LinkedList<>();
		try{
			query = "select id, created_on, modified_on, manufature_date, device_type, device_id_type, "
					+ "multiple_sim_status, sn_of_device, imei_esn_meid, "
					//+ "TO_DATE(DEVICE_LAUNCH_DATE, 'DD-MM-YYYY') as launch_date, device_status, device_action,"
					+ "DEVICE_LAUNCH_DATE as launch_date, device_status, device_action,"
					+ "tac, period, txn_id, state from device_db where txn_id='" + txnId + "'";

			System.out.println("Select Query on device_db ["+query+"]");
			logger.info("Select Query on device_db ["+query+"]");
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(query);

			while(rs.next()){
				System.out.println("Inside while of device_db.");		

				deviceDbs.add(new DeviceDb(rs.getLong("id"), 0, rs.getString("created_on"), rs.getString("modified_on"), 
						rs.getString("manufature_date"), rs.getString("device_type"), rs.getString("device_id_type"), 
						rs.getString("multiple_sim_status"), rs.getString("sn_of_device"), rs.getString("imei_esn_meid"), 
						rs.getString("launch_date"), rs.getString("device_status"), rs.getString("device_action"), 
						rs.getInt("tac"), rs.getString("period"), rs.getString("txn_id"), rs.getInt("state")));

			}

		}
		catch(Exception e){
			logger.info(e.getMessage(), e);
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}

		return deviceDbs;
	}

	public void insertDeviceDbAud(Connection conn, List<DeviceDb> deviceDbs) {
		boolean isOracle = conn.toString().contains("oracle");
		String dateFunction = Util.defaultDate(isOracle);

		PreparedStatement preparedStatement = null;


		String query = "insert into device_db_aud (id, rev, revtype, created_on, modified_on, device_type, device_id_type, "
				+ "multiple_sim_status, sn_of_device, imei_esn_meid, device_launch_date, device_status, device_action, "
				+ "tac, period, txn_id, state) values(";

		if (isOracle) {
			query = query + "DEVICE_DB_AUD_seq.nextVal,";
		}else {
			query = query + (getMaxIdDeviceDbAud(conn) + 1) +",";
		}

		query = query + "?,2," + dateFunction + "," + dateFunction + ",?,?,?,?,?,?,?,?,?,?,?,?)";

		logger.info("Add device_db_aud ["+query+"]");

		try {
			System.out.println("sop2.1");
			preparedStatement = conn.prepareStatement(query);

			for (DeviceDb deviceDb : deviceDbs) {
				preparedStatement.setLong(1, deviceDb.getRev());
				preparedStatement.setString(2, deviceDb.getDeviceType()); 
				preparedStatement.setString(3, deviceDb.getDeviceIdType());
				preparedStatement.setString(4, deviceDb.getMultipleSimStatus());
				preparedStatement.setString(5, deviceDb.getSnOfDevice());
				preparedStatement.setString(6, deviceDb.getImeiEsnMeid());
				preparedStatement.setString(7, deviceDb.getDeviceLaunchDate()); 
				preparedStatement.setString(8, deviceDb.getDeviceStatus());
				preparedStatement.setString(9, deviceDb.getDeviceAction());
				preparedStatement.setInt(10, deviceDb.getTac());
				preparedStatement.setString(11, deviceDb.getPeriod());
				preparedStatement.setString(12, deviceDb.getTxnId()); 
				preparedStatement.setLong(13, deviceDb.getState());

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

	public int deleteDevicesFromDeviceDb(Connection conn, String txnId) {
		String query = "";
		Statement stmt = null;
		int executeStatus = 0;

		query = "delete from device_db where txn_id='" + txnId + "'";	
		logger.info("delete device_db [" + query + "]");
		System.out.println("delete device_db [" + query + "]");

		try {
			stmt = conn.createStatement();
			executeStatus = stmt.executeUpdate(query);
			logger.info("Deleted from device_db successfully.");
			System.out.println("Deleted from device_db successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return executeStatus;
	}

	public Long getMaxIdDeviceDbAud(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		Long max = null;

		try{
			query = "select max(id) as max from device_db_aud";

			logger.info("Query ["+query+"]");
			System.out.println("Query ["+query+"]");
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				max = rs.getLong("max");
			}else {
				max = 0L;
			}

			logger.info("Next Id in device_db_aud[" + max + "]");
			return max;
		}
		catch(Exception e){
			logger.info("Exception in getFeatureMapping"+e);
			e.printStackTrace();
			return 0L;
		}
		finally{
			try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
}
