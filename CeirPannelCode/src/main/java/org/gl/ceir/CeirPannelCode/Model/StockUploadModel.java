package org.gl.ceir.CeirPannelCode.Model;

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
	private String userType;
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
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "StockUploadModel [fileName=" + fileName + ", id=" + id + ", invoiceNumber=" + invoiceNumber
				+ ", roleType=" + roleType + ", suplierName=" + suplierName + ", txnId=" + txnId + ", quantity="
				+ quantity + ", stockStatus=" + stockStatus + ", supplierId=" + supplierId + ", userId=" + userId
				+ ", userType=" + userType + "]";
	}
	

	

}
