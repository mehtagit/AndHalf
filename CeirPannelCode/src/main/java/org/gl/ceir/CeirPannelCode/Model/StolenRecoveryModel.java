package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.web.multipart.MultipartFile;

public class StolenRecoveryModel {
	
	private String blockingTimePeriod;
	private String blockingType;
	private String fileName;
	private int fileStatus;
	private int id;
	private int requestType;
	private String roleType;
	private String  txnId;
	private int userId;
	private int sourceType;
	private Integer qty;
	public String getBlockingTimePeriod() {
		return blockingTimePeriod;
	}
	public void setBlockingTimePeriod(String blockingTimePeriod) {
		this.blockingTimePeriod = blockingTimePeriod;
	}
	public String getBlockingType() {
		return blockingType;
	}
	public void setBlockingType(String blockingType) {
		this.blockingType = blockingType;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSourceType() {
		return sourceType;
	}
	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "StolenRecoveryModel [blockingTimePeriod=" + blockingTimePeriod + ", blockingType=" + blockingType
				+ ", fileName=" + fileName + ", fileStatus=" + fileStatus + ", id=" + id + ", requestType="
				+ requestType + ", roleType=" + roleType + ", txnId=" + txnId + ", userId=" + userId + ", sourceType="
				+ sourceType + ", qty=" + qty + "]";
	}
	
}
