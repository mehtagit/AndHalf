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
	
	public AuditTrail() {

	}
	
	public AuditTrail(long userId, String userName, long userTypeId, String userType, long featureId, String featureName, String subFeature, String jSessionId,String txnId) {
		this.userId = userId;
		this.userName = userName;
		this.userTypeId = userTypeId;
		this.userType = userType;
		this.featureId = featureId;
		this.featureName = featureName;
		this.subFeature = subFeature;
		this.jSessionId = jSessionId;
		this.txnId=txnId;
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

	public AuditTrail(Long userId, String userName, Long userTypeId, String userType, Long featureId,
			String featureName, String subFeature, String jSessionId,String txnId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userTypeId = userTypeId;
		this.userType = userType;
		this.featureId = featureId;
		this.featureName = featureName;
		this.subFeature = subFeature;
		this.jSessionId = jSessionId;
		this.txnId=txnId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuditTrail [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", featureName=");
		builder.append(featureName);
		builder.append(", subFeature=");
		builder.append(subFeature);
		builder.append(", jSessionId=");
		builder.append(jSessionId);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append("]");
		return builder.toString();
	}
	
}
