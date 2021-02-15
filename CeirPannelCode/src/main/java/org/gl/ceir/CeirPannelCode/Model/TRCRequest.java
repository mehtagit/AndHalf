package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class TRCRequest {
	private String endDate,searchString,startDate,tac,txnId,userType,filterUserType,userDisplayName,modifiedOn;
	private Integer status,adminStatus,featureId,userTypeId,file;
	private String displayName,Country;
	private long userId;
	private Long productName,modelNumber;
	public String columnName,sort;
	public String order,orderColumnName;
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
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
	public String getFilterUserType() {
		return filterUserType;
	}
	public void setFilterUserType(String filterUserType) {
		this.filterUserType = filterUserType;
	}
	public String getUserDisplayName() {
		return userDisplayName;
	}
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
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
	public Integer getFile() {
		return file;
	}
	public void setFile(Integer file) {
		this.file = file;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Long getProductName() {
		return productName;
	}
	public void setProductName(Long productName) {
		this.productName = productName;
	}
	public Long getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(Long modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOrderColumnName() {
		return orderColumnName;
	}
	public void setOrderColumnName(String orderColumnName) {
		this.orderColumnName = orderColumnName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRCRequest [endDate=");
		builder.append(endDate);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", tac=");
		builder.append(tac);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", filterUserType=");
		builder.append(filterUserType);
		builder.append(", userDisplayName=");
		builder.append(userDisplayName);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", status=");
		builder.append(status);
		builder.append(", adminStatus=");
		builder.append(adminStatus);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", file=");
		builder.append(file);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", Country=");
		builder.append(Country);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", productName=");
		builder.append(productName);
		builder.append(", modelNumber=");
		builder.append(modelNumber);
		builder.append(", columnName=");
		builder.append(columnName);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", order=");
		builder.append(order);
		builder.append(", orderColumnName=");
		builder.append(orderColumnName);
		builder.append("]");
		return builder.toString();
	}
	
	
}

