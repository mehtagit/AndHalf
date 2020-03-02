package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.model.ImeiMsisdnIdentity;
import com.gl.ceir.config.model.WhiteList;
import com.gl.ceir.config.service.WhiteListService;

import io.swagger.annotations.ApiOperation;

@RestController
public class WhiteListController {

	@Autowired
	private WhiteListService whiteListService;

	@ApiOperation(value = "View available White List Device ", response = WhiteList.class)
	@RequestMapping(path = "/WhiteList/", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdnAndImei(@RequestParam(required = false) Long msisdn,
			@RequestParam(required = false) String imei) {
		ImeiMsisdnIdentity imeiMsisdnIdentity = new ImeiMsisdnIdentity();
		imeiMsisdnIdentity.setMsisdn(msisdn);
		imeiMsisdnIdentity.setImei(imei);
		return getByMsisdnAndImei(imeiMsisdnIdentity);
	}

	public MappingJacksonValue getByMsisdnAndImei(@RequestBody ImeiMsisdnIdentity imeiMsisdnIdentity) {

		if (imeiMsisdnIdentity.getMsisdn() == null && imeiMsisdnIdentity.getImei() == null) {
			List<WhiteList> allWhiteList = whiteListService.getAll();
			MappingJacksonValue mapping = new MappingJacksonValue(allWhiteList);
			return mapping;
		}

		WhiteList whiteList = whiteListService.getByMsisdnAndImei(imeiMsisdnIdentity);
		if (whiteList == null)
			throw new ResourceNotFoundException("White List", "msisdn and imei",
					imeiMsisdnIdentity.getMsisdn() + " and " + imeiMsisdnIdentity.getImei());
		else
			return new MappingJacksonValue(whiteList);
	}

//	@ApiOperation(value = "Save new Device in White List ", response = WhiteList.class)
//	@RequestMapping(path = "/WhiteList/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody WhiteList whiteList) {
		WhiteList savedWhiteList = whiteListService.save(whiteList);
		MappingJacksonValue mapping = new MappingJacksonValue(savedWhiteList);
		return mapping;
	}

//	@ApiOperation(value = "Delete a Device from VIP List ")
//	@RequestMapping(path = "/WhiteList/", method = RequestMethod.DELETE)
	public void deleteByMsisdnAndImei(@RequestBody ImeiMsisdnIdentity imeiMsisdnIdentity) {
		whiteListService.deleteByMsisdnAndImei(imeiMsisdnIdentity);
	}

}
