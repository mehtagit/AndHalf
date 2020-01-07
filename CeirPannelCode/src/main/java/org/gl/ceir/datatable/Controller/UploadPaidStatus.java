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
		log.info("userType in uploadPaidStatus" +userType);
		Object response = null;
		Gson gsonObject=new Gson();
		Gson gson=new Gson();
		FilterRequest_UserPaidStatus filterrequest = gsonObject.fromJson(filter, FilterRequest_UserPaidStatus.class);

		//	filterrequest.setSearchString(request.getParameter("search[value]"));
		try {
			log.info("-----------filterrequest-------------"+filterrequest);
			response = uploadPaidStatusFeignClient.view(filterrequest, pageNo, pageSize, file);
			log.info("--response----------"+response);
			String apiResponse = gson.toJson(response);
			log.info("--contentList----------"+apiResponse);
			UserPaidStatusPaginationModel upsPaginationModel = gson.fromJson(apiResponse, UserPaidStatusPaginationModel.class);
			log.info("::::::::::::::::"+upsPaginationModel);	
			List<UserPaidStatusContent> contentList = upsPaginationModel.getContent();
			if(contentList.isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
			}
			else if("Custom".equals(userType)) {
				log.info("in Custom Userpaid Status---" +userType);
				
				for(UserPaidStatusContent contentModelList : contentList) {
					String nid = contentModelList.getNid();
					Integer sno = contentModelList.getId();
					String createdOn = contentModelList.getCreatedOn();
					String deviceIDInterp = contentModelList.getDeviceIdTypeInterp();
					String deviceTypeInterp = contentModelList.getDeviceTypeInterp();
					String currency = contentModelList.getCurrencyInterp() == null ? "" : contentModelList.getCurrencyInterp();
					String price = currency.concat(String.valueOf(contentModelList.getPrice()));
					String country = contentModelList.getCountry();
					String status = contentModelList.getTaxPaidStatusInterp();

					//params for action 
					Long imei1 = contentModelList.getFirstImei();
					String action = iconState.userPaidStatusIcon(imei1);

					Object[] data = {sno,createdOn,nid,deviceIDInterp,deviceTypeInterp,price,country,status,action};

					List<Object> datatableList = Arrays.asList(data);
					finalList.add(datatableList);
					datatableResponseModel.setData(finalList);
				}
			}else if("CEIRAdmin".equals(userType)) {
				for(UserPaidStatusContent contentModelList : contentList) {
					Integer sno = contentModelList.getId();
					String createdOn = contentModelList.getCreatedOn();
					String nid = contentModelList.getNid();
					String deviceIDInterp = contentModelList.getDeviceIdTypeInterp();
					String deviceTypeInterp = contentModelList.getDeviceTypeInterp();
					String currency = contentModelList.getCurrencyInterp() == null ? "" : contentModelList.getCurrencyInterp();
					String price = currency.concat(String.valueOf(contentModelList.getPrice()));
					String country = contentModelList.getCountry();
					String taxStatus = contentModelList.getTaxPaidStatusInterp();
					String status = contentModelList.getStateInterp();
					
					//params for action 
					Long imei1 = contentModelList.getFirstImei();
					String action = iconState.adminUserPaidStatusIcon(imei1,createdOn);

					Object[] data = {sno,createdOn,nid,deviceIDInterp,deviceTypeInterp,price,country,taxStatus,status,action};

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

		pageElement.setPageTitle("Upload Paid Status");

		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();

		log.info("USER STATUS:::::::::"+userStatus);
		log.info("session value user Type=="+session.getAttribute("usertype"));

		String[] names= {"HeaderButton","Add Device","./add-device-information","btnLink","FilterButton", "filter","filter()","submitFilter"};
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

		//Dropdown items			
		String[] selectParam= {"select","Device ID Type ","deviceIDType","","select","Device Type ","deviceTypeFilter","","select","Tax Paid Status","taxPaidStatus",""};
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
			String[] dateParam= {"date","Start date","startDate","","date","End date","endDate","",};
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
			String[] dateParam= {"date","Start date","startDate","","date","End date","endDate","","text","NID","nId",""};
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