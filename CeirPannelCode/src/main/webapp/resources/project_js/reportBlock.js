

function showSingleImeiBlock()
{
	$("#SingleImeiBlock").css("display", "block");
	$("#multipleImeiBlock").css("display", "none");
	

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
	
	$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#blockdeviceIdType');
			console.log('#blockdeviceIdType');
		}
	});
	
	

}



function showMultipleImeiBlock()
{
	$("#multipleImeiBlock").css("display", "block");
	$("#SingleImeiBlock").css("display", "none");
	
	$.getJSON('./getDropdownList/GRIEVANCE_CATEGORY', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#bulkBlockdeviceCategory');
			console.log('#bulkBlockdeviceCategory');
		}
	});
}

function showSingleImeiUnBlock()
{
	$("#SingleImeiUnBlock").css("display", "block");
	$("#multipleImeiUnBlock").css("display", "none");
	
$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#unbockSingledeviceType');
			console.log('#unbockSingledeviceType')
		}
	});

	$.getJSON('./getDropdownList/MULTI_SIM_STATUS', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#unbockSingleMultipleSimStatus');
			console.log('#unbockSingleMultipleSimStatus');
		}
	});
	
	$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#UnblockdeviceIdType');
			console.log('#UnblockdeviceIdType');
		}
	});
}



function showMultipleImeiUnBlock()
{
	$("#multipleImeiUnBlock").css("display", "block");
	$("#SingleImeiUnBlock").css("display", "none");
	$.getJSON('./getDropdownList/GRIEVANCE_CATEGORY', function(data) {
		
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#bulkunBlockdeviceCategory');
			console.log('#bulkunBlockdeviceCategory');
		}
	});
}



function submitBlockImei()
{
	 
		var bulkBlockdeviceCategory = $('#bulkBlockdeviceCategory').val();
		var blockbulkquantity = $('#blockbulkquantity').val();
		//var blockBulkFile = $('#blockBulkFile').val();
		var blockUnblockRemark = $('#blockbulkRemark').val();
		
		var ModeType='2';
		var requestType='2';
		var roleType = $("body").attr("data-roleType");
		var userId = $("body").attr("data-userID");
		console.log("bulkBlockdeviceCategory="+bulkBlockdeviceCategory+" blockbulkquantity=="+blockbulkquantity+" blockUnblockRemark="+blockUnblockRemark)
		
		var formData = new FormData();
		formData.append('file', $('#blockBulkFile')[0].files[0]);
		formData.append('deviceCaegory', bulkBlockdeviceCategory);
		formData.append('qty', blockbulkquantity);
		formData.append('remark', blockbulkRemark);
		formData.append('sourceType', ModeType);
		formData.append('requestType', requestType);
		formData.append('userId',userId);
		formData.append('roleType',roleType);
			

		$.ajax({
			url: './reportblockBulkFile',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function (data, textStatus, jqXHR) {

				console.log(data);
				//$('#fileStolenModal').closeModal();
				$('#markBulkAsBlock').openModal();
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

function submitUnBlockImei()
{
	    var unblockbulkquantity = $('#unblockbulkquantity').val();
		//var blockBulkFile = $('#blockBulkFile').val();
	    var bulkunBlockdeviceCategory=$('#bulkunBlockdeviceCategory').val();
		var unblockbulkRemark = $('#unblockbulkRemark').val();
		var ModeType='2';
		var requestType='3';
		var roleType = $("body").attr("data-roleType");
		var userId = $("body").attr("data-userID");
		//console.log("bulkBlockdeviceCategory="+bulkBlockdeviceCategory+" blockbulkquantity=="+blockbulkquantity+" blockUnblockRemark="+blockUnblockRemark)
		var formData = new FormData();
		formData.append('file', $('#unblockBulkFile')[0].files[0]);
		formData.append('qty', unblockbulkquantity);
		formData.append('deviceCaegory', bulkunBlockdeviceCategory);
		formData.append('remark', unblockbulkRemark);
		formData.append('sourceType', ModeType);
		formData.append('requestType', requestType);
		formData.append('userId',userId);
		formData.append('roleType',roleType);
$.ajax({
			url: './reportUnblockBulkFile',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function (data, textStatus, jqXHR) {
			console.log(data);
//		 console.log(data);
			$('#markBulkAsUnblock').openModal();
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
	var requestType=2;
	
	console.log("****");
	console.log("sucess include deviceType="+deviceType+" multipleSimStatus="+multipleSimStatus+" serialNumber="+serialNumber+" remark="+remark+" IMEI1="+IMEI1 );

var singleImeiBlockDetail={
	 	
		'deviceType':deviceType,
		'deviceIdType':blockdeviceIdType,
	 	'multipleSimStatus':multipleSimStatus,
	 	'serialNumber':serialNumber,
		'IMEI1':IMEI1,
		'IMEI2':IMEI2,
		'IMEI3':IMEI3,
		'IMEI4':IMEI4,
		'requestType':requestType,
		'remark':remark
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
			
			 console.log(data);
			 $('#markAsBlock').openModal();
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
	
	console.log("****");
	console.log("sucess include deviceType="+deviceType+" multipleSimStatus="+multipleSimStatus+" serialNumber="+serialNumber+" remark="+remark+" IMEI1="+IMEI1 );

var singleImeiBlockDetail={
	 	
		'deviceType':deviceType,
		'deviceIdType':blockdeviceIdType,
	 	'multipleSimStatus':multipleSimStatus,
	 	'serialNumber':serialNumber,
		'IMEI1':IMEI1,
		'IMEI2':IMEI2,
		'IMEI3':IMEI3,
		'IMEI4':IMEI4,
		'requestType':requestType,
		'remark':remark,
	
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
			
			 console.log(data);
			 $('#markAsUnblock').openModal();
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








//*******************************************View Pop up data *************************************************************************************************
function viewDeviceDetails(txnId,popUpType){

	
	var role = currentRoleType == null ? roleType : currentRoleType;
	console.log("popUpType=="+popUpType);
	$.ajax({
		url : "./openbulkView?reqType=editPage&txnId="+txnId+'&modeType=singleDeivce',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		type : 'GET',
		async :false,
		success : function(data) {
			console.log(data);
			setViewBulkPopUp(data,popUpType);
		},
		error : function() {
			alert("Failed");
		}
	});
}


function setViewBulkPopUp(data,popUpType){
	console.log("++++++++++"+popUpType);
	if(popUpType=='view'){	
	$("#viewBlockDeviceModal").openModal();
	$("#viewBulkBlockCategory").val(data.categoryInterp);
	$("#viewBulkBlockRemark").val(data.remark);
	$("#viewBulkBlockuploadFile").val(data.fileName);
	$("#viewBulkBlockquantity").val(data.qty);
	$("#viewBulkBlockTxnId").val(data.txnId);
	}
	else
	{
	$("#editBulkBlockDeviceModal").openModal();
	$("#editBulkBlockCategory").val(data.categoryInterp);
	$("#editBulkBlockRemark").val(data.remark);
	$("#editBulkBlockuploadFile").val(data.fileName);
	$("#editBulkBlockquantity").val(data.qty);
	$("#editBulkBlockTxnId").val(data.txnId);
	$("#editBulkBlockrequestType").val(data.requestType);
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

var formData = new FormData();
formData.append('file', $('#unblockBulkFile')[0].files[0]);
formData.append('qty', unblockbulkquantity);
formData.append('deviceCaegory', bulkunBlockdeviceCategory);
formData.append('remark', unblockbulkRemark);
formData.append('sourceType', ModeType);
formData.append('requestType', requestType);
formData.append('userId',userId);
formData.append('roleType',roleType);
$.ajax({
	url: './updateFileTypeStolenRecovery',
	type: 'POST',
	data: formData,
	processData: false,
	contentType: false,
	success: function (data, textStatus, jqXHR) {
	console.log(data);
// console.log(data);
	$('#confirmEditBlockUnblock').openModal();
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


}



