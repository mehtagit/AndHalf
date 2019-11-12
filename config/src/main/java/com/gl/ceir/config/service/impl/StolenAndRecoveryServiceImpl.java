package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StolenandRecoveryMgmt;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.repository.StolenAndRecoveryRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.specificationsbuilder.StolenAndRecoverySpecificationBuilder;

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


	public Page<StolenandRecoveryMgmt> getAllInfo(FilterRequest stolenandRecoveryMgmt, Integer pageNo, Integer pageSize){
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize);

			StolenAndRecoverySpecificationBuilder str =  new StolenAndRecoverySpecificationBuilder();

			if(Objects.nonNull(stolenandRecoveryMgmt.getUserId()))
				str.with(new SearchCriteria("userId", stolenandRecoveryMgmt.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(stolenandRecoveryMgmt.getRoleType()))
				str.with(new SearchCriteria("roleType", stolenandRecoveryMgmt.getRoleType(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(stolenandRecoveryMgmt.getConsignmentStatus()))
				str.with(new SearchCriteria("fileStatus", stolenandRecoveryMgmt.getConsignmentStatus(), SearchOperation.EQUALITY, Datatype.STRING));

			if(Objects.nonNull(stolenandRecoveryMgmt.getRequestType()))
				str.with(new SearchCriteria("requestType", stolenandRecoveryMgmt.getRequestType(), SearchOperation.EQUALITY, Datatype.STRING));


			return 	stolenAndRecoveryRepository.findAll(str.build(), pageable);


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
