package com.gl.ceir.config.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
public class DeviceCustomDb  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date createdOn;

	@JsonIgnore
	@UpdateTimestamp
	private Date modifiedOn;

	private String manufatureDate;
	private String deviceType;
	private String deviceIdType;
	private String multipleSimStatus;
	private String snOfDevice;
	private String imeiEsnMeid;
	private LocalDateTime deviceLaunchDate;
	private String deviceStatus;
	private String deviceAction;

	private Long customUserId;
	private String customTxnId;
	private LocalDateTime customDate;
	private Integer customDeviceStatus;
	private Integer previousCustomDeviceStatus;
	private Integer period;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
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
	public LocalDateTime getDeviceLaunchDate() {
		return deviceLaunchDate;
	}
	public void setDeviceLaunchDate(LocalDateTime deviceLaunchDate) {
		this.deviceLaunchDate = deviceLaunchDate;
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
	public Long getCustomUserId() {
		return customUserId;
	}
	public void setCustomUserId(Long customUserId) {
		this.customUserId = customUserId;
	}
	public String getCustomTxnId() {
		return customTxnId;
	}
	public void setCustomTxnId(String customTxnId) {
		this.customTxnId = customTxnId;
	}
	public LocalDateTime getCustomDate() {
		return customDate;
	}
	public void setCustomDate(LocalDateTime customDate) {
		this.customDate = customDate;
	}
	public Integer getCustomDeviceStatus() {
		return customDeviceStatus;
	}
	public void setCustomDeviceStatus(Integer customDeviceStatus) {
		this.customDeviceStatus = customDeviceStatus;
	}
	public Integer getPreviousCustomDeviceStatus() {
		return previousCustomDeviceStatus;
	}
	public void setPreviousCustomDeviceStatus(Integer previousCustomDeviceStatus) {
		this.previousCustomDeviceStatus = previousCustomDeviceStatus;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceCustomDb [id=");
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
		builder.append(", customUserId=");
		builder.append(customUserId);
		builder.append(", customTxnId=");
		builder.append(customTxnId);
		builder.append(", customDate=");
		builder.append(customDate);
		builder.append(", customDeviceStatus=");
		builder.append(customDeviceStatus);
		builder.append(", previousCustomDeviceStatus=");
		builder.append(previousCustomDeviceStatus);
		builder.append(", period=");
		builder.append(period);
		builder.append("]");
		return builder.toString();
	}

}
