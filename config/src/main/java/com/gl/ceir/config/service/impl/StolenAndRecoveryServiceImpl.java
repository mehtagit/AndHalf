package com.gl.ceir.config.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.StolenandRecoveryMgmt;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.repository.StolenAndRecoveryRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

@Service
public class StolenAndRecoveryServiceImpl {

	private static final Logger logger = LogManager.getLogger(StolenAndRecoveryServiceImpl.class);



	private final static String NUMERIC_STRING = "0123456789";

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	StolenAndRecoveryRepository stolenAndRecoveryRepository;

	@Autowired
	WebActionDbRepository webActionDbRepository;


	@Transactional
	public GenricResponse uploadDetails( StolenandRecoveryMgmt stolenandRecoveryDetails) {

		try {
			WebActionDb webActionDb = new WebActionDb();
			webActionDb.setFeature(stolenandRecoveryDetails.getRequestType());
			webActionDb.setSubFeature("upload");
			webActionDb.setTxnId(stolenandRecoveryDetails.getTxnId());
			webActionDb.setState(0);

			stolenAndRecoveryRepository.save(stolenandRecoveryDetails);

			webActionDbRepository.save(webActionDb);

			return new GenricResponse(0,"Upload Successfully.",stolenandRecoveryDetails.getTxnId());

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}


	}


	public List<StolenandRecoveryMgmt> getAllInfo(StolenandRecoveryMgmt stolenandRecoveryMgmt){
		try {



			return 	stolenAndRecoveryRepository.findByUserIdAndRoleType(stolenandRecoveryMgmt.getUserId(), 
					stolenandRecoveryMgmt.getRoleType());

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}	
	}


	public GenricResponse uploadMultipleStolen(List<StolenandRecoveryMgmt> stolenandRecoveryMgmt) {
		try {

			stolenAndRecoveryRepository.saveAll(stolenandRecoveryMgmt);

			for(StolenandRecoveryMgmt  request:stolenandRecoveryMgmt) {

				WebActionDb webActionDb = new WebActionDb();
				webActionDb.setState(0);
				webActionDb.setFeature(request.getRequestType());				
				webActionDb.setSubFeature("Upload");
				webActionDb.setData(request.getTxnId());

				webActionDbRepository.save(webActionDb);

			}
			return new GenricResponse(0, "Upload SucessFully", "");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}




}
