package com.ceir.CeirCode.repoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repo.SystemConfigDbRepository;
@Service
public class SystemConfigDbRepoService {

//	@Autowired
//	SystemConfigDbListRepository systemConfigRepo;
//	
//	public ArrayList<SystemConfigListDb> getDataByTag(String tag) {
//		
//	try {
//		ArrayList<SystemConfigListDb> systemConfigurationDb=systemConfigRepo.getByTag(tag);
//		return systemConfigurationDb;
//	}
//		
//	catch(Exception e) {
//		return new ArrayList<SystemConfigListDb>();
//	}
//	}
	
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
