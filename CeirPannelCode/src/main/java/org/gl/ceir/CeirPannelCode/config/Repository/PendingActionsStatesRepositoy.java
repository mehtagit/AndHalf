package org.gl.ceir.CeirPannelCode.config.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.gl.ceir.CeirPannelCode.config.Model.Action;
import org.gl.ceir.CeirPannelCode.config.Model.PendingActionStates;
import org.gl.ceir.CeirPannelCode.config.Model.Constants.TransactionState;

public interface PendingActionsStatesRepositoy extends JpaRepository<PendingActionStates, Integer> {

	public PendingActionStates findByCurrentStateAndNextStateAndAction(TransactionState currentState,
			TransactionState nextState, Action action);
}
