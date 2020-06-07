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
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
class SYS_REG {

    static final Logger logger = Logger.getLogger(SYS_REG.class);

    ;
     

    static String executeRule(String[] args, Connection conn) {
//        logger.info(" SYS_REG executeRule ");
        String res = "";
        try {

            Statement stmt2 = conn.createStatement();
            String qury = " select action from device_usage_db  where  imei ='" + args[3] + "'   union  select action from  device_duplicate_db  where  imei =   '" + args[3] + "'  ";

            ResultSet result1 = stmt2.executeQuery(qury);
            logger.info(qury);
            Set<String> hash_Set = new HashSet<String>();
            try {
                while (result1.next()) {
                    hash_Set.add(result1.getString(1));
                }
            } catch (Exception e) {
                logger.error("" + e);
            }
            if (hash_Set.contains("SYS_REG")) {
                logger.debug("Yes");
                res = "Yes";
            } else {
                logger.debug("No");
                res = "no";
            }
             result1.close();
                    stmt2.close();
             
        } catch (Exception e) {
            logger.error("" + e);
        }
        return res;
    }

    static String executeAction(String[] args, Connection conn,  BufferedWriter bw) {
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

                String fileString = args[15] + " , Error Description : IMEI/ESN/MEID  is System Registered ";

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
