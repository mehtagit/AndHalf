package com.gl.ceir.config.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AlertDb;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RunningAlertDb;
import com.gl.ceir.config.repository.AlertDbRepository;
import com.gl.ceir.config.repository.RunningAlertDbRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.util.InterpSetter;

@Service
public class AlertServiceImpl {

	private static final Logger logger = LogManager.getLogger(AlertServiceImpl.class);

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl; 

	@Autowired
	UserProfileRepository userProfileRepository;

	@Autowired
	InterpSetter interpSetter;
	
	@Autowired
	AlertDbRepository alertDbRepository;
	
	@Autowired
	RunningAlertDbRepository runningAlertDbRepository;

	public GenricResponse raiseAnAlert(String alertId, int userId) {

		try {
			AlertDb alertDb = alertDbRepository.getByAlertId(alertId);
			runningAlertDbRepository.save(new RunningAlertDb(userId, alertId, alertDb.getDescription(), 0));
			return new GenricResponse(0);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}