package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class RegularizeDeviceHistoryDb implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	private LocalDateTime createdOn;

	@UpdateTimestamp
	private LocalDateTime modifiedOn;
	private String nid;
	private int deviceStatus;
	private String firstImei;
	private String secondImei;
	private String thirdImei;
	private String fourthImei;
	private int taxPaidStatus;
	private Integer deviceType;
	private Integer deviceIdType;
	private String multiSimStatus;
	private String country;
	private String deviceSerialNumber;

	private String txnId;
	
	public RegularizeDeviceHistoryDb() {
		// TODO Auto-generated constructor stub
	}
	
	public RegularizeDeviceHistoryDb(String nid, int deviceStatus, String firstImei, String secondImei, String thirdimei, 
			String fourthImei, int taxPaidStatus, Integer deviceType, Integer deviceIdType, String multiSimStatus,
			String country, String deviceSerialNumber) {
		this.nid = nid;
		this.deviceStatus = deviceStatus;
		this.firstImei = firstImei;
		this.secondImei = secondImei;
		this.thirdImei = thirdimei;
		this.fourthImei = fourthImei;
		this.taxPaidStatus = taxPaidStatus;
		this.deviceIdType = deviceType;
		this.multiSimStatus = multiSimStatus;
		this.country = country;
		this.deviceSerialNumber = deviceSerialNumber;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public int getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(Integer status) {
		this.deviceStatus = status;
	}	

	
	public String getFirstImei() {
		return firstImei;
	}

	public void setFirstImei(String firstImei) {
		this.firstImei = firstImei;
	}

	public String getSecondImei() {
		return secondImei;
	}

	public void setSecondImei(String secondImei) {
		this.secondImei = secondImei;
	}

	public String getThirdImei() {
		return thirdImei;
	}

	public void setThirdImei(String thirdImei) {
		this.thirdImei = thirdImei;
	}

	public String getFourthImei() {
		return fourthImei;
	}

	public void setFourthImei(String fourthImei) {
		this.fourthImei = fourthImei;
	}

	public void setDeviceStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public int getTaxPaidStatus() {
		return taxPaidStatus;
	}

	public void setTaxPaidStatus(int taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getDeviceIdType() {
		return deviceIdType;
	}

	public void setDeviceIdType(Integer deviceIdType) {
		this.deviceIdType = deviceIdType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMultiSimStatus() {
		return multiSimStatus;
	}

	public void setMultiSimStatus(String multiSimStatus) {
		this.multiSimStatus = multiSimStatus;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserCustomHistoryDb [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", nid=");
		builder.append(nid);
		builder.append(", deviceStatus=");
		builder.append(deviceStatus);
		builder.append(", firstImei=");
		builder.append(firstImei);
		builder.append(", secondImei=");
		builder.append(secondImei);
		builder.append(", thirdImei=");
		builder.append(thirdImei);
		builder.append(", fourthImei=");
		builder.append(fourthImei);
		builder.append(", taxPaidStatus=");
		builder.append(taxPaidStatus);
		builder.append(", deviceType=");
		builder.append(deviceType);
		builder.append(", deviceIdType=");
		builder.append(deviceIdType);
		builder.append(", multiSimStatus=");
		builder.append(multiSimStatus);
		builder.append(", country=");
		builder.append(country);
		builder.append(", deviceSerialNumber=");
		builder.append(deviceSerialNumber);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append("]");
		return builder.toString();
	}

}
