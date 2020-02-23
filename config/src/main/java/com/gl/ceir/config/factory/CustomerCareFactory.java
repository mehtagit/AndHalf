package com.gl.ceir.config.factory;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.impl.CustomerCareCustom;
import com.gl.ceir.config.factory.impl.CustomerCareDistributor;
import com.gl.ceir.config.factory.impl.CustomerCareEndUser;
import com.gl.ceir.config.factory.impl.CustomerCareImporter;
import com.gl.ceir.config.factory.impl.CustomerCareManufacturer;
import com.gl.ceir.config.factory.impl.CustomerCareRetailer;

@Component
public class CustomerCareFactory {

	public final List<String> dbsList = Arrays.asList("IMPORTER", "DISTRIBUTOR", "RETAILER", "CUSTOM", 
			"MANUFACTURER", "REGULARIZE", "VIP", "BLACKLIST", "GREYLIST", "DUPLICATE", "STOLEN", "GLOBAL_BLACKIST", 
			"TYPE_APPROVED");
	
	@Autowired
	CustomerCareImporter customerCareImporter;

	@Autowired
	CustomerCareDistributor customerCareDistributor;
	
	@Autowired
	CustomerCareRetailer customerCareRetailer;
	
	@Autowired
	CustomerCareCustom customerCareCustom;
	
	@Autowired
	CustomerCareManufacturer customerCareManufacturer;
	
	@Autowired
	CustomerCareEndUser customerCareEndUser;

	public CustomerCareTarget getObject(String name) {

		switch (name) {
		case "IMPORTER":
			return customerCareImporter;
		case "DISTRIBUTOR":
			return customerCareDistributor;
		case "RETAILER":
			return customerCareRetailer;
		case "CUSTOM":
			return customerCareCustom;
		case "MANUFACTURER":
			return customerCareManufacturer;
		case "REGULARIZE":
			return customerCareEndUser;
		default:
			break;
		}

		return null;
	}
}
