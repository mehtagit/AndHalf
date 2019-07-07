package com.gl.ceir.executor.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.TransactionState;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.executor.services.State;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InitState extends State {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private PendingActionsService pendingActionsService;

	@Override
	public State execute(PendingActions pendingActions) {

		if (sendToCommunicationModule(pendingActions)) {
			pendingActions.setTransactionState(TransactionState.SEND_TO_COMM);
			pendingActionsService.save(pendingActions);
		}
		return null;
	}

	private boolean sendToCommunicationModule(PendingActions pendingActions) {
		logger.info("Pending Action IMEI:" + pendingActions.getImei());
		return true;
	}

}
