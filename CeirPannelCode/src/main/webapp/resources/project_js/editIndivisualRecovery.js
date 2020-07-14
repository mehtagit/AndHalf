/**
 * 
 */



window.onload = function exampleFunction() { 
	
    // Function to be executed 

	 	
} 

$(document).ready(function() {
 // executes when HTML-Document is loaded and DOM is ready
	
	//alert("ready");
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
	headers:
	{ 'X-CSRF-TOKEN': token }
	});

	$.ajaxSetup({
		async: false
		});
	$.getJSON('./productList', function(data) {
	 	////console.log("start");
		////console.log(data)
	 		for (i = 0; i < data.length; i++) {
	 			
	 			$('<option>').val(data[i].id).text(data[i].brand_name)
	 					.appendTo('#editsigleRecoverydeviceBrandName');
	 			
	 		}
		/* setBrandName();*/
		 
	 	})
	 	 $('div#initialloader').fadeIn('fast');
	 	  setTimeout(function(){ 
	 		 
	 		  viewIndivisualStolen(); 
	 		  }, 1000);

	});


function viewIndivisualStolen()
{

	
var txnid=$('#existingStolenTxnId').val();
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$.ajaxSetup({
headers:
{ 'X-CSRF-TOKEN': token }
});

	$.ajax({
		url: './openStolenAndRecoveryPage?txnId='+txnid+"&requestType=1",
		type: 'POST',
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		////console.log(response)
		////console.log("1=="+response.stolenIndividualUserDB.deviceBrandName);

		
		 //	alert(response.stolenIndividualUserDB.deviceBrandName);
		$('#editsigleRecoverydeviceBrandName').val(response.stolenIndividualUserDB.deviceBrandName).change();
		$('#selectedBrandName').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#editsingleRecoverymodalNumber').val(response.stolenIndividualUserDB.modelNumber);
		$('#brandNameValue').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#sigleRecoverydeviceIDType').val(response.stolenIndividualUserDB.deviceIdType);
		$('#sigleRecoverydeviceType').val(response.stolenIndividualUserDB.deviceType);
		$('#sigleRecoverydeviceSimStatus').val(response.stolenIndividualUserDB.multiSimStatus);
		$('#sigleRecoveryserialNumber').val(response.stolenIndividualUserDB.deviceSerialNumber);
		$('#sigleRecoveryaddress').val(response.stolenIndividualUserDB.deviceStolenPropertyLocation);
		$('#sigleRecoverystreetNumber').val(response.stolenIndividualUserDB.deviceStolenStreet);
		$('#sigleRecoveryvillage').val(response.stolenIndividualUserDB.deviceStolenVillage);
		$('#sigleRecoverylocality').val(response.stolenIndividualUserDB.deviceStolenLocality);
		$('#sigleRecoverydistrict').val(response.stolenIndividualUserDB.deviceStolenDistrict);
		$('#sigleRecoverycommune').val(response.stolenIndividualUserDB.deviceStolenCommune);
		$('#sigleRecoverypin').val(response.stolenIndividualUserDB.deviceStolenPostalCode);
		$('#country1').val(response.stolenIndividualUserDB.deviceStolenCountry).change();
		$('#state1').val(response.stolenIndividualUserDB.deviceStolenProvince);
		//$('#sigleRecoverydeviceStatus').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#sigleRecovery').val(response.remark);
		$('#sigleRecoveryReject').val(response.rejectedRemark);
		$('#bulkRecoveryDate').val(response.dateOfRecovery);
		$('#sigleRecoveryimeiNumber1').val(response.stolenIndividualUserDB.imeiEsnMeid1);
		$('#sigleRecoveryimeiNumber2').val(response.stolenIndividualUserDB.imeiEsnMeid2);
		$('#sigleRecoveryimeiNumber3').val(response.stolenIndividualUserDB.imeiEsnMeid3);
		$('#sigleRecoveryimeiNumber4').val(response.stolenIndividualUserDB.imeiEsnMeid4);
		$("label[for='sigleRecoveryimeiNumber1']").addClass('active');
		$("label[for='sigleRecoveryimeiNumber2']").addClass('active');
		$("label[for='sigleRecoveryimeiNumber3']").addClass('active');
		$("label[for='sigleRecoveryimeiNumber4']").addClass('active');
		
		
		
		},
		error: function (jqXHR, textStatus, errorThrown) {
			//console.log("error in ajax")

		}
	});
}






function updateIndivisualRecovery()
{
  var formData= new FormData();
	 
	var sigleRecoverydeviceBrandName=$('#editsigleRecoverydeviceBrandName').val();
	var sigleRecoveryimeiNumber1=$('#sigleRecoveryimeiNumber1').val();
	var sigleRecoveryimeiNumber2=$('#sigleRecoveryimeiNumber2').val();
	var sigleRecoveryimeiNumber3=$('#sigleRecoveryimeiNumber3').val();
	var sigleRecoveryimeiNumber4=$('#sigleRecoveryimeiNumber4').val();
	
	var sigleRecoverydeviceIDType=$('#sigleRecoverydeviceIDType').val();
	var sigleRecoverydeviceType=$('#sigleRecoverydeviceType').val();
	var sigleRecoverydeviceSimStatus=$('#sigleRecoverydeviceSimStatus').val();
    var sigleRecoveryserialNumber=$('#sigleRecoveryserialNumber').val();
	var sigleRecoveryaddress=$('#sigleRecoveryaddress').val();
	var sigleRecoverystreetNumber=$('#sigleRecoverystreetNumber').val();
	var sigleRecoveryvillage=$('#sigleRecoveryvillage').val();
	var sigleRecoverylocality=$('#sigleRecoverylocality').val();
	var sigleRecoverydistrict=$('#sigleRecoverydistrict').val();
	var sigleRecoverycommune=$('#sigleRecoverycommune').val();
	var sigleRecoverypin=$('#sigleRecoverypin').val();
   /* var deviceRecoveryDate=$('#deviceRecoveryDevice').val();*/
	var sigleRecovery =$('#sigleRecovery').val();
	var country1=$('#country1').val();
	var state1=$('#state1').val();
	//var sigleRecoverydeviceStatus=$('#sigleRecoverydeviceStatus').val();
	//var sigleRecoveryBlockPeriod=$('#stolenDatePeriod').val();
	var blockingType ='Immediate';
	var IndivisualRecoveryDevice=$('#bulkRecoveryDate').val();
	var txnid=$('#existingStolenTxnId').val();
	
	var stolenIndividualUserDB={
			"deviceBrandName": sigleRecoverydeviceBrandName,
			"modelNumber":$('#editsingleRecoverymodalNumber').val(),
			"deviceIdType": sigleRecoverydeviceIDType,
			"deviceStolenCommune": sigleRecoverycommune,
			"deviceStolenDistrict": sigleRecoverydistrict,
			"deviceStolenLocality": sigleRecoverylocality,
			"deviceStolenPostalCode": sigleRecoverypin,
			"deviceStolenPropertyLocation": sigleRecoveryaddress,
			"deviceStolenStreet": sigleRecoverystreetNumber,
			"deviceStolenVillage": sigleRecoveryvillage,
			"deviceType":sigleRecoverydeviceType,
			"imeiEsnMeid1": sigleRecoveryimeiNumber1,
			"imeiEsnMeid2": sigleRecoveryimeiNumber2,
			"imeiEsnMeid3": sigleRecoveryimeiNumber3,
			"imeiEsnMeid4": sigleRecoveryimeiNumber4,
			"deviceStolenProvince": state1,
			"remark": sigleRecovery,
			"multiSimStatus":sigleRecoverydeviceSimStatus,
			"deviceStolenCountry":country1,
			"deviceSerialNumber":sigleRecoveryserialNumber
			
	}
	

	var request={
			"txnId":txnid,
			"dateOfRecovery":IndivisualRecoveryDevice,
			"blockingType":blockingType,
			"deviceQuantity":1,
			"requestType":1,
			"sourceType":4,
			"remark": sigleRecovery,
			"stolenIndividualUserDB":stolenIndividualUserDB
	}

	formData.append("request",JSON.stringify(request));
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
	headers:
	{ 'X-CSRF-TOKEN': token }
	});

	$.ajax({
		url: './lawfulIndivisualStolenUpdate',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
		
			if(response.errorCode=='0'){
				$("#indivisualStolenButton").prop('disabled', true);
				$('#stolenSucessPopUp').openModal({dismissible:false});
			
			}
			else{
//				$('#sucessMessage').text('');
				$('#indivisualStolenButton').openModal({dismissible:false});
				$('#dynamicMessage').text('');
				$('#dynamicMessage').text(response.message);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			//console.log("error in ajax")

		}
	});
	return false;

	}


/*function changeBrandValue(brand_id){
 	alert("ss"+brand_id);
 	var brand_id = $('#editsingleStolendeviceBrandName').val();
 	$.getJSON('./productModelList?brand_id=' + brand_id,
 			function(data) {
 				$("#editsingleRecoverymodalNumber").empty();
 				for (i = 0; i < data.length; i++) {
 					$('<option>').val(data[i].id).text(
 							data[i].modelName).appendTo(
 							'#editsingleRecoverymodalNumber');
 				}
 			});
 }*/

$('#editsigleRecoverydeviceBrandName').on(
		'change',
		function() {
			var brand_id = $('#editsigleRecoverydeviceBrandName').val();
		//	alert("ss"+brand_id);
			////console.log("ss"+brand_id);
			$.ajaxSetup({
				async: false
				});
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$.ajaxSetup({
			headers:
			{ 'X-CSRF-TOKEN': token }
			});

			$.getJSON('./productModelList?brand_id=' + brand_id,
					function(data) {
						$("#editsingleRecoverymodalNumber").empty();
						for (i = 0; i < data.length; i++) {
							$('<option>').val(data[i].id).text(
									data[i].modelName).appendTo(
									'#editsingleRecoverymodalNumber');
						}
					});
		});
function setBrandName()
{
	var selectedBrandName = $('#selectedBrandName').val()
	////console.log("selectedBrandName value=="+selectedBrandName);
	$('editsigleRecoverydeviceBrandName').val(selectedBrandName);
}

/*


$('#singleStolendeviceIDType').on('change', function() {
	var value=parseInt($(this).val());
	$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").val('');
	switch (value) {
	case 0:
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("pattern","[0-9]{15,16}");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("maxlength","16");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").removeAttr("onkeyup");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationIMEI')+"')");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationIMEI')+"')");
		$('#errorMsgOnModal').text($.i18n('IMEIMsg'));
		
		break;
	case 1:
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("pattern","[A-F0-9]{15,16}");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("maxlength","16");
        $("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").removeAttr("onkeyup");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationMEID')+"')");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationMEID')+"')");
		$('#errorMsgOnModal').text($.i18n('MEIDMsg'));
		break;
	case 2:
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").val('');
        $("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("pattern","[0-9]{8,11}");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("onkeyup","isLengthValid(this.value)");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("maxlength","11");	
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$("#errorMsgOnModal").text($.i18n('ESNMsg'));
		break;
	}

}); 

function isLengthValid(val){
	var deviceIDLength=val.length;
	if(!isNaN(val)){
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("pattern","[0-9]{11,11}");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("maxlength","11");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
	}
	else if(typeof val == 'string' || val instanceof String){
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("maxlength","8");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("pattern","[A-F0-9]{8,8}");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN8')+"')");
		$("#singleStolenimei1,#singleStolenimei2,#singleStolenimei3,#singleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN8')+"')");

	}
}
*/