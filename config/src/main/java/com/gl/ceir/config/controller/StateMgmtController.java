package com.gl.ceir.config.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.TableActions;
import com.gl.ceir.config.service.impl.StateMgmtServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
public class StateMgmtController {

	private static final Logger logger = LogManager.getLogger(StateMgmtController.class);

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;

	@Autowired
	Utility utility;

	@ApiOperation(value = "View list of state by feature_id and user_type_id", response = StateMgmtDb.class)
	@GetMapping("/state-mgmt/{featureId}/{userTypeId}")
	public MappingJacksonValue getByFeatureIdAndUserTypeId(@PathVariable("featureId") Integer featureId, 
			@PathVariable("userTypeId") Integer userTypeId) {

		logger.info("Request TO view list of state by feature_id = " + featureId + " and userTypeId = "+ userTypeId);

		List<StateMgmtDb> states =  stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(featureId, userTypeId);

		MappingJacksonValue mapping = new MappingJacksonValue(states);

		logger.info("state_mgmt_db ByFeatureIdAndUserTypeId = " + mapping);

		return mapping;
	}
	
	
	@ApiOperation(value = "View list of state by feature_id and user_type_id", response = StateMgmtDb.class)
	@GetMapping("/table-actions/{featureId}/{userTypeId}")
	public MappingJacksonValue getTableActionsByFeatureIdAndUserTypeId(@PathVariable("featureId") Integer featureId, 
			@PathVariable("userTypeId") Integer userTypeId) {

		logger.info("Request TO view list of state by feature_id = " + featureId + " and userTypeId = "+ userTypeId);

		List<TableActions> tableActions = stateMgmtServiceImpl.getTableActionsByFeatureIdAndUserTypeId(featureId, userTypeId);

		MappingJacksonValue mapping = new MappingJacksonValue(tableActions);

		logger.info("TableActions getTableActionsByFeatureIdAndUserTypeId = " + mapping);

		return mapping;
	}

}
