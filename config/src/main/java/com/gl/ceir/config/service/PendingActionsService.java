package com.gl.ceir.config.service;

import com.gl.ceir.config.model.PendingActions;

public interface PendingActionsService extends RestServices<PendingActions> {
	public PendingActions get(String ticketId);

	public void delete(String ticketId);
}
