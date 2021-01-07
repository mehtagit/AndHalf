package com.ceir.CeirCode.filtermodel;

import com.ceir.CeirCode.model.AllRequest;

public class ReqHeaderFilter extends AllRequest{

	public String  startDate;
	public String   endDate;
	private String searchString;
	private String publicIp;
	private String username;
	public String getPublicIp() {
		return publicIp;
	}
	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqHeaderFilter [startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", publicIp=");
		builder.append(publicIp);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
		
	
	
}
