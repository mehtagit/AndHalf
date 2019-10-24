package org.gl.ceir.CeirPannelCode.config.Service;

import org.gl.ceir.CeirPannelCode.config.Model.Action;
import org.gl.ceir.CeirPannelCode.config.Model.PendingActionStates;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.TransactionState;

public interface PendingActionsStatesService extends RestServices<PendingActionStates> {

	public boolean isStateChangeAllowed(TransactionState currentState, TransactionState nextState, Action action);
}
