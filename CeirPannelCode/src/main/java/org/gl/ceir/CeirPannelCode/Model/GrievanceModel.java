package org.gl.ceir.CeirPannelCode.Model;

import java.util.List;

public class GrievanceModel {
private int id;
private int categoryId;
private int grievanceStatus ;
private int userId;
private String fileName;
private String grievanceId;
private String modifiedOn;
private String createdOn;
private String remarks;
private String txnId;
private String userType;
private String reply;
private String userDisplayName;
private List<MultipleFileModel> attachedFiles;



public List<MultipleFileModel> getAttachedFiles() {
return attachedFiles;
}
public void setAttachedFiles(List<MultipleFileModel> attachedFiles) {
this.attachedFiles = attachedFiles;
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
@Override
public String toString() {
return "GrievanceModel [id=" + id + ", categoryId=" + categoryId + ", grievanceStatus=" + grievanceStatus
+ ", userId=" + userId + ", fileName=" + fileName + ", grievanceId=" + grievanceId + ", modifiedOn="
+ modifiedOn + ", createdOn=" + createdOn + ", remarks=" + remarks + ", txnId=" + txnId + ", userType="
+ userType + ", reply=" + reply + ", userDisplayName=" + userDisplayName + ", attachedFiles="
+ attachedFiles + "]";
}
}