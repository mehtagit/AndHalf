package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.gl.ceir.CeirPannelCode.Model.ForgotPassword;
import org.gl.ceir.CeirPannelCode.Model.Password;
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
@FeignClient(url="${apiUrl1}",value = "loginUrls")
public interface UserLoginFeignImpl { 
	
	@PostMapping("/Login/checkUser") 
	public LoginResponse checkUser(User user);
	  
	@PostMapping("/Login/sessionTracking/{userid}")
	public HttpResponse sessionTracking(@PathVariable("userid") Integer userid);
	
	@PostMapping("/Login/forgotPassword")
	public HttpResponse ForgotPassword(ForgotPassword forgotPassword);
	
	@PostMapping("/Login/updateNewPassword")
	public HttpResponse updateNewPassword(Password forgotPassword);
	
}

