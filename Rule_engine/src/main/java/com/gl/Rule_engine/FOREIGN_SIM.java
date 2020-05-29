/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

import com.gl.utils.Util;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedWriter;
import org.apache.log4j.Logger;

class FOREIGN_SIM {

    static final Logger logger = Logger.getLogger(FOREIGN_SIM.class);

    static String executeRule(String[] args, Connection conn) {
        String res = null;
        logger.info("FOREIGN_SIM executeRule ....." + args[3]);
        try {
            String msisdn = args[12].startsWith("19") ? args[12].substring(2) : args[12];
            if (msisdn.startsWith("855")) {
                res = "Yes";
            } else {
                res = "No";
            }
        } catch (Exception e) {
            logger.info("Error.." + e);
        }
        return res;
    }

    static String executeAction(String[] args, Connection conn,  BufferedWriter bw) {

        try {
            switch (args[13]) {
            case "Allow": {
                String msisdn = args[12].startsWith("19") ? args[12].substring(2) : args[12];
                logger.info("Action is Allow");

                try {

                    boolean isOracle = conn.toString().contains("oracle");
                    String dateFunction = Util.defaultDate(isOracle);

                    ResultSet rs1 = null;
                    ResultSet rs = null;
                    Statement stmt = null;
                    int status = 0;
                    String my_query = "";
                    String query = "select * from foreign_sim_msisdn_db where msisdn='" + msisdn + "' and imei_esn_meid = '" + args[3] + "' ";
                    logger.info("device usage db" + query);
                    stmt = conn.createStatement();
                    rs1 = stmt.executeQuery(query);
                    int count = 0;
                    while (rs1.next()) {
                        status = 1;
                        count = rs1.getInt("msisdn_count");
                    }
                    
                    if (status == 0) {
                        my_query = "insert into foreign_sim_msisdn_db (created_on , updated_on , operator, file_name ,imei_esn_meid , msisdn_count ,msisdn  )"
                                + " values (" + dateFunction + " , " + dateFunction + " , '" + args[8] + "',  '" + args[5] + "',  '" + args[3] + "',  1,  '" + msisdn + "'    ) ";
                    } else {
                        my_query = "update   foreign_sim_msisdn_db set    file_name = '" + args[5] + "' , operator  = '" + args[8] + "' , updated_on = " + dateFunction + "  ,msisdn_count = " + (count + 1) + "     where   msisdn='" + msisdn + "' and imei_esn_meid = '" + args[3] + "'     ";
                    }
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(my_query);
                    rs.close();
                    rs1.close();
                    stmt.close();

                } catch (Exception e) {
                    logger.info("Error e " + e);
                }

            }
            break;
            case "Skip": {
                logger.info("Action is Skip");
            }
            break;
            case "Reject": {
                logger.info("Action is Reject");

                String fileString = args[15] + " , Error Description : Imei  Utilised By Foreign Sim   ";

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
//                        logger.info("inserted into device _invalid_db tabl");
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
            return "Failure";
        }
    }

}
