package org.gl.ceir.CeirPannelCode.Feignclient;
import java.io.File;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.ConsignmentFilterPojo;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
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

	
	
	
	//Add new  Consignment  feign  controller
	//@RequestMapping(value="/consignment/upload" ,method=RequestMethod.POST) 
	
	// @PostMapping(value="/consignment/upload")
	
	// @RequestLine("POST /consignment/upload")
	@PostMapping(value="/consignment/register")
    public GenricResponse addConsignment(ConsignmentModel consignment); 

	
	//***************************************************** update consignment feign ******************************************************************/ 
	@PostMapping(value="/consignment/update")
	public ConsignmentModel updateConsignment(@RequestParam("consignmentNumber") String consignmentNumber,@RequestParam("expectedArrivalPort") String expectedArrivalPort,@RequestParam("expectedArrivaldate") String expectedArrivaldate
			,@RequestParam("expectedDispatcheDate") String expectedDispatcheDate,@RequestParam("fileName") String fileName,@RequestParam("path") String filePath,@RequestParam("importerId") int importerId,@RequestParam("importerName") String importerName,
			@RequestParam("organisationCountry") String organisationCountry,@RequestParam("supplierId") String supplierId,@RequestParam("supplierName") String supplierName,@RequestParam("txnId") String txnid,@RequestParam("quantity") String quantity) ;

	

	
	
	//edit Consignment feign  controller
		@RequestMapping(value="/consignment/Record" ,method=RequestMethod.GET) 
		public @ResponseBody ConsignmentModel fetchConsignmentByTxnId(@RequestParam("txnId") String txnId) ;
		
	
	//delete Consignment feign  controller
	@RequestMapping(value="/consigment/Delete" ,method=RequestMethod.DELETE) 
	public @ResponseBody ConsignmentModel deleteConsignment(@RequestParam("txnId") String txnId) ;
	
	
	
	//filter  Consignment  feign  controller
		@RequestMapping(value="/consignment/filterDetails" ,method=RequestMethod.POST) 
		public List<ConsignmentModel> filterConsignmentdata(@RequestBody ConsignmentFilterPojo pojo) ;

		
		
	
	//download file(Error or Uploaded file) feign  controller
	@RequestMapping(value="/stoke/Download/uploadFile" ,method=RequestMethod.GET) 
		public @ResponseBody String downloadFile(@RequestParam("txnId") String txnId,@RequestParam("fileType") String fileType,@RequestParam("fileName") String fileName);

	
	
	
	//download file(Error or Uploaded file) feign  controller
		@RequestMapping(value="/stoke/Download/SampleFile" ,method=RequestMethod.GET) 
			public @ResponseBody String downloadSampleFile(@RequestParam("samplFileType") String fileType);

		
	/*
	 * @PostMapping("/MobileOperators/") public void saveOperator(@RequestBody
	 * Operator operator);
	 */ 

	/*
	 * @PutMapping("/MobileOperators/{id}") void
	 * editOperatorById(@PathVariable("id") int id,@RequestBody Operator op);
	 * 
	 * @DeleteMapping("/MobileOperators/{id}") void
	 * deleteOperatorById(@PathVariable("id") int id);
	 * 
	 * @GetMapping("/MediationSource/") public List<Mediation> getMediationData();
	 * 
	 * @PostMapping("/MediationSource/") public void addMediationData(@RequestBody
	 * Mediation mediation);
	 * 
	 * @PutMapping("/MediationSource/{id}") public void editMediation(@PathVariable
	 * int id,Mediation mediation);
	 * 
	 * @DeleteMapping("/MediationSource/{id}") public void
	 * deleteMediation(@PathVariable("id") int id);
	 */
}

