package com.gl.ceir.config.factory.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.DeviceImporterDb;
import com.gl.ceir.config.model.constants.Constants;
import com.gl.ceir.config.repository.DeviceImporterDbRepository;

@Component
public class CustomerCareImporter implements CustomerCareTarget{
	
	@Autowired
	DeviceImporterDbRepository deviceImporterDbRepository;
	
	@Override
	public CustomerCareDeviceState fetchDetailsByImei(String imei, CustomerCareDeviceState customerCareDeviceState) {
		
		DeviceImporterDb deviceDb = deviceImporterDbRepository.getByImeiEsnMeid(imei);
		
		if(Objects.nonNull(deviceDb)) {
			customerCareDeviceState.setTxnId(deviceDb.getTxnId());
			customerCareDeviceState.setDate(deviceDb.getCreatedOn().toString());
			customerCareDeviceState.setStatus(Constants.available);
			customerCareDeviceState.setFeatureId(deviceDb.getFeatureId());
		}else {
			customerCareDeviceState.setDate("");
			customerCareDeviceState.setStatus(Constants.non_available);
		}
		
		setName(customerCareDeviceState);
		return customerCareDeviceState;
	}

	@Override
	public void setName(CustomerCareDeviceState customerCareDeviceState) {
		customerCareDeviceState.setName("Importer");
	}

}
