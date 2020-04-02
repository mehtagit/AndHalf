package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RuleEngine;
import com.gl.ceir.config.service.impl.RuleEngineServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class RuleEngineController {

	private static final Logger logger = LogManager.getLogger(RuleEngineController.class);

	@Autowired
	RuleEngineServiceImpl ruleEngineServiceImpl;

	@ApiOperation(value = "pagination View filtered audit-trail", response = RuleEngine.class)
	@PostMapping("/filter/rule-engine")
	public MappingJacksonValue getFilteredData(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) {

		MappingJacksonValue mapping = null;

		logger.info("Request to view filtered rule engine = " + filterRequest);
		Page<RuleEngine> ruleEngine =  ruleEngineServiceImpl.filterRuleEngine(filterRequest, pageNo, pageSize);
		mapping = new MappingJacksonValue(ruleEngine);

		logger.info("Response of view Request = " + mapping);

		return mapping;
	}

	@ApiOperation(value = "View By Id || Audit Trail", response = MappingJacksonValue.class)
	@GetMapping("/rule-engine/{id}")
	public MappingJacksonValue findAuditTrailById(@PathVariable long id) {

		logger.info("Get rule engine by id [" + id + "]");

		RuleEngine ruleEngine = ruleEngineServiceImpl.findById(id);

		MappingJacksonValue mapping = new MappingJacksonValue(ruleEngine);

		logger.info("Response to send= " + mapping);

		return mapping;
	}
	
	@ApiOperation(value = "Update By Id || Rule Engine", response = MappingJacksonValue.class)
	@PutMapping("/rule-engine")
	public MappingJacksonValue updateAuditTrailById(@RequestBody RuleEngine ruleEngine) {

		logger.info("Update rule engine [" + ruleEngine + "]");

		GenricResponse genricResponse = ruleEngineServiceImpl.updateById(ruleEngine);

		MappingJacksonValue mapping = new MappingJacksonValue(ruleEngine);

		logger.info("Response to send= " + mapping);

		return mapping;
	}

}