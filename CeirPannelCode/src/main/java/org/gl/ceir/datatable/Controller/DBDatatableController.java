package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gl.ceir.CeirPannelCode.Feignclient.DBTablesFeignClient;
import org.gl.ceir.CeirPannelCode.Model.DBrowDataModel;
import org.gl.ceir.CeirPannelCode.Model.DbListDataHeaders;
import org.gl.ceir.CeirPannelCode.Model.MapDatatableResponse;
import org.gl.ceir.Class.HeadersTitle.DatatableHeaderModel;
import org.gl.ceir.configuration.ConfigParameters;
import org.gl.ceir.configuration.Translator;
import org.gl.ceir.pageElement.model.Button;
import org.gl.ceir.pageElement.model.InputFields;
import org.gl.ceir.pageElement.model.PageElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class DBDatatableController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	Translator Translator;
	@Autowired
	PageElement pageElement;
	@Autowired
	Button button;
	@Autowired
	DBTablesFeignClient dBTablesFeignClient;
	@Autowired
	DBrowDataModel dBrowDataModel;

	@PostMapping("dbManagementData")
	public ResponseEntity<?> viewdbTable(HttpServletRequest request, HttpSession session) {
		List<List<Object>> finalList = new ArrayList<List<Object>>();
		List<List<String>> mul = new ArrayList<List<String>>();
		String filter = request.getParameter("filter");
		MapDatatableResponse map = new MapDatatableResponse();
		Gson gsonObject = new Gson();
		DBrowDataModel filterrequest = gsonObject.fromJson(filter, DBrowDataModel.class);
		try {
			log.info("request passed to API:::::::::" + filter);
			Object response = dBTablesFeignClient.historyConsignmentFeign(filterrequest);
			Gson gson = new Gson();
			String apiResponse = gson.toJson(response);
			log.info("apiResponse ::::::::::::::" + apiResponse);
			DBrowDataModel dBrowDataModel = gson.fromJson(apiResponse, DBrowDataModel.class);
			log.info("response::::::" + dBrowDataModel);

			List<String> columnList = dBrowDataModel.getColumns();
			List<Map<String, String>> rowData = dBrowDataModel.getRowData();
			List<DbListDataHeaders> dataTableInputs = new ArrayList<>();

			if (columnList.isEmpty()) {
				dBrowDataModel.setColumns(Collections.emptyList());
			} else {
				List<String> list = dBrowDataModel.getColumns();
				ListIterator<String> iterator = list.listIterator();
				String columnName = null;
				while (iterator.hasNext()) {
					columnName = iterator.next();
					dataTableInputs.add(new DbListDataHeaders(columnName, columnName));
				}
				
				
				
				
				/*
				 * for (Map<String, String> dataInsideList : rowData) { //List<HashMap<String,
				 * String>> list2 = dBrowDataModel.getRowData();
				 * //log.info("list2 ::::::::::::::::::" +list2); for (String column :
				 * columnList) {
				 * 
				 * 
				 * }
				 * 
				 * 
				 * 
				 * log.info("map response ::::::::::::" +map);
				 * 
				 * 
				 * 
				 * Object[] finalData = {list,list1}; List<Object> finalDataList = new
				 * ArrayList<Object>(Arrays.asList(finalData)); finalList.add(finalDataList);
				 * 
				 * log.info("list ::::::::::::" +list); log.info("list1 ::::::::::::" +   list1);
				 * 
				 * Object[] headers = {list}; log.info("headers--->" +headers);
				 * 
				 * }
				 */

				map.setColumns(dataTableInputs);
				map.setData(rowData);

			}
			return new ResponseEntity<>(map, HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			dBrowDataModel.setColumns(Collections.emptyList());
			return new ResponseEntity<>(HttpStatus.OK);

		}
	}

	private Object row(Map<String, String> column) {
		// TODO Auto-generated method stub
		return null;
	}

	@PostMapping("dbTable/pageRendering")
	public ResponseEntity<?> pageRendering(String displayName, HttpSession session) {

		String userType = (String) session.getAttribute("usertype");
		String userStatus = (String) session.getAttribute("userStatus");

		InputFields inputFields = new InputFields();
		InputFields dateRelatedFields;

		pageElement.setPageTitle(Translator.toLocale("sidebar.DB_Tables"));

		List<Button> buttonList = new ArrayList<>();
		List<InputFields> dropdownList = new ArrayList<>();
		List<InputFields> inputTypeDateList = new ArrayList<>();
		log.info("USER STATUS:::::::::" + userStatus);
		log.info("session value user Type==" + session.getAttribute("usertype"));

		String[] names = { "HeaderButton", Translator.toLocale("button.addTag"), "AddField()", "btnLink",
				"FilterButton", Translator.toLocale("button.filter"),
				"Datatable(" + ConfigParameters.languageParam + ")", "submitFilter" };
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

		/*
		 * //Dropdown items String[]
		 * selectParam={"select",Translator.toLocale("select.filterTagId"),"filterTagId"
		 * ,""}; for(int i=0; i<selectParam.length; i++) { inputFields= new
		 * InputFields(); inputFields.setType(selectParam[i]); i++;
		 * inputFields.setTitle(selectParam[i]); i++; inputFields.setId(selectParam[i]);
		 * i++; inputFields.setClassName(selectParam[i]); dropdownList.add(inputFields);
		 * } pageElement.setDropdownList(dropdownList);
		 */

		// input type date list
		String[] dateParam = { "date", Translator.toLocale("input.startDate"), "startDate", "", "date",
				Translator.toLocale("input.endDate"), "endDate", "" };
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

		pageElement.setInputTypeDateList(inputTypeDateList);
		pageElement.setUserStatus(userStatus);
		return new ResponseEntity<>(pageElement, HttpStatus.OK);
	}

}
