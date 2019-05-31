package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gl.ceir.config.model.constants.ImeiStatus;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class RegularizedImei {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long imei;
	private Long imsi;

	private String mobileOperator;
	private Long msisdn;
	private Date createdOn;
	private String fileName;

	@Enumerated(EnumType.STRING)
	private ImeiStatus imeiStatus;

	private String failedRuleId;
	private String failedRuleName;
	private String remarks;
	private Date modefiedOn;

	private String action;

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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public String getFailedRuleId() {
		return failedRuleId;
	}

	public void setFailedRuleId(String failedRuleId) {
		this.failedRuleId = failedRuleId;
	}

	public String getFailedRuleName() {
		return failedRuleName;
	}

	public void setFailedRuleName(String failedRuleName) {
		this.failedRuleName = failedRuleName;
	}

}
