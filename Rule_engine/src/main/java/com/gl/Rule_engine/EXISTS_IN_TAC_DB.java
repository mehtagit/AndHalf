/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

//import com.example.BasicApplication;
 
import org.apache.log4j.Logger;
import gsmaTac.BasicApplication;
import java.sql.Connection;
import java.io.BufferedWriter;

/**
 *
 * @author user
 */
class EXISTS_IN_TAC_DB {

    static final Logger logger = Logger.getLogger(EXISTS_IN_TAC_DB.class);

    static String executeRule(String[] args, Connection conn) {
        String res = "No";
        logger.info("EXISTS_IN_TAC_DB executeRule");
        try {

            String tac = "";

            if (args[2].equalsIgnoreCase("CDR")) {
                if (args[10].equalsIgnoreCase("GSM")) {
                    tac = args[3].trim().substring(0, 8);
                }
            } else {
                tac = args[3].trim().substring(0, 8);
            }

            logger.info("tac val .." + tac);
            if (tac.equalsIgnoreCase("")) {
                res = "No";
            } else {
                logger.info("GSMA started");
                BasicApplication w = new BasicApplication();
                res = w.gsmaApplication(tac, conn);
            }
        } catch (Exception e) {
            logger.info("errror " + e);
        }

        return res;
    }

    static String executeAction(String[] args, Connection conn,  BufferedWriter bw) {
      try{  switch (args[13]) {
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
                String fileString = args[15] + " , Error Code :CON_RULE_0003 , Error Description :TAC in IMEI is not approved TAC from GSMA  ";
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
            case "NAN": {
                logger.info("Action is NAN");
                String fileString = args[15] + " , Error Code :CON_RULE_000X, Error Description :Something went Wrong while Authorization of TAC .Try after Some Time.   ";
                 bw.write(fileString);
                bw.newLine();
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
            logger.info(" Error " + e);
            return "Failure";
        }
    }

}

//        
//        logger.info("EXISTS_IN_TAC_DB executeAction ");
//        String res = "Success";
//        try {
//            if (args[2].equalsIgnoreCase("CDR")) {
//             
//         
//                
//            }
//            if (args[2].equalsIgnoreCase("consignment")) { 
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("fileName", args[14]);
//                String fileString = args[15] + " , ";
//                map.put("fileString", fileString);
//                   bw.write(fileString);
                
//            }
//
//        } catch (Exception e) {
//            logger.info("" + e);
//            res = "Error";
//        }
//        return res;
//    }
//
//}
//Execute Action
//if feature is CDR
//	
//else
//	create error file.
