/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
class DUPLICATE_USAGE_CHECK {

    static final Logger logger = Logger.getLogger(DUPLICATE_USAGE_CHECK.class);

    ;
     

    static String executeRule(String[] args, Connection conn) {
        String res = null;
        try {

            logger.info("DUPLICATE_USAGE_CHECK executeRule");
            {

                Statement stmt2 = conn.createStatement();
                ResultSet result1 = stmt2.executeQuery("  select count( imei) as c1  from device_usage_db where imei='" + args[3] + "' ");
                logger.info(" select count( msisdn) as c1  from device_usage_db where imei='" + args[3] + "'");
                int res1 = 0;
                try {
                    while (result1.next()) {
                        res1 = result1.getInt(1);
                    }
                } catch (Exception e) {
                    logger.info("eror " + e);
                }
                logger.info("device_usage_db count:" + res1);
                Statement stmt3 = conn.createStatement();
                logger.info(" select count(msisdn) as c1  from device_duplicate_db where imei='" + args[3] + "' ");
                ResultSet result3 = stmt3.executeQuery("  select count(imei) as c1  from device_duplicate_db where imei='" + args[3] + "' ");
                int res3 = 0;
                try {
                    while (result3.next()) {
                        res3 = result3.getInt(1);
                    }
                } catch (Exception e) {
                    logger.info("errro2 " + e);
                }

                logger.info("device_duplicate_db count:" + res3);
                int ttl = res1 + res3;
                logger.info("Total  count: " + ttl);
                Statement stmt4 = conn.createStatement();
                ResultSet result4 = stmt4.executeQuery("Select  value from system_configuration_db where tag='DUPLICATE_IMEI_USAGE_COUNT'");
                int res4 = 0;
                try {
                    while (result4.next()) {
                        res4 = result4.getInt(1);
                    }
                } catch (Exception e) {
                    logger.info("" + e);
                }
                logger.info("Select  value from system_configuration_db where tag='DUPLICATE_IMEI_USAGE_COUNT'  .... " + res4);
                if (res4 <= ttl) {
                    res = "Yes";
                } else {
                    res = "No";
                }
                 result1.close();
                 result3.close();
                 result4.close();
                stmt2.close();
                stmt3.close();
                stmt4.close();
                
            }
        } catch (Exception e) {
        }
        return res;
    }

    static String executeAction(String[] args, Connection conn, ArrayList<String> fileErrorLines) {
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
                logger.info("Action is Reject");
                String fileString = args[15] + " , Error Description : IMEI/ESN/MEID is already present in the system  ";
                fileErrorLines.add(fileString);

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
    }
}

//     regularize the user (USER_REG).
//
//         
//       
//        if (db_type.equals("oracle")) {
//            className = classNameO;
//            jdbcUrl = jdbcUrlO;
//        } else {
//            className = classNameM;
//            jdbcUrl = jdbcUrlM;
//        }
//        try {
//             
////            if (args[11].equalsIgnoreCase("grace")) 
//            {
//                Connection  
//                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");   //
//                Calendar cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 0);
//                String date = dateFormat1.format(cal.getTime());
//                String historyIns = " insert into device_usage_db (imei, created_on ,action    ) values  (  '" + args[3] + "'    ,  'USER_REG' ) ";
//                PreparedStatement statementN = conn.prepareStatement(historyIns);
//                int rowsInserted1 = statementN.executeUpdate();
//                if (rowsInserted1 > 0) {
//                    logger.info("inserted into device_usage_db");
//                }
//                 
//            }
//            
//            
//            {
//              Map<String, String> map = new HashMap<String, String>();
//            map.put("fileName", args[14]);
//          String fileString =args[15]  + " ,Error Occured :IMEI/ESN/MEID is already present in the system ";
//          map.put("fileString", fileString);
//              fileErrorLines.add(fileString);
//        }
//            
//            
//            
//        } catch (Exception e) {
//            logger.info("Errror" + e);
//        }
//
//
// Field                      | Type         | Null | Key | Default | Extra |
//+----------------------------+--------------+------+-----+---------+-------+
//| imei                       | bigint(20)   | NO   | PRI | NULL    |       |
//| created_on                 | datetime     | YES  |     | NULL    |       |
//| duplicate_count            | int(11)      | NO   |     | NULL    |       |
//| foregin_rule               | bit(1)       | NO   |     | NULL    |       |
//| global_blacklist           | bit(1)       | NO   |     | NULL    |       |
//| lastp_policy_breached      | int(11)      | NO   |     | NULL    |       |
//| lastp_policy_breached_date | datetime     | YES  |     | NULL    |       |
//| mobile_operator            | varchar(255) | YES  |     | NULL    |       |
//| pending                    | bit(1)       | NO   |     | NULL    |       |
//| period                     | varchar(30)  | YES  |     | NULL    |       |
//| remarks                    | varchar(255) | YES  |     | NULL    |       |
//| tax_paid                   | bit(1)       | NO   |     | NULL    |       |
//| valid_import               | bit(1)       | NO   |     | NULL    |       |
//| action                     | varchar(255) | YES  |     | NULL    |       |
//| failed_rule_id             | varchar(255) | YES  |     | NULL    |       |
//| failed_rule_name           | varchar(255) | YES  |     | NULL    |       |
//| imei_status                | int(11)      | YES  |     | NULL    |       |
//| imsi                       | bigint(20)   | YES  |     | NULL    |       |
//| mobile_operator_id         | bigint(20)   | YES  |     | NULL    |       |
//| msisdn                     | bigint(20)   | YES  |     | NULL    |       |
//| updated_on                 | datetime     | YES  |     | NULL    |       |
//| record_type                | smallint(6)  | YES  |     | NULL    |       |
//| system_type                | varchar(100) | YES  |     | NULL    |       |
//| create_filename            | varchar(50)  | YES  |     | NULL    |       |
//| update_filename            | varchar(50)  | YES  |     | NULL    |       |
//| failed_rule_date           | datetime     | YES  |     | NULL    |       |
//| tac                        | varchar(10)  | NO   |     | NULL    |       |
//+----------------------------+--------------+------+-----+---------+---
