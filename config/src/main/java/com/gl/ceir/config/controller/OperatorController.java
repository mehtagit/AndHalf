package com.gl.ceir.config.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FileDumpMgmt;
import com.gl.ceir.config.model.FileDumpFilterRequest;
import com.gl.ceir.config.service.impl.ListFileDetailsImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class OperatorController {

	@Autowired
	ListFileDetailsImpl  listFileDetailsImpl;

	@Autowired
	FileStorageProperties fileStorageProperties;

	private static final Logger logger = LogManager.getLogger(OperatorController.class);

}
