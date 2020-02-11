package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.brandRepoModel;
import com.gl.ceir.config.service.impl.BrandServiceImpl;
import com.gl.ceir.config.service.impl.ConsignmentServiceImpl;
import com.gl.ceir.config.service.impl.StackholderPolicyMappingServiceImpl;
import com.gl.ceir.config.service.impl.StolenAndRecoveryServiceImpl;
import com.gl.ceir.config.util.Utility;

import io.swagger.annotations.ApiOperation;

@RestController
public class BrandController {
	
	private static final Logger logger = LogManager.getLogger(BrandController.class);

	@Autowired
	BrandServiceImpl brandServiceImpl;

	@ApiOperation(value="View All list of Brands", response = brandRepoModel.class)
	@RequestMapping(path = "gsma/brandName", method = RequestMethod.GET)
	public MappingJacksonValue getAllBrands() {
	List<brandRepoModel> getBrands = brandServiceImpl.getAllBrands();
	MappingJacksonValue mapping = new MappingJacksonValue(getBrands);
	logger.info("Response of View ="+mapping);
	return mapping;
}
}
