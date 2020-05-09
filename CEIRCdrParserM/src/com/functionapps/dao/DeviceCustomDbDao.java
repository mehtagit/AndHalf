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

import com.functionapps.pojo.DeviceCustomDb;
import com.functionapps.pojo.DeviceImporterDb;
import com.functionapps.util.DateUtil;
import com.functionapps.util.Util;

public class DeviceCustomDbDao {
	static Logger logger = Logger.getLogger(DeviceCustomDbDao.class);
	static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";

	public DeviceCustomDbDao(){

	}

	public List<DeviceCustomDb> getDeviceCustomDbByTxnId(Connection conn, String txnId) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;

		List<DeviceCustomDb> deviceCustomDbs = new LinkedList<>();
		try{
			query = "select id, created_on, modified_on, device_type, device_id_type, "
					+ "multiple_sim_status, sn_of_device, imei_esn_meid, "
					//+ "TO_DATE(DEVICE_LAUNCH_DATE, 'DD-MM-YYYY') as launch_date, device_status, device_action, "
					+ "DEVICE_LAUNCH_DATE as launch_date, device_status, device_action, "
					+ "user_id, txn_id, device_state, previous_device_status, period,"
					+ "from device_custom_db where txn_id='" + txnId + "'";

			logger.info("Query to get File Details ["+query+"]");
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(query);

			while(rs.next()){
				deviceCustomDbs.add(new DeviceCustomDb(rs.getLong("id"), 0, rs.getString("created_on"),
						rs.getString("modified_on"), rs.getString("device_type"),  rs.getString("device_id_type"),
						rs.getString("multiple_sim_status"),  rs.getString("sn_of_device"), rs.getString("imei_esn_meid"),  
						rs.getString("launch_date"), rs.getString("device_status"),  rs.getString("device_action"),
						rs.getLong("user_id"), rs.getString("txn_id"), rs.getInt("device_state"), 
						rs.getInt("previous_device_status"), rs.getString("period"))); 

			}
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		finally{
			try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}			
		}

		return deviceCustomDbs;
	}
	
	public void insertDeviceCustomDbWithImporterObject(Connection conn, List<DeviceImporterDb> deviceImporterDbs) {
		boolean isOracle = conn.toString().contains("oracle");
		String dateFunction = Util.defaultDate(isOracle);
		
		PreparedStatement preparedStatement = null;
		String query = "insert into device_custom_db (created_on, modified_on, device_action, device_id_type, "
				+ "device_launch_date, device_status, device_type, imei_esn_meid, multiple_sim_status, period," 
				+ "sn_of_device, previous_device_status, txn_id, user_id, device_state"
				+ ") values(" + dateFunction + "," + dateFunction+",?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("Add device_custom_db ["+query+"]");
		System.out.println("Add device_custom_db ["+query+"]");

		try {
			preparedStatement = conn.prepareStatement(query);

			for (DeviceImporterDb deviceImporterDb : deviceImporterDbs) {
				preparedStatement.setString(1, deviceImporterDb.getDeviceAction());
				preparedStatement.setString(2, deviceImporterDb.getDeviceIdType()); 
				preparedStatement.setString(3, deviceImporterDb.getDeviceLaunchDate());
				preparedStatement.setString(4, deviceImporterDb.getDeviceStatus()); 
				preparedStatement.setString(5, deviceImporterDb.getDeviceType()); 
				preparedStatement.setString(6, deviceImporterDb.getImeiEsnMeid());
				preparedStatement.setString(7, deviceImporterDb.getMultipleSimStatus());
				preparedStatement.setString(8, deviceImporterDb.getPeriod()); 
				preparedStatement.setString(9, deviceImporterDb.getSnOfDevice());
				preparedStatement.setLong(10, deviceImporterDb.getPreviousDeviceStatus());
				preparedStatement.setString(11, deviceImporterDb.getTxnId()); 
				preparedStatement.setLong(12, deviceImporterDb.getUserId());
				preparedStatement.setInt(13, deviceImporterDb.getDeviceState());
				
				logger.info("Query"+preparedStatement);
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
	
	public void insertDeviceCustomDbAud(Connection conn, List<DeviceCustomDb> deviceCustomDbs) {
		boolean isOracle = conn.toString().contains("oracle");
		String dateFunction = Util.defaultDate(isOracle);

		String query = "insert into device_importer_db_aud (id,rev, revtype, created_on, device_action, device_id_type, "
				+ "device_launch_date, device_status, device_type, imei_esn_meid, modified_on, multiple_sim_status," 
				+ "sn_of_device, previous_device_status, txn_id, user_id, device_state) values(";

		if (isOracle) {
			query = query + "device_custom_db_aud_seq.nextVal,";
		}else {
			query = query + (getMaxIdDeviceCustomAud(conn) + 1) +",";
		}

		query = query + "?,?," + dateFunction + ",?,?,?,?,?,?," + dateFunction + ",?,?,?,?,?,?)";

		PreparedStatement preparedStatement = null;

		logger.info("Add device_custom_db_aud ["+query+"]");

		try {
			preparedStatement = conn.prepareStatement(query);

			for (DeviceCustomDb deviceCustomDb : deviceCustomDbs) {
				preparedStatement.setLong(1, deviceCustomDb.getRev());
				preparedStatement.setInt(2, 2);
				preparedStatement.setString(3, deviceCustomDb.getDeviceAction());	 
				preparedStatement.setString(4, deviceCustomDb.getDeviceIdType());
				preparedStatement.setString(5, deviceCustomDb.getDeviceLaunchDate());
				preparedStatement.setString(6, deviceCustomDb.getDeviceStatus());
				preparedStatement.setString(7, deviceCustomDb.getDeviceType());
				preparedStatement.setString(8, deviceCustomDb.getImeiEsnMeid()); 
				preparedStatement.setString(9, deviceCustomDb.getMultipleSimStatus());
				preparedStatement.setString(10, deviceCustomDb.getSnOfDevice());
				preparedStatement.setInt(11, deviceCustomDb.getPreviousDeviceStatus()); 
				preparedStatement.setString(12, deviceCustomDb.getTxnId());
				preparedStatement.setLong(13, deviceCustomDb.getUserId());
				preparedStatement.setInt(14, deviceCustomDb.getDeviceState()); 
				
				System.out.println("Query " + preparedStatement);
				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();

			logger.info("Inserted in device_custom_db_aud succesfully.");

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		finally{
			try {
				if(Objects.nonNull(preparedStatement))
					preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	public void insertDeviceCustomDbAudWithImporterObject(Connection conn, List<DeviceImporterDb> deviceImporterDbs, int revType) {
		boolean isOracle = conn.toString().contains("oracle");
		String dateFunction = Util.defaultDate(isOracle);

		String query = "insert into device_custom_db_aud (id,rev, revtype, created_on, device_action, device_id_type, "
				+ "device_launch_date, device_status, device_type, imei_esn_meid, modified_on, multiple_sim_status," 
				+ "sn_of_device, previous_device_status, txn_id, user_id, device_state) values(";

		if (isOracle) {
			query = query + "device_custom_db_aud_seq.nextVal,";
		}else {
			query = query + (getMaxIdDeviceCustomAud(conn) + 1) +",";
		}

		query = query + "?,?," + dateFunction + ",?,?,?,?,?,?," + dateFunction + ",?,?,?,?,?,?)";

		PreparedStatement preparedStatement = null;

		logger.info("Add device_custom_db_aud ["+query+"]");

		try {
			preparedStatement = conn.prepareStatement(query);

			for (DeviceImporterDb deviceCustomDb : deviceImporterDbs) {
				preparedStatement.setLong(1, deviceCustomDb.getRev());
				preparedStatement.setInt(2, revType);
				preparedStatement.setString(3, deviceCustomDb.getDeviceAction());	 
				preparedStatement.setString(4, deviceCustomDb.getDeviceIdType());
				preparedStatement.setString(5, deviceCustomDb.getDeviceLaunchDate());
				preparedStatement.setString(6, deviceCustomDb.getDeviceStatus());
				preparedStatement.setString(7, deviceCustomDb.getDeviceType());
				preparedStatement.setString(8, deviceCustomDb.getImeiEsnMeid()); 
				preparedStatement.setString(9, deviceCustomDb.getMultipleSimStatus());
				preparedStatement.setString(10, deviceCustomDb.getSnOfDevice());
				preparedStatement.setInt(11, deviceCustomDb.getPreviousDeviceStatus()); 
				preparedStatement.setString(12, deviceCustomDb.getTxnId());
				preparedStatement.setLong(13, deviceCustomDb.getUserId());
				preparedStatement.setInt(14, deviceCustomDb.getDeviceState()); 
				
				System.out.println("Query " + preparedStatement);
				preparedStatement.addBatch();
			}

			preparedStatement.executeBatch();

			logger.info("Inserted in device_custom_db_aud succesfully.");

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		finally{
			try {
				if(Objects.nonNull(preparedStatement))
					preparedStatement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	public int deleteDevicesFromDeviceCustomDb(Connection conn, String txnId) {
		String query = "";
		Statement stmt = null;
		int executeStatus = 0;

		query = "delete from device_custom_db where txn_id='" + txnId + "'";	
		logger.info("delete device_custom_db [" + query + "]");

		try {
			stmt = conn.createStatement();
			executeStatus = stmt.executeUpdate(query);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return executeStatus;
	}
	
	public Long getMaxIdDeviceCustomAud(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		Long max = null;

		try{
			query = "select max(id) as max from device_custom_db_aud";

			logger.info("Query ["+query+"]");
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(query);

			if(rs.next()){
				max = rs.getLong("max");
			}else {
				max = 0L;
			}
			
			logger.info("Next Id in device_custom_db_aud[" + max + "]");
			return max;
		}
		catch(Exception e){
			logger.info(e.getMessage(), e);
			return 0L;
		}
		finally{
			try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}			
		}
	}
}
