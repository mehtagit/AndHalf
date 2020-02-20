package com.ceir.CeirCode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.service.PortAddressService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/portAddress")
public class PortAddressController {

	@Autowired
	PortAddressService portAddressService;
	
	@ApiOperation(value = "get data By Port", response = HttpResponse.class)
	@PostMapping("/getByPort/{arrivalPort}")
	public ResponseEntity<?> findDataByPort(@PathVariable("arrivalPort") Integer port){
		return portAddressService.getDataByPort(port);
	}
	
	
}
