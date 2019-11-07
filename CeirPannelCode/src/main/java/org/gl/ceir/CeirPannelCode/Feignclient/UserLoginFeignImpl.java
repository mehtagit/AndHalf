package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.gl.ceir.CeirPannelCode.Model.User;
import org.gl.ceir.CeirPannelCode.Model.Usertype;
import org.gl.ceir.CeirPannelCode.Response.LoginResponse;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Service
@FeignClient(url="http://13.127.239.247:8085/CEIRCode",value = "loginUrls")
public interface UserLoginFeignImpl {

	
	@PostMapping("/Login/checkUser") 
	public LoginResponse checkUser(User user);
	  
	@PostMapping("/Login/sessionTracking/{userid}")
	public HttpResponse sessionTracking(@PathVariable("userid") Integer userid);
} 

