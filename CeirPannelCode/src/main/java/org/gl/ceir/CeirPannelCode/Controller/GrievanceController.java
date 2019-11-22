package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrievanceController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@RequestMapping(value=
		{"grievanceManagement"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewGrievance(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 
		log.info(" view Grievance entry point."); 
		 mv.setViewName("grievanceManagement");
		log.info(" view Grievance exit point."); 
		return mv; 
	}
	
}

