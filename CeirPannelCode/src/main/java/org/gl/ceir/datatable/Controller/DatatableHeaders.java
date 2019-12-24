package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gl.ceir.Class.HeadersTitle.DatatableHeaderModel;
import org.gl.ceir.Class.HeadersTitle.HeadersTitle;
import org.gl.ceir.interfacepackage.HeaderInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatatableHeaders implements HeaderInterface{

	@PostMapping("headers")
	public ResponseEntity<?> headers(@RequestParam(name="type",defaultValue = "stock",required = false) String role){
		List<DatatableHeaderModel> dataTableInputs = new ArrayList<>();
		try {



			// CONSIGNMENT DATATABLE HEADERS			
			if("consignment".equals(role)) {
				String[] headers = {HeadersTitle.creationDate,HeadersTitle.transactionID,HeadersTitle.supplierName,HeadersTitle.consignmentStatus,HeadersTitle.taxPaidStatus,HeadersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}




			//STOLEN DATATABLE HEADERS
			else if("stolen".equals(role)) {
				String[] headers = {HeadersTitle.date,HeadersTitle.transactionID,HeadersTitle.fileName,HeadersTitle.Status,HeadersTitle.source,HeadersTitle.requestType,HeadersTitle.action };		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//CUSTOM DATATABLE HEADERS
			else if("customConsignment".equals(role)) {
				String[] headers = {HeadersTitle.creationDate,HeadersTitle.transactionID,HeadersTitle.ImporterCompanyName,HeadersTitle.consignmentStatus,HeadersTitle.taxPaidStatus,HeadersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//Admin DATATABLE HEADERS
			else if("adminConsignment".equals(role)) {
				String[] headers = {HeadersTitle.submissionDate,HeadersTitle.transactionID,HeadersTitle.ImporterCompanyName,HeadersTitle.consignmentStatus,HeadersTitle.taxPaidStatus,HeadersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//consignments from stolen headers
			else if("stolenconsignment".equals(role)) {
				String[] headers = {HeadersTitle.blankHeader,HeadersTitle.requestDate,HeadersTitle.transactionID,HeadersTitle.supplierName,HeadersTitle.consignmentStatus,HeadersTitle.taxPaidStatus};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//stock headers
			else if("stockcheckHeaders".equals(role)) {
				String[] headers = {HeadersTitle.blankHeader,HeadersTitle.requestDate,HeadersTitle.transactionID,HeadersTitle.fileName,HeadersTitle.stockStatus};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//stolen headers
			else if("stolenCheckHeaders".equals(role)) {
				String[] headers = {HeadersTitle.blankHeader,HeadersTitle.requestDate,HeadersTitle.transactionID,HeadersTitle.fileName,HeadersTitle.Status,HeadersTitle.source,HeadersTitle.requestType};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//Grievance Headers
			else if("grievanceHeaders".equals(role)) {
			String[] headers = {HeadersTitle.raisedDate,HeadersTitle.lastUpdateDate,HeadersTitle.transactionID,HeadersTitle.grievanceId,HeadersTitle.recentStatus,HeadersTitle.action};	
			for(String header : headers) {
			dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}
			
			
			//customStock Headers
			else if("customStockHeaders".equals(role)) {
			String[] headers = {HeadersTitle.date,HeadersTitle.assignto,HeadersTitle.transactionID,HeadersTitle.fileName,HeadersTitle.stockStatus,HeadersTitle.action};	
			for(String header : headers) {
			dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}
			
			
			//AdminStock Headers
			else if("adminStockHeaders".equals(role)) {
			String[] headers = {HeadersTitle.date,HeadersTitle.transactionID,HeadersTitle.userId,HeadersTitle.roll,HeadersTitle.fileName,HeadersTitle.stockStatus,HeadersTitle.action};	
			for(String header : headers) {
			dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}
			
			//AdminRegistration Headers
			else if("adminRegistration".equals(role)) {
			String[] headers = {HeadersTitle.requestOn,HeadersTitle.disaplyName,HeadersTitle.asType,HeadersTitle.roll,HeadersTitle.Status,HeadersTitle.action};	
			for(String header : headers) {
			dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}
			
			//DashBoard dataTable Headers
			else if("dashboardNotification".equals(role)) {
			String[] headers = {HeadersTitle.sNo,HeadersTitle.date,HeadersTitle.transactionID,HeadersTitle.feature,HeadersTitle.message,HeadersTitle.action};	
			for(String header : headers) {
			dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}
			
			//TRC Manage Type dataTable Headers
			else if("trcManageType".equals(role)) {
			String[] headers = {HeadersTitle.requestDate,HeadersTitle.manufacturerName,HeadersTitle.country,HeadersTitle.tac,HeadersTitle.Status,HeadersTitle.approveRejectionDate,HeadersTitle.action};
			for(String header : headers) {
			dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//DEFAULT PORTION  
			else if("userPaidStatus".equals(role)) {
				String[] headers = {HeadersTitle.sNo,HeadersTitle.date,HeadersTitle.deviceIDType,HeadersTitle.deviceType,HeadersTitle.price,HeadersTitle.country,HeadersTitle.Status,HeadersTitle.action};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(header));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}

			

			//operator view
			else if("greyBlackList".equals(role)) {
			String[] headers = {HeadersTitle.updatedOn,HeadersTitle.fileName,HeadersTitle.fileType,HeadersTitle.action};
			for(String header : headers) {
			dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//stockHeaders
			else if("stockHeaders".equals(role)) {
			String[] headers = {HeadersTitle.date,HeadersTitle.transactionID,HeadersTitle.fileName,HeadersTitle.stockStatus,HeadersTitle.action};
			for(String header : headers) {
			dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//DEFAULT PORTION  
			else {
				String[] headers = {HeadersTitle.date,HeadersTitle.transactionID,HeadersTitle.fileName,HeadersTitle.stockStatus,HeadersTitle.action};		
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
