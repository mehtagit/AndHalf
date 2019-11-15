package com.gl.ceir.config.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
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
import com.gl.ceir.config.model.StatesInterpretationDb;
import com.gl.ceir.config.repository.StateMgmtRepository;
import com.gl.ceir.config.repository.StatesInterpretaionRepository;
import com.gl.ceir.config.util.Utility;


@Service
public class StateMgmtServiceImpl {

	private static final Logger logger = LogManager.getLogger(StateMgmtServiceImpl.class);

	@Autowired
	private StateMgmtRepository stateMgmtRepository;

	@Autowired
	private StatesInterpretaionRepository statesInterpretaionRepository; 

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	/*
	 * 
	 * @future_scope : must use a join between state_mgmt_db and states_interpretation_db
	 */
	public List<StateMgmtDb> getByFeatureIdAndUserTypeId(Integer featureId, Integer userTypeId) {
		try {
			List<StateMgmtDb> stateMgmtDbsResult = new ArrayList<StateMgmtDb>();
			
			logger.info("Going to get states by featureId and usertypeId ");

			List<StateMgmtDb> stateMgmtDbs = stateMgmtRepository.getByFeatureIdAndUserTypeId(featureId, userTypeId);
			logger.debug(stateMgmtDbs);
			logger.debug("Instance of Linked List : " + (stateMgmtDbs instanceof LinkedList<?>));
			logger.debug("Instance of Array List : " + (stateMgmtDbs instanceof ArrayList<?>));
			
			List<StatesInterpretationDb> statesInterpretationDbs = statesInterpretaionRepository.findByFeatureId(featureId);
			logger.debug(statesInterpretationDbs);
			
			for(StatesInterpretationDb statesInterpretationDb : statesInterpretationDbs) {
				
				for(StateMgmtDb stateMgmtDb : stateMgmtDbs) {
					if(stateMgmtDb.getState().equals(statesInterpretationDb.getState())) {
						stateMgmtDb.setInterp(statesInterpretationDb.getInterp());
						stateMgmtDbsResult.add(stateMgmtDb);
					}
				}
			}

			return stateMgmtDbsResult;
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

}
