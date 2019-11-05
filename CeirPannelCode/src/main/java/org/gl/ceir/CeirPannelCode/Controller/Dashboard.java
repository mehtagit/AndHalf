package org.gl.ceir.CeirPannelCode.Controller;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Dashboard {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@GetMapping("/importerDashboard")
	public ModelAndView openUserRegisterPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		log.info("importer dashboard entry point..");
		String username=(String)session.getAttribute("username");
		log.info("username from session:  "+username);
		mv.setViewName("dashboard");
		log.info("importer dashboard exit point..");
		return mv; 
	} 
} 
