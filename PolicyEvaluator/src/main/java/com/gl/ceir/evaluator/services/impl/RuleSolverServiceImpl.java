package com.gl.ceir.evaluator.services.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.SystemPolicyMapping;
import com.gl.ceir.config.model.constants.Period;
import com.gl.ceir.config.service.RulesService;
import com.gl.ceir.config.service.SystemPolicyMappingService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.config.PolicyEvaluatorConfig;
import com.gl.ceir.evaluator.services.RuleSolver;
import com.gl.ceir.evaluator.services.RuleSolverService;

@Service
public class RuleSolverServiceImpl implements RuleSolverService {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private RulesService rulesService;

	@Autowired
	private SystemPolicyMappingService systemPolicyMappingService;

	private List<Rules> ruleList;

	private List<SystemPolicyMapping> policyMappingList;

	@Autowired
	private PolicyEvaluatorConfig policyEvaluatorConfig;

	@PostConstruct
	public void readRules() {
		ruleList = rulesService.getAll();
		logger.info(ruleList);

		policyMappingList = systemPolicyMappingService
				.getSystemPoliciesByPeriod(Period.getPeriod(policyEvaluatorConfig.getPeriod()));
		logger.info(policyMappingList);
	}

	@Override
	public Rules checkFailedRule(Request request) {
		Rules failedRule = null;
		for (SystemPolicyMapping systemPolicyMapping : policyMappingList) {
			Rules rule = systemPolicyMapping.getRule();
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
