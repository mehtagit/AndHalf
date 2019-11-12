package com.gl.ceir.config.model;

import java.time.LocalDateTime;
import java.util.Date;

public class FilterRequest {

	public Integer userId;
	public LocalDateTime  startDate;
	public LocalDateTime   endDate;
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
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public String getTaxPaidStatus() {
		return taxPaidStatus;
	}
	public void setTaxPaidStatus(String taxPaidStatus) {
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
	@Override
	public String toString() {
		return "FilterRequest [userId=" + userId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", taxPaidStatus=" + taxPaidStatus + ", consignmentStatus=" + consignmentStatus + ", roleType="
				+ roleType + ", requestType=" + requestType + "]";
	}




}


