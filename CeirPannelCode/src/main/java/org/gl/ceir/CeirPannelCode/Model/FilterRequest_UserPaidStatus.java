package org.gl.ceir.CeirPannelCode.Model;

public class FilterRequest_UserPaidStatus {
	
	private Integer deviceIdType,deviceType,taxPaidStatus,consignmentStatus,action,featureId,roleTypeUserId,userId,userTypeId;
	private String createdOn,modifiedOn,nid,remarks,userType,txnId;
	  private Long imei1;
	public Integer getDeviceIdType() {
		return deviceIdType;
	}
	public void setDeviceIdType(Integer deviceIdType) {
		this.deviceIdType = deviceIdType;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public Integer getTaxPaidStatus() {
		return taxPaidStatus;
	}
	public void setTaxPaidStatus(Integer taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}
	public Integer getConsignmentStatus() {
		return consignmentStatus;
	}
	public void setConsignmentStatus(Integer consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public Integer getRoleTypeUserId() {
		return roleTypeUserId;
	}
	public void setRoleTypeUserId(Integer roleTypeUserId) {
		this.roleTypeUserId = roleTypeUserId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public Long getImei1() {
		return imei1;
	}
	public void setImei1(Long imei1) {
		this.imei1 = imei1;
	}
	@Override
	public String toString() {
		return "FilterRequest_UserPaidStatus [deviceIdType=" + deviceIdType + ", deviceType=" + deviceType
				+ ", taxPaidStatus=" + taxPaidStatus + ", consignmentStatus=" + consignmentStatus + ", action=" + action
				+ ", featureId=" + featureId + ", roleTypeUserId=" + roleTypeUserId + ", userId=" + userId
				+ ", userTypeId=" + userTypeId + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", nid="
				+ nid + ", remarks=" + remarks + ", userType=" + userType + ", txnId=" + txnId + ", imei1=" + imei1
				+ "]";
	}
	  
	  
	
		  
}
