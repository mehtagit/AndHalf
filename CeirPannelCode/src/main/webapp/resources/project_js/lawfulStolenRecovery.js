var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-stolenselected-roleType");  
var role = currentRoleType == null ? roleType : currentRoleType;
var userType = $("body").attr("data-roleType");
var featureId="5"; 
var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';

$.i18n().locale = lang;	

$.i18n().load( {
	'en': './resources/i18n/en.json',
	'km': './resources/i18n/km.json'
} ).done( function() { 
	rejectedMsg=$.i18n('rejectedMsg');
	consignmentApproved=$.i18n('consignmentApproved');
	errorMsg=$.i18n('errorMsg');
	havingTxnID=$.i18n('havingTxnID');
	updateMsg=$.i18n('updateMsg');
	hasBeenUpdated=$.i18n('hasBeenUpdated');
	consignmentDeleted=$.i18n('consignmentDeleted');
	deleteInProgress=$.i18n('deleteInProgress');
});




$(document).ready(function(){
	$('div#initialloader').fadeIn('fast');
	filterStolen();
	pageRendering();
});

$('.datepicker').on('mousedown',function(event){
	event.preventDefault();
});

populateCountries
(   
		"country"
);

var userType = $("body").attr("data-roleType");
var sourceType = localStorage.getItem("sourceType");

function filterStolen(){
	var filterRequest={
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"txnId":$('#transactionID').val(),
			"consignmentStatus":parseInt($('#status').val()),
			"requestType":parseInt($('#requestType').val()),
			"sourceType":parseInt($('#sourceStatus').val()),
			//"roleType": role,
			"userId": userId,
			"featureId":featureId,
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"userType":$("body").attr("data-roleType"),
	}

	$.ajax({
		url: './headers?type=lawfulStolenHeaders',
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#stolenLibraryTable").DataTable({
				bAutoWidth: false,
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering": false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				scrollCollapse: true,	
				ajax: {
					url: 'stolenData',
					type: 'POST',
					data : function(d) {
						d.filter =JSON.stringify(filterRequest); 
						console.log(JSON.stringify(filterRequest));
					}
				},
				"columns": result,
				fixedColumns: true,
				columnDefs: [
		            { width: 180, targets: result.length - 1 }
		        ]
			});
			$('div#initialloader').delay(300).fadeOut('slow');
		}
	}); 
}				

function pageRendering(){
	$.ajax({
		url: './stolen/pageRendering',
		type: 'POST',
		dataType: "json",
		success: function(data){
			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';
			$("#pageHeader").append(elem);
			var button=data.buttonList;

			var date=data.inputTypeDateList;
			for(i=0; i<date.length; i++){
				if(date[i].type === "date"){	
					$("#consignmentTableDIv").append("<div class='input-field col s6 m2'>"+
							"<div id='enddatepicker' class='input-group'>"+
							"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<label for="+date[i].id+">"+date[i].title
							+"</label>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");

				}else if(date[i].type === "text"){
					$("#consignmentTableDIv").append("<div class='input-field col s6 m2' ><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for="+date[i].id+" class='center-align'>"+date[i].title+"</label></div>");
				}
			} 

			// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#consignmentTableDIv").append("<div class='col s6 m2 selectDropdwn'>"+
							
							"<div class='select-wrapper select2  initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select2 initialized'>"+
							"<option>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}

				$("#consignmentTableDIv").append("<div class=' col s3 m2 l1'><button type='button' class='btn primary botton' id='submitFilter'/></div>");
				$("#consignmentTableDIv").append("<div class='col s12 m4'><a href='JavaScript:void(0);' onclick='exportStolenRecoveryData()'  class='export-to-excel right'>"+$.i18n('Export')+" <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");
				for(i=0; i<button.length; i++){
					$('#'+button[i].id).text(button[i].buttonTitle);
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}
			
			$('.datepicker').datepicker({
			    dateFormat: "yy-mm-dd"
			    });
		}

	
	}); 

	setAllDropdowns();
	if(userType=="CEIRAdmin"){
		$("#btnLink").css({display: "none"});
		}

}	 

function setAllDropdowns(){
	
	
	//Request Type status-----------dropdown
$.getJSON('./getTypeDropdownList/REQ_TYPE/'+$("body").attr("data-userTypeID")+'', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#requestType');
		}
	});

	//Source Type-----------dropdown
$.getJSON('./getSourceTypeDropdown/SOURCE_TYPE/'+featureId+'', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#sourceStatus');
		}
	});


	//Stolen Status-----------dropdown
	$.getJSON('./getDropdownList/'+featureId+'/'+$("body").attr("data-userTypeID"), function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].state).text(data[i].interp)
			.appendTo('#status'); 
		}
	});
	
	
}

function openStolenRecoveryModal(){
	console.log("openStolenRecoveryModal===");
	$('#stoleRecoveryModal').openModal();
}

//**********************************************************Export Excel file************************************************************************
function exportStolenRecoveryData()
{
	var stolenRecoveryStartDate=$('#startDate').val();
	var stolenRecoveryEndDate=$('#endDate').val();
	var stolenRecoveryTxnId=$('#transactionID').val();
	var stolenRecoveryFileStatus=parseInt($('#status').val());
	var stolenRecoverySourceStatus=parseInt($('#sourceStatus').val());
	var stolenRecoveryRequestType=parseInt($('#requestType').val());

	
	var roleType = $("body").attr("data-roleType");
	var currentRoleType = $("body").attr("data-stolenselected-roleType");
	
	var role = currentRoleType == null ? roleType : currentRoleType;
	console.log("roleType=="+roleType+" currentRoleType="+currentRoleType+" role="+role);
    
	console.log("stolenRecoveryFileStatus=="+stolenRecoveryFileStatus+" stolenRecoverySourceStatus=="+stolenRecoverySourceStatus+" stolenRecoveryRequestType="+stolenRecoveryRequestType)
      if(isNaN(stolenRecoveryFileStatus) && isNaN(stolenRecoverySourceStatus) && isNaN(stolenRecoveryRequestType))
	   {
    	  stolenRecoveryFileStatus='';
    	  stolenRecoverySourceStatus='';
    	  stolenRecoveryRequestType='';
    	  console.log(" 11111111stolenRecoveryFileStatus && stolenRecoverySourceStatus && stolenRecoveryRequestType is empty =="+stolenRecoveryFileStatus+stolenRecoverySourceStatus);
	   }
      else if(isNaN(stolenRecoveryFileStatus) && isNaN(stolenRecoverySourceStatus))
	   {
    	  stolenRecoveryFileStatus='';
    	  stolenRecoverySourceStatus='';
    	  console.log(" 2222stolenRecoveryFileStatus && stolenRecoverySourceStatus is empty=="+stolenRecoveryFileStatus+stolenRecoverySourceStatus);
	   }
      else if(isNaN(stolenRecoverySourceStatus) && isNaN(stolenRecoveryRequestType))
	   {
    	  stolenRecoverySourceStatus='';
    	  stolenRecoveryRequestType='';
    	  console.log(" 333333stolenRecoverySourceStatus && stolenRecoveryRequestType is empty="+stolenRecoverySourceStatus+stolenRecoveryRequestType);
	   }
      else if(isNaN(stolenRecoveryRequestType) && isNaN(stolenRecoveryFileStatus))
    	  {
    	   stolenRecoveryRequestType='';
    	   stolenRecoveryFileStatus='';
    	   console.log(" 44444stolenRecoveryRequestType && stolenRecoveryFileStatus is empty "+stolenRecoveryRequestType+stolenRecoveryFileStatus);
    	  }
      else if(isNaN(stolenRecoveryFileStatus))
    	  {
    	  stolenRecoveryFileStatus='';
    	  console.log("stolenRecoveryFileStatus is blank="+stolenRecoveryFileStatus);
    	  }
      else if(isNaN(stolenRecoverySourceStatus))
	  {
    	  stolenRecoverySourceStatus='';
    	  console.log("stolenRecoverySourceStatus is blank="+stolenRecoverySourceStatus);
	  }
      else if(isNaN(stolenRecoveryRequestType))
	  {
    	  stolenRecoveryRequestType='';
    	  console.log("stolenRecoveryRequestType is blank="+stolenRecoveryRequestType);
	  }

	var table = $('#stolenLibraryTable').DataTable();
	var info = table.page.info(); 
    var pageNo=info.page;
    var pageSize =info.length;
	console.log("--------"+pageSize+"---------"+pageNo);
	console.log("stolenRecoveryStartDate  ="+stolenRecoveryStartDate+"  stolenRecoveryEndDate=="+stolenRecoveryEndDate+"  stolenRecoveryTxnId="+stolenRecoveryTxnId+" stolenRecoveryFileStatus ="+stolenRecoveryFileStatus+"=role="+role+" stolenRecoverySourceStatus="+stolenRecoverySourceStatus+" stolenRecoveryRequestType"+stolenRecoveryRequestType);
	window.location.href="./exportStolenRecovery?stolenRecoveryStartDate="+stolenRecoveryStartDate+"&stolenRecoveryEndDate="+stolenRecoveryEndDate+"&stolenRecoveryTxnId="+stolenRecoveryTxnId+"&stolenRecoveryFileStatus="+stolenRecoveryFileStatus+"&stolenRecoverySourceStatus="+stolenRecoverySourceStatus+"&stolenRecoveryRequestType="+stolenRecoveryRequestType+"&pageSize="+pageSize+"&pageNo="+pageNo+"&roleType="+roleType;

}
	

function openStolenRecoveryModal()
{
	$('#chooseStolenOption').openModal();
}

function openStolenRecoveryPage(pageType,pageView)
{
	console.log("###");
	window.location.href = "./openlawfulStolenRecoveryPage?pageType="+pageType+"&pageView="+pageView;
}

function showSingleFormDiv()
{
	$("#SingleForm").css("display", "block");
	$("#Bulkform").css("display", "none");
	
	$('#singleFormSubmit').trigger("reset");
	$('#bulkFormSubmit').trigger("reset");
	
}
function  showBulkFormDiv(){
	
	$("#SingleForm").css("display", "none");
	$("#Bulkform").css("display", "block");
	
	$('#singleFormSubmit').trigger("reset");
	$('#bulkFormSubmit').trigger("reset");
	
}

function singleRecoverydiv()
{
	$("#singleRecoveryDiv").css("display", "block");
	$("#bulkRecoveryDiv").css("display", "none");
	
	$('#singleRecoveryForm').trigger("reset");
	$('#bulkRecoveryForm').trigger("reset");
	
}
function  showBulkRecovery(){
	
	$("#singleRecoveryDiv").css("display", "none");
	$("#bulkRecoveryDiv").css("display", "block");
	
	$('#singleRecoveryForm').trigger("reset");
	$('#bulkRecoveryForm').trigger("reset");
	
}




$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#singleStolendeviceIDType,#sigleRecoverydeviceIDType');

	}
});


$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#singleStolendeviceType,#sigleRecoverydeviceType');

	}
});

$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#sigleRecoverydeviceSimStatus,#singleStolenSimStatus');
		
	}
});

$.getJSON('./getDropdownList/OPERATORS', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#singleStolenOperator');
	}
});

$.getJSON('./getDropdownList/COMPLAINT_TYPE', function(data) {
	
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#singleStolenComplaintType,#deviceBulkStolenComplaint');
	}
});


$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#sigleRecoverydeviceStatus');
	
	}
});



function saveIndivisualStolenRequest(){
	var formData= new FormData();
	
	var singleStolenfirstName=$('#singleStolenfirstName').val();
	var singleStolenmiddleName=$('#singleStolenmiddleName').val();
	var singleStolenlastName=$('#singleStolenlastName').val();
	var singleStolennIDPassportNumber=$('#singleStolennIDPassportNumber').val();
	var singleStolenemail=$('#singleStolenemail').val();
	var singleStolenphone1=$('#singleStolenphone1').val();
	var singleStolenaddress=$('#singleStolenaddress').val();
	var singleStolenstreetNumber=$('#singleStolenstreetNumber').val();
	var singleStolenvillage=$('#singleStolenvillage').val();
	var singleStolenlocality=$('#singleStolenlocality').val();
	var singleStolendistrict=$('#singleStolendistrict').val();
	var singleStolencommune=$('#singleStolencommune').val();
	var singleStolenpin=$('#singleStolenpin').val();
	var country=$('#singleStolenfirstName').val();
	var state=$('#state').val();
	var blockingTimePeriod=$('#stolenDatePeriod').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	
	var singleStolendeviceBrandName=$('#singleStolendeviceBrandName').val();
	var singleStolenimeiNumber=$('#singleStolenimeiNumber').val();
	var singleStolendeviceIDType=$('#singleStolendeviceIDType').val();
	var singleStolendeviceType=$('#singleStolendeviceType').val();
	var singleStolenOperator=$('#singleStolenOperator').val();
	var singleStolenSimStatus=$('#singleStolenSimStatus').val();
	var singleStolenComplaintType=$('#singleStolenComplaintType').val();
	var singleStolenphone2 = $('#singleStolenphone2').val();
	var singleStolenmodalNumber= $('#singleStolenmodalNumber').val();
	
	var singleDeviceAddress=$('#singleDeviceAddress').val();
	var singleDevicestreetNumber=$('#singleDevicestreetNumber').val();
	var singleDevicevillage=$('#singleDevicevillage').val();
	var singleDevicelocality=$('#singleDevicelocality').val();
	var singleDevicedistrict=$('#singleDevicedistrict').val();
	var singleDevicecommune=$('#singleDevicecommune').val();
	var singleDevicepin=$('#singleDevicepin').val();
	var singleDevicecountry=$('#singleDevicecountry').val();
	var singleDevicestate=$('#singleDevicestate').val();
	var singleDeviceRemark=$('#singleDeviceRemark').val();
	

	
	var stolenIndividualUserDB={
			"alternateContactNumber": singleStolenphone1,
			"commune": singleStolencommune,
			"complaintType": singleStolenComplaintType,
			"contactNumber": singleStolenphone2,
			"country": country,
			"deviceBrandName": singleStolendeviceBrandName,
			"deviceIdType": singleStolendeviceIDType,
			"deviceStolenCommune": singleDevicecommune,
			"deviceStolenDistrict": singleDevicedistrict,
			"deviceStolenLocality": singleDevicelocality,
			"deviceStolenPostalCode": singleDevicepin,
			"deviceStolenPropertyLocation": singleDeviceAddress,
			"deviceStolenStreet": singleDevicestreetNumber,
			"deviceStolenVillage": singleDevicevillage,
			"deviceType":singleStolendeviceType,
			"district": singleStolendistrict,
			"email":singleStolenemail,
			"firstName":singleStolenfirstName,
			"imei_esn_meid": singleStolenimeiNumber,
			"lastName": singleStolenlastName,
			"locality": singleStolenlocality,
			"middleName": singleStolenmiddleName,
			"modelNumber":singleStolenmodalNumber,
			"nid": singleStolennIDPassportNumber,
			"operator": singleStolenOperator,
			"phoneNo": singleStolenphone2,
			"postalCode": singleDevicepin,
			"propertyLocation": singleStolenaddress,
			"province": state,
			"remark": singleDeviceRemark,
			"street": singleStolenstreetNumber,
			"village":singleStolenvillage
	}
	
	
	var request={
			"blockingTimePeriod":blockingTimePeriod,
			"blockingType":blockingType,
			"requestType":0,
			"sourceType":5,
			"stolenIndividualUserDB":stolenIndividualUserDB
	}
	formData.append('file', $('#singleStolenFile')[0].files[0]);
	formData.append("request",JSON.stringify(request));

	$.ajax({
		url: './lawfulIndivisualStolen',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		console.log(response)
		
			if(response.errorCode==0){
				$("#indivisualStolenButton").prop('disabled', true);
				$('#IndivisualStolenSucessPopup').openModal();
				$('#IndivisualStolenTxnId').text(response.txnId)
			}
			else{
//				$('#sucessMessage').text('');
				$('#regularisedDevice').openModal();
				$('#dynamicTxnId').text(data.txnId);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	return false;
	
}


//__________________--------------------------_______________________Edit  Pages functions___________________---------------------------------------_____________________________
















