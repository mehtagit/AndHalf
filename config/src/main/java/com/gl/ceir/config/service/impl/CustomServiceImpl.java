package com.gl.ceir.config.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.CustomDetails;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.DeviceDb;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.CustomDetailsRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;

@Service
public class CustomServiceImpl {


	private static final Logger logger = LogManager.getLogger(CustomServiceImpl.class);

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	CustomDetailsRepository customDetailsRepository;

	public List<ConsignmentMgmt> getCustomDetails(){
		try {
			return null;
			//consignmentRepository.getByFileStatusOrderByIdDesc("Success");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}


	public GenricResponse updateTaxPaidStatus(String txnId) {
		try {

			CustomDetails CustomDetails = new CustomDetails();

			//	consignmentRepository.updateUser("Paid","Accepted", txnId);

			//	List<DeviceDb> stokeDetails = stokeDetailsRepository.findByTxnIdAndSourceType(txnId, "Importer");


			return new GenricResponse(200, "Update Successfully.",txnId);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());}
	}








}
