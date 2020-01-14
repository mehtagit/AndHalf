package org.gl.ceir.CeirPannelCode.Model;

public class FilterRequest {
	public String startDate,endDate,roleType,userType,txnId,searchString,grievanceId,tag,remarks;
	private Integer userId,taxPaidStatus,consignmentStatus,featureId,userTypeId,fileStatus,requestType,sourceType,grievanceStatus,userRoleTypeId,status,asType,serviceDump,fileType,action,operatorTypeId,channel,type;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getGrievanceId() {
		return grievanceId;
	}
	public void setGrievanceId(String grievanceId) {
		this.grievanceId = grievanceId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTaxPaidStatus() {
		return taxPaidStatus;
	}
	public void setTaxPaidStatus(Integer taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}
	public Integer getConsignmentStatus() {
		return consignmentStatus;
	}
	public void setConsignmentStatus(Integer consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Integer getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(Integer fileStatus) {
		this.fileStatus = fileStatus;
	}
	public Integer getRequestType() {
		return requestType;
	}
	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getGrievanceStatus() {
		return grievanceStatus;
	}
	public void setGrievanceStatus(Integer grievanceStatus) {
		this.grievanceStatus = grievanceStatus;
	}
	public Integer getUserRoleTypeId() {
		return userRoleTypeId;
	}
	public void setUserRoleTypeId(Integer userRoleTypeId) {
		this.userRoleTypeId = userRoleTypeId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAsType() {
		return asType;
	}
	public void setAsType(Integer asType) {
		this.asType = asType;
	}
	public Integer getServiceDump() {
		return serviceDump;
	}
	public void setServiceDump(Integer serviceDump) {
		this.serviceDump = serviceDump;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public Integer getOperatorTypeId() {
		return operatorTypeId;
	}
	public void setOperatorTypeId(Integer operatorTypeId) {
		this.operatorTypeId = operatorTypeId;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilterRequest [startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", roleType=");
		builder.append(roleType);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", grievanceId=");
		builder.append(grievanceId);
		builder.append(", tag=");
		builder.append(tag);
		builder.append(", remarks=");
		builder.append(remarks);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", taxPaidStatus=");
		builder.append(taxPaidStatus);
		builder.append(", consignmentStatus=");
		builder.append(consignmentStatus);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", fileStatus=");
		builder.append(fileStatus);
		builder.append(", requestType=");
		builder.append(requestType);
		builder.append(", sourceType=");
		builder.append(sourceType);
		builder.append(", grievanceStatus=");
		builder.append(grievanceStatus);
		builder.append(", userRoleTypeId=");
		builder.append(userRoleTypeId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", asType=");
		builder.append(asType);
		builder.append(", serviceDump=");
		builder.append(serviceDump);
		builder.append(", fileType=");
		builder.append(fileType);
		builder.append(", action=");
		builder.append(action);
		builder.append(", operatorTypeId=");
		builder.append(operatorTypeId);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
}