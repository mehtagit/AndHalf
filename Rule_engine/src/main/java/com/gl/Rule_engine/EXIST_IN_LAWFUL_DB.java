/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedWriter;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class EXIST_IN_LAWFUL_DB {

    static final Logger logger = Logger.getLogger(EXIST_IN_LAWFUL_DB.class);

    public static String executeRule(String[] args, Connection conn) {

        String res = "";
        try {
            int count = 0;
            int count1 = 0;
            Statement stmt = conn.createStatement();

            ResultSet result2 = stmt.executeQuery("select  device_status from device_lawful_db where imei_esn_meid='" + args[3] + "' ");
            logger.debug("Qury ..select device_status from device_lawful_db where imei_esn_meid='" + args[3] + "' ");
            try {
                while (result2.next()) {
                    count1 = result2.getInt(1);
                }
                
            } catch (Exception e) {
                logger.info("E2." + e);
            } 
            
                if (count1 == 10) {
                    res = "YES";
                } else {
                    res = "NO";
                }
            result2.close();
            stmt.close();
        } catch (Exception e) {
            logger.error("E2." + e);
        } 
        return res;
    }

    static String executeAction(String[] args, Connection conn, BufferedWriter bw) {
//        logger.info("LBD executeAction");
        try {
            switch (args[13]) {
                case "Allow": {
                    logger.info("Action is Allow");
                }
                break;
                case "Skip": {
                    logger.info("Action is Skip");
                }
                break;
                case "Reject": {
                   
                    String fileString = args[15] + ",  Error Code : CON_RULE_0002,  Error Discription : IMEI/ESN/MEID  is  already marked as Stolen" ;
                    bw.write(fileString);
                    bw.newLine();

                }
                break;
                case "Block": {
                    logger.info("Action is Block");
                }
                break;
                case "Report": {
                    logger.info("Action is Report");

                }
                break;
                case "SYS_REG": {
                    logger.info("Action is SYS_REG");
                }
                break;
                case "USER_REG": {
                    logger.info("Action is USER_REG");
                }
                break;
                default:
                    logger.info(" The Action " + args[13] + "  is Not Defined  ");
            }
            return "Success";
        } catch (Exception e) {
            logger.error("Error.." + e);
        }
        return "Failure";

    }

}

//            String url = System.getenv("ceir_db_url");
//            String username = System.getenv("ceir_db_username");
//            String password = System.getenv("ceir_db_password");
//            String db_name = System.getenv("ceir_db_dbName");
//            String db_type = System.getenv("ceir_db_dbType");
//            String jdbcUrlO = "jdbc:oracle:thin:@" + url + "/" + db_name;
//            String classNameO = "oracle.jdbc.driver.OracleDriver";
//            String jdbcUrlM = "jdbc:mysql://" + url + "/" + db_name;
//            String classNameM = "com.mysql.cj.jdbc.Driver";
//
//             
//           
//            if (db_type.equalsIgnoreCase("oracle")) {
//                className = classNameO;
//                jdbcUrl = jdbcUrlO;
//            } else {
//                className = classNameM;
//                jdbcUrl = jdbcUrlM;
//            }
//
//             
//            if (args[2].equalsIgnoreCase("CDR")) {
//                logger.info(" LBD exACTION CDR");
//                Connection  
//                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");   //
//                Calendar cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 0);
//                String date = dateFormat1.format(cal.getTime());
//                String counted = " select count(*) from  device_usage_db   where  imei =   '" + args[3] + "'    ";
//                int count = 0;
//                Statement stmtc = conn.createStatement();
//                ResultSet resultc = stmtc.executeQuery(counted);
//                try {
//                    while (resultc.next()) {
//                        count = resultc.getInt(1);
//                    }
//                } catch (Exception e) {
//                    logger.info("E2." + e);
//                }
//                String historyIns = "";
//                if (count == 0) {
//                    historyIns = " insert into device_usage_db  (   imei ,created_on, failed_rule_id  , failed_rule_name , msisdn  ,action  ) "
//                            + "values  ( '" + args[3] + "' , '" + date + "' ,(select id from rule_engine where name =  '" + args[0] + "' )    , '" + args[0] + "'  , '" + args[12] + "' , '" + args[13] + "' ) ";
//
//                } else {
//                    historyIns = "   update device_usage_db set imei = '" + args[3] + "'  ,created_on =  '" + date + "', failed_rule_id = (select id from rule_engine where name =  '" + args[0] + "' )  , failed_rule_name  = '" + args[0] + "', msisdn = '" + args[12] + "' ,action = '" + args[13] + "'   where  imei = '" + args[3] + "'    ";
//
//                }
//
//                PreparedStatement statementN = conn.prepareStatement(historyIns);
//                logger.info("Qury.." + historyIns);
//                int rowsInserted1 = statementN.executeUpdate();
//                if (rowsInserted1 > 0) {
//                    logger.info("inserted/updated in device_usage_db ");
//                }
//
//                String stln = " insert into stolen_track_db (created_on, imei_esn_meid ,device_id_type,file_name,operator_id,record_date,operator_name,device_status    ) values "
//                logger.info("qury2 .." + stln);
//                PreparedStatement statementq = conn.prepareStatement(stln);
//                int rowsInserted3 = statementq.executeUpdate();
//                if (rowsInserted3 > 0) {
//                    logger.info("inserted in stolen_track_db ");
//                }
//                 
//
//            } else
