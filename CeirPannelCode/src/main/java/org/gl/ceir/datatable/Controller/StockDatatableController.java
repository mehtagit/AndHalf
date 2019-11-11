package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gl.ceir.CeirPannelCode.Feignclient.FeignCleintImplementation;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.Class.HeadersTitle.DatatableHeaderModel;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.HeadersTitle;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

@RequestMapping(value="/Stock")
@Controller
public class StockDatatableController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	@PostMapping
	@RequestMapping("/headers")
	public ResponseEntity<?> headers(@RequestParam(name="type",defaultValue = "consignment",required = false) String role){
		List<DatatableHeaderModel> dataTableInputs = new ArrayList<>();
		try {

			String[] headers = {HeadersTitle.date,HeadersTitle.transactionID,HeadersTitle.fileName,HeadersTitle.stockStatus,HeadersTitle.action};		
			for(String header : headers) {
				dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS); 
		}
	}
	
	
	
	@PostMapping
	@RequestMapping("/stockData") public ResponseEntity<?> viewStockList(@RequestBody FilterRequest filterrequest,@RequestParam(name="type",defaultValue = "stock",required = false) String role ,HttpServletRequest request) {	 		

		// Data set on this List
	//	List<List<String>> finalList=new ArrayList<List<String>>();
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;

		// TODO Convert header to an ENUM.
		// list provided via Back-end process
		try {				
		Object response = feignCleintImplementation.stockFilter(filterrequest,pageNo,pageSize);
		Gson gson= new Gson(); 
		String apiResponse = gson.toJson(response);
		StockPaginationModel contentResponse= gson.fromJson(apiResponse, StockPaginationModel.class);
		List<StockContent> paginationContentList = contentResponse.getContent();
		log.info("----------PAGINATION RESPONSE--------"+paginationContentList);
		datatableResponseModel.setRecordsTotal(null);
		datatableResponseModel.setRecordsFiltered(null);
		datatableResponseModel.setData(Collections.emptyList());
		return 	new ResponseEntity<>(datatableResponseModel, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			return 	new ResponseEntity<>(datatableResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
	}
	
	
	
	
	@PostMapping
	@RequestMapping("/pageRendering")
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "stock",required = false) String role){

		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();

		String[] names= {"Upload Stock","./CEIRLoginMerge/openUploadStock?reqType=formPage","btnLink", "filter","filter()","submitFilter"};

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
		String[] selectParam= {"select","Stock Status","filterFileStatus"};
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
