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
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.service.MobileOperatorService;

@RestController
public class MobileOperatorController {

	@Autowired
	MobileOperatorService mobileOperatorService;
	
	@GetMapping(path="/CEIR/MobileOperators/")
	public MappingJacksonValue getAllOperators()
	{
		List<MobileOperator> allOperators = mobileOperatorService.getAll();
		MappingJacksonValue mapping  = new MappingJacksonValue(allOperators);
		return mapping;
	}
	
	@GetMapping(path="/CEIR/MobileOperators/{id}/")
	public MappingJacksonValue getOperator(@PathVariable(value="id") Integer id)
	{
		MobileOperator operators = mobileOperatorService.findById(id);
		MappingJacksonValue mapping  = new MappingJacksonValue(operators);
		return mapping;
	}
	
	@PostMapping(path="/CEIR/MobileOperators/")
	public MappingJacksonValue saveOperator(@RequestBody MobileOperator mobileOperator)
	{
		MobileOperator savedOperators = mobileOperatorService.save(mobileOperator);
		MappingJacksonValue mapping  = new MappingJacksonValue(savedOperators);
		return mapping;
	}
	
	@DeleteMapping(path="/CEIR/MobileOperators/")
	public void dalateOperator(@PathVariable(value="id") Integer id)
	{
		mobileOperatorService.delete(id);
	}
	
	@PutMapping(path="/CEIR/MobileOperators/{id}/")
	public MappingJacksonValue getOperator(@PathVariable(value="id") Integer id , @RequestBody MobileOperator mobileOperator)
	{
		MobileOperator operators = mobileOperatorService.update(id, mobileOperator);
		MappingJacksonValue mapping  = new MappingJacksonValue(operators);
		return mapping;
	}
}
