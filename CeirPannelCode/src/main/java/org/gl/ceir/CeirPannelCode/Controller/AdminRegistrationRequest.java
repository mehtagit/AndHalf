package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class AdminRegistrationRequest {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserProfileFeignImpl userProfileFeignImpl; 

	@RequestMapping(value=
		{"/registrationRequest"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewConsignment(HttpSession session,@RequestParam(name="txnID",required = false) String txnID) {
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
		
		log.info("ID------------>"+id+"--------- Roles------------->"+roles+"--------type------>"+asType);
		
		Registration registration = userProfileFeignImpl.ViewAdminUser(id);
		log.info("View registration API Response--------------->" +registration);
		mv.addObject("registration", registration);
		
		log.info(" view trcInformation entry point."+registration+"---ID-----"+id); 
		
	
		if("TRC".equals(roles)) {
			log.info("-------------------->1");
			mv.setViewName("trcInformation");
		
		}else if("Operator".equals(roles)){
			log.info("-------------------->2");
			mv.setViewName("viewOperator");
		
		}else if("Custom".equals(roles)){
			log.info("-------------------->3");
			mv.setViewName("viewCustom");
			
		}else if(("Importer".equals(roles) || "Distributor".equals(roles) || "Retailer".equals(roles)) && "Company".equals(asType) || "Organization".equals(asType)){
			log.info("-------------------->4");
			mv.setViewName("viewCompany");
			
		}else if(("Importer".equals(roles) || "Distributor".equals(roles) || "Retailer".equals(roles)) && "Individual".equals(asType)){
			log.info("-------------------->5");
			mv.setViewName("viewIndividual");
		}
		
		log.info(" view trcInformation  exit point."); 
		return mv; 
	}
	
	
	
	
	//***************************************** Export Registration controller *********************************
	
	@RequestMapping(value="/exportAdminRegistration",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String exportToExcel(@RequestParam(name="RegistrationStartDate", required = false) String RegistrationStartDate,
			@RequestParam(name="RegistrationEndDate",required = false) String RegistrationEndDate,
			@RequestParam(name="asType",required = false) Integer asType,
			@RequestParam(name="userRoleTypeId", required = false) Integer userRoleTypeId,
			@RequestParam(name="status",required = false) Integer status,
			@RequestParam(name="pageSize") Integer pageSize,
			@RequestParam(name="pageNo") Integer pageNo,
			HttpServletRequest request,
			HttpSession session)
	{
				
		
		log.info("RegistrationStartDate=="+RegistrationStartDate+ " RegistrationEndDate ="+RegistrationEndDate+" asType="+asType+"userRoleTypeId="+userRoleTypeId);
		int userId= (int) session.getAttribute("userid");
		int file=1;
		String userType=(String) session.getAttribute("usertype"); 	
		FileExportResponse fileExportResponse;
		FilterRequest filterRequest= new FilterRequest();
		filterRequest.setStartDate(RegistrationStartDate);
		filterRequest.setEndDate(RegistrationEndDate);
		filterRequest.setAsType(asType);
		filterRequest.setUserRoleTypeId(userRoleTypeId);
		filterRequest.setStatus(status);
		filterRequest.setUserId(userId);
		filterRequest.setUserType(userType);
		log.info(" request passed to the exportTo Excel Api =="+filterRequest+" *********** pageSize"+pageSize+"  pageNo  "+pageNo);
		Object response = userProfileFeignImpl.registrationRequest(filterRequest, pageNo, pageSize,file);
		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
			log.info("response  from   export grievance  api="+fileExportResponse);
			return "redirect:"+fileExportResponse.getUrl();
		
	}
	
	
}
