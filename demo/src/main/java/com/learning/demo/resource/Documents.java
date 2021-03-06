package com.learning.demo.resource;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class Documents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String filename;

	private String filepath;

	private String fileDownloadUri;

	private String fileType;

	private DocumentType documentType;

	private long size;

	private DocumentStatus status;

	private String approvedBy;

	private Date approvalDate;

	private String rejectedReason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public DocumentStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	@Override
	public String toString() {
		return "Documents [" + (id != null ? "id=" + id + ", " : "")
				+ (filename != null ? "filename=" + filename + ", " : "")
				+ (filepath != null ? "filepath=" + filepath + ", " : "")
				+ (fileDownloadUri != null ? "fileDownloadUri=" + fileDownloadUri + ", " : "")
				+ (fileType != null ? "fileType=" + fileType + ", " : "")
				+ (documentType != null ? "documentType=" + documentType + ", " : "") + "size=" + size + ", "
				+ (status != null ? "status=" + status + ", " : "")
				+ (approvedBy != null ? "approvedBy=" + approvedBy + ", " : "")
				+ (approvalDate != null ? "approvalDate=" + approvalDate + ", " : "")
				+ (rejectedReason != null ? "rejectedReason=" + rejectedReason : "") + "]";
	}

}
