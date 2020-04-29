package com.gl.ceir.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gl.ceir.config.genericresponse.GenricResponse_Class;
import com.gl.ceir.config.model.FeatureValidateReq;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.util.HttpResponse;

@Service
@FeignClient(url = "${UserFeignClientPath}", value = "dsj" )
public interface UserFeignClient {

	@PostMapping(value="/periodValidate")
	public HttpResponse validatePeriod(FeatureValidateReq featureValidateReq) ;
	
	@PostMapping(value="/usertypeStatus/{usertypeId}")
	public GenricResponse_Class usertypeStatus(@PathVariable("usertypeId") Integer usertypeId) ;
	
	
	@PostMapping("/nameById/{id}")
	public GenricResponse_Class nameById(@PathVariable("id") Integer id);
	
	@PostMapping("/portAddress/viewById/{id}")
	public GenricResponse_Class portAddressInterp(@PathVariable("id") Integer id);

}