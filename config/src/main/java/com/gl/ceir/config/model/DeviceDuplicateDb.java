package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DeviceDuplicateDb {
	
	@EmbeddedId
	private ImeiMsisdnIdentity imeiMsisdnIdentity;
	
	@JsonIgnore
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@Column(nullable = false, updatable = false)
	private Date createdOn;

	
	private String mobileOperator;
	
	@Column(length = 30)
	private String period;  
	private String action; 
	private String failedRuleId; 
	private String failedRuleName; 
	private Long imsi;   
	
	@JsonIgnore
	@UpdateTimestamp
	private Date modifiedOn;    
	
	private Integer recordType; 
	
	@Column(length = 100)
	private String systemType;
	
	@Column(length = 50)
	private String createFilename; 
	
	@Column(length = 50)
	private String updateFilename; 
	
	private Date failedRuleDate; 
	
	@Column(length = 10)
	private String tac;
	
	public DeviceDuplicateDb() {
		// TODO Auto-generated constructor stub
	}

	public ImeiMsisdnIdentity getImeiMsisdnIdentity() {
		return imeiMsisdnIdentity;
	}

	public void setImeiMsisdnIdentity(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		this.imeiMsisdnIdentity = imeiMsisdnIdentity;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getCreateFilename() {
		return createFilename;
	}

	public void setCreateFilename(String createFilename) {
		this.createFilename = createFilename;
	}

	public String getUpdateFilename() {
		return updateFilename;
	}

	public void setUpdateFilename(String updateFilename) {
		this.updateFilename = updateFilename;
	}

	public Date getFailedRuleDate() {
		return failedRuleDate;
	}

	public void setFailedRuleDate(Date failedRuleDate) {
		this.failedRuleDate = failedRuleDate;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceDuplicateDb [imeiMsisdnIdentity=");
		builder.append(imeiMsisdnIdentity);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", mobileOperator=");
		builder.append(mobileOperator);
		builder.append(", period=");
		builder.append(period);
		builder.append(", action=");
		builder.append(action);
		builder.append(", failedRuleId=");
		builder.append(failedRuleId);
		builder.append(", failedRuleName=");
		builder.append(failedRuleName);
		builder.append(", imsi=");
		builder.append(imsi);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", recordType=");
		builder.append(recordType);
		builder.append(", systemType=");
		builder.append(systemType);
		builder.append(", createFilename=");
		builder.append(createFilename);
		builder.append(", updateFilename=");
		builder.append(updateFilename);
		builder.append(", failedRuleDate=");
		builder.append(failedRuleDate);
		builder.append(", tac=");
		builder.append(tac);
		builder.append("]");
		return builder.toString();
	}
}