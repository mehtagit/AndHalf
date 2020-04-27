package com.ceir.CEIRPostman.RepositoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CEIRPostman.Repository.EndUserRepo;
import com.ceir.CEIRPostman.model.EndUserDB;
@Service
public class EndUserRepoService {

	
	@Autowired
	EndUserRepo endUserRepo;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public EndUserDB getById(long id) {
		try {
			log.info("going to fetch end user data by Id"+id);
			return endUserRepo.findById(id);
		}
		catch(Exception e) {
			log.info("fail to fetch end user data by id"+id);
		   return new EndUserDB();
		}
	}
	
}

