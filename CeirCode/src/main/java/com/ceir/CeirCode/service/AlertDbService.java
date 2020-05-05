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
import com.ceir.CeirCode.filemodel.AlertDbFile;
import com.ceir.CeirCode.filtermodel.AlertDbFilter;
import com.ceir.CeirCode.filtermodel.ReqHeaderFilter;
import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.AllRequest;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.SubFeature;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserProfileFileModel;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.repo.AlertDbRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repoService.AlertRepoService;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.UserRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.tags.AlertDbTags;
import com.ceir.CeirCode.response.tags.PortAddsTags;
import com.ceir.CeirCode.response.tags.RegistrationTags;
import com.ceir.CeirCode.util.HttpResponse;
import com.ceir.CeirCode.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class AlertDbService {

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
	AlertDbRepo alertDbRepo;
	
	@Autowired
	AlertRepoService alertRepoService;
	
	@Autowired
	ReqHeaderRepoService headerService;
	
	
	private GenericSpecificationBuilder<AlertDb> buildSpecification(AlertDbFilter filterRequest){
		if(filterRequest.getUserId()!=0) {
		User user=userRepoService.findByUSerId(filterRequest.getUserId());
		if(user!=null) {
			userService.saveUserTrail(user, "Alert db", "View", filterRequest.getFeatureId());
		}
		}
		GenericSpecificationBuilder<AlertDb> uPSB = new GenericSpecificationBuilder<AlertDb>(propertiesReader.dialect);	

		if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getAlertId()) && filterRequest.getAlertId()!="")
			uPSB.with(new SearchCriteria("alertId",filterRequest.getAlertId(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
		uPSB.orSearch(new SearchCriteria("description", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("alertId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("feature", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return uPSB;
	}
	
	public List<AlertDb> getAll(AlertDbFilter filterRequest) {

		try {
			List<AlertDb> systemConfigListDbs = alertDbRepo.findAll( buildSpecification(filterRequest).build());

			return systemConfigListDbs;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;
		}

	}
	
	
	public Page<AlertDb>  viewAllAlertData(AlertDbFilter filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
			RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
			headerService.saveRequestHeader(header);
			userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
					filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.Alert_Management,SubFeatures.VIEW_ALL,filterRequest.getFeatureId());
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
            Page<AlertDb> page=alertDbRepo.findAll(buildSpecification(filterRequest).build(),pageable);
			return page;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;

		}
	}


	public FileDetails getAlertDbInFile(AlertDbFilter alertAbFilter) {
		log.info("inside export alert db data into file service");
		log.info("filter data:  "+alertAbFilter);
		RequestHeaders header=new RequestHeaders(alertAbFilter.getUserAgent(),alertAbFilter.getPublicIp(),alertAbFilter.getUsername());
		headerService.saveRequestHeader(header);
		userService.saveUserTrail(alertAbFilter.getUserId(),alertAbFilter.getUsername(),
				alertAbFilter.getUserType(),alertAbFilter.getUserTypeId(),Features.Alert_Management,SubFeatures.EXPORT,alertAbFilter.getFeatureId());

		String fileName = null;
		Writer writer   = null;
		AlertDbFile adFm = null;
		SystemConfigurationDb alertDbDowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("Alertdb_Download_Dir");
		SystemConfigurationDb alertDbDowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("Alertdb_Download_link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		User user=userRepoService.findByUSerId(alertAbFilter.getUserId());
		if(user!=null) {
			userService.saveUserTrail(user, "Alert db", "Export", alertAbFilter.getFeatureId());
		}
		String filePath  = alertDbDowlonadDir.getValue();
		log.info("filePath:  "+filePath);
		StatefulBeanToCsvBuilder<AlertDbFile> builder = null;
		StatefulBeanToCsv<AlertDbFile> csvWriter      = null;
		List<AlertDbFile> fileRecords       = null;
		//HeaderColumnNameTranslateMappingStrategy<UserProfileFileModel> mapStrategy = null;
		try {
			List<AlertDb> alertDbData = getAll(alertAbFilter);
			//if( alertDbData.getSize()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_AlertDb.csv";
			
			  //}else { fileName = LocalDateTime.now().format(dtf).replace(" ","_")+"_AlertDb.csv"; 
			  //}
			 			log.info(" file path plus filke name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<AlertDbFile>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( alertDbData.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<AlertDbFile>(); 
				for( AlertDb alertDb : alertDbData ) {
					adFm = new AlertDbFile();
					adFm.setCreatedOn(utility.converedtlocalTime(alertDb.getCreatedOn()));
					adFm.setModifiedOn(utility.converedtlocalTime(alertDb.getModifiedOn()));
					adFm.setAlertId(alertDb.getAlertId());;
					adFm.setFeature(alertDb.getFeature());
					adFm.setDescription(alertDb.getDescription());
					System.out.println(adFm.toString());
					fileRecords.add(adFm);
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
	
	public ResponseEntity<?> getAlertData(){
		try {
			List<AlertDb> alertDb=alertDbRepo.findAll();
			alertDb.sort((f1,f2)->f1.getAlertId().compareTo(f2.getAlertId()));
			return new ResponseEntity<>(alertDb, HttpStatus.OK);
		}
		catch(Exception e){
			log.info("exception occurs");
			log.info(e.toString());
			HttpResponse response=new HttpResponse();
			response.setResponse("Oop something wrong happened");
			response.setStatusCode(409);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> findById(AllRequest request){
		try {
			log.info("given data:  "+request);
			RequestHeaders header=new RequestHeaders(request.getUserAgent(),request.getPublicIp(),request.getUsername());
			headerService.saveRequestHeader(header);
			userService.saveUserTrail(request.getUserId(),request.getUsername(),
					request.getUserType(),request.getUserTypeId(),Features.Alert_Management,SubFeatures.VIEW,request.getFeatureId());

			AlertDb alertDb=alertDbRepo.findById(request.getDataId());
			if(alertDb!=null) {
				GenricResponse response=new GenricResponse(200,"","",alertDb);		
				return new ResponseEntity<>(response, HttpStatus.OK);
				
			}
			else {
				GenricResponse response=new GenricResponse(500,AlertDbTags.Alert_Data_By_Id_Fail.getMessage(),AlertDbTags.Alert_Data_By_Id_Fail.getTag());
				return  new ResponseEntity<>(response,HttpStatus.OK);

			}

		}
		catch(Exception e){
			log.info("exception occurs");
			log.info(e.toString());
			GenricResponse response=new GenricResponse(409,RegistrationTags.COMMAN_FAIL_MSG.getTag(),RegistrationTags.COMMAN_FAIL_MSG.getMessage(),"");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> updateAlertDb(AlertDb alertDb){
		log.info("inside update alertDb controller");
		log.info("given data:  "+alertDb);
		RequestHeaders header=new RequestHeaders(alertDb.getUserAgent(),alertDb.getPublicIp(),alertDb.getUsername());
		headerService.saveRequestHeader(header);
		userService.saveUserTrail(alertDb.getId(),alertDb.getUsername(),
				alertDb.getUserType(),alertDb.getUserTypeId(),Features.Alert_Management,SubFeatures.VIEW,alertDb.getFeatureId());
		AlertDb data=alertRepoService.getById(alertDb.getId());
		if(data!=null) {
		data.setDescription(alertDb.getDescription());
		AlertDb output=alertRepoService.save(data);
		
		if(output!=null) {
			GenricResponse response=new GenricResponse(200,AlertDbTags.Alert_Update_Sucess.getMessage(),AlertDbTags.Alert_Update_Sucess.getTag(),"");
			log.info("exit from  update AlertDb  controller");
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			GenricResponse response=new GenricResponse(500,AlertDbTags.Alert_Update_Fail.getMessage(),AlertDbTags.Alert_Update_Fail.getTag(),"");
			return  new ResponseEntity<>(response,HttpStatus.OK);
		}
		}
		else {
			GenricResponse response=new GenricResponse(500,AlertDbTags.Alert_Wrong_ID.getMessage(),AlertDbTags.Alert_Wrong_ID.getTag(),"");
			return  new ResponseEntity<>(response,HttpStatus.OK);		
		}

	}
	
}
