package com.ceir.CEIRPostman.RepositoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CEIRPostman.Repository.UserTemporarydetailsRepo;
import com.ceir.CEIRPostman.model.UserTemporarydetails;

@Service
public class UserTempRepoService {

	@Autowired
	UserTemporarydetailsRepo userTempRepo;
	

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public UserTemporarydetails getUserTempByUserId(long id) {
		try {
			log.info("going to fetch user temporary details by user id "+id);
			  return userTempRepo.findByUserDetails_id(id);
		}
		catch(Exception e) {
			log.info("fail to fetch user temporary details by user id "+id);
			log.info(e.toString());
			return new UserTemporarydetails();
		}
	}
}
