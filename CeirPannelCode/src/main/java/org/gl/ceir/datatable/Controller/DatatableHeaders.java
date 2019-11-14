package org.gl.ceir.datatable.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gl.ceir.Class.HeadersTitle.DatatableHeaderModel;
import org.gl.ceir.Class.HeadersTitle.HeadersTitle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatatableHeaders {
	
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



//DEFAULT PORTION  
else {
			String[] headers = {HeadersTitle.date,HeadersTitle.transactionID,HeadersTitle.fileName,HeadersTitle.stockStatus,HeadersTitle.action};		
			for(String header : headers) {
				dataTableInputs.add(new DatatableHeaderModel(header));
			}
			return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
}
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS); 
		}
		
		
	}
	
	
	
	
	
}
