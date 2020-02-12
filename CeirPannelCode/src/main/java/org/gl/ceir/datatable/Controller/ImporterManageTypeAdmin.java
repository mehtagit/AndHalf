package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.GrievanceFeignClient;
import org.gl.ceir.CeirPannelCode.Model.AttachedFile;
import org.gl.ceir.CeirPannelCode.Model.TRCRequest;
import org.gl.ceir.Class.HeadersTitle.DatatableResponseModel;
import org.gl.ceir.Class.HeadersTitle.IconsState;
import org.gl.ceir.configuration.ConfigParameters;
import org.gl.ceir.configuration.Translator;
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
public class ImporterManageTypeAdmin {
	@Autowired
	Translator Translator;
	@Autowired
	GrievanceFeignClient grievanceFeignClient;
	@Autowired
	TrcContentModel TrcContentModel;
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
	

	
	@PostMapping("importerAdmintrc")
	public ResponseEntity<?> viewImporterAdmin(
			@RequestParam(name = "type", defaultValue = "consignment", required = false) String role,
			@RequestParam(name = "sourceType", required = false) String sourceType,
			@RequestParam(name = "file", defaultValue = "0", required = false) Integer file, HttpServletRequest request,
			HttpSession session, @RequestParam(name = "sessionFlag", required = false) Integer sessionFlag) {

		String userType = (String) session.getAttribute("usertype");
		log.info("userType in TRC----" + userType);

		List<List<Object>> finalList = new ArrayList<List<Object>>();
		String filter = request.getParameter("filter");

		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize;

		Object response = null;
		Gson gsonObject = new Gson();
		Gson gson = new Gson();
		TRCRequest filterrequest = gsonObject.fromJson(filter, TRCRequest.class);
		filterrequest.setSearchString(request.getParameter("search[value]"));
		log.info("--pageSize-" + pageSize + "----pageNo" + pageNo + "----file" + file + "-filterrequest-------"
				+ filterrequest);

		try {
			response = grievanceFeignClient.viewTRC(filterrequest, pageNo, pageSize, file);
			log.info("response after Feign----->" + response);
			String apiResponse = gson.toJson(response);
			trcPaginationModel = gson.fromJson(apiResponse, TrcPaginationModel.class);
			log.info("apiResponse after Feign----->" + apiResponse);
			if (trcPaginationModel.getContent().isEmpty()) {
				datatableResponseModel.setData(Collections.emptyList());
			} else {
				if("Importer".equals(userType)){
					log.info("--------in Importal Controller");
					for(TrcContentModel trcContentModelList :trcPaginationModel.getContent()) {
						String createdOn = trcContentModelList.getCreatedOn();
						String trademark = trcContentModelList.getTrademark();
						String productName = trcContentModelList.getProductNameInterp();
						String txnId= trcContentModelList.getTxnId();
						String modelNumber = trcContentModelList.getModelNumberInterp();
						String manufacturerCountry = trcContentModelList.getManufacturerCountry();
						String tac = trcContentModelList.getTac();
						/* String status = trcContentModelList.getStateInterp(); */
						String status = trcContentModelList.getStateInterp();
						String fileName1= trcContentModelList.getFileName();
						String approveState = String.valueOf(trcContentModelList.getApproveStatus());	
						log.info("status----->" +status+"--Id--------->"+trcContentModelList.getId()+"--fileName1------->"+fileName1+"--txnId------>"+txnId);
						String action = iconState.importalTrcManageIcons(approveState,trcContentModelList.getId(),fileName1,txnId);
						Object[] data = {createdOn,trademark,productName,txnId,modelNumber,manufacturerCountry,tac,status,action};
						List<Object> datatableList = Arrays.asList(data);
						finalList.add(datatableList);
						datatableResponseModel.setData(finalList);
					}
				}else {
					for (TrcContentModel trcContentModelList : trcPaginationModel.getContent()) {
						log.info("inside Trc File Name" + trcContentModelList.getAttachedFiles());
						String fileName1 = "";
						List<AttachedFile> list = trcContentModelList.getAttachedFiles();
						for (AttachedFile fileList : list) {
							fileName1 = fileList.getFileName();

						}

						String createdOn = trcContentModelList.getCreatedOn();
						String trademark = trcContentModelList.getTrademark();
						String manufacturerCountry = trcContentModelList.getManufacturerCountry();
						String tac = trcContentModelList.getTac();
						String status = trcContentModelList.getStateInterp();
						String approveState = String.valueOf(trcContentModelList.getApproveStatus());	
						String txnId = trcContentModelList.getTxnId();
						String adminApproveStatus = String.valueOf(trcContentModelList.getAdminApproveStatus());

						log.info("status----->" + status + "adminState-------->"+ approveState+"--Id--------->" + trcContentModelList.getId()
								+ "--fileName1------->" + fileName1 + "--txnId------>" + txnId);
						String action = iconState.trcAdminManageIcons(approveState, trcContentModelList.getId(), fileName1,
								txnId,adminApproveStatus);
						Object[] data = { createdOn, txnId, trademark, manufacturerCountry, tac, status, action };
						List<Object> datatableList = Arrays.asList(data);
						finalList.add(datatableList);
						datatableResponseModel.setData(finalList);
					}
				}
			}
			datatableResponseModel.setRecordsTotal(trcPaginationModel.getNumberOfElements());
			datatableResponseModel.setRecordsFiltered(trcPaginationModel.getTotalElements());
			log.info(":::::datatableResponseModel:::::" + datatableResponseModel);
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.OK);

		} catch (Exception e) {

			datatableResponseModel.setRecordsTotal(null);
			datatableResponseModel.setRecordsFiltered(null);
			datatableResponseModel.setData(Collections.emptyList());
			return new ResponseEntity<>(datatableResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("adminImprterTrc/pageRendering")
	public ResponseEntity<?> directives(@RequestParam(name="type",defaultValue = "TRC",required = false) String role,HttpSession session){

		String userType = (String) session.getAttribute("usertype");
		String userStatus = (String) session.getAttribute("userStatus");
		
		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;
		
		
		pageElement.setPageTitle(Translator.toLocale("sidebar.Manage_Type-Approved"));
		
		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
			
			log.info("USER STATUS:::::::::"+userStatus);
			log.info("session value user Type=="+session.getAttribute("usertype"));
			
			
		
			String[] names= {"HeaderButton",Translator.toLocale("table.ReportTypeApprovedDevices"),"./register-form-importer","btnLink","FilterButton",Translator.toLocale("button.filter"),"typeApprovedDataTable("+ConfigParameters.languageParam+")","submitFilter"};
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
				String[] selectParam= {"select",Translator.toLocale("table.status"),"Status",""};
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
				String[] dateParam= {"date",Translator.toLocale("input.startDate"),"startDate","","date",Translator.toLocale("input.endDate"),"endDate","","text",Translator.toLocale("input.transactionID"),"transactionID","","text",Translator.toLocale("table.TAC"),"tac",""};
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
