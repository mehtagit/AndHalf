package com.functionapps.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.functionapps.pojo.DeviceImporterDb;

public class DeviceImporterDbDao {
	static Logger logger = Logger.getLogger(DeviceImporterDbDao.class);
	
	public List<DeviceImporterDb> getDeviceImporterDbByTxnId(Connection conn, String managementDb, String txnId) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;

		DeviceImporterDb deviceImporterDb = null;
		List<DeviceImporterDb> deviceImporterDbs = new LinkedList<>();
		try{
			query = "select * from device_importer_db where txn_id='" + txnId + "'";
			logger.info("Query to get File Details ["+query+"]");
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
		String query = "";
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();

			for (DeviceImporterDb deviceImporterDb : deviceImporterDbs) {
				query = "insert into consignment_mgmt_aud (rev, revtype, created_on, device_action, device_id_type, "
						+ "device_launch_date, device_status," 
						+ "device_type, imei_esn_meid, importer_date, importer_device_status, importer_txn_id,"
						+ "manufature_date, modified_on, multiple_sim_status, period," 
						+ "sn_of_device, device_block_status, local_date, previous_device_status," 
						+ "txn_id, user_id, device_state, feature_id"
						+ ") values("
						+ deviceImporterDb.getId() + "," + 2 + ",'" + deviceImporterDb.getCreatedOn() 
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

				System.out.println("Add device_importer_aud [" + query + " ]");
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
