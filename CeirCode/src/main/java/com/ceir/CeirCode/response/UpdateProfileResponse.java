package com.ceir.CeirCode.response;

public class UpdateProfileResponse {
	private String response;
	private Integer statusCode;
	private String userstatus;
	private long userId;
	
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
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UpdateProfileResponse [response=" + response + ", statusCode=" + statusCode + ", userstatus="
				+ userstatus + ", userId=" + userId + "]";
	}

	
}
