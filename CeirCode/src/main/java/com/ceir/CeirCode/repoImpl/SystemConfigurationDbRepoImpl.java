package com.ceir.CeirCode.repoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.repo.SystemConfigDbRepository;
@Service
public class SystemConfigurationDbRepoImpl {

	@Autowired
	SystemConfigDbRepository systemConfigRepo;
	
	public SystemConfigurationDb getDataByTag(String tag) {
		
	try {
		SystemConfigurationDb systemConfigurationDb=systemConfigRepo.findByTag(tag);
		return systemConfigurationDb;
	}
		
	catch(Exception e) {
		return null;
	}
	}
}
