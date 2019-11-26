package com.gl.CEIR.GreyListProcessing.Beans;

public class DumpDetails {

	private String imei;
	private String operation;
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "DumpDetails [imei=" + imei + ", operation=" + operation + "]";
	}


	
	
}
