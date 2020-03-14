package com.ceir.CeirCode.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
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
import com.ceir.CeirCode.model.StateMgmtDb;
import com.ceir.CeirCode.model.StatesInterpretationDb;
import com.ceir.CeirCode.model.SystemConfigurationDb;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserLogin;
import com.ceir.CeirCode.model.UserPasswordHistory;
import com.ceir.CeirCode.model.UserSecurityquestion;
import com.ceir.CeirCode.model.Userrole;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.model.constants.UserStatus;
import com.ceir.CeirCode.repo.LoginTrackingRepo;
import com.ceir.CeirCode.repo.UserPasswordHistoryRepo;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UserRoleRepo;
import com.ceir.CeirCode.repo.UserSecurityQuestionRepo;
import com.ceir.CeirCode.repoService.ReqHeaderRepoService;
import com.ceir.CeirCode.repoService.StateInterupRepoService;
import com.ceir.CeirCode.repoService.SystemConfigDbRepoService;
import com.ceir.CeirCode.repoService.SystemConfigurationDbRepoService;
import com.ceir.CeirCode.repoService.UserPassHistoryRepoService;
import com.ceir.CeirCode.response.tags.ProfileTags;
import com.ceir.CeirCode.response.tags.RegistrationTags;
import com.ceir.CeirCode.util.HttpResponse;
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
						String period=new String();
					
						SystemConfigurationDb systemConfigData=systemConfigurationDbRepoImpl.getDataByTag("GRACE_PERIOD_END_DATE");
						if(systemConfigData!=null) {
							period=featureService.currentPeriod(systemConfigData);			
							log.info("current period= "+period);
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
								UserData.getUserProfile().getOperatorTypeId(), UserData.getUserLanguage(),period,UserData.getCurrentStatus());  
						
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

	public ResponseEntity<?> sessionTracking(LoginTracking loginTracking){
		try {
			loginTracking.setCreatedOn(new Date());
			LoginTracking loginTrackingOutput=loginTrackingRepo.save(loginTracking);
			if(loginTrackingOutput!=null) {
				HttpResponse response=new HttpResponse("user session sucessfully added",200);
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}   
			else {
				HttpResponse response=new HttpResponse("user session failed to add",204);
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
			user=userRepo.findById(languageData.getUserId());
			if(user!=null) {
				user.setUserLanguage(languageData.getLanguage());
				userService.saveUserTrail(user, "Profile","change language",0);
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
				HttpResponse response=new HttpResponse(ProfileTags.SEC_QUES_MATCH.getMessage(),200,ProfileTags.SEC_QUES_MATCH.getTag()); 
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
