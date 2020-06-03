package org.gl.ceir.CeirPannelCode.Model;

import java.util.List;

public class StockUploadModel {
	
	
	private String fileName;
	private int id;
	private String invoiceNumber;
	private String roleType;
	private String suplierName;
	private String txnId;
	private int quantity;
	private int stockStatus;
	private String supplierId;
	private int userId;
	private String userType,remarks,createdOn,modifiedOn,stateInterp;
	private StockUserModel user;
	@Override
	public String toString() {
		return "StockUploadModel [fileName=" + fileName + ", id=" + id + ", invoiceNumber=" + invoiceNumber
				+ ", roleType=" + roleType + ", suplierName=" + suplierName + ", txnId=" + txnId + ", quantity="
				+ quantity + ", stockStatus=" + stockStatus + ", supplierId=" + supplierId + ", userId=" + userId
				+ ", userType=" + userType + ", remarks=" + remarks + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + ", stateInterp=" + stateInterp + ", user=" + user + "]";
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getSuplierName() {
		return suplierName;
	}
	public void setSuplierName(String suplierName) {
		this.suplierName = suplierName;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getStateInterp() {
		return stateInterp;
	}
	public void setStateInterp(String stateInterp) {
		this.stateInterp = stateInterp;
	}
	public StockUserModel getUser() {
		return user;
	}
	public void setUser(StockUserModel user) {
		this.user = user;
	}
	

	
	
	
}
