package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.service.MobileOperatorService;

@RestController
public class MobileOperatorController {

	@Autowired
	MobileOperatorService mobileOperatorService;

	@RequestMapping(path = "/MobileOperators/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<MobileOperator> allOperators = mobileOperatorService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(allOperators);
		return mapping;
	}

	@RequestMapping(path = "/MobileOperators/{id}", method = RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "id") Long id) {
		MobileOperator operators = mobileOperatorService.get(id);
		MappingJacksonValue mapping = new MappingJacksonValue(operators);
		return mapping;
	}

	@RequestMapping(path = "/MobileOperators/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody MobileOperator mobileOperator) {
		MobileOperator savedOperators = mobileOperatorService.save(mobileOperator);
		MappingJacksonValue mapping = new MappingJacksonValue(savedOperators);
		return mapping;
	}

	@RequestMapping(path = "/MobileOperators/", method = RequestMethod.DELETE)
	public void dalate(@PathVariable(value = "id") Long id) {
		mobileOperatorService.delete(id);
	}

	@RequestMapping(path = "/MobileOperators/", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") Integer id,
			@RequestBody MobileOperator mobileOperator) {
		MobileOperator operators = mobileOperatorService.update(mobileOperator);
		MappingJacksonValue mapping = new MappingJacksonValue(operators);
		return mapping;
	}
}
