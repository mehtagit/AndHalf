package com.ceir.CeirCode.filtermodel;

public class UserMgmtFilter {

	public String  startDate;
	public String   endDate;
	private Integer usertypeId;
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
	public Integer getUsertypeId() {
		return usertypeId;
	}
	public void setUsertypeId(Integer usertypeId) {
		this.usertypeId = usertypeId;
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
		builder.append("UserMgmtFilter [startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", usertypeId=");
		builder.append(usertypeId);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append("]");
		return builder.toString();
	}

	
}
