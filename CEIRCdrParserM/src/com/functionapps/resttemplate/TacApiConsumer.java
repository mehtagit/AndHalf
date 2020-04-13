package com.functionapps.resttemplate;

import org.apache.log4j.Logger;

import com.functionapps.constants.PropertyReader;

public class TacApiConsumer {

	private	final static Logger logger = Logger.getLogger(TacApiConsumer.class);

	PropertyReader propertyReader;

	public TacApiConsumer() {
		propertyReader = new PropertyReader();
	}

	public String updateStatus(String txnId, Long userId, String userType, int deleteFlag){

		try {
			String result = "";
			String uri = propertyReader.getPropValue("api.tac.delete") 
					+ "?txnId=" + txnId + "&"
					+ "userId=" + userId + "&"
					+ "UserType=" + userType + "&"
					+ "deleteFlag=" + deleteFlag;

			

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}
