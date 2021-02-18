package com.gl.ceir.config.model.file;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class StockCeirAdminFileModel {
		
	@CsvBindByName(column = "Created On")
	@CsvBindByPosition(position = 1)
	private String createdOn;
	
	@CsvBindByName(column = "Transaction ID")
	@CsvBindByPosition(position = 2)
	private String txnId;
	
	@CsvBindByName(column = "Name")
	@CsvBindByPosition(position = 3)
	private String name;
	
	@CsvBindByName(column = "Role Type")
	@CsvBindByPosition(position = 4)
	private String roleType;

	@CsvBindByName(column = "File Name")
	@CsvBindByPosition(position = 5)
	private String fileName;
	
	@CsvBindByName(column = "IMEI/MEID Quantity")
	@CsvBindByPosition(position = 7)
	private Integer quantity;

	@CsvBindByName(column = "Modified On")
	@CsvBindByPosition(position = 0)
	private String modifiedOn;
	
	@CsvBindByName(column = "Status")
	@CsvBindByPosition(position = 6)
	private String stockStatus;
	
	@CsvBindByName(column = "Supplier Name")
	@CsvBindByPosition(position = 9)
	private String supplierName;
	
	@CsvBindByName(column = "Device Quantity")
	@CsvBindByPosition(position = 8)
	private Integer deviceQuantity;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Integer getDeviceQuantity() {
		return deviceQuantity;
	}

	public void setDeviceQuantity(Integer deviceQuantity) {
		this.deviceQuantity = deviceQuantity;
	}

	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StockFileModel [createdOn=");
		builder.append(createdOn);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", stockStatus=");
		builder.append(stockStatus);
		builder.append(", supplierName=");
		builder.append(supplierName);
		builder.append(", deviceQuantity=");
		builder.append(deviceQuantity);
		builder.append("]");
		return builder.toString();
	}
}
