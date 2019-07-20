package com.gl.ceir.executor.services.impl;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.executor.config.Container;
import com.gl.ceir.executor.services.State;
import com.gl.ceir.executor.services.state.BlockedUserActionState;

@Service
public class PolicyExecutorBlocking {
	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private Executor executor;

	@Autowired
	private PendingActionsService pendingActionsService;

	@Autowired
	public PolicyExecutorBlocking() {

	}

	public void run() {
		while (true) {
			try {
				List<PendingActions> pendingActionsList = pendingActionsService.getReadyToBlocked();

				if (pendingActionsList != null) {
					for (PendingActions pendingActions : pendingActionsList) {
						State state = null;
						state = Container.context.getBean(BlockedUserActionState.class);
						state.setPendingActions(pendingActions);
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
