package com.gl.ceir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.service.Service;
import com.gl.ceir.service.impl.ConsignmentRevenueService;
import com.gl.ceir.service.impl.DeviceTaxReminder;
import com.gl.ceir.service.impl.VisaExpire;

@Component
public class Starter {

	@Autowired
	DeviceTaxReminder deviceTaxReminder;
	
	@Autowired
	VisaExpire visaExpire;
	
	@Autowired
	ConsignmentRevenueService consignmentRevenueService;
	
	public Service start(String name) {
		
		switch (name) {
		case ProcessName.REGISTERED_DEVICE_TAX_NOT_PAID_REMINDER:
			return deviceTaxReminder;
		case ProcessName.VISA_EXPIRE:
			return visaExpire;
		case ProcessName.CONSIGNMENT_REVENUE:
			return consignmentRevenueService;
			
		default:
			return null;
		}
	}
}
