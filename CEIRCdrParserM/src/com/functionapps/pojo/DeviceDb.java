package com.functionapps.pojo;

import java.io.Serializable;
import java.sql.Date;

public class DeviceDb  implements Serializable{

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
	private Date deviceLaunchDate;
	private String deviceStatus;
	private String deviceAction;

	private Integer tac;

	private String period;

	private String txnId;
	private Integer state;

	public DeviceDb() {
	}

	public DeviceDb(Long id, Long rev, int revtype, String createdOn, String modifiedOn, String manufatureDate, String deviceType, 
			String deviceIdType, 
			String multipleSimStatus, String snOfDevice, String imeiEsnMeid, Date deviceLaunchDate, 
			String deviceStatus, String deviceAction, Integer tac, String period, String txnId, Integer state) {
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
		this.tac = tac;
		this.period = period;
		this.txnId = txnId;
		this.state = state;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public Long getRev() {
		return rev;
	}

	public Date getDeviceLaunchDate() {
		return deviceLaunchDate;
	}

	public void setDeviceLaunchDate(Date deviceLaunchDate) {
		this.deviceLaunchDate = deviceLaunchDate;
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

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getTac() {
		return tac;
	}

	public void setTac(Integer tac) {
		this.tac = tac;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceDb [id=");
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
		builder.append(", DeviceLaunchDate=");
		builder.append(deviceLaunchDate);
		builder.append(", deviceStatus=");
		builder.append(deviceStatus);
		builder.append(", deviceAction=");
		builder.append(deviceAction);
		builder.append(", period=");
		builder.append(period);
		builder.append("]");
		return builder.toString();
	}

}