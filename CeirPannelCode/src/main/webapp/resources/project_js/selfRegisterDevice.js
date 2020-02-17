

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


/*var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';

$.i18n().locale = lang;	

$.i18n().load( {
	'en': './resources/i18n/en.json',
	'km': './resources/i18n/km.json'
} ).done( function() { 
});
*/




function showCambodianUserForm()
{
	$("#askVisaDetails").css("display", "none"); 
	$("#visaDetails").css("display", "none"); 
	$("#nationalityDiv").css("display", "none"); 
	$('#endUserLabelNID').text('National Id ');
	$('#nidType').text('Upload nid Image ');
	
	$("#nidPlaceHolder").attr("placeholder", "Upload Nid Image").val("").focus().blur();
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
	 
	 
	 
		$("#endUserLabelNID").append('<span class="star">*</span>');
		$("#nidType").append('<span class="star">*</span>');
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
	$('#endUserLabelNID').text('Passport Number ');
	$('#nidType').text('Upload Passport Image ');
	$("#askVisaDetails").css("display", "block");
	$("#nationalityDiv").css("display", "block");
	$("#onVisaNo").prop("checked", true);
	$("#nidPlaceHolder").attr("placeholder", "Upload Passport Image").val("").focus().blur();
	
	$("#nationality").attr("required", true);
	$("#endUserLabelNID").append('<span class="star">*</span>');
	$("#nidType").append('<span class="star">*</span>');
	
	 
	 /*$("#departmentName").attr("required", true);
	 $("#endUserdepartmentID").attr("required", true);
	 $("#endUserDepartmentId").attr("required", true);
	 $("#visaType").attr("required", true);
	 $("#datepicker").attr("required", true);
	 $("#datepicker1").attr("required", true);
	 $("#visaImage").attr("required", true);*/
	
}

function showVisaDetails(){
	$("#visaDetails").css("display", "block");
	$("#datepicker").attr("required", true);
	 //$("#visaNumber").attr("required", true);
	 $("#visaImage").attr("required", true);
	 $("#datepicker1").attr("required", true);
	 $("#visaType").attr("required", true);
	 
}
function hideVisaDetails(){
	$("#visaDetails").css("display", "none");
	 $('#visaDetails').find('input:text').val('');
	 $('#visaDetails').find('input:file').val('');
	 
	 $("#datepicker").attr("required", false);
	 $("#visaNumber").attr("required", false);
	 $("#visaImage").attr("required", false);
	 $("#datepicker1").attr("required", false);
	 $("#visaType").attr("required", false);
}



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






$(document).ready(function () {
	var max_fields = 15; //maximum input boxes allowed
	var wrapper = $(".mainDeviceInformation"); //Fields wrapper
	var add_button = $(".add_field_button"); //Add button ID
	var x = 1; //initlal text box count
	var id=2;
	$(add_button).click(function (e) { //on add input button click
		e.preventDefault();
		if (x < max_fields) { //max input box allowed
			x++; //text box increment


			$(wrapper).append(
					/*  '<div style="margin-top:30px;"><div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" id='"deviceType"+id+"'><option value="" disabled selected>Device Type</option><option value='Handheld'>Handheld</option><option value='MobilePhone'>Mobile Phone/Feature phone</option><option value='Vehicle'>Vehicle</option><option value='Portable'>Portable(include PDA)</option><option value='Module'>Module</option><option value='Dongle'>Dongle</option><option value='WLAN'>WLAN Router</option><option value='Modem'>Modem</option><option value='Smartphone'>Smartphone</option><option value='Computer'>Connected Computer</option><option value='Tablet'>Tablet</option><option value='e-Book'>e-Book</option></select></div><div class='col s12 m6'><label for='deviceIdType'>Device ID Type <span class='star'>*</span></label><select class='browser-default' id='deviceIdType'><option value="" disabled selected>Select Device ID Type</option><option value='IMEI'>IMEI</option><option value='ESN'>ESN</option><option value='MEID'>MEID</option></select></div><div class='col s12 m6'><label for='deviceType'>Multiple Sim Status <span class='star'>*</span></label><select class='browser-default' id='deviceType'><option value="" disabled selected>Multiple Sim Status</option><option value='Yes'>Yes</option><option value='No'>No</option></select></div><div class='col s12 m6'><label for='country'>Country bought From <span class='star'>*</span></label><select id='country1' class='browser-default' class='mySelect' style='padding-left: 0;' required></select></div><div class='input-field col s12 m6' style='margin-top: 28px;'><input type='text' id='serialNumber1' name='serialNumber1' pattern='[0-9]' title='Please enter your device serial number first' maxlength='20'><label for='serialNumber1'>Device Serial Number <span class='star'>*</span></label></div><div class='col s12 m6'><label for='deviceType'>Tax paid Status <span class='star'>*</span></label><select class='browser-default' id='deviceType'><option value="" disabled selected>Tax paid Status</option><option value='Regularized'>Regularized</option><option value='Paid'>Paid</option><option value='NotPaid'>Not Paid</option></select></div></div><div class='row'><div class='col s12 m6' style='margin-top: -10px;'><label for='taxStatus'>Device Status <span class='star'>*</span></label><select class='browser-default' id='taxStatus'><option value='' disabled selected>Select Device Status</option><option value='New'>New</option><option value='Used'>Used</option><option value='Refurbished'>Refurbished</option></select></div><div class='input-field col s12 m6 l6'><input type='text' name='Price' id='Price' maxlength='30'><label for='Price'>Price <span class='star'>*</span></label></div><div class='col s12 m6'><label for='Currency'>Currency <span class='star'>*</span></label><select class='browser-default' id='Currency'><option value='' disabled selected>Select Currency</option><option value='Regularized'>$</option><option value='Paid'>$</option><option value="NotPaid">$</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI1">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI2">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI3">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI4">4</label></div></div><div style="cursor:pointer;background-color:red;' class='remove_field btn right btn-info'>Remove</div></div>'*/
					'<div id="deviceInformation'+id+'" class="deviceInformation"><div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" required id="deviceType'+id+'"><option value="" disabled selected>Device Type</option></select></div><div class="col s12 m6"><label for="deviceIdType'+id+'">Device ID Type <span class="star">*</span></label><select class="browser-default" required id="deviceIdType'+id+'"><option value="" disabled selected>Select Device ID Type</option></select></div><div class="col s12 m6"><label for="multipleSimStatus'+id+'">Multiple Sim Status <span class="star">*</span></label><select class="browser-default" required id="multipleSimStatus'+id+'"><option value="" disabled selected>Multiple Sim Status</option></select></div><div class="col s12 m6"><label for="deviceCountry">Country bought From <span class="star">*</span></label><select id="country'+id+'"  class="browser-default" class="mySelect" style="padding-left: 0;" required></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber'+id+'" name="serialNumber'+id+'" required pattern="[A-Za-z0-9]{0,15}" title="Please enter your device serial number first" maxlength="15"><label for="serialNumber'+id+'">Device Serial Number <span class="star">*</span></label></div></div><div class="row"><div class="col s12 m6" style="margin-top: -10px;"><label for="deviceStatus'+id+'">Device Status <span class="star">*</span></label><select class="browser-default" required id="deviceStatus'+id+'"><option value="" disabled selected>Select Device Status</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEIA'+id+'" required name="IMEI1" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIA'+id+'">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEIB'+id+'" name="IMEI2" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIB'+id+'">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEIC'+id+'" name="IMEI3" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIC'+id+'">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEID'+id+'" name="IMEI4[]" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEID'+id+'">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">-Remove</div></div>'

			);  //add input box

			populateCountries
			(   
					"country"+id
			);



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

			$.getJSON('./getDropdownList/CURRENCY', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#Currency'+dropdownid);

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


			id++;
		}
	});
	$(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
		e.preventDefault();
		$(this).parent('div').remove();
		x--;
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
	var docType=$('#doc_type').val();
	var doc_type_numeric=$("#doc_type option:selected").attr("docValue");
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
			"entryDateInCountry":visaDate,
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

	


		var deviceInfo=
		{
				"country": deviceCountry,

				"deviceIdType": parseInt(deviceIdType1),
				"deviceSerialNumber": serialNumber1,
				"deviceStatus": parseInt(deviceStatus1),
				"deviceType": parseInt(deviceType1),
				"firstImei": parseInt(IMEI1),
				"secondImei": parseInt(IMEI2),
				"thirdImei": parseInt(IMEI3),
				"fourthImei": parseInt(IMEI4),
				"multiSimStatus": deviceStatus1,
				"nid":nationalID,
				"origin":"SELF"

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
			"doc_type_numeric":docType,
			"docType":doc_type_numeric
	}

	formData.append('uploadnationalID', $('#uploadnationalID')[0].files[0]);
	formData.append("request",JSON.stringify(request));
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
				$('#endUserRegisterDeviceModal').openModal();
				$('#endUsertXnId').text(data.txnId);
				$("#endUserRegisterButton").prop('disabled', true);
			}
			else{
//				$('#sucessMessage').text('');
				$('#endUserRegisterDeviceModal').openModal();
				$('#sucessMessageId').text('');
				$('#sucessMessageId').text(data.message);
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