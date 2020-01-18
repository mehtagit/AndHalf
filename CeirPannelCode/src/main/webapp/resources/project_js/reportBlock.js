

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
			'category':category
			
			
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
			'category':category
		
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
				setViewBulkPopUp(data,popUpType,requestType);
			},
			error : function() {
				alert("Failed");
			}
		});
	}


	function setViewBulkPopUp(data,popUpType,requestType){
		
	$.getJSON('./getDropdownList/BLOCK_CATEGORY', function(data) {
			$('#editBulkBlockCategory').empty();
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].value).text(data[i].interp)
				.appendTo('#editBulkBlockCategory');
				console.log('#editBulkBlockCategory');
			}
		});
		if(popUpType=='view'){	
			console.log("++++++++++"+popUpType+"requestType="+requestType);
			
		$("#viewBulkBlockDeviceModal").openModal();
		$("#viewBulkBlockCategory").val(data.blockCategoryInterp);
		$("#viewBulkBlockRemark").val(data.remark);
		$("#viewBulkBlockuploadFile").val(data.fileName);
		$("#viewBulkBlockquantity").val(data.qty);
		$("#viewBulkBlockTxnId").val(data.txnId);
		}
		else
		{
			console.log("++++++++++"+popUpType+" requestType="+requestType);
		$("#editBulkBlockDeviceModal").openModal();
		$("#editBulkBlockCategory").val(data.blockCategory).change();
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
	var roleType = $("body").attr("data-roleType");
	var userId = $("body").attr("data-userID");
	var formData = new FormData();
	formData.append('file', $('#editselectBulkBlockuploadFile')[0].files[0]);
	formData.append('qty', qty);
	formData.append('blockCategory', categoryInterp);
	formData.append('remark', remark);
	formData.append('sourceType', ModeType);
	formData.append('requestType', requestType);
	formData.append('userId',userId);
	formData.append('roleType',roleType);
	formData.append('txnId',txnId);
	formData.append('fileName',fileName);
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
	return false;

	}



	function viewblockImeiDevice(txnId,popUpType,requestType)
	{


		
		$.ajax({
			url : "./openSingleImeiForm?reqType=editPage&txnId="+txnId+'&modeType=singleDeivce',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			type : 'GET',
			async :false,
			success : function(data) {
				console.log(data);
				setSingleDeviceViewPopUp(data,popUpType,requestType);
			},
			error : function() {
				alert("Failed");
			}
		});
		
		
	}




	$.getJSON('./getDropdownList/DEVICE_TYPE', function(data) {
		$("#editblockdeviceType").empty();
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#editblockdeviceType,#viewblockdeviceType');
		
		}
	});

	$.getJSON('./getDropdownList/BLOCK_CATEGORY', function(data) {
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
			.appendTo('#editblockmultipleSimStatus,#viewblockmultipleSimStatus');
		
		}
	});

	$.getJSON('./getDropdownList/DEVICE_ID_TYPE', function(data) {
		$("#editblockdeviceIdType").empty();
		for (i = 0; i < data.length; i++) {
			$('<option>').val(data[i].value).text(data[i].interp)
			.appendTo('#editblockdeviceIdType,#viewblockdeviceIdType');
			
		}
	});




	function setSingleDeviceViewPopUp(data,popUpType,requestType){


		if(popUpType=='view'){
			$('#viewblockImeiDevice').openModal();
			console.log("++++++++++"+popUpType+"requestType="+requestType);
			for (i = 0; i < data.length; i++) {
				

		
				if(data[i].secondImei==0 && data[i].thirdImei==0 && data[i].fourthImei==0  )
				{
				$("#viewsingleblockIMEI2").val("");
				$("#viewsingleblockIMEI3").val("");
				$("#viewsingleblockIMEI4").val("");
				}
			else if(data[i].secondImei==0 && data[i].thirdImei==0 &&  data[i].fourthImei!=0 )
				{
				$("#viewsingleblockIMEI2").val("");
				$("#viewsingleblockIMEI3").val("");
				$("#viewsingleblockIMEI4").val(data[i].fourthImei);
				}
			else if(data[i].secondImei!=0 && data[i].thirdImei==0 && data[i].fourthImei==0)
				{
				$("#viewsingleblockIMEI2").val(data[i].secondImei);
				$("#viewsingleblockIMEI3").val("");
				$("#viewsingleblockIMEI4").val("");
				}
			else if(data[i].secondImei==0 && data[i].thirdImei!=0 &&  data[i].fourthImei==0)
				{
				$("#viewsingleblockIMEI2").val("");
				$("#viewsingleblockIMEI3").val(data[i].thirdImei);
				$("#viewsingleblockIMEI4").val("");
				}
			else{
				console.log("else############")
			}
		$("#viewblockdeviceType").val(data[i].deviceType).change();
		$("#viewblockdeviceIdType").val(data[i].deviceIdType).change();
		$("#viewblockmultipleSimStatus").val(data[i].multipleSimStatus);
		$("#viewsingleblockserialNumber").val(data[i].deviceSerialNumber);
		$("#viewsingleblockremark").val(data[i].remark);
		$("#viewsingleblockIMEI1").val(data[i].firstImei);
		$("#viewsingleblocTxnid").val(data[i].txnId);
		$("#viewsingleblockCategory").val(data[i].categoryInterp);
		if(data[i].blockingType=='tilldate')
			{
			$("#viewsingleblockingType").val(data[i].blockingTimePeriod);
			}
		else{
		$("#viewsingleblockingType").val(data[i].blockingType);
		}
		}
		}
		else
		{
	/*		 $("#editblockdeviceType option").remove();
			$("#editblockmultipleSimStatus option").remove();
			$("#editblockdeviceIdType option").remove();
			$("#editbulkBlockdeviceCategory option").remove();*/
			console.log("++++++++++"+popUpType+" requestType="+requestType);
		$("#editblockImeiDevice").openModal();

			
			
			
			for (i = 0; i < data.length; i++) {
			if(data[i].secondImei==0 && data[i].thirdImei==0 && data[i].fourthImei==0  )
				{
				$("#editsingleblockIMEI2").val("");
				$("#editsingleblockIMEI3").val("");
				$("#editsingleblockIMEI4").val("");
				}
			else if(data[i].secondImei==0 && data[i].thirdImei==0 &&  data[i].fourthImei!=0 )
				{
				$("#editsingleblockIMEI2").val("");
				$("#editsingleblockIMEI3").val("");
				$("#editsingleblockIMEI4").val(data[i].fourthImei);
				}
			else if(data[i].secondImei!=0 && data[i].thirdImei==0 && data[i].fourthImei==0)
				{
				$("#editsingleblockIMEI2").val(data[i].secondImei);
				$("#editsingleblockIMEI3").val("");
				$("#editsingleblockIMEI4").val("");
				}
			else if(data[i].secondImei==0 && data[i].thirdImei!=0 &&  data[i].fourthImei==0)
				{
				$("#editsingleblockIMEI2").val("");
				$("#editsingleblockIMEI3").val(data[i].thirdImei);
				$("#editsingleblockIMEI4").val("");
				}
			else{
				console.log("else############");
			}
			console.log("device id type="+data[i].deviceIdType);
			$("#editblockdeviceType").val(data[i].deviceType).change();
			$("#editblockdeviceIdType").val(data[i].deviceIdType).change();
			$("#editblockmultipleSimStatus").val(data[i].multipleSimStatus);
			$("#editsingleblockserialNumber").val(data[i].deviceSerialNumber);
			$("#editsingleblockremark").val(data[i].remark);
			$("#editsingleblockIMEI1").val(data[i].firstImei);
			$("#editsingleblockTxnid").val(data[i].txnId);
			$("#editbulkBlockdeviceCategory").val(data[i].category);
			$('input[name=editbulkBlockdeviceradio][value='+data[i].blockingTimePeriod+']').attr('checked', true); 
			$("#editsingleblocRequestType").val(requestType);
			}
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
				$('#confirmEditBlockUnblock').openModal();
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
		
	}

