package com.ceir.CeirCode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.model.PeriodValidate;
import com.ceir.CeirCode.service.FeatureService;
import com.ceir.CeirCode.util.HttpResponse;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class FeatureController{

	@Autowired
	FeatureService featureService;

	@ApiOperation(value = "user feature list", response = HttpResponse.class)
	@PostMapping("/featureList/{userid}")     
	public ResponseEntity<?> featureList(@PathVariable Integer userid){
		return featureService.featureData(userid) ;
	} 

	@ApiOperation(value = "period validate", response = HttpResponse.class)
	@PostMapping("/periodValidate")     
	public MappingJacksonValue  periodValidate(@RequestBody PeriodValidate periodValidate){
		HttpResponse response =new HttpResponse();
		response=featureService.periodValidation(periodValidate);
		MappingJacksonValue mapping=new MappingJacksonValue(response);
		return mapping;
	} 


	@ApiOperation(value = "all features", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/getAllFeatures") 
	public ResponseEntity<?> featureData(){
		return featureService.featureData();
	}



}