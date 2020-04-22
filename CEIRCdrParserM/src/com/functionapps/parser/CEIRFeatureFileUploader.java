package com.functionapps.parser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.functionapps.parser.ErrorFileGenrator;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.apache.log4j.Logger;

public class CEIRFeatureFileUploader {

	/**
	 * @param args
	 */
	static Logger logger = Logger.getLogger(CEIRFeatureFileUploader.class);

	public static void main(String[] args) {
		logger = Logger.getLogger(CEIRFeatureFileUploader.class);
//		String main_type = args[0];

		logger.info(" ");
		logger.info(" ");
		logger.info(" ");
		Connection conn = new com.functionapps.db.MySQLConnection().getConnection();
		CEIRFeatureFileFunctions ceirfunction = new CEIRFeatureFileFunctions();
		ResultSet file_details = ceirfunction.getFileDetails(conn);  //select * from web_action_db
		
		HexFileReader hfr = new HexFileReader();
		String basePath = "";
		String complete_file_path = "";
		CEIRParserMain ceir_parser_main = new CEIRParserMain();
		int raw_upload_set_no = 1;
		String[] rawDataResult = null;
		basePath = hfr.getFilePath(conn, "system_upload_filepath");  // filePath
		if (!basePath.endsWith("/")) {
			basePath += "/";
		}
		logger.info("Files basePath "+ basePath);
		ErrorFileGenrator errFile = new ErrorFileGenrator();
		//   errFile.gotoErrorFile(basePath, basePath);

		try {                                             //   NOTE main_type = file_details.getString("feature")
			while (file_details.next()) {
				logger.info("base path is " + basePath);
				logger.info( " file_details.getString FEATURE.."+  file_details.getString("feature")  );   // add usertype name 
//				CEIRFeatureFileParser cfp = new CEIRFeatureFileParser();
				String error_file_path = CEIRFeatureFileParser.getErrorFilePath(conn) + file_details.getString("txn_id") + "/" + file_details.getString("txn_id") + "_error.csv";
				File errorfile = new File(error_file_path);
				if (errorfile.exists()) {     // in case of update  ,, erlier file is deleted
					logger.info("Error file already esists ");
					if(errorfile.delete()) 
					{ 
						logger.info("File deleted successfully"); 
					} 
					else
					{ 
						logger.info("Failed to delete the file"); 
					} 
				}

				HashMap<String, String> feature_file_mapping = new HashMap<String, String>();
				feature_file_mapping = ceirfunction.getFeatureMapping(conn, file_details.getString("feature") , "NOUSER");  //select * from feature_mapping_db
				logger.info(",,,,,,,,,,,");
				HashMap<String, String> feature_file_management = new HashMap<String, String>();
				feature_file_management = ceirfunction.getFeatureFileManagement(conn, feature_file_mapping.get("mgnt_table_db"), file_details.getString("txn_id"));   //select * from " + management_db + " 

				if (feature_file_management.get("file_name") != null) {
					String user_type = ceirfunction.getUserType(conn, feature_file_management.get("user_id")  ,file_details.getString("feature").toUpperCase()  ,file_details.getString("txn_id")  );      //   usertype_name from users a, usertype b
					ceirfunction.addFeatureFileConfigDetails(conn, "insert", file_details.getString("feature"), file_details.getString("sub_feature"), file_details.getString("txn_id"), feature_file_management.get("file_name"), "Init", user_type);
					logger.info("*****");
					ceirfunction.updateFeatureFileStatus(conn, file_details.getString("txn_id"), 1, file_details.getString("feature"), file_details.getString("sub_feature"));  //insert into feature_file_config_db
					ceirfunction.updateFeatureManagementStatus(conn, file_details.getString("txn_id"), 1, feature_file_mapping.get("mgnt_table_db")   , file_details.getString("feature").toUpperCase()  ) ;  //update " + table_name + " set   " + main_type.toLowerCase() + "_status=
					ResultSet my_result_set = ceir_parser_main.operatorDetails(conn, file_details.getString("feature"));     /// update "+operator+"_raw"+" set status='Start'  // can be removed

					if (my_result_set.next()) {
						raw_upload_set_no = my_result_set.getInt("raw_upload_set_no");
					}
					logger.info("raw_upload_set_no >>>>>>"+ raw_upload_set_no);
					complete_file_path = basePath + file_details.getString("txn_id") + "/" + feature_file_management.get("file_name");
					logger.info("Complete file name is.... " + complete_file_path);
					rawDataResult = hfr.readConvertedFeatureFile(conn, feature_file_management.get("file_name"), complete_file_path, file_details.getString("feature"), basePath, raw_upload_set_no, file_details.getString("txn_id"), file_details.getString("sub_feature"), feature_file_mapping.get("mgnt_table_db")   ,user_type);

				}
			}
			raw_upload_set_no = 1;
			logger.info("  **********************CEIRFileUploader Finished ******************");
		} catch (SQLException e) {
			logger.info("CEIRFileUploader  Finished with Exception [" + e + "]");
			e.printStackTrace();
		} catch (IOException ex) {
			java.util.logging.Logger.getLogger(CEIRFeatureFileUploader.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
