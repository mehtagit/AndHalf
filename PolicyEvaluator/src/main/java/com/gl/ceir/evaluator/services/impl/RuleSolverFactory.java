package com.gl.ceir.evaluator.services.impl;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.evaluator.config.Container;
import com.gl.ceir.evaluator.services.RuleSolver;

public class RuleSolverFactory {

	public static RuleSolver getRuleSolver(Rules rule) {
		RuleSolver ruleSolver = null;
		switch (rule.getOperator()) {
		case BETWEEN:
			break;
		case EQUAL_TO:
			break;
		case GREATER_THEN:
			break;
		case GRETER_THEN_EQUALS_TO:
			break;
		case IN:
			ruleSolver = Container.context.getBean(InRuleSolver.class);
			break;
		case LESS_THEN:
			break;
		case LESS_THEN_EQUALS_TO:
			break;
		case NOT_EQUALS:
			break;
		case NOT_IN:
			break;
		default:
			break;

		}
		return ruleSolver;

	}
}
