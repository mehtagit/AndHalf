package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gl.ceir.config.model.constants.RuleOperator;
import com.gl.ceir.evaluator.config.Container;
import com.gl.ceir.evaluator.services.RuleSolver;

public class RuleSolverFactory {

	private static Logger logger = LogManager.getLogger(RuleSolverFactory.class);

	public static RuleSolver getRuleSolver(RuleOperator ruleOperator) {
		RuleSolver ruleSolver = null;
		switch (ruleOperator) {
		case BETWEEN:
			logger.warn("BETWEEN, RuleSolver is not available");
			break;
		case EQUAL_TO:
			ruleSolver = Container.context.getBean(EqualsToRuleSolver.class);
			break;
		case GREATER_THEN:
			ruleSolver = Container.context.getBean(GreaterThanRuleSolver.class);
			break;
		case GRETER_THEN_EQUALS_TO:
			logger.warn("GRETER_THEN_EQUALS_TO, RuleSolver is not available");
			break;
		case IN:
			ruleSolver = Container.context.getBean(InRuleSolver.class);
			break;
		case LESS_THEN:
			logger.warn("LESS_THEN, RuleSolver is not available");
			break;
		case LESS_THEN_EQUALS_TO:
			logger.warn("LESS_THEN_EQUALS_TO, RuleSolver is not available");
			break;
		case NOT_EQUAL_TO:
			ruleSolver = Container.context.getBean(EqualsToRuleSolver.class);
			break;
		case NOT_IN:
			logger.warn("NOT_IN, RuleSolver is not available");
			break;
		default:
			break;

		}
		return ruleSolver;

	}
}
