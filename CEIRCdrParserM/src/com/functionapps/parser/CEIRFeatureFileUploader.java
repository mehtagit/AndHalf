package com.functionapps.parser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class CEIRFeatureFileUploader {

	/**
	 * @param args
	 */
	static Logger logger = Logger.getLogger(CEIRFeatureFileUploader.class);

	public static void main(String[] args) {
		System.out.println("Process Started");
		Connection conn = new com.functionapps.db.MySQLConnection().getConnection();
		System.out.println("Connection Created");
		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
		System.out.println("ceirfunction Created");
		ResultSet file_details = ceirfunction.getFileDetails(conn,0);
		HexFileReader hfr      = new HexFileReader();
		String basePath = "";
		String complete_file_path = "";
		CEIRParserMain ceir_parser_main = new CEIRParserMain();
		int raw_upload_set_no=1;
		String[] rawDataResult = null;
		basePath = hfr.getFilePath(conn,"system_upload_filepath");
		if(!basePath.endsWith("/")){
			basePath+="/";
		}


		try {
			while(file_details.next()){
				System.out.println("base path is "+basePath);
				System.out.println("file_details.getString(feature)" + file_details.getString("feature"));
				System.out.println("file_details.getString(sub_feature)" + file_details.getString("sub_feature"));
				
				HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
				feature_file_mapping = ceirfunction.getFeatureMapping(conn,file_details.getString("feature"));
				System.out.println("feature_file_mapping " + feature_file_mapping);
				
				HashMap<String, String> feature_file_management = new HashMap<String, String>();
				feature_file_management = ceirfunction.getFeatureFileManagement(conn,feature_file_mapping.get("mgnt_table_db"),file_details.getString("txn_id"));
				System.out.println("feature_file_management " + feature_file_management);
				
				String user_type = ceirfunction.getUserType(conn,feature_file_management.get("user_id"));
				System.out.println("user_type " + user_type);
				
				ceirfunction.addFeatureFileConfigDetails(conn, "insert",file_details.getString("feature"),file_details.getString("sub_feature"),file_details.getString("txn_id"),feature_file_management.get("file_name"),"Init" , user_type);
				System.out.println("ceirfunction.addFeatureFileConfigDetails");
				
				ceirfunction.updateFeatureFileStatus(conn,file_details.getString("txn_id"),1,file_details.getString("feature"),file_details.getString("sub_feature"));
				System.out.println("ceirfunction.updateFeatureFileStatus");
				
				if(feature_file_management.get("file_name")!= null){
					ResultSet my_result_set = ceir_parser_main.operatorDetails(conn,file_details.getString("feature"));
					System.out.println("my_result_set " + my_result_set);
					
					if(my_result_set.next()){
						raw_upload_set_no = my_result_set.getInt("raw_upload_set_no");
					}
					complete_file_path = basePath+file_details.getString("txn_id")+"/"+feature_file_management.get("file_name");					
					System.out.println("Complete file name "+complete_file_path);

					if(file_details.getString("sub_feature").equalsIgnoreCase("delete")){
						ceirfunction.updateFeatureManagementDeleteStatus(conn, file_details.getString("txn_id"), 1, feature_file_mapping.get("mgnt_table_db"));						
					}else{
						ceirfunction.updateFeatureManagementStatus(conn, file_details.getString("txn_id"), 1, feature_file_mapping.get("mgnt_table_db"));
						rawDataResult = hfr.readConvertedFeatureFile(conn ,feature_file_management.get("file_name"), complete_file_path, file_details.getString("feature"),basePath,raw_upload_set_no,file_details.getString("txn_id"),file_details.getString("sub_feature"),feature_file_mapping.get("mgnt_table_db"));						
					}	
				}else {
					if(file_details.getString("feature").equalsIgnoreCase("TYPE_APPROVED")
							&& file_details.getString("sub_feature").equalsIgnoreCase("register")) {
						
						ceirfunction.updateFeatureManagementStatus(conn, file_details.getString("txn_id"), 
								1, 
								feature_file_mapping.get("mgnt_table_db"));
					}
				}				
			}
			raw_upload_set_no=1;

		} catch (SQLException e) {
			logger.info("CEIRFileUploader Exception ["+e+"]");
			System.out.println("Exception");
			e.printStackTrace();
		}

	}

}
