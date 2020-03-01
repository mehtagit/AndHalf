package com.gl.ceir.config.model.file;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class AuditTrailFileModel {
	
	@CsvBindByName(column = "Create On")
	@CsvBindByPosition(position = 0)
	private String createdOn;
	
	@CsvBindByName(column = "Txn Id")
	@CsvBindByPosition(position = 1)
	private String txnId;
	
	@CsvBindByName(column = "User Name")
	@CsvBindByPosition(position = 2)
	private String userName;
	
	@CsvBindByName(column = "Role Type")
	@CsvBindByPosition(position = 3)
	private String roleType;
	
	@CsvBindByName(column = "Feature")
	@CsvBindByPosition(position = 4)
	private String featureName;
	
	@CsvBindByName(column = "Sub Feature")
	@CsvBindByPosition(position = 5)
	private String subFeatureName;

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getSubFeatureName() {
		return subFeatureName;
	}

	public void setSubFeatureName(String subFeatureName) {
		this.subFeatureName = subFeatureName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuditTrailFileModel [createdOn=");
		builder.append(createdOn);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", roleType=");
		builder.append(roleType);
		builder.append(", featureName=");
		builder.append(featureName);
		builder.append(", subFeatureName=");
		builder.append(subFeatureName);
		builder.append("]");
		return builder.toString();
	}
	
	
}
