package org.gl.ceir.CeirPannelCode.Model;

public class GenricResponse {

	
	private String errorCode;
	private String txnId;
	private String  message;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "GenricResponse [errorCode=" + errorCode + ", txnId=" + txnId + ", message=" + message + "]";
	}
	
	
	
	
}
