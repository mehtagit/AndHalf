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
import com.ceir.CeirCode.filemodel.UserTypeFile;
import com.ceir.CeirCode.filtermodel.UsertypeFilter;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.model.constants.UserTypeStatusFlag;
import com.ceir.CeirCode.othermodel.ChangeUsertypeStatus;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repo.SystemConfigDbRepository;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.SystemConfigurationDbRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.tags.RegistrationTags;
import com.ceir.CeirCode.response.tags.UsertypeTags;
import com.ceir.CeirCode.util.CustomMappingStrategy;
import com.ceir.CeirCode.util.HttpResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class UserTypeService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PropertiesReaders propertiesReader;
	
	@Autowired
	ReqHeaderRepoService headerService;

	@Autowired
	UserService userService;

	
	@Autowired
	UsertypeRepo usertypeRepo;
	
	@Autowired
	SystemConfigurationDbRepoService systemConfigDbRepoService;
	
	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;
	
	@Autowired
	SystemConfigDbRepository systemConfigDbRepository;
	public Page<Usertype>  viewAllUserytypes(UsertypeFilter filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
//			RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
//			headerService.saveRequestHeader(header);
			userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
					filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.User_Type_Management,SubFeatures.VIEW_ALL,filterRequest.getFeatureId());
			
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "id"));
			GenericSpecificationBuilder<Usertype> uPSB = new GenericSpecificationBuilder<Usertype>(propertiesReader.dialect);	
			if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
				uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
				uPSB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

			if(Objects.nonNull(filterRequest.getStatus()) && filterRequest.getStatus()!=-1)
				uPSB.with(new SearchCriteria("status",filterRequest.getStatus(), SearchOperation.EQUALITY, Datatype.INTEGER));

			if(Objects.nonNull(filterRequest.getId()) && filterRequest.getId()!=-1)
				uPSB.with(new SearchCriteria("id",filterRequest.getId(), SearchOperation.EQUALITY, Datatype.INTEGER));

			if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			uPSB.orSearch(new SearchCriteria("usertypeName", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			}
			return usertypeRepo.findAll(uPSB.build(),pageable);

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;

		}
	}
	
	public ResponseEntity<?> changeUserTypeStatus(ChangeUsertypeStatus usertypeStatus){
		log.info("inside  change Usertype status  controller");  
		log.info(" usetypeStatus data:  "+usertypeStatus);      
		log.info("get usertype  data by usertype id below"); 
		Usertype userType=new Usertype();
		try {
//			RequestHeaders header=new RequestHeaders(usertypeStatus.getUserAgent(),usertypeStatus.getPublicIp(),usertypeStatus.getUsername());
//			headerService.saveRequestHeader(header);
			userService.saveUserTrail(usertypeStatus.getUserId(),usertypeStatus.getUsername(),
					usertypeStatus.getUserType(),usertypeStatus.getUserTypeId(),Features.User_Type_Management,SubFeatures.UPDATE,usertypeStatus.getFeatureId());
			 userType=usertypeRepo.findById(usertypeStatus.getUsertypeId());			
		}
		catch(Exception e) {
			log.info(e.getMessage());
			log.info(e.toString());
		}

		if(userType!=null) {
			userType.setStatus(usertypeStatus.getStatus());
			Usertype output=usertypeRepo.save(userType); 
			log.info("usertype data after update the status: "+output);
			if(output!=null) {
				HttpResponse response=new HttpResponse(UsertypeTags.UTStatus_Update_Success.getMessage(),
						200,UsertypeTags.UTStatus_Update_Success.getTag());
				log.info("response send to usertype:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse(UsertypeTags.UTStatus_Update_Fail.getMessage(),
						500,UsertypeTags.UTStatus_Update_Fail.getTag());
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			} 
		}    
		else { 
			HttpResponse response=new HttpResponse(UsertypeTags.Wrong_usertypeId.getMessage(),
					409,UsertypeTags.Wrong_usertypeId.getTag());
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}
	
	
public ResponseEntity<?> checkUsertypeStatus(long usertypeId){
		
		try {
			log.info("inside check usertype status");
			log.info("usertypeId value "+usertypeId);
			Usertype usertype=usertypeRepo.findById(usertypeId);  
			if(usertype.getStatus()==UserTypeStatusFlag.on.getCode()) {
				log.info("usertype is enable");
				GenricResponse response=new GenricResponse(200,UsertypeTags.UTStatus_Enable.getMessage(),UsertypeTags.UTStatus_Enable.getTag());
				return new ResponseEntity<>(response,HttpStatus.OK);			
			}
			
			else {
				log.info("usertype status is disable now ");
				GenricResponse response=new GenricResponse(409,UsertypeTags.UTStatus_Disable.getMessage(),UsertypeTags.UTStatus_Disable.getTag());
				return new ResponseEntity<>(response,HttpStatus.OK);			
			}
			
		}
		catch(Exception e) {
			log.info("something wrong happened here now");
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),500,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}

public FileDetails getFile(UsertypeFilter filter) {
	log.info("inside userType service");
	log.info("filter data:  "+filter);
	String fileName = null;
	Writer writer   = null;
	UserTypeFile uPFm = null;
	SystemConfigurationDb dowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("file.download-dir");
	SystemConfigurationDb dowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("file.download-link");
	DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	Integer pageNo = 0;
	Integer pageSize = Integer.valueOf(systemConfigDbRepository.findByTag("file.max-file-record").getValue());
	String filePath  = dowlonadDir.getValue();
	StatefulBeanToCsvBuilder<UserTypeFile> builder = null;
	StatefulBeanToCsv<UserTypeFile> csvWriter      = null;
	List<UserTypeFile> fileRecords       = null;
	MappingStrategy<UserTypeFile> mapStrategy = new CustomMappingStrategy<>();
	
	
	try {
		mapStrategy.setType(UserTypeFile.class);
		List<SystemConfigListDb> statusList=systemConfigRepo.getByTag("UserType_Status");
		List<Usertype> list = viewAllUserytypes(filter, pageNo, pageSize).getContent();
		for(Usertype usertype:list) {
		for(SystemConfigListDb status:statusList) {
		Integer value=status.getValue();
		if(usertype.getStatus()==value) {
		usertype.setStatusInterp(status.getInterp());
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
//		builder = new StatefulBeanToCsvBuilder<UserProfileFileModel>(writer);
//		csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
//		
		builder = new StatefulBeanToCsvBuilder<>(writer);
		csvWriter = builder.withMappingStrategy(mapStrategy).withSeparator(',').withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

		if( list.size() > 0 ) {
			//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
			fileRecords = new ArrayList<UserTypeFile>(); 
			for( Usertype userType : list ) {
				uPFm = new UserTypeFile();
				uPFm.setCreatedOn(userType.getCreatedOn().format(dtf));	
			    uPFm.setModifiedOn(userType.getModifiedOn().format(dtf));
				uPFm.setUsertypeName(userType.getUsertypeName());
				uPFm.setStatusInterp(userType.getStatusInterp());
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

}
}
