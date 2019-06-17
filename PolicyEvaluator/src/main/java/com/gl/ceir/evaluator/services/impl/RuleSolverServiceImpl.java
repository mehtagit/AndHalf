package com.gl.ceir.evaluator.services.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.service.RulesService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.RuleSolver;
import com.gl.ceir.evaluator.services.RuleSolverService;

@Service
public class RuleSolverServiceImpl implements RuleSolverService {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private RulesService rulesService;

	private List<Rules> ruleList;

	@PostConstruct
	public void readRules() {
		ruleList = rulesService.getAll();
		logger.info(ruleList);
	}

	@Override
	public Rules checkFailedRule(Request request) {
		Rules failedRule = null;
		for (Rules rule : ruleList) {

			RuleSolver ruleSolver = RuleSolverFactory.getRuleSolver(rule.getOperator());

			if (!ruleSolver.solve(rule, request)) {
				logger.info(rule.getName() + " Resolved and True");
				continue;
			} else {
				logger.info(rule.getName() + " Resolved and Failed");
				failedRule = rule;
				break;
			}
		}
		return failedRule;
	}

}
