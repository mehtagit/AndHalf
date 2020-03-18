package com.ceir.CeirCode.filtermodel;

public class PortAddressFilter {

	public String  startDate;
	public String   endDate;
	private Integer port;
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
	
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
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
		builder.append("PortAddressFilter [startDate=").append(startDate).append(", endDate=").append(endDate)
				.append(", port=").append(port).append(", searchString=").append(searchString).append("]");
		return builder.toString();
	} 
	
	
}
