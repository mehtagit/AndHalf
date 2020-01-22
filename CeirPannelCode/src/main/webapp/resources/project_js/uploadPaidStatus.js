var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var currentRoleType = $("body").attr("data-selected-roleType"); 
var featureId =12;
$( document ).ready(function() {
	var In = $("body").attr("session-value");

	if(In.length > 0 && In !='null' ){
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
				} 
				else if (data.errorCode == 0 && In == null) {
					$("#user123").css("display", "none");
					$("#user456").css("display", "block");
					$("#addbutton").css("display", "block");
					$("#submitbtn").css("display", "none");
				} 
				else
				{
					$("#user123").css("display", "block");
					$("#user456").css("display", "none");
					$("#addbutton").css("display", "none");
					$("#submitbtn").css("display", "none");	
				}
				$('#nationalID').val(In);
				regularizedCount();
			},
			error : function() {
				console.log("Failed");
			}
		}); 
		sessionStorage.setItem("nationalId", In);
		localStorage.setItem("nationalId", In);	
		pageRendering();
		filter();

		$("#btnLink").css({display: "block"});
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
				} 
				else if (data.errorCode == 0 && In == 'null') {
					$("#user123").css("display", "none");
					$("#user456").css("display", "block");
					$("#addbutton").css("display", "block");
					$("#submitbtn").css("display", "none");
				} 
				else
				{
					$("#user123").css("display", "block");
					$("#user456").css("display", "none");
					$("#addbutton").css("display", "none");
					$("#submitbtn").css("display", "none");	
				}
				$('#nationalID').val(In);
				regularizedCount();
			},
			error : function() {
				console.log("Failed");
			}
		}); 
		pageRendering();
		filter();
		
		$("#btnLink").css({display: "none"});

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

			console.log("incremented value="+id)
			$(wrapper).append(
					/*  '<div style="margin-top:30px;"><div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" id='"deviceType"+id+"'><option value="" disabled selected>Device Type</option><option value='Handheld'>Handheld</option><option value='MobilePhone'>Mobile Phone/Feature phone</option><option value='Vehicle'>Vehicle</option><option value='Portable'>Portable(include PDA)</option><option value='Module'>Module</option><option value='Dongle'>Dongle</option><option value='WLAN'>WLAN Router</option><option value='Modem'>Modem</option><option value='Smartphone'>Smartphone</option><option value='Computer'>Connected Computer</option><option value='Tablet'>Tablet</option><option value='e-Book'>e-Book</option></select></div><div class='col s12 m6'><label for='deviceIdType'>Device ID Type <span class='star'>*</span></label><select class='browser-default' id='deviceIdType'><option value="" disabled selected>Select Device ID Type</option><option value='IMEI'>IMEI</option><option value='ESN'>ESN</option><option value='MEID'>MEID</option></select></div><div class='col s12 m6'><label for='deviceType'>Multiple Sim Status <span class='star'>*</span></label><select class='browser-default' id='deviceType'><option value="" disabled selected>Multiple Sim Status</option><option value='Yes'>Yes</option><option value='No'>No</option></select></div><div class='col s12 m6'><label for='country'>Country bought From <span class='star'>*</span></label><select id='country1' class='browser-default' class='mySelect' style='padding-left: 0;' required></select></div><div class='input-field col s12 m6' style='margin-top: 28px;'><input type='text' id='serialNumber1' name='serialNumber1' pattern='[0-9]' title='Please enter your device serial number first' maxlength='20'><label for='serialNumber1'>Device Serial Number <span class='star'>*</span></label></div><div class='col s12 m6'><label for='deviceType'>Tax paid Status <span class='star'>*</span></label><select class='browser-default' id='deviceType'><option value="" disabled selected>Tax paid Status</option><option value='Regularized'>Regularized</option><option value='Paid'>Paid</option><option value='NotPaid'>Not Paid</option></select></div></div><div class='row'><div class='col s12 m6' style='margin-top: -10px;'><label for='taxStatus'>Device Status <span class='star'>*</span></label><select class='browser-default' id='taxStatus'><option value='' disabled selected>Select Device Status</option><option value='New'>New</option><option value='Used'>Used</option><option value='Refurbished'>Refurbished</option></select></div><div class='input-field col s12 m6 l6'><input type='text' name='Price' id='Price' maxlength='30'><label for='Price'>Price <span class='star'>*</span></label></div><div class='col s12 m6'><label for='Currency'>Currency <span class='star'>*</span></label><select class='browser-default' id='Currency'><option value='' disabled selected>Select Currency</option><option value='Regularized'>$</option><option value='Paid'>$</option><option value="NotPaid">$</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEI1" name="IMEI1" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI1">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEI2" name="IMEI2" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI2">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI3" name="IMEI3" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI3">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEI4" name="IMEI4[]" pattern="[0-9]"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEI4">4</label></div></div><div style="cursor:pointer;background-color:red;' class='remove_field btn right btn-info'>Remove</div></div>'*/
					'<div id="deviceInformation'+id+'" class="deviceInformation"><div class="row"><div class="col s12 m6"><label for="deviceType">Device Type <spanclass="star">*</span></label><select class="browser-default" required id="deviceType'+id+'"><option value="" disabled selected>Device Type</option></select></div><div class="col s12 m6"><label for="deviceIdType'+id+'">Device ID Type <span class="star">*</span></label><select class="browser-default" required id="deviceIdType'+id+'"><option value="" disabled selected>Select Device ID Type</option></select></div><div class="col s12 m6"><label for="multipleSimStatus'+id+'">Multiple Sim Status <span class="star">*</span></label><select class="browser-default" required id="multipleSimStatus'+id+'"><option value="" disabled selected>Multiple Sim Status</option></select></div><div class="col s12 m6"><label for="deviceCountry">Country bought From <span class="star">*</span></label><select id="country'+id+'"  class="browser-default" class="mySelect" style="padding-left: 0;" required></select></div><div class="input-field col s12 m6" style="margin-top: 28px;"><input type="text" id="serialNumber'+id+'" name="serialNumber'+id+'" required pattern="[A-Za-z0-9]{0,15}" title="Please enter your device serial number first" maxlength="15"><label for="serialNumber'+id+'">Device Serial Number <span class="star">*</span></label></div><div class="col s12 m6"><label for="taxStatus'+id+'">Tax paid Status <span class="star">*</span></label><select class="browser-default" required id="taxStatus'+id+'"><option value="" disabled selected>Tax paid Status</option></select></div></div><div class="row"><div class="col s12 m6" style="margin-top: -10px;"><label for="deviceStatus'+id+'">Device Status <span class="star">*</span></label><select class="browser-default" required id="deviceStatus'+id+'"><option value="" disabled selected>Select Device Status</option></select></div><div class="input-field col s12 m6 l6"><input type="text" name="Price" id="Price'+id+'" required maxlength="30"><label for="Price'+id+'">Price <span class="star">*</span></label></div><div class="col s12 m6"><label for="Currency'+id+'">Currency <span class="star">*</span></label><select class="browser-default" required id="Currency'+id+'"><option value="" disabled selected>Select Currency</option></select></div></div><div class="row"><div class="col s12 m12"><p>IMEI/MEID/ESN</p></div><div class="input-field col s12 m6"><input type="text" id="IMEIA'+id+'" required name="IMEI1" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIA'+id+'">1 <span class="star">*</span></label></div><div class="input-field col s12 m6"><input type="text" id="IMEIB'+id+'" name="IMEI2" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIB'+id+'">2</label></div><div class="input-field col s12 m6"><input type="text" id="IMEIC'+id+'" name="IMEI3" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEIC'+id+'">3</label></div><div class="input-field col s12 m6"><input type="text" id="IMEID'+id+'" name="IMEI4[]" pattern="[0-9]{15,16}"title="Please enter minimum 15 and maximum 16 digit only"maxlength="16"><label for="IMEID'+id+'">4</label></div></div><div style="cursor:pointer;background-color:red;" class="remove_field btn right btn-info">Remove</div></div>'

			);  //add input box

			populateCountries
			(   
					"country"+id
			);


			var allowed =localStorage.getItem("allowed");

			console.log("allowed session value=="+allowed)
			$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
				var dropdownid=id-1;
				if(dropdownid <= allowed){

					$('#taxStatus'+dropdownid).prop('disabled', 'disabled');
					$('<option  selected>').val("2").text("Regularized").appendTo('#taxStatus'+dropdownid);
					//console.log("+++++taxStatus"+dropdownid);
					//alert("Regularised");
				}
				else{
					for (i = 0; i < data.length; i++) {
						$('<option>').val(data[i].value).text(data[i].interp)
						.appendTo('#taxStatus'+dropdownid);
						//alert("NonRegularised");
						// $('#taxStatus'+dropdownid).prop('disabled', 'false');
						console.log("+++++taxStatus"+dropdownid);
					}
				}
			});



			$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#deviceType'+dropdownid);
					console.log('#deviceType'+dropdownid)
				}
			});



			$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#deviceIdType'+dropdownid);
					console.log('#deviceIdType'+dropdownid);
				}
			});

			$.getJSON('./getDropdownList/CURRENCY', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#Currency'+dropdownid);
					console.log('#Currency'+dropdownid);
				}
			});

			$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#multipleSimStatus'+dropdownid);
					console.log('#multipleSimStatus'+dropdownid);
				}
			});

			$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
				var dropdownid=id-1;
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#deviceStatus'+dropdownid);
					console.log('#deviceStatus'+dropdownid);
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


var sourceType =localStorage.getItem("sourceType");
function filter()
{       
	var sessionFlag=0;
	if(roleType=="Custom"){
		table('./headers?type=userPaidStatus','./user-paid-status-data?sessionFlag='+sessionFlag);
	}
	else if(roleType == "CEIRAdmin"){
		table('./headers?type=adminUserPaidStatus','./user-paid-status-data?sessionFlag='+sessionFlag);

	}
}


var nationalId =$("body").attr("session-value") =='null' ? null : $("body").attr("session-value");
function table(url,dataUrl){
	var request={
			"modifiedOn":$('#endDate').val(),
			"createdOn":$('#startDate').val(),
			"taxPaidStatus":parseInt($('#taxPaidStatus').val()),
			"userId":parseInt(userId),
			"featureId":parseInt(featureId),
			"userTypeId": parseInt($("body").attr("data-userTypeID")),
			"userType":$("body").attr("data-roleType"),
			"deviceIdType":parseInt($('#deviceIDType').val()),
			"deviceType":parseInt($('#deviceTypeFilter').val()),
			"txnId":$('#transactionID').val(),
			"consignmentStatus": null,
			"nid": nationalId == null ? $('#nId').val() : nationalId
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
				ajax: {
					url : dataUrl,
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify(request); 
						console.log(JSON.stringify(request));
					}

				},
				"columns": result
			});
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}


function pageRendering(){
	pageButtons('./upload-paid-status/pageRendering?type=userPaidStatus');

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
					$("#tableDiv").append("<div class='col s6 m2 l2 responsiveDiv'>"+
							"<div id='enddatepicker' class='input-group'>"+
							"<label for='TotalPrice'>"+date[i].title
							+"</label>"+"<input class='form-control datepicker' type='text' id="+date[i].id+" autocomplete='off'>"+
							"<span	class='input-group-addon' style='color: #ff4081'>"+
							"<i	class='fa fa-calendar' aria-hidden='true' style='float: right; margin-top: -37px;'>"+"</i>"+"</span>");

				}else if(date[i].type === "text"){
					$("#tableDiv").append("<div class='input-field col s6 m2 filterfield' style='margin-top: 22px;'><input type="+date[i].type+" id="+date[i].id+" maxlength='19' /><label for='TransactionID' class='center-align'>"+date[i].title+"</label></div>");
				}
			} 

			// dynamic dropdown portion
			var dropdown=data.dropdownList;
			for(i=0; i<dropdown.length; i++){
				var dropdownDiv=
					$("#tableDiv").append("<div class='col s6 m2 l2 selectDropdwn'>"+
							"<br>"+
							"<div class='select-wrapper select2 form-control boxBorder boxHeight initialized'>"+
							"<span class='caret'>"+"</span>"+
							"<input type='text' class='select-dropdown' readonly='true' data-activates='select-options-1023d34c-eac1-aa22-06a1-e420fcc55868' value='Consignment Status'>"+

							"<select id="+dropdown[i].id+" class='select2 form-control boxBorder boxHeight initialized'>"+
							"<option value=''>"+dropdown[i].title+
							"</option>"+
							"</select>"+
							"</div>"+
					"</div>");
			}



			$("#tableDiv").append("<div class='col s12 m1'><input type='button' class='btn primary botton' value='filter' id='submitFilter' /></div>");
			$("#tableDiv").append("<div class='col s12 m1'><a href='JavaScript:void(0)' onclick='exportpaidStatus()' type='button' class='export-to-excel right'>Export <i class='fa fa-file-excel-o' aria-hidden='true'></i></a></div>");

			for(i=0; i<button.length; i++){
				$('#'+button[i].id).text(button[i].buttonTitle);
				if(button[i].type === "HeaderButton"){
					$('#'+button[i].id).attr("href", button[i].buttonURL);
					$('#'+button[i].id).attr("href", "./add-device-information?NID="+$("body").attr("session-value")+"");
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

			$('.datepicker').datepicker({
				dateFormat: "yy-mm-dd"
			});
		}
	}); 	

	if(sessionStorage.getItem("admin")=="CEIRAdmin"){
		$("#btnLink").css({display: "none"});
	}
}


populateCountries("country");




function deleteByImei(imei){
	$('#deleteMsg').openModal();
	window.imei=imei;
}

function accept(){

	$.ajax({
		url : "./delete/"+window.imei,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'DELETE',
		success : function(data, textStatus, xhr) {

			$('#confirmDeleteMsg').openModal();
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
	$('#viewDeviceInformation').openModal();
	$.ajax({
		url : "./deviceInfo/"+imei,
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		success : function(data) {
			setViewPopupData(data);
		},
		error : function() {
			console.log("Failed");
		}
	});


}




function setViewPopupData(data){
	console.log("**************data*******"+JSON.stringify(data));
	$("#viewdeviceType").val(data.deviceTypeInterp);
	$("#viewdeviceIdType").val(data.deviceIdTypeInterp);
	$("#viewsimStatus").val(data.multiSimStatusInterp);
	$("#viewcountryBought").val(data.country);
	$("#viewserialNumber").val(data.deviceSerialNumber);
	$("#viewtaxStatus").val(data.taxPaidStatusInterp);
	$("#viewdeviceStatus").val(data.deviceStatusInterp);
	$("#viewPrice").val(data.price);
	$("#viewcurrency").val(data.currencyInterp);
	$("#viewIMEI1").val(data.firstImei);
	$("#viewIMEI2").val(data.secondImei); 
	$("#IMEI3").val(data.thirdImei);
	$("#viewIMEI4").val(data.fourthImei);



}

function viewDeviceHistory() {
	historytable("./headers?type=userPaidStatus","./user-paid-status-data");
};



function historytable(url,dataUrl){
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
				ajax: {
					url : dataUrl,
					type: 'POST',
					dataType: "json",
					data : function(d) {
						d.filter = JSON.stringify({						
							"nid": nationalId,
							"taxPaidStatus":3
						}); 
					}

				},
				"columns": result
			});
			$('#viewBlockDevices').openModal();
			//	$('div#initialloader').delay(300).fadeOut('slow');
			/*	$('div#initialloader').fadeOut('slow');*/
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax");
		}
	});
}

function taxPaid(imei){
	$('#payTaxModal').openModal();
	window.taxIMEI=imei;

}


function exportpaidStatus(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var taxPaidStatus = $('#taxPaidStatus').val();
	var deviceIdType = $('#deviceIDType').val();
	var deviceType = $('#deviceTypeFilter').val();
	
	
	var nid = nationalId == null ? $('#nId').val() : nationalId
	var table = $('#data-table-simple').DataTable();
	var info = table.page.info(); 
	var pageNo=info.page;
	var pageSize =info.length;
	console.log("--------"+pageSize+"---------"+pageNo);
	console.log("startDate  ="+startDate+"  endDate=="+endDate+"  taxPaidStatus="+taxPaidStatus+" deviceIdType ="+deviceIdType+"deviceType  "+deviceType+"nid = "+nid)
	window.location.href="./exportPaidStatus?startDate="+startDate+"&endDate="+endDate+"&taxPaidStatus="+taxPaidStatus+"&deviceIdType="+deviceIdType+"&deviceType="+deviceType+"&nid="+nid+"&pageSize="+pageSize+"&pageNo="+pageNo;
}



function submitDeviceInfo(){
	var formData= new FormData();


	//var nationalID=$('#nationalID').val();
	var csvUploadFile=$('#csvUploadFile').val();
	var firstName=$('#firstName').val();
	var middleName=$('#middleName').val();
	var lastName=$('#lastName').val();
	var address=$('#address').val();
	var streetNumber=$('#streetNumber').val();
	var locality=$('#locality').val();
	var country=$('#country').val();
	var state=$('#state').val();
	var email=$('#email').val();
	var phone=$('#phone').val();
	var state=$('#state').val();
	var docType=$('#doc_type').val();
	var doc_type_numeric=$("#doc_type option:selected").attr("docValue");
	
	var village=$('#village').val();
	var district=$('#district').val();
	var commune=$('#commune').val();
	var postalcode=$('#postalcode').val();

	var fieldId=1;
	var regularizeDeviceDbs =[];
	$('.deviceInformation').each(function() {
		var deviceType1=$('#deviceType'+fieldId).val();
		var serialNumber1=$('#serialNumber'+fieldId).val();
		var deviceIdType1=$('#deviceIdType'+fieldId).val();
		var taxStatus1=$('#taxStatus'+fieldId).val();
		var deviceStatus1=$('#deviceStatus'+fieldId).val();
		var Price1=$('#Price'+fieldId).val();
		var Currency1=$('#Currency'+fieldId).val();
		var IMEI1=$('#IMEIA'+fieldId).val();
		var IMEI2=$('#IMEIB'+fieldId).val();
		var IMEI3=$('#IMEIC'+fieldId).val();
		var IMEI4=$('#IMEID'+fieldId).val();
		var deviceCountry=$('#country'+fieldId).val();
		var multipleSimStatus1=$('#multipleSimStatus1'+fieldId).val();

		console.log("serialNumber1="+serialNumber1+" deviceIdType1="+deviceIdType1+" taxStatus1="+taxStatus1+" deviceStatus1="+deviceStatus1+" Price1="+Price1+" Currency1="+Currency1)
		var deviceInfo=
		{
			"country": deviceCountry,
			"currency": parseInt(Currency1),
			"deviceIdType": parseInt(deviceIdType1),
			"deviceSerialNumber": serialNumber1,
			"deviceStatus": parseInt(deviceStatus1),
			"deviceType": parseInt(deviceType1),
			"firstImei": parseInt(IMEI1),
			"secondImei": parseInt(IMEI2),
			"thirdImei": parseInt(IMEI3),
			"fourthImei": parseInt(IMEI4),
			"multiSimStatus": deviceStatus1,
			"price": parseFloat(Price1),
			"taxPaidStatus": parseInt(taxStatus1),
			"nid":nationalId,
			"txnId":""

		}
		regularizeDeviceDbs.push(deviceInfo);

//		console.log(formData.values());
//		console.log(JSON.stringify(formData));

//		alert(deviceType1,serialNumber1,deviceIdType1,taxStatus1,deviceStatus1,Price1,Currency1,IMEI1,IMEI2,IMEI3,IMEI4,multipleSimStatus1)
		fieldId++;


	});



	var request={
			"country": country,
			"email": email,
			"firstName": firstName,
			"lastName": lastName,
			"locality": locality,
			"middleName": middleName,
			"nid": nationalId,
			"phoneNo": phone,
			"propertyLocation": address,
			"province": state,
			"street": streetNumber,
			"regularizeDeviceDbs":regularizeDeviceDbs,
			"district":district,
			"commune":commune,
			"village":village,
			"postalCode":postalcode,
			"doc_type_numeric":docType,
			"docType":doc_type_numeric

	}
	formData.append('file', $('#csvUploadFile')[0].files[0]);
	formData.append("request",JSON.stringify(request));

	for (var value of formData.values()) {
		console.log(value);
	}
	$.ajax({
		url: './uploadPaidStatusForm',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
			console.log("in suucess method");

			console.log(data);
			$("#uploadPaidStatusbutton").prop('disabled', true);
//			$('#updateConsignment').modal();
			if(data.errorCode==200){

//				$('#sucessMessage').text('');
				$('#regularisedDevice').openModal();
				$('#dynamicTxnId').text(data.txnId);
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



populateCountries
(   
		"country1"
);


populateCountries
(   
		"country"
);



function taxPaidStatus(){
	var request={
			"firstImei": parseInt(window.taxIMEI),			
			"taxPaidStatus":0
	}
	$.ajax({
		url: './tax-paid/status',
		type: 'PUT',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		'data' : JSON.stringify(request),
		success: function (data, textStatus, jqXHR) {
			console.log("success"+JSON.stringify(data))
			var msg="The device status has been successfully updated";
			$('#payTaxModal').closeModal();
			$('#payNowTaxPayment').openModal();
			/*if(data.errorCode==200){
				$('#taxPaidMsg').text(msg);

			}
			else{
				$('#taxPaidMsg').text(msg);
			}*/

		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")
		}
	});
}
populateCountries
(   
		"country1"
);

/*populateCountries
	(   
			"country"
	);*/


populateCountries(
		"country",	"state");
/*$(document).ready(function() {
	console.log();
	function dropDownValues()

	{

	}

});*/



$(document).ready(function () {
	console.log("start,..");
	$.getJSON('./getDropdownList/CUSTOMS_TAX_STATUS', function(data) {
		/*for (i = 0; i < data.length; i++) {
			$('<option>').val("2").text("Regularized")
			.appendTo('#taxStatus1');
			console.log("...........");
		}*/
		$('#taxStatus1').prop('disabled', 'disabled');
		$('<option  selected>').val("2").text("Regularized").appendTo('#taxStatus1');
	});



	$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#deviceType1');
			console.log("...........");
		}
	});



	$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#deviceIdType1');
			console.log("...........");
		}
	});

	$.getJSON('./getDropdownList/CURRENCY', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#Currency1');
			console.log("...........");
		}
	});

	$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#multipleSimStatus1');
			console.log("...........");
		}
	});

	$.getJSON('./getDropdownList/DEVICE_STATUS', function(data) {
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#deviceStatus1');
			console.log("...........");
		}
	});
	
	
	$(document).ready(function(){
		$.getJSON('./getDropdownList/DOC_TYPE', function(data) {
			console.log("@@@@@"+JSON.stringify(data));
			for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].tagId).text(data[i].interp).attr("docValue",data[i].value).appendTo('#doc_type');
			//$('#docTypeNymericValue').val(data[i].value);
			}
		});
	});




});



function regularizedCount(){
	var nid= nationalId == 'null' ? null : nationalId;
			//var nid=$('#nationalID').val();
			console.log("nid==&&&&&&&&&&&&&&&&&"+nid);
	$.ajax({
		url: './countByNid?nid='+nid,
		type: 'GET',
		processData: false,
		contentType: false,
		success: function (data, textStatus, jqXHR) {
			console.log("in suucess method");
			console.log(data.data.allowed);
			var allowed=data.data.allowed;
			var current=data.data.current;
			console.log("set allowed value==="+allowed);
			console.log("set current value==="+current);
			localStorage.setItem("allowed", allowed);
			localStorage.setItem("current", current);
			/* var allowedd = localStorage.getItem("allowed");
	          console.log("_____________"+allowedd);*/
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log("error in ajax")

		}
	});
}



function refreshContent(){
	$('#payNowTaxPayment,#confirmDeleteMsg,#regularisedDevice').closeModal();
	window.location.reload(true);
}


function deviceApprovalPopup(imei,date,txnId){
	$('#approveInformation').openModal();
	window.imei=imei;
	window.date=date.replace("="," ");
	$('#approveTxnId').text(txnId);
}  


function aprroveDevice(){
	var approveRequest={
			"action" : 0,
			"imei1": window.imei,
			"featureId":parseInt(featureId),
			"remarks": "",
			"roleTypeUserId": parseInt($("body").attr("data-userTypeID")),
			"txnId": "",
			"userId":parseInt(userId),
			"userType": $("body").attr("data-roleType")	  	
	}

	$.ajax({
		url : './approveRejectDevice',
		data : JSON.stringify(approveRequest),
		dataType : 'json',
		'async' : false,
		contentType : 'application/json; charset=utf-8',
		type : 'PUT',
		success : function(data) {
			console.log("approveRequest----->"+JSON.stringify(approveRequest));
			if(data.errorCode==0){
				confirmApproveInformation(window.imei,window.date);
				console.log("inside Approve Success")
			}

		},
		error : function() {
			alert("Failed");
		}
	});
}


function confirmApproveInformation(imei,date){
	$('#approveInformation').closeModal(); 
	setTimeout(function(){ $('#confirmApproveInformation').openModal();}, 200);
}


function userRejectPopup(imei,txnId){
	$('#rejectInformation').openModal();
	$('#disapproveTxnId').text(txnId)
	window.imei=imei;
}



function rejectUser(){
	var rejectRequest={
			"action" : 1,
			"imei1": window.imei,
			"featureId":parseInt(featureId),
			"remarks": $("#Reason").val(),
			"roleTypeUserId": parseInt($("body").attr("data-userTypeID")),
			"txnId": "",
			"userId":parseInt(userId),
			"userType": $("body").attr("data-roleType")	  	
	}

	$.ajax({
		url : './approveRejectDevice',
		data : JSON.stringify(rejectRequest),
		dataType : 'json',
		'async' : false,
		contentType : 'application/json; charset=utf-8',
		type : 'PUT',
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
}



function confirmRejectInformation(){
	$('#rejectInformation').closeModal();
	setTimeout(function(){$('#confirmRejectInformation').openModal();},200);
}