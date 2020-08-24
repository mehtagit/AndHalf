package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@Component
public class CountData {
	@JsonProperty("Date")
	@SerializedName("Date")
	private String date;
	
	@JsonProperty("Total IMEI")
	@SerializedName("Total IMEI")
	private String totalIMEI;
	
	@JsonProperty("Total Unique IMEI")
	@SerializedName("Total Unique IMEI")
	private String totalUniqueIMEI;
	
	@JsonProperty("Total Paired IMEI")
	@SerializedName("Total Paired IMEI")
	private String totalPairedIMEI;
	
	@JsonProperty("Approved TAC")
	@SerializedName("Approved TAC")
	private String approvedTAC;
	
	@JsonProperty("Rejected TAC")
	@SerializedName("Rejected TAC")
	private String rejectedTAC;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotalIMEI() {
		return totalIMEI;
	}

	public void setTotalIMEI(String totalIMEI) {
		this.totalIMEI = totalIMEI;
	}

	public String getTotalUniqueIMEI() {
		return totalUniqueIMEI;
	}

	public void setTotalUniqueIMEI(String totalUniqueIMEI) {
		this.totalUniqueIMEI = totalUniqueIMEI;
	}

	public String getTotalPairedIMEI() {
		return totalPairedIMEI;
	}

	public void setTotalPairedIMEI(String totalPairedIMEI) {
		this.totalPairedIMEI = totalPairedIMEI;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CountData [date=");
		builder.append(date);
		builder.append(", totalIMEI=");
		builder.append(totalIMEI);
		builder.append(", totalUniqueIMEI=");
		builder.append(totalUniqueIMEI);
		builder.append(", totalPairedIMEI=");
		builder.append(totalPairedIMEI);
		builder.append(", approvedTAC=");
		builder.append(approvedTAC);
		builder.append(", rejectedTAC=");
		builder.append(rejectedTAC);
		builder.append("]");
		return builder.toString();
	} 
	
}
