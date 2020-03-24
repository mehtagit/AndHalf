package org.gl.ceir.CeirPannelCode.Model;

public class CustomerCareRequest {
	private String deviceIdType;
	private String imei;
	private String msisdn;
	private String searchString;
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
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	@Override
	public String toString() {
		return "CustomerCareRequest [deviceIdType=" + deviceIdType + ", imei=" + imei + ", msisdn=" + msisdn
				+ ", searchString=" + searchString + "]";
	}

	
}
