package com.functionapps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.functionapps.pojo.PolicyBreachNotification;
import com.functionapps.util.Util;

public class PolicyBreachNotificationDao {

    static Logger logger = Logger.getLogger(PolicyBreachNotificationDao.class);
    static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";

    public void insertNotification(Connection conn, PolicyBreachNotification policyBreachNotification) {
        boolean isOracle = conn.toString().contains("oracle");
        String dateFunction = Util.defaultDate(isOracle);

        String query = "insert into policy_breach_notification ( channel_type,CONTACT_NUMBER, IMEI,message,RETRY_COUNT ,created_on, modified_on) values "
                + " (?, ?,?, ? ,0 , " + dateFunction + "," + dateFunction + ")";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query);) {
            preparedStatement.setString(1, policyBreachNotification.getChannelType());
            preparedStatement.setString(2, policyBreachNotification.getContactNumber());
            preparedStatement.setString(3, policyBreachNotification.getImei());
            preparedStatement.setString(4, policyBreachNotification.getMessage());

            logger.info("Query " + preparedStatement);

            preparedStatement.execute();

            logger.info("Inserted in notification succesfully.");

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
