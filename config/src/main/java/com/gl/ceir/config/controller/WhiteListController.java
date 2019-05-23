package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.WhiteList;
import com.gl.ceir.config.service.WhiteListService;

@RestController
public class WhiteListController {

	@Autowired
	private WhiteListService whiteListService;

	@RequestMapping(path = "/WhiteList/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<WhiteList> allWhiteList = whiteListService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(allWhiteList);
		return mapping;
	}

	@RequestMapping(path = "/WhiteList/Msisdn/{msisdn}", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdn(@PathVariable(value = "msisdn") Long msisdn) {
		WhiteList whiteList = whiteListService.getByMsisdn(msisdn);

		if (whiteList == null)
			throw new ResourceNotFoundException("White List", "msisdn", msisdn);
		else
			return new MappingJacksonValue(whiteList);

	}

	@RequestMapping(path = "/WhiteList/Imei/{imei}", method = RequestMethod.GET)
	public MappingJacksonValue getByImei(@PathVariable(value = "imei") Long imei) {
		WhiteList whiteList = whiteListService.getByImei(imei);
		if (whiteList == null)
			throw new ResourceNotFoundException("White List", "imei", imei);
		else
			return new MappingJacksonValue(whiteList);

	}

	@RequestMapping(path = "/WhiteList/MsisdnAndImei/{msisdn}/{imei}", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdnAndImei(@PathVariable(value = "msisdn") Long msisdn,
			@PathVariable(value = "imei") Long imei) {
		WhiteList whiteList = whiteListService.getByMsisdnAndImei(msisdn, imei);
		if (whiteList == null)
			throw new ResourceNotFoundException("White List", "msisdn and imei", msisdn + " and " + imei);
		else
			return new MappingJacksonValue(whiteList);
	}

	@RequestMapping(path = "/WhiteList/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody WhiteList whiteList) {
		WhiteList savedWhiteList = whiteListService.save(whiteList);
		MappingJacksonValue mapping = new MappingJacksonValue(savedWhiteList);
		return mapping;
	}

	@RequestMapping(path = "/WhiteList/MsisdnAndImei/{msisdn}/{imei}", method = RequestMethod.DELETE)
	public MappingJacksonValue deleteByMsisdnAndImei(@PathVariable(value = "msisdn") Long msisdn,
			@PathVariable(value = "imei") Long imei) {
		WhiteList whiteList = whiteListService.getByMsisdnAndImei(msisdn, imei);
		MappingJacksonValue mapping = new MappingJacksonValue(whiteList);
		return mapping;
	}

	@RequestMapping(path = "/WhiteList/", method = RequestMethod.PUT)
	public MappingJacksonValue update(@RequestBody WhiteList whiteList) {
		WhiteList updateWhiteList = whiteListService.update(whiteList);
		MappingJacksonValue mapping = new MappingJacksonValue(updateWhiteList);
		return mapping;
	}
}
