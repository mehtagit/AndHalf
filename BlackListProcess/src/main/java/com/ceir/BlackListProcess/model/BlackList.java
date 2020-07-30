package com.ceir.BlackListProcess.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BlackList {

     private static long serialVersionUID = 1L;
     @Id
     @NotNull
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

     @NotNull
     private String imei;
     @NotNull
     private Long msisdn;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
     @CreationTimestamp
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime createdOn;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
     @UpdateTimestamp
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime modifiedOn;

     @JsonIgnore
     private String requestedBy;
     @JsonIgnore
     private String approvedBy;

     @Column(length = 15)
     private String roleType;
     private String userId;
     @Column(length = 20)
     private String txnId;

     private String operator_id;
     private String operator_name;

     public String getOperator_id() {
          return operator_id;
     }

     public void setOperator_id(String operator_id) {
          this.operator_id = operator_id;
     }

     public String getOperator_name() {
          return operator_name;
     }

     public void setOperator_name(String operator_name) {
          this.operator_name = operator_name;
     }

     private String deviceNumber;
     private String deviceType;
     private String deviceAction;
     private String deviceStatus;
     private String DeviceLaunchDate;
     private String multipleSimStatus;
     private String deviceId;
     private String imeiEsnMeid;
     private String modeType;
     private String requestType;
     private String userType;
     private String complainType;   
     
      
     
     @Type(type = "date")
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date expiryDate;

     public String getRoleType() {
          return roleType;
     }

     public void setRoleType(String roleType) {
          this.roleType = roleType;
     }

     public String getUserId() {
          return userId;
     }

     public void setUserId(String userId) {
          this.userId = userId;
     }

     public String getTxnId() {
          return txnId;
     }

     public void setTxnId(String txnId) {
          this.txnId = txnId;
     }

     public String getDeviceNumber() {
          return deviceNumber;
     }

     public void setDeviceNumber(String deviceNumber) {
          this.deviceNumber = deviceNumber;
     }

     public String getDeviceType() {
          return deviceType;
     }

     public void setDeviceType(String deviceType) {
          this.deviceType = deviceType;
     }

     public String getDeviceAction() {
          return deviceAction;
     }

     public void setDeviceAction(String deviceAction) {
          this.deviceAction = deviceAction;
     }

     public String getDeviceStatus() {
          return deviceStatus;
     }

     public void setDeviceStatus(String deviceStatus) {
          this.deviceStatus = deviceStatus;
     }

     public String getDeviceLaunchDate() {
          return DeviceLaunchDate;
     }

     public void setDeviceLaunchDate(String deviceLaunchDate) {
          DeviceLaunchDate = deviceLaunchDate;
     }

     public String getMultipleSimStatus() {
          return multipleSimStatus;
     }

     public void setMultipleSimStatus(String multipleSimStatus) {
          this.multipleSimStatus = multipleSimStatus;
     }

     public String getDeviceId() {
          return deviceId;
     }

     public void setDeviceId(String deviceId) {
          this.deviceId = deviceId;
     }

     public String getImeiEsnMeid() {
          return imeiEsnMeid;
     }

     public void setImeiEsnMeid(String imeiEsnMeid) {
          this.imeiEsnMeid = imeiEsnMeid;
     }

     public String getRequestedBy() {
          return requestedBy;
     }

     public void setRequestedBy(String requestedBy) {
          this.requestedBy = requestedBy;
     }

     public String getApprovedBy() {
          return approvedBy;
     }

     public void setApprovedBy(String approvedBy) {
          this.approvedBy = approvedBy;
     }

     public BlackList() {
          super();
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public LocalDateTime getCreatedOn() {
          return createdOn;
     }

     public void setCreatedOn(LocalDateTime createdOn) {
          this.createdOn = createdOn;
     }

     public LocalDateTime getModifiedOn() {
          return modifiedOn;
     }

     public void setModifiedOn(LocalDateTime modifiedOn) {
          this.modifiedOn = modifiedOn;
     }

     public String getImei() {
          return imei;
     }

     public void setImei(String imei) {
          this.imei = imei;
     }

     public Long getMsisdn() {
          return msisdn;
     }

     public void setMsisdn(Long msisdn) {
          this.msisdn = msisdn;
     }

     @Override
     public String toString() {
          StringBuilder builder = new StringBuilder();
          builder.append("BlackList [imei=");
          builder.append(imei);
          builder.append(", msisdn=");
          builder.append(msisdn);
          builder.append(", id=");
          builder.append(id);
          builder.append(", createdOn=");
          builder.append(createdOn);
          builder.append(", modifiedOn=");
          builder.append(modifiedOn);
          builder.append(", requestedBy=");
          builder.append(requestedBy);
          builder.append(", approvedBy=");
          builder.append(approvedBy);
          builder.append(", roleType=");
          builder.append(roleType);
          builder.append(", userId=");
          builder.append(userId);
          builder.append(", txnId=");
          builder.append(txnId);
          builder.append(", deviceNumber=");
          builder.append(deviceNumber);
          builder.append(", deviceType=");
          builder.append(deviceType);
          builder.append(", deviceAction=");
          builder.append(deviceAction);
          builder.append(", deviceStatus=");
          builder.append(deviceStatus);
          builder.append(", DeviceLaunchDate=");
          builder.append(DeviceLaunchDate);
          builder.append(", multipleSimStatus=");
          builder.append(multipleSimStatus);
          builder.append(", deviceId=");
          builder.append(deviceId);
          builder.append(", imeiEsnMeid=");
          builder.append(imeiEsnMeid);
          builder.append("]");
          return builder.toString();
     }

     public BlackList(@NotNull String imei, @NotNull Long msisdn, String roleType, String userId, String txnId,
             String deviceNumber, String deviceType, String deviceAction, String deviceStatus, String deviceLaunchDate,
             String multipleSimStatus, String deviceId, String imeiEsnMeid, String modeType,
             String requestType, String userType, String complainType, Date expiryDate, String operator_id, String operator_name ) {
          super();
          this.imei = imei;
          this.msisdn = msisdn;
          this.roleType = roleType;
          this.userId = userId;
          this.txnId = txnId;
          this.deviceNumber = deviceNumber;
          this.deviceType = deviceType;
          this.deviceAction = deviceAction;
          this.deviceStatus = deviceStatus;
          DeviceLaunchDate = deviceLaunchDate;
          this.multipleSimStatus = multipleSimStatus;
          this.deviceId = deviceId;
          this.imeiEsnMeid = imeiEsnMeid;
          this.modeType = modeType;
          this.requestType = requestType;
          this.userType = userType;
          this.complainType = complainType;
          this.expiryDate = expiryDate;
          this.operator_id = operator_id;
          this.operator_name =  operator_name;
     }

     public String getModeType() {
          return modeType;
     }

     public void setModeType(String modeType) {
          this.modeType = modeType;
     }

     public String getRequestType() {
          return requestType;
     }

     public void setRequestType(String requestType) {
          this.requestType = requestType;
     }

     public String getUserType() {
          return userType;
     }

     public void setUserType(String userType) {
          this.userType = userType;
     }

     public String getComplainType() {
          return complainType;
     }

     public void setComplainType(String complainType) {
          this.complainType = complainType;
     }

     public void setId(long id) {
          this.id = id;
     }

     public Date getExpiryDate() {
          return expiryDate;
     }

     public void setExpiryDate(Date expiryDate) {
          this.expiryDate = expiryDate;
     }

}



