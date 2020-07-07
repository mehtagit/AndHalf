package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Service.ProfileService;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.gl.ceir.pagination.model.UserManagementContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemUserManagement {
	
	@Autowired
	ProfileService profileService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value=
		{"/usertypeManagment"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewMessageManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view viewUserManagement entry point."); 
		 mv.setViewName("viewUserManagement");
		log.info(" view viewUserManagement exit point."); 
		return mv; 
	}
	
	

	@RequestMapping(value ="/updateSystemUserTypeStatus",method = RequestMethod.POST)
	@ResponseBody
	public  HttpResponse changeSystemUserTypeStatus(@RequestBody UserManagementContent userManagementContent,HttpSession session) {
		return profileService.changeSystemUserStatusService(userManagementContent,session);

	}
}
