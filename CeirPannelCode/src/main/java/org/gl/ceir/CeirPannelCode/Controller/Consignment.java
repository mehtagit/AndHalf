package org.gl.ceir.CeirPannelCode.Controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/Consignment")
@Controller
@ResponseBody
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
	  
	  log.info("consignment details feign client...."+consignmentdetails.toString()
	  ); ModelAndView mv = new ModelAndView(); mv.setViewName("viewConsignment");
	  mv.addObject("consignmentdetails", consignmentdetails);
	  log.info(" view consignment exit point."); 
	  return mv; 
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
	public GenricResponse registerConsignment(@RequestParam(name="supplierId",required = false) String supplierId,@RequestParam(name="supplierName",required = false) String supplierName
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
		// calling service method
		ConsignmentService service= new ConsignmentService();

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

	@RequestMapping(value= {"/updateRegisterConsignment/{txnid}/{formRequestType}"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public ModelAndView openconsignmentRecordPage(@ModelAttribute(name="consignment") ConsignmentModel consignment,  @PathVariable(name="txnid",required = false) String  txnid,@PathVariable("formRequestType") String formRequestType,  @RequestParam(name="file",required =false) MultipartFile file) {
		ModelAndView mv = new ModelAndView(); 
		ConsignmentService consignmentService= new ConsignmentService();
		log.info("entry point  in update Consignment .");

		log.info("formtype===="+formRequestType);

		//open edit consignment form
		if(formRequestType.equals("formpage"))
		{

			log.info("open  update Consignment  page.");
			consignment=consignmentService.fetchConsignmentData(txnid);
			mv.addObject("consignment", consignment);
			mv.setViewName("editConsignment");
		}
		//open view consignment form
		else if(formRequestType.equals("viewPage"))
		{
			log.info("open  view Consignment  form page.");
			consignment=consignmentService.fetchConsignmentData(txnid);
			mv.addObject("consignment", consignment);
			mv.setViewName("viewConsignmentRecord");
		}

		// submit update consignment form
		else {
			log.info(" update consignment form block.");
			ConsignmentService service= new ConsignmentService();
			// calling service method
			String response=service.updateConsignmnet(consignment, file, txnid);
			if(response=="success")
			{
				mv.setViewName("redirect:/Consignment/viewConsignment");
			}
			else {
				mv.setViewName("redirect:/Consignment/updateRegisterConsignment/formPage");	
			}
		}
		log.info(" update consignment exit point.");
		return mv;

	}





	//************************************************ delete consignment record  page********************************************************************************/

	@RequestMapping(value= {"/deleteConsignment/{txnid}"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public ModelAndView deleteConsignment(@PathVariable("txnid") String  txnid) {
		ModelAndView mv = new ModelAndView(); 
		ConsignmentService consignmentService= new ConsignmentService();
		String response=consignmentService.deleteConsignment(txnid);
		log.info("response="+response);
		if (response.equals("success"))
		{
			log.info("success");
			mv.setViewName("redirect:/Consignment/viewConsignment"); 
			return mv;
		}
		else {
			log.info("fail");
			mv.setViewName("redirect:/Consignment/viewConsignment"); 
			return mv;

		}

	}

	//**************************************************  download file  **************************************************************** 
	@RequestMapping(value="/dowloadFiles/{filetype}/{fileName}/{transactionNumber}",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
	public  String downloadFile(@PathVariable("transactionNumber") String txnid,@PathVariable("fileName") String fileName,@PathVariable("filetype") String filetype)  {
		ConsignmentService consignmentService= new ConsignmentService();

		String response=  consignmentService.downloadFile(txnid, fileName, filetype);

		return response;

	}


	// *********************************************** open register page or edit page ******************************
	@RequestMapping(value="/openRegisterConsignmentForm/{reqType}",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView openRegisterConsignmentForm(@PathVariable(name="reqType") String reqType)
	{
		ModelAndView mv= new ModelAndView();
		if(reqType.equals("formPage"))
		{
			log.info("open registration Consignment form");
			mv.setViewName("registerConsignment");
		}
		else {

			log.info("open Update registration Consignment form");
			mv.setViewName("editConsignment");
		}
		return mv;

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

}
