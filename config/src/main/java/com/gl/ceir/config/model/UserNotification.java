package com.gl.ceir.config.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserNotification {

	private Long msisdn;
	private Long imei;
	private Long imsi;

	private Long smsScriptId;
	private Long rulesId;
	@Id
	private String ticketId;

	private Date createdOn;
	private String submitStatus;
	private String message;

	@Enumerated(EnumType.STRING)
	private MessageType messageType;

	public Long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(Long msisdn) {
		this.msisdn = msisdn;
	}

	public Long getImei() {
		return imei;
	}

	public void setImei(Long imei) {
		this.imei = imei;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Long getSmsScriptId() {
		return smsScriptId;
	}

	public void setSmsScriptId(Long smsScriptId) {
		this.smsScriptId = smsScriptId;
	}

	public Long getRulesId() {
		return rulesId;
	}

	public void setRulesId(Long rulesId) {
		this.rulesId = rulesId;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

}
