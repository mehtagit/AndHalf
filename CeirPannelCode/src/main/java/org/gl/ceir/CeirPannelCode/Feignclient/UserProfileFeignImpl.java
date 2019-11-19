package org.gl.ceir.CeirPannelCode.Feignclient;

import org.gl.ceir.CeirPannelCode.Model.EditProfile;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.UserStatus;
import org.gl.ceir.CeirPannelCode.Response.UpdateProfileResponse;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(url="http://13.127.239.247:8086/CEIRCode2",value = "profileUrls")
public interface UserProfileFeignImpl {

	@PostMapping("/userProfile/changePassword")
	public HttpResponse changePassword(Password password);

	@PostMapping("/userProfile/updateUserStatus")
	public HttpResponse updateUserStatus(UserStatus userStatus);


	@PostMapping("/userProfile/editProfile/{userid}") 
    public Registration editUserProfile(@PathVariable("userid") Integer userid);
	
	@PostMapping("/userProfile/updateProfile") 
    public UpdateProfileResponse updateUserProfile(Registration registration);
} 

