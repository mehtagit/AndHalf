package com.ceir.GreyListProcess.process;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.GreyListProcess.model.FileDumpFilter;
import com.ceir.GreyListProcess.model.FileDumpMgmt;
import com.ceir.GreyListProcess.model.GreylistDbHistory;
import com.ceir.GreyListProcess.model.SystemConfigurationDb;
import com.ceir.GreyListProcess.repositoryImpl.ConfigurationManagementServiceImpl;
import com.ceir.GreyListProcess.repositoryImpl.ListFileDetailsImpl;
import com.ceir.GreyListProcess.repositoryImpl.NationalislmServiceImpl;
import com.ceir.GreyListProcess.util.Utility;
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
	String yesterdayId=new String();
	String currentTime=new String();
	FileDumpMgmt topDataForIncdump=new FileDumpMgmt();

	public void incrementalDumpProcess(String filePath) {
			log.info("inside incremental dump process");
			topDataForIncdump=listFileDetailsImpl.topDataByDumpType("Incremental");	
			systemConfigurationDb.setTag("GREYLIST_FILEPATH");
			if(topDataForIncdump!=null) {
				incrementalDumpFileProcess(filePath);
			}
			else {
				yesterdayId=utility.getYesterdayId();
				currentTime=utility.getTxnId();
				FileDumpFilter filter=new FileDumpFilter();
				String fileName=filePath+"GreyList_"+yesterdayId+".csv";
				saveDataIntoFile(filter,fileName,filePath);	
			}
			//			startWeekOfDay=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
			log.info("exit from incremental dump process");
	}

	public void incrementalDumpFileProcess(String filePath) {
		log.info("createdOn column value: "+topDataForIncdump.getCreatedOn());
		String configDate=utility.convertToDateformat(topDataForIncdump.getCreatedOn());
		log.info("date from file dump table if dumpType is Incremental: "+configDate);
		yesterdayDate=utility.getYesterdayDateString();
		currentDate=utility.currentDate();
		if(!configDate.equals(currentDate)) {
			log.info("if files not created today");
			currentDate=utility.currentDate();
			log.info("currentDate:  "+currentDate);
			long differenceOfDates=utility.getDifferenceDays(configDate,currentDate);
			log.info("difference from currentDate and dump table date: "+differenceOfDates);
			systemConfigurationDb.setTag("GREY_LIST_INCR_DUMP_FREQ_IN_DAYS");
			frequencyInDays=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
			log.info("total frequency In Days for incremental dump: "+frequencyInDays.getValue().toString());
			int daysUnit=Integer.parseInt(frequencyInDays.getValue());
			int days=0;
			Date dateForConfig=topDataForIncdump.getCreatedOn();
			while(differenceOfDates>Long.parseLong(frequencyInDays.getValue())){
				log.info("if difference between dates greater than total days of frequency");
				currentTime=utility.getTxnId();
				String DayAdded=utility.addDaysInDate(days,dateForConfig);
				log.info("day added + "+DayAdded);
				log.info("days: "+days);
				Date stringToDate=utility.stringToDate(DayAdded);
				log.info("day added date:  "+stringToDate);
				FileDumpFilter filter=new FileDumpFilter();
				filter.setStartDate(topDataForIncdump.getCreatedOn());
				filter.setEndDate(stringToDate);
				yesterdayId=utility.getYesterdayId();
				log.info("fetch data from greylist db between dates : "+topDataForIncdump.getCreatedOn() +"to "+ stringToDate);
				String fileName=filePath+"GreyList_"+utility.convertToDateIdformat(stringToDate)+".csv";
				log.info("file path and name is:  "+fileName);
				saveDataIntoFile(filter,fileName,filePath);
				DayAdded=utility.addDaysInDate(days, dateForConfig);
				configDate=DayAdded;
				//dateForConfig=stringToDate;
				days=days+daysUnit;
				differenceOfDates=utility.getDifferenceDays(configDate,currentDate);
				log.info("differenceOfDates after file created: "+differenceOfDates);
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
		fileDumpMgmt.setFileName(fileName);
		fileDumpMgmt.setCreatedOn(new Date());
		fileDumpMgmt.setServiceDump("0");
		listFileDetailsImpl.saveFileDumpMgmt(fileDumpMgmt);
	}
}
