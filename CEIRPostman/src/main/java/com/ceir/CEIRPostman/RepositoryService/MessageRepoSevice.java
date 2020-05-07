package com.ceir.CEIRPostman.RepositoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CEIRPostman.Repository.MessageConfigurationDbRepository;
import com.ceir.CEIRPostman.model.MessageConfigurationDb;

@Service
public class MessageRepoSevice {

	@Autowired
	MessageConfigurationDbRepository messageRepo;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public MessageConfigurationDb getByTag(String tag) {
		try
		{
			return messageRepo.getByTag(tag);
		}
		catch(Exception e) {
			logger.info("Error occurs when finding Message data by this tag "+tag);
			return new MessageConfigurationDb();
		}
	}
	
}
