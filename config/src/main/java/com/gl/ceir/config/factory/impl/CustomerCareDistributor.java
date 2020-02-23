package com.gl.ceir.config.factory.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.DeviceDistributerDb;
import com.gl.ceir.config.model.constants.Constants;
import com.gl.ceir.config.repository.DeviceDistributorDbRepository;

@Component
public class CustomerCareDistributor implements CustomerCareTarget{
	
	@Autowired
	DeviceDistributorDbRepository deviceDistributorDbRepository;
	
	@Override
	public CustomerCareDeviceState fetchDetailsByImei(String imei, CustomerCareDeviceState customerCareDeviceState) {
		
		DeviceDistributerDb deviceDistributerDb = deviceDistributorDbRepository.getByImeiEsnMeid(imei);
		
		if(Objects.nonNull(deviceDistributerDb)) {
			customerCareDeviceState.setTxnId(deviceDistributerDb.getDistributerTxnId());
			customerCareDeviceState.setDate(deviceDistributerDb.getCreatedOn().toString());
			customerCareDeviceState.setStatus(Constants.available);
		}else {
			customerCareDeviceState.setDate("");
			customerCareDeviceState.setStatus(Constants.non_available);
		}
		
		setName(customerCareDeviceState);
		return customerCareDeviceState;
	}

	@Override
	public void setName(CustomerCareDeviceState customerCareDeviceState) {
		customerCareDeviceState.setName("Distributor");
	}

}
