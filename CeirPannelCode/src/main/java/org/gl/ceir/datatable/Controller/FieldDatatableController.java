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
import org.gl.ceir.pagination.model.FieldContantModel;
import org.gl.ceir.pagination.model.FieldPaginationModel;
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
public class FieldDatatableController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	Translator Translator;
	@Autowired
	FeignCleintImplementation feignCleintImplementation;
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	@Autowired
	IconsState iconState;
	@Autowired
	FieldPaginationModel fieldPaginationModel;
	@Autowired
	FieldContantModel fieldContantModel;
	
	
	@PostMapping("fieldManagementData")
	 public ResponseEntity<?> viewFieldManagement(@RequestParam(name="type",defaultValue = "FieldManagement",required = false) String role,
	    @RequestParam(name="sourceType",required = false) String sourceType,HttpServletRequest request,HttpSession session) {
		List<List<Object>> finalList=new ArrayList<List<Object>>();
		String filter = request.getParameter("filter");
		Gson gsonObject=new Gson();
		FilterRequest filterrequest = gsonObject.fromJson(filter, FilterRequest.class);
		Integer file = 0;
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;
		filterrequest.setSearchString(request.getParameter("search[value]"));
		try {
			log.info("request send to the filter api ="+filterrequest);
			Object response = feignCleintImplementation.fieldManagementFeign(filterrequest, pageNo, pageSize, file);
			log.info("response in datatable"+response);
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			fieldPaginationModel = gson.fromJson(apiResponse, FieldPaginationModel.class);
			List<FieldContantModel> paginationContentList = fieldPaginationModel.getContent();
			if(paginationContentList.isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
			}else {
				for(FieldContantModel dataInsideList : paginationContentList) 
				{
					String displayName =dataInsideList.getDisplayName();
					//pageRendering(displayName, session);
				   log.info("displayName1---->" +displayName);
				   String tag = dataInsideList.getTag();
				   String interp = dataInsideList.getInterp();
				   String listOrder = String.valueOf(dataInsideList.getListOrder());
				   String tagId = dataInsideList.getTagId();
				  
				   String action=iconState.fieldManagementIcons(tag,interp,tagId);			   
				   Object[] finalData={displayName,interp,listOrder,tagId,action}; 
				   List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
				   finalList.add(finalDataList);
				   datatableResponseModel.setData(finalList);	
				}
			}
				//data set on ModelClass
				datatableResponseModel.setRecordsTotal(fieldPaginationModel.getNumberOfElements());
				datatableResponseModel.setRecordsFiltered(fieldPaginationModel.getTotalElements());
				return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		}catch(Exception e) {
			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			log.error(e.getMessage(),e);
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
	}
	
}

	
	
	@PostMapping("fieldManagement/pageRendering")
	public ResponseEntity<?> pageRendering(String displayName,HttpSession session){

	String userType = (String) session.getAttribute("usertype");
	String userStatus = (String) session.getAttribute("userStatus");

	InputFields inputFields = new InputFields();
	InputFields dateRelatedFields;

	pageElement.setPageTitle(Translator.toLocale("view.Field"));

	List<Button> buttonList = new ArrayList<>();
	List<InputFields> dropdownList = new ArrayList<>();
	List<InputFields> inputTypeDateList = new ArrayList<>();
	log.info("USER STATUS:::::::::"+userStatus);
	log.info("session value user Type=="+session.getAttribute("usertype"));



	String[] names= {"HeaderButton",Translator.toLocale("button.addTag"),"AddField()","btnLink","FilterButton", Translator.toLocale("button.filter"),"filterFieldTable("+ConfigParameters.languageParam+")","submitFilter"};
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

		/*
		 * //Dropdown items String[] selectParam=
		 * {"select",Translator.toLocale("select.consignmentStatus"),
		 * "filterConsignmentStatus","","select",Translator.toLocale(
		 * "select.taxPaidStatus"),"taxPaidStatus",""}; for(int i=0; i<
		 * selectParam.length; i++) { inputFields= new InputFields();
		 * inputFields.setType(selectParam[i]); i++;
		 * inputFields.setTitle(selectParam[i]); i++; inputFields.setId(selectParam[i]);
		 * i++; inputFields.setClassName(selectParam[i]); dropdownList.add(inputFields);
		 * } pageElement.setDropdownList(dropdownList);
		 */

	//input type date list	
	String[] dateParam= {"date",Translator.toLocale("input.startDate"),"startDate","","date",Translator.toLocale("input.endDate"),"endDate",""};
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