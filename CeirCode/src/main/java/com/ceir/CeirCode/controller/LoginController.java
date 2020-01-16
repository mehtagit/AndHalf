
package com.ceir.CeirCode.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceir.CeirCode.model.ForgotPassword;
import com.ceir.CeirCode.model.LoginTracking;
import com.ceir.CeirCode.model.NewPassword;
import com.ceir.CeirCode.model.User;
import com.ceir.CeirCode.model.UserLogin;
import com.ceir.CeirCode.service.LoginService;
import com.ceir.CeirCode.util.HttpResponse;

import io.swagger.annotations.ApiOperation;
@PropertySource(ignoreResourceNotFound = true, value =
		"classpath:application.properties")

@RestController 
@RequestMapping("/Login")
public class LoginController{
	@Autowired
	LoginService loginService;

	@ApiOperation(value = "user Login", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/checkUser")     
	public ResponseEntity<?> userLogin(@RequestBody UserLogin user){
		return loginService.userLogin(user); 
	}  
  

	@ApiOperation(value = "user session", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/sessionTracking/{userid}")     
	public ResponseEntity<?> sessionTracking(@PathVariable("userid") long userid  ){
		LoginTracking loginTracking=new LoginTracking();
		loginTracking.setLoginStatus(0);
		User user=new User();        
		user.setId(userid);    
		loginTracking.setUserTrack(user);  
		return loginService.sessionTracking(loginTracking);  
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
