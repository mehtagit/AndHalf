package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Util.UtilDownload;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
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
	
	/*
	 * @Autowired ConsignmentService consignmentService;
	 */

	private final Logger log = LoggerFactory.getLogger(getClass());

	@PostMapping("consignmentData")
	 public ResponseEntity<?> viewConsignmentList(@RequestParam(name="type",defaultValue = "consignment",required = false) String role ,HttpServletRequest request) {	 		

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
		log.info("-----after response-  paginationContentList------" + paginationContentList);
		for(ConsignmentContent dataInsideList : paginationContentList) 
		{
			String createdOn= dataInsideList.getCreatedOn();
			String txnId=	dataInsideList.getTxnId(); 
			String supplierName= dataInsideList.getSupplierName(); 
			// if API provide me consignmentStatusName
			String statusOfConsignment = String.valueOf(dataInsideList.getConsignmentStatus());
			String consignmentStatus = null;
			consignmentStatus = statusOfConsignment.equals("0") ? "Uploading" : 
				statusOfConsignment.equals("1") ? "Success" :
					statusOfConsignment.equals("2")  ? "Processing" :
						statusOfConsignment.equals("3") ?   "Error" : "Not Defined";

			String taxPaidStatus= dataInsideList.getTaxPaidStatus();
			//Icon classes
			String errorIcon="\"fa fa-exclamation-circle\"";
			String downloadIcon="\"fa fa-download\""; 
			String viewIcon="\"fa fa-eye teal-text\"";
			String editIcon="\"fa fa-pencil\""; 
			String deletionIcon="\"fa fa-trash\"";
			String replyIcon="\"fa-reply\""; 
			// URL link 
			String emptyURL="JavaScript:void(0);"; 
			String downloadURL = "./dowloadFiles/actual/"+dataInsideList.getFileName()+"/"+dataInsideList.getTxnId()+"";
			String errorURL = "./dowloadFiles/eror/"+dataInsideList.getFileName()+"/"+dataInsideList.getTxnId()+"";			
			String viewAction="viewConsignmentDetails('"+dataInsideList.getTxnId()+"')"; 
			String editAction="EditConsignmentDetails('"+dataInsideList.getTxnId()+"')";
			String deleteAction ="DeleteConsignmentRecord('"+dataInsideList.getTxnId()+"')";
			// icon title  
			String errorIconTitle="Error-File";
			String downloadIconTitle="Download"; 
			String viewIconTitle="View"; 
			String editIconTitle="Edit"; 
			String deleteIconTitle="Delete"; 
			String replyIconTitle="Reply";

			// state related Code 
			String error="<a href="+errorURL+"><i class="+errorIcon+" aria-hidden=\"true\" title="
					+errorIconTitle+" style=\"color: red; font-size:20px; margin-right:15px;\"></i></a>";
			String download="<a href="+downloadURL+" download=\"download\"><i class="
							+downloadIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color:#2e8b57\" title="
							+downloadIconTitle+" download=\"download\"></i></a>"; 
			String view="<a onclick="+viewAction+"><i class="+viewIcon+" aria-hidden=\"true\" title="
									+viewIconTitle+" style=\"font-size: 20px; margin:0 0 0 15px;\"></i></a>";
			String edit="<a onclick="+editAction+"><i class="
									+editIcon+" aria-hidden=\"true\" style=\"font-size: 20px; margin:0 15px 0 15px; color: #006994\" title="
									+editIconTitle+"></i></a>"; 
			String delete="<a onclick="+deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
											+deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
											+deleteIconTitle+"></i></a>"; 
			String reply="<a href="+emptyURL+" class=\"waves-effect waves-light modal-trigger\"><i class="
													+deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
													+replyIconTitle+"></i></a>";

				/*
				 * if("0".equals(statusOfConsignment)) {
				 * error="<a href="+errorURL+" class="+className+"><i  class="
				 * +errorIcon+" aria-hidden=\"true\" title="
				 * +errorIconTitle+" style=\"color: red; font-size:20px; margin-right:15px;\" ></i></a>"
				 * ; download="<a href="+downloadURL+" class="
				 * +className+" download=\"download\"><i class="
				 * +downloadIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color:#2e8b57\" title="
				 * +downloadIconTitle+" download=\"download\" ></i></a>";
				 * 
				 * edit="<a onclick="+editAction+" class="+className+"><i class="
				 * +editIcon+" aria-hidden=\"true\" style=\"font-size: 20px; margin:0 15px 0 15px; color: #006994\" title="
				 * +editIconTitle+"></i></a>"; } else if("1".equals(statusOfConsignment)) {
				 * delete="<a class="+className+" onclick="
				 * +deleteAction+" class=\"waves-effect waves-light modal-trigger\"><i class="
				 * +deletionIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
				 * +deleteIconTitle+"></i></a>";
				 * view="<a class="+className+" onclick="+viewAction+"><i class="
				 * +viewIcon+" aria-hidden=\"true\" title="
				 * +viewIconTitle+" style=\"font-size: 20px; margin:0 0 0 15px;\"></i></a>"; }
				 * else if("2".equals(statusOfConsignment)) {
				 * view="<a class="+className+" onclick="+viewAction+"><i class="
				 * +viewIcon+" aria-hidden=\"true\" title="
				 * +viewIconTitle+" style=\"font-size: 20px; margin:0 0 0 15px;\" ></i></a>";
				 * reply="<a class="+className+" href="
				 * +emptyURL+" class=\"waves-effect waves-light modal-trigger\"><i class="
				 * +replyIcon+" aria-hidden=\"true\" style=\"font-size: 20px; color: red;\" title="
				 * +replyIconTitle+"></i></a>"; }
				 */

				String action=error.concat(download).concat(view).concat(edit).concat(delete);
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
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "consignment",required = false) String role){
		log.info("USER ROLE ON THIS "+role);
		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		pageElement.setPageTitle("Consignment");
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();

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

		return new ResponseEntity<>(pageElement, HttpStatus.OK); 	
	}

}




