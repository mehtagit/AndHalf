package com.functionapps.resttemplate;

import org.apache.log4j.Logger;

import com.functionapps.constants.PropertyReader;
import com.functionapps.http.HttpURLConnectionExample;
import com.functionapps.pojo.HttpResponse;
import com.google.gson.Gson;

public class TacApiConsumer {

	private	final static Logger logger = Logger.getLogger(TacApiConsumer.class);

	PropertyReader propertyReader;
	Gson gson;

	public TacApiConsumer() {
		propertyReader = new PropertyReader();
		gson = new Gson();

	}

	public HttpResponse updateStatus(String txnId, Long userId, String userType, int deleteFlag){

		try {
			String result = "";
			String uri = propertyReader.getPropValue("api.tac.delete")
					+ "?txnId=" + txnId + "&"
					+ "userId=" + userId + "&"
					+ "userType=" + userType + "&"
					+ "deleteFlag=" + deleteFlag;

			String response = HttpURLConnectionExample.sendPOST(uri);
			HttpResponse httpResponse = gson.fromJson(response, HttpResponse.class);
			return httpResponse;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}
