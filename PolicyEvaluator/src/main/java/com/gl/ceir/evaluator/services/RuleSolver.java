package com.gl.ceir.evaluator.services;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.system.request.Request;

public interface RuleSolver {

	public boolean solve(Rules rule, Request request);

}
