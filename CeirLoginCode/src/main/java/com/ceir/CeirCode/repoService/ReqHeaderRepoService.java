package com.ceir.CeirCode.repoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.WebActionTbl;
import com.ceir.CeirCode.repo.ReqHeadersRepo;
import com.ceir.CeirCode.repo.WebActionRepo;

@Service
public class ReqHeaderRepoService {

	@Autowired
	ReqHeadersRepo reqHeaderRepo;
	
	@Autowired
	WebActionRepo webActionRepo;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	
	public RequestHeaders saveRequestHeader(RequestHeaders headers) {
	    try
	    {
        log.info("request headers going to save");	   
	    return 	reqHeaderRepo.save(headers);
	    }
	    catch(Exception e)
	    {
	    	log.info("error occurs while save request headers");
	    	log.info(e.toString());
	    	return null;	
	    }
		
		
	}
	
	public WebActionTbl  save(WebActionTbl webActionDb) {
		
		try {
			log.info("going to save in Web Action DB");
			return webActionRepo.save(webActionDb);
			
		}
		catch(Exception e) {
			log.info("error occurs while adding  port address data");
			log.info(e.toString());
			return  null;
		}
	}
	
}
