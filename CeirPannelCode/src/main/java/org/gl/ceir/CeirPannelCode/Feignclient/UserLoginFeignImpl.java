package org.gl.ceir.CeirPannelCode.Feignclient;

import java.util.List;

import org.gl.ceir.CeirPannelCode.Model.AddressModel;
import org.gl.ceir.CeirPannelCode.Model.AddressResponse;
import org.gl.ceir.CeirPannelCode.Model.ChangeLanguage;
import org.gl.ceir.CeirPannelCode.Model.ForgotPassword;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.User;
import org.gl.ceir.CeirPannelCode.Model.UserStatus;
import org.gl.ceir.CeirPannelCode.Response.LoginResponse;
import org.gl.ceir.CeirPannelCode.Response.UpdateProfileResponse;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Service
@FeignClient(url="${apiUrl1}",value = "loginUrls")
public interface UserLoginFeignImpl { 
	
	@PostMapping("/Login/checkUser") 
	public LoginResponse checkUser(User user);
	
	@PostMapping("/Login/changeLanguage") 
	public HttpResponse changeUserLanguage(ChangeLanguage language);
	  
	@PostMapping("/Login/sessionTracking/{userid}")
	public HttpResponse sessionTracking(@PathVariable("userid") Integer userid);
	
	@PostMapping("/Login/forgotPassword")
	public UpdateProfileResponse ForgotPassword(ForgotPassword forgotPassword);
	
	@PostMapping("/Login/updateNewPassword")
	public HttpResponse updateNewPassword(Password forgotPassword);
	
	@PostMapping("/Login/testLogin") 
	public LoginResponse searchUserDetailFeign(UserStatus userStatus);
	
	//*******************************District DropDown****************************************
	
	@PostMapping("/getDistrict")
	public @ResponseBody List<AddressResponse> getAllTagsDistrictFeign(AddressModel addressModel);
	
	//*******************************Commune DropDown****************************************
	
	@PostMapping("/getCommune")
	public @ResponseBody List<AddressResponse> getAllCommuneFeign(AddressModel addressModel);
	
	//*******************************Village DropDown****************************************
	
	@PostMapping("/getVillage")
	public @ResponseBody List<AddressResponse> getAllVillageFeign(AddressModel addressModel);
}

