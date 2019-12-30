package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UploadPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Feignclient.UserPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest_UserPaidStatus;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.UplodPaidStatusModel;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@Autowired
	UploadPaidStatusFeignClient uploadPaidStatusFeignClient;



	@GetMapping("uploadPaidStatus")
	public ModelAndView pageView(@RequestParam(name="via", required = false) String via,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if(session.getAttribute("usertype").equals("CEIRAdmin") && !("other".equals(via))) {
			modelAndView.setViewName("uploadPaidStatus");
		}
		else if("other".equals(via)) {
			modelAndView.setViewName("uploadPaidStatus");
		}
		else {
			modelAndView.setViewName("nidForm");
		}
		return modelAndView;
	}


	@GetMapping("add-device-information")
	public ModelAndView deviceInformationView() {
		ModelAndView modelAndView = new ModelAndView("addDeviceInformation");
		return modelAndView;
	}



	@PostMapping("uploadPaidStatusForm")
	public @ResponseBody GenricResponse register(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		log.info("-inside controller register-approved-device-------request---------");
		// log.info(""+request.getParameter("file"));
		String userName=session.getAttribute("username").toString();
		String userId= session.getAttribute("userid").toString();
		String name=session.getAttribute("name").toString();
		String txnNumber="R" + utildownload.getTxnId();
		log.info("Random transaction id number="+txnNumber);
		//request.setAttribute("txnId", txnNumber);
		//request.setAttribute("request[regularizeDeviceDbs][txnId]",txnNumber);
		String filter = request.getParameter("request");
		//log.info("txnid+++++++++++"+request.getParameter("request[regularizeDeviceDbs][txnId]"));
		Gson gson= new Gson(); 

		log.info("*********"+filter);

		Register_UploadPaidStatus regularizeDeviceDbs  = gson.fromJson(filter, Register_UploadPaidStatus.class);

		for(int i =0; i<regularizeDeviceDbs.getRegularizeDeviceDbs().size();i++) {
			regularizeDeviceDbs.getRegularizeDeviceDbs().get(i).setTxnId(txnNumber);
		}

		log.info(""+regularizeDeviceDbs.toString());
		log.info(" upload status  entry point.");
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
		log.info("request passed to the save regularizeDeviceDbs api"+regularizeDeviceDbs);
		GenricResponse response = null;
		try {
			response = userPaidStatusFeignClient.uploadPaidUser(regularizeDeviceDbs);
			//GenricResponse response = null;
			log.info("---------response--------"+response);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.info("exception in upload paid stat error"+e);
			e.printStackTrace();

		}
		return response;
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
		Object response = userPaidStatusFeignClient.consignmentFilter(filterRequestuserpaidStatus, pageNo, pageSize, file);
		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
		log.info("response  from   export grievance  api="+fileExportResponse);
		return "redirect:"+fileExportResponse.getUrl();
	}



	//***********************************************cuurency controller *************************************************
	@RequestMapping(value="/countByNid",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
	public @ResponseBody GenricResponse countByNid(@RequestParam(name="nid", required = false) String nId)  {
		log.info("request send to the currency  api="+nId);
		GenricResponse response= uploadPaidStatusFeignClient.countByNid(nId);
		log.info("response from currency api "+response);
		return response;

	}

	
	//********************************************Admin Approve/Reject Controller******************************************
	
	@PutMapping("approveRejectDevice") 
	public @ResponseBody GenricResponse approveRejectDevice (@RequestBody FilterRequest_UserPaidStatus filterRequestuserpaidStatus)  {
		log.info("request send to the approveRejectDevice api="+filterRequestuserpaidStatus);
		GenricResponse response= uploadPaidStatusFeignClient.approveRejectFeign(filterRequestuserpaidStatus);

		log.info("response from currency api "+response);
		return response;

		}
	
	
	
	
	


	@ResponseBody
	@PutMapping("tax-paid/status")
	public GenricResponse taxPaidStatusUpdate(@RequestBody Register_UploadPaidStatus model) {
		GenricResponse response = userPaidStatusFeignClient.tax(model);
		log.info("::::::::::model:::::::"+model);
		log.info("---------response--------"+response);
		return response;
	}
}
