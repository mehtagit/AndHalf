package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.CeirActionRequest;
import com.gl.ceir.config.model.EndUserDB;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.service.impl.RegularizedDeviceServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class RegularizedDeviceController {

	private static final Logger logger = LogManager.getLogger(RegularizedDeviceController.class);

	@Autowired
	RegularizedDeviceServiceImpl regularizedDeviceServiceImpl;
	
	@ApiOperation(value = "View End User Device by IMEI1", response = GenricResponse.class)
	@GetMapping("/end-user-device-info/{imei}")
	public MappingJacksonValue viewDeviceInfoByImei1(@PathVariable("imei") long imei) {

		logger.info("View Regularized device of IMEI= " + imei);

		RegularizeDeviceDb response = regularizedDeviceServiceImpl.viewDeviceInfoByImei1(imei);

		MappingJacksonValue mapping = new MappingJacksonValue(response); 
		return mapping;
	}

	@ApiOperation(value = "View Regularized DB of end User", response = RegularizeDeviceDb.class)
	@RequestMapping(path = "/filter/end-user-device-info", method = RequestMethod.POST)
	public MappingJacksonValue getDeviceByNid( @RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) {

		MappingJacksonValue mapping = null;
		
		if(file == 0) {
			logger.info("Regularize Device view info request " + filterRequest);
			Page<RegularizeDeviceDb> customInfo = regularizedDeviceServiceImpl.filter(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(customInfo);
		}else {
			logger.info("Regularized Device Export request " + filterRequest);
			FileDetails fileDetails = regularizedDeviceServiceImpl.getFilteredDeviceInFile(filterRequest);
			mapping = new MappingJacksonValue(fileDetails);
		}
		
		return mapping;
	}

	@ApiOperation(value = "Register End User Device Info ", response = GenricResponse.class)
	@PostMapping("/end-user-device-info")
	public GenricResponse saveEndUserInfo(@RequestBody EndUserDB endUserDB) {
		logger.info("Custom registeration request= " + endUserDB);

		GenricResponse genricResponse = regularizedDeviceServiceImpl.saveDevices(endUserDB);
		logger.info("Resonse send = " + genricResponse);
		return genricResponse;

	}

	@ApiOperation(value = "Update taxPaid Status", response = GenricResponse.class)
	@PutMapping("/update-tax-paid-status/end-user-device-info")
	public GenricResponse updateTaxPaidStatus( @RequestBody RegularizeDeviceDb userCustomDb) {

		logger.info("Update Custom taxPaid info Info request TxnId = " + userCustomDb);

		GenricResponse response = regularizedDeviceServiceImpl.updateTaxStatus(userCustomDb);

		logger.info("Response send to user = " + response);
		return response;

	}

	@ApiOperation(value = "Delete taxPaid Status", response = GenricResponse.class)
	@DeleteMapping("/end-user-device-info/{imei}")
	public GenricResponse deleteCustominfo( @PathVariable("imei") long imei) {

		logger.info("Request to delete regularized device = " + imei);
		
		GenricResponse  response = regularizedDeviceServiceImpl.deleteCustomInfo(imei);
		
		logger.info("Response send to user="+response);
		return response;
	}

	@ApiOperation(value = "Count of regularized devices for end user.", response = GenricResponse.class)
	@PostMapping("/end-user-device-info/count-by-nid/{nid}")
	public GenricResponse countOfRegularizedDevicesByNid(@PathVariable("nid") String nid) {
		logger.info("Request to get the count of regularized devices for end user = " + nid);

		GenricResponse genricResponse = regularizedDeviceServiceImpl.getCountOfRegularizedDevicesByNid(nid);
		logger.info("Resonse send = " + genricResponse);
		return genricResponse;

	}
	
	@ApiOperation(value = "Accept/Reject regularized Devices Status.", response = GenricResponse.class)
	@RequestMapping(path = "accept-reject/end-user-device", method = RequestMethod.PUT)
	public GenricResponse updateConsigmentStatus(@RequestBody CeirActionRequest ceirActionRequest) {

		logger.info("Request to update the regularized devices = " + ceirActionRequest);

		GenricResponse genricResponse = regularizedDeviceServiceImpl.acceptReject(ceirActionRequest);

		return genricResponse ;

	}
}