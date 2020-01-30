package com.gl.ceir.config.feign;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.gl.ceir.config.model.FeatureValidateReq;

@Service
@org.springframework.cloud.netflix.feign.FeignClient(url = "${UserFeignClientPath}", value = "dsj" )
public interface UserFeignClient {

	@PostMapping(value="/periodValidate")
	public Object validatePeriod(FeatureValidateReq featureValidateReq) ;

}