package org.gl.ceir.CeirPannelCode.Controller;
import java.util.List;
import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadStockController {

	@Autowired
	FeignCleintImplementation feignImpl;
	

	@RequestMapping(value= {"/selectModuleType"},method={org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public ModelAndView selectModeuleType(@RequestParam("modeuleType") String  module) {
		System.out.println("inside dashboard method");
		ModelAndView mv = new ModelAndView(); 
		
		/*
		 * List<ConsignmentPojo> consignmentdetails=feignImpl.consignmentList(id);
		 * mv.addObject("consignmentdetails", consignmentdetails);
		 * System.out.println("consignemnt pojo details=**" +consignmentdetails);
		 * mv.setViewName("demo");
		 */ 
		return mv;

	}
}
