/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.StolenApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class StolenRecoveryStart {

//    String url = System.getenv("ceir_db_url");
//    String username = System.getenv("ceir_db_username");
//    String password = System.getenv("ceir_db_password");
//    String db_name = System.getenv("ceir_db_dbName");
//    String db_type = System.getenv("ceir_db_dbType");
    String url = "172.31.22.35:3306";
    String username = "root";
    String password = "root";
    String db_name = "ceirconfig";
    String db_type = "mysql";

//    String jdbcUrl = "jdbc:oracle:thin:@" + url + "/" + db_name;     // for mysql change the getString(1), (2), (3) to 321
//    String className = "oracle.jdbc.driver.OracleDriver";
    String jdbcUrlO = "jdbc:oracle:thin:@" + url + "/" + db_name;
    String classNameO = "oracle.jdbc.driver.OracleDriver";

    String jdbcUrlM = "jdbc:mysql://" + url + "/" + db_name;
    String classNameM = "com.mysql.cj.jdbc.Driver";

    public void stolenVsRecover(String txn_id) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        System.out.println("stolenVsRecover start txnId " + txn_id);

        String className = null;
        String jdbcUrl = null;
        if (db_type.equals("oracle")) {
            className = classNameO;
            jdbcUrl = jdbcUrlO;
        } else {
            className = classNameM;
            jdbcUrl = jdbcUrlM;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("txn_id", txn_id);
        Class.forName(className);
        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = conn.createStatement();
        String qury = " select  id,  request_type,  source_type ,file_name ,qty, user_id from stolenand_recovery_mgmt where txn_id  = '" + txn_id + "'  ";
        ResultSet resultmsdn = null;
        resultmsdn = stmt.executeQuery(qury);
        String resultmsdn1 = null;
        String resultmsdn2 = null;
        String resultmsdn3 = null;
        String resultmsdn4 = null;
        String resultmsdn5 = null;
        String resultmsdn6 = null;
        try {
            while (resultmsdn.next()) {
                resultmsdn1 = resultmsdn.getString(1);
                resultmsdn2 = resultmsdn.getString(2);
                resultmsdn3 = resultmsdn.getString(3);
                resultmsdn4 = resultmsdn.getString(4);
                resultmsdn5 = resultmsdn.getString(5);
                resultmsdn6 = resultmsdn.getString(6);
//                map.put("id", resultmsdn.getString(3));
//                map.put("request_type", resultmsdn.getString(2));
//                map.put("source_type", resultmsdn.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error...." + e);
        }

        if (db_type.equals("oracle")) {
            map.put("id", resultmsdn6);
            map.put("request_type", resultmsdn5);
            map.put("source_type", resultmsdn4);
            map.put("file_name", resultmsdn3);
            map.put("qty", resultmsdn2);
            map.put("user_id", resultmsdn1);
        } else {
            map.put("id", resultmsdn1);
            map.put("request_type", resultmsdn2);
            map.put("source_type", resultmsdn3);
            map.put("file_name", resultmsdn4);
            map.put("qty", resultmsdn5);
            map.put("user_id", resultmsdn6);
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            System.out.println("key..." + key + "....valzzzz.." + val);
        }

        String str = "update web_action_db set  state = 1 where txn_id  = '" + txn_id + "'  ";
        PreparedStatement statement = conn.prepareStatement(str);
        statement.executeUpdate();

        if (map.get("request_type").equals("0")    ||   map.get("request_type").equals("2")) {
            System.out.println("welcome to Stolen/Block flow");
            SlolenFlow ss = new SlolenFlow();
            if (map.get("source_type").equals("5")) {
                System.out.println("welcome to Stolen/Block flow Indidual ");
                ss.stolenFlowStartSingle(map);
            }
            if (map.get("source_type").equals("6")) {
                System.out.println("welcome to Stolen/Block flow Bulk");
                String qury1 = "select value from  system_configuration_db  where tag  = 'stolenand_recovery_mgmt_filepath' ";
                Statement stmt1 = conn.createStatement();
                ResultSet resultm1 = stmt1.executeQuery(qury1);
                String fileplath1 = null;
                try {
                    while (resultm1.next()) {
                        fileplath1 = resultm1.getString(1);
                    }
                } catch (Exception e) {
                    System.out.println("Error...FILEPATH." + e);
                }

                String filepath = fileplath1 + "/" + map.get("file_name");

                Path path = Paths.get(filepath);
                long lineCount = Files.lines(path).count();
                System.out.println("lineCount...." + lineCount);
                BufferedReader reader;
                reader = new BufferedReader(new FileReader(filepath));
                reader.readLine();
                String line = reader.readLine();

                System.out.println("quantity " + map.get("qty"));
                if (parseInt(map.get("qty")) == lineCount) {
                    while (line != null) {
                        String[] arrOfFile = line.trim().split(",", 8);
                        String imei = arrOfFile[4];
                        map.put("imei_esn_meid", imei);
                        SlolenFlow sft = new SlolenFlow();
                        sft.check_device_info_db(map);
                        line = reader.readLine();
                    }
                } else {
                    String fileString = " File Name " + map.get("file_name") + ", Number of Records in file are not matched with File Provided";
                    map.put("fileString", fileString);
                    FileError ef = new FileError();
                    ef.gotoErrorFile(map);
                }
                reader.close();
            }
        }

        if (map.get("request_type").equals("1") || map.get("request_type").equals("3")) {
            RecoverFlow ss = new RecoverFlow();
            System.out.println("welcome to Recovery / Unblock flow");
            if (map.get("source_type").equals("4")) {
                System.out.println("welcome to Recovery/Unblock flow Single ");
                Statement stmt1 = conn.createStatement();
                String id = map.get("id");
                System.out.println("recoverFlowInitilise with   id  " + id);
                String qury1 = "";
                if (map.get("request_type").equals("1")) {
                    qury1 = " select imei_esn_meid from stolen_individual_userdb where stolen_id  =" + id + " ";   //, model_number, device_brand_name, contact_number
                } else {   
                    qury1 = "select imei  from single_imei_details where txn_id ='" + txn_id + "' ";
                }
                System.out.println(" Query i... " + qury1);
                ResultSet res = stmt1.executeQuery(qury1);
                String res1 = "0";
                try {
                    while (res.next()) {
                        res1 = res.getString(1);
                    }
                } catch (Exception e) {
                    System.out.println("Error..getImedn.." + e);
                }

                String valz = res1;
                System.out.println("Imei<,....." + valz);
                map.put("imei_esn_meid", valz);
                try {
                    if (valz == "0") {
                        System.out.println("imei Null");
                        FileError sv = new FileError();
                        map.put("fileString", "IMEI which are provided,  not in present Database.");
                        System.out.println("File error for Recover");
                        sv.gotoErrorFile(map);
                    } else {
                        ss.recoverFlowStartSingle(map);
                    }

                } catch (Exception e) {
                }

            }
            if (map.get("source_type").equals("6")) {
                System.out.println("welcome to recovery/ UNBLOCK flow Company");
                String qury1 = "select value from  system_configuration_db  where tag  = 'system_upload_filepath' ";
                Statement stmt1 = conn.createStatement();
                ResultSet resultm1 = stmt1.executeQuery(qury1);
                String fileplath1 = null;
                try {
                    while (resultm1.next()) {
                        fileplath1 = resultm1.getString(1);
                    }
                } catch (Exception e) {
                    System.out.println("Error...FILEPATH." + e);
                }

                String filepath = fileplath1 + "/" + map.get("file_name");

                Path path = Paths.get(filepath);
                long lineCount = Files.lines(path).count();
                System.out.println("lineCount...." + lineCount);
                BufferedReader reader;
                reader = new BufferedReader(new FileReader(filepath));
                reader.readLine();
                String line = reader.readLine();

                System.out.println("quantity " + map.get("qty"));
                if (parseInt(map.get("qty")) == lineCount) {
                    while (line != null) {
                        String[] arrOfFile = line.trim().split(",", 8);
                        String imei = arrOfFile[4];
                        map.put("imei_esn_meid", imei);
                        ss.recoverFlowStartSingle(map);
                        line = reader.readLine();
                    }
                } else {
                    String fileString = " File Name " + map.get("file_name") + ", Quantity = "+ map.get("qty")+" , File Records= "+lineCount+"... Quantity  is  not matched with File  Records Provided";
                    map.put("fileString", fileString);
                    FileError ef = new FileError();
                    ef.gotoErrorFile(map);
                }
                reader.close();
            }
        }

//        if(map.get("request_type").equals("2")){    // block
//             System.out.println("welcome to Block flow");
//            SlolenFlow ss = new SlolenFlow();
//            if (map.get("source_type").equals("5")) {
//                System.out.println("welcome to Stolen flow Indidual ");
//                ss.stolenFlowStartSingle(map);
//            }
//            
//            
//            
//            if (map.get("source_type").equals("6")) {
//                System.out.println("welcome to Stolen flow Bulk");
//                String qury1 = "select value from  system_configuration_db  where tag  = 'stolenand_recovery_mgmt_filepath' ";
//                Statement stmt1 = conn.createStatement();
//                ResultSet resultm1 = stmt1.executeQuery(qury1);
//                String fileplath1 = null;
//                try {
//                    while (resultm1.next()) {
//                        fileplath1 = resultm1.getString(1);
//                    }
//                } catch (Exception e) {
//                    System.out.println("Error...FILEPATH." + e);
//                }
//
//                String filepath = fileplath1 + "/" + map.get("file_name");
//
//                Path path = Paths.get(filepath);
//                long lineCount = Files.lines(path).count();
//                System.out.println("lineCount...." + lineCount);
//                BufferedReader reader;
//                reader = new BufferedReader(new FileReader(filepath));
//                reader.readLine();
//                String line = reader.readLine();
//
//                System.out.println("quantity " + map.get("qty"));
//                if (parseInt(map.get("qty")) == lineCount) {
//                    while (line != null) {
//                        String[] arrOfFile = line.trim().split(",", 8);
//                        String imei = arrOfFile[4];
//                        map.put("imei_esn_meid", imei);
//                        SlolenFlow sft = new SlolenFlow();
//                        sft.check_device_info_db(map);
//                        line = reader.readLine();
//                    }
//                } else {
//                    String fileString = " File Name " + map.get("file_name") + ", Number of Records in file are not matched with File Provided";
//                    map.put("fileString", fileString);
//                    FileError ef = new FileError();
//                    ef.gotoErrorFile(map);
//                }
//                reader.close();
//            }
//                
//        }  
//        if(map.get("request_type").equals("3")){    // unblock
//        
//        }
        conn.close();
        FileError vv = new FileError();
        map.put("stats", "2");
        vv.finalstolenand_recovery_mgmt_web_act(map);
    }

}

//                String qury1 = "select value from  system_configuration_db  where tag  = 'stolenand_recovery_mgmt_filepath' ";
//                ResultSet resultm1 = stmt.executeQuery(qury1);
//                String fileplath1 = null;
//                try {
//                    while (resultm1.next()) {
//                        fileplath1 = resultm1.getString(1);
//                    }
//                } catch (Exception e) {
//                    System.out.println("Error...FILEPATH." + e);
//                }
//
//                String filepath = fileplath1 + "/" + map.get("file_name");
//
//                BufferedReader reader;
//                reader = new BufferedReader(new FileReader(filepath));
//                String line = reader.readLine();
//                reader.readLine();
//                System.out.println("TO be updated created " + line.toString());
//                map.put("lineString", line.toString());
//                conn.close();
//                System.out.println("quantity " + map.get("qty"));
//                System.out.println("Line length " + line.length());
//                if (parseInt(map.get("qty")) != line.length()) {
//                    while (line != null) {
//                        String[] arrOfFile = line.trim().split(",", 8);
//                        String imei = arrOfFile[4];
//                        map.put("imei_esn_meid", imei);
//                        SlolenFlow sft = new SlolenFlow();
//                        sft.check_device_info_db(map);
//                        line = reader.readLine();
//                    }
//                } else {
//                    String fileString = " File Name " + map.get("file_name") + ", Number of Records in file are not matched with File Provided";
//                    map.put("fileString", fileString);
//                    FileError ef = new FileError();
//                    ef.gotoErrorFile(map);
//                }
//                reader.close();

