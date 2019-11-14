package org.gl.ceir.CeirPannelCode.Controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
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
		String roletype=session.getAttribute("usertype").toString();
		log.info("stolen page"+selectedUserTypeId); 
		if(selectedUserTypeId==null)
		{
		List<Usertype> userTypelist=(List<Usertype>) session.getAttribute("usertypeList");
		log.info("role type list=="+userTypelist);
		
		log.info("list size of  usertype=="+userTypelist.size());
		
		if(userTypelist.size()>1)
		{
			log.info("if condition.");

			mv.addObject("userTypelist", userTypelist);
			mv.setViewName("StolenRecoverytRoleType");
		}
		else if(userTypelist.size()==1)
		{
		log.info("else condition.");
		session.setAttribute("selectedUserTypeId", selectedUserTypeId);
		mv.setViewName("stolenRecovery");
		}
		
		}
		else {
			log.info("else condition selectedUserTypeId is not empty="+selectedUserTypeId);
			session.setAttribute("selectedUserTypeId", selectedUserTypeId);
			mv.setViewName("stolenRecovery");		
		
		}
				
				return mv; 
			}
	
//******************************************* multiple stolen recovery ************************************************************************88	
	  @RequestMapping(value={"/multipleStolenRecovery"},method={org.springframework.web. bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.
	  RequestMethod.POST}) 
	  public @ResponseBody GenricResponse markStolen(@RequestBody  StolenRecoveryModel stolen)
	  { 
	  log.info("enter in stolenRecovery controller");
	  log.info("StolenRequest details=="+stolen);
	  List<StolenRecoveryModel> request= new ArrayList<StolenRecoveryModel>();
	  request.add(stolen);
	  GenricResponse response=  feignCleintImplementation.multipleStolen(request);
	  log.info("response from feign=="+response);
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
		  log.info("blockingType value="+blockingType+" blockingTimePeriod=="+blockingTimePeriod+" file value="+file+"  requestType="+requestType+" roleType=="+roleType+" sourceType="+sourceType+" userId="+userId );
			
		  StolenRecoveryModel stolenRecoveryModel= new StolenRecoveryModel(); 
		  GenricResponse response= new GenricResponse();
			String stlnTxnNumber=utildownload.getTxnId();
			stlnTxnNumber = "S"+stlnTxnNumber;
			log.info("txnid="+stlnTxnNumber);
		  	try {
				byte[] bytes = file.getBytes();
				String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+stlnTxnNumber+"/";
				File dir = new File(rootPath + File.separator);

				if (!dir.exists()) 
					dir.mkdirs();
				// Create the file on server
				// Calendar now = Calendar.getInstance();

				File serverFile = new File(rootPath+file.getOriginalFilename());
				log.info("COMPLETE PATH" + serverFile);
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
			log.info("request sent to feign=="+stolenRecoveryModel);
			response=feignCleintImplementation.fileStolen(stolenRecoveryModel);
		  	return response;
	
	  }
	  
	  
// ************************************************************ file type recovery ********************************************************************
	  
	  @RequestMapping(value={"/fileTypeRecovery"},method={org.springframework.web. bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.
			  RequestMethod.POST}) 
	  public @ResponseBody GenricResponse fileTypeRecovery( @RequestParam(name="file",required = false) MultipartFile file,@RequestParam(name="requestType",required = false) String requestType,
			  @RequestParam(name="roleType",required = false) String roleType,  @RequestParam(name="sourceType",required = false) String sourceType,
			  @RequestParam(name="userId",required = false) Integer userId)
	  {			
		  log.info(" file value="+file+"  requestType="+requestType+" roleType=="+roleType+" sourceType="+sourceType+" userId="+userId );
			
		  StolenRecoveryModel stolenRecoveryModel= new StolenRecoveryModel(); 
		  GenricResponse response= new GenricResponse();
			String stlnTxnNumber=utildownload.getTxnId();
			stlnTxnNumber = "S"+stlnTxnNumber;
			log.info("txnid="+stlnTxnNumber);
		  	try {
				byte[] bytes = file.getBytes();
				String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+stlnTxnNumber+"/";
				File dir = new File(rootPath + File.separator);

				if (!dir.exists()) 
					dir.mkdirs();
				// Create the file on server
				// Calendar now = Calendar.getInstance();

				File serverFile = new File(rootPath+file.getOriginalFilename());
				log.info("COMPLETE PATH" + serverFile);
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
			log.info("request sent to feign=="+stolenRecoveryModel);
			response=feignCleintImplementation.fileStolen(stolenRecoveryModel);
		  	return response;
	
	  }

	  

}
