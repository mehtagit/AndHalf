package com.functionapps.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

import com.functionapps.constants.PropertyReader;

public class MySQLConnection {

    public static PropertyReader propertyReader;

    public Connection getConnection() {
//        if (Objects.isNull(propertyReader)) {
//            propertyReader = new PropertyReader();
//        }

        try {
//			final String JDBC_DRIVER = propertyReader.getPropValue("jdbc_driver").trim();
//			final String DB_URL = propertyReader.getPropValue("db_url").trim();
//			final String USER = propertyReader.getPropValue("username").trim();
//			final String PASS = propertyReader.getPropValue("password").trim();
            final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
            final String DB_URL = "jdbc:oracle:thin:@dmc-prod-db:1521/dmcproddb";
            final String USER = "CRESTELCEIR";
            final String PASS = "CRESTELCEIR";
            System.out.println(""+ JDBC_DRIVER);
            System.out.println(""+ DB_URL);
            System.out.println(""+ USER );
            System.out.println(""+  PASS);
            System.out.println(java.time.LocalDateTime.now());
            Connection conn = null;
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            System.out.println("Connnection created successfully" + conn);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(java.time.LocalDateTime.now());
            System.exit(0);
            return null;
        }
    }
}
