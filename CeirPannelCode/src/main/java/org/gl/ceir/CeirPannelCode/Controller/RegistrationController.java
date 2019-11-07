package org.gl.ceir.CeirPannelCode.Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserRegistrationFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.Otp;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.SecurityQuestion;
import org.gl.ceir.CeirPannelCode.Service.RegistrationService;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	private final Logger log = LoggerFactory.getLogger(getClass());	
	@RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
	public ModelAndView index(){
		log.info("inside index controller ");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		return mv;     
	}        
 
	@RequestMapping(value = "/registration",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView registration(@ModelAttribute Registration registration,HttpSession session, @RequestParam(name = "file",required = false) MultipartFile file ) throws IOException{
		if(registration.getFirstName() ==null ) {
			ModelAndView mv=registrationService.registrationView();
			return mv; 
		} 
		else { 
			ModelAndView mv=registrationService.saveRegistration(registration, file,session);
			return mv;	   
		}
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
	
}
