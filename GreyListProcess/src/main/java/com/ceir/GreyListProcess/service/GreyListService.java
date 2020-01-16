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
import com.ceir.GreyListProcess.repositoryImpl.WebActionRepoImpl;
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

	@Autowired
	WebActionRepoImpl webActionRepoImpl;

	private final Logger log =LoggerFactory.getLogger(getClass());

	public void run() {
		while(true) {
			SystemConfigurationDb filePath=new SystemConfigurationDb();
			log.info("inside in Grey List dump process");
			log.info("now going to check whether stolen data found on web_action_db table or not");   
			boolean checkStolenStatus=webActionRepoImpl.checkFeatureExist("Stolen");
			if(checkStolenStatus==false) {
				log.info("If stolen data doesnot exist in web action db");
				SystemConfigurationDb systemConfigurationDb=new SystemConfigurationDb();
				systemConfigurationDb.setTag("GREYLIST_FILEPATH");
				log.info("now fetching filepath to save grey list dump files");
				try{
					filePath=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
					log.info("filePath:  "+filePath.getValue());
				}
				catch(Exception e) {
					log.info("failed to fetch grey List dump file path");
					e.printStackTrace();
					filePath=new SystemConfigurationDb();
				}
				log.info("now going to process full dump and incremental process one by one");
				fullDumpProcess.fullDumpProcess(filePath.getValue());
				increDumpProcess.incrementalDumpProcess(filePath.getValue());	
				log.info("exit from grey List dump process");
			}
			else {
				log.info("stolen data exist in web action db");
				log.info("so this process cannot go further");
			}
			log.info("exit from Grey List dump process");
			try {
				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}