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

	private String manufatureDate;
	private String deviceNumber;
	private String deviceType;
	private String deviceAction;

	private Long  importerUserId;
	private Long distributerUserId;
	private Long retailerUserId;


	private String importerTxnId;
	private String distributerTxnId;
	private String retalierTxnId;        

	private Date importerDate;
	private Date distributerDate;
	private Date retailerDate;

	private int importerDeviceStatus;
	private int distributerDeviceStatus;
	private int retailerDeviceStatus;

	private int previousImporterDeviceStatus;
	private int previousDistributerDeviceStatus;
	private int previousRetailerDeviceStatus;

	private String deviceStatus;
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
	public String getManufatureDate() {
		return manufatureDate;
	}
	public void setManufatureDate(String manufatureDate) {
		this.manufatureDate = manufatureDate;
	}
	public Date getImporterDate() {
		return importerDate;
	}
	public void setImporterDate(Date importerDate) {
		this.importerDate = importerDate;
	}
	public Date getDistributerDate() {
		return distributerDate;
	}
	public void setDistributerDate(Date distributerDate) {
		this.distributerDate = distributerDate;
	}
	public Date getRetailerDate() {
		return retailerDate;
	}
	public void setRetailerDate(Date retailerDate) {
		this.retailerDate = retailerDate;
	}
	public int getImporterDeviceStatus() {
		return importerDeviceStatus;
	}
	public void setImporterDeviceStatus(int importerDeviceStatus) {
		this.importerDeviceStatus = importerDeviceStatus;
	}
	public int getDistributerDeviceStatus() {
		return distributerDeviceStatus;
	}
	public void setDistributerDeviceStatus(int distributerDeviceStatus) {
		this.distributerDeviceStatus = distributerDeviceStatus;
	}
	public int getRetailerDeviceStatus() {
		return retailerDeviceStatus;
	}
	public void setRetailerDeviceStatus(int retailerDeviceStatus) {
		this.retailerDeviceStatus = retailerDeviceStatus;
	}
	public int getPreviousImporterDeviceStatus() {
		return previousImporterDeviceStatus;
	}
	public void setPreviousImporterDeviceStatus(int previousImporterDeviceStatus) {
		this.previousImporterDeviceStatus = previousImporterDeviceStatus;
	}
	public int getPreviousDistributerDeviceStatus() {
		return previousDistributerDeviceStatus;
	}
	public void setPreviousDistributerDeviceStatus(int previousDistributerDeviceStatus) {
		this.previousDistributerDeviceStatus = previousDistributerDeviceStatus;
	}
	public int getPreviousRetailerDeviceStatus() {
		return previousRetailerDeviceStatus;
	}
	public void setPreviousRetailerDeviceStatus(int previousRetailerDeviceStatus) {
		this.previousRetailerDeviceStatus = previousRetailerDeviceStatus;
	}


}
