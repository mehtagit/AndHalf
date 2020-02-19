package org.gl.ceir.CeirPannelCode.Feignclient;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.ActionModel;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentUpdateRequest;
import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.GrievanceDropdown;
import org.gl.ceir.CeirPannelCode.Model.StockUploadModel;
import org.gl.ceir.CeirPannelCode.Model.StolenRecoveryModel;
import org.gl.ceir.CeirPannelCode.Model.Tag;
import org.gl.ceir.pagination.model.AuditContentModel;
import org.gl.ceir.pagination.model.ConfigContentModel;
import org.gl.ceir.pagination.model.MessageContentModel;
import org.gl.ceir.pagination.model.PolicyConfigContent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Service
@FeignClient(url = "${feignClientPath}",value = "dsj" )
public interface FeignCleintImplementation {


	//View all Consignment  feign  controller
	@RequestMapping(value="/consignment/Record" ,method=RequestMethod.GET) 
	public List<ConsignmentModel> consignmentList(@RequestParam long userId) ;



	//View filter Consignment  feign  controller
	@RequestMapping(value="/v2/filter/consignment" ,method=RequestMethod.GET) 
	public Object consignmentFilter(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) ;




	//Add new  Consignment  feign  controller
	//@RequestMapping(value="/consignment/upload" ,method=RequestMethod.POST) 

	// @PostMapping(value="/consignment/upload")

	// @RequestLine("POST /consignment/upload")
	@PostMapping(value="/consignment/register")
	public GenricResponse addConsignment(ConsignmentModel consignment); 


	//***************************************************** update consignment feign ******************************************************************/ 
	@PostMapping(value="/consignment/update")
	public GenricResponse updateConsignment(ConsignmentModel consignment) ;





	//edit Consignment feign  controller
	@RequestMapping(value="/consignment/view" ,method=RequestMethod.GET) 
	public @ResponseBody ConsignmentModel fetchConsignmentByTxnId(@RequestParam("txnId") String txnId) ;


	//delete Consignment feign  controller
	@RequestMapping(value="/consigment/delete" ,method=RequestMethod.DELETE) 
	public @ResponseBody GenricResponse deleteConsignment(ConsignmentModel consignmentModel,@RequestParam("userType") String userType) ;

	//accept reject Consignment feign  controller
	@RequestMapping(value="/update/consigmentStatus" ,method=RequestMethod.PUT) 
	public @ResponseBody GenricResponse updateConsignmentStatus(ConsignmentUpdateRequest consignmentUpdateRequest) ;





	//download file(Error or Uploaded file) feign  controller
	@RequestMapping(value="/Download/uploadFile" ,method=RequestMethod.GET) 
	public @ResponseBody FileExportResponse downloadFile(@RequestParam("txnId") String txnId,@RequestParam("fileType") String fileType,@RequestParam("fileName") String fileName,@RequestParam(name="tag",required = false) String tag);




	//download file(Error or Uploaded file) feign  controller
	@RequestMapping(value="/Download/SampleFile" ,method=RequestMethod.GET) 
	public @ResponseBody FileExportResponse downloadSampleFile(@RequestParam("featureId") Integer featureId);


	/// **************************************** Stock Api integration ******************************************************************************************


	// @RequestLine("POST /stock/upload")
	@PostMapping(value="/Stock/upload")
	public GenricResponse uploadStock(StockUploadModel stockUploadModel); 

	/// **************************************** Stock Api integration ******************************************************************************************


	//delete stock feign  controller
	@RequestMapping(value="/stock/delete" ,method=RequestMethod.POST) 
	public @ResponseBody GenricResponse deleteStock(StockUploadModel stockUploadModel,@RequestParam("userType") String userType) ;


	//edit stock feign  controller
	@RequestMapping(value="/stock/view" ,method=RequestMethod.POST) 
	public @ResponseBody StockUploadModel fetchUploadedStockByTxnId(StockUploadModel stockUploadModel) ;




	//***************************************************** update uploaded Stock feign ******************************************************************/ 
	@PostMapping(value="/Stock/update")
	public GenricResponse updateStock(StockUploadModel stockUploadModel) ;



	@RequestMapping(value="filter/stakeholder/record" ,method=RequestMethod.POST) 
	public Object stolenFilter(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) ;


	
	//accept reject stock feign  controller
		@RequestMapping(value="/accept-reject/stock" ,method=RequestMethod.PUT) 
		public @ResponseBody GenricResponse acceptRejectStock(ConsignmentUpdateRequest consignmentUpdateRequest) ;

	//****************************************************                              ***************************************************************************		
	//**************************************************** Stolen Recovery integration  **************************************************************************
	//****************************************************                              **************************************************************************			


	@RequestMapping(value="/stock/record" ,method=RequestMethod.POST) 
	public Object stockFilter(@RequestBody FilterRequest filterRequest,
			@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "file", defaultValue = "0") Integer file) ;



	//********************************************  upload multiple Stolen and Recovery ***************************************************************88
	@RequestMapping(value="/stakeholder/uploadMultiple/StolenAndRecovery" ,method=RequestMethod.POST) 
	public GenricResponse multipleStolen(@RequestBody List<StolenRecoveryModel> request) ;






	//**************************************************************** file Stolen type ***************************************************************************************************		


	@RequestMapping(value="/stakeholder/Stolen" ,method=RequestMethod.POST) 
	public GenricResponse fileStolen(@RequestBody StolenRecoveryModel request) ;




	//**************************************************************** file Recovery type ***************************************************************************************************		


	@RequestMapping(value="/stakeholder/Recovery" ,method=RequestMethod.POST) 
	public GenricResponse fileRecovery(@RequestBody StolenRecoveryModel request) ;


	//delete stolen recovery feign  controller
	@RequestMapping(value="/stakeholder/Delete" ,method=RequestMethod.DELETE) 
	public @ResponseBody GenricResponse deleteStolenRecovery(StolenRecoveryModel stolenRecoveryModel) ;
	/************* DROPDOWN *****************/

	@RequestMapping(value="/state-mgmt/{featureId}/{userTypeId}" ,method=RequestMethod.GET) 
	public List<Dropdown> consignmentStatusList(@PathVariable("featureId") Integer featureId,@PathVariable("userTypeId") Integer userTypeId);


	@RequestMapping(value="system-config-list/{tag}" ,method=RequestMethod.GET) 
	public List<Dropdown> taxPaidStatusList(@PathVariable("tag") String tag);

	//**************************************************************** file Stolen type ***************************************************************************************************		


	@RequestMapping(value="/stakeholder/update" ,method=RequestMethod.PUT) 
	public GenricResponse updateFileStolen(@RequestBody StolenRecoveryModel request) ;




	
	
	
	
	
	//Dashboard/Datatable Feign
		@RequestMapping(value="/v2/history/Notification" ,method=RequestMethod.GET) 
		public Object dashBoardNotification(@RequestBody FilterRequest filterRequest,
		@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) ;	
		
		
		
		
		

		//***************************************************Admin Registration as Type Dropdown********************************


		@RequestMapping(value="/system-config-list/by-tag-and-usertype/{tagId}/{userTypeId}" ,method=RequestMethod.GET) 
		public List<Dropdown> asTypeList(@PathVariable("tagId") String tag, @PathVariable("userTypeId") Integer userTypeId);


		@PostMapping("/system/viewTag")    
		public Dropdown dataByTag(Tag tag);       
		

		// fetch block/Unblock(bulk) devices.
		
				//edit stock feign  controller
				@RequestMapping(value="/stolen-and-recovery/by-txnId" ,method=RequestMethod.POST) 
				public @ResponseBody StolenRecoveryModel fetchBulkDeviceByTxnId(StolenRecoveryModel stolenRecoveryModel) ;

				
				//***************************************************Admin System message Management Feign********************************
				
				@RequestMapping(value="/filter/message-configuration" ,method=RequestMethod.POST) 
				public Object adminMessageFeign(@RequestBody FilterRequest filterRequest,
						@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
						@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
						@RequestParam(value = "file", defaultValue = "0") Integer file) ;

				
				//***************************************************Admin System Config Management Feign********************************
				
				@RequestMapping(value="/filter/system-configuration" ,method=RequestMethod.POST) 
				public Object adminConfigFeign(@RequestBody FilterRequest filterRequest,
						@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
						@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
						@RequestParam(value = "file", defaultValue = "0") Integer file) ;
				
		

			
//***************************************************Admin System Config Management Feign********************************

@RequestMapping(value="/filter/policy-configuration" ,method=RequestMethod.POST) 
public Object policyManagementFeign(@RequestBody FilterRequest filterRequest,
		@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
		@RequestParam(value = "file", defaultValue = "0") Integer file) ;

	

//************************************************ view Policy Config Feign *****************************************

@PostMapping("/policy/viewTag")
public @ResponseBody PolicyConfigContent viewPolicyConfigFeign(FilterRequest filterRequest);



//************************************************ view icon Message Management Feign *****************************************

@PostMapping("/message/viewTag")
public @ResponseBody MessageContentModel viewMessageFeign(FilterRequest filterRequest);


@PostMapping("/system/viewTag")
public @ResponseBody ConfigContentModel viewAdminFeign(FilterRequest filterRequest);


		@RequestMapping(value="/system-config-list/by-tag-and-featureid/{tagId}/{featureId}" ,method=RequestMethod.GET) 
		public List<Dropdown> modeType(@PathVariable("tagId") String tagId, @PathVariable("featureId") Integer featureId);
		
		//******************************* Block Unblock Approve/Reject Devices Feign ********************************
		
				@PutMapping("/accept-reject/stolen-recovery-block-unblock")
				public @ResponseBody GenricResponse approveRejectFeign(FilterRequest FilterRequest);
				
				
				//************************************ Table Actions Feign  *************************************************
				
				@RequestMapping(value="/table-actions/{featureId}/{userTypeId}" ,method=RequestMethod.GET) 
				public List<ActionModel> tableActionFeign(@PathVariable("featureId") Integer featureId,@PathVariable("userTypeId") Integer userTypeId);
				
		//************************************ Policy update Feign  *************************************************
				
				@PutMapping(value="/policy/update")
				public @ResponseBody PolicyConfigContent updatePolicy(PolicyConfigContent policyConfigContent);
				
				
				//************************************ Message update Feign  *************************************************
				
				@PutMapping(value="/message/update")
				public @ResponseBody MessageContentModel updateMessages(MessageContentModel messageContentModel);
				
				//************************************ System update Feign  *************************************************
				
				@PutMapping(value="/system/update")
				public @ResponseBody ConfigContentModel updateSystem(ConfigContentModel configContentModel);
				//***************************************************Audit Management Feign********************************

				@RequestMapping(value="/filter/audit-trail" ,method=RequestMethod.POST) 
				public Object auditManagementFeign(@RequestBody FilterRequest filterRequest,
						@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
						@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
						@RequestParam(value = "file", defaultValue = "0") Integer file) ;

			//************************************************ view Audit Management Feign *************************************

				@RequestMapping(value="/audit-trail/{id}" ,method=RequestMethod.GET) 
				public AuditContentModel viewAuditManagementFeign(@PathVariable("id") Integer id);

//************************************ Message update Feign  *************************************************
				
				@PostMapping(value="/checkDevice")
				public @ResponseBody GenricResponse viewDetails(FilterRequest filterRequest);
					
				//************************************ manage User Feign  *************************************************

				@RequestMapping(value="/filter/end-users" ,method=RequestMethod.POST) 
				public Object manageUserFeign(@RequestBody FilterRequest filterRequest,
						@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
						@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
						@RequestParam(value = "file", defaultValue = "0") Integer file);
		
				@PostMapping("/get/tags-mapping")
				public @ResponseBody List<GrievanceDropdown> catagoryDropdownListFeign(FilterRequest filterRequest);	
		

				//download file(Error or Uploaded file) feign  controller
				@RequestMapping(value="/Download/manuals" ,method=RequestMethod.GET) 
				public @ResponseBody FileExportResponse manualDownloadSampleFile();

}
		







