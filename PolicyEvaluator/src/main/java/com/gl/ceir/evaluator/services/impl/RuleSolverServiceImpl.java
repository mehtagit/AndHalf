package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.SystemPolicyMapping;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;
import com.gl.ceir.evaluator.services.RuleSolverService;

@Service
public class RuleSolverServiceImpl implements RuleSolverService {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private InMemoryLoader inMemoryLoader;

	@Override
	public Rules checkFailedRule(Request request) {
		Rules failedRule = null;
		for (SystemPolicyMapping systemPolicyMapping : inMemoryLoader.getPolicyMappingList()) {
			Rules rule = systemPolicyMapping.getRule();
			RuleSolver ruleSolver = RuleSolverFactory.getRuleSolver(rule.getOperator());

			if (!ruleSolver.solve(rule, request)) {
				logger.info("RuleSolver " + rule.getName() + ":Continue, " + request);
				continue;
			} else {
				logger.info("RuleSolver " + rule.getName() + ":Break, " + request);
				failedRule = rule;
				request.setScriptId(systemPolicyMapping.getScript().getId());
				break;
			}
		}
		return failedRule;
	}

}
