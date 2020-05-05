package com.ceir.CeirCode.service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.Constants.Datatype;
import com.ceir.CeirCode.Constants.SearchOperation;
import com.ceir.CeirCode.SpecificationBuilder.GenericSpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.exceptions.ResourceServicesException;
import com.ceir.CeirCode.filemodel.SlaFile;
import com.ceir.CeirCode.filtermodel.SlaFilter;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.SlaReport;
import com.ceir.CeirCode.model.StakeholderFeature;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.repo.SlaRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.SlaRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.UserRepoService;
import com.ceir.CeirCode.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
@Service
public class SlaService {

	
	private final Logger log = LoggerFactory.getLogger(getClass());


	@Autowired
	PropertiesReaders propertiesReader;
	@Autowired
	UserService userService;

	@Autowired
	UserRepoService userRepoService;
	
	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	@Autowired
	Utility utility;

	@Autowired
	SlaRepo slaRepo;
	
	@Autowired
	SlaRepoService slaRepoService;
	
	@Autowired
	FeatureService featureService;
	
	@Autowired
	ReqHeaderRepoService headerService;
	
	private GenericSpecificationBuilder<SlaReport> buildSpecification(SlaFilter filterRequest){
		
		GenericSpecificationBuilder<SlaReport> uPSB = new GenericSpecificationBuilder<SlaReport>(propertiesReader.dialect);	

		if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getFeature()) && filterRequest.getFeature()!=-1)
			uPSB.with(new SearchCriteria("featureId",filterRequest.getFeature(), SearchOperation.EQUALITY, Datatype.INTEGER));

		if(Objects.nonNull(filterRequest.getUsertype()) && filterRequest.getUsertype()!=-1)
			uPSB.with(new SearchCriteria("usertypeId",filterRequest.getUsertype(), SearchOperation.EQUALITY, Datatype.INTEGER));

		
		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
		uPSB.orSearch(new SearchCriteria("username", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("stateInterp", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("txnId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return uPSB;
	}
	
	public List<SlaReport> getAll(SlaFilter filterRequest) {

		try {
			List<SlaReport> systemConfigListDbs = slaRepo.findAll( buildSpecification(filterRequest).build());

			return systemConfigListDbs;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;
		}

	}
	
	
	public Page<SlaReport>  viewAllSlaData(SlaFilter filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
			RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
			headerService.saveRequestHeader(header);
			userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
					filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.SLA_Management,SubFeatures.VIEW_ALL,filterRequest.getFeatureId());

			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
            Page<SlaReport> page=slaRepo.findAll(buildSpecification(filterRequest).build(),pageable);
			return page;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;

		}
	}


	public FileDetails getSlaInFile(SlaFilter filterRequest) {
		log.info("inside export sla data into file service");
		log.info("filter data:  "+filterRequest);
		log.info("filter data:  "+filterRequest);
		RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
		headerService.saveRequestHeader(header);
		userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
				filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.SLA_Management,SubFeatures.EXPORT,filterRequest.getFeatureId());

		String fileName = null;
		Writer writer   = null;
		SlaFile slFm = null;
		SystemConfigurationDb alertDbDowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("Sla_Download_Dir");
		SystemConfigurationDb alertDbDowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("Sla_Download_link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		User user=userRepoService.findByUSerId(filterRequest.getUserId());
		if(user!=null) {
			userService.saveUserTrail(user, "Alert db", "Export", filterRequest.getFeatureId());
		}
		String filePath  = alertDbDowlonadDir.getValue();
		log.info("filePath:  "+filePath);
		StatefulBeanToCsvBuilder<SlaFile> builder = null;
		StatefulBeanToCsv<SlaFile> csvWriter      = null;
		List<SlaFile> fileRecords       = null;
		//HeaderColumnNameTranslateMappingStrategy<UserProfileFileModel> mapStrategy = null;
		try {
			List<SlaReport> slaData = getAll(filterRequest);
			//if( alertDbData.getSize()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_slaReport.csv";
			
			  //}else { fileName = LocalDateTime.now().format(dtf).replace(" ","_")+"_AlertDb.csv"; 
			  //}
			 			log.info(" file path plus filke name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<SlaFile>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( slaData.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<SlaFile>(); 
				List<StakeholderFeature> featureData=featureService.featureAllData();
				for( SlaReport sla : slaData ) {
					slFm = new SlaFile();
					slFm.setCreatedOn(utility.converedtlocalTime(sla.getCreatedOn()));
					slFm.setModifiedOn(utility.converedtlocalTime(sla.getModifiedOn()));
					slFm.setState(sla.getStateInterp());
					slFm.setUsername(sla.getUsername());
					slFm.setTransactionId(sla.getTxnId());
					if(Objects.nonNull(featureData)) {
                    	for(StakeholderFeature feature:featureData) {
                    		if(feature.getId()==sla.getFeatureId())
                    			slFm.setFeature(feature.getName());	
                    	}
                     }
					slFm.setUsertype(sla.getUserSlaReport().getUsertype().getUsertypeName());
					System.out.println(slFm.toString());
					fileRecords.add(slFm);
				}
				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath,alertDbDowlonadLink.getValue()+fileName ); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( writer != null )
					writer.close();
			} catch (IOException e) {}
		}

	}
	

}
