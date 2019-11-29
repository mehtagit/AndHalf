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
import com.gl.ceir.config.model.RequestCountAndQuantity;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.Grievance;
import com.gl.ceir.config.model.GrievanceMsg;
import com.gl.ceir.config.model.GrievanceMsgWithUser;
import com.gl.ceir.config.model.GrievanceReply;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.GrievanceGenricResponse;
import com.gl.ceir.config.model.GrievanceFilterRequest;
import com.gl.ceir.config.model.GrievanceHistory;
import com.gl.ceir.config.service.impl.GrievanceServiceImpl;
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
	
	@ApiOperation(value = "Add new grievance.", response = GrievanceGenricResponse.class)
	@RequestMapping(path = "/grievance/save", method = {RequestMethod.POST})
	public GrievanceGenricResponse uploadFile(@RequestBody Grievance grievance) {
		logger.info("New Grievance Request="+grievance);
		GrievanceGenricResponse genricResponse = grievanceServiceImpl.save(grievance);
		logger.info("New Grievance Response="+genricResponse);
		return genricResponse;
	}
	
	/**This api will give all open grievances**/
	/*@ApiOperation(value = "View all the list of grievances", response = Grievance.class)
	@RequestMapping(path = "/grievance/Record", method = {RequestMethod.GET})
	public MappingJacksonValue getGrievanceByUserId(@RequestParam("userId") Integer userId, @RequestParam("userType") String userType) {
		List<Grievance>  grievance = null;
		logger.info("Request TO view TO all record of user="+userId+" and user type="+userType);
		if( userType.equalsIgnoreCase("admin"))
			grievance = grievanceServiceImpl.getAllGrievanceStatusNotClosed(userId);
		else
			grievance = grievanceServiceImpl.getAllGrievanceStatusNotClosedForAdmin();
		MappingJacksonValue response = new MappingJacksonValue(grievance);
		logger.info("Response of view Request ="+response);
		return response;
	}*/
	
	/**This api will give all grievances even with closed status**/
	/*@ApiOperation(value = "View all the list of grievances", response = Grievance.class)
	@RequestMapping(path = "/grievance/AllRecord", method = {RequestMethod.GET})
	public MappingJacksonValue getAllGrievanceByUserId(@RequestParam("userId") Integer userId) {
		logger.info("Request TO view TO all record of user="+userId);
		List<Grievance>  grievance =  grievanceServiceImpl.getAllGrievanceStatusNotClosed(userId);
		MappingJacksonValue response = new MappingJacksonValue(grievance);
		logger.info("Response of view Request ="+response);
		return response;
	}*/
	
	@ApiOperation(value = "View filtered grievance", response = Grievance.class)
	@PostMapping("v1/filter/grievance")
	public MappingJacksonValue filterGrievances(@RequestBody GrievanceFilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		logger.info("Request to view filtered Grievances = " + filterRequest);
		List<Grievance>  grievance =  grievanceServiceImpl.getFilterGrievances(filterRequest, pageNo, pageSize);
		MappingJacksonValue mapping = new MappingJacksonValue(grievance);
		logger.info("Response of view filtered Grievances ="+mapping);
		return mapping;
	}

	@ApiOperation(value = "pagination View filtered grievance", response = Grievance.class)
	@PostMapping("v2/filter/grievance")
	public MappingJacksonValue withPaginationGrievances(@RequestBody GrievanceFilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) {
		MappingJacksonValue mapping = null;
		logger.info("Request to view filtered grievance = " + filterRequest);
		if( file == 0) {
			Page<Grievance>  grievance =  grievanceServiceImpl.getFilterPaginationGrievances(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(grievance);
		}else {
			FileDetails fileDetails = grievanceServiceImpl.getFilterGrievancesInFile(filterRequest, pageNo, pageSize);
			mapping = new MappingJacksonValue(fileDetails);
		}
		logger.info("Response of view filtered Grievances ="+mapping);
		return mapping;
	}
	
	@ApiOperation(value = "Add new grievance Message", response = GrievanceGenricResponse.class)
	@RequestMapping(path = "/grievance/saveMessage", method = {RequestMethod.POST})
	public GrievanceGenricResponse saveGrievanceMessage(@RequestBody GrievanceReply grievanceReply) {
		logger.info("New Grievance message Request="+grievanceReply);
		GrievanceGenricResponse genricResponse = grievanceServiceImpl.saveGrievanceMsg(grievanceReply);
		logger.info("New Grievance Response="+genricResponse);
		return genricResponse;
	}
	
	/**This api will give all open grievances**/
	@ApiOperation(value = "View all the list of grievance messages", response = GrievanceMsgWithUser.class)
	@RequestMapping(path = "/grievance/msg", method = {RequestMethod.GET})
	public MappingJacksonValue getGrievanceMessagesByGrievanceId(@RequestParam("userId") Integer userId,@RequestParam("grievanceId") String grievanceId
			,@RequestParam("recordLimit") Integer recordLimit) {
		logger.info("Request to view all messages of grievance="+userId+", Grievance Id:["+grievanceId+"] and Record Limit:["+recordLimit+"]");
		List<GrievanceMsgWithUser>  msgs =  grievanceServiceImpl.getAllGrievanceMessagesByGrievanceId(grievanceId, recordLimit, userId );
		MappingJacksonValue response = new MappingJacksonValue(msgs);
		logger.info("Response of view all messages of grievance ="+response);
		return response;
	}
	
	@ApiOperation(value = "pagination View filtered grievance history", response = Grievance.class)
	@PostMapping("v2/filter/grievanceHistory")
	public MappingJacksonValue withPaginationGrievanceHistory(@RequestBody GrievanceFilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		logger.info("Request to view filtered grievance history = " + filterRequest);
		Page<GrievanceHistory>  grievance =  grievanceServiceImpl.getFilterPaginationGrievanceHistory(filterRequest, pageNo, pageSize);
		MappingJacksonValue mapping = new MappingJacksonValue(grievance);
		logger.info("Response of view filtered grievance history ="+mapping);
		return mapping;
	}
	
	@ApiOperation(value = "Get total count.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/grievance/count", method = RequestMethod.POST)
	public MappingJacksonValue getgrievanceCount(@RequestBody RequestCountAndQuantity request ) {
		ResponseCountAndQuantity response = grievanceServiceImpl.getGrievanceCount(request);
		return new MappingJacksonValue(response);
	}
	
}
