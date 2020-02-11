package com.gl.ceir.config.service.impl;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.PolicyConfigurationDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;

@Service
public class FileServiceImpl {

	private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);

	@Autowired
	FileStorageProperties fileStorageProperties;

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

		return new FileDetails("", "", systemConfigurationDb.getValue() + fileName);
	}

	public FileDetails getManuals() {

		String fileName = null;
		SystemConfigurationDb systemConfigurationDb  = configurationManagementServiceImpl.findByTag(ConfigTags.manuals_link);
		fileName = "CEIRv1.0_User Manual (Importer)_v1.0";

		return new FileDetails("", "", systemConfigurationDb.getValue() + fileName);
	}

	public FileDetails downloadUploadedFile(String fileName, String txnId, String fileType, String tag) {

		String fileLink = null;
		SystemConfigurationDb systemConfigurationDb  = configurationManagementServiceImpl.findByTag(ConfigTags.upload_file_link);

		if("actual".equalsIgnoreCase(fileType)) {
			if("DEFAULT".equalsIgnoreCase(tag)) {
				fileLink = systemConfigurationDb.getValue() + txnId + "/" + fileName;
			}else {	
				fileLink = systemConfigurationDb.getValue() + txnId + "/" + tag + "/" + fileName;
			}
		}else if("error".equalsIgnoreCase(fileType)) {
			systemConfigurationDb  = configurationManagementServiceImpl.findByTag(ConfigTags.system_error_file_link);
			fileLink = systemConfigurationDb.getValue() + txnId + "/" + txnId + "_error.csv";
		}

		return new FileDetails("", "", fileLink);
	}
}
