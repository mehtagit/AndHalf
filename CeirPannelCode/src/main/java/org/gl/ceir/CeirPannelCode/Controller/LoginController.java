package org.gl.ceir.CeirPannelCode.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Model.ChangeLanguage;
import org.gl.ceir.CeirPannelCode.Model.ForgotPassword;
import org.gl.ceir.CeirPannelCode.Model.Password;
import org.gl.ceir.CeirPannelCode.Model.User;
import org.gl.ceir.CeirPannelCode.Response.LoginResponse;
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
	public ModelAndView login(HttpSession session){
			return loginService.loginPage();	
	} 
	
	@ResponseBody
	@RequestMapping(value = "/saveLogin",method = {RequestMethod.POST})
	public LoginResponse saveLogin(@RequestBody User user,HttpSession session,HttpServletResponse http){
			return loginService.checkLogin(user,session);
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeLanguage",method = {RequestMethod.POST})
	public HttpResponse changeLanguage(@RequestBody ChangeLanguage language){
			return loginService.changeLanguage(language);
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		return loginService.logout(session);

	}
	
	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public void indexSessionOut(HttpSession session,HttpServletResponse response) {
		 loginService.indexPageSessionOut(session,response);
	}
    
	@RequestMapping(value = "/redirectToHomePage", method = RequestMethod.GET)
	public void redirectHomePage(HttpSession session,HttpServletResponse response) {
		 loginService.indexPageSessionOut(session,response);
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
