package com.functionapps.db;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MySQLConnection{

 public Connection getConnection(){
	// JDBC driver name and database URL
	   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
//	   final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
	   //	   final String DB_URL = "jdbc:mysql://127.0.0.1:3306/pview";
//	   final String DB_URL = "jdbc:mysql://localhost:5643/ceir";
//	   final String DB_URL = "jdbc:mysql://localhost:5643/ceir";
	   final String DB_URL = "jdbc:mysql://172.31.22.35:3306/ceirconfig";
//	   final String DB_URL = "jdbc:oracle:thin:@172.24.1.96:1521/dbdmcrac";

	   //  Database credentials
//	   final String USER = "root";
//	   final String PASS = "mysql123";
	   final String USER = "root";
	   final String PASS = "root";

//	   final String USER = "CEIRCONFIG";
//	   final String PASS = "CEIRCONFIG";

	   //final String USER = "mxchange";
//	   final String PASS = "password";
	   //final String PASS = "mx123";
	   Connection conn = null;
	   //System.out.println("Inside get connection.");
	   try{
		   Class.forName (JDBC_DRIVER);
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   conn.setAutoCommit( false );
		   System.out.println("Connnection created successfully"+conn);
		   return conn;
	   }catch(Exception e){
		   e.printStackTrace();
		   return null;
	   }

 	}

}
