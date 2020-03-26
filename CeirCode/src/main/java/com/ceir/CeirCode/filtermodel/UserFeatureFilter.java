package com.ceir.CeirCode.filtermodel;

public class UserFeatureFilter {
	public String  startDate;
	public String   endDate;
	private Integer userType;
	private Integer feature;
	private String searchString;
	private String period;
	
	
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
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getFeature() {
		return feature;
	}
	public void setFeature(Integer feature) {
		this.feature = feature;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	

}
