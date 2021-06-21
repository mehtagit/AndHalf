package com.ceir.CeirCode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.model.StatesInterpretationDb;
import com.ceir.CeirCode.service.StateMgmtServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/Modem")
public class ProjectDropdownController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;
	
	@ApiOperation(value = "View list of state by feature_id", response = StatesInterpretationDb.class)
	@GetMapping("/state-mgmt/{featureId}/{userTypeId}")
	public MappingJacksonValue getByFeatureIdAndUserTypeId(@PathVariable("featureId") Integer featureId, 
			@PathVariable("userTypeId") Integer userTypeId) {

		log.info("Request TO view list of state by feature_id = " + featureId + " and userTypeId = "+ userTypeId);

		List<StatesInterpretationDb> states =  stateMgmtServiceImpl.findByFeatureId(featureId);

		MappingJacksonValue mapping = new MappingJacksonValue(states);

		log.info("state_mgmt_db ByFeatureId = " + mapping);
		
		return mapping;
	}
}
