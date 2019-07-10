package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.Tac;
import com.gl.ceir.config.service.TacService;

import io.swagger.annotations.ApiOperation;

@RestController
public class TacController {

	@Autowired
	private TacService tacService;

	@ApiOperation(value = "View All available Tacs ", response = Tac.class, responseContainer = "list")
	@RequestMapping(path = "/Tac/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<Tac> tacs = tacService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(tacs);
		return mapping;
	}

	@RequestMapping(path = "/Tac/{id}", method = RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "id") String id) {
		Tac tac = tacService.get(id);
		MappingJacksonValue mapping = new MappingJacksonValue(tac);
		return mapping;
	}

	@RequestMapping(path = "/Tac/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody Tac tac) {
		Tac savedTac = tacService.save(tac);
		MappingJacksonValue mapping = new MappingJacksonValue(savedTac);
		return mapping;
	}

	// @RequestMapping(path = "/Action/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Long id) {
		tacService.delete(id);
	}

	@RequestMapping(path = "/Tac/{id}", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") String id, @RequestBody Tac tac) {
		Tac updatedTac = tacService.update(tac);
		MappingJacksonValue mapping = new MappingJacksonValue(updatedTac);
		return mapping;
	}
}
