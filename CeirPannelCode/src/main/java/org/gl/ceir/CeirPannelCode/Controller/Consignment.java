package org.gl.ceir.CeirPannelCode.Controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Service.ConsignmentService;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/Consignment")
@Controller

public class Consignment {


	@Autowired
	
	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	UtilDownload utildownload;
	/*
	 * @Autowired ConsignmentService consignmentService;
	 */

	private final Logger log = LoggerFactory.getLogger(getClass());

	
	  @RequestMapping(value=
	  {"/viewConsignment"},method={org.springframework.web.bind.annotation.
	  RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
	  ) public ModelAndView viewConsignment() {
	  log.info(" view consignment entry point."); long id=1; List<ConsignmentModel>
	  consignmentdetails=feignCleintImplementation.consignmentList(id);
	  
	  ModelAndView mv = new ModelAndView(); mv.setViewName("viewConsignment");
	  mv.addObject("consignmentdetails", consignmentdetails);
	  log.info(" view consignment exit point."); 
	  return mv; 
	  }
	  
	  
	  @RequestMapping(value=
		  {"/filterConsignment"},method={org.springframework.web.bind.annotation.RequestMethod.POST}) 
	  public @ResponseBody List<ConsignmentModel>filterConsignment(@RequestBody FilterRequest filterRequest) {
		  
		  log.info("view consignment filter  entry point."+filterRequest);
		  List<ConsignmentModel>
		  consignmentdetails=feignCleintImplementation.consignmentFilter(filterRequest);
		  log.info("fillter Data=="+consignmentdetails);
		  log.info(" view consignment exit point."); 
		  return consignmentdetails; 
		  }
	 


	/*
	 * @RequestMapping(value={"/viewConsignment"},method={org.springframework.web.
	 * bind.annotation.
	 * RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
	 * ) public ModelAndView viewConsignment() { ModelAndView modelAndView=new
	 * ModelAndView("viewConsignment"); return modelAndView; }
	 * 
	 * @GetMapping
	 * 
	 * @RequestMapping("/viewConsignmentList" ) public ResponseEntity<?>
	 * viewConsignmentList() { long id=1; List<ConsignmentModel>
	 * consignmentdetails=feignCleintImplementation.consignmentList(id); return new
	 * ResponseEntity<>(consignmentdetails, HttpStatus.OK); }
	 */

	@RequestMapping(value= {"/registerConsignment"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public @ResponseBody GenricResponse registerConsignment(@RequestParam(name="supplierId",required = false) String supplierId,@RequestParam(name="supplierName",required = false) String supplierName
			,@RequestParam(name="consignmentNumber",required = false) String consignmentNumber,@RequestParam(name="expectedArrivaldate",required = false) String expectedArrivalDate,
			@RequestParam(name="organisationcountry",required = false) String organisationcountry,@RequestParam(name="expectedDispatcheDate",required = false) String expectedDispatcheDate,
			@RequestParam(name="expectedArrivalPort",required = false) String expectedArrivalPort,@RequestParam(name="quantity",required = false) String quantity,@RequestParam(name="file",required = false) MultipartFile file) {

		log.info(" Register consignment entry point.");

		String txnNumner=utildownload.getTxnId();
		txnNumner = "C"+txnNumner;
		
		
		log.info("txnid="+txnNumner);
		
		ConsignmentModel consignment = new ConsignmentModel();
        try {
		byte[] bytes = file.getBytes();
		String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnNumner+"/";
		File dir = new File(rootPath + File.separator);
         
		if (!dir.exists()) 
			dir.mkdirs();
		// Create the file on server
		// Calendar now = Calendar.getInstance();

		File serverFile = new File(rootPath+file.getOriginalFilename());
		log.info("COMPLETE PATH" + serverFile);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();
	
         }
        catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
		log.info(" submit register form block.");
	

		// set reaquest parameters into model class
		consignment.setSupplierId(supplierId);
		consignment.setSupplierName(supplierName);
		consignment.setConsignmentNumber(consignmentNumber);
		consignment.setExpectedDispatcheDate(expectedDispatcheDate);
		consignment.setExpectedArrivaldate(expectedArrivalDate);
		consignment.setOrganisationCountry(organisationcountry);
		consignment.setExpectedArrivalPort(expectedArrivalPort);
		consignment.setQuantity(quantity);
		consignment.setTxnId(txnNumner);
		consignment.setFileName(file.getOriginalFilename());
		consignment.setUserId(Long.valueOf(1));
		consignment.setImporterName("sharad yadav");
		consignment.setTaxPaidStatus("Not Paid");

		log.info("consignment object "+consignment.toString());
		log.info(file.getOriginalFilename());
		GenricResponse response = feignCleintImplementation.addConsignment(consignment);
		log.info("response from feign client=="+response.toString());
		return response;


	}



	//************************************************ Open consignment record  page********************************************************************************/

	@RequestMapping(value= {"/updateRegisterConsignment"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public @ResponseBody GenricResponse openconsignmentRecordPage(@RequestParam(name="supplierId",required = false) String supplierId,@RequestParam(name="supplierName",required = false) String supplierName
			,@RequestParam(name="consignmentNumber",required = false) String consignmentNumber,@RequestParam(name="expectedArrivaldate",required = false) String expectedArrivalDate,
			@RequestParam(name="organisationcountry",required = false) String organisationcountry,@RequestParam(name="expectedDispatcheDate",required = false) String expectedDispatcheDate,
			@RequestParam(name="expectedArrivalPort",required = false) String expectedArrivalPort,@RequestParam(name="quantity",required = false) String quantity,
			@RequestParam(name="file",required = false) MultipartFile file,@RequestParam(name="filename",required = false) String filename,@RequestParam(name="txnId",required = false) String txnId) {
		ConsignmentModel consignment = new ConsignmentModel();
		GenricResponse response= new GenricResponse();
		log.info("entry point  in update Consignment ** **.");
		
	    
	    log.info("file name without upload file="+filename);
		
		if(file==null)
		{
	        log.info("message  in file upload block ");
			
			consignment.setSupplierId(supplierId);
			consignment.setSupplierName(supplierName);
			consignment.setConsignmentNumber(consignmentNumber);
			consignment.setExpectedDispatcheDate(expectedDispatcheDate);
			consignment.setExpectedArrivaldate(expectedArrivalDate);
			consignment.setOrganisationCountry(organisationcountry);
			consignment.setExpectedArrivalPort(expectedArrivalPort);
			consignment.setQuantity(quantity);
			consignment.setTxnId(txnId);
			consignment.setFileName(filename);
			consignment.setUserId(Long.valueOf(1));
			consignment.setImporterName("sharad yadav");
			consignment.setTaxPaidStatus("Not Paid");
			
			log.info(consignment.toString());
		}
		else {
			
		
			
			log.info("file is empty or not "+file.isEmpty());
			 try {
					byte[] bytes = file.getBytes();
					String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnId+"/";
					File dir = new File(rootPath + File.separator);
			         
					if (!dir.exists()) 
						dir.mkdirs();
					// Create the file on server
					// Calendar now = Calendar.getInstance();

					File serverFile = new File(rootPath+file.getOriginalFilename());
					log.info("COMPLETE PATH" + serverFile);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				
			         }
			        catch (Exception e) {
						// TODO: handle exception
			        	e.printStackTrace();
					}
			
			
			log.info("message after file upload block ");
			
			consignment.setSupplierId(supplierId);
			consignment.setSupplierName(supplierName);
			consignment.setConsignmentNumber(consignmentNumber);
			consignment.setExpectedDispatcheDate(expectedDispatcheDate);
			consignment.setExpectedArrivaldate(expectedArrivalDate);
			consignment.setOrganisationCountry(organisationcountry);
			consignment.setExpectedArrivalPort(expectedArrivalPort);
			consignment.setQuantity(quantity);
			consignment.setTxnId(txnId);
			consignment.setFileName(filename);
			consignment.setUserId(Long.valueOf(1));
			consignment.setImporterName("sharad yadav");
			consignment.setTaxPaidStatus("Not Paid");
			
			log.info(consignment.toString());

		}

		 response = feignCleintImplementation.updateConsignment(consignment);
			log.info(" update consignment form block.");
			
			// calling service method
		//	String response=service.updateConsignmnet(consignment, file, txnid);
			
		
		log.info(" update consignment exit point.");
		return response;

	}





	//************************************************ delete consignment record  page********************************************************************************/

	@RequestMapping(value= {"/deleteConsignment"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public @ResponseBody GenricResponse deleteConsignment(@RequestBody ConsignmentModel consignmentModel) {
		
		log.info("enter in  delete consignment.");
		GenricResponse response=feignCleintImplementation.deleteConsignment(consignmentModel);
		log.info("response after delete consignment."+response);
		return response;

	}

	


	// *********************************************** open register page or edit page ******************************
	@RequestMapping(value="/openRegisterConsignmentForm",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView openRegisterConsignmentForm(@RequestParam(name="reqType") String reqType,@RequestParam(name="txnId",required = false) String txnId)
	{
		ModelAndView mv= new ModelAndView();
		if(reqType.equals("formPage"))
		{
			log.info("open registration Consignment form");
			mv.setViewName("registerConsignment");
		}
		else if(reqType.equals("editPage")) {
			ConsignmentModel  consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
			log.info("consignment details="+consignmentdetails);
			
			log.info("open Update registration Consignment form");
			mv.setViewName("editConsignment");
			mv.addObject("consignmentdetails", consignmentdetails);
		}
		else {
			ConsignmentModel  consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
			log.info("consignment details="+consignmentdetails);
			
			log.info("open view  registration Consignment form");
			mv.setViewName("viewConsignmentRecord");
			mv.addObject("consignmentdetails", consignmentdetails);
			
		}
		return mv;

	}
	
	// *********************************************** open register page or edit popup ******************************
	@RequestMapping(value="/openRegisterConsignmentPopup",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public @ResponseBody ConsignmentModel openRegisterConsignmentPopup(@RequestParam(name="reqType") String reqType,@RequestParam(name="txnId",required = false) String txnId)
	{
		ConsignmentModel  consignmentdetails= new ConsignmentModel();
		if(reqType.equals("editPage")) {
			consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
			log.info("consignment details="+consignmentdetails);
			log.info("open Update registration Consignment popup");
			
		}
		else {
			consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
			log.info("consignment details="+consignmentdetails);
			log.info("open view  registration Consignment pop up");
		}
		return consignmentdetails;

	}

	@RequestMapping(value="/demo/{reqType}",method={org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public @ResponseBody ConsignmentModel demo(@RequestParam(name="supplierId") String supplierId,@RequestParam(name="supplierName") String supplierName
			,@RequestParam(name="consignmentNumber") String consignmentNumber,@RequestParam(name="expectedArrivalDate") String expectedArrivalDate,
			@RequestParam(name="organisationcountry") String organisationcountry,@RequestParam(name="expectedDispatcheDate") String expectedDispatcheDate,
			@RequestParam(name="expectedArrivalPort") String expectedArrivalPort,@RequestParam(name="quantity") String quantity,@RequestParam(name="file") MultipartFile file,@PathVariable("reqType") String reqType)  {


		ConsignmentModel consignment = new ConsignmentModel();

		consignment.setSupplierId(supplierId);
		consignment.setSupplierName(supplierName);
		consignment.setConsignmentNumber(consignmentNumber);
		consignment.setExpectedDispatcheDate(expectedDispatcheDate);
		consignment.setExpectedArrivalDate(expectedArrivalDate);
		consignment.setOrganisationcountry(organisationcountry);
		consignment.setExpectedArrivalPort(expectedArrivalPort);
		consignment.setQuantity(quantity);

		log.info("request type=="+reqType);
		log.info("consignment object "+consignment.toString());
		log.info(file.getOriginalFilename());


		return consignment;

	}
	
	//**************************************************  download file  **************************************************************** 
		@RequestMapping(value="/dowloadFiles/{filetype}/{fileName}/{transactionNumber}",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
		public  String downloadFile(@PathVariable("transactionNumber") String txnid,@PathVariable("fileName") String fileName,@PathVariable("filetype") String filetype) throws IOException {
			
			log.info("inside file download method");
			System.out.println("transacation id="+txnid+" fileName="+fileName+" fileType="+filetype);
			log.info("txnid"+txnid+" fileName "+fileName+" fileType "+filetype);
			String response=feignCleintImplementation.downloadFile(txnid,filetype,fileName);
			log.info(response);
		
			return "redirect:"+response;

		}
		
		
		//************************************************ Download Sampmle file **************************************************
		@RequestMapping(value="/sampleFileDownload/{filetype}",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
		public  String downloadSampleFile(@PathVariable("filetype") String filetype) throws IOException {
			System.out.println("inside file download method");
			System.out.println(" fileType="+filetype);
			String response=feignCleintImplementation.downloadSampleFile(filetype);
			log.info(response);
		
			return "redirect:"+response;

		}

}
