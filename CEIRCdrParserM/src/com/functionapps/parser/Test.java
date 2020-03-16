package com.functionapps.parser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;


import com.mysql.jdbc.Statement;

public class Test {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ParseException, SQLException {
		
		String[] fileArray =null;
		String my_file = "SMART _20012020134956_00000";
		fileArray = my_file.split("_");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMddHHmmss");
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMddHHmmssSS");

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
//		String dateInString = "15-10-2015 10:20:56";
		Date date = sdf.parse(fileArray[fileArray.length-2]);
		System.out.println(sdf1.format(date));
		
		System.out.println("Data is "+fileArray[fileArray.length-2]);
		

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Date previousDate = sdf2.parse("2020-02-14");
		Date todayDate = new Date();
//		System.out.println("Today Date is "+sdf2.format(todayDate));
		if(todayDate.compareTo(previousDate)>=0){
			System.out.println("Post Grace");
		}
		else{
			System.out.println("Grace");
		}
		
		//		HexFileReader hfr = new HexFileReader();
//		Connection conn    = null;
//    	conn = new com.functionapps.db.MySQLConnection().getConnection();
//    	String basePath = hfr.getFilePath(conn);
//    	System.out.println("Base Path is "+basePath);
//    	
//		ArrayList<CDRColumn> filelist = hfr.getCDRFields(conn);
//		System.out.println(filelist.size());
//		for (CDRColumn fileob : filelist) {
//			System.out.println(fileob.columString);
//		}
		
		
//		HashMap<String, String> capitalCities = new HashMap<String, String>();
//
//	    // Add keys and values (Country, City)
//	    capitalCities.put("England", "London");
//	    capitalCities.put("Germany", "Berlin");
//	    capitalCities.put("Norway", "Oslo");
//	    capitalCities.put("USA", "Washington DC");
//	    Set<String> hash_keys = capitalCities.keySet();
//	    for(String key: hash_keys){
//	    	System.out.println(key);
//	    	System.out.println("Defined kye is ["+key+"] and value is["+capitalCities.get(key)+"]");
//	    }
//	    
//	    System.out.println(capitalCities.get("Berlin"));
	    

		String my_string = "hm.servedIMEI.len";
		System.out.println("first 8 digits "+my_string.substring(0,8));
		System.out.println(my_string.substring(3).substring(0,my_string.substring(3).length()-4));
		System.out.println(my_string.substring(my_string.length()-4,my_string.length()));
		System.out.println(my_string.charAt(my_string.length()-4));
		
		String served="";
		if(served==""||served==null){
			System.out.println("IMSE is Blanck or null");
		}
		
		ResultSet rs1 = my_Function(null, served);
		if(rs1.next()){
			System.out.println("Resust set is NULL");
		}
		System.out.println(rs1);
		System.out.println(rs1.getString("rep_name"));
	}

	static ResultSet my_Function(Connection conn, String operator){
		java.sql.Statement stmt = null;
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
}
