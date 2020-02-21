package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.service.impl.ConsignmentServiceImpl;
import com.gl.ceir.config.service.impl.StackholderPolicyMappingServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomerCareController {

	private static final Logger logger = LogManager.getLogger(CustomerCareController.class);

	@Autowired
	ConsignmentServiceImpl consignmentServiceImpl;

	@Autowired
	StolenAndRecoveryServiceImpl stolenAndRecoveryServiceImpl;

	@Autowired
	StackholderPolicyMappingServiceImpl stackholderPolicyMappingServiceImpl;

	@Autowired
	Utility utility;

	@ApiOperation(value = "View all the list of consignment", response = ConsignmentMgmt.class)
	@RequestMapping(path = "/customer-care/record", method = RequestMethod.GET)
	public MappingJacksonValue getByImporterId(@RequestParam("userId") Long userId) {

		logger.info("Request TO view TO all record of user = " + userId);

		List<ConsignmentMgmt> consignment = consignmentServiceImpl.getAll(userId);
		MappingJacksonValue mapping = new MappingJacksonValue(consignment);
		logger.info("Response of view Request = " + mapping);

		return mapping;
	}
	
}