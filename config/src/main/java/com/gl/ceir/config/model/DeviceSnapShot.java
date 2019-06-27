package com.gl.ceir.config.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.model.constants.Period;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class DeviceSnapShot {

	@Id
	private Long imei;

	private Long msisdn;
	private Long imsi;

	// @ManyToOne
	private Long mobileOperatorId;

	private ImeiStatus imeiStatus;
	private int lastpPolicyBreached;
	private Date lastpPolicyBreachedDate;
	private String failedRuleId;
	private String failedRuleName;

	private Period period;
	private Date createdOn;
	private String remarks;
	private int duplicateCount;
	private boolean taxPaid;
	private boolean foreginRule;
	private boolean globalBlacklist;
	private boolean validImport;

	private String action;

	@OneToMany(mappedBy = "deviceSnapShot", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<DuplicateImeiMsisdn> duplicateImeiMsisdns;

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

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Long getMobileOperatorId() {
		return mobileOperatorId;
	}

	public void setMobileOperatorId(Long mobileOperatorId) {
		this.mobileOperatorId = mobileOperatorId;
	}

	public ImeiStatus getImeiStatus() {
		return imeiStatus;
	}

	public void setImeiStatus(ImeiStatus imeiStatus) {
		this.imeiStatus = imeiStatus;
	}

	public int getLastpPolicyBreached() {
		return lastpPolicyBreached;
	}

	public void setLastpPolicyBreached(int lastpPolicyBreached) {
		this.lastpPolicyBreached = lastpPolicyBreached;
	}

	public Date getLastpPolicyBreachedDate() {
		return lastpPolicyBreachedDate;
	}

	public void setLastpPolicyBreachedDate(Date lastpPolicyBreachedDate) {
		this.lastpPolicyBreachedDate = lastpPolicyBreachedDate;
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

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getDuplicateCount() {
		return duplicateCount;
	}

	public void setDuplicateCount(int duplicateCount) {
		this.duplicateCount = duplicateCount;
	}

	public boolean isTaxPaid() {
		return taxPaid;
	}

	public void setTaxPaid(boolean taxPaid) {
		this.taxPaid = taxPaid;
	}

	public boolean isForeginRule() {
		return foreginRule;
	}

	public void setForeginRule(boolean foreginRule) {
		this.foreginRule = foreginRule;
	}

	public boolean isGlobalBlacklist() {
		return globalBlacklist;
	}

	public void setGlobalBlacklist(boolean globalBlacklist) {
		this.globalBlacklist = globalBlacklist;
	}

	public boolean isValidImport() {
		return validImport;
	}

	public void setValidImport(boolean validImport) {
		this.validImport = validImport;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<DuplicateImeiMsisdn> getDuplicateImeiMsisdns() {
		return duplicateImeiMsisdns;
	}

	public void setDuplicateImeiMsisdns(List<DuplicateImeiMsisdn> duplicateImeiMsisdns) {
		this.duplicateImeiMsisdns = duplicateImeiMsisdns;
	}

}
