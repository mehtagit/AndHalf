package com.ceir.CeirCode.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.ChangeLanguage;
import com.ceir.CeirCode.model.ForgotPassword;
import com.ceir.CeirCode.model.LoginResponse;
import com.ceir.CeirCode.model.LoginTracking;
import com.ceir.CeirCode.model.NewPassword;
import com.ceir.CeirCode.model.RequestHeaders;
import com.ceir.CeirCode.model.Securityquestion;
import com.ceir.CeirCode.model.StatesInterpretationDb;
import com.ceir.CeirCode.model.SystemConfigListDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserLogin;
import com.ceir.CeirCode.model.UserPasswordHistory;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.model.UserSecurityquestion;
import com.ceir.CeirCode.model.Userrole;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.model.constants.ChannelType;
import com.ceir.CeirCode.model.constants.Features;
import com.ceir.CeirCode.model.constants.SubFeatures;
import com.ceir.CeirCode.model.constants.UserStatus;
import com.ceir.CeirCode.repo.LoginTrackingRepo;
import com.ceir.CeirCode.repo.SystemConfigDbListRepository;
import com.ceir.CeirCode.repo.UserPasswordHistoryRepo;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UserRoleRepo;
import com.ceir.CeirCode.repo.UserSecurityQuestionRepo;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.StateInterupRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.UserPassHistoryRepoService;
import com.ceir.CeirCode.response.UpdateProfileResponse;
import com.ceir.CeirCode.response.tags.ProfileTags;
import com.ceir.CeirCode.response.tags.RegistrationTags;
import com.ceir.CeirCode.util.GenerateRandomDigits;
import com.ceir.CeirCode.util.HttpResponse;
import com.ceir.CeirCode.util.NotificationUtil;
import com.ceir.CeirCode.util.Utility;
@Service 
public class LoginService 
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	UserRepo userRepo;
	@Autowired
	UserRoleRepo userRoleRepo;
	
	@Autowired
	LoginTrackingRepo loginTrackingRepo;
	@Autowired     
	UserSecurityQuestionRepo userSecurityQuestionRepo;
	@Autowired
	SystemConfigDbRepoService systemConfigurationRepo;
	@Autowired
	Utility utility;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReqHeaderRepoService headerService;
	
	@Autowired
	FeatureService featureService;
	
	@Autowired
	SystemConfigDbRepoService systemConfigurationDbRepoImpl;

	@Autowired
	StateMgmtServiceImpl stateMgmtServiceImpl;
	
	@Autowired
	StateInterupRepoService stateInterupRepoService;
	
	@Autowired
	UserPassHistoryRepoService userPassHistoryRepoImpl;

	@Autowired
	UserPasswordHistoryRepo userPasswordHistoryRepo;
	
	@Autowired
	SystemConfigDbListRepository systemConfigRepo;
	
	@Autowired
	NotificationUtil emailUtils;

	@Autowired
	OtpService otpService;

	@Autowired   
	GenerateRandomDigits randomDigits;

	public ResponseEntity<?> userLogin(UserLogin userLogin)
	{
		try 
		{
			
			log.info("inside login controller");
			log.info("user data for login:  "+userLogin);

			User user=new User();
			user.setUsername(userLogin.getUsername());
			user.setPassword(userLogin.getPassword());
			user.setCurrentStatus(UserStatus.APPROVED.getCode());
			Integer status2=UserStatus.DISABLE.getCode();
			User UserData=userRepo.findByUsername(user.getUsername());
			if(UserData!= null) 
			{ 
				RequestHeaders header=new RequestHeaders(userLogin.getUserAgent(),userLogin.getPublicIp(),UserData.getUsername());
				headerService.saveRequestHeader(header);
				userService.saveUserTrail(UserData, "Login","Login",0);
				if(UserData.getPassword().equals(user.getPassword()))
				{
					if(UserData.getCurrentStatus()==user.getCurrentStatus() || UserData.getCurrentStatus()==status2)
					{
						SystemConfigurationDb systemConfiguration=systemConfigurationRepo.getDataByTag("USER_PASS_EXPIRY_DAYS"); 
						log.info("system config data by tag: USER_PASS_EXPIRY_DAYS"+systemConfiguration.getValue());
						Integer days=0;
						if(systemConfiguration!=null) {
							 days=Integer.parseInt(systemConfiguration.getValue());		
						}
						log.info("days going to add"+days);
    					String dateToString=utility.convertlocalTimeToString(UserData.getPasswordDate()); 
						Date userPasswordDate=utility.stringToDate(dateToString); 
						Date currentDate=utility.currentOnlyDate();
						Date passwordExpiryDate=utility.addDaysInDate(days,userPasswordDate);
						log.info("current date: "+currentDate);
						log.info("userCreatedDate: "+userPasswordDate);
						log.info("expiry date: "+passwordExpiryDate);
						if(currentDate.after(passwordExpiryDate)) {
							LoginResponse response=new LoginResponse("Password is expire now",401,UserData.getId());
							return new ResponseEntity<>(response,HttpStatus.OK);					
						}
						else {
						LoginTracking loginTrack = new LoginTracking(1, UserData);
						loginTrackingRepo.save(loginTrack); 
						List<Usertype> userRoles=new ArrayList<Usertype>();   
						List<Userrole> userroleList=new ArrayList<Userrole>();
						userroleList=userRoleRepo.findByUserData_Id(UserData.getId());
						for(Userrole userRole:userroleList) 
						{
							userRoles.add(userRole.getUsertypeData());
						}
						Integer period;
					
						SystemConfigurationDb systemConfigData=systemConfigurationDbRepoImpl.getDataByTag("GRACE_PERIOD_END_DATE");
						String periodInterp=new String();
						if(systemConfigData!=null) {
							period=featureService.currentPeriod(systemConfigData);			
							log.info("current period= "+period);
							List<SystemConfigListDb> periodList=systemConfigRepo.getByTag("Period");
							for(SystemConfigListDb periodData:periodList) {
								Integer data=periodData.getValue();
								if(period==data) {
									periodInterp=periodData.getInterp();
								}
							}
						}

						StatesInterpretationDb stateInterup=stateInterupRepoService.getByFeatureIdAndState(8, UserData.getCurrentStatus());
						String status=new String();
						if(stateInterup.getInterp()!=null) {
							status=stateInterup.getInterp();  	
						}
						LoginResponse response=new LoginResponse("user credentials are correct",200,
								userRoles, UserData.getUsername(), UserData.getId(), UserData.getUserProfile().getFirstName(),
								UserData.getUsertype().getUsertypeName(), UserData.getUsertype().getId(), 
								status,UserData.getUserProfile().getOperatorTypeName(),
								UserData.getUserProfile().getOperatorTypeId(), UserData.getUserLanguage(),
								periodInterp,UserData.getCurrentStatus(),UserData.getUsertype().getSelfRegister());  
						
						log.info("login response:  "+response);
						return new ResponseEntity<>(response,HttpStatus.OK);
					}
					}
					else 
					{
						HttpResponse response=new HttpResponse(RegistrationTags.LOGIN_UNAUTHORIZED.getMessage(),204,RegistrationTags.LOGIN_UNAUTHORIZED.getTag());
						return new ResponseEntity<>(response,HttpStatus.OK);
					}
				}
				else 
				{
					HttpResponse response=new HttpResponse(RegistrationTags.LOGIN_WRONG_DETAILS.getMessage(),403,RegistrationTags.LOGIN_WRONG_DETAILS.getTag());
					return new ResponseEntity<>(response,HttpStatus.OK);
				}
			}                                                   
			else 
			{
				HttpResponse response=new HttpResponse(RegistrationTags.LOGIN_WRONG_DETAILS.getMessage(),403,RegistrationTags.LOGIN_WRONG_DETAILS.getTag());
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),409,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}
	
	
	
	
	
	public ResponseEntity<?> testUserLogin(UserLogin userLogin)
	{
		try 
		{
			
			log.info("inside login controller");
			log.info("user data for login:  "+userLogin);

			User user=new User();
			user.setUsername(userLogin.getUsername());
			user.setPassword(userLogin.getPassword());
			user.setCurrentStatus(UserStatus.APPROVED.getCode());
			Integer status2=UserStatus.DISABLE.getCode();
			User UserData=userRepo.findByUsername(user.getUsername());
			if(UserData!= null) 
			{ 
				//RequestHeaders header=new RequestHeaders(userLogin.getUserAgent(),userLogin.getPublicIp(),UserData.getUsername());
				//headerService.saveRequestHeader(header);
				//userService.saveUserTrail(UserData, "Login","Login",0);
				if(UserData.getCurrentStatus()==user.getCurrentStatus() || UserData.getCurrentStatus()==status2)
					{
						SystemConfigurationDb systemConfiguration=systemConfigurationRepo.getDataByTag("USER_PASS_EXPIRY_DAYS"); 
						log.info("system config data by tag: USER_PASS_EXPIRY_DAYS"+systemConfiguration.getValue());
						Integer days=0;
						if(systemConfiguration!=null) {
							 days=Integer.parseInt(systemConfiguration.getValue());		
						}
						log.info("days going to add"+days);
    					String dateToString=utility.convertlocalTimeToString(UserData.getPasswordDate()); 
						Date userPasswordDate=utility.stringToDate(dateToString); 
						Date currentDate=utility.currentOnlyDate();
						Date passwordExpiryDate=utility.addDaysInDate(days,userPasswordDate);
						log.info("current date: "+currentDate);
						log.info("userCreatedDate: "+userPasswordDate);
						log.info("expiry date: "+passwordExpiryDate);
						if(currentDate.after(passwordExpiryDate)) {
							LoginResponse response=new LoginResponse("Password is expire now",401,UserData.getId());
							return new ResponseEntity<>(response,HttpStatus.OK);					
						}
						else {
						LoginTracking loginTrack = new LoginTracking(1, UserData);
						loginTrackingRepo.save(loginTrack); 
						List<Usertype> userRoles=new ArrayList<Usertype>();   
						List<Userrole> userroleList=new ArrayList<Userrole>();
						userroleList=userRoleRepo.findByUserData_Id(UserData.getId());
						for(Userrole userRole:userroleList) 
						{
							userRoles.add(userRole.getUsertypeData());
						}
						Integer period;
					
						SystemConfigurationDb systemConfigData=systemConfigurationDbRepoImpl.getDataByTag("GRACE_PERIOD_END_DATE");
						String periodInterp=new String();
						if(systemConfigData!=null) {
							period=featureService.currentPeriod(systemConfigData);			
							log.info("current period= "+period);
							List<SystemConfigListDb> periodList=systemConfigRepo.getByTag("Period");
							for(SystemConfigListDb periodData:periodList) {
								Integer data=periodData.getValue();
								if(period==data) {
									periodInterp=periodData.getInterp();
								}
							}
						}

						StatesInterpretationDb stateInterup=stateInterupRepoService.getByFeatureIdAndState(8, UserData.getCurrentStatus());
						String status=new String();
						if(stateInterup.getInterp()!=null) {
							status=stateInterup.getInterp();  	
						}
						LoginResponse response=new LoginResponse("user credentials are correct",200,
								userRoles, UserData.getUsername(), UserData.getId(), UserData.getUserProfile().getFirstName(),
								UserData.getUsertype().getUsertypeName(), UserData.getUsertype().getId(), 
								status,UserData.getUserProfile().getOperatorTypeName(),
								UserData.getUserProfile().getOperatorTypeId(), UserData.getUserLanguage(),
								periodInterp,UserData.getCurrentStatus(),UserData.getPassword());  
						
						log.info("login response:  "+response);
						return new ResponseEntity<>(response,HttpStatus.OK);
					}
					}
					else 
					{
						HttpResponse response=new HttpResponse(RegistrationTags.LOGIN_UNAUTHORIZED.getMessage(),204,RegistrationTags.LOGIN_UNAUTHORIZED.getTag());
						return new ResponseEntity<>(response,HttpStatus.OK);
					}
				
			}                                                   
			else 
			{
				HttpResponse response=new HttpResponse(RegistrationTags.LOGIN_WRONG_DETAILS.getMessage(),403,RegistrationTags.LOGIN_WRONG_DETAILS.getTag());
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			HttpResponse response=new HttpResponse(RegistrationTags.COMMAN_FAIL_MSG.getMessage(),409,RegistrationTags.COMMAN_FAIL_MSG.getTag());
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}

	public ResponseEntity<?> sessionTracking(LoginTracking loginTracking){
		try {
			userService.saveUserTrail(loginTracking.getUserTrack(), "Logout","Logout",0);
			LoginTracking loginTrackingOutput=loginTrackingRepo.save(loginTracking);
			if(loginTrackingOutput!=null) {
				HttpResponse response=new HttpResponse("user session sucessfully added",200);
				log.info("exist from sessionTracking");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}   
			else {
				HttpResponse response=new HttpResponse("user session failed to add",204);
				log.info("exist from sessionTracking");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}  
		catch(Exception e) {
			e.printStackTrace();
			HttpResponse response=new HttpResponse("Oops something wrong happened",409);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}    
	}

	public ResponseEntity<?> changeLanguage(ChangeLanguage languageData)
	{
		try 
		{
			User user=new User();
			userService.saveUserTrail(languageData.getUserId(),languageData.getUsername(),
		languageData.getUserType(),languageData.getUserTypeId(),Features.Profile,SubFeatures.Change_Language,0);
			user=userRepo.findById(languageData.getUserId());
			if(user!=null) {
				User output=userRepo.save(user);
				if(output!=null) 
				{
					HttpResponse response=new HttpResponse("user language sucessfully update",200);
					return new ResponseEntity<>(response,HttpStatus.OK);	
				}   
				else 
				{
					HttpResponse response=new HttpResponse("user language fails to update",204);
					return new ResponseEntity<>(response,HttpStatus.OK);	
				}
			}
			else {
				HttpResponse response=new HttpResponse("user id is incorrect",204);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
		}  
		catch(Exception e) 
		{
			e.printStackTrace();
			HttpResponse response=new HttpResponse("Oops something wrong happened",409);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}    
	}

	public ResponseEntity<?> forgotPassword(ForgotPassword forgotPassword)
	{ 
		log.info("inside forgot controller");
		log.info("forgot password data:  "+forgotPassword);
		log.info("get userid data by username below");
		User userData=userRepo.findByUsername(forgotPassword.getUsername());
		log.info("userdata id by username= "+userData); 
		if(userData!=null)
		{
			log.info("now match user question and answer on UserSecurityquestion");
			userService.saveUserTrail(userData, "Login","forgot password",0);
			Securityquestion securityData=new Securityquestion();
			securityData.setId(forgotPassword.getQuestionId());
			UserSecurityquestion questionDetails=userSecurityQuestionRepo.findByUser_IdAndSecurityQuestion_IdAndAnswer(userData.getId(),securityData.getId(),forgotPassword.getAnswer());
			log.info("user question mapping data: "+questionDetails);
			if(questionDetails !=null) 
			{
				String phoneOtp=otpService.phoneOtp(userData.getUserProfile().getPhoneNo()); 
				String emailOtpData=randomDigits.getNumericString(6);
				boolean notificationStatus=emailUtils.saveNotification("PRO_VERIFY_OTP_EMAIL_MSG", userData.getUserProfile(), 0,
						"Forgot Password", "Forgot Password", userData.getUsername(),
						"Forgot Password Notification "+userData.getUsername(),emailOtpData,
						ChannelType.EMAIL,"users",0); 
				log.info("notification save:  "+notificationStatus);
				boolean notificationStatusForSms=emailUtils.saveNotification("PRO_VERIFY_OTP__MSG", userData.getUserProfile(), 0,
						"User Login", "forgot Password", userData.getUsername(),
						userData.getUserProfile().getFirstName(),phoneOtp,ChannelType.SMS,"users",0);
				log.info("notificationStatusForSms save:  "+notificationStatusForSms);
				userData.setPreviousStatus(UserStatus.APPROVED.getCode());
				userData.setCurrentStatus(UserStatus.OTP_VERIFICATION_PENDING.getCode());
				UserProfile profile=userData.getUserProfile();
				profile.setUser(userData);
				profile.setEmailOtp(emailOtpData);
				profile.setPhoneOtp(phoneOtp);
				userData.setUserProfile(profile);
				userRepo.save(userData); log.info("user data changed"); 
				//HttpResponse response2=new HttpResponse(ProfileTags.PRO_SUCESS_OTPMSG.getMessage(),200,ProfileTags.PRO_SUCESS_OTPMSG.getTag()); 
				UpdateProfileResponse response=new UpdateProfileResponse(ProfileTags.PRO_SUCESS_OTPMSG.getMessage(),200,UserStatus.getUserStatusByCode(userData.getCurrentStatus()).getDescription()
						,userData.getId(),ProfileTags.PRO_SUCESS_OTPMSG.getTag());
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}          
			else 
			{ 
				HttpResponse response=new HttpResponse(ProfileTags.SECU_QUEST_NOT_MATCH.getMessage(),409,ProfileTags.SECU_QUEST_NOT_MATCH.getTag());
				log.info("response send to user:  "+response);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}    
		}
		else 
		{
			HttpResponse response=new HttpResponse(ProfileTags.WRONG_USERID.getMessage(),401,ProfileTags.WRONG_USERID.getTag());
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}

	public ResponseEntity<?> updateNewUserPassword(NewPassword password){
		log.info("inside set update new password controller");  
		log.info(" password data:  "+password); 
		log.info("get user  data by username below");
		User user=userRepo.findByUsername(password.getUsername());
		if(user!=null) 
		{
//			userService.saveUserTrail(user, "Login","update new password",0);
//			user.setPassword(password.getPassword());
//			userRepo.save(user);
//			HttpResponse response=new HttpResponse(ProfileTags.NEW_PASS_SUC.getMessage(),200,ProfileTags.NEW_PASS_SUC.getTag());
//			log.info("response send to user:  "+response);
//			return new ResponseEntity<>(response,HttpStatus.OK);	
			
			boolean passwordExist=userPassHistoryRepoImpl.passwordExist(password.getPassword(), user.getId());
			if(passwordExist==true) {
				log.info("if this password exist");
				HttpResponse response=new HttpResponse(ProfileTags.PRO_CPASS_LAST_3PASS_ERROR.getMessage(),204,
						ProfileTags.PRO_CPASS_LAST_3PASS_ERROR.getTag());
				log.info("exit from change password");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
			else {
				log.info("if this password does not exist");
				long count=userPassHistoryRepoImpl.countByUserId(user.getId());
				log.info("password count: "+count);
				if(count!=0) {
					if(count>=3) {
						log.info("going to delete password history greater than 3");
						UserPasswordHistory passHistory=userPassHistoryRepoImpl.getPasswordHistory(user.getId());
						userPasswordHistoryRepo.deleteById(passHistory.getId());
						user.setPassword(password.getPassword());
						return userService.setNewPassword(user);
					}
					else {
						log.info("if password history less than 3");
						user.setPassword(password.getPassword());
						return userService.setNewPassword(user);	
					}
				}
				else {
					user.setPassword(password.getPassword());
					return userService.setNewPassword(user);	
				}

			}
		} 
		else 
		{
			HttpResponse response=new HttpResponse(ProfileTags.NEW_PASS_FAIL.getMessage(),409,ProfileTags.NEW_PASS_FAIL.getTag());
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}
}
