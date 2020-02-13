package com.gl.ceir.config.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ActionsConfigDb;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.StatesInterpretationDb;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.TableActions;
import com.gl.ceir.config.model.TagsMapping;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.repository.ActionConfigRepository;
import com.gl.ceir.config.repository.StateMgmtRepository;
import com.gl.ceir.config.repository.StatesInterpretaionRepository;
import com.gl.ceir.config.repository.SystemConfigListRepository;
import com.gl.ceir.config.repository.TagsMappingRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.util.Utility;

@Service
public class TagsMappingServiceImpl {

	private static final Logger logger = LogManager.getLogger(TagsMappingServiceImpl.class);

	@Autowired
	private TagsMappingRepository tagsMappingRepository;
	
	@Autowired
	private SystemConfigListRepository systemConfigListRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	/*
	 * 
	 * @future_scope : must use a join between state_mgmt_db and states_interpretation_db
	 */
	public List<SystemConfigListDb> getByFilter(FilterRequest filterRequest) {
		try {
			List<SystemConfigListDb> SystemConfigListDbResult = new ArrayList<>();

			List<TagsMapping> tagsMappings = tagsMappingRepository.findAll(buildSpecification(filterRequest).build());
			logger.info(tagsMappings);
			
			List<SystemConfigListDb> systemConfigListDbs = systemConfigListRepository
					.findByTag(filterRequest.getChildTag(), new Sort(Sort.Direction.ASC, "listOrder"));
			logger.debug(systemConfigListDbs);

			for(SystemConfigListDb systemConfigListDb : systemConfigListDbs) {

				for(TagsMapping tagsMapping : tagsMappings) {
					if(systemConfigListDb.getValue() == tagsMapping.getChildValue()) {
						SystemConfigListDbResult.add(systemConfigListDb);
					}
				}
			}

			return SystemConfigListDbResult;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private GenericSpecificationBuilder<TagsMapping> buildSpecification(FilterRequest filterRequest){
		GenericSpecificationBuilder<TagsMapping> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		if(Objects.nonNull(filterRequest.getFeatureId()))
			cmsb.with(new SearchCriteria("featureId", filterRequest.getFeatureId(), SearchOperation.EQUALITY, Datatype.STRING));
		
		if(Objects.nonNull(filterRequest.getUserTypeId()))
			cmsb.with(new SearchCriteria("userTypeId", filterRequest.getUserTypeId() , SearchOperation.EQUALITY, Datatype.STRING));
		
		if(Objects.nonNull(filterRequest.getTag()))
			cmsb.with(new SearchCriteria("parentTag", filterRequest.getTag() , SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getChildTag()))
			cmsb.with(new SearchCriteria("childTag", filterRequest.getChildTag(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getParentValue()))
			cmsb.with(new SearchCriteria("parentValue", filterRequest.getParentValue(), SearchOperation.EQUALITY, Datatype.STRING));

		return cmsb;
	}

}
