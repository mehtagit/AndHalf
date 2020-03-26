package com.functionapps.parser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.functionapps.parser.service.ApproveConsignment;
import com.functionapps.parser.service.ConsignmentDelete;
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
				CEIRFeatureFileParser ceirfileparser = new CEIRFeatureFileParser();
				feature = featurers.getString("feature");
				ArrayList rulelist = new ArrayList<Rule>();		
				String period = ceirfileparser.checkGraceStatus(conn);
				logger.info("Period is ["+period+"] ");
				rulelist = ceirfileparser.getRuleDetails(feature,conn,"" ,period,"",featurers.getString("usertype_name"));				
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

	public String checkGraceStatus(Connection conn) {
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
		
		
		Statement stmt = null;
		try{
			if(operator.equalsIgnoreCase("consignment") &&(sub_feature.equalsIgnoreCase("register") || sub_feature.equalsIgnoreCase("update"))){
				new ConsignmentInsertUpdate().process(conn,operator, sub_feature, rulelist, txn_id, operator_tag);
			}else if(operator.equalsIgnoreCase("consignment") &&(sub_feature.equalsIgnoreCase("delete"))){
				System.out.println("running consignment delete process.");
				new ConsignmentDelete().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag);
			}else if(operator.equalsIgnoreCase("consignment") &&(sub_feature.equalsIgnoreCase("approve"))){
				System.out.println("running consignment approve process.");
				new ApproveConsignment().process(conn, operator, sub_feature, rulelist, txn_id, operator_tag);
			}else {
				System.out.println("Skipping the process.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getErrorFilePath(Connection conn) {
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

	void updateRawData(Connection conn,String operator, String id,String status) {
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


	ArrayList getRuleDetails(String operator, Connection conn,String operator_tag,String period,String feature,String usertype_name) {
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
	
	public ResultSet operatorDetails(Connection conn, String operator){
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		try{
        	query = "select * from rep_schedule_config_db where operator='"+operator+"'";
			stmt  = conn.createStatement();
			return rs = stmt.executeQuery(query);
		}
		catch(Exception e){
			System.out.println(""+e);
		}
		return rs;
	}

	public void updateLastStatuSno(Connection conn,String operator, int id,int limit) {
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

	 public void updateRawLastSno(Connection conn,String operator, int sno) {
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

