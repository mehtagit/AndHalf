package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.smsPortRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	    public ModelAndView viewMessageManagement(HttpSession session,@RequestParam(name="via", required = false) String via) {
		ModelAndView modelAndView = new ModelAndView();
		 log.info(" view port Management entry point."); 
		 if("viaDashboard".equals(via)) {
			 modelAndView.setViewName("ModemDetails");
		 }else {
			 modelAndView.setViewName("viewPortManagement");
		 }
		 log.info(" view port Management exit point."); 
		return modelAndView; 
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
	
		@PostMapping("portViewByID") 
		public @ResponseBody GenricResponse viewPortAddress (@RequestBody FilterRequest request)  {
			log.info("request send to the View Port api="+request);
			GenricResponse response= userProfileFeignImpl.viewPortFeign(request);
			log.info("response from add View api "+response);
			return response;
	}
		
		
	//------------------------------------- update Port Address ----------------------------------------							
		
		@PostMapping("runPortAddress") 
		public @ResponseBody GenricResponse runPortAddress (@RequestBody smsPortRequest webAction)  {
			log.info("request send to the Run Port api="+webAction);
			webAction.setAction("Start");
			webAction.setStatus("Init");
			GenricResponse response= userProfileFeignImpl.runPortAddressFeign(webAction);
			log.info("response from run api "+response);
			return response;
	}	
		
		
		@PostMapping("stopPortAddress") 
		public @ResponseBody GenricResponse stopPortAddress (@RequestBody smsPortRequest webAction)  {
			log.info("request send to the stop Port api="+webAction);
			webAction.setAction("Stop");
			webAction.setStatus("Init");
			GenricResponse response= userProfileFeignImpl.stopPortAddressFeign(webAction);
			log.info("response from stop api "+response);
			return response;
	}	
		
		
	
	
	//------------------------------------- delete Port Address ----------------------------------------	
	
		@PostMapping ("deletePort")
		public @ResponseBody GenricResponse deletePortAddress(@RequestBody FilterRequest filterRequest) {
			log.info("request send to the Delete PORT api="+filterRequest);
			GenricResponse response= userProfileFeignImpl.deletePortFeign(filterRequest);
			log.info("response after delete PORT."+response);
			return response;

	}
		
	
}



