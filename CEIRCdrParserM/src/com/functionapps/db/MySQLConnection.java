package com.functionapps.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

import com.functionapps.constants.PropertyReader;

import org.apache.log4j.Logger;

public class MySQLConnection {

    Logger logger = Logger.getLogger(MySQLConnection.class);

    public static PropertyReader propertyReader;

    public Connection getConnection() {
        if (Objects.isNull(propertyReader)) {
            propertyReader = new PropertyReader();
        }

        try {
            final String JDBC_DRIVER = propertyReader.getPropValue("jdbc_driver").trim();
            final String DB_URL = propertyReader.getPropValue("db_url").trim();
            final String USER = propertyReader.getPropValue("username").trim();
            final String PASS = propertyReader.getPropValue("password").trim();

             Connection conn = null;
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            logger.info("Connnection created successfully" + conn);
             return conn;
        } catch (Exception e) {
            logger.info("erorr" + e + " : " + java.time.LocalDateTime.now());
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}
 