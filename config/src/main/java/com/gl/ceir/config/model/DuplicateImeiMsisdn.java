package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DuplicateImeiMsisdn {
	@Id
	private int id;
	private long imei;
	private long msisdn;
	private String fileName;
	@OneToOne
	private MobileOperator mobileOperator;
	private Date createdOn;
	private boolean regulized;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getImei() {
		return imei;
	}

	public void setImei(long imei) {
		this.imei = imei;
	}

	public long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(long msisdn) {
		this.msisdn = msisdn;
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

	public boolean isRegulized() {
		return regulized;
	}

	public void setRegulized(boolean regulized) {
		this.regulized = regulized;
	}

}
