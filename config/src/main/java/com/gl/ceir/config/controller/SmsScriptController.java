package com.gl.ceir.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.MobileOperator;
import com.gl.ceir.config.model.Rules;
import com.gl.ceir.config.model.SmsAccount;
import com.gl.ceir.config.model.SmsScript;
import com.gl.ceir.config.service.RulesService;
import com.gl.ceir.config.service.SmsAccountService;
import com.gl.ceir.config.service.SmsScriptService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SmsScriptController {

	@Autowired
	private SmsScriptService smsScriptService;

	@RequestMapping(path = "/SmsScript/", method = RequestMethod.GET)
	public MappingJacksonValue getAll() {
		List<SmsScript> allRules = smsScriptService.getAll();
		MappingJacksonValue mapping = new MappingJacksonValue(allRules);
		return mapping;
	}

	@RequestMapping(path = "/SmsScript/{id}", method = RequestMethod.GET)
	public MappingJacksonValue get(@PathVariable(value = "id") Long id) {
		SmsScript smsScript = smsScriptService.get(id);
		MappingJacksonValue mapping = new MappingJacksonValue(smsScript);
		return mapping;
	}

	@RequestMapping(path = "/SmsScript/", method = RequestMethod.POST)
	public MappingJacksonValue save(@RequestBody SmsScript smsScript) {
		SmsScript savedSmsScript = smsScriptService.save(smsScript);
		MappingJacksonValue mapping = new MappingJacksonValue(savedSmsScript);
		return mapping;
	}

	@RequestMapping(path = "/SmsScript/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Long id) {
		smsScriptService.delete(id);
	}

	@RequestMapping(path = "/SmsScript/{id}", method = RequestMethod.PUT)
	public MappingJacksonValue update(@PathVariable(value = "id") Long id, @RequestBody SmsScript smsScript) {
		SmsScript updateSmsScript = smsScriptService.update(smsScript);
		MappingJacksonValue mapping = new MappingJacksonValue(updateSmsScript);
		return mapping;
	}
}
