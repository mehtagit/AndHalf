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
import com.gl.ceir.config.model.BlackList;

import com.gl.ceir.config.service.BlackListService;

@RestController
public class BlackListController {

	@Autowired
	private BlackListService blackListService;

	@RequestMapping(path = "/BlackList/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<BlackList> allWhiteList = blackListService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(allWhiteList);
		return mapping;
	}

	@RequestMapping(path = "/BlackList/Msisdn/{msisdn}", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdn(@PathVariable(value = "msisdn") Long msisdn) {
		BlackList blackList = blackListService.getByMsisdn(msisdn);

		if (blackList == null)
			throw new ResourceNotFoundException("BLACK List", "msisdn", msisdn);
		else
			return new MappingJacksonValue(blackList);
	}

	@RequestMapping(path = "/BlackList/Imei/{imei}", method = RequestMethod.GET)
	public MappingJacksonValue getByImei(@PathVariable(value = "imei") Long imei) {
		BlackList blackList = blackListService.getByImei(imei);
		if (blackList == null)
			throw new ResourceNotFoundException("BLACK List", "imei", imei);
		else
			return new MappingJacksonValue(blackList);
	}

	@RequestMapping(path = "/BlackList/MsisdnAndImei/{msisdn}/{imei}", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdnAndImei(@PathVariable(value = "msisdn") Long msisdn,
			@PathVariable(value = "imei") Long imei) {
		BlackList blackList = blackListService.getByMsisdnAndImei(msisdn, imei);
		if (blackList == null)
			throw new ResourceNotFoundException("BLACK List", "msisdn and imei", msisdn + " and " + imei);
		else
			return new MappingJacksonValue(blackList);
	}

	@RequestMapping(path = "/BlackList/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody BlackList blackList) {
		BlackList savedWhiteList = blackListService.save(blackList);
		MappingJacksonValue mapping = new MappingJacksonValue(savedWhiteList);
		return mapping;
	}

	@RequestMapping(path = "/BlackList/MsisdnAndImei/{msisdn}/{imei}", method = RequestMethod.DELETE)
	public MappingJacksonValue deleteByMsisdnAndImei(@PathVariable(value = "msisdn") Long msisdn,
			@PathVariable(value = "imei") Long imei) {
		BlackList blackList = blackListService.getByMsisdnAndImei(msisdn, imei);
		MappingJacksonValue mapping = new MappingJacksonValue(blackList);
		return mapping;
	}

	@RequestMapping(path = "/BlackList/", method = RequestMethod.PUT)
	public MappingJacksonValue update(@RequestBody BlackList blackList) {
		BlackList updateBlackList = blackListService.update(blackList);
		MappingJacksonValue mapping = new MappingJacksonValue(updateBlackList);
		return mapping;
	}
}
