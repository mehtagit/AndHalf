package org.gl.ceir.graph.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class BrandModelRowData {
	@SerializedName("Date")
	@JsonProperty("Date")
	private String date;
	
	@SerializedName("Brand Name")
	@JsonProperty("Brand Name")
	private String brandName;
	
	@SerializedName("Model Name")
	@JsonProperty("Model Name")
	private String modelNumber;
	
	@SerializedName("Consignment Status")
	@JsonProperty("Consignment Status")
	private String consignmentStatus;
	
	@SerializedName("Stock Status")
	@JsonProperty("Stock Status")
	private String stockStatus;
	
	@SerializedName("User Type")
	@JsonProperty("User Type")
	private String userType;
	
	@SerializedName("Grievance Status")
	@JsonProperty("Grievance Status")
	private String grievanceStatus;
	
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
	
	
	@SerializedName("Closed")
	@JsonProperty("Closed")
	private String closed;
	
	@SerializedName("Pending With Admin")
	@JsonProperty("Pending With Admin")
	private String pendigWithAdmin;
	
	@SerializedName("Pending With User")
	@JsonProperty("Pending With User")
	private String pendingWithUser;
	
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


	@SerializedName("Withdrawn By User")
	@JsonProperty("Withdrawn By User")
	private String withdrawnBySystem;
	
	@SerializedName("Approved by CEIR Admin")
	@JsonProperty("Approved by CEIR Admin")
	private String approvedByAdmin;
	
	@SerializedName("Withdrawn By CEIR Admin")
	@JsonProperty("Withdrawn By CEIR Admin")
	private String withdrawnByAdmin;
	
	@SerializedName("Count")
	@JsonProperty("Count")
	private String count;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getDate() {
	return date;
	}

	public void setDate(String date) {
	this.date = date;
	}

	public String getBrandName() {
	return brandName;
	}

	public void setBrandName(String brandName) {
	this.brandName = brandName;
	}

	public String getCount() {
	return count;
	}

	public void setCount(String count) {
	this.count = count;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getConsignmentStatus() {
		return consignmentStatus;
	}

	public void setConsignmentStatus(String consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getGrievanceStatus() {
		return grievanceStatus;
	}

	public void setGrievanceStatus(String grievanceStatus) {
		this.grievanceStatus = grievanceStatus;
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

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public String getPendigWithAdmin() {
		return pendigWithAdmin;
	}

	public void setPendigWithAdmin(String pendigWithAdmin) {
		this.pendigWithAdmin = pendigWithAdmin;
	}

	public String getPendingWithUser() {
		return pendingWithUser;
	}

	public void setPendingWithUser(String pendingWithUser) {
		this.pendingWithUser = pendingWithUser;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BrandModelRowData [date=");
		builder.append(date);
		builder.append(", brandName=");
		builder.append(brandName);
		builder.append(", modelNumber=");
		builder.append(modelNumber);
		builder.append(", consignmentStatus=");
		builder.append(consignmentStatus);
		builder.append(", stockStatus=");
		builder.append(stockStatus);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", grievanceStatus=");
		builder.append(grievanceStatus);
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
		builder.append(", closed=");
		builder.append(closed);
		builder.append(", pendigWithAdmin=");
		builder.append(pendigWithAdmin);
		builder.append(", pendingWithUser=");
		builder.append(pendingWithUser);
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
		builder.append(", withdrawnBySystem=");
		builder.append(withdrawnBySystem);
		builder.append(", approvedByAdmin=");
		builder.append(approvedByAdmin);
		builder.append(", withdrawnByAdmin=");
		builder.append(withdrawnByAdmin);
		builder.append(", count=");
		builder.append(count);
		builder.append(", additionalProperties=");
		builder.append(additionalProperties);
		builder.append("]");
		return builder.toString();
	}

	

}
