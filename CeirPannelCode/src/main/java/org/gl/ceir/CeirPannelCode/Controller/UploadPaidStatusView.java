package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Feignclient.ImmigrationFeignImpl;
import org.gl.ceir.CeirPannelCode.Feignclient.UploadPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Feignclient.UserPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Model.AddMoreFileModel;
import org.gl.ceir.CeirPannelCode.Model.EndUserVisaInfo;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest_UserPaidStatus;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.VisaDb;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.gl.ceir.pagination.model.UserPaidStatusContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import CeirPannelCode.Model.Register_UploadPaidStatus;

@Controller
public class UploadPaidStatusView {


	private final Logger log = LoggerFactory.getLogger(getClass());

	@Value ("${filePathforUploadFile}")
	String filePathforUploadFile;

	@Value ("${filePathforMoveFile}")
	String filePathforMoveFile;
	
	
	@Autowired
	UtilDownload utildownload;

	@Autowired
	UserPaidStatusFeignClient userPaidStatusFeignClient;

	@Autowired
	UploadPaidStatusFeignClient uploadPaidStatusFeignClient;

	@Autowired
	ImmigrationFeignImpl immigrationFeignImpl;

	
@Autowired
AddMoreFileModel addMoreFileModel,urlToUpload,urlToMove;

@Autowired

FeignCleintImplementation feignCleintImplementation;


	@GetMapping("uploadPaidStatus")
	public ModelAndView pageView(@RequestParam(name="via", required = false) String via,@RequestParam(name="NID", required = false) String NID,HttpSession session
			,@RequestParam(name="txnID",required = false) String txnID) {
		ModelAndView modelAndView = new ModelAndView();
		if((session.getAttribute("usertype").equals("CEIRAdmin") || session.getAttribute("usertype").equals("DRT")) && !("other".equals(via))) {
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
		log.info("enter in add device page.");
		ModelAndView modelAndView = new ModelAndView("addDeviceInformation");
		return modelAndView;
	}

	@GetMapping("view-device-information/{imei}")
	public ModelAndView viewDeviceInformationView(@PathVariable("imei") Long imei) {
		log.info(" imei =="+imei);
		ModelAndView modelAndView = new ModelAndView("viewAdddeviceInformation");
		UserPaidStatusContent content= uploadPaidStatusFeignClient.viewByImei(imei);
		log.info(" content =="+content);

		addMoreFileModel.setTag("upload_file_link");
        urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);
        log.info("file link =="+urlToUpload.getValue());
       // content.setFilePreviewLink(urlToUpload.getValue());
		String fileLink=urlToUpload.getValue();
		modelAndView.addObject("fileLink", fileLink);
        modelAndView.addObject("viewInformation", content);
		modelAndView.setViewName("viewAdddeviceInformation");
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
		addMoreFileModel.setTag("system_upload_filepath");
		urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);


		Register_UploadPaidStatus regularizeDeviceDbs  = gson.fromJson(filter, Register_UploadPaidStatus.class);
		regularizeDeviceDbs.setNationality("Cambodian");
		for(int i =0; i<regularizeDeviceDbs.getRegularizeDeviceDbs().size();i++) {
			regularizeDeviceDbs.getRegularizeDeviceDbs().get(i).setTxnId(txnNumber);
		}

		log.info(""+regularizeDeviceDbs.toString());
		log.info(" upload status  entry point.");
		try {
			byte[] bytes = file.getBytes();
		String rootPath =urlToUpload.getValue()+txnNumber+"/"; 
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
			@RequestParam(name="nid",required = false) String nid,
			@RequestParam(name="origin",required = false) String origin,
			@RequestParam(name="txnId",required = false) String txnId,
			@RequestParam(name="pageSize") Integer pageSize,
			@RequestParam(name="pageNo") Integer pageNo,
			@RequestParam(name="status", required = false) Integer status,
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
		filterRequestuserpaidStatus.setNid(nid);
		filterRequestuserpaidStatus.setTxnId(txnId);
		filterRequestuserpaidStatus.setUserId(userId);
		filterRequestuserpaidStatus.setStatus(status); 
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
	public @ResponseBody GenricResponse countByNid(@RequestParam(name="nid", required = false) String nId,@RequestParam(name="nationType", required = false) int nationType)  {
		log.info("request send to the currency  api="+nId+"  nationType   =="+nationType);
		GenricResponse response= uploadPaidStatusFeignClient.countByNid(nId,nationType);
		log.info("response from currency api "+response);
		return response;

	}

	
	//********************************************Admin Approve/Reject Controller******************************************
	
	@PostMapping("approveRejectDevice") 
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
	
	
	@GetMapping("selfRegisterDevice")
	public ModelAndView selfRegisterDevice(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		log.info("---entry point in enter nid page");
		modelAndView.setViewName("endUserNid");
		log.info("---exit  point in enter nid page");
		return modelAndView;
	}
	
	@PostMapping("selfRegisterDevicePage")
	public ModelAndView selfRegisterDevicePage(HttpSession session,@RequestParam(name="Search",required = false) String Search) {
		ModelAndView modelAndView = new ModelAndView();
		log.info("---entry point in self register page=="+Search);
		modelAndView.addObject("nid", Search);
		modelAndView.setViewName("selfRegisterDevice");
		log.info("---exit  point in self register page");
		return modelAndView;
	}
	
	
	@GetMapping("updateVisavalidity")
	public ModelAndView updateVisavalidity(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		log.info("---entry point in update visa validity page");
		modelAndView.setViewName("endUserUpdateVisaValidity");
		log.info("---exit  point in update visa validity page");
		return modelAndView;
	}
	
	@PostMapping("findEndUserByNid")
	public @ResponseBody GenricResponse findEndUserByNid(@RequestParam(name="findEndUserByNid",required = false) String findEndUserByNid) {
		log.info("---entry point in update visa validity page");
		GenricResponse endUserVisaInfo= new GenricResponse();
		log.info("Request send to the fetch recoed by Passport="+findEndUserByNid);
		endUserVisaInfo=	uploadPaidStatusFeignClient.fetchVisaDetailsbyPassport(findEndUserByNid);
		log.info("Response from fetchVisaDetailsbyPassport api== "+endUserVisaInfo);
		log.info("---exit  point in update visa validity page");
		return endUserVisaInfo;
	}
	
	@PostMapping("updateEndUSerVisaValidity")
	public @ResponseBody GenricResponse updateEndUSerVisaValidity(@RequestParam(name="passportImage",required = false) MultipartFile passportImage,@RequestParam(name="visaImage",required = false) MultipartFile visaImage,HttpServletRequest request,HttpSession session) {
		log.info("---entry point in update visa validity page");
		
		
	
		//request.setAttribute("txnId", txnNumber);
		//request.setAttribute("request[regularizeDeviceDbs][txnId]",txnNumber);
		String filter = request.getParameter("request");
		//log.info("txnid+++++++++++"+request.getParameter("request[regularizeDeviceDbs][txnId]"));
		Gson gson= new Gson(); 
		log.info("before casting request in to pojo classs"+filter);
		addMoreFileModel.setTag("system_upload_filepath");
		urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);

		addMoreFileModel.setTag("uploaded_file_move_path");
		urlToMove=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);

		EndUserVisaInfo endUservisaInfo  = gson.fromJson(filter, EndUserVisaInfo.class);

		log.info("after casting request in to pojo classs"+endUservisaInfo);
		log.info("device db size--"+endUservisaInfo.getVisaDb().size());
		  for(int i =0; i<endUservisaInfo.getVisaDb().size();i++) {
		  //regularizeDeviceDbs.getRegularizeDeviceDbs().get(i).setTxnId(txnNumber);
		  endUservisaInfo.setTxnId(endUservisaInfo.getTxnId());
		 // endUservisaInfo.getRegularizeDeviceDbs().get(i).setTxnId(txnNumber);
		  endUservisaInfo.getVisaDb().get(i).setVisaFileName((visaImage.getOriginalFilename()));
		  log.info("file name to be set in varivable="+endUservisaInfo.getVisaDb().get(i).getVisaFileName());
		  
		  }
		 
		
		//endUservisaInfo.getVisaDb().get(1).setVisaFileName((visaImage.getOriginalFilename()));

		log.info(""+endUservisaInfo);
		log.info(" upload status  entry point.");
		if(passportImage==null)
		{
			endUservisaInfo.setPassportFileName("");	
		}
		else {
			try {
				byte[] bytes = passportImage.getBytes();
			String rootPath =urlToUpload.getValue()+endUservisaInfo.getTxnId()+"/"; 
			File dir = new File(rootPath + File.separator);

			if (!dir.exists()) dir.mkdirs();
			// Create the file on server 
			File serverFile = new File(rootPath+passportImage.getOriginalFilename());
			log.info("uploaded file path on server" + serverFile); BufferedOutputStream
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes); 
			stream.close();
			endUservisaInfo.setPassportFileName(passportImage.getOriginalFilename());
			} 

			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}
		
		try {
			/*
			 * byte[] bytes = visaImage.getBytes(); String rootPath
			 * =filePathforUploadFile+endUservisaInfo.getTxnId()+"/"; File dir = new
			 * File(rootPath + File.separator);
			 * 
			 * if (!dir.exists()) dir.mkdirs(); // Create the file on server File serverFile
			 * = new File(rootPath+visaImage.getOriginalFilename());
			 * log.info("uploaded file path on server" + serverFile); BufferedOutputStream
			 * stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			 * stream.write(bytes); stream.close();
			 */
			

String rootPath = urlToUpload.getValue()+endUservisaInfo.getTxnId()+"/";
File tmpDir = new File(rootPath+visaImage.getOriginalFilename());
boolean exists = tmpDir.exists();
if(exists) {

Path temp = Files.move 
(Paths.get(urlToUpload.getValue()+"/"+endUservisaInfo.getTxnId()+"/"+visaImage.getOriginalFilename()), 
Paths.get(urlToMove.getValue()+visaImage.getOriginalFilename())); 
String movedPath=urlToMove.getValue()+visaImage.getOriginalFilename();	

log.info("file is already exist, moved to this "+movedPath+" path. ");
tmpDir.delete();
}
byte[] bytes = visaImage.getBytes();
File dir = new File(rootPath + File.separator);
if (!dir.exists()) 
dir.mkdirs();
File serverFile = new File(rootPath+visaImage.getOriginalFilename());
log.info("uploaded file path on server" + serverFile);
BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
stream.write(bytes);
stream.close();
	} 
	catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		GenricResponse endUserVisaInfo= new GenricResponse();
		log.info("Request send to the update emd user visa details ="+endUservisaInfo);
		endUserVisaInfo=	uploadPaidStatusFeignClient.updateEndUSerVisaDetailsby(endUservisaInfo);
		log.info("Response from fetchVisaDetailsbyPassport api== "+endUserVisaInfo);
		log.info("---exit  point in update visa validity page");
		return endUserVisaInfo;
	}
	
	
	
	
	@PostMapping("registerEndUserDevice")
	public @ResponseBody GenricResponse registerEndUserDevice(@RequestParam(name="visaImage",required = false) MultipartFile visaImage,@RequestParam(name="sourceType",required = false) String sourceType,
			@RequestParam(name="endUserDepartmentFile",required = false) MultipartFile endUserDepartmentFile,@RequestParam(name="uploadnationalID",required = false) MultipartFile uploadnationalID,
			HttpServletRequest request,HttpSession session,@RequestParam(name="docType",required = false) String docType) {
		log.info("---entry point in update visa validity page");
		log.info("---request---"+request.getParameter("request"));
		String txnNumber="";
		if (sourceType.equalsIgnoreCase("custom"))
		{
			txnNumber="R" + utildownload.getTxnId();
		}
		else if(sourceType.equalsIgnoreCase("Immigration")) {
			txnNumber="I" + utildownload.getTxnId();
		}
		else {
			txnNumber="A" + utildownload.getTxnId();
		}
		  
		  log.info("Random transaction id number="+txnNumber);
		   String filter = request.getParameter("request");
		   Gson gson= new Gson();
		  log.info("before casting request in to pojo classs"+filter);
		  
		  addMoreFileModel.setTag("system_upload_filepath");
			urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);
		  
		  EndUserVisaInfo endUservisaInfo = gson.fromJson(filter,  EndUserVisaInfo.class);
		   List<VisaDb> visaDbaaa= endUservisaInfo.getVisaDb();
		  if(endUservisaInfo.getNationality().equals(""))
		  {
			  log.info("nationality......");
			  endUservisaInfo.setNationality("Cambodian");
			  log.info("nationality......"+endUservisaInfo.getNationality());
		  }
		  if("N".equals(endUservisaInfo.getOnVisa())) {
			  endUservisaInfo.setVisaDb(null);
		  }
		  
		  if("N".equals(endUservisaInfo.getIsVip())) {
			  endUservisaInfo.setUserDepartment(null);
		  }
		  
		  endUservisaInfo.getOnVisa();
		  
		  log.info("after casting request in to pojo classs"+endUservisaInfo);

		  endUservisaInfo.setTxnId(txnNumber);
		  endUservisaInfo.setPassportFileName(uploadnationalID.getOriginalFilename());
			for(int i =0; i<endUservisaInfo.getRegularizeDeviceDbs().size();i++) {
				endUservisaInfo.getRegularizeDeviceDbs().get(i).setTxnId(txnNumber);
				if (sourceType.equalsIgnoreCase("enduser"))
				{
					endUservisaInfo.getRegularizeDeviceDbs().get(i).setCurrency("-1");
				}
				
			}
			if(uploadnationalID!=null) { 
			try {
				byte[] bytes = uploadnationalID.getBytes();
			String rootPath =urlToUpload.getValue()+txnNumber+"/"+docType+"/"; 
			File dir = new File(rootPath + File.separator);

			if (!dir.exists()) dir.mkdirs();
			// Create the file on server 
			File serverFile = new File(rootPath+uploadnationalID.getOriginalFilename());
			log.info("uploaded uploadnationalID file path on server" + serverFile); BufferedOutputStream
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes); 
			stream.close();
			} 

			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			}
		  
		  log.info(""+endUservisaInfo); 
		  log.info(" upload status  entry point.");
		  if(endUserDepartmentFile!=null) { 
			  log.info("department  Image is not blank");
		  
		  try {
			  byte[] bytes = endUserDepartmentFile.getBytes(); 
			  String rootPath  =urlToUpload.getValue()+txnNumber+"/";
			  File dir = new File(rootPath + File.separator);
		  
		  if (!dir.exists()) dir.mkdirs(); // Create the file on server 
		  File serverFile = new File(rootPath+endUserDepartmentFile.getOriginalFilename());
		  log.info("uploaded department  File  path on server" + serverFile); BufferedOutputStream
		  stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		  stream.write(bytes); stream.close();
		  }
		  
		  catch (Exception e) { // TODO: handle
			   e.printStackTrace(); } 
		  }
		  
		  
		
			  if(visaImage!=null) { 
				 log.info("visa Image is  not blank");
				 
			  try { byte[] bytes = visaImage.getBytes(); String rootPath
			  =urlToUpload.getValue()+txnNumber+"/"; File dir = new File(rootPath +
			  File.separator);
			  
			  if (!dir.exists()) dir.mkdirs(); // Create the file on server 
			  File serverFile = new File(rootPath+visaImage.getOriginalFilename());
			  log.info("uploaded  visa Image path on server" + serverFile); BufferedOutputStream
			  stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			  stream.write(bytes); stream.close();
			  }
			  
			  catch (Exception ex) { // TODO: handle exception e.printStackTrace(); } }
			  }
			  }
			
		  log.info("Request send to the update emd user visa details ="+endUservisaInfo
		  ); 
		  GenricResponse endUserVisaInfo= new GenricResponse();
		  endUserVisaInfo=uploadPaidStatusFeignClient.RegisterEndUserDevice(endUservisaInfo);
		  log.info("Response from fetchVisaDetailsbyPassport api== "+endUserVisaInfo);
		 
		
		log.info("---exit  point in update visa validity page");
		return endUserVisaInfo;
	}







	
	  @PutMapping("updateEndUserDevice")
	  public @ResponseBody GenricResponse updateEndUserDevice(@RequestParam(name="visaImage",required = false)
	  MultipartFile visaImage,@RequestParam(name="endUserDepartmentFile",required =
	  false) MultipartFile endUserDepartmentFile,HttpServletRequest
	  request,HttpSession session) {
	  log.info("---entry point in update visa validity page");
	  log.info("---request---"+request.getParameter("request"));
	  
	  String filter =request.getParameter("request");
	  Gson gson= new Gson();
	  log.info("before casting request in to pojo classs"+filter);
	  
	  EndUserVisaInfo endUservisaInfo = gson.fromJson(filter,EndUserVisaInfo.class);
	  GenricResponse response= new GenricResponse();
	  log.info("endUservisaInfo::::::::::"+endUservisaInfo);
	  
	  response=immigrationFeignImpl.RegisterEndUserDevice(endUservisaInfo);
	 return response; 
	  }
	  
	 }


	