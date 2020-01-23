package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.service.impl.ConsignmentServiceImpl;
import com.gl.ceir.config.service.impl.StackholderPolicyMappingServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("test")
public class TestController {

	private static final Logger logger = LogManager.getLogger(TestController.class);

	@Autowired
	ConsignmentServiceImpl consignmentServiceImpl;

	@Autowired
	StolenAndRecoveryServiceImpl stolenAndRecoveryServiceImpl;

	@Autowired
	StackholderPolicyMappingServiceImpl stackholderPolicyMappingServiceImpl;

	@Autowired
	Utility utility;

	@ApiOperation(value = "Update Consignment Status.", response = GenricResponse.class)
	@GetMapping("update/status/{featureId}/{txnId}/{Status}")
	public GenricResponse updateStatus(@PathVariable("featureId") int featureId,
			@PathVariable("txnId") int txnId,
			@PathVariable("Status") int Status) {

		logger.info("TEST : Request to update the feature [ " + featureId + "] of txnId [" + txnId +" ] with status [" + Status + "]");

		GenricResponse genricResponse = consignmentServiceImpl.updateConsignmentStatus(consignmentUpdateRequest);

		return genricResponse ;

	}

}
