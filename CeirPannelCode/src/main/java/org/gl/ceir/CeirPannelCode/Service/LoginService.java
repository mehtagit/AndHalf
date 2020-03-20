package org.gl.ceir.CeirPannelCode.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.gl.ceir.CeirPannelCode.Feignclient.FeatureFeignImpl;
import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Feignclient.UserLoginFeignImpl;
import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.ChangeLanguage;
import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.Feature;
import org.gl.ceir.CeirPannelCode.Model.ForgotPassword;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.Tag;
import org.gl.ceir.CeirPannelCode.Model.User;
import org.gl.ceir.CeirPannelCode.Model.UserHeader;
import org.gl.ceir.CeirPannelCode.Response.LoginResponse;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class LoginService {
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	
	private final Logger log = LoggerFactory.getLogger(getClass());	
	@Autowired
	UserLoginFeignImpl userLoginFeignImpl;
	@Autowired
	FeatureFeignImpl featureFeignImpl;

	@Autowired
	UserProfileFeignImpl userProfileFeignImpl;
	
	@Autowired
	RegistrationService registerService;
	
	
	public  ModelAndView loginPage(){
		log.info("inside login controller");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("login");
		log.info("exit from login controller");
		return mv;
	}

	public LoginResponse checkLogin(User user,HttpSession session,HttpServletRequest request) {
		log.info("check login controller ");
		UserHeader header=registerService.getUserHeaders(request);
		user.setUserAgent(header.getUserAgent());
		user.setPublicIp(header.getPublicIp());
		log.info("user data:  "+user);
        log.info("user agent=  "+user.getUserAgent() +" public ip of user: "+user.getPublicIp());		
		String validCaptcha=(String)session.getAttribute("captcha_security");
		log.info("captcha from session:  "+validCaptcha); 
		if(user.getCaptcha().equals(validCaptcha)) {
			log.info("if captcha match");
			LoginResponse response=new LoginResponse();
			response=userLoginFeignImpl.checkUser(user);
			log.info("login response:  "+response); 
			log.info("language = "+response.getUserLanguage());
			if(response.getStatusCode()==200) { 
				session.setAttribute("username", response.getUsername());
				session.setAttribute("userid", response.getUserId());
				session.setAttribute("usertypeList", response.getUserRoles());
				session.setAttribute("usertype", response.getPrimaryRole());
				session.setAttribute("name", response.getName());   
				session.setAttribute("userStatus", response.getStatus());
				session.setAttribute("userStatusValue", response.getStatusValue());
				session.setAttribute("usertypeId", response.getPrimaryRoleId());
				session.setAttribute("operatorTypeId", response.getOperatorTypeId());
				session.setAttribute("operatorTypeName", response.getOperatorTypeName());
				session.setAttribute("language",response.getUserLanguage()); 
				session.setAttribute("period", response.getPeriod());
				return response;      
			}       
			else {
				response.setResponse(response.getResponse());
				return response;
			}
		}
		else { 
			log.info("if captcha not match");
			LoginResponse response=new LoginResponse();
			response.setResponse("You have entered the wrong Captcha. Please enter the correct value");
			response.setTag("Wrong_captcha");
			return response; 
		}
	}
		
	public HttpResponse changeLanguage(String language,HttpSession session) {
		log.info("inside check change language controller ");
		log.info("language data:  "+language);
		Integer userID=(Integer)session.getAttribute("userid");
		log.info("userID from session: " +userID);
		ChangeLanguage languageData=new ChangeLanguage(userID,language);
		HttpResponse response=userLoginFeignImpl.changeUserLanguage(languageData);
		if(response!=null) {
			log.info("response from controller: "+response);
			session.removeAttribute("updatedLanguage");
			session.setAttribute("updatedLanguage", language);
		}
		log.info("exit from language controller ");
		return response;
	}

	public void sessionRemoveCode(Integer userid,HttpSession session) {
		
		log.info("userid from session: "+userid);
		if(userid!=null) {
			HttpResponse response=new HttpResponse();
			response=userLoginFeignImpl.sessionTracking(userid);
			log.info("response got: "+response);
		} 
		session.removeAttribute("username");
		session.removeAttribute("userid"); 
		session.removeAttribute("usertypeList");
		session.removeAttribute("usertype");
		session.removeAttribute("name");
		session.removeAttribute("userStatus");
		session.invalidate(); 
	}
	public ModelAndView logout(HttpSession session){
		log.info("inside logout controller");
		Integer userid=(Integer)session.getAttribute("userid");
		if(userid!=null) {
			userLoginFeignImpl.sessionTracking(userid);
		}
		sessionRemoveCode( userid, session);
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg","you have been logged out successfully");
		mv.setViewName("login");
		log.info("exit logout controller");
		return mv;
}
	
	public void  indexPageSessionOut(HttpSession session,HttpServletResponse http){
		log.info("inside index controller");
		Integer userid=(Integer)session.getAttribute("userid");
		sessionRemoveCode( userid, session);
		log.info("exit index controller");
		Tag tagData=new Tag("link_dmc_portal");
		Dropdown dropdown = feignCleintImplementation.dataByTag(tagData);
	    try {
			http.sendRedirect(dropdown.getValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "redirect:.../"+dropdown.getValue();
	}
	
	public void redirectToHome(HttpServletResponse http){
		log.info("inside index controller");
		log.info("exit index controller");
		Tag tagData=new Tag("link_dmc_portal");
		Dropdown dropdown = feignCleintImplementation.dataByTag(tagData);
		try {
		http.sendRedirect(dropdown.getValue());
		} catch (IOException e) {
		e.printStackTrace();
		}
		}

	public ModelAndView dashBoard(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		log.info("importer dashboard entry point..");
		String username=(String)session.getAttribute("username");
		String status=(String)session.getAttribute("userStatus");
		try {
		if(username.trim()!=null) {
			log.info("username from session:  "+username);
			log.info("user status from session :   "+status); 
			Integer userId=(Integer)session.getAttribute("userid");
			List<Feature> features=new ArrayList<Feature>();
			if(userId!=0) {
				features=featureFeignImpl.featureList(userId);	
			}

			mv.setViewName("dashboard");
			mv.addObject("features", features);
			log.info("importer dashboard exit point..");
			return mv;   
		}
		else {
			mv.addObject("msg","Please Login first");
			mv.setViewName("login"); 
			return mv;  
		}
		}
		catch(Exception e) {
			mv.addObject("msg","Please Login first");
			mv.setViewName("login"); 
			return mv; 	
		}
	}

	public HttpResponse forgotPasswordRequest(ForgotPassword password) {
		log.info("inside forgot password controller");
		log.info("password data is:  "+password);
		HttpResponse response=new HttpResponse();           
		response=userLoginFeignImpl.ForgotPassword(password);
		return response;
	}  

	public HttpResponse updateNewPassword(Password password) {
		log.info("inside update new password controller");
		log.info("password data is :  "+password);
		if(password.getPassword().equals(password.getConfirmPassword())) {
			HttpResponse response=new HttpResponse();            
			response=userLoginFeignImpl.updateNewPassword(password);
			return response;
		}
		else {
			HttpResponse response=new HttpResponse(); 
			response.setResponse("Both Passwords do the match");      
			return response;
		}

	}
	
	public HttpResponse changeExpirePassword(Password password) {
		log.info("inside change password controller");
		log.info("password data is :  "+password);                 
		if(password.getPassword().equals(password.getConfirmPassword())) {
			HttpResponse response=new HttpResponse();             
			response=userProfileFeignImpl.updateExpirePassword(password);
			log.info("response got:  "+response);
			return response; 	
		}
		else {    
			HttpResponse response=new HttpResponse();             
            response.setResponse("Both Passwords do the match");
			return response; 
		}
		  
	} 
	
}