package com.functionapps.parser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class CEIRFeatureFileFunctions {

    Logger logger = Logger.getLogger(CEIRFeatureFileFunctions.class);

//    public ResultSet getFileDetails(Connection conn) {
//        Statement stmt = null;
//        ResultSet rs = null;
//        String query = null;
//        String limiter = " limit 1 ";
//        if (conn.toString().contains("oracle")) {
//            limiter = " fetch next 1 rows only ";
//        }
//
//        try { /// state=0 and (feature = 'CONSIGNMENT' or feature = 'STOCK')
//            query = "select * from web_action_db where state=0      order by id asc " + limiter + "   ";
//            logger.info("Query to get  (getFileDetails) File Details [" + query + "]");
//
//            stmt = conn.createStatement();
//            return rs = stmt.executeQuery(query);
//        } catch (Exception e) {
//
//            logger.info("" + e);
//        }
//        return rs;
//
//    }

    
    public ResultSet getFileDetails(Connection conn, int state) {
		Statement stmt = null;
		ResultSet rs = null;
		String query = null;
                String limiter = " limit 1 ";
        if (conn.toString().contains("oracle")) {
            limiter = " fetch next 1 rows only ";
        }
                
		try{
//        	query = "select * from web_action_db where state="+state+" and feature='TYPE_APPROVED' order by id desc ";
			query = "select * from web_action_db where state="+state+"  order by id asc " + limiter + "  ";
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
    
    
    
    
    
    
    public HashMap<String, String> getFeatureMapping(Connection conn, String feature, String usertype_name) {
        HashMap<String, String> feature_mapping = new HashMap<String, String>();
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        String addQuery = "";
        String limiter = " limit 1 ";
        if (conn.toString().contains("oracle")) {
            limiter = " fetch next 1 rows only ";
        }

        if (!usertype_name.equals("NOUSER")) {
            addQuery = " and usertype_name = '" + usertype_name + "'   ";
        }

        try {
            query = "select * from feature_mapping_db where  feature='" + feature + "'  " + addQuery + "    " + limiter
                    + "   "; 
            logger.info("Query to get  (tFILEFUNCTIONgetFeatureMapping) File Details [" + query + "]");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                feature_mapping.put("usertype", rs.getString("usertype"));
                feature_mapping.put("feature", feature);
                feature_mapping.put("mgnt_table_db", rs.getString("mgnt_table_db"));
                feature_mapping.put("output_device_db", rs.getString("output_device_db"));
            }
        } catch (Exception e) {
            logger.info("  getFeatureMapping errror" + e);

        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                logger.info("errror" + e);
            }
        }

        return feature_mapping;

    }

    public HashMap<String, String> getFeatureFileManagement(Connection conn, String management_db, String txn_id) {
        HashMap<String, String> feature_file_management_details = new HashMap<String, String>();
        Statement stmt = null;
        ResultSet rs = null;
        String query = null;
        try {
            query = "select * from " + management_db + " where  txn_id='" + txn_id + "'";
            logger.info("Query to (getFeatureFileManagement) File Details [" + query + "]");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                feature_file_management_details.put("user_id", rs.getString("user_id"));
                feature_file_management_details.put("file_name", rs.getString("file_name"));
                feature_file_management_details.put("created_on", rs.getString("created_on"));
                feature_file_management_details.put("modified_on", rs.getString("modified_on"));
            }
        } catch (Exception e) {
            logger.info("Exception in feature_file_management_details" + e);

        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                logger.info("errror" + e);
            }
        }

        return feature_file_management_details;

    }

    public void addFeatureFileConfigDetails(Connection conn, String type, String feature, String sub_feature,
            String txn_id, String file_name, String status, String user_type) {
        String query = "";
        Statement stmt = null;
        if (type == "insert") {
            query = "insert into feature_file_config_db (feature,sub_feature,txn_id,file_name,status,usertype_name)values('"
                    + feature + "','" + sub_feature + "','" + txn_id + "','" + file_name + "','" + status + "','"
                    + user_type + "')";
        } else {
            query = "update feature_file_config_db set status='" + status + "' where feature = '" + feature
                    + "' and txn_id='" + txn_id + "' and sub_feature='" + sub_feature + "'";
        }
        logger.info("A config DB[" + query + "]");

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {
            logger.info("errror" + e);
        } finally {
            try {
                stmt.close();
                 conn.commit();
            } catch (Exception e) {
                logger.info("errror" + e);
            }
        }
    }

    public void updateFeatureFileStatus(Connection conn, String txn_id, int status, String feature, String subfeature) {
        String query = "";
        Statement stmt = null;
        int earlierState = 0;
        if (status == 1) {
            earlierState = 0;
        } else {
            earlierState = 1;
        }
        query = "update web_action_db set state=" + status + " where txn_id='" + txn_id + "' and feature='" + feature
                + "' and sub_feature='" + subfeature + "' "
                //						+ "  and  state = " + earlierState + " "
                + "";
        logger.info("update web action db...[" + query + "]");
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {
            logger.info("errror" + e);
        } finally {
            try {
                stmt.close();
                conn.commit();
            } catch (Exception e) {
                logger.info("Error at updateFeatureFileStatus.." + e);
            }
        }

    }

    public void updateFeatureManagementStatus(Connection conn, String txn_id, int status, String table_name, String main_type) {
        String query = "";
        Statement stmt = null;
        if (table_name.equalsIgnoreCase("stolenand_recovery_mgmt")) {
            main_type = "file";
        }
        query = "update " + table_name + " set   " + main_type.trim().toLowerCase() + "_status=" + status
                + " where txn_id='" + txn_id + "'";
        logger.info("update  " + main_type.toLowerCase() + "_status db..[" + query + "]");
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {

            logger.info("Error at updateFeatureManagementStatus.." + e);
        } finally {
            try {
                stmt.close();
                conn.commit();
            } catch (Exception e) {
                logger.info("Error at updateFeatureManagementStatus.." + e);
            }
        }

    }

    public String getUserType(Connection conn, String user_id, String main_type, String txn_id) {
        String user_type = null;
        String query = null;
        Statement stmt = null;
        ResultSet rs = null;

        query = "select b.usertype_name as usertype_name from users a, usertype b where a.usertype_id=b.id and a.id='"
                + user_id + "'";

        if (main_type.toLowerCase().equals("stock")) {
            query = "select  role_type  as usertype_name from stock_mgmt  where txn_id = '" + txn_id + "'"; // hardcodeed
        }
        logger.info(" Query at (getUserType)  for " + main_type + ":::" + query);
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                user_type = rs.getString(1);
            }
            logger.info("user_type.. +" + user_type);

        } catch (Exception e) {
            logger.info("errror" + e);
            logger.info("errror" + e);
        }
        try {
            rs.close();
            stmt.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.info("errror" + e);
        }
        return user_type;
    }

    public void UpdateStatusViaApi(Connection conn, String txn_id, int ConsigAction, HashMap<String, String> map,
            String feature, int StlnAction) {
        logger.info("consignmentUpdateViaApi.. ");
        ResultSet rs1 = null;
        Statement stmt = null;
        String tag = null;

        String apiURI = null;
        String payload = null;
        String featureId = "";
        String requestType = "";
        // String txn_id = map.get("txn_id");;
        String userId = "";

        if ((feature.equalsIgnoreCase("stolen") || feature.equalsIgnoreCase("recovery")
                || feature.equalsIgnoreCase("block") || feature.equalsIgnoreCase("unblock"))) {
            featureId = (map.get("request_type").equals("0") || map.get("request_type").equals("1")) ? "5" : "7";
            requestType = map.get("request_type");
            apiURI = "stolen-recovery_mailURI";
            userId = map.get("user_id");

            payload = " {\n" + "\"action\":" + StlnAction + ",\n" + "\"featureId\":" + featureId + ",\n"
                    + "\"remarks\":\"DONE\",\n" + "\"requestType\":" + requestType + ",\n"
                    + "\"roleType\":\"CEIRSYSTEM\",\n" + "\"roleTypeUserId\":0,\n" + "\"txnId\":\"" + txn_id + "\",\n"
                    + "\"userId\":" + userId + ",\n" + "\"userType\": \"CEIRSYSTEM\"\n" + "}  ";
        }

        if (feature.equalsIgnoreCase("consignment")) {
            apiURI = "mail_api_path";
            payload = "{  \"action\":    " + ConsigAction
                    + "    ,  \"requestType\": 0,  \"roleType\": \"CEIRSYSTEM\",  \"txnId\": \"" + txn_id
                    + "\"  ,\"featureId\" : 3 }";

        }

        String query = "select value from system_configuration_db where tag='" + apiURI + "'";

        try {

            logger.info("Query is " + query);
            logger.info("............" + payload);
            stmt = conn.createStatement();
            rs1 = stmt.executeQuery(query);
            while (rs1.next()) {
                tag = rs1.getString("value");
            }
            ;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                stmt.close();
                ;
            } catch (Exception e) {
                logger.info("errror" + e);
            }
        }
        logger.info("mail_api_path_ url is " + tag);

        String result = null;
        try {

            URL url = new URL(tag);
            HttpURLConnection hurl = (HttpURLConnection) url.openConnection();
            hurl.setRequestMethod("PUT");
            hurl.setDoOutput(true);
            hurl.setRequestProperty("Content-Type", "application/json");
            hurl.setRequestProperty("Accept", "application/json");

            // String payload = "{ \"action\": " + action + " , \"requestType\": 0,
            // \"roleType\": \"CEIRSYSTEM\", \"txnId\": \"" + txn_id + "\" ,\"featureId\" :
            // 3 }";
            OutputStreamWriter osw = new OutputStreamWriter(hurl.getOutputStream());
            osw.write(payload);
            osw.flush();
            osw.close();

            logger.info("DatA Putted");
            hurl.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(hurl.getInputStream()));
            String temp = null;
            StringBuilder sb = new StringBuilder();
            while ((temp = in.readLine()) != null) {
                sb.append(temp).append(" ");
            }
            result = sb.toString();
            in.close();
            logger.info("OUTPUT result is .." + result);
        } catch (IOException e) {
            logger.info("errror" + e);

        }

    }

    public HttpURLConnection getHttpConnection(String url, String type) {
        URL uri = null;
        HttpURLConnection con = null;
        try {
            uri = new URL(url);
            con = (HttpURLConnection) uri.openConnection();
            con.setRequestMethod(type); // type: POST, PUT, DELETE, GET
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setConnectTimeout(60000); // 60 secs...
            con.setReadTimeout(60000); // 60 secs
            con.setRequestProperty("Accept-Encoding", "application/json");
            con.setRequestProperty("Content-Type", "application/json");
        } catch (Exception e) {
            logger.info("connection i/o failed " + e);
        }
        return con;
    }

//	public void pdateFeatureManagementStatus(Connection conn, String txn_id,int status,String table_name) {
//		String query = "";
//		Statement stmt = null;
//		query = "update "+table_name+" set status="+status+" where txn_id='"+txn_id+"'";			
//		logger.info("update management db status ["+query+"]");
//		System.out.println("update management db status["+query+"]");
//		try {
//			stmt = conn.createStatement();
//			stmt.executeUpdate(query);
//			conn.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				stmt.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
    
        
	public void updateFeatureManagementDeleteStatus(Connection conn, String txn_id,int status,String table_name) {
		String query = "";
		Statement stmt = null;
		query = "update "+table_name+" set delete_status ="+status+" where txn_id='"+txn_id+"'";			
		logger.info("update delete status ["+query+"]");
		System.out.println("update delete status ["+query+"]");
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
    
    
    
}

//        try {
//          URL  url = new URL(tag);
//            HttpURLConnection hurl = (HttpURLConnection) url.openConnection();
//            hurl.setRequestMethod("PUT");
//            hurl.setDoOutput(true);
//            hurl.setRequestProperty("Content-Type", "application/json");
//            hurl.setRequestProperty("Accept", "application/json");
//
//            String payload = "{  action: " + action + ",  requestType: 0,  roleType: CEIRSYSTEM,  txnId: " + txn_id + ",featureId : 3 }";
//
//            OutputStreamWriter osw = new OutputStreamWriter(hurl.getOutputStream());
//            osw.write(payload);
//            osw.flush();
//            osw.close();
//            logger.info("Consignment status have Update SuccessFully  with status" + action + " for txn_d" + txn_id);
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            logger.info("errror" + e);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            logger.info("errror" + e);
//        }
// con = getHttpConnection(tag, "PUT");
//            //you can add any request body here if you want to post
//            logger.info("conn. Reutrned");
//            con.setDoInput(true);
//            con.setDoOutput(true);
//            DataOutputStream out = new DataOutputStream(con.getOutputStream());
//            out.writeBytes(reqbody);
//            out.flush();
//            out.close();
