package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.gl.ceir.pagination.model.ConsignmentContent;
import org.gl.ceir.pagination.model.ConsignmentPaginationModel;
import org.gl.ceir.pagination.model.UserModel;
import org.gl.ceir.pagination.model.UserProfileModel;
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
public class ConsignmentDatatableController {
	String className = "emptyClass";

	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	UtilDownload utildownload;
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	ConsignmentPaginationModel consignmentPaginationModel;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	@Autowired
	IconsState iconState;
	@Autowired
	UserProfileModel userprofileModel;	
	
	/*
	 * @Autowired ConsignmentService consignmentService;
	 */

	private final Logger log = LoggerFactory.getLogger(getClass());

	@PostMapping("consignmentData")
	 public ResponseEntity<?> viewConsignmentList(@RequestParam(name="type",defaultValue = "consignment",required = false) String role ,@RequestParam(name="sourceType",required = false) String sourceType,HttpServletRequest request,HttpSession session) {	 		
		
		log.info("session value user Type=="+session.getAttribute("usertype"));
		String userType = (String) session.getAttribute("usertype");
		
		// Data set on this List
		List<List<String>> finalList=new ArrayList<List<String>>();

		String filter = request.getParameter("filter");
		Gson gsonObject=new Gson();
		FilterRequest filterrequest = gsonObject.fromJson(filter, FilterRequest.class);

		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;

		// TODO Convert header to an ENUM.
		// list provided via Back-end process
		try {
		log.info("request send to the filter api ="+filterrequest);
		Object response = feignCleintImplementation.consignmentFilter(filterrequest,pageNo,pageSize);
		log.info("response:::::::::::::"+response);
		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		consignmentPaginationModel = gson.fromJson(apiResponse, ConsignmentPaginationModel.class);
		List<ConsignmentContent> paginationContentList = consignmentPaginationModel.getContent();
		log.info("paginationContentList::::::::::::"+paginationContentList);
		if(paginationContentList.isEmpty()) {
			datatableResponseModel.setData(Collections.emptyList());
		}
		else {
			log.info("sourceType---@@@@@@@@@@@@@@@@@--------"+sourceType);
			
			if("Importer".equals(userType) && "viaStolen".equals(sourceType)){
				for(ConsignmentContent dataInsideList : paginationContentList) 
				{
				String checboxes = "<input type=checkbox class=filled-in>";
				String createdOn= dataInsideList.getCreatedOn();
				String txnId=	dataInsideList.getTxnId(); 
				String supplierName= dataInsideList.getSupplierName(); 
				// if API provide me consignmentStatusName
				String statusOfConsignment = String.valueOf(dataInsideList.getConsignmentStatus());
				String consignmentStatusName = dataInsideList.getStateInterp();
				String taxPaidStatus= String.valueOf(dataInsideList.getTaxPaidStatus());
				String taxPaidStatusName=dataInsideList.getTaxInterp();
				String[] finalData={checboxes,createdOn,txnId,supplierName,consignmentStatusName,taxPaidStatusName}; 
					List<String> finalDataList=new ArrayList<String>(Arrays.asList(finalData));
					finalList.add(finalDataList);
					datatableResponseModel.setData(finalList);
				}
			}
			
			else if("Importer".equals(userType)){
				for(ConsignmentContent dataInsideList : paginationContentList) 
				{
				String createdOn= dataInsideList.getCreatedOn();
				String txnId=	dataInsideList.getTxnId(); 
				String supplierName= dataInsideList.getSupplierName(); 
				// if API provide me consignmentStatusName
				String statusOfConsignment = String.valueOf(dataInsideList.getConsignmentStatus());
				String consignmentStatusName = dataInsideList.getStateInterp();
				String taxPaidStatus= String.valueOf(dataInsideList.getTaxPaidStatus());
				String taxPaidStatusName=dataInsideList.getTaxInterp();
				String userStatus = (String) session.getAttribute("userStatus");
				String action=iconState.state(dataInsideList.getFileName(), txnId, statusOfConsignment,userStatus);
				
				String[] finalData={createdOn,txnId,supplierName,consignmentStatusName,taxPaidStatusName,action}; 
					List<String> finalDataList=new ArrayList<String>(Arrays.asList(finalData));
					finalList.add(finalDataList);
					datatableResponseModel.setData(finalList);
				}
			}
		
			
			else if("Custom".equals(userType)) {
				for(ConsignmentContent dataInsideList : paginationContentList) 
				{
				UserModel userModel = dataInsideList.getUser();
				UserProfileModel userprofileModel = userModel.getUserProfile();
				String createdOn= dataInsideList.getCreatedOn();
				String txnId = dataInsideList.getTxnId(); 
				String displayName = userprofileModel.getDisplayName();		
				String statusOfConsignment = String.valueOf(dataInsideList.getConsignmentStatus());
				String consignmentStatusName = dataInsideList.getStateInterp();
				String taxPaidStatus= String.valueOf(dataInsideList.getTaxPaidStatus());
				String taxPaidStatusName=dataInsideList.getTaxInterp();
				String userStatus = (String) session.getAttribute("userStatus");
				String action=iconState.customState(dataInsideList.getFileName(), txnId, statusOfConsignment,userStatus,displayName);
				
				String[] finalData={createdOn,txnId,displayName,consignmentStatusName,taxPaidStatusName,action}; 
					List<String> finalDataList=new ArrayList<String>(Arrays.asList(finalData));
					finalList.add(finalDataList);
					datatableResponseModel.setData(finalList);
				}
			}else if("CEIRAdmin".equals(userType)) {
				for(ConsignmentContent dataInsideList : paginationContentList) 
				{
				UserModel userModel = dataInsideList.getUser();
				UserProfileModel userprofileModel = userModel.getUserProfile();
				String createdOn= dataInsideList.getCreatedOn();
				String txnId = dataInsideList.getTxnId(); 
				String companyName = userprofileModel.getCompanyName();		
				String statusOfConsignment = String.valueOf(dataInsideList.getConsignmentStatus());
				String consignmentStatusName = dataInsideList.getStateInterp();
				String taxPaidStatus= String.valueOf(dataInsideList.getTaxPaidStatus());
				String taxPaidStatusName=dataInsideList.getTaxInterp();
				String userStatus = (String) session.getAttribute("userStatus");
				String action=iconState.adminState(dataInsideList.getFileName(), txnId, statusOfConsignment,userStatus);
				
				
				String[] finalData={createdOn,txnId,companyName,consignmentStatusName,taxPaidStatusName,action}; 
					List<String> finalDataList=new ArrayList<String>(Arrays.asList(finalData));
					finalList.add(finalDataList);
					datatableResponseModel.setData(finalList);
				}
			}
			
		}
		
		
		//data set on ModelClass
		
		datatableResponseModel.setRecordsTotal(consignmentPaginationModel.getNumberOfElements());
		datatableResponseModel.setRecordsFiltered(consignmentPaginationModel.getTotalElements());
		return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		
		}
		catch(Exception e) {
			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			log.error(e.getMessage(),e);
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		}
		
	}


	
	@PostMapping("consignment/pageRendering")
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "consignment",required = false) String role,@RequestParam(name="sourceType",required = false) String sourceType,HttpSession session){

	String userType = (String) session.getAttribute("usertype");
	String userStatus = (String) session.getAttribute("userStatus");

	InputFields inputFields = new InputFields();
	InputFields dateRelatedFields;

	pageElement.setPageTitle("Consignment");

	List<Button> buttonList = new ArrayList<>();
	List<InputFields> dropdownList = new ArrayList<>();
	List<InputFields> inputTypeDateList = new ArrayList<>();
	log.info("USER STATUS:::::::::"+userStatus);
	log.info("session value user Type=="+session.getAttribute("usertype"));
	log.info("sourceType in rendering $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" +sourceType);

	if("Importer".equals(userType) && "viaStolen".equals(sourceType)){
	String[] names= {"HeaderButton","Register Consignment","./openRegisterConsignmentForm?reqType=formPage","btnLink","FilterButton", "filter","filterConsignment()","submitFilter"};
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


	String[] footerBtn= {"FooterButton", "Mark As Stolen","markedstolen()","markedstolen","FooterButton", "Cancel","cancel()","cancel"};
	for(int i=0; i< footerBtn.length ; i++) {
	button = new Button();
	button.setType(footerBtn[i]);
	i++;
	button.setButtonTitle(footerBtn[i]);
	i++;
	button.setButtonURL(footerBtn[i]);
	i++;
	button.setId(footerBtn[i]);
	buttonList.add(button);
	}	
	pageElement.setButtonList(buttonList);



	//Dropdown items	
	String[] selectParam= {"select","Consignment Status","filterConsignmentStatus","","select","Tax Paid Status","taxPaidStatus",""};
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

	//input type date list	aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqq11111111111111111111111111111111111111111111111111111
	String[] dateParam= {"date","Start date","startDate","","date","End date","endDate","","text","Transaction ID","transactionID",""};
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
	String[] names= {"HeaderButton","Register Consignment","./openRegisterConsignmentForm?reqType=formPage","btnLink","FilterButton", "filter","filterConsignment()","submitFilter"};
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
	String[] selectParam= {"select","Consignment Status","filterConsignmentStatus","","select","Tax Paid Status","taxPaidStatus",""};
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
	String[] dateParam= {"date","Start date","startDate","","date","End date","endDate","","text","Transaction ID","transactionID",""};
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
	}
	





