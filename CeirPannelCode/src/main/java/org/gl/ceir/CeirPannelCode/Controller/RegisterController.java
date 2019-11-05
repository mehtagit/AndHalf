package org.gl.ceir.CeirPannelCode.Controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentFilterPojo;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.userTest;
import org.gl.ceir.CeirPannelCode.Service.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class RegisterController {

	@Autowired
	FeignCleintImplementation feignImpl;
	@Autowired
	LoginServices loginserviceIndexpage;

	/*
	 * @Autowired private UserValidator userValidator;
	 */

	/*
	 * @GetMapping({ "/", "/register" }) public ModelAndView register(HttpSession
	 * session) { ModelAndView mv = new ModelAndView();
	 * if(session.getAttribute("username")!=null) {
	 * mv.setViewName("redirect:/Dashboard");
	 * System.out.println("000000"+session.getAttribute("username")); } else {
	 * System.out.println("inside method"); mv.setViewName("StartingPage"); }
	 * 
	 * return mv; }
	 */  


	@GetMapping({ "/openUserRegisterPage" })
	public ModelAndView openUserRegisterPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("username")!=null)
		{
			mv.setViewName("redirect:/Dashboard");
			System.out.println("000000"+session.getAttribute("username"));
		}
		else
		{
		System.out.println("inside method");
		mv.setViewName("StartingPage");
		}
        return mv;
	}  


	
	
	@RequestMapping(value={"/Dashboard"},method={org.springframework.web.bind.annotation.RequestMethod. POST,org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView registerUser(@ModelAttribute("userForm") userTest userForm,HttpSession session) {
		System.out.println("inside login method");
		String username="sharad";
		//session.setAttribute("username", username);
		
		System.out.println("session value"+session.getAttribute("username"));
		ModelAndView mv = new ModelAndView();

		if(session.getAttribute("username")==null) {
		try { userTest userDetails = new userTest();
		System.out.println("username="+userForm.getUsername()+" usertype="+userForm.
				getUsertype()+" password="+userForm.getUsertype()); userDetails=
				loginserviceIndexpage.findByUsertypeAndUsernameAndPassword(userForm.
						getUsertype(), userForm.getUsername(), userForm.getUsertype());
				if(userDetails!=null ) 
				{
					if(userDetails.getUsername().equals(userForm.getUsertype()) &&
							userDetails.getPassword().equals(userForm.getUsertype()) ) {
System.out.println("validation sucessfulll");
						mv.setViewName("indexpage");
						session.setAttribute("username",username);
						mv.addObject("username", userDetails.getUsername()); 
						return mv;
						} 
					else {
						System.out.println("wrong username or password");
						mv.setViewName("UserRegistration");
						return mv ;
						}
					} 
				else {
						System.out.println("username or password is null");
						mv.setViewName("UserRegistration"); 
						return mv;
					} 
				} catch (Exception e) { // TODO: handle exception
								System.out.println("exception in login "+e); }
		
				return mv;
		}
				else {
					System.out.println("dashboard page witout login+++++++");
					
					mv.setViewName("indexpage");
			
					
				}
		return mv;

	}



	@RequestMapping(value= {"/importerConsignment/{id}"},method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
	public ModelAndView demo(@PathVariable("id") int id,HttpSession session) {
		System.out.println("inside dashboard method");
		ModelAndView mv = new ModelAndView(); 
		System.out.println("session value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+session.getAttribute("username"));	

		List<ConsignmentModel>  consignmentdetails=feignImpl.consignmentList(id);
		mv.addObject("consignmentdetails", consignmentdetails);
		System.out.println("consignemnt pojo details=**"  +consignmentdetails);
		mv.setViewName("demo"); 
		return mv;

	}

	//***********************************************  Add New Consignment  *****************************************************
	@RequestMapping(value="/addConsignment",method=RequestMethod.POST) 
	public ModelAndView addConsignment(@ModelAttribute ConsignmentModel consignmentFormData,@RequestParam("file") MultipartFile file,HttpServletRequest request) {

		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
		String paramName = (String)headerNames.nextElement();
		System.out.println("Headername==" + paramName );
		String paramValue = request.getHeader(paramName);
		System.out.println("Header Value==" +paramValue );
		}
		
		ModelAndView mv = new ModelAndView(); 
		System.out.println("inside addConsignment method");
		try {
	       
			System.out.println(" multipart filename="+file.getOriginalFilename()+" file size="+file.getSize()+" bytes details"+file.getBytes()+" name="+file.getName()+" Content type="+file.getContentType());
			mv.setViewName("demo"); 
			
			consignmentFormData.setImporterId(1);
			consignmentFormData.setImporterName("sharad yadav");
			System.out.println("addConsignment data="+consignmentFormData);
			
			byte[] bytes = file.getBytes();
			String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/";
			File dir = new File(rootPath + File.separator);
             
			if (!dir.exists()) 
				dir.mkdirs();
			// Create the file on server
			// Calendar now = Calendar.getInstance();

			File serverFile = new File(rootPath+file.getOriginalFilename());
			System.out.println("COMPLETE PATH" + serverFile);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			
			String filePath=rootPath+file.getOriginalFilename();
			
			/*
			 * feignImpl.addConsignment(consignmentFormData.
			 * getConsignmentNumber(),consignmentFormData.getExpectedArrivalPort(),
			 * consignmentFormData.getExpectedArrivalDate(),consignmentFormData.
			 * getExpectedDispatcheDate(),file.getOriginalFilename(),
			 * filePath,consignmentFormData.getImporterId(),consignmentFormData.
			 * getImporterName(),consignmentFormData.getOrganisationcountry(),
			 * consignmentFormData.getSupplierId(),consignmentFormData.getSupplierName(),
			 * consignmentFormData.getQuantity());
			 */
			mv.setViewName("redirect:/importerConsignment/1");  
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;

	}


	//***********************************************  update Consignment  *****************************************************
	@RequestMapping(value="/updateConsignmentDetail/{txnid}",method=RequestMethod.POST) 
	public ModelAndView updateConsignment(@ModelAttribute ConsignmentModel consignmentFormData,@RequestParam(name="file",required =false) MultipartFile file,HttpServletRequest request,@PathVariable("txnid") String txnid) {

		
		consignmentFormData.setImporterId(1);
		consignmentFormData.setImporterName("sharad yadav");
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
		String paramName = (String)headerNames.nextElement();
		System.out.println("Headername==" + paramName );
		String paramValue = request.getHeader(paramName);
		System.out.println("Header Value==" +paramValue );
		}
		String filePath="";
		ModelAndView mv = new ModelAndView(); 
		System.out.println("inside  update Consignment method");
		try {
	       System.out.println("transaction id="+txnid);
	       System.out.println("file name is *****************"+file.isEmpty());
	       
			System.out.println(" multipart filename="+file.getOriginalFilename()+" file size="+file.getSize()+" bytes details"+file.getBytes()+" name="+file.getName()+" Content type="+file.getContentType());
			mv.setViewName("demo"); 
			if(file.isEmpty()==false) {
				System.out.println("file is   not empty................");

			System.out.println("addConsignment data="+consignmentFormData);
			
			byte[] bytes = file.getBytes();
			String rootPath = "/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/";
			File dir = new File(rootPath + File.separator);

			if (!dir.exists()) 
				dir.mkdirs();
			// Create the file on server
			// Calendar now = Calendar.getInstance();

			File serverFile = new File(rootPath+file.getOriginalFilename());
			System.out.println("COMPLETE PATH" + serverFile);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			 filePath=rootPath+file.getOriginalFilename();
			}
			System.out.println("addConsignment data="+consignmentFormData);
			
			
			/*
			 * ConsignmentModel
			 * updateConsignment=feignImpl.updateConsignment(consignmentFormData.
			 * getConsignmentNumber(),consignmentFormData.getExpectedArrivalPort(),
			 * consignmentFormData.getExpectedArrivalDate(),consignmentFormData.
			 * getExpectedDispatcheDate(),file.getOriginalFilename(),
			 * filePath,consignmentFormData.getImporterId(),consignmentFormData.
			 * getImporterName(),consignmentFormData.getOrganisationcountry(),
			 * consignmentFormData.getSupplierId(),consignmentFormData.getSupplierName(),
			 * txnid,consignmentFormData.getQuantity());
			 */
			//System.out.println("response from update api======"+updateConsignment);
			mv.setViewName("redirect:/importerConsignment/1");  
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;

	}


	//************************************************ Open edit page********************************************************************************/
	
	@RequestMapping(value= {"/openEditPage/{txnid}"},method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
	public ModelAndView openEditconsignmentPage(@PathVariable("txnid") String  txnid) {
		System.out.println("inside edit page open method"+txnid);
		ModelAndView mv = new ModelAndView(); 
		

		ConsignmentModel  consignmentdetails=feignImpl.fetchConsignmentByTxnId(txnid);
		mv.addObject("consignmentdetails", consignmentdetails);
		System.out.println("consignemnt pojo details=**"  +consignmentdetails);
		mv.setViewName("editConsignment"); 
		return mv;

	}
	
	
	//************************************************ Open consignment record  page********************************************************************************/
	
	/*
	 * @RequestMapping(value=
	 * {"/openViewConsignmentRecord/{txnid}"},method={org.springframework.web.bind.
	 * annotation.RequestMethod.GET}) public ModelAndView
	 * openconsignmentRecordPage(@PathVariable("txnid") String txnid) {
	 * System.out.println("inside view consignment page open method"+txnid);
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * 
	 * ConsignmentPojo consignmentdetails=feignImpl.fetchConsignmentByTxnId(txnid);
	 * mv.addObject("consignmentdetails", consignmentdetails);
	 * System.out.println("consignemnt pojo details=**" +consignmentdetails);
	 * mv.setViewName("viewConsignmentRecord"); return mv;
	 * 
	 * }
	 */

	
	//feignImpl

	/*
	 * @GetMapping("ConsignmentData")
	 * 
	 * @ResponseBody public List<ConsignmentPojo> consignment(){
	 * 
	 * 
	 * return null;
	 * 
	 * }
	 */

	//************************************* filter Data**************************************************************************
	

	@RequestMapping(value= "/FillterConsignmentData",method={org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public ModelAndView FilterConsignmentData(@RequestParam(name = "startDate", required = false) String startDate ,@RequestParam(name="endDate",required =false) String endDate,
			@RequestParam(name="fileStatus",required = false) String fileStatus,@RequestParam(name="taxStatus",required = false) String taxStatus,
			@RequestParam("consignmentStatus") String consignmentStatus) {
		System.out.println("inside filter method");
		System.out.println("startDate= ("+startDate+") end date="+endDate+" file status="+fileStatus+" tax status="+taxStatus+"  consignmentStatus=="+consignmentStatus);
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("demo"); 

		
		ConsignmentFilterPojo filterdata= new ConsignmentFilterPojo();
		filterdata.setEndDate(endDate);
		filterdata.setFileStatus(fileStatus);
		filterdata.setStartDate(startDate);
		filterdata.setTaxPaidStatus(taxStatus);
		filterdata.setConsignmentStatus(consignmentStatus);
		filterdata.setImporterId(1);
		
	//	List<ConsignmentModel>  consignmentdetails=feignImpl.filterConsignmentdata(filterdata);
		//mv.addObject("consignmentdetails", consignmentdetails);
		mv.addObject("startDate", startDate);
		mv.addObject("endDate", endDate);
		mv.addObject("fileStatus", fileStatus);
		mv.addObject("taxStatus", taxStatus);
		mv.addObject("consignmentStatus", consignmentStatus);
	//	System.out.println("consignemnt pojo details=********"  +consignmentdetails);
		return mv;

	}

	
	
	// *************************************************** Delete Consognment ******************************************************************
	@RequestMapping(value="/deleteConsignment",method={org.springframework.web.bind.annotation.RequestMethod.POST}) 
	public @ResponseBody ModelAndView deleteconsignment(@RequestParam("txnId") String id) {
		System.out.println("inside delete method");
		System.out.println("transacation id="+id);
	///ConsignmentModel response=	feignImpl.deleteConsignment(id);
		// System.out.println("delete response="+response);
		ModelAndView mv = new ModelAndView(); 
		
		mv.setViewName("redirect:/importerConsignment/1"); 
		return mv;

	}
	
	//**************************************************  download file  **************************************************************** 
	@RequestMapping(value="/dowloadFiles/{filetype}/{fileName}/{transactionNumber}",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
	public  String downloadFile(@PathVariable("transactionNumber") String txnid,@PathVariable("fileName") String fileName,@PathVariable("filetype") String filetype) throws IOException {
		System.out.println("inside file download method");
		System.out.println("transacation id="+txnid+" fileName="+fileName+" fileType="+filetype);
		String response=feignImpl.downloadFile(txnid,filetype,fileName);
		System.out.println("download response="+response);
	
		return "redirect:"+response;

	}
	
	
	//************************************************ Download Sampmle file **************************************************
	@RequestMapping(value="/sampleFileDownload/{filetype}",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
	public  String downloadSampleFile(@PathVariable("filetype") String filetype) throws IOException {
		System.out.println("inside file download method");
		System.out.println(" fileType="+filetype);
		String response=feignImpl.downloadSampleFile(filetype);
		System.out.println("download response="+response);
	
		return "redirect:"+response;

	}

	//*************************************  upload stock *******************************************************************************************
	@RequestMapping(value={"/assignDistributor"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView assignDistributor() {

		System.out.println("inside distributor method");
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("assignDistributor");
		return mv;
     }

	//********************************************* Stolen Recovery **************************************************
	@RequestMapping(value={"/stolenRecovery"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView stolenRecovery() {

		System.out.println("inside stolen  Recovery method");
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("stolenRecovery");
		return mv;

	}
	
	
//***************************************** close update consignment page
	@RequestMapping(value={"/closeEditPage"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView closeEditPage() {

		System.out.println("inside close edit page  method");
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("redirect:/importerConsignment/1"); 
		return mv;

	}

	//**************************************** open register consignment page ********************************************
	@RequestMapping(value={"/openReisterConsignmentPage"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView openRegisterConsignmentPage() {

		System.out.println("inside open register consignment page  method");
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("registerConsignment"); 
		return mv;
       }
	
	
	

	@RequestMapping(value={"/Home"},method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public ModelAndView Home() { System.out.println("inside home method");
	ModelAndView mv = new ModelAndView(); mv.setViewName("Home"); return mv;



	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("username");
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/login");
		return mv;
	}

}
