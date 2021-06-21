package com.gl.ceirreportbuilder.model;

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
public class ReportFreqDb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="reportname_id")
	private Long reportnameId;
	
	@NotNull
	private Integer typeFlag;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;


	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime modifiedOn;
	
	@NotNull
	private Integer status;

	@ManyToOne
	@JoinColumn(name="reportname_id",insertable = false, updatable = false)
	@JsonIgnore
	private ReportDb report;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReportnameId() {
		return reportnameId;
	}

	public void setReportnameId(Long reportnameId) {
		this.reportnameId = reportnameId;
	}

	public Integer getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ReportDb getReport() {
		return report;
	}

	public void setReport(ReportDb report) {
		this.report = report;
	}

	@Override
	public String toString() {
		return "ReportFreqDb [id=" + id + ", reportnameId=" + reportnameId + ", typeFlag=" + typeFlag + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", status=" + status + ", report=" + report + "]";
	}
	
}
