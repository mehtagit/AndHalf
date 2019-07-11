package com.gl.ceir.config.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.gl.ceir.config.model.constants.TransactionState;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class PendingActions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String ticketId;

	@Enumerated(EnumType.STRING)
	private TransactionState transactionState;
	private Long msisdn;
	private Long imei;
	private Long imsi;
	// @OneToOne
	private Long mobileOperatorId;
	@OneToOne
	private Rules failedRule;
	private Date createdOn;
	@ManyToOne
	private Action action;
	private Date modifiedOn;
	private Date endDateforUserAction;
	private String filename;

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public TransactionState getTransactionState() {
		return transactionState;
	}

	public void setTransactionState(TransactionState transactionState) {
		this.transactionState = transactionState;
	}

	public Long getMobileOperatorId() {
		return mobileOperatorId;
	}

	public void setMobileOperatorId(Long mobileOperatorId) {
		this.mobileOperatorId = mobileOperatorId;
	}

	public Rules getFailedRule() {
		return failedRule;
	}

	public void setFailedRule(Rules failedRule) {
		this.failedRule = failedRule;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Date getEndDateforUserAction() {
		return endDateforUserAction;
	}

	public void setEndDateforUserAction(Date endDateforUserAction) {
		this.endDateforUserAction = endDateforUserAction;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

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

}
