package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.service.BlackListService;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.config.service.VipListService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;

@Service
public class InRuleSolver implements RuleSolver {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private BlackListService blackListService;

	@Autowired
	private VipListService vipListService;

	@Autowired
	private PendingActionsService pendingActionsService;

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@Override
	public boolean solve(Rules rule, Request request) {
		boolean result = false;
		try {
			logger.info("RuleSolver going to solve " + rule.getName());

			switch (rule.getParameters()) {
			case IMEI:
				logger.warn("IMEI, InRuleSolver not available");
				break;
			case IMEI_LENGTH:
				logger.warn("IMEI_LENGTH, InRuleSolver not available");
				break;
			case IMEI_MSISDN:
				if ("VIP".equals(rule.getMin())) {
					VipList vipList = vipListService
							.getByMsisdnAndImei(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
					result = vipList == null ? false : true;
				} else if ("BLACK".equals(rule.getMin())) {
					BlackList blackList = blackListService
							.getByMsisdnAndImei(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
					result = blackList == null ? false : true;
				} else if ("PENDING".equals(rule.getMin())) {
					PendingActions pendingActions = pendingActionsService
							.getByMsisdnAndImei(new ImeiMsisdnIdentity(request.getImei(), request.getMsisdn()));
					result = pendingActions == null ? false : true;
				}
				break;
			case MSISDN:
				logger.warn("MSISDN, InRuleSolver not available");
				break;
			case IMEI_COUNT:
				logger.warn("IMEI_COUNT, InRuleSolver not available");

				break;
			case IMEI_STATUS:
				DeviceSnapShot deviceSnapShot = deviceSnapShotService.get(request.getImei());
				if (deviceSnapShot == null)
					result = false;
				else {
					for (String status : rule.getMin().split(",")) {
						if (deviceSnapShot.getImeiStatus() == ImeiStatus.getImeiStatus(status)) {
							result = true;
							break;
						}
					}
				}
				break;
			}

			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
