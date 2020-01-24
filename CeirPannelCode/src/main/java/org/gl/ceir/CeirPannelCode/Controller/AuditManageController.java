package org.gl.ceir.CeirPannelCode.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.pagination.model.AuditContentModel;
import org.gl.ceir.pagination.model.PolicyConfigContent;
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
public class AuditManageController {
	
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value=
		{"/auditTrail"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewMessageManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view audit Management entry point."); 
		 mv.setViewName("auditManagement");
		log.info(" view auditManagement exit point."); 
		return mv; 
	}


	
	
	@ResponseBody
	@GetMapping("/audit/view/{id}")
	public AuditContentModel AuditManagementView(@PathVariable("id") Integer id) {
		AuditContentModel auditContentModel  = feignCleintImplementation.viewAuditManagementFeign(id);
		log.info("AuditContentModel response::::::::"+auditContentModel);
		return auditContentModel;
	}
	
}
