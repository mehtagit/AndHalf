package com.functionapps.pojo;

import java.io.Serializable;

public class DeviceImporterDb  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long rev;
	private Integer revtype;
	private String createdOn;
	private String modifiedOn;

	private String manufatureDate;
	private String deviceType;
	private String deviceIdType;
	private String multipleSimStatus;
	private String snOfDevice;
	private String imeiEsnMeid;
	private String deviceLaunchDate;
	private String deviceStatus;
	private String deviceAction;

	private Long userId;
	private String txnId;
	private String localDate;
	private Integer deviceState;
	private Integer previousDeviceStatus;
	private String period;
	private Integer featureId;
	
	public DeviceImporterDb() {
		
	}
	
	public DeviceImporterDb(Long id, Long rev, int revtype, String createdOn, String modifiedOn, String manufatureDate, 
			String deviceType, String deviceIdType, String multipleSimStatus, String snOfDevice, String imeiEsnMeid,
			String deviceLaunchDate, String deviceStatus, String deviceAction, Long userId, String txnId, String localDate, 
			Integer deviceState, Integer previousDeviceStatus, String period, Integer featureId) {
		this.id = id;
		this.rev = rev;
		this.revtype = revtype;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.manufatureDate = manufatureDate;
		this.deviceType = deviceType;
		this.deviceIdType = deviceIdType;
		this.multipleSimStatus = multipleSimStatus;
		this.snOfDevice = snOfDevice;
		this.imeiEsnMeid = imeiEsnMeid;
		this.deviceLaunchDate = deviceLaunchDate;
		this.deviceStatus = deviceStatus;
		this.deviceAction = deviceAction;
		this.userId = userId;
		this.txnId = txnId;
		this.localDate = localDate;
		this.deviceState = deviceState;
		this.previousDeviceStatus = previousDeviceStatus;
		this.period = period;
		this.featureId = featureId;
	}
	
	public Long getRev() {
		return rev;
	}

	public void setRev(Long rev) {
		this.rev = rev;
	}

	public Integer getRevtype() {
		return revtype;
	}

	public void setRevtype(Integer revtype) {
		this.revtype = revtype;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getManufatureDate() {
		return manufatureDate;
	}

	public void setManufatureDate(String manufatureDate) {
		this.manufatureDate = manufatureDate;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceIdType() {
		return deviceIdType;
	}

	public void setDeviceIdType(String deviceIdType) {
		this.deviceIdType = deviceIdType;
	}

	public String getMultipleSimStatus() {
		return multipleSimStatus;
	}

	public void setMultipleSimStatus(String multipleSimStatus) {
		this.multipleSimStatus = multipleSimStatus;
	}

	public String getSnOfDevice() {
		return snOfDevice;
	}

	public void setSnOfDevice(String snOfDevice) {
		this.snOfDevice = snOfDevice;
	}

	public String getImeiEsnMeid() {
		return imeiEsnMeid;
	}

	public void setImeiEsnMeid(String imeiEsnMeid) {
		this.imeiEsnMeid = imeiEsnMeid;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceAction() {
		return deviceAction;
	}

	public void setDeviceAction(String deviceAction) {
		this.deviceAction = deviceAction;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getDeviceLaunchDate() {
		return deviceLaunchDate;
	}

	public void setDeviceLaunchDate(String deviceLaunchDate) {
		this.deviceLaunchDate = deviceLaunchDate;
	}

	public String getLocalDate() {
		return localDate;
	}

	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}

	public Integer getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(Integer deviceState) {
		this.deviceState = deviceState;
	}

	public Integer getPreviousDeviceStatus() {
		return previousDeviceStatus;
	}

	public void setPreviousDeviceStatus(Integer previousDeviceStatus) {
		this.previousDeviceStatus = previousDeviceStatus;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getFeatureId() {
		return featureId;
	}

	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceImporterDb [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", manufatureDate=");
		builder.append(manufatureDate);
		builder.append(", deviceType=");
		builder.append(deviceType);
		builder.append(", deviceIdType=");
		builder.append(deviceIdType);
		builder.append(", multipleSimStatus=");
		builder.append(multipleSimStatus);
		builder.append(", snOfDevice=");
		builder.append(snOfDevice);
		builder.append(", imeiEsnMeid=");
		builder.append(imeiEsnMeid);
		builder.append(", deviceLaunchDate=");
		builder.append(deviceLaunchDate);
		builder.append(", deviceStatus=");
		builder.append(deviceStatus);
		builder.append(", deviceAction=");
		builder.append(deviceAction);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", localDate=");
		builder.append(localDate);
		builder.append(", deviceState=");
		builder.append(deviceState);
		builder.append(", previousDeviceStatus=");
		builder.append(previousDeviceStatus);
		builder.append(", period=");
		builder.append(period);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append("]");
		return builder.toString();
	}
}