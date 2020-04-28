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
import com.functionapps.util.Util;

public class ConsignmentMgmtDao {
	static Logger logger = Logger.getLogger(ConsignmentMgmtDao.class);
	static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";

	public void updateDeviceImporterDbAud(Connection conn, String txnId, int deleteFlag) {
		boolean isOracle = conn.toString().contains("oracle");
		String dateFunction = Util.defaultDate(isOracle);

		String query = "update consignment_mgmt set delete_flag=? where txn_id=?";
		logger.info("update delete flag in  consignment_mgmt [" + query + " ]");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conn.prepareStatement(query);

			preparedStatement.setInt(1, deleteFlag);
			preparedStatement.setString(2, txnId);

			logger.info("Query " + preparedStatement);
			preparedStatement.execute();

			logger.info("Update delete flag in  consignment_mgmt succesfully.");

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