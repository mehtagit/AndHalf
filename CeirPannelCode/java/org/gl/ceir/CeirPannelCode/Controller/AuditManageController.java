package org.gl.ceir.CeirPannelCode.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.TRCRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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

	

	//***************************************** Export Audit controller *********************************
	
	@RequestMapping(value="/exportAuditData",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String exportToExcel(HttpSession session,@RequestParam(name="pageSize") Integer pageSize,@RequestParam(name="pageNo") Integer pageNo)
	{
		int userId= (int) session.getAttribute("userid"); 
		int file=1;
		FileExportResponse fileExportResponse;
		FilterRequest filterRequest = new FilterRequest();	
		log.info(" request passed to the  export Audit Excel Api =="+filterRequest+" *********** pageSize"+pageSize+"  pageNo  "+pageNo+"  file  "+file);
		Object	response= feignCleintImplementation.auditManagementFeign(filterRequest, pageNo, pageSize, file);
		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
		log.info("response  from   export Audit  api ="+fileExportResponse);
		return "redirect:"+fileExportResponse.getUrl();
	}
}
