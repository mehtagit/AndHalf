package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.DashboardConfDb;


import com.gl.ceir.config.repository.DashboardConfDbRepository;

@Service
public class DashboardConfServiceImpl {
	private static final Logger logger = LogManager.getLogger(GrievanceServiceImpl.class);
	@Autowired
	DashboardConfDbRepository dashboardConfDbRepository;
	
	public List< DashboardConfDb> getDashboardConfig( Integer userTypeId ){
		try {
			logger.info("Going to get All dashboard config List for userTypeId:["+userTypeId+"]");
			return dashboardConfDbRepository.findByUserTypeId(userTypeId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
}
