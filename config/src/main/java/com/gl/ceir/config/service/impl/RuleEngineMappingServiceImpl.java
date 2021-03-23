package com.gl.ceir.config.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.RuleEngineMapping;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.file.RuleEngineMappingFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.RuleEngineMappingRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

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
	
	@Autowired
	SystemConfigurationDbRepository systemConfigurationDbRepository;
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
			logger.info("UserName while Updating Rule Mapping " +ruleEngineMapping.getUserName());
			ruleEngineMapping.setId(ruleEngineMappingOld.getId());
			ruleEngineMapping.setModifiedBy(ruleEngineMapping.getUserName());
			//			ruleEngineMapping.setCreatedOn(ruleEngineMappingOld.getCreatedOn());			
			ruleEngineMappingRepository.save(ruleEngineMapping);

			return new GenricResponse(0);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse save(RuleEngineMapping ruleEngineMapping){
		try {
			Optional<RuleEngineMapping> rule =Optional.ofNullable( ruleEngineMappingRepository.findByNameAndFeatureAndUserTypeAndRuleOrder(ruleEngineMapping.getName(),ruleEngineMapping.getFeature(),ruleEngineMapping.getUserType(),ruleEngineMapping.getRuleOrder()));
			
			if(ruleEngineMapping.getRuleOrder() == null) {
			Optional<RuleEngineMapping> object =Optional.ofNullable( ruleEngineMappingRepository.findByNameAndFeatureAndUserType(ruleEngineMapping.getName(),ruleEngineMapping.getFeature(),ruleEngineMapping.getUserType()));
			if(object.isPresent()) {
				return new GenricResponse(409, "COMBINATION_NOT_ALLOWED","Combination of Rule name, UserType and feature already exist", "");
			}
			}
			
			else if(rule.isPresent()) {
				return new GenricResponse(409, "ORDER_NOT_ALLOWED","The order value already exists for this combination of rule name, user type and feature name", "");
				
			}
			
			else{
			ruleEngineMappingRepository.save(ruleEngineMapping);
			return new GenricResponse(0);
			}
			
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
		return null;
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
			Integer pageSize,String operation) {

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			Page<RuleEngineMapping> page = ruleEngineMappingRepository.findAll( buildSpecification(filterRequest).build(), pageable );
			
			String operationType= "view".equalsIgnoreCase(operation) ? SubFeatures.VIEW_ALL : SubFeatures.EXPORT;
			auditTrailRepository.save( new AuditTrail( Long.valueOf(filterRequest.getUserId()),
					filterRequest.getUserName(), Long.valueOf(filterRequest.getUserTypeId()),
					"SystemAdmin", Long.valueOf(filterRequest.getFeatureId()),
					Features.RULE_FEATURE_MAPPING, operationType, "","NA",
					filterRequest.getRoleType(),filterRequest.getPublicIp(),filterRequest.getBrowser()));

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
			cmsb.orSearch(new SearchCriteria("name", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			cmsb.orSearch(new SearchCriteria("feature", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));	
			cmsb.orSearch(new SearchCriteria("userType", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));

		}
		return cmsb;
	}

	private void setInterp(RuleEngineMapping ruleEngineMapping) {
		/*if(Objects.nonNull(consignmentMgmt.getExpectedArrivalPort()))
			consignmentMgmt.setExpectedArrivalPortInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_PORT, consignmentMgmt.getExpectedArrivalPort()));
		 */
	}

	public FileDetails getFile(FilterRequest filter) {
		// TODO Auto-generated method stub

		logger.info("inside rule engine mapping ");
		logger.info("filter data:  "+filter);
		String fileName = null;
		Writer writer   = null;
		RuleEngineMappingFileModel uPFm = null;
		SystemConfigurationDb dowlonadDir=systemConfigurationDbRepository.getByTag("file.download-dir");
		SystemConfigurationDb dowlonadLink=systemConfigurationDbRepository.getByTag("file.download-link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Integer pageNo = 0;
		Integer pageSize = Integer.valueOf(systemConfigurationDbRepository.getByTag("file.max-file-record").getValue());
		String filePath  = dowlonadDir.getValue();
		StatefulBeanToCsvBuilder<RuleEngineMappingFileModel> builder = null;
		StatefulBeanToCsv<RuleEngineMappingFileModel> csvWriter      = null;
		List<RuleEngineMappingFileModel> fileRecords       = null;
		MappingStrategy<RuleEngineMappingFileModel> mapStrategy = new CustomMappingStrategy<>();	
		
		try {
			
			mapStrategy.setType(RuleEngineMappingFileModel.class);
			List<RuleEngineMapping> list = filterRuleEngineMapping(filter, pageNo, pageSize,"Export").getContent();
			logger.info("getting list : "+list);
			if( list.size()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_RuleEngineMapping.csv";
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_RuleEngineMapping.csv";
			}
			logger.info(" file path plus file name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
//			builder = new StatefulBeanToCsvBuilder<UserProfileFileModel>(writer);
//			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
//			
			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withMappingStrategy(mapStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( list.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<RuleEngineMappingFileModel>(); 
				for( RuleEngineMapping ruleEngineMapping : list ) {
					uPFm = new RuleEngineMappingFileModel();
					uPFm.setCreatedOn(ruleEngineMapping.getCreatedOn().format(dtf));
					uPFm.setModifiedOn(ruleEngineMapping.getModifiedOn().format(dtf));
					uPFm.setName(ruleEngineMapping.getName());
					uPFm.setFeature(ruleEngineMapping.getFeature());
					uPFm.setUserType(ruleEngineMapping.getUserType());
					uPFm.setRuleOrder(ruleEngineMapping.getRuleOrder());
					uPFm.setGraceAction(ruleEngineMapping.getGraceAction());
					uPFm.setPostGraceAction(ruleEngineMapping.getPostGraceAction());
					uPFm.setFailedRuleActionGrace(ruleEngineMapping.getFailedRuleActionGrace());
					uPFm.setFailedRuleActionPostGrace(ruleEngineMapping.getFailedRuleActionPostGrace());
					uPFm.setOutput(ruleEngineMapping.getOutput());
					fileRecords.add(uPFm);
				}
				logger.info("filePath::::"+uPFm);
				csvWriter.write(fileRecords);
			}
			logger.info("fileName::"+fileName);
			logger.info("filePath::::"+filePath);
			logger.info("link:::"+dowlonadLink.getValue());
			return new FileDetails(fileName, filePath,dowlonadLink.getValue().replace("$LOCAL_IP",propertiesReader.localIp)+fileName ); 
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( writer != null )
					writer.close();
			} catch (IOException e) {}
		}

	
	}

}