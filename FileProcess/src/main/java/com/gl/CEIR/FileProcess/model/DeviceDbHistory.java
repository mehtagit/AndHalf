package com.gl.CEIR.FileProcess.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class DeviceDbHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@CreationTimestamp
	private Date createdOn;


	@UpdateTimestamp
	private Date modifiedOn;

	private String supplierId;

	private String fileName;
	private String filePath;
	private String deviceAction;
	private String invoiceNumber;    

	private int	operation;       
	private String roleType;     
	private String 	txnId;          
	private Long userId;         
	private String supplierName;

	private String taxPaidStatus;

	private String deviceStatus;

	private String deviceLaunchDate;

	private String multipleSimStatus;
	private String 	deviceId;
	private String 	deviceNumber; 
	private String 	deviceType;   
	private String	imeiEsnEeid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDeviceAction() {
		return deviceAction;
	}
	public void setDeviceAction(String deviceAction) {
		this.deviceAction = deviceAction;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getDeviceLaunchDate() {
		return deviceLaunchDate;
	}
	public void setDeviceLaunchDate(String deviceLaunchDate) {
		this.deviceLaunchDate = deviceLaunchDate;
	}
	public String getMultipleSimStatus() {
		return multipleSimStatus;
	}
	public void setMultipleSimStatus(String multipleSimStatus) {
		this.multipleSimStatus = multipleSimStatus;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getImeiEsnEeid() {
		return imeiEsnEeid;
	}
	public void setImeiEsnEeid(String imeiEsnEeid) {
		this.imeiEsnEeid = imeiEsnEeid;
	}




}
