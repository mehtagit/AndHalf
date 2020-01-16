package com.ceir.GreyListProcess.process;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.GreyListProcess.model.FileDumpFilter;
import com.ceir.GreyListProcess.model.FileDumpMgmt;
import com.ceir.GreyListProcess.model.GreylistDb;
import com.ceir.GreyListProcess.model.SystemConfigurationDb;
import com.ceir.GreyListProcess.model.constants.DumpType;
import com.ceir.GreyListProcess.repository.SystemConfigurationDbRepository;
import com.ceir.GreyListProcess.repositoryImpl.ConfigurationManagementServiceImpl;
import com.ceir.GreyListProcess.repositoryImpl.ListFileDetailsImpl;
import com.ceir.GreyListProcess.repositoryImpl.NationalislmServiceImpl;
import com.ceir.GreyListProcess.util.Utility;

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
	String currentDate=new String();
	String yesterdayDate=new String();
	String currentTime=new String();
	String yesterdayTime=new String();
	FileDumpMgmt topDataForFulldump=new FileDumpMgmt();

	public void fullDumpProcess(String filePath) {
		log.info("inside full dump process");
		topDataForFulldump=listFileDetailsImpl.topDataByDumpType("Full");
		systemConfigurationDb.setTag("GREYLIST_FILEPATH");
		log.info("now checking whether full dump data exist in file_dump_mgmt table or not");
		if(topDataForFulldump!=null) {

			log.info("topDataForFulldump: "+topDataForFulldump.toString());
			fullDumpFileProcess(filePath);
		}
		else {
			log.info("if file_dump_mgmt table for full dump is not found");
			log.info("So this is full dump process first time to start");
			FileDumpFilter filter=new FileDumpFilter();
			yesterdayTime=utility.getYesterdayId();
			String fileName="GreyList_Full_"+yesterdayTime+".csv";
			//log.info("greyList full dump filename: "+yesterdayTime);
			saveDataIntoFile(filter,fileName,filePath);
		}
		log.info("exit from full dump process");
	}

	public void fullDumpFileProcess(String filePath) {


		yesterdayDate=utility.getYesterdayDateString();
		currentDate=utility.currentDate();
		Date dateSubtract=utility.subtractDays(topDataForFulldump.getCreatedOn(), 1);
		String configDate=utility.convertToDateformat(dateSubtract);
		log.info("date from file dump table if dumpType is Full: "+configDate);
		log.info("date from file dump table if dumpType is Incremental: "+configDate);
		yesterdayDate=utility.getYesterdayDateString();
		currentDate=utility.currentDate();
		if(!configDate.equals(yesterdayDate)) {

			log.info("currentDate:  "+currentDate);
			long differenceOfDates=utility.getDifferenceDays(configDate,currentDate);
			log.info("difference from currentDate and dump table date: "+differenceOfDates);
			systemConfigurationDb.setTag("GREY_LIST_FULL_DUMP_FREQ_IN_DAYS");
			frequencyInDays=configurationManagementServiceImpl.findByTag(systemConfigurationDb);
			log.info("total frequency In Days for incremental dump: "+frequencyInDays.getValue().toString());
			int daysUnit=Integer.parseInt(frequencyInDays.getValue());
			int days=0;
			Date dateForConfig=topDataForFulldump.getCreatedOn();
			while(differenceOfDates>Long.parseLong(frequencyInDays.getValue())){
				log.info("if difference between dates greater than total days of frequency");
				currentTime=utility.getTxnId();
				String DayAdded=utility.addDaysInDate(days,dateForConfig);
				log.info("day added + "+DayAdded);
				Date stringToDate=utility.stringToDate(DayAdded);
				log.info("day added date:  "+stringToDate);
				FileDumpFilter filter=new FileDumpFilter();
				filter.setStartDate(utility.convertToDateformat(topDataForFulldump.getCreatedOn()));
				filter.setEndDate(utility.convertToDateformat(stringToDate));
				log.info("fetch data from greylist db between dates : "+topDataForFulldump.getCreatedOn() +"to "+ stringToDate);
				String fileName="GreyList_Full_"+utility.convertToDateOnlyformat(stringToDate)+".csv";
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
		log.info("now fetching grey list data for full dump from table greylist_db");
		List<GreylistDb> greyListData=nationalislmServiceImpl.greyListDataByCreatedOn(fileDataFilter);
		//log.info("greyListData full dump data is fetched");
		//log.info("now going to save data into file");
		if(!greyListData.isEmpty()) {
			log.info("if grey list data is not empty");
			log.info("saving this grey list data to the file");
			log.info("filename: "+fileName);
			header="imei";
			utility.writeGreyListInFile(filePath+fileName, header, greyListData);        
		}
		else {
			log.info("if grey list is  empty");
			header="Message";
			String record="No data available in GreyList.";
			utility.writeInFile(filePath+fileName, header, record);	
		}
		FileDumpMgmt fileDumpMgmt=new FileDumpMgmt(new Date(),new Date(), fileName, DumpType.FULL.getCode(), 0, "Full");
		listFileDetailsImpl.saveFileDumpMgmt(fileDumpMgmt);
	}
}
