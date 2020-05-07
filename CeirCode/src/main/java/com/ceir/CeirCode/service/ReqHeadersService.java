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
import com.ceir.CeirCode.filemodel.ReqHeaderFile;
import com.ceir.CeirCode.filtermodel.AlertDbFilter;
import com.ceir.CeirCode.filtermodel.ReqHeaderFilter;
import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.repo.ReqHeadersRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.UserRepoService;
import com.ceir.CeirCode.util.HttpResponse;
import com.ceir.CeirCode.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class ReqHeadersService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ReqHeaderRepoService headerService;
	
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
	ReqHeadersRepo reqHeaderRepo;
	
	@Autowired
	Utility utility;
	
	public ResponseEntity<?> saveRequestHeaders(RequestHeaders requestHeaders){
      log.info("inside request headers controller");
	  log.info("Request headers data given: "+requestHeaders);
      RequestHeaders output=headerService.saveRequestHeader(requestHeaders);
      if(output!=null) {
             HttpResponse response=new HttpResponse("Request Headers data sucessfully save",200);
              return  new ResponseEntity<>(response,HttpStatus.OK);
      }
      else {
          HttpResponse response=new HttpResponse("Request Headers data fails to save",409);
          return  new ResponseEntity<>(response,HttpStatus.OK);
      }
	}
	
	
	private GenericSpecificationBuilder<RequestHeaders> buildSpecification(ReqHeaderFilter filterRequest){

		GenericSpecificationBuilder<RequestHeaders> uPSB = new GenericSpecificationBuilder<RequestHeaders>(propertiesReader.dialect);	

		if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));


		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
		uPSB.orSearch(new SearchCriteria("userAgent", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}
		
		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
		uPSB.orSearch(new SearchCriteria("publicIp", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("userAgent", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		uPSB.orSearch(new SearchCriteria("username", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return uPSB;
	}
	
	public List<RequestHeaders> getAll(ReqHeaderFilter filterRequest) {

		try {
			List<RequestHeaders> systemConfigListDbs = reqHeaderRepo.findAll( buildSpecification(filterRequest).build());

			return systemConfigListDbs;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;
		}

	}
	
	public Page<RequestHeaders>  viewAllHeadersData(ReqHeaderFilter filterRequest, Integer pageNo, Integer pageSize){
		try { 
            log.info("filter data: "+filterRequest);
//			RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
//			headerService.saveRequestHeader(header);
			userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
					filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.IP_Log_Management,SubFeatures.VIEW_ALL,filterRequest.getFeatureId());

			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
			Page<RequestHeaders> page = reqHeaderRepo.findAll( buildSpecification(filterRequest).build(), pageable );
			return page;
		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;
		}
	}


	public FileDetails getHeadersInFile(ReqHeaderFilter filterRequest, Integer pageNo, Integer pageSize) {
		log.info("inside export request headers data into file service");
		log.info("filter data:  "+filterRequest);
//		RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
//		headerService.saveRequestHeader(header);
		userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
				filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.IP_Log_Management,SubFeatures.EXPORT,filterRequest.getFeatureId());

		String fileName = null;
		Writer writer   = null;
		ReqHeaderFile adFm = null;
		SystemConfigurationDb alertDbDowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("reqHeader_Download_Dir");
		SystemConfigurationDb alertDbDowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("reqHeader_Download_link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		User user=userRepoService.findByUSerId(filterRequest.getUserId());
		if(user!=null) {
			userService.saveUserTrail(user, "Alert db", "Export", filterRequest.getFeatureId());
		}
		String filePath  = alertDbDowlonadDir.getValue();
		log.info("filePath:  "+filePath);
		StatefulBeanToCsvBuilder<ReqHeaderFile> builder = null;
		StatefulBeanToCsv<ReqHeaderFile> csvWriter      = null;
		List<ReqHeaderFile> fileRecords       = null;
		//HeaderColumnNameTranslateMappingStrategy<UserProfileFileModel> mapStrategy = null;
		try {
			List<RequestHeaders> alertDbData = this.getAll(filterRequest);
			//if( alertDbData.getSize()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_IPLog.csv";
			
			  //}else { fileName = LocalDateTime.now().format(dtf).replace(" ","_")+"_AlertDb.csv"; 
			  //}
			 			log.info(" file path plus filke name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<ReqHeaderFile>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( alertDbData.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<ReqHeaderFile>(); 
				for( RequestHeaders req : alertDbData ) {
					adFm = new ReqHeaderFile();
					adFm.setCreatedOn(utility.converedtlocalTime(req.getCreatedOn()));
					adFm.setPublicIp(req.getPublicIp());
					adFm.setUserAgent(req.getUserAgent());
					adFm.setUsername(req.getUsername());
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
	
	
	
}
