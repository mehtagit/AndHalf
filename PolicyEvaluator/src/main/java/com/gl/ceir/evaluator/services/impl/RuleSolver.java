package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.Step;
import com.gl.ceir.evaluator.services.RuleSolverService;

@Service("ruleSolverStep")
public class RuleSolver implements Step {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Qualifier("resultWritter")
	@Autowired
	private Step resultWritter;

	@Autowired
	private RuleSolverService ruleSolverService;

	@Override
	public void process(Request request) {
		logger.info("Rule solver Started solved for " + request);
		Rules rule = ruleSolverService.checkFailedRule(request);
		request.setFailRule(rule);
		logger.info("Rule solver solved for " + request);
		resultWritter.process(request);
	}

}
