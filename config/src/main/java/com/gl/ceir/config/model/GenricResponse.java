package com.gl.ceir.config.model;

public class GenricResponse {


	private int errorCode;
	private String message;
	private String txnId;


	public GenricResponse(int errorCode, String message, String txnId) {
		this.errorCode = errorCode;
		this.message = message;
		this.txnId = txnId;
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


	public String getTxnId() {
		return txnId;
	}


	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}


	@Override
	public String toString() {
		return "GenricResponse [errorCode=" + errorCode + ", message=" + message + ", txnId=" + txnId + "]";
	}



}
