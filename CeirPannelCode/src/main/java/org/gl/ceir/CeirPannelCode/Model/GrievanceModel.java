package org.gl.ceir.CeirPannelCode.Model;

import java.util.List;

public class GrievanceModel {
private int id;
private int categoryId;
private int grievanceStatus ;
private int userId,featureId;
private String fileName;
private String grievanceId;
private String modifiedOn;
private String createdOn;
private String remarks;
private String txnId;
private String userType;
private String reply;
private String userDisplayName,email,firstName,lastName,middleName,phoneNo;
private List<MultipleFileModel> attachedFiles;



@Override
public String toString() {
	return "GrievanceModel [id=" + id + ", categoryId=" + categoryId + ", grievanceStatus=" + grievanceStatus
			+ ", userId=" + userId + ", featureId=" + featureId + ", fileName=" + fileName + ", grievanceId="
			+ grievanceId + ", modifiedOn=" + modifiedOn + ", createdOn=" + createdOn + ", remarks=" + remarks
			+ ", txnId=" + txnId + ", userType=" + userType + ", reply=" + reply + ", userDisplayName="
			+ userDisplayName + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
			+ ", middleName=" + middleName + ", phoneNo=" + phoneNo + ", attachedFiles=" + attachedFiles + "]";
}



public int getFeatureId() {
	return featureId;
}



public void setFeatureId(int featureId) {
	this.featureId = featureId;
}



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}
public int getGrievanceStatus() {
	return grievanceStatus;
}
public void setGrievanceStatus(int grievanceStatus) {
	this.grievanceStatus = grievanceStatus;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getGrievanceId() {
	return grievanceId;
}
public void setGrievanceId(String grievanceId) {
	this.grievanceId = grievanceId;
}
public String getModifiedOn() {
	return modifiedOn;
}
public void setModifiedOn(String modifiedOn) {
	this.modifiedOn = modifiedOn;
}
public String getCreatedOn() {
	return createdOn;
}
public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
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
public String getReply() {
	return reply;
}
public void setReply(String reply) {
	this.reply = reply;
}
public String getUserDisplayName() {
	return userDisplayName;
}
public void setUserDisplayName(String userDisplayName) {
	this.userDisplayName = userDisplayName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getMiddleName() {
	return middleName;
}
public void setMiddleName(String middleName) {
	this.middleName = middleName;
}
public String getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}
public List<MultipleFileModel> getAttachedFiles() {
	return attachedFiles;
}
public void setAttachedFiles(List<MultipleFileModel> attachedFiles) {
	this.attachedFiles = attachedFiles;
}




}