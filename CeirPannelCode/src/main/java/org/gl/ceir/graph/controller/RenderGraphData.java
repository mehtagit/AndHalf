package org.gl.ceir.graph.controller;
import java.util.Collections;

import org.gl.ceir.CeirPannelCode.Feignclient.AnalyticsFeign;
import org.gl.ceir.CeirPannelCode.Service.GraphService;
import org.gl.ceir.graph.model.ActiveDeviceGraphContent;
import org.gl.ceir.graph.model.ActiveDeviceGraphResponseModel;
import org.gl.ceir.graph.model.BrandContent;
import org.gl.ceir.graph.model.BrandModelGrapContent;
import org.gl.ceir.graph.model.GraphRequest;
import org.gl.ceir.graph.model.UserDashboardGraphContent;
import org.gl.ceir.graph.model.UserDashboardGraphResponseModel;
import org.gl.ceir.graph.model.UserDashboardResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class RenderGraphData {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	GraphService graphService;

	@Autowired
	AnalyticsFeign analyticsFeign;
	
	@Autowired
	UserDashboardGraphResponseModel userDashboardGraphResponseModel;
	@Autowired
	UserDashboardResponseModel userDashboardResponseModel;
	@Autowired
	ActiveDeviceGraphResponseModel activeDeviceGraphResponseModel;
	@Autowired
	BrandContent brandModelGrapContent;

	@ResponseBody
	@RequestMapping(value = "/userLoginGraph",method = {RequestMethod.POST})
	public ResponseEntity<?> userLoginGraph(@RequestBody GraphRequest graphRequest){
		Object response= null;
		response = analyticsFeign.graph(graphRequest, graphRequest.getPageNo(),  graphRequest.getPageSize(),  graphRequest.getFile());
		 
		 log.info("userLoginGraph:::::::::countRequest::::::::"+graphRequest+"::::::countResponse:::::::"+response);
			try {
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			log.info("userLoginGraph::::::apiResponse:::::::"+apiResponse);
			
			userDashboardGraphResponseModel = gson.fromJson(apiResponse, UserDashboardGraphResponseModel.class);
			UserDashboardGraphContent paginationContentList = userDashboardGraphResponseModel.getContent();
			return new ResponseEntity<>(paginationContentList, HttpStatus.OK);
			}
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.SERVICE_UNAVAILABLE);
			}
	
	
	}
	
	
	
	@PostMapping("/report/data") 
	public ResponseEntity<?> activeDeviceGraph(@RequestBody GraphRequest graphRequest) {
		Object response= null;
		response = analyticsFeign.graph(graphRequest, graphRequest.getPageNo(),  graphRequest.getPageSize(),  graphRequest.getFile());
		 
		 log.info(":::::::::graphRequest::::::::"+graphRequest+"::::::response:::::::"+response);
	
			try {
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			log.info("::::::apiResponse:::::::"+apiResponse);
			
			activeDeviceGraphResponseModel = gson.fromJson(apiResponse, ActiveDeviceGraphResponseModel.class);
			 log.info("::::::graphResponseModel:::::::"+activeDeviceGraphResponseModel);
			 ActiveDeviceGraphContent paginationContentList = activeDeviceGraphResponseModel.getContent();
			 log.info("::::::paginationContentList:::::::"+paginationContentList);
			return new ResponseEntity<>(paginationContentList, HttpStatus.OK);
			}
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.SERVICE_UNAVAILABLE);
			}
	}
	
	@PostMapping("/brandModel/data") 
	public ResponseEntity<?> topBrandModel(@RequestBody GraphRequest graphRequest) {
		Object response= null;
		response = analyticsFeign.graph(graphRequest, graphRequest.getPageNo(),  graphRequest.getPageSize(),  graphRequest.getFile());
		 
		 log.info("::::::::: brand model graphRequest::::::::"+graphRequest+"::::::response:::::::"+response);
	
			try {
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			log.info("::::::apiResponse:::::::"+apiResponse);
			
			brandModelGrapContent = gson.fromJson(apiResponse, BrandContent.class);
			 log.info(":::::: brand model graphResponseModel:::::::"+brandModelGrapContent);
			 BrandModelGrapContent paginationContentList = brandModelGrapContent.getContent();
			 log.info(":::::: brand model paginationContentList:::::::"+paginationContentList);
			return new ResponseEntity<>(paginationContentList, HttpStatus.OK);
			}
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.SERVICE_UNAVAILABLE);
			}
	}
	
}
