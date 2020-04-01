package com.functionapps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.functionapps.pojo.Notification;

public class NotificationDao {
	static Logger logger = Logger.getLogger(NotificationDao.class);
	static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";

	public void insertNotification(Connection conn, Notification notification) {
		String query = "insert into notification (id, channel_type, created_on, feature_id, feature_name, "
				+ "message, modified_on, sub_feature, user_id, feature_txn_id, status"
				+ "retry_count, subject, refer_table, notification_priority, role_type, receiver_user_type" 
				+ ") values (notification_seq.nextVal,sysdate,?,?,?,sysdate,?,?,?,0,0,?,'USERS',5,?,?)";

		System.out.println("Query [" + query + " ]");
		logger.info("Add notification [ " + query + "]");

		try(PreparedStatement preparedStatement = conn.prepareStatement(query);){
			preparedStatement.setString(1, notification.getChannelType());
			preparedStatement.setLong(2, notification.getFeatureId());
			preparedStatement.setString(3, notification.getFeatureName());	 
			preparedStatement.setString(4, notification.getMessage());
			preparedStatement.setString(5, notification.getSubFeature());
			preparedStatement.setLong(6, notification.getUserId());
			preparedStatement.setString(7, notification.getSubject());
			preparedStatement.setString(8, notification.getRoleType());
			preparedStatement.setString(9, notification.getReceiverUserType());

			System.out.println("Query " + preparedStatement);
			preparedStatement.addBatch();


			preparedStatement.execute();

			System.out.println("Inserted in notification succesfully.");

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}