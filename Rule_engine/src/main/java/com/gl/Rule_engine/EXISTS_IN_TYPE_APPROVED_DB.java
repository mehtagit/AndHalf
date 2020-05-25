/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
class EXISTS_IN_TYPE_APPROVED_DB {

    static final Logger logger = Logger.getLogger(EXISTS_IN_TYPE_APPROVED_DB.class);

    static String executeRule(String[] args, Connection conn) {
        String res = "";
        try {

            
            Statement stmt2 = conn.createStatement();
            ResultSet result1 = stmt2.executeQuery("select count(tac) as cnt  from type_approved_db where tac='" + args[3].substring(0, 8) + "' ");
            logger.info("select count(tac) as cnt  from type_approved_db where tac='" + args[3].substring(0, 8) + "' ");
            int res1 = 0;
            try {
                while (result1.next()) {
                    res1 = result1.getInt(1);
                }
            } catch (Exception e) {
                logger.info("");
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
            logger.info("Error" + e);
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
                String fileString = args[15] + " ,Error Description : TAC in the IMEI/MEID is not a approved TAC from TRC ";
                fileErrorLines.add(fileString);
            }
            break;
            case "Block": {
            }
            break;
            case "Report": {
                try {
                    DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yy");   //
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, 0);
                    String date = dateFormat1.format(cal.getTime());
                    String user_id_qury = "    (select user_id from " + args[2].trim().toLowerCase() + "_mgmt where txn_id = '" + args[14] + "' ) ";
                    String pending_tac_approved_db = " insert into pending_tac_approved_db (created_on,feature_name ,  tac , txn_id, user_id   ,modified_on   ) "
                            + "   values  ( '" + date + "'  , '" + args[2] + "'  ,    '" + args[3].substring(0, 8) + "'  , '" + args[14] + "' , " + user_id_qury + "  ,  '" + date + "'   ) ";
                    logger.info("Qury is " + pending_tac_approved_db);
                    PreparedStatement statementN = conn.prepareStatement(pending_tac_approved_db);
                    int rowsInserted1 = statementN.executeUpdate();
                    if (rowsInserted1 > 0) {
                        logger.info("inserted into pending_tac_approved_db");
                    }
                       
             statementN.close();
                } catch (Exception e) {
                    logger.info("Error" + e);
                }

                String fileString = args[15];
                fileErrorLines.add(fileString);
            }
            break;
            case "SYS_REG": {
            }
            break;
            case "USER_REG": {
            }
            break;
            default:
                logger.info(" The Action " + args[13] + "  is Not Defined  ");

        }

        return "Success";
    }

}
