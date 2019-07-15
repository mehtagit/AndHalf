package com.gl.ceir.evaluator.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.gl.ceir.config.model.constants.Period;
import com.gl.ceir.config.model.constants.TransactionState;
import com.gl.ceir.config.repository.DeviceSnapShotRepository;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.TicketIdGenerator;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.config.PolicyEvaluatorConfig;
import com.gl.ceir.evaluator.pojo.Result;
import com.gl.ceir.evaluator.services.InputRepository;
import com.gl.ceir.evaluator.services.OutpuWriter;

@Service
public class OutputWriterImpl implements OutpuWriter {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private DuplicateImeiProcessor duplicateImeiProcessor;

	@Autowired
	private Result result;

	@Autowired
	private TicketIdGenerator ticketIdGenerator;

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@Autowired
	private DeviceSnapShotRepository deviceSnapShotRepository;

	@Autowired
	private PolicyEvaluatorConfig policyEvaluatorConfig;

	@Autowired
	private InputRepository inputRepository;

	@Override
	public boolean write(Request request) {
		return write(request, request.getActionNames());
	}

	@Override
	public boolean write(Request request, ActionNames actionNames) {

		try {
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
			logger.info("CountDown:" + inputRepository.getFileCountDownLatch().getCount() + ", " + request);
			inputRepository.getFileCountDownLatch().countDown();

			List<Request> list = inputRepository.getRequests().remove(request.getImei());
			if (list != null && list.size() > 1) {
				for (int i = 1; i < list.size(); i++) {
					try {
						duplicateImeiProcessor.getQueue().put(list.get(i));
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
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
		} catch (org.springframework.data.redis.RedisConnectionFailureException e) {
			deviceSnapShot = deviceSnapShotRepository.findById(request.getImei()).orElse(null);
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
		}
	}

	private PendingActions convertRequestToPendingActions(Request request) {
		PendingActions pendingActions = new PendingActions();
		pendingActions.setTicketId(ticketIdGenerator.getTicketId());
		pendingActions.setImei(request.getImei());
		pendingActions.setMsisdn(request.getMsisdn());
		if (request.getFailRule() != null) {
			pendingActions.setFailedRuleId(request.getFailRule().getId());
			pendingActions.setFailedRuleName(request.getFailRule().getName());
		}
		pendingActions.setAction(request.getAction());
		pendingActions.setTransactionState(TransactionState.INIT);
		pendingActions.setCreatedOn(new Date());
		// pendingActions.setEndDateforUserAction(endDateforUserAction);
		pendingActions.setFilename(request.getFilename());
		pendingActions.setImsi(request.getImsi());
		// pendingActions.setMobileOperatorId(request.getMobileOperatorId());
		pendingActions.setModifiedOn(new Date());
		pendingActions.setMobileOperator(request.getMobileOperator());
		return pendingActions;

	}

	private DeviceSnapShot convertRequestToDeviceSnapShot(Request request, boolean pending) {
		DeviceSnapShot deviceSnapShot = new DeviceSnapShot();
		deviceSnapShot.setImei(request.getImei());

		if (request.getFailRule() != null) {
			deviceSnapShot.setLastpPolicyBreached(request.getFailRule().getId().intValue());
		}
		deviceSnapShot.setLastpPolicyBreachedDate(new Date());

		deviceSnapShot.setPeriod(Period.getPeriod(policyEvaluatorConfig.getPeriod()));

		deviceSnapShot.setDuplicateImeiMsisdns(new ArrayList<>());
		DuplicateImeiMsisdn duplicateImeiMsisdn = convertToDuplicateImeiMsisdn(request);
		duplicateImeiMsisdn.setDeviceSnapShot(deviceSnapShot);
		deviceSnapShot.getDuplicateImeiMsisdns().add(duplicateImeiMsisdn);
		deviceSnapShot.setPending(pending);
		deviceSnapShot.setMobileOperator(request.getMobileOperator());
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

		if (request.getFailRule() != null) {
			duplicateImeiMsisdn.setFailedRuleId(request.getFailRule().getId());
			duplicateImeiMsisdn.setFailedRuleName(request.getFailRule().getName());
		}
		duplicateImeiMsisdn.setMobileOperator(request.getMobileOperator());

		// MobileOperator mobileOperator = new MobileOperator();
		// mobileOperator.setId(request.getMobileOperatorId());
		// duplicateImeiMsisdn.setMobileOperator(mobileOperator);
		return duplicateImeiMsisdn;

	}

}
