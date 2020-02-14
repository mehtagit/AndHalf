package com.ceir.SLAModule.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.SLAModule.App;
import com.ceir.SLAModule.entity.SlaReport;
import com.ceir.SLAModule.entity.StockMgmt;
import com.ceir.SLAModule.entity.SystemConfigurationDb;
import com.ceir.SLAModule.repoService.SlaRepoService;
import com.ceir.SLAModule.repoService.StockRepoService;
import com.ceir.SLAModule.repoService.SystemConfigRepoService;
import com.ceir.SLAModule.util.Utility;

@Service
public class StockService {

	@Autowired
	StockRepoService stockRepo;

	@Autowired
	Utility utility;

	@Autowired
	SystemConfigRepoService systemConfigRepoService;

	
	@Autowired
	SlaRepoService slaRepoService;
	private final static Logger log =LoggerFactory.getLogger(App.class);
	public void stockProcess(int status) {
		log.info("inside Stock sla process");
		log.info("now going to fetch Stock by status: "+status);
		List<StockMgmt> stockData=new ArrayList<StockMgmt>();
		try {
			stockData=stockRepo.stockDataByStatus(status);
		}
		catch(Exception e) {
			log.info(e.toString());
			log.info(e.getMessage());
		}
		if(stockData.isEmpty()==false) {
			
			log.info("Stock data= "+stockData);
			log.info("Stock data is available for this status");
			String currentDate=utility.currentDate();
			log.info("currentDate is "+currentDate);
			Iterator<StockMgmt> stockIterator=stockData.iterator();
			log.info("now going to find number of days for approval of Stock by  ceir");
			SystemConfigurationDb systemconfig=systemConfigRepoService.getByTag("GRIEV_PEN_WITH_ADMIN");
			if(systemconfig!=null) {
				long days=Long.parseLong(systemconfig.getValue());
                log.info("number of days for approval of Stock by  ceir is "+days);
				while(stockIterator.hasNext()) {
					StockMgmt stock=stockIterator.next();
					String modifiedDate=utility.convertlocalTimeToString(stock.getModifiedOn());
					log.info("Stock modified date= "+modifiedDate);
					if(modifiedDate!=null) {
						long dayDifferece=utility.getDifferenceDays(modifiedDate, currentDate);
						log.info("difference between current date and Stock modified date= "+dayDifferece);
						if(dayDifferece>days) {
							log.info("if difference greater than number of days for approval ");
							SlaReport  slaRepo=new SlaReport("Stock","Pending  From CEIR Admin",stock.getUser(),
									stock.getTxnId());
							SlaReport output=slaRepoService.saveSLA(slaRepo);	
							if(output!=null) {
								log.info("sla report sucessfully save");
							}
							else {
								log.info("sla report failed to save");
							}
						}
						else {
							log.info("if difference less than number of days for approval ");	
						}
					}
					else {
						log.info("Stock modified date is empty");
					}
				}
			}
			else {
				log.info("data failed to find by GRIEV_PEN_WITH_ADMIN tag in system_configuration_db table");
			}

		}
		else {
			log.info("Stock data failed to find when status is pending approval for custom");
		}
		log.info("exit from Stock sla process");  
	}
	
}
