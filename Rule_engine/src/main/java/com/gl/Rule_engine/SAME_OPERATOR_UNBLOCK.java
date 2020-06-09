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
class SAME_OPERATOR_UNBLOCK {

    static final Logger logger = Logger.getLogger(SYS_REG.class);

    static String executeRule(String[] args, Connection conn) {
        String res = "";
        try {
            String opr1 = null;
            String opr2 = null;
            Statement stmt2 = conn.createStatement();
            String qury = " select OPERATOR_TYPE_ID from stolenand_recovery_mgmt where  TXN_ID = (select TXN_ID  from  device_operator_db where IMEI_ESN_MEID = '" + args[3] + "' )";
            ResultSet result1 = stmt2.executeQuery(qury);
            logger.info(qury);
            try {
                while (result1.next()) {
                    opr1 = result1.getString(1);
                }
            } catch (Exception e) {
                logger.error("opr1 " + e);
            }

            qury = " select OPERATOR_TYPE_ID from stolenand_recovery_mgmt where  TXN_ID =  '" + args[14] + "' ";
            result1 = stmt2.executeQuery(qury);
            logger.info(qury);
            try {
                while (result1.next()) {
                    opr2 = result1.getString(1);
                }
            } catch (Exception e) {
                logger.error("opr2 " + e);
            }

            if (opr1.equals(opr2)) {
                res = "Yes";
            } else {
                res = "No";
            }
            result1.close();
            stmt2.close();

        } catch (Exception e) {
            logger.error("" + e);
        }
        return res;
    }

    static String executeAction(String[] args, Connection conn, BufferedWriter bw) {
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

                    String fileString = args[15] + ",Error Code :CON_RULE_0015   , Error Description : Current Operator don't have Permission to UnBlock this  IMEI/ESN/MEID   ";
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
