package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@Component
public class ActiveIMEICountContentModel {
	
	@JsonProperty("Date")
	@SerializedName("Date")
	private String date;
	
	

	@JsonProperty("Total Valid IMEI")
	@SerializedName("Total Valid IMEI")
	private String totalValidIMEI;
	
	@JsonProperty("Total New Valid IMEI")
	@SerializedName("Total New Valid IMEI")
	private String totalNewValidIMEI;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotalValidIMEI() {
		return totalValidIMEI;
	}

	public void setTotalValidIMEI(String totalValidIMEI) {
		this.totalValidIMEI = totalValidIMEI;
	}

	public String getTotalNewValidIMEI() {
		return totalNewValidIMEI;
	}

	public void setTotalNewValidIMEI(String totalNewValidIMEI) {
		this.totalNewValidIMEI = totalNewValidIMEI;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActiveIMEICountContentModel [date=");
		builder.append(date);
		builder.append(", totalValidIMEI=");
		builder.append(totalValidIMEI);
		builder.append(", totalNewValidIMEI=");
		builder.append(totalNewValidIMEI);
		builder.append("]");
		return builder.toString();
	}
	
	
}
