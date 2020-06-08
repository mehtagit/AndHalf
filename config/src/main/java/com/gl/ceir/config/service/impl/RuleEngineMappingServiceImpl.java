package com.gl.ceir.config.service.impl;

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
import com.gl.ceir.config.audit.AuditTrailMethods;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RuleEngineMapping;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.RuleEngineMappingRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;

@Service
public class RuleEngineMappingServiceImpl {

	private static final Logger logger = LogManager.getLogger(RuleEngineMappingServiceImpl.class);

	@Autowired
	RuleEngineMappingRepository ruleEngineMappingRepository;
	
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
	
	
	@Autowired
	AuditTrailRepository auditTrailRepository;
	public RuleEngineMapping findById(long id){
		try {
			return ruleEngineMappingRepository.getById(id);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse updateById(RuleEngineMapping ruleEngineMapping){
		try {
			RuleEngineMapping ruleEngineMappingOld =  ruleEngineMappingRepository.getById(ruleEngineMapping.getId());
			logger.info("ruleEngineMappingOld : " + ruleEngineMappingOld);
			ruleEngineMapping.setId(ruleEngineMappingOld.getId());
			
			ruleEngineMappingRepository.save(ruleEngineMapping);
			
			return new GenricResponse(0);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public GenricResponse save(RuleEngineMapping ruleEngineMapping){
		try {
			
			ruleEngineMappingRepository.save(ruleEngineMapping);
			return new GenricResponse(0);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public GenricResponse deleteById(RuleEngineMapping ruleEngineMapping){
		try {
			ruleEngineMappingRepository.deleteById(ruleEngineMapping.getId());
		
			return new GenricResponse(0);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public Page<RuleEngineMapping> filterRuleEngineMapping(FilterRequest filterRequest, Integer pageNo, 
			Integer pageSize) {

		try {
			
			
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));  

			Page<RuleEngineMapping> page = ruleEngineMappingRepository.findAll( buildSpecification(filterRequest).build(), pageable );
		
			auditTrailRepository.save( new AuditTrail( filterRequest.getUserId(),
			  filterRequest.getUserName(), Long.valueOf(filterRequest.getUserTypeId()),
			   "SystemAdmin", Long.valueOf(filterRequest.getFeatureId()),
			  Features.RULE_FEATURE_MAPPING, SubFeatures.VIEW, "","NA",
			  filterRequest.getRoleType()));
			 
			return page;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	
	private GenericSpecificationBuilder<RuleEngineMapping> buildSpecification(FilterRequest filterRequest){
		GenericSpecificationBuilder<RuleEngineMapping> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);
		
		if(Objects.nonNull(filterRequest.getRuleName()))
			cmsb.with(new SearchCriteria("name", filterRequest.getRuleName(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getFeatureName()))
			cmsb.with(new SearchCriteria("feature", filterRequest.getFeatureName(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getUserType()))
			cmsb.with(new SearchCriteria("userType", filterRequest.getUserType(), SearchOperation.EQUALITY, Datatype.STRING));
		
		
		 if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			 cmsb.orSearch(new SearchCriteria("ruleOrder", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));	
		 }
		return cmsb;
	}

	private void setInterp(RuleEngineMapping ruleEngineMapping) {
		/*if(Objects.nonNull(consignmentMgmt.getExpectedArrivalPort()))
			consignmentMgmt.setExpectedArrivalPortInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_PORT, consignmentMgmt.getExpectedArrivalPort()));
		 */
	}

}