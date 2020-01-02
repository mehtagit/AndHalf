package com.gl.CEIR.FileProcess.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.model.constants.ConsignmentStatus;
import com.gl.CEIR.FileProcess.model.entity.ConsignmentMgmt;
import com.gl.CEIR.FileProcess.model.entity.WebActionDb;
import com.gl.CEIR.FileProcess.repository.ConsignmentRepository;

@Component
public class ConsignmentDao {

	@Autowired
	ConsignmentRepository consignmentRepository;
	
	public ConsignmentMgmt setProcessing(WebActionDb webActionDb) {
		// Set WebAction state as Processing(1).
		ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(webActionDb.getTxnId());
		consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PROCESSING.getCode());
		return consignmentRepository.save(consignmentMgmt);
	}

}
