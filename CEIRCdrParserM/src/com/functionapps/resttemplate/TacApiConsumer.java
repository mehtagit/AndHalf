package com.functionapps.resttemplate;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.functionapps.constants.PropertyReader;

public class TacApiConsumer {

	private	final static Logger logger = Logger.getLogger(TacApiConsumer.class);

	PropertyReader propertyReader;
	RestTemplate restTemplate;

	public TacApiConsumer() {
		propertyReader = new PropertyReader();
		restTemplate = new RestTemplate();
	}

	public ResponseEntity<String> updateStatus(String txnId, Long userId, String userType, int deleteFlag){

		try {
			String uri = propertyReader.getPropValue("api.tac.delete") 
					+ "?txnId=" + txnId + "&"
					+ "userId=" + userId + "&"
					+ "UserType=" + userType + "&"
					+ "deleteFlag=" + deleteFlag;

			System.out.println("Tac delete URI : " + uri);


			ResponseEntity<String> result = restTemplate.postForEntity(uri, 
					"{}", 
					String.class);

			System.out.println("Response of Tac delete URI " + result.getBody());
			logger.info("Response of Tac delete URI " + result.getBody());

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}
