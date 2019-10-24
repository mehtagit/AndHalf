package org.gl.ceir.CeirPannelCode.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Model.UserNotification;
import org.gl.ceir.CeirPannelCode.config.Service.UserNotificationService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserNotificationController {

	private static final Logger logger = LogManager.getLogger(UserNotificationController.class);

	@Autowired
	private UserNotificationService userNotificationService;

	public MappingJacksonValue get(String ticketId) {
		UserNotification userNotification = userNotificationService.get(ticketId);
		MappingJacksonValue mapping = new MappingJacksonValue(userNotification);
		return mapping;
	}

	@ApiOperation(value = "View UserNotification by Msisdn or/and IMEI / Ticket id", response = UserNotification.class)
	@RequestMapping(path = "/UserNotification/", method = RequestMethod.GET)
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

		List<UserNotification> userNotification = userNotificationService.getByMsisdnAndImei(imeiMsisdnIdentity);
		if (userNotification == null)
			throw new ResourceNotFoundException("UserNotification ", "msisdn and imei",
					imeiMsisdnIdentity.getMsisdn() + " and " + imeiMsisdnIdentity.getImei());
		else
			return new MappingJacksonValue(userNotification);
	}

}
