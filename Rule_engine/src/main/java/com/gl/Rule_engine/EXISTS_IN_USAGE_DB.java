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
class EXISTS_IN_USAGE_DB {

static final Logger logger = Logger.getLogger(EXISTS_IN_USAGE_DB.class);
 
    static String executeRule(String[] args, Connection conn ) {
        
        String res = "";
try{
                Statement stmt2 = conn.createStatement();
                ResultSet result1 = stmt2.executeQuery("select count(imei_esn_meid) as cnt  from device_usage_db  where imei_esn_meid='" + args[3] + "' ");
                logger.info("select count(imei_esn_meid) as cnt  from device_usage_db  where imei_esn_meid='" + args[3] + "' ");
                int res1 = 0;
                try {
                    while (result1.next()) {
                        res1 = result1.getInt(1);
                    }
                } catch (Exception e) {
                    logger.info("" + e);
                }
                if (res1 != 0) {
                    logger.info("Yes");
                    res = "Yes";
                    
                } else {
                    logger.info("No");
                    res = "No";
                }
                   result1.close();
             stmt2.close();
            } catch (Exception e) {
                logger.info("" + e);
            }
        
        return res ;
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
                String fileString = args[15] + " , Error Description : IMEI/ESN/MEID is already in use in the network ";
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
//        String res = "Success";
//        try{
//        logger.info("EXISTS_IN_USAGE_DB executeAction");
////        if (args[2].equalsIgnoreCase("CDR")) {
////            logger.info("Error Not for CDR");
////        } else 
//        {
//              Map<String, String> map = new HashMap<String, String>();
//            map.put("fileName", args[14]);
//            String fileString = args[15] + ", Error Code:CON_RULE_0001,TAC in the IMEI/MEID is not a approved TAC from TRC" ;
//            map.put("fileString", fileString);
//              fileErrorLines.add(fileString);
//        }
//        }catch(Exception e){
//        res = "Error";}
//        return res ;
//    }

}
//
//EXISTS_IN_USAGE_DB - Other than CDR - Expected O/P no.
//Execute Rule
//select count(*) as cnt  from device_usage_db where imei ='XXXXXXX';
//if cnt >0 
//then
//	return yes
//else 
//	return no
//
//Execute Action
//	create error file.
