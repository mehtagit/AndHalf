package com.gl.CEIR.CDRPicker.beans;

public class ConfigurationDetails {

	private String aggregationType;
	private int aggregationValue;
	
	public String getAggregationType() {
		return aggregationType;
	}
	public void setAggregationType(String aggregationType) {
		this.aggregationType = aggregationType;
	}
	public int getAggregationValue() {
		return aggregationValue;
	}
	public void setAggregationValue(int aggregationValue) {
		this.aggregationValue = aggregationValue;
	}
	@Override
	public String toString() {
		return "ConfigurationDetails [aggregationType=" + aggregationType + ", aggregationValue=" + aggregationValue
				+ "]";
	}






}
