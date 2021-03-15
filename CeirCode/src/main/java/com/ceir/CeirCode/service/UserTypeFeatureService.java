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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.Constants.Datatype;
import com.ceir.CeirCode.Constants.SearchOperation;
import com.ceir.CeirCode.SpecificationBuilder.GenericSpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.exceptions.ResourceServicesException;
import com.ceir.CeirCode.filemodel.ExchangeRateFile;
import com.ceir.CeirCode.filemodel.UserFeatureFile;
import com.ceir.CeirCode.filtermodel.CurrencyFilter;
import com.ceir.CeirCode.filtermodel.UserTypeFeatureFilter;
import com.ceir.CeirCode.filtermodel.UsertypeFilter;
import com.ceir.CeirCode.model.AllRequest;
import com.ceir.CeirCode.model.Currency;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.UserToStakehoderfeatureMapping;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.othermodel.ChangePeriod;
import com.ceir.CeirCode.othermodel.ChangeUsertypeStatus;
import com.ceir.CeirCode.repo.FeatureRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repo.SystemConfigDbRepository;
import com.ceir.CeirCode.repo.UserToStakehoderfeatureMappingRepo;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.SystemConfigurationDbRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.tags.CurrencyTags;
import com.ceir.CeirCode.response.tags.UserTypeFeatureTags;
import com.ceir.CeirCode.response.tags.UsertypeTags;
import com.ceir.CeirCode.util.CustomMappingStrategy;
import com.ceir.CeirCode.util.HttpResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class UserTypeFeatureService {


	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PropertiesReaders propertiesReader;
	
	@Autowired
	UsertypeRepo userTypeRepo;
	
	@Autowired
	UserToStakehoderfeatureMappingRepo userTypeFeatureRepo;
	
	@Autowired
	ReqHeaderRepoService headerService;

	@Autowired
	UserService userService;
	
	@Autowired
	SystemConfigurationDbRepoService systemConfigDbRepoService;
	
	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;
	
	@Autowired
	SystemConfigDbRepository systemConfigDbRepository;
	
	public Page<UserToStakehoderfeatureMapping>  viewAllUserTypeFeatures(UserTypeFeatureFilter filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
//			RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
//			headerService.saveRequestHeader(header);
			userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
					filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.User_feature_mapping,SubFeatures.VIEW_ALL,filterRequest.getFeatureId());

			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
			GenericSpecificationBuilder<UserToStakehoderfeatureMapping> uPSB = new GenericSpecificationBuilder<UserToStakehoderfeatureMapping>(propertiesReader.dialect);	
			if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
				uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
				uPSB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getFeature()) && filterRequest.getFeature()!=-1)
				uPSB.with(new SearchCriteria("stakeholderFeature",filterRequest.getFeature(), SearchOperation.EQUALITY, Datatype.INTEGER));
			
			if(Objects.nonNull(filterRequest.getUsertypeId()) && filterRequest.getUsertypeId()!=-1)
				uPSB.with(new SearchCriteria("userTypeFeature",filterRequest.getUsertypeId(), SearchOperation.EQUALITY, Datatype.INTEGER));

			if(Objects.nonNull(filterRequest.getPeriod()) && filterRequest.getPeriod()!=-1)
				uPSB.with(new SearchCriteria("period",filterRequest.getPeriod(), SearchOperation.EQUALITY, Datatype.INTEGER));

			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
				uPSB.orSearch(new SearchCriteria("stakeholderFeature-name", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			    uPSB.orSearchUsertypeMapToFeature(new SearchCriteria("usertypeName", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
//			    uPSB.orSearchFeature(new SearchCriteria("name", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
				}
			
			return  userTypeFeatureRepo.findAll(uPSB.build(),pageable);

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;

		}
	}
	 
	public ResponseEntity<?> changePeriod(ChangePeriod userPeriod){
		log.info("inside  change userPeriod   controller");  
		log.info(" userPeriod data:  "+userPeriod);      
		log.info("get userTypeFeature  data by  id below"); 
		UserToStakehoderfeatureMapping featureMapping=new UserToStakehoderfeatureMapping();
//		RequestHeaders header=new RequestHeaders(userPeriod.getUserAgent(),userPeriod.getPublicIp(),userPeriod.getUsername());
//		headerService.saveRequestHeader(header);
		userService.saveUserTrail(userPeriod.getUserId(),userPeriod.getUsername(),
				userPeriod.getUserType(),userPeriod.getUserTypeId(),Features.User_feature_mapping,SubFeatures.UPDATE,userPeriod.getFeatureId());

		try {
			featureMapping=userTypeFeatureRepo.findById(userPeriod.getDataId());		
		}
		catch(Exception e) {
			log.info(e.getMessage());
			log.info(e.toString());
		}

		if(featureMapping!=null) {
			featureMapping.setPeriod(userPeriod.getPeriod());
			featureMapping.setModifiedBy(userPeriod.getUsername());
			UserToStakehoderfeatureMapping output=userTypeFeatureRepo.save(featureMapping); 
			if(output!=null) {
				HttpResponse response=new HttpResponse(UserTypeFeatureTags.UTFPeriod_Update_Success.getMessage(),
						200,UserTypeFeatureTags.UTFPeriod_Update_Success.getTag());
				log.info("response send:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse(UserTypeFeatureTags.UTFPeriod_Update_Fail.getMessage(),
						500,UserTypeFeatureTags.UTFPeriod_Update_Fail.getTag());
				log.info("response send"+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			} 
		}    
		else { 
			HttpResponse response=new HttpResponse(UserTypeFeatureTags.Wrong_userTypeFeatureId.getMessage(),
					409,UserTypeFeatureTags.Wrong_userTypeFeatureId.getTag());
			log.info("response send"+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}
	
	public FileDetails getFile(UserTypeFeatureFilter filter) {
		log.info("inside export user Type Feature service");
		log.info("filter data:  "+filter);
		String fileName = null;
		Writer writer   = null;
		UserFeatureFile uPFm = null;
		SystemConfigurationDb dowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("file.download-dir");
		SystemConfigurationDb dowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("file.download-link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Integer pageNo = 0;
		Integer pageSize = Integer.valueOf(systemConfigDbRepository.findByTag("file.max-file-record").getValue());
		String filePath  = dowlonadDir.getValue();
		StatefulBeanToCsvBuilder<UserFeatureFile> builder = null;
		StatefulBeanToCsv<UserFeatureFile> csvWriter      = null;
		List<UserFeatureFile> fileRecords       = null;
		MappingStrategy<UserFeatureFile> mapStrategy = new CustomMappingStrategy<>();
		
		
		try {
			mapStrategy.setType(UserFeatureFile.class);
			List<SystemConfigListDb> periodList=systemConfigRepo.getByTag("Period");
			List<UserToStakehoderfeatureMapping> list = viewAllUserTypeFeatures(filter, pageNo, pageSize).getContent();
			for(UserToStakehoderfeatureMapping feature:list) {
			feature.setUsertypeInterp(feature.getUserTypeFeature().getUsertypeName());
			feature.setFeatureInterp(feature.getStakeholderFeature().getName());
			for(SystemConfigListDb data:periodList) {
			Integer value=data.getValue();
			if(feature.getPeriod()==value) {
			feature.setPeriodInterp(data.getInterp());
			}
			}
			}
		

			if( list.size()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_CustomPort.csv";
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_CustomPort.csv";
			}
			log.info(" file path plus file name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
//			builder = new StatefulBeanToCsvBuilder<UserProfileFileModel>(writer);
//			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
//			
			builder = new StatefulBeanToCsvBuilder<>(writer);
			csvWriter = builder.withMappingStrategy(mapStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			if( list.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<UserFeatureFile>(); 
				for( UserToStakehoderfeatureMapping userfeature : list ) {
					uPFm = new UserFeatureFile();
					uPFm.setCreatedOn(userfeature.getCreatedOn().format(dtf));
					uPFm.setModifiedOn(userfeature.getModifiedOn().format(dtf));
					uPFm.setUsertypeInterp(userfeature.getUsertypeInterp());
					uPFm.setFeatureInterp(userfeature.getFeatureInterp());
					uPFm.setPeriodInterp(userfeature.getPeriodInterp());
					fileRecords.add(uPFm);
				}
				csvWriter.write(fileRecords);
			}
			log.info("fileName::"+fileName);
			log.info("filePath::::"+filePath);
			log.info("link:::"+dowlonadLink.getValue());
			return new FileDetails(fileName, filePath,dowlonadLink.getValue().replace("$LOCAL_IP",propertiesReader.localIp)+fileName ); 
		
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ResourceServicesException(this.getClass().getName(), e.getMessage());
		}finally {
			try {

				if( writer != null )
					writer.close();
			} catch (IOException e) {}
		}

	}public ResponseEntity<?> viewById(AllRequest request){
		log.info("inside view by Id userTypeFeature controller");
		log.info("data given : "+request);
		UserToStakehoderfeatureMapping output=userTypeFeatureRepo.findById(request.getDataId());
//		RequestHeaders header=new RequestHeaders(request.getUserAgent(),request.getPublicIp(),request.getUsername());
//		headerService.saveRequestHeader(header);
		userService.saveUserTrail(request.getUserId(),request.getUsername(),
				request.getUserType(),request.getUserTypeId(),Features.User_feature_mapping,SubFeatures.VIEW,request.getFeatureId());
		if(output!=null) {
			log.info("Modified by Name of system Admin when viewing  "+output.getModifiedBy());
			GenricResponse response=new GenricResponse(200,"","",output);
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,CurrencyTags.Curr_Data_By_Id_Fail.getTag(),CurrencyTags.Curr_Data_By_Id_Fail.getMessage(),"");
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}

	}
}
