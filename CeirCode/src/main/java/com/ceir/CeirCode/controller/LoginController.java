package com.ceir.CeirCode.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.model.ChangeLanguage;
import com.ceir.CeirCode.model.ForgotPassword;
import com.ceir.CeirCode.model.LoginTracking;
import com.ceir.CeirCode.model.NewPassword;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserLogin;
import com.ceir.CeirCode.repoService.UserRepoService;
import com.ceir.CeirCode.service.LoginService;
import com.ceir.CeirCode.service.UserService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/Login")
public class LoginController{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	LoginService loginService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepoService userRepoService;
	
	@ApiOperation(value = "user Login", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/checkUser")     
	public ResponseEntity<?> userLogin(@RequestBody UserLogin user){
		return loginService.userLogin(user); 
	}  
	
	

	@ApiOperation(value = "user test Login", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/testLogin")     
	public ResponseEntity<?> testLogin(@RequestBody UserLogin user){
		return loginService.testUserLogin(user); 
	}

	@ApiOperation(value = "user session", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/sessionTracking/{userid}")     
	public ResponseEntity<?> sessionTracking(@PathVariable("userid") long userid  ){
		log.info("inside sessionTracking controller and userId is: "+userid);
		User output=userRepoService.findByUSerId(userid);
		LoginTracking loginTracking=new LoginTracking(0,output);
		return loginService.sessionTracking(loginTracking);  
	}
	
	@ApiOperation(value = "change langauge", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/changeLanguage")     
	public ResponseEntity<?> sessionTracking(@RequestBody ChangeLanguage languageData  ){
		return loginService.changeLanguage(languageData);  
	}
	
	@ApiOperation(value = "forgot password", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/forgotPassword")   
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword forgotPassword)
	{     
		return loginService.forgotPassword(forgotPassword);
	}
	
	@ApiOperation(value = "update new  password", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/updateNewPassword")   
	public ResponseEntity<?> updateNewPassword(@RequestBody NewPassword password)
	{      
		return loginService.updateNewUserPassword(password);  
	}
}
