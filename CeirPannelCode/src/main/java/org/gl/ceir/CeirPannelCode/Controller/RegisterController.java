package org.gl.ceir.CeirPannelCode.Controller;

import java.util.List;

import org.gl.ceir.CeirPannelCode.model.UserRegister;
import org.gl.ceir.CeirPannelCode.model.Usertype;
import org.gl.ceir.CeirPannelCode.util.HttpClient;
import org.gl.ceir.CeirPannelCode.util.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Controller
public class RegisterController {
	@Value("${APIUrl1}")
	String apiUrl1;
	@GetMapping({"/","/userRegistration"})
	public ModelAndView register() {
		System.out.println("inside method");
		ModelAndView mv=new ModelAndView();
		mv.setViewName("UserRegistration");
		try {
			Gson gson=new Gson();
			HttpClient client=new HttpClient();
			HttpResponse output=client.sendPOST(apiUrl1+"getAllUsertypes"); 
			List<Usertype> usertypes=gson.fromJson(output.getResponse(), new TypeToken<List<Usertype>>() {}.getType());
            mv.addObject("usertypes",usertypes);  
		} 
		catch(Exception e) {
			e.printStackTrace();	
		}
		return mv;
	}    
	@GetMapping("/hello")
	public String hello() {
		System.out.println("inside method");
		return "hello";
	} 
	@PostMapping("saveUserRegister")
	public ModelAndView saveUserRegister(@ModelAttribute UserRegister userdata) {
		System.out.println("user data at the time of registration: "+userdata); 
		ModelAndView mv=new ModelAndView(); 
		mv.setViewName("redirect:/userRegistration");
		return mv;
	}
}
