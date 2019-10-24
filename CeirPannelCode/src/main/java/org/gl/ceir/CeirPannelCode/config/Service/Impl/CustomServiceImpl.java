package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.exceptions.ResourceServicesException;
import org.gl.ceir.CeirPannelCode.config.Model.Consignment;
import org.gl.ceir.CeirPannelCode.config.Model.CustomDetails;
import org.gl.ceir.CeirPannelCode.config.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.config.Model.StokeDetails;
import org.gl.ceir.CeirPannelCode.config.Repository.ConsignmentRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.CustomDetailsRepository;
import org.gl.ceir.CeirPannelCode.config.Repository.StokeDetailsRepository;

@Service
public class CustomServiceImpl {


	private static final Logger logger = LogManager.getLogger(CustomServiceImpl.class);

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	CustomDetailsRepository customDetailsRepository;

	public List<Consignment> getCustomDetails(){
		try {
			return	consignmentRepository.getByFileStatusOrderByIdDesc("Success");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}


	public GenricResponse updateTaxPaidStatus(String txnId) {
		try {

			CustomDetails CustomDetails = new CustomDetails();

			consignmentRepository.updateUser("Paid","Accepted", txnId);

			List<StokeDetails> stokeDetails = stokeDetailsRepository.findByTxnIdAndSourceType(txnId, "Importer");

			for(StokeDetails details : stokeDetails) {
				CustomDetails.setImei(details.getImei());
				CustomDetails.setSourceType("Importer");
				CustomDetails.setCreatedOn(new Date());
				CustomDetails.setUpdatedOn(new Date());

				customDetailsRepository.save(CustomDetails);
			}

			return new GenricResponse(200, "Update Successfully.");


		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());}
	}








}
