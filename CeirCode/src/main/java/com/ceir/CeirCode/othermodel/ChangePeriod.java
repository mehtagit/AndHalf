package com.ceir.CeirCode.othermodel;

public class ChangePeriod {


	private long id;
	private Integer period;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
		builder.append("ChangeUSerFeature [id=").append(id).append(", period=").append(period).append("]");
		return builder.toString();
	}
	
	
}
