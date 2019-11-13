package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class ConsignmentMgmt implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String supplierId;

	@NotNull
	private String supplierName;

	@Column(length = 15)
	private String consignmentNumber;

	@Column(length = 10)
	private String taxPaidStatus;


	@CreationTimestamp
	private LocalDateTime createdOn;


	@UpdateTimestamp
	private LocalDateTime modifiedOn;


	private Integer userId;

	@NotNull
	@Column(length = 20)
	private String txnId;

	private String fileName;

	@Column(length = 3)
	private int consignmentStatus;

	private String organisationCountry;

	@Column(length = 25)
	private String expectedDispatcheDate;

	@Column(length = 25)
	private String expectedArrivaldate;


	@Column(length = 10)
	private String expectedArrivalPort;

	private int quantity;

	private String remarks;

	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="user", joinColumns = @JoinColumn(name="id", insertable = false, updatable = false),
	inverseJoinColumns = @JoinColumn(name="user_id"))*/
	@OneToOne
	@JoinColumn(name="local_user_id", updatable = false)
	private User user;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplierld() {
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

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSupplierId() {
		return supplierId;
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

	public int getConsignmentStatus() {
		return consignmentStatus;
	}

	public void setConsignmentStatus(int consignmentStatus) {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ConsignmentMgmt [id=" + id + ", supplierId=" + supplierId + ", supplierName=" + supplierName
				+ ", consignmentNumber=" + consignmentNumber + ", taxPaidStatus=" + taxPaidStatus + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", userId=" + userId + ", txnId=" + txnId + ", fileName="
				+ fileName + ", consignmentStatus=" + consignmentStatus + ", organisationCountry=" + organisationCountry
				+ ", expectedDispatcheDate=" + expectedDispatcheDate + ", expectedArrivaldate=" + expectedArrivaldate
				+ ", expectedArrivalPort=" + expectedArrivalPort + ", quantity=" + quantity + ", remarks=" + remarks
				+ ", user=" + user + "]";
	}






}
