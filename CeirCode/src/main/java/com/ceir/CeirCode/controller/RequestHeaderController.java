package com.ceir.CeirCode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.service.ReqHeadersService;

@RestController
@CrossOrigin
public class RequestHeaderController {

	@Autowired
	ReqHeadersService reqHeadersService;

	@PostMapping("/userHeaders")
	public ResponseEntity<?> reqHeaders(@RequestBody RequestHeaders header){
		return reqHeadersService.saveRequestHeaders(header);
	}

}
