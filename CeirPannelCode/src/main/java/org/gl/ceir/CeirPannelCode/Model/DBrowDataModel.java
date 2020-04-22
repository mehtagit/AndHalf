package org.gl.ceir.CeirPannelCode.Model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class DBrowDataModel {
	private String dbName;
	private String tableName;
	private List<String> columns;
	private List<Map<String, String>> rowData;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DBrowDataModel [dbName=");
		builder.append(dbName);
		builder.append(", tableName=");
		builder.append(tableName);
		builder.append(", columns=");
		builder.append(columns);
		builder.append(", rowData=");
		builder.append(rowData);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
}
