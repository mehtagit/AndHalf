package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Model.StockUploadModel;
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
public class Stock {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired

	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	UtilDownload utildownload;
	
	
	
	@RequestMapping(value={"/assignDistributor"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST})
			public ModelAndView  viewStock( HttpSession session , @RequestParam(name="userTypeId",required=false) String selectedUserTypeId ) {
		ModelAndView mv = new ModelAndView();
		
		log.info("stock page"+selectedUserTypeId); 
		if(selectedUserTypeId==null)
		{
		List<Usertype> userTypelist=(List<Usertype>) session.getAttribute("usertypeList");
		log.info("role type list=="+userTypelist);
		
		log.info("list size of  usertype=="+userTypelist.size());
		
		if(userTypelist.size()>1)
		{
			log.info("if condition.");

			mv.addObject("userTypelist", userTypelist);
			mv.setViewName("assignDistributor");
		}
		else if(userTypelist.size()==1)
		{
		log.info("else condition.");
		session.setAttribute("selectedUserTypeId", selectedUserTypeId);
		mv.setViewName("ViewStock");
		}
		}
		else {
			log.info("else condition selectedUserTypeId is not empty="+selectedUserTypeId);
			session.setAttribute("selectedUserTypeId", selectedUserTypeId);
			mv.setViewName("ViewStock");
		
		}
				
				return mv; 
			}


	// *********************************************** open register page or edit page ******************************
	@RequestMapping(value="/openUploadStock",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView openRegisterConsignmentForm(@RequestParam(name="reqType") String reqType,@RequestParam(name="txnId",required = false) String txnId)
	{
		ModelAndView mv= new ModelAndView();
		if(reqType.equals("formPage"))
		{
			log.info("open uploadStock form");
			mv.setViewName("uploadStock");
		}
		else if(reqType.equals("editPage")) {
			//ConsignmentModel  consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
			log.info("consignment details=");
			
			log.info("open Update registration Consignment form");
			mv.setViewName("editConsignment");
			//mv.addObject("consignmentdetails", consignmentdetails);
		}
		else {
			//ConsignmentModel  consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
			log.info("consignment details=");

			log.info("open view  registration Consignment form");
			mv.setViewName("viewConsignmentRecord");
			//mv.addObject("consignmentdetails", consignmentdetails);

		}
		return mv;

	}
	
	@RequestMapping(value= {"/uploadStock"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public @ResponseBody GenricResponse registerConsignment(@RequestParam(name="supplierId",required = false) int supplierId,@RequestParam(name="supplierName",required = false) String supplierName
			,@RequestParam(name="invoiceNumber",required = false) String invoiceNumber,@RequestParam(name="quantity",required = false) int quantity,
			@RequestParam(name="file",required = false) MultipartFile file,HttpSession session) {

		String userName=session.getAttribute("username").toString();
		int userId= (int) session.getAttribute("userid");
		String name=session.getAttribute("name").toString();
		String roletype=session.getAttribute("usertype").toString();
		//String selectedUserTypeId=session.getAttribute("selectedUserTypeId").toString();
		
		log.info(" values from session  username="+userName+ "userId="+userId+" name ="+name);
		
		log.info(" Register consignment entry point.");

		String txnNumner=utildownload.getTxnId();
		txnNumner = "C"+txnNumner;


		log.info("txnid="+txnNumner);

		StockUploadModel stockUpload= new StockUploadModel();
		try {
			byte[] bytes = file.getBytes();
			String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnNumner+"/";
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
		log.info(" submit register form block.");


		// set reaquest parameters into model class
		
		stockUpload.setSupplierId(supplierId);
		stockUpload.setSuplierName(supplierName);
		stockUpload.setInvoiceNumber(invoiceNumber); 
		stockUpload.setQuantity(quantity);
		stockUpload.setTxnId(txnNumner);
		stockUpload.setFileName(file.getOriginalFilename());
		stockUpload.setUserId(userId);
		stockUpload.setRoleType(roletype);
		
		
		
		log.info(file.getOriginalFilename());
		log.info("consignment object "+stockUpload.toString());
	
		
		GenricResponse response = feignCleintImplementation.uploadStock(stockUpload);
		//log.info("response from feign client=="+response.toString());
	
		log.info("setting response=="+response.toString());
		return response;


	}

	@RequestMapping(value= {"/stockDelete"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public @ResponseBody GenricResponse deleteStock(@RequestBody StockUploadModel stockUpload,HttpSession session) {

		log.info("enter in  delete stock.");
		String roletypesession=String.valueOf(session.getAttribute("usertype"));
		stockUpload.setRoleType(roletypesession);
		GenricResponse response=feignCleintImplementation.deleteStock(stockUpload);
		log.info("response after delete consignment."+response);
		return response;

	}
	
	// *********************************************** open register page or edit popup ******************************
	@RequestMapping(value="/openStockPopup",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
	public @ResponseBody StockUploadModel openRegisterConsignmentPopup(@RequestParam(name="reqType") String reqType,@RequestParam(name="txnId",required = false) String txnId,HttpSession session)
	{

	
		log.info(" stock pop  up controller");
		
		StockUploadModel stockUploadModel= new StockUploadModel();
		StockUploadModel stockUploadModelResponse;
		log.info("roletype value=="+txnId);
		String roletypesession=String.valueOf(session.getAttribute("usertype"));
		log.info("session value=="+roletypesession);
		stockUploadModel.setTxnId(txnId);
		stockUploadModel.setRoleType(roletypesession);
		
		
		if(reqType.equals("editPage")) {
			stockUploadModelResponse=feignCleintImplementation.fetchUploadedStockByTxnId(stockUploadModel);
			log.info("stockUpload details="+stockUploadModelResponse);
			log.info("open Update stockUpload popup");
			return stockUploadModelResponse;


		}
		else {
			stockUploadModelResponse=feignCleintImplementation.fetchUploadedStockByTxnId(stockUploadModel);
			log.info("stockUpload details="+stockUploadModelResponse);
			log.info("open view stockUpload pop up");
			return stockUploadModelResponse;

		}
		
	}
	
	
	//************************************************ Open stock record page********************************************************************************/

		@RequestMapping(value= {"/updateUploadedStock"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
		public @ResponseBody GenricResponse openconsignmentRecordPage(@RequestParam(name="supplierId",required = false) int supplierId,@RequestParam(name="supplierName",required = false) String supplierName
				,@RequestParam(name="invoiceNumber",required = false) String invoiceNumber,@RequestParam(name="quantity",required = false) int quantity,
				@RequestParam(name="file",required = false) MultipartFile file,HttpSession session,@RequestParam(name="txnId",required = false) String txnId,@RequestParam(name="filename",required = false) String filename) {
			log.info("entry point  in update Stock ** **.");
			StockUploadModel stockUpload= new StockUploadModel();
		/*
		 * String roletypesession=String.valueOf(session.getAttribute("usertype"));
		 * String userName=session.getAttribute("username").toString(); int userId=
		 * (int) session.getAttribute("userid"); String
		 * name=session.getAttribute("name").toString();
		 */
			
			GenricResponse response= new GenricResponse();
			
			

			
			log.info("file name without upload file="+filename);

			if(file==null)
			{
				log.info("message  in file upload block ");

				stockUpload.setSupplierId(supplierId);
				stockUpload.setSuplierName(supplierName);
				stockUpload.setQuantity(quantity);
				stockUpload.setTxnId(txnId);
				stockUpload.setFileName(filename);
				stockUpload.setUserId(265);
				stockUpload.setRoleType("Importer");
				log.info(stockUpload.toString());
			}
			else {



				log.info("file is empty or not "+file.isEmpty());
				try {
					String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnId+"/";


					File tmpDir = new File(rootPath+file.getOriginalFilename());
					boolean exists = tmpDir.exists();

					if(exists) {
							
						log.info("file is exist.");
						Path temp = Files.move 
								(Paths.get("/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+txnId+"/"+file.getOriginalFilename()),  
										Paths.get("/home/ubuntu/apache-tomcat-9.0.4/webapps/MovedFiles/"+file.getOriginalFilename())); 

						// tmpDir.renameTo(new File("/home/ubuntu/apache-tomcat-9.0.4/webapps/MovedFile/"+txnId+"/"));
						log.info("after move file");
						tmpDir.delete();
					}


					byte[] bytes = file.getBytes();

					File dir = new File(rootPath + File.separator);

					if (!dir.exists()) 
						dir.mkdirs();

					File serverFile = new File(rootPath+file.getOriginalFilename());
					log.info("COMPLETE PATH" + serverFile);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					log.error(e.getMessage(), e);
				}


				log.info("message after file upload block ");

				stockUpload.setSupplierId(supplierId);
				stockUpload.setSuplierName(supplierName);
				stockUpload.setQuantity(quantity);
				stockUpload.setTxnId(txnId);
				stockUpload.setFileName(filename);
				stockUpload.setUserId(265);
				stockUpload.setRoleType("Importer");

				log.info(stockUpload.toString());
				
			}

			response = feignCleintImplementation.updateStock(stockUpload);
			log.info(" update stock form block.");

			// calling service method
			//	String response=service.updateConsignmnet(consignment, file, txnid);


			log.info(" update consignment exit point.");
			return response;

		}



	
}
