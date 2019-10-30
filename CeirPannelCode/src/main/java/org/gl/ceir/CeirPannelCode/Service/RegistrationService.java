package org.gl.ceir.CeirPannelCode.Service;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Feignclient.UserRegistrationFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.OtpResponse;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.SecurityQuestion;
import org.gl.ceir.CeirPannelCode.Model.Usertype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
@Service
public class RegistrationService {
	@Autowired
	UserRegistrationFeignImpl registrationFeignImpl;
	@Autowired
	UserRegistrationFeignImpl userRegistrationFeignImpl;
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
	public ModelAndView saveRegistration(Registration registration) {
		log.info("save registration page starting point");
		log.info("roles length:  "+registration.getRoles().length);
		log.info("registration data:  "+registration);
		OtpResponse response=userRegistrationFeignImpl.registration(registration);
		log.info("registration response:  "+response);
		ModelAndView mv=new ModelAndView();  
		mv.addObject("msg",response.getResponse());
		mv.setViewName("redirect:/verifyOtp");    
		log.info("save registration page end point");
		return mv;                 
	}
}
