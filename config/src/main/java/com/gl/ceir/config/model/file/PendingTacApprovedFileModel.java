package com.gl.ceir.config.model.file;

import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class PendingTacApprovedFileModel {
	
	@CsvBindByName(column = "Create On")
	@CsvBindByPosition(position = 0)
	private String createdOn;
	
	@CsvBindByName(column = "Txn Id")
	@CsvBindByPosition(position = 1)
	private String txnId;
	
	@CsvBindByName(column = "Tac")
	@CsvBindByPosition(position = 2)
	private String tac;
	
	@CsvBindByName(column = "Feature")
	@CsvBindByPosition(position = 3)
	private String featureName;
	
	@CsvBindByName(column = "User Type")
	@CsvBindByPosition(position = 2)
	private String userType;

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

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "PendingTacApprovedFileModel [createdOn=" + createdOn + ", txnId=" + txnId + ", tac=" + tac
				+ ", featureName=" + featureName + ", userType=" + userType + "]";
	}


}
