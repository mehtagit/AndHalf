package com.ceir.CeirCode.filtermodel;

public class CurrencyFilter {

	public String  startDate;
	public String   endDate;
	private Integer currency;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CurrencyFilter [startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", currency=");
		builder.append(currency);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
