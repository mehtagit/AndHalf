package com.ceir.SLAModule.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.SLAModule.App;
import com.ceir.SLAModule.entity.ConsignmentMgmt;
import com.ceir.SLAModule.entity.SlaReport;
import com.ceir.SLAModule.entity.SystemConfigurationDb;
import com.ceir.SLAModule.repo.ConsignmentRepo;
import com.ceir.SLAModule.repoService.ConsignmentRepoService;
import com.ceir.SLAModule.repoService.SlaRepoService;
import com.ceir.SLAModule.repoService.SystemConfigRepoService;
import com.ceir.SLAModule.util.Utility;

@Service
public class ConsignmentService {

	@Autowired
	ConsignmentRepoService consignRepo;

	@Autowired
	Utility utility;

	@Autowired
	SystemConfigRepoService systemConfigRepoService;

	@Autowired
	ConsignmentRepo consignRepository;
	
	@Autowired
	SlaRepoService slaRepoService;
	private final static Logger log =LoggerFactory.getLogger(App.class);
	public void consignmentProcess(int status) {
		log.info("inside consignment sla process");
		log.info("now going to fetch consignment by status: "+status);
		List<ConsignmentMgmt> consignData=new ArrayList<ConsignmentMgmt>();
		try {
		 consignData=consignRepo.fetchConsignmentByStatus(status);
		}
		catch(Exception e) {
			log.info(e.toString());
			log.info(e.getMessage());
		}
		if(consignData.isEmpty()==false) {
			
			log.info("consignment data= "+consignData);
			log.info("consignment data is available for this status");
			String currentDate=utility.currentDate();
			log.info("currentDate is "+currentDate);
			Iterator<ConsignmentMgmt> consignIterator=consignData.iterator();
			log.info("now going to find number of days for approval of consignment by  ceir");
			SystemConfigurationDb systemconfig=systemConfigRepoService.getByTag("CONSIGN_PEN_FOR_CEIR_APPROV_IN_DAYS");
			if(systemconfig!=null) {
				long days=Long.parseLong(systemconfig.getValue());
                log.info("number of days for approval of consignment by  ceir is "+days);
				while(consignIterator.hasNext()) {
					ConsignmentMgmt consign=consignIterator.next();
					String modifiedDate=utility.convertlocalTimeToString(consign.getModifiedOn());
					log.info("consignment modified date= "+modifiedDate);
					if(modifiedDate!=null) {
						long dayDifferece=utility.getDifferenceDays(modifiedDate, currentDate);
						log.info("difference between current date and consignment modified date= "+dayDifferece);
						if(dayDifferece>days) {
							log.info("if difference greater than number of days for approval ");
							SlaReport  slaRepo=new SlaReport("Consignment","Pending Approval From CEIR Authority",consign.getUser(),
									consign.getTxnId());
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
						log.info("consignment modified date is empty");
					}
				}
			}
			else {
				log.info("data failed to find by CONSIGN_PEN_FOR_CEIR_APPROV_IN_DAYS tag in system_configuration_db table");
			}

		}
		else {
			log.info("consignment data failed to find when status is pending approval for custom");
		}
		log.info("exit from consignment sla process");  
	}
}
