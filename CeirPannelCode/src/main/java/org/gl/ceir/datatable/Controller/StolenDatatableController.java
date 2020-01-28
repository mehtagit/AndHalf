package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.ActionModel;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.configuration.Translator;
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.gl.ceir.pagination.model.StolenContent;
import org.gl.ceir.pagination.model.StolenPaginationModel;
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
public class StolenDatatableController {

	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	UtilDownload utildownload;
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	StolenPaginationModel stolenPaginationModel;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	@Autowired
	IconsState iconState;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@PostMapping("stolenData")
	public ResponseEntity<?> viewStolenList(
			@RequestParam(name = "type", defaultValue = "stolen", required = false) String role,
			@RequestParam(name="sourceType",required = false) String sourceType,
			@RequestParam(name="featureId",required = false) Integer featureId,
			@RequestParam(name="userTypeId",required = false) Integer userTypeId,
			HttpServletRequest request, HttpSession session) {
		List<List<Object>> finalList = new ArrayList<List<Object>>();
		
		log.info("featureId------->"+featureId+"---userTypeId------>"+userTypeId);
		
		log.info("session value user Type=="+session.getAttribute("usertype"));
		String userType = (String) session.getAttribute("usertype");
		// FilterRequest filterrequest = request.getParameter("FilterRequest");
		//FilterRequest filterrequest = new FilterRequest();
		String filter = request.getParameter("filter");
		Gson gsonObject=new Gson();	
		Integer exportFile=0;
		FilterRequest filterrequest = gsonObject.fromJson(filter, FilterRequest.class);
		log.info("flter request=="+filterrequest);
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize;

		// TODO Convert header to an ENUM.
		// list provided via Back-end process
		try {
			Object response = feignCleintImplementation.stolenFilter(filterrequest, pageNo, pageSize,exportFile);
			log.info("response::::::::::::"+response);
			Gson gson = new Gson();
			String apiResponse = gson.toJson(response);
			stolenPaginationModel = gson.fromJson(apiResponse, StolenPaginationModel.class);
			List<StolenContent> paginationContentList = stolenPaginationModel.getContent();
			
			if(paginationContentList.isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
			}
			else {
				if("viaExistingRecovery".equals(sourceType)) {
					log.info("viaExistingRecovery");
					for (StolenContent dataInsideList : paginationContentList) {
						String checboxes = "<input type=checkbox class=filled-in>";
						String createdOn = dataInsideList.getCreatedOn();
						String txnId = dataInsideList.getTxnId();
						String fileName = dataInsideList.getFileName();
						// for future purpose statusOfStolen
						String statusOfStolen = String.valueOf(dataInsideList.getFileStatus());
						String stolenStatusName = dataInsideList.getStateInterp();
						String source =dataInsideList.getSourceTypeInterp();
						log.info("source type message="+source);
						String requestType = dataInsideList.getRequestType(); 
						String requestTypeName = dataInsideList.getRequestTypeInterp();
						int id = dataInsideList.getId();
						Object[] finalData = {checboxes,createdOn,txnId,fileName, stolenStatusName,source, requestTypeName};
						List<Object> finalDataList = new ArrayList<Object>(Arrays.asList(finalData));
						finalList.add(finalDataList);
						datatableResponseModel.setData(finalList);
					}
				}else if("Operator".equals(userType)) {
					log.info("in Opertator Controler-----" +userType);
					List<ActionModel> actionResponse = feignCleintImplementation.tableActionFeign(featureId,userTypeId);
					Gson JsonObj = new Gson();
					log.info("actionResponse CEIRAdmin::::::::::::"+JsonObj.toJson(actionResponse));
				
					for (StolenContent dataInsideList : paginationContentList) {
						String createdOn = dataInsideList.getCreatedOn();
						String txnId = dataInsideList.getTxnId();
						String operator = dataInsideList.getOperatorTypeIdInterp();
						String fileName = dataInsideList.getRequestType();
						String statusOfStolen = String.valueOf(dataInsideList.getFileStatus());
						String stolenStatusName = dataInsideList.getStateInterp();
						String source =dataInsideList.getSourceTypeInterp();
						String requestType = dataInsideList.getRequestType(); 
						String requestTypeName = dataInsideList.getRequestTypeInterp();
						int id = dataInsideList.getId();
						String userStatus = (String) session.getAttribute("userStatus");
						String action = iconState.blockUnblockState(actionResponse,dataInsideList.getFileName(), dataInsideList.getTxnId(),
								statusOfStolen, userStatus,requestType,id,dataInsideList.getQty(),dataInsideList.getSourceType());
						Object[] finalData = {createdOn,txnId,requestTypeName,source,stolenStatusName,action};
						List<Object> finalDataList = new ArrayList<Object>(Arrays.asList(finalData));
						finalList.add(finalDataList);
						datatableResponseModel.setData(finalList);
					}
			
				}else if("CEIRAdmin".equals(userType)) {
					log.info("in CEIRAdmin Controler-----" +userType);
					List<ActionModel> actionResponse = feignCleintImplementation.tableActionFeign(featureId,userTypeId);
					log.info("actionResponse CEIRAdmin::::::::::::"+actionResponse);
					
					for (StolenContent dataInsideList : paginationContentList) {
						String createdOn = dataInsideList.getCreatedOn();
						String txnId = dataInsideList.getTxnId();
						String operator = dataInsideList.getOperatorTypeIdInterp();
						String fileName = dataInsideList.getRequestType();
						String statusOfStolen = String.valueOf(dataInsideList.getFileStatus());
						String stolenStatusName = dataInsideList.getStateInterp();
						String source =dataInsideList.getSourceTypeInterp();
						String requestType = dataInsideList.getRequestType(); 
						String requestTypeName = dataInsideList.getRequestTypeInterp();
						int id = dataInsideList.getId();
						String userStatus = (String) session.getAttribute("userStatus");
						String action = iconState.adminBlockUnblock(actionResponse,dataInsideList.getFileName(), dataInsideList.getTxnId(),
								statusOfStolen, userStatus,requestType,id,dataInsideList.getQty(),dataInsideList.getSourceType());
						Object[] finalData = {createdOn,txnId,operator,requestTypeName,source,stolenStatusName,action};
						List<Object> finalDataList = new ArrayList<Object>(Arrays.asList(finalData));
						finalList.add(finalDataList);
						datatableResponseModel.setData(finalList);
					}
				}else if(userType.equals("Lawful Agency")) {
					log.info("in Lawful Agency-----" +userType);
					for (StolenContent dataInsideList : paginationContentList) {
						String createdOn = dataInsideList.getCreatedOn();
						String txnId = dataInsideList.getTxnId();
						String BlockType = dataInsideList.getBlockingType();
						String requestType = dataInsideList.getRequestTypeInterp();
						String requestTypeValue = dataInsideList.getRequestType();
						String mode= dataInsideList.getSourceTypeInterp();
						String stolenStatusName = dataInsideList.getStateInterp();
						String statusOfStolen = String.valueOf(dataInsideList.getFileStatus());
						int id = dataInsideList.getId();
						String userStatus = (String) session.getAttribute("userStatus");
						String action = iconState.StolenlawfulAgency(dataInsideList.getFileName(), dataInsideList.getTxnId(),
								statusOfStolen, userStatus,requestType,id,dataInsideList.getQty(),dataInsideList.getSourceType(),requestTypeValue);
						Object[] finalData = {createdOn,txnId,BlockType,requestType,mode,stolenStatusName,action};
						List<Object> finalDataList = new ArrayList<Object>(Arrays.asList(finalData));
						finalList.add(finalDataList);
						datatableResponseModel.setData(finalList);
					}
				}
				else {
					log.info("viaExistingRecovery");
					for (StolenContent dataInsideList : paginationContentList) {
						String createdOn = dataInsideList.getCreatedOn();
						String txnId = dataInsideList.getTxnId();
						String fileName = dataInsideList.getFileName();
						String statusOfStolen = String.valueOf(dataInsideList.getFileStatus());
						String stolenStatusName = dataInsideList.getStateInterp();
						String source =dataInsideList.getSourceTypeInterp();
						log.info("source type message="+source);
						String requestType = dataInsideList.getRequestType(); 
						String requestTypeName = dataInsideList.getRequestTypeInterp();
						int id = dataInsideList.getId();
						String userStatus = (String) session.getAttribute("userStatus");
						String action = iconState.stolenState(dataInsideList.getFileName(), dataInsideList.getTxnId(),
								statusOfStolen, userStatus,requestType,id,dataInsideList.getQty());
						Object[] finalData = { createdOn,txnId,fileName, stolenStatusName,source, requestTypeName, action };
						List<Object> finalDataList = new ArrayList<Object>(Arrays.asList(finalData));
						finalList.add(finalDataList);
						datatableResponseModel.setData(finalList);
					}

				}
			}

			// data set on ModelClass
			datatableResponseModel.setRecordsTotal(stolenPaginationModel.getNumberOfElements());
			datatableResponseModel.setRecordsFiltered(stolenPaginationModel.getTotalElements());
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK);

		} catch (Exception e) {
			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			log.error(e.getMessage(),e);
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK);
		}

	}

	@PostMapping("stolen/pageRendering")
	public ResponseEntity<?> pageRendering(
			@RequestParam(name = "type", defaultValue = "consignment", required = false) String role,@RequestParam(name="sourceType",required = false) String sourceType,
			HttpSession session) {
		String userStatus = (String) session.getAttribute("userStatus");
		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		log.info("session value user Type=="+session.getAttribute("usertype"));
		String userType = (String) session.getAttribute("usertype");
		
		
		if("Operator".equals(userType) ||"CEIRAdmin".equals(userType)) {
		pageElement.setPageTitle("Block/Unblock Devices");
		}else {
			pageElement.setPageTitle("Stolen/Recovery");
		}
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
		
		//This Block is for Operator & Admin Upper Filter/Button Forms------------------------------------------------
		
		if("Operator".equals(userType) || "CEIRAdmin".equals(userType)) {
			String[] names = { "HeaderButton", "Report Block/Unblock", "./selectblockUnblockPage",
					"btnLink", "FilterButton", "filter", "filterStolen()", "submitFilter" };
			for (int i = 0; i < names.length; i++) {
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
			
			//input type date list	
			String[] dateParam = { "date", Translator.toLocale("input.startDate"), "startDate", "", "date",Translator.toLocale("input.endDate"), "endDate", ""};
			for (int i = 0; i < dateParam.length; i++) {
				dateRelatedFields = new InputFields();
				dateRelatedFields.setType(dateParam[i]);
				i++;
				dateRelatedFields.setTitle(dateParam[i]);
				i++;
				dateRelatedFields.setId(dateParam[i]);
				i++;
				dateRelatedFields.setClassName(dateParam[i]);
				inputTypeDateList.add(dateRelatedFields);
			}
			
		//This is for Operator Dropdown for CEIRadmin
			if("CEIRAdmin".equals(userType)){
				String[] selectParam = { "select", "Operator", "operator", "","select",Translator.toLocale("table.requestType"), "requestType", "", "select",
						Translator.toLocale("input.mode"), "sourceStatus", "","select",Translator.toLocale("table.status"), "status","" };	
				for (int i = 0; i < selectParam.length; i++) {
					inputFields = new InputFields();
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
				
			}else {
				String[] selectParam = { "select",Translator.toLocale("table.requestType"), "requestType", "", "select",
					Translator.toLocale("input.mode"), "sourceStatus", "","select", Translator.toLocale("table.status"), "status","" };	
				for (int i = 0; i < selectParam.length; i++) {
					inputFields = new InputFields();
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
			}
			
	
		
		}else {
			
			//This Block is for all other Stolen Upper Filter/Button Forms------------------------------------------------
			
			String[] names = { "HeaderButton", "Report Stolen/Recovery", "openStolenRecoveryModal()",
					"btnLink", "FilterButton", "filter", "filterStolen()", "submitFilter" };
			for (int i = 0; i < names.length; i++) {
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
			
			//input type date list	
			String[] dateParam = { "date", "Start date", "startDate", "", "date", "End date", "endDate", "","text","Transaction ID","transactionID",""};
			for (int i = 0; i < dateParam.length; i++) {
				dateRelatedFields = new InputFields();
				dateRelatedFields.setType(dateParam[i]);
				i++;
				dateRelatedFields.setTitle(dateParam[i]);
				i++;
				dateRelatedFields.setId(dateParam[i]);
				i++;
				dateRelatedFields.setClassName(dateParam[i]);
				inputTypeDateList.add(dateRelatedFields);
			}
			
		

			String[] selectParam = { "select", "Status", "status", "", "select",
					"Mode", "sourceStatus", "","select", "Request Type", "requestType","" };	
			for (int i = 0; i < selectParam.length; i++) {
				inputFields = new InputFields();
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
			
			if("viaExistingRecovery".equals(sourceType)) {
				//log.info("if sourceType in stolen Render controller 1--------"+sourceType);
				String[] footerBtn = {"FooterButton", "Mark As Recovered","markedRecovered()","markedRecovered","FooterButton", "Cancel","redirectToViewStolenPage()","cancel"};
				for (int i = 0; i < footerBtn.length; i++) {
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
			}
		}
		
	
		pageElement.setInputTypeDateList(inputTypeDateList);
		pageElement.setUserStatus(userStatus);
		return new ResponseEntity<>(pageElement, HttpStatus.OK);
	}
}
