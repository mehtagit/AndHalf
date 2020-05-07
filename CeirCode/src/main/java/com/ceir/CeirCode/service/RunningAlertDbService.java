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
import com.ceir.CeirCode.filemodel.RunningAlertFile;
import com.ceir.CeirCode.filtermodel.AlertDbFilter;
import com.ceir.CeirCode.filtermodel.RunningAlertFilter;
import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.RunningAlertDb;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.constants.AlertStatus;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.repo.RunningAlertDbRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.UserRepoService;
import com.ceir.CeirCode.util.Utility;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
@Service
public class RunningAlertDbService {


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
	RunningAlertDbRepo runningAertRepo;

	@Autowired
	ReqHeaderRepoService headerService;


	private GenericSpecificationBuilder<RunningAlertDb> buildSpecification(RunningAlertFilter filterRequest){

		GenericSpecificationBuilder<RunningAlertDb> rASB = new GenericSpecificationBuilder<RunningAlertDb>(propertiesReader.dialect);	

		if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
			rASB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
			rASB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));

		if(Objects.nonNull(filterRequest.getAlertId()) && filterRequest.getAlertId()!="")
			rASB.with(new SearchCriteria("alertId",filterRequest.getAlertId(), SearchOperation.EQUALITY, Datatype.STRING));

		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			rASB.orSearch(new SearchCriteria("description", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}
		if(Objects.nonNull(filterRequest.getStatus()) && filterRequest.getStatus()!=-1)
			rASB.with(new SearchCriteria("status",filterRequest.getStatus(), SearchOperation.EQUALITY, Datatype.INTEGER));

		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			rASB.orSearch(new SearchCriteria("description", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			rASB.orSearch(new SearchCriteria("alertId", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
			rASB.orSearch(new SearchCriteria("username", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}

		return rASB;
	}

	public List<RunningAlertDb> getAll(RunningAlertFilter filterRequest) {

		try {

			List<RunningAlertDb> runningAlertDb = runningAertRepo.findAll( buildSpecification(filterRequest).build());
			runningAlertDb.sort((a1,a2)->a2.getModifiedOn().compareTo(a1.getModifiedOn()));
			return runningAlertDb;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;
		}

	}


	public Page<RunningAlertDb>  viewRunningAlertData(RunningAlertFilter filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
//			RequestHeaders header=new RequestHeaders(filterRequest.getUserAgent(),filterRequest.getPublicIp(),filterRequest.getUsername());
//			headerService.saveRequestHeader(header);
			userService.saveUserTrail(filterRequest.getUserId(),filterRequest.getUsername(),
					filterRequest.getUserType(),filterRequest.getUserTypeId(),Features.Running_Alert_Management,SubFeatures.VIEW_ALL,filterRequest.getFeatureId());
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
			Page<RunningAlertDb> page=runningAertRepo.findAll(buildSpecification(filterRequest).build(),pageable);		
			return page;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;

		}
	}


	public FileDetails getRunningAlertInFile(RunningAlertFilter runAlertFilter) {
		log.info("inside export running alert db data into file service");
		log.info("filter data:  "+runAlertFilter);
//		RequestHeaders header=new RequestHeaders(runAlertFilter.getUserAgent(),runAlertFilter.getPublicIp(),runAlertFilter.getUsername());
//		headerService.saveRequestHeader(header);
		userService.saveUserTrail(runAlertFilter.getUserId(),runAlertFilter.getUsername(),
				runAlertFilter.getUserType(),runAlertFilter.getUserTypeId(),Features.Running_Alert_Management,SubFeatures.EXPORT,runAlertFilter.getFeatureId());

		String fileName = null;
		Writer writer   = null;
		RunningAlertFile adFm = null;
		SystemConfigurationDb alertDbDowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("RunningAlert_Download_Dir");
		SystemConfigurationDb alertDbDowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("RunningAlert_Download_link");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		User user=userRepoService.findByUSerId(runAlertFilter.getUserId());
		if(user!=null) {
			userService.saveUserTrail(user, "Running Alert db", "Export", runAlertFilter.getFeatureId());
		}
		String filePath  = alertDbDowlonadDir.getValue();
		log.info("filePath:  "+filePath);
		StatefulBeanToCsvBuilder<RunningAlertFile> builder = null;
		StatefulBeanToCsv<RunningAlertFile> csvWriter      = null;
		List<RunningAlertFile> fileRecords       = null;
		try {
			List<RunningAlertDb> alertDbData = getAll(runAlertFilter);
			fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_RunningAlert.csv";
			log.info(" file path plus filke name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<RunningAlertFile>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( alertDbData.size() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<RunningAlertFile>(); 
				List<SystemConfigListDb> statusList=systemConfigRepo.getByTag("ALERT_STATE");
				for(RunningAlertDb runningAlert : alertDbData ) {
					adFm = new RunningAlertFile();
					adFm.setCreatedOn(utility.converedtlocalTime(runningAlert.getCreatedOn()));
					adFm.setAlertId(runningAlert.getAlertId());
					if(runningAlert.getStatus()!=null) {
						for(SystemConfigListDb data:statusList) {
							Integer value=data.getValue();
							if(runningAlert.getStatus()==value) {
								adFm.setStatus(data.getInterp());
							}
						}
					}
					adFm.setDescription(runningAlert.getDescription());
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
