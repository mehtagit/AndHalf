package org.gl.ceir.CeirPannelCode.Controller;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.EditProfile;
import org.gl.ceir.CeirPannelCode.Model.ForgotPassword;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Model.UserStatus;
import org.gl.ceir.CeirPannelCode.Response.UpdateProfileResponse;
import org.gl.ceir.CeirPannelCode.Service.ProfileService;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@RequestMapping(value = "changePassword",method = RequestMethod.POST)
	@ResponseBody
	public  HttpResponse changePassword(@RequestBody Password password,HttpSession session) {

		return profileService.changePassword(password,session);
	}

	@RequestMapping(value = "updateUserStatus",method = RequestMethod.POST)
	@ResponseBody
	public  HttpResponse updateUserStatus(@RequestBody UserStatus userStatus,HttpSession session) {
		return profileService.updateUSerStatus(userStatus,session);
	}  

	@RequestMapping(value = "/editProfile",method = RequestMethod.POST)
	@ResponseBody 
	public  Registration editUserProfile(HttpSession session) {
		return profileService.editUserProfile(session);
	} 
	
	@RequestMapping(value = "/updateProfile",method = RequestMethod.POST)
	@ResponseBody                                                             
	public  UpdateProfileResponse updateProfile(@RequestBody Registration registration,HttpSession session) {
		return profileService.updateProfile(registration, session);
	} 
	
	@RequestMapping(value ="adminApproval",method = RequestMethod.POST)
	@ResponseBody
	public  HttpResponse adminApproval(@RequestBody UserStatus userStatus,HttpSession session) {
		return profileService.adminApprovalService(userStatus,session);
		
	}
	
}
