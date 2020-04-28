package com.functionapps.db;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MySQLConnection {

    public Connection getConnection() {

//    	
//        final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver"; 
//	    final String DB_URL = "jdbc:oracle:thin:@172.24.1.96:1521/dbdmcrac";
//	   final String USER = "CEIRCONFIG";
//	   final String PASS = "CEIRCONFIG";

        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://172.31.22.35:3306/ceirconfig";
        final String USER = "root";
        final String PASS = "root";


        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);
            System.out.println("Connnection created successfully" + conn);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
