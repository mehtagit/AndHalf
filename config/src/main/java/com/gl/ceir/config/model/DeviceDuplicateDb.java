package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DeviceDuplicateDb {
	
	private Long imei;
	private Long msisdn;
	
	@JsonIgnore
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createdOn;

	@JsonIgnore
	@UpdateTimestamp
	private Date updatedOn;
	
	@Column(length = 20)
	private String failedRuleId;
	private String failedRuleName;
	private Integer imei_status;
	private Long imsi;
	private String mobileOperator;
	private Integer regulizedByUser;
	private Long deviceSnapShotImei;
	private Long mobileOperatorId;
	
	@Column(length = 50)
	private String createFilename;
	
	@Column(length = 50)
	private String updateFilename;
	
	private Integer recordType;
	
	@Column(length = 100)
	private String system_type;
	
	private String tac;
	private String period;
	private String action;
	
	public DeviceDuplicateDb() {
		// TODO Auto-generated constructor stub
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

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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

	public Integer getImei_status() {
		return imei_status;
	}

	public void setImei_status(Integer imei_status) {
		this.imei_status = imei_status;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public String getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public Integer getRegulizedByUser() {
		return regulizedByUser;
	}

	public void setRegulizedByUser(Integer regulizedByUser) {
		this.regulizedByUser = regulizedByUser;
	}

	public Long getDeviceSnapShotImei() {
		return deviceSnapShotImei;
	}

	public void setDeviceSnapShotImei(Long deviceSnapShotImei) {
		this.deviceSnapShotImei = deviceSnapShotImei;
	}

	public Long getMobileOperatorId() {
		return mobileOperatorId;
	}

	public void setMobileOperatorId(Long mobileOperatorId) {
		this.mobileOperatorId = mobileOperatorId;
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

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public String getSystem_type() {
		return system_type;
	}

	public void setSystem_type(String system_type) {
		this.system_type = system_type;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceDuplicateDb [imei=");
		builder.append(imei);
		builder.append(", msisdn=");
		builder.append(msisdn);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", updatedOn=");
		builder.append(updatedOn);
		builder.append(", failedRuleId=");
		builder.append(failedRuleId);
		builder.append(", failedRuleName=");
		builder.append(failedRuleName);
		builder.append(", imei_status=");
		builder.append(imei_status);
		builder.append(", imsi=");
		builder.append(imsi);
		builder.append(", mobileOperator=");
		builder.append(mobileOperator);
		builder.append(", regulizedByUser=");
		builder.append(regulizedByUser);
		builder.append(", deviceSnapShotImei=");
		builder.append(deviceSnapShotImei);
		builder.append(", mobileOperatorId=");
		builder.append(mobileOperatorId);
		builder.append(", createFilename=");
		builder.append(createFilename);
		builder.append(", updateFilename=");
		builder.append(updateFilename);
		builder.append(", recordType=");
		builder.append(recordType);
		builder.append(", system_type=");
		builder.append(system_type);
		builder.append(", tac=");
		builder.append(tac);
		builder.append(", period=");
		builder.append(period);
		builder.append(", action=");
		builder.append(action);
		builder.append("]");
		return builder.toString();
	}

}
