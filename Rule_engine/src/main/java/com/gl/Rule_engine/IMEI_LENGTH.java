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
class IMEI_LENGTH {

    static final Logger logger = Logger.getLogger(IMEI_LENGTH.class);

    static String executeRule(String[] args, Connection conn) {
        String res = "No";
        logger.info("IMEI_LENGTH executeRule ....." + args[3]);

        if (args[9].trim().equalsIgnoreCase("IMEI") || args[10].trim().equalsIgnoreCase("GSM")) {
            if ((args[3].length() == 15 || args[3].length() == 16) && (args[3].matches("^[0-9]+$"))) {
                res = "Yes";
            } else {
                res = "No";
            }
        } else {

            if (args[9].trim().equalsIgnoreCase("MEID")) {
                if ((args[3].length() == 15 || args[3].length() == 16) && (args[3].matches("^[0-9A-F]+$"))) {
                    res = "Yes";
                } else {
                    res = "No";
                }
            } else if (args[9].trim().equalsIgnoreCase("ESN")) {
                switch (args[3].length()) {
                    case 8:
                        if (args[3].matches("^[0-9A-F]+$")) {
                            res = "Yes";
                        } else {
                            res = "No";
                        }
                        break;
                    case 11:
                        if (args[3].matches("^[0-9]+$")) {
                            res = "Yes";
                        } else {
                            res = "No";
                        }
                        break;
                    default:
                        res = "No";
                        break;
                }
            } else {   ///cdma
                switch (args[3].length()) {
                    case 8:
                        if (args[3].matches("^[0-9A-F]+$")) {
                            res = "Yes";
                        } else {
                            res = "No";
                        }
                        break;

                    case 15:
                        if (args[3].matches("^[0-9A-F]+$")) {
                            res = "Yes";
                        } else {
                            res = "No";
                        }
                        break;

                    case 16:
                        if (args[3].matches("^[0-9A-F]+$")) {
                            res = "Yes";
                        } else {
                            res = "No";
                        }
                        break;

                    case 11:
                        if (args[3].matches("^[0-9]+$")) {
                            res = "Yes";
                        } else {
                            res = "No";
                        }
                        break;
                    default:
                        res = "No";
                        break;
                }

            }

        }

        try {
            // cdma  meid/esn  ..      gsm imei

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

                String fileString = args[15] + " , Error Description : IMEI/ESN/MEID is not as per specifications ";
                fileErrorLines.add(fileString);
            }
            break;
            case "Block": {
                logger.info("Action is Block");
            }
            break;
            case "Report": {
                logger.info("Action is Report");

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


//
//
//IMEI — numeric, between 0-9…
//MEID - alphanumeric, between 0-9-A-F…
//Both are 15 digit or 16 digits
//If the number is ESN, the length is either 8 or 11. If length is 11, then all value should be between 0-9.
//if length is 8, then value would be between 0-9,A-F
//In case of IMEI, it is called TAC, in case of MEID it is called Manufacturer code.