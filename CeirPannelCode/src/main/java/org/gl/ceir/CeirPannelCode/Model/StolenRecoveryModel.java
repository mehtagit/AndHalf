package org.gl.ceir.CeirPannelCode.Model;



public class StolenRecoveryModel {
	
	private String blockingTimePeriod;
	private String blockingType;
	private String fileName;
	private int fileStatus;
	private int id;
	private int requestType;
	private String roleType;
	private String  txnId,remark;
	private int userId,operatorTypeId;
	private int sourceType,category;
	private Integer qty,deviceCaegory,blockCategory;
	private String categoryInterp,blockCategoryInterp;
	
	
	
	
	public Integer getBlockCategory() {
		return blockCategory;
	}
	public void setBlockCategory(Integer blockCategory) {
		this.blockCategory = blockCategory;
	}
	public String getBlockCategoryInterp() {
		return blockCategoryInterp;
	}
	public void setBlockCategoryInterp(String blockCategoryInterp) {
		this.blockCategoryInterp = blockCategoryInterp;
	}
	@Override
	public String toString() {
		return "StolenRecoveryModel [blockingTimePeriod=" + blockingTimePeriod + ", blockingType=" + blockingType
				+ ", fileName=" + fileName + ", fileStatus=" + fileStatus + ", id=" + id + ", requestType="
				+ requestType + ", roleType=" + roleType + ", txnId=" + txnId + ", remark=" + remark + ", userId="
	
				+ userId + ", operatorTypeId=" + operatorTypeId + ", sourceType=" + sourceType + ", category="
				+ category + ", qty=" + qty + ", deviceCaegory=" + deviceCaegory + ", blockCategory=" + blockCategory
				+ ", categoryInterp=" + categoryInterp + ", blockCategoryInterp=" + blockCategoryInterp + "]";
	}
	public int getOperatorTypeId() {
		return operatorTypeId;
	}
	public void setOperatorTypeId(int operatorTypeId) {
		this.operatorTypeId = operatorTypeId;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getDeviceCaegory() {
		return deviceCaegory;
	}
	public void setDeviceCaegory(Integer deviceCaegory) {
		this.deviceCaegory = deviceCaegory;
	}
	public String getCategoryInterp() {
		return categoryInterp;
	}
	public void setCategoryInterp(String categoryInterp) {
		this.categoryInterp = categoryInterp;
	}
	
	
	
	
}
