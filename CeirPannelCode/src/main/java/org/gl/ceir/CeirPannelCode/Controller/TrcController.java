package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.TypeApprovedFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.TRCRegisteration;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.gl.ceir.interfaceImpl.RegisterationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class TrcController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	TypeApprovedFeignImpl typeApprovedFeignImpl;
	@Autowired
	RegisterationImpl registerationImpl;
	@Autowired
	UtilDownload utildownload;
	
	@RequestMapping(value=
		{"/manageTypeDevices"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	public ModelAndView viewManageType(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		log.info(" view TRC entry point."); 
		mv.setViewName("viewManageTypeApproved");
		log.info(" view TRC  exit point."); 
		return mv; 
	}
	
	
	@GetMapping("register-form")
	public ModelAndView regiserForm() {
		ModelAndView modelAndView = new ModelAndView("trcReportTypeApprovedDevice");
		return modelAndView;
		
	}
	
	  @PostMapping("register-approved-device")
	  public GenricResponse register(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		  log.info("---------request---------"+request.getParameter("manufacturerId"));
		 // log.info(""+request.getParameter("file"));
		  String userName=session.getAttribute("username").toString();
		  String userId= session.getAttribute("userid").toString();
		  String name=session.getAttribute("name").toString();
		  log.info(" Register consignment entry point.");
		  String txnNumner=utildownload.getTxnId();
		  txnNumner = "C"+txnNumner;
		  log.info("Random transaction id number="+txnNumner);
		  try { byte[] bytes = file.getBytes();
		  String rootPath ="/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnNumner+"/"; 
		  File dir = new File(rootPath + File.separator);
		  
		  if (!dir.exists()) dir.mkdirs();
		  // Create the file on server 
		  File serverFile = new File(rootPath+file.getOriginalFilename());
		  log.info("uploaded file path on server" + serverFile); BufferedOutputStream
		  stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		  stream.write(bytes); stream.close();
		  } 
		  
		  catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		  
		  // set request parameters into model class
		  TRCRegisteration model = registerationImpl.register(request,file.getOriginalFilename());
		  GenricResponse response = typeApprovedFeignImpl.register(model);
		  return response;
	  }
	  
	  
	  @ResponseBody
	  @PostMapping("viewByID/{id}")
		public TRCRegisteration viewByID(@PathVariable("id") int id) {
		  log.info("inside controller"+id);
		  TRCRegisteration result = typeApprovedFeignImpl.viewByID(id);
		  log.info("may be response wrong"+result);
		  return result;
		 }
}