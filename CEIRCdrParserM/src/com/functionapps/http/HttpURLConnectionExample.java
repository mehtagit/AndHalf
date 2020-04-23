package com.functionapps.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

	private static final String POST_PARAMS = "";

	public static String sendGET(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		StringBuffer response = new StringBuffer();
		
		System.out.println("GET Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}
		
		return response.toString();

	}

	public static String sendPOST(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END
		
		StringBuffer response = new StringBuffer();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			
		} else {
			System.out.println("POST request not worked");
		}
		
		return response.toString();
	}

	public static void main(String[] args) {
		try {
			String txnId = "T20200411152839491";
			String userId = "360";
			String userType = "CEIRSYSTEM";
			String deleteFlag = "1";

			String uri = "http://172.24.2.65:9502/CEIR/TypeApproved/delete" 
					+ "?txnId=" + txnId + "&"
					+ "userId=" + userId + "&"
					+ "userType=" + userType + "&"
					+ "deleteFlag=" + deleteFlag;

			sendPOST(uri);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}