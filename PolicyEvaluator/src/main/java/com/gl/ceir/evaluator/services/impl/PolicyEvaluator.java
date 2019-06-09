package com.gl.ceir.evaluator.services.impl;

import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.Chain;
import com.gl.ceir.evaluator.services.InputRepository;
import com.gl.ceir.evaluator.services.OutpuWriter;
import com.gl.ceir.evaluator.services.RuleSolverService;

@Component
@Scope("PROTOTYPE")
public class PolicyEvaluator {

	@Autowired
	private InputRepository inputRepository;

	@Autowired
	private Executor executor;

	@Autowired
	private Chain startPolicyEvaluator;

	@Autowired
	public PolicyEvaluator() {
	}

	public void run() {
		while (true) {
			List<Request> requests = inputRepository.read();

			if (requests == null) {
				continue;
			} else {
				for (Request request : requests) {
					executor.execute(() -> {
						startPolicyEvaluator.process(request);
					});
				}
			}
		}
	}

}
