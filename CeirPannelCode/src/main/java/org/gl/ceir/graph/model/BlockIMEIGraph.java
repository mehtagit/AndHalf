package org.gl.ceir.graph.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class BlockIMEIGraph {

	private Object dbName;
	private String tableName;
	private List<String> columns = null;
	private List<BlockIMEIRowData> rowData = null;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlockIMEIGraph [dbName=");
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
	public Object getDbName() {
		return dbName;
	}
	public void setDbName(Object dbName) {
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
	public List<BlockIMEIRowData> getRowData() {
		return rowData;
	}
	public void setRowData(List<BlockIMEIRowData> rowData) {
		this.rowData = rowData;
	}

}
