package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gl.ceir.config.model.constants.ImeiStatus;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class DuplicateImeiMsisdn {
	@EmbeddedId
	private ImeiMsisdnIdentity imeiMsisdnIdentity;

	private Long imsi;
	private String fileName;
	@ManyToOne
	private MobileOperator mobileOperator;
	private Date createdOn;
	private boolean regulizedByUser;

	private ImeiStatus imeiStatus;

	@ManyToOne
	@JoinColumn(name = "deviceSnapShotImei")
	private DeviceSnapShot deviceSnapShot;

	public ImeiMsisdnIdentity getImeiMsisdnIdentity() {
		return imeiMsisdnIdentity;
	}

	public void setImeiMsisdnIdentity(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		this.imeiMsisdnIdentity = imeiMsisdnIdentity;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MobileOperator getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(MobileOperator mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public boolean isRegulizedByUser() {
		return regulizedByUser;
	}

	public void setRegulizedByUser(boolean regulizedByUser) {
		this.regulizedByUser = regulizedByUser;
	}

	public ImeiStatus getImeiStatus() {
		return imeiStatus;
	}

	public void setImeiStatus(ImeiStatus imeiStatus) {
		this.imeiStatus = imeiStatus;
	}

	public DeviceSnapShot getDeviceSnapShot() {
		return deviceSnapShot;
	}

	public void setDeviceSnapShot(DeviceSnapShot deviceSnapShot) {
		this.deviceSnapShot = deviceSnapShot;
	}

}
