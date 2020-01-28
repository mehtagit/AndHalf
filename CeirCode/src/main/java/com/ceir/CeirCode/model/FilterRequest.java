package com.ceir.CeirCode.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
public class FilterRequest {
	
	
	public String  startDate;
	public String   endDate; 
	private Integer  status;
	private Integer asType;
	private long userRoleTypeId;
	private Integer viewAllUserStatus;
  
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
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getAsType() {
		return asType;
	}
	public void setAsType(Integer asType) {
		this.asType = asType;
	}
	public long getUserRoleTypeId() {
		return userRoleTypeId;
	}
	public void setUserRoleTypeId(long userRoleTypeId) {
		this.userRoleTypeId = userRoleTypeId;
	}
	public Integer getViewAllUserStatus() {
		return viewAllUserStatus;
	}
	public void setViewAllUserStatus(Integer viewAllUserStatus) {
		this.viewAllUserStatus = viewAllUserStatus;
	}
	@Override
	public String toString() {
		return "FilterRequest [startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", asType="
				+ asType + ", userRoleTypeId=" + userRoleTypeId + ", viewAllUserStatus=" + viewAllUserStatus + "]";
	}
}