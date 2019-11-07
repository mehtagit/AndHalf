package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;

@Service
public class BetweenRuleSolver implements RuleSolver {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public boolean solve(Rules rule, Request request) {
		boolean result = false;
		try {
			logger.debug("RuleSolver " + rule + ", " + request);
			switch (rule.getParameters()) {
			case IMEI:
				logger.warn("IMEI, InRuleSolver not available");
				break;
			case IMEI_LENGTH:
				int imeiLength = String.valueOf(request.getImei().longValue()).length();
				if (imeiLength >= Integer.valueOf(rule.getMin()) && imeiLength <= Integer.valueOf(rule.getMax()))
					result = false;
				else
					result = true;
				break;
			case IMEI_MSISDN:
				logger.warn("IMEI_MSISDN, InRuleSolver not available");
				break;
			case MSISDN:
				logger.warn("MSISDN, InRuleSolver not available");
				break;
			case IMEI_COUNT:
				logger.warn("IMEI_COUNT, InRuleSolver not available");

				break;
			case IMEI_STATUS:
				logger.warn("IMEI_STATUS, InRuleSolver not available");
				break;
			case IMEI_TAX:
				logger.warn("IMEI_TAX, InRuleSolver not available");
				break;
			case TAC:
				logger.warn("TAC, InRuleSolver not available");
				break;
			default:
				break;
			}

			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
