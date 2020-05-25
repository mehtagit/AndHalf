/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
class EXIST_IN_RETAILER_DB {

static final Logger logger = Logger.getLogger(EXIST_IN_RETAILER_DB.class);

    static String executeRule(String[] args, Connection conn ) {
        String res = "No";
        logger.info("EXIST_IN_RETAILER_DB executeRule");
       
        try {        
            Statement stmt2 = conn.createStatement();
             {
                ResultSet result1 = stmt2.executeQuery("select count(imei_esn_meid) from device_retailer_db  where imei_esn_meid='" + args[3] + "' ");
                logger.info("select count(imei_esn_meid) from device_retailer_db  where imei_esn_meid='" + args[3] + "' ");
                String res2 = "0";
                try {
                    while (result1.next()) {
                        res2 = result1.getString(1);
                    }
                } catch (Exception e) {
                    logger.info("");
                }
                if (!res2.equalsIgnoreCase("0")) {
                    res = "Yes";
                } else {
                    res = "No";
                }
                result1.close();
             stmt2.close();
            }
             
        } catch (Exception e) {
            logger.info("error.." + e);
        }
        return res;
    }

      static String executeAction(String[] args, Connection conn ,ArrayList<String> fileErrorLines) {
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
//        String rrst = "Success";
//        try {
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
////            if (args[11].equalsIgnoreCase("grace")) {
////                Connection  
////                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");   //
////                Calendar cal = Calendar.getInstance();
////                cal.add(Calendar.DATE, 0);
////                String date = dateFormat1.format(cal.getTime());
////
////                String historyIns = " insert into device_retailer_db (created_on,device_id ,device_type ,  device_status , imei_esn_meid   ) values  ( '" + date + "',  '" + args[4] + "'    , 10 , '" + args[3] + "' ) ";
////                PreparedStatement statementN = conn.prepareStatement(historyIns);
////                int rowsInserted1 = statementN.executeUpdate();
////                if (rowsInserted1 > 0) {
////                    logger.info("inserted into device_retailer_db for Grace Period");
////                }
//// 
////            } else 
//            {
//
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("fileName", args[14]);
//                String fileString = args[15] +" , Error Occured :IMEI/ESN/MEID is already present in the system";
//                map.put("fileString", fileString);
//                  fileErrorLines.add(fileString);
//                return "success";
//            }
//        } catch (Exception e) {
//            rrst = "Error";
//        }
//        return rrst;
//    }

}
