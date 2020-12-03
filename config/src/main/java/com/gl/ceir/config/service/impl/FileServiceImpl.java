package com.gl.ceir.config.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;

@Service
public class FileServiceImpl {

	private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired
	InterpSetter interpSetter;

	public FileDetails getSampleFile(int featureId) {

		String fileName = null;
		SystemConfigurationDb systemConfigurationDb  = configurationManagementServiceImpl.findByTag(ConfigTags.sample_file_link);

		switch (featureId) {
		case 3:
			fileName = "Consignment.csv";
			break;
		case 4:
			fileName = "Stock.csv";
			break;
		case 5:
			fileName = "StolenAndRecovery.csv";
			break;
		case 7:
			fileName = "Blockunblock.csv";
			break;
		default:
			break;
		}

		return new FileDetails("", "", systemConfigurationDb.getValue().replace("$LOCAL_IP",
				propertiesReader.localIp) + fileName);
	}

	public FileDetails getManuals(int userTypeId) {

		String fileName = null;
		SystemConfigurationDb systemConfigurationDb  = configurationManagementServiceImpl.findByTag(ConfigTags.manuals_link+"_"+userTypeId);
		/*switch (userTypeId) {
		case 1:
			fileName = "";
			break;
		case 4:
			fileName = "CEIR_User Manual (Importer)_v1.1.pdf";
			break;
		case 5:
			fileName = "CEIR_User Manual (Distributor)_v1.0.pdf";
			break;
		case 6:
			fileName = "CEIR_User Manual (Retailer)_v1.0.pdf";
			break;
		case 7:
			fileName = "";
			break;
		case 8:
			fileName = "";
			break;
		case 9:
			fileName = "CEIR_User Manual (Operator)_v1.0.pdf";
			break;
		case 10:
			fileName = "CEIR_User Manual TRC_v1.0.pdf";
			break;
		case 12:
			fileName = "CEIR_User Manual (Manufacturer)_v1.0.pdf";
			break;
		case 13:
			fileName = "";
			break;
		case 14:
			fileName = "CEIR_User Manual (Lawful Agency)_v1.0.pdf";
			break;
		case 17:
			fileName = "CEIRv1.0_User Manual (Importer)_v1.0.pdf";
			break;
		case 18:
			fileName = "CEIRv1.0_User Manual (Importer)_v1.0.pdf";
			break;
		case 19:
			fileName = "CEIRv1.0_User Manual (Importer)_v1.0.pdf";
			break;
		case 20:
			fileName = "CEIRv1.0_User Manual (Importer)_v1.0.pdf";
			break;
		default:
			break;
		}*/

//		fileName = ;
//		return new FileDetails("", "", systemConfigurationDb.getValue().replace("$LOCAL_IP",
//				propertiesReader.localIp) + fileName);
		return new FileDetails("", "", systemConfigurationDb.getValue().replace("$LOCAL_IP",
				propertiesReader.localIp));
	}

	public FileDetails downloadUploadedFile(String fileName, String txnId, String fileType, String tag) {

		String fileLink = null;
		SystemConfigurationDb systemConfigurationDb  = configurationManagementServiceImpl.findByTag(ConfigTags.upload_file_link);

		if("actual".equalsIgnoreCase(fileType)) {
			if("DEFAULT".equalsIgnoreCase(tag)) {
				fileLink = systemConfigurationDb.getValue().replace("$LOCAL_IP",
						propertiesReader.localIp) + txnId + "/" + fileName;
			}else {	
				fileLink = systemConfigurationDb.getValue().replace("$LOCAL_IP",
						propertiesReader.localIp) + txnId + "/" + tag + "/" + fileName;
			}
		}else if("error".equalsIgnoreCase(fileType)) {
			systemConfigurationDb  = configurationManagementServiceImpl.findByTag(ConfigTags.system_error_file_link);
			fileLink = systemConfigurationDb.getValue().replace("$LOCAL_IP",
					propertiesReader.localIp) + txnId + "/" + txnId + "_error.csv";
		}

		return new FileDetails("", "", fileLink);
	}
}
