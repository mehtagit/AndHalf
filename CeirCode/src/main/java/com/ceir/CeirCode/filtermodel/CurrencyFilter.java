package com.ceir.CeirCode.filtermodel;

public class CurrencyFilter {

	public String  startDate;
	public String   endDate;
	private Integer currency;
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
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
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
		builder.append("CurrencyFilter [startDate=").append(startDate).append(", endDate=").append(endDate)
				.append(", currency=").append(currency).append(", searchString=").append(searchString).append("]");
		return builder.toString();
	}
}
