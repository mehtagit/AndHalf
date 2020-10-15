package org.gl.ceir.graph.model;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class BrandModelRowData {
	@SerializedName("Date")
	@JsonProperty("Date")
	private String date;
	
	@SerializedName("Brand Name")
	@JsonProperty("Brand Name")
	private int brandName;
	
	@SerializedName("Model Name")
	@JsonProperty("Model Name")
	private int modelNumber;
	
	
	@SerializedName("Count")
	@JsonProperty("Count")
	private int count;


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getBrandName() {
		return brandName;
	}


	public void setBrandName(int brandName) {
		this.brandName = brandName;
	}


	public int getModelNumber() {
		return modelNumber;
	}


	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BrandModelRowData [date=");
		builder.append(date);
		builder.append(", brandName=");
		builder.append(brandName);
		builder.append(", modelNumber=");
		builder.append(modelNumber);
		builder.append(", count=");
		builder.append(count);
		builder.append(", getDate()=");
		builder.append(getDate());
		builder.append(", getBrandName()=");
		builder.append(getBrandName());
		builder.append(", getModelNumber()=");
		builder.append(getModelNumber());
		builder.append(", getCount()=");
		builder.append(getCount());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


}
