package com.gl.ceir.config.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.StockManagementRepository;

@Service
public class TestServiceImpl {

	private static final Logger logger = LogManager.getLogger(TestServiceImpl.class);
	
	@Autowired
	private ConsignmentRepository consignmentRepository;

	@Autowired
	StockManagementRepository stockManagementRepository;
	
	public GenricResponse updateStatus(int featureId, String txnId, int Status) {
		try {
			if(featureId == 3)	{
				ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(txnId);
				consignmentMgmt.setConsignmentStatus(Status);
				consignmentRepository.save(consignmentMgmt);
				
				return new GenricResponse(0, "Status of Consignment have been updated successfully.", txnId);
			}else if(featureId == 4) {
				StockMgmt stockMgmt = stockManagementRepository.getByTxnId(txnId);
				stockMgmt.setStockStatus(Status);
				stockManagementRepository.save(stockMgmt);
				return new GenricResponse(0, "Status of Stock have been updated successfully.", txnId);
			}else {
				return new GenricResponse(0, "Feature [" + featureId + "] is not supported.", txnId);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
}
