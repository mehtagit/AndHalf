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
	private Integer featureId;
	private long userId;
	private Integer userTypeId;
	private String searchString;
	
	
  
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
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	 
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer usertypeId) {
		this.userTypeId = usertypeId;
	}
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilterRequest [startDate=").append(startDate).append(", endDate=").append(endDate)
				.append(", status=").append(status).append(", asType=").append(asType).append(", userRoleTypeId=")
				.append(userRoleTypeId).append(", viewAllUserStatus=").append(viewAllUserStatus).append(", featureId=")
				.append(featureId).append(", userId=").append(userId).append(", userTypeId=").append(userTypeId)
				.append(", searchString=").append(searchString).append("]");
		return builder.toString();
	}

}