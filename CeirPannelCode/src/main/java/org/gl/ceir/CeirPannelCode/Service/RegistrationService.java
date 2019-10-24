package org.gl.ceir.CeirPannelCode.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gl.ceir.CeirPannelCode.Controller.RegistrationController;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
@Service
public class RegistrationService {
	private static final Logger log = LogManager.getLogger(RegistrationService.class);
	public ModelAndView registrationView() {
		log.info("view registration page starting point");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("registration");
		log.info("view registration page ending point");
		return mv;               
	}
	public ModelAndView saveRegistration(Registration registration) {
		log.info("save registration page starting point");
		log.info("registration data:  "+registration);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/verifyOtp");  
		log.info("save registration page end point");
		return mv;               
	}
}
