package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.http.HttpRequest;
import org.gl.ceir.CeirPannelCode.Model.ChangeLanguage;
import org.gl.ceir.CeirPannelCode.Model.ForgotPassword;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.User;
import org.gl.ceir.CeirPannelCode.Model.UserStatus;
import org.gl.ceir.CeirPannelCode.Response.LoginResponse;
import org.gl.ceir.CeirPannelCode.Response.UpdateProfileResponse;
import org.gl.ceir.CeirPannelCode.Service.LoginService;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	private final Logger log = LoggerFactory.getLogger(getClass());	
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(HttpSession session){
			return loginService.loginPage(session);	
	} 
	
	@ResponseBody
	@RequestMapping(value = "/saveLogin",method = {RequestMethod.POST})
	public LoginResponse saveLogin(@RequestBody User user,HttpSession session,HttpServletRequest request){
			return loginService.checkLogin(user,session,request);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/changeLanguage/{lang}",method = {RequestMethod.POST})
	public HttpResponse changeLanguage(@PathVariable("lang")String lang,HttpSession session){
		    
			return loginService.changeLanguage(lang,session);
  }
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpSession session) {
		return loginService.logout(session);

	}
	
	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public void indexSessionOut(HttpSession session,HttpServletResponse response) {
		 loginService.indexPageSessionOut(session,response);
	}
    

	@RequestMapping(value = "/redirectToHomePage", method = RequestMethod.GET)
	public void redirectHomePage(HttpSession session,HttpServletResponse response) {
	loginService.redirectToHome(response);
	}

	@RequestMapping(value = "changeExpirePassword",method = RequestMethod.POST)
	@ResponseBody
	public  HttpResponse changePassword(@RequestBody Password password) {
		return loginService.changeExpirePassword(password);
	}

	@RequestMapping(value = "/forgotPassword",method = RequestMethod.GET)
	public ModelAndView forgotPassword(){ 
		log.info("inside forgotPassword controller ");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("forgotPassword");
		return mv;   
	}

	@RequestMapping(value = "forgotPasswordRequest",method = RequestMethod.POST)
	@ResponseBody 
	public  UpdateProfileResponse forgotPasswordRequest(@RequestBody ForgotPassword forgotPassword) {
		return loginService.forgotPasswordRequest(forgotPassword);
	} 

	@RequestMapping(value = "updateNewPassword",method = RequestMethod.POST)
	@ResponseBody
	public  HttpResponse newPassword(@RequestBody Password password) {
		return loginService.updateNewPassword(password); 
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchUser",method = {RequestMethod.POST})
	public LoginResponse searchUserDetail(@RequestBody UserStatus userStatus,HttpSession session,HttpServletRequest request){
			return loginService.searchUserDetailService(userStatus,session,request);
	}
	
}
