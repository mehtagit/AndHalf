package com.gl.ceir.config.factory.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.DeviceManufacturerDb;
import com.gl.ceir.config.model.constants.Constants;
import com.gl.ceir.config.repository.DeviceManufacturerDbRepository;
import com.gl.ceir.config.util.CommonFunction;

@Component
public class CustomerCareManufacturer implements CustomerCareTarget{
	
	@Autowired
	DeviceManufacturerDbRepository deviceManufacturerDbRepository;
	@Autowired
	CommonFunction commonFunction;
	
	@Override
	public CustomerCareDeviceState fetchDetailsByImei(String imei, CustomerCareDeviceState customerCareDeviceState) {
		
		DeviceManufacturerDb deviceDb = deviceManufacturerDbRepository.getByImeiEsnMeid(imei);
		
		if(Objects.nonNull(deviceDb)) {
			customerCareDeviceState.setTxnId(deviceDb.getTxnId());
			customerCareDeviceState.setDate(deviceDb.getCreatedOn().toString());
			customerCareDeviceState.setStatus(Constants.available);
			customerCareDeviceState.setFeatureId(deviceDb.getFeatureId());
			customerCareDeviceState.setFeatureId(commonFunction.getFeatureIdByTxnId(deviceDb.getTxnId()));
		}else {
			//customerCareDeviceState.setDate("");
			customerCareDeviceState.setStatus(Constants.non_available);
			//customerCareDeviceState.setFeatureId(commonFunction.getFeatureIdByTxnId(deviceDb.getTxnId()));
		}
		customerCareDeviceState.setImei(imei);
		setName(customerCareDeviceState);
		return customerCareDeviceState;
	}

	@Override
	public void setName(CustomerCareDeviceState customerCareDeviceState) {
		customerCareDeviceState.setName("Manufacturer");
	}

}
