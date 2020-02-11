/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.StolenApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author user
 */
public class FileError {

//    String url = System.getenv("ceir_db_url");
//    String username = System.getenv("ceir_db_username");
//    String password = System.getenv("ceir_db_password");
//    String db_name = System.getenv("ceir_db_dbName");
//    String db_type = System.getenv("ceir_db_dbType");
//    String jdbcUrlO = "jdbc:oracle:thin:@" + url + "/" + db_name;
//    String classNameO = "oracle.jdbc.driver.OracleDriver";
//
//    String jdbcUrlM = "jdbc:mysql://" + url + "/" + db_name;
//    String classNameM = "com.mysql.cj.jdbc.Driver";
    String url = "172.31.22.35:3306";
    String username = "root";
    String password = "root";
    String db_name = "ceirconfig";
    String db_type = "mysql";

//    String url = System.getenv("ceir_db_url");
//    String username = System.getenv("ceir_db_username");
//    String password = System.getenv("ceir_db_password");
//    String db_name = System.getenv("ceir_db_dbName");
//    String db_type = System.getenv("ceir_db_dbType");
//    String jdbcUrl = "jdbc:oracle:thin:@" + url + "/" + db_name;
//    String className = "oracle.jdbc.driver.OracleDriver";
    String jdbcUrlO = "jdbc:oracle:thin:@" + url + "/" + db_name;
    String classNameO = "oracle.jdbc.driver.OracleDriver";
    String jdbcUrlM = "jdbc:mysql://" + url + "/" + db_name;
    String classNameM = "com.mysql.cj.jdbc.Driver";

    public void gotoErrorFile(Map<String, String> map) throws ClassNotFoundException, SQLException {
        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
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

        String fileString = map.get("fileString");
        String fileName = map.get("txn_id");

        try {
            File file = new File(errorPath + "/" + fileName);
            file.mkdir();
            String fileNameInput = errorPath + "/" + fileName + "/" + errorPath + "/" + fileName + "_error.csv";
            System.out.println("fileNameInput...." + fileNameInput);
            File fout = new File(fileNameInput);
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(fileString);
            bw.close();
        } catch (Exception e) {
            System.out.println("exception at File..." + e);
        }
        System.out.println("  Error File  Success...");
        exitProgram(map);
    }

    public void exitProgram(Map<String, String> map) throws ClassNotFoundException, SQLException {
        map.put("stats", "3");
        finalstolenand_recovery_mgmt_web_act(map);
        System.out.println(" Application Exited  Successfully...");
        System.exit(0);

    }

    void finalstolenand_recovery_mgmt_web_act(Map<String, String> map) throws ClassNotFoundException, SQLException {
        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
            className = classNameO;
            jdbcUrl = jdbcUrlO;
        } else {
            className = classNameM;
            jdbcUrl = jdbcUrlM;
        }
        String txn_id = map.get("txn_id");
        System.out.println("finalstolenand_recovery_mgmt_web_act  start  ");
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

        String str = "update web_action_db set  state = " + map.get("stats") + " where txn_id  = '" + txn_id + "'  ";
        System.out.println("Query  1 is" + str);
        PreparedStatement statement = conn.prepareStatement(str);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Success at web_action_db");
        }
        System.out.println("started   new at stolenand_recovery_mgmt ");
        String str1 = "update stolenand_recovery_mgmt set  file_status = '" + map.get("stats") + "' where txn_id  = '" + txn_id + "'  ";
        System.out.println("Query ..." + str1);
        PreparedStatement statement1 = conn.prepareStatement(str1);
        int rowsInserted1 = statement1.executeUpdate();
        if (rowsInserted1 > 0) {
            System.out.println("Success at stolenand_recovery_mgmt");
        }
        conn.close();
    }

    public void greyListMaintain(Map<String, String> map) throws ClassNotFoundException, SQLException {
        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
            className = classNameO;
            jdbcUrl = jdbcUrlO;
        } else {
            className = classNameM;
            jdbcUrl = jdbcUrlM;
        }

        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");   //
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        String date = dateFormat1.format(cal.getTime());
        System.out.println("date " + date);
        String txn_id = map.get("txn_id");
        String imei_esn_meid = map.get("imei_esn_meid");
        String user_id = map.get("user_id");
        String reason = "";
        String operation = "";

        System.out.println("Going for Greylist");

        if (map.get("request_type").equals("0")) {
            reason = "Stolen";
        }
        if (map.get("request_type").equals("1")) {
            reason = "Recover";
        }
        if (map.get("request_type").equals("2")) {
            reason = "Block";
        }

        if (map.get("request_type").equals("3")) {
            reason = "UnBlock";
        }

        if (map.get("request_type").equals("0") || map.get("request_type").equals("2")) {
            String greylistIns = " insert into greylist_db (created_on , imei, user_id , txn_id   ) "
                    + "values('" + date + "','" + imei_esn_meid + "' ,'" + user_id + "', '" + txn_id + "' )    ";
            System.out.println("greylistIns Inssert is " + greylistIns);
            PreparedStatement statementN = conn.prepareStatement(greylistIns);
            int rowsInserted = statementN.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("inserted into greylist as Stolen");
            }
            operation = "0";
        }
        if (map.get("request_type").equals("1") || map.get("request_type").equals("3")) {
            Statement stmt2 = conn.createStatement();
            stmt2.executeUpdate("delete from greylist_db where imei  = '" + imei_esn_meid + "'  ");
            System.out.println("Record deleted successfully");
            System.out.println("Removed from greylist as Stolen");
            operation = "1";
        }

        String greylist_history = " insert into greylist_db_history (created_on , imei, user_id , txn_id , reason, operation  ) "
                + "values('" + date + "','" + imei_esn_meid + "' ,'" + user_id + "', '" + txn_id + "', '" + reason + "' ,   '" + operation + "' )    ";
        System.out.println("history Inssert is " + greylist_history);
        PreparedStatement statementN = conn.prepareStatement(greylist_history);
        int rowsInserted = statementN.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("inserted into history");
        }
        conn.close();
    }

}
