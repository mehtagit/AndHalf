/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

import java.sql.Connection;
import java.io.BufferedWriter;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class IMEI_NULL {

    static final Logger logger = Logger.getLogger(IMEI_NULL.class);

    static String executeRule(String[] args, Connection conn) {
        String res = "";
        try {
            if ((args[3] == null) || args[3] == "") {
                res = "Yes";
            } else {
                res = "No";
            }
        } catch (Exception e) {
            logger.error("Error.." + e);
        }
        return res;
    }

    static String executeAction(String[] args, Connection conn,  BufferedWriter bw) {
        String res = "Success";
        try {
            {
                String fileString = args[15] + ", Error Description:IMEI/ESN/MEID is missing in the record. ";
                 bw.write(fileString);
                bw.newLine();
            }
        } catch (Exception e) {
            logger.debug("Error e ");
            res = "Error";
        }
        return res;
    }

}
