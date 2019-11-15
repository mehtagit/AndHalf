package org.gl.ceir.CeirPannelCode.Service;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.EditProfile;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.UserStatus;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	@Autowired
	UserProfileFeignImpl userProfileFeignImpl;
	
	private final Logger log = LoggerFactory.getLogger(getClass());	
	
	public HttpResponse changePassword(Password password,HttpSession session) {
		log.info("inside change password controller");
		log.info("password data is :  "+password);                 
		Integer userid=(Integer)session.getAttribute("userid");
		log.info("userid from session:  "+userid);
		password.setUserid(userid);      
		HttpResponse response=new HttpResponse();             
		response=userProfileFeignImpl.changePassword(password);
		return response;   
	} 
	
	public HttpResponse updateUSerStatus(UserStatus userStatus,HttpSession session) {
		log.info("inside update userStatus controller");
		Integer userid=(Integer)session.getAttribute("userid");
		log.info("userid from session:  "+userid);
		userStatus.setUserId(userid); 
		log.info("userStatus data is :  "+userStatus);
		HttpResponse response=new HttpResponse();             
		response=userProfileFeignImpl.updateUserStatus(userStatus);
		return response;  
	}               
	   
	public Registration editUserProfile(HttpSession session) {
		log.info("inside edit User Profile  controller");
		Integer userid=(Integer)session.getAttribute("userid");
		log.info("userid from session:  "+userid);
		Registration response=new Registration();             
		response=userProfileFeignImpl.editUserProfile(userid);
		return response;  
	}
	
	public HttpResponse updateProfile(Registration registration,HttpSession session) {
		log.info("inside update profile controller");
		log.info("profile data=  "+registration);
		HttpResponse response=new HttpResponse();   
		response=userProfileFeignImpl.updateUserProfile(registration);
		log.info("exit from update profile controller");
		return response;    
	}
	
	
	
}
