package com.gl.ceir.evaluator.services;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.repository.BlackListRepository;
import com.gl.ceir.config.system.request.Request;

public interface RuleSolver {

	public boolean solve(Rules rule, Request request);
}

class InRuleSolver implements RuleSolver {

	private BlackListRepository blackListRepository;

	@Override
	public boolean solve(Rules rule, Request request) {

	}

}
