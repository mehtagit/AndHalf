package com.gl.ceir.evaluator.services.impl;

import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.OutpuWriter;

public class OutputWriterImpl implements OutpuWriter {

	@Override
	public boolean write(Request request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean write(Request request, ActionNames actionNames) {
		PendingActions pendingActions = new PendingActions();
		pendingActions.setImei(request.getImei());
		pendingActions.setMsisdn(request.getMsisdn());
		PolicyEvaluator.batchInsertData.add(pendingActions);
		return false;
	}

}
