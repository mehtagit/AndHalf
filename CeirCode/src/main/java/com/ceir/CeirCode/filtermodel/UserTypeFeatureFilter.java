package com.ceir.CeirCode.filtermodel;

public class UserTypeFeatureFilter {
	
	public String  startDate;
	public String   endDate;
	private Integer userType;
	private Integer feature;
	private String searchString;
	private Integer period;
	
	
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
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserTypeFeatureFilter [startDate=").append(startDate).append(", endDate=").append(endDate)
				.append(", userType=").append(userType).append(", feature=").append(feature).append(", searchString=")
				.append(searchString).append(", period=").append(period).append("]");
		return builder.toString();
	}
	
	

}
