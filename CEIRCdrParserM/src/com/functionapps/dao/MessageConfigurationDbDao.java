package com.functionapps.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.functionapps.pojo.MessageConfigurationDb;

public class MessageConfigurationDbDao {
	static Logger logger = Logger.getLogger(MessageConfigurationDbDao.class);
	static String GENERIC_DATE_FORMAT = "dd-MM-yyyy";

	public Optional<MessageConfigurationDb> getMessageDbTag(Connection conn, String managementDb, String tag) {
		ResultSet rs = null;
		String query = "select id, description, tag, value, channel, active, subject "
				+ "from message_configuration_db where tag='" + tag + "'";

		logger.info("Query ["+query+"]");
		System.out.println("Query ["+query+"]");
		
		try(Statement stmt  = conn.createStatement()){
			rs = stmt.executeQuery(query);

			if(rs.next()){
				return Optional.of(new MessageConfigurationDb(rs.getLong("id"), rs.getString("tag"), 
						rs.getString("value"), rs.getString("description"), rs.getInt("channel"), 
						rs.getString("subject")));
			}
		}
		catch(Exception e){
			logger.info(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
}