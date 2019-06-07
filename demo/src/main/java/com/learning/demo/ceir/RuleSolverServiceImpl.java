package com.learning.demo.ceir;

import java.util.List;

public class RuleSolverServiceImpl implements RuleSolverService {

	private List<Rule> ruleList;

	@Override
	public Rule checkFailedRule(Request request) {
		Rule failedRule = null;
		for (Rule rule : ruleList) {

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
