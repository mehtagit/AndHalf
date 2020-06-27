package com.gl.ceir.config.model;

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

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime modifiedOn;
	
	private Long userId;
	private String userName;
	private Long userTypeId;
	private String userType;
	private Long featureId;
	private String featureName;
	private String subFeature;
	private String jSessionId;
	private String roleType;
	
	@Column(length = 20)
	private String txnId;
	
	public AuditTrail() {

	}
	
	public AuditTrail(long userId, String userName, Long userTypeId, String userType, long featureId, String featureName, 
			String subFeature, String jSessionId) {
		this.userId = userId;
		this.userName = userName;
		this.userTypeId = userTypeId;
		this.userType = userType;
		this.featureId = featureId;
		this.featureName = featureName;
		this.subFeature = subFeature;
		this.jSessionId = jSessionId;
	}
	public AuditTrail(long userId, String userName, Long userTypeId, String userType, long featureId, 
			String featureName, String subFeature, String jSessionId, String txnId) {
		this.userId = userId;
		this.userName = userName;
		this.userTypeId = userTypeId;
		this.userType = userType;
		this.featureId = featureId;
		this.featureName = featureName;
		this.subFeature = subFeature;
		this.jSessionId = jSessionId;
		this.txnId = txnId;
	}
	
	public AuditTrail(long userId, String userName, Long userTypeId, String userType, long featureId, 
			String featureName, String subFeature, String jSessionId, String txnId, String roleType) {
		this.userId = userId;
		this.userName = userName;
		this.userTypeId = userTypeId;
		this.userType = userType;
		this.featureId = featureId;
		this.featureName = featureName;
		this.subFeature = subFeature;
		this.jSessionId = jSessionId;
		this.txnId = txnId;
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
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("AuditTrail [id=");
		stringBuilder.append(id);
		stringBuilder.append(", createdOn=");
		stringBuilder.append(createdOn);
		stringBuilder.append(", modifiedOn=");
		stringBuilder.append(modifiedOn);
		stringBuilder.append(", userId=");
		stringBuilder.append(userId);
		stringBuilder.append(", userName=");
		stringBuilder.append(userName);
		stringBuilder.append(", userTypeId=");
		stringBuilder.append(userTypeId);
		stringBuilder.append(", userType=");
		stringBuilder.append(userType);
		stringBuilder.append(", featureId=");
		stringBuilder.append(featureId);
		stringBuilder.append(", featureName=");
		stringBuilder.append(featureName);
		stringBuilder.append(", subFeature=");
		stringBuilder.append(subFeature);
		stringBuilder.append(", jSessionId=");
		stringBuilder.append(jSessionId);
		stringBuilder.append(", roleType=");
		stringBuilder.append(roleType);
		stringBuilder.append(", txnId=");
		stringBuilder.append(txnId);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	
	
}
