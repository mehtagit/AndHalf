package org.gl.ceir.CeirPannelCode.Service;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.util.List;
import org.gl.ceir.CeirPannelCode.Feignclient.UserRegistrationFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.Otp;
import org.gl.ceir.CeirPannelCode.Model.OtpResponse;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.SecurityQuestion;
import org.gl.ceir.CeirPannelCode.Model.Usertype;
import org.gl.ceir.CeirPannelCode.Util.GenerateRandomDigits;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Service
public class RegistrationService {
	@Value("${FilePath1}") 
	String filePath; 
	@Autowired
	UserRegistrationFeignImpl registrationFeignImpl;
	@Autowired
	UserRegistrationFeignImpl userRegistrationFeignImpl;
	@Autowired
	GenerateRandomDigits randomDigits;
	private final Logger log = LoggerFactory.getLogger(getClass());
	public ModelAndView registrationView() {
		log.info("view registration page starting point");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("registration");
		List<Usertype> usertypeList=registrationFeignImpl.userypeList();
		List<SecurityQuestion> securityQuestionList=registrationFeignImpl.securityQuestionList();
		mv.addObject("usertypes",usertypeList);
		mv.addObject("questions",securityQuestionList);
		log.info("view registration page ending point");
		return mv;                
	}
	public ModelAndView saveRegistration(Registration registration, MultipartFile file) throws IOException {
		log.info("save registration page starting point");
		//log.info("roles length:  "+registration.getRoles().length);
		log.info("registration data:  "+registration); 
		ModelAndView mv=new ModelAndView(); 
		if(registration.getRePassword().equals(registration.getPassword())) {
			String username=randomDigits.getAlphaNumericString(4)+randomDigits.getNumericString(4)+randomDigits.getAlphaNumericString(1);
			registration.setUsername(username);
			StringBuilder combinedPath=new StringBuilder(filePath).append("/"+username);
			String finalPath=new String(combinedPath); 
			if("Individual".equals(registration.getType())){
				if(file.isEmpty()==true) { 
					log.info("if file is empty");
					mv.addObject("msg","please upload national information");
					mv.setViewName("registration");
				}    
				else{ 
					log.info("if user is individual  "); 
					log.info("file name: " +file.getOriginalFilename());
					log.info("finalPath:   "+finalPath);  
					 File dir = new File(finalPath);
					  if (!dir.exists()) dir.mkdirs();
					byte barr[]=file.getBytes();
					BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(finalPath + "/" + file.getOriginalFilename()));
					bout.write(barr);
					bout.flush();
					bout.close();
					OtpResponse response=userRegistrationFeignImpl.registration(registration);
					log.info("registration response:  "+response);
					if(response.getStatusCode()==200) {
					mv.addObject("msg",response.getResponse());
					mv.addObject("userId",response.getUserId());
					mv.setViewName("verifyOtp"); 
					}
					else {
						mv.addObject("msg",response.getResponse());
						mv.setViewName("registration");  
					}
				}  
			}  
			else {
				OtpResponse response=userRegistrationFeignImpl.registration(registration);
				if(response.getStatusCode()==200) {
					mv.addObject("msg",response.getResponse());
					mv.addObject("userId",response.getUserId());
					mv.setViewName("verifyOtp"); 
					}
					else {
						mv.addObject("msg",response.getResponse());
						mv.setViewName("registration");  
					}
			}
			log.info("save registration page end point");
		}  
		else {
			mv.addObject("msg","Password and Re password must be same");
			mv.setViewName("registration");      
		}
		return mv;                 
	}  

	public String otpPage() {
		log.info("inside verify otp page");
		return "verifyOtp";  
	} 

	public HttpResponse verifyOtp(Otp otp) {
		log.info("inside verify otp controller");
		log.info("otp data:  "+otp);    
		HttpResponse response=userRegistrationFeignImpl.otpValidate(otp);
		log.info("verify otp api response:  "+response);
		log.info("exit from verify otp controller");
		return response;
	}
	
	public HttpResponse resendOtp(Integer id) {
		log.info("inside resend otp controller");
		log.info("id:   "+id);     
		HttpResponse response=userRegistrationFeignImpl.otpResend(id); 
		log.info("resend otp api response:  "+response);
		log.info("exit from resend otp controller");
		return response;
	}
}
