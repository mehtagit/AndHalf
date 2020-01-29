package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.GrievanceFeignClient;
import org.gl.ceir.CeirPannelCode.Model.FilterRequest;
import org.gl.ceir.CeirPannelCode.Model.constants.UserStatus;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.configuration.Translator;
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.gl.ceir.pagination.model.OperatorContentModel;
import org.gl.ceir.pagination.model.OperatorPaginationModel;
import org.gl.ceir.pagination.model.RegistrationContentModel;
import org.gl.ceir.pagination.model.RegistrationPaginationModel;
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
public class operatorDatatableController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	String className = "emptyClass";
	@Autowired
	DatatableResponseModel datatableResponseModel;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	@Autowired
	OperatorContentModel operatorContentModel;
	@Autowired
	OperatorPaginationModel operatorPaginationModel;
	@Autowired
	GrievanceFeignClient grievanceFeignClient;
	@Autowired
	IconsState iconState;
	
	
	@PostMapping("operatorData")
	public ResponseEntity<?> viewOperatorRecord(@RequestParam(name="type",defaultValue = "Operator",required = false) String role, HttpServletRequest request,HttpSession session) {
		String userType = (String) session.getAttribute("usertype");
		int userId=	(int) session.getAttribute("userid");

		log.info("session value Operator Controller=="+session.getAttribute("usertype"));
		
		// Data set on this List
				List<List<Object>> finalList=new ArrayList<List<Object>>();
				String filter = request.getParameter("filter");
				Gson gsonObject=new Gson();
				FilterRequest filterrequest = gsonObject.fromJson(filter, FilterRequest.class);
				
				Integer pageSize = Integer.parseInt(request.getParameter("length"));
				Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize;
				filterrequest.setSearchString(request.getParameter("search[value]"));
				Integer file = 0;
				log.info("pageSize"+pageSize+"-----------pageNo---"+pageNo);
				try {
					log.info("request send to the filter api ="+filterrequest);
					Object response = grievanceFeignClient.viewOperatorFeign(filterrequest,pageNo,pageSize,file);
					log.info("response in datatable"+response);
					Gson gson= new Gson(); 
					String apiResponse = gson.toJson(response);
					
					operatorPaginationModel = gson.fromJson(apiResponse, OperatorPaginationModel.class);
					List<OperatorContentModel> paginationContentList = operatorPaginationModel.getContent();
				
					if(paginationContentList.isEmpty()) {
						datatableResponseModel.setData(Collections.emptyList());
					}else {
						
						for(OperatorContentModel dataInsideList : paginationContentList) 
						{
						   String createdOn = dataInsideList.getCreatedOn();
						   String fileName =   dataInsideList.getFileName();
						   String fileTypeInterp = dataInsideList.getFileTypeInterp();
						   String userStatus = (String) session.getAttribute("userStatus");
						   //log.info("----Id------"+Id+"-------id----------------"+id+"---userName-----"+username);
						   String action=iconState.greyBlackIcon(userStatus,fileName);			   
						   Object[] finalData={createdOn,fileName,fileTypeInterp,action}; 
							List<Object> finalDataList=new ArrayList<Object>(Arrays.asList(finalData));
							finalList.add(finalDataList);
							datatableResponseModel.setData(finalList);	
					}
				}
					//data set on ModelClass
					datatableResponseModel.setRecordsTotal(operatorPaginationModel.getNumberOfElements());
					datatableResponseModel.setRecordsFiltered(operatorPaginationModel.getTotalElements());
					return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
			}catch(Exception e) {
					datatableResponseModel.setRecordsTotal(null);
					datatableResponseModel.setRecordsFiltered(null);
					datatableResponseModel.setData(Collections.emptyList());
					log.error(e.getMessage(),e);
					return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK); 
				}
			}
	
			
	
	
	@PostMapping("operator/pageRendering")
	public ResponseEntity<?> pageRendering(@RequestParam(name="type",defaultValue = "operator",required = false) String role,
			@RequestParam(name="featureType",required = false) String featureType,HttpSession session){
		
		String userType = (String) session.getAttribute("usertype");
		String userStatus = (String) session.getAttribute("userStatus");
		
		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		if("greyList".equals(featureType)) {
		pageElement.setPageTitle("Grey List");	
		}else if("blackList".equals(featureType)) {
		pageElement.setPageTitle("Black List");
		}
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
			
			log.info("USER STATUS:::::::::"+userStatus);
			log.info("session value user Type=="+session.getAttribute("usertype"));
			
			String[] names= {"FilterButton", "filter","operatorDatatable()","submitFilter"};
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
			String[] selectParam= {"select",Translator.toLocale("input.filetype"),"fileType",""};
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
