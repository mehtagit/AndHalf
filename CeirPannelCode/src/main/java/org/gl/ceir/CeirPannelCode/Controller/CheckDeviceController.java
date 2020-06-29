package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
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
public class CheckDeviceController {
		private final Logger log = LoggerFactory.getLogger(getClass());
		
		@Autowired
		FeignCleintImplementation feignCleintImplementation;
		
		@RequestMapping(value=
			{"/checkDeviceslogin"},method={org.springframework.web.bind.annotation.
					RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
				)
		    public ModelAndView viewConsignment(HttpSession session) {
			ModelAndView mv = new ModelAndView();
			 log.info("view Check Device entry point."); 
			 mv.setViewName("checkDevices");
			log.info(" view Check Device  exit point."); 
			return mv; 
		}
		
		
		
		@PostMapping("/checkDevice") 
		public @ResponseBody GenricResponse checkImei(@RequestBody FilterRequest filterRequest)  {
			log.info("request send to the checkImei api="+filterRequest);
			GenricResponse response= feignCleintImplementation.viewDetails(filterRequest);
			log.info("response from currency api "+response);
			return response;

			}
		
}
