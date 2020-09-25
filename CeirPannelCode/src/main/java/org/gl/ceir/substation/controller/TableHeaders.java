package org.gl.ceir.substation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gl.ceir.Class.HeadersTitle.DatatableHeaderModel;
import org.gl.ceir.configuration.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableHeaders {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	Translator Translator;
	@PostMapping("headerName")
	public ResponseEntity<?> headers(@RequestParam(name="type",defaultValue = "default",required = false) String role){
		List<DatatableHeaderModel> dataTableInputs = new ArrayList<>();
		try {

			// CONSIGNMENT DATATABLE HEADERS			
			if("default".equals(role)) {
				
				String[] headers = {"table.unitID","table.distict","table.subStation","table.capacity","table.packetReceivedDate","table.ryVoltage","table.ybVoltage","table.brVoltage","table.rCurrent","table.yCurrent","table.bCurrent","table.rCap","table.yCap","table.bCap","table.totalPF","table.kva","table.kw","table.kvarInd","table.kvarCap","table.remark"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			else {
				String[] headers = {"table.date","table.transactionID","table.fileName","table.status","table.action"};    
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
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