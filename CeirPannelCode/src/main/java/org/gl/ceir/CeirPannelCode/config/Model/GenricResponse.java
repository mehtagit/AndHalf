package org.gl.ceir.CeirPannelCode.config.Model;

public class GenricResponse {


	private int errorCode;
	private String message;

	
	public GenricResponse(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}


	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}



}
