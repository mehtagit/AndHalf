package com.gl.ceir.config.service.impl;




import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ceir.config.ConfigTags;
import com.gl.ceir.config.EmailSender.EmailUtil;
import com.gl.ceir.config.EmailSender.MailSubjects;
import com.gl.ceir.config.configuration.FileStorageProperties;
import com.gl.ceir.config.configuration.PropertiesReader;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.AuditTrail;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.ConsignmentUpdateRequest;
import com.gl.ceir.config.model.FileDetails;
import com.gl.ceir.config.model.FilterRequest;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.ResponseCountAndQuantity;
import com.gl.ceir.config.model.SearchCriteria;
import com.gl.ceir.config.model.StateMgmtDb;
import com.gl.ceir.config.model.SystemConfigurationDb;
import com.gl.ceir.config.model.User;
import com.gl.ceir.config.model.UserProfile;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.brandRepoModel;
import com.gl.ceir.config.model.constants.ConsignmentStatus;
import com.gl.ceir.config.model.constants.Datatype;
import com.gl.ceir.config.model.constants.Features;
import com.gl.ceir.config.model.constants.SearchOperation;
import com.gl.ceir.config.model.constants.SubFeatures;
import com.gl.ceir.config.model.constants.Tags;
import com.gl.ceir.config.model.constants.TaxStatus;
import com.gl.ceir.config.model.constants.WebActionDbFeature;
import com.gl.ceir.config.model.constants.WebActionDbState;
import com.gl.ceir.config.model.constants.WebActionDbSubFeature;
import com.gl.ceir.config.model.file.ConsignmentFileModel;
import com.gl.ceir.config.repository.AuditTrailRepository;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.MessageConfigurationDbRepository;
import com.gl.ceir.config.repository.StockDetailsOperationRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.SystemConfigurationDbRepository;
import com.gl.ceir.config.repository.UserProfileRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;
import com.gl.ceir.config.repository.brandRepository;
import com.gl.ceir.config.repository.modelRepoModel;
import com.gl.ceir.config.service.businesslogic.StateMachine;
import com.gl.ceir.config.specificationsbuilder.SpecificationBuilder;
import com.gl.ceir.config.strategy.ConsignmentCsvMappingStrategy;
import com.gl.ceir.config.util.CustomMappingStrategy;
import com.gl.ceir.config.util.InterpSetter;
import com.gl.ceir.config.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class BrandServiceImpl {

	@Autowired
	private brandRepository brandRepository;

	private static final Logger logger = LogManager.getLogger(BrandServiceImpl.class);

	public List<brandRepoModel> getAllBrands() {
		try {
			logger.info("Going to get All Brand List ");
			return brandRepository.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}

	}
	//private final Path fileStorageLocation;

}
