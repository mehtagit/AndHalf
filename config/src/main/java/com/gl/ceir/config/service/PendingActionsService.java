package com.gl.ceir.config.service;

import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.PendingActions;

public interface PendingActionsService extends RestServices<PendingActions> {
	public PendingActions get(String ticketId);

	public PendingActions getByMsisdn(Long msisdn);

	public PendingActions getByImei(Long imei);

	public PendingActions getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity);

	public void delete(String ticketId);
}
