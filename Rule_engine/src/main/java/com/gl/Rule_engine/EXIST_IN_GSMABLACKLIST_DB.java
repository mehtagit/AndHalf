/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gl.Rule_engine;

import org.apache.log4j.Logger;

import com.gl.Rule_engine.BlackList.EncriptonBlacklistService;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class EXIST_IN_GSMABLACKLIST_DB {

    static final Logger logger = Logger.getLogger(EXIST_IN_GSMABLACKLIST_DB.class);

    static String executeRule(String[] args, Connection conn) {
        String rslt = EncriptonBlacklistService.startBlacklistApp(args[3], conn);
        return rslt;
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
            case "NAN": {
                logger.info("Action is NAN");
                String fileString = args[15] + " , Error Code :CON_RULE_000X, Error Description :Something went Wrong while Checking  Status of Imei .Try after Some Time.   ";
                fileErrorLines.add(fileString);
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

//create table t1 (
//    c1 NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
//    c2 VARCHAR2(10)
//    );
