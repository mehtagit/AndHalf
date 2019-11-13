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
	/*
	 * @Autowired ConsignmentService consignmentService;
	 */

	private final Logger log = LoggerFactory.getLogger(getClass());

	@PostMapping("consignmentData")
	 public ResponseEntity<?> viewConsignmentList(@RequestParam(name="type",defaultValue = "consignment",required = false) String role ,HttpServletRequest request,HttpSession session) {	 		

		// Data set on this List
		List<List<String>> finalList=new ArrayList<List<String>>();

		//FilterRequest filterrequest = request.getParameter("FilterRequest"); 
		FilterRequest filterrequest = new FilterRequest();

		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;

		// TODO Convert header to an ENUM.
		// list provided via Back-end process
		try {
		
		Object response = feignCleintImplementation.consignmentFilter(filterrequest,pageNo,pageSize);
		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		consignmentPaginationModel = gson.fromJson(apiResponse, ConsignmentPaginationModel.class);
		List<ConsignmentContent> paginationContentList = consignmentPaginationModel.getContent();
		for(ConsignmentContent dataInsideList : paginationContentList) 
		{
			String createdOn= dataInsideList.getCreatedOn();
			String txnId=	dataInsideList.getTxnId(); 
			String supplierName= dataInsideList.getSupplierName(); 
			// if API provide me consignmentStatusName
			String statusOfConsignment = String.valueOf(dataInsideList.getConsignmentStatus());
			String consignmentStatus = null;
			consignmentStatus = statusOfConsignment.equals("0") ? "INIT" : 
				statusOfConsignment.equals("1") ? "Processing" :
					statusOfConsignment.equals("2")  ? "Rejected By System" :
						statusOfConsignment.equals("3") ?   "Pending Approval from CEIR Authority" : 
							statusOfConsignment.equals("4") ?   "Rejected By CEIR Authority" :
								statusOfConsignment.equals("5") ?   "Pending Approvals from Customs" :
									statusOfConsignment.equals("6") ?   "Approved" : 
										statusOfConsignment.equals("7") ?   "Rejected by Customs" :
											statusOfConsignment.equals("8") ?   "Withdrawn by Importer" : "Withdrawn by CEIR";
			String taxPaidStatus= dataInsideList.getTaxPaidStatus();
			String userStatus = (String) session.getAttribute("userStatus");
			String action=iconState.state(dataInsideList.getFileName(), dataInsideList.getTxnId(), statusOfConsignment,userStatus);
			String[] finalData={createdOn,txnId,supplierName,consignmentStatus,taxPaidStatus,action}; 
				List<String> finalDataList=new ArrayList<String>(Arrays.asList(finalData));
				finalList.add(finalDataList);
				datatableResponseModel.setData(finalList);
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
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		}
		
	}


	
	@PostMapping("consignment/pageRendering")
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "consignment",required = false) String role,HttpSession session){
		String userStatus = (String) session.getAttribute("userStatus");
		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		pageElement.setPageTitle("Consignment");
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
		if("Disable".equals(userStatus)) {
			log.info("CAN NOT REGISTER USER BCOZ:::::::::"+userStatus);
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
		}
		else {
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
			}
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
		String[] dateParam= {"date","Start date","startDate","","date","End date","endDate",""};
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




