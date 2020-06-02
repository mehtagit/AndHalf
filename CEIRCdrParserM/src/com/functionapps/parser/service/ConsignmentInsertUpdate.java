package com.functionapps.parser.service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import com.functionapps.parser.ErrorFileGenrator;
import org.apache.log4j.Logger;
import com.functionapps.log.LogWriter;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.CEIRFeatureFileParser;
import com.functionapps.parser.Rule;
import com.functionapps.parser.RuleFilter;
import com.functionapps.util.Util;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConsignmentInsertUpdate {

    static Logger logger = Logger.getLogger(ConsignmentInsertUpdate.class);

    public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txn_id, String operator_tag, String usertype_name, int webActState) {

        ErrorFileGenrator errFile = new ErrorFileGenrator();
        String query = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        Statement stmt = null; // for rs
        Statement stmt0 = null; // for rs1
        Statement stmt1 = null; // devce_dv batch
        Statement stmt2 = null; // device_imprter_db btch
        Statement raw_stmt = null; // update raw table to cmplete
        Statement stmt3 = null; // custom db
        Statement stmt4 = null; // greylist_db
        Statement stmt5 = null; // greylisthistory_db

        String raw_query = null;

        String my_query = null;
        String device_db_query = null;
        String device_custom_db_qry = null;
        String device_greylist_db_qry = null;
        String device_greylist_History_db_qry = null;

        HashMap<String, String> my_rule_detail = new HashMap<String, String>();;
        HashMap<String, String> stolnRcvryDetails = new HashMap<String, String>();
        HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
        HashMap<String, String> feature_file_management = new HashMap<String, String>();
        CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();

        String period = "";
        int parser_base_limit = 0;
        int old_sno = 0;
        int update_sno = 0;
//        final String logPath = "./";
        LogWriter logWriter = new LogWriter();
        String logPath = logWriter.getLogPath();
        String fileName = null;
        File file = null;
//        String log = null;
        int split_upload_batch_no = 10; // it should be dymnamic
        int split_upload_batch_count = 0;
        int rrslt = 0;
        int countError = 0;
        int stolenRecvryBlock = 0;

        CEIRFeatureFileParser cEIRFeatureFileParser = new CEIRFeatureFileParser();
        String errorFilePath = cEIRFeatureFileParser.getErrorFilePath(conn);
        try {
            File fileEr = new File(errorFilePath + txn_id);       //   errFile.gotoErrorFilewithList(errorFilePath, txn_id, fileErrorLines);
            fileEr.mkdir();
            String fileNameInput = errorFilePath + txn_id + "/" + txn_id + "_error.csv";
            File fout = new File(fileNameInput);
            FileOutputStream fos = new FileOutputStream(fout, true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            feature_file_mapping = ceirfunction.getFeatureMapping(conn, operator, usertype_name); // select * from // feature_mapping_db
            logger.info("webActState.. " + webActState);
            if (webActState == 3) {
                query = "select count(*) as counnt from " + operator + "_raw where  txn_id='" + txn_id + "'  ";
                logger.info("query.. " + query);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                int rawCount = 0;
                int finalCount = 0;
                while (rs.next()) {
                    rawCount = rs.getInt(1);
                }
                logger.info("State .query. " + query);
                query = " select count(*) as counnt  from  " + feature_file_mapping.get("output_device_db") + " where   txn_id='" + txn_id + "'   ";
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    finalCount = rs.getInt(1);
                }

                logger.info("State ####3.. " + query);
                logger.info("State ####3..rawCount  " + rawCount + "  finalCount" + finalCount);
                if (rawCount == finalCount) {
                    ceirfunction.UpdateStatusViaApi(conn, txn_id, 0, operator);
                    logger.info("State ####3.. UpdateStatusViaApi 0");
                    ceirfunction.updateFeatureFileStatus(conn, txn_id, 4, operator, sub_feature);
                    logger.info("State ####3..webAction 4 ");
                } else {
                    query = " delete from   " + feature_file_mapping.get("output_device_db") + " where   txn_id='" + txn_id + "'   ";
                    rs = stmt.executeQuery(query);
                    logger.info("State >>>.. " + query);
                    query = "update  " + operator + "_raw  set status = 'Init' where  txn_id='" + txn_id + "'  ";
                    rs = stmt.executeQuery(query);
                    logger.info("State ####3.. " + query);
                    ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, operator, sub_feature);
                    logger.info("State ####3  webACtion 2.. " + query);
                    conn.commit();
                }
                // update web_action_db

                return;
            }
            stolnRcvryDetails.put("operator", operator);
            ResultSet my_result_set = cEIRFeatureFileParser.operatorDetails(conn, operator);
            if (my_result_set.next()) {
                parser_base_limit = my_result_set.getInt("split_upload_set_no");
                old_sno = my_result_set.getInt("last_upload_sno");
            }
            // query = "select * from "+operator+"_raw where sno>"+old_sno+" and             // status='Init' order by sno asc FETCH FIRST "+parser_base_limit+" ROWS WITH             // TIES ";
            query = "select * from " + operator + "_raw where   txn_id='" + txn_id + "' and status='Init' order by sno asc ";
            stmt = conn.createStatement();
            logger.info("Get Data (addCDRInProfileWithRule)Query.. " + query);
            rs = stmt.executeQuery(query);
            HashMap<String, String> device_info = new HashMap<String, String>();
            RuleFilter rule_filter = new RuleFilter();
            // updateLastStatuSno(conn, operator, old_sno, parser_base_limit); //update _raw
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fileName = "CEIR_" + operator + "_File_" + sdf.format(Calendar.getInstance().getTime()) + ".csv";
            logger.info(" File NAme as CEIR.myWriter(log) .." + fileName);
            file = new File(logPath);
            if (!file.exists()) {
                file.mkdir();
                logger.info("File not exists");
            }
            file = new File(logPath + fileName);
            FileWriter myWriter;
            if (file.exists()) {
                myWriter = new FileWriter(file, true);
            } else {
                myWriter = new FileWriter(file);
            }
            period = cEIRFeatureFileParser.checkGraceStatus(conn);

            feature_file_management = ceirfunction.getFeatureFileManagement(conn, feature_file_mapping.get("mgnt_table_db"), txn_id); // select * from " + management_db + " where
            if (operator.equalsIgnoreCase("Stolen") || operator.equalsIgnoreCase("Recovery") || operator.equalsIgnoreCase("Block") || operator.equalsIgnoreCase("Unblock")) {
                stolnRcvryDetails = cEIRFeatureFileParser.getStolenRecvryDetails(conn, txn_id);
                stolenRecvryBlock = 1;
            }

            logger.info("   -- OPERATOR/feature--... " + operator + ".--SUBFEATURE--." + sub_feature + "");
//            if ((sub_feature.equalsIgnoreCase("register") || sub_feature.equalsIgnoreCase("update") || sub_feature.equalsIgnoreCase("upload"))) {
            bw.write(" DEVICETYPE,DeviceIdType,MultipleSIMStatus,S/NofDevice,IMEI/ESN/MEID,Devicelaunchdate,DeviceStatus, Error Code ,Error Message ");
            bw.newLine();
//                errFile.gotoErrorFile(txn_id, "DEVICETYPE,DeviceIdType,MultipleSIMStatus,S/NofDevice,IMEI/ESN/MEID,Devicelaunchdate,DeviceStatus, Error Code ,Error Message ");
            while (rs.next()) {
                logger.info("Served IMEI 1 =" + rs.getString("IMEIESNMEID"));
                device_info.put("DeviceIdType", rs.getString("DeviceIdType"));
                device_info.put("IMEIESNMEID", rs.getString("IMEIESNMEID"));
                device_info.put("DeviceType", rs.getString("DeviceType"));
                device_info.put("MultipleSIMStatus", rs.getString("MultipleSIMStatus"));
                device_info.put("SNofDevice", rs.getString("SNofDevice"));
                device_info.put("Devicelaunchdate", rs.getString("Devicelaunchdate"));
                device_info.put("DeviceStatus", rs.getString("DeviceStatus"));
                device_info.put("operator", operator);
                device_info.put("feature", operator);
                device_info.put("file_name", rs.getString("file_name"));
                device_info.put("operator_tag", operator_tag);
                device_info.put("txn_id", txn_id);
//                try {
//                    if (file.length() == 0) {
//                        new LogWriter().writeFeatureEvents(myWriter, "IMEIESNMEID", "DeviceType", "MultipleSIMStatus", "SNofDevice", "Devicelaunchdate", "DeviceStatus", "txn_id", "operator", "file_name", "type", "rule_id", "rule_name", "status", "time");
//                    } else {
//                        new LogWriter().writeFeatureEvents(myWriter, rs.getString("IMEIESNMEID"), rs.getString("DeviceType"), rs.getString("MultipleSIMStatus"), rs.getString("SNofDevice"), rs.getString("Devicelaunchdate"), rs.getString("DeviceStatus"), txn_id, operator, rs.getString("file_name"), "Init", "", "", "", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//                    }
//                } catch (Exception e) {
//                    logger.error(" exception.. at   new LogWriter().writeFeatureEvents(myWriter, ,,," + e);
//                }
                logger.info(" process .. getMyFeatureRule start   ");
                my_rule_detail = rule_filter.getMyFeatureRule(conn, device_info, rulelist, myWriter, bw);
                logger.info(" process .. getMyFeatureRule End with   errorFlag  " + my_rule_detail.get("errorFlag"));
                String fileArray = device_info.get("DeviceType") + "," + device_info.get("DeviceIdType") + "," + device_info.get("MultipleSIMStatus") + "," + device_info.get("SNofDevice") + "," + device_info.get("IMEIESNMEID") + "," + device_info.get("Devicelaunchdate") + "," + device_info.get("DeviceStatus") + "";
//                    errFile.gotoErrorFile(txn_id, fileArray);
                if (my_rule_detail.get("errorFlag").equals("0")) {
                    bw.write(fileArray);
                    bw.newLine();
                } else {   // execute Action False
                    logger.info(" process .action_output." + my_rule_detail.get("action_output"));
                    if (my_rule_detail.get("action_output").equalsIgnoreCase("Failure")) {//action is failed
                        bw.write(fileArray + ", Action is not Completed for  " + my_rule_detail.get("rule_name"));
                        bw.newLine();
                    }
                    countError++;
                }
                split_upload_batch_count++;
                if (split_upload_batch_count == split_upload_batch_no) {
                    conn.commit();
                    split_upload_batch_count = 0;
                }
                update_sno = Integer.parseInt(rs.getString("sno"));
            }                 // while end
            if (update_sno != 0) {
                cEIRFeatureFileParser.updateRawLastSno(conn, operator, update_sno);
            }
            int imeiCountfromRaw = cEIRFeatureFileParser.imeiCountfromRawTable(conn, txn_id, operator);
            logger.info("count error.. " + countError + " , Total imei in mgmt db .. " + imeiCountfromRaw);

            String error_file_path = errorFilePath + txn_id + "/" + txn_id + "_error.csv";
            logger.info("countError " + countError);
            if (countError != 0) {
//                errFile.gotoErrorFilewithList(errorFilePath, txn_id, fileErrorLines);
                ceirfunction.UpdateStatusViaApi(conn, txn_id, 1, operator);
                ceirfunction.updateFeatureFileStatus(conn, txn_id, 4, operator, sub_feature); // update web_action_db
            } //            File errorfile = new File(error_file_path);            //            logger.info("File path is.. " + error_file_path + " , IF Error file exists .. " + errorfile.exists());            //            if (errorfile.exists()) {            //                ceirfunction.updateFeatureFileStatus(conn, txn_id, 4, operator, sub_feature); // update web_action_db  //               ceirfunction.UpdateStatusViaApi(conn, txn_id, 1, operator);            //                    ceirfunction.updateFeatureManagementStatus(conn, txn_id, 2, feature_file_mapping.get("mgnt_table_db"), operator); // 2 - pending approval , 3 -reject by                  // sys                       //    ceirfunction.addFeatureFileConfigDetails(conn, "update", operator, sub_feature, txn_id, "", "REJECTED_BY_SYSTEM", "");            //            } 
            else {

                logger.info("File  moving to old Folder ");
                file = new File(errorFilePath + txn_id + "/old/");
                if (!file.exists()) {
                    file.mkdir();
                }
                Path temp = Files.move(Paths.get(error_file_path),
                        Paths.get(errorFilePath + txn_id + "/old/" + txn_id + "_" + java.time.LocalDateTime.now() + "_P2_error.csv"));
                if (temp != null) {
                    logger.info("File renamed and moved successfully");
                } else {
                    logger.info("Failed to move the file");
                }

                rrslt = cEIRFeatureFileParser.getCustomData(conn, txn_id); // select user_type from stock_mgmt where txn_id
                logger.info(".getCustomData rslt ." + rrslt);
                stmt0 = conn.createStatement();
                stmt1 = conn.createStatement();
                stmt2 = conn.createStatement();
                stmt3 = conn.createStatement();
                stmt4 = conn.createStatement();
                stmt5 = conn.createStatement();
                split_upload_batch_count = 0;
                logger.info("  inserting....");
                rs1 = stmt0.executeQuery(query);
                String dateNow1 = "";
                if (conn.toString().contains("oracle")) {
                    dateNow1 = new SimpleDateFormat("dd-MMM-YY").format(new Date());
                } else {
                    dateNow1 = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
                }
                
                 boolean isOracle = conn.toString().contains("oracle");
            String dateFunction = Util.defaultDate(isOracle);
                
                
                
                
                logger.info(".output_device_db  ." + feature_file_mapping.get("output_device_db"));
                while (rs1.next()) {      //can b rs (we can run again)
                    split_upload_batch_count++;
                    String dvsStatus = rs1.getString("DeviceStatus");
                    if (stolenRecvryBlock == 1) {
                        dvsStatus = stolnRcvryDetails.get("divceStatus");
                    }

                    my_query = "insert into " + feature_file_mapping.get("output_device_db")
                            + " (device_id_type,created_on,device_launch_date,device_status,"
                            + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id ,feature_name ) " //,feature_name
                            + "values(" + "'" + rs1.getString("DeviceIdType") + "'," + "" + dateFunction + "," /// "dd-MMM-YY"                              
                            + "'" + rs1.getString("Devicelaunchdate") + "', '" + dvsStatus + "'  ,'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID")
                            + "'," + "" + dateFunction + "," + "'" + rs1.getString("MultipleSIMStatus") + "'," + "'"
                            + period + "'," + "'" + rs1.getString("SNofDevice") + "'," + "'" + txn_id + "'," + ""
                            + feature_file_management.get("user_id") + ", '" + operator + "'  )";    // "," + operator +  

                    device_db_query = "insert into device_db (device_id_type,created_on,device_launch_date,device_status,"
                            + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id , feature_name) " // feature_name
                            + "values('" + rs1.getString("DeviceIdType") + "'," + "" + dateFunction + "," + "'"
                            + rs1.getString("Devicelaunchdate") + "'," + "'" + rs1.getString("DeviceStatus") + "',"
                            + "'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID") + "',"
                            + "" + dateFunction + "," + "'" + rs1.getString("MultipleSIMStatus") + "',"
                            + "'" + (period == "grace" ? 0 : 1) + "'," + "'" + rs1.getString("SNofDevice") + "',"
                            + "'" + txn_id + "'  "
                            + " , '" + operator + "' "
                            + ")";

                    device_custom_db_qry = "insert into device_custom_db (device_id_type,created_on,device_launch_date,device_status,"
                            + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id ,feature_name) " // user_id
                            + "values('" + rs1.getString("DeviceIdType") + "'," + " " + dateFunction + "," + "'"
                            + rs1.getString("Devicelaunchdate") + "'," + "'" + rs1.getString("DeviceStatus") + "',"
                            + "'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID") + "',"
                            + "" + dateFunction + "," + "'" + rs1.getString("MultipleSIMStatus") + "',"
                             + "'" + (period == "grace" ? 0 : 1) + "'," + "'" + rs1.getString("SNofDevice") + "',"
                            + "'" + txn_id + "'  "
                            + " , '" + operator + "' "
                            + ")";

                    if (stolenRecvryBlock == 1) {
                        if (stolnRcvryDetails.get("operation").equals("0")) {
                            device_greylist_db_qry = "insert into   greylist_db (created_on , imei, user_id , txn_id , mode_type  , request_type, user_type  , complain_type)   "
                                    + "values(           " + "" + dateFunction + "," + "'" + rs1.getString("IMEIESNMEID")
                                    + "'," + " ( select username from users where users.id=  "
                                    + feature_file_management.get("user_id") + "  )  ,  " + " '" + txn_id + "', " + "'"
                                    + stolnRcvryDetails.get("source") + "'," + "'" + stolnRcvryDetails.get("reason")
                                    + "'," + "'" + stolnRcvryDetails.get("usertype") + "'," + "'"
                                    + stolnRcvryDetails.get("complaint_type") + "'  " + ")";

                        } else {
                            device_greylist_db_qry = "delete from greylist_db where imei  = '"
                                    + rs1.getString("IMEIESNMEID") + "' ";
                            my_query = "    delete from    " + feature_file_mapping.get("output_device_db") + " where imei_esn_meid  = '" + rs1.getString("IMEIESNMEID") + "'";
                        }

                        device_greylist_History_db_qry = "insert into   greylist_db_history (created_on , imei, user_id , txn_id , mode_type  , request_type, user_type  , complain_type ,operation)   "
                                + "values(           " + "" + dateFunction + "," + "'" + rs1.getString("IMEIESNMEID")
                                + "'," + " ( select username from users where users.id=  "
                                + feature_file_management.get("user_id") + "  )  ,  " + " '" + txn_id + "', " + "'"
                                + stolnRcvryDetails.get("source") + "'," + "'" + stolnRcvryDetails.get("reason") + "',"
                                + "'" + stolnRcvryDetails.get("usertype") + "'," + "'"
                                + stolnRcvryDetails.get("complaint_type") + "' , " + "'"
                                + stolnRcvryDetails.get("operation") + "'  " + ")";
                    }

                    //
                    stmt1.addBatch(my_query);
                    stmt2.addBatch(device_db_query);
                    if (rrslt != 0) {
                        stmt3.addBatch(device_custom_db_qry);
                    }
                    if (stolenRecvryBlock == 1) {
                        stmt4.addBatch(device_greylist_db_qry);
                        stmt5.addBatch(device_greylist_History_db_qry);
                        insertFromImporterManufactor(conn, rs1, stolnRcvryDetails, feature_file_management, feature_file_mapping, dateFunction, period, txn_id);
                    }
                    split_upload_batch_count++;
                    if (split_upload_batch_count == split_upload_batch_no) {
                        stmt1.executeBatch();
                        conn.commit();
                        stmt2.executeBatch();
                        conn.commit();
                        if (rrslt != 0) {
                            stmt3.executeBatch();
                            conn.commit();
                        }
                        if (stolenRecvryBlock == 1) {
                            stmt4.executeBatch();
                            conn.commit();
                            stmt5.executeBatch();
                            conn.commit();
                        }
                        split_upload_batch_count = 0;
                    }

//                    stmt1.executeUpdate(my_query); // added
//                    stmt2.executeUpdate(device_db_query); // addedd/
//                    if (rrslt != 0) {
//                        stmt3.executeUpdate(device_custom_db_qry);
//                    }
//                    if (stolenRecvryBlock == 1) {
//                        stmt4.executeUpdate(device_greylist_db_qry);
//                        stmt5.executeUpdate(device_greylist_History_db_qry);
//                        insertFromImporterManufactor(conn, rs1, stolnRcvryDetails, feature_file_management, feature_file_mapping, dateNow, period, txn_id);
//                    }
                }    // while Close

                stmt1.executeBatch();
                conn.commit();
                stmt2.executeBatch();
                conn.commit();
                if (rrslt != 0) {
                    stmt3.executeBatch();
                    conn.commit();
                }
                if (stolenRecvryBlock == 1) {
                    stmt4.executeBatch();
                    conn.commit();
                    stmt5.executeBatch();
                    conn.commit();
                }
                logger.info("entered outside");
                conn.commit();
                stmt.close();
                stmt0.close();
                stmt1.close();
                stmt2.close();
                stmt3.close();
                stmt4.close();
                stmt5.close();

//                logger.info("stmts closed");
                ceirfunction.UpdateStatusViaApi(conn, txn_id, 0, operator);
                logger.info("UpdateStatusViaApi  : 0");
                ceirfunction.updateFeatureFileStatus(conn, txn_id, 4, operator, sub_feature);
                logger.info("weBaction $4 done : ");
//          ceirfunction.updateFeatureManagementStatus(conn, txn_id, 3, feature_file_mapping.get("mgnt_table_db"), operator);
            }
            raw_stmt = conn.createStatement();
            raw_query = "update " + operator + "_raw" + " set status='Complete' where txn_id='" + txn_id + "'";
            logger.info("updating raw table with cmplte.." + raw_query);
            raw_stmt.executeUpdate(raw_query);
            conn.commit();
            raw_stmt.close();
//            }

            bw.close();
        } catch (Exception e) {
            logger.error("Error.." + e);
        } finally {
            try {

            } catch (Exception e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }
        }
    }

    private void insertFromImporterManufactor(Connection conn, ResultSet rs1, HashMap<String, String> stolnRcvryDetails, HashMap<String, String> feature_file_management, HashMap<String, String> feature_file_mapping, String dateFunction, String period, String txn_id) {
        logger.info("insertFromImporterManufactor.. ");
        try {
            String qry = " select a.imei_esn_meid from device_importer_db  a , device_importer_db  b  where a.sn_of_device = b.sn_of_device "
                    + "and b.imei_esn_meid = '" + rs1.getString("IMEIESNMEID") + "' "
                    //                    + " and a.imei_esn_meid  not in(select imei_esn_meid  from device_lawful_db ) "  
                    + " and a.imei_esn_meid != '" + rs1.getString("IMEIESNMEID") + "'  union  "
                    + "select a.imei_esn_meid from device_manufacturer_db  a , device_manufacturer_db  b  where a.sn_of_device = b.sn_of_device and"
                    + " b.imei_esn_meid = '" + rs1.getString("IMEIESNMEID") + "' "
                    //                    + " and a.imei_esn_meid  not in(select imei_esn_meid  from device_lawful_db ) "  
                    + " and a.imei_esn_meid != '" + rs1.getString("IMEIESNMEID") + "'  ";

            logger.info("insertFromImporterManufactor.. query is ::" + qry);
            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            Statement stmt3 = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            String my_query = null;
            String device_greylist_db_qry = null;
            String device_greylist_History_db_qry = null;
            while (rs.next()) {
                my_query = "insert into " + feature_file_mapping.get("output_device_db")
                        + " (device_id_type,created_on,device_launch_date,device_status,"
                        + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id) "
                        + "values(" + "'" + rs1.getString("DeviceIdType") + "'," + "" + dateFunction + "," /// "dd-MMM-YY"                              
                        + "'" + rs1.getString("Devicelaunchdate") + "', '" + stolnRcvryDetails.get("divceStatus") + "'  ,'" + rs1.getString("DeviceType") + "'," + "'" + rs.getString("imei_esn_meid")
                        + "'," + "" + dateFunction + "," + "'" + rs1.getString("MultipleSIMStatus") + "'," + "'"
                        + period + "'," + "'" + rs1.getString("SNofDevice") + "'," + "'" + txn_id + "'," + ""
                        + feature_file_management.get("user_id") + "" + ")";

                if (stolnRcvryDetails.get("operation").equals("0")) {
                    device_greylist_db_qry = "insert into   greylist_db (created_on , imei, user_id , txn_id , mode_type  , request_type, user_type  , complain_type)   "
                            + "values(  " + "" + dateFunction + "," + "'" + rs.getString("imei_esn_meid")
                            + "'," + " ( select username from users where users.id=  "
                            + feature_file_management.get("user_id") + "  )  ,  " + " '" + txn_id + "', " + "'"
                            + stolnRcvryDetails.get("source") + "'," + "'" + stolnRcvryDetails.get("reason")
                            + "'," + "'" + stolnRcvryDetails.get("usertype") + "'," + "'"
                            + stolnRcvryDetails.get("complaint_type") + "'  " + ")";

                } else {
                    device_greylist_db_qry = "delete from greylist_db where imei  = '" + rs.getString("imei_esn_meid") + "' ";
                    my_query = "    delete from    " + feature_file_mapping.get("output_device_db")
                            + " where imei_esn_meid  = '" + rs.getString("imei_esn_meid") + "'";

                }

                device_greylist_History_db_qry = "insert into   greylist_db_history (created_on , imei, user_id , txn_id , mode_type  , request_type, user_type  , complain_type ,operation)   "
                        + "values(           " + "" + dateFunction + "," + "'" + rs.getString("imei_esn_meid")
                        + "'," + " ( select username from users where users.id=  "
                        + feature_file_management.get("user_id") + "  )  ,  " + " '" + txn_id + "', " + "'"
                        + stolnRcvryDetails.get("source") + "'," + "'" + stolnRcvryDetails.get("reason") + "',"
                        + "'" + stolnRcvryDetails.get("usertype") + "'," + "'"
                        + stolnRcvryDetails.get("complaint_type") + "' , " + "'"
                        + stolnRcvryDetails.get("operation") + "'  " + ")";
                stmt1.executeUpdate(my_query);
                stmt2.executeUpdate(device_greylist_db_qry);
                stmt3.executeUpdate(device_greylist_History_db_qry);

                logger.info("insertFromImporterManufactor.. my_query is ::" + my_query);
                logger.info("insertFromImporterManufactor.. device_greylist_db_qry is ::" + device_greylist_db_qry);
                logger.info("insertFromImporterManufactor.. device_greylist_History_db_qry is ::" + device_greylist_History_db_qry);
            }

            stmt.close();
            stmt1.close();
            stmt2.close();
            stmt3.close();
            conn.commit();
        } catch (Exception e) {
            logger.error("Error.." + e);
        }

    }

}

//	void process(Connection conn,String operator,String sub_feature,ArrayList<Rule> rulelist,String txn_id,String operator_tag ){
//		String fileName = null;
//		File file       = null;
//		final String logPath  = "./";
//		Statement stmt1=null;
//		Statement stmt2=null;
//		Statement raw_stmt = null;
//		ResultSet rs = null;
//
//		String period = "";
//		int split_upload_batch_count=0;
//		String raw_query = null;
//		String my_query = null;
//		String device_db_query =null;
//		int update_sno = 0;
//		int parser_base_limit=0;
//		int split_upload_batch_no=0;
//		String query = null;
//		Statement stmt = null;
//		int old_sno = 0;
//		HashMap<String, String> my_rule_detail;
//		HashMap<String, String> device_info = new HashMap<String, String>();
//		RuleFilter rule_filter = new RuleFilter();
//		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
//		cEIRFeatureFileParser ceirfileparser = new cEIRFeatureFileParser();
//
//		try{
//			ResultSet my_result_set= ceirfileparser.operatorDetails( conn,  operator);
//			if(my_result_set.next()){
//				parser_base_limit = my_result_set.getInt("split_upload_set_no");
//				old_sno = my_result_set.getInt("last_upload_sno");
//				split_upload_batch_no = my_result_set.getInt("split_upload_batch_no");
//			}
////			query = "select * from "+operator+"_raw where sno>"+old_sno+" and status='Init' order by sno asc FETCH FIRST "+parser_base_limit+" ROWS WITH TIES ";
//			query = "select * from "+operator+"_raw where sno>"+old_sno+" and txn_id='"+txn_id+"' and status='Init' order by sno asc ";
//			stmt = conn.createStatement();
//			logger.info("Getting Data from raw table  ["+query+"]");
//			 // System.out.println("Get Data Query"+query);
//			rs=stmt.executeQuery(query);
//			ceirfileparser.updateLastStatuSno(conn,operator, old_sno,parser_base_limit);
//			
//			stmt1=conn.createStatement();
//			stmt2=conn.createStatement();
//			raw_stmt = conn.createStatement();
//
//			// CDR File Writer
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
//			fileName = "CEIR_"+operator+"_File_"+sdf.format(Calendar.getInstance().getTime())+".csv";
//			file     = new File( logPath );
//			if( !file.exists() ){
//				file.mkdir();
//				 // System.out.println("File not exists");
//			}	
//			file = new File( logPath+fileName );
//			FileWriter myWriter;
//			if(file.exists()){
//				myWriter = new FileWriter(file,true);
//			}
//			else{
//				myWriter = new FileWriter(file);				
//			}
//
//			period = ceirfileparser.checkGraceStatus(conn);
//			HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
//			feature_file_mapping = ceirfunction.getFeatureMapping(conn,operator);
//			HashMap<String, String> feature_file_management = new HashMap<String, String>();
//			feature_file_management = ceirfunction.getFeatureFileManagement(conn,feature_file_mapping.get("mgnt_table_db"),txn_id);
//			while(rs.next()){
//
//				 // System.out.println("Served IMEI 1 ="+rs.getString("IMEIESNMEID"));
//				device_info.put("IMEIESNMEID",rs.getString("IMEIESNMEID") );
//				device_info.put("DeviceType",rs.getString("DeviceType") );
//				device_info.put("MultipleSIMStatus",rs.getString("MultipleSIMStatus") );
//				device_info.put("SNofDevice",rs.getString("SNofDevice") );
//				device_info.put("Devicelaunchdate",rs.getString("Devicelaunchdate") );
//				device_info.put("DeviceStatus",rs.getString("DeviceStatus") );
//				device_info.put("operator",operator );
//				device_info.put("feature",operator );
//				device_info.put("file_name",rs.getString("file_name") );
//				device_info.put("operator_tag",operator_tag);
//				device_info.put("txn_id",txn_id);
//				// Event writer into file
//				if(file.length()==0){
//					new LogWriter().writeFeatureEvents(myWriter, "IMEIESNMEID",
//							"DeviceType", "MultipleSIMStatus", "SNofDevice", "Devicelaunchdate","DeviceStatus","txn_id",
//							"operator", "file_name", "type", "rule_id", "rule_name", "status","time");				
//				}
//				else{
//					new LogWriter().writeFeatureEvents(myWriter, rs.getString("IMEIESNMEID"),
//							rs.getString("DeviceType"), rs.getString("MultipleSIMStatus"),rs.getString("SNofDevice"),
//							rs.getString("Devicelaunchdate"),rs.getString("DeviceStatus"),txn_id,
//							operator, rs.getString("file_name"), "Init", "", "", "",new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));									
//				}				
//				my_rule_detail = rule_filter.getMyFeatureRule(conn,device_info,rulelist,myWriter);
//				split_upload_batch_count++;
//									
//				raw_query = "update "+operator+"_raw"+" set status='Complete' where sno='"+rs.getString("sno")+"'";
//				raw_stmt.addBatch(raw_query);
//				
//				if(split_upload_batch_count==split_upload_batch_no){
//					logger.info("Executing batch file");
//					raw_stmt.executeBatch();
//					conn.commit();
//					split_upload_batch_count=0;
//				}
//
//				
//				
//				update_sno = Integer.parseInt(rs.getString("sno"));
//				
//			}
//			if(update_sno!=0){
//				ceirfileparser.updateRawLastSno(conn,operator,  update_sno);
//				logger.info("Final Executing batch file");
//				raw_stmt.executeBatch();
//				conn.commit();
//			}
//			String error_file_path = ceirfileparser.getErrorFilePath(conn)+txn_id+"/"+txn_id+"_err.csv";
//			File errorfile = new File(error_file_path);
//			if(errorfile.exists()){
//				ceirfunction.updateFeatureFileStatus(conn,txn_id,2,operator, sub_feature);
//				ceirfunction.updateFeatureManagementStatus(conn, txn_id, 2, feature_file_mapping.get("mgnt_table_db"));		        	
//				ceirfunction.addFeatureFileConfigDetails(conn, "update",operator,sub_feature,txn_id,"","REJECTED_BY_SYSTEM","");
//
//			}
//			else{
//				rs.beforeFirst();
//				split_upload_batch_count=0;
//				while(rs.next()){
//					split_upload_batch_count++;
//					my_query = "insert into "+feature_file_mapping.get("output_device_db")+" (device_id_type,created_on,device_launch_date,device_status," +
//							"device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id) " +
//							"values('" +rs.getString("DeviceIdType")+"'," 
//							+"'"+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"',"
//							+"'"+rs.getString("Devicelaunchdate")+"',"
//							+"'"+rs.getString("DeviceStatus")+"',"
//							+"'"+rs.getString("DeviceType")+"',"
//							+"'"+rs.getString("IMEIESNMEID")+"',"
//							+"'"+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"',"
//							+"'"+rs.getString("MultipleSIMStatus")+"',"
//							+"'"+period+"',"
//							+"'"+rs.getString("SNofDevice")+"',"
//							+"'"+txn_id+"',"
//							+""+feature_file_management.get("user_id")+""
//							+")";
//					
//					device_db_query = "insert into device_db (device_id_type,created_on,device_launch_date,device_status," +
//							"device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id) " +
//							"values('" +rs.getString("DeviceIdType")+"'," 
//							+"'"+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"',"
//							+"'"+rs.getString("Devicelaunchdate")+"',"
//							+"'"+rs.getString("DeviceStatus")+"',"
//							+"'"+rs.getString("DeviceType")+"',"
//							+"'"+rs.getString("IMEIESNMEID")+"',"
//							+"'"+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"',"
//							+"'"+rs.getString("MultipleSIMStatus")+"',"
//							+"'"+period+"',"
//							+"'"+rs.getString("SNofDevice")+"',"
//							+"'"+txn_id+"',"
//							+""+feature_file_management.get("user_id")+""
//							+")";
//
//					
//					 // System.out.println(my_query);
//					 // System.out.println(device_db_query);
//					
//					stmt1.addBatch(my_query);
//					stmt2.addBatch(device_db_query);
//					if(split_upload_batch_count==split_upload_batch_no){
//						logger.info("Executing batch file");
//						 // System.out.println("Executing batch file");
//						stmt1.executeBatch();
//						stmt2.executeBatch();
//						conn.commit();
//						split_upload_batch_count=0;
//					}
//				}
//				stmt1.executeBatch();
//				stmt2.executeBatch();
//				conn.commit();
//				ceirfunction.updateFeatureFileStatus(conn,txn_id,2,operator, sub_feature);
//				ceirfunction.updateFeatureManagementStatus(conn, txn_id, 3, feature_file_mapping.get("mgnt_table_db"));		        	
//				ceirfunction.addFeatureFileConfigDetails(conn, "update",operator,sub_feature,txn_id,"","Complete","");
//			}			
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}

