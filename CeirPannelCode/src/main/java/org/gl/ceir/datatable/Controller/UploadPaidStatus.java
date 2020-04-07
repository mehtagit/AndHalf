package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.UploadPaidStatusFeignClient;
import org.gl.ceir.CeirPannelCode.Model.ConsignmentModel;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest_UserPaidStatus;
import org.gl.ceir.CeirPannelCode.Model.GenricResponse;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.configuration.ConfigParameters;
import org.gl.ceir.configuration.Translator;
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.gl.ceir.pagination.model.UserPaidStatusContent;
import org.gl.ceir.pagination.model.UserPaidStatusPaginationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@CrossOrigin
@RestController
public class UploadPaidStatus {
	@Autowired
	Translator Translator;
	@Autowired
	UploadPaidStatusFeignClient uploadPaidStatusFeignClient;

	@Autowired
	DatatableResponseModel datatableResponseModel;

	@Autowired
	IconsState iconState;

	
	@Autowired
	PageElement pageElement;

	@Autowired
	Button button;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/paid-status/{nid}")
	public ResponseEntity<?> respone(@PathVariable("nid") String nid) {
		GenricResponse response = uploadPaidStatusFeignClient.respone(nid);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("/user-paid-status-data")
	public ResponseEntity<?> view(@RequestParam(name="type",defaultValue = "userPaidStatus",required = false) String role,
			@RequestParam(name="sourceType",required = false) String sourceType,
			@RequestParam(name = "file", defaultValue = "0", required = false) Integer file,
			HttpServletRequest request,HttpSession session,
			@RequestParam(name="sessionFlag",
			required = false) Integer sessionFlag) {	
		// TODO Auto-generated method stub
		List<List<Object>> finalList = new ArrayList<List<Object>>();
		String filter = request.getParameter("filter");
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize;
		String userType = (String) session.getAttribute("usertype");
		Integer userId = (Integer) session.getAttribute("userid");
		Integer userTypeId = (Integer) session.getAttribute("usertypeId");
		log.info("userId==="+userId);
		String userStatus = (String) session.getAttribute("userStatus");
		log.info("userType in uploadPaidStatus" +userType);
		Object response = null;
		Gson gsonObject=new Gson();
		Gson gson=new Gson();
		FilterRequest_UserPaidStatus filterrequest = gsonObject.fromJson(filter, FilterRequest_UserPaidStatus.class);
		filterrequest.setUserId(userId);
		filterrequest.setUserType(userType);
		filterrequest.setUserTypeId(userTypeId);
		filterrequest.setSearchString(request.getParameter("search[value]"));
		log.info("filterrequest--->"+filterrequest);
		response = uploadPaidStatusFeignClient.view(filterrequest, pageNo, pageSize, file);
		log.info("request passed to the filter api  ="+filterrequest);
		String apiResponse = gson.toJson(response);
		log.info("response filter api  ="+apiResponse);
		//	filterrequest.setSearchString(request.getParameter("search[value]"));
		try {
			UserPaidStatusPaginationModel upsPaginationModel = gson.fromJson(apiResponse, UserPaidStatusPaginationModel.class);
			List<UserPaidStatusContent> contentList = upsPaginationModel.getContent();
			if(contentList.isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
			}
			else if("Custom".equals(userType) || "DRT".equals(userType)) {
				log.info("in Custom Userpaid Status---" +userType);
				
				for(UserPaidStatusContent contentModelList : contentList) {
					String nid = contentModelList.getNid();
					String txnId = contentModelList.getTxnId();
					//Integer sno = contentModelList.getId();
					String createdOn = contentModelList.getCreatedOn();
					String deviceIDInterp = contentModelList.getDeviceIdTypeInterp();
					//String deviceTypeInterp = contentModelList.getDeviceTypeInterp();
					String currency = contentModelList.getCurrencyInterp() == null ? "" : contentModelList.getCurrencyInterp();
					String price = currency.concat(String.valueOf(contentModelList.getPrice()));
					String country = contentModelList.getCountry();
					String status = contentModelList.getTaxPaidStatusInterp();
					String origin = contentModelList.getOrigin();
					//params for action 
					String imei1 = contentModelList.getFirstImei();
					String action = iconState.userPaidStatusIcon(imei1);

					
					Object[] data = {createdOn,nid,txnId,country,status,origin,action};

					List<Object> datatableList = Arrays.asList(data);
					finalList.add(datatableList);
					datatableResponseModel.setData(finalList);
				}
			}
			else if("CEIRAdmin".equals(userType)) {
				for(UserPaidStatusContent contentModelList : contentList) {
					//Integer sno = contentModelList.getId();
					String createdOn = contentModelList.getCreatedOn();
					String nid = contentModelList.getNid();
					String txnId = contentModelList.getTxnId(); 
					String deviceIDInterp = contentModelList.getDeviceIdTypeInterp();
					//String deviceTypeInterp = contentModelList.getDeviceTypeInterp();
					String currency = contentModelList.getCurrencyInterp() == null ? "" : contentModelList.getCurrencyInterp();
					String price = currency.concat(String.valueOf(contentModelList.getPrice()));
					String country = contentModelList.getCountry();
					String taxStatus = contentModelList.getTaxPaidStatusInterp();
					String status = contentModelList.getStateInterp();
					String origin = contentModelList.getOrigin();
					//params for action 
					String imei1 = contentModelList.getFirstImei();
					String deviceState = String.valueOf(contentModelList.getStatus());
					String action = iconState.adminUserPaidStatusIcon(imei1,createdOn,contentModelList.getTxnId(),deviceState,userStatus);

					Object[] data = {createdOn,nid,txnId,country,taxStatus,origin,status,action};

					List<Object> datatableList = Arrays.asList(data);
					finalList.add(datatableList);
					datatableResponseModel.setData(finalList);
				}
			}else if("Immigration".equals(userType)) {
				for(UserPaidStatusContent contentModelList : contentList) {
					log.info("in Immigration -----> "+userType);
					//Integer sno = contentModelList.getId();
					String createdOn = contentModelList.getCreatedOn();
					String nid = contentModelList.getNid();
					String txnId = contentModelList.getTxnId(); 
					String deviceIDInterp = contentModelList.getDeviceIdTypeInterp();
					//String deviceTypeInterp = contentModelList.getDeviceTypeInterp();
					String currency = contentModelList.getCurrencyInterp() == null ? "" : contentModelList.getCurrencyInterp();
					String price = currency.concat(String.valueOf(contentModelList.getPrice()));
					String country = contentModelList.getCountry();
					String taxStatus = contentModelList.getTaxPaidStatusInterp();
					String status = contentModelList.getStateInterp();
					
					//params for action 
					String imei1 = contentModelList.getFirstImei();
					String deviceState = String.valueOf(contentModelList.getStatus());
					String action = iconState.deviceActivationIcon(imei1,createdOn,contentModelList.getTxnId(),deviceState,userStatus);

					Object[] data = {createdOn,txnId,nid,action};

					List<Object> datatableList = Arrays.asList(data);
					finalList.add(datatableList);
					datatableResponseModel.setData(finalList);
				}
			}
			datatableResponseModel.setRecordsTotal(upsPaginationModel.getNumberOfElements());
			datatableResponseModel.setRecordsFiltered(upsPaginationModel.getTotalElements());
			log.info(":::::datatableResponseModel:::::"+datatableResponseModel);
			return new ResponseEntity<>(datatableResponseModel,HttpStatus.OK);	
		}
		catch(Exception e) {
			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			log.info(e.getMessage());
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK);
		}
	}





	@PostMapping("upload-paid-status/pageRendering")
	public ResponseEntity<?> directives(@RequestParam(name="type",defaultValue = "userPaidStatus",required = false) String role,HttpSession session){

		String userType = (String) session.getAttribute("usertype");
		String userStatus = (String) session.getAttribute("userStatus");

		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		if("Immigration".equals(userType)){
			pageElement.setPageTitle(Translator.toLocale("Device Activation"));	
		}else {
		pageElement.setPageTitle(Translator.toLocale("view.uplaodpaidstatus"));
		}
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
		
		
		
		if("Immigration".equals(userType)){
			String[] names= {"HeaderButton",Translator.toLocale("button.register"),"JavaScript:void(0);","btnLink","FilterButton", Translator.toLocale("button.filter"),"filter("+ConfigParameters.languageParam+")","submitFilter"};
			for(int i=0; i< names.length ; i++) {
				button = new Button();
				button.setType(names[i]);
				i++;
				button.setButtonTitle(names[i]);
				i++;
				button.setButtonURL(names[i]);
				i++;
				button.setId(names[i]);
				buttonList.add(button);
			}			
			pageElement.setButtonList(buttonList);
			
		}else {
			String[] names= {"HeaderButton",Translator.toLocale("button.adddevice"),"./add-device-information","btnLink","FilterButton", Translator.toLocale("button.filter"),"filter("+ConfigParameters.languageParam+")","submitFilter"};
			for(int i=0; i< names.length ; i++) {
				button = new Button();
				button.setType(names[i]);
				i++;
				button.setButtonTitle(names[i]);
				i++;
				button.setButtonURL(names[i]);
				i++;
				button.setId(names[i]);
				buttonList.add(button);
			}			
			pageElement.setButtonList(buttonList);
		}
		
		

		//Dropdown items			
		String[] selectParam= {"select",Translator.toLocale("select.deviceIDType"),"deviceIDType","","select",Translator.toLocale("select.deviceType"),"deviceTypeFilter","","select",Translator.toLocale("select.taxPaidStatus"),"taxPaidStatus",""};
		for(int i=0; i< selectParam.length; i++) {
			inputFields= new InputFields();
			inputFields.setType(selectParam[i]);
			i++;
			inputFields.setTitle(selectParam[i]);
			i++;
			inputFields.setId(selectParam[i]);
			i++;
			inputFields.setClassName(selectParam[i]);
			dropdownList.add(inputFields);
		}
		pageElement.setDropdownList(dropdownList);

		if("Custom".equals(userType)) {
			//input type date list
			String[] dateParam= {"date",Translator.toLocale("input.startDate"),"startDate","","date",Translator.toLocale("input.endDate"),"endDate","","text",Translator.toLocale("input.transactionID"),"transactionID",""};
			for(int i=0; i< dateParam.length; i++) {
			dateRelatedFields= new InputFields();
			dateRelatedFields.setType(dateParam[i]);
			i++;
			dateRelatedFields.setTitle(dateParam[i]);
			i++;
			dateRelatedFields.setId(dateParam[i]);
			i++;
			dateRelatedFields.setClassName(dateParam[i]);
			inputTypeDateList.add(dateRelatedFields);
			}
			}else if("Immigration".equals(userType)){
				String[] dateParam= {"date",Translator.toLocale("input.startDate"),"startDate","","date",Translator.toLocale("input.endDate"),"endDate","","text",Translator.toLocale("input.transactionID"),"transactionID","","text",Translator.toLocale("input.passportNo"),"nId",""};
				for(int i=0; i< dateParam.length; i++) {
				dateRelatedFields= new InputFields();
				dateRelatedFields.setType(dateParam[i]);
				i++;
				dateRelatedFields.setTitle(dateParam[i]);
				i++;
				dateRelatedFields.setId(dateParam[i]);
				i++;
				dateRelatedFields.setClassName(dateParam[i]);
				inputTypeDateList.add(dateRelatedFields);
				}
			}else {
			//input type date list
			String[] dateParam= {"date",Translator.toLocale("input.startDate"),"startDate","","date",Translator.toLocale("input.endDate"),"endDate","","text",Translator.toLocale("input.nidInput"),"nId","","text",Translator.toLocale("input.transactionID"),"transactionID",""};
			for(int i=0; i< dateParam.length; i++) {
			dateRelatedFields= new InputFields();
			dateRelatedFields.setType(dateParam[i]);
			i++;
			dateRelatedFields.setTitle(dateParam[i]);
			i++;
			dateRelatedFields.setId(dateParam[i]);
			i++;
			dateRelatedFields.setClassName(dateParam[i]);
			inputTypeDateList.add(dateRelatedFields);
			}
			}	
		pageElement.setInputTypeDateList(inputTypeDateList);
		pageElement.setUserStatus(userStatus);
		return new ResponseEntity<>(pageElement, HttpStatus.OK); 		
	}	



	//************************************************ delete consignment record page********************************************************************************/


	@DeleteMapping("/delete/{imei}")
	public @ResponseBody GenricResponse deleteConsignment(@PathVariable("imei") Long imei) {
		GenricResponse response=uploadPaidStatusFeignClient.delete(imei);
		log.info("response after delete consignment."+response);
		return response;
	}


	
	@GetMapping("/deviceInfo/{imei}")
	public @ResponseBody UserPaidStatusContent viewByImei(@PathVariable("imei") Long imei) {
		UserPaidStatusContent content= uploadPaidStatusFeignClient.viewByImei(imei);
		return content;
	}
	
	
}