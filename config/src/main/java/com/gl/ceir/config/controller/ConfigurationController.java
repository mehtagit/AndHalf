package com.gl.ceir.config.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.MessageConfigurationDb;
import com.gl.ceir.config.model.Notification;
import com.gl.ceir.config.model.PolicyConfigurationDb;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.service.impl.ConfigurationManagementServiceImpl;

import io.swagger.annotations.ApiOperation;



@RestController
public class ConfigurationController {

	private static final Logger logger = LogManager.getLogger(ConfigurationController.class);

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;


	@ApiOperation(value = "System Config view All Data", response = SystemConfigurationDb.class)
	@RequestMapping(path = "/system/viewAll", method = RequestMethod.POST)
	public MappingJacksonValue findSystemDetails() {

		logger.info("Request to get system all details");

		List<SystemConfigurationDb>  pocessDetails = configurationManagementServiceImpl.getAllInfo();

		MappingJacksonValue mapping = new MappingJacksonValue(pocessDetails);

		logger.info("Response to send="+pocessDetails);

		return mapping;
	}



	@ApiOperation(value = "System Config view Data using Tag", response = SystemConfigurationDb.class)
	@RequestMapping(path = "/system/viewTag", method = RequestMethod.POST)
	public MappingJacksonValue findSystemDetailsByTag(@RequestBody SystemConfigurationDb systemConfigurationDb) {

		logger.info("Details Get by system config Tag="+systemConfigurationDb);

		SystemConfigurationDb  pocessDetails = configurationManagementServiceImpl.findByTag(systemConfigurationDb);

		MappingJacksonValue mapping = new MappingJacksonValue(pocessDetails);

		logger.info("Response to send="+pocessDetails);

		return mapping;
	}


	@ApiOperation(value = "System Config update Data using id", response = GenricResponse.class)
	@RequestMapping(path = "/system/update", method = RequestMethod.PUT)
	public GenricResponse updateSytem(@RequestBody SystemConfigurationDb systemConfigurationDb) {

		logger.info("Update  system config request="+systemConfigurationDb);

		GenricResponse GenricResponse =	configurationManagementServiceImpl.updateSystemInfo(systemConfigurationDb);

		logger.info("Update sytem config response="+GenricResponse);

		return GenricResponse;
	}


	@ApiOperation(value = "Message Config view All Data", response = MessageConfigurationDb.class)
	@RequestMapping(path = "/message/viewAll", method = RequestMethod.POST)
	public MappingJacksonValue findMessageDetails() {

		logger.info("Details Get by Message config ");

		List<MessageConfigurationDb>  pocessDetails = configurationManagementServiceImpl.getMessageConfigAllDetails();

		MappingJacksonValue mapping = new MappingJacksonValue(pocessDetails);

		logger.info("Response to send="+pocessDetails);

		return mapping;

	}


	@ApiOperation(value = "Message Config view  Data by Tag", response = MessageConfigurationDb.class)
	@RequestMapping(path = "/message/viewTag", method = RequestMethod.POST)
	public MappingJacksonValue findMessageDetailsByTag(@RequestBody MessageConfigurationDb messageConfigurationDb) {

		logger.info("Details Get by Message config="+messageConfigurationDb);

		MessageConfigurationDb  pocessDetails = configurationManagementServiceImpl.getMessageConfigDetailsByTag(messageConfigurationDb);

		MappingJacksonValue mapping = new MappingJacksonValue(pocessDetails);

		logger.info("Response to send="+pocessDetails);

		return mapping;
	}

	@ApiOperation(value = "Message Config update Data using id", response = GenricResponse.class)
	@RequestMapping(path = "/message/update", method = RequestMethod.PUT)
	public GenricResponse updateMessage(@RequestBody MessageConfigurationDb messageConfigurationDb) {

		logger.info("Update message config request="+messageConfigurationDb);

		GenricResponse GenricResponse =	configurationManagementServiceImpl.updateMessageInfo(messageConfigurationDb);

		logger.info("Update sytem config response="+GenricResponse);

		return GenricResponse;
	}

	@ApiOperation(value = "Policy Config view  Data by Tag", response = PolicyConfigurationDb.class)
	@RequestMapping(path = "/policy/viewTag", method = RequestMethod.POST)
	public MappingJacksonValue findPolicyDetailsByTag(@RequestBody PolicyConfigurationDb messageConfigurationDb) {

		logger.info("Details Get by Message config="+messageConfigurationDb);

		PolicyConfigurationDb  pocessDetails = configurationManagementServiceImpl.getPolicyConfigDetailsByTag(messageConfigurationDb);
		MappingJacksonValue mapping = new MappingJacksonValue(pocessDetails);
		logger.info("Response to send="+pocessDetails);

		return mapping;
	}


	@ApiOperation(value = "Policy Config view All Data ", response = PolicyConfigurationDb.class)
	@RequestMapping(path = "/policy/viewAll", method = RequestMethod.POST)
	public MappingJacksonValue findPolicyDetails() {

		logger.info("Details Get by Message config");

		List<PolicyConfigurationDb>  pocessDetails = configurationManagementServiceImpl.getPolicyConfigDetails();

		MappingJacksonValue mapping = new MappingJacksonValue(pocessDetails);

		logger.info("Response to send="+pocessDetails);

		return mapping;
	}

	@ApiOperation(value = "Policy Config update Data using id", response = GenricResponse.class)
	@RequestMapping(path = "/policy/update", method = RequestMethod.PUT)
	public GenricResponse updatePolicy(@RequestBody PolicyConfigurationDb policyConfigurationDb) {

		logger.info("Update message config request="+policyConfigurationDb);

		GenricResponse GenricResponse =	configurationManagementServiceImpl.updatePolicyInfo(policyConfigurationDb);

		logger.info("Update sytem config response="+GenricResponse);

		return GenricResponse;
	}


	@ApiOperation(value = "Audit trail save data", response = GenricResponse.class)
	@RequestMapping(path = "/audit/save", method = RequestMethod.POST)
	public GenricResponse saveAudit(@RequestBody AuditTrail auditTrail) {

		logger.info("Audit trail request to save the data="+auditTrail);

		GenricResponse genricResponse = configurationManagementServiceImpl.saveAudit(auditTrail);

		logger.info("Response to send ="+genricResponse);

		return genricResponse;
	}



	@ApiOperation(value = " save Notification data", response = GenricResponse.class)
	@RequestMapping(path = "/notification/save", method = RequestMethod.POST)
	public GenricResponse saveNotification(@RequestBody Notification notification) {

		logger.info("Notification save request to save the data="+notification);

		GenricResponse genricResponse = configurationManagementServiceImpl.saveNotification(notification);

		logger.info("Response to send ="+genricResponse);

		return genricResponse;
	}

	@ApiOperation(value = "System Config List DB - view All Data", response = SystemConfigListDb.class)
	@GetMapping("/system-config-list/{tag}")
	public MappingJacksonValue findSystemConfigListByTag(@PathVariable("tag") String tag) {

		logger.info("Request to get system all details");

		List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag(tag);

		MappingJacksonValue mapping = new MappingJacksonValue(systemConfigListDbs);

		logger.info("Response to send = " + systemConfigListDbs);

		return mapping;
	}


}
