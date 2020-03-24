package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Feignclient.GsmaFeignClient;
import org.gl.ceir.CeirPannelCode.Model.CustomerCareRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.GsmaDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CCgsmaController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	GsmaFeignClient gsmaFeignClient;
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	

	@PostMapping("getGsmaDetails")
	public @ResponseBody GsmaDetail getDetails(@RequestParam(name = "msisdn", required = false) Long msisdn,
			@RequestParam(name = "imei", required = false) Long imei,
			@RequestParam(name = "identifierType", required = false) String identifierType, HttpSession session) {
		log.info("request passed to the getGsmaDetails Api msisdn-->"+msisdn+ " imei-->"+imei+" identifierType-->"+identifierType);
		GsmaDetail response = gsmaFeignClient.viewGsmaFeign(msisdn, imei, identifierType);
		log.info("response after getGsmaDetails." + response);
		return response;

	}
	
	
	
	//----------------------------  View Customer details ------------------------------- 
	
	@PostMapping("/customerRecord")
	public @ResponseBody GenricResponse viewCustomerDetails(
			@RequestParam(name = "listType", required = false) String listType,
			@RequestBody CustomerCareRequest customerCareRequest) {
		log.info("request send to the Customer details api=" + customerCareRequest+" listType-->" +listType);
		GenricResponse response = feignCleintImplementation.viewcustomerDetialsfeign(listType, customerCareRequest);
		log.info("response from Customer details api " + response);
		return response;

	}
}
