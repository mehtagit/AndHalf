package com.gl.ceir.evaluator.services.impl;

import java.util.List;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;
import com.gl.ceir.evaluator.services.RuleSolverService;

public class RuleSolverServiceImpl implements RuleSolverService {

	private List<Rules> ruleList;

	@Override
	public Rules checkFailedRule(Request request) {
		Rules failedRule = null;
		for (Rules rule : ruleList) {

			RuleSolver ruleSolver = RuleSolverFactory.getRuleSolver(rule);

			if (ruleSolver.solve(rule, request)) {
				continue;
			} else {
				failedRule = rule;
				break;
			}
		}
		return failedRule;
	}

}
