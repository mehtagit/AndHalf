package com.gl.ceir.config.model;

import com.gl.ceir.config.model.constants.DocumentType;

public class UploadFileRequest {
	private String ticketId;
	private DocumentType documentType;
	private Long imei;
	private Long msisdn;

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public void setImei(Long imei) {
		this.imei = imei;
	}

	public void setMsisdn(Long msisdn) {
		this.msisdn = msisdn;
	}

	public String getTicketId() {
		return ticketId;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public Long getImei() {
		return imei;
	}

	public Long getMsisdn() {
		return msisdn;
	}

}
