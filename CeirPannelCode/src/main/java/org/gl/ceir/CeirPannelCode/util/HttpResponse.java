package org.gl.ceir.CeirPannelCode.util;


public class HttpResponse {

	private String response;
	private Integer statusCode;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "LoginResponse [response=" + response + ", statusCode=" + statusCode + "]";
	}
	
	
}
