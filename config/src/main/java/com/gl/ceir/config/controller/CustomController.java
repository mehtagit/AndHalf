package com.gl.ceir.config.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.CustomRegistrationDB;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.UserCustomDb;
import com.gl.ceir.config.service.impl.CustomServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class CustomController {

	private static final Logger logger = LogManager.getLogger(CustomController.class);

	@Autowired
	CustomServiceImpl customServiceImpl;


	@ApiOperation(value = "View All available Success File Info", response = UserCustomDb.class)
	@RequestMapping(path = "/custom/record", method = RequestMethod.POST)
	public MappingJacksonValue getAll( @RequestBody UserCustomDb userCustomDb) {

		logger.info("Custom view info request");
		List<UserCustomDb> customInfo =	customServiceImpl.getCustomDetails(userCustomDb);

		MappingJacksonValue mapping = new MappingJacksonValue(customInfo);

		return mapping;

	}


	@ApiOperation(value = "Register the custom ", response = GenricResponse.class)
	@RequestMapping(path = "/custom/register", method = RequestMethod.POST)
	public GenricResponse saveCustomRegisterInfo( @Valid @RequestBody CustomRegistrationDB customRegistrationDB) {
		logger.info("Custom registeration request="+customRegistrationDB);

		GenricResponse genricResponse = customServiceImpl.saveRegisterInfo(customRegistrationDB);
		logger.info("Resonse send ="
				+genricResponse);
		return genricResponse;

	}


	@ApiOperation(value = "View the detail of the devices Status", response = GenricResponse.class)
	@RequestMapping(path = "/custom/view", method = RequestMethod.POST)
	public MappingJacksonValue viewCustominfo(@RequestBody UserCustomDb userCustomDb) {

		logger.info("Update Custom taxPaid info Info request TxnId="+userCustomDb);

		UserCustomDb  response =customServiceImpl.viewStatus(userCustomDb);

		MappingJacksonValue mapping = new MappingJacksonValue(response); 
		return mapping;

	}



	@ApiOperation(value = "Update taxPaid Status", response = GenricResponse.class)
	@RequestMapping(path = "/custom/update", method = RequestMethod.PUT)
	public GenricResponse updateCustominfo( @RequestBody UserCustomDb userCustomDb) {

		logger.info("Update Custom taxPaid info Info request TxnId="+userCustomDb);

		GenricResponse  response =customServiceImpl.updateTaxPaidStatus(userCustomDb);

		logger.info("Response send to user="+response);
		return response;

	}


	@ApiOperation(value = "Delete taxPaid Status", response = GenricResponse.class)
	@RequestMapping(path = "/custom/delete", method = RequestMethod.DELETE)
	public GenricResponse deleteCustominfo( @RequestBody UserCustomDb userCustomDb) {
		logger.info("Delete Request to custom info ="+userCustomDb);

		GenricResponse  response =customServiceImpl.deleteCustomInfo(userCustomDb);
		logger.info("Response send to user="+response);
		return response;
	}




}
