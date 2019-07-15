package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.model.DuplicateImeiMsisdn;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.NullMsisdnRegularized;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.model.constants.ImeiStatus;
import com.gl.ceir.config.service.BlackListService;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.DuplicateImeiMsisdnService;
import com.gl.ceir.config.service.NullMsisdnRegularizedService;
import com.gl.ceir.config.service.PendingActionsService;
import com.gl.ceir.config.service.VipListService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;

@Service
public class LuhnRuleSolver implements RuleSolver {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public boolean solve(Rules rule, Request request) {
		boolean result = false;
		try {
			logger.debug("RuleSolver " + rule + ", " + request);

			switch (rule.getParameters()) {
			case IMEI:
				int imeiLength = String.valueOf(request.getImei().longValue()).length();
				if (imeiLength == 16)
					result = false;
				else
					result = check(request.getImei());
				break;
			case IMEI_LENGTH:
				logger.warn("IMEI_LENGTH, InRuleSolver not available");
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
			default:
				break;
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	private boolean check(Long number) {
		boolean result = false;
		int modSum = 0;
		String input = String.valueOf(number);
		int lengthOfNumber = input.length();
		logger.info("Lung Checking for number:" + number + ", length = " + lengthOfNumber);

		for (int i = lengthOfNumber - 1; i >= 0; i--) {
			if (i % 2 != 0) {
				int doubleIt = (Integer.parseInt(String.valueOf(input.charAt(i)))) * 2;
				doubleIt = getSum(doubleIt);
				modSum = modSum + doubleIt;
			} else {
				modSum = modSum + Integer.parseInt(String.valueOf(input.charAt(i)));
			}
		}
		if (modSum % 10 == 0)
			result = true;
		return !result;
	}

	private int getSum(int input) {
		int ones = (input / 1) % 10;
		int tens = (input / 10) % 10;

		int sum = ones + tens;
		if (sum > 9) {
			return getSum(sum);
		} else {
			return sum;
		}
	}

}
