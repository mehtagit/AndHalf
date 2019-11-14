package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.repository.StateMgmtRepository;
import com.gl.ceir.config.util.Utility;


@Service
public class StateMgmtServiceImpl {

	private static final Logger logger = LogManager.getLogger(StateMgmtServiceImpl.class);

	@Autowired
	private StateMgmtRepository stateMgmtRepository;

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	/*
	 * @RequestParam = 0 - Return states
	 * @RequestParam = 1 - Return description of states.
	 */
	public List<StateMgmtDb> getByFeatureIdAndUserTypeId(Integer featureId, Integer userTypeId, Integer returnType) {
		try {
			
			logger.info("Going to get states by featureId and usertypeId ");
			List<StateMgmtDb> stateMgmtDbs = stateMgmtRepository.getByFeatureIdAndUserTypeId(featureId, userTypeId);
			
			return stateMgmtDbs;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
}
