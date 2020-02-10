package org.gl.ceir.CeirPannelCode.Feignclient;

import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.TRCRegisteration;
import org.gl.ceir.CeirPannelCode.Model.TRCRequest;
import org.gl.ceir.CeirPannelCode.Model.TypeApprovedStatusModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Service
@FeignClient(url="${dashBoardfeignClientPath}",value = "profileUrls")
public interface TypeApprovedFeignImpl {

	
	//View Manage Type-Approved Feign*****************************************
	@RequestMapping(value="/TypeApproved/view" ,method=RequestMethod.POST) 
	public Object manageTypeFeign(@RequestBody TRCRequest filterRequest,
	@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
	@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
	@RequestParam(value = "file", defaultValue = "0") Integer file) ;
	
	
	@RequestMapping(value="/TypeApproved/add" ,method=RequestMethod.POST) 
	public GenricResponse register(@RequestBody TRCRegisteration filterRequest) ;
	
	
	@PostMapping("/TypeApproved/viewById/{id}") 
	public TRCRegisteration viewByID(@PathVariable("id") int id) ;
	
	
	@PostMapping("/TypeApproved/update") 
	public GenricResponse updateApproved(@RequestBody TRCRegisteration model) ;
	

	@PostMapping("/TypeApproved/approveReject") 
	public GenricResponse TypeApproveReject(@RequestBody TypeApprovedStatusModel model) ;
	
	@PostMapping("/TypeApproved/delete") 
	public GenricResponse TypeApproveDelete(@RequestParam(name="id",required = false ) Integer id) ;
	
}
