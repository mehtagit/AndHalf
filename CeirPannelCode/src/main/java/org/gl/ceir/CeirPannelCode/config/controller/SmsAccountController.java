package org.gl.ceir.CeirPannelCode.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.gl.ceir.CeirPannelCode.config.Model.MobileOperator;
import org.gl.ceir.CeirPannelCode.config.Model.Rules;
import org.gl.ceir.CeirPannelCode.config.Model.SmsAccount;
import org.gl.ceir.CeirPannelCode.config.Service.RulesService;
import org.gl.ceir.CeirPannelCode.config.Service.SmsAccountService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SmsAccountController {

	@Autowired
	private SmsAccountService smsAccountService;

	@RequestMapping(path = "/SmsAccount/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<SmsAccount> allRules = smsAccountService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(allRules);
		return mapping;
	}

	@RequestMapping(path = "/SmsAccount/{id}", method = RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "id") Long id) {
		SmsAccount smsAccount = smsAccountService.get(id);
		MappingJacksonValue mapping = new MappingJacksonValue(smsAccount);
		return mapping;
	}

	@RequestMapping(path = "/SmsAccount/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody SmsAccount smsAccount) {
		SmsAccount savedSmsAccount = smsAccountService.save(smsAccount);
		MappingJacksonValue mapping = new MappingJacksonValue(savedSmsAccount);
		return mapping;
	}

	@RequestMapping(path = "/SmsAccount/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Long id) {
		smsAccountService.delete(id);
	}

	@RequestMapping(path = "/SmsAccount/{id}", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") Long id, @RequestBody SmsAccount smsAccount) {
		SmsAccount updateSmsAccount = smsAccountService.update(smsAccount);
		MappingJacksonValue mapping = new MappingJacksonValue(updateSmsAccount);
		return mapping;
	}
}
