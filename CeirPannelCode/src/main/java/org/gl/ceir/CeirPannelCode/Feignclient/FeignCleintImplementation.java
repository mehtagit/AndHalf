package org.gl.ceir.CeirPannelCode.Feignclient;
import java.io.File;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.ConsignmentFilterPojo;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.StockUploadModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import feign.Headers;


@Service
@FeignClient(url = "http://13.233.39.58:9090/CEIR",value = "dsj" )
public interface FeignCleintImplementation {
	
	
	//View all Consignment  feign  controller
	@RequestMapping(value="/consignment/Record" ,method=RequestMethod.GET) 
	public List<ConsignmentModel> consignmentList(@RequestParam long userId) ;

	
	
	//View filter Consignment  feign  controller
		@RequestMapping(value="/v1/filter/consignment" ,method=RequestMethod.GET) 
		public List<ConsignmentModel> consignmentFilter(FilterRequest filterrequest) ;

		
	
	
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
	
	
	
		
	
	//download file(Error or Uploaded file) feign  controller
	@RequestMapping(value="/Download/uploadFile" ,method=RequestMethod.GET) 
		public @ResponseBody String downloadFile(@RequestParam("txnId") String txnId,@RequestParam("fileType") String fileType,@RequestParam("fileName") String fileName);

	
	
	
	//download file(Error or Uploaded file) feign  controller
		@RequestMapping(value="/stoke/Download/SampleFile" ,method=RequestMethod.GET) 
			public @ResponseBody String downloadSampleFile(@RequestParam("samplFileType") String fileType);

	
	/// ****************************************Stock Api Integrreation******************************************************************************************
		
		
		// @RequestLine("POST /consignment/upload")
		@PostMapping(value="/Stock/upload")
	    public GenricResponse uploadStock(StockUploadModel stockUploadModel); 

		
}

