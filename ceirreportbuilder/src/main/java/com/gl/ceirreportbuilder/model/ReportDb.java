package com.gl.ceirreportbuilder.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

@Entity
public class ReportDb implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reportnameId;
	
	@NotNull
	@Column(length = 50)
	private String reportName;
	
	private Integer status;
	
	private Integer reportOrder;
	
	@Column(length = 2000)
	private String mysqlInputQuery;
	
	@Column(length = 2000)
	private String oracleInputQuery;
	
	@Column(length = 2000)
	private String monthlyInputQuery;
	
	@Column(length = 100)
	private String outputTable;
	
	@Column(length = 1000)
	private String insertQuery;
	
	@Column(length = 1500)
	private String reportDataQuery;
	
	private Integer viewflag;

	private String txnIdField;
	
	private String keyColumn;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;


	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime modifiedOn;


	public Long getReportnameId() {
		return reportnameId;
	}


	public void setReportnameId(Long reportnameId) {
		this.reportnameId = reportnameId;
	}


	public String getReportName() {
		return reportName;
	}


	public void setReportName(String reportName) {
		this.reportName = reportName;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getReportOrder() {
		return reportOrder;
	}


	public void setReportOrder(Integer reportOrder) {
		this.reportOrder = reportOrder;
	}


	public String getMysqlInputQuery() {
		return mysqlInputQuery;
	}


	public void setMysqlInputQuery(String mysqlInputQuery) {
		this.mysqlInputQuery = mysqlInputQuery;
	}


	public String getOracleInputQuery() {
		return oracleInputQuery;
	}


	public void setOracleInputQuery(String oracleInputQuery) {
		this.oracleInputQuery = oracleInputQuery;
	}


	public String getOutputTable() {
		return outputTable;
	}


	public void setOutputTable(String outputTable) {
		this.outputTable = outputTable;
	}


	public String getInsertQuery() {
		return insertQuery;
	}


	public void setInsertQuery(String insertQuery) {
		this.insertQuery = insertQuery;
	}


	public String getReportDataQuery() {
		return reportDataQuery;
	}


	public void setReportDataQuery(String reportDataQuery) {
		this.reportDataQuery = reportDataQuery;
	}


	public Integer getViewflag() {
		return viewflag;
	}


	public void setViewflag(Integer viewflag) {
		this.viewflag = viewflag;
	}


	public String getTxnIdField() {
		return txnIdField;
	}


	public void setTxnIdField(String txnIdField) {
		this.txnIdField = txnIdField;
	}


	public String getKeyColumn() {
		return keyColumn;
	}


	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
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


	public String getMonthlyInputQuery() {
		return monthlyInputQuery;
	}


	public void setMonthlyInputQuery(String monthlyInputQuery) {
		this.monthlyInputQuery = monthlyInputQuery;
	}


	@Override
	public String toString() {
		return "ReportDb [reportnameId=" + reportnameId + ", reportName=" + reportName + ", status=" + status
				+ ", reportOrder=" + reportOrder + ", mysqlInputQuery=" + mysqlInputQuery + ", oracleInputQuery="
				+ oracleInputQuery + ", monthlyInputQuery=" + monthlyInputQuery + ", outputTable=" + outputTable
				+ ", insertQuery=" + insertQuery + ", reportDataQuery=" + reportDataQuery + ", viewflag=" + viewflag
				+ ", txnIdField=" + txnIdField + ", keyColumn=" + keyColumn + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + "]";
	}
	
}
