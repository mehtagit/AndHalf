package com.gl.ceir.evaluator.services.impl;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.NullMsisdnRegularized;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;
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

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@Autowired
	private DuplicateImeiMsisdnService duplicateImeiMsisdnService;

	@Override
	public boolean write(Request request) {
		return write(request, request.getActionNames());
	}

	@Override
	public boolean write(Request request, ActionNames actionNames) {

		switch (actionNames) {
		case AUTO_REGULARIZED:
			addToDeviceSnapShot(request);
			break;
		case SYSTEM_REGULARIZED:
			if (request.getImei() == 0) {
				addToNullReqularised(request);
			} else {
				result.getPendingBatch().add(convertRequestToPendingActions(request));
				addToDeviceSnapShot(request);
			}
			break;
		case USER_REGULARIZED:
			result.getPendingBatch().add(convertRequestToPendingActions(request));
			addToDeviceSnapShot(request);
			break;
		case NO_ACTION:
			logger.info(request);
			break;
		}

		policyEvaluator.getFileCountDownLatch().countDown();
		return false;
	}

	private void addToNullReqularised(Request request) {
		NullMsisdnRegularized nullMsisdnRegularized = new NullMsisdnRegularized();
		nullMsisdnRegularized.setMsisdn(request.getMsisdn());
		result.getNullMsisdnRegularizeds().add(nullMsisdnRegularized);

	}

	private void addToDeviceSnapShot(Request request) {
		DeviceSnapShot deviceSnapShot = null;
		try {

			deviceSnapShot = deviceSnapShotService.get(request.getImei());
			if (deviceSnapShot == null) {
				result.getDeviceSnapshotBatch().add(convertRequestToDeviceSnapShot(request));
			} else {
				DuplicateImeiMsisdn duplicateImeiMsisdn = convertToDuplicateImeiMsisdn(request);
				duplicateImeiMsisdn.setDeviceSnapShot(deviceSnapShot);
				deviceSnapShot.getDuplicateImeiMsisdns().add(duplicateImeiMsisdn);
				result.getDeviceSnapshotBatch().add(deviceSnapShot);
			}

		} catch (com.gl.ceir.config.exceptions.ResourceNotFoundException e) {
			result.getDeviceSnapshotBatch().add(convertRequestToDeviceSnapShot(request));
		}
	}

	private PendingActions convertRequestToPendingActions(Request request) {
		PendingActions pendingActions = new PendingActions();
		pendingActions.setTicketId(ticketIdGenerator.getTicketId());
		pendingActions.setImei(request.getImei());
		pendingActions.setMsisdn(request.getMsisdn());
		pendingActions.setFailedRule(request.getFailRule());
		pendingActions.setAction(request.getAction());
		return pendingActions;

	}

	private DeviceSnapShot convertRequestToDeviceSnapShot(Request request) {
		DeviceSnapShot deviceSnapShot = new DeviceSnapShot();
		deviceSnapShot.setAction(request.getActionNames().toString());
		deviceSnapShot.setMobileOperatorId(1L);
		deviceSnapShot.setImei(request.getImei());
		deviceSnapShot.setMsisdn(request.getMsisdn());
		if (request.getActionNames() == ActionNames.USER_REGULARIZED)
			deviceSnapShot.setImeiStatus(ImeiStatus.PENDING_TO_REGUARIZED);
		else if (request.getActionNames() == ActionNames.SYSTEM_REGULARIZED)
			deviceSnapShot.setImeiStatus(ImeiStatus.SYSTEM_REGULARIZED);
		else if (request.getActionNames() == ActionNames.AUTO_REGULARIZED)
			deviceSnapShot.setImeiStatus(ImeiStatus.AUTO_REGULARIZED);
		// deviceSnapShot.setFailedRuleId(request.getFailRule().getId().toString());
		// deviceSnapShot.setFailedRuleName(request.getFailRule().getName());
		deviceSnapShot.setDuplicateImeiMsisdns(new ArrayList<>());
		DuplicateImeiMsisdn duplicateImeiMsisdn = convertToDuplicateImeiMsisdn(request);
		duplicateImeiMsisdn.setDeviceSnapShot(deviceSnapShot);
		deviceSnapShot.getDuplicateImeiMsisdns().add(duplicateImeiMsisdn);
		return deviceSnapShot;
	}

	private DuplicateImeiMsisdn convertToDuplicateImeiMsisdn(Request request) {
		DuplicateImeiMsisdn duplicateImeiMsisdn = new DuplicateImeiMsisdn();
		duplicateImeiMsisdn.setImeiMsisdnIdentity(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
		duplicateImeiMsisdn.setFileName(request.getFilename());
		if (request.getActionNames() == ActionNames.USER_REGULARIZED)
			duplicateImeiMsisdn.setImeiStatus(ImeiStatus.PENDING_TO_REGUARIZED);
		else if (request.getActionNames() == ActionNames.SYSTEM_REGULARIZED)
			duplicateImeiMsisdn.setImeiStatus(ImeiStatus.SYSTEM_REGULARIZED);
		else if (request.getActionNames() == ActionNames.AUTO_REGULARIZED)
			duplicateImeiMsisdn.setImeiStatus(ImeiStatus.AUTO_REGULARIZED);
		duplicateImeiMsisdn.setImsi(request.getImsi());
		duplicateImeiMsisdn.setRegulizedByUser(Boolean.FALSE);
		return duplicateImeiMsisdn;

	}

}
