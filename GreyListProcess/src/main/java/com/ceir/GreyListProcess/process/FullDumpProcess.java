package com.ceir.GreyListProcess.process;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.GreyListProcess.util.Utility;
import com.gl.ceir.config.model.FileDumpFilter;
import com.gl.ceir.config.model.FileDumpMgmt;
import com.gl.ceir.config.model.GreylistDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.service.impl.ConfigurationManagementServiceImpl;
import com.gl.ceir.config.service.impl.ListFileDetailsImpl;
import com.gl.ceir.config.service.impl.NationalislmServiceImpl;
@Service
public class FullDumpProcess {

	private final Logger log =LoggerFactory.getLogger(getClass());

	@Autowired
	SystemConfigurationDbRepository systemConfigDbRepo;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired 
	Utility utility;

	@Autowired
	ListFileDetailsImpl listFileDetailsImpl;

	@Autowired
	NationalislmServiceImpl nationalislmServiceImpl;
	
	SystemConfigurationDb systemConfigurationDb=new SystemConfigurationDb();
	SystemConfigurationDb frequencyInDays=new SystemConfigurationDb();
	SystemConfigurationDb startWeekOfDay=new SystemConfigurationDb();
	//SystemConfigurationDb filePath=new SystemConfigurationDb();
	String currentDate=new String();
	String yesterdayDate=new String();
	String currentTime=new String();
	
	FileDumpMgmt topDataForFulldump=new FileDumpMgmt();
	
	public void fullDumpProcess(String filePath) {
		try {
			log.info("inside full dump process");
			topDataForFulldump=listFileDetailsImpl.topDataByDumpType("Full");
			systemConfigurationDb.setTag("GREYLIST_FILEPATH");
	
			if(topDataForFulldump.getId()!=0) {
				fullDumpFileProcess(filePath);
			}
			else {
				currentTime=utility.getTxnId();
				FileDumpFilter filter=new FileDumpFilter();
				String fileName=filePath+currentTime+".csv";
				saveDataIntoFile(filter,fileName,filePath);
		
			}
			log.info("exit from full dump process");

		}
		catch(Exception e){
			log.info("error occur in full dump process");
			log.info(e.getMessage());
			//return null;
		}
	}

	public void fullDumpFileProcess(String filePath) {
		
		String configDate=utility.convertToDateformat(topDataForFulldump.getCreatedOn());
		log.info("date from file dump table if dumpType is Full: "+configDate);
		yesterdayDate=utility.getYesterdayDateString();
		if(configDate!=yesterdayDate) {
			log.info("if file dump type data file not created yesterday ");
			currentDate=utility.currentDate();
			systemConfigurationDb.setTag("GREY_LIST_FULL_DUMP_FREQ_IN_DAYS");
			frequencyInDays=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
			log.info("total frequency In Days for full dump: "+frequencyInDays);
			//systemConfigurationDb.setTag("GREY_LIST_START_OF_WEEK");
	//	startWeekOfDay=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
			
			long differenceOfDates=utility.getDifferenceDays(currentDate, configDate);
			log.info("difference of current date and date of dump table: "+frequencyInDays);
			while(differenceOfDates>Long.parseLong(frequencyInDays.getValue())){
				log.info("if difference between dates greater than total days of frequency");
				currentTime=utility.getTxnId();
			
				String DayAdded=utility.addDaysInDate(Integer.parseInt(frequencyInDays.getValue()), topDataForFulldump.getCreatedOn());
				Date stringToDate=utility.stringToDate(DayAdded);
				FileDumpFilter filter=new FileDumpFilter();
				filter.setStartDate(topDataForFulldump.getCreatedOn());
				filter.setEndDate(stringToDate);
				log.info("fetch data from greylist db between dates : "+topDataForFulldump.getCreatedOn() +"to "+ stringToDate);
				String fileName=filePath+currentTime+".csv";
				log.info("file path and name is:  "+fileName);
				saveDataIntoFile(filter,fileName,filePath);
				configDate=DayAdded;
				differenceOfDates=utility.getDifferenceDays(currentDate, configDate);
			}
		}
	}


	public void saveDataIntoFile(FileDumpFilter fileDataFilter,String fileName,String filePath) {
        String header=new String();
		List<GreylistDb> greyListData=nationalislmServiceImpl.greyListDataByCreatedOn(fileDataFilter);
		if(!greyListData.isEmpty()) {
			log.info("if grey list is not empty");
			header="imei";
			utility.writeGreyListInFile(fileName, header, greyListData);        
		}
		else {
			log.info("if grey list is  empty");
			header="Message";
			String record="No data available in GreyList.";
			utility.writeInFile(fileName, header, record);	
		}
		FileDumpMgmt fileDumpMgmt=new FileDumpMgmt();
		fileDumpMgmt.setDumpType("Full");
		String filename=filePath+currentTime+".csv";
		fileDumpMgmt.setFileName(filename);
		fileDumpMgmt.setCreatedOn(new Date());
	}
}
