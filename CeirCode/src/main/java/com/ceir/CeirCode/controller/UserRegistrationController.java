
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
import org.springframework.web.bind.annotation.RequestParam;
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
@CrossOrigin
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
	
	@PostMapping("/getUsertypes") 
	public ResponseEntity<?> getUsertypes(@RequestParam(defaultValue ="3",required = false,name = "type")int type){
		return userService.getUsertypeData(type);
	}

//	@ApiOperation(value = "internal usertypes data", response = HttpResponse.class)
//	
//	@PostMapping("/getInternalUsertypes") 
//	public ResponseEntity<?> getInternalUsertypes(){
//		return userService.getInternalUsertype();
//	} 
	
	@ApiOperation(value = "usertypes data", response = HttpResponse.class)
	
	@PostMapping("/usertypeIdByName/{usertype}") 
	public ResponseEntity<?> usertypeIdByName(@PathVariable("usertype")String usertype){
		return userService.usertypeIdByName(usertype);
	}	

	@ApiOperation(value = "security questions list", response = HttpResponse.class)
	
	@PostMapping("/getSecurityQuestion/{username}")
	public ResponseEntity<?> getSecurityQuestion(@PathVariable("username")String username){
		return userService.getSecurityQuestion(username);
	}                                                             

	
	@ApiOperation(value = "security questions list", response = HttpResponse.class)
	
	@PostMapping("/getAllSecurityQuestion")
	public ResponseEntity<?> getSecurityQuestion(){
		return userService.getSecurityQuestion2();
	}                                                             

	
	
	@ApiOperation(value = "user registration .", response = HttpResponse.class)
	
	@PostMapping("/registration")
	public ResponseEntity<?> saveUserRegistration(@RequestBody UserProfile userDetails){

		return userService.userRegistration(userDetails);
	} 
	
	@ApiOperation(value = "check registration status", response = HttpResponse.class)
	
	@PostMapping("/checkAvailability/{usertypeId}")
	public ResponseEntity<?> checkStatus(@PathVariable("usertypeId")Integer usertypeId){

		return userService.checkRegistration(usertypeId);
	} 

	@ApiOperation(value = "update email and phone status", response = HttpResponse.class)
	
	@PostMapping("/validate")
	public ResponseEntity<?> UpdateOtpStatus(@RequestBody Otp  otp)
	{  
		return userService.validateUser(otp);
	}            

	@ApiOperation(value = "otp resend", response = HttpResponse.class)
	
	@PostMapping("/resendOtp")   
	public ResponseEntity<?> resendOtp(@RequestBody ResendOtp otp)
	{     
		return userService.resendOtp(otp);
	} 
	
	@ApiOperation(value = "otp resend", response = HttpResponse.class)
	
	@PostMapping("/profileResendOtp")   
	public ResponseEntity<?> profileResendOtp(@RequestBody ResendOtp otp)
	{     
		return userService.profileResendOtp(otp);
	} 
	
	@ApiOperation(value = "soft Delete API", response = HttpResponse.class)
	@PostMapping("/softDelete")   
	public ResponseEntity<?> softDelete(@RequestParam(name="currentStatus",required = true ) int currentStatus,@RequestParam(name="username",required = true ) String username)
	{     	
		return userService.soft_delete(currentStatus, username);
	}
	
	
	
}