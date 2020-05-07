package com.ceir.CeirCode.filtermodel;

import com.ceir.CeirCode.model.AllRequest;

public class SlaFilter extends AllRequest{
	public String  startDate;
	public String  endDate;
	private String searchString;
	private long feature;
	private long usertype;
	
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
	
	public long getFeature() {
		return feature;
	}
	public void setFeature(long feature) {
		this.feature = feature;
	}
	public long getUsertype() {
		return usertype;
	}
	public void setUsertype(long usertype) {
		this.usertype = usertype;
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
		builder.append(", feature=");
		builder.append(feature);
		builder.append(", usertype=");
		builder.append(usertype);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
}
