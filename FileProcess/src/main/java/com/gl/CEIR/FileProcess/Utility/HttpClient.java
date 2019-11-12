package com.gl.CEIR.FileProcess.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HttpClient {

	private final Logger logger = LoggerFactory.getLogger(getClass());


	private Map<String, String> headers;
	private List<String> responseHeadersName;

	public Map<String, String> getHeaders() {
		return headers;
	}

	public HttpClient setHeaders(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}

	public List<String> getResponseHeadersName() {
		return responseHeadersName;
	}

	public HttpClient setResponseHeadersName(List<String> responseHeadersName) {
		this.responseHeadersName = responseHeadersName;
		return this;
	}

	public HttpResponse sendGet(String url) throws IOException {
		HttpResponse httpCgResponse = null;

		try {
			httpCgResponse = new HttpResponse();
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "application/json");

			if(headers != null) {
				headers.forEach((k, v) -> con.setRequestProperty(k, v));
			}

			int responseCode = con.getResponseCode();

			httpCgResponse.setErrorCode(responseCode);

			if (responseCode/100 == 2) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				httpCgResponse.setResponse(response.toString());
			}else{
				logger.info("Connection : " + con);
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				logger.info("http CG Response: "+httpCgResponse);
				httpCgResponse.setResponse(response.toString());
			}

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return httpCgResponse;
	}

	public HttpResponse sendHead(String url) throws IOException {
		HttpResponse httpResponse = new HttpResponse();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("HEAD");

		if(headers != null) {
			headers.forEach((k, v) -> con.setRequestProperty(k, v));
		}

		int responseCode = con.getResponseCode();
		httpResponse.setErrorCode(responseCode);

		// Set Headers which came back in response.
		if(responseHeadersName.size() > 0) {
			Map<String, String> responseHeaderMap = new HashMap<>();

			for(String responseHeader : responseHeadersName) {
				responseHeaderMap.put(responseHeader, con.getHeaderField(responseHeader));
			}

			httpResponse.setResponseHeaders(responseHeaderMap);
		}

		// Check response.
		if (responseCode/100 == 2) {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			httpResponse.setResponse(response.toString());
		}else{

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			logger.info("http CG Response: " + httpResponse);
			httpResponse.setResponse(response.toString());
		}

		return httpResponse;
	}

	public HttpResponse sendPost(String url,String body){
		if(body == null) {
			logger.info("For post body can't equal to null");
			return null;
		}
		HttpResponse httpCustomResponse = new HttpResponse();
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			//con.setRequestProperty("Authorization", "NRcDvc4DBzeNNLnOF3OoTU-Fpc5aIqvf6ujv2k0Vh1mM3JFRrCRZW2TfaS053FJ8He8iD8KMvPabctbzoiAN4bHUGRqm-71XPGWTalrZCf_wMq3nvLaxwm8gWBgOL_g8MGP8l7YZPO6U6Dtvep4flKDPUSqmFv8V29QUiyjgNtJX1CsF2FB-cargSF5VDpIM8S2Dpd9-WqRIw6fEi4pMjKxZ9Rn91sIBk_eXRsAjVJ0gtHbvjOlK00TfqupwmdryO6yPfryNzlWiOUp0vqxSGfvFVJElFT3ye-RAen8PRYeLXMoOvVr21YZ7jwDauT3ZKkEeiPaIO0tjBhzrMd8LQK_lL5pmwvaUfM6Wl2DAxGUEmPwB5_gToCXriWzyGGs-gbpmSTLDhqihlLq-J3erHTc1IsPMOJKAQH1deGrWGrQwW6ZqMW7cYPxjOqDNup-RCViW2hYTbRlUL_OqvsgSxIlWGtSakAWj_0EMArBVIuPWCZDUACMbnEyg-lWq_Uau");

			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Content-type", "application/json");
			/*con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Language", "en-US");*/

			con.setRequestProperty("Content-Length", ""+body.getBytes().length);



			if(headers != null) {
				headers.forEach((k, v) -> con.setRequestProperty(k, v));
			}
			Map<String, List<String>> map = con.getHeaderFields();

			logger.info("Header="+map);
			// For POST only - START
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			os.close();
			// For POST only - END

			int responseCode = con.getResponseCode();
			logger.debug("POST Response Code :: " + responseCode);
			httpCustomResponse.setErrorCode(responseCode);
			if (responseCode/100 == 2) { //success

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				httpCustomResponse.setResponse(response.toString());
				// print result
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				httpCustomResponse.setResponse(response.toString());
			}
		}catch (Exception e) {
			logger.error(""+e);
		}

		return httpCustomResponse;
	}


	public String sendDelete(String url, String body) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-type", "application/json");

		if(headers != null) {
			headers.forEach((k, v) -> con.setRequestProperty(k, v));
		}

		int responseCode = con.getResponseCode();
		System.out.println("DELETE Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			return response.toString();
		} else {
			System.out.println("DELETE request not worked");
		}

		return null;
	}

	public String sendPut(String url, String body) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-type", "application/json");

		if(headers != null) {
			headers.forEach((k, v) -> con.setRequestProperty(k, v));
		}

		con.setDoOutput(true);

		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
		osw.write(body);
		osw.flush();
		osw.close();

		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) { //success

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			return response.toString();
		} else {
			System.out.println("Request not worked");
		}

		return null;
	}

	public String submitReuqestToServer(String url,String json) throws ParseException, IOException  {

		StringEntity input = new StringEntity(json);
		input.setContentType("application/json");

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost postRequest=null;

		postRequest = new HttpPost(url);

		postRequest.addHeader("Authorization", "bearer NRcDvc4DBzeNNLnOF3OoTU-Fpc5aIqvf6ujv2k0Vh1mM3JFRrCRZW2TfaS053FJ8He8iD8KMvPabctbzoiAN4bHUGRqm-71XPGWTalrZCf_wMq3nvLaxwm8gWBgOL_g8MGP8l7YZPO6U6Dtvep4flKDPUSqmFv8V29QUiyjgNtJX1CsF2FB-cargSF5VDpIM8S2Dpd9-WqRIw6fEi4pMjKxZ9Rn91sIBk_eXRsAjVJ0gtHbvjOlK00TfqupwmdryO6yPfryNzlWiOUp0vqxSGfvFVJElFT3ye-RAen8PRYeLXMoOvVr21YZ7jwDauT3ZKkEeiPaIO0tjBhzrMd8LQK_lL5pmwvaUfM6Wl2DAxGUEmPwB5_gToCXriWzyGGs-gbpmSTLDhqihlLq-J3erHTc1IsPMOJKAQH1deGrWGrQwW6ZqMW7cYPxjOqDNup-RCViW2hYTbRlUL_OqvsgSxIlWGtSakAWj_0EMArBVIuPWCZDUACMbnEyg-lWq_Uau");
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.setEntity(input);
		logger.info("request:" + json);
		CloseableHttpResponse response = httpClient.execute(postRequest);
		int respCode = response.getStatusLine().getStatusCode();
		if (respCode == 200) {
			return EntityUtils.toString(response.getEntity());
		}else {
			logger.info("Response Code="+respCode);
			logger.info("REsponse ="+response.getEntity());
		}
		return null;
	}



	public static void main() {
		HttpClient httpClient = new HttpClient();
		try {
			System.out.println(httpClient.sendHead("http://th.gw2cloud.com/api/aoc.php"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}