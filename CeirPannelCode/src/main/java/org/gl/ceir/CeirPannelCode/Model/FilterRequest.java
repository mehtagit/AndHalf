package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class FilterRequest {
	public String startDate,endDate,createdOn,modifiedOn,roleType,userType,txnId,searchString,grievanceId,tag,remarks,deviceId,nid,childTag,field,interp,tagId,value,displayName,description,address,featureName,subFeatureName,userName,date,fileName,invoiceNumber,suplierName,supplierId,stateInterp,alertId,remark,email,phoneNo,username,tac,userDisplayName,filterUserName,FilterUserType,raisedBy,filteredUserType;
	private Integer pageNo, pageSize,userId,taxPaidStatus,consignmentStatus,featureId,userTypeId,fileStatus,requestType,sourceType,grievanceStatus,userRoleTypeId,status,asType,serviceDump,fileType,action,operatorTypeId,channel,type,deviceIdType,parentValue,id,port,currency,quantity,stockStatus,feature,period,year,dataId;
	private Double dollar,riel;
	private int roleTypeId;
	private String state,ruleName;
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
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
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
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getChildTag() {
		return childTag;
	}
	public void setChildTag(String childTag) {
		this.childTag = childTag;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getInterp() {
		return interp;
	}
	public void setInterp(String interp) {
		this.interp = interp;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getSubFeatureName() {
		return subFeatureName;
	}
	public void setSubFeatureName(String subFeatureName) {
		this.subFeatureName = subFeatureName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getSuplierName() {
		return suplierName;
	}
	public void setSuplierName(String suplierName) {
		this.suplierName = suplierName;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getStateInterp() {
		return stateInterp;
	}
	public void setStateInterp(String stateInterp) {
		this.stateInterp = stateInterp;
	}
	public String getAlertId() {
		return alertId;
	}
	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public String getUserDisplayName() {
		return userDisplayName;
	}
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	public String getFilterUserName() {
		return filterUserName;
	}
	public void setFilterUserName(String filterUserName) {
		this.filterUserName = filterUserName;
	}
	public String getFilterUserType() {
		return FilterUserType;
	}
	public void setFilterUserType(String filterUserType) {
		FilterUserType = filterUserType;
	}
	public String getRaisedBy() {
		return raisedBy;
	}
	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}
	public String getFilteredUserType() {
		return filteredUserType;
	}
	public void setFilteredUserType(String filteredUserType) {
		this.filteredUserType = filteredUserType;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
	public Integer getDeviceIdType() {
		return deviceIdType;
	}
	public void setDeviceIdType(Integer deviceIdType) {
		this.deviceIdType = deviceIdType;
	}
	public Integer getParentValue() {
		return parentValue;
	}
	public void setParentValue(Integer parentValue) {
		this.parentValue = parentValue;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}
	public Integer getFeature() {
		return feature;
	}
	public void setFeature(Integer feature) {
		this.feature = feature;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public Double getDollar() {
		return dollar;
	}
	public void setDollar(Double dollar) {
		this.dollar = dollar;
	}
	public Double getRiel() {
		return riel;
	}
	public void setRiel(Double riel) {
		this.riel = riel;
	}
	public int getRoleTypeId() {
		return roleTypeId;
	}
	public void setRoleTypeId(int roleTypeId) {
		this.roleTypeId = roleTypeId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilterRequest [startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
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
		builder.append(", deviceId=");
		builder.append(deviceId);
		builder.append(", nid=");
		builder.append(nid);
		builder.append(", childTag=");
		builder.append(childTag);
		builder.append(", field=");
		builder.append(field);
		builder.append(", interp=");
		builder.append(interp);
		builder.append(", tagId=");
		builder.append(tagId);
		builder.append(", value=");
		builder.append(value);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", address=");
		builder.append(address);
		builder.append(", featureName=");
		builder.append(featureName);
		builder.append(", subFeatureName=");
		builder.append(subFeatureName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", date=");
		builder.append(date);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", invoiceNumber=");
		builder.append(invoiceNumber);
		builder.append(", suplierName=");
		builder.append(suplierName);
		builder.append(", supplierId=");
		builder.append(supplierId);
		builder.append(", stateInterp=");
		builder.append(stateInterp);
		builder.append(", alertId=");
		builder.append(alertId);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", username=");
		builder.append(username);
		builder.append(", tac=");
		builder.append(tac);
		builder.append(", userDisplayName=");
		builder.append(userDisplayName);
		builder.append(", filterUserName=");
		builder.append(filterUserName);
		builder.append(", FilterUserType=");
		builder.append(FilterUserType);
		builder.append(", raisedBy=");
		builder.append(raisedBy);
		builder.append(", filteredUserType=");
		builder.append(filteredUserType);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
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
		builder.append(", deviceIdType=");
		builder.append(deviceIdType);
		builder.append(", parentValue=");
		builder.append(parentValue);
		builder.append(", id=");
		builder.append(id);
		builder.append(", port=");
		builder.append(port);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", stockStatus=");
		builder.append(stockStatus);
		builder.append(", feature=");
		builder.append(feature);
		builder.append(", period=");
		builder.append(period);
		builder.append(", year=");
		builder.append(year);
		builder.append(", dataId=");
		builder.append(dataId);
		builder.append(", dollar=");
		builder.append(dollar);
		builder.append(", riel=");
		builder.append(riel);
		builder.append(", roleTypeId=");
		builder.append(roleTypeId);
		builder.append(", state=");
		builder.append(state);
		builder.append(", ruleName=");
		builder.append(ruleName);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}