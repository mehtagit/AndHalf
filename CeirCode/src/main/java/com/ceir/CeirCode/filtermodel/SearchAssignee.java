package com.ceir.CeirCode.filtermodel;

public class SearchAssignee {

	private Integer type;
	private String field;
	private Integer userTypeId;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchAssignee [type=");
		builder.append(type);
		builder.append(", field=");
		builder.append(field);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append("]");
		return builder.toString();
	}
	
  	
		
	
}
