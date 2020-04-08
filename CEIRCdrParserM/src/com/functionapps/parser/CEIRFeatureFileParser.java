package com.functionapps.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.functionapps.constants.*;
import com.functionapps.log.LogWriter;

public class CEIRFeatureFileParser {

	static Logger logger = Logger.getLogger(CEIRFeatureFileParser.class);

	public static void main(String args[]) {

		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		//		String main_type = args[0];
		String feature = null;
		Connection conn = null;
		conn = (Connection) new com.functionapps.db.MySQLConnection().getConnection();
		ResultSet featurers = getFeatureFileDetails(conn );
		try {
			if (featurers.next()) {
				logger.info("CEIRFeatureFileParser started "  + featurers.getString("txn_id"));
				feature = featurers.getString("feature");
				ArrayList rulelist = new ArrayList<Rule>();
				String period = checkGraceStatus(conn);
				rulelist = getRuleDetails(feature, conn, "GSM", period, "", featurers.getString("usertype_name"));
				addCDRInProfileWithRule(feature, conn, rulelist, "", featurers.getString("txn_id"), featurers.getString("sub_feature") ,  featurers.getString("usertype_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static ResultSet getFeatureFileDetails(Connection conn ) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		String limiter = " limit 1 ";
		if (conn.toString().contains("oracle")) {
			limiter = " fetch next 1 rows only ";
		}
		try {        								//and feature = '"+main_type+"'
			query = "select * from feature_file_config_db where status='Init'  order by sno asc  " + limiter + " ";
			stmt = conn.createStatement();
			logger.info("get feature file details [" + query + "] ");
			return rs = stmt.executeQuery(query);
		} catch (Exception e) {
			logger.info("" + e);
		}
		return rs;
	}

	private static String checkGraceStatus(Connection conn) {
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
				e.printStackTrace();
			}

		}
		return period;
	}

	private static String getOperatorTag(Connection conn, String operator) {
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
				e.printStackTrace();
			}

		}
		return operator_tag;

	}

	private static void addCDRInProfileWithRule(String operator, Connection conn, ArrayList<Rule> rulelist, String operator_tag, String txn_id, String sub_feature , String usertype_name) {

		String query = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Statement stmt = null;    // for rs 
		Statement stmt0 = null;    //    for rs1 
		Statement stmt1 = null;    //devce_dv batch
		Statement stmt2 = null;     // device_imprter_db btch 
		Statement raw_stmt = null;    //  update raw table to cmplete
		Statement stmt3 = null;   // custom db
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

		HashMap<String, String> my_rule_detail;
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
		int split_upload_batch_no =  1 ;    // it should be dymnamic
		int split_upload_batch_count = 0;

		try {

			ResultSet my_result_set = operatorDetails(conn, operator);
			if (my_result_set.next()) {
				parser_base_limit = my_result_set.getInt("split_upload_set_no");
				old_sno = my_result_set.getInt("last_upload_sno");
			}
			//			query = "select * from "+operator+"_raw where sno>"+old_sno+" and status='Init' order by sno asc FETCH FIRST "+parser_base_limit+" ROWS WITH TIES ";
			query = "select * from " + operator + "_raw where sno>" + old_sno + " and txn_id='" + txn_id + "' and status='Init' order by sno asc ";
			stmt = conn.createStatement();
			logger.info("Get Data (addCDRInProfileWithRule)Query.. " + query);
			rs = stmt.executeQuery(query);
			HashMap<String, String> device_info = new HashMap<String, String>();
			RuleFilter rule_filter = new RuleFilter();
			//		updateLastStatuSno(conn, operator, old_sno, parser_base_limit);   //update _raw   db status = start


			// CDR File Writer
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fileName = "CEIR_" + operator + "_File_" + sdf.format(Calendar.getInstance().getTime()) + ".csv";
			logger.info(" File NAme as CEIR..."+ fileName);
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
			period = checkGraceStatus(conn);
			HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
			feature_file_mapping = ceirfunction.getFeatureMapping(conn, operator , usertype_name);     // select * from feature_mapping_db
			HashMap<String, String> feature_file_management = new HashMap<String, String>();
			feature_file_management = ceirfunction.getFeatureFileManagement(conn, feature_file_mapping.get("mgnt_table_db"), txn_id);   //select * from " + management_db + " where


			logger.info(" hare   200  values are   -- consignment--... "  + operator + ".--register--." + sub_feature +   "   --update-- "    + sub_feature);
			if ((sub_feature.equalsIgnoreCase("register") || sub_feature.equalsIgnoreCase("update")         ||   sub_feature.equalsIgnoreCase("upload")   )) {    // operator.equalsIgnoreCase("consignment") && 
				logger.info("Entered for Consignment...  it can be for STOCK");
				while (rs.next()) {

					logger.info("Served IMEI 1 =" + rs.getString("IMEIESNMEID"));
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
							new LogWriter().writeFeatureEvents(myWriter, "IMEIESNMEID",
									"DeviceType", "MultipleSIMStatus", "SNofDevice", "Devicelaunchdate", "DeviceStatus", "txn_id",
									"operator", "file_name", "type", "rule_id", "rule_name", "status", "time");
						} else {
							new LogWriter().writeFeatureEvents(myWriter, rs.getString("IMEIESNMEID"),
									rs.getString("DeviceType"), rs.getString("MultipleSIMStatus"), rs.getString("SNofDevice"),
									rs.getString("Devicelaunchdate"), rs.getString("DeviceStatus"), txn_id,
									operator, rs.getString("file_name"), "Init", "", "", "", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
						}
					}catch(Exception e ) {

						logger.info(" exception.. at   new LogWriter().writeFeatureEvents(myWriter, ,,,"  + e);
					}
					my_rule_detail = rule_filter.getMyFeatureRule(conn, device_info, rulelist, myWriter);
					split_upload_batch_count++;

					//					raw_query = "update " + operator + "_raw" + " set status='Complete' where sno='" + rs.getString("sno") + "'";
					//					raw_stmt.addBatch(raw_query);

					if (split_upload_batch_count == split_upload_batch_no) {
						//						raw_stmt.executeBatch();
						conn.commit();
						split_upload_batch_count = 0;
					}

					update_sno = Integer.parseInt(rs.getString("sno"));

				}

				conn.commit();
				if (update_sno != 0) {
					updateRawLastSno(conn, operator, update_sno);
					logger.info("Final Executing batch file...");
					conn.commit();
				}

				String error_file_path = getErrorFilePath(conn) + txn_id + "/" + txn_id + "_error.csv";

				File errorfile = new File(error_file_path);
				logger.info("  is Error fie exists .. "+   errorfile.exists()  +"  if true,path is... "+ error_file_path);
				if (errorfile.exists()) {
					ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, operator, sub_feature);  //update web_action_db 
					ceirfunction.consignmentUpdateViaApi(conn, txn_id, 1);
					ceirfunction.updateFeatureManagementStatus(conn, txn_id, 2, feature_file_mapping.get("mgnt_table_db")  , operator);	
					ceirfunction.addFeatureFileConfigDetails(conn, "update", operator, sub_feature, txn_id, "", "REJECTED_BY_SYSTEM", "");

				} else {

					int rrslt =  getCustomData( conn,  txn_id );




					stmt0 = conn.createStatement();
					stmt1 = conn.createStatement();
					stmt2 = conn.createStatement();

					split_upload_batch_count = 0;

					logger.info("   at stmt insertin..");
					rs1 = stmt0.executeQuery(query);
					String dateNow = "";
					if (conn.toString().contains("oracle")) {
						dateNow = new SimpleDateFormat("dd-MMM-YY").format(new Date())  ; 
					}else {
						dateNow = new SimpleDateFormat("YYYY-MM-dd").format(new Date())  ;
					}
					while (rs1.next()) {
						split_upload_batch_count++;
						my_query = "insert into " + feature_file_mapping.get("output_device_db") + " (device_id_type,created_on,device_launch_date,device_status,"
								+ "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id) "
								+ "values('" + rs1.getString("DeviceIdType") + "',"
								+ "'" +dateNow + "',"              ///  "dd-MMM-YY"     //   "YYYY-MM-dd hh:mm:ss"
								+ "'" + rs1.getString("Devicelaunchdate") + "',"
								+ "'" + rs1.getString("DeviceStatus") + "',"
								+ "'" + rs1.getString("DeviceType") + "',"
								+ "'" + rs1.getString("IMEIESNMEID") + "',"
								+ "'" + dateNow + "',"
								+ "'" + rs1.getString("MultipleSIMStatus") + "',"
								//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								+ "'" + period + "',"
								+ "'" + rs1.getString("SNofDevice") + "',"
								+ "'" + txn_id + "',"
								+ "" + feature_file_management.get("user_id") + ""
								+ ")";

						device_db_query = "insert into device_db (device_id_type,created_on,device_launch_date,device_status,"
								+ "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id) "     //user_id
								+ "values('" + rs1.getString("DeviceIdType") + "',"
								+ "'" + dateNow + "',"
								+ "'" + rs1.getString("Devicelaunchdate") + "',"
								+ "'" + rs1.getString("DeviceStatus") + "',"
								+ "'" + rs1.getString("DeviceType") + "',"
								+ "'" + rs1.getString("IMEIESNMEID") + "',"
								+ "'" + dateNow+ "',"
								+ "'" + rs1.getString("MultipleSIMStatus") + "',"
								//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								+ "'" + (period == "grace" ? 0:1 ) + "',"
								+ "'" + rs1.getString("SNofDevice") + "',"
								+ "'" + txn_id + "'  "
								//    + "  , " + feature_file_management.get("user_id") + ""
								+ ")";

						device_custom_db_qry = "insert into device_custom_db (device_id_type,created_on,device_launch_date,device_status,"
								+ "device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id) "     //user_id
								+ "values('" + rs1.getString("DeviceIdType") + "',"
								+ "'" + dateNow + "',"
								+ "'" + rs1.getString("Devicelaunchdate") + "',"
								+ "'" + rs1.getString("DeviceStatus") + "',"
								+ "'" + rs1.getString("DeviceType") + "',"
								+ "'" + rs1.getString("IMEIESNMEID") + "',"
								+ "'" + dateNow+ "',"
								+ "'" + rs1.getString("MultipleSIMStatus") + "',"
								//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								+ "'" + (period == "grace" ? 0:1 ) + "',"
								+ "'" + rs1.getString("SNofDevice") + "',"
								+ "'" + txn_id + "'  "
								//    + "  , " + feature_file_management.get("user_id") + ""
								+ ")";



						logger.info(my_query);
						logger.info(device_db_query);
						//
						stmt1.addBatch(my_query);
						stmt2.addBatch(device_db_query);
						stmt2.addBatch(device_db_query);


						logger.info(   "split_upload_batch_count == "  + split_upload_batch_count +  ".. split_upload_batch_no== .." + split_upload_batch_no);
						if (split_upload_batch_count == split_upload_batch_no) {
							logger.info("Executing batch file");
							logger.info(my_query);
							logger.info(device_db_query);

							stmt1.executeBatch();
							stmt2.executeBatch();

							//							stmt1.executeUpdate(my_query);    //   added
							//							stmt2.executeUpdate(device_db_query);    // addedd/


							if(rrslt  ==  1 ){
								stmt3 = conn.createStatement();
								stmt3.executeUpdate(device_custom_db_qry);
							}stmt3.close();


							conn.commit();
							split_upload_batch_count = 0;
						}
					}
					logger.info("entered outside");
					//					stmt1.executeBatch();
					//					stmt2.executeBatch();


					stmt.close();
					stmt0.close();
					stmt1.close();
					stmt2.close();
					conn.commit();
					logger.info("stmts closed");
					logger.info("  featuer is "+ operator   + "   it will update web_action_db(STOCK) too ");
					ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, operator, sub_feature);
					ceirfunction.consignmentUpdateViaApi(conn, txn_id, 0);
					ceirfunction.updateFeatureManagementStatus(conn, txn_id, 3, feature_file_mapping.get("mgnt_table_db")  , operator);	
					ceirfunction.addFeatureFileConfigDetails(conn, "update", operator, sub_feature, txn_id, "", "Complete", "");
				}
				raw_stmt = conn.createStatement();
				raw_query = "update " + operator + "_raw" + " set status='Complete' where txn_id='" + txn_id + "'";
				logger.info("updating raw table with cmplte.."  + raw_query);
				raw_stmt.executeUpdate(raw_query);
				conn.commit();
				raw_stmt.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();

				stmt1.close();
				stmt2.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static String getErrorFilePath(Connection conn) {
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
				e.printStackTrace();
			}

		}
		return errorFilePath;

	}



	public   static int  getCustomData(Connection conn, String txn_id ) {
		String query = null;
		Statement stmt = null;
		ResultSet rs1 = null;
		String rslt =   "";
		int rst = 0 ;
		query = " select user_type from  stock_mgmt   where txn_id =  '"+txn_id+"'  ";
		logger.info("getCustomData query .." + query);
		try {
			stmt = conn.createStatement();
			rs1 = stmt.executeQuery(query);
			while (rs1.next()) {
				rslt = rs1.getString(1);
			}
			conn.commit();
			if(rslt.equalsIgnoreCase("Cutom")) {
				logger.info("IT is  Custom");
				rst = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return rst;
	}













	private static void updateRawData(Connection conn, String operator, String id, String status) {
		String query = null;
		Statement stmt = null;
		query = "update " + operator + "_raw" + " set status='" + status + "' where sno='" + id + "'";
		logger.info("updateRawData query .." + query);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static ArrayList getRuleDetails(String operator, Connection conn, String operator_tag, String period, String feature, String usertype_name) {
		ArrayList rule_details = new ArrayList<Rule>();
		String query = null;
		ResultSet rs1 = null;
		Statement stmt = null;
		try {
			query = "select a.id as rule_id,a.name as rule_name,b.output as output,b.grace_action, b.post_grace_action, b.failed_rule_action_grace, b.failed_rule_action_post_grace from rule_engine a, rule_engine_mapping b where  a.name=b.name  and a.state='FULL' and b.feature='" + operator + "' and b.user_type='" + usertype_name + "' order by b.rule_order asc";

			logger.info("Query is  (getRuleDetails) " + query);
			stmt = conn.createStatement();
			rs1 = stmt.executeQuery(query);
			if (!rs1.isBeforeFirst()) {
				query = "select a.id as rule_id,a.name as rule_name,b.output as output,b.grace_action, b.post_grace_action, b.failed_rule_action_grace, b.failed_rule_action_post_grace from rule_engine a, rule_engine_mapping b where  a.name=b.name  and a.state='FULL' and b.feature='" + operator + "' and b.user_type='default' order by b.rule_order asc";
				stmt = conn.createStatement();
				rs1 = stmt.executeQuery(query);
			}
			while (rs1.next()) {
				if (rs1.getString("rule_name").equalsIgnoreCase("IMEI_LENGTH")) {
					if (operator_tag.equalsIgnoreCase("GSM")) {
						//						Rule rule = new Rule(rs1.getString("rule_name"),rs1.getString("output"),rs1.getString("rule_id"),period, rs1.getString(period+"_action"));
						Rule rule = new Rule(rs1.getString("rule_name"), rs1.getString("output"), rs1.getString("rule_id"), period, rs1.getString(period + "_action"), rs1.getString("failed_rule_action_" + period));

						rule_details.add(rule);
					}
				} else {
					Rule rule = new Rule(rs1.getString("rule_name"), rs1.getString("output"), rs1.getString("rule_id"), period, rs1.getString(period + "_action"), rs1.getString("failed_rule_action_" + period));
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
				e.printStackTrace();
			}
		}
		return rule_details;
	}

	static ResultSet operatorDetails(Connection conn, String operator) {
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

	private static void updateLastStatuSno(Connection conn, String operator, int id, int limit) {
		String query = null;
		Statement stmt = null;
		query = "update " + operator + "_raw" + " set status='Start' where sno>'" + id + "'";   //   
		logger.info(query);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void updateRawLastSno(Connection conn, String operator, int sno) {
		String query = null;
		Statement stmt = null;
		query = "update rep_schedule_config_db set last_upload_sno=" + sno + " where operator='" + operator + "'";
		logger.info(  "rep_schedule_config_db (updateRawLastSn o) NOTE IT  ()updates operatr .. plz chck" +    query);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
