package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.gl.ceir.pagination.model.UserPaidStatusContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadPaidStatusView {
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UtilDownload utildownload;

	
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
