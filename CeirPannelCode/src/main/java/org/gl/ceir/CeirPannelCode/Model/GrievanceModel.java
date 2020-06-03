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
private GrievanceMessageModal grievance;
private List<MultipleFileModel> attachedFiles;




public GrievanceMessageModal getGrievance() {
	return grievance;
}



public void setGrievance(GrievanceMessageModal grievance) {
	this.grievance = grievance;
}



@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("GrievanceModel [id=");
	builder.append(id);
	builder.append(", categoryId=");
	builder.append(categoryId);
	builder.append(", grievanceStatus=");
	builder.append(grievanceStatus);
	builder.append(", userId=");
	builder.append(userId);
	builder.append(", featureId=");
	builder.append(featureId);
	builder.append(", fileName=");
	builder.append(fileName);
	builder.append(", grievanceId=");
	builder.append(grievanceId);
	builder.append(", modifiedOn=");
	builder.append(modifiedOn);
	builder.append(", createdOn=");
	builder.append(createdOn);
	builder.append(", remarks=");
	builder.append(remarks);
	builder.append(", txnId=");
	builder.append(txnId);
	builder.append(", userType=");
	builder.append(userType);
	builder.append(", reply=");
	builder.append(reply);
	builder.append(", userDisplayName=");
	builder.append(userDisplayName);
	builder.append(", email=");
	builder.append(email);
	builder.append(", firstName=");
	builder.append(firstName);
	builder.append(", lastName=");
	builder.append(lastName);
	builder.append(", middleName=");
	builder.append(middleName);
	builder.append(", phoneNo=");
	builder.append(phoneNo);
	builder.append(", grievance=");
	builder.append(grievance);
	builder.append(", attachedFiles=");
	builder.append(attachedFiles);
	builder.append(", getFeatureId()=");
	builder.append(getFeatureId());
	builder.append(", getId()=");
	builder.append(getId());
	builder.append(", getCategoryId()=");
	builder.append(getCategoryId());
	builder.append(", getGrievanceStatus()=");
	builder.append(getGrievanceStatus());
	builder.append(", getUserId()=");
	builder.append(getUserId());
	builder.append(", getFileName()=");
	builder.append(getFileName());
	builder.append(", getGrievanceId()=");
	builder.append(getGrievanceId());
	builder.append(", getModifiedOn()=");
	builder.append(getModifiedOn());
	builder.append(", getCreatedOn()=");
	builder.append(getCreatedOn());
	builder.append(", getRemarks()=");
	builder.append(getRemarks());
	builder.append(", getTxnId()=");
	builder.append(getTxnId());
	builder.append(", getUserType()=");
	builder.append(getUserType());
	builder.append(", getReply()=");
	builder.append(getReply());
	builder.append(", getUserDisplayName()=");
	builder.append(getUserDisplayName());
	builder.append(", getEmail()=");
	builder.append(getEmail());
	builder.append(", getFirstName()=");
	builder.append(getFirstName());
	builder.append(", getLastName()=");
	builder.append(getLastName());
	builder.append(", getMiddleName()=");
	builder.append(getMiddleName());
	builder.append(", getPhoneNo()=");
	builder.append(getPhoneNo());
	builder.append(", getAttachedFiles()=");
	builder.append(getAttachedFiles());
	builder.append(", getClass()=");
	builder.append(getClass());
	builder.append(", hashCode()=");
	builder.append(hashCode());
	builder.append(", toString()=");
	builder.append(super.toString());
	builder.append("]");
	return builder.toString();
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