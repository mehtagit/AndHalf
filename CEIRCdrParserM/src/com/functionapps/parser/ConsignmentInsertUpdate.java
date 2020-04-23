package com.functionapps.parser;

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

import org.apache.log4j.Logger;

import com.functionapps.log.LogWriter;

public class ConsignmentInsertUpdate {
	static Logger logger = Logger.getLogger(ConsignmentInsertUpdate.class);

	 public  void process(Connection conn,String operator,String sub_feature,   ArrayList<Rule> rulelist,String txn_id,  String operator_tag,   String usertype_name){
		String fileName = null;
		File file       = null;
		final String logPath  = "./";
		Statement stmt1=null;
		Statement stmt2=null;
		Statement raw_stmt = null;
		ResultSet rs = null;

		String period = "";
		int split_upload_batch_count=0;
		String raw_query = null;
		String my_query = null;
		String device_db_query =null;
		int update_sno = 0;
		int parser_base_limit=0;
		int split_upload_batch_no=0;
		String query = null;
		Statement stmt = null;
		int old_sno = 0;
		HashMap<String, String> my_rule_detail;
		HashMap<String, String> device_info = new HashMap<String, String>();
		RuleFilter rule_filter = new RuleFilter();
		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
		CEIRFeatureFileParser ceirfileparser = new CEIRFeatureFileParser();

		try{
			ResultSet my_result_set= ceirfileparser.operatorDetails( conn,  operator);
			if(my_result_set.next()){
				parser_base_limit = my_result_set.getInt("split_upload_set_no");
				old_sno = my_result_set.getInt("last_upload_sno");
				split_upload_batch_no = my_result_set.getInt("split_upload_batch_no");
			}
//			query = "select * from "+operator+"_raw where sno>"+old_sno+" and status='Init' order by sno asc FETCH FIRST "+parser_base_limit+" ROWS WITH TIES ";
			query = "select * from "+operator+"_raw where sno>"+old_sno+" and txn_id='"+txn_id+"' and status='Init' order by sno asc ";
			stmt = conn.createStatement();
			logger.info("Getting Data from raw table  ["+query+"]");
			System.out.println("Get Data Query"+query);
			rs=stmt.executeQuery(query);
			ceirfileparser.updateLastStatuSno(conn,operator, old_sno,parser_base_limit);
			
			stmt1=conn.createStatement();
			stmt2=conn.createStatement();
			raw_stmt = conn.createStatement();

			// CDR File Writer
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			fileName = "CEIR_"+operator+"_File_"+sdf.format(Calendar.getInstance().getTime())+".csv";
			file     = new File( logPath );
			if( !file.exists() ){
				file.mkdir();
				System.out.println("File not exists");
			}	
			file = new File( logPath+fileName );
			FileWriter myWriter;
			if(file.exists()){
				myWriter = new FileWriter(file,true);
			}
			else{
				myWriter = new FileWriter(file);				
			}

			period = ceirfileparser.checkGraceStatus(conn);
			HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
			feature_file_mapping = ceirfunction.getFeatureMapping(conn, operator, usertype_name);
			HashMap<String, String> feature_file_management = new HashMap<String, String>();
			feature_file_management = ceirfunction.getFeatureFileManagement(conn,feature_file_mapping.get("mgnt_table_db"),txn_id);
			while(rs.next()){

				System.out.println("Served IMEI 1 ="+rs.getString("IMEIESNMEID"));
				device_info.put("IMEIESNMEID",rs.getString("IMEIESNMEID") );
				device_info.put("DeviceType",rs.getString("DeviceType") );
				device_info.put("MultipleSIMStatus",rs.getString("MultipleSIMStatus") );
				device_info.put("SNofDevice",rs.getString("SNofDevice") );
				device_info.put("Devicelaunchdate",rs.getString("Devicelaunchdate") );
				device_info.put("DeviceStatus",rs.getString("DeviceStatus") );
				device_info.put("operator",operator );
				device_info.put("feature",operator );
				device_info.put("file_name",rs.getString("file_name") );
				device_info.put("operator_tag",operator_tag);
				device_info.put("txn_id",txn_id);
				// Event writer into file
				if(file.length()==0){
					new LogWriter().writeFeatureEvents(myWriter, "IMEIESNMEID",
							"DeviceType", "MultipleSIMStatus", "SNofDevice", "Devicelaunchdate","DeviceStatus","txn_id",
							"operator", "file_name", "type", "rule_id", "rule_name", "status","time");				
				}
				else{
					new LogWriter().writeFeatureEvents(myWriter, rs.getString("IMEIESNMEID"),
							rs.getString("DeviceType"), rs.getString("MultipleSIMStatus"),rs.getString("SNofDevice"),
							rs.getString("Devicelaunchdate"),rs.getString("DeviceStatus"),txn_id,
							operator, rs.getString("file_name"), "Init", "", "", "",new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));									
				}				
				my_rule_detail = rule_filter.getMyFeatureRule(conn,device_info,rulelist,myWriter);
				split_upload_batch_count++;
									
				raw_query = "update "+operator+"_raw"+" set status='Complete' where sno='"+rs.getString("sno")+"'";
				raw_stmt.addBatch(raw_query);
				
				if(split_upload_batch_count==split_upload_batch_no){
					logger.info("Executing batch file");
					raw_stmt.executeBatch();
					conn.commit();
					split_upload_batch_count=0;
				}

				
				
				update_sno = Integer.parseInt(rs.getString("sno"));
				
			}
			if(update_sno!=0){
				ceirfileparser.updateRawLastSno(conn,operator,  update_sno);
				logger.info("Final Executing batch file");
				raw_stmt.executeBatch();
				conn.commit();
			}
			String error_file_path = ceirfileparser.getErrorFilePath(conn)+txn_id+"/"+txn_id+"_err.csv";
			File errorfile = new File(error_file_path);
			if(errorfile.exists()){
				ceirfunction.updateFeatureFileStatus(conn,txn_id,2,operator, sub_feature);
				ceirfunction.updateFeatureManagementStatus(conn, txn_id, 2, feature_file_mapping.get("mgnt_table_db"));		        	
				ceirfunction.addFeatureFileConfigDetails(conn, "update",operator,sub_feature,txn_id,"","REJECTED_BY_SYSTEM","");

			}
			else{
				rs.beforeFirst();
				split_upload_batch_count=0;
				while(rs.next()){
					split_upload_batch_count++;
					my_query = "insert into "+feature_file_mapping.get("output_device_db")+" (device_id_type,created_on,device_launch_date,device_status," +
							"device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id) " +
							"values('" +rs.getString("DeviceIdType")+"'," 
							+"'"+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"',"
							+"'"+rs.getString("Devicelaunchdate")+"',"
							+"'"+rs.getString("DeviceStatus")+"',"
							+"'"+rs.getString("DeviceType")+"',"
							+"'"+rs.getString("IMEIESNMEID")+"',"
							+"'"+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"',"
							+"'"+rs.getString("MultipleSIMStatus")+"',"
							+"'"+period+"',"
							+"'"+rs.getString("SNofDevice")+"',"
							+"'"+txn_id+"',"
							+""+feature_file_management.get("user_id")+""
							+")";
					
					device_db_query = "insert into device_db (device_id_type,created_on,device_launch_date,device_status," +
							"device_type,imei_esn_meid,modified_on,multiple_sim_status,period,sn_of_device,txn_id,user_id) " +
							"values('" +rs.getString("DeviceIdType")+"'," 
							+"'"+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"',"
							+"'"+rs.getString("Devicelaunchdate")+"',"
							+"'"+rs.getString("DeviceStatus")+"',"
							+"'"+rs.getString("DeviceType")+"',"
							+"'"+rs.getString("IMEIESNMEID")+"',"
							+"'"+new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date())+"',"
							+"'"+rs.getString("MultipleSIMStatus")+"',"
							+"'"+period+"',"
							+"'"+rs.getString("SNofDevice")+"',"
							+"'"+txn_id+"',"
							+""+feature_file_management.get("user_id")+""
							+")";

					
					System.out.println(my_query);
					System.out.println(device_db_query);
					
					stmt1.addBatch(my_query);
					stmt2.addBatch(device_db_query);
					if(split_upload_batch_count==split_upload_batch_no){
						logger.info("Executing batch file");
						System.out.println("Executing batch file");
						stmt1.executeBatch();
						stmt2.executeBatch();
						conn.commit();
						split_upload_batch_count=0;
					}
				}
				stmt1.executeBatch();
				stmt2.executeBatch();
				conn.commit();
				ceirfunction.updateFeatureFileStatus(conn,txn_id,2,operator, sub_feature);
				ceirfunction.updateFeatureManagementStatus(conn, txn_id, 3, feature_file_mapping.get("mgnt_table_db"));		        	
				ceirfunction.addFeatureFileConfigDetails(conn, "update",operator,sub_feature,txn_id,"","Complete","");
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
