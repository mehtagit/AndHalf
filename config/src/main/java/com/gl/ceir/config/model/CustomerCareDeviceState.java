package com.gl.ceir.config.model;

import java.io.Serializable;

public class CustomerCareDeviceState implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String date;
	private String status;
	private String txnId;
	private Integer featureId;

	public CustomerCareDeviceState() {}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerCareDeviceState [name=");
		builder.append(name);
		builder.append(", date=");
		builder.append(date);
		builder.append(", status=");
		builder.append(status);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append("]");
		return builder.toString();
	}
	
}