package com.gl.ceir.evaluator.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gl.ceir.config.model.SystemPolicyMapping;
import com.gl.ceir.config.service.SystemPolicyMappingService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.config.AppConfig;
import com.gl.ceir.evaluator.services.Chain;
import com.gl.ceir.evaluator.services.OutpuWriter;

public class ResultWritter implements Chain {

	private static final Logger logger = LogManager.getLogger(ResultWritter.class);

	@Autowired
	private PolicyEvaluator policyEvaluator;
	@Autowired
	private OutpuWriter outpuWriter;

	@Autowired
	private SystemPolicyMappingService systemPolicyMappingService;

	@Autowired
	private AppConfig appConfig;

	private Chain nextInChain;

	@Override
	public void setNext(Chain nextInChain) {
		this.nextInChain = nextInChain;
	}

	@Override
	public void process(Request request) {
		logger.info("ResultWritter " + request);
		policyEvaluator.getFileCountDownLatch().countDown();
		List<SystemPolicyMapping> policyList = systemPolicyMappingService.getSystemPolicies(request.getFailRule());

		for (SystemPolicyMapping systemPolicyMapping : policyList) {
			if (systemPolicyMapping.getPeriod() == appConfig.getPeriod()) {
				outpuWriter.write(request, systemPolicyMapping.getAction().getName());
			}
		}

	}

}
