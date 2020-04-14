package com.gl.ceir.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gl.ceir.config.model.FeatureValidateReq;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.util.HttpResponse;

@Service
@FeignClient(url = "${UserFeignClientPath}", value = "dsj" )
public interface UserFeignClient {

	@PostMapping(value="/periodValidate")
	public HttpResponse validatePeriod(FeatureValidateReq featureValidateReq) ;
	
	@PostMapping(value="/usertypeStatus/{usertypeId}")
	public GenricResponse usertypeStatus(@PathVariable("usertypeId") Integer usertypeId) ;

}