/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

import java.sql.Connection;
import java.io.BufferedWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class EXIST_IN_BLACKLIST_DB {

    static final Logger logger = Logger.getLogger(EXIST_IN_BLACKLIST_DB.class);

     

    static String executeRule(String[] args, Connection conn) {
        String res = "";
        logger.debug("EXIST_IN_BLACKLIST_DB executeRule");

        try {

            Statement stmt2 = conn.createStatement();
            {
                String qur = " select sum(countd) from   (select count(imei) as countd from black_list  where imei  =   '" + args[3] + "'      union all select count(imei) as countd from greylist_db  where imei  =  '" + args[3] + "' )                          ";
                logger.debug("Query:  " + qur);

                ResultSet result1 = stmt2.executeQuery(qur);
                String res2 = "0";
                try {
                    while (result1.next()) {
                        res2 = result1.getString(1);
                    }
                } catch (Exception e) {
                    logger.info("Error " + e);
                }
                if (!res2.equals("0")) {
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

    static String executeAction(String[] args, Connection conn,  BufferedWriter bw) {
     try{   switch (args[13]) {
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
            logger.info(" Error " + e);
            return "Failure";
        }
    }

}
