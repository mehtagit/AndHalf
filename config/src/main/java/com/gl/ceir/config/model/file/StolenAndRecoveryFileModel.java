package com.gl.ceir.config.model.file;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class StolenAndRecoveryFileModel {
	
	@CsvBindByName(column = "Created On")
	@CsvBindByPosition(position = 0)
	private String createdOn;

	@CsvBindByName(column = "Modified On")
	@CsvBindByPosition(position = 1)
	private String modifiedOn;
	
	@CsvBindByName(column = "Txn Id")
	@CsvBindByPosition(position = 2)
	private String txnId;
	
	@CsvBindByName(column = "Request Type")
	@CsvBindByPosition(position = 3)
	private String requestType;
	
	@CsvBindByName(column = "Mode")
	@CsvBindByPosition(position = 4)
	private String mode;
	
	@CsvBindByName(column = "Status")
	@CsvBindByPosition(position = 5)
	private String stolenStatus;
	
	@CsvBindByName(column = "Source")
	@CsvBindByPosition(position = 6)
	private String source;
	
	@CsvBindByName(column = "Filename")
	@CsvBindByPosition(position = 7)
	private String fileName;
	
	@CsvBindByName(column = "Device Quantity")
	@CsvBindByPosition(position = 8)
	private Integer deviceQuantity;
	
	public Integer getDeviceQuantity() {
		return deviceQuantity;
	}

	public void setDeviceQuantity(Integer deviceQuantity) {
		this.deviceQuantity = deviceQuantity;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStolenStatus() {
		return stolenStatus;
	}

	public void setStolenStatus(String stolenStatus) {
		this.stolenStatus = stolenStatus;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StolenAndRecoveryFileModel [createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", requestType=");
		builder.append(requestType);
		builder.append(", mode=");
		builder.append(mode);
		builder.append(", stolenStatus=");
		builder.append(stolenStatus);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append("]");
		return builder.toString();
	}

}
