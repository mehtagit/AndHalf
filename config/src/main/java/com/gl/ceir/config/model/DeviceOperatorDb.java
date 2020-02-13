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
public class DeviceOperatorDb  implements Serializable{

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

	private Long operatorUserId;
	private String operatorTxnId;        
	private LocalDateTime operatorDate;
	private Integer operatorStatus;
	private Integer operatorPreviousStatus;
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
	public Long getOperatorUserId() {
		return operatorUserId;
	}
	public void setOperatorUserId(Long operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
	public String getOperatorTxnId() {
		return operatorTxnId;
	}
	public void setOperatorTxnId(String operatorTxnId) {
		this.operatorTxnId = operatorTxnId;
	}
	public LocalDateTime getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(LocalDateTime operatorDate) {
		this.operatorDate = operatorDate;
	}
	public Integer getOperatorStatus() {
		return operatorStatus;
	}
	public void setOperatorStatus(Integer operatorStatus) {
		this.operatorStatus = operatorStatus;
	}
	public Integer getOperatorPreviousStatus() {
		return operatorPreviousStatus;
	}
	public void setOperatorPreviousStatus(Integer operatorPreviousStatus) {
		this.operatorPreviousStatus = operatorPreviousStatus;
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
		builder.append("DeviceOperatorDb [id=");
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
		builder.append(", operatorUserId=");
		builder.append(operatorUserId);
		builder.append(", operatorTxnId=");
		builder.append(operatorTxnId);
		builder.append(", operatorDate=");
		builder.append(operatorDate);
		builder.append(", operatorStatus=");
		builder.append(operatorStatus);
		builder.append(", operatorPreviousStatus=");
		builder.append(operatorPreviousStatus);
		builder.append(", period=");
		builder.append(period);
		builder.append("]");
		return builder.toString();
	}
	
		
}
