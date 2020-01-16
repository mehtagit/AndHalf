package com.ceir.CeirCode.util;
public class OtpResponse {
	private String response;
	private Integer statusCode;

	
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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
		return "OtpResponse [response=" + response + ", statusCode=" + statusCode + ", userId=" + userId + "]";
	}


}
