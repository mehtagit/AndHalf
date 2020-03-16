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
import com.functionapps.constants.*;
import com.functionapps.log.LogWriter;

import org.apache.log4j.Logger;
public class CEIRFeatureFileParser {
	static Logger logger = Logger.getLogger(CEIRFeatureFileParser.class);

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		logger = Logger.getLogger(CEIRParserMain.class);
		String feature = null;
		Connection conn = null;
		conn = (Connection) new com.functionapps.db.MySQLConnection().getConnection();
		ResultSet featurers=getFeatureFileDetails(conn);
		try {
			if(featurers.next()){
				feature = featurers.getString("feature");
				ArrayList rulelist = new ArrayList<Rule>();		
				String period = checkGraceStatus(conn);
				logger.info("Period is ["+period+"] ");
				rulelist = getRuleDetails(feature,conn,"" ,period,"",featurers.getString("usertype_name"));				
				addCDRInProfileWithRule(feature, conn, rulelist,"",featurers.getString("txn_id"),featurers.getString("sub_feature"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private static ResultSet getFeatureFileDetails(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		try{
        	query = "select * from feature_file_config_db where status='Init' order by sno asc limit 1 ";
			stmt  = conn.createStatement();
			logger.info("get feature file details ["+query+"] ");

			return rs    = stmt.executeQuery(query);
		}
		catch(Exception e){
			System.out.println(""+e);
		}
		return rs;
	}

	private static String checkGraceStatus(Connection conn) {
		String period="";
		String query    = null;
		ResultSet rs1    = null;
		Statement stmt  = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		Date graceDate =null;
		try{
			query = "select value from system_configuration_db where tag='grace_period_end_date'";
			System.out.println("Period is "+period);

			System.out.println("Query is "+query);
			logger.info("Check Grace End Date ["+query+"]");
			System.out.println("Period is "+period);

			stmt  = conn.createStatement();
			rs1    = stmt.executeQuery(query);
			System.out.println("Period is "+period);
			while( rs1.next() ){
				graceDate = sdf.parse(rs1.getString("value"));
				if(currentDate.compareTo(graceDate)>0){
					period= "post_grace";
				}
				else{
					period = "grace";
				}
			}
			System.out.println("Period is "+period);
		}catch( Exception ex ){
			logger.info("check Grace Status  ["+ex+"]");
			ex.printStackTrace();
		}
		finally{
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
		String operator_tag="";
		String query    = null;
		ResultSet rs1    = null;
		Statement stmt  = null;

		try{
			query = "select * from system_config_list_db where tag='OPERATORS' and interp='"+operator+"'";
			System.out.println("Query is "+query);
			logger.info("get operator tag ["+query+"]");
			stmt  = conn.createStatement();
			rs1    = stmt.executeQuery(query);
			while( rs1.next() ){
				operator_tag = rs1.getString("tag_id");
			}
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		finally{
			try {
				stmt.close();
				rs1.close();
			} catch (SQLException e) {
				logger.info("check Grace Status  ["+e+"]");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return operator_tag;

	}

	private static void addCDRInProfileWithRule(String operator, Connection conn,ArrayList<Rule> rulelist,String operator_tag,String txn_id,String sub_feature) {
		
		
		String query = null;
		ResultSet rs = null;
		Statement stmt = null;
		String table_name = null;
		String query_type = null;
		Statement raw_stmt = null;
		String raw_query = null;

		String imei = null;
		String msisdn = null;
		String update_my_record = null;
		int output = 0;
		String my_query = null;
		String device_db_query =null;
		Statement stmt1=null;
		Statement stmt2=null;
		HashMap<String, String> my_rule_detail;
		String failed_rule = null;
		String failed_rule_name="";
		String failed_rule_id = "";
		String action = "";
		String period = "";
		int parser_base_limit = 0; 
		int old_sno = 0;
		int update_sno = 0;
		final String logPath  = "./";
		String fileName = null;
		File file       = null;
		String log      = null;
		int split_upload_batch_no = 0;
		int split_upload_batch_count = 0;

		try{
			
			ResultSet my_result_set= operatorDetails( conn,  operator);
			if(my_result_set.next()){
				parser_base_limit = my_result_set.getInt("split_upload_set_no");
				old_sno = my_result_set.getInt("last_upload_sno");
			}
//			query = "select * from "+operator+"_raw where sno>"+old_sno+" and status='Init' order by sno asc FETCH FIRST "+parser_base_limit+" ROWS WITH TIES ";
			query = "select * from "+operator+"_raw where sno>"+old_sno+" and txn_id='"+txn_id+"' and status='Init' order by sno asc ";
			stmt = conn.createStatement();
			logger.info("Getting Data from raw table  ["+query+"]");
			System.out.println("Get Data Query"+query);
			rs=stmt.executeQuery(query);
			HashMap<String, String> device_info = new HashMap<String, String>();
			RuleFilter rule_filter = new RuleFilter();
			updateLastStatuSno(conn,operator, old_sno,parser_base_limit);
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
			CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
			period = checkGraceStatus(conn);
			HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
			feature_file_mapping = ceirfunction.getFeatureMapping(conn,operator);
			HashMap<String, String> feature_file_management = new HashMap<String, String>();
			feature_file_management = ceirfunction.getFeatureFileManagement(conn,feature_file_mapping.get("mgnt_table_db"),txn_id);

			if(operator.equalsIgnoreCase("consignment") &&(sub_feature.equalsIgnoreCase("register") || sub_feature.equalsIgnoreCase("update"))){
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
					updateRawLastSno(conn,operator,  update_sno);
					logger.info("Final Executing batch file");
					raw_stmt.executeBatch();
					conn.commit();
				}
				String error_file_path = getErrorFilePath(conn)+txn_id+"/"+txn_id+"_err.csv";
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
//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
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
//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
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
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static String getErrorFilePath(Connection conn) {
		String errorFilePath="";
		String query    = null;
		ResultSet rs1    = null;
		Statement stmt  = null;

		try{
			query = "select * from system_configuration_db where tag='system_error_filepath'";
			System.out.println("Query is "+query);
			logger.info("get operator tag ["+query+"]");
			stmt  = conn.createStatement();
			rs1    = stmt.executeQuery(query);
			while( rs1.next() ){
				errorFilePath = rs1.getString("value");
			}
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		finally{
			try {
				stmt.close();
				rs1.close();
			} catch (SQLException e) {
				logger.info("check Grace Status  ["+e+"]");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return errorFilePath;

	}

	private static void updateRawData(Connection conn,String operator, String id,String status) {
		String query = null;
		Statement stmt = null;
		query = "update "+operator+"_raw"+" set status='"+status+"' where sno='"+id+"'";
		System.out.println(query);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	private static ArrayList getRuleDetails(String operator, Connection conn,String operator_tag,String period,String feature,String usertype_name) {
		ArrayList rule_details = new ArrayList<Rule>();
		String query    = null;
		ResultSet rs1    = null;
		Statement stmt  = null;
		try{
			query = "select a.id as rule_id,a.name as rule_name,a.output as output,b.grace_action, b.post_grace_action, b.failed_rule_action_grace, b.failed_rule_action_post_grace from rule_engine a, rule_engine_mapping b where  a.name=b.name  and a.state='FULL' and b.feature='"+operator+"' and b.user_type='"+usertype_name+"' order by b.rule_order asc";

			System.out.println("Query is "+query);
			stmt  = conn.createStatement();
			rs1    = stmt.executeQuery(query);
			if(!rs1.isBeforeFirst()){
				query = "select a.id as rule_id,a.name as rule_name,a.output as output,b.grace_action, b.post_grace_action, b.failed_rule_action_grace, b.failed_rule_action_post_grace from rule_engine a, rule_engine_mapping b where  a.name=b.name  and a.state='FULL' and b.feature='"+operator+"' and b.user_type='default' order by b.rule_order asc";				
				stmt  = conn.createStatement();
				rs1    = stmt.executeQuery(query);
			}
			while( rs1.next() ){
				
				if(rs1.getString("rule_name").equalsIgnoreCase("IMEI_LENGTH")){
					if(operator_tag.equalsIgnoreCase("GSM")){
//						Rule rule = new Rule(rs1.getString("rule_name"),rs1.getString("output"),rs1.getString("rule_id"),period, rs1.getString(period+"_action"));
						Rule rule = new Rule(rs1.getString("rule_name"),rs1.getString("output"),rs1.getString("rule_id"),period, rs1.getString(period+"_action"),rs1.getString("failed_rule_action_"+period));

						rule_details.add(rule);						
					}
				}
				else{
					Rule rule = new Rule(rs1.getString("rule_name"),rs1.getString("output"),rs1.getString("rule_id"),period, rs1.getString(period+"_action"),rs1.getString("failed_rule_action_"+period));
					rule_details.add(rule);					
				}
			}
		}catch( Exception ex ){
			ex.printStackTrace();
		}
		finally{
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
	static ResultSet operatorDetails(Connection conn, String operator){
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		try{
        	query = "select * from rep_schedule_config_db where operator='"+operator+"'";
			stmt  = conn.createStatement();
			return rs    = stmt.executeQuery(query);
		}
		catch(Exception e){
			System.out.println(""+e);
		}
		return rs;
	}

	private static void updateLastStatuSno(Connection conn,String operator, int id,int limit) {
		String query = null;
		Statement stmt = null;
		query = "update "+operator+"_raw"+" set status='Start' where sno>'"+id+"'";
		System.out.println(query);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void updateRawLastSno(Connection conn,String operator, int sno) {
		String query = null;
		Statement stmt = null;
		query = "update rep_schedule_config_db set last_upload_sno="+sno+" where operator='"+operator+"'";
		System.out.println(query);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}

