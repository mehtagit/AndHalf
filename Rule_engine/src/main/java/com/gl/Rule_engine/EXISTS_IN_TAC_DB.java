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
import java.util.ArrayList;

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
                String fileString = args[15] + " , Error Code :CON_RULE_0003 , Error Description :TAC in IMEI is not approved TAC from GSMA  ";
                fileErrorLines.add(fileString);
            }
            break;
            case "Block": {
                logger.info("Action is Block");
            }
            break;
            case "Report": {
                logger.info("Action is Report");

//                     
//                    Connection  
//                    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");   //
//                    Calendar cal = Calendar.getInstance();
//                    cal.add(Calendar.DATE, 0);
//                    String date = dateFormat1.format(cal.getTime());
//
//                    String qry1 = " INSERT into device_invalid_db (imei_esn_meid ,operator_id,operator_name, record_date, rule_name,   device_id_type  ,created_on  ) "
//                            + "values  (  '" + args[3] + "'  ,    '" + args[] + "' , '" + args[] + "' , '" +  + "' , 'EXISTS_IN_TAC_DB'  , '" + args[4] + "'  ,  '" + date + "' ) ";
//                    logger.info(" Query1.( DB TO BE FORMED LATER ,CN HAve problm in schema sctructure). " + qry1);
//                    PreparedStatement statementN = conn.prepareStatement(qry1);
//                    int rowsInserted1 = statementN.executeUpdate();
//                    if (rowsInserted1 > 0) {
//                        logger.info("device_usage_db updated");
//                    }
//                     
//                } catch (Exception e) {
//                    logger.info("erorr " + e);
//                }
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
//                  fileErrorLines.add(fileString);
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
