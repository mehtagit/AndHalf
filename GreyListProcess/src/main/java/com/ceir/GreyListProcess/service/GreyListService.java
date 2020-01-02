package com.ceir.GreyListProcess.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.GreyListProcess.model.SystemConfigurationDb;
import com.ceir.GreyListProcess.process.FullDumpProcess;
import com.ceir.GreyListProcess.process.IncrementalDumpProcess;
import com.ceir.GreyListProcess.repository.SystemConfigurationDbRepository;
import com.ceir.GreyListProcess.repositoryImpl.ConfigurationManagementServiceImpl;
import com.ceir.GreyListProcess.util.Utility;
@Service
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
    		SystemConfigurationDb filePath=new SystemConfigurationDb();
    		log.info("inside in grey List dump process");
			SystemConfigurationDb systemConfigurationDb=new SystemConfigurationDb();
			systemConfigurationDb.setTag("GREYLIST_FILEPATH");
		try {
			filePath=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
			log.info("filePath:  "+filePath.getValue());
		}
		catch(Exception e) {
          e.printStackTrace();
          filePath=new SystemConfigurationDb();
		}
			fullDumpProcess.fullDumpProcess(filePath.getValue());
		    increDumpProcess.incrementalDumpProcess(filePath.getValue());	
			log.info("exit from grey List dump process");
		
		try {
			Thread.sleep(3600000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	}
}
