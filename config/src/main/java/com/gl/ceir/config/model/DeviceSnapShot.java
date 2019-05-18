package com.gl.ceir.config.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class DeviceSnapShot {
	@EmbeddedId
	private ImeiMsisdnIdentity imeiMsisdnIdentity;
	private Long imsi;
	@OneToOne
	private MobileOperator mobileOperator;

	@Enumerated(EnumType.STRING)
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
	private boolean greyList;
	private boolean validImport;

	private String action;

	public DeviceSnapShot() {
	}

	public ImeiMsisdnIdentity getImeiMsisdnIdentity() {
		return imeiMsisdnIdentity;
	}

	public void setImeiMsisdnIdentity(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		this.imeiMsisdnIdentity = imeiMsisdnIdentity;
	}

	public MobileOperator getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(MobileOperator mobileOperator) {
		this.mobileOperator = mobileOperator;
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

	public boolean isGreyList() {
		return greyList;
	}

	public void setGreyList(boolean greyList) {
		this.greyList = greyList;
	}

	public boolean isValidImport() {
		return validImport;
	}

	public void setValidImport(boolean validImport) {
		this.validImport = validImport;
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
