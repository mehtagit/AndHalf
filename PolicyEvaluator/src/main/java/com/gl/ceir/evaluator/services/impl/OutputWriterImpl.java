package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.service.TicketIdGenerator;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.pojo.Result;
import com.gl.ceir.evaluator.services.OutpuWriter;

@Service
public class OutputWriterImpl implements OutpuWriter {

	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private PolicyEvaluator policyEvaluator;

	@Autowired
	private Result result;

	@Autowired
	private TicketIdGenerator ticketIdGenerator;

	@Override
	public boolean write(Request request) {
		return write(request, request.getActionNames());
	}

	@Override
	public boolean write(Request request, ActionNames actionNames) {
		PendingActions pendingActions = new PendingActions();
		DeviceSnapShot deviceSnapShot = new DeviceSnapShot();
		deviceSnapShot.setMobileOperatorId(1L);
		deviceSnapShot.setImei(request.getImei());
		deviceSnapShot.setMsisdn(request.getMsisdn());
		pendingActions.setTicketId(ticketIdGenerator.getTicketId());
		pendingActions.setImei(request.getImei());
		pendingActions.setMsisdn(request.getMsisdn());
		pendingActions.setFailedRule(request.getFailRule());

		switch (actionNames) {
		case AUTO_REGULARIZED:
			break;
		case SYSTEM_REGULARIZED:
			result.getPendingBatch().add(pendingActions);
			break;
		case USER_REGULARIZED:
			result.getPendingBatch().add(pendingActions);
			break;
		default:
			break;

		}
		result.getDeviceSnapshotBatch().add(deviceSnapShot);
		logger.info("Added to batch " + request);
		policyEvaluator.getFileCountDownLatch().countDown();
		return false;
	}

}
