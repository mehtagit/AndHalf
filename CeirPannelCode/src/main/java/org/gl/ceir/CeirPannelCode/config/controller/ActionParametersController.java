package org.gl.ceir.CeirPannelCode.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.gl.ceir.CeirPannelCode.config.Model.ActionParameters;
import org.gl.ceir.CeirPannelCode.config.Model.MobileOperator;
import org.gl.ceir.CeirPannelCode.config.Service.ActionParametersService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ActionParametersController {

	@Autowired
	private ActionParametersService actionParametersService;

	@ApiOperation(value = "View All available Action Parameters", response = ActionParameters.class, responseContainer = "list")

	@RequestMapping(path = "/ActionParameters/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		final List<ActionParameters> actionParameters = actionParametersService.getAll();
		final MappingJacksonValue mapping = new MappingJacksonValue(actionParameters);
		return mapping;
	}

	// @RequestMapping(path = "/ActionParameters/{action_id}", method =
	// RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "action_id") final Long action_id) {
		final List<ActionParameters> actionParameters = actionParametersService.findByAction(action_id);
		final MappingJacksonValue mapping = new MappingJacksonValue(actionParameters);
		return mapping;
	}

	@ApiOperation(value = "View All available Action Parameters of particular Action Name (USER_REGULARIZED / SYSTEM_REGULARIZED)", response = ActionParameters.class, responseContainer = "list")
	@RequestMapping(path = "/ActionParameters/{action_name}", method = RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "action_name") final String action) {
		final List<ActionParameters> actionParameters = actionParametersService.findByAction(action);
		final MappingJacksonValue mapping = new MappingJacksonValue(actionParameters);
		return mapping;
	}

	// @RequestMapping(path = "/ActionParameters/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody final ActionParameters actionParameters) {
		final ActionParameters savedActionParameters = actionParametersService.save(actionParameters);
		final MappingJacksonValue mapping = new MappingJacksonValue(savedActionParameters);
		return mapping;
	}

	// @RequestMapping(path = "/ActionParameters/{id}", method =
	// RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") final Long id) {
		actionParametersService.delete(id);
	}

	// @RequestMapping(path = "/ActionParameters/{id}", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") final Long id,
			@RequestBody final ActionParameters actionParameters) {
		final ActionParameters updatedActionParameters = actionParametersService.update(actionParameters);
		final MappingJacksonValue mapping = new MappingJacksonValue(updatedActionParameters);
		return mapping;
	}
}
