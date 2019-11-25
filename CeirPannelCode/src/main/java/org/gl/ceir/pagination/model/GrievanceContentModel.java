package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GrievanceContentModel {
	private String grievanceId;
	private Integer userId;
	private String userType;
	private Integer grievanceStatus;
	private String txnId;
	private Integer categoryId;
	private String fileName;
	private String createdOn;
	private String modifiedOn;
	private String remarks;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	

	public String getGrievanceId() {
		return grievanceId;
	}

	public void setGrievanceId(String grievanceId) {
		this.grievanceId = grievanceId;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public Integer getUserId() {
	return userId;
	}

	public void setUserId(Integer userId) {
	this.userId = userId;
	}

	public String getUserType() {
	return userType;
	}

	public void setUserType(String userType) {
	this.userType = userType;
	}

	public Integer getGrievanceStatus() {
	return grievanceStatus;
	}

	public void setGrievanceStatus(Integer grievanceStatus) {
	this.grievanceStatus = grievanceStatus;
	}

	public String getTxnId() {
	return txnId;
	}

	public void setTxnId(String txnId) {
	this.txnId = txnId;
	}

	public Integer getCategoryId() {
	return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
	this.categoryId = categoryId;
	}

	public String getFileName() {
	return fileName;
	}

	public void setFileName(String fileName) {
	this.fileName = fileName;
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

	public String getRemarks() {
	return remarks;
	}

	public void setRemarks(String remarks) {
	this.remarks = remarks;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	
	}

	@Override
	public String toString() {
		return "GrievanceContentModel [grievanceId=" + grievanceId + ", userId=" + userId + ", userType=" + userType
				+ ", grievanceStatus=" + grievanceStatus + ", txnId=" + txnId + ", categoryId=" + categoryId
				+ ", fileName=" + fileName + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", remarks="
				+ remarks + ", additionalProperties=" + additionalProperties + "]";
	}
	
	
}
