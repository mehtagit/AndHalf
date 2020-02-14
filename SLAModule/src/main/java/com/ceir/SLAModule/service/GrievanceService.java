package com.ceir.SLAModule.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.SLAModule.App;
import com.ceir.SLAModule.entity.Grievance;
import com.ceir.SLAModule.entity.SlaReport;
import com.ceir.SLAModule.entity.SystemConfigurationDb;
import com.ceir.SLAModule.repoService.GrievanceRepoService;
import com.ceir.SLAModule.repoService.SlaRepoService;
import com.ceir.SLAModule.repoService.SystemConfigRepoService;
import com.ceir.SLAModule.util.Utility;
@Service
public class GrievanceService {

	@Autowired
	GrievanceRepoService grievanceRepo;

	@Autowired
	Utility utility;

	@Autowired
	SystemConfigRepoService systemConfigRepoService;

	
	@Autowired
	SlaRepoService slaRepoService;
	private final static Logger log =LoggerFactory.getLogger(App.class);
	public void grievanceProcess(int status) {
		log.info("inside Greivance sla process");
		log.info("now going to fetch Greivance by status: "+status);
		List<Grievance> greivanceData=new ArrayList<Grievance>();
		try {
			greivanceData=grievanceRepo.fetchGrievanceByStatus(status);
		}
		catch(Exception e) {
			log.info(e.toString());
			log.info(e.getMessage());
		}
		if(greivanceData.isEmpty()==false) {
			
			log.info("Greivance data= "+greivanceData);
			log.info("Greivance data is available for this status");
			String currentDate=utility.currentDate();
			log.info("currentDate is "+currentDate);
			Iterator<Grievance> grievIterator=greivanceData.iterator();
			log.info("now going to find number of days for approval of Greivance by  ceir");
			SystemConfigurationDb systemconfig=systemConfigRepoService.getByTag("GRIEV_PEN_WITH_ADMIN");
			if(systemconfig!=null) {
				long days=Long.parseLong(systemconfig.getValue());
                log.info("number of days for approval of Greivance by  ceir is "+days);
				while(grievIterator.hasNext()) {
					Grievance grievance=grievIterator.next();
					String modifiedDate=utility.convertlocalTimeToString(grievance.getModifiedOn());
					log.info("Greivance modified date= "+modifiedDate);
					if(modifiedDate!=null) {
						long dayDifferece=utility.getDifferenceDays(modifiedDate, currentDate);
						log.info("difference between current date and greivance modified date= "+dayDifferece);
						if(dayDifferece>days) {
							log.info("if difference greater than number of days for approval ");
							SlaReport  slaRepo=new SlaReport("Grievance","Pending  From CEIR Admin",grievance.getUser(),
									grievance.getTxnId());
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
						log.info("greivance modified date is empty");
					}
				}
			}
			else {
				log.info("data failed to find by GRIEV_PEN_WITH_ADMIN tag in system_configuration_db table");
			}

		}
		else {
			log.info("greivance data failed to find when status is pending approval for custom");
		}
		log.info("exit from greivance sla process");  
	}
}
