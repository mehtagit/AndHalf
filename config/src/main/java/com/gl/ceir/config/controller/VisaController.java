package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.EndUserDB;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.service.impl.EnduserServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class VisaController {

	private static final Logger logger = LogManager.getLogger(VisaController.class);

	@Autowired
	EnduserServiceImpl enduserServiceImpl;

	@ApiOperation(value = "Update visa of end User", response = RegularizeDeviceDb.class)
	@PutMapping("/visa/end-user")
	public MappingJacksonValue getEnduserByNid(@RequestBody EndUserDB endUserDB) {

		logger.info("A request to to update user visa : " + endUserDB);
		MappingJacksonValue mapping = null;
		GenricResponse genricResponse = enduserServiceImpl.updateVisaEndUser(endUserDB);
		logger.info("A request to to update user visa : " + endUserDB);
		
		mapping = new MappingJacksonValue(genricResponse);

		return mapping;
	}
}
