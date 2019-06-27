package com.gl.ceir.evaluator.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.SystemPolicyMapping;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.model.constants.Period;
import com.gl.ceir.config.service.SystemPolicyMappingService;
import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.config.PolicyEvaluatorConfig;
import com.gl.ceir.evaluator.services.Step;
import com.gl.ceir.evaluator.services.OutpuWriter;

@Service("resultWritter")
public class ResultWritter implements Step {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private OutpuWriter outpuWriter;

	@Autowired
	private SystemPolicyMappingService systemPolicyMappingService;

	@Autowired
	private PolicyEvaluatorConfig policyEvaluatorConfig;

	@Override
	public void process(Request request) {
		logger.info("ResultWritter " + request);

		if (request.getFailRule() == null) {
			request.setActionNames(ActionNames.AUTO_REGULARIZED);
			outpuWriter.write(request);
		} else {
			SystemPolicyMapping systemPolicyMapping = systemPolicyMappingService
					.getSystemPolicies(request.getFailRule(), Period.getPeriod(policyEvaluatorConfig.getPeriod()));
			request.setActionNames(systemPolicyMapping.getAction().getName());
			request.setAction(systemPolicyMapping.getAction());
			outpuWriter.write(request);

		}

	}

}
