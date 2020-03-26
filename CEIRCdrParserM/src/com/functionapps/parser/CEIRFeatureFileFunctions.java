package com.functionapps.parser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class CEIRFeatureFileFunctions {
	Logger logger = Logger.getLogger(CEIRFeatureFileFunctions.class);

	public ResultSet getFileDetails(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		try{
        	query = "select * from web_action_db where state=0 and feature='consignment' order by id desc limit 1";
        	logger.info("Query to get File Details ["+query+"]");
        	stmt  = conn.createStatement();
			return rs    = stmt.executeQuery(query);
		}
		catch(Exception e){
        	logger.info("Exception in getFileDetails["+e+"]");
			System.out.println(""+e);
		}
		return rs;

	}

	public HashMap<String, String> getFeatureMapping(Connection conn,String feature) {
		HashMap<String, String> feature_mapping = new HashMap<String, String>();
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		try{
        	query = "select * from feature_mapping_db where  feature='"+feature+"'";
        	logger.info("Query to get File Details ["+query+"]");
        	stmt  = conn.createStatement();
        	rs = stmt.executeQuery(query);
        	while(rs.next()){
        		feature_mapping.put("usertype", rs.getString("usertype"));
        		feature_mapping.put("feature", feature);
        		feature_mapping.put("mgnt_table_db",rs.getString("mgnt_table_db"));
        		feature_mapping.put("output_device_db", rs.getString("output_device_db"));
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

		return feature_mapping;

	}

	public HashMap<String, String> getFeatureFileManagement(Connection conn,String management_db, String txn_id) {
		HashMap<String, String> feature_file_management_details = new HashMap<String, String>();
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
		try{
        	query = "select * from "+management_db+" where  txn_id='"+txn_id+"'";
        	logger.info("Query to get File Details ["+query+"]");
        	stmt  = conn.createStatement();
        	rs = stmt.executeQuery(query);
        	while(rs.next()){
        		feature_file_management_details.put("user_id", rs.getString("user_id"));
        		feature_file_management_details.put("file_name", rs.getString("file_name"));
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

		return feature_file_management_details;

	}

	public void addFeatureFileConfigDetails(Connection conn, String type, String feature,String sub_feature,String txn_id,String file_name,String status,String user_type) {
		String query = "";
		Statement stmt = null;
		if(type=="insert"){
			query ="insert into feature_file_config_db (feature,sub_feature,txn_id,file_name,status,usertype_name)values('"+feature+"','"+sub_feature+"','"+txn_id+"','"+file_name+"','"+status+"','"+user_type+"')";
		}		
		else{
			query = "update feature_file_config_db set status='"+status+"' where feature = '"+feature+"' and txn_id='"+txn_id+"' and sub_feature='"+sub_feature+"'";			
		}
		System.out.println("Add feature file Details in config DB["+query+"]");
		logger.info("Add feature file Details in config DB["+query+"]");
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
				e.printStackTrace();
			}
		}
	}

	public void updateFeatureFileStatus(Connection conn, String txn_id,int status,String feature,String subfeature) {
		String query = "";
		Statement stmt = null;
		query = "update web_action_db set state="+status+" where txn_id='"+txn_id+"' and feature='"+feature+"' and sub_feature='"+subfeature+"'";			
		logger.info("update web action db ["+query+"]");
		System.out.println("update web action db["+query+"]");
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			// conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void updateFeatureManagementStatus(Connection conn, String txn_id,int status,String table_name) {
		String query = "";
		Statement stmt = null;
		query = "update "+table_name+" set consignment_status="+status+" where txn_id='"+txn_id+"'";			
		logger.info("update web action db ["+query+"]");
		System.out.println("update web action db["+query+"]");
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
				e.printStackTrace();
			}
		}
		
	}

	public String getUserType(Connection conn, String user_id) {
		String user_type =null;
		String query = null;
		Statement stmt=null;
		ResultSet rs = null;
		query = "select b.usertype_name as usertype_name from users a, usertype b where a.usertype_id=b.id and a.id='"+user_id+"'";
		System.out.println("device usage db"+query);
		try {

			stmt = conn.createStatement();
			rs=stmt.executeQuery(query);			
			while(rs.next()){
				user_type = rs.getString("usertype_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user_type;
	}
	
}
