package com.gl.ceir.config.factory.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.model.constants.Constants;
import com.gl.ceir.config.repository.RegularizedDeviceDbRepository;

@Component
public class CustomerCareEndUser implements CustomerCareTarget{

	@Autowired
	RegularizedDeviceDbRepository regularizedDeviceDbRepository;

	@Override
	public CustomerCareDeviceState fetchDetailsByImei(String imei, CustomerCareDeviceState customerCareDeviceState) {



		RegularizeDeviceDb deviceDb = regularizedDeviceDbRepository.getByImei(imei);

		if(Objects.nonNull(deviceDb)) {
			customerCareDeviceState.setTxnId(deviceDb.getTxnId());
			customerCareDeviceState.setDate(deviceDb.getCreatedOn().toString());
			customerCareDeviceState.setStatus(Constants.available);
			customerCareDeviceState.setFeatureId(12);
		}
		else {
			customerCareDeviceState.setDate("");
			customerCareDeviceState.setStatus(Constants.non_available); 
			customerCareDeviceState.setFeatureId(12);
		}
		
		customerCareDeviceState.setImei(imei);
		setName(customerCareDeviceState);

		return customerCareDeviceState;
	}

	@Override
	public void setName(CustomerCareDeviceState customerCareDeviceState) {
		customerCareDeviceState.setName("End User");
	}

}
