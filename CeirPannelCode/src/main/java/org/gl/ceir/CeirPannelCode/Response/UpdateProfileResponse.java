package org.gl.ceir.CeirPannelCode.Response;

public class UpdateProfileResponse {
	private String response;
	private Integer statusCode;
	private String userstatus;
	private Integer userId;
	
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UpdateProfileResponse [response=" + response + ", statusCode=" + statusCode + ", userstatus="
				+ userstatus + ", userId=" + userId + "]";
	}

	
}
