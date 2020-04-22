var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-stolenselected-roleType");  
var role = currentRoleType == null ? roleType : currentRoleType;
var userType = $("body").attr("data-roleType");
var featureId = window.parent.$('.navData li.active a').attr('data-featureid');
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

var userType = $("body").attr("data-roleType");
var sourceType = localStorage.getItem("sourceType");

function filterStolen(){

	if(userType=="Lawful Agency"){
		Datatable('./headers?type=lawfulStolenHeaders','./stolenData?featureId='+featureId)
	}else if(userType =="CEIRAdmin"){
		Datatable('./headers?type=lawfulStolenHeaders','./stolenData?featureId='+featureId)
	}
	localStorage.removeItem('sourceType');
}




function Datatable(url,DataUrl){
	var filterRequest={
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"txnId":$('#transactionID').val(),
			"consignmentStatus":parseInt($('#status').val()),
			"requestType":parseInt($('#requestType').val()),
			"sourceType":parseInt($('#sourceStatus').val()),
			"roleType": role,
			"userId": userId,
			"featureId":featureId,
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"userType":$("body").attr("data-roleType"),
	}

	
	if(lang=='km'){
		var langFile='./resources/i18n/khmer_datatable.json';
	}

	
	$.ajax({
		url: url,
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
				"oLanguage": {  
					"sUrl": langFile  
				},
				scrollCollapse: true,	
				ajax: {
					url: DataUrl,
					type: 'POST',
					data : function(d) {
						d.filter =JSON.stringify(filterRequest); 
						console.log(JSON.stringify(filterRequest));
					}
				},
				"columns": result,
				fixedColumns: true,
				columnDefs: [
					{ width: 245, targets: result.length - 1 }
					]
			});
			$('div#initialloader').delay(300).fadeOut('slow');
		}
	}); 
}				

function pageRendering(){
	$.ajax({
		url: './stolen/pageRendering?featureId='+featureId,
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
							"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off' onchange='checkDate(startDate,endDate)'>"+
							"<label for="+date[i].id+">"+date[i].title
							+"</label>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");
					$( "#"+date[i].id ).datepicker({
						dateFormat: "yy-mm-dd",
						 maxDate: new Date()
			        }); 
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
	$('#stoleRecoveryModal').openModal({
		dismissible:false
	});
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

	var filterRequest={
			"endDate":stolenRecoveryEndDate,
			"startDate":stolenRecoveryStartDate,
			"txnId":stolenRecoveryTxnId,
			"grievanceStatus":stolenRecoveryFileStatus,
			"sourceType":stolenRecoverySourceStatus,
			"requestType":stolenRecoveryRequestType,
			"featureId":featureId,
			"roleType":roleType,
			"pageNo":parseInt(pageNo),
			"pageSize":parseInt(pageSize)
			
	}
	console.log(JSON.stringify(filterRequest))
	$.ajax({
		url: './exportStolenRecovery',
		type: 'POST',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : JSON.stringify(filterRequest),
		success: function (data, textStatus, jqXHR) {
			  window.location.href = data.url;

		},
		error: function (jqXHR, textStatus, errorThrown) {
			
		}
	});
}


function openStolenRecoveryModal()
{
	$('#chooseStolenOption').openModal({
		dismissible:false
	});
}

function openStolenRecoveryPage(pageType,pageView,txnId)
{
	window.location.href = "./openlawfulStolenRecoveryPage?pageType="+pageType+"&pageView="+pageView+"&txnId="+txnId;
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
	var trimContactNumber1=$('#singleStolenphone1').val();
	var singleStolenphone1 =trimContactNumber1.replace(/[^A-Z0-9]/ig, "");
	var singleStolenaddress=$('#singleStolenaddress').val();
	var singleStolenstreetNumber=$('#singleStolenstreetNumber').val();
	var singleStolenvillage=$('#singleStolenvillage').val();
	var singleStolenlocality=$('#singleStolenlocality').val();
	var singleStolendistrict=$('#singleStolendistrict').val();
	var singleStolencommune=$('#singleStolencommune').val();
	var singleStolenpin=$('#singleStolenpin').val();
	var country=$('#country').val();
	var state=$('#state').val();
	var blockingTimePeriod=$('#stolenDatePeriod').val();
	var blockingType =$('.blocktypeRadio:checked').val();

	var singleStolendeviceBrandName=$('#singleStolendeviceBrandName').val();

	var singleStolenimei1=$('#singleStolenimei1').val();
	var singleStolenimei2=$('#singleStolenimei2').val();
	var singleStolenimei3=$('#singleStolenimei3').val();
	var singleStolenimei4=$('#singleStolenimei4').val();


	var singleStolendeviceIDType=$('#singleStolendeviceIDType').val();
	var singleStolendeviceType=$('#singleStolendeviceType').val();
	var singleStolenOperator=parseInt($('#singleStolenOperator').val());
	var singleStolenSimStatus=$('#singleStolenSimStatus').val();
	var singleStolenComplaintType=$('#singleStolenComplaintType').val();
	var trimContactNumber2 = $('#singleStolenphone2').val();
	var singleStolenphone2 =trimContactNumber2.replace(/[^A-Z0-9]/ig, "");
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
	var IndivisualStolenDate=$('#IndivisualStolenDate').val();	
	var indivisualStolenfileName=$('#singleStolenFile').val();

	var uploadedFileName = $("#singleStolenFile").val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	console.log("**** file name"+uploadedFileName)

	var fileFileDetails=$('#uploadFirSingle').val();
	fileFileDetails=fileFileDetails.replace(/^.*[\\\/]/, '');

	var stolenIndividualUserDB={
			"alternateContactNumber": singleStolenphone1,
			"commune": singleStolencommune,
			
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
			"deviceStolenCountry": singleDevicecountry,
			"deviceStolenProvince": singleDevicestate,
			"deviceType":singleStolendeviceType,
			"district": singleStolendistrict,
			"email":singleStolenemail,
			"firstName":singleStolenfirstName,
			"imeiEsnMeid1": parseInt(singleStolenimei1),
			"imeiEsnMeid2": parseInt(singleStolenimei2),
			"imeiEsnMeid3": parseInt(singleStolenimei3),
			"imeiEsnMeid4": parseInt(singleStolenimei4),
			"lastName": singleStolenlastName,
			"locality": singleStolenlocality,
			"multiSimStatus": singleStolenSimStatus,
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
			"village":singleStolenvillage,
			"nidFileName":uploadedFileName
	}


	var request={
			"dateOfStolen":IndivisualStolenDate,
			"blockingTimePeriod":blockingTimePeriod,
			"blockingType":blockingType,
			"requestType":0,
			"sourceType":5,
			"firFileName":fileFileDetails,
			"complaintType": singleStolenComplaintType,
			"operatorTypeId":singleStolenOperator,
			"stolenIndividualUserDB":stolenIndividualUserDB
	}
	formData.append('firFileName', $('#uploadFirSingle')[0].files[0]);
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
				$('#IndivisualStolenSucessPopup').openModal({
					dismissible:false
				});
				$('#IndivisualStolenTxnId').text(response.txnId)
			}
			else{
				$('#sucessMessage').text('');
				$('#IndivisualStolenSucessPopup').openModal({
					dismissible:false
				});
				$('#sucessMessage').text(response.message);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	return false;

}


//__________________--------------------------_______________________Edit  Pages functions___________________---------------------------------------_____________________________





function saveCompanyStolenRequest(){
	var formData= new FormData();

	var bulkStolencompanyName=$('#bulkStolencompanyName').val();
	var bulkStolenaddress=$('#bulkStolenaddress').val();
	var bulkStolenstreetNumber=$('#bulkStolenstreetNumber').val();
	var bulkStolenvillage=$('#bulkStolenvillage').val();
	var bulkStolenlocality=$('#bulkStolenlocality').val();
	var bulkStolendistrict=$('#bulkStolendistrict').val();
	var bulkStolencommune=$('#bulkStolencommune').val();
	var bulkStolenpin=$('#bulkStolenpin').val();
	var country2=$('#country2').val();
	var state2=$('#state2').val();

	var blockingTimePeriod=$('#stolenBulkDatePeriod').val();
	var blockingType =$('.bulkblocktypeRadio:checked').val();


	var firstName=$('#firstName').val();
	var bulkStolenmiddleName=$('#bulkStolenmiddleName').val();
	var bulkStolenlastName=$('#bulkStolenlastName').val();
	var bulkStolenofficeEmail=$('#bulkStolenofficeEmail').val();
	var trimbulkStolenContact=$('#bulkStolenContact').val();
	var bulkStolenContact =trimbulkStolenContact.replace(/[^A-Z0-9]/ig, "");
	var uploadFirBulk=$('#uploadFirBulk').val();

	var deviceBulkStolenaddress=$('#deviceBulkStolenaddress').val();
	var deviceBulkStolenstreetNumber=$('#deviceBulkStolenstreetNumber').val();
	var deviceBulkStolenvillage=$('#deviceBulkStolenvillage').val();
	var deviceBulkStolenlocality=$('#deviceBulkStolenlocality').val();
	var deviceBulkStolendistrict=$('#deviceBulkStolendistrict').val();
	var deviceBulkStolencommune=$('#deviceBulkStolencommune').val();
	var deviceBulkStolenpin=$('#deviceBulkStolenpin').val();
	var country3 = $('#country3').val();
	var state3= $('#state3').val();
	var deviceBulkStolenComplaint=$('#deviceBulkStolenComplaint').val();
	var deviceBulkStolenquantity=$('#deviceBulkStolenquantity').val();
	var deviceBulkStolenRemark=$('#deviceBulkStolenRemark').val();
	var bulkStolenDate=$('#bulkStolenDate').val();

	var uploadFirBulk=$('#uploadFirBulk').val();
	uploadFirBulk=uploadFirBulk.replace(/^.*[\\\/]/, '');

	var stolenOrganizationUserDB= {
			"commune": bulkStolencommune,
			"companyName": bulkStolencompanyName,
			"country": country2,
			"district": bulkStolendistrict,
			"email": bulkStolenofficeEmail,
			"incidentCommune": deviceBulkStolencommune,
			"incidentCountry": country3,
			"incidentDistrict": deviceBulkStolendistrict,
			"incidentLocality": deviceBulkStolenlocality,
			"incidentPostalCode": deviceBulkStolenpin,
			"incidentProvince": state3,
			"incidentStreet": deviceBulkStolenstreetNumber,
			"incidentPropertyLocation": deviceBulkStolenaddress,
			"incidentVillage": deviceBulkStolenvillage,
			"locality": deviceBulkStolenlocality ,
			"personnelFirstName": firstName,
			"personnelLastName":bulkStolenlastName ,
			"personnelMiddleName":bulkStolenmiddleName ,
			"phoneNo": bulkStolenContact,
			"postalCode": bulkStolenpin,
			"propertyLocation": bulkStolenaddress,
			"province": state2,
			"street": bulkStolenstreetNumber,
			"village": bulkStolenvillage
	}


	var request={
			"qty":deviceBulkStolenquantity,
			"dateOfStolen":bulkStolenDate,
			"blockingTimePeriod":blockingTimePeriod,
			"blockingType":blockingType,
			"remark":deviceBulkStolenRemark,
			"complaintType": deviceBulkStolenComplaint,
			"requestType":0,
			"sourceType":6,
			"firFileName":uploadFirBulk,
			"stolenOrganizationUserDB":stolenOrganizationUserDB
	}
	formData.append('file', $('#deviceBulkStolenFile')[0].files[0]);
	formData.append('firFileName', $('#uploadFirBulk')[0].files[0]);
	formData.append("request",JSON.stringify(request));

	$.ajax({
		url: './lawfulOraganisationStolen',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
			console.log(response)

			if(response.errorCode==0){
				$("#bulkStolenButton").prop('disabled', true);
				$('#IndivisualStolenSucessPopup').openModal({
					dismissible:false
				});
				$('#IndivisualStolenTxnId').text(response.txnId)
			}
			else{
				$('#sucessMessage').text('');
				$('#regularisedDevice').openModal({
					dismissible:false
				});
				$('#sucessMessage').text(response.message);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	return false;


}


function DeleteConsignmentRecord(txnId,id){
	$("#DeleteConsignment").openModal({
		dismissible:false
	});
	$("#transID").text(txnId);
	$("#setStolenRecoveyRowId").text(id);
}


function confirmantiondelete(){
	var txnId = $("#transID").text();
	var id= $("#setStolenRecoveyRowId").text();
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var currentRoleType = $("body").attr("data-stolenselected-roleType"); 
	var remarks = $("#textarea1").val();
	var role = currentRoleType == null ? roleType : currentRoleType;
	console.log("txnId===**"+txnId+" userId="+userId+" roleType== "+roleType+ " currentRoleType=="+currentRoleType);
	var obj ={
			"txnId" : txnId,
			"roleType":role,
			"userId":userId,
			"id":id,
			"remark":remarks

	}
	$.ajax({
		url : "./stolenRecoveryDelete",
		data : JSON.stringify(obj),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data, textStatus, xhr) {
			console.log(data);
			if(data.errorCode == 200){
				$("#consignmentText").text(data.message);
			}else if(data.errorCode == 0){
				$("#consignmentText").text(data.message);
			}
		},
		error : function() {
			console.log("Error");
		}
	});
	$("#DeleteConsignment").closeModal();
	$("#confirmDeleteConsignment").openModal({
		dismissible:false
	});
	return false;
}

//------------------------------------------- Admin Approve------------------------------------------ 

function deviceApprovalPopup(transactionId,requestType){
	$('#approveInformation').openModal({dismissible:false});
	$('#blockApproveTxnId').text(transactionId);
	window.transactionId=transactionId;
	window.requestType=requestType;
}

function aprroveDevice(){
	var approveRequest={
			"action" : 0,
			"featureId":parseInt(featureId),
			"requestType":parseInt(window.requestType),
			"roleType": roleType,
			"roleTypeUserId": parseInt($("body").attr("data-userTypeID")),
			"txnId": window.transactionId,
			"userId":parseInt(userId)
	}

	$.ajax({
		url : './blockUnblockApproveReject',
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		'async' : false,
		contentType : 'application/json; charset=utf-8',
		type : 'PUT',
		success : function(data) {
			console.log("approveRequest----->"+JSON.stringify(approveRequest));
			if(data.errorCode==0){
				confirmApproveInformation();
				console.log("inside Approve Success")
			}

		},
		error : function() {
			alert("Failed");
		}
	});
}

function confirmApproveInformation(){
	$('#approveInformation').closeModal(); 
	setTimeout(function(){ $('#confirmApproveInformation').openModal({dismissible:false});}, 200);
}


//------------------------------------------- Admin Reject------------------------------------------

function userRejectPopup(transactionId,requestType){
	$('#rejectInformation').openModal({dismissible:false});
	$('#rejectTxnId').text(transactionId);
	window.transactionId=transactionId;
	window.requestType=requestType;
}

function rejectUser(){
	var rejectRequest={
			"action" : 1,
			"featureId":parseInt(featureId),
			"remarks": $("#Reason").val(),
			"requestType":parseInt(window.requestType),
			"roleType": roleType,
			"roleTypeUserId": parseInt($("body").attr("data-userTypeID")),
			"txnId": window.transactionId,
			"userId":parseInt(userId)
	}

	$.ajax({
		url : './blockUnblockApproveReject',
		data : JSON.stringify(rejectRequest),
		dataType : 'json',
		'async' : false,
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success : function(data) {
			console.log("approveRequest----->"+JSON.stringify(rejectRequest));
			if(data.errorCode==0){
				confirmRejectInformation();
				console.log("inside Reject Success")
			}

		},
		error : function() {
			alert("Failed");
		}
	});
	
	return false;
}

function confirmRejectInformation(){
	$('#rejectInformation').closeModal();
	setTimeout(function(){$('#confirmRejectInformation').openModal({dismissible:false});},200);
}

/*function clearFileName() {
	alert("ss")
	$('#bulkRecoveryFile').val('');
	$("#bulkRecoveryFileName").val('');
	$('#fileFormateModal').closeModal();
}
*/

function clearFileName() {
	var fieldId=$('#FilefieldId').val();
	
	if(fieldId=='singleStolenFile')
		{
		$('#'+fieldId).val('');
		$('#singleStolenFileName').val('');
		}
	else if(fieldId=='uploadFirSingle')
		{
		$('#'+fieldId).val('');
		$('#uploadFirSingleName').val('');
		}
	
	
	else if(fieldId=='deviceBulkStolenFile')
		{
		$('#'+fieldId).val('');
		$('#deviceBulkStolenFileName').val('');
		}
	else if(fieldId=='uploadFirBulk')
	{
		$('#'+fieldId).val('');
	$('#uploadFirSingleBulkName').val('');
	}
	
	$('#fileFormateModal').closeModal();
	$('#FilefieldId').val('');
}


$.getJSON('./productList', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].id).text(data[i].brand_name)
				.appendTo('#singleStolendeviceBrandName');
	}
});

$('#singleStolendeviceBrandName').on(
		'change',
		function() {
			var brand_id = $('#singleStolendeviceBrandName').val();
			$.getJSON('./productModelList?brand_id=' + brand_id,
					function(data) {
						$("#singleStolenmodalNumber").empty();
						for (i = 0; i < data.length; i++) {
							$('<option>').val(data[i].id).text(
									data[i].modelName).appendTo(
									'#singleStolenmodalNumber');
						}
					});
		});


