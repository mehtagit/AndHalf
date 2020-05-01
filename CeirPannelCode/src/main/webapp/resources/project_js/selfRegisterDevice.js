$('#langlist').on('change', function() {
	window.lang=$('#langlist').val() == 'km' ? 'km' : 'en';
	var url_string = window.location.href;
	var url = new URL(url_string);
	var type = url.searchParams.get("type");
	window.location.assign("selfRegisterDevice?lang="+window.lang);			
}); 


$('#langlist').val(data_lang_param);
$.i18n().locale = data_lang_param;
var successMsg;
$.i18n().load( {
	'en': './resources/i18n/en.json',
	'km': './resources/i18n/km.json'
} ).done( function() { 
});


/*var input = document.querySelector("#phone1");
window.intlTelInput(input, {
	utilsScript: "js/utils.js",
});

var input = document.querySelector("#phone");
window.intlTelInput(input, {
	utilsScript: "js/utils.js",
});*/


$('#datepicker,#datepicker1').datepicker({
	dateFormat: "yy-mm-dd"
});


populateCountries(
		"country",
		"state"
);
populateStates(
		"country",
		"state"
);

populateCountries(
		"country1"

);




function showCambodianUserForm()
{
	$("#askVisaDetails").css("display", "none"); 
	$("#visaDetails").css("display", "none"); 
	$("#nationalityDiv").css("display", "none"); 
	$('#endUserLabelNID').text($.i18n('National ID'));
	$('#nidType').text($.i18n('Upload ID Image'));
	$("#datepicker").attr("required", false);
	$("#nidPlaceHolder").attr("placeholder", $.i18n('Upload ID Image')).val("").focus().blur();
	$('#visaDetails').find('input:text').val('');
	$('#visaDetails').find('input:file').val('');
	$('input[name="onVisa"]').prop('checked', false);

	$("#datepicker").attr("required", false);
	$("#visaNumber").attr("required", false);
	$("#visaImage").attr("required", false);
	$("#datepicker1").attr("required", false);
	$("#visaType").attr("required", false);

	$("#nationality").attr("required", false);
	$("#departmentName").attr("required", false);
	$("#endUserdepartmentID").attr("required", false);
	$("#endUserDepartmentId").attr("required", false);
	$("#visaType").attr("required", false);
	$("#datepicker").attr("required", false);
	$("#datepicker1").attr("required", false);
	$("#visaImage").attr("required", false);
	$("#visaNumber").attr("required", false);

	$("#entryCountryDiv").css("display", "none");

	$("#endUserLabelNID").append('<span class="star">*</span>');
	$("#nidType").append('<span class="star">*</span>');
	localStorage.removeItem('allowed');
    var allowedCount=	regularizedCount(1);
 	if(allowedCount>0)
 		{
 		$('#taxStatus1').prop('disabled', 'disabled');
		$('#taxStatus1').val("2");
		}
 	else{
 		$('#taxStatus1').prop('disabled', false);
 		$('#taxStatus1').val("1");
 	}
 	
 	$("#priceDiv").css("display", "block");
 	$("#CurrencyDiv").css("display", "none");
 	
 	var removeBulkDiv;
 	$('.deviceInformation').each(function(){
 		removeBulkDiv=id-1;
 		if(removeBulkDiv>1)
 		{
 			$('#deviceInformation'+removeBulkDiv).remove();
 	 		id--;
 	 		x--;
 			}
 		else{
 			
 		}
 	 });
}

function removeSelectVip()
{
	$("#vipUserDiv").css("display", "none");	
	$('#vipUserDiv').find('input:text').val('');
	$('#vipUserDiv').find('input:file').val('');

	$("#departmentName").removeAttr('required');
	$("#endUserdepartmentID").removeAttr('required');
	$("#endUserDepartmentId").removeAttr('required');
}

function  selectVip(){

	$("#vipUserDiv").css("display", "block");
	$("#departmentName").attr("required", true);
	$("#endUserdepartmentID").attr("required", true);
	$("#endUserDepartmentId").attr("required", true);

}

function showOtherUserForm()
{
	$('#endUserLabelNID').text($.i18n('Passport Number'));
	$('#nidType').text($.i18n('Upload Passport Image'));
	$("#askVisaDetails").css("display", "block");
	$("#nationalityDiv").css("display", "block");
	$("#onVisaNo").prop("checked", true);
	$("#nidPlaceHolder").attr("placeholder", $.i18n('Upload Passport Image')).val("").focus().blur();

	$("#nationality").attr("required", true);
	$("#endUserLabelNID").append('<span class="star">*</span>');
	$("#nidType").append('<span class="star">*</span>');
	$("#datepicker").attr("required", true);
	
	$("#entryCountryDiv").css("display", "block");
	/*$("#departmentName").attr("required", true);
	 $("#endUserdepartmentID").attr("required", true);
	 $("#endUserDepartmentId").attr("required", true);
	 $("#visaType").attr("required", true);
	 $("#datepicker").attr("required", true);
	 $("#datepicker1").attr("required", true);
	 $("#visaImage").attr("required", true);*/
	
	localStorage.removeItem('allowed');
    var allowedCount=	regularizedCount(2);
 	if(allowedCount>0)
 		{
 		$('#taxStatus1').prop('disabled', 'disabled');
		$('#taxStatus1').val("2");
		}
 	else{
 		$('#taxStatus1').prop('disabled', false);
 		$('#taxStatus1').val("1");
 	}
 	
 	$("#priceDiv").css("display", "none");
 	$("#CurrencyDiv").css("display", "none");
 	
	var removeBulkDiv;
 	$('.deviceInformation').each(function(){
 		removeBulkDiv=id-1;
 		if(removeBulkDiv>1)
 		{
 			$('#deviceInformation'+removeBulkDiv).remove();
 	 		id--;
 	 		x--;
 			}
 		else{
 			
 		}
 	  });
 	
}

function showVisaDetails(){
	$("#visaDetails").css("display", "block");
	
	$("#visaNumber").attr("required", true);
	$("#visaImage").attr("required", true);
	$("#datepicker1").attr("required", true);
	$("#visaType").attr("required", true);

}
function hideVisaDetails(){
	$("#visaDetails").css("display", "none");
	$('#visaDetails').find('input:text').val('');
	$('#visaDetails').find('input:file').val('');

	//$("#datepicker").attr("required", false);
	$("#visaNumber").attr("required", false);
	$("#visaImage").attr("required", false);
	$("#datepicker1").attr("required", false);
	$("#visaType").attr("required", false);
}
function regularizedCount(nationType){
	console.log("----"+nationType)
	var allowed='';
	if(nationType==undefined)
		{
		nationType=1;
		}
	var nid= nationalId == 'null' ? null : nationalId;
	$.ajax({
		url: './countByNid?nid='+nid+"&nationType="+nationType,
		type: 'GET',
		processData: false,
		contentType: false,
		async: false,
		success: function (data, textStatus, jqXHR) {

			 allowed=data.data.allowed;
			var current=data.data.current;
			
			localStorage.setItem("allowed", allowed);
			localStorage.setItem("current", current);
			localStorage.setItem("nationType", nationType);
			
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	
	return allowed;
}

$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
	var checkAllowedCount =localStorage.getItem("allowed");	
	//alert("222222"+checkAllowedCount);
	console.log("  checkAllowedCount  == "+checkAllowedCount)
	if(checkAllowedCount==0)
		{
	
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#taxStatus1');

		}
		}
	else{
	
	$('#taxStatus1').prop('disabled', 'disabled');
	$('<option  selected>').val("2").text("Regularized").appendTo('#taxStatus1');
	}
});

$.getJSON('./getDropdownList/VISA_TYPE', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#visaType');

	}
});
$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#deviceType1');

	}
});
$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#deviceIdType1');

	}
});
$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#multipleSimStatus1');

	}
});
$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#deviceStatus1');

	}
});

$.getJSON('./getDropdownList/CURRENCY', function(data) {

	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#Currency1');

	}
});






$(document).ready(function () {

	$( document ).ready(function() {
		
		var In = $("body").attr("session-value");
		if(In.length > 0 && In !='null' ){
		
			$.ajax({
				url : "./paid-status/"+In,
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				type : 'GET',
				success : function(data) {
                   
                    sessionStorage.setItem("nationalId", In);
                    localStorage.setItem("nationalId", In);
					if (data.errorCode == 1) {
					
						$("#user123").css("display", "none");
						$("#user456").css("display", "block");
						$("#addbutton").css("display", "block");
						$("#submitbtn").css("display", "none");
						/*$('div#initialloader').fadeIn('fast');*/
						pageRendering(data_lang_param);
						filter(data_lang_param);
					} 
					else if (data.errorCode == 0 && In == 'null') {
						$("#user123").css("display", "none");
						$("#user456").css("display", "block");
						$("#addbutton").css("display", "block");
						$("#submitbtn").css("display", "none");
						$('div#initialloader').delay(300).fadeOut('slow');
					} 
					else
					{
						$("#user123").css("display", "block");
						$("#user456").css("display", "none");
						$("#addbutton").css("display", "none");
						$("#submitbtn").css("display", "none");	
						$('div#initialloader').delay(300).fadeOut('slow');
					}
					$('#nationalID').val(In);
					regularizedCount();
				},
				error : function() {
					console.log("Failed");
				}
			}); 
				
			


		}
		else{
			
			//sessionStorage.setItem("admin","CEIRAdmin");
			$.ajax({
				url : "./paid-status/"+In,
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				type : 'GET',
				success : function(data) {

					//	sessionStorage.removeItem('nationalId');

					if (data.errorCode == 1) {
						$("#user123").css("display", "none");
						$("#user456").css("display", "block");
						$("#addbutton").css("display", "block");
						$("#submitbtn").css("display", "none");
						pageRendering(data_lang_param);
						filter(data_lang_param);
					} 
					else if (data.errorCode == 0 && In == 'null') {
						$("#user123").css("display", "none");
						$("#user456").css("display", "block");
						$("#addbutton").css("display", "block");
						$("#submitbtn").css("display", "none");
						$('div#initialloader').delay(300).fadeOut('slow');
					} 
					else
					{
						$("#user123").css("display", "block");
						$("#user456").css("display", "none");
						$("#addbutton").css("display", "none");
						$("#submitbtn").css("display", "none");
						$('div#initialloader').delay(300).fadeOut('slow');
					}
					regularizedCount();
				},
				error : function() {
					console.log("Failed");
				}
			}); 
			

			$("#btnLink").css({display: "none"});


		}



	});

	
});


function pageRendering(lang){
	pageButtons('./upload-paid-status/pageRendering?type=userPaidStatus&lang='+lang);

	localStorage.removeItem('sourceType');

}


function pageButtons(url){
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(data){
			data.userStatus == "Disable" ? $('#btnLink').addClass( "eventNone" ) : $('#btnLink').removeClass( "eventNone" );

			var elem='<p class="PageHeading">'+data.pageTitle+'</p>';		
			$("#pageHeader").append(elem);
			var button=data.buttonList;



			var date=data.inputTypeDateList;
			for(i=0; i<date.length; i++){
				if(date[i].type === "date"){
					$("#tableDiv").append("<div class='input-field col s6 m2'>"+
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
					$("#tableDiv").append("<div class='input-field col s6 m2'><input type="+date[i].type+" id="+date[i].id+" /><label for="+date[i].id+" id="+date[i].id+">"+date[i].title+"</label></div>");
				}
				
			} 

			// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#tableDiv").append("<div class='col s6 m2 selectDropdwn'>"+

							"<div class='select-wrapper select2  initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select-wrapper select2  initialized' style='height: 2.4rem;'>"+
							"<option value=''>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}



			$("#tableDiv").append("<div class='col s3 m2 l1'><button type='button' class='btn primary botton'  id='submitFilter' /></div></div></div>");
			$("#tableDiv").append("<div class='col s3 m2 l1'><a href='JavaScript:void(0)' onclick='exportpaidStatus()' type='button' class='export-to-excel right'>"+$.i18n('Export')+" <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");

			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				if(button[i].type === "HeaderButton"){
					//$('#'+button[i].id).attr("href", button[i].buttonURL);
					$('#'+button[i].id).attr("href", "./EndUser_AddDevices");
				}
				else{
					$('#'+button[i].id).attr("onclick", button[i].buttonURL);
				}
			}


			//Tax paid status-----------dropdown

			$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
				for (i = 0; i < data.length; i++) {
					//console.log(data[i].value);
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#taxPaidStatus');
				}
			});
			
			//Stolen Status-----------dropdown
			$.getJSON('./getDropdownList/12/17', function(data) {
				console.log("___data");
				console.log(data);
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].state).text(data[i].interp)
					.appendTo('#recordStatus'); 
				}
			});


			$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#deviceTypeFilter');
				}
			});


			$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#deviceIDType');
				}
			});
			
	
			
		}
	}); 	

	if(sessionStorage.getItem("admin")=="CEIRAdmin"){
		$("#btnLink").css({display: "none"});
	}
}


var sourceType =localStorage.getItem("sourceType");
function filter(lang)
{       
var sessionFlag=0;
table('./headers?type=userPaidStatus&lang='+lang,'./user-paid-status-data');
	
}
var nationalId =$("body").attr("session-value") =='null' ? null : $("body").attr("session-value");
function table(url,dataUrl){
	var txnIdValue = $("body").attr("session-valueTxnID");
	var txn= (txnIdValue == 'null' && transactionIDValue == undefined)? $('#transactionID').val() : transactionIDValue;
	var request={
			"origin":"self",
			"endDate":$('#endDate').val(),
			"startDate":$('#startDate').val(),
			"taxPaidStatus":parseInt($('#taxPaidStatus').val()),
			"featureId":parseInt(12),
			"userTypeId": parseInt(17),
			"userType":"End User",
			"status":parseInt($('#recordStatus').val()),
			"txnId":txn,
			//"userId":330,
			"consignmentStatus": null,
			"nid": nationalId == null ? $('#nId').val() : nationalId
	}


	if(data_lang_param=='km'){
		var langFile="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json";
	}
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#data-table-simple").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				"oLanguage": {  
					"sUrl": langFile  
				},
				ajax: {
					url : dataUrl,
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify(request); 

					}

				},
				"columns": result
			});
			$('div#initialloader').delay(300).fadeOut('slow');
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}

var x = 1; //initlal text box count
var id=2;
$(document).ready(function () {
	
	$.getJSON('./getSourceTypeDropdown/DOC_TYPE/12', function(data) {

		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].tagId).text(data[i].interp).attr("docValue",data[i].value).appendTo('#doc_type');

		}
	});
	
	

	$.getJSON('./addMoreFile/more_files_count', function(data) {
		console.log(data);
		
		localStorage.setItem("maxCount", data.value);
		
	});

		//var max_fields = 2; //maximum input boxes allowed
		var max_fields =localStorage.getItem("maxCount");
		console.log("max_fields from api="+max_fields);

	$('#langlist').val(data_lang_param);
	//var max_fields = 15; //maximum input boxes allowed
	var wrapper = $(".mainDeviceInformation"); //Fields wrapper
	var add_button = $(".add_field_button"); //Add button ID
	
	$(add_button).click(function (e) { //on add input button click
		e.preventDefault();
		var nationType= localStorage.getItem("nationType");
		if (x < max_fields) { //max input box allowed
			x++; //text box increment

			if (nationType=='1')
			{
			$(wrapper).append(
					
				//	'<div id="deviceInformation'+id+'" class="deviceInformation"><div class="row"><div class="col s12 m6"><label for="deviceIdType'+id+'">'+$.i18n('deviceIDType')+' <span class="star">*</span></label><select oninput="InvalidMsg(this,"select",'+$.i18n('deviceIDType')+')  oninvalid="InvalidMsg(this,"select",'+$.i18n('deviceIDType')+') class="browser-default" required id="deviceIdType'+id+'"><option value="" disabled selected>'+$.i18n('selectDeviceIDType')+'</option></select></div><div class="col s12 m6"><label for="multipleSimStatus'+id+'">'+$.i18n('multipleSimStatus')+'</label><select class="browser-default"  id="multipleSimStatus'+id+'"><option value="" disabled selected>'+$.i18n('multipleSimStatus')+'</option></select></div><div class="col s12 m6"><label for="deviceType">'+$.i18n('deviceType')+' </label><select class="browser-default"  id="deviceType'+id+'"><option value="" disabled selected>'+$.i18n('deviceType')+'</option></select></div><div class="col s12 m6"><label for="deviceCountry">'+$.i18n('country')+'</label><select id="country'+id+'"  class="browser-default" class="mySelect" style="padding-left: 0;" ></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber'+id+'" name="serialNumber'+id+'"  pattern="[A-Za-z0-9]{0,15}" title="Please enter your device serial number first" maxlength="15"><label for="serialNumber'+id+'">'+$.i18n('deviceSerialNumber')+'</label></div><div class="row"><div class="col s12 m6" ><label for="deviceStatus'+id+'">'+$.i18n('deviceStatus')+'</label><select class="browser-default"  style="margin-top: 5px;" id="deviceStatus'+id+'"><option value="" disabled selected>'+$.i18n('selectDeviceStatus')+'</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEIA'+id+'" required name="IMEI1" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIA'+id+'">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEIB'+id+'" name="IMEI2" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIB'+id+'">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEIC'+id+'" name="IMEI3" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIC'+id+'">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEID'+id+'" name="IMEI4[]" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEID'+id+'">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">'+$.i18n('remove')+'</div></div>'

					
					'<div id="deviceInformation'+id+'" class="deviceInformation"><div class="row"><div class="col s12 m6"><label for="deviceIdType'+id+'">'+$.i18n('deviceIDType')+' <span class="star">*</span></label><select class="browser-default"  id="deviceIdType'+id+'" oninput="InvalidMsg(this,\'select\',\''+$.i18n('deviceIDType')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('deviceIDType')+'\');" required ><option value="" disabled selected>'+$.i18n('selectDeviceIDType')+'</option></select></div><div class="col s12 m6"><label for="multipleSimStatus'+id+'">'+$.i18n('multipleSimStatus')+'</label><select class="browser-default" oninput="InvalidMsg(this,\'select\',\''+$.i18n('multipleSimStatus')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('multipleSimStatus')+'\');"  id="multipleSimStatus'+id+'"><option value="" disabled selected>'+$.i18n('multipleSimStatus')+'</option></select></div><div class="col s12 m6"><label for="deviceType">'+$.i18n('deviceType')+' </label><select class="browser-default" oninput="InvalidMsg(this,\'select\',\''+$.i18n('deviceType')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('deviceType')+'\');" id="deviceType'+id+'"><option value="" disabled selected>'+$.i18n('deviceType')+'</option></select></div><div class="col s12 m6"><label for="deviceCountry">'+$.i18n('countryBoughtFrom')+'</label><select id="country'+id+'" oninput="InvalidMsg(this,\'select\',\''+$.i18n('countryBoughtFrom')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('countryBoughtFrom')+'\');"  class="browser-default" class="mySelect" style="padding-left: 0;" ></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber'+id+'" oninput="InvalidMsg(this,\'input\',\''+$.i18n('deviceSerialNumber')+'\');"  oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('deviceSerialNumber')+'\');" name="serialNumber'+id+'"  pattern="[A-Za-z0-9]{0,15}" title="Please enter your device serial number first" maxlength="15"><label for="serialNumber'+id+'">'+$.i18n('deviceSerialNumber')+'</label></div><div class="row"><div class="col s12 m6" ><label for="deviceStatus'+id+'">'+$.i18n('deviceStatus')+'</label><select class="browser-default" oninput="InvalidMsg(this,\'input\',\''+$.i18n('deviceStatus')+'\');"  oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('deviceStatus')+'\');"  style="margin-top: 5px;" id="deviceStatus'+id+'"><option value="" disabled selected>'+$.i18n('selectDeviceStatus')+'</option></select></div></div><div class="col s12 m6"><label for="taxStatus'+id+'">'+$.i18n('taxPaidStatus')+' <span class="star">*</span></label><select class="browser-default" oninput="InvalidMsg(this,\'select\',\''+$.i18n('taxPaidStatus')+'\');"oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('taxPaidStatus')+'\');" required id="taxStatus'+id+'"><option value="" disabled selected>'+$.i18n('taxPaidStatus')+'</option></select></div></div><div class="input-field col s12 m6 l6"><input type="text" name="Price" id="Price'+id+'" onkeyup=showHideCurrency()  oninput="InvalidMsg(this,\'input\',\''+$.i18n('price')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('price')+'\');"  maxlength="30"><label for="Price'+id+'">'+$.i18n('price')+' <span class="star"></span></label></div><div id="CurrencyDiv'+id+'" style="display:none" class="col s12 m6"><label for="Currency'+id+'">'+$.i18n('currency')+' <span class="star">*</span></label><select class="browser-default" oninput="InvalidMsg(this,\'input\',\''+$.i18n('currency')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('currency')+'\');"  id="Currency'+id+'"><option value="" disabled selected>'+$.i18n('selectCurrency')+'</option></select></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEIA'+id+'"  required name="IMEI1" pattern="[0-9]{15,16}"  maxlength="16" oninput="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"><label for="IMEIA'+id+'">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEIB'+id+'" name="IMEI2" pattern="[0-9]{15,16}"title="" oninput="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');" maxlength="16"><label for="IMEIB'+id+'">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEIC'+id+'" name="IMEI3" pattern="[0-9]{15,16}"title="" oninput="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');" maxlength="16"><label for="IMEIC'+id+'">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEID'+id+'" name="IMEI4[]" pattern="[0-9]{15,16}"title=""  oninput="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');" maxlength="16"><label for="IMEID'+id+'">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">'+$.i18n('remove')+'</div></div>'
			);  //add input box
			}
			else if(nationType=='2'){
				
				$(wrapper).append(
						
						//	'<div id="deviceInformation'+id+'" class="deviceInformation"><div class="row"><div class="col s12 m6"><label for="deviceIdType'+id+'">'+$.i18n('deviceIDType')+' <span class="star">*</span></label><select oninput="InvalidMsg(this,"select",'+$.i18n('deviceIDType')+')  oninvalid="InvalidMsg(this,"select",'+$.i18n('deviceIDType')+') class="browser-default" required id="deviceIdType'+id+'"><option value="" disabled selected>'+$.i18n('selectDeviceIDType')+'</option></select></div><div class="col s12 m6"><label for="multipleSimStatus'+id+'">'+$.i18n('multipleSimStatus')+'</label><select class="browser-default"  id="multipleSimStatus'+id+'"><option value="" disabled selected>'+$.i18n('multipleSimStatus')+'</option></select></div><div class="col s12 m6"><label for="deviceType">'+$.i18n('deviceType')+' </label><select class="browser-default"  id="deviceType'+id+'"><option value="" disabled selected>'+$.i18n('deviceType')+'</option></select></div><div class="col s12 m6"><label for="deviceCountry">'+$.i18n('country')+'</label><select id="country'+id+'"  class="browser-default" class="mySelect" style="padding-left: 0;" ></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber'+id+'" name="serialNumber'+id+'"  pattern="[A-Za-z0-9]{0,15}" title="Please enter your device serial number first" maxlength="15"><label for="serialNumber'+id+'">'+$.i18n('deviceSerialNumber')+'</label></div><div class="row"><div class="col s12 m6" ><label for="deviceStatus'+id+'">'+$.i18n('deviceStatus')+'</label><select class="browser-default"  style="margin-top: 5px;" id="deviceStatus'+id+'"><option value="" disabled selected>'+$.i18n('selectDeviceStatus')+'</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEIA'+id+'" required name="IMEI1" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIA'+id+'">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEIB'+id+'" name="IMEI2" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIB'+id+'">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEIC'+id+'" name="IMEI3" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIC'+id+'">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEID'+id+'" name="IMEI4[]" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEID'+id+'">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">'+$.i18n('remove')+'</div></div>'

							
							'<div id="deviceInformation'+id+'" class="deviceInformation"><div class="row"><div class="col s12 m6"><label for="deviceIdType'+id+'">'+$.i18n('deviceIDType')+' <span class="star">*</span></label><select class="browser-default"  id="deviceIdType'+id+'" oninput="InvalidMsg(this,\'select\',\''+$.i18n('deviceIDType')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('deviceIDType')+'\');" required ><option value="" disabled selected>'+$.i18n('selectDeviceIDType')+'</option></select></div><div class="col s12 m6"><label for="multipleSimStatus'+id+'">'+$.i18n('multipleSimStatus')+'</label><select class="browser-default" oninput="InvalidMsg(this,\'select\',\''+$.i18n('multipleSimStatus')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('multipleSimStatus')+'\');"  id="multipleSimStatus'+id+'"><option value="" disabled selected>'+$.i18n('multipleSimStatus')+'</option></select></div><div class="col s12 m6"><label for="deviceType">'+$.i18n('deviceType')+' </label><select class="browser-default" oninput="InvalidMsg(this,\'select\',\''+$.i18n('deviceType')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('deviceType')+'\');" id="deviceType'+id+'"><option value="" disabled selected>'+$.i18n('deviceType')+'</option></select></div><div class="col s12 m6"><label for="deviceCountry">'+$.i18n('countryBoughtFrom')+'</label><select id="country'+id+'" oninput="InvalidMsg(this,\'select\',\''+$.i18n('countryBoughtFrom')+'\');"  oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('countryBoughtFrom')+'\');"  class="browser-default" class="mySelect" style="padding-left: 0;" ></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber'+id+'" oninput="InvalidMsg(this,\'input\',\''+$.i18n('deviceSerialNumber')+'\');"  oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('deviceSerialNumber')+'\');" name="serialNumber'+id+'"  pattern="[A-Za-z0-9]{0,15}" title="Please enter your device serial number first" maxlength="15"><label for="serialNumber'+id+'">'+$.i18n('deviceSerialNumber')+'</label></div><div class="row"><div class="col s12 m6" ><label for="deviceStatus'+id+'">'+$.i18n('deviceStatus')+'</label><select class="browser-default" oninput="InvalidMsg(this,\'input\',\''+$.i18n('deviceStatus')+'\');"  oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('deviceStatus')+'\');"  style="margin-top: 5px;" id="deviceStatus'+id+'"><option value="" disabled selected>'+$.i18n('selectDeviceStatus')+'</option></select></div><div class="col s12 m6"><label for="taxStatus'+id+'">'+$.i18n('taxPaidStatus')+' <span class="star">*</span></label><select class="browser-default" oninput="InvalidMsg(this,\'select\',\''+$.i18n('taxPaidStatus')+'\');"oninvalid="InvalidMsg(this,\'select\',\''+$.i18n('taxPaidStatus')+'\');" required id="taxStatus'+id+'"><option value="" disabled selected>'+$.i18n('taxPaidStatus')+'</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEIA'+id+'"  required name="IMEI1" pattern="[0-9]{15,16}"  maxlength="16" oninput="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"><label for="IMEIA'+id+'">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEIB'+id+'" name="IMEI2" pattern="[0-9]{15,16}"title="" oninput="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');" maxlength="16"><label for="IMEIB'+id+'">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEIC'+id+'" name="IMEI3" pattern="[0-9]{15,16}"title="" oninput="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');" maxlength="16"><label for="IMEIC'+id+'">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEID'+id+'" name="IMEI4[]" pattern="[0-9]{15,16}"title=""  oninput="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');"oninvalid="InvalidMsg(this,\'input\',\''+$.i18n('IMEIValidationMSg')+'\');" maxlength="16"><label for="IMEID'+id+'">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">'+$.i18n('remove')+'</div></div>'
					);
			}
			populateCountries
			(   
					"country"+id
			);

			var allowed =localStorage.getItem("allowed");

			$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
				var dropdownid=id-1;
				if(dropdownid <= allowed){

					$('#taxStatus'+dropdownid).prop('disabled', 'disabled');
					$('<option  selected>').val("2").text("Regularized").appendTo('#taxStatus'+dropdownid);
						
				}
				else{
					for (i = 0; i < data.length; i++) {
						$('<option>').val(data[i].value).text(data[i].interp)
						.appendTo('#taxStatus'+dropdownid);

					}
				}
			});

			$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#deviceType'+dropdownid);

				}
			});
			$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#deviceIdType'+dropdownid);

				}
			});
			$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#multipleSimStatus'+dropdownid);

				}
			});
			$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#deviceStatus'+dropdownid);

				}
			});
			$.getJSON('./getDropdownList/CURRENCY', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#Currency'+dropdownid);

				}
			});



			id++;
		}
	});
	$(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
		e.preventDefault();
		var Iid=id-1;
		 /*alert("@@@"+Iid)*/
		console.log("  Iid==== "+Iid);
		 $('#deviceInformation'+Iid).remove();
		$(this).parent('div').remove();
		x--;
		id--;
		console.log("id=="+id);
	})
});





function submitEndUserDeviceInfo(){
	var formData= new FormData();


	var nationalID=$('#endUserNID').val();
	var endUserNID=$('#endUserNID').val();
	var firstName=$('#endUserfirstName').val();
	var middleName=$('#endUsermiddleName').val();
	var lastName=$('#endUserlastName').val();
	var address=$('#address').val();
	var streetNumber=$('#streetNumber').val();
	var locality=$('#locality').val();
	var village=$('#village').val();
	var country=$('#country').val();
	var state=$('#state').val();
	var email=$('#endUseremailID').val();
	var phone=$('#phone').val();
	var state=$('#state').val();
	var docType =$('#doc_type').val();
	var  doc_type_numeric =$("#doc_type option:selected").attr("docValue");
	var district=$('#endUserdistrict').val();
	var commune=$('#commune').val();
	var postalcode=$('#pin').val();
	var nationality=$('#nationality').val();

	var departmentName=$('#departmentName').val();
	var departmentFileID=$('#endUserDepartmentId').val();
	var departmentID=$('#endUserdepartmentID').val();
	var postalcode=$('#pin').val();

	var isVip=$('input[name="selectvip"]:checked').val();
	var onVisa=$('input[name="onVisa"]:checked').val();

	if(onVisa==undefined){
		onVisa='N';
	}
	var visaType=$('#visaType').val();
	var visaDate=$('#datepicker').val();
	var visaNumber=$('#visaNumber').val();
	var visaExpirydate=$('#datepicker1').val();
	var visaImage=$('#visaImage').val();

	if(visaNumber=="")
	{
		visaNumber=null;
	}

	if(visaImage==""){
		visaImage="";

	}
	else if(visaImage==undefined){
		visaImage="";

	}
	else{
		visaImage=$('#visaImage').val().replace('C:\\fakepath\\','');
		formData.append('visaImage', $('#visaImage')[0].files[0]);

	}


	var visaDb=[];
	var visa={
			"visaType":visaType,
			"visaExpiryDate":visaExpirydate,
			"visaFileName": visaImage,
			"visaNumber": visaNumber,
			"visaType": visaType
	}
	visaDb.push(visa);
	if(departmentFileID=="")
	{
		departmentFileID="";


	}
	else if(departmentFileID==undefined){
		departmentFileID="";

	}
	else{
		departmentFileID=$('#endUserDepartmentId').val().replace('C:\\fakepath\\','');
		formData.append('endUserDepartmentFile', $('#endUserDepartmentId')[0].files[0]); 

	}
	var departmentDetails={

			"departmentId": departmentID,
			"name": departmentName,
			"departmentFilename":departmentFileID
	}



	var fieldId=1;
	var regularizeDeviceDbs =[];
	$('.deviceInformation').each(function() {
		var deviceType1=$('#deviceType'+fieldId).val();
		var serialNumber1=$('#serialNumber'+fieldId).val();
		var deviceIdType1=$('#deviceIdType'+fieldId).val();
		var deviceStatus1=$('#deviceStatus'+fieldId).val();
		var IMEI1=$('#IMEIA'+fieldId).val();
		var IMEI2=$('#IMEIB'+fieldId).val();
		var IMEI3=$('#IMEIC'+fieldId).val();
		var IMEI4=$('#IMEID'+fieldId).val();
		var deviceCountry=$('#country'+fieldId).val();
		var multipleSimStatus1=$('#multipleSimStatus'+fieldId).val();
		var taxStatus1=$('#taxStatus'+fieldId).val();
		var Price1=$('#Price'+fieldId).val();
		var Currency1=$('#Currency'+fieldId).val();




		var deviceInfo=
		{
				"country": deviceCountry,

				"deviceIdType": parseInt(deviceIdType1),
				"deviceSerialNumber": serialNumber1,
				"deviceStatus": parseInt(deviceStatus1),
				"deviceType": parseInt(deviceType1),
				"firstImei": IMEI1,
				"secondImei": IMEI2,
				"thirdImei": IMEI3,
				"fourthImei": IMEI4,
				"multiSimStatus": deviceStatus1,
				"nid":nationalID,
				"origin":"Self",
				"currency": parseInt(Currency1),
				"price": parseFloat(Price1),
				"taxPaidStatus": parseInt(taxStatus1)

		}
		regularizeDeviceDbs.push(deviceInfo);
		fieldId++;


	});



	var request={
			"country": country,
			"email": email,
			"firstName": firstName,
			"lastName": lastName,
			"locality": locality,
			"middleName": middleName,
			"nid": nationalID,
			"phoneNo": phone,
			"propertyLocation": address,
			"province": state,
			"entryDateInCountry":visaDate,
			"street": streetNumber,
			"regularizeDeviceDbs":regularizeDeviceDbs,
			"visaDb":visaDb,
			"nationality":nationality,
			"userDepartment":departmentDetails,
			"district":district,
			"isVip":isVip,
			"onVisa":onVisa,
			"commune":commune,
			"village":village,
			"postalCode":postalcode,
			"docType":doc_type_numeric 
	}

	formData.append('uploadnationalID', $('#uploadnationalID')[0].files[0]);
	formData.append("request",JSON.stringify(request));
	formData.append("sourceType",'enduser');
	formData.append("docType",docType);
	
	$.ajax({
		url: './registerEndUserDevice',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {

			console.log(data);


			if(data.errorCode==0){

//				$('#sucessMessage').text('');
				$('#endUserRegisterDeviceModal').openModal({dismissible:false});;
				$('#endUsertXnId').text(data.txnId);
				$("#endUserRegisterButton").prop('disabled', true);
			}
			else{
//				$('#sucessMessage').text('');
				$('#endUserRegisterDeviceModal').openModal({dismissible:false});;
				$('#sucessMessageId').text('');
				$('#sucessMessageId').text($.i18n(data.tag));
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	return false;
}


$(document).on("keyup", "#visaNumber", function(e) {
	var visaNumber=$('#visaNumber').val();

	if(visaNumber.length<'1' )
	{
		$("#datepicker1").attr("required", false);
		/*$('#currency').attr("disabled",true);*/
		$('#visaExpiryDateDiv').hide();

	}
	else
	{
		$("#datepicker1").attr("required", true);
		/*$('#currency').attr("disabled",false);*/
		$('#visaExpiryDateDiv').show();

	}
});


function fileTypeValueChanges(id) {

	var uploadedFileName = $("#"+id).val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	//alert("file extension=="+uploadedFileName)
	var ext = uploadedFileName.split('.').pop();

	var fileSize = ($("#"+id)[0].files[0].size);
	/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
	alert("----"+fileSize);*/
	fileSize = Math.floor(fileSize/1000);

	//alert(uploadedFileName+"----------"+ext+"----"+fileSize)
	var areEqual =ext.toLowerCase()=='png';
	//alert(areEqual);
	if(areEqual==true)
		{
		ext='PNG';
		}
	if (uploadedFileName.length > 30) {
		$('#fileFormateModal').openModal({dismissible:false});;
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageMessage'));
	} 
	else if(ext !='PNG')
	{
		$('#fileFormateModal').openModal({
			dismissible:false
		});
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageMessage'));

	}
	else if(fileSize>=100){
		$('#fileFormateModal').openModal({
			dismissible:false
		});
		$('#fileErrormessage').text('');
		$('#fileErrormessage').text($.i18n('imageSize'));	
	}



}


function clearFileName() {
	$('#nidPlaceHolder').val('');
	$("#uploadnationalID").val('');
	$('#fileFormateModal').closeModal();
}


function visaImageValidation() {
	var uploadedFileName = $("#visaImage").val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	//alert("file extension=="+uploadedFileName)
	var ext = uploadedFileName.split('.').pop();

	var fileSize = ($("#visaImage")[0].files[0].size);
	/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
	alert("----"+fileSize);*/
	fileSize = Math.floor(fileSize/1000);

	var areEqual =ext.toLowerCase()=='png';
	//alert(areEqual);
	if(areEqual==true)
		{
		ext='PNG';
		}

	if (uploadedFileName.length > 30) {
		$('#visafileFormateModal').openModal({dismissible:false});
		$('#visafileErrormessage').text('');
		$('#visafileErrormessage').text($.i18n('imageMessage'));
	} 
	else if(ext!='PNG')
	{
		$('#visafileFormateModal').openModal({
			dismissible:false
		});
		$('#visafileErrormessage').text('');
		$('#visafileErrormessage').text($.i18n('imageMessage'));

	}
	else if(fileSize>='100'){
		$('#visafileFormateModal').openModal({
			dismissible:false
		});
		$('#visafileErrormessage').text('');
		$('#visafileErrormessage').text($.i18n('imageSize'));	
	}



}


function clearVisaName() {
	$('#visaImage').val('');
	$("#ensUserVisaPlaceHolder").val('');
	$('#visafileFormateModal').closeModal();
}



function deptImageValidation() {
	var uploadedFileName = $("#endUserDepartmentId").val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	//alert("file extension=="+uploadedFileName)
	var ext = uploadedFileName.split('.').pop();

	var fileSize = ($("#endUserDepartmentId")[0].files[0].size);
	/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
	alert("----"+fileSize);*/
	fileSize = Math.floor(fileSize/1000);
	
	var areEqual =ext.toLowerCase()=='png';
	//alert(areEqual);
	if(areEqual==true)
		{
		ext='PNG';
		}

	if (uploadedFileName.length > 30) {
		$('#DeptfileFormateModal').openModal({dismissible:false});
		$('#DeptfileErrormessage').text('');
		$('#DeptfileErrormessage').text($.i18n('imageMessage'));
	} 
	else if(ext!='PNG')
	{
		$('#DeptfileFormateModal').openModal({
			dismissible:false
		});
		$('#DeptfileErrormessage').text('');
		$('#DeptfileErrormessage').text($.i18n('imageMessage'));

	}
	
	else if(fileSize>='100'){
		$('#DeptfileFormateModal').openModal({
			dismissible:false
		});
		$('#DeptfileErrormessage').text('');
		$('#DeptfileErrormessage').text($.i18n('imageSize'));	
	}



}


function clearDeptName() {
	$('#endUserDepartmentId').val('');
	$("#endUSerNidaPlaceholder").val('');
	$('#visafileFormateModal').closeModal();
}



function viewDeviceHistory() {
	historytable("./headers?type=userPaidStatus","./user-paid-status-data");
};



function historytable(url,dataUrl){

	if(data_lang_param=='km'){
		var langFile="//cdn.datatables.net/plug-ins/1.10.20/i18n/Khmer.json";
	}
	$.ajax({
		url: url,
		type: 'POST',
		dataType: "json",
		success: function(result){
			var table=	$("#data-table-history").DataTable({
				destroy:true,
				"serverSide": true,
				orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"bSearchable" : true,
				"oLanguage": {  
					"sUrl": langFile  
				},
				ajax: {
					url : dataUrl,
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify({						
							"nid": nationalId,
							"taxPaidStatus":3,
								"featureId":12
						}); 
					}

				},
				"columns": result
			});
			$('#viewBlockDevices').openModal({dismissible:false});
			$('div#initialloader').delay(300).fadeOut('slow');
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}
function deleteByImei(imei){
	$('#deleteMsg').openModal({dismissible:false});
	window.imei=imei;
}

function accept(){

	$.ajax({
		url : "./delete/"+window.imei,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'DELETE',
		success : function(data, textStatus, xhr) {

			$('#confirmDeleteMsg').openModal({dismissible:false});
			$('#deleteMsg').closeModal();
			/*if(data.errorCode == 200){
					$("#responseMsg").text(data.message);
				}else if(data.errorCode == 0){
					$("#responseMsg").text(data.message);
				}*/
		},
		error : function() {
			console.log("Error");
		}
	});
}

function viewDetails(imei){ 
		$('#viewbyImei').val(imei);
		$('#viewDeviceForm').submit();
		/*window.location.href="./view-device-information/"+imei;*/


}


function regularizedCount(nationType){
	console.log("----"+nationType)
	var allowed='';
	if(nationType==undefined)
		{
		nationType=1;
		}
	var nid= nationalId == 'null' ? null : nationalId;
	$.ajax({
		url: './countByNid?nid='+nid+"&nationType="+nationType,
		type: 'GET',
		processData: false,
		contentType: false,
		async: false,
		success: function (data, textStatus, jqXHR) {

			 allowed=data.data.allowed;
			var current=data.data.current;
			
			localStorage.setItem("allowed", allowed);
			localStorage.setItem("current", current);
			localStorage.setItem("nationType", nationType);
			
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
	
	return allowed;
}

$(document).on("keyup", "#Price1", function(e) {
	var totalPrice=$('#Price1').val();
	if(totalPrice.length<'1' )
	{
		$("#Currency1").attr("required", false);
		/*$('#currency').attr("disabled",true);*/
		$('#CurrencyDiv').hide();

		$("#Currency1")[0].selectedIndex = 0;

	}
	else
	{
		$('#Currency1').prop('required',true);
		//$("#currency").attr("required", true);
		/*$('#currency').attr("disabled",false);*/
		$('#CurrencyDiv').show();

	}
});

function showHideCurrency() {
	//alert(id-1);
	 var cuurecyId=id-1;
	 //alert(ssss);
	var totalPriceaaa=$('#Price'+cuurecyId).val();
	/*$('#country'+fieldId).val();*/
	//alert(totalPriceaaa)
	if(totalPriceaaa.length<'1' )
	{
		$("#Currency"+cuurecyId).attr("required", false);
		/*$('#currency').attr("disabled",true);*/
		$('#CurrencyDiv'+cuurecyId).hide();

		$("#Currency"+cuurecyId)[0].selectedIndex = 0;

	}
	else
	{
		$('#Currency'+cuurecyId).prop('required',true);
		//$("#currency").attr("required", true);
		/*$('#currency').attr("disabled",false);*/
		$('#CurrencyDiv'+cuurecyId).show();

	}
}
function refreshContent(){
	$('#payNowTaxPayment,#confirmDeleteMsg,#regularisedDevice').closeModal();
	window.location.reload(true);
}


$('input[type="checkbox"]').click(function(){
    if($(this).prop("checked") == true){
        
        $('#endUserRegisterButton').prop('disabled', false);
    }
    else if($(this).prop("checked") == false){
    	 $('#endUserRegisterButton').prop('disabled', true);
       
    }
});



function historyRecord(txnID){
	console.log("txn id=="+txnID)
	$("#tableOnModal").openModal({dismissible:false});
	 var filter =[];
	 var formData= new FormData();
	 var filterRequest={
			"tableName": "regularize_device_db_aud",
			"dbName" : "ceirconfig",
			"txnId":txnID
	}
	formData.append("filter",JSON.stringify(filterRequest));	
	if(data_lang_param=='km'){
		var langFile='../resources/i18n/khmer_datatable.json';
	}
	console.log("22");
	$.ajax({
		url: 'Consignment/consignment-history',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function(result){
			var dataObject = eval(result);
			//alert(JSON.stringify(dataObject.data))
			$('#data-table-history2').dataTable({
				 "order" : [[1, "asc"]],
				 destroy:true,
				"serverSide": false,
				 orderCellsTop : true,
				"ordering" : false,
				"bPaginate" : true,
				"bFilter" : true,
				"bInfo" : true,
				"scrollX": true,
				"bSearchable" : true,
				 "data": dataObject.data,
				 "columns": dataObject.columns
			
		    });
			$('div#initialloader').delay(300).fadeOut('slow');
	}
		
});

	$('.datepicker').on('mousedown',function(event){
	event.preventDefault();
});

	
	
	
	
}