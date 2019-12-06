package org.gl.ceir.CeirPannelCode.Controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignClientImplementation;
import org.gl.ceir.CeirPannelCode.Feignclient.UserRegistrationFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.Operator;
import org.gl.ceir.CeirPannelCode.Model.Otp;
import org.gl.ceir.CeirPannelCode.Model.OtpResponse;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.SecurityQuestion;
import org.gl.ceir.CeirPannelCode.Model.Usertype;
import org.gl.ceir.CeirPannelCode.Service.RegistrationService;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Controller 
public class RegistrationController {
	@Autowired    
	RegistrationService registrationService;
	@Autowired
	UserRegistrationFeignImpl userRegistrationFeignImpl;
    @Autowired
    FeignClientImplementation feignImplementation;
	private final Logger log = LoggerFactory.getLogger(getClass());	
	@RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
	public ModelAndView index(){
		log.info("inside index controller ");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		return mv;      
	}         

	@RequestMapping(value = "/usertypeList",method = {RequestMethod.GET})
	@ResponseBody  
	public List<Usertype> usertypeList(){ 
		List<Usertype> response =userRegistrationFeignImpl.userypeList();
		return response;          
	} 
	
	@RequestMapping(value = "/operatorList/{tag}",method = {RequestMethod.GET})
	@ResponseBody  
	public List<Operator> operatorList(@PathVariable String tag){ 
		List<Operator> response =feignImplementation.operatorList(tag);
		return response;            
	} 

	@RequestMapping(value = "/registration",method = {RequestMethod.GET})
	public ModelAndView registration(@RequestParam(name = "usertypeId",required =false) Integer usertypeId	) throws IOException{
		ModelAndView mv=registrationService.registrationView(usertypeId);
		
		return mv; 
	} 
	@RequestMapping(value = "/customRegistration",method = {RequestMethod.GET})
	public ModelAndView customRegistration(@RequestParam(name = "usertypeId",required =false) Integer usertypeId	) throws IOException{
		ModelAndView mv=registrationService.customRegistrationView(usertypeId);
		return mv; 
	} 

	@RequestMapping(value = "/operatorRegistration",method = {RequestMethod.GET})
	public ModelAndView operatorRegistration(@RequestParam(name = "usertypeId",required =false) Integer usertypeId	) throws IOException{
		ModelAndView mv=registrationService.operatorRegistrationView(usertypeId);
		return mv; 
	} 

	@RequestMapping(value = "/saveRegistration",method = {RequestMethod.POST})
	@ResponseBody     
	public OtpResponse saveRegistration(@RequestParam(name = "data",required = true) String data,
			@RequestParam(name = "file",required = false)MultipartFile file,
			@RequestParam(name = "photo",required = false)MultipartFile photo,
			@RequestParam(name = "NationalIdImage",required = false)MultipartFile nationalIdImage,
			@RequestParam(name = "idCard",required = false)MultipartFile idCard,
			HttpSession session) throws IOException{
		OtpResponse response =registrationService.saveRegistration(data, file,photo,nationalIdImage,idCard,session);  
		return response;             
	}



	@RequestMapping(value = "/verifyOtpPage",method = {RequestMethod.GET})
	public ModelAndView verifyOtpPage(){
		ModelAndView mv=new ModelAndView();
		log.info("inside verifyOtp page controller ");
		mv.setViewName("verifyOtp");
		return mv;      
	} 

	@RequestMapping(value = "/securityQuestionList",method = {RequestMethod.GET})
	@ResponseBody 
	public List<SecurityQuestion> questionList(){
		List<SecurityQuestion> response =registrationService.securityQuestionList();
		return response;         
	}

	@RequestMapping(value = "/verifyOtp",method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse verifyOtp(@RequestBody Otp otp){
		HttpResponse response =registrationService.verifyOtp(otp);
		return response;       
	}



	@RequestMapping(value = "/resendOtp/{userid}",method = {RequestMethod.POST})
	@ResponseBody
	public HttpResponse resendOtp(@PathVariable Integer userid){
		HttpResponse response =registrationService.resendOtp(userid);
		return response;                 
	}  



	@GetMapping("/editProfile")
	public ModelAndView editProfile() {
		ModelAndView mv = new ModelAndView();
		log.info(" editProfile entry point..");
		mv.setViewName("editProfile");
		log.info("editProfile exit point..");
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET,value = "/captcha")
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{

		registrationService.captcha(request, response, session);
	} 
	
	@RequestMapping(value = "/registrationUserType",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody  
	public List<Usertype> userTypeDropdown(){ 
		List<Usertype> response =userRegistrationFeignImpl.userRegistrationDropdown();
		return response;          
	} 
}
