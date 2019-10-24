package org.gl.ceir.CeirPannelCode.config.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceNotFoundException;
import org.gl.ceir.CeirPannelCode.config.Model.BlackList;
import org.gl.ceir.CeirPannelCode.config.Model.DeviceSnapShot;
import org.gl.ceir.CeirPannelCode.config.Model.ImeiMsisdnIdentity;
import org.gl.ceir.CeirPannelCode.config.Service.DeviceSnapShotService;

import io.swagger.annotations.ApiOperation;
 
@RestController
public class DeviceSnapShotController {

	private static final Logger logger = LogManager.getLogger(DeviceSnapShotController.class);

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@ApiOperation(value = "View available DeviceSnapShot ", response = BlackList.class)
	@RequestMapping(path = "/DeviceSnapShot/{imei}", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdnAndImei(@PathVariable("imei") Long imei) {
		ImeiMsisdnIdentity imeiMsisdnIdentity = new ImeiMsisdnIdentity();
		imeiMsisdnIdentity.setMsisdn(0L);
		imeiMsisdnIdentity.setImei(imei);
		return getByMsisdnAndImei(imeiMsisdnIdentity);
	}

	public MappingJacksonValue getByMsisdnAndImei(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		logger.info("get BlackList Method Calling " + imeiMsisdnIdentity);

		DeviceSnapShot deviceSnapShots = deviceSnapShotService.get(imeiMsisdnIdentity.getImei());
		if (deviceSnapShots == null)
			throw new ResourceNotFoundException("BLACK List", "msisdn and imei",
					imeiMsisdnIdentity.getMsisdn() + " and " + imeiMsisdnIdentity.getImei());
		else
			return new MappingJacksonValue(deviceSnapShots);
	}

}
