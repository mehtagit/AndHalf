package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Model.ForgotPassword;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.User;
import org.gl.ceir.CeirPannelCode.Service.LoginService;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ModelAndView login(@ModelAttribute User user,HttpSession session){
		if(user.getUsername()==null) {
			return loginService.loginPage();	
		}
		else {
			return loginService.checkLogin(user,session);
		}           
	} 

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		return loginService.logout(session);

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
	public  HttpResponse forgotPasswordRequest(@RequestBody ForgotPassword forgotPassword) {
		return loginService.forgotPasswordRequest(forgotPassword);
	} 

	@RequestMapping(value = "updateNewPassword",method = RequestMethod.POST)
	@ResponseBody
	public  HttpResponse newPassword(@RequestBody Password password) {
		return loginService.updateNewPassword(password); 
	}

}
