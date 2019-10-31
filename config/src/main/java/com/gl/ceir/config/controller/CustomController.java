package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.service.impl.CustomServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomController {

	private static final Logger logger = LogManager.getLogger(CustomController.class);

	@Autowired
	CustomServiceImpl customServiceImpl;


	@ApiOperation(value = "View All available Success File Info", response = ConsignmentMgmt.class)
	@RequestMapping(path = "/custom/view", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {

		logger.info("Custom view info request");
		List<ConsignmentMgmt> customInfo =	customServiceImpl.getCustomDetails();

		MappingJacksonValue mapping = new MappingJacksonValue(customInfo);

		return mapping;

	}


	@ApiOperation(value = "Update taxPaid Status", response = GenricResponse.class)
	@RequestMapping(path = "/custom/update", method = RequestMethod.GET)
	public GenricResponse updateCustominfo(String txnId) {

		logger.info("Update Custom Info request TxnId="+txnId);

		GenricResponse  response =customServiceImpl.updateTaxPaidStatus(txnId);

		return response;

	}






}
