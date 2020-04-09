package com.gl.ceir.factory.service.transaction;

import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.entity.EndUserDB;
import com.gl.ceir.entity.VisaDb;
import com.gl.ceir.notifier.NotifierWrapper;
import com.gl.ceir.repo.EndUserDbRepository;

@Component
@Transactional(rollbackOn = Exception.class)
public class ExpireVisaTransaction {
	
	private static final Logger logger = LogManager.getLogger(ExpireVisaTransaction.class);

	@Autowired
	EndUserDbRepository endUserDbRepository;
	
	@Autowired
	NotifierWrapper notifierWrapper;
	
	@Transactional
	public void performTransaction(EndUserDB endUserDB, VisaDb visaDb) {
		if(Objects.isNull(endUserDB)) {
			logger.info("No end user is found associated with visa of id [" + visaDb.getId() + "]");
		}else {
			logger.info("Going to delete end user with nid [" + endUserDB.getNid() + "]");
			endUserDbRepository.deleteByNid(endUserDB.getNid());
			logger.info("End user is deleted successfully [" + endUserDB.getNid() + "]");
		}
	}

}
