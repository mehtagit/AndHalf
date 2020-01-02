package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemAdminController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@RequestMapping(value=
		{"/messageManagement"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewMessageManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view viewMessageManagement entry point."); 
		 mv.setViewName("viewMessageManagement");
		log.info(" view viewMessageManagement exit point."); 
		return mv; 
	}
	
}
