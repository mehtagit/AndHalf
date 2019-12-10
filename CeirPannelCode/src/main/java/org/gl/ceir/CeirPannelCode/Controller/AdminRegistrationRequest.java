package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminRegistrationRequest {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserProfileFeignImpl userProfileFeignImpl; 

	@RequestMapping(value=
		{"/registrationRequest"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewConsignment(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view Admin Registration entry point."); 
		 mv.setViewName("AdminRegistrationRequest");
		log.info(" view Admin Registration  exit point."); 
		return mv; 
	}
	
	
	@RequestMapping(value=
		{"/trcInformation"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewAdminUser(HttpSession session,@RequestParam(name="id") int id, @RequestParam(name="roles") String roles,@RequestParam(name="type") String asType) {
		ModelAndView mv = new ModelAndView();
		//log.info("ID----------------->"+id+"--------- Roles------------->"+roles+"--------type------>"+asType);
		
		Registration registration = userProfileFeignImpl.ViewAdminUser(id);
		mv.addObject("registration", registration);
		log.info(" view trcInformation entry point."+registration); 
		
	
		if("TRC".equals(roles)) {
			log.info("-------------------->1");
			mv.setViewName("trcInformation");
		
		}else if("Operator".equals(roles)){
			log.info("-------------------->2");
			mv.setViewName("viewOperator");
		
		}else if("Custom".equals(roles)){
			log.info("-------------------->3");
			mv.setViewName("viewCustom");
			
		}else if("Importer".equals(roles) || "Distributor".equals(roles) || "Retailer".equals(roles) && "Company".equals(asType)){
			log.info("-------------------->4");
			mv.setViewName("viewCompany");
			
		}else if("Individual".equals(asType) && "Importer".equals(roles) || "Distributor".equals(roles) || "Retailer".equals(roles)){
			log.info("-------------------->5");
			mv.setViewName("viewIndividual");
		}
		
		log.info(" view trcInformation  exit point."); 
		return mv; 
	}
	
	
	
}
