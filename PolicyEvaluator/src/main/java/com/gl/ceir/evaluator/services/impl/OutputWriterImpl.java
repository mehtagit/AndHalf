package com.gl.ceir.evaluator.services.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.model.NullMsisdnRegularized;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.model.constants.Period;
import com.gl.ceir.config.model.constants.TransactionState;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;
import com.gl.ceir.config.service.TicketIdGenerator;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.config.PolicyEvaluatorConfig;
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
	private PolicyEvaluatorConfig policyEvaluatorConfig;

	@Override
	public boolean write(Request request) {
		return write(request, request.getActionNames());
	}

	@Override
	public boolean write(Request request, ActionNames actionNames) {

		switch (actionNames) {
		case AUTO_REGULARIZED:
			addToDeviceSnapShot(request, false);
			break;
		case SYSTEM_REGULARIZED:
			if (request.getImei() == 0) {
				addToNullReqularised(request);
			} else {
				result.insertPendingActions(convertRequestToPendingActions(request));
				// result.getPendingBatch().add(convertRequestToPendingActions(request));
				addToDeviceSnapShot(request, true);
			}
			break;
		case USER_REGULARIZED:
			result.insertPendingActions(convertRequestToPendingActions(request));
			// result.getPendingBatch().add(convertRequestToPendingActions(request));
			addToDeviceSnapShot(request, true);
			break;
		case NO_ACTION:
			// logger.info(request);
			break;
		}
		logger.info("CountDown:" + policyEvaluator.getFileCountDownLatch().getCount() + ", " + request);
		policyEvaluator.getFileCountDownLatch().countDown();
		return false;
	}

	private void addToNullReqularised(Request request) {
		NullMsisdnRegularized nullMsisdnRegularized = new NullMsisdnRegularized();
		nullMsisdnRegularized.setMsisdn(request.getMsisdn());
		result.insertNullMsisdnRegularized(nullMsisdnRegularized);
		// result.getNullMsisdnRegularizeds().add(nullMsisdnRegularized);

	}

	private void addToDeviceSnapShot(Request request, boolean pending) {
		DeviceSnapShot deviceSnapShot = null;
		try {

			deviceSnapShot = deviceSnapShotService.get(request.getImei());
			if (deviceSnapShot == null) {
				result.insertDeviceSnapShot(convertRequestToDeviceSnapShot(request, pending));
				// result.getDeviceSnapshotBatch().add(convertRequestToDeviceSnapShot(request,
				// pending));
			} else {
				DuplicateImeiMsisdn duplicateImeiMsisdn = convertToDuplicateImeiMsisdn(request);
				duplicateImeiMsisdn.setDeviceSnapShot(deviceSnapShot);
				deviceSnapShot.getDuplicateImeiMsisdns().add(duplicateImeiMsisdn);
				result.insertDeviceSnapShot(deviceSnapShot);
				// result.getDeviceSnapshotBatch().add(deviceSnapShot);
			}

		} catch (com.gl.ceir.config.exceptions.ResourceNotFoundException e) {
			result.insertDeviceSnapShot(convertRequestToDeviceSnapShot(request, pending));
			// result.getDeviceSnapshotBatch().add(convertRequestToDeviceSnapShot(request,
			// pending));
		}
	}

	private PendingActions convertRequestToPendingActions(Request request) {
		PendingActions pendingActions = new PendingActions();
		pendingActions.setTicketId(ticketIdGenerator.getTicketId());
		pendingActions.setImei(request.getImei());
		pendingActions.setMsisdn(request.getMsisdn());
		pendingActions.setFailedRule(request.getFailRule());
		pendingActions.setAction(request.getAction());
		pendingActions.setTransactionState(TransactionState.INIT);
		pendingActions.setCreatedOn(new Date());
		// pendingActions.setEndDateforUserAction(endDateforUserAction);
		pendingActions.setFilename(request.getFilename());
		pendingActions.setImsi(request.getImsi());
		// pendingActions.setMobileOperatorId(request.getMobileOperatorId());
		pendingActions.setModifiedOn(new Date());
		return pendingActions;

	}

	private DeviceSnapShot convertRequestToDeviceSnapShot(Request request, boolean pending) {
		DeviceSnapShot deviceSnapShot = new DeviceSnapShot();
		deviceSnapShot.setAction(request.getActionNames().toString());
		// deviceSnapShot.setMobileOperatorId(1L);
		deviceSnapShot.setImei(request.getImei());
		deviceSnapShot.setMsisdn(request.getMsisdn());

		if (request.getFailRule() != null) {
			deviceSnapShot.setFailedRuleId(request.getFailRule().getId().toString());
			deviceSnapShot.setLastpPolicyBreached(request.getFailRule().getId().intValue());
			deviceSnapShot.setFailedRuleName(request.getFailRule().getName());
		}
		deviceSnapShot.setImsi(request.getImsi());
		deviceSnapShot.setLastpPolicyBreachedDate(new Date());

		deviceSnapShot.setPeriod(Period.getPeriod(policyEvaluatorConfig.getPeriod()));

		if (request.getActionNames() == ActionNames.USER_REGULARIZED)
			deviceSnapShot.setImeiStatus(ImeiStatus.USER_REGULARIZED);
		else if (request.getActionNames() == ActionNames.SYSTEM_REGULARIZED)
			deviceSnapShot.setImeiStatus(ImeiStatus.SYSTEM_REGULARIZED);
		else if (request.getActionNames() == ActionNames.AUTO_REGULARIZED)
			deviceSnapShot.setImeiStatus(ImeiStatus.AUTO_REGULARIZED);
		deviceSnapShot.setDuplicateImeiMsisdns(new ArrayList<>());
		DuplicateImeiMsisdn duplicateImeiMsisdn = convertToDuplicateImeiMsisdn(request);
		duplicateImeiMsisdn.setDeviceSnapShot(deviceSnapShot);
		deviceSnapShot.getDuplicateImeiMsisdns().add(duplicateImeiMsisdn);
		deviceSnapShot.setPending(pending);
		return deviceSnapShot;
	}

	private DuplicateImeiMsisdn convertToDuplicateImeiMsisdn(Request request) {
		DuplicateImeiMsisdn duplicateImeiMsisdn = new DuplicateImeiMsisdn();
		duplicateImeiMsisdn.setImeiMsisdnIdentity(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
		duplicateImeiMsisdn.setFileName(request.getFilename());
		if (request.getActionNames() == ActionNames.USER_REGULARIZED)
			duplicateImeiMsisdn.setImeiStatus(ImeiStatus.USER_REGULARIZED);
		else if (request.getActionNames() == ActionNames.SYSTEM_REGULARIZED)
			duplicateImeiMsisdn.setImeiStatus(ImeiStatus.SYSTEM_REGULARIZED);
		else if (request.getActionNames() == ActionNames.AUTO_REGULARIZED)
			duplicateImeiMsisdn.setImeiStatus(ImeiStatus.AUTO_REGULARIZED);
		duplicateImeiMsisdn.setImsi(request.getImsi());
		duplicateImeiMsisdn.setRegulizedByUser(Boolean.FALSE);
		duplicateImeiMsisdn.setCreatedOn(new Date());

		// MobileOperator mobileOperator = new MobileOperator();
		// mobileOperator.setId(request.getMobileOperatorId());
		// duplicateImeiMsisdn.setMobileOperator(mobileOperator);
		return duplicateImeiMsisdn;

	}

}
