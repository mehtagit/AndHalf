package com.gl.ceir.config.model;

public class CustomerCareRequest {
	
	private String deviceIdType;
	private String imei;
	private String msisdn;
	
	public CustomerCareRequest() {
		
	}
	
	public String getDeviceIdType() {
		return deviceIdType;
	}
	public void setDeviceIdType(String deviceIdType) {
		this.deviceIdType = deviceIdType;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerCare [deviceIdType=");
		builder.append(deviceIdType);
		builder.append(", imei=");
		builder.append(imei);
		builder.append(", msisdn=");
		builder.append(msisdn);
		builder.append("]");
		return builder.toString();
	}
	
}
