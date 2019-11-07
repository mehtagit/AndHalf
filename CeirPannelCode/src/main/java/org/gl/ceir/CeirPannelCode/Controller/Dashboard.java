package org.gl.ceir.CeirPannelCode.Controller;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Dashboard {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	LoginService loginService;
	
	
	@GetMapping("/importerDashboard")
	public ModelAndView openUserRegisterPage(HttpSession session) {
		return loginService.Dashboard(session);   
	} 
 
	@RequestMapping(value={"/Home"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView Home() { System.out.println("inside home method");
	ModelAndView mv = new ModelAndView(); mv.setViewName("Home"); return mv;
	}
	
} 
