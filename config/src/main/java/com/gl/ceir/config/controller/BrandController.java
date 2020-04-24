package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.brandRepoModel;
import com.gl.ceir.config.service.impl.BrandServiceImpl;

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
