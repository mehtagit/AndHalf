package com.gl.ceir.config.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RuleEngine;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.repository.RuleEngineRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;

@Service
public class RuleEngineServiceImpl {

	private static final Logger logger = LogManager.getLogger(RuleEngineServiceImpl.class);

	@Autowired
	RuleEngineRepository ruleEngineRepository;
	
	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	@Autowired
	InterpSetter interpSetter;
	
	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	public RuleEngine findById(long id){
		try {
			return ruleEngineRepository.getById(id);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse updateById(RuleEngine ruleEngine){
		try {
			RuleEngine ruleEngineOld =  ruleEngineRepository.getById(ruleEngine.getId());
			ruleEngine.setId(ruleEngineOld.getId());
			
			ruleEngineRepository.save(ruleEngine);
			
			return new GenricResponse(0);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public Page<RuleEngine> filterRuleEngine(FilterRequest filterRequest, Integer pageNo, 
			Integer pageSize) {

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			Page<RuleEngine> page = ruleEngineRepository.findAll( buildSpecification(filterRequest).build(), pageable );

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}
	
	public List<RuleEngine> allRuleNames() {

		try {
			
			return ruleEngineRepository.findAll();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	private GenericSpecificationBuilder<RuleEngine> buildSpecification(FilterRequest filterRequest){
		//ranjeet

		GenericSpecificationBuilder<RuleEngine> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
		
		
		if(Objects.nonNull(filterRequest.getState()))
			cmsb.with(new SearchCriteria("state", filterRequest.getState(), SearchOperation.EQUALITY, Datatype.STRING));
		
		 if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			 
		     cmsb.orSearch(new SearchCriteria("name", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			 cmsb.orSearch(new SearchCriteria("state", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			 cmsb.orSearch(new SearchCriteria("description", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			 
	

		 }
		

		return cmsb;
	
		
		/*
		GenericSpecificationBuilder<RuleEngine> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
		
		if(Objects.nonNull(filterRequest.getState()))
			cmsb.with(new SearchCriteria("state", filterRequest.getState(), SearchOperation.EQUALITY, Datatype.STRING));
		
		 if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			 
		     cmsb.orSearch(new SearchCriteria("name", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			 cmsb.orSearch(new SearchCriteria("state", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			 cmsb.orSearch(new SearchCriteria("description", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			 
	

		 }
		

		return cmsb;
	*/}

	private void setInterp(RuleEngine ruleEngine) {
		/*if(Objects.nonNull(consignmentMgmt.getExpectedArrivalPort()))
			consignmentMgmt.setExpectedArrivalPortInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_PORT, consignmentMgmt.getExpectedArrivalPort()));
		 */
	}

}