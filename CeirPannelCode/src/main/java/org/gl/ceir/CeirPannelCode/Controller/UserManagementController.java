package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.NewRule;
import org.gl.ceir.CeirPannelCode.Model.NewSystemUser;
import org.gl.ceir.CeirPannelCode.Model.OtpResponse;
import org.gl.ceir.CeirPannelCode.Model.Registration;
import org.gl.ceir.CeirPannelCode.Util.GenerateRandomDigits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class UserManagementController {

	@Autowired
	GenerateRandomDigits randomDigits;
	@Autowired
	UserProfileFeignImpl userProfileFeignImpl;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = { "/userManagement" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView viewUserManagement(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		log.info(" view viewUserManagement entry point.");
		mv.setViewName("userManagement");
		log.info(" view viewUserManagement exit point.");
		return mv;
	}

	@GetMapping("register-user-form")
	public ModelAndView save() {
		return new ModelAndView("addSystemUser");

	}

	@RequestMapping(value = { "/saveNewSystemUser" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET,
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public @ResponseBody GenricResponse saveRecord(@RequestBody NewSystemUser newSystemUser) {

		if (newSystemUser.getRePassword().equals(newSystemUser.getPassword())) {
			log.info("if password and confirm password match");
			String username = randomDigits.getAlphaNumericString(4) + randomDigits.getNumericString(4)
					+ randomDigits.getAlphaNumericString(1);
			newSystemUser.setUserName(username);
			log.info("request::::::" + newSystemUser);
			GenricResponse response = userProfileFeignImpl.saveSystemUser(newSystemUser);
			log.info("response::::::" + response);
			return response;
		} else {
			GenricResponse response = new GenricResponse();
			log.info("confirm password is not same as password");
			response.setTag("password_mismatch");
			response.setResponse("Password and Confirm password must be same");
			return response;
		}

	}

	// ------------------------------------- view User
	// ----------------------------------------

	@PostMapping("viewUser")
	public @ResponseBody GenricResponse viewUser(@RequestBody NewSystemUser newSystemUser) {
		log.info("request send to the viewUser api=" + newSystemUser);
		GenricResponse response = userProfileFeignImpl.viewUserFeign(newSystemUser);
		log.info("response from viewUser api " + response);
		return response;
	}

	// ------------------------------------- update user type
	// ----------------------------------------

	@PostMapping("updateUser")
	public @ResponseBody GenricResponse updateUserDetail(@RequestBody NewSystemUser newSystemUser) {
		log.info("request send to the Update user api=" + newSystemUser);
		GenricResponse response = userProfileFeignImpl.updateUserFeign(newSystemUser);
		log.info("response from update api " + response);
		return response;
	}

	// ------------------------------------- Delete User
	// ----------------------------------------

	@PostMapping("deleteSystemUserType")
	public @ResponseBody GenricResponse delete(@RequestBody NewSystemUser newSystemUser) {
		GenricResponse response = userProfileFeignImpl.deleteUserFeign(newSystemUser);
		return response;

	}
}
