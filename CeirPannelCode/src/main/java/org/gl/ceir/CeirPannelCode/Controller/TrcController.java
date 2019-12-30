package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.TypeApprovedFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.TRCRegisteration;
import org.gl.ceir.CeirPannelCode.Model.TRCRequest;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.gl.ceir.interfaceImpl.RegisterationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;



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

	@ResponseBody
	@PostMapping("register-approved-device")
	public GenricResponse register(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		log.info("-inside controller register-approved-device-------request---------"+request.getParameter("manufacturerId"));
		// log.info(""+request.getParameter("file"));
		String userName=session.getAttribute("username").toString();
		String userId= session.getAttribute("userid").toString();
		String name=session.getAttribute("name").toString();
		Map<String, String[]> parameterMap = request.getParameterMap();
		log.info("************"+parameterMap.toString());
		log.info(" Register consignment entry point.");
		String txnNumber="T" + utildownload.getTxnId();
		log.info("Random transaction id number="+txnNumber);
		request.getParameterValues("");
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
		TRCRegisteration model = registerationImpl.register(request,file.getOriginalFilename(),txnNumber);
		log.info("---------model--------"+model);
		GenricResponse response = typeApprovedFeignImpl.register(model);
		//GenricResponse response = null;
		log.info("---------response--------"+response);
		return response;
	}

	@ResponseBody
	@PostMapping("viewByID/{id}")
	public ResponseEntity<?> viewByID(@PathVariable("id") int id) {
		TRCRegisteration result = typeApprovedFeignImpl.viewByID(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}



	//************************************************ update consignment record page********************************************************************************/

	@PostMapping("update-register-approved-device")
	public @ResponseBody GenricResponse updateRegister(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session,
			@RequestParam(name="manufacturerId",required = false) String manufacturerId,@RequestParam(name="manufacturerName",required = false) String manufacturerName,@RequestParam(name="country",required = false) String country,
			@RequestParam(name="requestDate",required = false) String requestDate,@RequestParam(name="tac",required = false) String tac,@RequestParam(name="approveDisapproveDate",required = false) String approveDisapproveDate,
			@RequestParam(name="status",required = false) Integer approveStatus,@RequestParam(name="remark",required = false) String remark,@RequestParam(name="txnId",required = false) String txnId,
			@RequestParam(name="fileName",required = false) String fileName,@RequestParam(name="id",required = false) Integer id) {
		log.info("---------request---------"+request.getParameter("manufacturerId"));
		// log.info(""+request.getParameter("file"));

		Integer userId= Integer.parseInt(session.getAttribute("userid").toString());
		request.setAttribute("userId",userId );
		log.info(" updateRegister consignment entry point.");
		log.info(" requestDate=="+requestDate);
		TRCRegisteration model = new TRCRegisteration();

		log.info("txnid="+txnId);
		if (file==null)
		{	
			log.info("file is not empty");
			model.setManufacturerId(manufacturerId);
			model.setManufacturerName(manufacturerName);
			model.setCountry(country);
			model.setRequestDate(requestDate);
			model.setTac(tac);
			model.setApproveDisapproveDate(approveDisapproveDate);
			model.setApproveStatus(approveStatus);
			model.setStatus(approveStatus);
			model.setRemark(remark);
			model.setFile(fileName);
			model.setTxnId(txnId);
			model.setId(id);
			model.setUserId(userId);
		}
		else {

			try { byte[] bytes = file.getBytes();
			String rootPath ="/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnId+"/"; 
			File dir = new File(rootPath + File.separator);
			File tmpDir = new File(rootPath+file.getOriginalFilename());
			boolean exists = tmpDir.exists();
			if(exists) {

				Path temp = Files.move 
						(Paths.get("/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnId+"/"+file.getOriginalFilename()), 
								Paths.get("/home/ubuntu/apache-tomcat-9.0.4/webapps/MovedFiles/"+file.getOriginalFilename())); 
				String movedPath="/home/ubuntu/apache-tomcat-9.0.4/webapps/MovedFiles/"+file.getOriginalFilename();	
				// tmpDir.renameTo(new File("/home/ubuntu/apache-tomcat-9.0.4/webapps/MovedFile/"+txnId+"/"));
				log.info("file is already exist, moved to this "+movedPath+" path. ");
				tmpDir.delete();
			}
			if (!dir.exists()) dir.mkdirs();
			// Create the file on server 
			File serverFile = new File(rootPath+file.getOriginalFilename());
			log.info("uploaded file path on server" + serverFile); BufferedOutputStream
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes); stream.close();
			log.info("before hit");
			model.setManufacturerId(manufacturerId);
			model.setManufacturerName(manufacturerName);
			model.setCountry(country);
			model.setRequestDate(requestDate);
			model.setTac(tac);
			model.setApproveDisapproveDate(approveDisapproveDate);
			model.setApproveStatus(approveStatus);
			model.setRemark(remark);
			model.setFile(fileName);
			model.setTxnId(txnId);
			model.setUserId(userId);
			model.setId(id);
			} 

			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} 
		}
		// set request parameters into model class
		log.info("request passed to the update register api="+model);
		GenricResponse response = typeApprovedFeignImpl.updateApproved(model);
		log.info("response from update api=="+response);
		return response;
	}



	//***************************************** Export Grievance controller *********************************
	@RequestMapping(value="/exportTac",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String exportToExcel(@RequestParam(name="tacStartDate",required = false) String tacStartDate,@RequestParam(name="tacStatus",required = false) Integer tacStatus,
			HttpSession session,@RequestParam(name="pageSize") Integer pageSize,@RequestParam(name="pageNo") Integer pageNo,@RequestParam(name="tacNumber") String tacNumber,
			@RequestParam(name="tacEndDate",required = false) String tacEndDate)
	{
		log.info("tacStartDate=="+tacStartDate+ " tacStatus ="+tacStatus+" tacNumber="+tacNumber+"tacEndDate="+tacEndDate);
		int userId= (int) session.getAttribute("userid"); 
		int file=1;
		FileExportResponse fileExportResponse;
		TRCRequest trcRequest = new TRCRequest();
		trcRequest.setStartDate(tacStartDate);
		trcRequest.setEndDate(tacEndDate);
		trcRequest.setTac(tacNumber);
		trcRequest.setStatus(tacStatus);
		trcRequest.setUserId(userId);
		log.info(" request passed to the exportTo trcRequest Excel Api =="+trcRequest+" *********** pageSize"+pageSize+"  pageNo  "+pageNo);
		Object	response= typeApprovedFeignImpl.manageTypeFeign(trcRequest, pageNo, pageSize, file);

		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
		log.info("response  from   export trc  api ="+fileExportResponse);

		return "redirect:"+fileExportResponse.getUrl();
	}
			

}