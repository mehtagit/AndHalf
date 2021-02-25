package com.gl.ceir.config.model.file;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class BlockUnblockFileModel {
	
	
	@CsvBindByName(column = "Modified On")
	@CsvBindByPosition(position = 0)
	private String modifiedOn;
	
	@CsvBindByName(column = "Created On")
	@CsvBindByPosition(position = 1)
	private String createdOn;

	
	
	@CsvBindByName(column = "Transaction ID")
	@CsvBindByPosition(position = 2)
	private String txnId;
	
	@CsvBindByName(column = "Operator")
	@CsvBindByPosition(position = 3)
	private String source;
	
	
	@CsvBindByName(column = "Request Type")
	@CsvBindByPosition(position = 4)
	private String requestType;
	
	@CsvBindByName(column = "Mode")
	@CsvBindByPosition(position = 5)
	private String mode;
	
	@CsvBindByName(column = "Status")
	@CsvBindByPosition(position = 6)
	private String stolenStatus;
	
	@CsvBindByName(column = "IMEI/MEID Quantity")
	@CsvBindByPosition(position = 7)
	private Integer quantity;
	
	@CsvBindByName(column = "Device Quantity")
	@CsvBindByPosition(position = 8)
	private Integer deviceQuantity;
	
	@CsvBindByName(column = "Filename")
	@CsvBindByPosition(position = 9)
	private String fileName;
	

	
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlockUnblockFileModel [modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", source=");
		builder.append(source);
		builder.append(", requestType=");
		builder.append(requestType);
		builder.append(", mode=");
		builder.append(mode);
		builder.append(", stolenStatus=");
		builder.append(stolenStatus);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", deviceQuantity=");
		builder.append(deviceQuantity);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append("]");
		return builder.toString();
	}

	
	
}