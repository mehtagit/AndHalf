package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Feignclient.UserProfileFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.gl.ceir.pagination.model.RegistrationContentModel;
import org.gl.ceir.pagination.model.RegistrationPaginationModel;
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
public class RegistrationReqDatatableController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	String className = "emptyClass";
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	IconsState iconState;
	@Autowired
	RegistrationContentModel registrationcontentmodel;
	@Autowired
	RegistrationPaginationModel registrationpaginationmodel;
	@Autowired
	UserProfileFeignImpl userProfileFeignImpl;
	
	@PostMapping("registrationData")
	public ResponseEntity<?> viewUserProfileRecord(@RequestParam(name="type",defaultValue = "registration",required = false) String role, HttpServletRequest request,HttpSession session) {
	
		String userType = (String) session.getAttribute("usertype");
		int userId=	(int) session.getAttribute("userid");
		
		log.info("session value user Type admin registration Controller=="+session.getAttribute("usertype"));
		
		// Data set on this List
		List<List<String>> finalList=new ArrayList<List<String>>();
		FilterRequest filterrequest = new FilterRequest();
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;
		log.info("pageSize"+pageSize+"-----------pageNo---"+pageNo);
		
		
		try {
			Object response = userProfileFeignImpl.registrationRequest(filterrequest,pageNo,pageSize);
			log.info("response in datatable"+response);
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			registrationpaginationmodel = gson.fromJson(apiResponse, RegistrationPaginationModel.class);
			List<RegistrationContentModel> paginationContentList = registrationpaginationmodel.getContent();
			if(paginationContentList.isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
				
			}
			else {
			
				for(RegistrationContentModel dataInsideList : paginationContentList) 
				{
				   String createdOn = (String) dataInsideList.getCreatedOn();
				   String Id = String.valueOf(dataInsideList.getId());
				   String type = dataInsideList.getType();
				   String roles =  (String) dataInsideList.getRoles();
				   String StatusofGrievance = String.valueOf(dataInsideList.getStatus());
				   String grievanceStatusName = dataInsideList.getStateInterp();
				   String userStatus = (String) session.getAttribute("userStatus");
				   String action=iconState.adminRegistrationRequest(Id,StatusofGrievance,userStatus,grievanceStatusName);			   
				   String[] finalData={createdOn,Id,type,roles,grievanceStatusName,action}; 
					List<String> finalDataList=new ArrayList<String>(Arrays.asList(finalData));
					finalList.add(finalDataList);
					datatableResponseModel.setData(finalList);	
			}
		}
			//data set on ModelClass
			datatableResponseModel.setRecordsTotal(registrationpaginationmodel.getNumberOfElements());
			datatableResponseModel.setRecordsFiltered(registrationpaginationmodel.getTotalElements());
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
	}catch(Exception e) {
		datatableResponseModel.setRecordsTotal(null);
		datatableResponseModel.setRecordsFiltered(null);
		datatableResponseModel.setData(Collections.emptyList());
		return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		}
	}
	
	


	@PostMapping("registration/pageRendering")
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "consignment",required = false) String role,HttpSession session){

		String userType = (String) session.getAttribute("usertype");
		String userStatus = (String) session.getAttribute("userStatus");
		
		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		pageElement.setPageTitle("Registration Request");
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
			
			log.info("USER STATUS:::::::::"+userStatus);
			log.info("session value user Type=="+session.getAttribute("usertype"));
			
			String[] names= {"FilterButton", "filter","filterConsignment()","submitFilter"};
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
			String[] selectParam= {"select","As Type ","asType","","select","Role","role","","select","Status","recentStatus",""};
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
			
			//input type date list		
			String[] dateParam= {"date","Start date","startDate","","date","End date","endDate","", "text","Transaction ID","transactionID","","text","Grievance ID","grievanceID","" };
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
