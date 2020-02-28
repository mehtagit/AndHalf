package com.gl.ceir.config.factory;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.factory.impl.CustomerCareBlacklist;
import com.gl.ceir.config.factory.impl.CustomerCareCustom;
import com.gl.ceir.config.factory.impl.CustomerCareDistributor;
import com.gl.ceir.config.factory.impl.CustomerCareDuplicate;
import com.gl.ceir.config.factory.impl.CustomerCareEndUser;
import com.gl.ceir.config.factory.impl.CustomerCareGreylist;
import com.gl.ceir.config.factory.impl.CustomerCareGsmaBlacklist;
import com.gl.ceir.config.factory.impl.CustomerCareImporter;
import com.gl.ceir.config.factory.impl.CustomerCareManufacturer;
import com.gl.ceir.config.factory.impl.CustomerCareRetailer;
import com.gl.ceir.config.factory.impl.CustomerCareStolen;
import com.gl.ceir.config.factory.impl.CustomerCareTypeApprove;
import com.gl.ceir.config.factory.impl.CustomerCareVipList;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StolenAndRecoveryRepository;

@Component
public class CustomerCareFactory {

	public final List<String> deviceList = Arrays.asList("IMPORTER", "DISTRIBUTOR", "RETAILER", "CUSTOM", 
			"MANUFACTURER", "REGULARIZE", "VIP");
	
	public final List<String> stateList = Arrays.asList("BLACKLIST", "GREYLIST", "DUPLICATE", "STOLEN", "GLOBAL_BLACKIST", 
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
	
	@Autowired
	CustomerCareBlacklist customerCareBlacklist;
	
	@Autowired
	CustomerCareGreylist customerCareGreylist;
	
	@Autowired
	CustomerCareStolen customerCareStolen;
	
	@Autowired
	CustomerCareGsmaBlacklist customerCareGsmaBlacklist;
	
	@Autowired
	CustomerCareVipList customerCareVipList;
	
	@Autowired
	CustomerCareDuplicate customerCareDuplicate;
	
	@Autowired
	CustomerCareTypeApprove customerCareTypeApprove;

	// View By txn id repository.

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StockManagementRepository stockManagementRepository;

	@Autowired
	StolenAndRecoveryRepository stolenAndRecoveryRepository;

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
		case "BLACKLIST":
			return customerCareBlacklist;
		case "GREYLIST":
			return customerCareGreylist;
		case "STOLEN":
			return customerCareStolen; 
		case "GLOBAL_BLACKIST":
			return customerCareGsmaBlacklist;
		case "VIP":
			return customerCareVipList;
		case "DUPLICATE":
			return customerCareDuplicate;
		case "TYPE_APPROVED":
			return customerCareTypeApprove;
			
		default:
			break;
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	public CustomerCareRepo getRepoByName(String name) {

		switch (name) {
		case Features.CONSIGNMENT:
			return consignmentRepository;
		case Features.STOCK:
			return stockManagementRepository;
		case Features.STOLEN_RECOVERY:
			return stolenAndRecoveryRepository;
		default:
			break;
		}

		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public CustomerCareRepo getRepoByFeatureId(int featureId) {

		switch (featureId) {
		case 3:
			return consignmentRepository;
		case 4:
			return stockManagementRepository;
		case 5:
			return stolenAndRecoveryRepository;
		case 7:
			return stolenAndRecoveryRepository;
		default:
			break;
		}

		return null;
	}
}
