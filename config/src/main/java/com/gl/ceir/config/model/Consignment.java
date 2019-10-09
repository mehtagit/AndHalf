package com.gl.ceir.config.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Consignment implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String supplierId;

	@NotNull
	private String supplierName;

	private String consignmentNumber;
	private String taxPaidStatus;
	private Date createdOn;
	private Date modifiedOn;
	private Long importerId;
	@NotNull
	private String txnId;
	private String importerName;

	private String totalPrice;

	private String fileName;

	private String fileStatus;

	private String consignmentStatus;

	private String organisationCountry;
	private String expectedDispatcheDate;
	private String expectedArrivaldate;
	private String expectedArrivalPort;
	private int quantity;
	

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Long getImporterId() {
		return importerId;
	}

	public void setImporterId(Long importerId) {
		this.importerId = importerId;
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
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Consignment [id=" + id + ", supplierId=" + supplierId + ", supplierName=" + supplierName
				+ ", consignmentNumber=" + consignmentNumber + ", taxPaidStatus=" + taxPaidStatus + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", importerId=" + importerId + ", txnId=" + txnId
				+ ", importerName=" + importerName + ", totalPrice=" + totalPrice + ", fileName=" + fileName
				+ ", fileStatus=" + fileStatus + ", consignmentStatus=" + consignmentStatus + ", organisationCountry="
				+ organisationCountry + ", expectedDispatcheDate=" + expectedDispatcheDate + ", expectedArrivaldate="
				+ expectedArrivaldate + ", expectedArrivalPort=" + expectedArrivalPort + ", quantity=" + quantity + "]";
	}


	
	

}
