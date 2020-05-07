package com.ceir.CeirCode.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

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
import com.ceir.CeirCode.filtermodel.UserMgmtFilter;
import com.ceir.CeirCode.model.FilterRequest;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.RunningAlertDb;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.StateMgmtDb;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserDetails;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.Userrole;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.model.constants.AlertStatus;
import com.ceir.CeirCode.model.constants.UserStatus;
import com.ceir.CeirCode.model.constants.UsertypeData;
import com.ceir.CeirCode.repo.UserProfileRepo;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.repoService.RunningAlertRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.UserRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.tags.PortAddsTags;
import com.ceir.CeirCode.response.tags.RegistrationTags;
import com.ceir.CeirCode.util.HttpResponse;
@Service
public class UserMgmtService {


	@Autowired
	UserRepo userRepo;

	@Autowired
	PropertiesReaders propertiesReader;

	@Autowired
	UserRepoService userRepoService;

	@Autowired
	UsertypeRepo usertypeRepo;
	
	@Autowired
	UserProfileRepo  userProfileRepo; 
	
	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;
	
	@Autowired
	RunningAlertRepoService alertDbRepo;

	
	private final Logger log = LoggerFactory.getLogger(getClass());

	
	private GenericSpecificationBuilder<User> buildSpecification(UserMgmtFilter filterRequest){

		GenericSpecificationBuilder<User> uPSB = new GenericSpecificationBuilder<User>(propertiesReader.dialect);	
		//		User user=userRepoService.findByUSerId(filterRequest.getUserId());
		//		if(user!=null) {
		//			userService.saveUserTrail(user, "Registration Request", "View", filterRequest.getFeatureId());
		//		}

		if(Objects.nonNull(filterRequest.getStartDate()) && filterRequest.getStartDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getStartDate(), SearchOperation.GREATER_THAN, Datatype.DATE));
		if(Objects.nonNull(filterRequest.getEndDate()) && filterRequest.getEndDate()!="")
			uPSB.with(new SearchCriteria("createdOn",filterRequest.getEndDate(), SearchOperation.LESS_THAN, Datatype.DATE));
		if(Objects.nonNull(filterRequest.getUsertypeId()) && filterRequest.getUsertypeId()!=-1)
			uPSB.with(new SearchCriteria("usertype",filterRequest.getUsertypeId(), SearchOperation.EQUALITY, Datatype.INT));
		if(Objects.nonNull(filterRequest.getSearchString()) && !filterRequest.getSearchString().isEmpty()){
			uPSB.orSearchUsertype(new SearchCriteria("username", filterRequest.getSearchString(), SearchOperation.LIKE, Datatype.STRING));
		}
		uPSB.addSpecification(uPSB.joinWithUserType(new SearchCriteria("selfRegister",0, SearchOperation.EQUALITY, Datatype.INT)));
		uPSB.with(new SearchCriteria("currentStatus",3, SearchOperation.EQUALITY, Datatype.INT));

		return uPSB;
	}

	public List<User> getAll(UserMgmtFilter filterRequest) {

		try {
			List<User> systemConfigListDbs = userRepo.findAll( buildSpecification(filterRequest).build());
			return systemConfigListDbs;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.getClass().getMethods().toString());
			log.info(e.toString());
			return null;
		}
	}

	public Page<User>  viewAllRecord(UserMgmtFilter filterRequest, Integer pageNo, Integer pageSize){
		try { 
			log.info("filter data:  "+filterRequest);
			Pageable pageable = PageRequest.of(pageNo, pageSize, new Sort(Sort.Direction.DESC, "modifiedOn"));
			Page<User> page = userRepo.findAll( buildSpecification(filterRequest).build(), pageable );
			return page;

		} catch (Exception e) {
			log.info("Exception found ="+e.getMessage());
			log.info(e.toString());
			return null;

		}
	}

	@Transactional
	public GenricResponse saveUser(UserDetails details)
	{
		Usertype userType=new Usertype(details.getUserTypeId());
		String displayName="";
	    if(Objects.nonNull(details.getMiddleName())) {
	    	 displayName=details.getFirstName()+" "+details.getMiddleName()+" "+details.getLastName();
	    	    	
	    }
	    else {
	    	
	    	 displayName=details.getFirstName()+" "+details.getLastName();

	    }
		UserProfile profile=new UserProfile(details.getFirstName(),details.getMiddleName(),details.getLastName(),
				details.getEmail(),details.getPhoneNo(),displayName);
		List<Userrole> role=new ArrayList<Userrole>();
		User user=new User(details.getUsername(),details.getPassword(),3,details.getRemarks(),profile,userType,role);
		user.setUserLanguage("en");
		profile.setUser(user);
		Userrole userRole=new Userrole(user,userType);
		role.add(userRole);
		User output=new User();
		boolean emailExist=userProfileRepo.existsByEmail(details.getEmail());
		if(emailExist) {
		
			GenricResponse response=new GenricResponse(409,RegistrationTags.Email_Exist.getTag(),RegistrationTags.Email_Exist.getMessage(),"");
			return response;
		}

		boolean phoneExist=userProfileRepo.existsByPhoneNo(details.getPhoneNo());
		if(phoneExist) {
			
			GenricResponse response=new GenricResponse(409,RegistrationTags.Phone_Exist.getTag(),RegistrationTags.Phone_Exist.getMessage(),"");
			return response;
		}
		if(Objects.isNull(details.getUserTypeId()) || details.getUserTypeId()==0) {
			GenricResponse response=new GenricResponse(409,"Usertype Id value must not be null or 0","");
			return  response;
			
		}
		HashMap<Integer, String> map=new HashMap<Integer,String>();
		map.put(UsertypeData.CEIRAdmin.getCode(), "CEIRAdmin_User_Limit");
		map.put(UsertypeData.SystemAdmin.getCode(), "SystemAdmin_User_Limit");
		map.put(UsertypeData.Anonymous.getCode(), "Anonymous_User_Limit");
		map.put(UsertypeData.Operation.getCode(), "Operation_User_Limit");
		map.put(UsertypeData.End_User.getCode(), "customer_user_limit");
		map.put(UsertypeData.Customer_Care.getCode(), "end_user_limit");
		int usertypeId=(int) details.getUserTypeId();
		log.info("then going to fetch data from system configuration db by tag "+map.get(usertypeId));
		SystemConfigurationDb systemConfigData=systemConfigurationDbRepoImpl.getDataByTag(map.get(usertypeId));
		long userLimit=0;
		if(systemConfigData!=null) {
			try {
				log.info("value got by tag= "+systemConfigData.getValue());
				userLimit=Long.parseLong(systemConfigData.getValue());				
			}
			catch(Exception e) {
				
				RunningAlertDb alertDb=new RunningAlertDb("alert001"," user creation limit is exceeded for "+UsertypeData.getByCode(usertypeId).getDescription() +" usertype",AlertStatus.Init.getCode());
				alertDbRepo.saveAlertDb(alertDb);
				log.info(e.toString());
			}

		}
        
		long count=userRepoService.countByUsertypeId(details.getUserTypeId());
		log.info("total users find by this usertype= "+count);
		log.info("now going to compare these two above values");
		if(count>=userLimit) {
            log.info("if usertype count greater than total users limit then we don't able to create new user now");
			GenricResponse response=new GenricResponse(409,"","User creation limit for the usertype is now exceeded","");
			return  response;
		}

		try {
                output=userRepo.save(user);
		}
		catch(Exception e) {
			log.info("Exception occurs");
			log.info(e.toString());
			GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Save_Fail.getMessage(),PortAddsTags.PAdd_Save_Fail.getTag());
			return  response;
           
		}
		if(output!=null) {
			GenricResponse response=new GenricResponse(200,"User Successfuly saved","");
			return  response;
		}
		else {
			GenricResponse response=new GenricResponse(500,"User fail to save","");
			return  response;
		}
		
	}	
		public GenricResponse updateUser(UserDetails details)
		{
			Usertype userType=new Usertype(details.getUserTypeId());
			List<Userrole> role=new ArrayList<Userrole>();
			User userData=new User();
			try {
				userData=userRepo.findById(details.getId());
				if(Objects.isNull(userData)) {
					GenricResponse response=new GenricResponse(500,"User is is wrong","");
					return  response;
				}
		}
		catch(Exception e) {
			log.info("Exception occurs");
			log.info(e.toString());
			GenricResponse response=new GenricResponse(500,"Oops something wrong happened","");
			return  response;
           
		}
			Userrole userRole=new Userrole(userData,userType);
			for(Userrole roles:userData.getUserRole()) {
				userRole.setId(roles.getId());				
			}
			userData.setUsertype(userType);
			userData.setUserRole(role);
			role.add(userRole);
			User output=new User();
			try {
	                output=userRepo.save(userData);
			}
			catch(Exception e) {
				log.info("Exception occure");
				log.info(e.toString());
				GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Save_Fail.getMessage(),PortAddsTags.PAdd_Save_Fail.getTag());
				return  response;
	           
			}
			if(output!=null) {
				GenricResponse response=new GenricResponse(200,"User Successfuly update","");
				return  response;
			}
			else {
				GenricResponse response=new GenricResponse(500,"User fail to update","");
				return  response;
			}


	}
	
		@Transactional
		public GenricResponse deleteById(long id)
		{
			try {
	                userRepo.deleteById(id);
					GenricResponse response=new GenricResponse(200,"User Successfuly delete","");
					return response;
			}
			catch(Exception e) {
				log.info("Exception occure");
				log.info(e.toString());
				GenricResponse response=new GenricResponse(500,"User fail to delete","");
				return  response;

	           
			}
			
	}
		
		public GenricResponse viewById(long id){
			log.info("inside view by address port controller");
			User output=new User();
			try {
				 output=userRepo.findById(id);
		}
		catch(Exception e) {
			log.info("Exception occure");
			log.info(e.toString());
			GenricResponse response=new GenricResponse(500,"user id is wrong","");
			return  response;

           
		}
		
			if(output!=null) {
				Usertype usertype=usertypeRepo.findById(output.getUsertype().getId());
			   UserDetails details=new UserDetails(output.getUserProfile().getFirstName(),
					   output.getUserProfile().getMiddleName(), output.getUserProfile().getLastName(),
					   output.getUserProfile().getPhoneNo(), output.getUserProfile().getEmail(),
					   usertype.getUsertypeName(), output.getUsername(), id, output.getUsertype().getId(), output.getRemark());
				GenricResponse response=new GenricResponse(200,"","",details);		
			
				return  response;
			}
			else {
				GenricResponse response=new GenricResponse(500,PortAddsTags.PAdd_Data_By_Id_Fail.getMessage(),PortAddsTags.PAdd_Data_By_Id_Fail.getTag());
				return  response;
			}

		}
		
}
