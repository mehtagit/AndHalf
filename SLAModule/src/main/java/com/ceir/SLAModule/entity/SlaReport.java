package com.ceir.SLAModule.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class SlaReport {


	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String feature;
	private String state;
	

	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userSlaReport;
	
	private String txnId;
	
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUserSlaReport() {
		return userSlaReport;
	}

	public void setUserSlaReport(User userSlaReport) {
		this.userSlaReport = userSlaReport;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
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

	
	@Override
	public String toString() {
		return "SlaReport [id=" + id + ", feature=" + feature + ", state=" + state + ", userSlaReport=" + userSlaReport
				+ ", txnId=" + txnId + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + "]";
	}
	
	public SlaReport() {

	}


	public SlaReport(String feature, String state, User user, String txnId) {
		super();
		this.feature = feature;
		this.state = state;
		userSlaReport=user;
		this.txnId = txnId;
	}


}
