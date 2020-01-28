package com.ceir.CeirCode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceir.CeirCode.service.FeatureService;
import com.ceir.CeirCode.util.HttpResponse;
import io.swagger.annotations.ApiOperation;

@RestController
public class FeatureController{
	
	@Autowired
	FeatureService featureService;

	@ApiOperation(value = "user Login", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/featureList/{userid}")     
	public ResponseEntity<?> featureList(@PathVariable Integer userid){
		return featureService.featureData(userid) ;
	}  
}