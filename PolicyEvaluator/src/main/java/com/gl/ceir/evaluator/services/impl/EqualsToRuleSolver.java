package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.model.constants.RuleOperator;
import com.gl.ceir.config.repository.DeviceSnapShotRepository;
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
			logger.debug("RuleSolver " + rule + ", " + request);
			switch (rule.getParameters()) {
			case IMEI:

				if (request.getImei() == null || request.getImei().longValue() == Integer.valueOf(rule.getMin()))
					result = true;

				break;
			case IMEI_LENGTH:
				logger.warn("IMEI_LENGTH, EqualToRuleSolver not available");
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
				try {
					DeviceSnapShot deviceSnapShot = deviceSnapShotService.get(request.getImei());

					if (Boolean.valueOf(rule.getMin()) == deviceSnapShot.isTaxPaid())
						result = true;
				} catch (com.gl.ceir.config.exceptions.ResourceNotFoundException e) {
					result = false;
				}

				break;
			case TAC:
				break;
			default:
				break;
			}
			result = (rule.getOperator() == RuleOperator.EQUAL_TO ? result : !result);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

}