package org.gl.ceir.CeirPannelCode.Feignclient;

import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.UserStatus;
import org.gl.ceir.CeirPannelCode.Response.UpdateProfileResponse;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Component
@Service
@FeignClient(url="${apiUrl1}",value = "profileUrls")
public interface UserProfileFeignImpl {

	@PostMapping("/userProfile/changePassword")
	public HttpResponse changePassword(Password password);
	
	@PostMapping("/userProfile/updateExpirePassword")
	public HttpResponse updateExpirePassword(Password password);
	

	@PostMapping("/userProfile/updateUserStatus")
	public HttpResponse updateUserStatus(UserStatus userStatus);


	@PostMapping("/userProfile/editProfile/{userid}") 
    public Registration editUserProfile(@PathVariable("userid") Integer userid);
	
	@PostMapping("/userProfile/updateProfile") 
    public UpdateProfileResponse updateUserProfile(Registration registration);
	
	//****************************************************************Admin Registration api starts from here ***************************************************************************************************	
	//View admin registration feign controller
	@RequestMapping(value="/userProfile/record" ,method=RequestMethod.POST) 
	public Object registrationRequest(@RequestBody FilterRequest filterRequest,
	@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
	@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
	@RequestParam(value = "file", defaultValue = "0") Integer file
	) ;

	
	@PostMapping("/userProfile/adminApproval")
	public HttpResponse adminUserApproval(UserStatus userStatus);
	
	@PostMapping("/userProfile/viewProfile/{id}")
	public Registration ViewAdminUser(@PathVariable("id") long id);
	
} 

