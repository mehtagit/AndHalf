package org.gl.ceir.pagination.model;

import java.util.HashMap;
import java.util.Map;

public class ConsignmentContent {

	private Integer id;
	private String supplierId;
	private String supplierName = null;
	private String consignmentNumber;
	private String taxPaidStatus;
	private String createdOn;
	private String modifiedOn;
	private Integer userId;
	private String txnId;
	private String fileName;
	private Integer consignmentStatus;
	private String organisationCountry;
	private String expectedDispatcheDate;
	@Override
	public String toString() {
		return "ConsignmentContent [id=" + id + ", supplierId=" + supplierId + ", supplierName=" + supplierName
				+ ", consignmentNumber=" + consignmentNumber + ", taxPaidStatus=" + taxPaidStatus + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", userId=" + userId + ", txnId=" + txnId + ", fileName="
				+ fileName + ", consignmentStatus=" + consignmentStatus + ", organisationCountry=" + organisationCountry
				+ ", expectedDispatcheDate=" + expectedDispatcheDate + ", expectedArrivaldate=" + expectedArrivaldate
				+ ", expectedArrivalPort=" + expectedArrivalPort + ", quantity=" + quantity + ", remarks=" + remarks
				+ ", additionalProperties=" + additionalProperties + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getConsignmentNumber() {
		return consignmentNumber;
	}
	public void setConsignmentNumber(String consignmentNumber) {
		this.consignmentNumber = consignmentNumber;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Integer getConsignmentStatus() {
		return consignmentStatus;
	}
	public void setConsignmentStatus(Integer consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}
	public String getOrganisationCountry() {
		return organisationCountry;
	}
	public void setOrganisationCountry(String organisationCountry) {
		this.organisationCountry = organisationCountry;
	}
	public String getExpectedDispatcheDate() {
		return expectedDispatcheDate;
	}
	public void setExpectedDispatcheDate(String expectedDispatcheDate) {
		this.expectedDispatcheDate = expectedDispatcheDate;
	}
	public String getExpectedArrivaldate() {
		return expectedArrivaldate;
	}
	public void setExpectedArrivaldate(String expectedArrivaldate) {
		this.expectedArrivaldate = expectedArrivaldate;
	}
	public String getExpectedArrivalPort() {
		return expectedArrivalPort;
	}
	public void setExpectedArrivalPort(String expectedArrivalPort) {
		this.expectedArrivalPort = expectedArrivalPort;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Object getRemarks() {
		return remarks;
	}
	public void setRemarks(Object remarks) {
		this.remarks = remarks;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	private String expectedArrivaldate;
	private String expectedArrivalPort;
	private Integer quantity;
	private Object remarks;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
}
