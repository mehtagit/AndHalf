package com.gl.ceir.evaluator.services;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.system.request.Request;

public interface RuleSolverService {
	public Rules checkFailedRule(Request request);
}
