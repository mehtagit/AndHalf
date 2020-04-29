/*	window.parent.$('#langlist').on('change', function() {
			var lang=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
		
			window.location.assign("openBlockUnblockPage?pageType=block&lang="+lang);
		}); */
		
		var langParam=window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';
		$.i18n().locale = langParam;
		var successMsg;
		$.i18n().load( {
			'en': './resources/i18n/en.json',
			'km': './resources/i18n/km.json'
		} ).done( function() { 
			editUnblock=$.i18n('editStolenDevice');
			
		});
		

		$(document).ready(function () {
			
			$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
				
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#blockdeviceType');
					console.log('#blockdeviceType')
				}
			});

			$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
				
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].value).text(data[i].interp)
					.appendTo('#blockmultipleSimStatus');
					console.log('#blockmultipleSimStatus');
				}
			});
		});
		
		$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
			
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#blockdeviceIdType');
				console.log('#blockdeviceIdType');
			}
		});
		
		var RequestData = {
				"tag" : "GREY_TO_BLACK_MOVE_PERIOD_IN_DAY"
		}
		$.ajax({
			url : "./system/viewTag",
			data :	JSON.stringify(RequestData),
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'POST',
			success : function(data) {
				console.log(data.value);
				$('#defaultPeriodId,#bulkblocktypeRadioId,#editbulkblocktypeRadioId').attr('title', data.value+' Days');
			},
			error : function() {
				console.log("Failed");
			}
		});
	
		$.getJSON('./getTypeDropdownList/BLOCK_CATEGORY/'+$("body").attr("data-userTypeID"), function(data) {
			
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#bulkBlockdeviceCategory');
				console.log('#bulkBlockdeviceCategory');
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#singleDeviceCategory');
				console.log('#singleDeviceCategory');
			}
		});
		

function showSingleImeiBlock()
{
	$("#SingleImeiBlock").css("display", "block");
	$("#multipleImeiBlock").css("display", "none");
	
	$('#multipleImeiBlockform').trigger("reset");
	$('#SingleImeiBlockform').trigger("reset");
	

	/*$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#blockdeviceType');
			console.log('#blockdeviceType')
		}
	});

	$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#blockmultipleSimStatus');
			console.log('#blockmultipleSimStatus');
		}
	});
	
	$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#blockdeviceIdType');
			console.log('#blockdeviceIdType');
		}
	});
	
	
*/
}



function showMultipleImeiBlock()
{
	$("#multipleImeiBlock").css("display", "block");
	$("#SingleImeiBlock").css("display", "none");
	$('#multipleImeiBlockform').trigger("reset");
	$('#SingleImeiBlockform').trigger("reset");
	
/*	$.getJSON('./getDropdownList/GRIEVANCE_CATEGORY', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#bulkBlockdeviceCategory');
			console.log('#bulkBlockdeviceCategory');
		}
	});*/
}

function showSingleImeiUnBlock()
{
	$("#SingleImeiUnBlock").css("display", "block");
	$("#multipleImeiUnBlock").css("display", "none");
	
	$('#multipleImeiUnBlockform').trigger("reset");
	$('#SingleImeiUnBlockform').trigger("reset");
	

}



function showMultipleImeiUnBlock()
{
	$("#multipleImeiUnBlock").css("display", "block");
	$("#SingleImeiUnBlock").css("display", "none");
	
	$('#multipleImeiUnBlockform').trigger("reset");
	$('#SingleImeiUnBlockform').trigger("reset");
	
}



function submitBlockImei()
{
	 
		var bulkBlockdeviceCategory = $('#bulkBlockdeviceCategory').val();
		var blockbulkquantity = $('#blockbulkquantity').val();
		//var blockBulkFile = $('#blockBulkFile').val();
		var blockbulkRemark = $('#blockbulkRemark').val();
		var ModeType='3';
		var requestType='2';
		var roleType = $("body").attr("data-roleType");
		var userId = $("body").attr("data-userID");
		var bulkBlockingTimePeriod=$('#stolenBulkDatePeriod').val();
		var bulkBlocktype =$('.bulkblocktypeRadio:checked').val();
		 var deviceQuantity=$('#blockbulkDeviceQuantity').val();
		console.log("bulkBlockdeviceCategory="+bulkBlockdeviceCategory+" blockbulkquantity=="+blockbulkquantity+" blockUnblockRemark="+blockbulkRemark)
		
		var formData = new FormData();
		formData.append('file', $('#blockBulkFile')[0].files[0]);
		formData.append('blockCategory', bulkBlockdeviceCategory);
		formData.append('qty', blockbulkquantity);
		formData.append('remark', blockbulkRemark);
		formData.append('sourceType', ModeType);
		formData.append('requestType', requestType);
		formData.append('userId',userId);
		formData.append('roleType',roleType);
		formData.append('blockingTimePeriod',bulkBlockingTimePeriod);
		formData.append('blockingType',bulkBlocktype);
		formData.append('deviceQuantity',deviceQuantity);
		
			

		$.ajax({
			url: './reportblockBulkFile',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function (data, textStatus, jqXHR) {
				$("#blockBulkSubmitButton").prop('disabled', true);
				console.log(data);
				//$('#fileStolenModal').closeModal();
				$('#markBulkAsBlock').openModal({dismissible:false});
				$('#txnIdsingleImei').text(data.txnId);

			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});

		return false;

	
}

function submitUnBlockImei()
{
	    var unblockbulkquantity = $('#unblockbulkquantity').val();
		//var blockBulkFile = $('#blockBulkFile').val();
	    var bulkunBlockdeviceCategory=$('#bulkunBlockdeviceCategory').val();
		var unblockbulkRemark = $('#unblockbulkRemark').val();
		var ModeType='3';
		var requestType='3';
		var roleType = $("body").attr("data-roleType");
		var userId = $("body").attr("data-userID");
		var deviceQuantity=$('#unblockbulkDevicequantity').val();

		//console.log("bulkBlockdeviceCategory="+bulkBlockdeviceCategory+" blockbulkquantity=="+blockbulkquantity+" blockUnblockRemark="+blockUnblockRemark)
		var formData = new FormData();
		formData.append('file', $('#unblockBulkFile')[0].files[0]);
		formData.append('qty', unblockbulkquantity);
		formData.append('blockCategory', bulkunBlockdeviceCategory);
		formData.append('remark', unblockbulkRemark);
		formData.append('sourceType', ModeType);
		formData.append('requestType', requestType);
		formData.append('userId',userId);
		formData.append('roleType',roleType);
		formData.append('deviceQuantity',deviceQuantity);
		

$.ajax({
			url: './reportUnblockBulkFile',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function (data, textStatus, jqXHR) {
			console.log(data);
			$("#bulkUnblockSubmitButton").prop('disabled', true);
//		 console.log(data);
			$('#markBulkAsUnblock').openModal({dismissible:false});
			 $('#txnIdUnblocksingleDevice').text(data.txnId);

			},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log("error in ajax")
			}
		});

		return false;

	
}

function submitSingleBlockDevicesRequest()
{
	
	var deviceType=$('#blockdeviceType').val();
	var blockdeviceIdType=$('#blockdeviceIdType').val();
	var multipleSimStatus=$('#blockmultipleSimStatus').val();
	var serialNumber=$('#singleblockserialNumber').val();
	var remark=$('#singleblockremark').val();
	var IMEI1=$('#singleblockIMEI1').val();
	var IMEI2=$('#singleblockIMEI2').val();
	var IMEI3=$('#singleblockIMEI3').val();
	var IMEI4=$('#singleblockIMEI4').val();
	var blockingTimePeriod=$('#stolenDatePeriod').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	var category= $('#singleDeviceCategory').val();
	
	var requestType=2;
	
	console.log("****"+blockingTimePeriod+"**"+blockingType);
	console.log("sucess include deviceType="+deviceType+" multipleSimStatus="+multipleSimStatus+" serialNumber="+serialNumber+" remark="+remark+" IMEI1="+IMEI1 );

var singleImeiBlockDetail={
	 	
		'deviceType':deviceType,
		'deviceIdType':blockdeviceIdType,
	 	'multipleSimStatus':multipleSimStatus,
	 	'deviceSerialNumber':serialNumber,
	 	'firstImei':IMEI1,
		'secondImei':IMEI2,
		'thirdImei':IMEI3,
		'fourthImei':IMEI4,
		'requestType':requestType,
		'remark':remark,
		'sourceType':4,
		'blockingTimePeriod':blockingTimePeriod,
		'blockingType':blockingType,
		'category':category,
		'deviceQuantity':1
		
		
}
		
		console.log(JSON.stringify(singleImeiBlockDetail));
		console.log("*********");
	 	
	 $.ajax({
		url: './blockUnBlockSingleDevices',
		data : JSON.stringify(singleImeiBlockDetail),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success: function (data, textStatus, jqXHR) {
			$("#singleblockSubmit").prop('disabled', true);
			 console.log(data);
			 $('#markAsBlock').openModal({dismissible:false});
			 $('#txnIdblockBulkDevice').text(data.txnId);
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
		console.log("error in ajax")
		}
	});
		return false;
}



function submitSingleUnBlockDevicesRequest()
{
	var deviceType=$('#unbockSingledeviceType').val();
	var blockdeviceIdType=$('#UnblockdeviceIdType').val();
	var multipleSimStatus=$('#unbockSingleMultipleSimStatus').val();
	var serialNumber=$('#unbockSingleSerialNumber').val();
	var remark=$('#unbockSingleRemark').val();
	var IMEI1=$('#unbockSingleIMEI1').val();
	var IMEI2=$('#unbockSingleIMEI2').val();
	var IMEI3=$('#unbockSingleIMEI3').val();
	var IMEI4=$('#unbockSingleIMEI4').val();
	var requestType=3;
	var roleType = $("body").attr("data-roleType");
	var blockingTimePeriod=$('#stolenDatePeriodUnblock').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	var category= $('#singleDeviceUnblock').val();


	
	
	
	console.log("sucess include deviceType="+deviceType+" multipleSimStatus="+multipleSimStatus+" serialNumber="+serialNumber+" remark="+remark+" IMEI1="+IMEI1 );

var singleImeiBlockDetail={
	 	
		'deviceType':deviceType,
		'deviceIdType':blockdeviceIdType,
	 	'multipleSimStatus':multipleSimStatus,
	 	'deviceSerialNumber':serialNumber,
	 	'firstImei':IMEI1,
		'secondImei':IMEI2,
		'thirdImei':IMEI3,
		'fourthImei':IMEI4,
		'requestType':requestType,
		'remark':remark,
		'sourceType':4,
		'blockingTimePeriod':blockingTimePeriod,
		'blockingType':blockingType,
		'category':category,
		'deviceQuantity':1
	
}
		
		console.log(JSON.stringify(singleImeiBlockDetail));
		console.log("*********");
	 	
	 $.ajax({
		url: './blockUnBlockSingleDevices',
		data : JSON.stringify(singleImeiBlockDetail),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success: function (data, textStatus, jqXHR) {
			$("#singleUnblockSubmitButton").prop('disabled', true);
			 console.log(data);
			 $('#markAsUnblock').openModal({dismissible:false});
			 $('#txnIdblocksingleDevice').text(data.txnId);
		},
		error: function (jqXHR, textStatus, errorThrown) {
		console.log("error in ajax")
		}
	});
		return false;
}








//*******************************************View Pop up data *************************************************************************************************
function viewDeviceDetails(txnId,popUpType,requestType){
console.log("requestType=="+requestType)
	
	var role = currentRoleType == null ? roleType : currentRoleType;
	console.log("popUpType=="+popUpType);
	$.ajax({
		url : "./openbulkView?reqType="+requestType+"&txnId="+txnId+'&modeType=singleDeivce',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		async :false,
		success : function(data) {
			console.log(data);
			setViewBulkPopUp(data,popUpType,requestType);
		},
		error : function() {
			
		}
	});
}

$.getJSON('./getTypeDropdownList/BLOCK_CATEGORY/'+$("body").attr("data-userTypeID"), function(data) {
	$('#editBulkBlockCategory').empty();
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#editBulkBlockCategory');
		
	}
});
function setViewBulkPopUp(data,popUpType,requestType){
	



	if(popUpType=='view'){	
		console.log("++++++++++"+popUpType+"requestType="+requestType);
	
		if(requestType=="3")
		{
			
			$("#bulkblockingTypeId").css("display", "none");
		$('#viewModalHeader').text($.i18n('viewBulkUnblock'));
		}
		else{
			$("#bulkblockingTypeId").css("display", "block");
		$('#viewModalHeader').text($.i18n('viewBlockDevice'));
		}
			
	$("#viewBulkBlockDeviceModal").openModal({dismissible:false});
	$("#viewBulkBlockCategory").val(data.blockCategoryInterp);
	$("#viewBulkBlockRemark").val(data.remark);
	$("#viewBulkBlockuploadFile").val(data.fileName);
	$("#viewBulkBlockquantity").val(data.qty);
	$("#viewBulkBlockDevicequantity").val(data.deviceQuantity);
	$("#viewBulkBlockTxnId").val(data.txnId);
	$("#viewBulkBlockRemarkReject").val(data.rejectedRemark);
	$("#viewBulkBlockRemarkRejectDiv").css("display", "block");
	if(data.blockingType=='tilldate')
	{
	
	$("#viewbulkblockingType").val("Later ( Block time period : " +data.blockingTimePeriod+" )");
	}
else{
$("#viewbulkblockingType").val(data.blockingType);
}
	}
	else
	{
		if(requestType=="3")
		{
			$("#editBulkBlockDiv").css("display", "none"); 
		$('#editblockHeading').text(editUnblock);
		}
		else{
			$("#editBulkBlockDiv").css("display", "block"); 
		$('#editblockHeading').text($.i18n('editBlockDevice'));
		}
		
		console.log("++++++++++"+popUpType+" requestType="+requestType);
	$("#editBulkBlockDeviceModal").openModal({dismissible:false});
	$("#editBulkBlockRemark").val(data.remark);
	$("#editBulkBlockuploadFile").val(data.fileName);
	$("#editBulkBlockquantity").val(data.qty);
	$("#editBulkBlockDevicequantity").val(data.deviceQuantity);
	$("#editBulkBlockTxnId").val(data.txnId);
	$("#editBulkBlockrequestType").val(data.requestType);
	$("#editBulkBlockCategory").val(data.blockCategory);
	$('input[name=editbulkblocktypeName][value='+data.blockingType+']').attr('checked', true);
	if(data.blockingType=='tilldate')
	{
	$("#bulkeditcalender").css("display", "block"); 
	
	$("#editstolenBulkDatePeriod").val(data.blockingTimePeriod);
	}
else{
	$("#bulkeditcalender").css("display", "none"); 
}
	
	}
}


function updateBulkDevice(){
var categoryInterp=	$("#editBulkBlockCategory").val();
var remark=	$("#editBulkBlockRemark").val();
var fileName=$("#editBulkBlockuploadFile").val();
var qty=$("#editBulkBlockquantity").val();
var txnId=$("#editBulkBlockTxnId").val();
var requestType=$("#editBulkBlockrequestType").val();
var ModeType=3
var roleType = $("body").attr("data-roleType");
var userId = $("body").attr("data-userID");
var formData = new FormData();
var editbulkBlockingTimePeriod=$('#editstolenBulkDatePeriod').val();
var editbulkBlocktype =$('.editbulkblocktypeRadio:checked').val();
var devicequantity = $("#editBulkBlockDevicequantity").val();
formData.append('file', $('#editselectBulkBlockuploadFile')[0].files[0]);
formData.append('qty', qty);
formData.append('deviceQuantity',devicequantity);
formData.append('blockCategory', categoryInterp);
formData.append('remark', remark);
formData.append('sourceType', ModeType);
formData.append('requestType', requestType);
formData.append('userId',userId);
formData.append('roleType',roleType);
formData.append('txnId',txnId);
formData.append('fileName',fileName);
formData.append('blockingTimePeriod',editbulkBlockingTimePeriod);
formData.append('blockingType',editbulkBlocktype);

$.ajax({
	url: './updateFileTypeStolenRecovery',
	type: 'POST',
	data: formData,
	processData: false,
	contentType: false,
	success: function (data, textStatus, jqXHR) {
	console.log(data);
// console.log(data);
	$('#confirmEditBlockUnblock').openModal({dismissible:false});
		//if(data.errorCode==200){
		/* 
						 $('#stockSucessMessage').text('');
						 $('#stockSucessMessage').text('Operation is not allowed');
							 }
						 else{
							 $('#stockSucessMessage').text('');
			 				 $('#stockSucessMessage').text('Your update on the form for transaction ID ('+data.txnId+') has been successfully updated.');
						 } */ 
		// $('#updateConsignment').modal('open'); 
		//alert("success");

	},
	error: function (jqXHR, textStatus, errorThrown) {
		console.log("error in ajax")
	}
});
return false;

}



function viewblockImeiDevice(txnId,popUpType,requestType)
{


	
	$.ajax({
		/*url : "./openSingleImeiForm?reqType=editPage&txnId="+txnId+'&modeType=singleDeivce',*/
		url : "./openbulkView?reqType="+requestType+"&txnId="+txnId+'&modeType=singleDeivce',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		async :false,
		success : function(data) {
			console.log(data);
			setSingleDeviceViewPopUp(data,popUpType,requestType);
		},
		error : function() {
		
		}
	});
	
	
}




$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
	$("#editblockdeviceType").empty();
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#editblockdeviceType');
	
	}
});

$.getJSON('./getTypeDropdownList/BLOCK_CATEGORY/'+$("body").attr("data-userTypeID"), function(data) {
	$("#editbulkBlockdeviceCategory").empty();
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#editbulkBlockdeviceCategory');
		console.log('#editbulkBlockdeviceCategory');
	}
});

$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
	$("#editblockmultipleSimStatus").empty();
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#editblockmultipleSimStatus');
	
	}
});

$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
	$("#editblockdeviceIdType").empty();
	for (i = 0; i < data.length; i++) {
		$('<option>').val(data[i].value).text(data[i].interp)
		.appendTo('#editblockdeviceIdType');
		
	}
});




function setSingleDeviceViewPopUp(data,popUpType,requestType){
console.log(data.rejectedRemark);
console.log(data.singleImeiDetails.firstImei);

	if(popUpType=='view'){
		$("#viewsingleblockremarkDiv").css("display", "block"); 
		if(requestType=="3")
		{
			
			$("#blockingTypeId").css("display", "none"); 
			$("#editBlockTimePeriod").css("display", "none"); 
		$('#singleBlockUnblockHeading').text($.i18n('viewBulkUnblock'));
		}
		else{
			$("#blockingTypeId").css("display", "block"); 
			$("#editBlockTimePeriod").css("display", "block"); 
			
		$('#singleBlockUnblockHeading').text($.i18n('viewBlockDevice'));
		}
		$('#viewblockImeiDevice').openModal({dismissible:false});
		console.log("++++++++++"+popUpType+"requestType="+requestType);
		
			

	
			if(data.singleImeiDetails.secondImei==0 && data.singleImeiDetails.thirdImei==0 && data.singleImeiDetails.fourthImei==0  )
			{
			$("#viewsingleblockIMEI2").val("");
			$("#viewsingleblockIMEI3").val("");
			$("#viewsingleblockIMEI4").val("");
			}
		else if(data.singleImeiDetails.secondImei==0 && data.singleImeiDetails.thirdImei==0 && data.singleImeiDetails.fourthImei!=0 )
			{
			$("#viewsingleblockIMEI2").val("");
			$("#viewsingleblockIMEI3").val("");
			$("#viewsingleblockIMEI4").val(data.singleImeiDetails.fourthImei);
			}
		else if(data.singleImeiDetails.secondImei!=0 && data.singleImeiDetails.thirdImei==0 && data.singleImeiDetails.fourthImei==0)
			{
			$("#viewsingleblockIMEI2").val(data.singleImeiDetails.secondImei);
			$("#viewsingleblockIMEI3").val("");
			$("#viewsingleblockIMEI4").val("");
			}
		else if(data.singleImeiDetails.secondImei==0 && data.singleImeiDetails.thirdImei!=0 && data.singleImeiDetails.fourthImei==0)
			{
			$("#viewsingleblockIMEI2").val("");
			$("#viewsingleblockIMEI3").val(data.singleImeiDetails.thirdImei);
			$("#viewsingleblockIMEI4").val("");
			}
		else{
			console.log("else############")
		}
			
	$("#viewblockdeviceType").val(data.singleImeiDetails.deviceTypeInterp);
	$("#viewblockdeviceIdType").val(data.singleImeiDetails.deviceIdTypeInterp);
	$("#viewblockmultipleSimStatus").val(data.singleImeiDetails.multipleSimStatusInterp);
	$("#viewsingleblockserialNumber").val(data.singleImeiDetails.deviceSerialNumber);
	$("#viewsingleblockremark").val(data.singleImeiDetails.remark);
	$("#viewsingleblockIMEI1").val(data.singleImeiDetails.firstImei);
	$("#viewsingleblocTxnid").val(data.singleImeiDetails.txnId);
	$("#viewsingleblockCategory").val(data.singleImeiDetails.categoryInterp);
	$("#viewsingleblockremark").val(data.singleImeiDetails.remark);
	$("#viewsingleblockremarkReject").val(data.rejectedRemark);
	
	
	if(data.blockingType=='tilldate')
		{
		
		$("#viewsingleblockingType").val("Later ( Block time period : " +data.blockingTimePeriod+" )");
		}
	else{
	$("#viewsingleblockingType").val(data.blockingType);
	}
	
	}
	else
	{
/*		 $("#editblockdeviceType option").remove();
		 $("#editblockmultipleSimStatus option").remove();
		 $("#editblockdeviceIdType option").remove();
		 $("#editbulkBlockdeviceCategory option").remove();*/
		if(requestType=="3")
		{
			  
		$('#singleBlockDeviceHeading').text(editUnblock);
		}
		else{
			   
		$('#singleBlockDeviceHeading').text($.i18n('editBlockDevice'));
		}
		
		
		console.log("++++++++++"+popUpType+" requestType="+requestType);
	$("#editblockImeiDevice").openModal({dismissible:false});
	 
		
		
		
		
		if(data.singleImeiDetails.secondImei==0 && data.singleImeiDetails.thirdImei==0 && data.singleImeiDetails.fourthImei==0  )
			{
			$("#editsingleblockIMEI2").val("");
			$("#editsingleblockIMEI3").val("");
			$("#editsingleblockIMEI4").val("");
			}
		else if(data.singleImeiDetails.secondImei==0 && data.singleImeiDetails.thirdImei==0 && data.singleImeiDetails.fourthImei!=0 )
			{
			$("#editsingleblockIMEI2").val("");
			$("#editsingleblockIMEI3").val("");
			$("#editsingleblockIMEI4").val(data.singleImeiDetails.fourthImei);
			}
		else if(data.singleImeiDetails.secondImei!=0 && data.singleImeiDetails.thirdImei==0 && data.singleImeiDetails.fourthImei==0)
			{
			$("#editsingleblockIMEI2").val(data.singleImeiDetails.secondImei);
			$("#editsingleblockIMEI3").val("");
			$("#editsingleblockIMEI4").val("");
			}
		else if(data.singleImeiDetails.secondImei==0 && data.singleImeiDetails.thirdImei!=0 && data.singleImeiDetails.fourthImei==0)
			{
			$("#editsingleblockIMEI2").val("");
			$("#editsingleblockIMEI3").val(data.singleImeiDetails.thirdImei);
			$("#editsingleblockIMEI4").val("");
			}
		else{
			console.log("else############");
		}
		console.log("device id type="+data.singleImeiDetails.deviceIdType);
		$("#editblockdeviceType").val(data.singleImeiDetails.deviceType).change();
		$("#editblockdeviceIdType").val(data.singleImeiDetails.deviceIdType).change();
		$("#editblockmultipleSimStatus").val(data.singleImeiDetails.multipleSimStatus);
		$("#editsingleblockserialNumber").val(data.singleImeiDetails.deviceSerialNumber);
		$("#editsingleblockremark").val(data.singleImeiDetails.remark);
		$("#editsingleblockIMEI1").val(data.singleImeiDetails.firstImei);
		$("#editsingleblockTxnid").val(data.singleImeiDetails.txnId);
		$("#editbulkBlockdeviceCategory").val(data.singleImeiDetails.category);
		
		$('input[name=editbulkBlockdeviceradio][value='+data.blockingType+']').attr('checked', true); 
		
		if(data.blockingType=='tilldate')
			{
			$("#calender").css("display", "block"); 
			
			$("#stolenDatePeriodedit").val(data.blockingTimePeriod);
			}
		else{
			$("#calender").css("display", "none"); 
		}
		//$('input[name=editbulkBlockdeviceradio][value='+data[i].blockingTimePeriod+']').attr('checked', true); 
		$("#editsingleblocRequestType").val(requestType);
		}
	
}




function updateSingleBlockDevicesRequest()
{
	
	var deviceType=$('#editblockdeviceType').val();
	var blockdeviceIdType=$('#editblockdeviceIdType').val();
	var multipleSimStatus=$('#editblockmultipleSimStatus').val();
	var serialNumber=$('#editsingleblockserialNumber').val();
	var remark=$('#editsingleblockremark').val();
	var IMEI1=$('#editsingleblockIMEI1').val();
	var IMEI2=$('#editsingleblockIMEI2').val();
	var IMEI3=$('#editsingleblockIMEI3').val();
	var IMEI4=$('#editsingleblockIMEI4').val();
	var txnId=$('#editsingleblockTxnid').val();
	var requestType=$('#editsingleblocRequestType').val();
	var blockingTimePeriod=$('#stolenDatePeriodedit').val();
	var blockingType =$('.blocktypeRadio:checked').val();
	var category=$('#editbulkBlockdeviceCategory').val();
	
	
	console.log("****");
	//console.log("sucess include deviceType="+deviceType+" multipleSimStatus="+multipleSimStatus+" serialNumber="+serialNumber+" remark="+remark+" IMEI1="+IMEI1 );

var singleImeiBlockDetail={
	 	
		'deviceType':deviceType,
		'deviceIdType':blockdeviceIdType,
	 	'multipleSimStatus':multipleSimStatus,
	 	'deviceSerialNumber':serialNumber,
		'firstImei':IMEI1,
		'secondImei':IMEI2,
		'thirdImei':IMEI3,
		'fourthImei':IMEI4,
		'requestType':requestType,
		'remark':remark,
		'txnId':txnId,
		'sourceType': 4,
		'blockingTimePeriod':blockingTimePeriod,
		'blockingType':blockingType,
		'category':category
}
		
		console.log(JSON.stringify(singleImeiBlockDetail));
		console.log("*********");
	 	
	 $.ajax({
		url: './updateBlockUnBlockSingleDevices',
		data : JSON.stringify(singleImeiBlockDetail),
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'POST',
		success: function (data, textStatus, jqXHR) {
			
			 console.log(data);
			 $('#confirmEditBlockUnblock').openModal({dismissible:false});
	/*		 if(data.errorCode==200){
			
		     $('#singleDeviceBlockMessage').text('');
			 $('#singleDeviceBlockMessage').text(data.message);
				 }
			 else{
				 $('#singleDeviceBlockMessage').text('');
 				 $('#singleDeviceBlockMessage').text(data.message);
			 }*/
		   // $('#updateConsignment').modal('open'); 
			//alert("success");
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
		console.log("error in ajax")
		}
	});
		return false;
}




$('#stolenDatePeriod').datepicker({
	dateFormat: "yy-mm-dd"
	});

$('#stolenDatePeriodedit').datepicker({
	dateFormat: "yy-mm-dd"
	});

$('#stolenDatePeriodUnblock').datepicker({
	dateFormat: "yy-mm-dd"
	});


function singleImeiFormClear(){
	$('#editSingleImeiform').trigger("reset");
	//$('input[name=editbulkBlockdeviceradio]').attr('checked', false);
}



function fileTypeValueChanges(formType) {

	 if(formType=='2')
    {
		var uploadedFileName = $("#editselectBulkBlockuploadFile").val();
		uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
		var ext = uploadedFileName.split('.').pop();

		var fileSize = ($("#editselectBulkBlockuploadFile")[0].files[0].size);
		fileSize = (Math.round((fileSize / 1024) * 100) / 100)
		if (uploadedFileName.length > 30) {
			$('#fileFormateModal').openModal({dismissible:false});

		} 
		else if(ext!='csv')
		{
			$('#fileFormateModal').openModal({
				dismissible:false
			});

		}
		else if(fileSize>='2000'){
			$('#fileFormateModal').openModal({
				dismissible:false
			});

		}
	}
	else 
		{
		
		var uploadedFileName = $("#blockBulkFile").val();
		uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
		var ext = uploadedFileName.split('.').pop();

		var fileSize = ($("#blockBulkFile")[0].files[0].size);
		fileSize = (Math.round((fileSize / 1024) * 100) / 100)
		if (uploadedFileName.length > 30) {
			$('#fileFormateModal').openModal({dismissible:false});

		} 
		else if(ext!='csv')
		{
			$('#fileFormateModal').openModal({
				dismissible:false
			});

		}
		else if(fileSize>='2000'){
			$('#fileFormateModal').openModal({
				dismissible:false
			});

		}
		}


}


function clearFileName() {
	$('#unblockFileName').val('');
	$("#blockBulkFile").val('');
	$('#unblockFileName').val('');
	$("#editselectBulkBlockuploadFile").val('');
	$("#editBulkBlockuploadFile").val('');
	$('#fileFormateModal').closeModal();
}


$("input[type=file]").keypress(function(ev) {
    return false;
    //ev.preventDefault(); //works as well

});