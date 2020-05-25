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
public class EXISTS_IN_FOREIGN_DB {

    static final Logger logger = Logger.getLogger(EXIST_IN_CUSTOM_DB.class);

    static String executeRule(String[] args, Connection conn) {
        String res = "";

        try {

            Statement stmt2 = conn.createStatement();
//            if (args[2].equalsIgnoreCase("CDR")) {
//                logger.info("Error Not For CDR");
//            } 
//            else
            String qry = "select count(regularize_device_db.nid) from regularize_device_db inner join end_userdb on end_userdb.nid=regularize_device_db.nid where "
                    + "(first_imei='" + args[3] + "' or second_imei='" + args[3] + "' or third_imei='" + args[3] + "' or fourth_imei='" + args[3] + "') and nationality<>'Cambodian';";

            ResultSet result1 = stmt2.executeQuery(qry);
            logger.info(" Foreign Db " + qry);
            int res2 = 0;
            try {
                while (result1.next()) {
                    res2 = result1.getInt(1);
                }
            } catch (Exception e) {
                logger.info("");
            }
            if (res2 != 0) {
                res = "Yes";
            } else {
                res = "No";
            }

            result1.close();

            stmt2.close();

        } catch (Exception e) {
            logger.info("error.." + e);
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
