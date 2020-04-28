package com.ceir.CeirCode.filtermodel;

public class RunningAlertFilter {

	
	public String  startDate;
	public String   endDate;
	private String alertId;
	private String searchString;
	private Integer status;
	private long userId;
	private Integer userTypeId;
	private Integer featureId;

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
	public String getAlertId() {
		return alertId;
	}
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RunningAlertFilter [startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", alertId=");
		builder.append(alertId);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", status=");
		builder.append(status);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append("]");
		return builder.toString();
	}

	
	

}
