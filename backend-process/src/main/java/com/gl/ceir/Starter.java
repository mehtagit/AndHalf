package com.gl.ceir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.service.Service;
import com.gl.ceir.service.impl.DeviceTaxReminder;

@Component
public class Starter {

	@Autowired
	DeviceTaxReminder deviceTaxReminder;
	
	public Service start(String name) {
		
		switch (name) {
		case ProcessName.Registerd_Device_Tax_Not_paid_Reminder:
			return deviceTaxReminder;
		default:
			return null;
		}
	}
}
