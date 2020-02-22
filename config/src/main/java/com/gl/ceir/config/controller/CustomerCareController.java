package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.CustomerCareRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.service.impl.CustomerCareServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomerCareController {

	private static final Logger logger = LogManager.getLogger(CustomerCareController.class);

	@Autowired
	CustomerCareServiceImpl customerCareServiceImpl;

	@Autowired
	Utility utility;

	@ApiOperation(value = "View all DB's state for the imei or msisdn.", response = GenricResponse.class)
	@PostMapping("/customer-care/record")
	public MappingJacksonValue getByImporterId(@RequestBody CustomerCareRequest customerCareRequest) {

		logger.info(String.format("Request TO view TO all record of user = %s", customerCareRequest));

		GenricResponse response = customerCareServiceImpl.getAll(customerCareRequest);
		MappingJacksonValue mapping = new MappingJacksonValue(response);
		logger.info("Response of view Request = " + mapping);

		return mapping;
	}
	
}