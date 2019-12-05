package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.NumberOfBox;
import org.gl.ceir.CeirPannelCode.Model.RequestCountAndQuantity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url = "${dashBoardfeignClientPath}",value = "dashoard" )
public interface DashboardFeignClient {
	
	//********************************************* Notification fiegn implementation ************************************************************************




	//countAndQuantity  feign  controller
	@RequestMapping(value="/consignment/countAndQuantity" ,method=RequestMethod.GET) 
	public RequestCountAndQuantity consignmentNotification(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "featureId") Integer featureId,@RequestParam(value = "userTypeId") Integer userTypeId ) ;

	//stock/countAndQuantity  feign  controller
	@RequestMapping(value="/stock/countAndQuantity" ,method=RequestMethod.GET) 
	public RequestCountAndQuantity stockNotification(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "stockStatus") Integer stockStatus,@RequestParam(value = "userTypeId") Integer userTypeId) ;

	//stock/countAndQuantity  feign  controller
	@RequestMapping(value="/stakeholder/count" ,method=RequestMethod.GET) 
	public RequestCountAndQuantity stolenRecoveryNotification(@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "requestType") String requestType,@RequestParam(value = "fileStatus") Integer fileStatus,@RequestParam(value = "userTypeId") Integer userTypeId);


	//stock/countAndQuantity  feign  controller
	@RequestMapping(value="/grievance/count" ,method=RequestMethod.GET) 
	public RequestCountAndQuantity grievanceNotification(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "grievanceStatus") Integer grievanceStatus,@RequestParam(value = "userTypeId") Integer userTypeId);

	//dashboard/dbConf  controller
		@RequestMapping(value="/dashboard/dbConf" ,method=RequestMethod.GET) 
		public List<NumberOfBox> dashBoardDBConf(@RequestParam(value = "userTypeId") Integer userTypeId);
	}
