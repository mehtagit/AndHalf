package com.gl.ceir.config.factory;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.impl.CustomerCareDistributor;
import com.gl.ceir.config.factory.impl.CustomerCareImporter;

@Component
public class CustomerCareFactory {

	public final List<String> dbsList = Arrays.asList("IMPORTER", "DISTRIBUTOR");
	
	@Autowired
	CustomerCareImporter customerCareImporter;

	@Autowired
	CustomerCareDistributor customerCareDistributor;

	public CustomerCareTarget getObject(String name) {

		switch (name) {
		case "IMPORTER":
			return customerCareImporter;
		case "DISTRIBUTOR":
			return customerCareDistributor;
		default:
			break;
		}

		return null;
	}
}
