package org.gl.ceir.CeirPannelCode.Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.gl.ceir.CeirPannelCode.Feignclient.DBTablesFeignClient;
import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Feignclient.GrievanceFeignClient;
import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.AddMoreFileModel;
import org.gl.ceir.CeirPannelCode.Model.DBrowDataModel;
import org.gl.ceir.CeirPannelCode.Model.DbListDataHeaders;
import org.gl.ceir.CeirPannelCode.Model.FileExportResponse;
import org.gl.ceir.CeirPannelCode.Model.MapDatatableResponse;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@RequestMapping(value="/Consignment")
@Controller
public class FileAndHistoryController {

	
	
	@Autowired
	GrievanceFeignClient grievanceFeignClient;
	
	
@Autowired
AddMoreFileModel addMoreFileModel,urlToUpload,urlToMove;


@Autowired
UserProfileFeignImpl userProfileFeignImpl;
	
@Autowired

FeignCleintImplementation feignCleintImplementation;
@Autowired
UtilDownload utildownload;
@Autowired
DBTablesFeignClient dBTablesFeignClient;
@Autowired
DBrowDataModel dBrowDataModel;
private final Logger log = LoggerFactory.getLogger(getClass());

	//************************************************* download file *************************************************************** 

	@RequestMapping(value="/dowloadFiles/{filetype}/{fileName}/{transactionNumber}/{doc_TypeTag}",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
	//@RequestMapping(value="/dowloadFiles/{filetype}/{fileName}/{transactionNumber}",method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers = {"content-Disposition=attachment"}) 

	public @ResponseBody FileExportResponse downloadFile(@PathVariable("transactionNumber") String txnid,@PathVariable("fileName") String fileName,@PathVariable("filetype") String filetype,@PathVariable(name="doc_TypeTag",required = false) String doc_TypeTag) throws IOException {

		FileExportResponse response = new FileExportResponse();	
	log.info("inside file download method"+doc_TypeTag);

	addMoreFileModel.setTag("system_upload_filepath");

	
	urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);
	log.info("url to download file=="+urlToUpload.getValue());

	if (filetype.equalsIgnoreCase("actual"))
	{

	if (!doc_TypeTag.equals("DEFAULT"))
	{
		log.info("doc_TypeTag_______"+doc_TypeTag);
		String rootPath = urlToUpload.getValue()+txnid+"/"+doc_TypeTag+"/";
		File tmpDir = new File(rootPath+fileName);
		boolean exists = tmpDir.exists();
		if(exists) {

	String extension = fileName.substring(fileName.lastIndexOf("."));
	log.info("fileExtension==="+extension);

					if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".gif") || extension.equalsIgnoreCase(".jpg"))		
					{
						response=feignCleintImplementation.downloadFile(txnid,filetype,fileName.replace("%20", " "),doc_TypeTag);
						response.setFilePath("imageType");
						return response;
					}
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
		addMoreFileModel.setTag("system_error_filepath");
		
		urlToUpload=feignCleintImplementation.addMoreBuutonCount(addMoreFileModel);
		log.info("url to download error  file path =="+urlToUpload.getValue());
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



	// consignment History 

	@PostMapping("consignment-history")
	public ResponseEntity<?> viewHistory(HttpServletRequest request) {
		List<List<Object>> finalList = new ArrayList<List<Object>>();
		List<List<String>> mul = new ArrayList<List<String>>();
		String filter = request.getParameter("filter");
		MapDatatableResponse map = new MapDatatableResponse();
		Gson gsonObject = new Gson();
		DBrowDataModel filterRequest = gsonObject.fromJson(filter, DBrowDataModel.class);
		try {
			log.info("request passed to API:::::::::" + filter);
			Object response = dBTablesFeignClient.historyConsignmentFeign(filterRequest);
			Gson gson = new Gson();
			String apiResponse = gson.toJson(response);
			log.info("apiResponse ::::::::::::::" + apiResponse);
			DBrowDataModel dBrowDataModel = gson.fromJson(apiResponse, DBrowDataModel.class);
			log.info("response::::::" + dBrowDataModel);

			List<String> columnList = dBrowDataModel.getColumns();
			List<Map<String, String>> rowData = dBrowDataModel.getRowData();
			List<DbListDataHeaders> headers = new ArrayList<>();

			if (columnList.isEmpty()) {
				dBrowDataModel.setColumns(Collections.emptyList());
			} else {
				List<String> list = dBrowDataModel.getColumns();
				ListIterator<String> iterator = list.listIterator();
				String columnName = null;
				while (iterator.hasNext()) {
					columnName = iterator.next();
					headers.add(new DbListDataHeaders(columnName, columnName));
				}

				map.setColumns(headers);
				map.setData(rowData);

			}
			return new ResponseEntity<>(map, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			dBrowDataModel.setColumns(Collections.emptyList());
			return new ResponseEntity<>(HttpStatus.OK);

		}
	}



@RequestMapping(value="/ManualFileDownload",method={org.springframework.web.bind.annotation.RequestMethod.GET}) 
public String ManualSampleFile(@RequestParam(name="userTypeId",required = false) int userTypeId) throws IOException {
log.info("request send to the manual sample file download api=");
log.info("userTypeId==="+userTypeId);

FileExportResponse response=feignCleintImplementation.manualDownloadSampleFile(userTypeId);
log.info("response from manual sample file download file "+response);

return "redirect:"+response.getUrl();

}
}
