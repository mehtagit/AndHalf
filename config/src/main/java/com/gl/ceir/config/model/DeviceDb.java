package com.gl.ceir.config.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DeviceDb  implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@CreationTimestamp
	private Date createdOn;

	@JsonIgnore
	@UpdateTimestamp
	private Date modifiedOn;

	private String deviceNumber;

	private String deviceType;
	private String txnId;
	private String deviceAction;
	private Long  importerUserId;
	private Long distributerUserId;
	private Long retailerUserId;
	private String importerTxnId;
	private String distributerTxnId;
	private String retalierTxnId;        
	private String	 deviceStatus;

	private String DeviceLaunchDate;
	private String multipleSimStatus;

	private String  deviceId;
	private String imeiEsnMeid;
	
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
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	
	public Long getImporterUserId() {
		return importerUserId;
	}

	public void setImporterUserId(Long importerUserId) {
		this.importerUserId = importerUserId;
	}
	public Long getDistributerUserId() {
		return distributerUserId;
	}
	public void setDistributerUserId(Long distributerUserId) {
		this.distributerUserId = distributerUserId;
	}
	public Long getRetailerUserId() {
		return retailerUserId;
	}
	public void setRetailerUserId(Long retailerUserId) {
		this.retailerUserId = retailerUserId;
	}
	public String getImporterTxnId() {
		return importerTxnId;
	}
	public void setImporterTxnId(String impoterTxnId) {
		this.importerTxnId = impoterTxnId;
	}
	public String getDistributerTxnId() {
		return distributerTxnId;
	}
	public void setDistributerTxnId(String distributerTxnId) {
		this.distributerTxnId = distributerTxnId;
	}
	public String getRetalierTxnId() {
		return retalierTxnId;
	}
	public void setRetalierTxnId(String retalierTxnId) {
		this.retalierTxnId = retalierTxnId;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getDeviceLaunchDate() {
		return DeviceLaunchDate;
	}
	public void setDeviceLaunchDate(String deviceLaunchDate) {
		DeviceLaunchDate = deviceLaunchDate;
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
	public String getImeiEsnMeid() {
		return imeiEsnMeid;
	}
	public void setImeiEsnMeid(String imeiEsnMeid) {
		this.imeiEsnMeid = imeiEsnMeid;
	}
	public String getDeviceAction() {
		return deviceAction;
	}
	public void setDeviceAction(String deviceAction) {
		this.deviceAction = deviceAction;
	}













}
