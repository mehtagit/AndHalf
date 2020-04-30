package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserManagementController {


	@Autowired
	UserProfileFeignImpl userProfileFeignImpl;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value=
		{"/userManagement"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
		public ModelAndView viewUserManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		log.info(" view viewUserManagement entry point."); 
		mv.setViewName("userManagement");
		log.info(" view viewUserManagement exit point."); 
		return mv; 
	}

}
