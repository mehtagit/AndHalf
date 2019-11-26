package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class UserCustomDb implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	private LocalDateTime createdOn;

	@UpdateTimestamp
	private LocalDateTime modifiedOn;
	private int status;
	private Long firstImei;
	private Long secondImei;
	private Long thirdImei;
	private Long fourthImei;
	private int taxPaidStatus;
	private String deviceType;
	private String multiSimStatus;
	private String country;
	private String deviceSerialNumber;
	private String txnId;
	private String nid;

	@ManyToOne 
	@JoinColumn(name = "userId") 
	private CustomRegistrationDB customRegistrationDB;


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


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getFirstImei() {
		return firstImei;
	}

	public void setFirstImei(Long firstImei) {
		this.firstImei = firstImei;
	}

	public Long getSecondImei() {
		return secondImei;
	}

	public void setSecondImei(Long secondImei) {
		this.secondImei = secondImei;
	}

	public Long getThirdImei() {
		return thirdImei;
	}

	public void setThirdImei(Long thirdImei) {
		this.thirdImei = thirdImei;
	}

	public Long getFourthImei() {
		return fourthImei;
	}

	public void setFourthImei(Long fourthImei) {
		this.fourthImei = fourthImei;
	}

	public int getTaxPaidStatus() {
		return taxPaidStatus;
	}

	public void setTaxPaidStatus(int taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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

	public CustomRegistrationDB getCustomRegistrationDB() {
		return customRegistrationDB;
	}

	public void setCustomRegistrationDB(CustomRegistrationDB customRegistrationDB) {
		this.customRegistrationDB = customRegistrationDB;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	@Override
	public String toString() {
		return "UserCustomDb [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", status="
				+ status + ", firstImei=" + firstImei + ", secondImei=" + secondImei + ", thirdImei=" + thirdImei
				+ ", fourthImei=" + fourthImei + ", taxPaidStatus=" + taxPaidStatus + ", deviceType=" + deviceType
				+ ", multiSimStatus=" + multiSimStatus + ", country=" + country + ", deviceSerialNumber="
				+ deviceSerialNumber + ", txnId=" + txnId + ", nid=" + nid + ", customRegistrationDB="
				+ customRegistrationDB + "]";
	}






}
