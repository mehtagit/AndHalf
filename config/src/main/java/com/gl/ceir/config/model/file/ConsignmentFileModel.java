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
	@CsvBindByName(column = "Quantity")
	@CsvBindByPosition(position = 5)
	private int quantity;
	@CsvBindByName(column = "Create On")
	@CsvBindByPosition(position = 6)
	private String createdOn;
	@CsvBindByName(column = "Modified On")
	@CsvBindByPosition(position = 7)
	private String modifiedOn;

	public ConsignmentFileModel() {
		// TODO Auto-generated constructor stub
	}

	public ConsignmentFileModel(String consignmentStatus, String txnId, String supplierName, 
			String taxPaidStatus, String fileName, String createdOn, String modifiedOn, int quantity) {
		this.consignmentStatus = consignmentStatus;
		this.txnId = txnId;
		this.supplierName = supplierName;
		this.taxPaidStatus = taxPaidStatus;
		this.fileName = fileName;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder()
				.append("ConsignmentFileModel [")
				.append("consignmentStatus=")
				.append(consignmentStatus)
				.append(", txnId=")
				.append(txnId)
				.append(", supplierName=")
				.append(supplierName)
				.append(", taxPaidStatus=")
				.append(taxPaidStatus)
				.append(", fileName=")
				.append(fileName)
				.append(", createdOn=")
				.append(createdOn)
				.append(", modifiedOn=")
				.append(modifiedOn)
				.append("]");

		return builder.toString();
	}

}
