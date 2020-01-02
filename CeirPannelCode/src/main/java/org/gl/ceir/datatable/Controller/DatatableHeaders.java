package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gl.ceir.Class.HeadersTitle.DatatableHeaderModel;
import org.gl.ceir.Class.HeadersTitle.HeadersTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatatableHeaders {

	@Autowired
	HeadersTitle headersTitle;

	@PostMapping("headers")
	public ResponseEntity<?> headers(@RequestParam(name="type",defaultValue = "stock",required = false) String role){
		List<DatatableHeaderModel> dataTableInputs = new ArrayList<>();
		try {

			// CONSIGNMENT DATATABLE HEADERS			
			if("consignment".equals(role)) {
				String[] headers = {headersTitle.creationDate,headersTitle.transactionID,headersTitle.supplierName,headersTitle.consignmentStatus,headersTitle.taxPaidStatus,headersTitle.action};		
				System.out.println(":::::::::::::::headers:::::::::"+headers);
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}




			//STOLEN DATATABLE HEADERS
			else if("stolen".equals(role)) {
				String[] headers = {headersTitle.date,headersTitle.transactionID,headersTitle.fileName,headersTitle.Status,headersTitle.source,headersTitle.requestType,headersTitle.action };		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//CUSTOM DATATABLE HEADERS
			else if("customConsignment".equals(role)) {
				String[] headers = {headersTitle.creationDate,headersTitle.transactionID,headersTitle.ImporterCompanyName,headersTitle.consignmentStatus,headersTitle.taxPaidStatus,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//Admin DATATABLE HEADERS
			else if("adminConsignment".equals(role)) {
				String[] headers = {headersTitle.submissionDate,headersTitle.transactionID,headersTitle.ImporterCompanyName,headersTitle.consignmentStatus,headersTitle.taxPaidStatus,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//consignments from stolen headers
			else if("stolenconsignment".equals(role)) {
				String[] headers = {headersTitle.blankHeader,headersTitle.requestDate,headersTitle.transactionID,headersTitle.supplierName,headersTitle.consignmentStatus,headersTitle.taxPaidStatus};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//stock headers
			else if("stockcheckHeaders".equals(role)) {
				String[] headers = {headersTitle.blankHeader,headersTitle.requestDate,headersTitle.transactionID,headersTitle.fileName,headersTitle.stockStatus};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//stolen headers
			else if("stolenCheckHeaders".equals(role)) {
				String[] headers = {headersTitle.blankHeader,headersTitle.requestDate,headersTitle.transactionID,headersTitle.fileName,headersTitle.Status,headersTitle.source,headersTitle.requestType};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//Grievance Headers
			else if("grievanceHeaders".equals(role)) {
				String[] headers = {headersTitle.raisedDate,headersTitle.lastUpdateDate,headersTitle.transactionID,headersTitle.grievanceId,headersTitle.recentStatus,headersTitle.action};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//customStock Headers
			else if("customStockHeaders".equals(role)) {
				String[] headers = {headersTitle.date,headersTitle.assignto,headersTitle.transactionID,headersTitle.fileName,headersTitle.stockStatus,headersTitle.action};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//AdminStock Headers
			else if("adminStockHeaders".equals(role)) {
				String[] headers = {headersTitle.date,headersTitle.transactionID,headersTitle.disaplyName,headersTitle.roll,headersTitle.fileName,headersTitle.stockStatus,headersTitle.action};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//AdminRegistration Headers
			else if("adminRegistration".equals(role)) {
				String[] headers = {headersTitle.requestOn,headersTitle.disaplyName,headersTitle.asType,headersTitle.roll,headersTitle.Status,headersTitle.action};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//DashBoard dataTable Headers
			else if("dashboardNotification".equals(role)) {
				String[] headers = {headersTitle.sNo,headersTitle.date,headersTitle.transactionID,headersTitle.feature,headersTitle.message,headersTitle.action};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//TRC Manage Type dataTable Headers
			else if("trcManageType".equals(role)) {
				String[] headers = {headersTitle.requestDate,headersTitle.manufacturerName,headersTitle.country,headersTitle.tac,headersTitle.Status,headersTitle.approveRejectionDate,headersTitle.action};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//DEFAULT PORTION  
			else if("userPaidStatus".equals(role)) {
				String[] headers = {headersTitle.sNo,headersTitle.date,headersTitle.deviceIDType,headersTitle.deviceType,headersTitle.price,headersTitle.country,headersTitle.Status,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}



			//operator view
			else if("greyBlackList".equals(role)) {
				String[] headers = {headersTitle.updatedOn,headersTitle.fileName,headersTitle.fileType,headersTitle.action};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}

			//stockHeaders
			else if("stockHeaders".equals(role)) {
				String[] headers = {headersTitle.date,headersTitle.transactionID,headersTitle.fileName,headersTitle.stockStatus,headersTitle.action};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}


			//adminUserPaidStatus Headers 
			else if("adminUserPaidStatus".equals(role)) {
				String[] headers = {headersTitle.sNo,headersTitle.date,headersTitle.nid,headersTitle.deviceIDType,headersTitle.deviceType,headersTitle.price,headersTitle.country,headersTitle.taxPaidStatus,headersTitle.Status,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}

			//adminUserPaidStatus Headers 
			else if("blockUnblock".equals(role)) {
				String[] headers = {headersTitle.date,headersTitle.transactionID,headersTitle.requestType,headersTitle.mode,headersTitle.Status,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}

			//adminUserPaidStatus Headers 
			else if("blockUnblock".equals(role)) {
				String[] headers = {headersTitle.date,headersTitle.transactionID,headersTitle.requestType,headersTitle.mode,headersTitle.Status,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//adminSystemMessage Headers 
			else if("adminSystemMessage".equals(role)) {
				String[] headers = {headersTitle.date,headersTitle.parameterName,headersTitle.value,headersTitle.description,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//adminConfigMessage Headers 
			
			else if("adminConfigMessage".equals(role)) {
				String[] headers = {headersTitle.date,headersTitle.parameterName,headersTitle.value,headersTitle.description,headersTitle.type,headersTitle.remarks,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//DEFAULT PORTION  
			else {
				String[] headers = {headersTitle.date,headersTitle.transactionID,headersTitle.fileName,headersTitle.stockStatus,headersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.NOT_FOUND);
			}


		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS); 
		}


	}
}
