package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.service.impl.EnduserServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class EnduserController {

	private static final Logger logger = LogManager.getLogger(EnduserController.class);

	@Autowired
	EnduserServiceImpl enduserServiceImpl;

	@ApiOperation(value = "View Regularized DB of end User", response = RegularizeDeviceDb.class)
	@GetMapping("/end-user/{nid}")
	public MappingJacksonValue getEnduserByNid(@PathVariable("nid") String nid) {

		MappingJacksonValue mapping = null;

		GenricResponse genricResponse = enduserServiceImpl.endUserByNid(nid);
		mapping = new MappingJacksonValue(genricResponse);

		return mapping;
	}
}
