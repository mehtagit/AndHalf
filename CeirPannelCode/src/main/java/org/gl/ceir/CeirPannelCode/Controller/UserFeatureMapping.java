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
public class UserFeatureMapping {
	
	@Autowired
	ProfileService profileService;
	
private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value=
		{"/usertypeFeatureMapping"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewMessageManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view usertypeFeatureMapping entry point."); 
		 mv.setViewName("userFeatureMapping");
		log.info(" view usertypeFeatureMapping exit point."); 
		return mv; 
	}
	
	
	@RequestMapping(value ="/updateSystemUserPeriod",method = RequestMethod.POST)
	@ResponseBody
	public  HttpResponse changeUserPeriodStatus(@RequestBody UserManagementContent userManagementContent,HttpSession session) {
		return profileService.changeSystemUserPeriodService(userManagementContent,session);
		
	}
}
