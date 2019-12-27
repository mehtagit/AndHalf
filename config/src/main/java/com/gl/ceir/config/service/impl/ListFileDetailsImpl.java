package com.gl.ceir.config.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.FileDumpMgmt;
import com.gl.ceir.config.model.GreylistDb;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.repository.FileDumpMgmtRepository;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;

@Service
public class ListFileDetailsImpl {

	private static final Logger logger = LogManager.getLogger(ListFileDetailsImpl.class);

	@Autowired
	FileDumpMgmtRepository fileDumpMgmtRepository;

	@Autowired
	PropertiesReader propertiesReader;
	
	public List<FileDumpMgmt> getByListType(String listType){
		try {

			return fileDumpMgmtRepository.getByServiceDump(listType);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public FileDumpMgmt topDataByDumpType(String dumpType) {
		try {
			return fileDumpMgmtRepository.findTopByDumpTypeOrderByIdDesc(dumpType);
		}
		catch(Exception e) {
			e.printStackTrace();
			FileDumpMgmt fileDumpMgmt=new FileDumpMgmt();
			return fileDumpMgmt;
		}
	}
	
	public FileDumpMgmt saveFileDumpMgmt(FileDumpMgmt fileDumpMgmt) {
		try {
			return fileDumpMgmtRepository.save(fileDumpMgmt);
		}
		catch(Exception e) {
			e.printStackTrace();
			FileDumpMgmt fileData=new FileDumpMgmt();
			return fileData;
		}
	}
	
	
	
	
}
