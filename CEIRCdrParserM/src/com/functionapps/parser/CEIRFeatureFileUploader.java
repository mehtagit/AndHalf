package com.functionapps.parser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class CEIRFeatureFileUploader {

    static Logger logger = Logger.getLogger(CEIRFeatureFileUploader.class);

    public static void main(String[] args) {
        logger.info(" ");
        logger.info(" ");
        Connection conn = new com.functionapps.db.MySQLConnection().getConnection();
        CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
        ResultSet file_details = ceirfunction.getFileDetails(conn, 0);  //select * from web_action_db limit 1 

        HexFileReader hfr = new HexFileReader();
        String basePath = "";
        String complete_file_path = "";
        CEIRParserMain ceir_parser_main = new CEIRParserMain();
        int raw_upload_set_no = 1;
        String[] rawDataResult = null;
        basePath = hfr.getFilePath(conn, "system_upload_filepath");  // filePath
        if (!basePath.endsWith("/")) {
            basePath += "/";
        }
        logger.info(" file basePath " + basePath);
        try {
            while (file_details.next()) {
                logger.info("base path is " + basePath);
                logger.info(" file_details.getString FEATURE.." + file_details.getString("feature"));   // add usertype name 
                //				CEIRFeatureFileParser cfp = new CEIRFeatureFileParser();
                String error_file_path = CEIRFeatureFileParser.getErrorFilePath(conn) + file_details.getString("txn_id") + "/" + file_details.getString("txn_id") + "_error.csv";
                File errorfile = new File(error_file_path);
                if (errorfile.exists()) {     // in case of update  ,, earlier file is remove
                    logger.info("Error file already esists ");
                    if (errorfile.delete()) {
                        logger.info("File deleted successfully");
                    } else {
                        logger.info("Failed to delete the file");
                    }
                }
                HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
                feature_file_mapping = ceirfunction.getFeatureMapping(conn, file_details.getString("feature"), "NOUSER");  //select * from feature_mapping_db
                logger.info(",,,,,,,,,,,");
                HashMap<String, String> feature_file_management = new HashMap<String, String>();
                feature_file_management = ceirfunction.getFeatureFileManagement(conn, feature_file_mapping.get("mgnt_table_db"), file_details.getString("txn_id"));   //select * from " + management_db + " 
                if (file_details.getString("feature").equalsIgnoreCase("Register") && !(feature_file_management.get("modified_on").equals(feature_file_management.get("created_on")))) {
                    ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 4, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db           
                    ceirfunction.updateFeatureManagementStatus(conn, file_details.getString("txn_id"), 2, feature_file_mapping.get("mgnt_table_db"), file_details.getString("feature"));
                    logger.info("  It is regsiter and different dates,, means it is modified  so we remove it");
                    break;
                }
                String user_type = ceirfunction.getUserType(conn, feature_file_management.get("user_id"), file_details.getString("feature").toUpperCase(), file_details.getString("txn_id"));      //   usertype_name from users a, usertype b
                //  ceirfunction.addFeatureFileConfigDetails(conn, "insert", file_details.getString("feature"), file_details.getString("sub_feature"), file_details.getString("txn_id"), feature_file_management.get("file_name"), "Init", user_type);     //insert into feature_file_config_db 
                logger.info("*****" + user_type);
                ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 1, file_details.getString("feature"), file_details.getString("sub_feature"));  //update web_action_db set state
//                ceirfunction.updateFeatureManagementStatus(conn, file_details.getString("txn_id"), 1, feature_file_mapping.get("mgnt_table_db"), file_details.getString("feature"));       //update " + mgmt table set _status= 1 (processsing)  
                  if (!(feature_file_management.get("file_name") == null  || feature_file_management.get("file_name").equals(""))) {
                    logger.info("File Found... " + file_details.getString("feature"));
                    ResultSet my_result_set = ceir_parser_main.operatorDetails(conn, file_details.getString("feature"));
                    logger.info("my_result_set " + my_result_set);
                    if (my_result_set.next()) {
                        raw_upload_set_no = my_result_set.getInt("raw_upload_set_no");
                    }
                    complete_file_path = basePath + file_details.getString("txn_id") + "/" + feature_file_management.get("file_name");
                    System.out.println("Complete file name " + complete_file_path);
                    logger.info("Complete file name is.... " + complete_file_path);
                    if (file_details.getString("sub_feature").equalsIgnoreCase("delete")  || file_details.getString("sub_feature").equalsIgnoreCase("approve")) {
                        ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 2, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db               
                    } 
//                     if (file_details.getString("sub_feature").equalsIgnoreCase("delete")) {
//                        ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 2, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db               
//                    } 
                    
                    
                     if (file_details.getString("sub_feature").equalsIgnoreCase("register")    ||  file_details.getString("sub_feature").equalsIgnoreCase("update")  ) {
                       logger.info("Sub State :.... " + file_details.getString("sub_feature")   + " Only Web action State to be update ");
                         rawDataResult = hfr.readConvertedFeatureFile(conn, feature_file_management.get("file_name"), complete_file_path, file_details.getString("feature"), basePath, raw_upload_set_no, file_details.getString("txn_id"), file_details.getString("sub_feature"), feature_file_mapping.get("mgnt_table_db"), user_type);
                    }
                     
                     
                     
                } else {
                    logger.info("No File Found.. ");
                    if (file_details.getString("feature").equalsIgnoreCase("TYPE_APPROVED") && file_details.getString("sub_feature").equalsIgnoreCase("register")) {
                        ceirfunction.updateFeatureManagementStatus(conn, file_details.getString("txn_id"), 1, feature_file_mapping.get("mgnt_table_db"), file_details.getString("feature"));
                        ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 2, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db    
                    } else {
                        FeatureForSingleStolenBlock featureForSingleStolenBlock = new FeatureForSingleStolenBlock();
                        featureForSingleStolenBlock.readFeatureWithoutFile(conn, file_details.getString("feature"), raw_upload_set_no, file_details.getString("txn_id"), file_details.getString("sub_feature"), feature_file_mapping.get("mgnt_table_db"), user_type);
                    }

                }
            }
            raw_upload_set_no = 1;

        } catch (Exception e) {
            logger.info("CEIRFileUploader  Finished with Exception [" + e + "]");
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
                logger.info(" [" + ex + "]");
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(CEIRFeatureFileUploader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

//                ResultSet my_result_set = ceir_parser_main.operatorDetails(conn, file_details.getString("feature"));     //select * from rep_schedule_config_db
//                if (my_result_set.next()) {
//                    raw_upload_set_no = my_result_set.getInt("raw_upload_set_no");
//                }
//                logger.info("raw_upload_set_no >>>>>>" + raw_upload_set_no);
//                complete_file_path = basePath + file_details.getString("txn_id") + "/" + feature_file_management.get("file_name");
//                logger.info("Complete file name is.... " + complete_file_path);
//                File uplodedfile = new File(complete_file_path);
//                logger.info("File exist Type.... " + uplodedfile.exists());
//                if (uplodedfile.exists()) {
//                    logger.info("File Exists.... ");
//                    rawDataResult = hfr.readConvertedFeatureFile(conn, feature_file_management.get("file_name"), complete_file_path, file_details.getString("feature"), basePath, raw_upload_set_no, file_details.getString("txn_id"), file_details.getString("sub_feature"), feature_file_mapping.get("mgnt_table_db"), user_type);
//                } else {
//                    logger.info("File not exists.... ");
//                    hfr.readFeatureWithoutFile(conn, file_details.getString("feature"), raw_upload_set_no, file_details.getString("txn_id"), file_details.getString("sub_feature"), feature_file_mapping.get("mgnt_table_db"), user_type);
//                }
