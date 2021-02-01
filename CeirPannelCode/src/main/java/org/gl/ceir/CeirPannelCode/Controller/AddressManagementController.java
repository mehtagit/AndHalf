package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserLoginFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class AddressManagementController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserLoginFeignImpl userLoginFeignImpl;
	
	@RequestMapping(value={"/addressManagement"},method={org.springframework.web.bind.annotation.
			RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	public ModelAndView viewManageType(HttpSession session,@RequestParam(name="txnID",required = false) String txnID) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewAddressManagement");
		return mv; 
	}

	
	//***************************************** Export Address controller *********************************
	@PostMapping("exportAddress")
	@ResponseBody
	public FileExportResponse exportToExcel(@RequestBody FilterRequest filterRequest,HttpSession session)
	{
		Gson gsonObject=new Gson();
		Object response;
		Integer file = 1;	
		String userType=(String) session.getAttribute("usertype");
		Integer usertypeId=(int) session.getAttribute("usertypeId");
		filterRequest.setUserType(userType);
		filterRequest.setUserTypeId(usertypeId);
		log.info("filterRequest:::::::::"+filterRequest);
		response= userLoginFeignImpl.viewAllLocality(filterRequest, filterRequest.getPageNo(), filterRequest.getPageSize(), file);
		FileExportResponse fileExportResponse;
		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
		log.info("response  from  Alert Export  api="+fileExportResponse);

		return fileExportResponse;
	}		
		
		//------------------------------------- Delete Address ----------------------------------------	
		
		@DeleteMapping ("deleteAddress")
		public @ResponseBody GenricResponse deleteAddressDetails(@RequestBody FilterRequest filterRequest) {
			log.info("request send to the Delete Addresss api="+filterRequest);
			GenricResponse response=userLoginFeignImpl.deleteAddressFeign(filterRequest);
			log.info("response after Delete Addresss api."+response);
			return response;

		}
		
		/*------------------------------------- Add Province ------------------------------------------ */

	    @PostMapping("addProvince") 
	    public @ResponseBody GenricResponse saveProvince (@RequestBody FilterRequest filterRequest)  {
		   log.info("request send to the add Province api="+filterRequest);
	 	   GenricResponse response= userLoginFeignImpl.AddProvinceManagementFeign(filterRequest);
		   log.info("response from add Province api "+response);
		   return response;
	    }
	    
	    /*------------------------------------- Add District ------------------------------------------ */

	    @PostMapping("addDistrict") 
	    public @ResponseBody GenricResponse saveDistrict (@RequestBody FilterRequest filterRequest)  {
		   log.info("request send to the add District api="+filterRequest);
	 	   GenricResponse response= userLoginFeignImpl.AddDistrictManagementFeign(filterRequest);
		   log.info("response from add District api "+response);
		   return response;
	    }
	    
	    /*------------------------------------- Add commune ------------------------------------------ */

	    @PostMapping("addCommune") 
	    public @ResponseBody GenricResponse saveCommune (@RequestBody FilterRequest filterRequest)  {
		   log.info("request send to the add commune api="+filterRequest);
	 	   GenricResponse response= userLoginFeignImpl.AddCommuneManagementFeign(filterRequest);
		   log.info("response from add commune api "+response);
		   return response;
	    }
	    
	    /*------------------------------------- Add village ------------------------------------------ */

	    @PostMapping("addVillage") 
	    public @ResponseBody GenricResponse saveVillage (@RequestBody FilterRequest filterRequest)  {
		   log.info("request send to the add village api="+filterRequest);
	 	   GenricResponse response= userLoginFeignImpl.AddVillageManagementFeign(filterRequest);
		   log.info("response from add village api "+response);
		   return response;
	    }
	}

