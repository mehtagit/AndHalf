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
			@RequestParam(name = "type", defaultValue = "stolen", required = false) String role,@RequestParam(name="sourceType",required = false) String sourceType,
			HttpServletRequest request, HttpSession session) {
		List<List<String>> finalList = new ArrayList<List<String>>();

		// FilterRequest filterrequest = request.getParameter("FilterRequest");
		//FilterRequest filterrequest = new FilterRequest();
		String filter = request.getParameter("filter");
		Gson gsonObject=new Gson();	
		FilterRequest filterrequest = gsonObject.fromJson(filter, FilterRequest.class);
		log.info("flter request=="+filterrequest);
		Integer pageSize = Integer.parseInt(request.getParameter("length"));
		Integer pageNo = Integer.parseInt(request.getParameter("start")) / pageSize;

		// TODO Convert header to an ENUM.
		// list provided via Back-end process
		try {

			Object response = feignCleintImplementation.stolenFilter(filterrequest, pageNo, pageSize);
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
						String source =dataInsideList.getSourceType();
						log.info("source type message="+source);
						String requestType = dataInsideList.getRequestType();
						int id = dataInsideList.getId();
						String[] finalData = {checboxes,createdOn,txnId,fileName, stolenStatusName,source, requestType};
						List<String> finalDataList = new ArrayList<String>(Arrays.asList(finalData));
						finalList.add(finalDataList);
						datatableResponseModel.setData(finalList);
					}
				}else {
					log.info("viaExistingRecovery");
					for (StolenContent dataInsideList : paginationContentList) {
						String createdOn = dataInsideList.getCreatedOn();
						String txnId = dataInsideList.getTxnId();
						String fileName = dataInsideList.getFileName();
						String statusOfStolen = String.valueOf(dataInsideList.getFileStatus());
						String stolenStatusName = dataInsideList.getStateInterp();
						String source =dataInsideList.getSourceType();
						log.info("source type message="+source);
						String requestType = dataInsideList.getRequestType();
						int id = dataInsideList.getId();
						String userStatus = (String) session.getAttribute("userStatus");
						String action = iconState.stolenState(dataInsideList.getFileName(), dataInsideList.getTxnId(),
								statusOfStolen, userStatus,requestType,id);
						String[] finalData = { createdOn,txnId,fileName, stolenStatusName,source, requestType, action };
						List<String> finalDataList = new ArrayList<String>(Arrays.asList(finalData));
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

		pageElement.setPageTitle("Stolen/Recovery");

		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
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
				"Source", "sourceStatus", "","select", "Request Type", "requestType","" };	
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
			String[] footerBtn = {"FooterButton", "Mark As Recovered","markedRecovered()","markedRecovered","FooterButton", "Cancel","cancel()","cancel"};
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
		pageElement.setInputTypeDateList(inputTypeDateList);
		pageElement.setUserStatus(userStatus);
		return new ResponseEntity<>(pageElement, HttpStatus.OK);
	}
}
