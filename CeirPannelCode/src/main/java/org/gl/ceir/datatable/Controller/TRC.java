package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.GrievanceFeignClient;
import org.gl.ceir.CeirPannelCode.Model.TRCRequest;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.interfacepackage.CRUD;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@CrossOrigin
public class TRC implements CRUD{

	@Autowired
	GrievanceFeignClient grievanceFeignClient;	
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	TrcPaginationModel trcPaginationModel;
	@Autowired
	IconsState iconState;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@PostMapping("trc")
	@Override
	public ResponseEntity<?> view(@RequestParam(name="type",defaultValue = "consignment",required = false) String role ,
			 @RequestParam(name="sourceType",required = false) String sourceType,
			 @RequestParam(name = "file", defaultValue = "0", required = false) Integer file,
			 HttpServletRequest request,HttpSession session,
			 @RequestParam(name="sessionFlag",
			 required = false) Integer sessionFlag) {
		// TODO Auto-generated method stub
		List<List<Object>> finalList = new ArrayList<List<Object>>();
		String filter = request.getParameter("filter");
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize;
		
		Object response = null;
		Gson gsonObject=new Gson();
		Gson gson=new Gson();
		TRCRequest filterrequest = gsonObject.fromJson(filter, TRCRequest.class);
		filterrequest.setSearchString(request.getParameter("search[value]"));
		log.info("--pageSize-"+pageSize+"----pageNo"+pageNo+"----file"+file+"-filterrequest-------"+filterrequest);
		try {
			response =grievanceFeignClient.viewTRC(filterrequest, pageNo, pageSize, file);
			String apiResponse = gson.toJson(response);
			trcPaginationModel = gson.fromJson(apiResponse, TrcPaginationModel.class);

			if(trcPaginationModel.getContent().isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
			}
			else {
				
				for(TrcContentModel trcContentModelList :trcPaginationModel.getContent()) {
					String requestedDate = trcContentModelList.getRequestDate();
					String manufacturerName = trcContentModelList.getManufacturerName();
					String country = trcContentModelList.getCountry();
					String tac = trcContentModelList.getTac();
					Integer status = trcContentModelList.getStatus();
					String statusInterp = trcContentModelList.getStateInterp();
					String approveRejectionDate = trcContentModelList.getApproveDisapproveDate();
					String action = iconState.trcManageIcons(status,trcContentModelList.getId());
					
					Object[] data = {requestedDate,manufacturerName,country,tac,statusInterp,approveRejectionDate,action};
					
					List<Object> datatableList = Arrays.asList(data);
					finalList.add(datatableList);
					datatableResponseModel.setData(finalList);
				}
		}
			datatableResponseModel.setRecordsTotal(trcPaginationModel.getNumberOfElements());
			datatableResponseModel.setRecordsFiltered(trcPaginationModel.getTotalElements());
			log.info(":::::datatableResponseModel:::::"+datatableResponseModel);
			return new ResponseEntity<>(datatableResponseModel,HttpStatus.OK);	
		}
		catch(Exception e) {
			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	
	
	@PostMapping("TRC/pageRendering")
	public ResponseEntity<?> directives(@RequestParam(name="type",defaultValue = "TRC",required = false) String role,HttpSession session){

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
			
			String[] names= {"HeaderButton","Report Type-Approved Devices","./register-form","btnLink","FilterButton", "filter","typeApprovedDataTable()","submitFilter"};
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