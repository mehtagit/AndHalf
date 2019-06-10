package com.gl.ceir.evaluator.services.impl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.controller.BlackListController;
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
	private static final Logger logger = LogManager.getLogger(PolicyEvaluator.class);

	@Autowired
	private InputRepository inputRepository;

	@Autowired
	private Executor executor;

	@Autowired
	private Chain startPolicyEvaluator;

	public CountDownLatch fileCountDownLatch;

	@Autowired
	public PolicyEvaluator() {
	}

	public CountDownLatch getFileCountDownLatch() {
		return fileCountDownLatch;
	}

	private boolean start;

	public void setStart(boolean start) {
		this.start = start;
	}

	public void run() {
		while (true) {
			try {
				while (start) {
					List<Request> requests = inputRepository.read();

					if (requests == null) {
						continue;
					} else {
						start = false;
						fileCountDownLatch = new CountDownLatch(requests.size());
						for (Request request : requests) {
							executor.execute(() -> {
								startPolicyEvaluator.process(request);
							});
						}

						fileCountDownLatch.countDown();
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

}
