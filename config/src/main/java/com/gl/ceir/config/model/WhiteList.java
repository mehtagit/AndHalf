package com.gl.ceir.config.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

// CR(imei,msisdn)UD 
@Entity
public class WhiteList {

	@EmbeddedId
	private ImeiMsisdnIdentity imeiMsisdnIdentity;

	@JsonIgnore
	private String requestedBy;
	@JsonIgnore
	private String approvedBy;

	public ImeiMsisdnIdentity getImeiMsisdnIdentity() {
		return imeiMsisdnIdentity;
	}

	public void setImeiMsisdnIdentity(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		this.imeiMsisdnIdentity = imeiMsisdnIdentity;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

}
