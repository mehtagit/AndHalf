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
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.gl.ceir.pagination.model.StockContent;
import org.gl.ceir.pagination.model.StockPaginationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
@RestController
public class StockDatatableController {
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
	StockPaginationModel stockPaginationModel;
	@Autowired
	IconsState iconState;
	
	@PostMapping("stockData")
	public ResponseEntity<?> viewStockList(@RequestParam(name="type",defaultValue = "stock",required = false) String role ,HttpServletRequest request,HttpSession session) {	 		
		// Data set on this List
				List<List<String>> finalList=new ArrayList<List<String>>();

				//FilterRequest filterrequest = request.getParameter("FilterRequest");
				String filter = request.getParameter("filter");
				Gson gsonObject=new Gson();	
				FilterRequest filterrequest = gsonObject.fromJson(filter, FilterRequest.class);
				Integer pageSize = Integer.parseInt(request.getParameter("length"));
				Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;
				// TODO Convert header to an ENUM.
				// list provided via Back-end process
				try {
					
				Object response = feignCleintImplementation.stockFilter(filterrequest,pageNo,pageSize);
				Gson gson= new Gson(); 
				String apiResponse = gson.toJson(response);
				stockPaginationModel = gson.fromJson(apiResponse, StockPaginationModel.class);
				List<StockContent> paginationContentList = stockPaginationModel.getContent();
				log.info("-----after response-  paginationContentList------" + paginationContentList);
				if(paginationContentList.isEmpty()) {
					datatableResponseModel.setData(Collections.emptyList());
				}
				else {
				for(StockContent dataInsideList : paginationContentList) 
				{
					String date= dataInsideList.getCreatedOn(); 
					String txnId= dataInsideList.getTxnId(); 
					String file= dataInsideList.getFileName();
					// if API provide me consignmentStatusName
					String statusOfStock = String.valueOf(dataInsideList.getStockStatus());
					String stockStatus = null;
					stockStatus = statusOfStock.equals("0") ? "INIT" : 
						statusOfStock.equals("1") ? "Processing" :
							statusOfStock.equals("2")  ? "Error" :
								statusOfStock.equals("3") ?   "Success" : "Not Defined";
					String userStatus = (String) session.getAttribute("userStatus");
					String action = iconState.stockState(file,txnId,statusOfStock,userStatus);
					String[] finalData={date,txnId,file,stockStatus,action}; 
					List<String> finalDataList=new ArrayList<String>(Arrays.asList(finalData));
					finalList.add(finalDataList);
					datatableResponseModel.setData(finalList);
					}
				}
				//data set on ModelClass
				datatableResponseModel.setRecordsTotal(stockPaginationModel.getNumberOfElements());
				datatableResponseModel.setRecordsFiltered(stockPaginationModel.getTotalElements());
				log.info("------------datatableModel::::::::"+datatableResponseModel);
				return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
				
				}
				catch(Exception e) {
					datatableResponseModel.setRecordsTotal(null);
					datatableResponseModel.setRecordsFiltered(null);
					datatableResponseModel.setData(Collections.emptyList());
					return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
				}
	}
	
	
	
	
	@PostMapping
	@RequestMapping("stock/pageRendering")
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "stock",required = false) String role){

		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();

		String[] names= {"Upload Stock","./openUploadStock?reqType=formPage","btnLink", "filter","filter()","submitFilter"};

		for(int i=0; i< names.length ; i++) {
			button = new Button();
			button.setButtonTitle(names[i]);
			i++;
			button.setButtonURL(names[i]);
			i++;
			button.setId(names[i]);
			buttonList.add(button);
		}	
		
		//Dropdown items			
		String[] selectParam= {"select","Stock Status","filterFileStatus",""};
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
		pageElement.setPageTitle("View Stock");
		pageElement.setButtonList(buttonList);
		pageElement.setDropdownList(dropdownList);
		pageElement.setInputTypeDateList(inputTypeDateList);
		return new ResponseEntity<>(pageElement, HttpStatus.OK); 	
	}
}
