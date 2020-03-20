/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.functionapps.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author user
 */
public class ErrorFileGenrator {

    static String url = System.getenv("ceir_db_url");
    static String username = System.getenv("ceir_db_username");
    static String password = System.getenv("ceir_db_password");
    static String db_name = System.getenv("ceir_db_dbName");
    static String db_type = System.getenv("ceir_db_dbType");
    static String jdbcUrlO = "jdbc:oracle:thin:@" + url + "/" + db_name;
    static String classNameO = "oracle.jdbc.driver.OracleDriver";
    static String jdbcUrlM = "jdbc:mysql://" + url + "/" + db_name;
    static String classNameM = "com.mysql.cj.jdbc.Driver";

    public  void gotoErrorFile(String txn_id, String errorString) {
        try {

            String className = null;
            String jdbcUrl = null;
            if (db_type.equalsIgnoreCase("oracle")) {
                className = classNameO;
                jdbcUrl = jdbcUrlO;
            } else {
                className = classNameM;
                jdbcUrl = jdbcUrlM;
            }

            System.out.println("Error File init");

            Class.forName(className);
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = conn.createStatement();
            String qury = "select value from  system_configuration_db  where tag  = 'system_error_filepath' ";
            ResultSet resultmsdn = null;
            resultmsdn = stmt.executeQuery(qury);
            String errorPath = null;
            try {
                while (resultmsdn.next()) {
                    errorPath = resultmsdn.getString(1);
                }
            } catch (Exception e) {
                System.out.println("Error...errorPath." + e);
            }
            conn.close();
            System.out.println("ErrorPath.." + errorPath);

//            String fileString = map.get("fileString");
//            String fileName = map.get("fileName");
//            
            
            System.out.println("fileString is " + errorString);

            try {
                File file = new File(errorPath + txn_id);
                file.mkdir();
                String fileNameInput = errorPath + txn_id + "/" + txn_id + "_error.csv";       // 
                System.out.println("fileNameInput...." + fileNameInput);
                File fout = new File(fileNameInput);
                FileOutputStream fos = new FileOutputStream(fout);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(errorString);
                bw.close();
            } catch (Exception e) {
                System.out.println("exception at File..." + e);
            }
            System.out.println("Success");

        } catch (Exception e) {
            System.out.println("Exception ..." + e);
        }
    }

}
