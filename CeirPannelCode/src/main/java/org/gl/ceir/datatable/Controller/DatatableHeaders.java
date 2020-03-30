package org.gl.ceir.datatable.Controller;

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
public class DatatableHeaders {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	Translator Translator;
	@PostMapping("headers")
	public ResponseEntity<?> headers(@RequestParam(name="type",defaultValue = "stock",required = false) String role){
		List<DatatableHeaderModel> dataTableInputs = new ArrayList<>();
		try {

			// CONSIGNMENT DATATABLE HEADERS			
			if("consignment".equals(role)) {
				String[] headers = {"table.creationDate","table.transactionID","table.suppliername","table.consignmentStatus","table.taxPaidStatus","table.quantity","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}




			//STOLEN DATATABLE HEADERS
			else if("stolen".equals(role)) {
				String[] headers = {"table.date","table.transactionID","table.fileName","table.status","table.source","table.requestType","table.quantity","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//CUSTOM DATATABLE HEADERS
			else if("customConsignment".equals(role)) {
				String[] headers = {"table.creationDate","table.transactionID","table.importerCompanyName","table.consignmentStatus","table.taxPaidStatus","table.quantity","table.action"};		
				for(String header : headers) {
					log.info("Translator.toLocale(header)----"+Translator.toLocale(header));
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//Admin DATATABLE HEADERS
			else if("adminConsignment".equals(role)) {
				String[] headers = {"table.submissiondate","table.transactionID","table.importerCompanyName","table.consignmentStatus","table.taxPaidStatus","table.quantity","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//consignments from stolen headers
			else if("stolenconsignment".equals(role)) {
				String[] headers = {"table.blankheader","table.requestdate","table.transactionID","table.suppliername","table.consignmentStatus","table.taxPaidStatus","table.quantity"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//stock headers
			else if("stockcheckHeaders".equals(role)) {
				String[] headers = {"table.blankheader","table.requestdate","table.transactionID","table.fileName","table.stockStatus","table.quantity"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//stockHeaders
			else if("stockHeaders".equals(role)) {
				String[] headers = {"table.date","table.transactionID","table.fileName","table.stockStatus","table.quantity","table.action"};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}

			//customStock Headers
			else if("customStockHeaders".equals(role)) {
				String[] headers = {"table.date","table.assignto","table.transactionID","table.fileName","table.stockStatus","table.quantity","table.action"};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//AdminStock Headers
			else if("adminStockHeaders".equals(role)) {
				String[] headers = {"table.date","table.transactionID","table.displayName","table.roleType","table.fileName","table.stockStatus","table.quantity","table.action"};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}
			
			
			
			//stolen headers
			else if("stolenCheckHeaders".equals(role)) {
				String[] headers = {"table.blankheader","table.requestdate","table.transactionID","table.fileName","table.status","table.source","table.requestType"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			//Grievance Headers
			else if("grievanceHeaders".equals(role)) {
				
				String[] headers = {"table.raiseddate","table.lastupdatedate","table.transactionID","table.grievanceID","table.grievancestatus","table.action"};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}


			

			//AdminRegistration Headers
			else if("adminRegistration".equals(role)) {
				String[] headers = {"table.RequestedOn","table.lastupdatedate","table.displayName","table.AsType","table.userType","table.status","table.action"};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//DashBoard dataTable Headers
			else if("dashboardNotification".equals(role)) {
				String[] headers = {"table.date","table.transactionID","table.feature","table.message","table.action"};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//TRC Manage Type dataTable Headers
			else if("trcManageType".equals(role)) {
				String[] headers = {"table.creationDate","table.transactionID","table.requestdate","table.ManufacturerName","table.country","table.TAC","table.status","table.Approve/RejectionDate","table.action"};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}

			//DEFAULT PORTION  
			else if("userPaidStatus".equals(role)) {
				String[] headers = {"table.date","table.nid","table.transactionID","table.country","table.status","table.origin","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}



			//operator view
			else if("greyBlackList".equals(role)) {
				String[] headers = {"table.updatedOn","table.fileName","table.fileType","table.action"};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}

			
			//adminUserPaidStatus Headers 
			else if("adminUserPaidStatus".equals(role)) {

				String[] headers = {"table.date","table.nid","table.transactionID","table.country","table.taxPaidStatus","table.origin","table.status","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			

			//adminUserPaidStatus Headers 
			else if("blockUnblock".equals(role)) {
				String[] headers = {"table.date","table.transactionID","table.requestType","input.mode","table.status","table.quantity","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//adminSystemMessage Headers 
			else if("adminSystemMessage".equals(role)) {
				String[] headers = {"table.creationDate","table.lastupdatedate","table.Description","table.Value","table.Channel","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//adminConfigMessage Headers 
			
			else if("adminConfigMessage".equals(role)) {
				String[] headers = {"table.creationDate","table.lastupdatedate","table.Description","table.Value","table.Type","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			
			//adminPolicyManagement Headers 
			
			else if("adminPolicyManagement".equals(role)) {
				String[] headers = {"table.creationDate","table.lastupdatedate","table.Description","table.Value","table.Period","table.status","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//AdmintrcManageType Headers 
			
			else if("AdmintrcManageType".equals(role)) {
				String[] headers = {"table.creationDate","table.transactionID","table.requestdate","table.ManufacturerName","table.country","table.TAC","table.status","table.Approve/RejectionDate","table.CEIRAdminStatus","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
//BlockUnblockCEIRAdmin Headers 
			
			else if("BlockUnblockCEIRAdmin".equals(role)) {
				String[] headers = {"table.date","table.transactionID","table.Operator","table.requestType","table.Mode","table.status","table.quantity","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
		
			
//lawfulStolenHeaders Headers 
			
			else if("lawfulStolenHeaders".equals(role)) {
				String[] headers = {"table.requestdate","table.transactionID","table.BlockType","table.requestType","table.Mode","table.status","table.quantity","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
//auditManagement Headers 
			
			else if("auditManagement".equals(role)) {
				String[] headers = {"table.creationDate","table.transactionID","table.UserName","table.userType","table.feature","table.SubFeature","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
//ManageUserType Headers 
			
			else if("ManageUserType".equals(role)) {
				String[] headers = {"table.RegisterDate","table.transactionID","input.nidInput","table.Nationality","table.LocalContactNumber","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			//ManageUserType Headers 
			
			else if("ImporterTrcManageType".equals(role)) {
				String[] headers = {"table.creationDate","table.Trademark","table.ProductName","table.transactionID","table.ModelNumber","table.country","table.TAC","table.status","table.action"};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//deviceActivation Headers 
			
			else if("deviceActivation".equals(role)) {
				String[] headers = {"table.RegisterDate","table.transactionID","table.PassportNumber","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			
			//AdminImprtertrcManageType Headers 
			
			else if("AdminImportertrcManageType".equals(role)) {
				String[] headers = {"table.creationDate","table.transactionID","table.userType","table.displayName","table.Trademark","table.country","table.TAC","table.status","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			
			//AssigneeStock
			
			else if("AssigneeStock".equals(role)) {
				String[] headers = {"tabel.Assignee","tabel.AssigneeName","table.Contact","table.email","table.action"};		
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			
//fieldManagement
			
			else if("fieldManagement".equals(role)) {
				String[] headers = {"tabel.field","table.displayName","tabel.fieldId","table.Description","table.action"};			
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			
			//Port Management
			
			else if("portManagement".equals(role)) {
				String[] headers = {"table.creationDate","table.port","table.address","table.action"};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//Currency Management
			
			else if("currencyHeaders".equals(role)) {
				String[] headers = {"table.creationDate","table.month","table.currency","table.cambodian","table.doller","table.action"};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//customer Care DashBoard dataTable Headers
			else if("ccdashboardNotification".equals(role)) {
				String[] headers = {"table.date","table.transactionID","table.feature","table.message","table.action"};	
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);	
			}
			
			//user Management
			
			else if("userManagementHeaders".equals(role)) {
				String[] headers = {"table.creationDate","table.lastupdatedate","table.userType","table.status","table.action"};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}
			
			//user Feature Management
			
			else if("userFeatureHeaders".equals(role)) {
				String[] headers = {"table.creationDate","table.lastupdatedate","table.userType","table.feature","table.period","table.action"};
				for(String header : headers) {
					dataTableInputs.add(new DatatableHeaderModel(Translator.toLocale(header)));
				}
				return new ResponseEntity<>(dataTableInputs, HttpStatus.OK);
			}

			
			//DEFAULT PORTION  
			else {
				String[] headers = {"table.date","table.transactionID","table.fileName","table.stockStatus","table.action"};		
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
