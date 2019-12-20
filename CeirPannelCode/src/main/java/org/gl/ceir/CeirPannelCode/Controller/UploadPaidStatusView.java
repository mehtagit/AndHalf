package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.UplodPaidStatusModel;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import CeirPannelCode.Model.Register_UploadPaidStatus;

@Controller
public class UploadPaidStatusView {
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UtilDownload utildownload;

	
	@Autowired
	UserPaidStatusFeignClient userPaidStatusFeignClient;
	
	@GetMapping("uploadPaidStatus")
	public ModelAndView pageView() {
		ModelAndView modelAndView = new ModelAndView("uploadPaidStatus");
		return modelAndView;
	}

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("register-approved-device") public GenricResponse
	 * register(@RequestParam(name="file",required = false) MultipartFile
	 * file,HttpServletRequest request,HttpSession session) {
	 * log.info("-inside controller register-approved-device-------request---------"
	 * +request.getParameter("manufacturerId")); //
	 * log.info(""+request.getParameter("file")); String
	 * userName=session.getAttribute("username").toString(); String userId=
	 * session.getAttribute("userid").toString(); String
	 * name=session.getAttribute("name").toString(); Map<String, String[]>
	 * parameterMap = request.getParameterMap(); log.info(parameterMap.toString());
	 * log.info(" Register consignment entry point."); String txnNumber="T" +
	 * utildownload.getTxnId(); log.info("Random transaction id number="+txnNumber);
	 * request.getParameterValues(""); try { byte[] bytes = file.getBytes(); String
	 * rootPath ="/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnNumber+"/";
	 * File dir = new File(rootPath + File.separator);
	 * 
	 * if (!dir.exists()) dir.mkdirs(); // Create the file on server File serverFile
	 * = new File(rootPath+file.getOriginalFilename());
	 * log.info("uploaded file path on server" + serverFile); BufferedOutputStream
	 * stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	 * stream.write(bytes); stream.close(); }
	 * 
	 * catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * log.info("above model"+txnNumber); // set request parameters into model class
	 * //TRCRegisteration model =
	 * registerationImpl.register(request,file.getOriginalFilename(),txnNumber);
	 * //log.info("---------model--------"+model); //GenricResponse response =
	 * typeApprovedFeignImpl.register(model); GenricResponse response = null;
	 * log.info("---------response--------"+response); return response; }
	 * 
	 */	
	
	
	@GetMapping("add-device-information")
	public ModelAndView deviceInformationView() {
		ModelAndView modelAndView = new ModelAndView("addDeviceInformation");
		return modelAndView;
	}

	

	@ResponseBody
	@PostMapping("uploadPaidStatusForm")
	public GenricResponse register(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		log.info("-inside controller register-approved-device-------request---------");
		// log.info(""+request.getParameter("file"));
		String userName=session.getAttribute("username").toString();
		String userId= session.getAttribute("userid").toString();
		String name=session.getAttribute("name").toString();
		String txnNumber="T" + utildownload.getTxnId();
		log.info("Random transaction id number="+txnNumber);
		//request.setAttribute("txnId", txnNumber);
		String filter = request.getParameter("request"); 
		Gson gson= new Gson(); 
	
		log.info("*********"+filter);
		
		Register_UploadPaidStatus regularizeDeviceDbs  = gson.fromJson(filter, Register_UploadPaidStatus.class);
		log.info(""+regularizeDeviceDbs.toString());
		log.info(" Register consignment entry point.");
		try { byte[] bytes = file.getBytes();
		String rootPath ="/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnNumber+"/"; 
		File dir = new File(rootPath + File.separator);

		if (!dir.exists()) dir.mkdirs();
		// Create the file on server 
		File serverFile = new File(rootPath+file.getOriginalFilename());
		log.info("uploaded file path on server" + serverFile); BufferedOutputStream
		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes); 
		stream.close();
		} 

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		log.info("request passed to the regularizeDeviceDbs api"+regularizeDeviceDbs);
		
		GenricResponse response = userPaidStatusFeignClient.uploadPaidUser(regularizeDeviceDbs);
		//GenricResponse response = null;
		log.info("---------response--------"+response);
		return response;
	}

}
