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
public class DeviceManufacturerDb  implements Serializable{

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

	private String manufaturerDate;
	private String deviceType;
	private String deviceIdType;
	private String multipleSimStatus;
	private String snOfDevice;
	private String imeiEsnMeid;
	private LocalDateTime DeviceLaunchDate;
	private String deviceStatus;
	private String deviceAction;

	private Long manufacturerUserId;
	private String manufacturerTxnId;
	private LocalDateTime manufacturerDate;
	private Integer manufacturerStatus;
	private Integer manufacturerPreviousStatus;
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
	public String getManufaturerDate() {
		return manufaturerDate;
	}
	public void setManufaturerDate(String manufaturerDate) {
		this.manufaturerDate = manufaturerDate;
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
		return DeviceLaunchDate;
	}
	public void setDeviceLaunchDate(LocalDateTime deviceLaunchDate) {
		DeviceLaunchDate = deviceLaunchDate;
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
	public Long getManufacturerUserId() {
		return manufacturerUserId;
	}
	public void setManufacturerUserId(Long manufacturerUserId) {
		this.manufacturerUserId = manufacturerUserId;
	}
	public String getManufacturerTxnId() {
		return manufacturerTxnId;
	}
	public void setManufacturerTxnId(String manufacturerTxnId) {
		this.manufacturerTxnId = manufacturerTxnId;
	}
	public LocalDateTime getManufacturerDate() {
		return manufacturerDate;
	}
	public void setManufacturerDate(LocalDateTime manufacturerDate) {
		this.manufacturerDate = manufacturerDate;
	}
	public Integer getManufacturerStatus() {
		return manufacturerStatus;
	}
	public void setManufacturerStatus(Integer manufacturerStatus) {
		this.manufacturerStatus = manufacturerStatus;
	}
	public Integer getManufacturerPreviousStatus() {
		return manufacturerPreviousStatus;
	}
	public void setManufacturerPreviousStatus(Integer manufacturerPreviousStatus) {
		this.manufacturerPreviousStatus = manufacturerPreviousStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceManufacturerDb [id=");
		builder.append(id);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", manufaturerDate=");
		builder.append(manufaturerDate);
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
		builder.append(DeviceLaunchDate);
		builder.append(", deviceStatus=");
		builder.append(deviceStatus);
		builder.append(", deviceAction=");
		builder.append(deviceAction);
		builder.append(", manufacturerUserId=");
		builder.append(manufacturerUserId);
		builder.append(", manufacturerTxnId=");
		builder.append(manufacturerTxnId);
		builder.append(", manufacturerDate=");
		builder.append(manufacturerDate);
		builder.append(", manufacturerStatus=");
		builder.append(manufacturerStatus);
		builder.append(", manufacturerPreviousStatus=");
		builder.append(manufacturerPreviousStatus);
		builder.append("]");
		return builder.toString();
	}
		
}
