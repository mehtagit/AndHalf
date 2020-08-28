package org.gl.ceir.graph.controller;

import java.util.Collections;

import org.gl.ceir.CeirPannelCode.Feignclient.AnalyticsFeign;
import org.gl.ceir.graph.model.ActiveDeviceCountContent;
import org.gl.ceir.graph.model.ActiveDeviceCountResponseModel;
import org.gl.ceir.graph.model.GraphRequest;
import org.gl.ceir.graph.model.UserDashboardCountContent;
import org.gl.ceir.graph.model.UserDashboardResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class GraphDashboardCount {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	AnalyticsFeign analyticsFeign;
	@Autowired
	ActiveDeviceCountResponseModel countResponseModel;

	@Autowired
	UserDashboardResponseModel userDashboardResponseModel;
	
	@PostMapping("/report/count") 
	public ResponseEntity<?> activeDeviceCount(@RequestBody GraphRequest graphRequest) {
		Object response= null;
		response = analyticsFeign.graph(graphRequest, graphRequest.getPageNo(),  graphRequest.getPageSize(),  graphRequest.getFile());
		 
		 log.info(":::::::::countRequest::::::::"+graphRequest+"::::::countResponse:::::::"+response);
			try {
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			log.info("::::::apiResponse:::::::"+apiResponse);
			
			countResponseModel = gson.fromJson(apiResponse, ActiveDeviceCountResponseModel.class);
			ActiveDeviceCountContent paginationContentList = countResponseModel.getContent();
			return new ResponseEntity<>(paginationContentList, HttpStatus.OK);
			}
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.SERVICE_UNAVAILABLE);
			}
	}
	
	
	
	@PostMapping("/user/report/count") 
	public ResponseEntity<?> activeDeviceGraph(@RequestBody GraphRequest graphRequest) {

		Object response= null;
		response = analyticsFeign.graph(graphRequest, graphRequest.getPageNo(),  graphRequest.getPageSize(),  graphRequest.getFile());
		 
		 log.info("/user/report/count:::::::::countRequest::::::::"+graphRequest+"::::::countResponse:::::::"+response);
			try {
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			log.info("/user/report/count::::::apiResponse:::::::"+apiResponse);
			
			userDashboardResponseModel = gson.fromJson(apiResponse, UserDashboardResponseModel.class);
			UserDashboardCountContent paginationContentList = userDashboardResponseModel.getContent();
			return new ResponseEntity<>(paginationContentList, HttpStatus.OK);
			}
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.SERVICE_UNAVAILABLE);
			}
	
	}
}
