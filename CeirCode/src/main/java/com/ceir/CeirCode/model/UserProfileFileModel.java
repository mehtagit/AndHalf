package com.ceir.CeirCode.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class UserProfileFileModel {
    
	@CsvBindByName(column = "Requested On")
	@CsvBindByPosition(position = 0)
	private String requestedOn;
	@CsvBindByName(column = "Modified On")
	@CsvBindByPosition(position = 1)
	private String modifiedOn;
	
	@CsvBindByName(column = "User ID")
	@CsvBindByPosition(position = 2)
	private String userID;
	
	@CsvBindByName(column = "Status")
	@CsvBindByPosition(position = 3)
	private String status;
	
	@CsvBindByName(column = "Type")
	@CsvBindByPosition(position = 4)
	private String type;
	
	@CsvBindByName(column = "User Type")
	@CsvBindByPosition(position = 5)
	private String userType;
	
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		builder.append(", userID=");
		builder.append(userID);
		builder.append(", status=");
		builder.append(status);
		builder.append(", type=");
		builder.append(type);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", approvedBy=");
		builder.append(approvedBy);
		builder.append("]");
		return builder.toString();
	}

	@CsvBindByName(column = "Approved By")
	@CsvBindByPosition(position = 6)
	private String approvedBy;
	
	
}
