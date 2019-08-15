package com.gl.ceir.evaluator.services;

import com.gl.ceir.config.model.constants.RulesNames;
import com.gl.ceir.config.system.request.Request;

public interface SolveRules {
	public boolean solve(RulesNames rulesNames, Request request);

	public boolean solve(Long ruleId, Request request);
}
