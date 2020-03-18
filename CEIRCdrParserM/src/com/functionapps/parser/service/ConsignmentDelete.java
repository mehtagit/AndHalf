package com.functionapps.parser.service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.functionapps.log.LogWriter;
import com.functionapps.parser.CEIRFeatureFileFunctions;
import com.functionapps.parser.CEIRFeatureFileParser;
import com.functionapps.parser.Rule;
import com.functionapps.parser.RuleFilter;
import com.functionapps.pojo.DeviceImporterDb;

public class ConsignmentDelete {
	static Logger logger = Logger.getLogger(ConsignmentDelete.class);

	public void process(Connection conn, String operator, String sub_feature, ArrayList<Rule> rulelist, String txn_id, String operator_tag ){
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
			
			period = ceirfileparser.checkGraceStatus(conn);
			HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
			feature_file_mapping = ceirfunction.getFeatureMapping(conn,operator);
			
			HashMap<String, String> feature_file_management = new HashMap<String, String>();
			feature_file_management = ceirfunction.getFeatureFileManagement(conn, feature_file_mapping.get("mgnt_table_db"),txn_id);
			
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
				ceirfunction.updateFeatureFileStatus(conn, txn_id, 2, operator, sub_feature);
				ceirfunction.updateFeatureManagementStatus(conn, txn_id, 3, feature_file_mapping.get("mgnt_table_db"));		        	
				ceirfunction.addFeatureFileConfigDetails(conn, "update",operator,sub_feature,txn_id,"","Complete","");
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<DeviceImporterDb> getDeviceImporterDbByTxnId(Connection conn, String management_db, String txn_id) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		
		DeviceImporterDb deviceImporterDb = null;
		List<DeviceImporterDb> deviceImporterDbs = new LinkedList<>();
		try{
        	query = "select txn_id, imei_esn_meid from device_importer_db where txn_id='" + txn_id + "'";
        	logger.info("Query to get File Details ["+query+"]");
        	stmt  = conn.createStatement();
        	rs = stmt.executeQuery(query);
        	
        	while(rs.next()){
        		deviceImporterDb = new DeviceImporterDb();
        		deviceImporterDb.setTxnId(rs.getString("txn_id"));
        		deviceImporterDb.setImeiEsnMeid(rs.getString("imei_esn_meid"));
        	}
		}
		catch(Exception e){
			logger.info("Exception in getFeatureMapping"+e);
		}
		finally{
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}

		return deviceImporterDbs;
	}
}
