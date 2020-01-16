package com.ceir.CeirCode.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.SpecificationBuilder.UserProfileSpecificationBuilder;
import com.ceir.CeirCode.configuration.PropertiesReaders;
import com.ceir.CeirCode.model.ChangePassword;
import com.ceir.CeirCode.model.EmailDetails;
import com.ceir.CeirCode.model.FilterRequest;
import com.ceir.CeirCode.model.MessageConfigurationDb;
import com.ceir.CeirCode.model.Otp;
import com.ceir.CeirCode.model.QuestionPair;
import com.ceir.CeirCode.model.SearchCriteria;
import com.ceir.CeirCode.model.Securityquestion;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.UserSecurityquestion;
import com.ceir.CeirCode.model.UserStatusRequest;
import com.ceir.CeirCode.model.UserTemporarydetails;
import com.ceir.CeirCode.model.Userrole;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.Constants.Datatype;
import com.ceir.CeirCode.Constants.SearchOperation;
import com.ceir.CeirCode.model.constants.UserStatus;
import com.ceir.CeirCode.repo.NotificationRepository;
import com.ceir.CeirCode.repo.SecurityQuestionRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repo.UserProfileRepo;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UserRoleRepo;
import com.ceir.CeirCode.repo.UserSecurityQuestionRepo;
import com.ceir.CeirCode.repo.UserTemporarydetailsRepo;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.response.UpdateProfileResponse;
import com.ceir.CeirCode.util.EmailUtil;
import com.ceir.CeirCode.util.EmailUtil2;
import com.ceir.CeirCode.util.GenerateRandomDigits;
import com.ceir.CeirCode.util.HttpResponse;
import com.ceir.CeirCode.util.OtpResponse;
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
	EmailUtil2 emailUtil;
	@Autowired
	OtpService otpService;
	@Value("${mailusername}")
	String fromEmail;
	@Autowired
	UsertypeRepo usertypeRepo;
	@Autowired 
	SecurityQuestionRepo securityQuestionRepo; 
	@Autowired   
	GenerateRandomDigits randomDigits;

	@Autowired
	SystemConfigDbListRepository systemConfigRepo;

	@Autowired
	EmailUtil emailUtils;


	@Autowired
	UserTemporarydetailsRepo userTemporarydetailsRepo;

	@Autowired
	NotificationRepository notificationRepo;

	public ResponseEntity<?> getUsertypeData(){

		try {    
			List<Usertype> usertypeData=usertypeRepo.findAll();  
			return new ResponseEntity<>(usertypeData, HttpStatus.OK);
		}
		catch(Exception e){
			HttpResponse response=new HttpResponse();
			response.setResponse("Oop something wrong happened");
			response.setStatusCode(409);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	} 

	public ResponseEntity<?> getSecurityQuestion(){
		try {
			List<Securityquestion> securityQuestionList=securityQuestionRepo.findAll();
			return new ResponseEntity<>(securityQuestionList,HttpStatus.OK);
		}
		catch(Exception e) {
			HttpResponse response=new HttpResponse();
			response.setResponse("Oop something wrong happened");
			response.setStatusCode(409);
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
				role.setCreatedOn(new Date());
				role.setModifiedOn(new Date());;
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
				role.setCreatedOn(new Date());
				role.setModifiedOn(new Date());
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

				long usertypeArray1[]={4,5,6};
				long usertypeArray2[]={4,5};
				long usertypeArray3[]={4,5};
				long usertypeArray4[]={5,6};  
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

				//	log.info("if roles length  greater than 1");
				//				Set<String> usertypeNameList = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
				//				HashMap<String, Long> map =new HashMap<String, Long>();
				//				
				//				HashMap<String, Long> map2 =new HashMap<String, Long>();

				//if(map.equals(map2))
				//				
				//				for(long utId:usertypes) {
				//					log.info("find usertypes name");
				//					Usertype ut=usertypeRepo.findById(utId);
				//					usertypeNameList.add(ut.getUsertypeName());
				//					map.put(ut.getUsertypeName(), utId);
				//				}
				//
				//				log.info("usertype value in hashmap:  "+map); 
				//				Set<String> set1 = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
				//				set1.add("Importer");
				//				set1.add("Distributor");
				//				set1.add("Retailer");	
				//				log.info("set1 =  "+set1);
				//				Set<String> set2 = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
				//				set2.add("Importer");
				//				set2.add("Distributor");
				//				
				//				log.info("set2 =  "+set2);
				//				Set<String> set3 = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
				//				set3.add("Importer");
				//				set3.add("Retailer");
				//				log.info("set3 =  "+set3);
				//				
				//				Set<String> set4 = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
				//				set4.add("Distributor");
				//				set4.add("Retailer");
				//				log.info("set4 =  "+set4);
				//
				//				log.info("usertypeName Data:  "+usertypeNameList);
				//				if(set1.containsAll(usertypeNameList)) {
				//					log.info("if roles are IDR");
				//					return map.get("Importer");	 
				//				}
				//				else if(set2.containsAll(usertypeNameList)) {	
				//					log.info("if roles are ID");
				//					return map.get("Importer");	
				//				}
				//				else if(set3.containsAll(usertypeNameList)){
				//					log.info("if roles are IR");
				//					return map.get("Importer");		
				//				}
				//				else if(set4.containsAll(usertypeNameList)) {
				//					log.info("if roles are DR");
				//					return map.get("Distributor");		
				//				}  
				//				else {
				//					log.info("if role set not found");
				//					return 0;
				//				}
				//			}
				//			else {
				//				log.info("if role length is 0 or less than that");
				//				return 0;
				//			}
			}
			return 0;
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

	public ResponseEntity<?> userRegistration(UserProfile userDetails)  {
		log.info("user details----::::   "+userDetails); 
		//Usertype usertypesData=usertypeRepo.findById(userDetails.getUsertypeId());
		/*
		 * if(usertypesData!=null) {
		 * if(!"Importer".equalsIgnoreCase(usertypesData.getUsertypeName())) { long
		 * data[]= {userDetails.getUsertypeId()}; userDetails.setRoles(data); } else {}
		 * }
		 */
		if(userDetails.getRoles()==null) {
			long data[]= {userDetails.getUsertypeId()}; userDetails.setRoles(data);
		}
		long rolesOutput=roleCheck(userDetails.getRoles());
		log.info("roles output:  "+rolesOutput);
		if(rolesOutput > 0) {
			log.info("primary usertypeId is:  "+rolesOutput);	
			User userData=new User();  
			userData.setCreatedOn(LocalDateTime.now());    
			userData.setModifiedOn(LocalDateTime.now());  
			userData.setPassword(userDetails.getPassword());
			userData.setUsername(userDetails.getUsername()); 
			userData.setCurrentStatus(UserStatus.INIT.getCode());
			Usertype userTypeData=new Usertype();
			userTypeData.setId(rolesOutput);  
			userData.setUsertype(userTypeData);  
			User userOutput=userRepo.save(userData);    
			if(userOutput!=null) {
				int rolesAddStatus=saveRoles(userDetails.getRoles(),userOutput.getId());
				log.info("role addition status:  "+rolesAddStatus); 
				long userid=userOutput.getId();
				log.info("userid= "+userid);
				User users=new User();
				users.setId(userid);
				userDetails.setUser(userData);
				userDetails.setCreatedOn(LocalDateTime.now());
				userDetails.setModifiedOn(LocalDateTime.now()); 
				if(userDetails.getAuthorityEmail()==null) {
				if(userDetails.getType()==0){
					String displayName=userDetails.getFirstName()+" "+userDetails.getMiddleName()+" "+userDetails.getLastName();
					userDetails.setDisplayName(displayName);
				}
				else {
					String displayName=userDetails.getCompanyName();      
					userDetails.setDisplayName(displayName);	  
				}
				}
				else {
					List<SystemConfigListDb> asTypeList=systemConfigRepo.getByTag("AS_TYPE");
						for(SystemConfigListDb asType:asTypeList) {
							if(userDetails.getType()==asType.getValue()) {
								userDetails.setDisplayName(asType.getInterp());
							}
						}
				}
				UserProfile output=userProfileRepo.save(userDetails);			 
				if(output!=null) {
					User dataForProfile=new User();
					dataForProfile.setId(userOutput.getId());
					String phoneOtp=otpService.phoneOtp(output.getPhoneNo());
					output.setPhoneOtp(phoneOtp);
					userProfileRepo.save(output);
					int outputGot=saveUserSecurityquestion(output);	
					log.info("user security question output:  "+outputGot);
					EmailDetails details=new EmailDetails();
					details.setFromEmail(fromEmail); 
					details.setToEmail(output.getEmail());
					details.setSubject("Email verification otp");
					String emailOtp=randomDigits.getNumericString(6);
					details.setMsgBody("your otp is "+emailOtp );
					log.info("from email:  "+fromEmail);
					boolean notificationStatus =emailUtils.saveNotification("REG_VERIFY_OTP_EMAIL_MSG", output, 0,
							"User Registration", "Registration", userOutput.getUsername(), "Email verification otp",emailOtp);
					log.info("notification save:  "+notificationStatus);
					output.setEmailOtp(emailOtp);                    
					userProfileRepo.save(output);	
					userOutput.setPreviousStatus(UserStatus.INIT.getCode());
					userOutput.setCurrentStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
					userRepo.save(userOutput); 
					OtpResponse response=new OtpResponse();
					response.setStatusCode(200);  
					response.setUserId(userOutput.getId());    
					response.setResponse("The text and and an e-mail with OTP details has been sent to your registered Phone Number and E-Mail ID");
					log.info("response:   "+response);
					return new ResponseEntity<>(response,HttpStatus.OK);
				} 
				else { 
					HttpResponse response=new HttpResponse();
					response.setStatusCode(409);    
					response.setResponse("user profile data not added");
					return new ResponseEntity<>(response,HttpStatus.OK);
				}
			}
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(409); 
				response.setResponse("user data not added");
				return new ResponseEntity<>(response,HttpStatus.OK);		
			}
		}
		else {    


			HttpResponse response=new HttpResponse();
			response.setStatusCode(409); 
			response.setResponse("please select correct roles");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}


	public ResponseEntity<?> UpdateOtpStatus(Otp  otp)
	{                                
		try { 
			log.info("inside validate otp ");
			log.info("otp data:  "+otp);      
			User output=userRepo.findById(otp.getUserid());
			if(output!=null) {

				if(output.getPreviousStatus()==UserStatus.APPROVED.getCode()) {

					UserTemporarydetails details=userTemporarydetailsRepo.findByUserDetails_id(output.getId());
					if(otp.getEmailOtp().equals(details.getEmailIdOtp()) && otp.getPhoneOtp().equals(details.getMobileOtp())) {      				  
						output.setCurrentStatus(UserStatus.PENDING_ADMIN_APPROVAL.getCode());
						output.setPreviousStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
						log.info("going to save user status");  
						userRepo.save(output);   
						log.info("user status is saved");
						UserProfile userProfileData=new UserProfile();
						userProfileData=output.getUserProfile();
						userProfileData.setPhoneNo(details.getMobileNo());
						userProfileData.setEmail(details.getEmailId());
						log.info("now going to save email and phone data in profile data");
						userProfileRepo.save(userProfileData);
						log.info("user profile data now save");
						HttpResponse response=new HttpResponse(); 
						response.setStatusCode(200);  
						response.setResponse("Your OTP is verified! ");
						return new ResponseEntity<>(response,HttpStatus.OK);	
					}
					else {
						log.info("if otp are incorrect");
						HttpResponse response=new HttpResponse();
						response.setStatusCode(409);  
						response.setResponse("Otp failed to verify"); 
						return new ResponseEntity<>(response,HttpStatus.OK);	  
					}
				} else {

					log.info("user profile data from db:  "+output); 
					if(otp.getEmailOtp().equals(output.getUserProfile().getEmailOtp()) && otp.getPhoneOtp().equals(output.getUserProfile().getPhoneOtp())) {      
						log.info("if otp are correct");
						HttpResponse response=new HttpResponse(); 
						User user=userRepo.findById(output.getId());
						/*
						 * if(user.getCurrentStatus()==UserStatus.APPROVED.getCode()) {
						 * 
						 * }
						 */
						user.setPreviousStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
						user.setCurrentStatus(UserStatus.PENDING_ADMIN_APPROVAL.getCode());     
						User output2=userRepo.save(user); 

						EmailDetails details=new EmailDetails(); details.setFromEmail(fromEmail);
						details.setToEmail(output2.getUserProfile().getEmail());
						details.setSubject("Email verification otp");
						details.setMsgBody("You are sucessfully registered, Your username is "+user.
								getUsername()+" and password is "+user.getPassword()+" ");
						log.info("from email:  "+fromEmail); //boolean
						//emailOtpStatus=otpService.emailOtp(details);
						/*
						 * boolean notificationStatus1=emailUtils.saveNotification(
						 * "REG_NOTIFY_CEIR_ADMIN_TO_VERIFY_USER", output2.getUserProfile(),0,
						 * "Registration", "notify admin to user saved and validated",
						 * output2.getUsername(),"User Registration Alert for approval  on CEIR Portal",
						 * ""); log.info("notification save for ceir admin:  "+notificationStatus1);
						 */
						
						boolean notificationStatus2=emailUtils.saveNotification("REG_WAIT_USER_FOR_APPROV_STATUS", output2.getUserProfile(),
								0, "Registration", "user phone and email details validated", output2.getUsername(),"CEIR Portal Registration Alert","");
						log.info("notification save:  "+notificationStatus2);

						response.setStatusCode(200);  
						response.setResponse("Your OTP is verified! The form has been submitted for approval. You will receive an intimation on your registered e-mail with the approval status within 2 to 3 working days");
						return new ResponseEntity<>(response,HttpStatus.OK);	
					}  
					else { 
						log.info("if otp are incorrect");
						HttpResponse response=new HttpResponse();
						response.setStatusCode(409);  
						response.setResponse("Otp failed to verify"); 
						return new ResponseEntity<>(response,HttpStatus.OK);	
					}
				}
			}
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(409);
				response.setResponse("user id is wrong");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409); 
			response.setResponse("Oops something wrong happened");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}

	public ResponseEntity<?> resendOtp(int userId) {   
		log.info("inside resend otp controller"); 
		UserProfile profile=userProfileRepo.findByUser_Id(userId);
		String smsOtp=otpService.phoneOtp(profile.getPhoneNo());
		EmailDetails details=new EmailDetails();
		details.setFromEmail(fromEmail); 
		details.setToEmail(profile.getEmail());
		details.setSubject("Email verification otp");
		String emailOtp=randomDigits.getNumericString(6);
		details.setMsgBody("your otp is "+emailOtp );
		log.info("from email:  "+fromEmail);                   
		boolean emailOtpStatus=otpService.emailOtp(details);
		log.info("email otp sending status :   "+emailOtpStatus);
		profile.setEmailOtp(emailOtp);
		profile.setPhoneOtp(smsOtp);
		UserProfile output=userProfileRepo.save(profile);
		if(output !=null) {
			HttpResponse response=new HttpResponse(); 
			User user=userRepo.findById(output.getUser().getId());
			userRepo.save(user);   
			response.setStatusCode(200);  
			response.setResponse("A text message and e-mail with OTP has been sent");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		} 
		else {
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409);
			response.setResponse("Otp failed to resend");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}

	}

	public ResponseEntity<?> changePassword(ChangePassword password){
		try {
			log.info("inside change password controller");
			log.info("ChangePassword data from form: "+password);
			User user=userRepo.findById(password.getUserid());
			if(password.getOldPassword().equals(user.getPassword())) {
				user.setPassword(password.getPassword());
				User output=userRepo.save(user);
				if(output!=null) {
					boolean notificationStatus=emailUtils.saveNotification("PRO_CHANGE_PASSWORD_BY_USER", output.getUserProfile(), 0, "Profile","change Password", output.getPassword(), "Transaction Alert from CEIR Portal", "");	
					log.info("notification save: "+notificationStatus);
					HttpResponse response=new HttpResponse();
					response.setStatusCode(200);
					response.setResponse("User password is changed");
					log.info("exit from change password");
					return new ResponseEntity<>(response,HttpStatus.OK);	
				}
				else {
					HttpResponse response=new HttpResponse();
					response.setStatusCode(500);
					response.setResponse("Failed to change password");
					log.info("exit from change password");   
					return new ResponseEntity<>(response,HttpStatus.OK);
				}
			} 
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(204);
				response.setResponse("Kindly enter the correct password");
				log.info("exit from change password");  
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


	public ResponseEntity<?> updateUserStatus(UserStatusRequest userStatus){
		log.info("inside  update User status  controller");  
		log.info(" userStatus data:  "+userStatus);      
		log.info("get user  data by username below");  
		User user=userRepo.findById(userStatus.getUserId());
		if(user!=null) { 
			Integer userStatus2 = UserStatus.getUserStatusByDesc(userStatus.getStatus()).getCode();
			user.setPreviousStatus(user.getCurrentStatus()); 
			user.setCurrentStatus(userStatus2);              
			User output=userRepo.save(user); 
			log.info("user data after update the status: "+output);
			if(output!=null) {
				String tag="";
				if(output.getCurrentStatus()==UserStatus.APPROVED.getCode()) {
					tag="PRO_ENABLE_ACC_BY_USER";
					log.info("if user status is approved");
				}
				else if(output.getCurrentStatus()==UserStatus.DISABLE.getCode()) {
					tag="PRO_DISABLE_ACC_BY_USER";
					log.info("if user status is disable");
				}
				else if(output.getCurrentStatus()==UserStatus.DEACTIVATE.getCode()) {
					tag="PRO_DEACTIVATE_ACC_BY_USER";
					log.info("if user status is deactivate");
				}
				else { 
					tag="";
					log.info("current user status id "+ output.getCurrentStatus() );
				}

				boolean notificationStatus=emailUtils.saveNotification(tag, output.getUserProfile(),0, "Profile","update user status", output.getUsername(), "Transaction Alert from CEIR Portal", "");
				log.info("notification save: "+notificationStatus);
				HttpResponse response=new HttpResponse();
				response.setStatusCode(200);   
				response.setResponse("User status has been changed");
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(500); 
				response.setResponse("User status failed to change");
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			} 
		}    
		else { 
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409);
			response.setResponse("User status fail to change"); 
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

			if(user!=null) {       
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
					if(user.getType()==asType.getValue()) {
						user.setAsTypeName(asType.getInterp());
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


	public ResponseEntity<?> updateProfile(UserProfile userProfile){
		try {
			log.info("inside into update user profile");     
			log.info("user profile data:  :  "+userProfile);    

			UserProfile userProfileData=userProfileRepo.findById(userProfile.getId());
			if(userProfile.getPassword().equals(userProfileData.getUser().getPassword())) {
				log.info("user profile data by id:  "+userProfileData); 
				long mainRole=roleCheck(userProfile.getRoles()); 
				if(mainRole>0) {  
					User userData=userRepo.findByUserProfile_Id(userProfile.getId());
					Usertype usertype=new Usertype();
					log.info("going to update user main role = "+mainRole);
					usertype.setId(mainRole);
					userData.setUsertype(usertype);  

					User UserOutput=userRepo.save(userData);   

					log.info("UserOutput:  "+UserOutput);



					if(!userProfile.getPhoneNo().equals(userProfileData.getPhoneNo()) || !userProfile.getEmail().equals(userProfileData.getEmail())) {
						UserTemporarydetails details=new UserTemporarydetails();
						String phoneOtp=otpService.phoneOtp(userProfile.getPhoneNo());
						details.setMobileOtp(phoneOtp);
						EmailDetails eDetails=new EmailDetails();
						eDetails.setFromEmail(fromEmail); 
						eDetails.setToEmail(userProfile.getEmail());
						String subject="One Time Password (OTP) for your online registration  on CEIR Portal";
						eDetails.setSubject("Email verification otp");
						String emailOtpData=randomDigits.getNumericString(6);
						eDetails.setMsgBody("your otp is "+emailOtpData );
						log.info("from email:  "+fromEmail);
						//boolean emailOtpStatus=otpService.emailOtp(eDetails);
						userProfile.setUser(UserOutput); 
						boolean notificationStatus=emailUtils.saveNotification("REG_VERIFY_OTP_EMAIL_MSG", userProfile, 0, "Profile","Edit Profile", UserOutput.getUsername(), subject, emailOtpData);
						log.info("notification save:  "+notificationStatus);
						details.setEmailIdOtp(emailOtpData);

						details.setMobileNo(userProfile.getPhoneNo()); 
						details.setEmailId(userProfile.getEmail()); 
						details.setUserDetails(UserOutput);  

						UserTemporarydetails dataByProfileId=userTemporarydetailsRepo.findByUserDetails_id(UserOutput.getId());
						log.info("temporary email and phone no profile details by user profile table id "+userProfile.getId());  

						if(dataByProfileId!=null) {
							details.setId(dataByProfileId.getId());
						}

						log.info("going to add data to user profile temporary data table");
						userTemporarydetailsRepo.save(details);
						log.info("after adding data to temporaray table");
						userData.setPreviousStatus(UserStatus.APPROVED.getCode());
						userData.setCurrentStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
						userRepo.save(userData);  
						log.info("user data changed");

					}
					userProfile.setCompanyName(userProfileData.getCompanyName());
					userProfile.setVatNo(userProfileData.getVatNo());
					userProfile.setVatStatus(userProfileData.getVatStatus());

					User userForProfile=new User();
					userForProfile.setId(UserOutput.getId());
					userForProfile.setUsername(UserOutput.getUsername());
					userForProfile.setCreatedOn(UserOutput.getCreatedOn());
					userForProfile.setModifiedOn(UserOutput.getModifiedOn());
					userForProfile.setPassword(UserOutput.getPassword());
					userForProfile.setCurrentStatus(UserOutput.getCurrentStatus());
					userForProfile.setPreviousStatus(UserOutput.getPreviousStatus());
					userForProfile.setUsertype(usertype);
					userProfile.setUser(userForProfile);      
					userProfile.setPassportNo(userProfileData.getPassportNo());
					userProfile.setCreatedOn(userProfileData.getCreatedOn());
					userProfile.setModifiedOn(userProfileData.getModifiedOn());
					userProfile.setPhoneNo(userProfileData.getPhoneNo());
					userProfile.setEmail(userProfileData.getEmail());
					userProfile.setPhoneOtp(userProfileData.getPhoneOtp());
					userProfile.setEmailOtp(userProfileData.getEmailOtp());
					log.info("userProfile data going to update: "+userProfile);
					log.info("user id on profile:  "+userProfile.getUser().toString());

					log.info("now going to save user profile data");
					UserProfile output=userProfileRepo.save(userProfile);
					log.info("user profile data is save");
					if(output!=null) { 

						int rolesOutput=updateRoles(userProfile.getRoles(), output.getUser().getId());
						log.info("user role update output:  "+rolesOutput);
						log.info("if user profile table is update ");
						log.info("going to update user  questions and answer");
						int i=updateUserSecurityquestion(userProfile);
						log.info("question output:  "+i); 
						UpdateProfileResponse response=new UpdateProfileResponse();      
						response.setStatusCode(200);   
						if(userData.getCurrentStatus()==1)
						{
							response.setResponse("The text and and an e-mail with OTP details has been sent to your new registered Phone Number and E-Mail ID");	
						}
						else {
							response.setResponse("User profile data succesfully update"); 
						}

						response.setUserstatus(UserStatus.getUserStatusByCode(userData.getCurrentStatus()).getDescription());
						response.setUserId(output.getUser().getId());
						//response.setUserstatus(userstatus);       
						log.info("response send to user:  "+response); 
						return new ResponseEntity<>(response,HttpStatus.OK);	
					}                 
					else {
						log.info("user profile to update");
						HttpResponse response=new HttpResponse();
						response.setStatusCode(204);   
						response.setResponse("User profile data fail to update"); 
						log.info("response send to user:  "+response);
						return new ResponseEntity<>(response,HttpStatus.OK);	
					}
				}
				else {
					log.info("user profile to update");
					HttpResponse response=new HttpResponse();
					response.setStatusCode(204);   
					response.setResponse("Please select correct role"); 
					log.info("response send to user:  "+response);
					return new ResponseEntity<>(response,HttpStatus.OK);	
				}
			}
			else { 
				log.info("user profile to update");
				HttpResponse response=new HttpResponse();
				response.setStatusCode(401);      
				response.setResponse("Please enter correct password"); 
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}
		catch(Exception e) {
			log.info("if exception occur");
			log.info(e.getMessage());
			e.printStackTrace();
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409); 
			response.setResponse("Oops something wrong happened");
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}

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

	public ResponseEntity<?> profileDataById(long id){
		try { 
			log.info("inside into edit profile");     
			log.info("user id:  "+id);    
			log.info("get user profile data by id below");
			UserProfile user=userProfileRepo.findById(id);
			if(user!=null) {       
				List<Long> rolesId=new ArrayList<Long>();
				for(Userrole userRoles:user.getUser().getUserRole()) {
					rolesId.add(userRoles.getUsertypeData().getId());
				}     
				log.info("roles Ids :  "+rolesId);
				long[] arr = new long[rolesId.size()]; 
				arr = Longs.toArray(rolesId); 
				user.setRoles(arr); 
				List<SystemConfigListDb> asTypeList=systemConfigRepo.getByTag("AS_TYPE");

					for(SystemConfigListDb asType:asTypeList) {
						if(user.getType()==asType.getValue()) {
							user.setAsTypeName(asType.getInterp());
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
			log.info("exception occur");
			e.printStackTrace();
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
			User user=userRepo.findById(userStatus.getUserId());
			if(user!=null) {
				user.setRemark(userStatus.getRemark());
				user.setPreviousStatus(user.getCurrentStatus());
				user.setCurrentStatus(UserStatus.getUserStatusByDesc(userStatus.getStatus()).getCode());

				log.info("user data to update:  "+user);
				User output=userRepo.save(user);
				log.info("output got:  "+output);
				log.info("user data:  "+output);
				if(output!=null) {
					String tag="";
					String subject="";
					String status="";
					if(output.getCurrentStatus()==UserStatus.APPROVED.getCode()) {
						tag="REG_ACCEPT_BY_CEIR_ADMIN";
						subject="CEIR Admin accept the user";
						status="Approve";
					}
					else if(output.getCurrentStatus()==UserStatus.REJECTED.getCode()) {
						tag="REG_REJECT_BY_CEIR_ADMIN";
						subject="CEIR Admin reject the user";
						status="Reject";
					}
					else {}
					boolean emailStatus=emailUtils.saveNotification(tag, output.getUserProfile(), userStatus.getFeatureId(), "Registration Request",status, output.getUsername(), subject,"");
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
		if("REG_ACCEPT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag())){
			emailBody=emailBody.replaceAll("<First name>", profile.getFirstName());
			emailBody=emailBody.replaceAll("<userID>", profile.getUser().getUsername());
			return emailBody;
		}
		else if("REG_REJECT_BY_CEIR_ADMIN".equals(msgConfigDb.getTag())){
			emailBody=emailBody.replaceAll("<First name>", profile.getFirstName());
			emailBody=emailBody.replaceAll("<userID>", profile.getUser().getUsername());
			emailBody=emailBody.replaceAll("<Reason>", profile.getUser().getRemark());
			return emailBody;
		}
		else  if("REG_VERIFY_OTP_EMAIL_MSG".equals(msgConfigDb.getTag())){
			emailBody=emailBody.replaceAll("<otp>", otp);
			return emailBody; 
		}
		else if("PRO_CHANGE_PASSWORD_BY_USER".equals(msgConfigDb.getTag()) || "PRO_DEACTIVATE_ACC_BY_USER".equals(msgConfigDb.getTag()) ||
				"PRO_DISABLE_ACC_BY_USER".equals(msgConfigDb.getTag()) || "PRO_ENABLE_ACC_BY_USER".equals(msgConfigDb.getTag())
				|| "REG_WAIT_USER_FOR_APPROV_STATUS".equals(msgConfigDb.getTag()) ){
			emailBody=emailBody.replaceAll("<First name>", profile.getFirstName());
           return emailBody;
		}
		


		else {return null;}
	}

}