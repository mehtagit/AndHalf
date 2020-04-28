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
public class CEIRParserMain {
	private static Logger logger;

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		logger = Logger.getLogger(CEIRParserMain.class);
		String operator = args[0];
		Connection conn = null;
		conn = (Connection) new com.functionapps.db.MySQLConnection().getConnection();
		String operator_tag =  getOperatorTag(conn,operator);
		logger.info("Operator tag is ["+operator_tag+"] ");
		
		ArrayList rulelist = new ArrayList<Rule>();		
		String period = checkGraceStatus(conn);
		logger.info("Period is ["+period+"] ");
		rulelist = getRuleDetails(operator,conn,operator_tag ,period);
		addCDRInProfileWithRule(operator, conn, rulelist,operator_tag);
		
		
		
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
	
			logger.info("Check Grace End Date ["+query+"]");
			

			stmt  = conn.createStatement();
			rs1    = stmt.executeQuery(query);
		
			while( rs1.next() ){
				graceDate = sdf.parse(rs1.getString("value"));
				if(currentDate.compareTo(graceDate)>0){
					period= "post_grace";
				}
				else{
					period = "grace";
				}
			}
			logger.info("Period is "+period);
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

	private static void addCDRInProfileWithRule(String operator, Connection conn,ArrayList<Rule> rulelist,String operator_tag) {
		
		
		String query = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		Statement raw_stmt = null;
		String raw_query = null;
		
		int output = 0;
		String my_query = null;
		Statement stmt1=null;
		HashMap<String, String> my_rule_detail;
		
		String failed_rule_name="";
		String failed_rule_id = "";
		String action = "";
		String period = "";
		int parser_base_limit = 0; 
		int old_sno = 0;
		int update_sno = 0;
		final String logPath  = "./";
//		final String logPath ="/home/ceirapp/ceir_parser/logs/";
		String fileName = null;
		File file       = null;
		String log      = null;
		int split_upload_batch_no = 0;
		int split_upload_batch_count = 0;

		try{
			logger.info("Getting operatorDetails..select * from rep_schedule_config_db where operator ="+operator+" ");
			ResultSet my_result_set= operatorDetails( conn,  operator);
			if(my_result_set.next()){
				parser_base_limit = my_result_set.getInt("split_upload_set_no");
				old_sno = my_result_set.getInt("last_upload_sno");
                                split_upload_batch_no = my_result_set.getInt("split_upload_batch_no");
			}
//			query = "select * from "+operator+"_raw where sno>"+old_sno+" and status='Init' order by sno asc FETCH FIRST "+parser_base_limit+" ROWS WITH TIES ";
			query = "select * from "+operator+"_raw where sno>"+old_sno+" and sno<="+(old_sno +parser_base_limit)+" and status='Init' order by sno asc ";
			stmt = conn.createStatement();
			logger.info("Getting Data from raw table  ["+query+"]");
			
			rs=stmt.executeQuery(query);
			HashMap<String, String> device_info = new HashMap<String, String>();
			RuleFilter rule_filter = new RuleFilter();
			updateLastStatuSno(conn,operator, old_sno,parser_base_limit);
			stmt1=conn.createStatement();
			raw_stmt = conn.createStatement();

			// CDR File Writer
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			
			fileName = "CEIR_CDR_File_"+sdf.format(Calendar.getInstance().getTime())+".csv";
			file     = new File( logPath );
			if( !file.exists() ){
				file.mkdir();
				logger.info("File not exists");
				
			}	
			file = new File( logPath+fileName );
			FileWriter myWriter;
			if(file.exists()){
				myWriter = new FileWriter(file,true);
			}
			else{
				myWriter = new FileWriter(file);				
			}
			
			
			while(rs.next()){
//				failed_rule = executeRule(rs.getString("servedIMEI"),rulelist);
				logger.info("Served IMEI 1 ="+rs.getString("servedIMEI"));
				device_info.put("servedIMEI",rs.getString("servedIMEI") );
				device_info.put("recordType",rs.getString("recordType") );
				device_info.put("servedIMSI",rs.getString("servedIMSI") );
				device_info.put("servedMSISDN",rs.getString("servedMSISDN") );
				device_info.put("systemType",rs.getString("systemType") );
				device_info.put("operator",rs.getString("operator") );
				device_info.put("file_name",rs.getString("file_name") );
				device_info.put("record_time",rs.getString("record_time") );
				device_info.put("operator_tag",operator_tag);

				// Event writer into file
				if(file.length()==0){
					new LogWriter().writeEvents(myWriter, "servedIMEI",
							"recordType", "servedIMSI", "servedMSISDN", "systemType",
							"operator", "file_name", "record_time", "type", "rule_id", "rule_name", "status","time");				
				}
				else{
					new LogWriter().writeEvents(myWriter, rs.getString("servedIMEI"),
							rs.getString("recordType"), rs.getString("servedIMSI"),rs.getString("servedMSISDN"),
							rs.getString("systemType"),
							rs.getString("operator"), rs.getString("file_name"), rs.getString("record_time"), "Init", "", "", "",new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));				
					
				}

				
				
				//				MyRuleFilter rule_filter = new MyRuleFilter();
//				updateRawData(conn, operator,rs.getString("sno"),"Started");
//				logger.info("Served IMEI 2 ="+rs.getString("servedIMEI"));
				if(rs.getString("servedIMEI")==null || rs.getString("servedIMEI").equals("") || rs.getString("servedIMEI")== null){
					output = checkDeviceNullDB(conn,rs.getString("servedMSISDN"));
					if(output==0){
						my_query = "insert into device_null_db (msisdn,imsi,create_filename,update_filename," +
								"updated_on,created_on,record_type,system_type) " +
								"values('" +rs.getString("servedMSISDN")+"'," 
								+"'"+rs.getString("servedIMSI")+"',"
								+"'"+rs.getString("file_name")+"',"
								+"'"+rs.getString("file_name")+"',"
//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								+"'"+rs.getString("record_time")+"',"
								+"'"+rs.getString("record_time")+"',"
								+"'"+rs.getString("recordType")+"',"
								+"'"+rs.getString("systemType")+"'"
								+")";
						logger.info("need to insert");
					}
					else{
						my_query = "update device_null_db set " +
								"update_filename = '"+rs.getString("file_name")
//								+"',updated_on=TO_DATE('"+rs.getString("record_time")+
//								"','yyyy/mm/dd hh24:mi:ss')"+
								+"',updated_on='"+rs.getString("record_time")+
								"'"+
								" where msisdn='"+rs.getString("servedMSISDN")+"'";
						logger.info("need to update");
					}
				}
				else{
					device_info.put("tac", rs.getString("servedIMEI").substring(0,8));
					my_rule_detail = rule_filter.getMyRule(conn,device_info,rulelist,myWriter);
					if(my_rule_detail.get("rule_name")!=null){
						failed_rule_name = my_rule_detail.get("rule_name");
						failed_rule_id = my_rule_detail.get("rule_id");
						action = my_rule_detail.get("action");
						period = my_rule_detail.get("period");
					}
					else{
						failed_rule_name = "";
						failed_rule_id = "";
						action = "";
						period = "";						
					}
					output = checkDeviceUsageDB(conn, rs.getString("servedIMEI"), rs.getString("servedMSISDN"));
					if(output ==0){
						my_query = "insert into device_usage_db (imei,msisdn,imsi,create_filename,update_filename," +
								"updated_on,created_on,system_type,failed_rule_id,failed_rule_name,tac,period,action) " +
								"values('"+rs.getString("servedIMEI")+"',"
								+"'"+rs.getString("servedMSISDN")+"'," 
								+"'"+rs.getString("servedIMSI")+"',"
								+"'"+rs.getString("file_name")+"',"
								+"'"+rs.getString("file_name")+"',"
//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								+"'"+rs.getString("record_time")+"',"
//								+"'"+rs.getString("record_time")+"',"
//								+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
								+"'"+rs.getString("record_time")+"',"
								+"'"+rs.getString("systemType")+"',"
								+"'"+failed_rule_id+"',"
								+"'"+failed_rule_name+"',"
								+"'"+rs.getString("servedIMEI").substring(0,8)+"',"
								+"'"+period+"',"
								+"'"+action+"'"
								+")";
						logger.info("need to insert into usage db");
					}
					else if(output ==1){
						my_query = "update device_usage_db set " +
								"update_filename = '"+rs.getString("file_name")
//								+"', updated_on=TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss')"
								+"', updated_on='"+rs.getString("record_time")+"'"
								+", failed_rule_id='"+failed_rule_id
								+"', failed_rule_name='"+failed_rule_name
								+"',period='"+period
								+"',action='"+action
								+"' where imei='"+rs.getString("servedIMEI")+"'";
						logger.info("need update into usage db");						
					}
					else{
						output = checkDeviceDuplicateDB(conn,rs.getString("servedIMEI"), rs.getString("servedMSISDN"));
						if(output == 0){
							my_query = "insert into device_duplicate_db (imei,msisdn,imsi,create_filename,update_filename," +
									"updated_on,created_on,system_type,failed_rule_id,failed_rule_name,tac,period,action) " +
									"values('"+rs.getString("servedIMEI")+"',"
									+"'"+rs.getString("servedMSISDN")+"'," 
									+"'"+rs.getString("servedIMSI")+"',"
									+"'"+rs.getString("file_name")+"',"
									+"'"+rs.getString("file_name")+"',"
//									+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
//									+"TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss'),"
									+"'"+rs.getString("record_time")+"',"
									+"'"+rs.getString("record_time")+"',"
									+"'"+rs.getString("systemType")+"',"
									+"'"+failed_rule_id+"',"
									+"'"+failed_rule_name+"',"
									+"'"+rs.getString("servedMSISDN").substring(0,8)+"',"
									+"'"+period+"',"
									+"'"+action+"'"
									+")";
							logger.info("need to insert in duplicate DB");
						}
						else{
							my_query = "update device_duplicate_db set " +
									"update_filename = '"+rs.getString("file_name")
//									+"', updated_on=TO_DATE('"+rs.getString("record_time")+"','yyyy/mm/dd hh24:mi:ss')"
									+"', updated_on='"+rs.getString("record_time")+"'"
									+", failed_rule_id='"+failed_rule_id
									+"', failed_rule_name='"+failed_rule_name
									+"',period='"+period
									+"',action='"+action
									+"' where msisdn='"+rs.getString("servedMSISDN")+"' and imei='"+rs.getString("servedIMEI")+"'";
							logger.info("need to update duplicate DB");
						}
					}
				}
//				logger.info("Final Query to update or insert in Device DB ["+my_query+"]");
//				logger.info("servedIMEI "+rs.getString("servedIMEI"));
//				logger.info(my_query);
////				stmt1.addBatch(my_query);
//				stmt1.executeUpdate(my_query);
//				 // conn.commit();
//				updateRawData(conn, operator,rs.getString("sno"),"Complete");

				logger.info("Final Query to update or insert in Device DB ["+my_query+"]");
				logger.info("servedIMEI "+rs.getString("servedIMEI"));
				logger.info(my_query);
//				stmt1.executeUpdate(my_query);
				split_upload_batch_count++;
				stmt1.addBatch(my_query);
				
				
				raw_query = "update "+operator+"_raw"+" set status='Complete' where sno='"+rs.getString("sno")+"'";
				raw_stmt.addBatch(raw_query);
				
				if(split_upload_batch_count==split_upload_batch_no){
					logger.info("Executing batch file");
					stmt1.executeBatch();
					raw_stmt.executeBatch();
					  conn.commit();
					split_upload_batch_count=0;
				}

				update_sno = Integer.parseInt(rs.getString("sno"));
//				stmt1.close();
				
			}
			if(update_sno!=0){
				updateRawLastSno(conn,operator,  update_sno);
				logger.info("Final Executing batch file");

				stmt1.executeBatch();
				raw_stmt.executeBatch();
				 conn.commit();

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

	private static String executeRule(String string, ArrayList<Rule> rulelist) {

		return "BlackList";
	}

	private static void updateRawData(Connection conn,String operator, String id,String status) {
		String query = null;
		Statement stmt = null;
		query = "update "+operator+"_raw"+" set status='"+status+"' where sno='"+id+"'";
		logger.info(query);
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

	private static int checkDeviceDuplicateDB(Connection conn, String imei,String msisdn) {
		String query = null;
		ResultSet rs1 = null;
		Statement stmt = null;
		int status=0;
		try{
			query = "select * from device_duplicate_db where imei='"+imei+"' and msisdn = '"+msisdn+"'";
			logger.info("device_dupliate db"+query);
			stmt = conn.createStatement();
			rs1=stmt.executeQuery(query);			
			while(rs1.next()){				
				status=1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
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
		logger.info(status);
		return status;
	}

	private static int checkDeviceUsageDB(Connection conn,String imei, String msisdn) {
		String query = null;
		ResultSet rs1 = null;
		Statement stmt = null;
		int status=0;
		try{
			query = "select * from device_usage_db where imei='"+imei+"'";
			logger.info("device usage db"+query);

			stmt = conn.createStatement();
			rs1=stmt.executeQuery(query);			
			while(rs1.next()){
				logger.info(rs1.getString("msisdn"));
				if(rs1.getString("msisdn") != msisdn){
					status=2;
				}
				else{
					status=1;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
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

		logger.info(status);
		return status;
	}

	public static int checkDeviceNullDB(Connection conn,String msisdn) {
		String query = null;
		ResultSet rs1 = null;
		Statement stmt = null;
		int status=0;
		try{
			query = "select * from device_null_db where msisdn='"+msisdn+"'";
			logger.info("device usage db"+query);
			stmt = conn.createStatement();
			rs1=stmt.executeQuery(query);			
			while(rs1.next()){				
				status=1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
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
		
		logger.info(status);		
		return status;
	}

	private static ArrayList getRuleDetails(String operator, Connection conn,String operator_tag,String period) {
		ArrayList rule_details = new ArrayList<Rule>();
		String query    = null;
		ResultSet rs1    = null;
		Statement stmt  = null;
		try{
                    query = "select a.id as rule_id,a.name as rule_name,b.output as output,b.grace_action, b.post_grace_action, b.failed_rule_action_grace, b.failed_rule_action_post_grace from rule_engine a, rule_engine_mapping b where  a.name=b.name  and a.state='FULL' and b.feature='CDR' order by b.rule_order asc";

			logger.info("Query is "+query);
			stmt  = conn.createStatement();
			rs1    = stmt.executeQuery(query);
			while( rs1.next() ){
				
				if(rs1.getString("rule_name").equalsIgnoreCase("IMEI_LENGTH")){
					if(operator_tag.equalsIgnoreCase("GSM")){
//						Rule rule = new Rule(rs1.getString("rule_name"),rs1.getString("output"),rs1.getString("rule_id"),period, rs1.getString(period+"_action"));
						Rule rule = new Rule(rs1.getString("rule_name"),rs1.getString("output"),rs1.getString("rule_id"),period, rs1.getString(period+"_action"),rs1.getString("failed_rule_action_"+period));
						rule_details.add(rule);						
					}
				}
				else{
//					Rule rule = new Rule(rs1.getString("rule_name"),rs1.getString("output"),rs1.getString("rule_id"),period, rs1.getString(period+"_action"));
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
			logger.info(""+e);
		}
		return rs;
	}

	private static void updateLastStatuSno(Connection conn,String operator, int id,int limit) {
		String query = null;
		Statement stmt = null;
//		query = "update "+operator+"_raw"+" set status='Start' where sno>'"+id+"' and rownum<= "+limit;
		query = "update "+operator+"_raw"+" set status='Start' where sno>'"+id+"' and sno<="+(id+limit);
		logger.info(  "updateLastStatuSno qury.."+ query);
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
		logger.info("updateRawLastSno qury is "+ query);
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
