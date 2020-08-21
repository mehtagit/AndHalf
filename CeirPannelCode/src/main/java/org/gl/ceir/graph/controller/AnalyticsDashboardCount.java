package org.gl.ceir.graph.controller;

import java.util.Collections;

import org.gl.ceir.CeirPannelCode.Feignclient.AnalyticsFeign;
import org.gl.ceir.graph.model.CountContent;
import org.gl.ceir.graph.model.CountResponseModel;
import org.gl.ceir.graph.model.GraphRequest;
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
public class AnalyticsDashboardCount {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	AnalyticsFeign analyticsFeign;
	@Autowired
	CountResponseModel countResponseModel;
	
	@PostMapping("/report/count") 
	public ResponseEntity<?> activeDeviceCount(@RequestBody GraphRequest graphRequest) {
		Object response= null;
		response = analyticsFeign.activeDeviceGraph(graphRequest, graphRequest.getPageNo(),  graphRequest.getPageSize(),  graphRequest.getFile());
		 
		 log.info(":::::::::countRequest::::::::"+graphRequest+"::::::countResponse:::::::"+response);
			try {
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			log.info("::::::apiResponse:::::::"+apiResponse);
			
			countResponseModel = gson.fromJson(apiResponse, CountResponseModel.class);
			CountContent paginationContentList = countResponseModel.getContent();
			return new ResponseEntity<>(paginationContentList, HttpStatus.OK);
			}
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.SERVICE_UNAVAILABLE);
			}
	}
}
