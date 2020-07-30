package com.ceir.SLAModule.repoService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.SLAModule.App;
import com.ceir.SLAModule.entity.ConsignmentMgmt;
import com.ceir.SLAModule.repo.ConsignmentRepo;

@Service
public class ConsignmentRepoService {

	@Autowired
	ConsignmentRepo consignRepo;
	
	private final static Logger log =LoggerFactory.getLogger(App.class);
	
	public List<ConsignmentMgmt> fetchConsignmentByStatus(int status){
		try {
			log.info("now fetching consignment data by status");
			return consignRepo.findByConsignmentStatus(status);
		}
		catch(Exception e) {
			log.info("error occuring when fetch consigment data b status");
			log.info(e.toString());
			return new ArrayList<ConsignmentMgmt>();
		}
	}
	
}

