package org.gl.ceir.CeirPannelCode.Service;

import org.gl.ceir.CeirPannelCode.Feignclient.UserLoginFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.User;
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
	
	public ModelAndView checkLogin(User user) {
		log.info("check login controller");
		ModelAndView mv=new ModelAndView();
		HttpResponse response=new HttpResponse();
		response=userLoginFeignImpl.checkUser(user);
		if(response.getStatusCode()==200) {
			mv.setViewName("dashboard");
			return mv;
		}
		else {
			mv.setViewName("login");
			mv.addObject("msg",response.getResponse());
			return mv;
		}
	}
}
