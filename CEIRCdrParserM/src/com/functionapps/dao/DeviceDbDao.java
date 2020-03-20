package com.functionapps.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.functionapps.pojo.DeviceDb;

public class DeviceDbDao {
	static Logger logger = Logger.getLogger(DeviceDbDao.class);
	
	public List<DeviceDb> getDeviceDbByTxnId(Connection conn, String managementDb, String txnId) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;

		List<DeviceDb> deviceDbs = new LinkedList<>();
		try{
			query = "select * from device_db where txn_id='" + txnId + "'";
			logger.info("Query to get File Details ["+query+"]");
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(query);

			while(rs.next()){
				deviceDbs.add(new DeviceDb(0L, 0, rs.getString("created_on"), rs.getString("modifiedOn"), 
						rs.getString("manufature_date"), rs.getString("device_type"), rs.getString("device_id_type"), 
						rs.getString("multiple_sim_status"), rs.getString("sn_of_device"), rs.getString("imei_esn_meid"), 
						rs.getString("device_launch_date"), rs.getString("deviceStatus"), rs.getString("device_action"), 
						rs.getInt("tac"), rs.getString("period"), rs.getString("txn_id"), rs.getInt("state")));
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

		return deviceDbs;
	}

	public void insertDeviceDbAud(Connection conn, List<DeviceDb> deviceDbs) {
		String query = "";
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();

			for (DeviceDb deviceDb : deviceDbs) {
				query = "insert into device_db_aud (rev, revtype, created_on, modified_on, manufature_date, device_type, "
						+ "device_id_type, "
						+ "multiple_sim_status, sn_of_device, imei_esn_meid, device_launch_date, device_status, "
						+ "device_action, tac, period, txn_id, state"
						+ ") values("
						+ deviceDb.getId() + "," + 2 + ",'" + deviceDb.getCreatedOn() 
						+ "','" + deviceDb.getModifiedOn() + "','" + deviceDb.getManufatureDate() + "','" 
						+ deviceDb.getDeviceType() + "','" + deviceDb.getDeviceIdType() + "','" 
						+ deviceDb.getMultipleSimStatus() + "','" + deviceDb.getSnOfDevice()+ "','" 
						+ deviceDb.getImeiEsnMeid() + "','" + deviceDb.getDeviceLaunchDate() + "','"
						+ deviceDb.getDeviceStatus() + "','" + deviceDb.getDeviceAction() + "','"
						+ deviceDb.getTac() + "','" + deviceDb.getPeriod() + "','"
						+ deviceDb.getTxnId() + "','" + deviceDb.getState() + "','"
						+")";

				System.out.println("Add device_db_aud [" + query + " ]");
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
			conn.commit();
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

	
}
