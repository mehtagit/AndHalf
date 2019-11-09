package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.web.multipart.MultipartFile;

public class ConsignmentModel {
	
	private  int id;
	private String supplierId;
	private String supplierName;
	private String taxPaidStatus;
	private String createdOn;
	private String modifiedOn;
	private int importerId;
	private String consignmentNumber;
	private String expectedArrivalDate;
	private String expectedArrivaldate;
	private String expectedDispatcheDate;
	private String expectedArrivalPort;
	private String organisationcountry;
	private String organisationCountry;
	private MultipartFile file;
	private String txnId;
	private String importerName;
	private String totalPrice;
	private String fileName;
	private String fileStatus;
	private String consignmentStatus;
	private String quantity;
	private Long userId  ;
	private String remarks;
	private String roleType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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
	public int getImporterId() {
		return importerId;
	}
	public void setImporterId(int importerId) {
		this.importerId = importerId;
	}
	public String getConsignmentNumber() {
		return consignmentNumber;
	}
	public void setConsignmentNumber(String consignmentNumber) {
		this.consignmentNumber = consignmentNumber;
	}
	public String getExpectedArrivalDate() {
		return expectedArrivalDate;
	}
	public void setExpectedArrivalDate(String expectedArrivalDate) {
		this.expectedArrivalDate = expectedArrivalDate;
	}
	public String getExpectedArrivaldate() {
		return expectedArrivaldate;
	}
	public void setExpectedArrivaldate(String expectedArrivaldate) {
		this.expectedArrivaldate = expectedArrivaldate;
	}
	public String getExpectedDispatcheDate() {
		return expectedDispatcheDate;
	}
	public void setExpectedDispatcheDate(String expectedDispatcheDate) {
		this.expectedDispatcheDate = expectedDispatcheDate;
	}
	public String getExpectedArrivalPort() {
		return expectedArrivalPort;
	}
	public void setExpectedArrivalPort(String expectedArrivalPort) {
		this.expectedArrivalPort = expectedArrivalPort;
	}
	public String getOrganisationcountry() {
		return organisationcountry;
	}
	public void setOrganisationcountry(String organisationcountry) {
		this.organisationcountry = organisationcountry;
	}
	public String getOrganisationCountry() {
		return organisationCountry;
	}
	public void setOrganisationCountry(String organisationCountry) {
		this.organisationCountry = organisationCountry;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getImporterName() {
		return importerName;
	}
	public void setImporterName(String importerName) {
		this.importerName = importerName;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}
	public String getConsignmentStatus() {
		return consignmentStatus;
	}
	public void setConsignmentStatus(String consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	@Override
	public String toString() {
		return "ConsignmentModel [id=" + id + ", supplierId=" + supplierId + ", supplierName=" + supplierName
				+ ", taxPaidStatus=" + taxPaidStatus + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", importerId=" + importerId + ", consignmentNumber=" + consignmentNumber + ", expectedArrivalDate="
				+ expectedArrivalDate + ", expectedArrivaldate=" + expectedArrivaldate + ", expectedDispatcheDate="
				+ expectedDispatcheDate + ", expectedArrivalPort=" + expectedArrivalPort + ", organisationcountry="
				+ organisationcountry + ", organisationCountry=" + organisationCountry + ", file=" + file + ", txnId="
				+ txnId + ", importerName=" + importerName + ", totalPrice=" + totalPrice + ", fileName=" + fileName
				+ ", fileStatus=" + fileStatus + ", consignmentStatus=" + consignmentStatus + ", quantity=" + quantity
				+ ", userId=" + userId + ", remarks=" + remarks + ", roleType=" + roleType + "]";
	}
	
	
	
	
}
