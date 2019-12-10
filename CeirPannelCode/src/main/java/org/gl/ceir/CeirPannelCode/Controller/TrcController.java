package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.TypeApprovedFeignImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class TrcController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TypeApprovedFeignImpl typeApprovedFeignImpl;
	
	@RequestMapping(value=
		{"/manageTypeDevices"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewManageType(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view TRC entry point."); 
		 mv.setViewName("viewManageTypeApproved");
		log.info(" view TRC  exit point."); 
		return mv; 
	}
	
}
