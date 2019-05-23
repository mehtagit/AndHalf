package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.service.PendingActionsService;

@RestController
public class PendingActionController {

	@Autowired
	private PendingActionsService pendingActionsService;

	@RequestMapping(path = "/PendingActions/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<PendingActions> allPendingActions = pendingActionsService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(allPendingActions);
		return mapping;
	}

	@RequestMapping(path = "/PendingActions/{ticketId}", method = RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "ticketId") String ticketId) {
		PendingActions pendingActions = pendingActionsService.get(ticketId);
		MappingJacksonValue mapping = new MappingJacksonValue(pendingActions);
		return mapping;
	}

	// @RequestMapping(path = "/PendingActions/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody PendingActions pendingActions) {
		PendingActions savedPendingActions = pendingActionsService.save(pendingActions);
		MappingJacksonValue mapping = new MappingJacksonValue(savedPendingActions);
		return mapping;
	}

	@RequestMapping(path = "/PendingActions/{ticketId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "ticketId") String ticketId) {

	}

	// @RequestMapping(path = "/PendingActions/{id}", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") String ticketId,
			@RequestBody PendingActions pendingActions) {
		PendingActions updatePendingActions = pendingActionsService.update(pendingActions);
		MappingJacksonValue mapping = new MappingJacksonValue(updatePendingActions);
		return mapping;
	}
}
