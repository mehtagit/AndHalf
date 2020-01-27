package org.gl.ceir.Class.HeadersTitle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources({
	@PropertySource("classpath:messages.properties")
})

public class HeadersTitle {

	@Value("${table.creationDate}")
	public String creationDate;
	@Value("${table.transactionID}")
	public String  transactionID;
	@Value("${table.supplierName}")
	public String  supplierName;
	@Value("${table.consignmentStatus}")
	public String consignmentStatus;
	@Value("${table.taxPaidStatus}")
	public String taxPaidStatus;
	@Value("${table.action}")
	public String  action;

	//Stock 
	@Value("${table.date}")
	public   String date;
	@Value("${table.fileName}")
	public   String fileName;
	@Value("${table.stockStatus}")
	public   String stockStatus;
	@Value("${table.assignto}")
	public  String assignto;

	//Stolen
	public   String requestDate = "Request Date";
	public   String Status = "Status";
	public   String source = "Source";
	public   String requestType = "Request Type";

	//Custom

	public   String ImporterCompanyName = "Importer/CompanyName";
	//Admin
	public   String submissionDate = "Submission Date";
	public   String userId = "User ID";
	public   String roll = "Role Type";
	public   String asType = "As Type";
	public   String requestOn = "Requested On";
	public   String disaplyName = "Display Name";



	//pick consignments from stolen
	public   String blankHeader = "Select";

	//Grievance
	public   String raisedDate = "Raised Date";
	public   String lastUpdateDate = "Last Update Date";
	public   String grievanceId = "Grievance ID";
	public   String grievanceStatus = "Grievance Status";

	//Dashboard Notification Table
	public   String sNo = "S.No.";
	public   String feature = "Feature";
	public   String message = "Message";

	//TRC Headers
	public   String manufacturerName = "Manufacturer Name";
	public   String country = "Country";
	public   String tac = "TAC";
	public   String approveRejectionDate = "Approve/Rejection Date";
	public   String adminStatus = "CEIR Admin Status";
	public   String trcStatus = "TRC Status";

	//User Paid Status
	public   String deviceIDType= "Device ID Type";
	public   String deviceType= "Device Type";
	public   String price = "Price";
	public   String nid = "NID/Passport No.";


	//operator Grey/Black List
	public   String updatedOn= "Updated On";
	public   String fileType= "File Type";

	//Block/unblock
	public   String mode= "Mode";
	public   String operator= "Operator";

	//Admin Message Management
	public String parameterName= "Parameter Name";
	public  String description= "Description";
	public  String value= "Value";
	public  String channel= "Channel";

	//Admin Config Management
	public  String type="Type";
	public  String remarks="Remark";


	//Admin Config Management
	public  String period="Period";
	public  String policyOrder="Policy Order";
	
	//Lawful Stolen Headers
		public  String contact="Contact Number";
		public  String blockType="Block Type";

		//Audit Management
		public  String userName="User Name";
		public  String subFeature="Sub Feature";	

		  //ManageUserType 
		  public String registerDate="Register Date"; 
		  public String  passportNo="Passport Number"; 
		  public String name="Name"; 
		  public String nationality="Nationality"; 
		  public String visaExpDate="Visa Expiry Date";
		  public String localContact="Local Contact Number";
		 
}
