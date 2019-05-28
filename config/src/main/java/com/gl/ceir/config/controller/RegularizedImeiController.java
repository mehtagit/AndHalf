package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.model.RegularizedImei;
import com.gl.ceir.config.service.RegularizedImeiSerivce;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@RestController
public class RegularizedImeiController {

	@Autowired
	private RegularizedImeiSerivce regularizedImeiSerivce;

	@RequestMapping(path = "/RegularizedImei/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<RegularizedImei> allRegularizedImeis = regularizedImeiSerivce.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(allRegularizedImeis);
		return mapping;
	}

	@RequestMapping(path = "/RegularizedImei/{id}", method = RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "id") Long id) {
		RegularizedImei regularizedImei = regularizedImeiSerivce.get(id);
		MappingJacksonValue mapping = new MappingJacksonValue(regularizedImei);
		return mapping;
	}

	@RequestMapping(path = "/RegularizedImei/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody RegularizedImei regularizedImei) {
		RegularizedImei savedRegularizedImei = regularizedImeiSerivce.save(regularizedImei);
		MappingJacksonValue mapping = new MappingJacksonValue(savedRegularizedImei);
		return mapping;
	}

	@RequestMapping(path = "/RegularizedImei/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Long id) {
		regularizedImeiSerivce.delete(id);
	}

	@RequestMapping(path = "/RegularizedImei/{id}", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") Long id,
			@RequestBody RegularizedImei regularizedImei) {
		RegularizedImei updateRegularizedImei = regularizedImeiSerivce.update(regularizedImei);
		MappingJacksonValue mapping = new MappingJacksonValue(updateRegularizedImei);
		return mapping;
	}
}
