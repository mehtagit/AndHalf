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
import org.gl.ceir.configuration.ConfigParameters;
import org.gl.ceir.configuration.Translator;
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
	Translator Translator;
	
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
	 public ResponseEntity<?> viewConsignmentList(@RequestParam(name="type",defaultValue = "consignment",required = false) String role ,
			 @RequestParam(name="sourceType",required = false) String sourceType,HttpServletRequest request,HttpSession session,@RequestParam(name="sessionFlag",required = false) Integer sessionFlag) {	 		
		
		log.info("session value user Type=="+session.getAttribute("usertype"));
		String userType = (String) session.getAttribute("usertype");
		
		// Data set on this List
		List<List<Object>> finalList=new ArrayList<List<Object>>();

		String filter = request.getParameter("filter");
		Gson gsonObject=new Gson();
		Object response;
		Integer file = 0;
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;
		
		FilterRequest filterrequest = gsonObject.fromJson(filter, FilterRequest.class);
		filterrequest.setSearchString(request.getParameter("search[value]"));
		
		log.info("session flag value==####@@@@@@@@@@@@@="+sessionFlag);
		log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$="+filterrequest);
		/*
		 * if(sessionFlag==2) { if (filterrequest.getTxnId()==null &&
		 * filterrequest.getConsignmentStatus()==null &&
		 * filterrequest.getTaxPaidStatus()==null && filterrequest.getStartDate()==null
		 * && filterrequest.getEndDate()==null ) { log.info("----------------------");
		 * 
		 * }
		 * 
		 * else if(filterrequest.getTxnId()!=null &&
		 * filterrequest.getConsignmentStatus()==null &&
		 * filterrequest.getTaxPaidStatus()==null &&
		 * filterrequest.getStartDate().equals("") &&
		 * filterrequest.getEndDate().equals("") ) { log.info("++++++++++++++++++++++");
		 * session.setAttribute("consignmentStartDate", filterrequest.getStartDate());
		 * session.setAttribute("consignmentEndDate",filterrequest.getEndDate());
		 * session.setAttribute("consignmentStatus",
		 * filterrequest.getConsignmentStatus());
		 * session.setAttribute("consignmentTaxPaidStatus",filterrequest.
		 * getTaxPaidStatus()); session.setAttribute("consignmentTxnId",
		 * filterrequest.getTxnId()); } else if(filterrequest.getTxnId()!=null &&
		 * filterrequest.getConsignmentStatus()!=null &&
		 * filterrequest.getTaxPaidStatus()==null &&
		 * filterrequest.getStartDate().equals("") &&
		 * filterrequest.getEndDate().equals("") ) {
		 * log.info("111111111111111111111111");
		 * session.setAttribute("consignmentStartDate", filterrequest.getStartDate());
		 * session.setAttribute("consignmentEndDate",filterrequest.getEndDate());
		 * session.setAttribute("consignmentStatus",
		 * filterrequest.getConsignmentStatus());
		 * session.setAttribute("consignmentTaxPaidStatus",filterrequest.
		 * getTaxPaidStatus()); session.setAttribute("consignmentTxnId",
		 * filterrequest.getTxnId()); } else if(filterrequest.getTxnId().equals("") &&
		 * filterrequest.getConsignmentStatus()!=null &&
		 * filterrequest.getTaxPaidStatus()==null &&
		 * filterrequest.getStartDate().equals("") &&
		 * filterrequest.getEndDate().equals("") ) { log.info("#####################");
		 * session.setAttribute("consignmentStartDate", filterrequest.getStartDate());
		 * session.setAttribute("consignmentEndDate",filterrequest.getEndDate());
		 * session.setAttribute("consignmentStatus",
		 * filterrequest.getConsignmentStatus());
		 * session.setAttribute("consignmentTaxPaidStatus",filterrequest.
		 * getTaxPaidStatus()); session.setAttribute("consignmentTxnId",
		 * filterrequest.getTxnId()); } else if(filterrequest.getTxnId()==null &&
		 * filterrequest.getConsignmentStatus()==null &&
		 * filterrequest.getTaxPaidStatus()!=null && filterrequest.getStartDate()==null
		 * && filterrequest.getEndDate()==null ) {
		 * log.info("22222222222222222222222222");
		 * session.setAttribute("consignmentStartDate", filterrequest.getStartDate());
		 * session.setAttribute("consignmentEndDate",filterrequest.getEndDate());
		 * session.setAttribute("consignmentStatus",
		 * filterrequest.getConsignmentStatus());
		 * session.setAttribute("consignmentTaxPaidStatus",filterrequest.
		 * getTaxPaidStatus()); session.setAttribute("consignmentTxnId",
		 * filterrequest.getTxnId()); } else if(filterrequest.getTxnId()==null &&
		 * filterrequest.getConsignmentStatus()==null &&
		 * filterrequest.getTaxPaidStatus()==null && filterrequest.getStartDate()!=null
		 * && filterrequest.getEndDate()==null ) {
		 * log.info("33333333333333333333333333");
		 * session.setAttribute("consignmentStartDate", filterrequest.getStartDate());
		 * session.setAttribute("consignmentEndDate",filterrequest.getEndDate());
		 * session.setAttribute("consignmentStatus",
		 * filterrequest.getConsignmentStatus());
		 * session.setAttribute("consignmentTaxPaidStatus",filterrequest.
		 * getTaxPaidStatus()); session.setAttribute("consignmentTxnId",
		 * filterrequest.getTxnId()); } else if(filterrequest.getTxnId()==null &&
		 * filterrequest.getConsignmentStatus()==null &&
		 * filterrequest.getTaxPaidStatus()==null && filterrequest.getStartDate()==null
		 * && filterrequest.getEndDate()!=null ) {
		 * log.info("4444444444444444444444444444444");
		 * session.setAttribute("consignmentStartDate", filterrequest.getStartDate());
		 * session.setAttribute("consignmentEndDate",filterrequest.getEndDate());
		 * session.setAttribute("consignmentStatus",
		 * filterrequest.getConsignmentStatus());
		 * session.setAttribute("consignmentTaxPaidStatus",filterrequest.
		 * getTaxPaidStatus()); session.setAttribute("consignmentTxnId",
		 * filterrequest.getTxnId()); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * String consignmentStartDate=(String)
		 * session.getAttribute("consignmentStartDate"); String
		 * consignmentEndDate=(String) session.getAttribute("consignmentEndDate");
		 * Integer consignmentStatus=(Integer)
		 * session.getAttribute("consignmentStatus"); Integer
		 * consignmentTaxPaidStatus=(Integer)
		 * session.getAttribute("consignmentTaxPaidStatus"); String
		 * consignmentTxnId=(String) session.getAttribute("consignmentTxnId");
		 * 
		 * log.info("filterd start date ="+filterrequest.getStartDate()
		 * +" filterd end date=="+filterrequest.getEndDate()
		 * +" filter consignment status="+filterrequest.getConsignmentStatus()
		 * +" consignmentTaxPaidStatus ="
		 * +consignmentTaxPaidStatus+" consignment txn id=="+filterrequest.getTxnId());
		 * 
		 * if(session.getAttribute("consignmentStartDate")==null &&
		 * session.getAttribute("consignmentEndDate")==null &&
		 * session.getAttribute("consignmentStatus")==null &&
		 * session.getAttribute("consignmentTaxPaidStatus")==null &&
		 * session.getAttribute("consignmentTxnId")==null ) {
		 * 
		 * 
		 * 
		 * filterrequest.setStartDate(consignmentStartDate);filterrequest.setEndDate(
		 * consignmentEndDate);filterrequest.setConsignmentStatus(consignmentStatus);
		 * filterrequest.setTaxPaidStatus(consignmentTaxPaidStatus);filterrequest.
		 * setTxnId(consignmentTxnId);
		 * 
		 * log.
		 * info("session is  blank *********************** request send to the filter api ="
		 * +filterrequest); log.info("*******consignmentStartDate=="+
		 * consignmentStartDate+" consignmentEndDate=="
		 * +consignmentEndDate+" consignmentStatus=="
		 * +consignmentStatus+" consignmentTaxPaidStatus=="
		 * +consignmentTaxPaidStatus+" consignmentTxnId ="+consignmentTxnId);
		 * 
		 * response =
		 * feignCleintImplementation.consignmentFilter(filterrequest,pageNo,pageSize,
		 * file);
		 * 
		 * 
		 * } else {
		 * 
		 * filterrequest.setStartDate(consignmentStartDate);filterrequest.setEndDate(
		 * consignmentEndDate);filterrequest.setConsignmentStatus(consignmentStatus);
		 * filterrequest.setTaxPaidStatus(consignmentTaxPaidStatus);filterrequest.
		 * setTxnId(consignmentTxnId);
		 * 
		 * log.
		 * info("session is not blank ************************ request send to the filter api ="
		 * +filterrequest); log.info("++++++++++++=consignmentStartDate=="+
		 * consignmentStartDate+" consignmentEndDate=="
		 * +consignmentEndDate+" consignmentStatus=="
		 * +consignmentStatus+" consignmentTaxPaidStatus=="
		 * +consignmentTaxPaidStatus+" consignmentTxnId ="+consignmentTxnId);
		 * 
		 * response =
		 * feignCleintImplementation.consignmentFilter(filterrequest,pageNo,pageSize,
		 * file);
		 * 
		 * } } else { log.
		 * info("session not to be use>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "
		 * ); response =
		 * feignCleintImplementation.consignmentFilter(filterrequest,pageNo,pageSize,
		 * file); }
		 */
		 
		 // TODO Convert header to an ENUM.
		// list provided via Back-end process
		log.info("request send to the filter api ="+filterrequest);
		response = feignCleintImplementation.consignmentFilter(filterrequest,pageNo,pageSize,file);
		log.info("response:::::::::::::"+response);
		try {
			
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
			log.info("sourceType---"+userType);
			if("Importer".equals(userType) && "viaStolen".equals(sourceType)){
				for(ConsignmentContent dataInsideList : paginationContentList) 
				{
				String checboxes = "<input type=checkbox class=filled-in>";
				String createdOn= dataInsideList.getCreatedOn();
				String txnId=	dataInsideList.getTxnId(); 
				String supplierName= dataInsideList.getSupplierName(); 
				// if API provide me consignmentStatusName
				//String statusOfConsignment = String.valueOf(dataInsideList.getConsignmentStatus());
				String consignmentStatusName = dataInsideList.getStateInterp();
			//	String taxPaidStatus= String.valueOf(dataInsideList.getTaxPaidStatus());
				String taxPaidStatusName=dataInsideList.getTaxInterp();
				String quantity = String.valueOf(dataInsideList.getQuantity());
				//String deviceQuantity=String.valueOf(dataInsideList.getDeviceQuantity());
				Object[] finalData={checboxes,createdOn,txnId,supplierName,consignmentStatusName,taxPaidStatusName,quantity}; 
					List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
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
				String quantity = String.valueOf(dataInsideList.getQuantity());
				String deviceQuantity=String.valueOf(dataInsideList.getDeviceQuantity());
				String action=iconState.state(dataInsideList.getFileName(), txnId, statusOfConsignment,userStatus);
				
				Object[] finalData={createdOn,txnId,supplierName,consignmentStatusName,taxPaidStatusName,quantity,deviceQuantity,action}; 
					List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
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
				String quantity = String.valueOf(dataInsideList.getQuantity());
				String deviceQuantity=String.valueOf(dataInsideList.getDeviceQuantity());
				String action=iconState.customState(dataInsideList.getFileName(), txnId, statusOfConsignment,userStatus,displayName);
				
				
			
				String[] finalData={createdOn,txnId,displayName,consignmentStatusName,taxPaidStatusName,quantity,deviceQuantity,action}; 
					List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
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
				String displayName = userprofileModel.getDisplayName();;		
				String statusOfConsignment = String.valueOf(dataInsideList.getConsignmentStatus());
				String consignmentStatusName = dataInsideList.getStateInterp();
				String taxPaidStatus= String.valueOf(dataInsideList.getTaxPaidStatus());
				String taxPaidStatusName=dataInsideList.getTaxInterp();
				String userStatus = (String) session.getAttribute("userStatus");
				String quantity = String.valueOf(dataInsideList.getQuantity());
				String deviceQuantity=String.valueOf(dataInsideList.getDeviceQuantity());
				String action=iconState.adminState(dataInsideList.getFileName(), txnId, statusOfConsignment,userStatus,displayName);
				
				
				String[] finalData={createdOn,txnId,displayName,consignmentStatusName,taxPaidStatusName,quantity,deviceQuantity,action}; 
					List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
					finalList.add(finalDataList);
					datatableResponseModel.setData(finalList);
				}
			}else if("DRT".equals(userType)) {
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
				String quantity = String.valueOf(dataInsideList.getQuantity());
				String deviceQuantity=String.valueOf(dataInsideList.getDeviceQuantity());
				String action=iconState.consignmentDRTState(dataInsideList.getFileName(), txnId, statusOfConsignment,userStatus,displayName);
				
				
			
				String[] finalData={createdOn,txnId,displayName,consignmentStatusName,taxPaidStatusName,quantity,deviceQuantity,action}; 
					List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
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

	pageElement.setPageTitle(Translator.toLocale("view.consignment"));

	List<Button> buttonList = new ArrayList<>();
	List<InputFields> dropdownList = new ArrayList<>();
	List<InputFields> inputTypeDateList = new ArrayList<>();
	log.info("USER STATUS:::::::::"+userStatus);
	log.info("session value user Type=="+session.getAttribute("usertype"));
	log.info("sourceType in rendering $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" +sourceType);

	if("Importer".equals(userType) && "viaStolen".equals(sourceType)){
	String[] names= {"HeaderButton",Translator.toLocale("button.registerConsignment"),"./openRegisterConsignmentForm?reqType=formPage","btnLink","FilterButton", Translator.toLocale("button.filter"),"filterConsignment("+ConfigParameters.languageParam+")","submitFilter"};
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


	String[] footerBtn= {"FooterButton", Translator.toLocale("button.markAsStolen"),"markedstolen()","markedstolen","FooterButton", Translator.toLocale("button.cancel"),"redirectToViewPage()","cancel"};
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
	String[] selectParam= {"select",Translator.toLocale("select.consignmentStatus"),"filterConsignmentStatus","","select",Translator.toLocale("select.taxPaidStatus"),"taxPaidStatus",""};
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
	}else {
	String[] names= {"HeaderButton",Translator.toLocale("button.registerConsignment"),"./openRegisterConsignmentForm?reqType=formPage","btnLink","FilterButton",Translator.toLocale("button.filter"),"filterConsignment(window.parent.$('#langlist').val())","submitFilter"};
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
	String[] selectParam= {"select",Translator.toLocale("select.consignmentStatus"),"filterConsignmentStatus","","select",Translator.toLocale("select.taxPaidStatus"),"taxPaidStatus",""};
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

	}

	pageElement.setInputTypeDateList(inputTypeDateList);
	pageElement.setUserStatus(userStatus);
	return new ResponseEntity<>(pageElement, HttpStatus.OK); 
	}
	}
	





