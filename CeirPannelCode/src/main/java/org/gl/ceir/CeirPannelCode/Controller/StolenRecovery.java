package org.gl.ceir.CeirPannelCode.Controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.Usertype;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StolenRecovery {

	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired

	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	UtilDownload utildownload;
	
	
	
	@RequestMapping(value={"/stolenRecovery"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST})
			public ModelAndView  viewStolenRecovery( HttpSession session , @RequestParam(name="userTypeId",required=false) String selectedUserTypeId ) {
		ModelAndView mv = new ModelAndView();
		String roletype=session.getAttribute("usertype").toString();
		log.info("stolen page"+selectedUserTypeId); 
		if(selectedUserTypeId==null)
		{
		List<Usertype> userTypelist=(List<Usertype>) session.getAttribute("usertypeList");
		log.info("role type list=="+userTypelist);
		
		log.info("list size of  usertype=="+userTypelist.size());
		
		if(userTypelist.size()>1)
		{
			log.info("if condition.");

			mv.addObject("userTypelist", userTypelist);
			mv.setViewName("StolenRecoverytRoleType");
		}
		else if(userTypelist.size()==1)
		{
		log.info("else condition.");
		session.setAttribute("selectedUserTypeId", roletype);
		mv.setViewName("stolenRecovery");
		}
		
		}
		else {
			log.info("else condition selectedUserTypeId is not empty="+selectedUserTypeId);
			session.setAttribute("selectedUserTypeId", selectedUserTypeId);
			mv.setViewName("stolenRecovery");
		
		}
				
				return mv; 
			}
	
	/*
	 * @RequestMapping(value={"/stolenRecovery"},method={org.springframework.web.
	 * bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.
	 * RequestMethod.POST}) public GenricResponse markStolen(@RequestBody
	 * StolenRecovery stolen,@RequestParam(name="requestType") String requestType )
	 * { log.info("enter in stolenRecovery controller");
	 * log.info("StolenRequest details=="+stolen);
	 * log.info("requestType="+requestType); return null;
	 * 
	 * }
	 */

}
