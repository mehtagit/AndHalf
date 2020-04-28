package com.ceir.CeirCode.filemodel;
import java.time.LocalDateTime;


public class RunningAlertFile {

	private String createdOn;
	private String alertId;
	private String description;
	private String status;
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	
	public String getAlertId() {
		return alertId;
	}
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RunningAlertFile [createdOn=");
		builder.append(createdOn);
		builder.append(", alertId=");
		builder.append(alertId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}