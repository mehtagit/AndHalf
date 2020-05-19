package com.gl.ceir.config.factory;

import com.gl.ceir.config.model.CustomerCareDeviceState;

public interface CustomerCareTarget {
	
	public CustomerCareDeviceState fetchDetailsByImei(String imei, CustomerCareDeviceState customerCareDeviceState);
	
	public void setName(CustomerCareDeviceState customerCareDeviceState);
	
}
