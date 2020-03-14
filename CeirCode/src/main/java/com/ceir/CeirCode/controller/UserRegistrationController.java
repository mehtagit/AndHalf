
package com.ceir.CeirCode.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ceir.CeirCode.model.Otp;
import com.ceir.CeirCode.model.ResendOtp;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.repo.SecurityQuestionRepo;
import com.ceir.CeirCode.repo.UserProfileRepo;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.service.LoginService;
import com.ceir.CeirCode.service.OtpService;
import com.ceir.CeirCode.service.UserService;
import com.ceir.CeirCode.util.GenerateRandomDigits;
import com.ceir.CeirCode.util.HttpResponse;
import io.swagger.annotations.ApiOperation;
@RestController  
@RequestMapping("/userRegistration")
public class UserRegistrationController {
	@Autowired    
	GenerateRandomDigits randomDigits;
	@Autowired        
	UserRepo userRepo;
	@Autowired  
	UserProfileRepo  userProfileRepo;
	@Autowired 
	UserService userService;           
	@Autowired
	OtpService otpService;
	@Autowired 
	UsertypeRepo usertypeRepo;
	@Autowired
	LoginService loginService;

	@Autowired 
	SecurityQuestionRepo securityQuestionRepo; 
	@ApiOperation(value = "usertypes data", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/getUsertypes") 
	public ResponseEntity<?> getUsertypes(@RequestHeader HttpHeaders headers){
		return userService.getUsertypeData(headers);
	}
	
	@ApiOperation(value = "usertypes data", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/usertypeIdByName/{usertype}") 
	public ResponseEntity<?> usertypeIdByName(@PathVariable("usertype")String usertype){
		return userService.usertypeIdByName(usertype);
	}
	
	

	@ApiOperation(value = "security questions list", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/getSecurityQuestion")
	public ResponseEntity<?> getSecurityQuestion(@RequestHeader HttpHeaders headers){
		return userService.getSecurityQuestion(headers);
	}                                                             

	@ApiOperation(value = "user registration .", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/registration")
	public ResponseEntity<?> saveUserRegistration(@RequestBody UserProfile userDetails){

		return userService.userRegistration(userDetails);
	} 
	
	@ApiOperation(value = "check registration status", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/checkAvailability/{usertypeId}")
	public ResponseEntity<?> checkStatus(@PathVariable("usertypeId")Integer usertypeId){

		return userService.checkRegistration(usertypeId);
	} 

	@ApiOperation(value = "update email and phone status", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/validate")
	public ResponseEntity<?> UpdateOtpStatus(@RequestBody Otp  otp)
	{  
		return userService.validateUser(otp);
	}            

	@ApiOperation(value = "otp resend", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/resendOtp")   
	public ResponseEntity<?> resendOtp(@RequestBody ResendOtp otp)
	{     
		return userService.resendOtp(otp);
	}        
	
	
	
}