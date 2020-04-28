package com.ceir.CeirCode.filtermodel;

public class SlaFilter {
	public String  startDate;
	public String  endDate;
	private String searchString;
	private Integer userTypeId;
	private Integer featureId;
	private Integer usertype;
	private long userId;
	private Integer feature;
	
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
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
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
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public Integer getFeature() {
		return feature;
	}
	public void setFeature(Integer feature) {
		this.feature = feature;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SlaFilter [startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", usertype=");
		builder.append(usertype);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", feature=");
		builder.append(feature);
		builder.append("]");
		return builder.toString();
	}
	
	
}
