package com.gl.ceir.evaluator.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.SystemPolicyMapping;
import com.gl.ceir.config.model.constants.ActionNames;
import com.gl.ceir.config.model.constants.ActionParametersName;
import com.gl.ceir.config.model.constants.Period;
import com.gl.ceir.config.service.ActionParametersService;
import com.gl.ceir.config.service.RulesService;
import com.gl.ceir.config.service.SystemPolicyMappingService;
import com.gl.ceir.evaluator.config.PolicyEvaluatorConfig;

@Service
public class InMemoryLoader {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ActionParametersService actionParametersService;

	@Autowired
	private SystemPolicyMappingService systemPolicyMappingService;

	@Autowired
	private RulesService rulesService;

	@Autowired
	private PolicyEvaluatorConfig policyEvaluatorConfig;

	private List<SystemPolicyMapping> policyMappingList;

	private Map<ActionNames, Map<ActionParametersName, String>> mapOfActionParameters;

	private List<Rules> ruleList;

	@PostConstruct
	public void readRules() {
		ruleList = rulesService.getAll();
		logger.info(ruleList);

		policyMappingList = systemPolicyMappingService
				.getSystemPoliciesByPeriod(Period.getPeriod(policyEvaluatorConfig.getPeriod()));
		logger.info(policyMappingList);

		mapOfActionParameters = actionParametersService.findAllWithMap();
		logger.info(mapOfActionParameters);
	}

	public List<SystemPolicyMapping> getPolicyMappingList() {
		return policyMappingList;
	}

	public Map<ActionNames, Map<ActionParametersName, String>> getMapOfActionParameters() {
		return mapOfActionParameters;
	}

	public List<Rules> getRuleList() {
		return ruleList;
	}

	public String getValueOfActionAndActionparameter(ActionNames actionNames,
			ActionParametersName actionParametersName) {
		if (mapOfActionParameters.get(actionNames) == null) {
			return null;
		}

		return mapOfActionParameters.get(actionNames).get(actionParametersName);
	}
}
