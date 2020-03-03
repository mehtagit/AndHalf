package com.gl.ceir.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.entity.AlertDb;
import com.gl.ceir.pojo.GenricResponse;
import com.gl.ceir.repo.AlertDbRepository;
import com.gl.ceir.repo.RunningAlertDb;
import com.gl.ceir.repo.RunningAlertDbRepository;

@Service
public class AlertServiceImpl {

	private static final Logger logger = LogManager.getLogger(AlertServiceImpl.class);
	
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
			return new GenricResponse(1);
		}
	}

}