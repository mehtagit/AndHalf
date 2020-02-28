package com.gl.ceir.config.factory.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.VipList;
import com.gl.ceir.config.model.constants.Constants;
import com.gl.ceir.config.repository.VipListRepository;

@Component
public class CustomerCareVipList implements CustomerCareTarget{
	
	@Autowired
	VipListRepository vipListRepository;
	
	@Override
	public CustomerCareDeviceState fetchDetailsByImei(String imei, CustomerCareDeviceState customerCareDeviceState) {
		
		VipList vipList = vipListRepository.findByImeiMsisdnIdentityImei(Long.parseLong(imei));
		
		if(Objects.nonNull(vipList)) {
			customerCareDeviceState.setTxnId("");
			customerCareDeviceState.setDate(vipList.getCreatedOn().toString());
			customerCareDeviceState.setStatus(Constants.available);
			customerCareDeviceState.setFeatureId(0);
		}else {
			customerCareDeviceState.setDate("");
			customerCareDeviceState.setStatus(Constants.non_available);
		}
		
		setName(customerCareDeviceState);
		return customerCareDeviceState;
	}

	@Override
	public void setName(CustomerCareDeviceState customerCareDeviceState) {
		customerCareDeviceState.setName("VIP");
	}

}