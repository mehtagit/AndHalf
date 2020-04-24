package com.gl.ceir.config.model.file;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class StockFileModel {
		
	@CsvBindByName(column = "Txn Id")
	@CsvBindByPosition(position = 1)
	private String txnId;

	@CsvBindByName(column = "Status")
	@CsvBindByPosition(position = 4)
	private String stockStatus;
	
	@CsvBindByName(column = "Supplier Name")
	@CsvBindByPosition(position = 5)
	private String supplierName;
	
	@CsvBindByName(column = "File Name")
	@CsvBindByPosition(position = 2)
	private String fileName;
	
	@CsvBindByName(column = "Created On")
	@CsvBindByPosition(position = 0)
	private String createdOn;

	@CsvBindByName(column = "Modified On")
	@CsvBindByPosition(position = 3)
	private String modifiedOn;

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
		StringBuilder builder = new StringBuilder()
		.append("ConsignmentFileModel [")
		.append("stockStatus=")
		.append(stockStatus)
		.append(", txnId=")
		.append(txnId)
		.append(", supplierName=")
		.append(supplierName)
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
