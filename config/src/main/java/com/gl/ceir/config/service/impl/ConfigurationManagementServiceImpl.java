package com.gl.ceir.config.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ConfigurationManagement;
import com.gl.ceir.config.repository.ConfigurationManagementRepository;

@Service
public class ConfigurationManagementServiceImpl {


	private static final Logger logger = LogManager.getLogger(ConfigurationManagementServiceImpl.class);

	@Autowired
	ConfigurationManagementRepository configurationManagementRepository;

	


	
	




}
