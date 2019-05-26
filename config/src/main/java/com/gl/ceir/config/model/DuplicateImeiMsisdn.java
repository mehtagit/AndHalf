package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class DuplicateImeiMsisdn {
	@EmbeddedId
	private ImeiMsisdnIdentity imeiMsisdnIdentity;

	private long imsi;
	private String fileName;
	@OneToOne
	private MobileOperator mobileOperator;
	private Date createdOn;
	private boolean regulizedByUser;
	@Enumerated(EnumType.STRING)
	private ImeiStatus imeiStatus;

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

	public long getImsi() {
		return imsi;
	}

	public void setImsi(long imsi) {
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

}
