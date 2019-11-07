package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.constants.RulesNames;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;
import com.gl.ceir.evaluator.services.SolveRules;

@Service
public class SolveRulesImpl implements SolveRules {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private InMemoryLoader inMemoryLoader;

	@Override
	public boolean solve(RulesNames rulesNames, Request request) {

		if (rulesNames == null) {
			logger.info("RulesNames :" + rulesNames + " Not Found, " + request);
			return false;
		}
		Rules rule = inMemoryLoader.getRuleByName(rulesNames);

		RuleSolver ruleSolver = RuleSolverFactory.getRuleSolver(rule.getOperator());

		return !ruleSolver.solve(rule, request);

	}

	@Override
	public boolean solve(Long ruleId, Request request) {
		Rules rule = inMemoryLoader.getRuleById(ruleId);

		if (rule == null) {
			logger.info("Rule Id :" + ruleId + " Not Found, " + request);
			return false;
		}

		RuleSolver ruleSolver = RuleSolverFactory.getRuleSolver(rule.getOperator());

		return !ruleSolver.solve(rule, request);
	}

}
