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

import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.file.AuditTrailFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class AuditTrailServiceImpl {

	private static final Logger logger = LogManager.getLogger(AuditTrailServiceImpl.class);

	@Autowired
	FileStorageProperties fileStorageProperties;

	@Autowired
	AuditTrailRepository auditTrailRepository;

	@Autowired
	PropertiesReader propertiesReader;

	@Autowired
	Utility utility;

	@Autowired	
	EmailUtil emailUtil;

	@Autowired
	InterpSetter interpSetter;

	public AuditTrail findById(long id){
		try {
			return auditTrailRepository.getById(id);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}
	}

	public List<AuditTrail> getAll(FilterRequest filterRequest) {

		try {
			List<AuditTrail> auditTrails = auditTrailRepository.findAll( buildSpecification(filterRequest).build());

			for(AuditTrail auditTrail : auditTrails ) {
				setInterp(auditTrail);
			}

			return auditTrails;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public Page<AuditTrail> filterAuditTrail(FilterRequest filterRequest, Integer pageNo, 
			Integer pageSize) {

		try {
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			Page<AuditTrail> page = auditTrailRepository.findAll( buildSpecification(filterRequest).build(), pageable );

			for(AuditTrail auditTrail : page.getContent()) {
				setInterp(auditTrail);
			}

			return page;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}

	public FileDetails getFilteredAuditTrailInFile(FilterRequest filterRequest) {
		String fileName = null;
		Writer writer   = null;
		AuditTrailFileModel atfm = null;
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		String filePath = fileStorageProperties.getAuditTrailDownloadDir();
		StatefulBeanToCsvBuilder<AuditTrailFileModel> builder = null;
		StatefulBeanToCsv<AuditTrailFileModel> csvWriter = null;
		List< AuditTrailFileModel > fileRecords = null;

		try {
			List<AuditTrail> auditTrails = getAll(filterRequest);
			if( !auditTrails.isEmpty() ) {
				if(Objects.nonNull(filterRequest.getUserId()) && (filterRequest.getUserId() != -1 && filterRequest.getUserId() != 0)) {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_AuditTrails.csv";
				}else {
					fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_AuditTrails.csv";
				}
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_") + "_AuditTrails.csv";
			}

			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<AuditTrailFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( !auditTrails.isEmpty() ) {
				fileRecords = new ArrayList<>(); 

				for(AuditTrail auditTrail : auditTrails ) {
					atfm = new AuditTrailFileModel();

					atfm.setUserId(auditTrail.getUserId());
					atfm.setFeatureName(auditTrail.getFeatureName());
					atfm.setSubFeatureName(auditTrail.getSubFeature());

					logger.debug(atfm);

					fileRecords.add(atfm);
				}

				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath, fileStorageProperties.getAuditTrailDownloadLink() + fileName ); 

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

	private SpecificationBuilder<AuditTrail> buildSpecification(FilterRequest filterRequest){
		SpecificationBuilder<AuditTrail> cmsb = new SpecificationBuilder<>(propertiesReader.dialect);

		if (!"SystemAdmin".equalsIgnoreCase(filterRequest.getUserType())) {
			if(Objects.nonNull(filterRequest.getUserId()))
				cmsb.with(new SearchCriteria("userId", filterRequest.getUserId(), SearchOperation.EQUALITY, Datatype.STRING));
		}
		return cmsb;
	}

	private void setInterp(AuditTrail auditTrail) {
		/*if(Objects.nonNull(consignmentMgmt.getExpectedArrivalPort()))
			consignmentMgmt.setExpectedArrivalPortInterp(interpSetter.setConfigInterp(Tags.CUSTOMS_PORT, consignmentMgmt.getExpectedArrivalPort()));
		 */
	}

}
