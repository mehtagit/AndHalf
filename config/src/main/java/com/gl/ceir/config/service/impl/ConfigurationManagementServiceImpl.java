package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.MessageConfigurationDb;
import com.gl.ceir.config.model.MessageConfigurationHistoryDb;
import com.gl.ceir.config.model.PolicyConfigurationDb;
import com.gl.ceir.config.model.PolicyConfigurationHistoryDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.SystemConfigurationHistoryDb;
import com.gl.ceir.config.repository.MessageConfigurationDbRepository;
import com.gl.ceir.config.repository.MessageConfigurationHistoryDbRepository;
import com.gl.ceir.config.repository.PolicyConfigurationDbRepository;
import com.gl.ceir.config.repository.PolicyConfigurationHistoryDbRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.SystemConfigurationHistoryDbRepository;

@Service
public class ConfigurationManagementServiceImpl {


	private static final Logger logger = LogManager.getLogger(ConfigurationManagementServiceImpl.class);

	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;

	@Autowired
	MessageConfigurationDbRepository messageConfigurationDbRepository;

	@Autowired
	PolicyConfigurationDbRepository policyConfigurationDbRepository;

	@Autowired
	SystemConfigurationHistoryDbRepository systemConfigurationHistoryDbRepository;

	@Autowired
	MessageConfigurationHistoryDbRepository messageConfigurationHistoryDbRepository;

	@Autowired
	PolicyConfigurationHistoryDbRepository policyConfigurationHistoryDbRepository;



	public List<SystemConfigurationDb> getAllInfo(){
		try {

			return systemConfigurationDbRepository.findAll();

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}




	public SystemConfigurationDb findByTag(SystemConfigurationDb systemConfigurationDb){
		try {

			return systemConfigurationDbRepository.getByTag(systemConfigurationDb.getTag());

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	public GenricResponse updateSystemInfo(SystemConfigurationDb systemConfigurationDb) {
		try {

			SystemConfigurationDb idDetails = systemConfigurationDbRepository.getById(systemConfigurationDb.getId());

			if(idDetails == null) {

				return new GenricResponse(15, "This Id does not exist", "");
			}

			SystemConfigurationHistoryDb syc = new SystemConfigurationHistoryDb();
			syc.setDescription(idDetails.getDescription());
			syc.setTag(idDetails.getTag());
			syc.setValue(idDetails.getValue());


			systemConfigurationHistoryDbRepository.save(syc);

			systemConfigurationDb.setTag(idDetails.getTag());
			systemConfigurationDb.setCreatedOn(idDetails.getCreatedOn());
			systemConfigurationDbRepository.save(systemConfigurationDb);

			return new GenricResponse(0, "System configuration update Sucessfully", "");

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());	
		}
	}



	public List<MessageConfigurationDb> getMessageConfigAllDetails(){

		try {

			return messageConfigurationDbRepository.findAll();

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	

	}



	public MessageConfigurationDb getMessageConfigDetailsByTag(MessageConfigurationDb messageConfigurationDb){
		try {

			return messageConfigurationDbRepository.getByTag(messageConfigurationDb.getTag());

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}


	public GenricResponse updateMessageInfo(MessageConfigurationDb messageConfigurationDb) {
		try {

			MessageConfigurationDb mcd = messageConfigurationDbRepository.getById(messageConfigurationDb.getId());
			if(mcd == null) {
				return new GenricResponse(15, "This id does not exist","");
			}
			MessageConfigurationHistoryDb mshb = new MessageConfigurationHistoryDb();
			mshb.setDescription(mcd.getDescription());
			mshb.setTag(mcd.getTag());
			mshb.setValue(mcd.getValue());

			messageConfigurationHistoryDbRepository.save(mshb);

			messageConfigurationDb.setCreatedOn(mcd.getCreatedOn());
			messageConfigurationDb.setTag(mcd.getTag());
			messageConfigurationDbRepository.save(messageConfigurationDb);

			return new  GenricResponse(0, "Message config info update sucessfully", "");

		} catch (Exception e) {

			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());	
		}
	}



	public PolicyConfigurationDb getPolicyConfigDetailsByTag(PolicyConfigurationDb messageConfigurationDb){
		try {

			return policyConfigurationDbRepository.getByTag(messageConfigurationDb.getTag());

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}


	public List<PolicyConfigurationDb> getPolicyConfigDetails(){
		try {

			return policyConfigurationDbRepository.findAll();

		} catch (Exception e) {
			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}


	public GenricResponse updatePolicyInfo(PolicyConfigurationDb policyConfigurationDb) {
		try {

			PolicyConfigurationDb mcd = policyConfigurationDbRepository.getById(policyConfigurationDb.getId());
			if(mcd == null) {
				return new GenricResponse(15, "This id does not exist","");
			}

			PolicyConfigurationHistoryDb mshb = new PolicyConfigurationHistoryDb();

			mshb.setDescription(mcd.getDescription());
			mshb.setTag(mcd.getTag());
			mshb.setValue(mcd.getValue());

			policyConfigurationHistoryDbRepository.save(mshb);

			policyConfigurationDb.setTag(mshb.getTag());
			policyConfigurationDb.setCreatedOn(mcd.getCreatedOn());
			policyConfigurationDbRepository.save(policyConfigurationDb);

			return new  GenricResponse(0, "Policy config info update sucessfully", "");

		} catch (Exception e) {

			logger.info("Exception found="+e.getMessage());
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());	
		}
	}








}
