package com.gl.ceir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.factory.service.Service;
import com.gl.ceir.factory.service.impl.CloseGrievance;
import com.gl.ceir.factory.service.impl.ConsignmentRevenueService;
import com.gl.ceir.factory.service.impl.DeviceTaxReminder;
import com.gl.ceir.factory.service.impl.VisaExpire;

@Component
public class Starter {

	@Autowired
	DeviceTaxReminder deviceTaxReminder;
	
	@Autowired
	VisaExpire visaExpire;
	
	@Autowired
	ConsignmentRevenueService consignmentRevenueService;
	
	@Autowired
	CloseGrievance closeGrievance;
	
	public Service start(String name) {
		
		switch (name) {
		case ProcessName.REGISTERED_DEVICE_TAX_NOT_PAID_REMINDER:
			return deviceTaxReminder;
		case ProcessName.VISA_EXPIRE:
			return visaExpire;
		case ProcessName.CONSIGNMENT_REVENUE:
			return consignmentRevenueService;
		case ProcessName.CLOSE_GRIEVANCE:
			return closeGrievance;
			
		default:
			return null;
		}
	}
}
