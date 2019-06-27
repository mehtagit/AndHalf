package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.model.constants.RuleOperator;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;

@Service
public class EqualsToRuleSolver implements RuleSolver {

	private Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@Override
	public boolean solve(Rules rule, Request request) {
		boolean result = false;
		try {
			logger.info("RuleSolver going to solve " + rule.getName());
			switch (rule.getParameters()) {
			case IMEI:

				if (request.getImei() == null || request.getImei().longValue() == Integer.valueOf(rule.getMin()))
					result = true;

				break;
			case IMEI_LENGTH:

				if (String.valueOf(request.getImei().longValue()).length() == Integer.valueOf(rule.getMin()))
					result = true;

				break;
			case IMEI_MSISDN:
				logger.warn("IMEI_MSISDN, EqualToRuleSolver not available");
				break;
			case MSISDN:
				logger.warn("MSISDN, EqualToRuleSolver not available");
				break;
			case IMEI_COUNT:
				logger.warn("IMEI_COUNT, EqualToRuleSolver not available");
				break;

			case IMEI_STATUS:
				logger.warn("IMEI_STATUS, EqualToRuleSolver not available");
				break;
			case IMEI_TAX:
				DeviceSnapShot deviceSnapShot = deviceSnapShotService.getByImeiAndMsisdn(request.getImei(),
						request.getMsisdn());
				if (Boolean.valueOf(rule.getMin()) == deviceSnapShot.isTaxPaid())
					result = true;

				break;
			}
			result = (rule.getOperator() == RuleOperator.EQUAL_TO ? result : !result);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
