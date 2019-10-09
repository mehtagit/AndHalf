package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.StackholderPolicyMapping;
import com.gl.ceir.config.repository.StackholderPolicyMappingRepository;

@Service
public class StackholderPolicyMappingServiceImpl {

	private static final Logger logger = LogManager.getLogger(StackholderPolicyMappingServiceImpl.class);



	@Autowired
	StackholderPolicyMappingRepository stackholderPolicyMappingRepository;





	public StackholderPolicyMapping getBlackListConfigDetails() {
		try {
			return stackholderPolicyMappingRepository.getByListType("BlackList");

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}



	public List<StackholderPolicyMapping> getFileControllingDetails(){

		return 	stackholderPolicyMappingRepository.findByListTypeOrListType("BlackList", "GreyList");


	}	


}
