package com.gl.ceir.config.model;

import java.time.LocalDateTime;

public class GrievanceFilterRequest {
	public Integer userId;
	public LocalDateTime startDate;
	public LocalDateTime endDate;
	public String txnId;
	public String grievanceId;
	private int grievanceStatus;
	
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
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
	public String getGrievanceId() {
		return grievanceId;
	}
	public void setGrievanceId(String grievanceId) {
		this.grievanceId = grievanceId;
	}
	public int getGrievanceStatus() {
		return grievanceStatus;
	}
	public void setGrievanceStatus(int grievanceStatus) {
		this.grievanceStatus = grievanceStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GrievanceFilterRequest [userId=");
		builder.append(userId);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", grievanceId=");
		builder.append(grievanceId);
		builder.append(", grievanceStatus=");
		builder.append(grievanceStatus);
		builder.append("]");
		return builder.toString();
	}
	
}
