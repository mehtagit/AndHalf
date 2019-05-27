package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.PendingActions;
import com.gl.ceir.config.service.PendingActionsService;

import io.swagger.annotations.ApiOperation;

@RestController
public class PendingActionController {

	private static final Logger logger = LogManager.getLogger(PendingActionController.class);

	@Autowired
	private PendingActionsService pendingActionsService;

	public MappingJacksonValue get(String ticketId) {
		PendingActions pendingActions = pendingActionsService.get(ticketId);
		MappingJacksonValue mapping = new MappingJacksonValue(pendingActions);
		return mapping;
	}

	@ApiOperation(value = "View PendingActions by Msisdn or/and IMEI ", response = PendingActions.class)
	@RequestMapping(path = "/PendingActions/", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdnAndImei(@RequestParam(required = false) Long msisdn,
			@RequestParam(required = false) Long imei, @RequestParam(required = false) String ticketId) {
		if (ticketId == null || "".equals(ticketId)) {
			ImeiMsisdnIdentity imeiMsisdnIdentity = new ImeiMsisdnIdentity();
			imeiMsisdnIdentity.setMsisdn(msisdn);
			imeiMsisdnIdentity.setImei(imei);
			return getByMsisdnAndImei(imeiMsisdnIdentity);
		} else {
			return get(ticketId);
		}
	}

	public MappingJacksonValue getByMsisdnAndImei(@RequestBody ImeiMsisdnIdentity imeiMsisdnIdentity) {

		if (imeiMsisdnIdentity == null
				|| (imeiMsisdnIdentity.getMsisdn() == null && imeiMsisdnIdentity.getImei() == null)) {
			List<PendingActions> allWhiteList = pendingActionsService.getAll();
			MappingJacksonValue mapping = new MappingJacksonValue(allWhiteList);
			return mapping;
		}

		PendingActions pendingActions = pendingActionsService.getByMsisdnAndImei(imeiMsisdnIdentity);
		if (pendingActions == null)
			throw new ResourceNotFoundException("Pending Actions", "msisdn and imei",
					imeiMsisdnIdentity.getMsisdn() + " and " + imeiMsisdnIdentity.getImei());
		else
			return new MappingJacksonValue(pendingActions);
	}

	// @RequestMapping(path = "/PendingActions/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody PendingActions pendingActions) {
		PendingActions savedPendingActions = pendingActionsService.save(pendingActions);
		MappingJacksonValue mapping = new MappingJacksonValue(savedPendingActions);
		return mapping;
	}

	// @RequestMapping(path = "/PendingActions/{ticketId}", method =
	// RequestMethod.DELETE)
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
