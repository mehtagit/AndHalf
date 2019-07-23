package com.gl.ceir.config.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.TransactionState;

public interface PendingActionsService extends RestServices<PendingActions> {
	public PendingActions get(String ticketId);

	public PendingActions getByMsisdn(Long msisdn);

	public PendingActions getByImei(Long imei);

	public PendingActions getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void delete(String ticketId);

	public List<PendingActions> saveAll(List<PendingActions> pendingActions);

	public List<PendingActions> getByTransactionState(TransactionState transactionState);

	public List<PendingActions> getReadyToBlocked();

	public List<PendingActions> getReminderList();

	public List<PendingActions> getApprovedList();

	public int updateTransactionState(TransactionState transactionState, String ticketId);

	public boolean regularizedTicket(String ticketID);

}
