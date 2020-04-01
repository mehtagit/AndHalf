package com.gl.ceir.config.factory.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.CustomerCareTarget;
import com.gl.ceir.config.model.CustomerCareDeviceState;
import com.gl.ceir.config.model.DeviceLawfulDb;
import com.gl.ceir.config.model.DeviceOperatorDb;
import com.gl.ceir.config.model.constants.Constants;
import com.gl.ceir.config.repository.DeviceLawfulDbRepository;
import com.gl.ceir.config.repository.DeviceOperatorDbRepository;
import com.gl.ceir.config.util.CommonFunction;

@Component
public class CustomerCareStolen implements CustomerCareTarget{

	@Autowired
	DeviceOperatorDbRepository deviceOperatorDbRepository;

	@Autowired
	DeviceLawfulDbRepository deviceLawfulDbRepository;
	@Autowired
	CommonFunction commonFunction;
	@Override
	public CustomerCareDeviceState fetchDetailsByImei(String imei, CustomerCareDeviceState customerCareDeviceState) {

		DeviceOperatorDb deviceOperatorDb = deviceOperatorDbRepository.getByImeiEsnMeidAndDeviceState(imei, 12);

		if(Objects.nonNull(deviceOperatorDb)) {
			customerCareDeviceState.setTxnId(deviceOperatorDb.getTxnId());
			customerCareDeviceState.setDate(deviceOperatorDb.getCreatedOn().toString());
			customerCareDeviceState.setStatus(Constants.available);
			customerCareDeviceState.setFeatureId(deviceOperatorDb.getFeatureId());
		}else {
			DeviceLawfulDb deviceLawfulDb = deviceLawfulDbRepository.getByImeiEsnMeidAndDeviceState(imei, 10);
			if(Objects.nonNull(deviceLawfulDb)) {
				customerCareDeviceState.setTxnId(deviceLawfulDb.getTxnId());
				customerCareDeviceState.setDate(deviceLawfulDb.getCreatedOn().toString());
				customerCareDeviceState.setStatus(Constants.available);
				customerCareDeviceState.setFeatureId(commonFunction.getFeatureIdByTxnId(deviceLawfulDb.getTxnId()));
			}else {
				customerCareDeviceState.setDate("");
				customerCareDeviceState.setStatus(Constants.non_available);
				customerCareDeviceState.setFeatureId(0);
			}
		}
		customerCareDeviceState.setImei(imei);
		setName(customerCareDeviceState);
		return customerCareDeviceState;
	}

	@Override
	public void setName(CustomerCareDeviceState customerCareDeviceState) {
		customerCareDeviceState.setName("Stolen");
	}
}