package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RegularizedImei {

	@Id
	@GeneratedValue
	private Long id;
	private Long imei;
	private Long imsi;

	@ManyToOne
	private MobileOperator mobileOperator;
	private Long msisdn;
	private Date createdOn;
	private String fileName;
	private ImeiStatus imeiStatus;
	@ManyToOne
	private Rules failedRule;
	private String remarks;
	private Date modefiedOn;

	public RegularizedImei() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImei() {
		return imei;
	}

	public void setImei(Long imei) {
		this.imei = imei;
	}

	public MobileOperator getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(MobileOperator mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public Long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(Long msisdn) {
		this.msisdn = msisdn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ImeiStatus getImeiStatus() {
		return imeiStatus;
	}

	public void setImeiStatus(ImeiStatus imeiStatus) {
		this.imeiStatus = imeiStatus;
	}

	public Rules getFailedRule() {
		return failedRule;
	}

	public void setFailedRule(Rules failedRule) {
		this.failedRule = failedRule;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getModefiedOn() {
		return modefiedOn;
	}

	public void setModefiedOn(Date modefiedOn) {
		this.modefiedOn = modefiedOn;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

}
