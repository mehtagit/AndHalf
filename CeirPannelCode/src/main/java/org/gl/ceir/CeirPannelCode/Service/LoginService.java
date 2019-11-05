package org.gl.ceir.CeirPannelCode.Service;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserLoginFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.User;
import org.gl.ceir.CeirPannelCode.Response.LoginResponse;
import org.gl.ceir.CeirPannelCode.Util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class LoginService {
	private final Logger log = LoggerFactory.getLogger(getClass());	
	@Autowired
	UserLoginFeignImpl userLoginFeignImpl;
	
	public  ModelAndView loginPage(){
		log.info("inside login controller");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("login");
		log.info("exit from login controller");
		return mv;
	}
	
	public ModelAndView checkLogin(User user,HttpSession session) {
		log.info("check login controller");
		ModelAndView mv=new ModelAndView();
		LoginResponse response=new LoginResponse();
		response=userLoginFeignImpl.checkUser(user);
		log.info("login response:  "+response); 
		if(response.getStatusCode()==200) {
			session.setAttribute("username", response.getUsername());
			session.setAttribute("userid", response.getUserId());
			session.setAttribute("userRoles", response.getUserRoles());
			mv.setViewName("redirect:/importerDashboard");
			return mv;     
		}
		else {
			mv.setViewName("login");
			mv.addObject("msg",response.getResponse());
			return mv;
		}
	}
}
