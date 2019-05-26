package com.gl.ceir.config.controller;

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

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.BlackList;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.service.DeviceSnapShotService;

import io.swagger.annotations.ApiOperation;

@RestController
public class DeviceSnapShotController {

	private static final Logger logger = LogManager.getLogger(DeviceSnapShotController.class);

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@ApiOperation(value = "View available DeviceSnapShot ", response = BlackList.class)
	@RequestMapping(path = "/DeviceSnapShot/", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdnAndImei(@RequestParam(required = false) Long msisdn,
			@RequestParam(required = false) Long imei) {
		ImeiMsisdnIdentity imeiMsisdnIdentity = new ImeiMsisdnIdentity();
		imeiMsisdnIdentity.setMsisdn(msisdn);
		imeiMsisdnIdentity.setImei(imei);
		return getByMsisdnAndImei(imeiMsisdnIdentity);
	}

	public MappingJacksonValue getByMsisdnAndImei(@RequestBody ImeiMsisdnIdentity imeiMsisdnIdentity) {
		logger.info("get BlackList Method Calling " + imeiMsisdnIdentity);

		List<DeviceSnapShot> deviceSnapShots = deviceSnapShotService.get(imeiMsisdnIdentity);
		if (deviceSnapShots == null)
			throw new ResourceNotFoundException("BLACK List", "msisdn and imei",
					imeiMsisdnIdentity.getMsisdn() + " and " + imeiMsisdnIdentity.getImei());
		else
			return new MappingJacksonValue(deviceSnapShots);
	}

}
