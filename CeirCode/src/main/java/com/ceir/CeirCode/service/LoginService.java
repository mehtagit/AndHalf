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
import com.ceir.CeirCode.model.Securityquestion;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserLogin;
import com.ceir.CeirCode.model.UserSecurityquestion;
import com.ceir.CeirCode.model.Userrole;
import com.ceir.CeirCode.model.Usertype;
import com.ceir.CeirCode.model.constants.UserStatus;
import com.ceir.CeirCode.repo.LoginTrackingRepo;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UserRoleRepo;
import com.ceir.CeirCode.repo.UserSecurityQuestionRepo;
import com.ceir.CeirCode.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service 
public class LoginService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	UserRoleRepo userRoleRepo;
	private final Logger log =
			LoggerFactory.getLogger(getClass());
	@Autowired
	LoginTrackingRepo loginTrackingRepo;
	@Autowired     
	UserSecurityQuestionRepo userSecurityQuestionRepo;
	public ResponseEntity<?> userLogin(UserLogin userLogin){
		try {
			log.info("inside login controller");
			log.info("user data for login:  "+userLogin);
			User user=new User();
			user.setUsername(userLogin.getUsername());
			user.setPassword(userLogin.getPassword());
			user.setCurrentStatus(UserStatus.APPROVED.getCode());
			Integer status2=UserStatus.DISABLE.getCode();
			User UserData=userRepo.findByUsername(user.getUsername());
			if(UserData!= null) { 
				if(UserData.getPassword().equals(user.getPassword())){
				if(UserData.getCurrentStatus()==user.getCurrentStatus() || UserData.getCurrentStatus()==status2){
					log.info("user data : "+UserData);  
					LoginTracking loginTrack=new LoginTracking(1,UserData,new Date());
					log.info("loginTrack to save:"+loginTrack+"and user data: "+UserData.toString());
					LoginTracking trackOutput=loginTrackingRepo.save(loginTrack); 
					log.info("trackOutput:  "+ trackOutput);   
					LoginResponse response=new LoginResponse();  
					response.setStatusCode(200);     
					response.setResponse("user credentials are correct");
					response.setUsername(UserData.getUsername());
					response.setUserId(UserData.getId()); 
					response.setName(UserData.getUserProfile().getFirstName());
					response.setPrimaryRole(UserData.getUsertype().getUsertypeName());
					response.setPrimaryRoleId(UserData.getUsertype().getId());
					response.setStatus(UserStatus.getUserStatusByCode(UserData.getCurrentStatus()).getDescription());   
					response.setOperatorTypeId(UserData.getUserProfile().getOperatorTypeId());
					response.setOperatorTypeName(UserData.getUserProfile().getOperatorTypeName());
					response.setUserLanguage(UserData.getUserLanguage());
					List<Userrole> userroleList=new ArrayList<Userrole>();
					userroleList=userRoleRepo.findByUserData_Id(UserData.getId());
					List<Usertype> userRoles=new ArrayList<Usertype>();   
					
					for(Userrole userRole:userroleList) {
						userRoles.add(userRole.getUsertypeData());
					}
					response.setUserRoles(userRoles); 
					return new ResponseEntity<>(response,HttpStatus.OK);
				}
				else {
					HttpResponse response=new HttpResponse();
					response.setStatusCode(204);    
					response.setResponse("You are not allow to access this account");
					return new ResponseEntity<>(response,HttpStatus.OK);
				}
				}
				else {
					HttpResponse response=new HttpResponse();
					response.setStatusCode(403);    
					response.setResponse("Please enter the correct credentials");
					return new ResponseEntity<>(response,HttpStatus.OK);
				}
			}                                                   
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(403);    
				response.setResponse("Please enter the correct credentials");
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

	public ResponseEntity<?> sessionTracking(LoginTracking loginTracking){
		try {
			loginTracking.setCreatedOn(new Date());
			LoginTracking loginTrackingOutput=loginTrackingRepo.save(loginTracking);
			if(loginTrackingOutput!=null) {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(200);     
				response.setResponse("user session sucessfully added");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}   
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(204);     
				response.setResponse("user session failed to add");
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
	
	public ResponseEntity<?> changeLanguage(ChangeLanguage languageData){
		try {
             User user=new User();
             user=userRepo.findById(languageData.getUserId());
             if(user!=null) {
            	 user.setUserLanguage(languageData.getLanguage());
            	 User output=userRepo.save(user);
			if(output!=null) {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(200);     
				response.setResponse("user language sucessfully update");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}   
			else {
				HttpResponse response=new HttpResponse();
				response.setStatusCode(204);     
				response.setResponse("user language fails to update");
				return new ResponseEntity<>(response,HttpStatus.OK);	
			}
             }
             else {
            	 HttpResponse response=new HttpResponse();
 				response.setStatusCode(204);     
 				response.setResponse("user id is incorrect");
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
	
	
	
	

	public ResponseEntity<?> forgotPassword(ForgotPassword forgotPassword){ 
		log.info("inside forgot controller");
		log.info("forgot password data:  "+forgotPassword);
		log.info("get userid data by username below");
		User userData=userRepo.findByUsername(forgotPassword.getUsername());
		log.info("userdata id by username= "+userData); 
		if(userData!=null) {
		log.info("now match user question and answer on UserSecurityquestion");
		Securityquestion securityData=new Securityquestion();
		securityData.setId(forgotPassword.getQuestionId());
		UserSecurityquestion questionDetails=userSecurityQuestionRepo.findByUser_IdAndSecurityQuestion_IdAndAnswer(userData.getId(),securityData.getId(),forgotPassword.getAnswer());
		log.info("user question mapping data: "+questionDetails);
		if(questionDetails !=null) {
			HttpResponse response=new HttpResponse(); 
			response.setStatusCode(200);  
			response.setResponse("security question and answer match");
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}          
		else { 
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409);
			response.setResponse("Security question and answer did not match");
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}    }
		else {
			HttpResponse response=new HttpResponse();
			response.setStatusCode(401);  
			response.setResponse("Please enter correct User Id");
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}

	}

	public ResponseEntity<?> updateNewUserPassword(NewPassword password){
		log.info("inside set update new password controller");  
		log.info(" password data:  "+password); 
		log.info("get user  data by username below");
		User user=userRepo.findByUsername(password.getUsername());
		if(user!=null) {
			user.setPassword(password.getPassword());
			userRepo.save(user);
			HttpResponse response=new HttpResponse();
			response.setStatusCode(200);
			response.setResponse("User new password has been added");
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		} 
		else {
			HttpResponse response=new HttpResponse();
			response.setStatusCode(409);
			response.setResponse("User new password fail to update");
			log.info("response send to user:  "+response);
			return new ResponseEntity<>(response,HttpStatus.OK);	
		}
	}
}
