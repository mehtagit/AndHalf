package org.gl.ceir.CeirPannelCode.Controller;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.AddMoreFileModel;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentUpdateRequest;
import org.gl.ceir.CeirPannelCode.Model.Dropdown;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.CeirPannelCode.Service.ConsignmentService;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@RequestMapping(value="/Consignment")
@Controller

public class Consignment {

	@Value ("${filePathforUploadFile}")
	String filePathforUploadFile;

	@Value ("${filePathforMoveFile}")
	String filePathforMoveFile;
	

	@Value ("${filePathforErrorFile}")
	String filePathforErrorFile;
	
@Autowired
AddMoreFileModel addMoreFileModel,urlToUpload;



	
@Autowired

FeignCleintImplementation feignCleintImplementation;
@Autowired
UtilDownload utildownload;
/*
* @Autowired ConsignmentService consignmentService;
*/

private final Logger log = LoggerFactory.getLogger(getClass());


@RequestMapping(value=
{"/viewConsignment"},method={org.springframework.web.bind.annotation.
RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST})
public ModelAndView viewConsignment(HttpSession session,@RequestParam(name="txnID",required = false) String txnID) {
ModelAndView mv = new ModelAndView();



log.info(" view consignment entry point................."); 
mv.setViewName("viewConsignment");
log.info(" view consignment exit point."); 
return mv; 
}



/*
* @RequestMapping(value=
* {"/filterConsignments"},method={org.springframework.web.bind.annotation.
* RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
* ) public @ResponseBody List<ConsignmentModel>filterConsignment(@RequestBody
* FilterRequest filterRequest) { filterRequest.setUserId("1");
* log.info("view consignment filter entry point.************"+filterRequest);
* List<ConsignmentModel>
* consignmentdetails=feignCleintImplementation.consignmentFilter(filterRequest)
* ; log.info("fillter Data=="+consignmentdetails);
* log.info(" view consignment exit point."); return consignmentdetails; }
* 
*/



/*
* @RequestMapping(value=
* {"/filterConsignment"},method={org.springframework.web.bind.annotation.
* RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
* ) public @ResponseBody List<ConsignmentModel>filterConsignment(@RequestBody
* FilterRequest filterRequest, HttpSession session) {
* log.info("coming in controller+++++");
* 
* 
* String userid= session.getAttribute("userid").toString();
* log.info("user id from session="+userid);
* filterRequest.setUserId(Integer.parseInt(userid));
* 
* log.info("filterRequest=="+filterRequest);
* 
* session.setAttribute("startDate", filterRequest.getStartDate());
* session.setAttribute("endDate",filterRequest.getEndDate());
* session.setAttribute("consignmentStatus",
* filterRequest.getConsignmentStatus()); session.setAttribute("taxPaidStatus",
* filterRequest.getTaxPaidStatus());
* 
* log.info("session value=="+session.getAttribute("consignmentStatus"));
* 
* if(session.getAttribute("startDate")!=null
* ||session.getAttribute("endDate")!=null||
* session.getAttribute("consignmentStatus")!=null ||
* session.getAttribute("taxPaidStatus")!=null ) {
* 
* log.info("session is available atleast in one parameters");
* 
* filterRequest.setConsignmentStatus((int)session.getAttribute(
* "consignmentStatus")); filterRequest.setEndDate((String)
* session.getAttribute("startdate")); filterRequest.setStartDate((String)
* session.getAttribute("endDate")); filterRequest.setTaxPaidStatus((String)
* session.getAttribute("taxPaidStatus")); }
* 
* else { log.info("session is not present consignment value ="+filterRequest);
* 
* }
* 
* 
* 
* 
* log.info("view consignment filter entry point."+filterRequest);
* List<ConsignmentModel>
* consignmentdetails=feignCleintImplementation.consignmentFilter(filterRequest)
* ; log.info("fillter Data=="+consignmentdetails);
* log.info(" view consignment exit point."); return consignmentdetails; }
*/


/*
* @RequestMapping(value={"/viewConsignment"},method={org.springframework.web.
* bind.annotation.
* RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}
* ) public ModelAndView viewConsignment() { ModelAndView modelAndView=new
* ModelAndView("viewConsignment"); return modelAndView; }
* 
* @GetMapping
* 
* @RequestMapping("/viewConsignmentList" ) public ResponseEntity<?>
* viewConsignmentList() { long id=1; List<ConsignmentModel>
* consignmentdetails=feignCleintImplementation.consignmentList(id); return new
* ResponseEntity<>(consignmentdetails, HttpStatus.OK); }
*/

@RequestMapping(value= {"/registerConsignment"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
public @ResponseBody GenricResponse registerConsignment(@RequestParam(name="supplierId",required = false) String supplierId,@RequestParam(name="supplierName",required = false) String supplierName
,@RequestParam(name="consignmentNumber",required = false) String consignmentNumber,@RequestParam(name="expectedArrivaldate",required = false) String expectedArrivalDate,
@RequestParam(name="organisationcountry",required = false) String organisationcountry,@RequestParam(name="expectedDispatcheDate",required = false) String expectedDispatcheDate,
@RequestParam(name="expectedArrivalPort",required = false) Integer expectedArrivalPort,@RequestParam(name="quantity",required = false) String quantity,
@RequestParam(name="file",required = false) MultipartFile file,HttpSession session,@RequestParam(name="totalPrice",required = false) String totalPrice,@RequestParam(name="currency",required = false) Integer currency,HttpServletRequest request) {

	log.info("headers request="+request.getHeaderNames());
	log.info("user-agent"+request.getHeader("user-agent"));
	  Enumeration headerNames = request.getHeaderNames();
      while (headerNames.hasMoreElements()) {
          String key = (String) headerNames.nextElement();
          String value = request.getHeader(key);
          log.info("request headers value="+key+" : "+value);
      }

String userName=session.getAttribute("username").toString();
String userId= session.getAttribute("userid").toString();
String name=session.getAttribute("name").toString();
log.info(" Register consignment entry point.");
String txnNumner=utildownload.getTxnId();
txnNumner = "C"+txnNumner;

addMoreFileModel.setTag("system_upload_filepath");
urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);

log.info("url to upload file=="+urlToUpload.getValue());
log.info("Random transaction id number="+txnNumner);
ConsignmentModel consignment = new ConsignmentModel();
try {
byte[] bytes = file.getBytes();
String rootPath = urlToUpload.getValue()+txnNumner+"/";
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
// set request parameters into model class
consignment.setSupplierId(supplierId);
consignment.setSupplierName(supplierName);
consignment.setConsignmentNumber(consignmentNumber);
consignment.setExpectedDispatcheDate(expectedDispatcheDate);
consignment.setExpectedArrivaldate(expectedArrivalDate);
consignment.setOrganisationCountry(organisationcountry);
consignment.setExpectedArrivalPort(expectedArrivalPort);
consignment.setQuantity(quantity);
consignment.setTxnId(txnNumner);
consignment.setFileName(file.getOriginalFilename());
consignment.setUserId(Long.valueOf(userId));

consignment.setCurrency(currency);
consignment.setTotalPrice(totalPrice);
log.info("consignment form parameters passed to register consignment api "+consignment.toString());
GenricResponse response = feignCleintImplementation.addConsignment(consignment);
log.info("response from register consignment api"+response.toString());
return response;


}



//************************************************ update consignment record page********************************************************************************/

@RequestMapping(value= {"/updateRegisterConsignment"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
public @ResponseBody GenricResponse openconsignmentRecordPage(@RequestParam(name="supplierId",required = false) String supplierId,@RequestParam(name="supplierName",required = false) String supplierName
,@RequestParam(name="consignmentNumber",required = false) String consignmentNumber,@RequestParam(name="expectedArrivaldate",required = false) String expectedArrivalDate,
@RequestParam(name="organisationcountry",required = false) String organisationcountry,@RequestParam(name="expectedDispatcheDate",required = false) String expectedDispatcheDate,
@RequestParam(name="expectedArrivalPort",required = false) Integer expectedArrivalPort,@RequestParam(name="quantity",required = false) String quantity, HttpSession session,
@RequestParam(name="file",required = false) MultipartFile file,@RequestParam(name="filename",required = false) String filename,@RequestParam(name="txnId",required = false) String txnId,
@RequestParam(name="totalPrice",required = false) String totalPrice,@RequestParam(name="currency",required = false) Integer currency) 
{
ConsignmentModel consignment = new ConsignmentModel();

String userName=session.getAttribute("username").toString();
String userId= session.getAttribute("userid").toString();
String name=session.getAttribute("name").toString();

GenricResponse response= new GenricResponse();

addMoreFileModel.setTag("system_upload_filepath");
urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);

log.info("entry point in update Consignment.");
if(file==null)
{
consignment.setSupplierId(supplierId);
consignment.setSupplierName(supplierName);
consignment.setConsignmentNumber(consignmentNumber);
consignment.setExpectedDispatcheDate(expectedDispatcheDate);
consignment.setExpectedArrivaldate(expectedArrivalDate);
consignment.setOrganisationCountry(organisationcountry);
consignment.setExpectedArrivalPort(expectedArrivalPort);
consignment.setQuantity(quantity);
consignment.setTxnId(txnId);
consignment.setFileName(filename);
consignment.setUserId(Long.valueOf(userId));
consignment.setCurrency(currency);
consignment.setTotalPrice(totalPrice);


}
else {
log.info("file is empty or not "+file.isEmpty());
try {
String rootPath = urlToUpload.getValue()+txnId+"/";
File tmpDir = new File(rootPath+file.getOriginalFilename());
boolean exists = tmpDir.exists();
if(exists) {

Path temp = Files.move 
(Paths.get(urlToUpload.getValue()+"/"+txnId+"/"+file.getOriginalFilename()), 
Paths.get(filePathforMoveFile+file.getOriginalFilename())); 
String movedPath=filePathforMoveFile+file.getOriginalFilename();	

log.info("file is already exist, moved to this "+movedPath+" path. ");
tmpDir.delete();
}
byte[] bytes = file.getBytes();
File dir = new File(rootPath + File.separator);
if (!dir.exists()) 
dir.mkdirs();
File serverFile = new File(rootPath+file.getOriginalFilename());
log.info("uploaded file path on server" + serverFile);
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

consignment.setSupplierId(supplierId);
consignment.setSupplierName(supplierName);
consignment.setConsignmentNumber(consignmentNumber);
consignment.setExpectedDispatcheDate(expectedDispatcheDate);
consignment.setExpectedArrivaldate(expectedArrivalDate);
consignment.setOrganisationCountry(organisationcountry);
consignment.setExpectedArrivalPort(expectedArrivalPort);
consignment.setQuantity(quantity);
consignment.setTxnId(txnId);
consignment.setFileName(filename);
consignment.setUserId(Long.valueOf(userId));
consignment.setCurrency(currency);

consignment.setTotalPrice(totalPrice);
}

log.info("Request passed to the update register consignment="+consignment.toString());
response = feignCleintImplementation.updateConsignment(consignment);
log.info(" response from update Consignment api="+response);
return response;

}





//************************************************ delete consignment record page********************************************************************************/

@RequestMapping(value= {"/deleteConsignment"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
public @ResponseBody GenricResponse deleteConsignment(@RequestBody ConsignmentModel consignmentModel,HttpSession session) {

log.info("enter in delete consignment.");
String userType=(String) session.getAttribute("usertype");
log.info("request passed to the deleteConsignment Api="+consignmentModel);
GenricResponse response=feignCleintImplementation.deleteConsignment(consignmentModel,userType);
log.info("response after delete consignment."+response);
return response;

}



//*********************************************** update consignment Status *******************************************************************************/

@RequestMapping(value= {"/updateConsignmentStatus"},method={org.springframework.web.bind.annotation.RequestMethod.GET,org.springframework.web.bind.annotation.RequestMethod.POST}) 
public @ResponseBody GenricResponse updateConsignmentStatus(@RequestBody ConsignmentUpdateRequest consignmentUpdateRequest,HttpSession session) {
ConsignmentUpdateRequest request= new ConsignmentUpdateRequest ();
log.info("enter in update consignment status ."+consignmentUpdateRequest);


request.setAction(consignmentUpdateRequest.getAction());
request.setTxnId(consignmentUpdateRequest.getTxnId());
request.setRoleType((String) session.getAttribute("usertype"));
request.setRoleTypeUserId((int) session.getAttribute("usertypeId"));
request.setUserId((int) session.getAttribute("userid"));
request.setRemarks(consignmentUpdateRequest.getRemarks());
request.setTxnId(consignmentUpdateRequest.getTxnId());
request.setFeatureId(consignmentUpdateRequest.getFeatureId());
log.info(" request passed to the update consignment status="+request);
GenricResponse response=feignCleintImplementation.updateConsignmentStatus(request);
log.info("response after update consignment status="+response);
return response;

}




// ********************************************** open register page or edit page *****************************
@RequestMapping(value="/openRegisterConsignmentForm",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
public ModelAndView openRegisterConsignmentForm(@RequestParam(name="reqType") String reqType,@RequestParam(name="txnId",required = false) String txnId)
{
ModelAndView mv= new ModelAndView();
if(reqType.equals("formPage"))
{
log.info("open registration Consignment form");
mv.setViewName("registerConsignment");
}
else if(reqType.equals("editPage")) {
ConsignmentModel consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
log.info("consignment details="+consignmentdetails);

log.info("open Update registration Consignment form");
mv.setViewName("editConsignment");
mv.addObject("consignmentdetails", consignmentdetails);
}
else {
ConsignmentModel consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
log.info("consignment details="+consignmentdetails);
log.info("open view registration Consignment form");
mv.setViewName("viewConsignmentRecord");
mv.addObject("consignmentdetails", consignmentdetails);

}
return mv;

}

// ********************************************** open register page or edit popup *****************************
@RequestMapping(value="/openRegisterConsignmentPopup",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
public @ResponseBody ConsignmentModel openRegisterConsignmentPopup(@RequestParam(name="reqType") String reqType,@RequestParam(name="txnId",required = false) String txnId)
{
ConsignmentModel consignmentdetails= new ConsignmentModel();
if(reqType.equals("editPage")) {
log.info("transaction id passed to the fetch consignment api="+txnId);
consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
log.info(" response from fetch consignment api ="+consignmentdetails);


}
else {
log.info("transaction id passed to the fetch consignment api="+txnId);
consignmentdetails=feignCleintImplementation.fetchConsignmentByTxnId(txnId);
log.info(" response from fetch consignment api ="+consignmentdetails);
}
return consignmentdetails;

}


//************************************************* download file *************************************************************** 

@RequestMapping(value="/dowloadFiles/{filetype}/{fileName}/{transactionNumber}/{doc_TypeTag}",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
//@RequestMapping(value="/dowloadFiles/{filetype}/{fileName}/{transactionNumber}",method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers = {"content-Disposition=attachment"}) 

public @ResponseBody FileExportResponse downloadFile(@PathVariable("transactionNumber") String txnid,@PathVariable("fileName") String fileName,@PathVariable("filetype") String filetype,@PathVariable(name="doc_TypeTag",required = false) String doc_TypeTag) throws IOException {

	FileExportResponse response = new FileExportResponse();	
log.info("inside file download method"+doc_TypeTag);

//AddMoreFileModel urlToUpload= new AddMoreFileModel();
addMoreFileModel.setTag("system_upload_filepath");
urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);

if (filetype.equalsIgnoreCase("actual"))
{

if (!doc_TypeTag.equals("DEFAULT"))
{
	log.info("doc_TypeTag_______"+doc_TypeTag);
	String rootPath = urlToUpload.getValue()+txnid+"/"+doc_TypeTag+"/";
	File tmpDir = new File(rootPath+fileName);
	boolean exists = tmpDir.exists();
	if(exists) {

		log.info("file against document   is exist.");
	}
	else {
		log.info(" file against documrnt type   is not exist.");
		response.setUrl("Not Found");
		return response;
	}

}
else if(doc_TypeTag.equalsIgnoreCase("DEFAULT")) {
	log.info("doc_TypeTag==="+doc_TypeTag);
	String rootPath = urlToUpload.getValue()+txnid+"/";
	File tmpDir = new File(rootPath+fileName);
	boolean exists = tmpDir.exists();
	if(exists) {

		log.info("actual file is exist.");
	}
	else {
		log.info(" actual file is not exist.");
		response.setUrl("Not Found");
		return response;
	}

}
}
else if(filetype.equalsIgnoreCase("error"))
{
	String rootPath = urlToUpload.getValue()+txnid+"/"+txnid+"_error.csv";
	File tmpDir = new File(rootPath);
	boolean exists = tmpDir.exists();
	if(exists) {
       log.info(" error file is exist.");
	}
	else {
		log.info(" error file is not exist.");
		response.setUrl("Not Found");
		return response;
	}

}


log.info(" everything is fine for hit to api for file downloading");
log.info("request send to the download file api= txnid("+txnid+") fileName ("+fileName+") fileType ("+filetype+")"+doc_TypeTag);
 response=feignCleintImplementation.downloadFile(txnid,filetype,fileName.replace("%20", " "),doc_TypeTag);
log.info("response of download api="+response+"------------------"+fileName.replace("%20", " "));
log.info("redirect:"+response.getUrl());
//ModelAndView mv= new ModelAndView(("redirect:"+ URLEncoder.encode(response.getUrl(), "UTF-8")));




		/*
		 * File file = new File(response.getUrl()); if(file.exists()){
		 * log.info("file is exist "); return response.getUrl(); } else {
		 * log.info("file is Not exist "); return null; }
		 */
return response;
}


//*********************************************** Download Sampmle file *************************************************
@RequestMapping(value="/sampleFileDownload/{featureId}",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
public String downloadSampleFile(@PathVariable("featureId") String  featureId) throws IOException {
log.info("request send to the sample file download api="+featureId);
int featureIdForFile=Integer.parseInt(featureId);

FileExportResponse response=feignCleintImplementation.downloadSampleFile(featureIdForFile);
log.info("response from sample file download file "+response);

return "redirect:"+response.getUrl();

}

//***********************************************cuurency controller *************************************************
@RequestMapping(value="/consignmentCurency",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
public @ResponseBody List<Dropdown> cuurencyforRegisterConsignment(@RequestParam("CURRENCY") String currency)  {
log.info("request send to the currency  api="+currency);
List<Dropdown> response= new ArrayList<Dropdown>();
response=feignCleintImplementation.taxPaidStatusList(currency);
log.info("response from currency api "+response);
return response;

}

//***************************************** Export Grievance controller *********************************
@RequestMapping(value="/exportConsignmnet",method ={org.springframework.web.bind.annotation.RequestMethod.GET})
public String exportToExcel(@RequestParam(name="consignmentStartDate",required = false) String consignmentStartDate,@RequestParam(name="consignmentEndDate",required = false) String consignmentEndDate,
		@RequestParam(name="consignmentTxnId",required = false) String consignmentTxnId,@RequestParam(name="consignmentTaxPaidStatus") Integer consignmentTaxPaidStatus,HttpServletRequest request,
		HttpSession session,@RequestParam(name="pageSize") Integer pageSize,@RequestParam(name="pageNo") Integer pageNo,@RequestParam(name="filterConsignmentStatus") Integer filterConsignmentStatus)
{
	log.info("consignmentStartDate=="+consignmentStartDate+ " consignmentEndDate ="+consignmentEndDate+" consignmentTxnId="+consignmentTxnId+"consignmentTaxPaidStatus="+consignmentTaxPaidStatus+" filterConsignmentStatus="+filterConsignmentStatus);
	int userId= (int) session.getAttribute("userid"); 
	int file=1;
	String userType=(String) session.getAttribute("usertype");
	Integer usertypeId=(int) session.getAttribute("usertypeId");
	FileExportResponse fileExportResponse;
	FilterRequest filterRequest= new FilterRequest();
	filterRequest.setStartDate(consignmentStartDate);
	filterRequest.setEndDate(consignmentEndDate);
	filterRequest.setTxnId(consignmentTxnId);
	filterRequest.setTaxPaidStatus(consignmentTaxPaidStatus);
	filterRequest.setConsignmentStatus(filterConsignmentStatus);
	filterRequest.setUserId(userId);
	filterRequest.setUserType(userType);
	filterRequest.setUserTypeId(usertypeId);
	filterRequest.setFeatureId(3);
	log.info(" request passed to the exportTo Excel Api =="+filterRequest+" *********** pageSize"+pageSize+"  pageNo  "+pageNo);
	Object	response= feignCleintImplementation.consignmentFilter(filterRequest, pageNo, pageSize, file);

   Gson gson= new Gson(); 
   String apiResponse = gson.toJson(response);
   fileExportResponse = gson.fromJson(apiResponse, FileExportResponse.class);
   log.info("response  from   export consignment  api="+fileExportResponse);
	
	return "redirect:"+fileExportResponse.getUrl();
}



@RequestMapping(value="/ManualFileDownload",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
public String ManualSampleFile(@RequestParam(name="userTypeId",required = false) int userTypeId) throws IOException {
log.info("request send to the manual sample file download api=");


FileExportResponse response=feignCleintImplementation.manualDownloadSampleFile(userTypeId);
log.info("response from manual sample file download file "+response);

return "redirect:"+response.getUrl();

}
}