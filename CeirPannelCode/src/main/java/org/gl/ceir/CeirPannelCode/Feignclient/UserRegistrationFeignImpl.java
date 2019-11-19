package org.gl.ceir.CeirPannelCode.Feignclient;
import java.util.List;
import org.gl.ceir.CeirPannelCode.Model.Otp;
import org.gl.ceir.CeirPannelCode.Model.OtpResponse;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.SecurityQuestion;
import org.gl.ceir.CeirPannelCode.Model.Usertype;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient(url="http://13.127.239.247:8086/CEIRCode2",value = "registrationUrls")
public interface UserRegistrationFeignImpl {
  
	@PostMapping("/userRegistration/getUsertypes")
	public List<Usertype> userypeList();
	                                  
	@PostMapping("/userRegistration/getSecurityQuestion")
	public List<SecurityQuestion> securityQuestionList();
	
	@PostMapping("/userRegistration/registration")
	public OtpResponse registration(@RequestBody Registration registration); 
	                                          
	@PostMapping("/userRegistration/validate")
	public HttpResponse  otpValidate(@RequestBody Otp otp);
	
	@PostMapping("/userRegistration/resendOtp/{userid}")   
	public HttpResponse otpResend(@PathVariable("userid") Integer userid);
	
	
}
