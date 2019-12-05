package com.gl.ceir.config.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class StolenandRecoveryMgmt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	
	private String fileName;
	
	private Integer fileStatus;

	@NotNull	
	private String txnId;

	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createdOn;
	
	@UpdateTimestamp
	private Date modifiedOn;
	
	private Integer requestType;
	
	@Transient
	private String requestTypeInterp;
	
	private String roleType;
	
	private String blockingType;
	
	private String blockingTimePeriod;
	
	private Integer sourceType;
	
	@Transient
	private String sourceTypeInterp;
	
	@Transient
	private String stateInterp;
	
	@OneToOne(mappedBy = "sARm", cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.LAZY)
	SingleImeiDetails singleImeiDetails;  
	
	@Transient
	private Long imei;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(Integer fileStatus) {
		this.fileStatus = fileStatus;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getBlockingType() {
		return blockingType;
	}
	public void setBlockingType(String blockingType) {
		this.blockingType = blockingType;
	}
	public String getBlockingTimePeriod() {
		return blockingTimePeriod;
	}
	public void setBlockingTimePeriod(String blockingTimePeriod) {
		this.blockingTimePeriod = blockingTimePeriod;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public SingleImeiDetails getSingleImeiDetails() {
		return singleImeiDetails;
	}
	public void setSingleImeiDetails(SingleImeiDetails singleImeiDetails) {
		this.singleImeiDetails = singleImeiDetails;
	}
	public Long getImei() {
		return imei;
	}
	public void setImei(Long imei) {
		this.imei = imei;
	}
	
	public String getRequestTypeInterp() {
		return requestTypeInterp;
	}
	public void setRequestTypeInterp(String requestTypeInterp) {
		this.requestTypeInterp = requestTypeInterp;
	}
	public String getSourceTypeInterp() {
		return sourceTypeInterp;
	}
	public void setSourceTypeInterp(String sourceTypeInterp) {
		this.sourceTypeInterp = sourceTypeInterp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStateInterp() {
		return stateInterp;
	}
	public void setStateInterp(String stateInterp) {
		this.stateInterp = stateInterp;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StolenandRecoveryMgmt [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", fileStatus=");
		builder.append(fileStatus);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", requestType=");
		builder.append(requestType);
		builder.append(", roleType=");
		builder.append(roleType);
		builder.append(", blockingType=");
		builder.append(blockingType);
		builder.append(", blockingTimePeriod=");
		builder.append(blockingTimePeriod);
		builder.append(", sourceType=");
		builder.append(sourceType);
		builder.append(", singleImeiDetails=");
		builder.append(singleImeiDetails);
		builder.append(", imei=");
		builder.append(imei);
		builder.append("]");
		return builder.toString();
	}

	
}
