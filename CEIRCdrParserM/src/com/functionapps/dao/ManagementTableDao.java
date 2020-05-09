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

import com.functionapps.pojo.ManagementDb;
import com.functionapps.util.Util;

public class ManagementTableDao {
	static Logger logger = Logger.getLogger(ManagementTableDao.class);
	static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";
	
	public List<ManagementDb> getManagementDbByTxnId(Connection conn, String txnId, String managementTable) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;

		List<ManagementDb> managementDbs = new LinkedList<>();
		try{
			query = "select id, created_on, modified_on, device_type, device_id_type, "
					+ "multiple_sim_status, sn_of_device, imei_esn_meid, "
					//+ "TO_DATE(DEVICE_LAUNCH_DATE, 'DD-MM-YYYY') as launch_date, device_status, device_action, "
					+ "DEVICE_LAUNCH_DATE as launch_date, device_status, device_action, "
					+ "user_id, txn_id, previous_device_status, period "
					+ "from " 
					+ managementTable 
					+ " where txn_id='" + txnId + "'";

			logger.info("Query ["+query+"]");

			
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(query);

			while(rs.next()){


				managementDbs.add(new ManagementDb(rs.getLong("id"), 0, rs.getString("created_on"),
						rs.getString("modified_on"),  rs.getString("device_type"),  rs.getString("device_id_type"),
						rs.getString("multiple_sim_status"),  rs.getString("sn_of_device"), rs.getString("imei_esn_meid"), 
						rs.getString("launch_date"), rs.getString("device_action"), 
						rs.getLong("user_id"), rs.getString("txn_id"), rs.getString("device_status"),
						rs.getInt("previous_device_status"),
						rs.getString("period")) ); 

			}
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			e.printStackTrace();
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

		return managementDbs;
	}

	public int deleteDevicesFromManagementDb(Connection conn, String txnId, String managementTable) {
		String query = "";
		Statement stmt = null;
		int executeStatus = 0;

		query = "delete from " + managementTable + " where txn_id='" + txnId + "'";	
		logger.info("Query ["+query+"]");
		System.out.println("Query ["+query+"]");

		try {
			stmt = conn.createStatement();
			executeStatus = stmt.executeUpdate(query);
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

	public void updateMgmtDeleteFlag(Connection conn, String tableName, String txnId, int deleteFlag) {
		boolean isOracle = conn.toString().contains("oracle");
		String dateFunction = Util.defaultDate(isOracle);

		String query = "update " + tableName + " set delete_flag=? where txn_id=?";
		logger.info("Query [" + query + " ]");
		System.out.println("Query [" + query + " ]");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conn.prepareStatement(query);

			preparedStatement.setInt(1, deleteFlag);
			preparedStatement.setString(2, txnId);

			logger.info("Query " + preparedStatement);
			preparedStatement.execute();

			logger.info("Update delete flag in  "+tableName+" succesfully.");
			System.out.println("Update delete flag in  "+tableName+" succesfully.");
			
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

}