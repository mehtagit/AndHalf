package org.gl.ceir.graph.controller;
import java.util.Optional;

import org.gl.ceir.CeirPannelCode.Feignclient.AnalyticsFeign;
import org.gl.ceir.CeirPannelCode.Model.LoginGraphFilter;
import org.gl.ceir.CeirPannelCode.Service.GraphService;
import org.gl.ceir.graph.model.GraphContent;
import org.gl.ceir.graph.model.GraphRequest;
import org.gl.ceir.graph.model.GraphResponseModel;
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
public class AnalyticsController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	GraphService graphService;

	@Autowired
	AnalyticsFeign analyticsFeign;
	
	@Autowired
	GraphResponseModel graphResponseModel;
	@ResponseBody
	@RequestMapping(value = "/userLoginGraph",method = {RequestMethod.POST})
	public ResponseEntity<?> userLoginGraph(@RequestBody LoginGraphFilter filter){
		return graphService.userLoginGraph(filter);
	}
	
	@PostMapping("/report/data") 
	public ResponseEntity<?> activeDeviceGraph(@RequestBody GraphRequest graphRequest) {
		Integer file = 0;
		Integer pageSize = 1;
		Integer pageNo =0;
		Object response= null;
		 response = analyticsFeign.activeDeviceGraph(graphRequest, pageNo, pageSize, file);
		 
		 log.info("::::::response:::::::"+response);
	
			try {
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			log.info("::::::apiResponse:::::::"+apiResponse);
			
			graphResponseModel = gson.fromJson(apiResponse, GraphResponseModel.class);
			 log.info("::::::graphResponseModel:::::::"+graphResponseModel);
			GraphContent paginationContentList = graphResponseModel.getContent();
			 log.info("::::::paginationContentList:::::::"+paginationContentList);
			return new ResponseEntity<>(paginationContentList, HttpStatus.OK);
			}
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(Optional.empty(), HttpStatus.SERVICE_UNAVAILABLE);
			}
		
	}


	


}
