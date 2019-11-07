package com.gl.ceir.config.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class StolenandRecoveryMgmt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	private String fileName;
	private int fileStatus;
    
	@NotNull	
	private String txnId;

	@JsonIgnore
	@CreationTimestamp
	private Date createdOn;

	@JsonIgnore
	@UpdateTimestamp
	private Date modifiedOn;
	private String requestType;
	private String roleType;
	private String blockingType;
	private String blockingTimePeriod;
	
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
	public int getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(int fileStatus) {
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
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
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
	@Override
	public String toString() {
		return "StolenandRecoveryMgmt [id=" + id + ", userId=" + userId + ", fileName=" + fileName + ", fileStatus="
				+ fileStatus + ", txnId=" + txnId + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", requestType=" + requestType + ", roleType=" + roleType + ", blockingType=" + blockingType
				+ ", blockingTimePeriod=" + blockingTimePeriod + "]";
	}
	
	
	
	
	
}
