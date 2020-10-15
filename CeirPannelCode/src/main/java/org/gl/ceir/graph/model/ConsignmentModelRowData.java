package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class ConsignmentModelRowData {
	
	@SerializedName("Date")
	@JsonProperty("Date")
	private String date;
	
	@SerializedName("Approved")
	@JsonProperty("Approved")
	private String approved;

	@SerializedName("Pending Approval From CEIR Admin")
	@JsonProperty("Pending Approval From CEIR Admin")
	private String pendingByAdmin;
	
	@SerializedName("Rejected by CEIR Admin")
	@JsonProperty("Rejected by CEIR Admin")
	private String rejectedByAdmin;
	
	@SerializedName("Rejected By System")
	@JsonProperty("Rejected By System")
	private String rejectedBySystem;
	
	@SerializedName("Withdrawn By Importer")
	@JsonProperty("Withdrawn By Importer")
	private String withdrawnByImporter;
	
	@SerializedName("Withdrawn By CEIR")
	@JsonProperty("Withdrawn By CEIR")
	private String withdrawnByCeir;
	
	@SerializedName("Processing")
	@JsonProperty("Processing")
	private String processing;
	
	@SerializedName("Pending Clearance From Customs")
	@JsonProperty("Pending Clearance From Customs")
	private String pendingClearenceFromCustom;
	
	

	@SerializedName("New")
	@JsonProperty("New")
	private String newState;
	
	@SerializedName("Rejected By Customs")
	@JsonProperty("Rejected By Customs")
	private String rejectedByCustoms;
	
	@SerializedName("Rejected by DRT")
	@JsonProperty("Rejected by DRT")
	private String rejectedByDrt;
	
	@SerializedName("Pending Clearance from DRT")
	@JsonProperty("Pending Clearance from DRT")
	private String pndingByDrt;

	
	



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getApproved() {
		return approved;
	}



	public void setApproved(String approved) {
		this.approved = approved;
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



	public String getRejectedBySystem() {
		return rejectedBySystem;
	}



	public void setRejectedBySystem(String rejectedBySystem) {
		this.rejectedBySystem = rejectedBySystem;
	}



	public String getWithdrawnByImporter() {
		return withdrawnByImporter;
	}



	public void setWithdrawnByImporter(String withdrawnByImporter) {
		this.withdrawnByImporter = withdrawnByImporter;
	}



	public String getWithdrawnByCeir() {
		return withdrawnByCeir;
	}



	public void setWithdrawnByCeir(String withdrawnByCeir) {
		this.withdrawnByCeir = withdrawnByCeir;
	}



	public String getProcessing() {
		return processing;
	}



	public void setProcessing(String processing) {
		this.processing = processing;
	}



	public String getPendingClearenceFromCustom() {
		return pendingClearenceFromCustom;
	}



	public void setPendingClearenceFromCustom(String pendingClearenceFromCustom) {
		this.pendingClearenceFromCustom = pendingClearenceFromCustom;
	}



	public String getNewState() {
		return newState;
	}



	public void setNewState(String newState) {
		this.newState = newState;
	}



	public String getRejectedByCustoms() {
		return rejectedByCustoms;
	}



	public void setRejectedByCustoms(String rejectedByCustoms) {
		this.rejectedByCustoms = rejectedByCustoms;
	}



	public String getRejectedByDrt() {
		return rejectedByDrt;
	}



	public void setRejectedByDrt(String rejectedByDrt) {
		this.rejectedByDrt = rejectedByDrt;
	}



	public String getPndingByDrt() {
		return pndingByDrt;
	}



	public void setPndingByDrt(String pndingByDrt) {
		this.pndingByDrt = pndingByDrt;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConsignmentModelRowData [date=");
		builder.append(date);
		builder.append(", approved=");
		builder.append(approved);
		builder.append(", pendingByAdmin=");
		builder.append(pendingByAdmin);
		builder.append(", rejectedByAdmin=");
		builder.append(rejectedByAdmin);
		builder.append(", rejectedBySystem=");
		builder.append(rejectedBySystem);
		builder.append(", withdrawnByImporter=");
		builder.append(withdrawnByImporter);
		builder.append(", withdrawnByCeir=");
		builder.append(withdrawnByCeir);
		builder.append(", processing=");
		builder.append(processing);
		builder.append(", pendingClearenceFromCustom=");
		builder.append(pendingClearenceFromCustom);
		builder.append(", newState=");
		builder.append(newState);
		builder.append(", rejectedByCustoms=");
		builder.append(rejectedByCustoms);
		builder.append(", rejectedByDrt=");
		builder.append(rejectedByDrt);
		builder.append(", pndingByDrt=");
		builder.append(pndingByDrt);
		builder.append("]");
		return builder.toString();
	}



	
	
}
