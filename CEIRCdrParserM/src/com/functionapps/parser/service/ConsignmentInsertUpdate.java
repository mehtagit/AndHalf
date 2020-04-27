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
//import org.apache.log4j.Logger;
import java.util.logging.Logger;

import com.functionapps.log.LogWriter;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.CEIRFeatureFileParser;
import com.functionapps.parser.Rule;
import com.functionapps.parser.RuleFilter;

public class ConsignmentInsertUpdate {
	static Logger logger = Logger.getLogger("ConsignmentInsertUpdate");
//                                                                                            
  public  void process(Connection conn,String operator,String sub_feature,   ArrayList<Rule> rulelist,String txn_id,  String operator_tag,   String usertype_name) {

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
String table_name = null;
String query_type = null;

String raw_query = null;

String imei = null;
String msisdn = null;
String update_my_record = null;
int output = 0;
String my_query = null;
String device_db_query = null;
String device_custom_db_qry = null;
String device_greylist_db_qry = null;
String device_greylist_History_db_qry = null;

HashMap<String, String> my_rule_detail = new HashMap<String, String>();;
HashMap<String, String> stolnRcvryDetails = new HashMap<String, String>();

String failed_rule = null;
String failed_rule_name = "";
String failed_rule_id = "";
String action = "";
String period = "";
int parser_base_limit = 0;
int old_sno = 0;
int update_sno = 0;
final String logPath = "./";
String fileName = null;
File file = null;
String log = null;
int split_upload_batch_no = 1; // it should be dymnamic
int split_upload_batch_count = 0;
int rrslt = 0;
int countError = 0;
int stolenRecvryBlock = 0;
       CEIRFeatureFileParser cEIRFeatureFileParser = new CEIRFeatureFileParser();
try {
  stolnRcvryDetails.put("operator", operator);
  ResultSet my_result_set = cEIRFeatureFileParser.operatorDetails(conn, operator);
  if (my_result_set.next()) {
      parser_base_limit = my_result_set.getInt("split_upload_set_no");
      old_sno = my_result_set.getInt("last_upload_sno");
  }
  // query = "select * from "+operator+"_raw where sno>"+old_sno+" and             // status='Init' order by sno asc FETCH FIRST "+parser_base_limit+" ROWS WITH             // TIES ";
  query = "select * from " + operator + "_raw where sno>" + old_sno + " and txn_id='" + txn_id
          + "' and status='Init' order by sno asc ";
  stmt = conn.createStatement();
  logger.info("Get Data (addCDRInProfileWithRule)Query.. " + query);
  rs = stmt.executeQuery(query);
  HashMap<String, String> device_info = new HashMap<String, String>();
  RuleFilter rule_filter = new RuleFilter();
  // updateLastStatuSno(conn, operator, old_sno, parser_base_limit); //update _raw
  // db status = start

  // CDR File Writer
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
  CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
  period = cEIRFeatureFileParser.checkGraceStatus(conn);
  HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
  feature_file_mapping = ceirfunction.getFeatureMapping(conn, operator, usertype_name); // select * from 
  // feature_mapping_db
  HashMap<String, String> feature_file_management = new HashMap<String, String>();
  feature_file_management = ceirfunction.getFeatureFileManagement(conn,
          feature_file_mapping.get("mgnt_table_db"), txn_id); // select * from " + management_db + " where
  if (operator.equalsIgnoreCase("Stolen") || operator.equalsIgnoreCase("Recovery") || operator.equalsIgnoreCase("Block") || operator.equalsIgnoreCase("Unblock")) {
      stolnRcvryDetails = cEIRFeatureFileParser.getStolenRecvryDetails(conn, txn_id);
      stolenRecvryBlock = 1;
  } else {

  }

  logger.info(" hare   200  values are   -- OPERATOR/feature--... " + operator + ".--register--." + sub_feature + "   --update-- " + sub_feature);
  if ((sub_feature.equalsIgnoreCase("register") || sub_feature.equalsIgnoreCase("update") || sub_feature.equalsIgnoreCase("upload"))) {
      logger.info("Entered for ...  " + operator);

       errFile.gotoErrorFile(txn_id, "DEVICETYPE,DeviceIdType,MultipleSIMStatus,S/NofDevice,IMEI/ESN/MEID,Devicelaunchdate,DeviceStatus, Error Code ,Error Message ");
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
          // Event writer into file
          try {
              if (file.length() == 0) {
                  new LogWriter().writeFeatureEvents(myWriter, "IMEIESNMEID", "DeviceType", "MultipleSIMStatus", "SNofDevice", "Devicelaunchdate", "DeviceStatus", "txn_id", "operator", "file_name", "type", "rule_id", "rule_name", "status", "time");
              } else {
                  new LogWriter().writeFeatureEvents(myWriter, rs.getString("IMEIESNMEID"), rs.getString("DeviceType"), rs.getString("MultipleSIMStatus"), rs.getString("SNofDevice"), rs.getString("Devicelaunchdate"), rs.getString("DeviceStatus"), txn_id, operator, rs.getString("file_name"), "Init", "", "", "", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
              }
          } catch (Exception e) {
              logger.info(" exception.. at   new LogWriter().writeFeatureEvents(myWriter, ,,," + e);
          }
          my_rule_detail = rule_filter.getMyFeatureRule(conn, device_info, rulelist, myWriter);
          if (my_rule_detail.get("errorFlag").equals("0")) {
              String fileArray = device_info.get("DeviceType") + "," + device_info.get("DeviceIdType") + "," + device_info.get("MultipleSIMStatus") + "," + device_info.get("SNofDevice") + "," + device_info.get("IMEIESNMEID") + "," + device_info.get("Devicelaunchdate") + "," + device_info.get("DeviceStatus") + "";
              errFile.gotoErrorFile(txn_id, fileArray);
              logger.info("  ");
          } else {
              countError++;
          }

          split_upload_batch_count++;

          if (split_upload_batch_count == split_upload_batch_no) {
              // conn.commit();
              split_upload_batch_count = 0;
          }
          update_sno = Integer.parseInt(rs.getString("sno"));
      }

      if (update_sno != 0) {
          cEIRFeatureFileParser.updateRawLastSno(conn, operator, update_sno);

      }

      int imeiCountfromRaw = cEIRFeatureFileParser.imeiCountfromRawTable(conn, txn_id, operator);
      logger.info("count error.. " + countError + " , total imei in mgmt db .. " + imeiCountfromRaw);

      String error_file_path = cEIRFeatureFileParser.getErrorFilePath(conn) + txn_id + "/" + txn_id + "_error.csv";
      if (countError == 0) {
          logger.info("File delete as no Error");
          File myObj = new File(error_file_path);
          myObj.delete();
      }

      File errorfile = new File(error_file_path);
      logger.info("File path is.. " + error_file_path + " , IF Error file exists .. " + errorfile.exists());
      if (errorfile.exists()) {
          ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, operator, sub_feature); // update	 	// web_action_db
          ceirfunction.consignmentUpdateViaApi(conn, txn_id, 1, stolnRcvryDetails, operator, 1);
          ceirfunction.updateFeatureManagementStatus(conn, txn_id, 2, feature_file_mapping.get("mgnt_table_db"), operator); // 2 - pending approval , 3 -reject by                   // sys
          ceirfunction.addFeatureFileConfigDetails(conn, "update", operator, sub_feature, txn_id, "", "REJECTED_BY_SYSTEM", "");

      } else {
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

          String dateNow = "";
          if (conn.toString().contains("oracle")) {
              dateNow = new SimpleDateFormat("dd-MMM-YY").format(new Date());
          } else {
              dateNow = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
          }
          logger.info(".output_device_db  ." + feature_file_mapping.get("output_device_db"));

          while (rs1.next()) {
              split_upload_batch_count++;

              String dvsStatus = rs1.getString("DeviceStatus");
              if (stolenRecvryBlock == 1) {
                  dvsStatus = stolnRcvryDetails.get("divceStatus");
              }

              my_query = "insert into " + feature_file_mapping.get("output_device_db")
                      + " (device_id_type,created_on,device_launch_date,device_status,"
                      + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id) "
                      + "values(" + "'" + rs1.getString("DeviceIdType") + "'," + "'" + dateNow + "'," /// "dd-MMM-YY"                              
                      + "'" + rs1.getString("Devicelaunchdate") + "', '" + dvsStatus + "'  ,'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID")
                      + "'," + "'" + dateNow + "'," + "'" + rs1.getString("MultipleSIMStatus") + "'," + "'"
                      + period + "'," + "'" + rs1.getString("SNofDevice") + "'," + "'" + txn_id + "'," + ""
                      + feature_file_management.get("user_id") + "" + ")";

              device_db_query = "insert into device_db (device_id_type,created_on,device_launch_date,device_status,"
                      + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id) " // user_id
                      + "values('" + rs1.getString("DeviceIdType") + "'," + "'" + dateNow + "'," + "'"
                      + rs1.getString("Devicelaunchdate") + "'," + "'" + rs1.getString("DeviceStatus") + "',"
                      + "'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID") + "',"
                      + "'" + dateNow + "'," + "'" + rs1.getString("MultipleSIMStatus") + "',"
                      + "'" + (period == "grace" ? 0 : 1) + "'," + "'" + rs1.getString("SNofDevice") + "',"
                      + "'" + txn_id + "'  "
                      // + " , " + feature_file_management.get("user_id") + ""
                      + ")";

              device_custom_db_qry = "insert into device_custom_db (device_id_type,created_on,device_launch_date,device_status,"
                      + "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id) " // user_id
                      + "values('" + rs1.getString("DeviceIdType") + "'," + "'" + dateNow + "'," + "'"
                      + rs1.getString("Devicelaunchdate") + "'," + "'" + rs1.getString("DeviceStatus") + "',"
                      + "'" + rs1.getString("DeviceType") + "'," + "'" + rs1.getString("IMEIESNMEID") + "',"
                      + "'" + dateNow + "'," + "'" + rs1.getString("MultipleSIMStatus") + "',"
                      // +"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
                      // +"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
                      + "'" + (period == "grace" ? 0 : 1) + "'," + "'" + rs1.getString("SNofDevice") + "',"
                      + "'" + txn_id + "'  "
                      // + " , " + feature_file_management.get("user_id") + ""
                      + ")";

              if (stolenRecvryBlock == 1) {
                  if (stolnRcvryDetails.get("operation").equals("0")) {
                      device_greylist_db_qry = "insert into   greylist_db (created_on , imei, user_id , txn_id , mode_type  , request_type, user_type  , complain_type)   "
                              + "values(           " + "'" + dateNow + "'," + "'" + rs1.getString("IMEIESNMEID")
                              + "'," + " ( select username from users where users.id=  "
                              + feature_file_management.get("user_id") + "  )  ,  " + " '" + txn_id + "', " + "'"
                              + stolnRcvryDetails.get("source") + "'," + "'" + stolnRcvryDetails.get("reason")
                              + "'," + "'" + stolnRcvryDetails.get("usertype") + "'," + "'"
                              + stolnRcvryDetails.get("complaint_type") + "'  " + ")";

                  } else {
                      device_greylist_db_qry = "delete from greylist_db where imei  = '"
                              + rs1.getString("IMEIESNMEID") + "' ";

                      my_query = "    delete from    " + feature_file_mapping.get("output_device_db")
                              + " where imei_esn_meid  = '" + rs1.getString("IMEIESNMEID") + "'";

                  }
              
              device_greylist_History_db_qry = "insert into   greylist_db_history (created_on , imei, user_id , txn_id , mode_type  , request_type, user_type  , complain_type ,operation)   "
                      + "values(           " + "'" + dateNow + "'," + "'" + rs1.getString("IMEIESNMEID")
                      + "'," + " ( select username from users where users.id=  "
                      + feature_file_management.get("user_id") + "  )  ,  " + " '" + txn_id + "', " + "'"
                      + stolnRcvryDetails.get("source") + "'," + "'" + stolnRcvryDetails.get("reason") + "',"
                      + "'" + stolnRcvryDetails.get("usertype") + "'," + "'"
                      + stolnRcvryDetails.get("complaint_type") + "' , " + "'"
                      + stolnRcvryDetails.get("operation") + "'  " + ")";
              }
              //
              // stmt1.addBatch(my_query);
              // stmt2.addBatch(device_db_query);
              // logger.info( "split_upload_batch_count == " + split_upload_batch_count + "..
              // split_upload_batch_no== .." + split_upload_batch_no);
              // if (split_upload_batch_count == split_upload_batch_no) {
              logger.info("my Qury....." + my_query);
              stmt1.executeUpdate(my_query); // added

              logger.info("device_db_query.." + device_db_query);
              stmt2.executeUpdate(device_db_query); // addedd/
              if (rrslt != 0) {
                  logger.info("Executing customDb qury");
                  logger.info("device_custom_db_qry..." + device_custom_db_qry);
                  stmt3.executeUpdate(device_custom_db_qry);
              }
              if (stolenRecvryBlock == 1) {
                  logger.info("device_greylist_db_qry.." + device_greylist_db_qry);
                  logger.info("device_greylist_History_db_qry... " + device_greylist_History_db_qry);
                  stmt4.executeUpdate(device_greylist_db_qry);
                  stmt5.executeUpdate(device_greylist_History_db_qry);
              }

              split_upload_batch_count = 0;
              // }
          }
          logger.info("entered outside");

//			stmt1.executeBatch();
//			stmt2.executeBatch();
          // conn.commit();
          stmt.close();
          stmt0.close();
          stmt1.close();
          stmt2.close();
          stmt3.close();
          stmt4.close();
          stmt5.close();

          logger.info("stmts closed");
          logger.info("  featuer is " + operator + "   it will update web_action_db() too ");
          ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, operator, sub_feature);
          ceirfunction.consignmentUpdateViaApi(conn, txn_id, 0, stolnRcvryDetails, operator, 0);
          ceirfunction.updateFeatureManagementStatus(conn, txn_id, 3,                            feature_file_mapping.get("mgnt_table_db"), operator);
          ceirfunction.addFeatureFileConfigDetails(conn, "update", operator, sub_feature, txn_id, "", "Complete", "");
      }
      raw_stmt = conn.createStatement();
      raw_query = "update " + operator + "_raw" + " set status='Complete' where txn_id='" + txn_id + "'";
      logger.info("updating raw table with cmplte.." + raw_query);
      raw_stmt.executeUpdate(raw_query);
      // conn.commit();
      raw_stmt.close();

  }  
  


} catch (Exception e) {
  logger.info("Error.." + e);
} finally {
  try {

//      c onn.close();
  } catch (Exception e) {
      // TODO Auto-generated catch block
      logger.info("Error.." + e);
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
//			System.out.println("Get Data Query"+query);
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
//				System.out.println("File not exists");
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
//				System.out.println("Served IMEI 1 ="+rs.getString("IMEIESNMEID"));
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
//					System.out.println(my_query);
//					System.out.println(device_db_query);
//					
//					stmt1.addBatch(my_query);
//					stmt2.addBatch(device_db_query);
//					if(split_upload_batch_count==split_upload_batch_no){
//						logger.info("Executing batch file");
//						System.out.println("Executing batch file");
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
}
