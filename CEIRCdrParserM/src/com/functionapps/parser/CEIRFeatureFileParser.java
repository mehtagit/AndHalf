package com.functionapps.parser;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import com.functionapps.parser.service.ApproveConsignment;
import com.functionapps.parser.service.ConsignmentDelete;
import com.functionapps.parser.service.ConsignmentInsertUpdate;
import com.functionapps.parser.service.RegisterTac;
import com.functionapps.parser.service.WithdrawnTac;
import com.functionapps.parser.service.StockDelete;
import org.apache.log4j.Logger;

public class CEIRFeatureFileParser {

    public static Logger logger = Logger.getLogger(CEIRFeatureFileParser.class);

    public static void main(String args[]) {
        logger.info("  ");
        logger.info("  ");
        logger.info(" CEIRFeatureFileParser.class ");
        String feature = null;
        Connection conn = null;
        conn = (Connection) new com.functionapps.db.MySQLConnection().getConnection();
        CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
        CEIRFeatureFileParser ceirfileparser = new CEIRFeatureFileParser();
        ResultSet featurers = ceirfunction.getFileDetails(conn, 2);     //select * from web_action_db 
//		ResultSet featurers=getFeatureFileDetails(conn);
        try {
            if (featurers.next()) {
                ceirfunction.updateFeatureFileStatus(conn, featurers.getString("txn_id"), 3, featurers.getString("feature"), featurers.getString("sub_feature"));  // update web_action
                if (featurers.getString("feature").equalsIgnoreCase("Register Device") || featurers.getString("feature").equalsIgnoreCase("Update Visa")) {
                    logger.info(" Feature  Register Device / Visa Update. ");
                    ceirfunction.UpdateStatusViaApi(conn, featurers.getString("txn_id"), 0, featurers.getString("feature"));
                    ceirfunction.updateFeatureFileStatus(conn, featurers.getString("txn_id"), 4, featurers.getString("feature"), featurers.getString("sub_feature"));  // update web_action
                } else {
                    HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
                    feature_file_mapping = ceirfunction.getFeatureMapping(conn, featurers.getString("feature"), "NOUSER");
                    HashMap<String, String> feature_file_management = new HashMap<String, String>();
                    feature_file_management = ceirfunction.getFeatureFileManagement(conn, feature_file_mapping.get("mgnt_table_db"), featurers.getString("txn_id"));   //  select * from " + management_db 
                    String user_type = ceirfunction.getUserType(conn, feature_file_management.get("user_id"), featurers.getString("feature"), featurers.getString("txn_id"));
                    feature = featurers.getString("feature");
                    ArrayList rulelist = new ArrayList<Rule>();
                    String period = ceirfileparser.checkGraceStatus(conn);
                    logger.info("Period is [" + period + "] ");
                    rulelist = ceirfileparser.getRuleDetails(feature, conn, "", period, "", user_type);
                    addCDRInProfileWithRule(feature, conn, rulelist, "", featurers.getString("txn_id"), featurers.getString("sub_feature"), user_type);
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
             System.exit(0);
        }
    }

    public static ResultSet getFeatureFileDetails(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        String limiter = " limit 1 ";
        if (conn.toString().contains("oracle")) {
            limiter = " fetch next 1 rows only ";
        }
        try { // and feature = '"+main_type+"'
            query = "select * from feature_file_config_db where status='Init'  order by sno asc  " + limiter + " ";
            stmt = conn.createStatement();
            logger.info("get feature file details [" + query + "] ");
            return rs = stmt.executeQuery(query);
        } catch (Exception e) {
            logger.info("" + e);
        }
        return rs;
    }

    public static String checkGraceStatus(Connection conn) {
        String period = "";
        String query = null;
        ResultSet rs1 = null;
        Statement stmt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        Date graceDate = null;
        try {
            query = "select value from system_configuration_db where tag='grace_period_end_date'";
            logger.info("Query(checkGraceStatus)is " + query);
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query);
            while (rs1.next()) {
                graceDate = sdf.parse(rs1.getString("value"));
                if (currentDate.compareTo(graceDate) > 0) {
                    period = "post_grace";
                } else {
                    period = "grace";
                }
            }
            logger.info("Period is ..." + period);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }

        }
        return period;
    }

    public static String getOperatorTag(Connection conn, String operator) {
        String operator_tag = "";
        String query = null;
        ResultSet rs1 = null;
        Statement stmt = null;
        try {
            query = "select * from system_config_list_db where tag='OPERATORS' and interp='" + operator + "'";
            logger.info("Query is " + query);
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query);
            while (rs1.next()) {
                operator_tag = rs1.getString("tag_id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }

        }
        return operator_tag;

    }

    public static void addCDRInProfileWithRule(String operator, Connection conn, ArrayList<Rule> rulelist, String operator_tag, String txn_id, String sub_feature, String usertype_name) {

        try {
            if (((sub_feature.equalsIgnoreCase("Register") || sub_feature.equalsIgnoreCase("update") || sub_feature.equalsIgnoreCase("UPLOAD")) ) &&  !operator.equalsIgnoreCase("TYPE_APPROVED")    ) {
               logger.info(" NOTE.. ** NOT FOR TYPE APPROVE" );
                new ConsignmentInsertUpdate().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag, usertype_name);
            } else if (operator.equalsIgnoreCase("consignment") && (sub_feature.equalsIgnoreCase("delete"))) {
                System.out.println("running consignment delete process.");
                new ConsignmentDelete().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag, usertype_name);
            } else if (operator.equalsIgnoreCase("consignment") && (sub_feature.equalsIgnoreCase("approve"))) {
                System.out.println("running consignment approve process.");
                new ApproveConsignment().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag, usertype_name);
            } else if (operator.equalsIgnoreCase("TYPE_APPROVED") && (sub_feature.equalsIgnoreCase("REGISTER"))) {
                System.out.println("running tac register process.");
                new RegisterTac().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag, usertype_name);
            } else if (operator.equalsIgnoreCase("TYPE_APPROVED") && (sub_feature.equalsIgnoreCase("delete"))) {
                System.out.println("running tac delete process.");
                new WithdrawnTac().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag, usertype_name);
            } else if (operator.equalsIgnoreCase("STOCK") && (sub_feature.equalsIgnoreCase("DELETE"))) {
                System.out.println("running stock delete process.");
                new StockDelete().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag, usertype_name, "");
            } else {
                System.out.println("Skipping the process.");
            }

            CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
            ceirfunction.updateFeatureFileStatus(conn, txn_id, 4, operator, sub_feature);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static HashMap<String, String> getStolenRecvryDetails(Connection conn, String txn_id) {
        HashMap<String, String> map = new HashMap<String, String>();
        String errorFilePath = "";
        String query = null;
        String source_type = null;
        String request_type = null;
        String complaint_type = null;
        ResultSet rs1 = null;
        Statement stmt = null;
        String operation = null;
        String txnId = null;
        String reason = null;
        String usertype = null;
        String source = null;
        String divceStatus = null;
        ;
        String user_id = null;
        ;
        try {
            query = "select request_type ,source_type  ,complaint_type  ,txn_id ,user_id from stolenand_recovery_mgmt   where txn_id = '"
                    + txn_id + "'";
            logger.info("Query (getStolenRecvryDetails) PArseris " + query);
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query);
            while (rs1.next()) {
                source_type = rs1.getString("source_type");
                request_type = rs1.getString("request_type");
                complaint_type = rs1.getString("complaint_type");
                txnId = rs1.getString("txn_id");
                user_id = rs1.getString("user_id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs1.close();
            } catch (SQLException e) {
                logger.error("Error.." + e);
            }
        }

        if (request_type.equals("0")) {
            reason = "Stolen";
            usertype = "Lawful Agency";
            operation = "0";
            divceStatus = "10";
        }
        if (request_type.equals("1")) {
            reason = "Recover";
            usertype = "Lawful Agency";
            operation = "1";
            divceStatus = "11";
        }

        if (request_type.equals("2")) {
            reason = "Block";
            usertype = "Operator";
            operation = "0";
            divceStatus = "12";
        }

        if (request_type.equals("3")) {
            reason = "UnBlock";
            usertype = "Operator";
            operation = "1";
            divceStatus = "13";
        }

        if (source_type.equals("4") || source_type.equals("5")) {
            source = "Single";
        } else {
            source = "Bulk";
        }

        map.put("source_type", source_type);
        map.put("request_type", request_type);
        map.put("reason", reason);
        map.put("usertype", usertype);
        map.put("source", source);
        map.put("complaint_type", complaint_type);
        map.put("operation", operation);
        map.put("txnId", txnId);
        map.put("divceStatus", divceStatus);
        map.put("user_id", user_id);

        if (txnId == null) {
            map.put("source_type", "");
            map.put("request_type", "");
            map.put("reason", "");
            map.put("usertype", "");
            map.put("source", "");
            map.put("complaint_type", "");
            map.put("operation", "");
            map.put("txnId", "");
            map.put("divceStatus", "");
            map.put("user_id", "");

        }

        return map;

    }

    public static String getErrorFilePath(Connection conn) {
        String errorFilePath = "";
        String query = null;
        ResultSet rs1 = null;
        Statement stmt = null;

        try {
            query = "select * from system_configuration_db where tag='system_error_filepath'";
            logger.info("Query (getErrorFilePath) PArseris " + query);
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query);
            while (rs1.next()) {
                errorFilePath = rs1.getString("value");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs1.close();
            } catch (SQLException e) {

                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }

        }
        return errorFilePath;

    }

    public static int getCustomData(Connection conn, String txn_id) {
        String query = null;
        Statement stmt = null;
        ResultSet rs1 = null;
        String rslt = "";
        int rst = 0;
        query = " select user_type from  stock_mgmt   where txn_id =  '" + txn_id + "'  ";
        logger.info("getCustomData query .." + query);
        try {
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query);
            while (rs1.next()) {
                rslt = rs1.getString(1);
            }
            conn.commit();
            if (rslt.equalsIgnoreCase("Custom")) {
                logger.info("IT is  Custom");
                rst = 1;
            }
        } catch (SQLException e) {
            logger.error("Error.." + e);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }

        }
        return rst;
    }

    public static void updateRawData(Connection conn, String operator, String id, String status) {
        String query = null;
        Statement stmt = null;
        query = "update " + operator + "_raw" + " set status='" + status + "' where sno='" + id + "'";
        logger.info("updateRawData query .." + query);
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            logger.error("Error.." + e);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }
        }

    }

    public static ArrayList getRuleDetails(String operator, Connection conn, String operator_tag, String period, String feature, String usertype_name) {
        ArrayList rule_details = new ArrayList<Rule>();
        String query = null;
        ResultSet rs1 = null;
        Statement stmt = null;
        try {
            query = "select a.id as rule_id,a.name as rule_name,b.output as output,b.grace_action, b.post_grace_action, b.failed_rule_action_grace, b.failed_rule_action_post_grace from rule_engine a, rule_engine_mapping b where  a.name=b.name  and a.state='FULL' and b.feature='"
                    + operator + "' and b.user_type='" + usertype_name + "'  and  b." + period + "_action !='NA'       order by b.rule_order asc";

            logger.info("Query is  (getRuleDetails) " + query);
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query);
            if (!rs1.isBeforeFirst()) {
                query = "select a.id as rule_id,a.name as rule_name,b.output as output,b.grace_action, b.post_grace_action, b.failed_rule_action_grace, b.failed_rule_action_post_grace from rule_engine a, rule_engine_mapping b where  a.name=b.name  and a.state='FULL' and b.feature='"
                        + operator + "' and b.user_type='default' order by b.rule_order asc";
                stmt = conn.createStatement();
                rs1 = stmt.executeQuery(query);
            }
            while (rs1.next()) {
                if (rs1.getString("rule_name").equalsIgnoreCase("IMEI_LENGTH")) {
                    if (operator_tag.equalsIgnoreCase("GSM")) {
                        // Rule rule = new
                        // Rule(rs1.getString("rule_name"),rs1.getString("output"),rs1.getString("rule_id"),period,
                        // rs1.getString(period+"_action"));
                        Rule rule = new Rule(rs1.getString("rule_name"), rs1.getString("output"),
                                rs1.getString("rule_id"), period, rs1.getString(period + "_action"),
                                rs1.getString("failed_rule_action_" + period));

                        rule_details.add(rule);
                    }
                } else {
                    Rule rule = new Rule(rs1.getString("rule_name"), rs1.getString("output"), rs1.getString("rule_id"),
                            period, rs1.getString(period + "_action"), rs1.getString("failed_rule_action_" + period));
                    rule_details.add(rule);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs1.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }
        }
        return rule_details;
    }

    public static ResultSet operatorDetails(Connection conn, String operator) {
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        try {
            query = "select * from rep_schedule_config_db where operator='" + operator + "'";
            stmt = conn.createStatement();
            return rs = stmt.executeQuery(query);
        } catch (Exception e) {
            logger.info("" + e);
        }
        return rs;
    }

    public static void updateLastStatuSno(Connection conn, String operator, int id, int limit) {
        String query = null;
        Statement stmt = null;
        query = "update " + operator + "_raw" + " set status='Start' where sno>'" + id + "'"; //
        logger.info(query);
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            logger.error("Error.." + e);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }
        }
    }

    public static void updateRawLastSno(Connection conn, String operator, int sno) {
        String query = null;
        Statement stmt = null;
        query = "update rep_schedule_config_db set last_upload_sno=" + sno + " where operator='" + operator + "'";
        logger.info(" update rep_schedule_config_db .. " + query);
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            logger.error("Error.." + e);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }
        }
    }

    public static int imeiCountfromRawTable(Connection conn, String txn_id, String operator) {
        int rsslt = 0;
        String query = null;
        Statement stmt = null;
        query = "select count(*) as cnt from  " + operator + "_raw  where txn_id ='" + txn_id + "'  ";
        logger.info(" select imeiCountfromRawTable .. " + query);
        try {
            ResultSet rs = null;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                rsslt = rs.getInt("cnt");
            }
            conn.commit();
        } catch (SQLException e) {
            logger.error("Error.." + e);
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("Error.." + e);
            }
        }

        return rsslt;
    }

}

//     public static  void addCDRInProfileWithRule(String operator, Connection conn, ArrayList<Rule> rulelist,
//            String operator_tag, String txn_id, String sub_feature, String usertype_name) {
//        ErrorFileGenrator errFile = new ErrorFileGenrator();
//        String query = null;
//        ResultSet rs = null;
//        ResultSet rs1 = null;
//        Statement stmt = null; // for rs
//        Statement stmt0 = null; // for rs1
//        Statement stmt1 = null; // devce_dv batch
//        Statement stmt2 = null; // device_imprter_db btch
//        Statement raw_stmt = null; // update raw table to cmplete
//        Statement stmt3 = null; // custom db
//        Statement stmt4 = null; // greylist_db
//        Statement stmt5 = null; // greylisthistory_db
//        String table_name = null;
//        String query_type = null;
//
//        String raw_query = null;
//
//        String imei = null;
//        String msisdn = null;
//        String update_my_record = null;
//        int output = 0;
//        String my_query = null;
//        String device_db_query = null;
//        String device_custom_db_qry = null;
//        String device_greylist_db_qry = null;
//        String device_greylist_History_db_qry = null;
//
//        HashMap<String, String> my_rule_detail = new HashMap<String, String>();;
//        HashMap<String, String> stolnRcvryDetails = new HashMap<String, String>();
//
//        String failed_rule = null;
//        String failed_rule_name = "";
//        String failed_rule_id = "";
//        String action = "";
//        String period = "";
//        int parser_base_limit = 0;
//        int old_sno = 0;
//        int update_sno = 0;
//        final String logPath = "./";
//        String fileName = null;
//        File file = null;
//        String log = null;
//        int split_upload_batch_no = 1; // it should be dymnamic
//        int split_upload_batch_count = 0;
//        int rrslt = 0;
//        int countError = 0;
//        int stolenRecvryBlock = 0;
//        try {
//            stolnRcvryDetails.put("operator", operator);
//            ResultSet my_result_set = operatorDetails(conn, operator);
//            if (my_result_set.next()) {
//                parser_base_limit = my_result_set.getInt("split_upload_set_no");
//                old_sno = my_result_set.getInt("last_upload_sno");
//            }
//            // query = "select * from "+operator+"_raw where sno>"+old_sno+" and             // status='Init' order by sno asc FETCH FIRST "+parser_base_limit+" ROWS WITH             // TIES ";
//            query = "select * from " + operator + "_raw where sno>" + old_sno + " and txn_id='" + txn_id
//                    + "' and status='Init' order by sno asc ";
//            stmt = conn.createStatement();
//            logger.info("Get Data (addCDRInProfileWithRule)Query.. " + query);
//            rs = stmt.executeQuery(query);
//            HashMap<String, String> device_info = new HashMap<String, String>();
//            RuleFilter rule_filter = new RuleFilter();
//            // updateLastStatuSno(conn, operator, old_sno, parser_base_limit); //update _raw
//            // db status = start
//
//            // CDR File Writer
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            fileName = "CEIR_" + operator + "_File_" + sdf.format(Calendar.getInstance().getTime()) + ".csv";
//            logger.info(" File NAme as CEIR.myWriter(log) .." + fileName);
//            file = new File(logPath);
//            if (!file.exists()) {
//                file.mkdir();
//                logger.info("File not exists");
//            }
//            file = new File(logPath + fileName);
//            FileWriter myWriter;
//            if (file.exists()) {
//                myWriter = new FileWriter(file, true);
//            } else {
//                myWriter = new FileWriter(file);
//            }
//            CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
//            period = checkGraceStatus(conn);
//            HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
//            feature_file_mapping = ceirfunction.getFeatureMapping(conn, operator, usertype_name); // select * from
//            // feature_mapping_db
//            HashMap<String, String> feature_file_management = new HashMap<String, String>();
//            feature_file_management = ceirfunction.getFeatureFileManagement(conn,
//                    feature_file_mapping.get("mgnt_table_db"), txn_id); // select * from " + management_db + " where
//            if (operator.equalsIgnoreCase("Stolen") || operator.equalsIgnoreCase("Recovery") || operator.equalsIgnoreCase("Block") || operator.equalsIgnoreCase("Unblock")) {
//                stolnRcvryDetails = getStolenRecvryDetails(conn, txn_id);
//                stolenRecvryBlock = 1;
//            } else {
//
//            }
//
//            logger.info(" hare   200  values are   -- OPERATOR/feature--... " + operator + ".--register--." + sub_feature + "   --update-- " + sub_feature);
//            if ((sub_feature.equalsIgnoreCase("register") || sub_feature.equalsIgnoreCase("update") || sub_feature.equalsIgnoreCase("upload"))) {
//                logger.info("Entered for ...  " + operator);
//
//                 errFile.gotoErrorFile(txn_id, "DEVICETYPE,DeviceIdType,MultipleSIMStatus,S/NofDevice,IMEI/ESN/MEID,Devicelaunchdate,DeviceStatus, Error Code ,Error Message ");
//                while (rs.next()) {
//                    logger.info("Served IMEI 1 =" + rs.getString("IMEIESNMEID"));
//                    device_info.put("DeviceIdType", rs.getString("DeviceIdType"));
//                    device_info.put("IMEIESNMEID", rs.getString("IMEIESNMEID"));
//                    device_info.put("DeviceType", rs.getString("DeviceType"));
//                    device_info.put("MultipleSIMStatus", rs.getString("MultipleSIMStatus"));
//                    device_info.put("SNofDevice", rs.getString("SNofDevice"));
//                    device_info.put("Devicelaunchdate", rs.getString("Devicelaunchdate"));
//                    device_info.put("DeviceStatus", rs.getString("DeviceStatus"));
//                    device_info.put("operator", operator);
//                    device_info.put("feature", operator);
//                    device_info.put("file_name", rs.getString("file_name"));
//                    device_info.put("operator_tag", operator_tag);
//                    device_info.put("txn_id", txn_id);
//                    // Event writer into file
//                    try {
//                        if (file.length() == 0) {
//                            new LogWriter().writeFeatureEvents(myWriter, "IMEIESNMEID", "DeviceType", "MultipleSIMStatus", "SNofDevice", "Devicelaunchdate", "DeviceStatus", "txn_id", "operator", "file_name", "type", "rule_id", "rule_name", "status", "time");
//                        } else {
//                            new LogWriter().writeFeatureEvents(myWriter, rs.getString("IMEIESNMEID"), rs.getString("DeviceType"), rs.getString("MultipleSIMStatus"), rs.getString("SNofDevice"), rs.getString("Devicelaunchdate"), rs.getString("DeviceStatus"), txn_id, operator, rs.getString("file_name"), "Init", "", "", "", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//                        }
//                    } catch (Exception e) {
//                        logger.info(" exception.. at   new LogWriter().writeFeatureEvents(myWriter, ,,," + e);
//                    }
//                    my_rule_detail = rule_filter.getMyFeatureRule(conn, device_info, rulelist, myWriter);
//                    if (my_rule_detail.get("errorFlag").equals("0")) {
//                        String fileArray = device_info.get("DeviceType") + "," + device_info.get("DeviceIdType") + "," + device_info.get("MultipleSIMStatus") + "," + device_info.get("SNofDevice") + "," + device_info.get("IMEIESNMEID") + "," + device_info.get("Devicelaunchdate") + "," + device_info.get("DeviceStatus") + "";
//                        errFile.gotoErrorFile(txn_id, fileArray);
//                        logger.info("  ");
//                    } else {
//                        countError++;
//                    }
//
//                    split_upload_batch_count++;
//
//                    if (split_upload_batch_count == split_upload_batch_no) {
//                        // conn.commit();
//                        split_upload_batch_count = 0;
//                    }
//                    update_sno = Integer.parseInt(rs.getString("sno"));
//                }
//
//                if (update_sno != 0) {
//                    updateRawLastSno(conn, operator, update_sno);
//
//                }
//
//                int imeiCountfromRaw = imeiCountfromRawTable(conn, txn_id, operator);
//                logger.info("count error.. " + countError + " , total imei in mgmt db .. " + imeiCountfromRaw);
//
//                String error_file_path = getErrorFilePath(conn) + txn_id + "/" + txn_id + "_error.csv";
//                if (countError == 0) {
//                    logger.info("File delete as no Error");
//                    File myObj = new File(error_file_path);
//                    myObj.delete();
//                }
//
//                File errorfile = new File(error_file_path);
//                logger.info("File path is.. " + error_file_path + " , IF Error file exists .. " + errorfile.exists());
//                if (errorfile.exists()) {
//                    ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, operator, sub_feature); // update	 	// web_action_db
//                    ceirfunction.consignmentUpdateViaApi(conn, txn_id, 1, stolnRcvryDetails, operator, 1);
//                    ceirfunction.updateFeatureManagementStatus(conn, txn_id, 2, feature_file_mapping.get("mgnt_table_db"), operator); // 2 - pending approval , 3 -reject by                   // sys
//                    ceirfunction.addFeatureFileConfigDetails(conn, "update", operator, sub_feature, txn_id, "", "REJECTED_BY_SYSTEM", "");
//
//                } else {
//                    rrslt = getCustomData(conn, txn_id); // select user_type from stock_mgmt where txn_id
//                    logger.info(".getCustomData rslt ." + rrslt);
//                    stmt0 = conn.createStatement();
//                    stmt1 = conn.createStatement();
//                    stmt2 = conn.createStatement();
//                    stmt3 = conn.createStatement();
//                    stmt4 = conn.createStatement();
//                    stmt5 = conn.createStatement();
//
//                    split_upload_batch_count = 0;
//
//                    logger.info("  inserting....");
//                    rs1 = stmt0.executeQuery(query);
//
//                    String dateNow = "";
//                    if (conn.toString().contains("oracle")) {
//                        dateNow = new SimpleDateFormat("dd-MMM-YY").format(new Date());
//                    } else {
//                        dateNow = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
//                    }
//                    logger.info(".output_device_db  ." + feature_file_mapping.get("output_device_db"));
//
//                    while (rs1.next()) {
//                        split_upload_batch_count++;
//
//                        String dvsStatus = rs1.getString("DeviceStatus");
//                        if (stolenRecvryBlock == 1) {
//                            dvsStatus = stolnRcvryDetails.get("divceStatus");
//                        }
//
//                        my_query = "insert into " + feature_file_mapping.get("output_device_db")
//                                + " (device_id_type,created_on,device_launch_date,device_status,"
//                                + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id) "
//                                + "values(" + "'" + rs1.getString("DeviceIdType") + "'," + "'" + dateNow + "'," /// "dd-MMM-YY"                              
//                                + "'" + rs1.getString("Devicelaunchdate") + "', '" + dvsStatus + "'  ,'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID")
//                                + "'," + "'" + dateNow + "'," + "'" + rs1.getString("MultipleSIMStatus") + "'," + "'"
//                                + period + "'," + "'" + rs1.getString("SNofDevice") + "'," + "'" + txn_id + "'," + ""
//                                + feature_file_management.get("user_id") + "" + ")";
//
//                        device_db_query = "insert into device_db (device_id_type,created_on,device_launch_date,device_status,"
//                                + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id) " // user_id
//                                + "values('" + rs1.getString("DeviceIdType") + "'," + "'" + dateNow + "'," + "'"
//                                + rs1.getString("Devicelaunchdate") + "'," + "'" + rs1.getString("DeviceStatus") + "',"
//                                + "'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID") + "',"
//                                + "'" + dateNow + "'," + "'" + rs1.getString("MultipleSIMStatus") + "',"
//                                + "'" + (period == "grace" ? 0 : 1) + "'," + "'" + rs1.getString("SNofDevice") + "',"
//                                + "'" + txn_id + "'  "
//                                // + " , " + feature_file_management.get("user_id") + ""
//                                + ")";
//
//                        device_custom_db_qry = "insert into device_custom_db (device_id_type,created_on,device_launch_date,device_status,"
//                                + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id) " // user_id
//                                + "values('" + rs1.getString("DeviceIdType") + "'," + "'" + dateNow + "'," + "'"
//                                + rs1.getString("Devicelaunchdate") + "'," + "'" + rs1.getString("DeviceStatus") + "',"
//                                + "'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID") + "',"
//                                + "'" + dateNow + "'," + "'" + rs1.getString("MultipleSIMStatus") + "',"
//                                // +"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
//                                // +"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
//                                + "'" + (period == "grace" ? 0 : 1) + "'," + "'" + rs1.getString("SNofDevice") + "',"
//                                + "'" + txn_id + "'  "
//                                // + " , " + feature_file_management.get("user_id") + ""
//                                + ")";
//
//                        if (stolenRecvryBlock == 1) {
//                            if (stolnRcvryDetails.get("operation").equals("0")) {
//                                device_greylist_db_qry = "insert into   greylist_db (created_on , imei, user_id , txn_id , mode_type  , request_type, user_type  , complain_type)   "
//                                        + "values(           " + "'" + dateNow + "'," + "'" + rs1.getString("IMEIESNMEID")
//                                        + "'," + " ( select username from users where users.id=  "
//                                        + feature_file_management.get("user_id") + "  )  ,  " + " '" + txn_id + "', " + "'"
//                                        + stolnRcvryDetails.get("source") + "'," + "'" + stolnRcvryDetails.get("reason")
//                                        + "'," + "'" + stolnRcvryDetails.get("usertype") + "'," + "'"
//                                        + stolnRcvryDetails.get("complaint_type") + "'  " + ")";
//
//                            } else {
//                                device_greylist_db_qry = "delete from greylist_db where imei  = '"
//                                        + rs1.getString("IMEIESNMEID") + "' ";
//
//                                my_query = "    delete from    " + feature_file_mapping.get("output_device_db")
//                                        + " where imei_esn_meid  = '" + rs1.getString("IMEIESNMEID") + "'";
//
//                            }
//                        
//                        device_greylist_History_db_qry = "insert into   greylist_db_history (created_on , imei, user_id , txn_id , mode_type  , request_type, user_type  , complain_type ,operation)   "
//                                + "values(           " + "'" + dateNow + "'," + "'" + rs1.getString("IMEIESNMEID")
//                                + "'," + " ( select username from users where users.id=  "
//                                + feature_file_management.get("user_id") + "  )  ,  " + " '" + txn_id + "', " + "'"
//                                + stolnRcvryDetails.get("source") + "'," + "'" + stolnRcvryDetails.get("reason") + "',"
//                                + "'" + stolnRcvryDetails.get("usertype") + "'," + "'"
//                                + stolnRcvryDetails.get("complaint_type") + "' , " + "'"
//                                + stolnRcvryDetails.get("operation") + "'  " + ")";
//                        }
//                        //
//                        // stmt1.addBatch(my_query);
//                        // stmt2.addBatch(device_db_query);
//                        // logger.info( "split_upload_batch_count == " + split_upload_batch_count + "..
//                        // split_upload_batch_no== .." + split_upload_batch_no);
//                        // if (split_upload_batch_count == split_upload_batch_no) {
//                        logger.info("my Qury....." + my_query);
//                        stmt1.executeUpdate(my_query); // added
//
//                        logger.info("device_db_query.." + device_db_query);
//                        stmt2.executeUpdate(device_db_query); // addedd/
//                        if (rrslt != 0) {
//                            logger.info("Executing customDb qury");
//                            logger.info("device_custom_db_qry..." + device_custom_db_qry);
//                            stmt3.executeUpdate(device_custom_db_qry);
//                        }
//                        if (stolenRecvryBlock == 1) {
//                            logger.info("device_greylist_db_qry.." + device_greylist_db_qry);
//                            logger.info("device_greylist_History_db_qry... " + device_greylist_History_db_qry);
//                            stmt4.executeUpdate(device_greylist_db_qry);
//                            stmt5.executeUpdate(device_greylist_History_db_qry);
//                        }
//
//                        split_upload_batch_count = 0;
//                        // }
//                    }
//                    logger.info("entered outside");
//
////					stmt1.executeBatch();
////					stmt2.executeBatch();
//                    // conn.commit();
//                    stmt.close();
//                    stmt0.close();
//                    stmt1.close();
//                    stmt2.close();
//                    stmt3.close();
//                    stmt4.close();
//                    stmt5.close();
//
//                    logger.info("stmts closed");
//                    logger.info("  featuer is " + operator + "   it will update web_action_db() too ");
//                    ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, operator, sub_feature);
//                    ceirfunction.consignmentUpdateViaApi(conn, txn_id, 0, stolnRcvryDetails, operator, 0);
//                    ceirfunction.updateFeatureManagementStatus(conn, txn_id, 3,                            feature_file_mapping.get("mgnt_table_db"), operator);
//                    ceirfunction.addFeatureFileConfigDetails(conn, "update", operator, sub_feature, txn_id, "", "Complete", "");
//                }
//                raw_stmt = conn.createStatement();
//                raw_query = "update " + operator + "_raw" + " set status='Complete' where txn_id='" + txn_id + "'";
//                logger.info("updating raw table with cmplte.." + raw_query);
//                raw_stmt.executeUpdate(raw_query);
//                // conn.commit();
//                raw_stmt.close();
//
//            }  
//            else if(operator.equalsIgnoreCase("consignment") &&(sub_feature.equalsIgnoreCase("delete"))){
//				System.out.println("running consignment delete process.");
//				new ConsignmentDelete().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag);
//			}else if(operator.equalsIgnoreCase("consignment") &&(sub_feature.equalsIgnoreCase("approve"))){
//				System.out.println("running consignment approve process.");
//				new ApproveConsignment().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag);
//			}else if(operator.equalsIgnoreCase("TYPE_APPROVED") &&(sub_feature.equalsIgnoreCase("REGISTER"))){
//				System.out.println("running tac register process.");
//				new RegisterTac().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag);
//			}else if(operator.equalsIgnoreCase("TYPE_APPROVED") &&(sub_feature.equalsIgnoreCase("delete"))){
//				System.out.println("running tac delete process.");
//				new WithdrawnTac().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag);
//			}else {
//				System.out.println("Skipping the process.");
//			}
//            
//            
//            
//            
////            if(sub_feature.equalsIgnoreCase("delete")) {
////                logger.info("Delete Functionality ") ;
////                
////            }
//            
//            
//
//
//        } catch (Exception e) {
//            logger.error("Error.." + e);
//        } finally {
//            try {
//
//                c onn.close();
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                logger.error("Error.." + e);
//            }
//        }
//    }
//    public public static  void main(String args[]) {
//
//        logger.info(" ");
//        logger.info(" ");
//        logger.info(" ");
//        // String main_type = args[0];
//        String feature = null;
//        Connection conn = null;
//        conn = (Connection) new com.functionapps.db.MySQLConnection().getConnection();
//        ResultSet featurers = getFeatureFileDetails(conn);
//        try {
////            if (featurers.next()) {
////                logger.info("CEIRFeatureFileParser started " + featurers.getString("txn_id"));
////                feature = featurers.getString("feature");
////                ArrayList rulelist = new ArrayList<Rule>();
////                String period = checkGraceStatus(conn);
////                rulelist = getRuleDetails(feature, conn, "GSM", period, "", featurers.getString("usertype_name"));
////                addCDRInProfileWithRule(feature, conn, rulelist, "", featurers.getString("txn_id"), featurers.getString("sub_feature"), featurers.getString("usertype_name"));
////            }
//            
//            
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            logger.error("Error.." + e);
//        }
//    }
