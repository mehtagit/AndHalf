package org.gl.ceir.graph.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.annotations.SerializedName;
@Component
public class RowData {
	@SerializedName("Approved TAC")
	private String approvedTAC;
	@SerializedName("Rejected TAC")
	private String rejectedTAC;
	
	
	@SerializedName("Created On")
	private String createdOn;
	@SerializedName("Modified On")
	private String modifiedOn;
	
	
	@SerializedName("IMEI Count")
	private String imeiCount;
	@SerializedName("MSISDN Frequency")
	private String msisdnFrequency;
	
	

    	 
    	 
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
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
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
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RowData [approvedTAC=");
		builder.append(approvedTAC);
		builder.append(", rejectedTAC=");
		builder.append(rejectedTAC);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", imeiCount=");
		builder.append(imeiCount);
		builder.append(", msisdnFrequency=");
		builder.append(msisdnFrequency);
		builder.append(", additionalProperties=");
		builder.append(additionalProperties);
		builder.append("]");
		return builder.toString();
	}

}
