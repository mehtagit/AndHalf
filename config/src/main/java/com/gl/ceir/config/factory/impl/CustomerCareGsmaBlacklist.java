package com.gl.ceir.config.factory.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.GsmaBlackList;
import com.gl.ceir.config.model.constants.Constants;
import com.gl.ceir.config.repository.GsmaBlacklistRepository;

@Component
public class CustomerCareGsmaBlacklist implements CustomerCareTarget{
	
	@Autowired
	GsmaBlacklistRepository blackListRepository;
	
	@Override
	public CustomerCareDeviceState fetchDetailsByImei(String imei, CustomerCareDeviceState customerCareDeviceState) {
		
		GsmaBlackList blackList = blackListRepository.getByDeviceid(imei);
		
		if(Objects.nonNull(blackList)) {
			customerCareDeviceState.setTxnId("");
			customerCareDeviceState.setDate(blackList.getCreatedOn());
			customerCareDeviceState.setStatus(Constants.available);
			customerCareDeviceState.setFeatureId(0);
		}else {
			customerCareDeviceState.setDate("");
			customerCareDeviceState.setStatus(Constants.non_available);
			customerCareDeviceState.setFeatureId(0);
		}
		
		setName(customerCareDeviceState);
		return customerCareDeviceState;
	}

	@Override
	public void setName(CustomerCareDeviceState customerCareDeviceState) {
		customerCareDeviceState.setName("Global Black List");
	}

}
