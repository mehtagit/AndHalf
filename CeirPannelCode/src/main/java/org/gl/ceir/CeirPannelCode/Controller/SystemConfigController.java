package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.pagination.model.ConfigContentModel;
import org.gl.ceir.pagination.model.MessageContentModel;
import org.gl.ceir.pagination.model.PolicyConfigContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemConfigController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	
	@RequestMapping(value=
		{"/systempConfigManagement"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewConfigManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view ConfigManagement entry point."); 
		 mv.setViewName("viewConfigManagement");
		log.info(" view ConfigManagement exit point."); 
		return mv; 
	}
	
	
	@PostMapping("/system/viewTag") 
	public @ResponseBody ConfigContentModel SystemConfigViewTag (@RequestBody FilterRequest filterRequest)  {
		log.info("request send to the SystemConfigViewTag api="+filterRequest);
		ConfigContentModel response= feignCleintImplementation.viewAdminFeign(filterRequest);
		log.info("response from currency api "+response);
		return response;
	}
	
	
	@PutMapping("/system/update")
	public @ResponseBody ConfigContentModel updateSystem (@RequestBody ConfigContentModel configContentModel) {
		log.info("request send update Messsage api="+configContentModel);
		configContentModel = feignCleintImplementation.updateSystem(configContentModel);
		log.info("response from update Message api "+configContentModel);
		return configContentModel;
		
	}
	
	
}
