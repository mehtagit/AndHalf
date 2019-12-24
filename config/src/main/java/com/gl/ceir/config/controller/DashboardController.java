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

import com.gl.ceir.config.model.DashboardConfDb;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.service.impl.ConsignmentServiceImpl;
import com.gl.ceir.config.service.impl.DashboardConfServiceImpl;
import com.gl.ceir.config.service.impl.GrievanceServiceImpl;
import com.gl.ceir.config.service.impl.ListFileDetailsImpl;
import com.gl.ceir.config.service.impl.StockServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.service.impl.TypeApproveServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class DashboardController {
	private static final Logger logger = LogManager.getLogger(GrievanceController.class);

	@Autowired
	GrievanceServiceImpl grievanceServiceImpl;
	@Autowired
	ConsignmentServiceImpl consignmentServiceImpl;
	@Autowired
	StockServiceImpl stackholderServiceImpl;
	@Autowired
	StolenAndRecoveryServiceImpl stolenAndRecoveryServiceImpl;
	@Autowired
	DashboardConfServiceImpl dashboardConfServiceImpl;
	@Autowired
	ListFileDetailsImpl listFileDetailsImpl;
	@Autowired
	TypeApproveServiceImpl typeApproveServiceImpl;
	
	@ApiOperation(value = "Get total count.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/grievance/count", method = RequestMethod.GET)
	public MappingJacksonValue getgrievanceCount(@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "userTypeId") Integer userTypeId,
			@RequestParam(value = "userType") String userType,
			@RequestParam(value = "featureId") Integer featureId) {
		ResponseCountAndQuantity response = grievanceServiceImpl.getGrievanceCount(userId, userTypeId, featureId, userType);
		return new MappingJacksonValue(response);
	}
	
	@ApiOperation(value = "Get total count.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/consignment/countAndQuantity", method = RequestMethod.GET)
	public MappingJacksonValue getConsignmentCountAndQuantity( @RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "userTypeId") Integer userTypeId,
			@RequestParam(value = "userType") String userType,
			@RequestParam(value = "featureId") Integer featureId ) {
		ResponseCountAndQuantity response = consignmentServiceImpl.getConsignmentCountAndQuantity(userId, userTypeId, featureId, userType);
		return new MappingJacksonValue(response);
	}
	
	@ApiOperation(value = "Get total count and quantity.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/stock/countAndQuantity", method = RequestMethod.GET)
	public MappingJacksonValue getStockCountAndQuantity( @RequestParam(value = "userId") long userId,
			@RequestParam(value = "userTypeId") Integer userTypeId,
			@RequestParam(value = "userType") String userType,
			@RequestParam(value = "featureId") Integer featureId ) {
		ResponseCountAndQuantity response = stackholderServiceImpl.getStockCountAndQuantity( userId, userTypeId, featureId, userType );
		return new MappingJacksonValue(response);
	}
	
	@ApiOperation(value = "Get total count.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/stakeholder/count", method = RequestMethod.GET)
	public MappingJacksonValue getStolenAndRecoveryCount( @RequestParam(value = "userId") long userId,
			@RequestParam(value = "userTypeId") Integer userTypeId,
			@RequestParam(value = "featureId") Integer featureId,
			@RequestParam(value = "userType") String userType,
			@RequestParam(value = "requestType") String requestType) {
		ResponseCountAndQuantity response = stolenAndRecoveryServiceImpl.getStolenAndRecoveryCount( userId, userTypeId, featureId, requestType, userType);
		return new MappingJacksonValue(response);
	}
	
	@ApiOperation(value = "Get total count.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/filedump/count", method = RequestMethod.GET)
	public MappingJacksonValue getOperatorCount( @RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "userTypeId") Integer userTypeId,
			@RequestParam(value = "featureId") Integer featureId,
			@RequestParam(value = "userType") String userType,
			@RequestParam(value = "serviceDump") Integer serviceDump) {
		ResponseCountAndQuantity response = listFileDetailsImpl.getFileDumpCount(serviceDump);
		return new MappingJacksonValue(response);
	}
	
	@ApiOperation(value = "Get total count.", response = ResponseCountAndQuantity.class)
	@RequestMapping(path = "/TypeApproved/count", method = RequestMethod.GET)
	public MappingJacksonValue getTACCount( @RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "userTypeId") Integer userTypeId,
			@RequestParam(value = "featureId") Integer featureId,
			@RequestParam(value = "userType") String userType,
			@RequestParam(value = "approveStatus") Integer approveStatus) {
		ResponseCountAndQuantity response = typeApproveServiceImpl.getTACCount(userId, approveStatus);
		return new MappingJacksonValue(response);
	}
	
	@ApiOperation(value = "Get total count.", response = DashboardConfDb.class)
	@RequestMapping(path = "/dashboard/dbConf", method = RequestMethod.GET)
	public MappingJacksonValue getDashboardConf( @RequestParam(value = "userTypeId") Integer userTypeId) {
		List<DashboardConfDb> response = dashboardConfServiceImpl.getDashboardConfig(userTypeId);
		return new MappingJacksonValue(response);
	}
	
}
