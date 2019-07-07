package com.gl.ceir.executor.services;

import com.gl.ceir.config.model.PendingActions;

public abstract class State implements Runnable {

	private PendingActions pendingActions;

	public void setPendingActions(PendingActions pendingActions) {
		this.pendingActions = pendingActions;
	}

	public abstract State execute(PendingActions pendingActions);

	public void run() {
		execute(pendingActions);
	}
}
