/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.StolenApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author user
 */
public class RecoverFlow {

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

    public void recoverFlowStartSingle(Map<String, String> map) throws ClassNotFoundException, SQLException {

        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
            className = classNameO;
            jdbcUrl = jdbcUrlO;
        } else {
            className = classNameM;
            jdbcUrl = jdbcUrlM;
        }
        String imei_esn_meid = map.get("imei_esn_meid");
        map.put("imei_esn_meid", imei_esn_meid);
        System.out.println("recover/bulkFlowStartSingle with id  " + imei_esn_meid);
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = conn.createStatement();

        String qryVal = "";
        String typ = "";
        if (map.get("request_type").equals("1")) {
            qryVal = "lawful_device_status";
            typ="Stolen";
        } else {
            qryVal = "operator_device_status";
            typ=" Blocked ";
        }

        String qury = " select "+qryVal+" from device_db where imei_esn_meid  ='" + imei_esn_meid + "' ";
        System.out.println("  " + qury);
        ResultSet resultmsdn = null;
        resultmsdn = stmt.executeQuery(qury);
        int device_status = 0;
        try {
            while (resultmsdn.next()) {
                device_status = resultmsdn.getInt(1);
            }
            if (device_status == 10  || device_status == 12 ) {
                recoveryImei(map);
            } else {
                FileError sv = new FileError();
                map.put("fileString", "Device is not "+typ+" or Device is not found in device_db.");
                System.out.println("File error for Recover");
                sv.gotoErrorFile(map);
            }
        } catch (Exception e) {
            System.out.println("Exeption " + e);
        }
    }

    private void recoveryImei(Map<String, String> map) throws ClassNotFoundException, SQLException {

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
        map.put("txn_id", txn_id);
        System.out.println("recoverIMEI with   id  " + txn_id);
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        String imei_esn_meid = map.get("imei_esn_meid");

        String historyIns = "insert into device_db_history_new select * from device_db where imei_esn_meid  = '" + imei_esn_meid + "'   ";
        System.out.println("historyIns is " + historyIns);

        PreparedStatement statementN = conn.prepareStatement(historyIns);
        int rowsInserted1 = statementN.executeUpdate();
        if (rowsInserted1 > 0) {
            System.out.println("inserted into history");
        }
        String txnId = map.get("txn_id");
     String str1  = "";
        if (map.get("request_type").equals("1")) {
        str1 = "update device_db set lawful_txn_id = '" + txnId + "' , lawful_user_id =(select user_id from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ) , lawful_date = (select dateOfStolen from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ) , lawful_device_status = 11 where imei_esn_meid  = '" + imei_esn_meid + "'  ";
        }
         if (map.get("request_type").equals("3")){
        str1 = "update device_db set operator_txn_id = '" + txnId + "' , operator_user_id =(select user_id from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ) , operator_date = (select dateOfStolen from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ) , operator_device_status = 13    , operator_previous_device_status =12 where imei_esn_meid  = '" + imei_esn_meid + "'  ";
        }
      
        System.out.println("Query is " + str1);
        PreparedStatement statementt = conn.prepareStatement(str1);
        int rowsInserted3 = statementt.executeUpdate();
        if (rowsInserted3 > 0) {
            System.out.println("Query Updated recoverFlowStartSingle for device_db  block");
        }
        System.out.println("Recovery/UnBlocking Complete");
        FileError bb = new FileError ();
        bb.greyListMaintain(map); 
    }
}
