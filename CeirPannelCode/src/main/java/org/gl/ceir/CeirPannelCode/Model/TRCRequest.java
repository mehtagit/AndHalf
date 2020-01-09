package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class TRCRequest {
	private String endDate,searchString,startDate,tac,txnId;
	private Integer status,userId,adminStatus;
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}
	@Override
	public String toString() {
		return "TRCRequest [endDate=" + endDate + ", searchString=" + searchString + ", startDate=" + startDate
				+ ", tac=" + tac + ", txnId=" + txnId + ", status=" + status + ", userId=" + userId + ", adminStatus="
				+ adminStatus + "]";
	}
	
	
	
	
		}

