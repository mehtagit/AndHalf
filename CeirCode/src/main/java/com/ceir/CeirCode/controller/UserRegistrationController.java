package com.ceir.CeirCode.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceir.CeirCode.model.ForgotPassword;
import com.ceir.CeirCode.model.Otp;
import com.ceir.CeirCode.model.UserProfile;
import com.ceir.CeirCode.repo.SecurityQuestionRepo;
import com.ceir.CeirCode.repo.UserProfileRepo;
import com.ceir.CeirCode.repo.UserRepo;
import com.ceir.CeirCode.repo.UsertypeRepo;
import com.ceir.CeirCode.service.LoginService;
import com.ceir.CeirCode.service.OtpService;
import com.ceir.CeirCode.service.UserService;
import com.ceir.CeirCode.util.EmailUtil2;
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
	EmailUtil2 emailUtil;
	@Autowired
	OtpService otpService;
	@Value("${mailusername}")
	String fromEmail;
	@Autowired 
	UsertypeRepo usertypeRepo;
	@Autowired
	LoginService loginService;

	@Autowired 
	SecurityQuestionRepo securityQuestionRepo; 
	@ApiOperation(value = "usertypes data", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/getUsertypes") 
	public ResponseEntity<?> getUsertypes(){
		return userService.getUsertypeData();
	} 

	@ApiOperation(value = "security questions list", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/getSecurityQuestion") 
	public ResponseEntity<?> getSecurityQuestion(){
		return userService.getSecurityQuestion();
	}                                                             

	@ApiOperation(value = "user registration .", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/registration")
	public ResponseEntity<?> saveUserRegistration(@RequestBody UserProfile userDetails){

		return userService.userRegistration(userDetails);
	} 

	@ApiOperation(value = "update email and phone status", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/validate")
	public ResponseEntity<?> UpdateOtpStatus(@RequestBody Otp  otp)
	{  
		return userService.UpdateOtpStatus(otp);
	}            

	@ApiOperation(value = "otp resend", response = HttpResponse.class)
	@CrossOrigin
	@PostMapping("/resendOtp/{userid}")   
	public ResponseEntity<?> resendOtp(@PathVariable("userid") Integer id)
	{     
		return userService.resendOtp(id);
	}        


	

	/*
	 * @ApiOperation(value = "resend sms otp", response = HttpResponse.class)
	 * 
	 * @CrossOrigin
	 * 
	 * @PostMapping("/resendSmsOtp/{mobile}/{userid}") public ResponseEntity<?>
	 * resendSmsOtp(@PathVariable("mobile")String mobile){ try { String
	 * smsOtp=otpService.phoneOtp(mobile); OtpResponse response=new OtpResponse();
	 * response.setStatusCode(200); //response.setPhoneOtp(smsOtp);
	 * response.setResponse("sms sucessfully sent on your phone"); return new
	 * ResponseEntity<>(response,HttpStatus.OK); } catch(Exception e) {
	 * e.printStackTrace(); e.printStackTrace(); HttpResponse response=new
	 * HttpResponse(); response.setStatusCode(409);
	 * response.setResponse("Oops something wrong happened"); return new
	 * ResponseEntity<>(response,HttpStatus.CONFLICT); } }
	 * 
	 * @ApiOperation(value = "resend email otp", response = HttpResponse.class)
	 * 
	 * @CrossOrigin
	 * 
	 * @PostMapping("/resendEmailOtp/{email}") public ResponseEntity<?>
	 * resendEmailOtp(@PathVariable("email")String email){ try {
	 * 
	 * EmailDetails details=new EmailDetails(); details.setFromEmail(fromEmail);
	 * details.setToEmail(email); details.setSubject("Email verification otp");
	 * String emailOtp=randomDigits.getNumericString(6);
	 * details.setMsgBody("your otp is "+emailOtp); boolean
	 * emailOtpStatus=otpService.emailOtp(details); if(emailOtpStatus==true) {
	 * OtpResponse response=new OtpResponse(); response.setStatusCode(200); //
	 * response.setEmailOtp(emailOtp);
	 * response.setResponse("otp sucessfully sent on email"); return new
	 * ResponseEntity<>(response,HttpStatus.OK); } else { OtpResponse response=new
	 * OtpResponse(); response.setStatusCode(409); //response.setPhoneOtp(emailOtp);
	 * response.setResponse("otp failed to sent"); return new
	 * ResponseEntity<>(response,HttpStatus.CONFLICT); }
	 * 
	 * } catch(Exception e) { e.printStackTrace(); e.printStackTrace(); HttpResponse
	 * response=new HttpResponse(); response.setStatusCode(409);
	 * response.setResponse("Oops something wrong happened"); return new
	 * ResponseEntity<>(response,HttpStatus.CONFLICT); } }
	 */
}