package com.ceir.CeirCode.model;

import java.time.LocalDateTime;

public class UserProfileFileModel {
    
	private String requestedOn;
	private String modifiedOn;
	private String displayName;
	private String type;
	private String userType;
	private String status;
	private String approvedBy;
	
	public String getRequestedOn() {
		return requestedOn;
	}
	public void setRequestedOn(String requestedOn) {
		this.requestedOn = requestedOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserProfileFileModel [requestedOn=");
		builder.append(requestedOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", type=");
		builder.append(type);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", status=");
		builder.append(status);
		builder.append(", approvedBy=");
		builder.append(approvedBy);
		builder.append("]");
		return builder.toString();
	}
}
