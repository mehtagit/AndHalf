package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@Component
public class ActiveDeviceGraphRowData {
	@SerializedName("Approved TAC")
	private String approvedTAC;
	@SerializedName("Rejected TAC")
	private String rejectedTAC;
	
	
	@SerializedName("Created On")
	private String createdOn;
	@SerializedName("Modified On")
	private String modifiedOn;
	
	@JsonProperty("Date")
	@SerializedName("Date")
	private String date;
	
	@JsonProperty("Operator Name")
	@SerializedName("Operator Name")
	private String operatorName;
	
	@JsonProperty("Total IMEI")
	@SerializedName("Total IMEI")
	private String totalImei;
	
	@JsonProperty("QB")
	@SerializedName("QB")
	private String qb;
	
	@JsonProperty("SEATEL")
	@SerializedName("SEATEL")
	private String seatel;
	
	@JsonProperty("SMART")
	@SerializedName("SMART")
	private String smart;
	
	@JsonProperty("CELLCARD")
	@SerializedName("CELLCARD")
	private String cellcard;
	
	@JsonProperty("METFONE")
	@SerializedName("METFONE")
	private String metfone;
	
	
	public String getApprovedTAC() {
		return approvedTAC;
	}
	public void setApprovedTAC(String approvedTAC) {
		this.approvedTAC = approvedTAC;
	}
	public String getRejectedTAC() {
		return rejectedTAC;
	}
	public void setRejectedTAC(String rejectedTAC) {
		this.rejectedTAC = rejectedTAC;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getImeiCount() {
		return imeiCount;
	}
	public void setImeiCount(String imeiCount) {
		this.imeiCount = imeiCount;
	}
	public String getMsisdnFrequency() {
		return msisdnFrequency;
	}
	public void setMsisdnFrequency(String msisdnFrequency) {
		this.msisdnFrequency = msisdnFrequency;
	}
	@SerializedName("IMEI Count")
	private String imeiCount;
	@SerializedName("MSISDN Frequency")
	private String msisdnFrequency;


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public String getTotalImei() {
		return totalImei;
	}
	public void setTotalImei(String totalImei) {
		this.totalImei = totalImei;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActiveDeviceGraphRowData [approvedTAC=");
		builder.append(approvedTAC);
		builder.append(", rejectedTAC=");
		builder.append(rejectedTAC);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", date=");
		builder.append(date);
		builder.append(", operatorName=");
		builder.append(operatorName);
		builder.append(", totalImei=");
		builder.append(totalImei);
		builder.append(", qb=");
		builder.append(qb);
		builder.append(", seatel=");
		builder.append(seatel);
		builder.append(", smart=");
		builder.append(smart);
		builder.append(", cellcard=");
		builder.append(cellcard);
		builder.append(", metfone=");
		builder.append(metfone);
		builder.append(", imeiCount=");
		builder.append(imeiCount);
		builder.append(", msisdnFrequency=");
		builder.append(msisdnFrequency);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
