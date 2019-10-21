package org.gl.ceir.CeirPannelCode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class HttpClient {


	public HttpResponse sendPOST(String url, String body) throws IOException {
		HttpResponse HttpResponse = new HttpResponse();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-type", "application/json");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(body.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		HttpResponse.setStatusCode(responseCode);
		if (responseCode == 200 || responseCode == 202) { //success

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			HttpResponse.setResponse(response.toString());
			// print result
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			HttpResponse.setResponse(response.toString());
			// print result
		}

		return HttpResponse;
	}
	
	
	public HttpResponse sendPOST(String url) throws IOException {
HttpResponse HttpResponse = new HttpResponse();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-type", "text/plain");

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		
		HttpResponse.setStatusCode(responseCode);
		
		if ((responseCode/100) == 2) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			HttpResponse.setResponse(response.toString());
		}else{
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			HttpResponse.setResponse(response.toString());
		}
		
		return HttpResponse;
	}


	public HttpResponse sendDELETE(String url) throws IOException {
HttpResponse HttpResponse = new HttpResponse();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-type", "text/plain");

		int responseCode = con.getResponseCode();
		System.out.println("DELETE Response Code :: " + responseCode);
		
		HttpResponse.setStatusCode(responseCode);
		
		if ((responseCode/100) == 2) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			HttpResponse.setResponse(response.toString());
		}else{
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			HttpResponse.setResponse(response.toString());
		}
		
		return HttpResponse;
	}
	
	public HttpResponse sendDELETE(String url, String body) throws IOException {
		HttpResponse HttpResponse = new HttpResponse();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-type", "application/json");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(body.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("DELETE Response Code :: " + responseCode);
		HttpResponse.setStatusCode(responseCode);
		if (responseCode == 200 || responseCode == 202) { //success

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			HttpResponse.setResponse(response.toString());
			// print result
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			HttpResponse.setResponse(response.toString());
			// print result
		}

		return HttpResponse;
			}



	public HttpResponse sendPUT(String url, String body) throws IOException {
		HttpResponse HttpResponse = new HttpResponse();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-type", "application/json");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(body.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("PUT Response Code :: " + responseCode);
		HttpResponse.setStatusCode(responseCode);
		if (responseCode == 200 || responseCode == 202) { //success

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			HttpResponse.setResponse(response.toString());
			// print result
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			HttpResponse.setResponse(response.toString());
			// print result
		}

		return HttpResponse;
	   }

	public HttpResponse sendGET(String url) throws IOException {
		HttpResponse HttpResponse = new HttpResponse();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-type", "text/plain");

		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		
		HttpResponse.setStatusCode(responseCode);
		
		if ((responseCode/100) == 2) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
     
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			HttpResponse.setResponse(response.toString());
		}else{
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			HttpResponse.setResponse(response.toString());
		}
		
		return HttpResponse;
	}
}