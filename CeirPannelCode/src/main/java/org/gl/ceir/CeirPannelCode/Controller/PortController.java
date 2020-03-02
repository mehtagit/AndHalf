package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PortController {
	
	@Autowired
	UserProfileFeignImpl userProfileFeignImpl;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value=
		{"/portManagement"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewMessageManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view port Management entry point."); 
		 mv.setViewName("viewPortManagement");
		log.info(" view port Management exit point."); 
		return mv; 
	}
	
	
	/*------------------------------------- Add Port ------------------------------------------ */

	    @PostMapping("add-Port") 
	    public @ResponseBody GenricResponse AddPortAddress (@RequestBody FilterRequest filterRequest)  {
		   log.info("request send to the add Port api="+filterRequest);
	 	   GenricResponse response= userProfileFeignImpl.AddPortAddressFeign(filterRequest);
		   log.info("response from add Port api "+response);
		   return response;
	}
	
	
	//------------------------------------- view Port Address ----------------------------------------							
	
		@PostMapping("portViewByID/{id}") 
		public @ResponseBody GenricResponse viewPortAddress (@PathVariable("id") Integer id)  {
			log.info("request send to the View Port api="+id);
			GenricResponse response= userProfileFeignImpl.viewPortFeign(id);
			log.info("response from add View api "+response);
			return response;
	}
		
		
	//------------------------------------- update Port Address ----------------------------------------							
		
		@PostMapping("updatePortAddress") 
		public @ResponseBody GenricResponse updatePortAddress (@RequestBody FilterRequest filterRequest)  {
			log.info("request send to the Update Port api="+filterRequest);
			GenricResponse response= userProfileFeignImpl.updatePortAddressFeign(filterRequest);
			log.info("response from update api "+response);
			return response;
	}	
	
	
	//------------------------------------- delete Port Address ----------------------------------------	
	
		@PostMapping ("deletePort/{id}")
		public @ResponseBody GenricResponse deletePortAddress(@PathVariable("id") Integer id) {
			log.info("request send to the Delete PORT api="+id);
			GenricResponse response= userProfileFeignImpl.deletePortFeign(id);
			log.info("response after delete PORT."+response);
			return response;

	}
}



