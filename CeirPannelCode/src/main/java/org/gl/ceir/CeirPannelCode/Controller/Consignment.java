package org.gl.ceir.CeirPannelCode.Controller;


import org.slf4j.LoggerFactory;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentPojo;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/Consignment")
@Controller
public class Consignment {
	
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
	public ModelAndView demo(@ModelAttribute( name="consignment") ConsignmentPojo consignment,@PathVariable(name="requestType",required = false) String pagetype) {
		log.info(" Register consignment entry point.");
		ModelAndView mv = new ModelAndView();
		System.out.println("request type==="+pagetype);
		System.out.println("pojo value="+consignment);
		if(pagetype.equals("formPage")) {
			System.out.println("pojo is null");
			mv.setViewName("registerConsignment");
			
		}
		else {
			System.out.println("pojo is filled");
			mv.setViewName("registerConsignment");
		}
		 
		mv.setViewName("registerConsignment");
		log.info(" Register consignment exit point.");
		return mv;
	}
	
	

	//************************************************ Open consignment record  page********************************************************************************/
	
		@RequestMapping(value= {"/openViewConsignmentRecord/{txnid}"},method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
		public ModelAndView openconsignmentRecordPage(@PathVariable("txnid") String  txnid) {
			System.out.println("inside view consignment page open method"+txnid);
			ModelAndView mv = new ModelAndView(); 
			mv.setViewName("viewConsignmentRecord"); 
			return mv;

		}
		
		

		//************************************************ Open consignment record  page********************************************************************************/
		
			@RequestMapping(value= {"/openEditConsignment/{txnid}"},method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
			public ModelAndView openconsignmentPage(@PathVariable("txnid") String  txnid) {
				System.out.println("inside view consignment page open method"+txnid);
				ModelAndView mv = new ModelAndView(); 
				mv.setViewName("editConsignment"); 
				return mv;

			}
		
	 
}
