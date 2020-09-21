package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.GsmaDetail;
import org.gl.ceir.CeirPannelCode.Model.InterRelatedRuleFeatureMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Service
@FeignClient(url="${gsmaFeignClientPath}",value = "profileUrls")
public interface GsmaFeignClient {

	//View all product Name feign controller
		
		@RequestMapping(value="/gsma/brandName" ,method=RequestMethod.GET) 
		public List<Dropdown> viewAllProductList();
		
		

		@RequestMapping(value="/gsma/modelName" ,method=RequestMethod.GET) 
		public List<Dropdown> viewAllmodel(@RequestParam(name="brand_id") Integer brand_id);
		
		
	//--------------------------------- Gsma Details ---------------------------------
		
		@PostMapping("/gsma/GsmaValues")	
		public @ResponseBody GsmaDetail viewGsmaFeign(@RequestParam(name = "msisdn", required = false) String msisdn,
				@RequestParam(name = "imei", required = false) String imei,
				@RequestParam(name = "identifierType", required = false) String identifierType);
	
	
		
		
		@RequestMapping(value="/rule/GetfeaturebyRuleName" ,method=RequestMethod.POST) 
		public List<String> getFeatureName(@RequestParam(name = "ruleName", required = false) String ruleName);
		
		
		//---------------------------------check Msisdn Exist or not ---------------------------------
		
		@PostMapping(path = "gsma/CheckImeiMsisdnValues")
				public @ResponseBody String checkImeiDetails(@RequestParam(name = "imei", required = false) String imei,
						@RequestParam(name = "msisdn", required = false) String msisdn);
		
		
		
		
		@PostMapping(path ="rule/getRuleFeaturAction")
		public @ResponseBody List<InterRelatedRuleFeatureMapping> interRelateMapping(@RequestParam(name = "featureName", required = false) String featureName,
				@RequestParam(name = "ruleName", required = false) String ruleName);


}
