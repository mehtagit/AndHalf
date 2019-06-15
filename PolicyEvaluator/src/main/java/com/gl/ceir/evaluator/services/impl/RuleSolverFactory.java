package com.gl.ceir.evaluator.services.impl;

import com.gl.ceir.config.model.constants.RuleOperator;
import com.gl.ceir.evaluator.config.Container;
import com.gl.ceir.evaluator.services.RuleSolver;

public class RuleSolverFactory {

	public static RuleSolver getRuleSolver(RuleOperator ruleOperator) {
		RuleSolver ruleSolver = null;
//		switch (ruleOperator) {
//		case BETWEEN:
//			break;
//		case EQUAL_TO:
//			break;
//		case GREATER_THEN:
//			break;
//		case GRETER_THEN_EQUALS_TO:
//			break;
//		case IN:
//			ruleSolver = Container.context.getBean(InRuleSolver.class);
//			break;
//		case LESS_THEN:
//			break;
//		case LESS_THEN_EQUALS_TO:
//			break;
//		case NOT_EQUALS:
//			break;
//		case NOT_IN:
//			break;
//		default:
//			break;
//
//		}
		ruleSolver = Container.context.getBean(InRuleSolver.class);
		return ruleSolver;

	}
}
