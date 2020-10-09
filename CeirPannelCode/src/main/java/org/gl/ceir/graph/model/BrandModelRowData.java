package org.gl.ceir.graph.model;

import java.util.HashMap;
import java.util.Map;

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
	private String brandName;
	
	@SerializedName("Model Name")
	@JsonProperty("Model Name")
	private String modelNumber;
	
	@SerializedName("Count")
	@JsonProperty("Count")
	private String count;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getDate() {
	return date;
	}

	public void setDate(String date) {
	this.date = date;
	}

	public String getBrandName() {
	return brandName;
	}

	public void setBrandName(String brandName) {
	this.brandName = brandName;
	}

	public String getCount() {
	return count;
	}

	public void setCount(String count) {
	this.count = count;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BrandModelRowData [date=");
		builder.append(date);
		builder.append(", brandName=");
		builder.append(brandName);
		builder.append(", count=");
		builder.append(count);
		builder.append(", additionalProperties=");
		builder.append(additionalProperties);
		builder.append("]");
		return builder.toString();
	}

}
