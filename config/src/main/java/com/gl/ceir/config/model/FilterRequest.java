package com.gl.ceir.config.model;

public class FilterRequest {

	public Integer userId;
	private String txnId;
	public String startDate;
	public String endDate;
	public Integer taxPaidStatus;
	private Integer consignmentStatus;
	private String roleType;
	private String requestType;
	private String userType;
	private Integer featureId;
	private Integer userTypeId;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getTaxPaidStatus() {
		return taxPaidStatus;
	}
	public void setTaxPaidStatus(Integer taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}

	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Integer getConsignmentStatus() {
		return consignmentStatus;
	}
	public void setConsignmentStatus(Integer consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	@Override
	public String toString() {
		return "FilterRequest [userId=" + userId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", taxPaidStatus=" + taxPaidStatus + ", consignmentStatus=" + consignmentStatus + ", roleType="
				+ roleType + ", requestType=" + requestType + ", userType=" + userType + ", featureId=" + featureId
				+ ", userTypeId=" + userTypeId + "]";
	}




}


