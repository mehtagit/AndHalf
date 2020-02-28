package com.ceir.CeirCode.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ceir.CeirCode.model.constants.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Notification  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	private LocalDateTime modifiedOn;

	private String channelType;
	@Column(length = 1000)
	private String message;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    User userForNofication;
	
	private Long featureId;
	
	private String featureTxnId;
	
	private String featureName;
	
	private String subFeature;
	
	private Integer status;
	
	private String subject;
	
	private Integer retryCount=0;
	
	public Notification() {}
	
	public Notification(String channelType2, String message2, User userId, Long featureId2, String featureName2, 
			String subFeature2, String featureTxnId2,String subject ) {
		this.userForNofication = userId;
		this.channelType = channelType2;
		this.message = message2;
		this.featureId = featureId2;
		this.featureTxnId = featureTxnId2;
		this.featureName = featureName2;
		this.subFeature = subFeature2;
		status = NotificationStatus.INIT.getCode();
		this.subject = subject;
	}
	
	
	public Notification(Long id, LocalDateTime createdOn, LocalDateTime modifiedOn, String channelType, String message,
			Long userId, Long featureId, String featureTxnId, String featureName, String subFeature, Integer status,
			String subject, Integer retryCount) {
		super();
		this.id = id;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.channelType = channelType;
		this.message = message;
		this.featureId = featureId;
		this.featureTxnId = featureTxnId;
		this.featureName = featureName;
		this.subFeature = subFeature;
		status = NotificationStatus.INIT.getCode();
		this.subject = subject;
		this.retryCount = retryCount;
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
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getFeatureTxnId() {
		return featureTxnId;
	}
	public void setFeatureTxnId(String featureTxnId) {
		this.featureTxnId = featureTxnId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}


	public User getUserForNofication() {
		return userForNofication;
	}


	public void setUserForNofication(User userForNofication) {
		this.userForNofication = userForNofication;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", channelType="
				+ channelType + ", message=" + message + ", featureId=" + featureId + ", featureTxnId=" + featureTxnId
				+ ", featureName=" + featureName + ", subFeature=" + subFeature + ", status=" + status + ", subject="
				+ subject + ", retryCount=" + retryCount + "]";
	}
}