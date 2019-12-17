package org.gl.ceir.CeirPannelCode.Feignclient;

import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(url = "${feignClientPath}",value = "dsj" )
public interface UploadPaidStatusFeignClient {

	@GetMapping("/end-user/{nid}")
	public GenricResponse respone(@PathVariable("nid") String nid);
	
}
