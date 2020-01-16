package com.ceir.BlackListProcess.process;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.BlackListProcess.model.BlackList;
import com.ceir.BlackListProcess.model.BlacklistDbHistory;
import com.ceir.BlackListProcess.model.GreylistDb;
import com.ceir.BlackListProcess.model.GreylistDbHistory;
import com.ceir.BlackListProcess.model.ImeiMsisdnIdentity;
import com.ceir.BlackListProcess.model.SystemConfigurationDb;
import com.ceir.BlackListProcess.model.constants.StockOperation;
import com.ceir.BlackListProcess.repoimpl.BlackListRepoImpl;
import com.ceir.BlackListProcess.repoimpl.ConfigurationManagementServiceImpl;
import com.ceir.BlackListProcess.repoimpl.ListFileDetailsImpl;
import com.ceir.BlackListProcess.repoimpl.NationalislmServiceImpl;
import com.ceir.BlackListProcess.repoimpl.WebActionRepoImpl;
import com.ceir.BlackListProcess.util.Utility;

@Service
public class BlackListProcess {

	@Autowired
	ListFileDetailsImpl listFileDetailsImpl;

	@Autowired
	Utility utility;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired
	NationalislmServiceImpl nationalislmServiceImpl;

	@Autowired
	BlackListRepoImpl blackListRepoImpl;

	@Autowired
	WebActionRepoImpl webActionRepoImpl;
	
	private final Logger log =LoggerFactory.getLogger(getClass());

	public void blackListProcess() {
		log.info("inside blacklist process");
		boolean checkStolenStatus=webActionRepoImpl.checkFeatureExist("Stolen");
		if(checkStolenStatus==false) {
		log.info("if stolen data is not found");
		SystemConfigurationDb greyToBlackListPeriodInDay=new SystemConfigurationDb();
		SystemConfigurationDb sysConfigDb=new SystemConfigurationDb();
		//log.info("now going to check whether data entered on file_dump_mgmt for grey list");
		//FileDumpMgmt topDataForBlackList=listFileDetailsImpl.topDataByServiceDump(0);
		//log.info("topDataForIncdump:  "+topDataForBlackList.toString());
		//if(topDataForBlackList!= null) {
		//log.info("if topDataForBlackList is not null");
		//String currentDate=utility.currentDate();
		Date currentDdate=utility.currentOnlyDate();
		log.info("current Date:  "+currentDdate);
		//String fileDumpDate=utility.convertToDateformat(topDataForBlackList.getCreatedOn());
		//Date dumpDate=utility.convertDateToFormat(topDataForBlackList.getCreatedOn());
		//log.info("fileDumpDate:  "+dumpDate);
		//if(dumpDate.equals(currentDdate)) {
		sysConfigDb.setTag("GREY_TO_BLACK_MOVE_PERIOD_IN_DAY");
		log.info("now going to check GREY TO BLACK MOVE PERIOD IN DAY");
		greyToBlackListPeriodInDay=configurationManagementServiceImpl.findByTag(sysConfigDb);
		log.info("GREY TO BLACK MOVE PERIOD IN DAY: "+greyToBlackListPeriodInDay.getValue());
		Integer days=Integer.parseInt(greyToBlackListPeriodInDay.getValue());
		//String compareDate=utility.addDaysInCurrentDate(days);
		//Date periodDate=utility.stringToDate(compareDate);
		List<GreylistDb> greyListData=new ArrayList<GreylistDb>();
		log.info("going to fetch grey list data:");
		greyListData=nationalislmServiceImpl.findAllGreyListData();
		if(greyListData!=null) {
			greyListProcess(greyListData);
		}
		//}
		//}
		}
		else {
			log.info("stolen data exist in web action db :  "+checkStolenStatus);
		}
		log.info("exit from blackList process");
	}

	public void greyListProcess(List<GreylistDb> greyListData) {
		log.info("if greylist data is not null");
		for(GreylistDb greyListDb:greyListData) {
			Date currentDate=utility.currentOnlyDate();
			log.info("grace period expiry date for particular imei: "+greyListDb.getExpiryDate());
			log.info("current date:  "+currentDate); 
			if(currentDate.after(greyListDb.getExpiryDate())) {
				log.info("if grace period for this imei is completed: "+greyListDb.getImei());	
				GreylistDbHistory greyListHistory=new GreylistDbHistory(new Date(),new Date(),
						greyListDb.getImei(), greyListDb.getRoleType(), greyListDb.getUserId(),
						greyListDb.getTxnId(), greyListDb.getDeviceNumber(), greyListDb.getDeviceType(),
						greyListDb.getDeviceAction(),greyListDb.getDeviceStatus(),greyListDb.getDeviceLaunchDate(),
						greyListDb.getMultipleSimStatus(), greyListDb.getDeviceId(),greyListDb.getImeiEsnMeid(),StockOperation.INSERTION.getCode()
						,"Moved to BlackList");
				GreylistDbHistory greylistDbHistory=nationalislmServiceImpl.saveGreyListHistory(greyListHistory);
                
				if(greylistDbHistory!=null) {
					log.info("now data is saved into GreylistDbHistory table");
					int deleteGreyListById=nationalislmServiceImpl.deleteGreyListById(greyListDb.getId());
					if(deleteGreyListById!=0) {
						log.info("that imei record removed from grey_List_Db table");
						BlackList blackList=new BlackList();
						ImeiMsisdnIdentity imeiMsisdnIdentity=new ImeiMsisdnIdentity();
						imeiMsisdnIdentity.setImei(greylistDbHistory.getImei());
						imeiMsisdnIdentity.setMsisdn(0l); 
						blackList.setImeiMsisdnIdentity(imeiMsisdnIdentity);
						
						BlackList blackListOutput=blackListRepoImpl.saveBlackList(blackList);	
						if(blackListOutput!=null) {
							log.info("data saved into black_list table");
							BlacklistDbHistory blackListHistory=new BlacklistDbHistory(
									new Date(),new Date(),
									greyListDb.getImei(), greyListDb.getRoleType(), greyListDb.getUserId(),
									greyListDb.getDeviceNumber(), greyListDb.getDeviceType(),
									greyListDb.getDeviceAction(),greyListDb.getDeviceStatus(),greyListDb.getDeviceLaunchDate(),
									greyListDb.getMultipleSimStatus(), greyListDb.getDeviceId(),greyListDb.getImeiEsnMeid(),StockOperation.INSERTION.getCode());
							BlacklistDbHistory output=blackListRepoImpl.saveBlackListHistory(blackListHistory);	
							if(output!=null) {
								System.out.println("data saved into blacklist_db_history table");
							}
						}
					}
				}
			}
			else{

			}
		}
	}
}
