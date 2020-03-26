package com.ceir.CeirCode.filtermodel;

public class UsertypeFilter {
	public String  startDate;
	public String   endDate;
	private Integer id;
	private String searchString;
	private Integer status;
	
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsertypeFilter [startDate=").append(startDate).append(", endDate=").append(endDate)
				.append(", id=").append(id).append(", searchString=").append(searchString).append(", status=")
				.append(status).append("]");
		return builder.toString();
	}
}
