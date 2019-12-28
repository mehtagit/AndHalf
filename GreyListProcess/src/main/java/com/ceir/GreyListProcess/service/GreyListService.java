package com.ceir.GreyListProcess.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceir.GreyListProcess.process.FullDumpProcess;
import com.ceir.GreyListProcess.process.IncrementalDumpProcess;
import com.ceir.GreyListProcess.util.Utility;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.service.impl.ConfigurationManagementServiceImpl;

public class GreyListService implements Runnable{

	@Autowired
	SystemConfigurationDbRepository systemConfigDbRepo;

	@Autowired
	Utility utility;
	
	@Autowired
	IncrementalDumpProcess increDumpProcess;
	
	@Autowired
	FullDumpProcess fullDumpProcess;
	
	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;
	
	private final Logger log =LoggerFactory.getLogger(getClass());
	
	public void run() {
       while(true) {
		try {
			log.info("inside in grey List dump process");
			SystemConfigurationDb systemConfigurationDb=new SystemConfigurationDb();
			SystemConfigurationDb filePath=new SystemConfigurationDb();
			systemConfigurationDb.setTag("GREYLIST_FILEPATH");
			filePath=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
			fullDumpProcess.fullDumpProcess(filePath.getValue());
			increDumpProcess.incrementalDumpProcess(filePath.getValue());	
			log.info("exit from grey List dump process");
			//Thread.sleep(3000000);
		}
		catch(Exception e) {
          e.printStackTrace();
          
		}

		try {
			Thread.sleep(86400000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	}
}
