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
class EXIST_IN_TAX_PAID_DB {

    static final Logger logger = Logger.getLogger(EXIST_IN_TAX_PAID_DB.class);

    static String executeRule(String[] args, Connection conn) {
        logger.info("EXIST_IN_TAX_PAID_DB executeRule");
        String res = "No";
        try {

            Statement stmt2 = conn.createStatement();
            ResultSet result1 = stmt2.executeQuery("select count(imei_esn_meid) as cnt  from device_custom_db  where imei_esn_meid='" + args[3] + "'  ");
            int res1 = 0;
            try {
                while (result1.next()) {
                    res1 = result1.getInt(1);
                }
            } catch (Exception e) {
                logger.info("Error" + e);
            }
            if (res1 != 0) {
                logger.info("Yes");
                res = "Yes";
            } else {
                res = "No";
            }
            result1.close();
             stmt2.close();

        } catch (Exception e) {
            logger.info("" + e);
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

//    static String executeAction(String[] args, Connection conn ,ArrayList<String> fileErrorLines) {
//        logger.info("exist_in_tax_paid executeAction ");
//        String res = "Success";
//        try {
//            if (args[2].equalsIgnoreCase("CDR")) {
//                 
//               
//                if (db_type.equalsIgnoreCase("oracle")) {
//                    className = classNameO;
//                    jdbcUrl = jdbcUrlO;
//                } else {
//                    className = classNameM;
//                    jdbcUrl = jdbcUrlM;
//                }
//
//                 
//                Connection  
//                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");   //
//                Calendar cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 0);
//                String date = dateFormat1.format(cal.getTime());
//
//                String historyIns = " update device_usage_db set failed_rule_date = '" + date + "'   ,failed_rule_id = (select id from rule_engine where name = 'EXIST_IN_TAX_PAID_DB'),  failed_rule_name ='EXIST_IN_TAX_PAID_DB' , action = 'USER' ";
//                logger.info(" is " + historyIns);
//                PreparedStatement statementN = conn.prepareStatement(historyIns);
//                int rowsInserted1 = statementN.executeUpdate();
//                if (rowsInserted1 > 0) {
//                    logger.info("device_usage_db updated");
//                }
//                 
//            } else {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("fileName", args[14]);
//                String fileString =args[15]  + " ,Error Occured :IMEI/ESN/MEID is already present in the system ";
//                map.put("fileString", fileString);
//                  fileErrorLines.add(fileString);
//            }
//
//        } catch (Exception e) {
//            logger.info("" + e);
//            res = "Error";
//        }
//        return res;
//
//    }
}

//else {
//                Statement stmt3 = conn.createStatement();
//                ResultSet result3 = stmt3.executeQuery("select count(*) as cnt  from device_db  where imei_esn_meid='" + args[3] + "' "
//                        + "  and manufacturer_device_status = 'approved' ");
//
//                logger.info(" Query ... select count(*) as cnt  from device_db  where imei_esn_meid='" + args[3] + "' "
//                        + "  and manufacturer_device_status = 'approved' ");
//
//                int res3 = 0;
//                try {
//                    while (result3.next()) {
//                        res3 = result3.getInt(1);
//                    }
//                } catch (Exception e) {
//                    logger.info("Error1 " + e);
//                }
//                if (res3 != 0) {
//                    logger.info("Yes");
//                    res = "Yes";
//                } else {
//                    Statement stmt4 = conn.createStatement();
//                    ResultSet result4 = stmt4.executeQuery(" select  tax_paid_status from  regularize_device_db  where first_imei= '" + args[3] + "' or second_imei  = '" + args[3] + "' or fourth_imei= '" + args[3] + "' or third_imei = '" + args[3] + "'  ");
//
//                    logger.info("Qry 3 ...select  tax_paid_status from  regularize_device_db  where first_imei= '" + args[3] + "' or second_imei  = '" + args[3] + "' or fourth_imei= '" + args[3] + "' or third_imei = '" + args[3] + "'  ");
//                    int res4 = 9;
//                    try {
//                        while (result4.next()) {
//                            res4 = result4.getInt(1);
//                        }
//                    } catch (Exception e) {
//                        logger.info("");
//                    }
//                    if (res4 == 0) {
//                        logger.info("Yes");
//                        res = "Yes";
//
//                    } else {
//                        logger.info("No");
//                        res = "No";
//                    }
//
//                }
//                 
//            }   whoch state in regularize_device_db update
