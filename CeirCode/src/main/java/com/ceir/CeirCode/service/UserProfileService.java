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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.ceir.CeirCode.Constants.Datatype;
import com.ceir.CeirCode.Constants.SearchOperation;
import com.ceir.CeirCode.SpecificationBuilder.SpecificationBuilder;
import com.ceir.CeirCode.SpecificationBuilder.UserProfileSpecificationBuilder;
import com.ceir.CeirCode.configuration.FileStorageProperties;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.exceptions.ResourceServicesException;
import com.ceir.CeirCode.filtermodel.SearchAssignee;
import com.ceir.CeirCode.model.FileDetails;
import com.ceir.CeirCode.model.FilterRequest;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.StateMgmtDb;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.UserProfileFileModel;
import com.ceir.CeirCode.model.constants.AssigneeType;
import com.ceir.CeirCode.model.constants.UserStatus;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repo.UserProfileRepo;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.UserRepoService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Service
public class UserProfileService {

	@Autowired
	FileStorageProperties fileStorageProperties;
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired  
	UserProfileRepo  userProfileRepo;

	@Autowired
	PropertiesReaders propertiesReader;

	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;

	@Autowired
	UserService userService;

	@Autowired
	UserRepoService userRepoService;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;


	public Page<UserProfile>  viewAllRecord(FilterRequest filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
			Integer currentStatus=null;
			User user=userRepoService.findByUSerId(filterRequest.getUserId());
			if(user!=null) {
				userService.saveUserTrail(user, "Registration Request", "View", filterRequest.getFeatureId());
			}
			if(filterRequest.getStatus()!=null && filterRequest.getStatus()!=-1){
				currentStatus=UserStatus.PENDING_ADMIN_APPROVAL.getCode();
			}
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));

			UserProfileSpecificationBuilder uPSB = new UserProfileSpecificationBuilder(propertiesReader.dialect);	

			if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
				uPSB.addSpecification(uPSB.joinWithUser(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE)));

			if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
				uPSB.addSpecification(uPSB.joinWithUser(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE)));

			if(Objects.nonNull(filterRequest.getAsType()) && filterRequest.getAsType()!=-1)
				uPSB.with(new SearchCriteria("type",filterRequest.getAsType(), SearchOperation.EQUALITY, Datatype.INTEGER));

			if(Objects.nonNull(filterRequest.getUserRoleTypeId()) && filterRequest.getUserRoleTypeId() !=0 && filterRequest.getUserRoleTypeId()!=-1)
				uPSB.addSpecification(uPSB.joinWithMultiple(new SearchCriteria("id",filterRequest.getUserRoleTypeId(), SearchOperation.EQUALITY, Datatype.LONG)));

			if(Objects.nonNull(currentStatus) && currentStatus!=-1) 
				uPSB.addSpecification(uPSB.joinWithUser(new SearchCriteria("currentStatus",filterRequest.getStatus(), SearchOperation.EQUALITY, Datatype.INTEGER)));
			else  
				uPSB.addSpecification(uPSB.joinWithUser(new SearchCriteria("currentStatus",UserStatus.PENDING_ADMIN_APPROVAL.getCode(), SearchOperation.EQUALITY, Datatype.INT)));				

			log.info("uPSB specification:  "+uPSB);

			List<StateMgmtDb> statusList = stateMgmtServiceImpl.getByFeatureIdAndUserTypeId(filterRequest.getFeatureId(), filterRequest.getUserTypeId());

			Page<UserProfile> page = userProfileRepo.findAll(uPSB.build(),pageable);

			for(UserProfile userProfile : page.getContent()) {

				for(StateMgmtDb stateMgmtDb : statusList) {
					if(userProfile.getUser().getCurrentStatus() == stateMgmtDb.getState()) {
						userProfile.getUser().setStateInterp(stateMgmtDb.getInterp());
						break;
					}
				}

				// setInterp(consignmentMgmt2);
			}

			return page;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());

			e.printStackTrace();
			return null;
		}
	}


	public Page<UserProfile> assigneeInfo(SearchAssignee searchAssignee, Integer pageNo, Integer pageSize){
		log.info("inside assignee controller");
		log.info("assignee information: "+searchAssignee);
		Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
		SpecificationBuilder<UserProfile> specification=new SpecificationBuilder<UserProfile>(propertiesReader.dialect) ;
		if(Objects.nonNull(searchAssignee.getField())) 
		{
			if(searchAssignee.getType()!=null && searchAssignee.getType()!=0) {
				if(searchAssignee.getField()!=null && !searchAssignee.getField().equals(""))
				{	
					if(searchAssignee.getType()==AssigneeType.NAME.getCode()) {
						specification.with(new SearchCriteria("displayName", searchAssignee.getField(), SearchOperation.LIKE,Datatype.STRING));

					}
					else if(searchAssignee.getType()==AssigneeType.CONTACT.getCode()) {
						specification.with(new SearchCriteria("phoneNo", searchAssignee.getField(),SearchOperation.EQUALITY, Datatype.STRING));

					}
					else if(searchAssignee.getType()==AssigneeType.EMAIL.getCode()) {
						specification.with(new SearchCriteria("email", searchAssignee.getField(),SearchOperation.EQUALITY, Datatype.STRING));
					}
					else {

					}
					/*
					 * int arr[]= {5,6};
					 * specification.addSpecification(specification.joinWithUser(new
					 * SearchCriteria("usertypeData",arr, SearchOperation.EQUALITY,
					 * Datatype.INTEGER)));
					 */		     // specification.addSpecification(specification.joinWithUser(new SearchCriteria("usertypeData",6, SearchOperation.EQUALITY, Datatype.INTEGER)));
				}
			}
		}
		return userProfileRepo.findAll(specification.build(),pageable);
		//return userProfileRepo.findAll(specification.build());

	}


	public FileDetails getFilterUSerPRofileInFile(FilterRequest profileFilter, Integer pageNo, Integer pageSize) {
		log.info("inside export user profile data into file service");
		String fileName = null;
		Writer writer   = null;
		UserProfileFileModel uPFm = null;
		SystemConfigurationDb userProfileDowlonadDir=systemConfigurationDbRepoImpl.getDataByTag("USER_PRO_EXP_DIR");
		SystemConfigurationDb userProfileDowlonadLink=systemConfigurationDbRepoImpl.getDataByTag("USER_PRO_EXP_DOWNLOAD_LINK");
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		User user=userRepoService.findByUSerId(profileFilter.getUserId());
		if(user!=null) {
			userService.saveUserTrail(user, "Registration Request", "Export", profileFilter.getFeatureId());
		}
		String filePath  = userProfileDowlonadDir.getValue();
		log.info("filePath:  "+filePath);
		StatefulBeanToCsvBuilder<UserProfileFileModel> builder = null;
		StatefulBeanToCsv<UserProfileFileModel> csvWriter      = null;
		List<UserProfileFileModel> fileRecords       = null;
		HeaderColumnNameTranslateMappingStrategy<UserProfileFileModel> mapStrategy = null;
		try {
			Page<UserProfile> userProfileData = this.viewAllRecord(profileFilter, pageNo, pageSize);

			List<SystemConfigListDb> asTypeList=systemConfigRepo.getByTag("AS_TYPE");

			for(UserProfile profile:userProfileData.getContent()) {
				for(SystemConfigListDb asType:asTypeList) {
					if(profile.getType()==asType.getValue()) {
						profile.setAsTypeName(asType.getInterp());
					}
				}
			}

			if( userProfileData.getSize()> 0 ) {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_UserProfile.csv";
			}else {
				fileName = LocalDateTime.now().format(dtf).replace(" ", "_")+"_UserProfile.csv";
			}
			log.info(" file path plus filke name: "+Paths.get(filePath+fileName));
			writer = Files.newBufferedWriter(Paths.get(filePath+fileName));
			builder = new StatefulBeanToCsvBuilder<UserProfileFileModel>(writer);
			csvWriter = builder.withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER).build();
			if( userProfileData.getSize() > 0 ) {
				//List<SystemConfigListDb> systemConfigListDbs = configurationManagementServiceImpl.getSystemConfigListByTag("GRIEVANCE_CATEGORY");
				fileRecords = new ArrayList<UserProfileFileModel>(); 
				for( UserProfile userProfile : userProfileData ) {
					uPFm = new UserProfileFileModel();
					uPFm.setRequestedOn(userProfile.getUser().getCreatedOn());
					uPFm.setUserId(userProfile.getUser().getUsername());
					uPFm.setAsType(userProfile.getAsTypeName());
					uPFm.setRoleType(userProfile.getUser().getUsertype().getUsertypeName());
					uPFm.setStatus(UserStatus.getUserStatusByCode(userProfile.getUser().getCurrentStatus()).getDescription());
					System.out.println(uPFm.toString());
					fileRecords.add(uPFm);
				}
				csvWriter.write(fileRecords);
			}
			return new FileDetails( fileName, filePath,userProfileDowlonadLink.getValue()+fileName ); 
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
