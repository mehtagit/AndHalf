package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class operatorController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping(value=
		{"/greyList"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewOperator(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" viewOperator entry point."); 
		 mv.setViewName("viewOperatorFeature");
		log.info("viewOperator exit point."); 
		return mv; 
	}
	
	
	
	
}
