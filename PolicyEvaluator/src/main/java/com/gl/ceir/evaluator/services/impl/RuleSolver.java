package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.Chain;
import com.gl.ceir.evaluator.services.RuleSolverService;

public class RuleSolver implements Chain {

	private static final Logger logger = LogManager.getLogger(RuleSolver.class);

	private Chain nextInChain;

	@Autowired
	private RuleSolverService ruleSolverService;

	@Override
	public void setNext(Chain nextInChain) {
		this.nextInChain = nextInChain;
	}

	@Override
	public void process(Request request) {
		Rules rule = ruleSolverService.checkFailedRule(request);
		request.setFailRule(rule);
		logger.info("Rule solver solved for " + request);
		nextInChain.process(request);
	}

}
