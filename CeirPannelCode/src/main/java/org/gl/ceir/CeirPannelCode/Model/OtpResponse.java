package org.gl.ceir.CeirPannelCode.Model;

import org.gl.ceir.CeirPannelCode.Util.HttpResponse;

public class OtpResponse {
	private String response;
	private Integer statusCode;
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

    

