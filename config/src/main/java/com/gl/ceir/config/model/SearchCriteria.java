package com.gl.ceir.config.model;

import com.gl.ceir.config.model.constants.SearchOperation;

public class SearchCriteria {

	private String key;
	private Object value;
	private SearchOperation searchOperation;
	
	public SearchCriteria(String key, Object value, SearchOperation searchOperation) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.value = value;
		this.searchOperation = searchOperation;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public SearchOperation getSearchOperation() {
		return searchOperation;
	}
	public void setSearchOperation(SearchOperation searchOperation) {
		this.searchOperation = searchOperation;
	}
	
}
