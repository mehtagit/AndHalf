package org.gl.ceir.CeirPannelCode.Model;

public class StolenRecoveryModel {
	
	private String blockingTimePeriod;
	private String blockingType;
	private String fileName;
	private int fileStatus;
	private int id;
	private String requestType;
	private String roleType;
	private String  txnId;
	private int userId;
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
	@Override
	public String toString() {
		return "StolenRecovery [blockingTimePeriod=" + blockingTimePeriod + ", blockingType=" + blockingType
				+ ", fileName=" + fileName + ", fileStatus=" + fileStatus + ", id=" + id + ", requestType="
				+ requestType + ", roleType=" + roleType + ", txnId=" + txnId + ", userId=" + userId + "]";
	}
	
	
	

}
