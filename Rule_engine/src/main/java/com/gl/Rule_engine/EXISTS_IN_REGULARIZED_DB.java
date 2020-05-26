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
class EXISTS_IN_REGULARIZED_DB {

    static final Logger logger = Logger.getLogger(EXISTS_IN_REGULARIZED_DB.class);

    ;
     

    static String executeRule(String[] args, Connection conn) {
        String str = "No";
        logger.info("EXISTS_IN_REGULARIZED_DB executeRule");


            try {

                Statement stmt2 = conn.createStatement();
                ResultSet result1 = stmt2.executeQuery("select  tax_paid_status from regularize_device_db where first_imei='" + args[3] + "' or second_imei='" + args[3] + "' or third_imei='" + args[3] + "' or fourth_imei='" + args[3] + "' ");
                String res1 = "No";
                try {
                    while (result1.next()) {
                        res1 = result1.getString(1);
                    }
                } catch (Exception e) {
                    logger.info("error  + e" + e);
                }

                if (!res1.equals("0")) {
                    res1 = "Yes";
                } else {
                    res1 = "No";
                }
result1.close();

                stmt2.close();
               
            } catch (Exception e) {
                logger.info("Erroer" + e);
            }
        
        return str;
    }

    static String executeAction(String[] args, Connection conn, ArrayList<String> fileErrorLines) {
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

//        
//        
//        try {
//             
//           
//            if (db_type.equals("oracle")) {
//                className = classNameO;
//                jdbcUrl = jdbcUrlO;
//            } else {
//                className = classNameM;
//                jdbcUrl = jdbcUrlM;
//            }
//             
//            Connection  
//            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");   //
//            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.DATE, 0);
//            String date = dateFormat1.format(cal.getTime());
//
//            String historyIns = " update device_usage_db set failed_rule_date =  '" + date + "'  ,   action ='User' , failed_rule_id = (select id from rule_engine where name = 'EXISTS_IN_REGULARIZED_DB')  , failed_rule_name = 'EXISTS_IN_REGULARIZED_DB' ) ";
//            PreparedStatement statementN = conn.prepareStatement(historyIns);
//            int rowsInserted1 = statementN.executeUpdate();
//            if (rowsInserted1 > 0) {
//                logger.info("insert into device_end_user_db ");
//            }
//             
//            return "Success";
//        } catch (Exception e) {
//            logger.info("error" + e);
//        }
//        return "Success";
//        
//        
//        
//        
//    }
// 
