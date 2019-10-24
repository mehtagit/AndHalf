package org.gl.ceir.CeirPannelCode.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
     @Autowired 
     RegistrationService registrationService;
     
	
     private static final Logger log = LogManager.getLogger(RegistrationController.class);

	@RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
	public ModelAndView index(){
		log.info("inside index controller ");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("index");
		return mv;    
	}   

	@RequestMapping(value = "/registration",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView registration(@ModelAttribute Registration registration){
		if(registration.getFirstName() ==null ) {
		ModelAndView mv=registrationService.registrationView();
		return mv;
		}
		else { 
			ModelAndView mv=registrationService.saveRegistration(registration);
			return mv;	
		}
		
		                                                                       
	} 

	
	@RequestMapping(value = "/verifyOtp",method = RequestMethod.GET)
	public ModelAndView verifyOtp(){
		log.info("inside verifyOtp controller ");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("verifyOtp");
		return mv;   
	}

	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(){
		log.info("inside login controller ");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("login");
		return mv;   
	}

	@RequestMapping(value = "/forgotPassword",method = RequestMethod.GET)
	public ModelAndView forgotPassword(){
		log.info("inside forgotPassword controller ");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("forgotPassword");
		return mv;   
	}

	/*
	 * @GetMapping({"/importerDashboard" }) public ModelAndView
	 * openUserRegisterPage() { ModelAndView mv = new ModelAndView();
	 * log.info("importer dashboard entry point.."); mv.setViewName("dashboard");
	 * log.info("importer dashboard exit point.."); return mv; }
	 */

	@GetMapping("/editProfile")
	public ModelAndView editProfile() {
		ModelAndView mv = new ModelAndView();
		log.info(" editProfile entry point..");
		mv.setViewName("editProfile");
		log.info("editProfile exit point..");
		return mv;
	}
}
