package com.gl.ceir.config.model;

public class ApiResponse {

	private int responseCode;
	private String responseStatus;
	private Object data;

	public ApiResponse(Object data) {
		this.responseCode = 0;
		this.responseStatus = "SUCCESS";
		this.data = data;
	}

	public ApiResponse(int responseCode, String responseStatus) {
		this.responseCode = responseCode;
		this.responseStatus = responseStatus;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public Object getData() {
		return data;
	}

}
