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
import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.repository.VipListRepository;
import com.gl.ceir.config.service.BlackListService;
import com.gl.ceir.config.service.VipListService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class VipListController {

	@Autowired
	private VipListService vipListService;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfull retrieved list") })
	@RequestMapping(path = "/VipList/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<VipList> allVipList = vipListService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(allVipList);
		return mapping;
	}

	@RequestMapping(path = "/VipList/Msisdn/{msisdn}", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdn(@PathVariable(value = "msisdn") Long msisdn) {
		VipList vipList = vipListService.getByMsisdn(msisdn);

		if (vipList == null)
			throw new ResourceNotFoundException("VIP List", "msisdn", msisdn);
		else
			return new MappingJacksonValue(vipList);

	}

	@RequestMapping(path = "/VipList/Imei/{imei}", method = RequestMethod.GET)
	public MappingJacksonValue getByImei(@PathVariable(value = "imei") Long imei) {
		VipList vipList = vipListService.getByImei(imei);
		if (vipList == null)
			throw new ResourceNotFoundException("VIP List", "imei", imei);
		else
			return new MappingJacksonValue(vipList);
	}

	@RequestMapping(path = "/VipList/MsisdnAndImei/{msisdn}/{imei}", method = RequestMethod.GET)
	public MappingJacksonValue getByMsisdnAndImei(@PathVariable(value = "msisdn") Long msisdn,
			@PathVariable(value = "imei") Long imei) {
		VipList vipList = vipListService.getByMsisdnAndImei(msisdn, imei);
		if (vipList == null)
			throw new ResourceNotFoundException("VIP List", "msisdn and imei", msisdn + " and " + imei);
		else
			return new MappingJacksonValue(vipList);
	}

	@RequestMapping(path = "/VipList/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody VipList vipList) {
		VipList savedVipList = vipListService.save(vipList);
		MappingJacksonValue mapping = new MappingJacksonValue(savedVipList);
		return mapping;
	}

	@RequestMapping(path = "/VipList/MsisdnAndImei/{msisdn}/{imei}", method = RequestMethod.DELETE)
	public MappingJacksonValue deleteByMsisdnAndImei(@PathVariable(value = "msisdn") Long msisdn,
			@PathVariable(value = "imei") Long imei) {
		VipList vipList = vipListService.getByMsisdnAndImei(msisdn, imei);
		MappingJacksonValue mapping = new MappingJacksonValue(vipList);
		return mapping;
	}

	@RequestMapping(path = "/VipList/", method = RequestMethod.PUT)
	public MappingJacksonValue update(@RequestBody VipList vipList) {
		VipList updatedVipList = vipListService.update(vipList);
		MappingJacksonValue mapping = new MappingJacksonValue(updatedVipList);
		return mapping;
	}
}
