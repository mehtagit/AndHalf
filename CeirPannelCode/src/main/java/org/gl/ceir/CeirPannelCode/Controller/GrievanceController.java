package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Feignclient.GrievanceFeignClient;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.GrievanceModel;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import CeirPannelCode.Model.Register_UploadPaidStatus;

@Controller
public class GrievanceController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired

	
	@Value ("${filePathforUploadFile}")
	String filePathforUploadFile;

	@Value ("${filePathforMoveFile}")
	String filePathforMoveFile;
	
	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	UtilDownload utildownload;
	@Autowired
	GrievanceFeignClient grievanceFeignClient;
	
	GrievanceModel grievance= new GrievanceModel();
	GenricResponse response = new GenricResponse();
	
	@RequestMapping(value=
		{"grievanceManagement"},method={org.springframework.web.bind.annotation.
				RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
			)
	    public ModelAndView viewGrievance(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		 
		log.info(" view Grievance entry point."); 
	    mv.setViewName("grievanceManagement");
		log.info(" view Grievance exit point."); 
		return mv; 
	}
	
	
	@RequestMapping(value= {"/saveGrievance"},method= RequestMethod.POST,consumes = "multipart/form-data") 
	public @ResponseBody GenricResponse saveGrievance(@RequestParam(name="files[]") MultipartFile[] fileUpload,HttpServletRequest request,HttpSession session) {


		int userId= (int) session.getAttribute("userid");
		String roletype=(String) session.getAttribute("usertype");

		String grevnceId=utildownload.getTxnId();
		grevnceId = "G"+grevnceId;
		Gson gson= new Gson(); 
		String grievanceDetails=request.getParameter("multirequest");
		log.info("grievanceDetails------"+grievanceDetails);

		GrievanceModel grievanceRequest  = gson.fromJson(grievanceDetails, GrievanceModel.class);
		grievanceRequest.setUserId(userId);
		grievanceRequest.setUserType(roletype);
		grievanceRequest.setGrievanceId(grevnceId);

		for (int i=0;i<grievanceRequest.getAttachedFiles().size();i++) {
			grievanceRequest.getAttachedFiles().get(i).setGrievanceId(grevnceId);
			//grievanceRequest.getMultifile().get(i).getDocType();
		}

		log.info("Random  genrated transaction number ="+grevnceId);
		int i=0;
		for( MultipartFile file : fileUpload) {

			log.info("-----"+ file.getOriginalFilename());
			log.info("++++"+ file);
		
			String tagName=grievanceRequest.getAttachedFiles().get(i).getDocType();
			log.info("doctype Name==="+tagName+"value of index="+i);
			

			try {
				byte[] bytes =
						file.getBytes(); String rootPath = filePathforUploadFile+grevnceId+"/"+tagName+"/"; 
						File dir =   new File(rootPath + File.separator);
						if (!dir.exists()) dir.mkdirs(); // Create the file on server // Calendar now = Calendar.getInstance();
						File serverFile = new File(rootPath+file.getOriginalFilename());
						log.info("uploaded file path on server" + serverFile); BufferedOutputStream
						stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes); stream.close(); 
						//  grievanceRequest.setFileName(file.getOriginalFilename());

			}
			catch (Exception e) { //
				// TODO: handle exception e.printStackTrace(); }

				// set reaquest parameters into model class

			}
			i++;


		}
		/*
		 * grievance.setCategoryId(categoryId); grievance.setRemarks(remarks);
		 * grievance.setTxnId(txnId);
		 */
		/*
		 * grievance.setUserId(userId); grievance.setUserType(roletype);
		 * grievance.setGrievanceId(grevnceId);
		 * 
		 */
		log.info("grievance form parameters passed to save grievance api "+grievanceRequest);
		 response = grievanceFeignClient.saveGrievance(grievanceRequest);
		 response.setTxnId(grevnceId);

		 log.info("response from save grievance api"+response);
		 log.info("save grievance  exit point.");
		 return response;
	}
//***************************************** open save greivance *********************************
	@RequestMapping(value="/openGrievanceForm",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView openRegisterConsignmentForm(@RequestParam(name="reqType") String reqType)
	{
		ModelAndView mv= new ModelAndView();
		if(reqType.equals("formPage"))
		{
			log.info("open save  Grievance form");
			mv.setViewName("saveGrievance");
		}

		return mv;
}


	//***************************************** view Grievance controller *********************************
		@RequestMapping(value="/viewGrievance",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
		public @ResponseBody List<GrievanceModel> viewGrievance(@RequestParam(name="grievanceId") String grievanceId,HttpSession session ,@RequestParam(name="recordLimit") Integer recordLimit )
		{
			log.info("entery point in view grievance.");
			int userId= (int) session.getAttribute("userid");
			List<GrievanceModel>  grievanceModel=new ArrayList<GrievanceModel> ();
			log.info("Request pass to the view grievance api ="+grievanceId+"  userId= "+userId);
			grievanceModel=grievanceFeignClient.viewGrievance(grievanceId, userId,recordLimit);
			log.info("Response from  view grievance api = "+grievanceModel);
			return grievanceModel;
	}
		
		
		
		//***************************************** view Grievance controller *********************************
				@RequestMapping(value="/saveGrievanceMessage",method ={org.springframework.web.bind.annotation.RequestMethod.POST})
				public @ResponseBody GenricResponse saveGrievanceReply(@RequestParam(name="files[]") MultipartFile[] fileUpload,HttpServletRequest request,HttpSession session)
				{
				

				//	log.info("grievanceId=="+grievanceId+ " remark ="+remark+" txnId="+txnId+" file name=="+file.getOriginalFilename());
					int userId= (int) session.getAttribute("userid"); 
					String roletype=(String) session.getAttribute("usertype");
				    log.info("userid=="+userId+" roletype="+roletype);
				    
				    String grievanceDetails=request.getParameter("multirequest");
					log.info("grievanceDetails------"+grievanceDetails);
					Gson gson= new Gson(); 	
					GrievanceModel grievanceRequest  = gson.fromJson(grievanceDetails, GrievanceModel.class);
					grievanceRequest.setUserId(userId);
					grievanceRequest.setUserType(roletype);
					int i=0;
					for( MultipartFile file : fileUpload) {
						String tagName=grievanceRequest.getAttachedFiles().get(i).getDocType();
					try {
						if(fileUpload==null)
						{
							grievanceRequest.getAttachedFiles().get(i).setFileName("");
						}
						else {
						byte[] bytes = file.getBytes();
						String rootPath = filePathforUploadFile+grievanceRequest.getGrievanceId()+"/"+tagName+"/";
						File dir = new File(rootPath + File.separator);

						if (!dir.exists()) 
							dir.mkdirs();
						// Create the file on server
						// Calendar now = Calendar.getInstance();

						File serverFile = new File(rootPath+file.getOriginalFilename());
						log.info("uploaded file path on server" + serverFile);
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();
						}
						
						
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					i++;
					}
		/*
		 * grievanceModel.setTxnId(txnId); grievanceModel.setReply(remark);
		 * grievanceModel.setGrievanceId(grievanceId); grievanceModel.setUserId(userId);
		 * grievanceModel.setUserType(roletype);
		 * grievanceModel.setGrievanceStatus(grievanceTicketStatus);
		 */
				
				log.info("request passed to the save grievance method="+grievanceRequest);
				response= grievanceFeignClient.saveGrievanceMessage(grievanceRequest);
				log.info("response  from   save grievance method="+response);	
				response.setTxnId(grievanceRequest.getGrievanceId());
				return response;
			}

				//***************************************** Export Grievance controller *********************************
						@RequestMapping(value="/exportGrievance",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
						public String exportToExcel(@RequestParam(name="grievanceStartDate",required = false) String grievanceStartDate,@RequestParam(name="grievanceEndDate",required = false) String grievanceEndDate,
								@RequestParam(name="grievancetxnId",required = false) String grievancetxnId,@RequestParam(name="grievanceId") String grievanceId,HttpServletRequest request,
								HttpSession session,@RequestParam(name="pageSize") Integer pageSize,@RequestParam(name="pageNo") Integer pageNo,@RequestParam(name="grievanceStatus") Integer grievanceStatus)
						{
							log.info("grievanceStartDate=="+grievanceStartDate+ " grievanceEndDate ="+grievanceEndDate+" grievancetxnId="+grievancetxnId+"grievanceId="+grievanceId);
							int userId= (int) session.getAttribute("userid"); 
							int file=1;
							String userType=(String) session.getAttribute("usertype");
						    Integer usertypeId=(int) session.getAttribute("usertypeId");
							FileExportResponse fileExportResponse;
							FilterRequest filterRequest= new FilterRequest();
							filterRequest.setStartDate(grievanceStartDate);
							filterRequest.setEndDate(grievanceEndDate);
							filterRequest.setTxnId(grievancetxnId);
							filterRequest.setGrievanceStatus(grievanceStatus);
							filterRequest.setGrievanceId(grievanceId);
							filterRequest.setUserId(userId);
							filterRequest.setUserType(userType);
							filterRequest.setUserTypeId(usertypeId);
							log.info(" request passed to the exportTo Excel Api =="+filterRequest+" *********** pageSize"+pageSize+"  pageNo  "+pageNo);
						Object	response= grievanceFeignClient.grievanceFilter(filterRequest,pageNo,pageSize,file);
						
						Gson gson= new Gson(); 
						String apiResponse = gson.toJson(response);
						fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
							log.info("response  from   export grievance  api="+fileExportResponse);
							
							return "redirect:"+fileExportResponse.getUrl();
					}

						@RequestMapping(value={"/openEndUserGrievancePage"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST})
						    public  ModelAndView openEndUserGrievancePage(@RequestParam(name="reportType") Integer reportType) 
						{
							ModelAndView mv = new ModelAndView();
							 
							log.info(" view End user Grievance entry point."+reportType); 
						    mv.setViewName("openEndUserGrievancePage");
							log.info(" view End user Grievance exit point."); 
							return mv; 
						}

}

