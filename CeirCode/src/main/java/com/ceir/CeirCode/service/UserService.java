package com.ceir.CeirCode.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.AlertDb;
import com.ceir.CeirCode.model.AuditTrail;
import com.ceir.CeirCode.model.ChangePassword;
import com.ceir.CeirCode.model.ChangeUserStatus;
import com.ceir.CeirCode.model.MessageConfigurationDb;
import com.ceir.CeirCode.model.Otp;
import com.ceir.CeirCode.model.PortAddress;
import com.ceir.CeirCode.model.QuestionPair;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.ResendOtp;
import com.ceir.CeirCode.model.RunningAlertDb;
import com.ceir.CeirCode.model.Securityquestion;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserPasswordHistory;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.UserSecurityquestion;
import com.ceir.CeirCode.model.UserStatusRequest;
import com.ceir.CeirCode.model.UserTemporarydetails;
import com.ceir.CeirCode.model.Userrole;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.model.constants.AlertStatus;
import com.ceir.CeirCode.model.constants.ChannelType;
import com.ceir.CeirCode.model.constants.SelfRegistration;
import com.ceir.CeirCode.model.constants.UserStatus;
import com.ceir.CeirCode.model.constants.UserTypeStatusFlag;
import com.ceir.CeirCode.model.constants.UsertypeData;
import com.ceir.CeirCode.othermodel.RolesData;
import com.ceir.CeirCode.repo.NotificationRepository;
import com.ceir.CeirCode.repo.SecurityQuestionRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repo.UserPasswordHistoryRepo;
import com.ceir.CeirCode.repo.UserProfileRepo;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UserRoleRepo;
import com.ceir.CeirCode.repo.UserSecurityQuestionRepo;
import com.ceir.CeirCode.repo.UserTemporarydetailsRepo;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.repoService.RunningAlertRepoService;
import com.ceir.CeirCode.repoService.AudiTrailRepoService;
import com.ceir.CeirCode.repoService.PortAddressRepoService;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.SystemConfigurationDbRepoService;
import com.ceir.CeirCode.repoService.UserPassHistoryRepoService;
import com.ceir.CeirCode.repoService.UserRepoService;
import com.ceir.CeirCode.response.GenricResponse;
import com.ceir.CeirCode.response.UpdateProfileResponse;
import com.ceir.CeirCode.response.tags.ProfileTags;
import com.ceir.CeirCode.response.tags.RegistrationTags;
import com.ceir.CeirCode.response.tags.UpdateUserStatusTags;
import com.ceir.CeirCode.util.NotificationUtil;
import com.ceir.CeirCode.util.GenerateRandomDigits;
import com.ceir.CeirCode.util.HttpResponse;
import com.ceir.CeirCode.util.OtpResponse;
import com.ceir.CeirCode.util.Utility;
import com.google.common.primitives.Longs;

@Service 
public class UserService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	UserRoleRepo userRoleRepo;
	@Autowired
	UserSecurityQuestionRepo userSecurityQuestionRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserProfileRepo  userProfileRepo;
	@Autowired
	OtpService otpService;
	@Autowired
	UsertypeRepo usertypeRepo;
	@Autowired 
	SecurityQuestionRepo securityQuestionRepo; 
	@Autowired   
	GenerateRandomDigits randomDigits;

	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	@Autowired
	SystemConfigDbRepoService systemConfigurationRepo;

	@Autowired
	NotificationUtil emailUtils;

	@Autowired
	UserTemporarydetailsRepo userTemporarydetailsRepo;

	@Autowired
	NotificationRepository notificationRepo;

	@Autowired
	UserPassHistoryRepoService userPassHistoryRepoImpl;

	@Autowired
	UserPasswordHistoryRepo userPasswordHistoryRepo;

	@Autowired
	Utility utitlity;

	@Autowired
	AudiTrailRepoService audiTrailRepoService;

	@Autowired
	ReqHeaderRepoService headerService;

	@Autowired
	PortAddressRepoService portAddressRepoService;

	@Autowired
	UserRepoService userRepoService;

	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;

	@Autowired
	RunningAlertRepoService alertDbRepo;

	public ResponseEntity<?> getUsertypeData(HttpHeaders headers){
		try {
			log.info("headers:  "+headers);
			List<Usertype> usertypeData=usertypeRepo.findBySelfRegister(SelfRegistration.YES.getCode());
			usertypeData.sort((u1,u2)->u1.getUsertypeName().compareTo(u2.getUsertypeName()));
			return new ResponseEntity<>(usertypeData, HttpStatus.OK);
		}
		catch(Exception e){
			HttpResponse response=new HttpResponse();
			response.setResponse("Oop something wrong happened");
			response.setStatusCode(409);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> getInternalUsertype(){
		try {
			List<Usertype> usertypeData=usertypeRepo.findBySelfRegister(SelfRegistration.NO.getCode());
			usertypeData.sort((u1,u2)->u1.getUsertypeName().compareTo(u2.getUsertypeName()));
			return new ResponseEntity<>(usertypeData, HttpStatus.OK);
		}
		catch(Exception e){
			HttpResponse response=new HttpResponse();
			response.setResponse("Oop something wrong happened");
			response.setStatusCode(409);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}

	public ResponseEntity<?> usertypeIdByName(String usertype){

		try {    
			Usertype userType=usertypeRepo.findByUsertypeName(usertype);
			if(userType!=null) {
				return new ResponseEntity<>(userType, HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse();
				response.setResponse("wrong usertype");
				response.setStatusCode(204);
				return new ResponseEntity<>(response,HttpStatus.OK);
			}

		}
		catch(Exception e){
			HttpResponse response=new HttpResponse();
			response.setResponse("Oop something wrong happened");
			response.setStatusCode(409);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}


	public ResponseEntity<?> getSecurityQuestion(String username){
		try {
			log.info("username:  "+username);
			User user=userRepo.findByUsername(username);
			if(user!=null) {
				List<Securityquestion> securityQuestionList=new ArrayList<Securityquestion>();
				if(user.getUserSecurityquestion().isEmpty()==false) {
				for(UserSecurityquestion securityQues:user.getUserSecurityquestion()) {
					Securityquestion ques=new Securityquestion(securityQues.getSecurityQuestion().getId(),
							securityQues.getSecurityQuestion().getQuestion(),securityQues.getSecurityQuestion().getCategory());
					securityQuestionList.add(ques);
				}
				}
				GenricResponse response=new GenricResponse(200,"data is found","",securityQuestionList);
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			else {
				GenricResponse response=new GenricResponse(409,RegistrationTags.REG_WRONG_USER_ID.getTag(),RegistrationTags.REG_WRONG_USER_ID.getMessage(),"");
				return new ResponseEntity<>(response,HttpStatus.OK);
			}

	
		}
		catch(Exception e) {
			GenricResponse response=new GenricResponse(409,RegistrationTags.COMMAN_FAIL_MSG.getTag(),RegistrationTags.COMMAN_FAIL_MSG.getMessage(),"");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> getSecurityQuestion2(){
	
		try {
		       List<Securityquestion> securityQuestionList=securityQuestionRepo.findAll();
				return new ResponseEntity<>(securityQuestionList,HttpStatus.OK);
	
		}
		catch(Exception e) {
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),409,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}

	public int saveRoles(long usertypes[] , long userid) {
		try {  
			for(long usertypeId:usertypes) {
				Userrole role=new Userrole();
				User user=new User();
				user.setId(userid);
				role.setUserData(user);
				Usertype userType=new Usertype();
				userType.setId(usertypeId);  
				role.setUsertypeData(userType); 
				Userrole roleOutput=userRoleRepo.save(role);
				log.info("save roles output :  "+roleOutput);
			}
			return 1;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int updateRoles(long usertypes[] , long userid) {
		try {                 
			log.info("going to update user roles");
			for(long usertypeId:usertypes) {
				Userrole role=new Userrole();
				User user=new User();
				user.setId(userid);
				role.setUserData(user);
				Usertype userType=new Usertype();
				userType.setId(usertypeId);  
				role.setUsertypeData(userType); 
				Userrole roles=userRoleRepo.findByUserData_IdAndUsertypeData_Id(userid, usertypeId);
				log.info("user roles by userid and user type id :  "+roles);
				if(roles!=null) {
					role.setId(roles.getId());  
				}
				Userrole roleOutput=userRoleRepo.save(role);
				log.info("save roles output :  "+roleOutput);
			}
			return 1;
		}
		catch(Exception e) {
			log.info("excpetion occur in updating user role");
			e.printStackTrace();
			return 0;
		}
	}

	public long roleCheck(long usertypes[]) {
		try { 
			log.info("inside add roles"); 
			log.info("usertypes= "+Arrays.toString(usertypes));
			log.info("usertypes length is"+usertypes.length); 
			if(usertypes.length==1) {
				long usertypeId = 0;
				for(long id:usertypes) {
					usertypeId=id;
				}
				return	usertypeId;
			}
			else if(usertypes.length>1){
				Arrays.sort(usertypes);
				long usertypeArray1[]={4,5,6};
				long usertypeArray2[]={4,5};
				long usertypeArray3[]={4,6};
				long usertypeArray4[]={5,6};  
				Arrays.sort(usertypeArray3);
				log.info("usertype array1= "+Arrays.toString(usertypeArray1));
				log.info("usertype array2= "+Arrays.toString(usertypeArray2));
				log.info("usertype array3= "+Arrays.toString(usertypeArray3));
				log.info("usertype array4= "+Arrays.toString(usertypeArray4));
				boolean b=Arrays.equals(usertypes, usertypeArray4);
				log.info("if roles are DR:  "+b);
				if(Arrays.equals(usertypes, usertypeArray1)) {
					log.info("if roles are IDR");
					return 4;
				}
				else if(Arrays.equals(usertypes, usertypeArray2)) {
					log.info("if roles are ID");
					return 4;
				}
				else if(Arrays.equals(usertypes, usertypeArray3)) {
					log.info("if roles are IR");
					return 4;
				} 
				else if(Arrays.equals(usertypes, usertypeArray4)) {
					log.info("if roles are DR");
					return 5;
				}
				else {
					log.info("if role set not found");
					return 0;

				}


			}
			return 0;
		}
		catch(Exception e) {

			e.printStackTrace();
			return 0;
		}
	}

	public long roleCheck2(long usertypes[],long mainRole) {
		try { 
			log.info("inside add roles"); 
			log.info("usertypes= "+Arrays.toString(usertypes));
			log.info("usertypes length is"+usertypes.length); 
			List<Long> roles=new ArrayList<Long>();
			roles.add(4l);
			roles.add(5l);
			roles.add(6l);
			if(roles.contains(mainRole)) {
			if(usertypes.length==1) {
				long usertypeId = 0;
				for(long id:usertypes) {
					usertypeId=id;
				}
				return	usertypeId;
			}
			
			else if(usertypes.length>1){
				Arrays.sort(usertypes);
				long usertypeArray1[]={4,5,6};
				long usertypeArray2[]={4,5};
				long usertypeArray3[]={4,6};
				long usertypeArray4[]={5,6};  
				Arrays.sort(usertypeArray3);
				log.info("usertype array1= "+Arrays.toString(usertypeArray1));
				log.info("usertype array2= "+Arrays.toString(usertypeArray2));
				log.info("usertype array3= "+Arrays.toString(usertypeArray3));
				log.info("usertype array4= "+Arrays.toString(usertypeArray4));
				boolean b=Arrays.equals(usertypes, usertypeArray4);
				log.info("if roles are DR:  "+b);
				if(Arrays.equals(usertypes, usertypeArray1)) {
					log.info("if roles are IDR");
					return 4;
				}
				else if(Arrays.equals(usertypes, usertypeArray2)) {
					log.info("if roles are ID");
					return 4;
				}
				else if(Arrays.equals(usertypes, usertypeArray3)) {
					log.info("if roles are IR");
					return 4;
				} 
				else if(Arrays.equals(usertypes, usertypeArray4)) {
					log.info("if roles are DR");
					return 5;
				}
				else {
					log.info("if role set not found");
					return 0;

				}


			}
			return 0;
			}
			else {
				
				return mainRole;
			}
		}
		catch(Exception e) {

			e.printStackTrace();
			return 0;
		}
	}
	public int saveUserSecurityquestion(UserProfile profile) {
		try {
			for(QuestionPair questionPair:profile.getQuestionList()) {
				UserSecurityquestion userSecurityQuestion=new UserSecurityquestion();
				Securityquestion securityQuestion=new Securityquestion();
				securityQuestion.setId(questionPair.getQuestionId());
				userSecurityQuestion.setAnswer(questionPair.getAnswer());
				userSecurityQuestion.setUser(profile.getUser());
				userSecurityQuestion.setSecurityQuestion(securityQuestion); 
				userSecurityQuestionRepo.save(userSecurityQuestion);
			}  
			return 1;  
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return 0;
		}
	};


	public int updateUserSecurityquestion(UserProfile profile) {
		try {
			log.info("inside update user security question function");
			log.info("question list from profile:  "+profile.getQuestionList());       
			log.info("userid from profile:  "+profile.getUser().getId());
			for(QuestionPair questionPair:profile.getQuestionList()) {
				log.info("inside for loop");
				UserSecurityquestion userSecurityQuestion=new UserSecurityquestion();
				Securityquestion securityQuestion=new Securityquestion();  
				log.info("going to find  userQuestionData ");
				log.info("userid is:  "+profile.getUser().getId()  +" question id is "+questionPair.getQuestionId() +" and answer is " +questionPair.getAnswer());
				securityQuestion.setId(questionPair.getQuestionId());
				userSecurityQuestion.setId(questionPair.getId()); 
				userSecurityQuestion.setAnswer(questionPair.getAnswer());
				userSecurityQuestion.setUser(profile.getUser());
				userSecurityQuestion.setSecurityQuestion(securityQuestion); 
				UserSecurityquestion output=	userSecurityQuestionRepo.save(userSecurityQuestion);
				log.info("updating user security question output:  "+output);
			}  
			return 1;
		}
		catch(Exception e) {
			log.info("inside user security question update error");
			log.info(e.getMessage());
			e.printStackTrace();
			return 0;
		}
	};
    @Transactional(rollbackOn = Exception.class)
  	public ResponseEntity<?> userRegistration(UserProfile userDetails)  {
    	try {
		log.info("user details----::::   "+userDetails); 
		Usertype userType=usertypeRepo.findByUsertypeName(userDetails.getUsertypeName());

		if(userType!=null) {
			if(userDetails.getRoles()==null) {
				long data[]= {userType.getId()}; userDetails.setRoles(data);
			}
		}
		long rolesOutput=roleCheck(userDetails.getRoles());
		boolean emailExist=userProfileRepo.existsByEmail(userDetails.getEmail());
		if(emailExist) {
		
			HttpResponse response=new HttpResponse(RegistrationTags.Email_Exist.getMessage(),409,RegistrationTags.Email_Exist.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);
		}

		boolean phoneExist=userProfileRepo.existsByPhoneNo(userDetails.getPhoneNo());
		if(phoneExist) {
			
			HttpResponse response=new HttpResponse(RegistrationTags.Phone_Exist.getMessage(),409,RegistrationTags.Phone_Exist.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		

		
		log.info("roles output:  "+rolesOutput);
		if(rolesOutput > 0) {
				List<Long> usertypeList=usertypeCheck();
				log.info("primary usertypeId is:  "+rolesOutput);	
				Usertype userTypeData=new Usertype();
				userTypeData.setId(rolesOutput);  
				userTypeData.setUsertypeName(userDetails.getUsername());
				User userData=new User(userDetails.getUsername(),userDetails.getPassword(),UserStatus.NEW.getCode(),null,userTypeData);  
				userData.setUserLanguage(userDetails.getUserLanguage());
				User userOutput=userRepo.save(userData);

				if(userOutput!=null) {
					RequestHeaders header=new RequestHeaders(userDetails.getUserAgent(),userDetails.getPublicIp(),userOutput.getUsername());
					headerService.saveRequestHeader(header);
					int rolesAddStatus=saveRoles(userDetails.getRoles(),userOutput.getId());
					saveUserTrail(userOutput,"Registration","user submit registration form",0);
					log.info("role addition status:  "+rolesAddStatus); 
					long userid=userOutput.getId();
					log.info("userid= "+userid);
					User users=new User();
					users.setId(userid);
					userDetails.setUser(userData);

					if(usertypeList.contains(userType.getId())) {
						if(userDetails.getType()==0){
							String displayName="";
							 if(Objects.nonNull(userDetails.getMiddleName())) {
						    	 displayName=userDetails.getFirstName()+" "+userDetails.getMiddleName()+" "+userDetails.getLastName();
						    	    	
						    }
						    else {
						    	
						    	 displayName=userDetails.getFirstName()+" "+userDetails.getLastName();

						    }
							userDetails.setDisplayName(displayName);
						}
						else {
					 		String displayName=userDetails.getCompanyName();      
							userDetails.setDisplayName(displayName);	  
						}
					}
					else {
						String displayName=userDetails.getFirstName()+" "+userDetails.getMiddleName()+" "+userDetails.getLastName();
						userDetails.setDisplayName(displayName);
					}
					UserProfile output=userProfileRepo.save(userDetails);			 
					if(output!=null) {
						UserPasswordHistory userPassHistory=new UserPasswordHistory(userOutput,userDetails.getPassword());
						log.info("going to save user passowrd in user_password_history table");
						UserPasswordHistory userPasswordOtput=userPassHistoryRepoImpl.saveUserPassword(userPassHistory);
						if(userPasswordOtput!=null) {
							log.info("user passowrd sucessfully save");
						}
						User dataForProfile=new User();
						dataForProfile.setId(userOutput.getId());
						String phoneOtp=otpService.phoneOtp(output.getPhoneNo());
						output.setPhoneOtp(phoneOtp);
						userProfileRepo.save(output);
						int outputGot=saveUserSecurityquestion(output);	
						log.info("user security question output:  "+outputGot);
						String emailOtp=randomDigits.getNumericString(6);
						output.setEmailOtp(emailOtp); 
						output.setSource("Self");
						userProfileRepo.save(output);	
						boolean notificationStatus =emailUtils.saveNotification("REG_VERIFY_OTP_EMAIL_MSG", output, 0,
								"User Registration", "Registration", userOutput.getUsername(), "OTP Notification for User Registration",emailOtp,ChannelType.EMAIL,"");
						log.info("notification save:  "+notificationStatus);
						boolean notificationStatusForSms =emailUtils.saveNotification("REG_VERIFY_OTP_SMS_MSG", output, 0,
								"User Registration", "Registration", userOutput.getUsername(),output.getFirstName(),phoneOtp,ChannelType.SMS,"");
						log.info("notificationStatusForSms save:  "+notificationStatusForSms);
					
					  userOutput.setPreviousStatus(UserStatus.NEW.getCode());
					  userOutput.setCurrentStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
					  userRepo.save(userOutput);
									OtpResponse response=new OtpResponse(RegistrationTags.REG_SUCESS_RESP.getMessage(),200,userOutput.getId(),RegistrationTags.REG_SUCESS_RESP.getTag());
						log.info("response:   "+response);
						return new ResponseEntity<>(response,HttpStatus.OK);
					} 
					else { 
						HttpResponse response=new HttpResponse(RegistrationTags.REG_PROFILE_FAIL_RESP.getMessage(),409,RegistrationTags.REG_PROFILE_FAIL_RESP.getTag());
						return new ResponseEntity<>(response,HttpStatus.OK);
					}
				}
				else {
					HttpResponse response=new HttpResponse(RegistrationTags.REG_USER_FAIL_RESP.getMessage(),409,RegistrationTags.REG_USER_FAIL_RESP.getTag());
					return new ResponseEntity<>(response,HttpStatus.OK);		
				}
			
		}
		else {    

			HttpResponse response=new HttpResponse(RegistrationTags.REG_FAIL_ROLES_RESP.getMessage(),409,RegistrationTags.REG_FAIL_ROLES_RESP.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
    	}
    	catch(Exception e) {
    		log.info(e.getMessage());
    		log.info(e.toString());
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),409,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);
    	}
	}
	public List<Long> usertypeCheck(){
		List<Long> usertypeId=new ArrayList<Long>();
		usertypeId.add(4l);
		usertypeId.add(5l);
		usertypeId.add(6l);
		return usertypeId;
	}


	public ResponseEntity<?> validateUser(Otp  otp)
	{                                
		try{ 
			log.info("inside validate otp ");
			log.info("otp data:  "+otp);    


			User output=userRepo.findById(otp.getUserid());
			if(output!=null) {
				RequestHeaders header=new RequestHeaders(otp.getUserAgent(),otp.getPublicIp(),output.getUsername());
				headerService.saveRequestHeader(header);
				saveUserTrail(output,"Registration","user validate",0);
				//return validateNewUser( output,otp);
				if(output.getPreviousStatus()==UserStatus.APPROVED.getCode()) {
					if(otp.getForgotPassword()==1) {
						return validateNewUser( output,otp);
					}
					else {
						return validateOldUser(output,otp);
					}
				
				} 
				else {
					return validateNewUser( output,otp);
				}
			}
			else {
				HttpResponse response=new HttpResponse(RegistrationTags.REG_WRONG_USER_ID.getMessage(),409,RegistrationTags.REG_WRONG_USER_ID.getTag());
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info("error occure");
			log.info(e.toString());
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),409,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}

	public ResponseEntity<?> validateOldUser(User user,Otp  otp){
		UserTemporarydetails details=userTemporarydetailsRepo.findByUserDetails_id(user.getId());
		long diffInMinutes=utitlity.timeDifferenceInMinutes(details.getModifiedOn());
		log.info("differnce in minuted between two times: "+diffInMinutes);
		if(diffInMinutes<=10) {
			if(otp.getEmailOtp().equals(details.getEmailIdOtp()) && otp.getPhoneOtp().equals(details.getMobileOtp())) {      				  
				user.setCurrentStatus(UserStatus.APPROVED.getCode());
				user.setPreviousStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
				log.info("going to save user status");  
				userRepo.save(user);   
				log.info("user status is saved");
				UserProfile userProfileData=user.getUserProfile();
				userProfileData.setPhoneNo(details.getMobileNo());
				userProfileData.setEmail(details.getEmailId());
				log.info("now going to save email and phone data in profile data");
				userProfileRepo.save(userProfileData);
				log.info("user profile data now save");
				HttpResponse response=new HttpResponse(RegistrationTags.OTP_SUCESS_For_UpdateUSer.getMessage(),200,RegistrationTags.OTP_SUCESS_For_UpdateUSer.getTag()); 
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else { 
				log.info("if otp are incorrect");
				HttpResponse response=new HttpResponse(RegistrationTags.REG_WRONG_OTP.getMessage(),409,RegistrationTags.REG_WRONG_OTP.getTag());
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}
		else {
			log.info("if Otp time is expired");
			HttpResponse response=new HttpResponse(RegistrationTags.REG_EXPIRE_OTP.getMessage(),409,RegistrationTags.REG_EXPIRE_OTP.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}

	}

	public ResponseEntity<?> validateNewUser(User output, Otp otp){
		log.info("user profile data from db:  "+output); 
		long diffInMinutes=utitlity.timeDifferenceInMinutes(output.getUserProfile().getModifiedOn());
		log.info("differnce in minuted between two times: "+diffInMinutes);
		if(diffInMinutes<=10) {
			if(otp.getEmailOtp().equals(output.getUserProfile().getEmailOtp()) && otp.getPhoneOtp().equals(output.getUserProfile().getPhoneOtp())) 
			{      
				log.info("if otp are correct");
				HttpResponse response=new HttpResponse(RegistrationTags.OTP_SUCESS_RESP.getMessage(),200,RegistrationTags.OTP_SUCESS_RESP.getTag()); 
				User user=userRepo.findById(output.getId());
				user.setPreviousStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
		
				if(otp.getForgotPassword()==1) {
					user.setCurrentStatus(UserStatus.APPROVED.getCode());     					
				}
				else {
					user.setCurrentStatus(UserStatus.PENDING_ADMIN_APPROVAL.getCode());     
					boolean notificationStatus2=emailUtils.saveNotification("REG_WAIT_USER_FOR_APPROV_STATUS", user.getUserProfile(),
							0, "Registration", "user phone and email details validated", user.getUsername(),"Registration Request Notification Alert "+output.getUserProfile().getFirstName(),"",ChannelType.EMAIL,"");
					log.info("notification save:  "+notificationStatus2);
		
				}

				userRepo.save(user); 
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}  
			else { 
				log.info("if otp are incorrect");
				HttpResponse response=new HttpResponse(RegistrationTags.REG_WRONG_OTP.getMessage(),409,RegistrationTags.REG_WRONG_OTP.getTag());
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}
		else {
			log.info("if Otp time is expired");
			HttpResponse response=new HttpResponse(RegistrationTags.REG_EXPIRE_OTP.getMessage(),409,RegistrationTags.REG_EXPIRE_OTP.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}
	public ResponseEntity<?> resendOtp(ResendOtp otp) {   
		log.info("inside resend otp controller"); 
		UserProfile profile=userProfileRepo.findByUser_Id(otp.getUserId());
		saveUserTrail(profile.getUser(),"Registration","resend otp",0);
		String smsOtp=otpService.phoneOtp(profile.getPhoneNo());
		String emailOtp=randomDigits.getNumericString(6);
		profile.setEmailOtp(emailOtp);
		profile.setPhoneOtp(smsOtp);
		UserProfile output=userProfileRepo.save(profile);
		boolean notificationStatus =emailUtils.saveNotification("REG_VERIFY_OTP_EMAIL_MSG", profile, 0,
				"User Registration", "Registration", profile.getUser().getUsername(), "Email verification resend otp",emailOtp,ChannelType.EMAIL,"");
		log.info("notification save:  "+notificationStatus);
		boolean notificationStatusForSms =emailUtils.saveNotification("REG_VERIFY_OTP_SMS_MSG", profile, 0,
				"User Registration", "Registration", profile.getUser().getUsername(), "Phone verification resend otp",smsOtp,ChannelType.SMS,"");
		log.info("notificationStatusForSms save:  "+notificationStatusForSms);
		if(output !=null) {
			RequestHeaders header=new RequestHeaders(otp.getUserAgent(),otp.getPublicIp(),output.getUser().getUsername());
			headerService.saveRequestHeader(header);
			User user=userRepo.findById(output.getUser().getId());
			userRepo.save(user);   
			HttpResponse response=new HttpResponse(RegistrationTags.REOTP_SUCESS_RESP.getMessage(),200,RegistrationTags.REOTP_SUCESS_RESP.getTag()); 
			return new ResponseEntity<>(response,HttpStatus.OK);	
		} 
		else {
			HttpResponse response=new HttpResponse(RegistrationTags.REG_REOTP_FAIL.getMessage(),409,RegistrationTags.REG_REOTP_FAIL.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}

	}
	
	public ResponseEntity<?> checkRegistration(Integer usertypeId){
		
		try {
			log.info("inside check registration method");
			log.info("usertypeId value "+usertypeId);
			Usertype usertype=usertypeRepo.findById(usertypeId);  
			log.info("now going to check this usertype is available for registration or not");
			if(usertype.getStatus()==UserTypeStatusFlag.on.getCode()) {
				log.info("usertype is available for trgistration");
				HashMap<Integer, String> map=new HashMap<Integer,String>();
				map.put(UsertypeData.Importer.getCode(), "imp_user_limit");
				map.put(UsertypeData.Distributor.getCode(), "dist_user_limit");
				map.put(UsertypeData.Retailer.getCode(), "ret_user_limit");
				map.put(UsertypeData.Custom.getCode(), "cus_user_limit");
				map.put(UsertypeData.Operator.getCode(), "ope_user_limit");
				map.put(UsertypeData.TRC.getCode(), "trc_user_limit");
				map.put(UsertypeData.Manufacturer.getCode(), "man_user_limit");
				map.put(UsertypeData.Lawful_Agency.getCode(), "law_user_limit");
				map.put(UsertypeData.End_User.getCode(), "end_user_limit");
				map.put(UsertypeData.Immigration.getCode(), "imm_user_limit");
				map.put(UsertypeData.Customer_Care.getCode(), "customer_user_limit");
				map.put(UsertypeData.DRT.getCode(), "drt_user_limit");
				
				log.info("then going to fetch data from system configuration db by tag "+map.get(usertypeId));
				SystemConfigurationDb systemConfigData=systemConfigurationDbRepoImpl.getDataByTag(map.get(usertypeId));
				long userLimit=0;
				if(systemConfigData!=null) {
					try {
						log.info("value got by tag= "+systemConfigData.getValue());
						userLimit=Long.parseLong(systemConfigData.getValue());				
					}
					catch(Exception e) {
						RunningAlertDb alertDb=new RunningAlertDb("alert001"," user creation limit is exceeded for "+usertype.getUsertypeName() +" usertype",AlertStatus.Init.getCode());
						alertDbRepo.saveAlertDb(alertDb);
						log.info(e.toString());
					}

				}
                
				long count=userRepoService.countByUsertypeId(usertypeId);
				log.info("total users find by this usertype= "+count);
				log.info("now going to compare these two above values");
				if(count>=userLimit) {
                    log.info("if usertype count greater than total users limit then we don't able to create new user now");
					HttpResponse response=new HttpResponse(RegistrationTags.Reg_userlimit_exceed.getMessage(),409,RegistrationTags.Reg_userlimit_exceed.getTag());
					return new ResponseEntity<>(response,HttpStatus.OK);
				}

				else {
                    log.info("if usertype count less than total users limit then so happily we able to create new user now");
					HttpResponse response=new HttpResponse(RegistrationTags.Reg_allowed.getMessage(),200,RegistrationTags.Reg_allowed.getTag());
					return new ResponseEntity<>(response,HttpStatus.OK);			
					
				}
				
			}
			
			else {
				log.info("usertype status is disable now so registration is not available as of now");
				HttpResponse response=new HttpResponse(RegistrationTags.Reg_flag_off.getMessage(),409,RegistrationTags.Reg_flag_off.getTag());
				return new ResponseEntity<>(response,HttpStatus.OK);			
			}
			
		}
		catch(Exception e) {
			log.info("something wrong happened here now");
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),500,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}

	public ResponseEntity<?> changePassword(ChangePassword password){
		try {
			log.info("inside change password controller");
			log.info("ChangePassword data from form: "+password);
			User user=userRepo.findById(password.getUserid());
			saveUserTrail(user, "Profile","change password",0);
			return changePasswordMethod(user,password);
		}

		catch(Exception e) {
			log.info("exception occur");
			e.printStackTrace();
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),409,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}


	public ResponseEntity<?> updateExpirePassword(ChangePassword password){
		try {
			log.info("inside change password controller");
			log.info("ChangePassword data from form: "+password);
			User user=userRepo.findById(password.getUserid());
			//user.setPasswordDate(LocalDateTime.now());
			saveUserTrail(user, "Login","save new password",0);
			return changePasswordMethod(user,password);
		}

		catch(Exception e) {
			log.info("exception occur");
			e.printStackTrace();
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409); 
			response.setResponse("Oops something wrong happened");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}



	public ResponseEntity<?> changePasswordMethod(User user,ChangePassword password){
		if(password.getOldPassword().equals(user.getPassword())) {
			boolean passwordExist=userPassHistoryRepoImpl.passwordExist(password.getPassword(), password.getUserid());
			if(passwordExist==true) {
				log.info("if this password exist");
				HttpResponse response=new HttpResponse(ProfileTags.PRO_CPASS_LAST_3PASS_ERROR.getMessage(),204,
						ProfileTags.PRO_CPASS_LAST_3PASS_ERROR.getTag());
				log.info("exit from change password");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				log.info("if this password does not exist");
				long count=userPassHistoryRepoImpl.countByUserId(password.getUserid());
				log.info("password count: "+count);
				if(count!=0) {
					if(count>=3) {
						log.info("going to delete password history greater than 3");
						UserPasswordHistory passHistory=userPassHistoryRepoImpl.getPasswordHistory(password.getUserid());
						userPasswordHistoryRepo.deleteById(passHistory.getId());
						user.setPassword(password.getPassword());
						return setPassword(user);
					}
					else {
						log.info("if password history less than 3");
						user.setPassword(password.getPassword());
						return setPassword(user);	
					}
				}
				else {
					user.setPassword(password.getPassword());
					return setPassword(user);	
				}

			}


		} 
		else {
			HttpResponse response=new HttpResponse(ProfileTags.PRO_OldPass_Error.getMessage(),204,
					ProfileTags.PRO_OldPass_Error.getTag());
			log.info("exit from change password");  
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}  
	}

	public ResponseEntity<?> setPassword(User user){
		User output=userRepo.save(user);
		if(output!=null) {
			UserPasswordHistory userPassHistory=new UserPasswordHistory();
			userPassHistory.setPassword(user.getPassword());
			userPassHistory.setUserPassword(output);
			userPassHistory.setCreatedOn(LocalDateTime.now());
			userPassHistory.setModifiedOn(LocalDateTime.now());
			log.info("going to save user passowrd in user_password_history table");
			UserPasswordHistory userPasswordOtput=userPassHistoryRepoImpl.saveUserPassword(userPassHistory);
			if(userPasswordOtput!=null) {
				log.info("user passowrd sucessfully save");
			}
			boolean notificationStatus=emailUtils.saveNotification("PRO_CHANGE_PASSWORD_BY_USER", output.getUserProfile(), 0, "Profile","change Password", output.getUsername(), "Password Change Notification for "+output.getUsername(), "",ChannelType.EMAIL,"");	
			log.info("notification save: "+notificationStatus);
			HttpResponse response=new HttpResponse(ProfileTags.PRO_CPASS_SUCESS.getMessage(),200,ProfileTags.PRO_CPASS_SUCESS.getTag());
			log.info("exit from change password");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
		else {
			HttpResponse response=new HttpResponse(ProfileTags.PRO_CPASS_FAIL.getMessage(),500,ProfileTags.PRO_CPASS_FAIL.getTag());
			log.info("exit from change password");   
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> setNewPassword(User user){
		User output=userRepo.save(user);
		if(output!=null) {
			UserPasswordHistory userPassHistory=new UserPasswordHistory();
			userPassHistory.setPassword(user.getPassword());
			userPassHistory.setUserPassword(output);
			userPassHistory.setCreatedOn(LocalDateTime.now());
			userPassHistory.setModifiedOn(LocalDateTime.now());
			log.info("going to save user passowrd in user_password_history table");
			UserPasswordHistory userPasswordOtput=userPassHistoryRepoImpl.saveUserPassword(userPassHistory);
			if(userPasswordOtput!=null) {
				log.info("user passowrd sucessfully save");
			}
			boolean notificationStatus=emailUtils.saveNotification("FORGOT_PASSWORD_EMAIL", output.getUserProfile(), 0, "Profile","forgot Password", output.getUsername(), "Password Update Notification for "+output.getUsername(), "",ChannelType.EMAIL,"");	
			log.info("notification save: "+notificationStatus);
			HttpResponse response=new HttpResponse(ProfileTags.NEW_PASS_SUC.getMessage(),200,ProfileTags.NEW_PASS_SUC.getTag());
			log.info("exit from update new password");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
		else {
			HttpResponse response=new HttpResponse(ProfileTags.NEW_PASS_FAIL.getMessage(),500,ProfileTags.NEW_PASS_FAIL.getTag());
			log.info("exit from  update New password");   
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}


	public ResponseEntity<?> updateUserStatus(UserStatusRequest userStatus){
		log.info("inside  update User status  controller");  
		log.info(" userStatus data:  "+userStatus);      
		log.info("get user  data by userid below");  
		User user=userRepo.findById(userStatus.getUserId());
		if(user!=null) {

			Integer userStatus2 = UserStatus.getUserStatusByDesc(userStatus.getStatus()).getCode();
			user.setPreviousStatus(user.getCurrentStatus()); 
			user.setCurrentStatus(userStatus2); 
			user.setModifiedBy(userStatus.getUsername());
			User output=userRepo.save(user); 
			log.info("user data after update the status: "+output);
			if(output!=null) {
				String tag="";
				String subFeature=new String();
				String subject="";
				if(output.getCurrentStatus()==UserStatus.APPROVED.getCode()) {
					tag="PRO_ENABLE_ACC_BY_USER";
					log.info("if user status is approved");
					subFeature="user enable";
					subject="Account Enabled Notification for "+output.getUsername();

				}
				else if(output.getCurrentStatus()==UserStatus.DISABLE.getCode()) {
					tag="PRO_DISABLE_ACC_BY_USER";
					log.info("if user status is disable");
					subFeature="user disable";
					subject="Account Disabled Notification for "+output.getUsername();
				}
				else if(output.getCurrentStatus()==UserStatus.DEACTIVATE.getCode()) {
					tag="PRO_DEACTIVATE_ACC_BY_USER";
					log.info("if user status is deactivate");
					subFeature="user deactivate";
					subject="Deactivation Notification for "+output.getUsername();
				}
				else { 
					tag="";
					log.info("current user status id "+ output.getCurrentStatus() );
					subFeature="";
				}
				saveUserTrail(user, "Profile",subFeature,0);			
				boolean notificationStatus=emailUtils.saveNotification(tag, output.getUserProfile(),0, "Profile","update user status", output.getUsername(), ""
						+ subject, "",ChannelType.EMAIL,"");
				log.info("notification save: "+notificationStatus);
				HttpResponse response=new HttpResponse(UpdateUserStatusTags.USER_STATUS_CHANGED.getMessage(),
						200,UpdateUserStatusTags.USER_STATUS_CHANGED.getTag());
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse(UpdateUserStatusTags.USER_STATUS_CHANGE_FAIL.getMessage(),
						500,UpdateUserStatusTags.USER_STATUS_CHANGE_FAIL.getTag());
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			} 
		}    
		else { 
			HttpResponse response=new HttpResponse(UpdateUserStatusTags.USER_STATUS_CHANGE_FAIL.getMessage(),
					409,UpdateUserStatusTags.USER_STATUS_CHANGE_FAIL.getTag());
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}



	public ResponseEntity<?> changeUserStatus(ChangeUserStatus userStatus){
		log.info("inside  chane User status  controller");  
		log.info(" userStatus data:  "+userStatus);      
		log.info("get user  data by userid below");  
		User user=userRepo.findById(userStatus.getId());
		if(user!=null) {
			user.setPreviousStatus(user.getCurrentStatus()); 
			user.setCurrentStatus(userStatus.getStatus()); 
			user.setRemark(userStatus.getRemark());
			user.setReferenceId(userStatus.getReferenceId());
			user.setModifiedBy(userStatus.getUsername());
			User output=userRepo.save(user); 
			User userData=userRepo.findById(userStatus.getUserId());
			if(userData!=null)
			{
				saveUserTrail(userData, "Registration Request","change user status",8);				
			}
			
			log.info("user data after update the status: "+output);
			if(output!=null) {
	
				HttpResponse response=new HttpResponse(UpdateUserStatusTags.USER_STATUS_CHANGED.getMessage(),
						200,UpdateUserStatusTags.USER_STATUS_CHANGED.getTag());
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse(UpdateUserStatusTags.USER_STATUS_CHANGE_FAIL.getMessage(),
						500,UpdateUserStatusTags.USER_STATUS_CHANGE_FAIL.getTag());
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			} 
		}    
		else { 
			HttpResponse response=new HttpResponse(UpdateUserStatusTags.USER_STATUS_WRONG_USERID.getMessage(),
					409,UpdateUserStatusTags.USER_STATUS_WRONG_USERID.getTag());
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}


	public ResponseEntity<?> editProfile(long id){
		try {
			log.info("inside into edit profile");     
			log.info("user id:  "+id);    
			log.info("get user  data by userid below");
			UserProfile user=userProfileRepo.findByUser_Id(id);
			if(Objects.nonNull(user)) {
				saveUserTrail(user.getUser(),"Profile","view",0);
				log.info("user profile data: "+user);
				List<QuestionPair> questionList=new ArrayList<QuestionPair>();
				log.info("now going to fetch security question data of user");
				for(UserSecurityquestion question:user.getUser().getUserSecurityquestion()) {
					QuestionPair questionPair=new QuestionPair();
					questionPair.setId(question.getId());  
					questionPair.setQuestion(question.getSecurityQuestion().getQuestion());
					questionPair.setQuestionId(question.getSecurityQuestion().getId());
					questionPair.setAnswer(question.getAnswer());
					questionList.add(questionPair); 
				}
				log.info("questionList:  "+questionList);
				user.setQuestionList(questionList);
				user.setUserTypeId(user.getUser().getUsertype().getId());
				List<Long> rolesId=new ArrayList<Long>();
				log.info("now going to fetch roles of user");
				for(Userrole userRoles:user.getUser().getUserRole()) {
					rolesId.add(userRoles.getUsertypeData().getId());
				}     
				log.info("roles Ids :  "+rolesId);
				long[] arr = new long[rolesId.size()]; 
				arr = Longs.toArray(rolesId); 
				user.setRoles(arr); 
				List<SystemConfigListDb> asTypeList=systemConfigRepo.getByTag("AS_TYPE");
				for(SystemConfigListDb asType:asTypeList) {
					Integer value=asType.getValue();
					if(user.getType()==value) {
						user.setAsTypeName(asType.getInterp());
					}
				}
				List<SystemConfigListDb> arrivaPortList=systemConfigRepo.getByTag("CUSTOMS_PORT");
				for(SystemConfigListDb port:arrivaPortList) {
					Integer value=port.getValue();
					if(user.getArrivalPort()==value) {
						user.setArrivalPortName(port.getInterp());
					}
				}
			
				List<SystemConfigListDb> nature_Of_Employment=systemConfigRepo.getByTag("Nature_Of_Employment");
				for(SystemConfigListDb emplomentType:nature_Of_Employment) {
					Integer value=emplomentType.getValue();
					if(user.getNatureOfEmployment()==value) {
						user.setNatureOfEmploymentInterp(emplomentType.getInterp());
					}
				}
				
				if(user.getPortAddress()!=null) {
					PortAddress portAddress=portAddressRepoService.getById(user.getPortAddress());
					user.setPortAddressName(portAddress.getAddress());
				}

				SystemConfigurationDb filePath=systemConfigurationRepo.getDataByTag("USER_FILE_DOWNLOAD_PATH");	
				if(filePath!=null) {
					if(user.getNidFilename()!=null || !"null".equalsIgnoreCase(user.getNidFilename())) {
						user.setNidFilePath(filePath.getValue()+"/"+user.getUser().getUsername()+"/NID/");				
					}
					if(user.getPhotoFilename()!=null || !"null".equalsIgnoreCase(user.getPhotoFilename())) {
						user.setPhotoFilePath(filePath.getValue()+"/"+user.getUser().getUsername()+"/photo/");	
					}
					if(user.getIdCardFilename()!=null || !"null".equalsIgnoreCase(user.getIdCardFilename())) {
						user.setIdCardFilePath(filePath.getValue()+"/"+user.getUser().getUsername()+"/IDCard/");	
					}
					if(user.getVatFilename()!=null || !"null".equalsIgnoreCase(user.getVatFilename())) {
						user.setVatFilePath(filePath.getValue()+"/"+user.getUser().getUsername()+"/Vat/");					
					}
			}
				log.info("All data now fetched");
				return new ResponseEntity<>(user,HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(204);  
				response.setResponse("User profile data fail to find by userid"); 
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}
		catch(Exception e) {
			log.info("exception occur");
			e.printStackTrace();
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409); 
			response.setResponse("Oops something wrong happened");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}

	}


	public void setEmailAndPhoneDetails(UserProfile userProfile,User userData) {
		String phoneOtp=otpService.phoneOtp(userProfile.getPhoneNo()); 
		String emailOtpData=randomDigits.getNumericString(6);
		userProfile.setUser(userData);
		boolean notificationStatus=emailUtils.saveNotification("PRO_VERIFY_OTP_EMAIL_MSG", userProfile, 0,
				"User Profile", "update Profile", userData.getUsername(),
				"Edit Profile Notification "+userData.getUsername(),emailOtpData,
				ChannelType.EMAIL,"user_temp"); 
		log.info("notification save:  "+notificationStatus);
		boolean notificationStatusForSms=emailUtils.saveNotification("PRO_VERIFY_OTP__MSG", userProfile, 0,
				"User Profile", "update Profile", userData.getUsername(),
				userProfile.getFirstName(),phoneOtp,ChannelType.SMS,"user_temp");
		log.info("notificationStatusForSms save:  "+notificationStatusForSms);
		UserTemporarydetails details=new UserTemporarydetails(userData,userProfile.getEmail(),userProfile.getPhoneNo(),emailOtpData,phoneOtp); 
		UserTemporarydetails dataByProfileId=userTemporarydetailsRepo.findByUserDetails_id(userData.getId());
		log.info("temporary email and phone no profile details by user profile table id "+userProfile.getId()); 
		if(dataByProfileId!=null) {
			details.setId(dataByProfileId.getId()); }
		log.info("going to add data to user profile temporary data table");
		userTemporarydetailsRepo.save(details);
		log.info("after adding data to temporaray table");
		userData.setPreviousStatus(UserStatus.APPROVED.getCode());
		userData.setCurrentStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
		userRepo.save(userData); log.info("user data changed");
	}
	
	
	public ResponseEntity<?> updateProfile(UserProfile userProfile){
		try {
			log.info("inside into update user profile");     
			log.info("user profile data:  :  "+userProfile);    
			UserProfile userProfileData=userProfileRepo.findById(userProfile.getId());
			if(userProfile.getPassword().equals(userProfileData.getUser().getPassword())) {
				log.info("user profile data by id:  "+userProfileData); 
				/*
				 * long mainRole=roleCheck(userProfile.getRoles()); if(mainRole>0) {
				 */
					User userData=userRepo.findByUserProfile_Id(userProfile.getId());
					saveUserTrail(userData,"Profile","update",0);   
					boolean emailExist=userProfileRepo.existsByEmail(userProfile.getEmail());
					boolean phoneExist=userProfileRepo.existsByPhoneNo(userProfile.getPhoneNo());
					if(!userProfile.getPhoneNo().equals(userProfileData.getPhoneNo()) &&
							!userProfile.getEmail().equals(userProfileData.getEmail())) { 
						
						if(emailExist) {
						
							HttpResponse response=new HttpResponse(RegistrationTags.Email_Exist.getMessage(),409,RegistrationTags.Email_Exist.getTag());
							return new ResponseEntity<>(response,HttpStatus.OK);
						}


						if(phoneExist) {
							
							HttpResponse response=new HttpResponse(RegistrationTags.Phone_Exist.getMessage(),409,RegistrationTags.Phone_Exist.getTag());
							return new ResponseEntity<>(response,HttpStatus.OK);
						}
						setEmailAndPhoneDetails(userProfile,userData);
					}
					
					else if(!userProfile.getPhoneNo().equals(userProfileData.getPhoneNo()) &&
							userProfile.getEmail().equals(userProfileData.getEmail())) { 
						if(phoneExist) {
							
							HttpResponse response=new HttpResponse(RegistrationTags.Phone_Exist.getMessage(),409,RegistrationTags.Phone_Exist.getTag());
							return new ResponseEntity<>(response,HttpStatus.OK);
						}
						setEmailAndPhoneDetails(userProfile,userData);
						 
					}
					else	if(userProfile.getPhoneNo().equals(userProfileData.getPhoneNo()) &&
							!userProfile.getEmail().equals(userProfileData.getEmail())) { 
						if(emailExist) {
						
							HttpResponse response=new HttpResponse(RegistrationTags.Email_Exist.getMessage(),409,RegistrationTags.Email_Exist.getTag());
							return new ResponseEntity<>(response,HttpStatus.OK);
						}
						setEmailAndPhoneDetails(userProfile,userData);
					
					}

					updateUserFields(userProfile,userProfileData);
					userProfileData.setUser(userData);
					log.info("userProfile data going to update: "+userProfileData);
					log.info("now going to save user profile data");
					UserProfile output=userProfileRepo.save(userProfileData);
					log.info("user profile data is save");
					if(output!=null) { 
                        if(userProfile.getRoles()!=null) {
                        	if(userProfile.getRoles().length!=0) {
                        		int rolesOutput=updateRoles(userProfile.getRoles(), output.getUser().getId());    		
                        		log.info("user role update output:  "+rolesOutput);
                        	}
                        }
					
				
						log.info("if user profile table is update ");
						log.info("going to update user  questions and answer");
						int i=updateUserSecurityquestion(userProfileData);
						log.info("question output:  "+i); 
						String tag="";
						String msg="";
						if(userData.getCurrentStatus()==UserStatus.APPROVED.getCode()) {
							tag=ProfileTags.PRO_SUCESS_MSG.getTag();
							msg=ProfileTags.PRO_SUCESS_MSG.getMessage();
						}
						else {
							tag=ProfileTags.PRO_SUCESS_OTPMSG.getTag();
							msg=ProfileTags.PRO_SUCESS_OTPMSG.getMessage();
				
						}
						UpdateProfileResponse response=new UpdateProfileResponse(msg,200,UserStatus.getUserStatusByCode(userData.getCurrentStatus()).getDescription()
								,output.getUser().getId(),tag);
						log.info("response send to user:  "+response); 
						return new ResponseEntity<>(response,HttpStatus.OK);	
					}                 
					else {
						log.info("user profile to update");
						HttpResponse response=new HttpResponse(ProfileTags.PRO_FAIL_MSG.getMessage(),204,ProfileTags.PRO_FAIL_MSG.getTag());
						log.info("response send to user:  "+response);
						return new ResponseEntity<>(response,HttpStatus.OK);	
					}
				/*}
				else {
					log.info("user profile to update");
					HttpResponse response=new HttpResponse(RegistrationTags.REG_FAIL_ROLES_RESP.getMessage(),204,RegistrationTags.REG_FAIL_ROLES_RESP.getTag());
					log.info("response send to user:  "+response);
					return new ResponseEntity<>(response,HttpStatus.OK);	
				}*/
			}
			else { 
				log.info("user profile to update");
				HttpResponse response=new HttpResponse(ProfileTags.PRO_CORRECT_PASS.getMessage(),401,ProfileTags.PRO_CORRECT_PASS.getTag());
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}
		catch(Exception e) {
			log.info("if exception occur");
			log.info(e.toString());
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),409,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}

	}


	public void updateUserFields(UserProfile userProfile,UserProfile userProfileData ) {
		userProfileData.setFirstName(userProfile.getFirstName());
		userProfileData.setMiddleName(userProfile.getMiddleName());
		userProfileData.setLastName(userProfile.getLastName());
		userProfileData.setPropertyLocation(userProfile.getPropertyLocation());
		userProfileData.setStreet(userProfile.getStreet());
		userProfileData.setVillage(userProfile.getVillage());
		userProfileData.setLocality(userProfile.getLocality());
		userProfileData.setDistrict(userProfile.getDistrict());
		userProfileData.setCommune(userProfile.getCommune());
		userProfileData.setPostalCode(userProfile.getPostalCode());
		userProfileData.setCountry(userProfile.getCountry());
		userProfileData.setProvince(userProfile.getProvince());
		userProfileData.setCountry(userProfile.getCountry());
		userProfileData.setQuestionList(userProfile.getQuestionList());
	}

	public HttpResponse updateStatus(User user) {
		HttpResponse response=new HttpResponse();
		try {

			User userDetails = userRepo.findById(user.getId());
			if(userDetails == null) {
				response.setStatusCode(204);   
				response.setResponse("User  data not found"); 
				return response;
			}
			else {
				user.setCreatedOn(userDetails.getCreatedOn());
				user.setPassword(userDetails.getPassword());
				user.setUsername(userDetails.getUsername());
				userRepo.save(user);
				response.setStatusCode(200);   
				response.setResponse("User status is update");
				return response;
			}
		} catch (Exception e) {
			response.setStatusCode(204);   
			response.setResponse("User profile data fail to update"); 
			log.info("response send to user:  "+response);
			return  response;	
		}
	}

	public ResponseEntity<?> profileDataById(long id,long userId){
		try { 
			log.info("inside into edit profile");     
			log.info("user id:  "+id);    
			log.info("get user profile data by id below");
			UserProfile user=userProfileRepo.findById(id);
			if(user!=null) {  
				User userData=userRepo.findById(userId);
				if(userData!=null)
				{
					saveUserTrail(userData, "Registration Request","view By Id",8);					
				}

				List<Long> rolesId=new ArrayList<Long>();
				List<RolesData> rolesList=new ArrayList<RolesData>();
				for(Userrole userRoles:user.getUser().getUserRole()) {
					rolesId.add(userRoles.getUsertypeData().getId());
					rolesList.add(new RolesData(userRoles.getUserData().getId(),userRoles.getUsertypeData().getUsertypeName()));
				}     
				log.info("roles Ids :  "+rolesId);
				log.info("user roles :  "+rolesList);
				
				long[] arr = new long[rolesId.size()]; 
				arr = Longs.toArray(rolesId); 
			
				user.setRolesList(rolesList);
				user.setRoles(arr); 
				List<SystemConfigListDb> asTypeList=systemConfigRepo.getByTag("AS_TYPE");
				for(SystemConfigListDb asType:asTypeList) {
					Integer value=asType.getValue();
					if(user.getType()==value) {
						user.setAsTypeName(asType.getInterp());
					}
				}
				List<SystemConfigListDb> arrivaPortList=systemConfigRepo.getByTag("CUSTOMS_PORT");
				for(SystemConfigListDb port:arrivaPortList) {
					Integer value=port.getValue();
					if(user.getArrivalPort()==value) {
						user.setArrivalPortName(port.getInterp());
					}
				}
				List<SystemConfigListDb> nature_Of_Employment=systemConfigRepo.getByTag("Nature_Of_Employment");
				for(SystemConfigListDb emplomentType:nature_Of_Employment) {
					Integer value=emplomentType.getValue();
					if(user.getNatureOfEmployment()==value) {
						user.setNatureOfEmploymentInterp(emplomentType.getInterp());
					}
				}
				if(user.getPortAddress()!=null) {
					PortAddress portAddress=portAddressRepoService.getById(user.getPortAddress());
					user.setPortAddressName(portAddress.getAddress());
				}

				SystemConfigurationDb filePath=systemConfigurationRepo.getDataByTag("USER_FILE_DOWNLOAD_PATH");	
				if(filePath!=null) {
					if(user.getNidFilename()!=null || !"null".equalsIgnoreCase(user.getNidFilename())) {
						user.setNidFilePath(filePath.getValue()+"/"+user.getUser().getUsername()+"/NID/");				
					}
					if(user.getPhotoFilename()!=null || !"null".equalsIgnoreCase(user.getPhotoFilename())) {
						user.setPhotoFilePath(filePath.getValue()+"/"+user.getUser().getUsername()+"/photo/");	
					}
					if(user.getIdCardFilename()!=null || !"null".equalsIgnoreCase(user.getIdCardFilename())) {
						user.setIdCardFilePath(filePath.getValue()+"/"+user.getUser().getUsername()+"/IDCard/");	
					}
					if(user.getVatFilename()!=null || !"null".equalsIgnoreCase(user.getVatFilename())) {
						user.setVatFilePath(filePath.getValue()+"/"+user.getUser().getUsername()+"/Vat/");					
					}
				}
				return new ResponseEntity<>(user,HttpStatus.OK);	
			}                 
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(204);  
				response.setResponse("User profile data not found"); 
				log.info("response send to user:  "+response);

				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}
		catch(Exception e) {
			log.info("exception occur here");
            log.info(e.toString());
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409); 
			response.setResponse("Oops something wrong happened");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		} 
	}

	public HttpResponse AdminApproval(UserStatusRequest userStatus) {
		try {
			log.info("userStatus data: "+userStatus);
			log.info("inside admin approval controller");
			User user=userRepo.findById(userStatus.getId());
			if(user!=null) {
				user.setApprovedBy(user.getUsername());
				user.setRemark(userStatus.getRemark());
				user.setPreviousStatus(user.getCurrentStatus());
				user.setCurrentStatus(userStatus.getStatusValue());
                user.setModifiedBy(userStatus.getUsername());
				log.info("user data to update:  "+user);
				User output=userRepo.save(user);
				log.info("output got:  "+output);
				log.info("user data:  "+output);
				if(output!=null) {
					String tag="";
					String subject="";
					String status="";
					String feature="";
					if(output.getCurrentStatus()==UserStatus.APPROVED.getCode()) {
						feature="Approve";
						tag="REG_ACCEPT_BY_CEIR_ADMIN";
						subject="Registration Approval Notification for "+output.getUsername();
						status="Approve";
					}
					else if(output.getCurrentStatus()==UserStatus.REJECTED.getCode()) {
						feature="Reject";
						tag="REG_REJECT_BY_CEIR_ADMIN";
						subject="Registration Rejection Notification for "+output.getUsername();
						status="Reject";
					}
					else {}
					User userData=userRepo.findById(userStatus.getUserId());
					if(userData!=null) {
						saveUserTrail(userData,"Registration Request",feature,userStatus.getFeatureId());
					}
					boolean emailStatus=emailUtils.saveNotification(tag, output.getUserProfile(), userStatus.getFeatureId(), "Registration Request",status, output.getUsername(), subject,"",ChannelType.EMAIL,"");
					log.info("emailStatus : "+emailStatus);
					HttpResponse response=new HttpResponse();
					response.setStatusCode(200);
					response.setResponse("user status has been update");
					return response;
				}
				
				else {
					HttpResponse response=new HttpResponse();
					response.setStatusCode(204);
					response.setResponse("user status failed to update");
					return response;
				} 
			}    
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(204);
				response.setResponse("please enter correct user id");
				return response;
			}

		} 
		catch(Exception e) {
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409); 
			response.setResponse("Oops something wrong happened");
			return response;
		}
	}

	public String emailContent(MessageConfigurationDb msgConfigDb,UserProfile profile,String otp) {
		log.info("tag:  "+msgConfigDb.getTag());
		log.info("value: "+msgConfigDb.getValue());
		log.info("user data:  "+profile.getUser());
		String emailBody=null;

		emailBody=msgConfigDb.getValue();
		HashMap<String, String> map=contentMap( emailBody, profile, otp);
		String[] words={"<First name>","<userID>","<Reason>","<otp>","<number>","<user_type>"};
		for(int i=0;i<words.length;i++) {
			log.info("tag: "+words[i]);
			if(emailBody.contains(words[i])) {
				log.info("if this tag is present in emailContent");
				emailBody=map.get(words[i]);
			}
		}
		return emailBody;

		//		if("REG_ACCEPT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag())){
		//			emailBody=emailBody.replaceAll("<First name>", profile.getFirstName());
		//			emailBody=emailBody.replaceAll("<userID>", profile.getUser().getUsername());
		//			return emailBody;
		//		
		//		else if("REG_REJECT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag())){
		//			emailBody=emailBody.replaceAll("<First name>", profile.getFirstName());
		//			emailBody=emailBody.replaceAll("<userID>", profile.getUser().getUsername());
		//			emailBody=emailBody.replaceAll("<Reason>", profile.getUser().getRemark());
		//			return emailBody;
		//		}
		//		else  if("REG_VERIFY_OTP_EMAIL_MSG".equals(msgConfigDb.getTag())){
		//			emailBody=emailBody.replaceAll("<otp>", otp);
		//			return emailBody; 
		//		}
		//		else if("PRO_CHANGE_PASSWORD_BY_USER".equals(msgConfigDb.getTag()) || "PRO_DEACTIVATE_ACC_BY_USER".equals(msgConfigDb.getTag()) ||
		//				"PRO_DISABLE_ACC_BY_USER".equals(msgConfigDb.getTag()) || "PRO_ENABLE_ACC_BY_USER".equals(msgConfigDb.getTag())
		//				|| "REG_WAIT_USER_FOR_APPROV_STATUS".equals(msgConfigDb.getTag()) ){
		//			emailBody=emailBody.replaceAll("<First name>", profile.getFirstName());
		//			return emailBody;
		//		}
		//		else  if("REG_VERIFY_OTP_SMS_MSG".equals(msgConfigDb.getTag())){
		//			emailBody=emailBody.replaceAll("<number>", otp);
		//			return emailBody; 
		//		}
		//		else {return emailBody;}
	}

	public HashMap<String, String> contentMap(String emailBody,UserProfile profile,String otp){
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("<First name>",emailBody= emailBody.replaceAll("<First name>", profile.getFirstName()));
		map.put("<userID>",emailBody=emailBody.replaceAll("<userID>", profile.getUser().getUsername()));
		map.put("<Reason>", emailBody=emailBody.replaceAll("<Reason>", profile.getUser().getRemark()));
		map.put("<otp>",emailBody=emailBody.replaceAll("<otp>", otp));
		map.put("<number>",emailBody=emailBody.replaceAll("<number>", otp));
		map.put("<user_type>",emailBody=emailBody.replaceAll("<user_type>", profile.getUser().getUsertype().getUsertypeName()));
		return map;
	}

	public int saveUserTrail(User user,String feature ,String subFeature,long featureId) {
		try {
			AuditTrail auditTrail=new AuditTrail(user.getId(), user.getUsername(),
					user.getUsertype().getId(),user.getUsertype().getUsertypeName(), featureId,
					feature, subFeature,"0","NA");
			log.info("going to save audit trail");
			AuditTrail output=audiTrailRepoService.saveAuditTrail(auditTrail);
			if(output!=null) {
				log.info("audit trail sucessfully save");
			}
			else {
				log.info("user trail fails to save");
			}
			return 1;
		}
		catch(Exception e) {
			log.info(e.toString());
			return 0;
		}
	}
} 