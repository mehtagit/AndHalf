package org.gl.ceir.CeirPannelCode.Controller;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.gl.ceir.CeirPannelCode.Model.*;
import org.gl.ceir.CeirPannelCode.Service.ConsignmentService;

@RequestMapping(value="/Consignment")
@Controller
public class Consignment {
	
	/*
	 * @Autowired ConsignmentService consignmentService;
	 */
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value= {"/viewConsignment"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public ModelAndView viewConsignment() {
		log.info(" view consignment entry point.");
		System.out.println("view consignment entry point.");
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("viewConsignment");
		log.info(" view consignment exit point.");
		return mv;
	}
	

	
	@RequestMapping(value= {"/registerConsignment/{requestType}"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public ModelAndView registerConsignment(@ModelAttribute(name="consignment") ConsignmentModel consignment,@PathVariable(name="requestType",required = false) String pagetype,@RequestParam(name="file",required = false) MultipartFile file) {
		log.info(" Register consignment entry point.");
		ModelAndView mv = new ModelAndView();
		
		// open register consignment form
		if(pagetype.equals("formPage")) {
			log.info(" open register form.");
			mv.setViewName("registerConsignment");
		}
		
		
		//submit register consignment form
		else {
			log.info(" submit register form block.");
			// calling service method
			ConsignmentService service= new ConsignmentService();
			String responce =service.registerConsignmnet(consignment,file);
			if(responce=="success")
			{
			mv.setViewName("redirect:/Consignment/viewConsignment");
			}
			else {
				mv.setViewName("redirect:/Consignment/registerConsignment/formPage");	
			}
		}
		log.info(" Register consignment exit point.");
		// return view page  according to response
		return mv;
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
	 
}
