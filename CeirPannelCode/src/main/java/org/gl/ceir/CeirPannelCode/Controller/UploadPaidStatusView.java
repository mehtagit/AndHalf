package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UserPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest_UserPaidStatus;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.TRCRegisteration;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class UploadPaidStatusView {
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UtilDownload utildownload;
	
	@Autowired
	UserPaidStatusFeignClient UserPaidStatusFeignClient;

	
	@GetMapping("uploadPaidStatus")
	public ModelAndView pageView() {
		ModelAndView modelAndView = new ModelAndView("uploadPaidStatus");
		return modelAndView;
	}
	@ResponseBody
	@PostMapping("register-approved-device")
	public GenricResponse register(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		log.info("-inside controller register-approved-device-------request---------"+request.getParameter("manufacturerId"));
		// log.info(""+request.getParameter("file"));
		String userName=session.getAttribute("username").toString();
		String userId= session.getAttribute("userid").toString();
		String name=session.getAttribute("name").toString();
		log.info(" Register consignment entry point.");
		String txnNumber="T" + utildownload.getTxnId();
		log.info("Random transaction id number="+txnNumber);
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
		log.info("above model"+txnNumber);
		// set request parameters into model class
		//TRCRegisteration model = registerationImpl.register(request,file.getOriginalFilename(),txnNumber);
		//log.info("---------model--------"+model);
		//GenricResponse response = typeApprovedFeignImpl.register(model);
		GenricResponse response = null;
		log.info("---------response--------"+response);
		return response;
	}

	
	@GetMapping("add-device-information")
	public ModelAndView deviceInformationView() {
		ModelAndView modelAndView = new ModelAndView("addDeviceInformation");
		return modelAndView;
	}
	
	
	
	//***************************************** Export Registration controller *********************************
	
	@RequestMapping(value="/exportPaidStatus",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String exportToExcel(@RequestParam(name="startDate", required = false) String startDate,
			@RequestParam(name="endDate",required = false) String endDate,
			@RequestParam(name="taxPaidStatus",required = false) Integer taxPaidStatus,
			@RequestParam(name="deviceIdType", required = false) Integer deviceIdType,
			@RequestParam(name="deviceType",required = false) Integer deviceType,
			@RequestParam(name="pageSize") Integer pageSize,
			@RequestParam(name="pageNo") Integer pageNo,
			HttpServletRequest request,
			HttpSession session)
	{
		
		int userId= (int) session.getAttribute("userid");
		int file=1;
		String userType=(String) session.getAttribute("usertype"); 	
		FileExportResponse fileExportResponse;
		FilterRequest_UserPaidStatus filterRequestuserpaidStatus = new FilterRequest_UserPaidStatus();
		filterRequestuserpaidStatus.setCreatedOn(startDate);
		filterRequestuserpaidStatus.setModifiedOn(endDate);
		filterRequestuserpaidStatus.setTaxPaidStatus(taxPaidStatus);
		filterRequestuserpaidStatus.setDeviceIdType(deviceIdType);
		filterRequestuserpaidStatus.setDeviceType(deviceType);
		log.info(" request passed to the exportTo Excel Api =="+filterRequestuserpaidStatus+" *********** pageSize"+pageSize+"  pageNo  "+pageNo);
		Object response = UserPaidStatusFeignClient.consignmentFilter(filterRequestuserpaidStatus, pageNo, pageSize, file);
		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
			log.info("response  from   export grievance  api="+fileExportResponse);
			return "redirect:"+fileExportResponse.getUrl();
	}
	
	
}
