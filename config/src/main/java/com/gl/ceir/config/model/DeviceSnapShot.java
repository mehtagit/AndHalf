package com.gl.ceir.config.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class DeviceSnapShot {
	@Id
	@GeneratedValue
	private long id;
	private long msisnd;
	private long imei;
	@OneToOne
	private MobileOperator mobileOperator;
	private ImeiStatus imeiStatus;
	private int lastpPolicyBreached;
	private Date lastpPolicyBreachedDate;
	@OneToMany
	private List<Rules> breachedRules;
	private Period period;
	private Date createdOn;
	private String remarks;
	private int duplicateCount;
	private boolean taxPaid;
	private boolean foreginRule;
	private boolean globalBlacklist;
	private boolean greyList;
	private boolean validImport;
	@OneToMany
	private List<DuplicateImeiMsisdn> duplicateImeiMsisdn;

	public DeviceSnapShot() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMsisnd() {
		return msisnd;
	}

	public void setMsisnd(long msisnd) {
		this.msisnd = msisnd;
	}

	public long getImei() {
		return imei;
	}

	public void setImei(long imei) {
		this.imei = imei;
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

	public List<Rules> getBreachedRules() {
		return breachedRules;
	}

	public void setBreachedRules(List<Rules> breachedRules) {
		this.breachedRules = breachedRules;
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

	public List<DuplicateImeiMsisdn> getDuplicateImeiMsisdn() {
		return duplicateImeiMsisdn;
	}

	public void setDuplicateImeiMsisdn(List<DuplicateImeiMsisdn> duplicateImeiMsisdn) {
		this.duplicateImeiMsisdn = duplicateImeiMsisdn;
	}

}
