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
public class DeviceEndUserDb  implements Serializable{

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

	private Long endUserUserId;
	private String endUserTxnId;   
	private LocalDateTime endUserDate;
	private Integer endUserDeviceStatus;
	private Integer previousEndUserDeviceStatus;
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


	public Long getEndUserUserId() {
		return endUserUserId;
	}


	public void setEndUserUserId(Long endUserUserId) {
		this.endUserUserId = endUserUserId;
	}


	public String getEndUserTxnId() {
		return endUserTxnId;
	}


	public void setEndUserTxnId(String endUserTxnId) {
		this.endUserTxnId = endUserTxnId;
	}


	public LocalDateTime getEndUserDate() {
		return endUserDate;
	}


	public void setEndUserDate(LocalDateTime endUserDate) {
		this.endUserDate = endUserDate;
	}


	public Integer getEndUserDeviceStatus() {
		return endUserDeviceStatus;
	}


	public void setEndUserDeviceStatus(Integer endUserDeviceStatus) {
		this.endUserDeviceStatus = endUserDeviceStatus;
	}


	public Integer getPreviousEndUserDeviceStatus() {
		return previousEndUserDeviceStatus;
	}


	public void setPreviousEndUserDeviceStatus(Integer previousEndUserDeviceStatus) {
		this.previousEndUserDeviceStatus = previousEndUserDeviceStatus;
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
		builder.append("DeviceEndUserDb [id=");
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
		builder.append(", endUserUserId=");
		builder.append(endUserUserId);
		builder.append(", endUserTxnId=");
		builder.append(endUserTxnId);
		builder.append(", endUserDate=");
		builder.append(endUserDate);
		builder.append(", endUserDeviceStatus=");
		builder.append(endUserDeviceStatus);
		builder.append(", previousEndUserDeviceStatus=");
		builder.append(previousEndUserDeviceStatus);
		builder.append(", period=");
		builder.append(period);
		builder.append("]");
		return builder.toString();
	}


	

}
