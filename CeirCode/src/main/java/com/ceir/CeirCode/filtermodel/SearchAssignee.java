package com.ceir.CeirCode.filtermodel;

public class SearchAssignee {

	private Integer type;
	private String field;
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
	@Override
	public String toString() {
		return "SearchAssignee [type=" + type + ", field=" + field + "]";
	}
	
  	
		
	
}
