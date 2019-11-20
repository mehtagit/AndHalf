package com.gl.ceir.config.model;

import java.time.LocalDateTime;

public class GrievanceFilterRequest {
	public Integer userId;
	public LocalDateTime startDate;
	public LocalDateTime endDate;
	public String txnId;
	public Long grievanceId;
	private int grievanceStatus;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTransactionId(String txnId) {
		this.txnId = txnId;
	}
	public Long getGrievanceId() {
		return grievanceId;
	}
	public void setGrievanceId(Long grievanceId) {
		this.grievanceId = grievanceId;
	}
	public int getGrievanceStatus() {
		return grievanceStatus;
	}
	public void setGrievanceStatus(int grievanceStatus) {
		this.grievanceStatus = grievanceStatus;
	}
}
