package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.pagination.model.MessageContentModel;
import org.gl.ceir.pagination.model.PolicyConfigContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemAdminController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
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
	
	
	
	@PostMapping("/message/viewTag") 
	public @ResponseBody MessageContentModel policyViewTag (@RequestBody FilterRequest filterRequest)  {
		log.info("request send to the policyViewTag api="+filterRequest);
		MessageContentModel response= feignCleintImplementation.viewMessageFeign(filterRequest);

		log.info("response from currency api "+response);
		return response;

		}
	
}
