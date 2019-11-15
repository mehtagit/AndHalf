package org.gl.ceir.CeirPannelCode.Feignclient;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentUpdateRequest;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.StockUploadModel;
import org.gl.ceir.CeirPannelCode.Model.StolenRecoveryModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Service
@FeignClient(url = "http://13.233.39.58:9090/CEIR",value = "dsj" )
public interface FeignCleintImplementation {
	
	
	//View all Consignment  feign  controller
	@RequestMapping(value="/consignment/Record" ,method=RequestMethod.GET) 
	public List<ConsignmentModel> consignmentList(@RequestParam long userId) ;

	
	
	//View filter Consignment  feign  controller
		@RequestMapping(value="/v2/filter/consignment" ,method=RequestMethod.GET) 
		public Object consignmentFilter(@RequestBody FilterRequest filterRequest,
				@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
				@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) ;

		
	
	
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
	public @ResponseBody GenricResponse deleteConsignment(ConsignmentModel consignmentModel) ;
	
	//delete Consignment feign  controller
		@RequestMapping(value="/update/consigmentStatus" ,method=RequestMethod.PUT) 
		public @ResponseBody GenricResponse updateConsignmentStatus(ConsignmentUpdateRequest consignmentUpdateRequest) ;
		
	
	
		
	
	//download file(Error or Uploaded file) feign  controller
	       @RequestMapping(value="/Download/uploadFile" ,method=RequestMethod.GET) 
	     	public @ResponseBody String downloadFile(@RequestParam("txnId") String txnId,@RequestParam("fileType") String fileType,@RequestParam("fileName") String fileName);

	
	
	
	//download file(Error or Uploaded file) feign  controller
		    @RequestMapping(value="/stoke/Download/SampleFile" ,method=RequestMethod.GET) 
			public @ResponseBody String downloadSampleFile(@RequestParam("samplFileType") String fileType);

	
	/// **************************************** Stock Api integration ******************************************************************************************
		
		
		    // @RequestLine("POST /stock/upload")
		    @PostMapping(value="/Stock/upload")
	        public GenricResponse uploadStock(StockUploadModel stockUploadModel); 

	/// **************************************** Stock Api integration ******************************************************************************************
		
		
 //delete stock feign  controller
		    @RequestMapping(value="/stock/delete" ,method=RequestMethod.POST) 
		    public @ResponseBody GenricResponse deleteStock(StockUploadModel stockUploadModel) ;
		

		    //edit stock feign  controller
			@RequestMapping(value="/stock/view" ,method=RequestMethod.POST) 
			public @ResponseBody StockUploadModel fetchUploadedStockByTxnId(StockUploadModel stockUploadModel) ;
			

			
			
			//***************************************************** update uploaded Stock feign ******************************************************************/ 
			@PostMapping(value="/Stock/update")
			public GenricResponse updateStock(StockUploadModel stockUploadModel) ;

	
			
			@RequestMapping(value="/stakeholder/record" ,method=RequestMethod.POST) 
			public Object stolenFilter(@RequestBody FilterRequest filterRequest,
					@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
					@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) ;
		
			
//****************************************************                              ***************************************************************************		
//**************************************************** Stolen Recovery integration  **************************************************************************
//****************************************************                              **************************************************************************			
			
		
		@RequestMapping(value="/stock/record" ,method=RequestMethod.POST) 
		public Object stockFilter(@RequestBody FilterRequest filterRequest,
				@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
				@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) ;
		
		
		
//********************************************  upload multiple Stolen and Recovery ***************************************************************88
		@RequestMapping(value="/stakeholder/uploadMultiple/Stolen" ,method=RequestMethod.POST) 
		public GenricResponse multipleStolen(@RequestBody List<StolenRecoveryModel> request) ;



	
		
	
//**************************************************************** file Stolen type ***************************************************************************************************		
		

		@RequestMapping(value="/stakeholder/Stolen" ,method=RequestMethod.POST) 
		public GenricResponse fileStolen(@RequestBody StolenRecoveryModel request) ;



		
//**************************************************************** file Recovery type ***************************************************************************************************		
				

		@RequestMapping(value="/stakeholder/Recovery" ,method=RequestMethod.POST) 
		public GenricResponse fileRecovery(@RequestBody StolenRecoveryModel request) ;


	
}

