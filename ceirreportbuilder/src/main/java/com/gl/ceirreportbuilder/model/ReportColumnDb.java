package com.gl.ceirreportbuilder.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class ReportColumnDb implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long report_column_id;
	
	@NotNull
	@Column(name="reportname_id")
	private Long reportnameId;
	
	@NotNull
	@Column(length=50)
	private String columnName;
	
	//@NotNull
	@Column(name="mysql_query", length=1000)
	private String columnQuery;
	
	@Column(name="oracle_query", length=1000)
	private String oracleQuery;
	
//	private Integer reportColumnId;
	
	private Integer source;
	
	@NotNull
	@Column(length=50)
	private String headerName;
	
	@NotNull
	@Column(length=50)
	private String insertparameter;
	
	private Integer columnOrder;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;


	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime modifiedOn;
	
	@ManyToOne
	@JoinColumn(name="reportname_id",insertable = false, updatable = false)
	@JsonIgnore
	private ReportDb report;
	
	@NotNull
	@Column(length=50)
	private String columnDataType;
	
	private Integer typeFlag;

	public String getColumnDataType() {
		return columnDataType;
	}

	public void setColumnDataType(String columnDataType) {
		this.columnDataType = columnDataType;
	}

	public Long getReport_column_id() {
		return report_column_id;
	}

	public void setReport_column_id(Long report_column_id) {
		this.report_column_id = report_column_id;
	}

	public Long getReportnameId() {
		return reportnameId;
	}

	public void setReportnameId(Long reportnameId) {
		this.reportnameId = reportnameId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnQuery() {
		return columnQuery;
	}

	public void setColumnQuery(String columnQuery) {
		this.columnQuery = columnQuery;
	}

//	public Integer getReportColumnId() {
//		return reportColumnId;
//	}
//
//	public void setReportColumnId(Integer reportColumnId) {
//		this.reportColumnId = reportColumnId;
//	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getInsertparameter() {
		return insertparameter;
	}

	public void setInsertparameter(String insertparameter) {
		this.insertparameter = insertparameter;
	}

	public Integer getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(Integer columnOrder) {
		this.columnOrder = columnOrder;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public ReportDb getReport() {
		return report;
	}

	public void setReport(ReportDb report) {
		this.report = report;
	}

	public String getOracleQuery() {
		return oracleQuery;
	}

	public void setOracleQuery(String oracleQuery) {
		this.oracleQuery = oracleQuery;
	}

	public Integer getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}

	@Override
	public String toString() {
		return "ReportColumnDb [report_column_id=" + report_column_id + ", reportnameId=" + reportnameId
				+ ", columnName=" + columnName + ", columnQuery=" + columnQuery + ", oracleQuery=" + oracleQuery
				+ ", source=" + source + ", headerName=" + headerName + ", insertparameter=" + insertparameter
				+ ", columnOrder=" + columnOrder + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", report=" + report + ", columnDataType=" + columnDataType + ", typeFlag=" + typeFlag + "]";
	}
	
}
