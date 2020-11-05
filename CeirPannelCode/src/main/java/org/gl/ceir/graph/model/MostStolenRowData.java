package org.gl.ceir.graph.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
@Component
public class MostStolenRowData {
	@SerializedName("Date")
	@JsonProperty("Date")
	private String date;
	
	@SerializedName("Stolen Count")
	@JsonProperty("Stolen Count")
	private int stolenCount;

	@SerializedName("Recoverd Count")
	@JsonProperty("Recoverd Count")
	private int recoverdCount;
	
	@SerializedName("Blocked Count")
	@JsonProperty("Blocked Count")
	private int blockedCount ;
	
	@SerializedName("Pending Count")
	@JsonProperty("Pending Count")
	private int pendingCount ;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStolenCount() {
		return stolenCount;
	}

	public void setStolenCount(int stolenCount) {
		this.stolenCount = stolenCount;
	}

	public int getRecoverdCount() {
		return recoverdCount;
	}

	public void setRecoverdCount(int recoverdCount) {
		this.recoverdCount = recoverdCount;
	}

	public int getBlockedCount() {
		return blockedCount;
	}

	public void setBlockedCount(int blockedCount) {
		this.blockedCount = blockedCount;
	}

	public int getPendingCount() {
		return pendingCount;
	}

	public void setPendingCount(int pendingCount) {
		this.pendingCount = pendingCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MostStolenRowData [date=");
		builder.append(date);
		builder.append(", stolenCount=");
		builder.append(stolenCount);
		builder.append(", recoverdCount=");
		builder.append(recoverdCount);
		builder.append(", blockedCount=");
		builder.append(blockedCount);
		builder.append(", pendingCount=");
		builder.append(pendingCount);
		builder.append("]");
		return builder.toString();
	}

}
