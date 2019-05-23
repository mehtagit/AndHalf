package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.ActionParameters;
import com.gl.ceir.config.service.ActionParametersService;

@RestController
public class ActionParametersController {

	@Autowired
	private ActionParametersService actionParametersService;

	// based on action get getall

	// @RequestMapping(path = "/ActionParameters/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<ActionParameters> actionParameters = actionParametersService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(actionParameters);
		return mapping;
	}

	@RequestMapping(path = "/ActionParameters/{action_id}", method = RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "action_id") Long action_id) {
		List<ActionParameters> actionParameters = actionParametersService.findByAction(action_id);
		MappingJacksonValue mapping = new MappingJacksonValue(actionParameters);
		return mapping;
	}

	@RequestMapping(path = "/ActionParameters/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody ActionParameters actionParameters) {
		ActionParameters savedActionParameters = actionParametersService.save(actionParameters);
		MappingJacksonValue mapping = new MappingJacksonValue(savedActionParameters);
		return mapping;
	}

	// @RequestMapping(path = "/ActionParameters/{id}", method =
	// RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Long id) {
		actionParametersService.delete(id);
	}

	// @RequestMapping(path = "/ActionParameters/{id}", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") Long id,
			@RequestBody ActionParameters actionParameters) {
		ActionParameters updatedActionParameters = actionParametersService.update(actionParameters);
		MappingJacksonValue mapping = new MappingJacksonValue(updatedActionParameters);
		return mapping;
	}
}
