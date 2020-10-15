package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class StockModelRowData {
	
	
	@SerializedName("Date")
	@JsonProperty("Date")
	private String date;
	
	@SerializedName("Withdrawn By User")
	@JsonProperty("Withdrawn By User")
	private String withdrawnBySystem;
	
	@SerializedName("Approved by CEIR Admin")
	@JsonProperty("Approved by CEIR Admin")
	private String approvedByAdmin;
	
	@SerializedName("Withdrawn By CEIR Admin")
	@JsonProperty("Withdrawn By CEIR Admin")
	private String withdrawnByAdmin;

	@SerializedName("New")
	@JsonProperty("New")
	private String newState;
	
	@SerializedName("Processing")
	@JsonProperty("Processing")
	private String processing;
	
	@SerializedName("Rejected By System")
	@JsonProperty("Rejected By System")
	private String rejectedBySystem;
	
	@SerializedName("Pending Approval From CEIR Admin")
	@JsonProperty("Pending Approval From CEIR Admin")
	private String pendingByAdmin;
	
	@SerializedName("Rejected by CEIR Admin")
	@JsonProperty("Rejected by CEIR Admin")
	private String rejectedByAdmin;

	
		public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWithdrawnBySystem() {
		return withdrawnBySystem;
	}

	public void setWithdrawnBySystem(String withdrawnBySystem) {
		this.withdrawnBySystem = withdrawnBySystem;
	}

	public String getApprovedByAdmin() {
		return approvedByAdmin;
	}

	public void setApprovedByAdmin(String approvedByAdmin) {
		this.approvedByAdmin = approvedByAdmin;
	}

	public String getWithdrawnByAdmin() {
		return withdrawnByAdmin;
	}

	public void setWithdrawnByAdmin(String withdrawnByAdmin) {
		this.withdrawnByAdmin = withdrawnByAdmin;
	}

	public String getNewState() {
		return newState;
	}

	public void setNewState(String newState) {
		this.newState = newState;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getRejectedBySystem() {
		return rejectedBySystem;
	}

	public void setRejectedBySystem(String rejectedBySystem) {
		this.rejectedBySystem = rejectedBySystem;
	}

	public String getPendingByAdmin() {
		return pendingByAdmin;
	}

	public void setPendingByAdmin(String pendingByAdmin) {
		this.pendingByAdmin = pendingByAdmin;
	}

	public String getRejectedByAdmin() {
		return rejectedByAdmin;
	}

	public void setRejectedByAdmin(String rejectedByAdmin) {
		this.rejectedByAdmin = rejectedByAdmin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StockModelRowData [date=");
		builder.append(date);
		builder.append(", withdrawnBySystem=");
		builder.append(withdrawnBySystem);
		builder.append(", approvedByAdmin=");
		builder.append(approvedByAdmin);
		builder.append(", withdrawnByAdmin=");
		builder.append(withdrawnByAdmin);
		builder.append(", newState=");
		builder.append(newState);
		builder.append(", processing=");
		builder.append(processing);
		builder.append(", rejectedBySystem=");
		builder.append(rejectedBySystem);
		builder.append(", pendingByAdmin=");
		builder.append(pendingByAdmin);
		builder.append(", rejectedByAdmin=");
		builder.append(rejectedByAdmin);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
