package org.gl.ceir.CeirPannelCode.Feignclient;

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

@Service
@FeignClient(url = "${apiUrl1}", value = "loginUrls")
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
}
