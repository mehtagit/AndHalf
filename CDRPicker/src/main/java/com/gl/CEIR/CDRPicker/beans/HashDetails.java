package com.gl.CEIR.CDRPicker.beans;

public class HashDetails {

	private String msisdn;
	private String imsi;
	private int count;
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "HashDetails [msisdn=" + msisdn + ", imsi=" + imsi + ", count=" + count + "]";
	}
	
	
	
	
}
