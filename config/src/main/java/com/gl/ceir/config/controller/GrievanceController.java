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

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.Grievance;
import com.gl.ceir.config.model.GrievanceMsg;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.GrievanceFilterRequest;
import com.gl.ceir.config.service.impl.GrievanceServiceImpl;
import com.gl.ceir.config.service.impl.StackholderPolicyMappingServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
public class GrievanceController {
	private static final Logger logger = LogManager.getLogger(GrievanceController.class);

	@Autowired
	GrievanceServiceImpl grievanceServiceImpl;
	@Autowired
	FileStorageProperties fileStorageProperties;
	@Autowired
	Utility utility;
	
	@ApiOperation(value = "Add new grievance.", response = GenricResponse.class)
	@RequestMapping(path = "/grievance/save", method = {RequestMethod.GET,RequestMethod.POST})
	public GenricResponse uploadFile(@RequestBody Grievance grievance) {
		logger.info("New Grievance Request="+grievance);
		GenricResponse genricResponse = grievanceServiceImpl.save(grievance);
		logger.info("New Grievance Response="+genricResponse);
		return genricResponse;
	}
	
	@ApiOperation(value = "View all the list of grievances", response = Grievance.class)
	@RequestMapping(path = "/grievance/Record", method = {RequestMethod.GET,RequestMethod.POST})
	public MappingJacksonValue getGrievanceByUserId(@RequestParam("userId") Integer userId) {
		logger.info("Request TO view TO all record of user="+userId);
		List<Grievance>  grievance =  grievanceServiceImpl.getGrievanceByUserId(userId);
		MappingJacksonValue response = new MappingJacksonValue(grievance);
		logger.info("Response of view Request ="+response);
		return response;
	}
	
	@ApiOperation(value = "View filtered consignment", response = Grievance.class)
	@PostMapping("v1/filter/grievance")
	public MappingJacksonValue filterGrievances(@RequestBody GrievanceFilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		logger.info("Request TO view filtered Grievances = " + filterRequest);
		List<Grievance>  grievance =  grievanceServiceImpl.getFilterGrievances(filterRequest, pageNo, pageSize);
		MappingJacksonValue mapping = new MappingJacksonValue(grievance);
		logger.info("Response of view Request ="+mapping);
		return mapping;
	}

	@ApiOperation(value = "pagination View filtered consignment", response = Grievance.class)
	@PostMapping("v2/filter/grievance")
	public MappingJacksonValue withPaginationGrievances(@RequestBody GrievanceFilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		logger.info("Request TO view filtered grievance = " + filterRequest);
		Page<Grievance>  grievance =  grievanceServiceImpl.getFilterPaginationGrievances(filterRequest, pageNo, pageSize);
		MappingJacksonValue mapping = new MappingJacksonValue(grievance);
		logger.info("Response of view Request ="+mapping);
		return mapping;
	}
	
}
