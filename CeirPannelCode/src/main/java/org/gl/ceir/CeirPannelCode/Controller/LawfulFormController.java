package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UploadPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.LawfulStolenRecovey;
import org.gl.ceir.CeirPannelCode.Model.SingleImeiDetailsModel;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import CeirPannelCode.Model.Register_UploadPaidStatus;

@Controller
public class LawfulFormController 
{

	@Value ("${filePathforUploadFile}")
	String filePathforUploadFile;

	@Value ("${filePathforMoveFile}")
	String filePathforMoveFile;
	
	@Autowired
	UtilDownload utildownload;

	@Autowired
	UploadPaidStatusFeignClient uploadPaidStatusFeignClient;

	
	private final Logger log = LoggerFactory.getLogger(getClass());
	ModelAndView mv = new ModelAndView();
	GenricResponse response= new GenricResponse();
	
	
	
	@RequestMapping(value="/openlawfulStolenRecoveryPage",method = {RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView openStolenRecoveryPage(@RequestParam(name="pageType") String pageType,@RequestParam(name="pageView") String pageView,
			@RequestParam(name="txnId") String txnId)
	{
		log.info("entry point in  open stolen and recovery  page."+pageType+"   txnId   ="+txnId);
		if(pageType.equals("stolen"))
		{
			log.info("block page");

			mv.setViewName("lawfulStolen");
		}else if(pageType.equals("recovery")) {
			log.info("recovery");
			mv.setViewName("lawfulRecovery");
		}
		else if(pageType.equals("editIndivisualsStolen"))
		{
			log.info("editIndivisualsStolen");
			mv.addObject("pageName","editIndivisualStolen");
			mv.setViewName("editIndivisualStolen");
		}
		else if(pageType.equals("editCompanyStolen"))
		{
			log.info("editCompanyStolen");
			mv.addObject("pageName","editCompanyStolen");
			mv.setViewName("editCompanyStolen");
		}
		else if(pageType.equals("editIndivisualRecovery"))
		{
			log.info("editIndivisualRecovery");
			mv.addObject("pageName","editIndivisualRecovery");
			mv.setViewName("editIndivisualRecovery");
		}
		else if(pageType.equals("editCompanyRecovery"))
		{
			log.info("editCompanyRecovery");
			mv.addObject("pageName","editCompanyRecovery");
			mv.setViewName("editCompanyRecovery");
		}
		
		log.info("exit point in  open stolen and recovery   page."+pageView);
		mv.addObject("stolenTxnId",txnId);
		mv.addObject("viewType",pageView);
		return mv;
	}
	
	

	@PostMapping("lawfulIndivisualStolen")
	public @ResponseBody GenricResponse register(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		log.info("-inside controllerlawfulIndivisualStolen-------request---------");
		String userName=session.getAttribute("username").toString();
		Integer userId= (Integer) session.getAttribute("userid");
		String roletype=session.getAttribute("usertype").toString();
		String name=session.getAttribute("name").toString();
		String txnNumber="L" + utildownload.getTxnId();
		log.info("Random transaction id number="+txnNumber);
		String filter = request.getParameter("request");
		Gson gson= new Gson(); 
        log.info("*********"+filter);
        LawfulStolenRecovey lawfulIndivisualStolen  = gson.fromJson(filter, LawfulStolenRecovey.class);
        log.info(""+lawfulIndivisualStolen.toString());
        lawfulIndivisualStolen.setTxnId(txnNumber);
        lawfulIndivisualStolen.setUserId(userId);
        lawfulIndivisualStolen.setRoleType(roletype);
        lawfulIndivisualStolen.setFileName(file.getOriginalFilename());
        
		try {
			byte[] bytes = file.getBytes();
		String rootPath =filePathforUploadFile+txnNumber+"/"; 
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
		log.info("request passed to the save regularizeDeviceDbs api"+lawfulIndivisualStolen);
		GenricResponse response = null;
		try {
			response = uploadPaidStatusFeignClient.lawfulIndivisualStolen(lawfulIndivisualStolen);
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
	
	
	@PostMapping("lawfulOraganisationStolen")
	public @ResponseBody GenricResponse lawfulOraganisationStolen(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		log.info("-inside controllerlawfulIndivisualStolen-------request---------");
		String userName=session.getAttribute("username").toString();
		Integer userId= (Integer) session.getAttribute("userid");
		String roletype=session.getAttribute("usertype").toString();
		String name=session.getAttribute("name").toString();
		String txnNumber="L" + utildownload.getTxnId();
		log.info("Random transaction id number="+txnNumber);
		String filter = request.getParameter("request");
		
		Gson gson= new Gson(); 
        log.info("*********"+filter);
        LawfulStolenRecovey lawfulIndivisualStolen  = gson.fromJson(filter, LawfulStolenRecovey.class);
        log.info(""+lawfulIndivisualStolen.toString());
        lawfulIndivisualStolen.setTxnId(txnNumber);
        lawfulIndivisualStolen.setUserId(userId);
        lawfulIndivisualStolen.setRoleType(roletype);
        lawfulIndivisualStolen.setFileName(file.getOriginalFilename());
        
		try {
			byte[] bytes = file.getBytes();
		String rootPath =filePathforUploadFile+txnNumber+"/"; 
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
		log.info("request passed to the report Indivisual and comapny  api"+lawfulIndivisualStolen);
		GenricResponse response = null;
		try {
			response = uploadPaidStatusFeignClient.lawfulIndivisualStolen(lawfulIndivisualStolen);
			//GenricResponse response = null;
			log.info("---------response  from Indivisual and comapny api "+response);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.info("exception in upload paid stat error"+e);
			e.printStackTrace();

		}
		return response;
	}
	
	
	

	@PostMapping("lawfulIndivisualRecovery")
	public @ResponseBody GenricResponse lawfulIndivsualRecovery(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		String userName=session.getAttribute("username").toString();
		Integer userId= (Integer) session.getAttribute("userid");
		String roletype=session.getAttribute("usertype").toString();
		String name=session.getAttribute("name").toString();
		String txnNumber="L" + utildownload.getTxnId();
		log.info("Random transaction id number="+txnNumber);
		String filter = request.getParameter("request");
		
		Gson gson= new Gson(); 
        log.info("*********"+filter);
        LawfulStolenRecovey lawfulIndivisualStolen  = gson.fromJson(filter, LawfulStolenRecovey.class);
        log.info(""+lawfulIndivisualStolen.toString());
        lawfulIndivisualStolen.setTxnId(txnNumber);
        lawfulIndivisualStolen.setUserId(userId);
        lawfulIndivisualStolen.setRoleType(roletype);
        
       if(file==null)
       {
    	   log.info("file is blank");
    	   lawfulIndivisualStolen.setFileName("");   
       }
       else {
    	try {
    		log.info("file is not blank");
			byte[] bytes = file.getBytes();
		String rootPath =filePathforUploadFile+txnNumber+"/"; 
		File dir = new File(rootPath + File.separator);

		if (!dir.exists()) dir.mkdirs();
		// Create the file on server 
		File serverFile = new File(rootPath+file.getOriginalFilename());
		log.info("uploaded file path on server" + serverFile); BufferedOutputStream
		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes); 
		stream.close();
		 lawfulIndivisualStolen.setFileName(file.getOriginalFilename());
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
       }
       log.info("request passed to the report Indivisual and comapny recovery api"+lawfulIndivisualStolen);
		GenricResponse response = null;
		try {
			response = uploadPaidStatusFeignClient.lawfulIndivisualStolen(lawfulIndivisualStolen);
			//GenricResponse response = null;
			log.info("---------response  from Indivisual and comapny recovery api "+response);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.info("exception in upload paid stat error"+e);
			e.printStackTrace();

		}
		return response;
	}


	@RequestMapping(value="/openStolenAndRecoveryPage",method ={org.springframework.web.bind.annotation.RequestMethod.POST})
	public @ResponseBody LawfulStolenRecovey openSingleImeiForm(@RequestParam(name="txnId",required = false) String txnId,HttpSession session)
	{
		log.info("entry point of  fetch lawful stolen an recovery devices in the bases of transaction id .");
		
		LawfulStolenRecovey lawfulStolenRecovery= new LawfulStolenRecovey();
		LawfulStolenRecovey lawfulUser= new LawfulStolenRecovey();
		log.info("request passed to the fetch Device api="+txnId);
		lawfulUser.setTxnId(txnId);
		lawfulStolenRecovery=uploadPaidStatusFeignClient.fetchSingleDevicebyTxnId(lawfulUser);
		log.info("response from fetch lawful stolen an recovery devices  api="+lawfulStolenRecovery);
			return lawfulStolenRecovery;
	}
	
	
	@PostMapping("lawfulIndivisualStolenUpdate")
	public @ResponseBody GenricResponse updateStolenRequest(@RequestParam(name="file",required = false) MultipartFile file,HttpServletRequest request,HttpSession session) {
		log.info("-inside controller lawfulIndivisualStolen update-------request---------");
		String userName=session.getAttribute("username").toString();
		Integer userId= (Integer) session.getAttribute("userid");
		String roletype=session.getAttribute("usertype").toString();
		String name=session.getAttribute("name").toString();
		
		String filter = request.getParameter("request");
		Gson gson= new Gson(); 
        log.info("*********"+filter);
        LawfulStolenRecovey lawfulIndivisualStolen  = gson.fromJson(filter, LawfulStolenRecovey.class);
        log.info(""+lawfulIndivisualStolen.toString());
        lawfulIndivisualStolen.setUserId(userId);
        lawfulIndivisualStolen.setRoleType(roletype);
        
        if(file==null) {
        	log.info("file is empty");	
        }
        else {
		try {
			
			byte[] bytes = file.getBytes();
		String rootPath =filePathforUploadFile+lawfulIndivisualStolen.getTxnId()+"/"; 
		File dir = new File(rootPath + File.separator);

		if (!dir.exists()) dir.mkdirs();
		// Create the file on server 
		File serverFile = new File(rootPath+file.getOriginalFilename());
		log.info("uploaded file path on server" + serverFile); BufferedOutputStream
		stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes); 
		stream.close();
		lawfulIndivisualStolen.setFileName(file.getOriginalFilename());
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
		log.info("request passed to the update stolen  api"+lawfulIndivisualStolen);
		GenricResponse response = null;
		try {
			response = uploadPaidStatusFeignClient.updateIndivisualStolen(lawfulIndivisualStolen);
			//GenricResponse response = null;
			log.info("---------response from indivisual stolen api --------"+response);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.info("exception in upload paid stat error"+e);
			e.printStackTrace();

		}
		return response;
	}
	
}
