package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminRegistrationRequest {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	

	@RequestMapping(value=
		{"/registrationRequest"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewConsignment(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view Admin Registration entry point."); 
		 mv.setViewName("AdminRegistrationRequest");
		log.info(" view Admin Registration  exit point."); 
		return mv; 
	}
	
	
}
