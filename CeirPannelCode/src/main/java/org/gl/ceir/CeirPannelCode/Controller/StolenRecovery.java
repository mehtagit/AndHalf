package org.gl.ceir.CeirPannelCode.Controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.StockUploadModel;
import org.gl.ceir.CeirPannelCode.Model.StolenRecoveryModel;
import org.gl.ceir.CeirPannelCode.Model.Usertype;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StolenRecovery {

	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired

	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	UtilDownload utildownload;
	
	
	
	@RequestMapping(value={"/stolenRecovery"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST})
			public ModelAndView  viewStolenRecovery( HttpSession session , @RequestParam(name="userTypeId",required=false) String selectedUserTypeId ) {
		ModelAndView mv = new ModelAndView();
		log.info("entry point in stolen recovery  page");
		String roletype=session.getAttribute("usertype").toString();
		if(selectedUserTypeId==null)
		{
		List<Usertype> userTypelist=(List<Usertype>) session.getAttribute("usertypeList");
		if(userTypelist.size()>1)
		{   log.info("role type list=="+userTypelist);
			mv.addObject("userTypelist", userTypelist);
			mv.setViewName("StolenRecoverytRoleType");
		}
		else if(userTypelist.size()==1)
		{
		log.info("role type is"+roletype);
		session.setAttribute("selectedUserTypeId", roletype);
		mv.setViewName("stolenRecovery");
		}
		}
		else {
			log.info("selected role type in stolen and recovery  is = "+selectedUserTypeId);
			session.setAttribute("selectedUserTypeId", selectedUserTypeId);
			mv.setViewName("stolenRecovery");		
		
		}
				
				return mv; 
			}
	
//******************************************* multiple stolen recovery ************************************************************************88	
	  @RequestMapping(value={"/multipleStolenRecovery"},method={org.springframework.web. bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.
	  RequestMethod.POST}) 
	  public @ResponseBody GenricResponse markStolen(@RequestBody ArrayList<StolenRecoveryModel>  stolen)
	  { 
	  log.info("enter in multiple stolenRecovery controller");
		
		/*
		 * List<StolenRecoveryModel> request= new ArrayList<StolenRecoveryModel>();
		 * request.add(stolen);
		 */
		 
	  log.info("stolen request  passed to the multiple stolen ="+stolen);
	  GenricResponse response=  feignCleintImplementation.multipleStolen(stolen);
	  log.info("response from multiple Stolen api=="+response);
	  log.info(" multiple stolen recovery  exit point .");
	  return response;
	  
	  }
	 
//*************************************************** file type stolen ****************************************************************************
	  
	  @RequestMapping(value={"/fileTypeStolen"},method={org.springframework.web. bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.
			  RequestMethod.POST}) 
	  public @ResponseBody GenricResponse FileTypeStolen(@RequestParam(name="blockingType",required = false) String blockingType,@RequestParam(name="blockingTimePeriod",required = false) String blockingTimePeriod,
			  @RequestParam(name="file",required = false) MultipartFile file,@RequestParam(name="requestType",required = false) String requestType,
			  @RequestParam(name="roleType",required = false) String roleType,  @RequestParam(name="sourceType",required = false) String sourceType,
			  @RequestParam(name="userId",required = false) Integer userId)
	  {	
		  log.info(" file stolen entry point .");
		 
		    StolenRecoveryModel stolenRecoveryModel= new StolenRecoveryModel(); 
		    GenricResponse response= new GenricResponse();
			String stlnTxnNumber=utildownload.getTxnId();
			stlnTxnNumber = "S"+stlnTxnNumber;
			log.info("Random transaction id number="+stlnTxnNumber);
		  	try {
				byte[] bytes = file.getBytes();
				String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+stlnTxnNumber+"/";
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
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			stolenRecoveryModel.setBlockingTimePeriod(blockingTimePeriod);
			stolenRecoveryModel.setBlockingType(blockingType);
			stolenRecoveryModel.setFileName(file.getOriginalFilename());
			stolenRecoveryModel.setRequestType(requestType);
			stolenRecoveryModel.setSourceType(sourceType);
			stolenRecoveryModel.setUserId(userId);
			stolenRecoveryModel.setRoleType(roleType);
			stolenRecoveryModel.setTxnId(stlnTxnNumber);
			log.info("request passed to the file stolen api ="+stolenRecoveryModel);
			response=feignCleintImplementation.fileStolen(stolenRecoveryModel);
			log.info("respondse from file stolen api="+response);
			log.info(" file stolen api exist point .");
		  	return response;
	
	  }
	  
	  
// ************************************************************ file type recovery ********************************************************************
	  
	  @RequestMapping(value={"/fileTypeRecovery"},method={org.springframework.web. bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.
			  RequestMethod.POST}) 
	  public @ResponseBody GenricResponse fileTypeRecovery( @RequestParam(name="file",required = false) MultipartFile file,@RequestParam(name="requestType",required = false) String requestType,
			  @RequestParam(name="roleType",required = false) String roleType,  @RequestParam(name="sourceType",required = false) String sourceType,
			  @RequestParam(name="userId",required = false) Integer userId)
	  {	
		  
		  log.info(" file Recovery api entry point .");
		  StolenRecoveryModel stolenRecoveryModel= new StolenRecoveryModel(); 
		  GenricResponse response= new GenricResponse();
			String stlnTxnNumber=utildownload.getTxnId();
			stlnTxnNumber = "S"+stlnTxnNumber;
			log.info("Random transaction id number="+stlnTxnNumber);
		  	try {
				byte[] bytes = file.getBytes();
				String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+stlnTxnNumber+"/";
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
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			stolenRecoveryModel.setFileName(file.getOriginalFilename());
			stolenRecoveryModel.setRequestType(requestType);
			stolenRecoveryModel.setSourceType(sourceType);
			stolenRecoveryModel.setUserId(userId);
			stolenRecoveryModel.setRoleType(roleType);
			stolenRecoveryModel.setTxnId(stlnTxnNumber);
			log.info("request sent to fileRecovery api ="+stolenRecoveryModel);
			response=feignCleintImplementation.fileRecovery(stolenRecoveryModel);
			log.info("request sent to file Recovery api ="+response);
			log.info(" file Recovery api exist point .");
			
			return response;
	
	  }

	  
// ***************************************** delete stolen recovery controller **************************************************************

		@RequestMapping(value= {"/stolenRecoveryDelete"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
		public @ResponseBody GenricResponse deleteStock(@RequestBody StolenRecoveryModel stolenRecoveryModel,HttpSession session) {

			log.info("enter in  delete stolenRecovery.");
			log.info("request passed to the delete stolenRecovery Api="+stolenRecoveryModel);
			GenricResponse response=feignCleintImplementation.deleteStolenRecovery(stolenRecoveryModel);
			log.info("response after delete stolenRecovery."+response);
			log.info("exit point of delete stolenRecovery.");
			return response;
			

		}
		
// ****************************************** update stolen recovery controller**********************************************************8
		 
		
			  
			  @RequestMapping(value={"/updateFileTypeStolenRecovery"},method={org.springframework.web. bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.
					  RequestMethod.POST}) 
			  public @ResponseBody GenricResponse updateFileTypeStolenRecovery(@RequestParam(name="blockingType",required = false) String blockingType,@RequestParam(name="blockingTimePeriod",required = false) String blockingTimePeriod,
					  @RequestParam(name="file",required = false) MultipartFile file,@RequestParam(name="requestType",required = false) String requestType,
					  @RequestParam(name="roleType",required = false) String roleType,  @RequestParam(name="sourceType",required = false) String sourceType,
					  @RequestParam(name="userId",required = false) Integer userId,@RequestParam(name="txnId",required = false) String txnId,@RequestParam(name="id",required = false) int id)
			  {	
				  StolenRecoveryModel stolenRecoveryModel= new StolenRecoveryModel();
				  GenricResponse response = new GenricResponse();
				  log.info(" update file stolen/recovery entry point .");
				  log.info("Random transaction id number="+txnId);
				  	try {
				  		String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnId+"/";
				  		File tmpDir = new File(rootPath+file.getOriginalFilename());
				  		boolean exists = tmpDir.exists();

				  		if(exists) {
				  		Path temp = Files.move 
				  		(Paths.get("/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnId+"/"+file.getOriginalFilename()), 
				  		Paths.get("/home/ubuntu/apache-tomcat-9.0.4/webapps/MovedFiles/"+file.getOriginalFilename())); 

				  		String movedPath="/home/ubuntu/apache-tomcat-9.0.4/webapps/MovedFiles/"+file.getOriginalFilename();
				  		// tmpDir.renameTo(new File("/home/ubuntu/apache-tomcat-9.0.4/webapps/MovedFile/"+txnId+"/"));
				  		log.info("file is already exist moved to the this "+movedPath+" path");
				  		tmpDir.delete();
				  		}
						byte[] bytes = file.getBytes();
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
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					stolenRecoveryModel.setBlockingTimePeriod(blockingTimePeriod);
					stolenRecoveryModel.setBlockingType(blockingType);
					stolenRecoveryModel.setFileName(file.getOriginalFilename());
					stolenRecoveryModel.setRequestType(requestType);
					stolenRecoveryModel.setSourceType(sourceType);
					stolenRecoveryModel.setUserId(userId);
					stolenRecoveryModel.setRoleType(roleType);
					stolenRecoveryModel.setTxnId(txnId);
					stolenRecoveryModel.setId(id);
					
					log.info("request passed to the update file stolen api ="+stolenRecoveryModel);
					response=feignCleintImplementation.fileStolen(stolenRecoveryModel);
					log.info("respondse from update  file stolen api="+response);
					log.info(" update  file stolen api exist point .");
				  	return response;
			
			  }
			  
			  
}
