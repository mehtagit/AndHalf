package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class LawfulDeviceCountData {
	  @JsonProperty("Date")
	  @SerializedName("Date")
	  private String date;
	
      @JsonProperty("Total Stolen IMEI")
  	  @SerializedName("Total Stolen IMEI")
      private String stolenIMEI;
      
      @JsonProperty("Total Lost IMEI")
      @SerializedName("Total Lost IMEI")
      private String lostIMEI;
      
      @JsonProperty("Total Recoverd IMEI")
      @SerializedName("Total Recoverd IMEI")
      private String recoveredIMEI;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStolenIMEI() {
		return stolenIMEI;
	}

	public void setStolenIMEI(String stolenIMEI) {
		this.stolenIMEI = stolenIMEI;
	}

	public String getLostIMEI() {
		return lostIMEI;
	}

	public void setLostIMEI(String lostIMEI) {
		this.lostIMEI = lostIMEI;
	}

	public String getRecoveredIMEI() {
		return recoveredIMEI;
	}

	public void setRecoveredIMEI(String recoveredIMEI) {
		this.recoveredIMEI = recoveredIMEI;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LawfulDeviceCountData [date=");
		builder.append(date);
		builder.append(", stolenIMEI=");
		builder.append(stolenIMEI);
		builder.append(", lostIMEI=");
		builder.append(lostIMEI);
		builder.append(", recoveredIMEI=");
		builder.append(recoveredIMEI);
		builder.append("]");
		return builder.toString();
	}
      
      
}
