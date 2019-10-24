package org.gl.ceir.CeirPannelCode.config.Service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.gl.ceir.CeirPannelCode.config.exceptions.OperationNotAllowedException;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.PendingActions;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.TransactionState;

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

	public boolean regularizedTicket(String ticketID);

	public void changeTransactionState(String ticketId, TransactionState nextState) throws OperationNotAllowedException;

}
