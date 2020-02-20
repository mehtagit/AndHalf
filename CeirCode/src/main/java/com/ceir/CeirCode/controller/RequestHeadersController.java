package com.ceir.CeirCode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.UserStatusRequest;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.service.ReqHeadersService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class RequestHeadersController {

	@Autowired
	ReqHeadersService reqHeaderService;
	
	@ApiOperation(value = "save request headers", response = HttpResponse.class)
	@PostMapping("/saveRequestHeaders") 
	public ResponseEntity<?> saveRequest(@RequestBody RequestHeaders header){
		return reqHeaderService.saveRequestHeaders(header);
	} 
}
