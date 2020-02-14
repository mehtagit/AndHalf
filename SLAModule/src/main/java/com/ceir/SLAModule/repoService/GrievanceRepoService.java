package com.ceir.SLAModule.repoService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.SLAModule.App;
import com.ceir.SLAModule.entity.Grievance;
import com.ceir.SLAModule.repo.GrievanceRepo;
@Service
public class GrievanceRepoService {

	@Autowired
	GrievanceRepo grievanceRepo;
	
private final static Logger log =LoggerFactory.getLogger(App.class);
	
	public List<Grievance> fetchGrievanceByStatus(int status){
		try {
			log.info("now fetching grievance data by status");
			return grievanceRepo.findByGrievanceStatus(status);
		}
		catch(Exception e) {
			log.info("error occuring when fetch grievance data by status");
			log.info(e.toString());
			return new ArrayList<Grievance>();
		}
	}
}
