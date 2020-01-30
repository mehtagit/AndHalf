package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.service.impl.ConsignmentServiceImpl;
import com.gl.ceir.config.service.impl.StackholderPolicyMappingServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
public class ConsignmentController {

	private static final Logger logger = LogManager.getLogger(ConsignmentController.class);

	@Autowired
	ConsignmentServiceImpl consignmentServiceImpl;

	@Autowired
	StolenAndRecoveryServiceImpl stolenAndRecoveryServiceImpl;

	@Autowired
	StackholderPolicyMappingServiceImpl stackholderPolicyMappingServiceImpl;

	@Autowired
	Utility utility;

	@ApiOperation(value = "Add new consignment.", response = GenricResponse.class)
	@RequestMapping(path = "/consignment/register", method = RequestMethod.POST)
	public GenricResponse uploadFile(@RequestBody ConsignmentMgmt consignmentUploadRequest) {

		logger.info("Consignment Register Request = " + consignmentUploadRequest);

		GenricResponse genricResponse = consignmentServiceImpl.registerConsignment( consignmentUploadRequest);
		logger.info("Consignment Register Response = " + genricResponse);

		return genricResponse;
	}

	@ApiOperation(value = "Update Consignment Info.", response = GenricResponse.class)
	@RequestMapping(path = "/consignment/update", method = RequestMethod.POST)
	public GenricResponse updateConsigmentInfo(@RequestBody ConsignmentMgmt consignmentUploadRequest) {

		logger.info("Update Consignment Request = " + consignmentUploadRequest.toString());

		GenricResponse genricResponse =	consignmentServiceImpl.updateConsignment(consignmentUploadRequest);
		logger.info("Update Consignment Response ="+genricResponse);

		return genricResponse;

	}

	@ApiOperation(value = "View all the list of consignment", response = ConsignmentMgmt.class)
	@RequestMapping(path = "/consignment/Record", method = RequestMethod.GET)
	public MappingJacksonValue getByImporterId(@RequestParam("userId") Long userId) {

		logger.info("Request TO view TO all record of user="+userId);

		List<ConsignmentMgmt> consignment = consignmentServiceImpl.getAll(userId);
		MappingJacksonValue mapping = new MappingJacksonValue(consignment);
		logger.info("Response of view Request = " + mapping);

		return mapping;
	}

	@ApiOperation(value = "View filtered consignment", response = ConsignmentMgmt.class)
	@PostMapping("v1/filter/consignment")
	public MappingJacksonValue filterConsignments(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		logger.info("Request TO view filtered consignment = " + filterRequest);

		List<ConsignmentMgmt>  consignment = consignmentServiceImpl.getFilterConsignments(filterRequest, pageNo, pageSize);
		MappingJacksonValue mapping = new MappingJacksonValue(consignment);
		logger.info("Response of view Request ="+mapping);

		return mapping;
	}

	@ApiOperation(value = "pagination View filtered consignment", response = ConsignmentMgmt.class)
	@PostMapping("v2/filter/consignment")
	public MappingJacksonValue withPaginationConsignments(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) {

		MappingJacksonValue mapping = null;
		if(file == 0) {
			logger.info("Request to view filtered consignment = " + filterRequest);
			Page<ConsignmentMgmt> consignment =  consignmentServiceImpl.getFilterPaginationConsignments(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(consignment);
		}else {
			logger.info("Request to export filtered consignment = " + filterRequest);
			FileDetails fileDetails = consignmentServiceImpl.getFilteredConsignmentInFileV2(filterRequest);
			mapping = new MappingJacksonValue(fileDetails);
		}

		logger.info("Response of view Request = " + mapping);

		return mapping;
	}

	@ApiOperation(value = "View the Particular consignment info.", response = ConsignmentMgmt.class)
	@RequestMapping(path = "/consignment/view", method = RequestMethod.GET)
	public MappingJacksonValue getByTxnId(@RequestParam("txnId") String txnId) {

		logger.info("View Request only Single Record="+txnId);

		ConsignmentMgmt consignmentRecordInfo = consignmentServiceImpl.getRecordInfo(txnId);
		MappingJacksonValue mapping = new MappingJacksonValue(consignmentRecordInfo);
		logger.info("Response of View ="+mapping);

		return mapping;
	}

	@ApiOperation(value = "Delete Consignment.", response = GenricResponse.class)
	@RequestMapping(path = "/consigment/delete", method = RequestMethod.DELETE)
	public GenricResponse deleteConsigment(@RequestBody ConsignmentMgmt consignmentUploadRequest,
			@RequestParam(value = "userType", required = false) String userType) {

		logger.info("Consignment Withdraw Request ="+consignmentUploadRequest);

		GenricResponse genricResponse =	consignmentServiceImpl.deleteConsigmentInfo(consignmentUploadRequest, userType);
		logger.info("Response of Delete Request="+genricResponse);

		return genricResponse;

	}

	@ApiOperation(value = "Update Consignment Status.", response = GenricResponse.class)
	@RequestMapping(path = "update/consigmentStatus", method = RequestMethod.PUT)
	public GenricResponse updateConsigmentStatus(@RequestBody ConsignmentUpdateRequest consignmentUpdateRequest) {

		logger.info("Request to update the consignmentStatus="+consignmentUpdateRequest);

		GenricResponse genricResponse = consignmentServiceImpl.updateConsignmentStatus(consignmentUpdateRequest);

		return genricResponse ;

	}


	/*@ApiOperation(value = "Get total count and quantity.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/consignment/countAndQuantity", method = RequestMethod.POST)
	public MappingJacksonValue getConsignmentCountAndQuantity( @RequestBody RequestCountAndQuantity request ) {
		ResponseCountAndQuantity response = consignmentServiceImpl.getConsignmentCountAndQuantity(request);
		return new MappingJacksonValue(response);
	}*/
}
