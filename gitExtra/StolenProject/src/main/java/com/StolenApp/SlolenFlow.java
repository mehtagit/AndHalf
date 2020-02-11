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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SlolenFlow {

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

    public void stolenFlowStartSingle(Map<String, String> map) throws ClassNotFoundException, SQLException {
        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
            className = classNameO;
            jdbcUrl = jdbcUrlO;
        } else {
            className = classNameM;
            jdbcUrl = jdbcUrlM;
        }
        String id = map.get("id");
        String txn_id = map.get("txn_id");
        System.out.println("stolenFlowStart with   id  " + id);
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = conn.createStatement();
        String qury = "";
        String ty = "";
        if (map.get("request_type").equals("2")) {
            qury = "select imei,imei, imei, imei  from single_imei_details where txn_id ='" + txn_id + "'   ";  // if error occuors  //imei , first_imei,fourth_imei ,third_imei
            ty = "BLOCK";
        }
        if (map.get("request_type").equals("0")) {
            qury = " select imei_esn_meid, model_number, device_brand_name, contact_number from stolen_individual_userdb where stolen_id  =" + id + " ";
            ty = "STOLEN";
        }
        System.out.println(" stolenFlow or Block flow " + ty);
        System.out.println(" squery.. " + qury);
        ResultSet resultmsdn = null;
        resultmsdn = stmt.executeQuery(qury);
        String resultmsdn1 = null;
        String resultmsdn2 = null;
        String resultmsdn3 = null;
        String resultmsdn4 = null;
        try {
            while (resultmsdn.next()) {
                resultmsdn1 = resultmsdn.getString(4);
                resultmsdn2 = resultmsdn.getString(3);
                resultmsdn3 = resultmsdn.getString(2);
                resultmsdn4 = resultmsdn.getString(1);
            }

            if (db_type.equals("oracle")) {
                map.put("imei_esn_meid", resultmsdn1);
                map.put("model_number", resultmsdn2);
                map.put("device_brand_name", resultmsdn3);
                map.put("contact_number", resultmsdn4);
            } else {
                map.put("imei_esn_meid", resultmsdn4);
                map.put("model_number", resultmsdn3);
                map.put("device_brand_name", resultmsdn2);
                map.put("contact_number", resultmsdn1);;
            }

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                System.out.println(" KKKKEEYYYY..." + key + "vvvv" + val);
            }

        } catch (Exception e) {
            System.out.println("Error..getImedn.." + e);
        }

        String valz = map.get("imei_esn_meid");
        System.out.println("getImeiWitisdn<,....." + valz);
        try {
            if (map.get("imei_esn_meid") == null) {
                System.out.println("11111");
            }
        } catch (Exception e) {;
        }

        System.out.println(".............ddddddd......");
        if (map.get("imei_esn_meid") == null) {
            System.out.println("Started.......");
            String imei = getImeiWithMsisdn(map);
            System.out.println("GETTED IMEI is " + imei);
            map.put("imei_esn_meid", imei);
            System.out.println("Going to check_device_info_db ...... ");
            check_device_info_db(map);
        } else {
            System.out.println("Going to check_device_info_db 2222 ");
            check_device_info_db(map);
        }
        conn.close();
    }

    private String getImeiWithMsisdn(Map<String, String> map) throws ClassNotFoundException, SQLException {

        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
            className = classNameO;
            jdbcUrl = jdbcUrlO;
        } else {
            className = classNameM;
            jdbcUrl = jdbcUrlM;
        }

        String imei = "";
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

        String lawful_stolen_usage_db_num_days_qury = " select value from  system_configuration_db  where tag  = 'lawful_stolen_usage_db_num_days'";
        System.out.println(" getImeiMsisdn ,,,lawful_stolen_usage_db_num_days_qury,,, " + lawful_stolen_usage_db_num_days_qury);
        Statement stmt8 = conn.createStatement();
        ResultSet resultDay = stmt8.executeQuery(lawful_stolen_usage_db_num_days_qury);
        int days = 0;
        try {
            while (resultDay.next()) {
                days = resultDay.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error..lawful_stolen_usage_db_num_days_qury.." + e);
        }

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");   //
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days);
        String date = dateFormat1.format(cal.getTime());
        System.out.println("date " + date);

        List lst = new ArrayList();
        List lstGsma = new ArrayList();
        String msisdn = map.get("contact_number");
        String strTacs = "Result......";
        String device_usage_db_qury = " select imei from  device_usage_db  where msisdn = " + msisdn + "  and created_on > '" + date + "' ";
        System.out.println(" ImeiWithMsisdn ,,,device_usage_db,,, " + device_usage_db_qury);
        Statement stmt = conn.createStatement();
        ResultSet resultmsdn = stmt.executeQuery(device_usage_db_qury);
        try {
            while (resultmsdn.next()) {
                lst.add(resultmsdn.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error..getErrorMsisdn.." + e);
        }
        System.out.println("List size ..." + lst.size());
        String device_duplicate_db_qury = " select imei from  device_duplicate_db  where msisdn = " + msisdn + "  and created_on > '" + date + "' ";
        System.out.println(" getImeiSMsisdn ,,,device_duplicate_db,,, " + device_duplicate_db_qury);
        ResultSet result2 = stmt.executeQuery(device_duplicate_db_qury);
        try {
            while (result2.next()) {
                lst.add(result2.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error..getImeithMsisdn   ,,,,  device_duplicate_db.." + e);
        }
        System.out.println("list SIZE" + lst.size());
        if (lst.isEmpty()) {
            System.out.println("Empty List");
            FileError ef = new FileError();
            String fileString = "No device Found in device_duplicate_db and device_usage_db  with  msisdn = " + msisdn + " and  Use date after " + date + "";
            map.put("fileString", fileString);
            ef.gotoErrorFile(map);
        } else {
            System.out.println("NOT Empty List");
            if (lst.size() == 1) {
                imei = (String) lst.get(0);
            } else {

                int column = 1;
                String device_brand_name = map.get("device_brand_name");
                String model_number = map.get("model_number");
                System.out.println("device_brand_name*************" + device_brand_name);
                System.out.println("model_number*************" + model_number);
                int coustion = 0;
                int resultsiz = 0;
                for (int ind = 0; ind < lst.size(); ind++) {
                    String gsma_tac_qury = " select    band_name , model_name  from  gsma_tac_db  where device_id = " + lst.get(ind).toString().substring(0, 8) + "  ";
                    System.out.println(" Query ,,,,,, " + gsma_tac_qury);
                    ResultSet result3 = stmt.executeQuery(gsma_tac_qury);
                    try {
                        while (result3.next()) {
                            resultsiz++;
                            System.out.println("Result Startes");
                            if (db_type.equals("oracle")) {
                                strTacs += column + "> Brand Name :" + result3.getString(2) + ",Model Name : " + result3.getString(1) + ",,,,,";

                                if (model_number.equals(result3.getString(1)) && device_brand_name.equals(result3.getString(2))) {
                                    lstGsma.add(lst.get(ind).toString());
                                    coustion++;
                                }
                            } else {
                                strTacs += column + "> Brand Name :" + result3.getString(1) + ",Model Name : " + result3.getString(2) + ",,,,,";

                                if (model_number.equals(result3.getString(2)) && device_brand_name.equals(result3.getString(1))) {
                                    lstGsma.add(lst.get(ind).toString());
                                    coustion++;
                                }
                            }
                        }
                        column++;
                    } catch (Exception e) {
                        System.out.println("Error..gmeiWithMsisdn.." + e);
                    }
                }
                conn.close();
                System.out.println(" resultsiz listZise" + resultsiz);
                System.out.println(" column of  listZise" + column);

                if (coustion > 1) {
                    System.out.println("  in gsma_tac_db ,,  2 or more values  exists  ");
                }
                if (lstGsma.size() == 1) {
                    imei = (String) lstGsma.get(0);
                } else {
                    System.out.println(" List Size  in Gsma_tac_db is not valid");
                    FileError ef = new FileError();
                    String fileString = strTacs + "...... NO SIMILAR  Model And Brand Name  FOUND IN Gsma_tac_Db SCHEMA ";
                    map.put("fileString", fileString);
                    ef.gotoErrorFile(map);
                }
            }
        }
        return imei;
    }

    void check_device_info_db(Map<String, String> map) throws ClassNotFoundException, SQLException {
        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
            className = classNameO;
            jdbcUrl = jdbcUrlO;
        } else {
            className = classNameM;
            jdbcUrl = jdbcUrlM;
        }
        System.out.println("check_device_info_db Started");
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = conn.createStatement();
        String imei_esn_meid = map.get("imei_esn_meid").toString();

        // select imei_esn_meid from device_db where  imei_esn_meid 
        // if found 
        String device_usase_db_qury = " select  count(imei_esn_meid) as count from device_db  where imei_esn_meid = '" + imei_esn_meid + "'  ";
        System.out.println("check_device_info_db,,,, " + device_usase_db_qury);
        ResultSet result4 = stmt.executeQuery(device_usase_db_qury);
        int imeiPresnt = 0;
        try {
            while (result4.next()) {
                imeiPresnt = result4.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error..check_device_info_db.." + e);
        }
        conn.close();
        if (imeiPresnt != 0) {
            device_db_imei_found(map);
        } else {
            device_db_imei_NOTfound(map);
        }

    }

    void device_db_imei_found(Map<String, String> map) throws ClassNotFoundException, SQLException {
        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
            className = classNameO;
            jdbcUrl = jdbcUrlO;
        } else {
            className = classNameM;
            jdbcUrl = jdbcUrlM;
        }
        System.out.println("device_db_imei_found Started");
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = conn.createStatement();
        String imei_esn_meid = map.get("imei_esn_meid").toString();
        String txnId = map.get("txn_id");

        String device_for_stolen = " select  lawful_device_status  from device_db  where imei_esn_meid = '" + imei_esn_meid + "'  ";
        Statement stmt5 = conn.createStatement();
        ResultSet result5 = stmt5.executeQuery(device_for_stolen);
        int lawful_device_status = 0;
        try {
            while (result5.next()) {
                lawful_device_status = result5.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error..check_device_info_db.." + e);
        }
        if (lawful_device_status == 10) {
            System.out.println("Error generted due to Already Exists in device_db");
            FileError ef = new FileError();
            String fileString = ".Error generted due to Imei Already Exists in device_db  as Stolen having imei is '" + imei_esn_meid + "' ";
            map.put("fileString", fileString);
            ef.gotoErrorFile(map);

        }

        String device_usase_db_qury = " select  count(lawful_user_id) as count ,  from device_db  where imei_esn_meid = '" + imei_esn_meid + "'  ";
        ResultSet result4 = stmt.executeQuery(device_usase_db_qury);
        int lawful_user_id = 0;
        try {
            while (result4.next()) {
                lawful_user_id = result4.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error..check_device_info_db.." + e);
        }

        String str1 = "";
        if (lawful_user_id == 0) {

            System.out.println("lawful_userBLOCK  empty  ");

            str1 = "update device_db set lawful_txn_id = '" + txnId + "' , lawful_user_id =(select user_id from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ) , lawful_date = (select dateOfStolen from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ) , lawful_device_status = 10 where imei_esn_meid  = '" + imei_esn_meid + "'  ";

            System.out.println("Query is " + str1);
            PreparedStatement statementt = conn.prepareStatement(str1);
            int rowsInserted3 = statementt.executeUpdate();
            if (rowsInserted3 > 0) {
                System.out.println("Query Updated for EmptyLawFul block");
            }

        } else {
            System.out.println("lawful_user_id BLOCK FOUND");    /// delete and updateTOHistory and insert new 

            String historyIns = "insert into device_db_history_new select * from device_db where imei_esn_meid  = '" + imei_esn_meid + "'   ";
            System.out.println("historyIns is " + historyIns);
            PreparedStatement statementN = conn.prepareStatement(historyIns);
            int rowsInserted = statementN.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("inserted into history");
            }
            Statement stmt2 = conn.createStatement();
            stmt2.executeUpdate("delete from device_db where imei_esn_meid  = '" + imei_esn_meid + "'  ");
            System.out.println("Record deleted successfully");
            insertNewInDeviceDb(map);
        }
        conn.close();
    }

    public void device_db_imei_NOTfound(Map<String, String> map) throws ClassNotFoundException, SQLException {
        insertNewInDeviceDb(map);

    }

    public void insertNewInDeviceDb(Map<String, String> map) throws ClassNotFoundException, SQLException {

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
        String txnId = map.get("txn_id");
        System.out.println("insertNewInDeviceDb Started");
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

//        String str = "insert into  device_db (lawful_txn_id ,lawful_device_status,     lawful_user_id ,lawful_date, imei_esn_meid)
//values('" + txnId + "' , 10 ,  (select user_id from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ) , ( select dateOfStolen from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ), '" + imei_esn_meid + "'  "
//                + "         )";
        String str = "";
        if (map.get("request_type").equals("0")) {
            str = "      Insert into  device_db (lawful_txn_id ,lawful_device_status,     lawful_user_id ,lawful_date, imei_esn_meid,    distributer_device_status , importer_device_status,previous_distributer_device_status  , previous_importer_device_status,     previous_retailer_device_status ,end_user_device_status , previous_custom_device_status ,previous_end_user_device_status , retailer_device_status , custom_device_status)"
                    + "values('" + txnId + "' , 10 ,  (select user_id from stolenand_recovery_mgmt where txn_id = '" + txnId + "'  ) , ( select dateOfStolen from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ),"
                    + "  '" + imei_esn_meid + "',0,0,0 ,0 ,0 ,0 ,0, 0,0 ,0);";
        }
        if (map.get("request_type").equals("2")) {
            str = "      Insert into  device_db (operator_txn_id ,operator_device_status,     operator_user_id ,operator_date, imei_esn_meid,    distributer_device_status , importer_device_status,previous_distributer_device_status  , previous_importer_device_status,     previous_retailer_device_status ,end_user_device_status , previous_custom_device_status ,previous_end_user_device_status , retailer_device_status , custom_device_status)"
                    + "values('" + txnId + "' , 10 ,  (select user_id from stolenand_recovery_mgmt where txn_id = '" + txnId + "'  ) , ( select dateOfStolen from stolenand_recovery_mgmt where txn_id = '" + txnId + "' ),"
                    + "  '" + imei_esn_meid + "',0,0,0 ,0 ,0 ,0 ,0, 0,0 ,0);";
        }

// operator_previous_device_status  
        System.out.println("Query is " + str);
        PreparedStatement statement = conn.prepareStatement(str);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("exceuted at insertNewInDeviceDb");
        }
        conn.close();
        
        
          System.out.println("Stolen/Blocking Complete");
        FileError bb = new FileError ();
        bb.greyListMaintain(map); 
    }

}
