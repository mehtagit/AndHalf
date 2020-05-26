/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
class TAC_FORMAT {

    static final Logger logger = Logger.getLogger(TAC_FORMAT.class);

    static String executeRule(String[] args, Connection conn) {
        String res = null;
        logger.info("TAC_FORMAT executeRule ....." + args[3]);
        try {
            if ((args[3].length() == 8 && (args[3].matches("^[-\\w.]+")))) {      // args[10].equalsIgnoreCase("GSM") &&
                res = "Yes";
                logger.info("TAC_FORMAT   ok ");
            } else {
                res = "No";
                logger.info("TAC_FORMAT   NOT OK ");
            }
        } catch (Exception e) {
            logger.info("Error.." + e);
        }
        return res;
    }

    static String executeAction(String[] args, Connection conn, ArrayList<String> fileErrorLines) {

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
                logger.info("Action is Reject");

                String fileString = args[15] + " , Error Description :  TAC is not as per specifications  ";

                fileErrorLines.add(fileString);
            }
            break;
            case "Block": {
                logger.info("Action is Block");
            }
            break;
            case "Report": {
                logger.info("Action is Report");

//                try {
//                     
//
//                    String actn = "";
//
//                    if (args[11].equalsIgnoreCase("grace")) {
//                        actn = "0";
//                    } else {
//                        actn = "1";
//                    }
//                    logger.info("Action ..." + actn);
//                    Connection  
//                    boolean isOracle = conn.toString().contains("oracle");
//                    String dateFunction = Util.defaultDate(isOracle);
//                    String qry1 = " insert into device_invalid_db (imei ,   failedrule, failedruleid, action, failed_rule_date  ) values  (  '" + args[3] + "'  ,  'IMEI_LENGTH'  , ( select id from rule_engine where name =   'IMEI_LENGTH' ), '" + actn + "' , " + dateFunction + " ) ";
//                    logger.info("" + qry1);
//                    PreparedStatement statement1 = conn.prepareStatement(qry1);
//                    int rowsInserted11 = statement1.executeUpdate();
//                    if (rowsInserted11 > 0) {
//                        logger.info("inserted into device_invalid_db tabl");
//                    }
//                     
//                } catch (Exception e) {
//                    logger.info("Error e " + e);
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
        } catch (Exception e) {
            logger.info(" Error " + e);
            return "FAilure";
        }
    }

}
