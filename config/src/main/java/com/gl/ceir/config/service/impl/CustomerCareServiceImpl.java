package com.gl.ceir.config.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.factory.CustomerCareFactory;
import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.CustomerCareRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.util.Utility;

@Service
public class CustomerCareServiceImpl {

	private static final Logger logger = LogManager.getLogger(CustomerCareServiceImpl.class);

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	@Autowired
	UserStaticServiceImpl userStaticServiceImpl;

	@Autowired 
	CustomerCareFactory customerCareFactory;

	public GenricResponse getAll(CustomerCareRequest customerCareRequest) {
		try {
			List<CustomerCareDeviceState> customerCareDeviceStates = new LinkedList<>();

			if(Objects.nonNull(customerCareRequest.getImei()) 
					&& "IMEI".equalsIgnoreCase(customerCareRequest.getDeviceIdType())) {

				customerCareFactory.dbsList.stream().forEach( o -> {
					CustomerCareTarget customerCareTarget = customerCareFactory.getObject(o);
					if(Objects.isNull(customerCareTarget)) {
						logger.info("Corresponding object of Db [" + o + "] is not defined in the factory ");
						return;
					}
					
					// To avoid 500, if only one or few DB's have issue for the imei.
					try {
						customerCareDeviceStates.add(customerCareTarget.fetchDetailsByImei(customerCareRequest.getImei()));
					}catch (Exception e) {
						logger.error("Db [" + o + "] have some issue in fetching data for imei [" + customerCareRequest.getImei() + "] ");
					}
				});

				return new GenricResponse(0, GenericMessageTags.SUCCESS.getMessage(), "", customerCareDeviceStates);
			}else if(Objects.nonNull(customerCareRequest.getMsisdn())){
				// TODO

				return new GenricResponse(0, GenericMessageTags.SUCCESS.getMessage(), "", null);
			}else {
				return new GenricResponse(1, GenericMessageTags.INVALID_REQUEST.getMessage(), "", null);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
}
