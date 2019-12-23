package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.GrievanceModel;
import org.gl.ceir.CeirPannelCode.Model.TRCRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Component
@Service
@FeignClient(url = "${dashBoardfeignClientPath}",value = "grievance" )


public interface GrievanceFeignClient {


	//****************************************************************grievance api starts from here ***************************************************************************************************		
	//View filter grievance  feign  controller
	@RequestMapping(value="/v2/filter/grievance" ,method=RequestMethod.GET) 
	public Object grievanceFilter(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) ;


	// ******************************************** save 	grievance api ********************************************************************************
	@RequestMapping(value="/grievance/save" ,method=RequestMethod.POST) 
	public GenricResponse saveGrievance(@RequestBody GrievanceModel greGrievanceModel) ;



	// ******************************************** view 	grievance api ********************************************************************************
	@RequestMapping(value="/grievance/msg" ,method=RequestMethod.GET) 
	public List<GrievanceModel> viewGrievance(@RequestParam("grievanceId") String  grievanceId,@RequestParam("userId") Integer userId,@RequestParam("recordLimit") Integer recordLimit) ;

	// ******************************************** save 	grievance api ********************************************************************************
	@RequestMapping(value="/grievance/saveMessage" ,method=RequestMethod.POST) 
	public GenricResponse saveGrievanceMessage(@RequestBody GrievanceModel greGrievanceModel) ;



	//***************************************************TRC********************************

	@PostMapping("TypeApproved/view")
	public Object viewTRC(@RequestBody TRCRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,@RequestParam(value = "file", defaultValue = "0") Integer file);	
	
	
	//***************************************************View Operator********************************
	
	@RequestMapping(value="/filedump/filter" ,method=RequestMethod.GET) 
	public Object viewOperatorFeign(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) ;


}
