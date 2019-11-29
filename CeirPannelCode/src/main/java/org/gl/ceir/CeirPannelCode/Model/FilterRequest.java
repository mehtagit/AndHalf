package org.gl.ceir.CeirPannelCode.Model;

public class FilterRequest {
	public Integer userId;
	public String  startDate;
	public String   endDate;
	public Integer taxPaidStatus;
	private Integer consignmentStatus;
	private String roleType;
	private String requestType;
	private int featureId;
	private int userTypeId;
	private String userType;
	private String txnId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Integer getConsignmentStatus() {
		return consignmentStatus;
	}
	public void setConsignmentStatus(Integer consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public int getFeatureId() {
		return featureId;
	}
	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	@Override
	public String toString() {
		return "FilterRequest [userId=" + userId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", taxPaidStatus=" + taxPaidStatus + ", consignmentStatus=" + consignmentStatus + ", roleType="
				+ roleType + ", requestType=" + requestType + ", featureId=" + featureId + ", userTypeId=" + userTypeId
				+ ", userType=" + userType + ", txnId=" + txnId + "]";
	}
}