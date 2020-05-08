package com.gl.ceir.config.model.file;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class ConsignmentFileModel {

	@CsvBindByName(column = "Txn Id")
	@CsvBindByPosition(position = 0)
	private String txnId;
	@CsvBindByName(column = "Status")
	@CsvBindByPosition(position = 1)
	private String consignmentStatus;
	@CsvBindByName(column = "Supplier Name")
	@CsvBindByPosition(position = 2)
	private String supplierName;
	@CsvBindByName(column = "Tax Status")
	@CsvBindByPosition(position = 3)
	private String taxPaidStatus;
	@CsvBindByName(column = "File Name")
	@CsvBindByPosition(position = 4)
	private String fileName;
	@CsvBindByName(column = "IMEI Quantity")
	@CsvBindByPosition(position = 5)
	private Integer quantity;
	@CsvBindByName(column = "Create On")
	@CsvBindByPosition(position = 6)
	private String createdOn;
	@CsvBindByName(column = "Modified On")
	@CsvBindByPosition(position = 7)
	private String modifiedOn;

	@CsvBindByName(column = "Device Quantity")
	@CsvBindByPosition(position = 8)
	private Integer deviceQuantity;
	public ConsignmentFileModel() {
		// TODO Auto-generated constructor stub
	}

	public ConsignmentFileModel(String consignmentStatus, String txnId, String supplierName, 
			String taxPaidStatus, String fileName, String createdOn, String modifiedOn, Integer quantity,Integer deviceQuantity) {
		this.consignmentStatus = consignmentStatus;
		this.txnId = txnId;
		this.supplierName = supplierName;
		this.taxPaidStatus = taxPaidStatus;
		this.fileName = fileName;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.quantity = quantity;
		this.deviceQuantity=deviceQuantity;
	}

	public String getConsignmentStatus() {
		return consignmentStatus;
	}

	public void setConsignmentStatus(String consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
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

	public String getTaxPaidStatus() {
		return taxPaidStatus;
	}

	public void setTaxPaidStatus(String taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConsignmentFileModel [txnId=");
		builder.append(txnId);
		builder.append(", consignmentStatus=");
		builder.append(consignmentStatus);
		builder.append(", supplierName=");
		builder.append(supplierName);
		builder.append(", taxPaidStatus=");
		builder.append(taxPaidStatus);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", deviceQuantity=");
		builder.append(deviceQuantity);
		builder.append("]");
		return builder.toString();
	}

	public Integer getDeviceQuantity() {
		return deviceQuantity;
	}

	public void setDeviceQuantity(Integer deviceQuantity) {
		this.deviceQuantity = deviceQuantity;
	}

}
