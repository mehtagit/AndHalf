package org.gl.ceir.CeirPannelCode.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadPaidStatusView {
	@GetMapping("uploadPaidStatus")
	public ModelAndView pageView() {
		ModelAndView modelAndView = new ModelAndView("uploadPaidStatus");
		return modelAndView;
	}
	
	
	@GetMapping("add-device-information")
	public ModelAndView deviceInformationView() {
		ModelAndView modelAndView = new ModelAndView("addDeviceInformation");
		return modelAndView;
	}
	
}
