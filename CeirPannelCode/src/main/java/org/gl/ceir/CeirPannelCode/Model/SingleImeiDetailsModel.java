package org.gl.ceir.CeirPannelCode.Model;

public class SingleImeiDetailsModel {
	private String categoryInterp,createdOn,deviceIdTypeInterp,deviceTypeInterp,deviceSerialNumber,modifiedOn,multipleSimStatusInterp,remark,txnId,userType,blockingTimePeriod,blockingType;
	private Integer category,deviceIdType,deviceType,multipleSimStatus,processState,requestType,sourceType,operatorTypeId;
	private long userId,id;
	private String firstImei,fourthImei,secondImei,thirdImei;
	public String getCategoryInterp() {
		return categoryInterp;
	}
	public void setCategoryInterp(String categoryInterp) {
		this.categoryInterp = categoryInterp;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getDeviceIdTypeInterp() {
		return deviceIdTypeInterp;
	}
	public void setDeviceIdTypeInterp(String deviceIdTypeInterp) {
		this.deviceIdTypeInterp = deviceIdTypeInterp;
	}
	public String getDeviceTypeInterp() {
		return deviceTypeInterp;
	}
	public void setDeviceTypeInterp(String deviceTypeInterp) {
		this.deviceTypeInterp = deviceTypeInterp;
	}
	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}
	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getMultipleSimStatusInterp() {
		return multipleSimStatusInterp;
	}
	public void setMultipleSimStatusInterp(String multipleSimStatusInterp) {
		this.multipleSimStatusInterp = multipleSimStatusInterp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
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
	public Integer getMultipleSimStatus() {
		return multipleSimStatus;
	}
	public void setMultipleSimStatus(Integer multipleSimStatus) {
		this.multipleSimStatus = multipleSimStatus;
	}
	public Integer getProcessState() {
		return processState;
	}
	public void setProcessState(Integer processState) {
		this.processState = processState;
	}
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getOperatorTypeId() {
		return operatorTypeId;
	}
	public void setOperatorTypeId(Integer operatorTypeId) {
		this.operatorTypeId = operatorTypeId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstImei() {
		return firstImei;
	}
	public void setFirstImei(String firstImei) {
		this.firstImei = firstImei;
	}
	public String getFourthImei() {
		return fourthImei;
	}
	public void setFourthImei(String fourthImei) {
		this.fourthImei = fourthImei;
	}
	public String getSecondImei() {
		return secondImei;
	}
	public void setSecondImei(String secondImei) {
		this.secondImei = secondImei;
	}
	public String getThirdImei() {
		return thirdImei;
	}
	public void setThirdImei(String thirdImei) {
		this.thirdImei = thirdImei;
	}
	@Override
	public String toString() {
		return "SingleImeiDetailsModel [categoryInterp=" + categoryInterp + ", createdOn=" + createdOn
				+ ", deviceIdTypeInterp=" + deviceIdTypeInterp + ", deviceTypeInterp=" + deviceTypeInterp
				+ ", deviceSerialNumber=" + deviceSerialNumber + ", modifiedOn=" + modifiedOn
				+ ", multipleSimStatusInterp=" + multipleSimStatusInterp + ", remark=" + remark + ", txnId=" + txnId
				+ ", userType=" + userType + ", blockingTimePeriod=" + blockingTimePeriod + ", blockingType="
				+ blockingType + ", category=" + category + ", deviceIdType=" + deviceIdType + ", deviceType="
				+ deviceType + ", multipleSimStatus=" + multipleSimStatus + ", processState=" + processState
				+ ", requestType=" + requestType + ", sourceType=" + sourceType + ", operatorTypeId=" + operatorTypeId
				+ ", userId=" + userId + ", id=" + id + ", firstImei=" + firstImei + ", fourthImei=" + fourthImei
				+ ", secondImei=" + secondImei + ", thirdImei=" + thirdImei + "]";
	}
	}
