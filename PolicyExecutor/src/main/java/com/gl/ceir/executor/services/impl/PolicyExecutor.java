package com.gl.ceir.executor.services.impl;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.model.constants.TransactionState;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.executor.config.Container;
import com.gl.ceir.executor.services.State;
import com.gl.ceir.executor.services.state.InitSystemRegularisedState;
import com.gl.ceir.executor.services.state.InitUserRegularisedState;

@Service
public class PolicyExecutor {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private Executor executor;

	@Autowired
	private PendingActionsService pendingActionsService;

	@Autowired
	public PolicyExecutor() {

	}

	public void run() {
		while (true) {
			try {
				List<PendingActions> pendingActionsList = pendingActionsService
						.getByTransactionState(TransactionState.INIT);

				if (pendingActionsList != null) {
					for (PendingActions pendingActions : pendingActionsList) {
						State state = null;
						if (pendingActions.getAction().getName() == ActionNames.SYSTEM_REGULARIZED) {
							state = Container.context.getBean(InitSystemRegularisedState.class);
							state.setPendingActions(pendingActions);
						} else if (pendingActions.getAction().getName() == ActionNames.USER_REGULARIZED) {
							state = Container.context.getBean(InitUserRegularisedState.class);
							state.setPendingActions(pendingActions);
						}
						executor.execute(state);
					}
				}

				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

}
