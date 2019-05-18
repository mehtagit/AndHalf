package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.Action;
import com.gl.ceir.config.model.DeviceSnapShot;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.MediationSource;
import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.service.ActionService;
import com.gl.ceir.config.service.DeviceSnapShotService;
import com.gl.ceir.config.service.MediationSourceService;
import io.swagger.annotations.ApiResponse;

@RestController
public class DeviceSnapShotController {

	@Autowired
	private DeviceSnapShotService deviceSnapShotService;

	@RequestMapping(path = "/DeviceSnapShot/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<DeviceSnapShot> deviceSnapShots = deviceSnapShotService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(deviceSnapShots);
		return mapping;
	}

	@RequestMapping(path = "/DeviceSnapShot/{id}", method = RequestMethod.POST)
	public MappingJacksonValue get(@RequestBody ImeiMsisdnIdentity imeiMsisdnIdentity) {
		DeviceSnapShot deviceSnapShot = deviceSnapShotService.get(imeiMsisdnIdentity);
		MappingJacksonValue mapping = new MappingJacksonValue(deviceSnapShot);
		return mapping;
	}

	@RequestMapping(path = "/DeviceSnapShot/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody DeviceSnapShot deviceSnapShot) {
		DeviceSnapShot savedDeviceSnapShot = deviceSnapShotService.save(deviceSnapShot);
		MappingJacksonValue mapping = new MappingJacksonValue(savedDeviceSnapShot);
		return mapping;
	}

	@RequestMapping(path = "/DeviceSnapShot/", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "imeiMsisdnIdentity") ImeiMsisdnIdentity imeiMsisdnIdentity) {
		deviceSnapShotService.delete(imeiMsisdnIdentity);
	}

	@RequestMapping(path = "/DeviceSnapShot/", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") Long id, @RequestBody DeviceSnapShot deviceSnapShot) {
		DeviceSnapShot updatedDeviceSnapShot = deviceSnapShotService.update(deviceSnapShot);
		MappingJacksonValue mapping = new MappingJacksonValue(updatedDeviceSnapShot);
		return mapping;
	}
}
