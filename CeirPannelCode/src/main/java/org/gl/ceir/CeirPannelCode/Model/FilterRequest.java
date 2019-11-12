package org.gl.ceir.CeirPannelCode.Model;

import java.time.LocalDateTime;

public class FilterRequest {
	public Integer userId;
	public String  startDate;
	public String   endDate;
	public String taxPaidStatus;
	private Integer consignmentStatus;
	private String roleType;
	private String requestType;
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
	public String getTaxPaidStatus() {
		return taxPaidStatus;
	}
	public void setTaxPaidStatus(String taxPaidStatus) {
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
	@Override
	public String toString() {
		return "FilterRequest [userId=" + userId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", taxPaidStatus=" + taxPaidStatus + ", consignmentStatus=" + consignmentStatus + ", roleType="
				+ roleType + ", requestType=" + requestType + "]";
	}
	
}
