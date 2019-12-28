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
import com.gl.ceir.config.model.GreylistDbHistory;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.service.impl.ConfigurationManagementServiceImpl;
import com.gl.ceir.config.service.impl.ListFileDetailsImpl;
import com.gl.ceir.config.service.impl.NationalislmServiceImpl;

@Service
public class IncrementalDumpProcess {
	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired
	ListFileDetailsImpl listFileDetailsImpl;

	@Autowired 
	Utility utility;

	@Autowired
	NationalislmServiceImpl nationalislmServiceImpl;

	private final Logger log =LoggerFactory.getLogger(getClass());

	SystemConfigurationDb systemConfigurationDb=new SystemConfigurationDb();
	SystemConfigurationDb frequencyInDays=new SystemConfigurationDb();
	SystemConfigurationDb startWeekOfDay=new SystemConfigurationDb();
	String currentDate=new String();
	String yesterdayDate=new String();
	String currentTime=new String();
	FileDumpMgmt topDataForIncdump=new FileDumpMgmt();

	public void incrementalDumpProcess(String filePath) {
		try {
			log.info("inside full dump process");
			topDataForIncdump=listFileDetailsImpl.topDataByDumpType("Incremental");	
			systemConfigurationDb.setTag("GREYLIST_FILEPATH");
			if(topDataForIncdump.getId()!=0) {
				incrementalDumpFileProcess(filePath);
			}
			else {
				currentTime=utility.getTxnId();
				FileDumpFilter filter=new FileDumpFilter();
				String fileName=filePath+currentTime+".csv";
				saveDataIntoFile(filter,fileName,filePath);	
			}
//			startWeekOfDay=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
			log.info("exit from full dump process");
		}

		catch(Exception e) {
			log.info("error occur in full dump process");
			log.info(e.getMessage());
		}
	}

	public void incrementalDumpFileProcess(String filePath) {
		String configDate=utility.convertToDateformat(topDataForIncdump.getCreatedOn());
		log.info("date from file dump table if dumpType is Incremental: "+configDate);
		yesterdayDate=utility.getYesterdayDateString();
		if(configDate!=yesterdayDate) {
			long differenceOfDates=utility.getDifferenceDays(currentDate, configDate);
			log.info("difference from currentDate and dump table date: "+differenceOfDates);
			while(differenceOfDates>Long.parseLong(frequencyInDays.getValue())){
				log.info("if difference between dates greater than total days of frequency");
				currentTime=utility.getTxnId();
				String DayAdded=utility.addDaysInDate(Integer.parseInt(frequencyInDays.getValue()), topDataForIncdump.getCreatedOn());
				Date stringToDate=utility.stringToDate(DayAdded);
				FileDumpFilter filter=new FileDumpFilter();
				filter.setStartDate(topDataForIncdump.getCreatedOn());
				filter.setEndDate(stringToDate);
				log.info("fetch data from greylist db between dates : "+topDataForIncdump.getCreatedOn() +"to "+ stringToDate);
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
		List<GreylistDbHistory> greyListData=nationalislmServiceImpl.greyListHistoryDataByCreatedOn(fileDataFilter);
		if(!greyListData.isEmpty()) {
			log.info("if grey list history table is not empty");
			header="IMEI,Operation";
			utility.writeGreyListHistoryInFile(fileName, header, greyListData);        
		}
		else {
			log.info("if grey list history table is empty");
			header="Message";
			String record="No data available in GreyList.";
			utility.writeInFile(fileName, header, record);	
		}
		FileDumpMgmt fileDumpMgmt=new FileDumpMgmt();
		fileDumpMgmt.setDumpType("Incremental");
		String filename=filePath+currentTime+".csv";
		fileDumpMgmt.setFileName(filename);
		fileDumpMgmt.setCreatedOn(new Date());
	}


}
