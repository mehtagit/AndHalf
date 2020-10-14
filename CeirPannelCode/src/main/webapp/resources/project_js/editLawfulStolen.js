/**
 * 
 */




/*$('#singleStolendeviceBrandName').on(
		'change',
		function() {
		
		});*/

populateCountries("singleDevicecountry", "singleDevicestate");
	populateStates("singleDevicecountry", "singleDevicestate");

	populateCountries("country", "state");
	populateStates("country", "state");
	
	
setTimeout(function(){

	$('.datepick').datepicker({
		dateFormat : "yy-mm-dd",
		maxDate:"0"
	});

	$('#stolenDatePeriodedit').datepicker({
		dateFormat : "yy-mm-dd",
		minDate: "0"
	});

	/*var input = document.querySelector("#singleStolenphone1");
	window.intlTelInput(input, {
		utilsScript : "${context}/resources/js/utils.js",
	});*/
	$('#singleStolenphone1').val(window.xop);
}, 1000);



$(document).ready(function() {
	// executes when HTML-Document is loaded and DOM is ready
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
	headers:
	{ 'X-CSRF-TOKEN': token }
	});

	$.ajax({
		url: './productList',
		type: 'GET',
		processData: false,
		contentType: false,
		async:false,
		success: function (data, textStatus, jqXHR) {
			////console.log(data)
			for (i = 0; i < data.length; i++) {
				$('<option>').val(data[i].id).text(data[i].brand_name)
						.appendTo('#editsingleStolendeviceBrandName');
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			////console.log("error in ajax")

		}
	});
	
	$('div#initialloader').fadeIn('fast');
	  setTimeout(function(){ 
	 		 
 		  viewIndivisualStolen(); 
 		  }, 1000);
	
	  
	  
		/*var promise = new Promise(function(resolve, reject) {
			//alert("promise");
			$.getJSON('./productList', function(data) {
				////console.log(data)
				
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].id).text(data[i].brand_name)
							.appendTo('#editsingleStolendeviceBrandName');
				}
			});
		resolve();	
		});

		promise.then(function() {
			//alert("then");
			$('div#initialloader').fadeIn('fast');
			 setTimeout(function(){ viewIndivisualStolen(); }, 5000);
			viewIndivisualStolen();
		});*/
		
	
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
		url: './openStolenAndRecoveryPage?txnId='+txnid+"&requestType=0",
		type: 'POST',
		processData: false,
		contentType: false,
		success: function (response, textStatus, jqXHR) {
			////console.log(response)

			$('#singleStolenfirstName').val(response.stolenIndividualUserDB.firstName);
			$('#singleStolenmiddleName').val(response.stolenIndividualUserDB.middleName);
			if(response.stolenIndividualUserDB.middleName=="" || response.stolenIndividualUserDB.middleName==null){
				$('#singleStolenmiddleName').val('NA');
			}
			$('#singleStolenlastName').val(response.stolenIndividualUserDB.lastName);
			$('#singleStolennIDPassportNumber').val(response.stolenIndividualUserDB.nid);
			$('#singleStolenemail').val(response.stolenIndividualUserDB.email);
			if(response.stolenIndividualUserDB.email=="" || response.stolenIndividualUserDB.email==null){
				$('#singleStolenemail').val('NA');
			}

			previousVal = "";
			function InputChangeListener()
			{
				if($('#singleStolenphone1').val()
						!= previousVal)
				{
					previousVal  = $('#singleStolenphone1').val();
					$('#singleStolenphone1').change();    
				}
			}

			setInterval(InputChangeListener, 200);

			




			previousVal2 = "";
			function InputChangeListener2()
			{
				if($('#singleStolenphone2').val()
						!= previousVal2)
				{
					previousVal2  = $('#singleStolenphone2').val();
					$('#singleStolenphone2').change();    
				}
			}

			setInterval(InputChangeListener2, 300);


			//	//alert(response.stolenIndividualUserDB.country);
			window.xop=response.stolenIndividualUserDB.alternateContactNumber;
			$('#singleStolenphone1').val(response.stolenIndividualUserDB.alternateContactNumber);

			$('#singleStolenaddress').val(response.stolenIndividualUserDB.propertyLocation);
			////alert(response.stolenIndividualUserDB.street+"-----"+response.stolenIndividualUserDB.alternateContactNumber)
			$('#singleStolenstreetNumber').val(response.stolenIndividualUserDB.street);
			$('#singleStolenvillage').val(response.stolenIndividualUserDB.village);
			if(response.stolenIndividualUserDB.village=="" || response.stolenIndividualUserDB.village==null){
				$('#singleStolenvillage').val('NA');
			}
			$('#singleStolenlocality').val(response.stolenIndividualUserDB.locality);
			if(response.stolenIndividualUserDB.locality=="" || response.stolenIndividualUserDB.locality==null){
				$('#singleStolenlocality').val('NA');
			}
			$('#singleStolendistrict').val(response.stolenIndividualUserDB.district);
			$('#singleStolencommune').val(response.stolenIndividualUserDB.commune);
			$('#singleStolenpin').val(response.stolenIndividualUserDB.postalCode);
			$('#country').val(response.stolenIndividualUserDB.country).change();
			$('#state').val(response.stolenIndividualUserDB.province);
			$('#editsingleStolendeviceBrandName').val(response.stolenIndividualUserDB.deviceBrandName).change();
			////alert(response.stolenIndividualUserDB.deviceBrandName);
			$('#editsingleStolenmodalNumber').val(response.stolenIndividualUserDB.modelNumber);
			$('#singleStolenFileName').val(response.stolenIndividualUserDB.nidFileName);
			$('#updatesingleStolenimei1').val(response.stolenIndividualUserDB.imeiEsnMeid1);
			$('#updatesingleStolenimei2').val(response.stolenIndividualUserDB.imeiEsnMeid2);
			if(response.stolenIndividualUserDB.imeiEsnMeid2=="" || response.stolenIndividualUserDB.imeiEsnMeid2==null){
				$('#updatesingleStolenimei2').val("NA");	
			}

			$('#updatesingleStolenimei3').val(response.stolenIndividualUserDB.imeiEsnMeid3);
			if(response.stolenIndividualUserDB.imeiEsnMeid3=="" || response.stolenIndividualUserDB.imeiEsnMeid3==null){
				$('#updatesingleStolenimei3').val("NA");	
			}
			$('#updatesingleStolenimei4').val(response.stolenIndividualUserDB.imeiEsnMeid4);
			if(response.stolenIndividualUserDB.imeiEsnMeid4=="" || response.stolenIndividualUserDB.imeiEsnMeid4==null){
				$('#updatesingleStolenimei4').val("NA");	
			}
			$('#singleStolendeviceIDType').val(response.stolenIndividualUserDB.deviceIdType);
			$('#singleStolendeviceType').val(response.stolenIndividualUserDB.deviceType);
			$('#editsingleStolenmodalNumber').val(response.stolenIndividualUserDB.modelNumber);
			/*$('#singleStolenFileName').val(response.fileName);*/
			window.xop2=response.stolenIndividualUserDB.contactNumber;
			$('#singleStolenphone2').val(response.stolenIndividualUserDB.contactNumber);
			$('#singleStolenphone3').val(response.stolenIndividualUserDB.contactNumber2);
			if(response.stolenIndividualUserDB.contactNumber2=="" || response.stolenIndividualUserDB.contactNumber2==null){
				$('#singleStolenphone3').val("NA");	
			}
			$('#singleStolenphone4').val(response.stolenIndividualUserDB.contactNumber3);
			if(response.stolenIndividualUserDB.contactNumber3=="" || response.stolenIndividualUserDB.contactNumber3==null){
				$('#singleStolenphone4').val("NA");	
			}
			$('#singleStolenphone5').val(response.stolenIndividualUserDB.contactNumber4);
			if(response.stolenIndividualUserDB.contactNumber4=="" || response.stolenIndividualUserDB.contactNumber4==null){
				$('#singleStolenphone5').val("NA");	
			}
			$('#singleStolenOperator').val(response.stolenIndividualUserDB.operator);
			$('#singleStolenOperator3').val(response.stolenIndividualUserDB.operator2);
			$('#singleStolenOperator4').val(response.stolenIndividualUserDB.operator3);
			$('#singleStolenOperator5').val(response.stolenIndividualUserDB.operator4);
			$('#singleStolenSimStatus').val(response.stolenIndividualUserDB.multiSimStatus);
			$('#singleStolenComplaintType').val(response.complaintType);
			$('#singleDeviceAddress').val(response.stolenIndividualUserDB.deviceStolenPropertyLocation);
			$('#singleDevicestreetNumber').val(response.stolenIndividualUserDB.deviceStolenStreet);
			$('#singleDevicevillage').val(response.stolenIndividualUserDB.deviceStolenVillage);
			if(response.stolenIndividualUserDB.deviceStolenVillage=="" || response.stolenIndividualUserDB.deviceStolenVillage==null){
				$('#singleDevicevillage').val("NA");	
			}
			$('#singleDevicelocality').val(response.stolenIndividualUserDB.deviceStolenLocality);
			if(response.stolenIndividualUserDB.deviceStolenLocality=="" || response.stolenIndividualUserDB.deviceStolenLocality==null){
				$('#singleDevicelocality').val("NA");	
			}
			$('#singleDevicedistrict').val(response.stolenIndividualUserDB.deviceStolenDistrict);
			$('#singleDevicecommune').val(response.stolenIndividualUserDB.deviceStolenCommune);
			$('#singleDevicepin').val(response.stolenIndividualUserDB.deviceStolenPostalCode);

			$('#singleDevicecountry').val(response.stolenIndividualUserDB.deviceStolenCountry).change();
			$('#singleDevicestate').val(response.stolenIndividualUserDB.deviceStolenProvince);
			$('#singleDeviceRemark').val(response.stolenIndividualUserDB.remark);
			if(response.stolenIndividualUserDB.remark=="" || response.stolenIndividualUserDB.remark==null){
				$('#singleDeviceRemark').val("NA");	
			}
			$('#IndivisualStolenDate').val(response.dateOfStolen);
			
			$("#singleDeviceRejectRemark").val(response.rejectedRemark);
			if(response.rejectedRemark=="" || response.rejectedRemark==null){
				$('#singleDeviceRejectRemark').val("NA");	
			}
			//$('#singleStolenFileName').val(response.firFileName);
			
			
$('input[name=editbulkBlockdeviceradio][value='+response.blockingType+']').attr('checked', true); 

if(response.blockingType=='tilldate')
{
$("#calender").css("display", "block"); 

$("#stolenDatePeriodedit").val(response.blockingTimePeriod);
}
else{
$("#calender").css("display", "none"); 
}
			$("label[for='IndivisualStolenDate']").addClass('active');
			$("label[for='updatesingleStolenimei1']").addClass('active');
			$("label[for='updatesingleStolenimei1']").addClass('active');
			$("label[for='updatesingleStolenimei2']").addClass('active');
			$("label[for='updatesingleStolenimei3']").addClass('active');
			$("label[for='updatesingleStolenimei4']").addClass('active');

			$('#PassportNidLink').attr("onclick",'previewFile("'+response.fileLink+'","'+response.stolenIndividualUserDB.nidFileName+'","'+response.txnId+'")');
			$('#firImageLink').attr("onclick",'previewFile("'+response.fileLink+'","'+response.firFileName+'","'+response.txnId+'")');
			$('#uploadFirSingleName').val(response.firFileName);
			if(response.firFileName=="" || response.firFileName==null){
				$('#uploadFirSingleName').val("NA");	
				 $("#firImageLink").removeAttr('onclick');
				 $("#firImageLink").removeAttr('href');
			}
			$('div#initialloader').delay(300).fadeOut('slow');
			
			if ($('#pageViewType').val() == 'edit') {
				if(response.stolenIndividualUserDB.imeiEsnMeid1=="" || response.stolenIndividualUserDB.imeiEsnMeid1==null){
					$('#updatesingleStolenimei1').val("");	
				}
				if(response.stolenIndividualUserDB.imeiEsnMeid2=="" || response.stolenIndividualUserDB.imeiEsnMeid2==null){
					$('#updatesingleStolenimei2').val("");	
				}
				if(response.stolenIndividualUserDB.imeiEsnMeid3=="" || response.stolenIndividualUserDB.imeiEsnMeid3==null){
					$('#updatesingleStolenimei3').val("");	
				}
				if(response.stolenIndividualUserDB.imeiEsnMeid4=="" || response.stolenIndividualUserDB.imeiEsnMeid4==null){
					$('#updatesingleStolenimei4').val("");	
				}
				if(response.stolenIndividualUserDB.contactNumber2=="" || response.stolenIndividualUserDB.contactNumber2==null){
					$('#singleStolenphone3').val("");	
				}
				if(response.stolenIndividualUserDB.contactNumber3=="" || response.stolenIndividualUserDB.contactNumber3==null){
					$('#singleStolenphone4').val("");	
				}
				if(response.stolenIndividualUserDB.contactNumber4=="" || response.stolenIndividualUserDB.contactNumber4==null){
					$('#singleStolenphone5').val("");	
				}
				if(response.stolenIndividualUserDB.email=="" || response.stolenIndividualUserDB.email==null){
					$('#singleStolenemail').val('');
				}
				setOpertorTypeMandaotry();
			}
			
				
		},
		error: function (jqXHR, textStatus, errorThrown) {
			////console.log("error in ajax")

		}
	});
}






function updateIndivisualStolen()
{
	if($('#singleStolendeviceIDType').val()==0){
		var checkIMEI=checkDuplicateImei($('#updatesingleStolenimei1').val(),$('#updatesingleStolenimei2').val(),$('#updatesingleStolenimei3').val(),$('#updatesingleStolenimei4').val());
		if(checkIMEI===true){
		$('#errorMsgOnModal').text('');
		$('#errorMsgOnModal').text($.i18n('duplicateImeiMessage'));
		return false;
	}
	}
	$('div#initialloader').fadeIn('fast');

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
	var blockingTimePeriod=$('#stolenDatePeriodedit').val();
	var blockingType =$('.blocktypeRadio:checked').val();


	var singleStolendeviceBrandName=$('#editsingleStolendeviceBrandName').val();
	var updatesingleStolenimei1=$('#updatesingleStolenimei1').val();
	var updatesingleStolenimei2=$('#updatesingleStolenimei2').val();
	var updatesingleStolenimei3=$('#updatesingleStolenimei3').val();
	var updatesingleStolenimei4=$('#updatesingleStolenimei4').val();

	var singleStolendeviceIDType=$('#singleStolendeviceIDType').val();
	var singleStolendeviceType=$('#singleStolendeviceType').val();
	var singleStolenOperator=$('#singleStolenOperator').val();
	var singleStolenSimStatus=$('#singleStolenSimStatus').val();
	var singleStolenComplaintType=$('#singleStolenComplaintType').val();
	var trimContactNumber2 = $('#singleStolenphone2').val();
	var singleStolenphone2 =trimContactNumber2.replace(/[^A-Z0-9]/ig, "");
	var singleStolenmodalNumber= $('#editsingleStolenmodalNumber').val();

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
	var txnid=$('#existingStolenTxnId').val();
	var indivisualStolenfileName=$('#singleStolenFileName').val();
	var uploadFirFile=$('#uploadFirSingleName').val();
	
	
	var singleStolenOperator2=parseInt($('#singleStolenOperator3').val());
	var singleStolenOperator3=parseInt($('#singleStolenOperator4').val());
	var singleStolenOperator4=parseInt($('#singleStolenOperator5').val());
	var trimContactNumber3 = $('#singleStolenphone3').val();
	var singleStolenphone3 =trimContactNumber3.replace(/[^A-Z0-9]/ig, "");
	
	var trimContactNumber4 = $('#singleStolenphone4').val();
	var singleStolenphone4 =trimContactNumber4.replace(/[^A-Z0-9]/ig, "");
	
	var trimContactNumber5 = $('#singleStolenphone5').val();
	var singleStolenphone5 =trimContactNumber5.replace(/[^A-Z0-9]/ig, "");
	
	if(singleStolendeviceIDType==""){
		singleStolendeviceIDType=null;
	}
	
	if(singleStolendeviceType==""){
		singleStolendeviceType=null;
	}
	
	var stolenIndividualUserDB={
			"alternateContactNumber": singleStolenphone1,
			"commune": singleStolencommune,
			"complaintType": singleStolenComplaintType,
			"contactNumber": singleStolenphone2,
			"contactNumber2": singleStolenphone3,
			"contactNumber3": singleStolenphone4,
			"contactNumber4": singleStolenphone5,
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
			"imeiEsnMeid1": updatesingleStolenimei1,
			"imeiEsnMeid2": updatesingleStolenimei2,
			"imeiEsnMeid3": updatesingleStolenimei3,
			"imeiEsnMeid4": updatesingleStolenimei4,
			"lastName": singleStolenlastName,
			"locality": singleStolenlocality,
			"multiSimStatus": singleStolenSimStatus,
			"middleName": singleStolenmiddleName,
			"modelNumber":singleStolenmodalNumber,
			"nid": singleStolennIDPassportNumber,
			"operator": singleStolenOperator,
			"operator2": singleStolenOperator2,
			"operator3": singleStolenOperator3,
			"operator4": singleStolenOperator4,
			"phoneNo": singleStolenphone2,
			"postalCode": singleDevicepin,
			"propertyLocation": singleStolenaddress,
			"province": state,
			"remark": singleDeviceRemark,
			"street": singleStolenstreetNumber,
			"village":singleStolenvillage,
			"nidFileName":indivisualStolenfileName

	}
	var request={
			"txnId":txnid,
			"qty":1,
			"deviceQuantity":1,
			"dateOfStolen":IndivisualStolenDate,
			"blockingTimePeriod":blockingTimePeriod,
			"blockingType":blockingType,
			"requestType":0,
			"sourceType":5,
			"firFileName":uploadFirFile,
			"complaintType":$('#singleStolenComplaintType').val(),
			"stolenIndividualUserDB":stolenIndividualUserDB
	}
	formData.append('file', $('#singleStolenFile')[0].files[0]);
	formData.append('firFileName', $('#uploadFirSingle')[0].files[0]);
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
			////console.log(response);
			$('div#initialloader').delay(300).fadeOut('slow');
			if(response.errorCode==0){
				$("#IndivisualUpdateStolen").prop('disabled', true);
				$('#stolenSucessPopUp').openModal({dismissible:false});
			}
			else if(response.errorCode==5){
				
				$('#stolenSucessPopUp').openModal({dismissible:false});
				$('#dynamicMessage').text('');
				$('#dynamicMessage').text($.i18n(response.tag));
			}
			else{
				$('#stolenSucessPopUp').openModal({dismissible:false});
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



function isImageValid(id) {
	var uploadedFileName = $("#"+id).val();
	uploadedFileName = uploadedFileName.replace(/^.*[\\\/]/, '');
	////alert("file extension=="+uploadedFileName)
	var ext = uploadedFileName.split('.').pop();
	$('#FilefieldId').val(id);
	var fileSize = ($("#"+id)[0].files[0].size);
	/*fileSize = (Math.round((fileSize / 100000) * 100) / 100)
	//alert("----"+fileSize);*/
	fileSize = Math.floor(fileSize/1000) + 'KB';

	////alert(uploadedFileName+"----------"+ext+"----"+fileSize)
	var areEqual =ext.toLowerCase()=='png';
	////alert(areEqual);
	if(areEqual==true)
	{
		ext='PNG';
	}

	if (uploadedFileName.length > 30) {
		$('#fileFormateModal').openModal();
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

	var fieldId=$('#FilefieldId').val();
	//var existingFileName=$('#existingFileName').val();
	////alert("existingFileName=="+existingFileName);
	////alert(fieldId);
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
	$('#fileFormateModal').closeModal();
}












var lang = window.parent.$('#langlist').val() == 'km' ? 'km' : 'en';

$.i18n().locale = lang;
////alert(lang)
var successMsg, stolenIndivisual;

$.i18n().load({
	'en' : './resources/i18n/en.json',
	'km' : './resources/i18n/km.json'
}).done(function() {
	stolenIndivisual = $.i18n('stolenIndivisual');
	editstolenIndivisual = $.i18n('editstolenIndivisual');

	viewPageType();
});

function viewPageType() {
	if ($('#pageViewType').val() == 'view') {
		$('#headingType').text('');
		$('#headingType').text(stolenIndivisual);
		$("#passportImageDiv").removeClass("btn");
		$('#passportImageText').text('');
		$('#singleStolenFile').attr('type', 'text');
		$("#PassportNidLink").css("display", "block");
		$("#singleStolenFile").css("display", "none");

		$("#firImageDiv").removeClass("btn");
		$('#firDivText').text('');
		$('#uploadFirSingle').attr('type', 'text');
		$("#firImageLink").css("display", "block");
		$("#uploadFirSingle").css("display", "none");
		$("#sampleFileLink").css("display", "none");
		// //alert(stolenIndivisual);
		$("#IndivisualUpdateStolen").css("display", "none");
   		$(".star").css("display", "none");
		
   		
   		$("#singleDeviceRejectRemarkDiv").css("display", "block");
		$("#SingleForm").find("input,select,textarea,button").prop(
				"disabled", true);
		  $("#operator3span").css("display", "none");
		  $("#operator4span").css("display", "none");
		  $("#operator5span").css("display", "none");
	} else {
		$('#headingType').text('');
		$("#singleDeviceRejectRemarkDiv").css("display", "none");
		$('#headingType').text(editstolenIndivisual);
		$("#SingleForm").find("input,select,textarea,button").prop(
				"disabled", false);
	}

}







populateCountries("country2", "state2");
populateStates("country2", "state2");

populateCountries("country3", "state3");
populateStates("country3", "state3");


setTimeout(function(){


	


	$('.datepick').datepicker({
		dateFormat : "yy-mm-dd"
	});

	$('#stolenDatePeriodedit').datepicker({
		dateFormat : "yy-mm-dd",
		minDate: "0"
	});

	
	/*var input = document.querySelector("#singleStolenphone2");
	window.intlTelInput(input, {
		utilsScript : "${context}/resources/js/utils.js",
	});*/
	$('#singleStolenphone2').val(window.xop2);
}, 1000);


function changeBrandValue(brand_id){
	////alert("ss"+brand_id);
	//var brand_id = $('#editsingleStolendeviceBrandName').val();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
	headers:
	{ 'X-CSRF-TOKEN': token }
	});

	$.ajaxSetup({
		async: false
		});
	$.getJSON('./productModelList?brand_id=' + brand_id,
			function(data) {
				$("#editsingleStolenmodalNumber").empty();
				for (i = 0; i < data.length; i++) {
					$('<option>').val(data[i].id).text(
							data[i].modelName).appendTo(
							'#editsingleStolenmodalNumber');
				}
			});
}




function setOpertorTypeMandaotry()
{
	var mobilenumber2=$("#singleStolenphone3").val();
	 var mobilenumber3=$("#singleStolenphone4").val();
	 var mobilenumber4=$("#singleStolenphone5").val();
	 
	  if(mobilenumber2>1)
		  {
		  $("#operator3span").css("display", "block");
		  }

	  if(mobilenumber3>1)
		  {
		  $("#operator4span").css("display", "block");
		  }
	
	  if(mobilenumber4>1)
		  {
		  $("#operator5span").css("display", "block");
		  }	
}




$('#singleStolendeviceIDType').on('change', function() {
	var value=parseInt($(this).val());

	switch (value) {
	case 0:
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").val('');
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("pattern","[0-9]{15,16}");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("maxlength","16");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").removeAttr("onkeyup");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationIMEI')+"')");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationIMEI')+"')");
		$('#errorMsgOnModal').text($.i18n('IMEIMsg'));
		
		break;
	case 1:
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("pattern","[A-F0-9]{15,16}");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("maxlength","16");
        $("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").removeAttr("onkeyup");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationMEID')+"')");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationMEID')+"')");
		$('#errorMsgOnModal').text($.i18n('MEIDMsg'));
		break;
	case 2:
		
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").val('');
        $("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("pattern","[0-9]{8,11}");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("onkeyup","isLengthValid(this.value)");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("maxlength","11");	
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$("#errorMsgOnModal").text($.i18n('ESNMsg'));
		break;
	}

}); 

function isLengthValid(val){
	var deviceIDLength=val.length;
	if(!isNaN(val)){
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("pattern","[0-9]{11,11}");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("maxlength","11");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN11')+"')");
	}
	else if(typeof val == 'string' || val instanceof String){
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("maxlength","8");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("pattern","[A-F0-9]{8,8}");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninput","InvalidMsg(this,'input','"+$.i18n('validationESN8')+"')");
		$("#updatesingleStolenimei1,#updatesingleStolenimei2,#updatesingleStolenimei3,#updatesingleStolenimei4").attr("oninvalid","InvalidMsg(this,'input','"+$.i18n('validationESN8')+"')");

	}
}







