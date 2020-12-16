package org.gl.ceir.CeirPannelCode.Model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class DBrowDataModel {
	private String dbName;
	private String tableName;
	private Integer pageNo, pageSize,unitId;
	private List<String> columns;
	private List<Map<String, String>> rowData;
	
	private String endDate,startDate,txnId,username,userType;
	private Integer reportnameId,userTypeId,userId,featureId;
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public List<Map<String, String>> getRowData() {
		return rowData;
	}
	public void setRowData(List<Map<String, String>> rowData) {
		this.rowData = rowData;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Integer getReportnameId() {
		return reportnameId;
	}
	public void setReportnameId(Integer reportnameId) {
		this.reportnameId = reportnameId;
	}
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DBrowDataModel [dbName=");
		builder.append(dbName);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", unitId=");
		builder.append(unitId);
		builder.append(", columns=");
		builder.append(columns);
		builder.append(", rowData=");
		builder.append(rowData);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", username=");
		builder.append(username);
		builder.append(", userType=");
		builder.append(userType);
		builder.append(", reportnameId=");
		builder.append(reportnameId);
		builder.append(", userTypeId=");
		builder.append(userTypeId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", getDbName()=");
		builder.append(getDbName());
		builder.append(", getTableName()=");
		builder.append(getTableName());
		builder.append(", getPageNo()=");
		builder.append(getPageNo());
		builder.append(", getPageSize()=");
		builder.append(getPageSize());
		builder.append(", getUnitId()=");
		builder.append(getUnitId());
		builder.append(", getColumns()=");
		builder.append(getColumns());
		builder.append(", getRowData()=");
		builder.append(getRowData());
		builder.append(", getEndDate()=");
		builder.append(getEndDate());
		builder.append(", getStartDate()=");
		builder.append(getStartDate());
		builder.append(", getTxnId()=");
		builder.append(getTxnId());
		builder.append(", getUsername()=");
		builder.append(getUsername());
		builder.append(", getUserType()=");
		builder.append(getUserType());
		builder.append(", getReportnameId()=");
		builder.append(getReportnameId());
		builder.append(", getUserTypeId()=");
		builder.append(getUserTypeId());
		builder.append(", getUserId()=");
		builder.append(getUserId());
		builder.append(", getFeatureId()=");
		builder.append(getFeatureId());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
