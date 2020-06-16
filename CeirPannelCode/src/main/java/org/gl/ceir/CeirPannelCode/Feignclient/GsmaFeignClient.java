package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.GsmaDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@FeignClient(url = "${gsmaFeignClientPath}", value = "profileUrls")
public interface GsmaFeignClient {

	// View all product Name feign controller

	@RequestMapping(value = "/gsma/brandName", method = RequestMethod.GET)
	public List<Dropdown> viewAllProductList();

	@RequestMapping(value = "/gsma/modelName", method = RequestMethod.GET)
	public List<Dropdown> viewAllmodel(@RequestParam(name = "brand_id") Integer brand_id);

	// --------------------------------- Gsma Details
	// ---------------------------------

	@PostMapping("/gsma/GsmaValues")
	public @ResponseBody GsmaDetail viewGsmaFeign(@RequestParam(name = "msisdn", required = false) String msisdn,
			@RequestParam(name = "imei", required = false) String imei,
			@RequestParam(name = "identifierType", required = false) String identifierType);

	@RequestMapping(value = "/Rule/DistinctName", method = RequestMethod.GET)
	public List<String> getFeatureName();

}
