package com.ceir.CEIRPostman.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CEIRPostman.RepositoryService.SystemConfigurationDbRepoImpl;
import com.ceir.CEIRPostman.model.SystemConfigurationDb;

@Service
public class EmailConfiguration {

	
@Autowired
SystemConfigurationDbRepoImpl systemConfigurationDbRepoImpl;

private final Logger log = LoggerFactory.getLogger(getClass());

public void getEmailConfiguration() {
	
	SystemConfigurationDb emailHost=systemConfigurationDbRepoImpl.getDataByTag("Email_Host");
	SystemConfigurationDb emailPort=systemConfigurationDbRepoImpl.getDataByTag("Email_Port");
	SystemConfigurationDb emailUsername=systemConfigurationDbRepoImpl.getDataByTag("Email_Username");
	SystemConfigurationDb emailPassword=systemConfigurationDbRepoImpl.getDataByTag("Email_Password");
	log.info("email host: "+emailHost.getValue());
	log.info("email port: "+emailPort.getValue());
	log.info("email username: "+emailUsername.getValue());
	log.info("email password: "+emailPassword.getValue());
	
}
}
