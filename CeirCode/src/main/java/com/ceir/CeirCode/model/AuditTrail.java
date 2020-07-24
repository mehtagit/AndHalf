package com.ceir.CeirCode.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class AuditTrail implements Serializable {

	private static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable =false)
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;

	
	@Column(nullable =false)
	@UpdateTimestamp
	private LocalDateTime modifiedOn;
	private Long userId;
	private String userName;
	private Long userTypeId;
	private String userType;
	private Long featureId;
	private String featureName;
	private String subFeature;
	private String jSessionId;
	private String  txnId;
	private String roleType;
	public AuditTrail() {

	}
	
	public AuditTrail(long userId, String userName, long userTypeId, String userType, long featureId, String featureName, String subFeature, String jSessionId,String txnId,String roleType) {
		this.userId = userId;
		this.userName = userName;
		this.userTypeId = userTypeId;
		this.userType = userType;
		this.featureId = featureId;
		this.featureName = featureName;
		this.subFeature = subFeature;
		this.jSessionId = jSessionId;
		this.txnId=txnId;
		this.roleType=roleType;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		AuditTrail.serialVersionUID = serialVersionUID;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Long getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getSubFeature() {
		return subFeature;
	}
	public void setSubFeature(String subFeature) {
		this.subFeature = subFeature;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getjSessionId() {
		return jSessionId;
	}
	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}
	
	
	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	

	@Override
	public String toString() {
		return "AuditTrail [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", userId=" + userId
				+ ", userName=" + userName + ", userTypeId=" + userTypeId + ", userType=" + userType + ", featureId="
				+ featureId + ", featureName=" + featureName + ", subFeature=" + subFeature + ", jSessionId="
				+ jSessionId + ", txnId=" + txnId + ", roleType=" + roleType + "]";
	}
	
}
