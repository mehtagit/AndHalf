package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.NewRule;
import org.gl.ceir.CeirPannelCode.Model.NewSystemUser;
import org.gl.ceir.CeirPannelCode.Util.GenerateRandomDigits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserManagementController {

	@Autowired
	GenerateRandomDigits randomDigits;
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

	
	@GetMapping("register-user-form")
	public ModelAndView save() {
		return new ModelAndView("addSystemUser");
		
	}
	
	
	@RequestMapping(value= {"/saveNewSystemUser"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public NewSystemUser saveRecord(@RequestBody NewSystemUser newSystemUser) 
	{
		//RuleListContent ruleList = new RuleListContent();
		String username=randomDigits.getAlphaNumericString(4)+randomDigits.getNumericString(4)+randomDigits.getAlphaNumericString(1);
		log.info("random Generated username--->" +username);
		
		newSystemUser.setUsername(username);
		log.info("request::::::"+newSystemUser);
		
		
		NewSystemUser response = userProfileFeignImpl.saveSystemUser(newSystemUser);
		log.info(" response::::::"+response);
		return response;

	}
	
	
	
	//------------------------------------- view User ----------------------------------------							
	
			@PostMapping("viewUser/{id}") 
			public @ResponseBody GenricResponse viewUser (@PathVariable ("id") Integer id )  {
				log.info("request send to the viewUser api="+id);
				GenricResponse response= userProfileFeignImpl.viewUserFeign(id);
				log.info("response from viewUser api "+response);
				return response;
		}
			
	
	//------------------------------------- update Currency ----------------------------------------							
			
			@PostMapping("updateUser") 
			public @ResponseBody GenricResponse updateUserDetail(@RequestBody NewSystemUser newSystemUser)  {
				log.info("request send to the Update user api="+newSystemUser);
				GenricResponse response= userProfileFeignImpl.updateUserFeign(newSystemUser);
				log.info("response from update api "+response);
				return response;
		}			
}
