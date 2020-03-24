package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CurrencyController {

	@Autowired
	UserProfileFeignImpl userProfileFeignImpl;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value=
		{"/currencyManagement"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewMessageManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 log.info(" view Currency Management entry point."); 
		 mv.setViewName("viewCurrencyManagement");
		log.info(" view Currency Management exit point."); 
		return mv; 
	}
	
	

	/*------------------------------------- Add Currency ------------------------------------------ */

	    @PostMapping("add-currency") 
	    public @ResponseBody GenricResponse AddCurrency (@RequestBody FilterRequest filterRequest)  {
		   log.info("request send to the add Currency api="+filterRequest);
	 	   GenricResponse response= userProfileFeignImpl.AddCurrencyFeign(filterRequest);
		   log.info("response from add Port api "+response);
		   return response;
	}
	
	
	//------------------------------------- view Currency ----------------------------------------							
	
		@PostMapping("currencyViewByID/{id}") 
		public @ResponseBody GenricResponse viewCurrency (@PathVariable ("id") Integer id )  {
			log.info("request send to the View currency api="+id);
			GenricResponse response= userProfileFeignImpl.viewCurrencyFeign(id);
			log.info("response from Currency api "+response);
			return response;
	}
		
		
	//------------------------------------- update Currency ----------------------------------------							
		
		@PostMapping("updateCurrency") 
		public @ResponseBody GenricResponse updatePortAddress (@RequestBody FilterRequest filterRequest)  {
			log.info("request send to the Update Currency api="+filterRequest);
			GenricResponse response= userProfileFeignImpl.updateCurrencyFeign(filterRequest);
			log.info("response from update api "+response);
			return response;
	}	
	
	
}