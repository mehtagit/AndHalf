package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RequestCountAndQuantityWithLongUserId;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.StackholderPolicyMapping;
import com.gl.ceir.config.model.StolenandRecoveryMgmt;
import com.gl.ceir.config.service.impl.DeviceSnapShotServiceImpl;
import com.gl.ceir.config.service.impl.StackholderPolicyMappingServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
public class StolenAndRecoveryController {

	private static final Logger logger = LogManager.getLogger(DeviceSnapShotServiceImpl.class);

	@Autowired
	StolenAndRecoveryServiceImpl stolenAndRecoveryServiceImpl;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StackholderPolicyMappingServiceImpl stackholderPolicyMappingServiceImpl;

	@Autowired
	Utility utility;


	@ApiOperation(value = "Upload Recovery Details.", response = GenricResponse.class)
	@RequestMapping(path = "/stakeholder/Recovery", method = RequestMethod.POST)
	public GenricResponse uploadFileAction(@RequestBody StolenandRecoveryMgmt stolenandRecoveryRequest) {

		logger.info("Upload Recovery Details="+stolenandRecoveryRequest);


		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.uploadDetails(stolenandRecoveryRequest);

		logger.info("Upload recovery details response="+genricResponse);

		return genricResponse;

	}


	@ApiOperation(value = "Upload Stolen Details.", response = GenricResponse.class)
	@RequestMapping(path = "/stakeholder/Stolen", method = RequestMethod.POST)
	public GenricResponse uploadStolenDetails(@RequestBody StolenandRecoveryMgmt stolenandRecoveryDetails)
	{
		logger.info("Stolen upload Request="+stolenandRecoveryDetails);

		StackholderPolicyMapping mapping = new StackholderPolicyMapping();
		mapping.setListType("BlackList");


		if(stolenandRecoveryDetails.getBlockingType() == null || stolenandRecoveryDetails.getBlockingType().equalsIgnoreCase("Default") ||
				stolenandRecoveryDetails.getBlockingType() == "") {

			StackholderPolicyMapping config = stackholderPolicyMappingServiceImpl.getPocessListConfigDetails(mapping);
			String newTime = utility.newDate(config.getGraceTimePeriod());

			stolenandRecoveryDetails.setBlockingTimePeriod(newTime);
			stolenandRecoveryDetails.setBlockingType("Default");
		}

		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.uploadDetails(stolenandRecoveryDetails);
		logger.info("Stolen upload Response="+genricResponse);

		return genricResponse;
	}

	@ApiOperation(value = "Upload Stolen Details.", response = GenricResponse.class)
	@RequestMapping(path = "v2/stakeholder/Stolen", method = RequestMethod.POST)
	public GenricResponse v2uploadStolenDetails(@RequestBody StolenandRecoveryMgmt stolenandRecoveryDetails)
	{
		logger.info("Stolen upload Request="+stolenandRecoveryDetails);

		StackholderPolicyMapping mapping = new StackholderPolicyMapping();
		mapping.setListType("BlackList");


		if(stolenandRecoveryDetails.getBlockingType() == null || stolenandRecoveryDetails.getBlockingType().equalsIgnoreCase("Default") ||
				stolenandRecoveryDetails.getBlockingType() == "") {

			StackholderPolicyMapping config = stackholderPolicyMappingServiceImpl.getPocessListConfigDetails(mapping);
			String newTime = utility.newDate(config.getGraceTimePeriod());

			stolenandRecoveryDetails.setBlockingTimePeriod(newTime);
			stolenandRecoveryDetails.setBlockingType("Default");
		}

		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.v2uploadDetails(stolenandRecoveryDetails);
		logger.info("Stolen upload Response="+genricResponse);

		return genricResponse;
	}

	
	
	

	@ApiOperation(value = "Upload Multiple Stolen Details.", response = GenricResponse.class)
	@RequestMapping(path = "/stakeholder/uploadMultiple/StolenAndRecovery", method = RequestMethod.POST)
	public GenricResponse uploadMultipleStolenDetails(@RequestBody List<StolenandRecoveryMgmt> stolenandRecoveryDetails)
	{
		logger.info("Multiple Stolen Upload Request="+stolenandRecoveryDetails);

		GenricResponse genricResponse =	stolenAndRecoveryServiceImpl.uploadMultipleStolen(stolenandRecoveryDetails);

		logger.info("Muliple Stolen Upload Response ="+genricResponse);
		return genricResponse;

	}

	@ApiOperation(value = "View Stolen and Recovery Details.", response = StolenandRecoveryMgmt.class)
	@RequestMapping(path = "/stakeholder/record", method = RequestMethod.POST)
	public MappingJacksonValue getAllActionDetails(@RequestBody FilterRequest stolenandRecoveryDetails,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		logger.info("Record request to Stolen And Recovery Info="+stolenandRecoveryDetails);

		Page<StolenandRecoveryMgmt>	stolenandRecoveryDetailsResponse = stolenAndRecoveryServiceImpl.getAllInfo(stolenandRecoveryDetails,pageNo,pageSize);

		MappingJacksonValue mapping = new MappingJacksonValue(stolenandRecoveryDetailsResponse);

		logger.info("Record Response of Stolen And Recovery Info="+mapping);

		return mapping;
	}



	@ApiOperation(value = "Delete Stolen and  Recovery Details.", response = GenricResponse.class)
	@RequestMapping(path = "/stakeholder/Delete", method = RequestMethod.DELETE)
	public GenricResponse deleteRecord(@RequestBody StolenandRecoveryMgmt stolenandRecoveryRequest) {

		logger.info("Record Delete request ="+stolenandRecoveryRequest);

		GenricResponse genricResponse = stolenAndRecoveryServiceImpl.deleteRecord(stolenandRecoveryRequest);

		logger.info("Response send ="+genricResponse);

		return genricResponse;


	}



	@ApiOperation(value = "Update Stolen and  Recovery Details.", response = GenricResponse.class)
	@RequestMapping(path = "/stakeholder/update", method = RequestMethod.PUT)
	public GenricResponse updateRecord(@RequestBody StolenandRecoveryMgmt stolenandRecoveryRequest) {
		logger.info("Record update request="+stolenandRecoveryRequest);

		StackholderPolicyMapping mapping = new StackholderPolicyMapping();
		mapping.setListType("BlackList");
		if(stolenandRecoveryRequest.getBlockingType() == null || stolenandRecoveryRequest.getBlockingType().equalsIgnoreCase("Default") ||
				stolenandRecoveryRequest.getBlockingType() == "") {

			StackholderPolicyMapping config = stackholderPolicyMappingServiceImpl.getPocessListConfigDetails(mapping);
			String newTime = utility.newDate(config.getGraceTimePeriod());

			stolenandRecoveryRequest.setBlockingTimePeriod(newTime);
			stolenandRecoveryRequest.setBlockingType("Default");
		}



		GenricResponse genricResponse = stolenAndRecoveryServiceImpl.updateRecord(stolenandRecoveryRequest);

		logger.info("Response send="+genricResponse);

		return genricResponse;

	}



	@ApiOperation(value = "View Stolen and  Recovery Details.", response = StolenandRecoveryMgmt.class)
	@RequestMapping(path = "/stakeholder/view", method = RequestMethod.POST)
	public MappingJacksonValue viewRecord(@RequestBody StolenandRecoveryMgmt stolenandRecoveryRequest)
	{
		logger.info("view Stolen and recovery request="+stolenandRecoveryRequest);

		StolenandRecoveryMgmt mgmt = stolenAndRecoveryServiceImpl.viewRecord(stolenandRecoveryRequest);
		logger.info("View details Response send ="+mgmt);

		MappingJacksonValue mapping = new MappingJacksonValue(mgmt);
		return mapping;

	}

	@ApiOperation(value = "Get total count.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/stakeholder/count", method = RequestMethod.POST)
	public MappingJacksonValue getStolenAndRecoveryCount( @RequestBody RequestCountAndQuantityWithLongUserId request, @RequestParam(value = "requestType") String requestType) {
		ResponseCountAndQuantity response = stolenAndRecoveryServiceImpl.getStolenAndRecoveryCount( request, requestType);
		return new MappingJacksonValue(response);
	}



}
