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
import com.gl.ceir.config.model.PendingTacApprovedDb;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.GenericMessageTags;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.file.AuditTrailFileModel;
import com.gl.ceir.config.model.file.PendingTacApprovedFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.PendingTacApprovedRepository;
import com.gl.ceir.config.repository.UserRepository;
import com.gl.ceir.config.specificationsbuilder.GenericSpecificationBuilder;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class PendingTacApprovedImpl {

	private static final Logger logger = LogManager.getLogger(PendingTacApprovedImpl.class);

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired
	InterpSetter interpSetter;

	@Autowired
	PendingTacApprovedRepository pendingTacApprovedRepository;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConfigurationManagementServiceImpl configurationManagementServiceImpl;

	public GenricResponse saveSystemConfigList(PendingTacApprovedDb pendingTacApprovedDb){
		try {
			if(Objects.isNull(pendingTacApprovedDb.getTac())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}

			pendingTacApprovedRepository.save(pendingTacApprovedDb);
			return new GenricResponse(0);

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

			PendingTacApprovedDb pendingTacApprovedDb = pendingTacApprovedRepository.getById(filterRequest.getId());
			return new GenricResponse(0, "SUCCESS", "SUCCESS", pendingTacApprovedDb);

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse findByTxnId(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getTxnId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}

			PendingTacApprovedDb pendingTacApprovedDb = pendingTacApprovedRepository.getByTxnId(filterRequest.getTxnId());
			if(Objects.nonNull(pendingTacApprovedDb)) {
				return new GenricResponse(0, "SUCCESS", "SUCCESS", pendingTacApprovedDb);
			}else {
				return new GenricResponse(1, "Not Found", "Not Found", "");
			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public GenricResponse deletePendingApproval(FilterRequest filterRequest){
		try {
			if(Objects.isNull(filterRequest.getUserId())) {
				return new GenricResponse(1, GenericMessageTags.NULL_REQ.getTag(), 
						GenericMessageTags.NULL_REQ.getMessage(), null);
			}
			User user = userRepository.getById(filterRequest.getUserId());

			auditTrailRepository.save(new AuditTrail(user.getId(), user.getUsername(), 0L, "System", 0L, 
					Features.CONFIG_LIST, SubFeatures.DELETE, ""));
			logger.info("AUDIT : Delete Tags list saved in audit_trail.");

			if(Objects.nonNull(filterRequest.getTxnId())) {
				pendingTacApprovedRepository.deleteByTxnId(filterRequest.getTxnId());
				return new GenricResponse(0, "Deleted Successully.", "", "");
			}else if(Objects.nonNull(filterRequest.getTac()) && Objects.nonNull(filterRequest.getImporterId())){
				pendingTacApprovedRepository.deleteByTacAndUserId(filterRequest.getTac(), filterRequest.getImporterId());
				return new GenricResponse(0, "Deleted Successully.", "", "");
			}else {
				return new GenricResponse(1, "No Deletion Allowed.", "", "");
			}

		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public Page<PendingTacApprovedDb> filterPendingTacApprovedDb(FilterRequest filterRequest, Integer pageNo,
			Integer pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			Page<PendingTacApprovedDb> page = pendingTacApprovedRepository.findAll( buildSpecification(filterRequest).build(), pageable );

			/*
			 * for(AuditTrail auditTrail : page.getContent()) { setInterp(auditTrail); }
			 */

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	private GenericSpecificationBuilder<PendingTacApprovedDb> buildSpecification(FilterRequest filterRequest){
		GenericSpecificationBuilder<PendingTacApprovedDb> cmsb = new GenericSpecificationBuilder<>(propertiesReader.dialect);

		 if(Objects.nonNull(filterRequest.getUserId())) {
			 cmsb.with(new SearchCriteria("userId", filterRequest.getFilteredUserId(), SearchOperation.EQUALITY, Datatype.STRING));
		 }
		 if(Objects.nonNull(filterRequest.getStartDate()) && !filterRequest.getStartDate().isEmpty())
			 cmsb.with(new SearchCriteria("createdOn", filterRequest.getStartDate() , SearchOperation.GREATER_THAN, Datatype.DATE));

		 if(Objects.nonNull(filterRequest.getEndDate()) && !filterRequest.getEndDate().isEmpty())
			 cmsb.with(new SearchCriteria("createdOn", filterRequest.getEndDate() , SearchOperation.LESS_THAN, Datatype.DATE));

		 if(Objects.nonNull(filterRequest.getTxnId()) && !filterRequest.getTxnId().isEmpty())
			 cmsb.with(new SearchCriteria("txnId", filterRequest.getTxnId(), SearchOperation.EQUALITY, Datatype.STRING));

		 if(Objects.nonNull(filterRequest.getFeatureName()) && !filterRequest.getFeatureName().isEmpty())
			 cmsb.with(new SearchCriteria("featureName", filterRequest.getFeatureName(), SearchOperation.EQUALITY, Datatype.STRING));

		 if(Objects.nonNull(filterRequest.getUserType()) && !filterRequest.getUserType().isEmpty())
			 cmsb.with(new SearchCriteria("userType", filterRequest.getUserType(), SearchOperation.EQUALITY, Datatype.STRING));
		 if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){

			 cmsb.orSearch(new SearchCriteria("userType", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));

			 cmsb.orSearch(new SearchCriteria("featureName", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));

			 cmsb.orSearch(new SearchCriteria("txnId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			 
		 }
		 return cmsb;
	}

	public FileDetails getFilteredPendingTacApprovedDbInFile(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		PendingTacApprovedFileModel atfm = null;

		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter dtf2  = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

		SystemConfigurationDb filepath = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_dir);
		logger.info("CONFIG : file_consignment_download_dir [" + filepath + "]");
		SystemConfigurationDb link = configurationManagementServiceImpl.findByTag(ConfigTags.file_download_link);
		logger.info("CONFIG : file_consignment_download_link [" + link + "]");

		String filePath = filepath.getValue();
		StatefulBeanToCsvBuilder<PendingTacApprovedFileModel> builder = null;
		StatefulBeanToCsv<PendingTacApprovedFileModel> csvWriter = null;
		List<PendingTacApprovedFileModel> fileRecords = null;

		try {
			List<PendingTacApprovedDb> pendingTacApprovedDbs = getAll(filterRequest);
			if( !pendingTacApprovedDbs.isEmpty() ) {
				if(Objects.nonNull(filterRequest.getUserId()) && (filterRequest.getUserId() != -1 && filterRequest.getUserId() != 0)) {
					fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "PendingTacApprovedDbs.csv";
				}else {
					fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "PendingTacApprovedDbs.csv";
				}
			}else {
				fileName = LocalDateTime.now().format(dtf2).replace(" ", "_") + "PendingTacApprovedDbs.csv";
			}

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !pendingTacApprovedDbs.isEmpty() ) {
				fileRecords = new ArrayList<>(); 


				for(PendingTacApprovedDb pendingTacApprovedDb : pendingTacApprovedDbs ) { 
					atfm = new PendingTacApprovedFileModel();

					atfm.setCreatedOn(pendingTacApprovedDb.getCreatedOn().toString());
					atfm.setTxnId(pendingTacApprovedDb.getTxnId()); 
					atfm.setTac(pendingTacApprovedDb.getTac());
					atfm.setFeatureName(pendingTacApprovedDb.getFeatureName());
					atfm.setUserType(pendingTacApprovedDb.getUserType());

					logger.debug(atfm);

					fileRecords.add(atfm); }


				csvWriter.write(fileRecords);
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

	private List<PendingTacApprovedDb> getAll(FilterRequest filterRequest) {
		try {
			List<PendingTacApprovedDb> pendingTacApprovedDbs = pendingTacApprovedRepository.findAll( buildSpecification(filterRequest).build());

			/*
			 * for(AuditTrail auditTrail : auditTrails ) { setInterp(auditTrail); }
			 */

			return pendingTacApprovedDbs;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}
}
