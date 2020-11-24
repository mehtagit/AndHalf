/**
 * 
 */



window.onload = function exampleFunction() { 
	
    // Function to be executed 

	 	
} 

$(document).ready(function() {
 // executes when HTML-Document is loaded and DOM is ready
	
	////alert("ready");
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
	 	//////console.log("start");
		//////console.log(data)
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
		//////console.log(response)
		//////console.log("1=="+response.stolenIndividualUserDB.deviceBrandName);

		
		 //	//alert(response.stolenIndividualUserDB.deviceBrandName);
		$('#editsigleRecoverydeviceBrandName').val(response.stolenIndividualUserDB.deviceBrandName).change();
		$('#selectedBrandName').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#editsingleRecoverymodalNumber').val(response.stolenIndividualUserDB.modelNumber);
		$('#brandNameValue').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#sigleRecoverydeviceIDType').val(response.stolenIndividualUserDB.deviceIdType);
		$('#sigleRecoverydeviceType').val(response.stolenIndividualUserDB.deviceType);
		$('#sigleRecoverydeviceSimStatus').val(response.stolenIndividualUserDB.multiSimStatus);
		$('#sigleRecoveryserialNumber').val(response.stolenIndividualUserDB.deviceSerialNumber);
		if(response.stolenIndividualUserDB.deviceSerialNumber=="" || response.stolenIndividualUserDB.deviceSerialNumber==null){
			$('#sigleRecoveryserialNumber').val('NA');	
		}
		$('#sigleRecoveryaddress').val(response.stolenIndividualUserDB.deviceStolenPropertyLocation);
		$('#sigleRecoverystreetNumber').val(response.stolenIndividualUserDB.deviceStolenStreet);
		$('#sigleRecoveryvillage').val(response.stolenIndividualUserDB.deviceStolenVillage);
		if(response.stolenIndividualUserDB.deviceStolenVillage=="" || response.stolenIndividualUserDB.deviceStolenVillage==null){
			$('#sigleRecoveryvillage').val('NA');	
		}
		$('#sigleRecoverylocality').val(response.stolenIndividualUserDB.deviceStolenLocality);
		if(response.stolenIndividualUserDB.deviceStolenLocality=="" || response.stolenIndividualUserDB.deviceStolenLocality==null){
			$('#sigleRecoverylocality').val('NA');	
		}
		$('#sigleRecoverydistrict').val(response.stolenIndividualUserDB.deviceStolenDistrict);
		$('#sigleRecoverycommune').val(response.stolenIndividualUserDB.deviceStolenCommune);
		$('#sigleRecoverypin').val(response.stolenIndividualUserDB.deviceStolenPostalCode);
		$('#country1').val(response.stolenIndividualUserDB.deviceStolenCountry).change();
		$('#state1').val(response.stolenIndividualUserDB.deviceStolenProvince);
		//$('#sigleRecoverydeviceStatus').val(response.stolenIndividualUserDB.deviceBrandName);
		$('#sigleRecovery').val(response.remark);
		if(response.remark=="" || response.remark==null){
			$('#sigleRecovery').val('NA');	
		}
		$('#sigleRecoveryReject').val(response.rejectedRemark);
		if(response.rejectedRemark=="" || response.rejectedRemark==null){
			$('#sigleRecoveryReject').val('NA');	
		}
		$('#bulkRecoveryDate').val(response.dateOfRecovery);
		$('#sigleRecoveryimeiNumber1').val(response.stolenIndividualUserDB.imeiEsnMeid1);
		if(response.stolenIndividualUserDB.imeiEsnMeid1=="" || response.stolenIndividualUserDB.imeiEsnMeid1==null){
			$('#sigleRecoveryimeiNumber1').val('NA');	
		}
		$('#sigleRecoveryimeiNumber2').val(response.stolenIndividualUserDB.imeiEsnMeid2);
		if(response.stolenIndividualUserDB.imeiEsnMeid2=="" || response.stolenIndividualUserDB.imeiEsnMeid2==null){
			$('#sigleRecoveryimeiNumber2').val('NA');	
		}
		$('#sigleRecoveryimeiNumber3').val(response.stolenIndividualUserDB.imeiEsnMeid3);
		if(response.stolenIndividualUserDB.imeiEsnMeid3=="" || response.stolenIndividualUserDB.imeiEsnMeid3==null){
			$('#sigleRecoveryimeiNumber3').val('NA');	
		}
		$('#sigleRecoveryimeiNumber4').val(response.stolenIndividualUserDB.imeiEsnMeid4);
		if(response.stolenIndividualUserDB.imeiEsnMeid4=="" || response.stolenIndividualUserDB.imeiEsnMeid4==null){
			$('#sigleRecoveryimeiNumber4').val('NA');	
		}
		$("label[for='sigleRecoveryimeiNumber1']").addClass('active');
		$("label[for='sigleRecoveryimeiNumber2']").addClass('active');
		$("label[for='sigleRecoveryimeiNumber3']").addClass('active');
		$("label[for='sigleRecoveryimeiNumber4']").addClass('active');
		
		if ($('#pageViewType').val() == 'edit') {
			if(response.stolenIndividualUserDB.imeiEsnMeid2=="" || response.stolenIndividualUserDB.imeiEsnMeid2==null){
				$('#sigleRecoveryimeiNumber2').val('');	
			}
			if(response.stolenIndividualUserDB.imeiEsnMeid1=="" || response.stolenIndividualUserDB.imeiEsnMeid1==null){
				$('#sigleRecoveryimeiNumber1').val('');	
			}
			if(response.stolenIndividualUserDB.imeiEsnMeid3=="" || response.stolenIndividualUserDB.imeiEsnMeid3==null){
				$('#sigleRecoveryimeiNumber3').val('');	
			}
			if(response.stolenIndividualUserDB.imeiEsnMeid4=="" || response.stolenIndividualUserDB.imeiEsnMeid4==null){
				$('#sigleRecoveryimeiNumber4').val('');	
			}
			if(response.stolenIndividualUserDB.imeiEsnMeid1!="" || response.stolenIndividualUserDB.imeiEsnMeid1!=null){
				$("#singleStolendeviceIDType").attr("required", true);
				
				$("#deviceIdTypeSpan").css("display", "none");	
			}
		}
		
		},
		error: function (jqXHR, textStatus, errorThrown) {
			////console.log("error in ajax")

		}
	});
}






function updateIndivisualRecovery()
{
	if($('#sigleRecoverydeviceIDType').val()==0){
		var checkIMEI=checkDuplicateImei($('#sigleRecoveryimeiNumber1').val(),$('#sigleRecoveryimeiNumber2').val(),$('#sigleRecoveryimeiNumber3').val(),$('#sigleRecoveryimeiNumber4').val());
		if(checkIMEI===true){
		$('#errorMsgOnModal').text('');
		$('#errorMsgOnModal').text($.i18n('duplicateImeiMessage'));
		return false;
	}
	}
  var formData= new FormData();
  $('div#initialloader').fadeIn('fast');
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
	
	if(sigleRecoverydeviceIDType==''){
		sigleRecoverydeviceIDType=null;
	}
	if(sigleRecoverydeviceType==''){
		sigleRecoverydeviceType=null;
	}
	
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
			$('div#initialloader').delay(300).fadeOut('slow');
			if(response.errorCode=='0'){
				$("#indivisualStolenButton").prop('disabled', true);
				$('#stolenSucessPopUp').openModal({dismissible:false});
			
			}
			else if(response.errorCode=='5'){
				
				$('#stolenSucessPopUp').openModal({dismissible:false});
				$('#dynamicMessage').text('');
				$('#dynamicMessage').text($.i18n(response.tag));
			
			}
			else{
//				$('#sucessMessage').text('');
				$('#indivisualStolenButton').openModal({dismissible:false});
				$('#dynamicMessage').text('');
				$('#dynamicMessage').text(response.message);
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			////console.log("error in ajax")

		}
	});
	return false;

	}


/*function changeBrandValue(brand_id){
 	//alert("ss"+brand_id);
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
		//	//alert("ss"+brand_id);
			//////console.log("ss"+brand_id);
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
	//////console.log("selectedBrandName value=="+selectedBrandName);
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


$(document).on("keyup", "#updatesingleStolenimei1", function(e) {
	var singleStolenimei1=$('#updatesingleStolenimei1').val();
	if(singleStolenimei1.length<'1' )
	{
		$("#singleStolendeviceIDType").attr("required", false);
		/*$('#currency').attr("disabled",true);*/
		/*$('#currencyDiv').hide();

		$("#currency")[0].selectedIndex = 0;*/
		$("#deviceIdTypeSpan").css("display", "none");
	}
	else
	{
		$('#singleStolendeviceIDType').prop('required',true);
		//$("#currency").attr("required", true);
		/*$('#currency').attr("disabled",false);*/
		$("#deviceIdTypeSpan").css("display", "block");

	}
});
