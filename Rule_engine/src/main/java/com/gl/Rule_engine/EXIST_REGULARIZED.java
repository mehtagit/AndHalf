package com.gl.Rule_engine;

 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
class EXIST_REGULARIZED {

static final Logger logger = Logger.getLogger(EXIST_REGULARIZED.class);

   

    static String executeRule(String[] args, Connection conn ) {
        String str = "";
        logger.info("EXIST_REGULARIZED executeRule ");
//        if (!args[2].equalsIgnoreCase("CDR")) {
//            logger.info("NOT FOR CDR");
//        } else 
        {

             
            try {
              
                 
                Statement stmt2 = conn.createStatement();
                ResultSet result1 = stmt2.executeQuery("select action, msisdn from device_usage_db  where imei_esn_meid='" + args[3] + "' ");
                logger.info("select action, msisdn from device_usage_db  where imei_esn_meid='" + args[3] + "' ");
                 String actn = "";
                String msdn = "";
                try {
                    while (result1.next()) {
                        actn = result1.getString("action");
                        msdn = result1.getString("msisdn");
                    }
                } catch (Exception e) {
                    logger.info("");
                }
                logger.info("actn " + actn + "... msdn.." + msdn);
                if (actn.equalsIgnoreCase("2") || actn.equalsIgnoreCase("0")) {
                    if (msdn.equalsIgnoreCase(args[12])) {
                        str = "No";
                        logger.info("No");
                    } else {
                        str = chckDubplicateDb(args,conn);
                    }
                } else {
                    str = chckDubplicateDb(args ,conn);
                }
               result1.close();
             stmt2.close();
            } catch (Exception e) {
                logger.info("Erroer" + e);
            }
        }
        return str;
    }

    public static String chckDubplicateDb(String[] args, Connection conn ) {
        logger.info("Chcking for Dupblicate");
        String res = "";
         
        try {

             
             
            Statement stmt3 = conn.createStatement();
            ResultSet result3 = stmt3.executeQuery("select action from device_duplicate_db  where imei_esn_meid='" + args[3] + "'    and msisdn = '" + args[12] + "' ");
            logger.info("select action from device_duplicate_db  where imei_esn_meid='" + args[3] + "'    and msisdn = '" + args[12] + "' ");
            String actn3 = "";
            try {
                while (result3.next()) {
                    actn3 = result3.getString(1);
                }
            } catch (Exception e) {
                logger.info("");
            }
            if (actn3.equalsIgnoreCase("2") || actn3.equalsIgnoreCase("0")) {

                logger.info("No");
                res = "No";

            } else {
                logger.info("Yes");
                res = "Yes";
            }
             result3.close();
             stmt3.close();
        } catch (Exception e) {
            logger.info("Error .."+ e);
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
        } catch (Exception e) {
            logger.info(" Error " + e);
            return "FAilure";
        }
    }

}
