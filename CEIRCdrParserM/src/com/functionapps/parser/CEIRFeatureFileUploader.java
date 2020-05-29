package com.functionapps.parser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import com.functionapps.util.Util;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class CEIRFeatureFileUploader {

    static Logger logger = Logger.getLogger(CEIRFeatureFileUploader.class);

    public static void main(String[] args) {
        logger.info(" CEIRFeatureFileUploader.class ");
        HexFileReader hfr = new HexFileReader();
        String basePath = "";
        String complete_file_path = "";
        CEIRParserMain ceir_parser_main = new CEIRParserMain();
        int raw_upload_set_no = 1;
        String[] rawDataResult = null;
        HashMap<String, String> feature_file_management = new HashMap<String, String>();
        Connection conn = new com.functionapps.db.MySQLConnection().getConnection();
        CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
        HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
        ResultSet file_details = ceirfunction.getFileDetails(conn, 0);  //select * from web_action_db limit 1 
        try {
            while (file_details.next()) {
                System.out.println("" + file_details.getString("txn_id"));
                logger.info("State ###.. " + file_details.getString("state"));
                ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 1, file_details.getString("feature"), file_details.getString("sub_feature"));  //update web_action_db set state 1
//                feature = file_details.getString("feature").replaceAll("\\s", "");

                logger.info("  FEATURE.." + file_details.getString("feature"));                              // add usertype name 
                if (file_details.getString("feature").equalsIgnoreCase("Register Device")) {
                    if ((file_details.getString("sub_feature").equalsIgnoreCase("Register")) || (file_details.getString("sub_feature").equalsIgnoreCase("Add Device"))) {     //'Add Device'

                        ceirfunction.UpdateStatusViaApi(conn, file_details.getString("txn_id"), 0, file_details.getString("feature"));
                        logger.info("Status via API 0 done ");
                        ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 2, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db           
                        logger.info("Web Action 2 done ");
                        break;
                    } else {
                        ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 2, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db           
                        logger.info("Web Action 2 done ");
                        break;
                    }

                }
                //      ceirfunction.getfromRegulizeEnterInCustom(conn, file_details.getString("txn_id"), file_details.getString("feature"))  ;
                //    ceirfunction.deleteFromCustom(conn, file_details.getString("txn_id"), file_details.getString("feature"))  ;    

                if (file_details.getString("feature").equalsIgnoreCase("Update Visa")) {
                    logger.info(" Feature  Visa Update. Only Web action Db state Update");
                    ceirfunction.UpdateStatusViaApi(conn, file_details.getString("txn_id"), 0, file_details.getString("feature"));
                    logger.info("UpdateStatusViaApi 0 done ");
                    ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 4, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db           
                    logger.info("Web Action 2 done ");
                    break;
                }

                feature_file_mapping = ceirfunction.getFeatureMapping(conn, file_details.getString("feature"), "NOUSER");  //select * from feature_mapping_db
                feature_file_management = ceirfunction.getFeatureFileManagement(conn, feature_file_mapping.get("mgnt_table_db"), file_details.getString("txn_id"));   //select * from " + management_db + " 

                long diffTime = Util.timeDiff(feature_file_management.get("created_on"), feature_file_management.get("modified_on"));
                logger.info("Time Difference .." + diffTime);
                if (((file_details.getString("sub_feature").equalsIgnoreCase("Register") || file_details.getString("sub_feature").equalsIgnoreCase("UPLOAD")) && (diffTime != 0))) {
                    logger.info("  It is Regsiter/Upload and different time" + feature_file_management.get("created_on") + " ||  " + feature_file_management.get("modified_on") + " OR delete Flaag : " + feature_file_management.get("delete_flag"));
                    ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 4, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db           
                    break;
                }
                logger.info(" .**.");

                try {     // check it for null

                    if (file_details.getString("feature").equalsIgnoreCase("CONSIGNMENT") || file_details.getString("feature").equalsIgnoreCase("STOCK")) {
                    } else {     // optimise
                        if (feature_file_management.get("delete_flag") == null) {
                            logger.info("  delete_flag   NULL.**.");
                        } else {
                            if (feature_file_management.get("delete_flag").equals("0")) {
                                logger.info("  Other Than Stock/Consignment , delete Flag : " + feature_file_management.get("delete_flag"));
                                ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 4, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db           
                                logger.info("  Web Action 4 done    ");
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("delete Flag .");
                }
                logger.info(" .##.");
                String errFilEpath = CEIRFeatureFileParser.getErrorFilePath(conn);
                String error_file_path = errFilEpath + file_details.getString("txn_id") + "/" + file_details.getString("txn_id") + "_error.csv";
                logger.info(" error_file_path.##." + error_file_path);
                File errorfile = new File(error_file_path);
                if (errorfile.exists()) {     // in case of update  ,, earlier file is remove
                    logger.info("File  moving to old Folder ");
                    errorfile = new File(errFilEpath + file_details.getString("txn_id") + "/old/");
                    if (!errorfile.exists()) {
                        errorfile.mkdir();
                    }
                    logger.info("mkdir Done ");
                    Path temp = Files.move(Paths.get(error_file_path),
                            Paths.get(errFilEpath + file_details.getString("txn_id") + "/old/" + file_details.getString("txn_id") + "_" + java.time.LocalDateTime.now() + "_P1_error.csv"));
                    if (temp != null) {
                        logger.info("File renamed and moved successfully for P1");
                    } else {
                        logger.info("Failed to move the file");
                    }

//                    logger.info("Error file already esists ");
//                    if (err orfile.delete()) {
//                        logger.info("File deleted successfully");
//                    } else {
//                        logger.info("Failed to delete the file");
//                    }
                }
                String user_type = ceirfunction.getUserType(conn, feature_file_management.get("user_id"), file_details.getString("feature").toUpperCase(), file_details.getString("txn_id"));      //   usertype_name from users a, usertype b
                //  ceirfunction.addFeatureFileConfigDetails(conn, "insert", file_details.getString("feature"), file_details.getString("sub_feature"), file_details.getString("txn_id"), feature_file_management.get("file_name"), "Init", user_type);     //insert into feature_fil e_config_db 
                logger.info("user_types *****" + user_type);
                if (!(feature_file_management.get("file_name") == null || feature_file_management.get("file_name").equals(""))) {
                    logger.info("File Found... " + file_details.getString("feature"));
                    ResultSet my_result_set = ceir_parser_main.operatorDetails(conn, file_details.getString("feature"));
                    logger.info("my_result_set " + my_result_set);
                    if (my_result_set.next()) {
                        raw_upload_set_no = my_result_set.getInt("raw_upload_set_no");
                    }
                    basePath = hfr.getFilePath(conn, "system_upload_filepath");  // filePath
                    if (!basePath.endsWith("/")) {
                        basePath += "/";
                    }
                    logger.info(" file basePath " + basePath);
                    complete_file_path = basePath + file_details.getString("txn_id") + "/" + feature_file_management.get("file_name");
                    logger.info("Complete file name is.... " + complete_file_path);
                    if (file_details.getString("sub_feature").equalsIgnoreCase("delete") || file_details.getString("sub_feature").equalsIgnoreCase("approve")) {
                        logger.info("sub_feature ...  DELETE / APPROVE " + file_details.getString("sub_feature"));
                        ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 2, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db               
                        logger.info("WEbaction status  2 Done  ");
                    }
                    if (file_details.getString("sub_feature").equalsIgnoreCase("register") || file_details.getString("sub_feature").equalsIgnoreCase("update") || file_details.getString("sub_feature").equalsIgnoreCase("upload")) {
                        logger.info("sub_feature ...   " + file_details.getString("sub_feature"));
                        if (file_details.getInt("state") == 1) {
                            logger.info(" state == 1  ");
                            getFinalDetailValues(conn, complete_file_path, feature_file_mapping.get("output_device_db"), file_details.getString("txn_id"), file_details.getString("feature"), file_details.getString("sub_feature"));
                            break;
                        } else {
                            rawDataResult = hfr.readConvertedFeatureFile(conn, feature_file_management.get("file_name"), complete_file_path, file_details.getString("feature"), basePath, raw_upload_set_no, file_details.getString("txn_id"), file_details.getString("sub_feature"), feature_file_mapping.get("mgnt_table_db"), user_type);
                        }
                    }
                } else {
                    logger.info("No File Found.. ");
                    if (file_details.getString("feature").equalsIgnoreCase("TYPE_APPROVED")) {
                        logger.info("TYPE_APPROVED  with .. " + file_details.getString("sub_feature"));
                        ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 2, file_details.getString("feature"), file_details.getString("sub_feature")); // update web_action_db    
                    } else {
                        logger.info("NOT typeApprove   ");
                        FeatureForSingleStolenBlock featureForSingleStolenBlock = new FeatureForSingleStolenBlock();
                        featureForSingleStolenBlock.readFeatureWithoutFile(conn, file_details.getString("feature"), raw_upload_set_no, file_details.getString("txn_id"), file_details.getString("sub_feature"), feature_file_mapping.get("mgnt_table_db"), user_type);
                    }
                }
            }
            raw_upload_set_no = 1;
        } catch (Exception e) {
            logger.error("CEIRFileUploader  Finished with Exception [" + e + "]");
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
                logger.error(" [" + ex + "]");
            }
        } finally {
            try {

                CEIRFeatureFileParser.ParserMain(conn);

            } catch (Exception ex) {
                logger.error(" [ " + ex + "]");
            }
        }
    }

    static void getFinalDetailValues(Connection conn, String complete_file_path, String outputDb, String txn_id, String feature, String sub_feature) {
        ResultSet rs = null;
        Statement stmt = null;
        String query = null;
        CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
        try {
            query = "select count(*) as cont from  " + feature + "_raw  where txn_id ='" + txn_id + "'";
            logger.info("Query is " + query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            int dbCount = 0;
            while (rs.next()) {
                dbCount = rs.getInt("cont");
            }

            Path path = Paths.get(complete_file_path);
            long lineCount = Files.lines(path).count();         //  if space is their , it fails 
            logger.info("lineCount from File  is " + lineCount);
            logger.info("lineCount from outputDB   is " + dbCount);
            if (dbCount == lineCount) {
                ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, feature, sub_feature);
                logger.info("Web action 2   done");
            } else {
                query = "delete from   " + outputDb + " where txn_id ='" + txn_id + "'";
                logger.info(query);
                stmt.executeQuery(query);
                ceirfunction.updateFeatureFileStatus(conn, txn_id, 0, feature, sub_feature);
                logger.info("Web action 0   done");
            }

        } catch (Exception e) {
            logger.error(new Object() {
            }.getClass().getEnclosingMethod().getName() + e);
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
