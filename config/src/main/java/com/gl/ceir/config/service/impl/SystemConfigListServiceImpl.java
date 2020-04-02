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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.SystemConfigListDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.Usertype;
import com.gl.ceir.config.model.file.SystemConfigListFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.SystemConfigListRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;


@Service
public class SystemConfigListServiceImpl {

	private static final Logger logger = LogManager.getLogger(SystemConfigListServiceImpl.class);

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired
	InterpSetter interpSetter;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	@Autowired
	SystemConfigListRepository systemConfigListRepository;

	@Autowired
	UserRepository userRepository;
	
	public GenricResponse saveSystemConfigList(SystemConfigListDb systemConfigListDb){
		try {
			if(Objects.isNull(systemConfigListDb.getTag())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}

			systemConfigListDb.setListOrder(0);
			
			systemConfigListRepository.save(systemConfigListDb);
			return new GenricResponse(0);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public GenricResponse updateSystemConfigList(SystemConfigListDb systemConfigListDb){
		try {
			if(Objects.isNull(systemConfigListDb.getId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}
			
			SystemConfigListDb systemConfigListDb2 = systemConfigListRepository.getById(systemConfigListDb.getId());
			systemConfigListDb2.setDescription(systemConfigListDb.getDescription());
			systemConfigListDb2.setTagId(systemConfigListDb.getTagId());
			systemConfigListDb2.setInterp(systemConfigListDb.getInterp());
			systemConfigListRepository.save(systemConfigListDb2);
			return new GenricResponse(0);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse getTagsList(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getUserId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}
			User user = userRepository.getById(filterRequest.getUserId());

			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), user.getUsername(), 0L, "System", 0L, 
					Features.CONFIG_LIST, SubFeatures.VIEW, ""));
			logger.info("AUDIT : Unique Tags list saved in audit_trail.");

			List<String> result = systemConfigListRepository.findDistinctTags();

			return new GenricResponse(0, "Sucess", "", result);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	public GenricResponse findDistinctTagsWithDescription(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getUserId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}
			User user = userRepository.getById(filterRequest.getUserId());

			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), user.getUsername(), 0L, "System", 0L, 
					Features.CONFIG_LIST, SubFeatures.VIEW, ""));
			logger.info("AUDIT : Unique Tags list saved in audit_trail.");

			List<SystemConfigListDb> systemConfigListDbs = systemConfigListRepository.findDistinctTagsWithDescription();

			return new GenricResponse(0, "Sucess", "", systemConfigListDbs);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse findById(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}
			User user = userRepository.getById(filterRequest.getUserId());

			auditTrailRepository.save(new AuditTrail(filterRequest.getUserId(), user.getUsername(),
					Usertype.SYSTEM_ADMIN.getCode(), 
					Usertype.SYSTEM_ADMIN.getName(),
					0L, 
					Features.CONFIG_LIST, SubFeatures.VIEW, ""));
			logger.info("AUDIT :  findById saved in audit_trail.");

			SystemConfigListDb systemConfigListDb = systemConfigListRepository.getById(filterRequest.getId());
			return new GenricResponse(0, "SUCCESS", "SUCCESS", systemConfigListDb);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<SystemConfigListDb> getAll(FilterRequest filterRequest) {

		try {
			List<SystemConfigListDb> systemConfigListDbs = systemConfigListRepository.findAll( buildSpecification(filterRequest).build());

			return systemConfigListDbs;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public Page<SystemConfigListDb> filter(FilterRequest filterRequest, Integer pageNo, 
			Integer pageSize) {

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			Page<SystemConfigListDb> page = systemConfigListRepository.findAll(buildSpecification(filterRequest).build(), pageable );

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public FileDetails getFilteredAuditTrailInFile(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		SystemConfigListFileModel fileModel = null;
		
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");

		if(Objects.isNull(filepath) || Objects.isNull(link)) {
			logger.info("CONFIG: MISSING : file_system_config_list_download_dir or file_system_config_list_download_link not found.");
			return null;
		}
		String filePath = filepath.getValue();
		StatefulBeanToCsvBuilder<SystemConfigListFileModel> builder = null;
		StatefulBeanToCsv<SystemConfigListFileModel> csvWriter = null;
		List< SystemConfigListFileModel > fileRecords = null;

		try {
			List<SystemConfigListDb> configListDbs = getAll(filterRequest);
			if( !configListDbs.isEmpty() ) {
				if(Objects.nonNull(filterRequest.getUserId()) && (filterRequest.getUserId() != -1 && filterRequest.getUserId() != 0)) {
					fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_Config_Tag.csv";
				}else {
					fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_ConfigTag.csv";
				}
			}else {
				fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "_Configtag.csv";
			}

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<SystemConfigListFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !configListDbs.isEmpty() ) {
				fileRecords = new ArrayList<>(); 

				for(SystemConfigListDb systemConfigListDb : configListDbs ) {
					fileModel = new SystemConfigListFileModel();

					// fileModel.setUserId(auditTrail.getUserId());
					
					logger.debug(fileModel);
					fileRecords.add(fileModel);
				}

				csvWriter.write(fileRecords);
			}else {
				csvWriter.write(new SystemConfigListFileModel());
			}
			return new FileDetails( fileName, filePath, link.getValue() + fileName ); 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {
				if( Objects.nonNull(writer) )
					writer.close();
			} catch (IOException e) {}
		}
	}

	private GenericSpecificationBuilder<SystemConfigListDb> buildSpecification(FilterRequest filterRequest){
		GenericSpecificationBuilder<SystemConfigListDb> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		if(Objects.nonNull(filterRequest.getTag()))
			cmsb.with(new SearchCriteria("tag", filterRequest.getTag(), SearchOperation.EQUALITY, Datatype.STRING));


		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			// cmsb.orSearch(new SearchCriteria("userName", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return cmsb;
	}

	public GenricResponse deleteValue(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getUserId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}
			User user = userRepository.getById(filterRequest.getUserId());

			auditTrailRepository.save(new AuditTrail(user.getId(), user.getUsername(), 0L, "System", 0L, 
					Features.CONFIG_LIST, SubFeatures.DELETE, ""));
			logger.info("AUDIT : Delete Tags list saved in audit_trail.");

			systemConfigListRepository.deleteById(filterRequest.getId());

			return new GenricResponse(0, "Sucess", "", "");

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
	
	/*
	private void setInterp(AuditTrail auditTrail) {
		if(Objects.nonNull(consignmentMgmt.getExpectedArrivalPort()))
			consignmentMgmt.setExpectedArrivalPortInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_PORT, consignmentMgmt.getExpectedArrivalPort()));

	}
	 */

}
