package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.TypeApprovedFeignImpl;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.constants.ApproveTypeStatus;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.gl.ceir.pagination.model.TrcContentModel;
import org.gl.ceir.pagination.model.TrcPaginationModel;
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
public class ManageTypeApproveDatatableController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	String className = "emptyClass";
	
	@Autowired
	TypeApprovedFeignImpl typeApprovedFeignImpl;
	@Autowired
	TrcPaginationModel trcPaginationModel;
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	IconsState iconState;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	
	@PostMapping("typeApprovedData")
	public ResponseEntity<?> viewManageTypeRecord(@RequestParam(name="type",defaultValue = "registration",required = false) String role, HttpServletRequest request,HttpSession session) {
		
		String userType = (String) session.getAttribute("usertype");
		int userId=	(int) session.getAttribute("userid");
		
		log.info("session value user Type admin ManageType Controller=="+session.getAttribute("usertype"));
		// Data set on this List
		List<List<String>> finalList=new ArrayList<List<String>>();
		FilterRequest filterrequest = new FilterRequest();
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize ;
		log.info("pageSize"+pageSize+"-----------pageNo---"+pageNo);
		try {
			log.info("request send to the filter api ="+filterrequest);
			Object response = typeApprovedFeignImpl.manageTypeFeign(filterrequest, pageNo, pageSize);
			log.info("response in datatable"+response);
			Gson gson= new Gson(); 
			String apiResponse = gson.toJson(response);
			trcPaginationModel = gson.fromJson(apiResponse, TrcPaginationModel.class);
			List<TrcContentModel> paginationContentList = trcPaginationModel.getContent();
			if(paginationContentList.isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
				
			}
			else {
			
				for(TrcContentModel dataInsideList : paginationContentList) 
				{
				   String createdOn = (String) dataInsideList.getCreatedOn();
				   String manufacturerName =   dataInsideList.getManufacturerName();
				   String country = dataInsideList.getCountry();
				   String tac =  dataInsideList.getTac();
				   String status = ApproveTypeStatus.getUserStatusByCode(dataInsideList.getStatus()).getDescription();
				   String approveDisapproveDate = dataInsideList.getApproveDisapproveDate();
				   String userStatus = (String) session.getAttribute("userStatus");
				   String action=iconState.trcManageIcons(userStatus);		   
				   String[] finalData={createdOn,manufacturerName,country,tac,status,approveDisapproveDate,action}; 
				   List<String> finalDataList=new ArrayList<String>(Arrays.asList(finalData));
					finalList.add(finalDataList);
					datatableResponseModel.setData(finalList);	
					
			}
		}
			//data set on ModelClass
			datatableResponseModel.setRecordsTotal(trcPaginationModel.getNumberOfElements());
			datatableResponseModel.setRecordsFiltered(trcPaginationModel.getTotalElements());
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		}catch(Exception e) {
			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			log.error(e.getMessage(),e);
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
		}
	}
	
	
	
	@PostMapping("TRC/pageRendering")
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "TRC",required = false) String role,HttpSession session){

		String userType = (String) session.getAttribute("usertype");
		String userStatus = (String) session.getAttribute("userStatus");
		
		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		pageElement.setPageTitle("Manage Type-Approved Devices");
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
			
			log.info("USER STATUS:::::::::"+userStatus);
			log.info("session value user Type=="+session.getAttribute("usertype"));
			
			String[] names= {"HeaderButton","Report Type-Approved Devices","./openReportTypeForm?reqType=formPage","btnLink","FilterButton", "filter","typeApprovedDataTable()","submitFilter"};
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
			String[] selectParam= {"select","Status ","Status",""};
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
			String[] dateParam= {"date","Start date","startDate","","date","End date","endDate","","text","TAC","tac",""};
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
