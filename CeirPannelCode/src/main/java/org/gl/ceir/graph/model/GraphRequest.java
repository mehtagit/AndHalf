package org.gl.ceir.graph.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GraphRequest {

	private List<String> columns;
	private String groupBy;
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getEndDate() {
		return endDate;
	}
	
	
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getReportnameId() {
		return reportnameId;
	}
	public void setReportnameId(int reportnameId) {
		this.reportnameId = reportnameId;
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
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	private String dbName;
	private String endDate ;
	private int reportnameId;
	private String searchString;
	private String startDate ;
	private String tableName;
	private String txnId ;
	
	private int file;
	private int pageSize;
	private int pageNo;
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GraphRequest [columns=");
		builder.append(columns);
		builder.append(", groupBy=");
		builder.append(groupBy);
		builder.append(", dbName=");
		builder.append(dbName);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", reportnameId=");
		builder.append(reportnameId);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", file=");
		builder.append(file);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append("]");
		return builder.toString();
	}

	
	
}
