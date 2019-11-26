package com.gl.CEIR.CDRPicker.beans;

public class RedisParams {

	private  String imei;
	private String imsi;
	private String mSSIDN;
	private String operator;
	private String date;
	private String rATType;
	private String fileName;
	private String countType;
	private String location;
	private String recordType;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getmSSIDN() {
		return mSSIDN;
	}
	public void setmSSIDN(String mSSIDN) {
		this.mSSIDN = mSSIDN;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getrATType() {
		return rATType;
	}
	public void setrATType(String rATType) {
		this.rATType = rATType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	@Override
	public String toString() {
		return "RedisParams [imei=" + imei + ", imsi=" + imsi + ", mSSIDN=" + mSSIDN + ", operator=" + operator
				+ ", date=" + date + ", rATType=" + rATType + ", fileName=" + fileName + ", countType=" + countType
				+ ", location=" + location + ", recordType=" + recordType + "]";
	}
	
	
	
}
