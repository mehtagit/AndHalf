package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.configuration.ConfigParameters;
import org.gl.ceir.configuration.Translator;
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.gl.ceir.pagination.model.AuditContentModel;
import org.gl.ceir.pagination.model.AuditPaginationModel;
import org.gl.ceir.pagination.model.PolicyConfigContent;
import org.gl.ceir.pagination.model.PolicyConfigPagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class AuditDatatableController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	IconsState iconState;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	AuditContentModel auditContentModel;
	@Autowired
	AuditPaginationModel auditPaginationModel;
	@Autowired
	Translator Translator;
	
	@PostMapping("auditManagementData")
	public ResponseEntity<?> viewAuditManagement(@RequestParam(name="type",defaultValue = "AuditManagement",required = false) String role, HttpServletRequest request,HttpSession session) {
		String userType = (String) session.getAttribute("usertype");
		int userId=	(int) session.getAttribute("userid");
		// Data set on this List
		List<List<Object>> finalList=new ArrayList<List<Object>>();
		String filter = request.getParameter("filter");
		Gson gsonObject=new Gson();
		FilterRequest filterrequest = gsonObject.fromJson(filter, FilterRequest.class);
		Integer file = 0;
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;
		filterrequest.setSearchString(request.getParameter("search[value]"));
		log.info("pageSize"+pageSize+"-----------pageNo---"+pageNo);
		try {
			log.info("request send to the filter api ="+filterrequest);
			Object response = feignCleintImplementation.auditManagementFeign(filterrequest, pageNo, pageSize, file);
			log.info("response in datatable"+response);
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			auditPaginationModel = gson.fromJson(apiResponse, AuditPaginationModel.class);
			List<AuditContentModel> paginationContentList = auditPaginationModel.getContent();
			if(paginationContentList.isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
			}else {
				for(AuditContentModel dataInsideList : paginationContentList) 
				{
				  String id = String.valueOf(dataInsideList.getId());	
				  String createdOn = dataInsideList.getCreatedOn();
				  String modifiedOn = dataInsideList.getModifiedOn();
				  String txnId = dataInsideList.getTxnId();
				  String getuserId = String.valueOf(dataInsideList.getUserId());
				  String userName = dataInsideList.getUserName();
				  String userTypeName = dataInsideList.getUserType();
				  String featureName = dataInsideList.getFeatureName();
				  String subFeature = dataInsideList.getSubFeature();
				   String userStatus = (String) session.getAttribute("userStatus");
				   String action=iconState.auditManagementIcons(userStatus,getuserId,id);			   
				   Object[] finalData={createdOn,txnId,userName,userTypeName,featureName,subFeature,action}; 
				   List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
				   finalList.add(finalDataList);
				   datatableResponseModel.setData(finalList);	
			}
			}
			//data set on ModelClass
			datatableResponseModel.setRecordsTotal(auditPaginationModel.getNumberOfElements());
			datatableResponseModel.setRecordsFiltered(auditPaginationModel.getTotalElements());
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
			
		}catch(Exception e) {
			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			log.error(e.getMessage(),e);
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		}
	}
	
	
	@PostMapping("audit/pageRendering")
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "Config",required = false) String role,HttpSession session){

		String userType = (String) session.getAttribute("usertype");
		String userStatus = (String) session.getAttribute("userStatus");
		
		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		pageElement.setPageTitle("Audit Management");
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
			
			log.info("USER STATUS:::::::::"+userStatus);
			log.info("session value user Type=="+session.getAttribute("usertype"));
			
			String[] names = { "HeaderButton", Translator.toLocale("button.addCurrency"), "AddCurrencyAddress()", "btnLink",
					"FilterButton", Translator.toLocale("button.filter"),"auditManagementDatatable(" + ConfigParameters.languageParam + ")", "submitFilter" };
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
		  String[] selectParam={"select","User Type","roleType",""}; 
		  for(int i=0; i<selectParam.length; i++) { 
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
		 
			
			//input type date list		
			String[] dateParam= {"date","Start Date","startDate","","date","End Date","endDate","","text","Transaction ID","transactionID","","text","Feature","feature","","text","Sub Feature","subFeature","","text","User Name","userName",""};
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
			
			
			pageElement.setInputTypeDateList(inputTypeDateList);
			pageElement.setUserStatus(userStatus);
			return new ResponseEntity<>(pageElement, HttpStatus.OK); 
		
		
	}
	
	
}
